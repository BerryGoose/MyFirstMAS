package org.example;

import org.example.actors.order.Order;
import org.example.reader.OrderReader;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "data\\orders.csv";

        List<Order> orderArr = OrderReader.readOrder(filePath);
        for (Order order: orderArr){
            System.out.println(order.toString());
        }
    }
}