package Actrors;

import Geometry.*;
import java.util.Arrays;
import java.util.Objects;

public class CourierActor {
    String myAddr;
    String dispAddr;
    Route[] routes;
    Point position;
    double direction;
    double earned;
    double satisfaction;

    public CourierActor(){
        myAddr = "";
        dispAddr = "";
        position = new Point();
    }
    public CourierActor(String inpDispAddr, Point inpPosition){
        myAddr = "";
        dispAddr = inpDispAddr;
        position = inpPosition;
    }
    public String getMyAddr(){return myAddr;}
    public String getDispAddr(){return dispAddr;}
    public Route[] getRoutes(){return routes;}
    public Point getPosition(){return position;}
    public double getDirection(){return direction;}
    public double getEarned(){return earned;}
    public double getSatisfaction(){return satisfaction;}

    public void setDispAddr(String inpDispAddr){
        dispAddr = inpDispAddr;
    }
    public void setPosition(Point inpPosition){
        position = inpPosition;
    }
    public void setDirection(double inpDirection){
        direction = inpDirection;
    }

    @Override
    public String toString() {
        return "CourierActor{" +
                "myAddr='" + myAddr + '\'' +
                ", position=" + position +
                ", direction=" + direction +
                ", earned=" + earned +
                ", satisfaction=" + satisfaction +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourierActor that = (CourierActor) o;
        return myAddr.equals(that.myAddr);
    }

    @Override
    public int hashCode() {
        return Objects.hash(myAddr);
    }
}
