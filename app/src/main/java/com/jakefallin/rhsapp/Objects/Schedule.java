package com.jakefallin.rhsapp.Objects;


/**
 * Created by Jake on 3/7/2016.
 */
public class Schedule {

    private String period, start, end, teacher;
    private boolean cb1, cb2, cb3, cb4;

    private ClassTeacherItem cti;
    private boolean selected;

    public Schedule() {

    }

    public Schedule(String mPeriod, String mStart, String mEnd) {
        period = mPeriod;
        start = mStart;
        end = mEnd;
        teacher = "";
        cb1 = true;
        cb2 = true;
        cb3 = true;
        cb4 = true;
    }
    public Schedule(String mPeriod, String mTeacher, String mStart, String mEnd, boolean c1, boolean c2, boolean c3, boolean c4) {
        period = mPeriod;
        start = mStart;
        end = mEnd;
        teacher = mTeacher;
        cb1 = c1;
        cb2 = c2;
        cb3 = c3;
        cb4 = c4;
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

    public boolean isCb1() {
        return cb1;
    }

    public void setCb1(boolean cb1) {
        this.cb1 = cb1;
    }

    public boolean isCb2() {
        return cb2;
    }

    public void setCb2(boolean cb2) {
        this.cb2 = cb2;
    }

    public boolean isCb3() {
        return cb3;
    }

    public void setCb3(boolean cb3) {
        this.cb3 = cb3;
    }

    public boolean isCb4() {
        return cb4;
    }

    public void setCb4(boolean cb4) {
        this.cb4 = cb4;
    }
}