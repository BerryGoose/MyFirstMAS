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
