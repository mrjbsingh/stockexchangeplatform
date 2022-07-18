package com.deeprooted.ordermanager.models;

public class Transaction extends BaseEntity {
    private String buyOrderId;
    private String sellOrderId;
    private Long pricePerKg;
    private Long quantity;

    public Transaction(String buyOrderId, String sellOrderId, Long pricePerKg, Long quantity) {
        this.buyOrderId = buyOrderId;
        this.sellOrderId = sellOrderId;
        this.pricePerKg = pricePerKg;
        this.quantity = quantity;
    }

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return this.id;
    }

    public String getBuyOrderId() {
        return buyOrderId;
    }

    public String getSellOrderId() {
        return sellOrderId;
    }

    public Long getPricePerKg() {
        return pricePerKg;
    }


    public Long getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "buyOrderId='" + buyOrderId + '\'' +
                ", sellOrderId='" + sellOrderId + '\'' +
                ", pricePerKg=" + pricePerKg +
                ", quantity=" + quantity +
                '}';
    }
}
