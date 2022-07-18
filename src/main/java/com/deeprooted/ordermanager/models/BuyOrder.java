package com.deeprooted.ordermanager.models;

import com.deeprooted.ordermanager.models.producecomponent.Produce;

public class BuyOrder extends Order{
    //other BuyOrder specific property

    public BuyOrder(String orderId, long timeStamp, Produce produce, Long pricePerKg, Long quantity) {

        super(orderId, timeStamp, produce, pricePerKg, quantity, OrderType.BUY_ORDER);

    }

    @Override
    public String toString() {
        return "BuyOrder{}" + super.toString();
    }
//other BuyOrder specific behaviours
}
