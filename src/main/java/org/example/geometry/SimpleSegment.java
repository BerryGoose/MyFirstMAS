package org.example.geometry;

import java.util.Objects;
public class SimpleSegment {
    private final double start;
    private final double end;

    public SimpleSegment(){
        start = 0;
        end = 0;
    }
    public SimpleSegment(double start, double end){
        this.start = start;
        this.end = end;
    }
    public double getStart() {return start;}
    public double getEnd(){return end;}
    public double getLength(){return Math.abs(start - end);}

    public boolean containPoint(double point){
        return start <= point && point <= end;
    }

    public boolean overlay(SimpleSegment segment){
        if (this.containPoint(segment.getStart()) || this.containPoint(segment.getEnd())){
            return true;
        }
        return segment.containPoint(this.getStart()) || segment.containPoint(this.getEnd());
    }

    @Override
    public String toString() {
        return "SimpleSegment{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleSegment that = (SimpleSegment) o;
        return Double.compare(that.start, start) == 0 && Double.compare(that.end, end) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

}
