package com.jakefallin.rhsapp.Objects;


/**
 * Created by Jake on 3/7/2016.
 */
public class Schedule {

    private String period, start, end, teacher;
    private ClassTeacherItem cti;
    private boolean selected;

    public Schedule() {

    }

    public Schedule(String mPeriod, String mStart, String mEnd) {
        period = mPeriod;
        start = mStart;
        end = mEnd;
        teacher = "";
    }
    public Schedule(String mPeriod, String mTeacher, String mStart, String mEnd) {
        period = mPeriod;
        start = mStart;
        end = mEnd;
        teacher = mTeacher;
    }
    public Schedule(ClassTeacherItem mCti, ClassTeacherItem mCti2, String mStart, String mEnd)
    {
        period = mCti.getMyClass();
        teacher = mCti.getMyTeacher();
        start = mStart;
        end = mEnd;

    }

    public String getPeriod() {
        return period;
    }

    public String getStart() {
        if(start != "") {
            String[] separated = start.split(":");
            int hour = Integer.parseInt(separated[0]);
            if (hour > 12) {
                hour -= 12;
                return ("" + hour + ":" + separated[1] + " P.M.");
            }
            return (separated[0] + ":" + separated[1] + " A.M.");
        }
        return "";
    }

    public String getEnd() {
        if(end != "") {
            String[] separated = end.split(":");
            int hour = Integer.parseInt(separated[0]);
            if (hour > 12) {
                hour -= 12;
                return ("" + hour + ":" + separated[1] + " P.M.");
            }
            return (separated[0] + ":" + separated[1] + " A.M.");
        }
        else
            return "";
    }

    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getStart24(){
        return start;
    }

    public String getEnd24(){
        return end;
    }

    public String getTeacher() {
        return teacher;
    }

    public ClassTeacherItem getCti() {
        return cti;
    }
}