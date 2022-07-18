package com.deeprooted.ordermanager.models;

import com.deeprooted.ordermanager.models.producecomponent.Produce;

public class Order {
    private String orderId;
    private long timeStamp;
    private Produce produce;
    private Long pricePerKg;
    private Long quantity;
    private OrderType orderType;

    public Order(String orderId, long timeStamp, Produce produce, Long pricePerKg, Long quantity, OrderType orderType) {
        this.orderId = orderId;
        this.timeStamp = timeStamp;
        this.produce = produce;
        this.pricePerKg = pricePerKg;
        this.quantity = quantity;
        this.orderType = orderType;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Produce getProduce() {
        return produce;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public void setProduce(Produce produce) {
        this.produce = produce;
    }

    public Long getPricePerKg() {
        return pricePerKg;
    }

    public void setPricePerKg(Long pricePerKg) {
        this.pricePerKg = pricePerKg;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", timeStamp=" + timeStamp +
                ", produce=" + produce +
                ", pricePerKg=" + pricePerKg +
                ", quantity=" + quantity +
                ", orderType=" + orderType +
                '}';
    }
}
