package Actrors;

import java.util.Objects;

public class OrderActor {
    Order order;
    String myAddr;

    public OrderActor(){
        myAddr = "";
        order = null;
    }
    public OrderActor(Order inpOrder){
        myAddr = "";
        order = inpOrder;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getMyAddr() {
        return myAddr;
    }

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
        return myAddr.equals(that.myAddr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(myAddr);
    }
}
