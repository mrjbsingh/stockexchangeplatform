package com.deeprooted.ordermanager.controllers;

import java.lang.*;

import com.deeprooted.ordermanager.dto.OrderRequestDTO;
import com.deeprooted.ordermanager.models.BuyOrder;
import com.deeprooted.ordermanager.models.Order;
import com.deeprooted.ordermanager.models.ProduceFactory;
import com.deeprooted.ordermanager.models.SellOrder;
import com.deeprooted.ordermanager.models.producecomponent.Produce;
import com.deeprooted.ordermanager.services.BuyOrderService;
import com.deeprooted.ordermanager.services.SellOrderService;

public class OrderController {
    /*
        It will receive orderRequestDTO and based on orderId it will call respective Service
        if orderId contains 'd' --> BuyOrderService
        if orderId contains 's' --> SellOrderService
     */
    public void generateNewOder(OrderRequestDTO requestDTO){

        if(requestDTO.getOrderId().charAt(0) == 'd'){
            BuyOrderService buyOrderService = BuyOrderService.getBuyOrderServiceInstance();
            buyOrderService.createBuyOrder(requestDTO.getOrderId(), requestDTO.getTime(), requestDTO.getProduceName(),
                    requestDTO.getPricePerKg(), requestDTO.getQuantity());

        }
        else if(requestDTO.getOrderId().charAt(0) == 's'){
            SellOrderService sellOrderService = SellOrderService.getSellOrderServiceInstance();
            sellOrderService.createSellOrder(requestDTO.getOrderId(), requestDTO.getTime(), requestDTO.getProduceName(),
                    requestDTO.getPricePerKg(), requestDTO.getQuantity());

        }
    }



}
