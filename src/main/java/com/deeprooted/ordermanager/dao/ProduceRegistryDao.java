package com.deeprooted.ordermanager.dao;

import java.util.HashMap;
import java.util.Map;
/*
    This will store all the produce and its corresponding id
    To add any new produce we need to follow this steps:-
    1- Add it into produceRegistry Map
    2- Make its concrete class in Model
    3- Register that class into ProduceFactory
 */
public class ProduceRegistryDao {

    private static Map<String, String> produceRegistry;

    static {
        produceRegistry = new HashMap<>();
        produceRegistry.put("POTATO", "produce-1");
        produceRegistry.put("TOMATO", "produce-2");
        produceRegistry.put("LADYFINGER", "produce-3");
        produceRegistry.put("ONION", "produce-4");
    }

    public static Map<String, String> getProduceRegistry(){
        return produceRegistry;
    }
}
