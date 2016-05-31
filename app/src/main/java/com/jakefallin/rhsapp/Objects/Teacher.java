package com.jakefallin.rhsapp.Objects;

/**
 * Created by Jake on 3/11/2016.
 */
public class Teacher {

    private String first, last, email;


    public Teacher() {
    }

    public Teacher(String first1, String last1, String email1) {
        first = first1;
        last = last1;
        email = email1;

    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

