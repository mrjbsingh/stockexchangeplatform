package com.deeprooted.ordermanager.dao;

import com.deeprooted.ordermanager.models.Ledger;

import java.util.HashMap;
import java.util.Map;

public class LedgerHardCodedDAO {

    private static Map<String, Ledger> produceLedgerMap;

    static {
        produceLedgerMap = new HashMap<>();
        Map<String, String> produceRegistry = ProduceRegistryDao.getProduceRegistry();
        for (Map.Entry<String, String> produce: produceRegistry.entrySet()){
            String produceId = produce.getValue();
            produceLedgerMap.put(produceId, new Ledger());
        }
    }

    public static Map<String, Ledger> getProduceLedgerMap(){
        return produceLedgerMap;
    }

    public static Ledger findLedgerByProduceID(String produceId){
        return produceLedgerMap.get(produceId);
    }
}
