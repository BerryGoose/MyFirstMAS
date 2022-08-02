package org.example.actors.order;

import org.example.actors.BaseActor;
import org.example.message.Message;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class OrderActor extends BaseActor {
    private Order order;
    private final String myAddress;
    private String dispAddress;
    public OrderActor(@NotNull Order order, String name, String dispAddress){
        super(name);
        myAddress = super.getMyAddress();
        this.order = order;
        this.dispAddress = dispAddress;
    }

    public Order getOrder() {return order;}
    public String getMyAddress() {return myAddress;}
    public void setOrder(@NotNull Order order) {this.order = order;}
    public void setDispAddress(String dispAddress){this.dispAddress = dispAddress;}

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
    public void receiveMessage(String sender, Message message){

    }


}
