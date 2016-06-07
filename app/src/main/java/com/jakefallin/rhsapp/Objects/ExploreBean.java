package com.jakefallin.rhsapp.Objects;

/**
 * Created by Jake on 4/17/2016.
 */

public class ExploreBean {

    private String when, where, more, date;

    public ExploreBean(String mWhen, String mWhere, String mMore, String mDate) {
        when = mWhen;
        where = mWhere;
        more = mMore;
        date = mDate;

    }

    public String getWhen() {
        return when;
    }

    public String getWhere() {
        return where;
    }

    public String getMore() {
        return more;
    }

    public String getDate() {
        return date;
    }
}