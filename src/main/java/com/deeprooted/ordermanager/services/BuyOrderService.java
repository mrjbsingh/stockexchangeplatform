package com.deeprooted.ordermanager.services;

import com.deeprooted.ordermanager.models.BuyOrder;
import com.deeprooted.ordermanager.models.ProduceFactory;
import com.deeprooted.ordermanager.models.producecomponent.Produce;

/*
       BuyOrderService would be Singleton class. So each buy request
       will be getting register through this service
*/
public class BuyOrderService {
    private static BuyOrderService buyOrderServiceInstance;
    private LedgerService ledgerService;

    private BuyOrderService() {
        ledgerService = LedgerService.getLedgerServiceInstance();
    }

    public static BuyOrderService getBuyOrderServiceInstance(){
        if(buyOrderServiceInstance != null){
            return buyOrderServiceInstance;
        }
        synchronized (BuyOrderService.class){
            if(buyOrderServiceInstance == null){
                buyOrderServiceInstance = new BuyOrderService();
            }
            return buyOrderServiceInstance;
        }
    }
    /*
        creating a BuyOrder object and registering it to
        respective ledger ( each produce will have its own ledger)
     */
    public void createBuyOrder(String orderId, Long time, String produceName, Long pricePerKg, Long quantity){

        Produce produce = ProduceFactory.createProduceInstance(produceName);
        BuyOrder buyOrder = new BuyOrder(orderId, time, produce, pricePerKg, quantity);

        registerBuyOrder(buyOrder);

    }
    /*
        this function call ledgerService to register this new BuyOrder
        this legerService have all Information
        regarding different types of ledger for each produce from LedgerDAO
    */
    public void registerBuyOrder(BuyOrder buyOrder){

        try {
            this.ledgerService.registerBuyOderIntoLedger(buyOrder);
        }catch (Exception e){

        }


    }

}
