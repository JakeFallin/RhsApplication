package com.jakefallin.rhsapp.Objects;

import java.util.ArrayList;

/**
 * Created by Molly on 5/23/2016.
 */


public class TeachersInfoBean {

    private ArrayList<Teacher> t;

    public TeachersInfoBean(ArrayList<Teacher> myTeacher)
    {
        t = myTeacher;
    }

    public ArrayList<Teacher> getTeachers() {
        return t;
    }

    public void setTeachers(ArrayList<Teacher> t) {
        this.t = t;
    }
}
