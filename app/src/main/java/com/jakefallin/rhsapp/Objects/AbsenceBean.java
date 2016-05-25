package com.jakefallin.rhsapp.Objects;

import java.util.List;

/**
 * Created by Jake on 3/11/2016.
 */
public class AbsenceBean {

    private String name;
    private List<AbsenceInfoBean> AbsenceInfoBeanList;

    public AbsenceBean(String mname) {name = mname;}

    public List<AbsenceInfoBean> getAbsenceInfoBeanList() {
        return AbsenceInfoBeanList;
    }

    public void setAbsenceInfoBeanList(List<AbsenceInfoBean> absenceInfoBeanList) {
        this.AbsenceInfoBeanList = absenceInfoBeanList;
    }

    public String getName() {
        return name;
    }


}
