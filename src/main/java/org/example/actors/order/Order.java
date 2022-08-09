package org.example.actors.order;

import org.example.geometry.*;
import org.jetbrains.annotations.NotNull;
import java.util.Objects;
public class Order {
    private final Point start;
    private final Point end;
    private final SimpleSegment startTimePeriod;
    private final SimpleSegment endTimePeriod;
    public Order(){
        start = new Point();
        end = new Point();
        startTimePeriod = new SimpleSegment();
        endTimePeriod = new SimpleSegment();
    }
    public Order(@NotNull Point start, @NotNull Point end, SimpleSegment startTimePeriod, SimpleSegment endTimePeriod){
        this.start = start;
        this.end = end;
        this.startTimePeriod = startTimePeriod;
        this.endTimePeriod = endTimePeriod;
    }

    public Point getStart(){return start;}
    public Point getEnd(){return end;}
    public SimpleSegment getStartTimePeriod(){return startTimePeriod;}
    public SimpleSegment getEndTimePeriod(){return endTimePeriod;}

    @Override
    public String toString() {
        return "Order{" +
                "start=" + start +
                ", end=" + end +
                ", startTimePeriod=" + startTimePeriod +
                ", endTimePeriod=" + endTimePeriod +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return start.equals(order.start) && end.equals(order.end) && startTimePeriod.equals(order.startTimePeriod) && endTimePeriod.equals(order.endTimePeriod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end, startTimePeriod, endTimePeriod);
    }
}
