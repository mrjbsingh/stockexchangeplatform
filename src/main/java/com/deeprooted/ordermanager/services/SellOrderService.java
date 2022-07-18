package com.deeprooted.ordermanager.services;

import com.deeprooted.ordermanager.models.ProduceFactory;
import com.deeprooted.ordermanager.models.SellOrder;
import com.deeprooted.ordermanager.models.producecomponent.Produce;

import static com.deeprooted.ordermanager.utils.Utility.convertTimeInMillis;

public class SellOrderService {
    private static SellOrderService sellOrderServiceInstance;
    private LedgerService ledgerService;

    private SellOrderService() {
        ledgerService = LedgerService.getLedgerServiceInstance();
    }

    public static SellOrderService getSellOrderServiceInstance(){
        if(sellOrderServiceInstance != null){
            return sellOrderServiceInstance;
        }
        synchronized (SellOrderService.class){
            if(sellOrderServiceInstance == null){
                sellOrderServiceInstance = new SellOrderService();
            }
            return sellOrderServiceInstance;
        }
    }
    public void createSellOrder(String orderId, Long time, String produceName, Long pricePerKg, Long quantity){

        Produce produce = ProduceFactory.createProduceInstance(produceName);
        SellOrder sellOrder = new SellOrder(orderId, time, produce, pricePerKg, quantity);

        registerSellOrder(sellOrder);

    }

    public void registerSellOrder(SellOrder sellOrder){
        /*
        this function call ledgerService to register this new SellOrder
        this legerService have all Information
        regarding different types of ledger for each produce from LedgerDAO
        */
        try {
            this.ledgerService.registerSellOderIntoLedger(sellOrder);
        }catch (Exception e){

        }


    }
}
