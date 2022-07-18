package com.deeprooted.ordermanager.services;

import com.deeprooted.ordermanager.dao.LedgerHardCodedDAO;
import com.deeprooted.ordermanager.dao.TransactionDAO;
import com.deeprooted.ordermanager.exceptions.ProduceNotFoundException;
import com.deeprooted.ordermanager.models.BuyOrder;
import com.deeprooted.ordermanager.models.Ledger;
import com.deeprooted.ordermanager.models.SellOrder;
import com.deeprooted.ordermanager.models.Transaction;
import com.deeprooted.ordermanager.services.matchingengine.DemandSupplyMatchingEngine;
import com.deeprooted.ordermanager.services.matchingengine.FIFODemandSupplyMatchingEngine;

import java.util.ArrayList;
import java.util.Map;
/*
    LedgerService is singleton class. This class is having Map of each produce
    and their respective Ledger. For e.g.-
            All tomato Buy/Sell order will be saved in tomato Ledger
            All potato Buy/Sell order will be saved in potato Ledger
    This separation of Ledger for each produce will improve system performance
    as we need to compute in lesser data.
 */
public class LedgerService {
    private static LedgerService ledgerServiceInstance;
    private DemandSupplyMatchingEngine demandSupplyMatchingEngine;
    private TransactionDAO transactionDAO;
    private Map<String, Ledger> produceLedgerMap;

    private LedgerService(){
        produceLedgerMap = LedgerHardCodedDAO.getProduceLedgerMap();
        /*
            concrete class of FIFO DemandSupplyEngine is
            passed to perform all matching using FIFOAlgorithm in Future other
            Algorithmic Strategy can be passed
         */
        demandSupplyMatchingEngine = new FIFODemandSupplyMatchingEngine();
        transactionDAO = new TransactionDAO();
    }

    public static LedgerService getLedgerServiceInstance(){
        if(ledgerServiceInstance != null){
            return ledgerServiceInstance;
        }
        synchronized (LedgerService.class){
            if(ledgerServiceInstance == null){
                ledgerServiceInstance = new LedgerService();
            }
            return ledgerServiceInstance;
        }
    }

    public void setDemandSupplyMatchingEngine(DemandSupplyMatchingEngine demandSupplyMatchingEngine) {
        this.demandSupplyMatchingEngine = demandSupplyMatchingEngine;
    }

    public Map<String, Ledger> getProduceLedgerMap(){
        return this.produceLedgerMap;
    }
    /*
        Here all buOrder of respective produce is saved into
        its own Ledger using produceLedgerMap
        After adding order it will trigger DemandSupplyMatchingEngine
        to performOrderMatching into that ledger
     */
    public void registerBuyOderIntoLedger(BuyOrder buyOrder){

        String produceId = buyOrder.getProduce().getProduceId();
        Ledger ledger;
        if(produceLedgerMap.containsKey(produceId)){
            ledger = produceLedgerMap.get(produceId);   // it will fetch respective produce Ledger
            ledger.saveBuyOrder(buyOrder);              // save order into that ledger

            performOrderMatching(ledger);               // it will trigger demandSupplyMatchingEngine
        }
        else {
             /*
                It will through produceNotFoundException.
                If that produce is not register into Produce Registry
                So, Please add the produce into registry
             */
            throw new ProduceNotFoundException(" Ledger for ProduceId " + produceId + " not exist. Please make sure produce is register");
        }

    }
    /*
        Here all SellOrder of respective produce is saved into
        its own Ledger using produceLedgerMap
        After adding order it will trigger DemandSupplyMatchingEngine
        to performOrderMatching into that ledger
    */
    public void registerSellOderIntoLedger(SellOrder sellOrder){

        String produceId = sellOrder.getProduce().getProduceId();
        Ledger ledger;
        if(produceLedgerMap.containsKey(produceId)){
            ledger = produceLedgerMap.get(produceId);   // it will fetch respective produce Ledger
            ledger.saveSellOrder(sellOrder);            // save order into that ledger

            performOrderMatching(ledger);               // it will trigger demandSupplyMatchingEngine
        }
        else {
            /*
                It will through produceNotFoundException.
                If that produce is not register into Produce Registry
                So, Please add the produce into registry
             */
            throw new ProduceNotFoundException(" Ledger for ProduceId " + produceId + " not exist. Please make sure produce is register");
        }

    }
    /*
        As shown as any new entry either Buy or Sell is added to Ledger
        performOrderMatching will trigger DemandSupplyMatchingEngine
        It will compute transaction based on different strategy and
        return list of transaction
     */
    public void performOrderMatching(Ledger ledger){

        ArrayList<Transaction> transactions = demandSupplyMatchingEngine.demandSupplyMatching(ledger);

        if(!transactions.isEmpty()){
            saveTransactionToDAO(transactions);     //This List of transaction is saved into TransactionDAO
        }

    }
    /*
        It will generate transactionId and then save it to transaction Dao
     */
    private void saveTransactionToDAO(ArrayList<Transaction> transactions){
        for (Transaction transaction: transactions){
            Long id = (long)(transactionDAO.size() + 1);        //generating transaction based on size
                                                                //transactionDAO
            transaction.setId(id);
            transactionDAO.save(transaction);
        }
    }
}
