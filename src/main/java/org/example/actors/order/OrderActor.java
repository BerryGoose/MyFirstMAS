package org.example.actors.order;

import org.example.actors.BaseActor;
import org.example.message.*;
import org.jetbrains.annotations.NotNull;
import java.util.*;
public class OrderActor extends BaseActor {
    private Order order;
    private String dispAddress;
    public OrderActor(@NotNull Order order, String name, String dispAddress){
        super(name);
        this.order = order;
        this.dispAddress = dispAddress;
        send(dispAddress, new Message(MsgType.NewOrder));
        findCourier();
    }

    public Order getOrder() {return order;}
    public String getMyAddress() {return myAddress;}
    public void setOrder(@NotNull Order order) {this.order = order;}
    public void setDispAddress(String dispAddress){this.dispAddress = dispAddress;}

    private void addToWaitList(){
        send(dispAddress, new Message(MsgType.AddToWaitList));
    }

    private void findCourier(){
        List<String> potentialCouriers = getPotentialCouriers();
        if(potentialCouriers.size() == 0){
            addToWaitList();
        }else {
            List<String> ableToDeliverCouriers = getAbleToDeliverCouriers(potentialCouriers);
            if (ableToDeliverCouriers.size() == 0){
                addToWaitList();
            }else {
                HashMap<String, Double> deliverCosts = getDeliveryCosts(ableToDeliverCouriers);
                String courier = getCheapestCourier(deliverCosts);
                send(courier, new Message(MsgType.AskToDeliver, order));
                send(dispAddress, new Message(MsgType.RemoveFromWaitList));
            }
        }
    }
    private String getCheapestCourier(HashMap<String, Double> deliverCosts){
        List<String> keysArr = new ArrayList<String>(deliverCosts.keySet());
        String cheapestCourier = keysArr.get(new Random().nextInt(keysArr.size()));
        double cheapestCost = deliverCosts.get(cheapestCourier);
        for(String courier : keysArr){
            cheapestCost = deliverCosts.get(cheapestCourier);
            if (deliverCosts.get(courier) < cheapestCost){
                cheapestCourier = courier;
            }
        }
        return cheapestCourier;
    }

    private HashMap<String, Double> getDeliveryCosts(List<String> courierList){
        HashMap<String, Double> deliverCosts = new HashMap<String, Double>();
        for (String courier : courierList){
            deliverCosts.put(courier, (double) ask(courier, new Message(MsgType.GetDeliveryCost, order)));
        }
        return  deliverCosts;
    }

    private List<String> getAbleToDeliverCouriers(List<String> courierList){
        List<String> ableToDeliver = new ArrayList<String>();
        for(String courier: courierList){
            boolean canDeliver = (boolean) ask(courier, new Message(MsgType.CanDeliver, order));
            if (canDeliver){
                ableToDeliver.add(courier);
            }
        }
        return  ableToDeliver;
    }

    private List<String> getPotentialCouriers(){
        List<String> couriersList = (List<String>) ask(dispAddress, new Message(MsgType.GetCouriers));
        List<String> potentialCouriers = new ArrayList<String>();
        for (String courier: couriersList){
            boolean willBeInArea = (boolean) ask(courier, new Message(MsgType.WillBeInArea, order));
            int routesAmount = (int) ask(courier, new Message(MsgType.GetRoutesAmount));
            boolean canAddToEnd = (boolean) ask(courier, new Message(MsgType.CanAddToEnd, order));
            if (willBeInArea || routesAmount == 0 || canAddToEnd){
                potentialCouriers.add(courier);
            }
        }
        return potentialCouriers;
    }

    @Override
    public String toString() {
        return "OrderActor{" +
                "order=" + order +
                ", myAddress='" + myAddress + '\'' +
                ", dispAddress='" + dispAddress + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OrderActor that = (OrderActor) o;
        return Objects.equals(order, that.order) && Objects.equals(myAddress, that.myAddress) && Objects.equals(dispAddress, that.dispAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), order, myAddress, dispAddress);
    }

    @Override
    public Object receiveMessage(String sender, Message message){
        switch (message.getType()){
            case FindCourier:
                findCourier();
                break;
        }
        return null;
    }
}
