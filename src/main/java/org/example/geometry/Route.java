package org.example.geometry;

import org.example.actors.order.Order;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Route {
    private final Segment way;
    private final RouteType type;
    private final Order order;
    private final SimpleSegment timePeriod;
    public Route(){
        way = new Segment();
        type = RouteType.Wait;
        order = new Order();
        timePeriod = new SimpleSegment();
    }
    public Route(Point start, Point end, double startTime, double endTime, RouteType type, @NotNull Order order){
        this.way = new Segment(start, end);
        this.type = type;
        this.order = order;
        this.timePeriod = new SimpleSegment(startTime, endTime);
    }
    public Segment getWay(){return way;}
    public RouteType getType(){return type;}
    public SimpleSegment getTimePeriod(){return timePeriod;}
    public Order getOrder(){return order;}
    public double getLength(){return way.getLength();}

    @Override
    public String toString() {
        return "Route{" +
                "way=" + way +
                ", type=" + type +
                ", order=" + order +
                ", timePeriod=" + timePeriod +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return way.equals(route.way) && type == route.type && order.equals(route.order) && timePeriod.equals(route.timePeriod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(way, type, order, timePeriod);
    }
}
