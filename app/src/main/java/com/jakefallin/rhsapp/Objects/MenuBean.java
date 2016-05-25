package com.jakefallin.rhsapp.Objects;

import java.util.List;

public class MenuBean {
    private String title;
    private List<ExploreBean> exploreBeanList;

    public MenuBean(String mTitle) {
        title = mTitle;
    }

    public List<ExploreBean> getExploreBeanList() {
        return exploreBeanList;
    }

    public void setExploreBeanList(List<ExploreBean> exploreBeanList) {
        this.exploreBeanList = exploreBeanList;
    }

    public String getTitle() {
        return title;
    }
}
