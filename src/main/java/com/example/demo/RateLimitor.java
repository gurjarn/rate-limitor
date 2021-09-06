package com.example.demo;

import java.time.Instant;
import java.util.Map;
import java.util.SortedMap;

public class RateLimitor {
    
    private Map<Integer, SortedMap<Instant, Integer>> currentState;
    
    
    public RateLimitor(Map<Integer, SortedMap<Instant, Integer>> currentState){
        this.currentState = currentState;
    }
    
    public boolean rateLimit(int customerId){
        
        int totalProcessedrequests = 0;
        //some validations on customer id
        
        //get the current state of the customer id
        SortedMap<Instant, Integer> currentStateCustomer = currentState.get(customerId);

        Instant currentTime = Instant.now();
        
        //compare each entry in key set with time
        
        //traverse through each entry and see if the entry is in the duration
        totalProcessedrequests = currentStateCustomer.entrySet()
                .stream()
                .filter( e -> e.getKey().isAfter(currentTime.minusSeconds(RateLimts.DEFAULT_SLIDING_WINDOW)))
                .map(e -> e.getValue())
                .reduce(0, Integer::sum);
        //modify currentstate with new request count
        currentStateCustomer.computeIfPresent(currentTime, (k,v) -> v +1);
        
        //clean up entries past the sliding window
        currentStateCustomer.entrySet().removeIf(e -> e.getKey().isBefore(currentTime.minusSeconds(RateLimts.DEFAULT_SLIDING_WINDOW)));
        currentState.put(customerId, currentStateCustomer);
        
        return (totalProcessedrequests < RateLimts.DEFAULT_RATE_LIMIT);
    }
}
