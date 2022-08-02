package org.example.actors.courier;

import org.example.CostConsts;
import org.example.TimeConsts;
import org.example.actors.ActorSystem;
import org.example.actors.BaseActor;
import org.example.actors.order.Order;
import org.example.geometry.Point;
import org.example.geometry.Route;
import org.example.geometry.RouteType;
import org.example.geometry.Segment;
import org.example.message.Message;
import org.example.message.MsgType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CourierActor extends BaseActor {
    private double speed;
    private String dispAddress;
    private final String myAddress;
    private List<Route> routes = new ArrayList<Route>();
    private Point position;
    private double direction;
    private double earned;
    private CourierStatus status = CourierStatus.Wait;

    public CourierActor(){
        super("");
        myAddress = super.getMyAddress();
        position = new Point();
    }
    public CourierActor(String dispAddress, Point position, String name, double speed){
        super(name);
        myAddress = super.getMyAddress();
        this.dispAddress = dispAddress;
        this.position = position;
        this.speed = speed;

    }
    public String getMyAddress(){return myAddress;}
    public String getDispAddress(){return dispAddress;}
    public List<Route> getRoutes(){return routes;}
    public Point getPosition(){return position;}
    public double getDirection(){return direction;}
    public double getEarned(){return earned;}
    public double getSpeed(){return speed;}
    public double getSatisfaction(){
        double satisfaction = 0;
        for (Route route : routes) {
            satisfaction += route.getLength() / speed;
        }
        int time = Integer.parseInt(ActorSystem.ask(dispAddress, new Message(MsgType.GetTime)));
        return satisfaction / TimeConsts.getDay();
    }

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

    private void move(){
        if(status == CourierStatus.Work){
            direction = position.angleTo(routes.get(0).getWay().getEnd());
        } else if (status == CourierStatus.Take) {
            direction = position.angleTo(routes.get(0).getWay().getStart());
        }
        double dx = speed * Math.cos(direction);
        double dy = speed * Math.sin(direction);

        position = position.sumPoint(new Point(dx, dy));
    }

    private void update(){
        int time = Integer.parseInt(ActorSystem.ask(dispAddress, new Message(MsgType.GetTime)));
        if (routes.size() == 0){
            status = CourierStatus.Wait;
        } else if (status == CourierStatus.Work) {
            if (position.equals(routes.get(0).getWay().getEnd())){
                earned += CostConsts.getKmCost() * routes.get(0).getLength();
                routes.remove(0);
                if (routes.size() == 0){
                    status = CourierStatus.Wait;
                }
                else{
                    status = CourierStatus.Take;
                    this.move();
                    earned -= CostConsts.getMsSelfCost();
                }
            }
            else {
                this.move();
                earned -= CostConsts.getMsSelfCost();
            }
            
        } else if (status == CourierStatus.Wait) {
            if (routes.get(0).getOrder().getStartTimePeriod().getStart() <= time && time <= routes.get(0).getOrder().getStartTimePeriod().getEnd()){
                status = CourierStatus.Work;
            }
        } else if (status == CourierStatus.Take) {
            if (position.equals(routes.get(0).getWay().getStart())) {
                if (routes.get(0).getOrder().getStartTimePeriod().getStart() <= time && time <= routes.get(0).getOrder().getStartTimePeriod().getEnd()) {
                    status = CourierStatus.Work;
                    move();
                    earned -= CostConsts.getMsSelfCost();
                } else status = CourierStatus.Wait;
            }else {
                move();
                earned -= CostConsts.getMsSelfCost();
            }
        }
    }

    private boolean willBeInArea(Point areaCenter, double areaRadius){
        if (routes.size() == 0){
            return false;
        }
        if (new Segment(position, routes.get(0).getWay().getStart()).crossCircle(areaCenter, areaRadius)){
            return true;
        }
        for (int i = 0; i < routes.size() - 1;i++){
            if (routes.get(i).getWay().crossCircle(areaCenter, areaRadius)){
                return true;
            }
            if (new Segment(routes.get(i).getWay().getEnd(), routes.get(i + 1).getWay().getStart()).crossCircle(areaCenter, areaRadius)){
                return true;
            }
        }
        return false;
    }

    private int indexToInsert(Order order){
        int time = Integer.parseInt(ActorSystem.ask(dispAddress, new Message(MsgType.GetTime)));
        if (routes.size() == 0){
            if (order.getStartTimePeriod().getEnd() >= time + position.distanceTo(order.getStart()) / speed){
                return 0;
            }
        } if (status == CourierStatus.Wait || status == CourierStatus.Take){
            double takeTime = position.distanceTo(order.getStart()) / speed;
            double deliveryTime = order.getStart().distanceTo(order.getEnd()) / speed;
            double timeToNext = order.getEnd().distanceTo(routes.get(0).getWay().getStart()) / speed;

            if (takeTime < order.getStartTimePeriod().getStart()){
                if (order.getStartTimePeriod().getStart() + deliveryTime + timeToNext < routes.get(0).getOrder().getStartTimePeriod().getEnd()){
                    return 0;
                }
            } else if (order.getStartTimePeriod().getStart() <= takeTime && takeTime <= order.getStartTimePeriod().getEnd()) {
                if (takeTime + deliveryTime + timeToNext <= routes.get(0).getOrder().getStartTimePeriod().getEnd()){
                    return 0;
                }
            }
        }
        if (routes.get(routes.size() - 1).getTimePeriod().getEnd() + routes.get(routes.size() - 1).getWay().getEnd().distanceTo(order.getStart()) / speed < order.getStartTimePeriod().getEnd()){
            return routes.size() - 1;
        }

        for (int i = 0; i < routes.size() - 1;i++){
            double arriveTime = routes.get(i).getTimePeriod().getEnd() + routes.get(i).getWay().getEnd().distanceTo(order.getStart()) / speed;
            double deliveryTime = order.getStart().distanceTo(order.getEnd()) / speed;
            double timeToNext = order.getEnd().distanceTo(routes.get(i + 1).getWay().getStart()) / speed;

            if (arriveTime <= order.getStartTimePeriod().getStart()){
                if (order.getStartTimePeriod().getStart() + deliveryTime + timeToNext < routes.get(i + 1).getOrder().getStartTimePeriod().getEnd()){
                    return i;
                }
                if (order.getStartTimePeriod().getStart() <= arriveTime && arriveTime <= order.getStartTimePeriod().getEnd()){
                    if (arriveTime + deliveryTime + timeToNext < routes.get(i + 1).getOrder().getStartTimePeriod().getEnd()){
                        return i;
                    }
                }
            }
        }
        return -1;
    }

    private double deliveryCost(Order order){
        int index = indexToInsert(order);
        double summaryDistance = order.getStart().distanceTo(order.getEnd());
        if (index == 0){
            summaryDistance += position.distanceTo(order.getStart());
        }
        else{
            summaryDistance += routes.get(index - 1).getWay().getEnd().distanceTo(order.getStart()) + order.getEnd().distanceTo(routes.get(index).getWay().getStart());
        }
        return summaryDistance * CostConsts.getKmCost();
    }

    private void addOrder(Order order){
        int time = Integer.parseInt(ActorSystem.ask(dispAddress, new Message(MsgType.GetTime)));
        int index;
        if (routes.size() == 0){
            index = 0;
            status = CourierStatus.Take;
        }
        else{
            index = indexToInsert(order);
        }
        double departureTime;
        if (index == 0){
            departureTime = time + position.distanceTo(order.getStart()) / speed;
        }
        else{
            departureTime = Math.max(order.getStartTimePeriod().getStart(), routes.get(index - 1).getTimePeriod().getEnd() + routes.get(index - 1).getWay().getEnd().distanceTo(order.getStart()) / speed);
        }
        double deliveryTime = order.getStart().distanceTo(order.getEnd()) / speed;
        Route newRoute = new Route(order.getStart(), order.getEnd(), departureTime, departureTime + deliveryTime, RouteType.Work, order);
        routes.add(index, newRoute);
    }

    @Override
    public String toString() {
        return "CourierActor{" +
                "speed=" + speed +
                ", dispAddress='" + dispAddress + '\'' +
                ", myAddress='" + myAddress + '\'' +
                ", routes=" + routes +
                ", position=" + position +
                ", direction=" + direction +
                ", earned=" + earned +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CourierActor)) return false;
        if (!super.equals(o)) return false;
        CourierActor that = (CourierActor) o;
        return Double.compare(that.getSpeed(), getSpeed()) == 0 && Double.compare(that.getDirection(), getDirection()) == 0 && Double.compare(that.getEarned(), getEarned()) == 0 && Objects.equals(getDispAddress(), that.getDispAddress()) && Objects.equals(getMyAddress(), that.getMyAddress()) && Objects.equals(getRoutes(), that.getRoutes()) && Objects.equals(getPosition(), that.getPosition()) && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getSpeed(), getDispAddress(), getMyAddress(), getRoutes(), getPosition(), getDirection(), getEarned(), status);
    }

    @Override
    public void receiveMessage(String sender, Message message){
    }
    @Override
    public String receiveMessage(Message message){
        return null;
    }





}
