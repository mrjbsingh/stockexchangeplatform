package com.deeprooted.ordermanager.services.matchingengine;

import com.deeprooted.ordermanager.services.matchingstrategy.FIFOMatchingStrategy;
/*
    This class is child class of DemandSupplyMatchingEngine.
    It takes FIFO Matching Strategy using constructor Injection
 */
public class FIFODemandSupplyMatchingEngine extends DemandSupplyMatchingEngine {

    public FIFODemandSupplyMatchingEngine(){
        this.matchingStrategy = new FIFOMatchingStrategy();
    }

}
