package com.deeprooted.ordermanager.models;

import com.deeprooted.ordermanager.models.producecomponent.Produce;

public class SellOrder extends Order{

    //other SellOrder specific property

    public SellOrder(String orderId, long timeStamp, Produce produce, Long pricePerKg, Long quantity) {

        super(orderId, timeStamp, produce, pricePerKg, quantity, OrderType.SELL_ORDER);

    }

    //other SellOrder specific behaviours

    @Override
    public String toString() {
        return "SellOrder{}" + super.toString();
    }

}
