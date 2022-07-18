package com.deeprooted.ordermanager;

import com.deeprooted.ordermanager.controllers.OrderController;
import com.deeprooted.ordermanager.dto.OrderRequestDTO;
import com.deeprooted.ordermanager.filehandler.InputReader;

import java.util.ArrayList;

public class DemandSupplyMatchingApplication {

    public static void main(String[] args) {
        /*
            we can provide the input through input file in resource
            where each line will contain order request in format -
            <order-id> <time> <produce> <price/kg> <quantity in kg>
            s1 09:45 tomato 24/kg 100kg
            d1 09:47 tomato 22/kg 110kg
         */
        String inputFilePath = "src/main/resources/input.txt";

        OrderController orderController = new OrderController();
        InputReader inputReader = new InputReader(inputFilePath);

        /*
            getRequestDtoList will make request Dto object by reading
            all the data from the input file and pass each request to orderController
            Flow of the code-
            OrderController --> (Buy/Sell)OrderService-->LedgerService--> DemandSupplyMatchingEngine

            if any DemandSupply is satisfied engine will create List of Transaction
            which will be stored in TransactionDAO and then to output.txt(inside resource folder)
            output format:- <demand-order-id> <supply-order-id> <price/kg> <quantity in kg>
            d1 s2 20/kg 90kg
            d1 s3 19/kg 20kg
         */
        ArrayList<OrderRequestDTO> orderRequestDtoList = inputReader.getRequestDtoListFromInput();

        for(OrderRequestDTO orderRequestDTO: orderRequestDtoList){
            orderController.generateNewOder(orderRequestDTO);
        }


    }
}
