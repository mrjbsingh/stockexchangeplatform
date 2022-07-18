package com.deeprooted.ordermanager.filehandler;

import com.deeprooted.ordermanager.dto.OrderRequestDTO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import static com.deeprooted.ordermanager.utils.Utility.convertTimeInMillis;

public class InputReader {

    private String filePath;

    public InputReader(String filePath){
        this.filePath = filePath;
    }

    public ArrayList<OrderRequestDTO> getRequestDtoListFromInput(){
        ArrayList<OrderRequestDTO> orderRequestDtoList = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(filePath));

            while (scanner.hasNext()){
                String input[] = scanner.nextLine().toString().split(" ");
                orderRequestDtoList.add(new OrderRequestDTO(input[0], convertTimeInMillis(input[1]), input[2],
                        fetchPricePerKg(input[3]), fetchQuantityInKg(input[4])));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        /*
            sorting Input orderRequest to make proper time based input stream
         */
        Collections.sort(orderRequestDtoList, new sortTimeBased());

        return orderRequestDtoList;
    }

    private Long fetchPricePerKg(String str){
        str = str.replace("/kg","");
        return Long.parseLong(str);
    }
    private Long fetchQuantityInKg(String str){
        str = str.replace("kg","");
        return Long.parseLong(str);
    }
    class sortTimeBased implements Comparator<OrderRequestDTO> {

        @Override
        public int compare(OrderRequestDTO request1, OrderRequestDTO request2) {
            return request1.getTime().compareTo(request2.getTime());
        }
    }
}
