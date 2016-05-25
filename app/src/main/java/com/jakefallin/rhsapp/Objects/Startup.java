package com.jakefallin.rhsapp.Objects;

/**
 * Created by Jake on 5/18/2016.
 */
import android.util.Pair;

import java.util.List;


public class Startup {


    private String title, description;
    private boolean cb1, cb2, cb3, cb4;

    public Startup(int day) {

        title = "period" + day;
        description = "";
        cb1 = true;
        cb2 = true;
        cb3 = true;
        cb4 = true;
    }

    public Startup(String t, String d, boolean c1, boolean c2, boolean c3, boolean c4)
    {
        title = t;
        description = d;
        cb1 = c1;
        cb2 = c2;
        cb3 = c3;
        cb4 = c4;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
