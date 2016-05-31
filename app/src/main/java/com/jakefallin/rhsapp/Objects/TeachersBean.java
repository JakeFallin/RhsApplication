package com.jakefallin.rhsapp.Objects;

import java.util.List;

/**
 * Created by Jake on 3/11/2016.
 */
public class TeachersBean {

    private String letter;
    private List<TeachersInfoBean> teachersInfoBeanList;

    public TeachersBean(String mLetter) {letter = mLetter;}

    public List<TeachersInfoBean> getTeachersInfoBeanList() {
        return teachersInfoBeanList;
    }

    public void setTeachersInfoBeanList(List<TeachersInfoBean> mTeachersInfoBeanList) {
        teachersInfoBeanList = mTeachersInfoBeanList;
    }

    public String getLetter() {
        return letter;
    }


}
