package org.example;

import org.example.actrors.Order;
import org.example.geometry.Point;
import org.example.geometry.SimpleSegment;

public abstract class OrderReader {

    public Order readOrder(String line){
        double pickupWindow = 120;
        double deliveryWindow = 480;

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
        Point end = new Point(Double.parseDouble(inpEnd[0]),Double.parseDouble(inpEnd[0]));
        SimpleSegment startTimePeriod = new SimpleSegment(startTime, startTime + pickupWindow);
        SimpleSegment endTimePeriod = new SimpleSegment(startTime, startTime + deliveryWindow);

        return  new Order(start, end, startTimePeriod, endTimePeriod);
    }
}
