package com.deeprooted.ordermanager.models;

import java.util.Comparator;
import java.util.PriorityQueue;
/*
    Each produce will have its own ledger which contain all buyOrder and sellOrder
    into respective priority. All buyOrder will be arranged such that maximum priced
    buyOrder will be at highest priority for execution.
    Similarly, all sellOrder arrange in low to High priority.
    At any time ledger will look like -
    SellOrder Price     BuyOrder Price
        10                  8
        11                  7
        14                  6
        17

 */
public class Ledger {
    private PriorityQueue<BuyOrder> buyOrders;
    private PriorityQueue<SellOrder> sellOrders;

    public Ledger(){
        this.buyOrders = new PriorityQueue<BuyOrder>(new HighToLowComparator());
        this.sellOrders = new PriorityQueue<SellOrder>(new LowToHighComparator());
    }

    public void saveBuyOrder(BuyOrder buyOrder){
        this.buyOrders.add(buyOrder);
    }

    public void saveSellOrder(SellOrder sellOrder){
        this.sellOrders.add(sellOrder);
    }

    public void removeBuyOrder(BuyOrder buyOrder){
        this.buyOrders.remove(buyOrder);
    }

    public void removeSellOrder(SellOrder sellOrder){
        this.sellOrders.remove(sellOrder);
    }

    public BuyOrder getTopBuyOrders(){
        return this.buyOrders.poll();
    }

    public boolean isBuyOrderEmpty(){
        return this.buyOrders.isEmpty();
    }

    public SellOrder getTopSellOrders(){
        return this.sellOrders.poll();
    }

    public boolean isSellOrderEmpty(){
        return this.sellOrders.isEmpty();
    }

    /*
        It will sort the elements in PriorityQueue from low to High Price ( ascending order)
        based on price and if price is same then time of order generation is given priority
     */
    class LowToHighComparator implements Comparator<Order> {

        public int compare(Order order1, Order order2){

            if(order1.getPricePerKg() == order2.getPricePerKg()){
                Long time1 = order1.getTimeStamp();
                Long time2 = order2.getTimeStamp();
                if( time1.compareTo(time2)==0){
                    return order1.getOrderId().compareTo(order2.getOrderId());
                }
                else
                    return time1.compareTo(time2);
            }
            else if(order1.getPricePerKg() > order2.getPricePerKg()){
                return 1;
            }
            else {
                return -1;
            }

        }
    }
        /*
           It will sort the elements in PriorityQueue from high to low Price ( descending order)
           based on price and if price is same then time of order generation is given priority.
        */
    class HighToLowComparator implements Comparator<Order> {

        public int compare(Order order1, Order order2){

            if(order1.getPricePerKg() == order2.getPricePerKg()){
                Long time1 = order1.getTimeStamp();
                Long time2 = order2.getTimeStamp();
                if( time1.compareTo(time2)==0){
                    return order1.getOrderId().compareTo(order2.getOrderId());
                }
                else
                    return time1.compareTo(time2);
            }
            else if(order2.getPricePerKg() > order1.getPricePerKg()){
                return 1;
            }
            else {
                return -1;
            }
        }
    }
}
