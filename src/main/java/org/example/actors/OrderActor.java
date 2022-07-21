package org.example.actors;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class OrderActor {
    private Order order;
    private String myAddr;
    private String dispAddress;

    public OrderActor(){
        order = new Order();
    }
    public OrderActor(@NotNull Order order, String name, String dispAddress){
        myAddr = name;
        this.order = order;
        this.dispAddress = dispAddress;
    }

    public Order getOrder() {return order;}
    public String getMyAddress() {return myAddr;}
    public void setOrder(@NotNull Order order) {this.order = order;}
    public void setDispAddress(String dispAddress){this.dispAddress = dispAddress;}

    @Override
    public String toString() {
        return "OrderActor{" +
                "order=" + order +
                ", myAddr='" + myAddr + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderActor that = (OrderActor) o;
        return Objects.equals(order, that.order) && Objects.equals(myAddr, that.myAddr) && Objects.equals(dispAddress, that.dispAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, myAddr, dispAddress);
    }
}
