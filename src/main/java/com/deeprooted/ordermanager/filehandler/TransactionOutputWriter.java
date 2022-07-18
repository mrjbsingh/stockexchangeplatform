package com.deeprooted.ordermanager.filehandler;

import com.deeprooted.ordermanager.models.Transaction;

import java.io.*;

public class TransactionOutputWriter {

    private String filePath;

    public TransactionOutputWriter(){
        this.filePath = "src/main/resources/output.txt";
        flushOldContent();
    }

    public void writeToOutputFile(Transaction transaction){

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(filePath, true));
            String outputFormat = covertTransactionToRequiredOutputFormat(transaction);
            out.write(outputFormat);
            out.newLine();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private String covertTransactionToRequiredOutputFormat(Transaction transaction){
        StringBuilder output = new StringBuilder();
        output.append(transaction.getBuyOrderId() + " ");
        output.append(transaction.getSellOrderId() + " ");
        output.append(transaction.getPricePerKg() + "/kg ");
        output.append(transaction.getQuantity() + "kg");
        return output.toString();
    }
    public void flushOldContent(){

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(filePath));
            out.write("");
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
