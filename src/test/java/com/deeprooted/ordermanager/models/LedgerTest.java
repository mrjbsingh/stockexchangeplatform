package com.deeprooted.ordermanager.models;

import com.deeprooted.ordermanager.models.producecomponent.Produce;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

import static com.deeprooted.ordermanager.utils.Utility.convertTimeInMillis;

public class LedgerTest {
    @Test
    void givenListBuyOrder_whenSaveOrder_thenReturnOrderInPriority(){
        Ledger potatoLedger = new Ledger();
        ArrayList<BuyOrder> buyOrders = new ArrayList<>();

        Produce potato = ProduceFactory.createProduceInstance("potato");
        buyOrders.add(new BuyOrder("d1", convertTimeInMillis("09:45"), potato, 24L, 10L));
        buyOrders.add(new BuyOrder("d2", convertTimeInMillis("09:46"), potato, 24L, 50L));
        buyOrders.add(new BuyOrder("d3", convertTimeInMillis("09:47"), potato, 28L, 50L));
        buyOrders.add(new BuyOrder("d4", convertTimeInMillis("09:47"), potato, 25L, 50L));

        for (BuyOrder buyOrder: buyOrders){
            potatoLedger.saveBuyOrder(buyOrder);
        }
        ArrayList<BuyOrder> expected = new ArrayList<>();
        expected.add(new BuyOrder("d3", convertTimeInMillis("09:47"), potato, 28L, 50L));
        expected.add(new BuyOrder("d4", convertTimeInMillis("09:47"), potato, 25L, 50L));
        expected.add(new BuyOrder("d1", convertTimeInMillis("09:45"), potato, 24L, 10L));
        expected.add(new BuyOrder("d2", convertTimeInMillis("09:46"), potato, 24L, 50L));
        int index = 0;

        while (!potatoLedger.isBuyOrderEmpty()){
            BuyOrder actualBuyOrder = potatoLedger.getTopBuyOrders();
            assertEquals(expected.get(index).getOrderId(), actualBuyOrder.getOrderId(), "BuyOrder is not as expected");
            index++;
        }

    }
    @Test
    void givenListSellOrder_whenSaveOrder_thenReturnOrderInPriority(){
        Ledger potatoLedger = new Ledger();

        ArrayList<SellOrder> sellOrders = new ArrayList<>();

        Produce potato = ProduceFactory.createProduceInstance("potato");
        sellOrders.add(new SellOrder("s1", convertTimeInMillis("09:46"), potato, 24L, 50L));
        sellOrders.add(new SellOrder("s2", convertTimeInMillis("09:46"), potato, 18L, 10L));
        sellOrders.add(new SellOrder("s3", convertTimeInMillis("09:46"), potato, 20L, 50L));
        sellOrders.add(new SellOrder("s4", convertTimeInMillis("09:46"), potato, 22L, 10L));

        for (SellOrder sellOrder: sellOrders){
            potatoLedger.saveSellOrder(sellOrder);
        }

        ArrayList<SellOrder> expected = new ArrayList<>();
        expected.add(new SellOrder("s2", convertTimeInMillis("09:46"), potato, 18L, 10L));
        expected.add(new SellOrder("s3", convertTimeInMillis("09:46"), potato, 20L, 50L));
        expected.add(new SellOrder("s4", convertTimeInMillis("09:46"), potato, 22L, 10L));
        expected.add(new SellOrder("s1", convertTimeInMillis("09:46"), potato, 24L, 50L));

        int index = 0;

        while (!potatoLedger.isSellOrderEmpty()){
            SellOrder actualSellOrder = potatoLedger.getTopSellOrders();
            assertEquals(expected.get(index).getOrderId(), actualSellOrder.getOrderId(), "SellOrder is not as expected");
            index++;
        }

    }
}
