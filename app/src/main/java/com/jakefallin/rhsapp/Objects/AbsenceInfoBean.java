package com.jakefallin.rhsapp.Objects;

/**
 * Created by Molly on 5/23/2016.
 */


public class AbsenceInfoBean {

    private String location;
    private String period;

    public AbsenceInfoBean(String mlocation, String mperiod) {
        location = mlocation;
        period = mperiod;
    }

    public String getLocation() {
        return location;
    }

    public String getPeriod() {
        return period;
    }
}
