package com.example.android.materialdesigncodelab;


import java.util.ArrayList;

/**
 * Created by Jake on 3/7/2016.
 */
public class Schedule {

    private String period, start, end;

    public Schedule() {

    }

    public Schedule(String mPeriod, String mStart, String mEnd) {
        period = mPeriod;
        start = mStart;
        end = mEnd;
    }

    public String getPeriod() {
        return period;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

}