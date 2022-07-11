package Actrors;

import Geometry.*;

import java.util.Objects;

public class Order {
    Point start;
    Point end;
    double startTime;
    public Order(){
        start = new Point();
        end = new Point();
        startTime = 0;
    }
    public Order(Point inpStart, Point inpEnd, double inpStartTime){
        start = inpStart;
        end = inpEnd;
        startTime = inpStartTime;
    }

    public Point getStart() {
        return start;
    }

    public void setStart(Point start) {
        this.start = start;
    }

    public Point getEnd() {
        return end;
    }

    public void setEnd(Point end) {
        this.end = end;
    }

    public double getStartTime() {
        return startTime;
    }

    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "start=" + start +
                ", end=" + end +
                ", startTime=" + startTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Double.compare(order.startTime, startTime) == 0 && Objects.equals(start, order.start) && Objects.equals(end, order.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end, startTime);
    }
}
