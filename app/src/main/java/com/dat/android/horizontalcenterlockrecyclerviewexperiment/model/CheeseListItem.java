package com.dat.android.horizontalcenterlockrecyclerviewexperiment.model;

import java.util.List;

/**
 * Created by Nguyen on 7/5/2016.
 */
public class CheeseListItem {
    private List<String> cheese;
    private String name;

    public CheeseListItem() {
    }

    public CheeseListItem(List<String> cheese, String name) {
        this.cheese = cheese;
        this.name = name;
    }

    public List<String> getCheese() {
        return cheese;
    }

    public void setCheese(List<String> cheese) {
        this.cheese = cheese;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
