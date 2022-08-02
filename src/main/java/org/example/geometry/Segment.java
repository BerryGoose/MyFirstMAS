package org.example.geometry;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Segment {
    private final Point start;
    private final Point end;

    public Segment(){
        start = new Point();
        end = new Point();
    }

    public Segment(@NotNull Point start, @NotNull Point end){
        this.start = start;
        this.end = end;
    }

    public Point getStart(){return start;}
    public Point getEnd() {return end;}
    public double getLength() {return Math.sqrt((start.getX() - end.getX()) * (start.getX() - end.getX()) + (start.getY() - end.getY()) * (start.getY() - end.getY()));}
    public double getB(){return start.getY() - getAngle() * start.getX();}
    public double getAngle() {
        return end.difPoint(start).getAngle();
    }

    public boolean pointOnSegment(@NotNull Point point){
        if(point.getY() == this.getAngle() * point.getX() + this.getB()){
            return start.getX() <= point.getX() && point.getX() <= end.getX();
        }
        return false;
    }

    public static boolean segmentOverlay(Segment segment1, Segment segment2){
        if (segment1.getAngle() == segment2.getAngle() && segment1.getB() == segment2.getB()){
            if(segment2.pointOnSegment(segment1.getStart()) || segment2.pointOnSegment(segment1.getEnd())){
                return true;
            } else return segment1.pointOnSegment(segment2.getStart()) || segment1.pointOnSegment(segment2.getEnd());
        }
        return false;
    }

    public boolean crossCircle(Point center, double radius){
        double a = 1 + this.getAngle() * this.getAngle();
        double b = 2 * this.getAngle() * (this.getB() - center.getY()) - 2 * center.getX();
        double c = Math.pow(center.getX(), 2) + Math.pow(this.getB() - center.getY(), 2) - Math.pow(radius, 2);

        double D = b * b - 4 * a * c;

        if (D < 0){
            return false;
        }
        else if (D == 0){
            double x = -b / (2 * a);
            double y = this.getAngle() * x + this.getB();

            return this.pointOnSegment(new Point(x, y));
        }

        else if (D > 0){
            double x1 = (-b - Math.sqrt(D)) / (2 * a);
            double x2 = (-b + Math.sqrt(D)) / (2 * a);

            double y1 = this.getAngle() * x1 + this.getB();
            double y2 = this.getAngle() * x2 + this.getB();

            Point point1 = new Point(x1, y1);
            Point point2 = new Point(x2, y2);

            Segment segment = new Segment(point1, point2);
            return Segment.segmentOverlay(this, segment);
        }
        return false;
    }




    @Override
    public String toString() {
        return "Segment{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Segment segment = (Segment) o;
        return Objects.equals(start, segment.start) && Objects.equals(end, segment.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}
