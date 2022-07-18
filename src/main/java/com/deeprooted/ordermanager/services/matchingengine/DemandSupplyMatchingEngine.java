package com.deeprooted.ordermanager.services.matchingengine;

import com.deeprooted.ordermanager.models.Ledger;
import com.deeprooted.ordermanager.models.Transaction;
import com.deeprooted.ordermanager.services.matchingstrategy.MatchingStrategy;

import java.util.ArrayList;
/*
    Base class for all DemandSupplyMatchingEngine
    Each concrete class will have its own MatchingStrategy.
    This class is following Strategy Design Pattern
 */
public abstract class DemandSupplyMatchingEngine {

    /*
        Different matchingStrategy can be passed into DemandSupplyMatchingEngine
        using composition and MatchingStrategy Interface
     */
    MatchingStrategy matchingStrategy;

    public ArrayList<Transaction> demandSupplyMatching(Ledger ledger){
        return matchingStrategy.computeMatching(ledger);
    }

}
