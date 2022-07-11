package Geometry;

import java.util.Objects;

public class SimpleSegment {
    double start;
    double end;
    double length;

    public SimpleSegment(){
        start = 0;
        end = 0;
        length = 0;
    }
    public SimpleSegment(double inpStart, double inpEnd){
        start = inpStart;
        end = inpEnd;
        length = Math.abs(start - end);
    }
    public double getStart() {return start;}
    public double getEnd(){return end;}
    public double getLength(){return length;}

    public void setStart(double start) {
        this.start = start;
        this.length = Math.abs(this.start - this.end);
    }
    public void setEnd(double end){
        this.end = end;
        this.length = Math.abs(this.start - this.end);
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
        return Double.compare(that.start, start) == 0 && Double.compare(that.end, end) == 0 && Double.compare(that.length, length) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end, length);
    }
}
