package Actrors;

public class DispatcherActor {
    String myAddr = "";
    String[] orders;
    String[] couriers;
    public DispatcherActor(){

    }

    public String[] getOrders(){return orders;}
    public String[] getCouriers(){return couriers;}
}
