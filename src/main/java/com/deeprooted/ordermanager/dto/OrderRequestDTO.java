package com.deeprooted.ordermanager.dto;

public class OrderRequestDTO {
    private String orderId;
    private Long time;
    private String produceName;
    private Long pricePerKg;
    private Long quantity;

    public OrderRequestDTO(String orderId, Long time, String produceName, Long pricePerKg, Long quantity) {
        this.orderId = orderId;
        this.time = time;
        this.produceName = produceName;
        this.pricePerKg = pricePerKg;
        this.quantity = quantity;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getProduceName() {
        return produceName;
    }

    public void setProduceName(String produceName) {
        this.produceName = produceName;
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
        return "OrderRequestDTO{" +
                "orderId='" + orderId + '\'' +
                ", time='" + time + '\'' +
                ", produceName='" + produceName + '\'' +
                ", pricePerKg=" + pricePerKg +
                ", quantity=" + quantity +
                '}';
    }
}
