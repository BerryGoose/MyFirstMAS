package Geometry;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Point {
    double x;
    double y;
    double angle;

    public Point(){
        x = 0;
        y = 0;
        angle = 0;
    }

    public Point(double inpX, double inpY){
        x = inpX;
        y = inpY;
        angle = _angle(x, y);
    }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
        angle = _angle(this.x, this.y);
    }
    public void setY(double y) {
        this.y = y;
        angle = _angle(this.x, this.y);
    }

    public void addPoint(@NotNull Point p){
        this.x += p.x;
        this.y += p.y;
        this.angle = _angle(this.x, this.y);
    }
    public Point PointaddPoint(@NotNull Point p){
        double tempX = this.x + p.x;
        double tempY = this.y + p.y;
        return new Point(tempX, tempY);
    }

    public void subPoint(@NotNull Point p){
        this.x -= p.x;
        this.y -= p.y;
        this.angle = _angle(this.x, this.y);
    }

    public Point PointsubPoint(@NotNull Point p){
        double tempX = this.x - p.x;
        double tempY = this.y - p.y;
        return new Point(tempX, tempY);
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
        return Double.compare(point.x, x) == 0 && Double.compare(point.y, y) == 0 && Double.compare(point.angle, angle) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, angle);
    }

    double _angle(double x, double y){
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
}
