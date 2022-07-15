package org.example;

import org.example.actrors.Order;
import org.example.geometry.Point;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        String filePath = "Data\\orders.csv";

        Order[] orderArr = OrderReader.readOrder(filePath);
        for (Order order: orderArr){
            System.out.println(order.toString());
        }
    }
}