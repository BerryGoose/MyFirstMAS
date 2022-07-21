package org.example.actors;

import org.example.geometry.Point;
import org.example.geometry.Route;

import java.util.Arrays;
import java.util.Objects;

public class CourierActor {
    private String myAddress;
    private String dispAddress;
    private Route[] routes;
    private Point position;
    private double direction;
    private double earned;
    private CourierStatus status = CourierStatus.Wait;

    public CourierActor(){
        position = new Point();
    }
    public CourierActor(String dispAddress, Point position, String name){
        myAddress = name;
        this.dispAddress = dispAddress;
        this.position = position;

    }
    public String getMyAddress(){return myAddress;}
    public String getDispAddress(){return dispAddress;}
    public Route[] getRoutes(){return routes;}
    public Point getPosition(){return position;}
    public double getDirection(){return direction;}
    public double getEarned(){return earned;}

    public void setDispAddress(String dispAddress){
        this.dispAddress = dispAddress;
    }
    public void setPosition(Point position){
        this.position = position;
    }
    public void setDirection(double direction){
        this.direction = direction;
    }
    public void setStatus(CourierStatus status){
        this.status = status;
    }

    @Override
    public String toString() {
        return "CourierActor{" +
                "myAddress='" + myAddress + '\'' +
                ", routes=" + Arrays.toString(routes) +
                ", position=" + position +
                ", direction=" + direction +
                ", earned=" + earned +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourierActor that = (CourierActor) o;
        return Double.compare(that.direction, direction) == 0 && Double.compare(that.earned, earned) == 0 && Objects.equals(myAddress, that.myAddress) && Objects.equals(dispAddress, that.dispAddress) && Arrays.equals(routes, that.routes) && Objects.equals(position, that.position) && status == that.status;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(myAddress, dispAddress, position, direction, earned, status);
        result = 31 * result + Arrays.hashCode(routes);
        return result;
    }
}
