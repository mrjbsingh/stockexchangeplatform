package com.deeprooted.ordermanager.models;

import com.deeprooted.ordermanager.dao.ProduceRegistryDao;
import com.deeprooted.ordermanager.models.producecomponent.*;

import java.util.HashMap;
import java.util.Map;

public class ProduceFactory {

    private static Map<String, String> produceRepository;

    static {
        produceRepository = ProduceRegistryDao.getProduceRegistry();
    }
    public static Produce createProduceInstance(String produceName){

        try{
            switch (produceName.toUpperCase()){
                case "POTATO" :
                    return new Potato(produceName.toUpperCase(), produceRepository.get(produceName.toUpperCase()));
                case "TOMATO" :
                    return new Tomato(produceName.toUpperCase(), produceRepository.get(produceName.toUpperCase()));
                case "LADYFINGER" :
                    return new LadyFinger(produceName.toUpperCase(), produceRepository.get(produceName.toUpperCase()));
                case "ONION" :
                    return new Onion(produceName.toUpperCase(), produceRepository.get(produceName.toUpperCase()));
                default:
                    throw new RuntimeException("Unknown produceType " + produceName);
            }
        }catch (Exception ex){
            System.out.println("Unknown produceType " + produceName);
        }
        return null;
    }
}
