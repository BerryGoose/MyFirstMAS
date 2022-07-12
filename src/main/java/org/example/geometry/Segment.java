package org.example.geometry;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Segment {
    private Point start;
    private Point end;

    public Segment(){
        start = new Point();
        end = new Point();
    }

    public Segment(@NotNull Point start, @NotNull Point end){
        this.start = start.copy();
        this.end = end.copy();
    }

    public Point getStart(){return start.copy();}
    public Point getEnd() {return end.copy();}
    public double getLength() {return Math.sqrt((start.getX() - end.getX()) * (start.getX() - end.getX()) + (start.getY() - end.getY()) * (start.getY() - end.getY()));}
    public double getB(){return start.getY() - getAngle() * start.getX();}
    public double getAngle() {
        return end.difPoint(start).getAngle();
    }

    public void setStart(@NotNull Point start){
        this.start = start.copy();
    }
    public void setEnd(@NotNull Point end){
        this.end = end.copy();
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

    public Segment copy(){
        return new Segment(start.copy(), end.copy());
    }
}
