package org.example.reader;

import org.example.actors.order.Order;
import org.example.constants.TimeConsts;
import org.example.geometry.Point;
import org.example.geometry.SimpleSegment;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public abstract class OrderReader {
    static public HashMap<Integer, Order> readOrder(String filePath){
        return readOrder(filePath, TimeConsts.STANDARTPICKUPWINDOW, TimeConsts.STANDARTDELIVERYWINDOW);
    }

    static public HashMap<Integer, Order> readOrder(String filePath, int pickupWindow, int deliveryWindow){
        HashMap<Integer, Order> orderHashMap = new HashMap<>();
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

                int arrivalTime = Integer.parseInt(strs[0]) * TimeConsts.MSINMINUTE;
                int startTime = Integer.parseInt(orderTime) * TimeConsts.MSINMINUTE;

                Point start = new Point(Double.parseDouble(inpStart[0]), Double.parseDouble(inpStart[1]));
                Point end = new Point(Double.parseDouble(inpEnd[0]),Double.parseDouble(inpEnd[1]));
                SimpleSegment startTimePeriod = new SimpleSegment(startTime, startTime + pickupWindow);
                SimpleSegment endTimePeriod = new SimpleSegment(startTime, startTime + deliveryWindow);

                Order newOrder = new Order(start, end, startTimePeriod, endTimePeriod);

                orderHashMap.put(arrivalTime, newOrder);
                line = reader.readLine();
            }
            reader.close();
            inputReader.close();
        } catch (IOException ex){
            ex.printStackTrace();
        }
        return orderHashMap;
    }
}
