package com.deeprooted.ordermanager.services;

import com.deeprooted.ordermanager.exceptions.ProduceNotFoundException;
import com.deeprooted.ordermanager.models.BuyOrder;
import com.deeprooted.ordermanager.models.producecomponent.Produce;
import com.deeprooted.ordermanager.services.LedgerService;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class LedgerServiceTest {
    @Test
    void givenTwoLedgerServiceInstance_thenTestSingletonProperty(){
        LedgerService ledgerServiceInstance_1 = LedgerService.getLedgerServiceInstance();
        LedgerService ledgerServiceInstance_2 = LedgerService.getLedgerServiceInstance();
        assertSame(ledgerServiceInstance_1, ledgerServiceInstance_2, "LedgerService fails Singleton Pattern Test");
    }

    @Test
    void givenLedgerServiceInstance_registerUnknownProduceType_thenThrowProduceNotFoundException(){

        LedgerService ledgerServiceInstance = LedgerService.getLedgerServiceInstance();
        Produce unregisteredProduce = new Produce("Bt-brinjal", "produce-114");
        BuyOrder buyOrderUnregisteredProudce = new BuyOrder("d111", 36900000L, unregisteredProduce,
                24L, 123L);

        assertThrows(ProduceNotFoundException.class,
                ()->ledgerServiceInstance.registerBuyOderIntoLedger(buyOrderUnregisteredProudce),
                "Expected Produce Not Found Exception");
    }
}
