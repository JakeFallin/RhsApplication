package com.example.android.materialdesigncodelab;


import java.util.ArrayList;

/**
 * Created by Jake on 3/7/2016.
 */
public class Period {
    private String periodNum;
    private String startTime;
    private String endTime;

    public Period(String periodNum1, String startTime1, String endTime1) {
        periodNum1 = periodNum;
        startTime1 = startTime;
        endTime1 = endTime;
    }

    public String getPeriodNum() {
        return periodNum;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    private static int lastContactId = 0;

    public static ArrayList<Period> createList() {
        ArrayList<Period> schedule = new ArrayList<Period>();


        schedule.add(new Period("Period 1", "7:45", "8:45"));
        schedule.add(new Period("Period 2", "8:50", "9:50"));
        schedule.add(new Period("Period 3", "9:55", "10:55"));
        schedule.add(new Period("Lunch", "11:00", "11:35"));
        schedule.add(new Period("Period 5", "11:40", "12:40"));
        schedule.add(new Period("Period 6", "12:45", "1:45"));
        schedule.add(new Period("Period 7", "1:50", "2:50"));


        return schedule;
    }
}