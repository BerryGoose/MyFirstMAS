package org.example;

import org.example.actors.Order;
import org.example.reader.OrderReader;

public class Main {
    public static void main(String[] args) {
        String filePath = "Data\\orders.csv";

        Order[] orderArr = OrderReader.readOrder(filePath);
        for (Order order: orderArr){
            System.out.println(order.toString());
        }
    }
}