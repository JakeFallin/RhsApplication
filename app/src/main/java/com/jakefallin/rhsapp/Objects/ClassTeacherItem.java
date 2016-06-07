package com.jakefallin.rhsapp.Objects;

/**
 * Created by Jake on 4/26/2016.
 */
public class ClassTeacherItem {


    private String myClass, myTeacher;
    private boolean day1, day2, day3, day4;

    public ClassTeacherItem() {
        myClass = "Period 1";
        myTeacher = "";
        day1 = false;
        day2 = false;
        day3 = false;
        day4 = false;
    }

    public ClassTeacherItem(String c, String t, boolean d1, boolean d2, boolean d3, boolean d4) {
        myClass = c;
        myTeacher = t;
        day1 = d1;
        day2 = d2;
        day3 = d3;
        day4 = d4;
    }

    public ClassTeacherItem(boolean free) {
        myClass = "Free";
        myTeacher = "";
        day1 = true;
        day2 = true;
        day3 = true;
        day4 = true;
    }


    public boolean isDay1() {
        return day1;
    }

    public void setDay1(boolean day1) {
        this.day1 = day1;
    }

    public boolean isDay2() {
        return day2;
    }

    public void setDay2(boolean day2) {
        this.day2 = day2;
    }

    public boolean isDay3() {
        return day3;
    }

    public void setDay3(boolean day3) {
        this.day3 = day3;
    }

    public boolean isDay4() {
        return day4;
    }

    public void setDay4(boolean day4) {
        this.day4 = day4;
    }

    public String getMyClass() {
        return myClass;
    }

    public void setMyClass(String myClass) {
        this.myClass = myClass;
    }

    public String getMyTeacher() {
        return myTeacher;
    }

    public void setMyTeacher(String myTeacher) {
        this.myTeacher = myTeacher;
    }
}