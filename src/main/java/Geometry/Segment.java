package Geometry;

import java.util.Objects;

public class Segment {
    Point start;
    Point end;
    double length;
    double angle;
    double b;

    public Segment(){
        start = new Point();
        end = new Point();
        length = _length(start, end);
        angle = 0;
        b = 0;
    }

    public Segment(Point inpStart, Point inpEnd){
        start = inpStart;
        end = inpEnd;
        length = _length(start, end);
        angle = _angle(start, end);
        b = _b(start, end);
    }

    public Point getStart(){return start;}
    public Point getEnd() {return end;}
    public double getLength() {return length;}
    public double getB(){return b;}
    public double getAngle() {return angle;}

    public void setStart(Point start){
        this.start = start;
        angle = _angle(start, end);
        b = _b(start, end);
    }
    public void setEnd(Point end){
        this.end = end;
        angle = _angle(start, end);
        b = _b(start, end);
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
        return Double.compare(segment.length, length) == 0 && Double.compare(segment.angle, angle) == 0 && Double.compare(segment.b, b) == 0 && start.equals(segment.start) && end.equals(segment.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end, length, angle, b);
    }

    double _length(Point start, Point end){
        return Math.sqrt((start.x - end.x) * (start.x - end.x) + (start.y - end.y) * (start.y - end.y));
    }

    double _angle(Point start, Point end){
        Point tempPoint = start;
        tempPoint.subPoint(end);
        return tempPoint.angle;
    }

    double _b(Point start, Point end){
        return start.y - _angle(start, end) * start.x;
    }


}
