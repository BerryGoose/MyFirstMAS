package org.example;

import org.example.actrors.Order;
import org.example.geometry.Point;
import org.example.geometry.SimpleSegment;

import java.io.*;

public abstract class OrderReader {

    static public Order[] readOrder(String filePath){
        int pickupWindow = 120;
        int deliveryWindow = 480;
        Order[] orderArray = new Order[0];
        try{
            File input = new File(filePath);
            FileReader inputReader = new FileReader(input);
            BufferedReader reader = new BufferedReader(inputReader);
            try{
                String line = reader.readLine();

                while(line != null){
                    String orderTime;
                    String[] inpStart;
                    String[] inpEnd;
                    String[] strs;

                    strs = line.split(", ");
                    inpStart = strs[1].split(" ");
                    inpEnd = strs[2].split(" ");
                    orderTime = strs[3];

                    double startTime = Double.parseDouble(orderTime);

                    Point start = new Point(Double.parseDouble(inpStart[0]), Double.parseDouble(inpStart[1]));
                    Point end = new Point(Double.parseDouble(inpEnd[0]),Double.parseDouble(inpEnd[1]));
                    SimpleSegment startTimePeriod = new SimpleSegment(startTime, startTime + pickupWindow);
                    SimpleSegment endTimePeriod = new SimpleSegment(startTime, startTime + deliveryWindow);

                    Order newOrder = new Order(start, end, startTimePeriod, endTimePeriod);

                    Order[] tempOrderArray = new Order[orderArray.length + 1];

                    System.arraycopy(orderArray, 0, tempOrderArray, 0, orderArray.length);
                    tempOrderArray[orderArray.length] = newOrder;
                    orderArray = tempOrderArray.clone();

                    line = reader.readLine();
                }
            }catch (IOException ex){
                ex.printStackTrace();
            }
        } catch (FileNotFoundException ex){
            ex.printStackTrace();
         }
        return orderArray;
    }
}
