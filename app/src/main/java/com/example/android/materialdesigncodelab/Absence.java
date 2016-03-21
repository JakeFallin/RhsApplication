package com.example.android.materialdesigncodelab;

/**
 * Created by Jake on 3/11/2016.
 */
public class Absence {

    private String date, locationPreset, periodPreset;
    private String absences;
    private String name, info, location, reason;

    public Absence() {}

    public Absence(String name1, String location1, String reason1)
    {
        name = name1;
        location = location1;
        reason = reason1;
    }

    public String getAbsences() {
        return absences;
    }

    public String getDate() {
        return date;
    }

    public String getLocationPreset() {
        return locationPreset;
    }

    public String getName() { return name; }

    public String getPeriodPreset() {
        return periodPreset;
    }

    public String getInfo() {
        return info;
    }

    public String getLocation() {
        return location;
    }

    public String getReason() {
        return reason;
    }
}
