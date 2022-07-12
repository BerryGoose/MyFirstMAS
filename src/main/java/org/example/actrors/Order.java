package org.example.actrors;


import org.example.geometry.*;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;


public class Order {
    private Point start;
    private Point end;
    private SimpleSegment startTimePeriod;
    private SimpleSegment endTimePeriod;
    public Order(){
        start = new Point();
        end = new Point();
        startTimePeriod = new SimpleSegment();
        endTimePeriod = new SimpleSegment();
    }
    public Order(@NotNull Point start, @NotNull Point end, double startTime){
        this.start = start.copy() ;
        this.end = end.copy();
        startTimePeriod = new SimpleSegment(startTime, startTime + 120);
        endTimePeriod = new SimpleSegment(startTime, startTime + 480);
    }

    public Point getStart(){return start.copy();}
    public Point getEnd(){return end.copy();}
    public SimpleSegment getStartTimePeriod(){return startTimePeriod.copy();}
    public SimpleSegment getEndTimePeriod(){return endTimePeriod.copy();}

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

    public Order copy(){
        return new Order(start.copy(), end.copy(), startTimePeriod.getStart());
    }
}
