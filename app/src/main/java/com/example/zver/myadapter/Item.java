package com.example.zver.myadapter;

import java.util.ArrayList;

/**
 * Created 04.07.2016.
 */
public class Item {

    private String name;
    private boolean checkStatus = false;

    public Item(String name, boolean checkStatus){
        this.name = name;
        this.checkStatus = checkStatus;
    }

    public Item(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(boolean checkStatus) {
        this.checkStatus = checkStatus;
    }
}
