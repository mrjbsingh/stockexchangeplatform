package com.deeprooted.ordermanager.services.matchingengine;

import com.deeprooted.ordermanager.models.*;
import com.deeprooted.ordermanager.models.producecomponent.Produce;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import static com.deeprooted.ordermanager.utils.Utility.convertTimeInMillis;

public class DemandSupplyMatchingEngineTest {

    @Test
    void givenOrderList_saveOrderToLedger_thenReturnTransaction(){
        Ledger potatoLedger = new Ledger();
        ArrayList<BuyOrder> buyOrders = new ArrayList<>();
        ArrayList<SellOrder> sellOrders = new ArrayList<>();

        Produce potato = ProduceFactory.createProduceInstance("potato");
        buyOrders.add(new BuyOrder("d1", convertTimeInMillis("09:45"), potato, 24L, 10L));
        buyOrders.add(new BuyOrder("d2", convertTimeInMillis("09:46"), potato, 24L, 50L));
        sellOrders.add(new SellOrder("s1", convertTimeInMillis("09:46"), potato, 24L, 50L));
        sellOrders.add(new SellOrder("s2", convertTimeInMillis("09:46"), potato, 24L, 10L));

        for (BuyOrder buyOrder: buyOrders){
            potatoLedger.saveBuyOrder(buyOrder);
        }
        for (SellOrder sellOrder: sellOrders){
            potatoLedger.saveSellOrder(sellOrder);
        }
        DemandSupplyMatchingEngine demandSupplyMatchingEngine = new FIFODemandSupplyMatchingEngine();
        ArrayList<Transaction> actualTransaction = demandSupplyMatchingEngine.demandSupplyMatching(potatoLedger);

        ArrayList<Transaction> expectedTransaction = new ArrayList<>();

        expectedTransaction.add(new Transaction("d1", "s1", 24L, 10L));
        expectedTransaction.add(new Transaction("d2", "s1", 24L, 40L));
        expectedTransaction.add(new Transaction("d2", "s2", 24L, 10L));

        assertArrayEquals(expectedTransaction, actualTransaction);

    }

    private void assertArrayEquals(ArrayList<Transaction> expectedTransaction, ArrayList<Transaction> actualTransaction) {
        int index = 0;
        for (Transaction transaction: expectedTransaction){
            assertEquals(transaction.toString(), actualTransaction.get(index).toString(), "Transaction is not as expected");
            index++;
        }
    }

}
