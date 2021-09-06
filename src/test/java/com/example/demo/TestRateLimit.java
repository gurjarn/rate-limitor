package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class TestRateLimit {
    
    
    @Test
    public void testRequestIsAllowed(){
        //setup data for last sliding window less than 10
        //pass the customer id and should return success (true)

        Map<Integer, SortedMap<Instant, Integer>> currentState = new HashMap<>();
        currentState.put(1, new TreeMap<>());
        RateLimitor limitor = new RateLimitor(currentState);
        limitor.rateLimit(1);

    }
    
    @Test
    public void testRequestDenied(){
        
        //setup data for last sliding window more than 10
        //pass the customer id and should return false;
        Assertions.fail();
    }
    
    @Test
    public void testMultipleRequests(){
        
    }
    
    @Test
    public void testSlidingWindowSuccess(){
        //make requests for failure (exhaust the sliding window)
        
        //wait for a sliding window duration to reset the limit
        
        //make a new request and assert that it's successful

        Assertions.fail();
        
    }
}
