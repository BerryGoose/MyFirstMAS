package org.example;

import org.example.actors.DispatcherActor;
import org.example.actors.courier.CourierActor;
import org.example.actors.order.Order;
import org.example.actors.order.OrderActor;
import org.example.constants.SpeedConsts;
import org.example.constants.TimeConsts;
import org.example.geometry.Point;
import org.example.geometry.SimpleSegment;
import org.example.reader.OrderReader;

import java.util.HashMap;

public class Main {
    public static void System(int couriersAmount){
        DispatcherActor dispatcher = new DispatcherActor("Dispatcher");
        for(int i = 1;i <= couriersAmount; i++){
            new CourierActor(dispatcher.getMyAddress(), new Point(), "Courier" + String.valueOf(i), SpeedConsts.FOURTYKMH);
        }
        String filePath = "data/orders.csv";
        HashMap<Integer, Order> orderHashMap = OrderReader.readOrder(filePath);
        int orderCount = 0;
        for(int time = 0; time < TimeConsts.MSINDAY; time++){
            if (orderHashMap.containsKey(time)){
                Order newOrder = orderHashMap.get(time);
                orderCount++;
                new OrderActor(newOrder, "Order" + String.valueOf(orderCount), dispatcher.getMyAddress());
            }
            dispatcher.update();
        }
    int breakpoint;
    }
    public static void testSystem(){
        DispatcherActor dispatcher = new DispatcherActor("Dispatcher");
        CourierActor courier1 = new CourierActor(dispatcher.getMyAddress(), new Point(), "Courier1", SpeedConsts.FOURTYKMH);
        CourierActor courier2 = new CourierActor(dispatcher.getMyAddress(), new Point(), "Courier2", SpeedConsts.FOURTYKMH);

        int order1StartTime = TimeConsts.MSINHOUR * 2;
        int order2StartTime = TimeConsts.MSINHOUR * 2;
        int order3StartTime = TimeConsts.MSINHOUR * 5;

        Order order1 = new Order(new Point(-33, -28), new Point(13, 7), new SimpleSegment(order1StartTime, order1StartTime + TimeConsts.STANDARTPICKUPWINDOW), new SimpleSegment(order1StartTime + TimeConsts.STANDARTPICKUPWINDOW, order1StartTime + TimeConsts.STANDARTDELIVERYWINDOW));
        Order order2 = new Order(new Point(29, 31), new Point(-55, -38),new SimpleSegment(order2StartTime, order2StartTime + TimeConsts.STANDARTPICKUPWINDOW), new SimpleSegment(order2StartTime + TimeConsts.STANDARTPICKUPWINDOW, order2StartTime + TimeConsts.STANDARTDELIVERYWINDOW));
        Order order3 = new Order(new Point(-37, -58), new Point(18, -5),new SimpleSegment(order3StartTime, order3StartTime + TimeConsts.STANDARTPICKUPWINDOW), new SimpleSegment(order3StartTime + TimeConsts.STANDARTPICKUPWINDOW, order3StartTime + TimeConsts.STANDARTDELIVERYWINDOW));

        for (int time = 0; time < TimeConsts.MSINDAY;time++){
            if(time == TimeConsts.MSINHOUR * 2){
                OrderActor orderActor1 = new OrderActor(order1, "Order1", dispatcher.getMyAddress());
                OrderActor orderActor2 = new OrderActor(order2, "Order2", dispatcher.getMyAddress());
            }
            if (time == TimeConsts.MSINHOUR * 4){
                OrderActor orderActor3 = new OrderActor(order3, "Order3", dispatcher.getMyAddress());
            }
            dispatcher.update();
        }
        int breakpoint = 123;
    }
    public static void main(String[] args) {
//        testSystem();
        System(10);
    }
}