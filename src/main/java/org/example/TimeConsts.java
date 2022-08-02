package org.example;

public class TimeConsts {
    private static final double second = 100;
    private static final double minute = second * 60;
    private static final double hour = minute * 60;
    private static final double day = hour * 24;

    public static double getMinute() {
        return minute;
    }

    public static double getHour() {
        return hour;
    }

    public static double getDay() {
        return day;
    }

    public static double getSecond() {
        return second;
    }
}
