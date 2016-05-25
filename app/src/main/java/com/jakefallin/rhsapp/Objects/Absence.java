package com.jakefallin.rhsapp.Objects;

import android.util.Pair;

import java.util.List;

/**
 * Created by Jake on 3/11/2016.
 */
public class Absence {


    private String name;
    private List<Pair<String, String>> locations;

    public Absence() {}

    public Absence(String name1, List<Pair<String,String>> locations1)
    {
        name = name1;
        locations = locations1;
    }


    public String getName() { return name; }





    public List<Pair<String, String>> getLocation() {
        return locations;
    }
}
