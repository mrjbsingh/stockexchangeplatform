package com.deeprooted.ordermanager.services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

public class BuyOrderServiceTest {
    @Test
    void givenTwoBuyOderServiceInstance_thenTestSingletonProperty(){
        BuyOrderService buyOrderServiceInstance_1 = BuyOrderService.getBuyOrderServiceInstance();
        BuyOrderService buyOrderServiceInstance_2 = BuyOrderService.getBuyOrderServiceInstance();
        assertSame(buyOrderServiceInstance_1, buyOrderServiceInstance_2, "BuyOderService fails Singleton Pattern Test");
    }
}
