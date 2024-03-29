package org.example.geometry;

import org.example.constants.SpeedConsts;
import java.util.Objects;
import java.util.Random;
public class Point {
    private final double x;
    private final double y;
    public Point(){
        x = 0;
        y = 0;
    }
    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public double getAngle() {
        if(x == 0 & y == 1) return Math.PI / 2;
        if(x == 0 & y == -1) return 3 * Math.PI / 2;
        if(x == 1 & y == 0) return 0;
        if(x == -1 & y == 0) return Math.PI;

        if(x > 0 & y > 0) return Math.atan(y / x);
        if(x < 0 & y > 0) return Math.PI / 2 - Math.atan(y / x);
        if(x > 0 & y < 0) return 1.5 * Math.PI - Math.atan(y / x);
        if(x < 0 & y < 0) return Math.PI + Math.atan(y / x);

        return 0;
    }

    public double abs(){
        return Math.sqrt(x * x + y * y);
    }
    public Point sumPoint(Point other){
        double tempX = this.x + other.x;
        double tempY = this.y + other.y;
        return new Point(tempX, tempY);
    }

    public Point difPoint(Point other){
        double tempX = this.x - other.x;
        double tempY = this.y - other.y;
        return new Point(tempX, tempY);
    }

    public boolean isEqual(Point other){
        return distanceTo(other) <= SpeedConsts.FOURTYKMH;
    }
    public double distanceTo(Point point){
        return this.difPoint(point).abs();
    }

    public double angleTo(Point point){
        return point.difPoint(this).getAngle();
    }

    public boolean isInCircle(Point center, double radius){
        return center.distanceTo(this) <= radius;
    }

    public static Point randomPoint(){
        Random random = new Random();
        double radius = random.nextDouble(80);
        double angle = random.nextDouble(2 * Math.PI);
        double x = radius * Math.cos(angle);
        double y = radius * Math.sin(angle);
        return new Point(x, y);
    }
    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Double.compare(point.x, x) == 0 && Double.compare(point.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
