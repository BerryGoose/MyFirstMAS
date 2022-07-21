package org.example.reader;

import org.example.actors.Order;
import org.example.geometry.Point;
import org.example.geometry.SimpleSegment;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class OrderReader {
    static public List<Order> readOrder(String filePath){
        return readOrder(filePath, 120, 480);
    }

    static public List<Order> readOrder(String filePath, int pickupWindow, int deliveryWindow){

        List<Order> orderArray = new ArrayList<>();
        try{
            File input = new File(filePath);
            FileReader inputReader = new FileReader(input);
            BufferedReader reader = new BufferedReader(inputReader);
            String line = reader.readLine();

            while(line != null){;
                String[] strs = line.split(", ");
                String[] inpStart = strs[1].split(" ");
                String[] inpEnd = strs[2].split(" ");
                String orderTime = strs[3];

                double startTime = Double.parseDouble(orderTime);

                Point start = new Point(Double.parseDouble(inpStart[0]), Double.parseDouble(inpStart[1]));
                Point end = new Point(Double.parseDouble(inpEnd[0]),Double.parseDouble(inpEnd[1]));
                SimpleSegment startTimePeriod = new SimpleSegment(startTime, startTime + pickupWindow);
                SimpleSegment endTimePeriod = new SimpleSegment(startTime, startTime + deliveryWindow);

                Order newOrder = new Order(start, end, startTimePeriod, endTimePeriod);

                orderArray.add(newOrder);
                line = reader.readLine();
            }
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return orderArray;
    }
}
