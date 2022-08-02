package org.example.actors;

import org.example.message.Message;
import org.example.message.MsgType;
import java.util.Arrays;

public class DispatcherActor extends BaseActor{
    private String[] orders;
    private String[] couriers;

    private int time;
    public DispatcherActor(String name){
        super(name);
        String myAddress = super.getMyAddress();
    }

    public String[] getOrders(){return orders;}
    public String[] getCouriers(){return couriers;}
    public String getMyAddress(){return myAddress;}

//    private List<String> findCouriers(Order order){
//        List<String> couriersList = new ArrayList<>();
//        for (String courier:
//             couriers) {
//            boolean willBeInArea = ActorSystem.ask(courier, new Message(MsgType.WillBeInArea, "List<Object>?"));
//
//        }
//    }

    @Override
    public String receiveMessage(Message message){
        if (message.getType() == MsgType.GetTime){
            return String.valueOf(time);
        }
        return null;
    }
    @Override
    public void receiveMessage(String sender, Message message){

    }

    @Override
    public String toString() {
        return "DispatcherActor{" +
                "orders=" + Arrays.toString(orders) +
                ", couriers=" + Arrays.toString(couriers) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DispatcherActor that = (DispatcherActor) o;
        return Arrays.equals(orders, that.orders) && Arrays.equals(couriers, that.couriers);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Arrays.hashCode(orders);
        result = 31 * result + Arrays.hashCode(couriers);
        return result;
    }

}
