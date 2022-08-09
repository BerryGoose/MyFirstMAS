package org.example.actors;

import org.example.message.Message;
import org.example.message.MsgType;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
public class DispatcherActor extends BaseActor{
    private List<String> orders;
    private List<String> couriers;
    private HashSet<String> waitList;
    public DispatcherActor(String name){
        super(name);
        orders = new ArrayList<String>();
        couriers = new ArrayList<String>();
        waitList = new HashSet<String>();
    }
    public List<String> getOrders(){return orders;}
    public List<String> getCouriers(){return couriers;}
    public String getMyAddress(){return myAddress;}

    public void update(){
        time += 1;
        for(String courier : couriers){
            send(courier, new Message(MsgType.Update));
        }
        for (String order : waitList){
            send(order, new Message(MsgType.FindCourier));
        }
    }
    @Override
    public Object receiveMessage(String sender, Message message){
        switch (message.getType()){
            case NewCourier:
                couriers.add(sender);
                break;
            case NewOrder:
                orders.add(sender);
                break;
            case GetCouriers:
                return couriers;
            case AddToWaitList:
                if (waitList.contains(sender)){
                    break;
                }
                waitList.add(sender);
                break;
            case RemoveFromWaitList:
                waitList.remove(sender);
                break;
        }
        return null;
    }
}
