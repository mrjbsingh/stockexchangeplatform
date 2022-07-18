package com.deeprooted.ordermanager.services.matchingstrategy;

import com.deeprooted.ordermanager.models.BuyOrder;
import com.deeprooted.ordermanager.models.Ledger;
import com.deeprooted.ordermanager.models.SellOrder;
import com.deeprooted.ordermanager.models.Transaction;

import java.util.ArrayList;
/*
    This is a concrete strategy class. It will implement
    MatchingStrategy Interface and override computeMatching
    to perform Matching Algorithm.
    FIFO matching strategy, computeMatching function compute based on
    simple price match and FIFO logic
 */
public class FIFOMatchingStrategy implements MatchingStrategy{

    @Override
    public ArrayList<Transaction> computeMatching(Ledger ledger) {
        ArrayList<Transaction> transactions = new ArrayList<>();
        Transaction transaction;
        BuyOrder topBuyOrder = ledger.getTopBuyOrders();
        SellOrder topSellOrder = ledger.getTopSellOrders();

        //if BuyOrder price is enough to satisfy sellOrder it will
        // recursively settle all transaction using while loop

        while ( topBuyOrder!= null && topSellOrder != null && topBuyOrder.getPricePerKg() >= topSellOrder.getPricePerKg()){

            if(topBuyOrder.getQuantity() == topSellOrder.getQuantity()){

                transaction = new Transaction(topBuyOrder.getOrderId(), topSellOrder.getOrderId(),
                        topSellOrder.getPricePerKg(), topSellOrder.getQuantity());

            }
            else if(topBuyOrder.getQuantity() > topSellOrder.getQuantity()){

                transaction = new Transaction(topBuyOrder.getOrderId(), topSellOrder.getOrderId(),
                        topSellOrder.getPricePerKg(), topSellOrder.getQuantity());

                Long quantityLeft = topBuyOrder.getQuantity() - topSellOrder.getQuantity();
                topBuyOrder.setQuantity(quantityLeft);

                //adding that buyOrder again in queue

                ledger.saveBuyOrder(topBuyOrder);
            }
            else {   //if sellQuantity is more than buyQuantity

                transaction = new Transaction(topBuyOrder.getOrderId(), topSellOrder.getOrderId(),
                        topSellOrder.getPricePerKg(), topBuyOrder.getQuantity());

                Long quantityLeft = topSellOrder.getQuantity() - topBuyOrder.getQuantity();
                topSellOrder.setQuantity(quantityLeft);

                //adding that SellOrder again in queue

                ledger.saveSellOrder(topSellOrder);
            }

            transactions.add(transaction);

            //initializing topOrder & topSellOrder to check if it further satisfies any order

            topBuyOrder = ledger.getTopBuyOrders();
            topSellOrder = ledger.getTopSellOrders();
        }

        //saving the last order poped out of queue as it while not satisfied the while condition
        //adding it back to priority queue

        if(topBuyOrder != null)
            ledger.saveBuyOrder(topBuyOrder);
        if(topSellOrder != null)
            ledger.saveSellOrder(topSellOrder);


        //returning all the transaction list
        return transactions;
    }
}
