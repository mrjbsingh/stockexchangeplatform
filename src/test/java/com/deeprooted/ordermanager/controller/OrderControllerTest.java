package com.deeprooted.ordermanager.controller;

import com.deeprooted.ordermanager.controllers.OrderController;
import com.deeprooted.ordermanager.dto.OrderRequestDTO;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import static com.deeprooted.ordermanager.utils.Utility.convertTimeInMillis;
import static org.junit.jupiter.api.Assertions.*;

public class OrderControllerTest {
    String outputFilePath = "src/main/resources/output.txt";
    @Test
    void givenListOfOrderRequest_saveToLedger_thenCheckTransactionsInOutputFile(){
        OrderController orderController = new OrderController();

        ArrayList<OrderRequestDTO> orderRequestDtoList = new ArrayList<>();

        orderRequestDtoList.add(new OrderRequestDTO("s1", convertTimeInMillis("09:45"), "tomato", 24L, 100L));
        orderRequestDtoList.add(new OrderRequestDTO("s1", convertTimeInMillis("09:45"), "tomato", 24L, 100L));
        orderRequestDtoList.add(new OrderRequestDTO("s2", convertTimeInMillis("09:46"), "tomato", 20L, 90L));
        orderRequestDtoList.add(new OrderRequestDTO("d1", convertTimeInMillis("09:47"), "tomato", 22L, 110L));
        orderRequestDtoList.add(new OrderRequestDTO("d2", convertTimeInMillis("09:48"), "tomato", 21L, 10L));
        orderRequestDtoList.add(new OrderRequestDTO("d3", convertTimeInMillis("09:49"), "tomato", 21L, 40L));
        orderRequestDtoList.add(new OrderRequestDTO("s3", convertTimeInMillis("09:50"), "tomato", 19L, 50L));

        for(OrderRequestDTO orderRequestDTO: orderRequestDtoList){
            orderController.generateNewOder(orderRequestDTO);
        }

        ArrayList<String> expected = new ArrayList<>();

        expected.add("d1 s2 20/kg 90kg");
        expected.add("d1 s3 19/kg 20kg");
        expected.add("d2 s3 19/kg 10kg");
        expected.add("d3 s3 19/kg 20kg");

        try {
            Scanner scanner = new Scanner(new File(outputFilePath));
            int index = 0;
            while (scanner.hasNext()){
                String actual = scanner.nextLine().toString();
                assertEquals(expected.get(index), actual, "transaction is not as expected");
                index++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
