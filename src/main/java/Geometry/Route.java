package Geometry;

import java.util.Objects;

public class Route {
    Segment way = new Segment();
    double startTime;
    double endTime;

    routeType type;

    public Route(){
    }
    public Route(Point inpStart, Point inpEnd, double inpStartTime, double inpEndTime, routeType inpType){
        way = new Segment(inpStart, inpEnd);
        startTime = inpStartTime;
        endTime = inpEndTime;
        type = inpType;
    }

    public Segment getWay() {
        return way;
    }
    public double getStartTime() {
        return startTime;
    }
    public double getEndTime() {
        return endTime;
    }
    public routeType getType() {
        return type;
    }

    public void setWay(Segment way) {
        this.way = way;
    }
    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }
    public void setEndTime(double endTime) {
        this.endTime = endTime;
    }
    public void setType(routeType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Route{" +
                "way=" + way +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", type=" + type +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return Double.compare(route.startTime, startTime) == 0 && Double.compare(route.endTime, endTime) == 0 && Objects.equals(way, route.way) && type == route.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(way, startTime, endTime, type);
    }
}
