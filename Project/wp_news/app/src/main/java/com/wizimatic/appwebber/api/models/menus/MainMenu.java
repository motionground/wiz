package com.wizimatic.appwebber.api.models.menus;

import com.google.gson.annotations.SerializedName;

/**
 * Created by SAIF-MCC on 8/21/2017.
 */

public class MainMenu {
    @SerializedName("ID")
    private Double mID;
    @SerializedName("name")
    private String mName;
    @SerializedName("count")
    private Double mCount;

    public MainMenu() {
    }

    public Double getID() {
        return mID;
    }

    public void setID(Double ID) {
        mID = ID;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Double getCount() {
        return mCount;
    }

    public void setCount(Double count) {
        mCount = count;
    }
}
