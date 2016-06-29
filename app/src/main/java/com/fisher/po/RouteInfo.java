package com.fisher.po;

/**
 * Created by Administrator on 2016/6/15/.
 */
public class RouteInfo {
    private String time;

    private String location;

    private String context;

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return this.time;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return this.location;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getContext() {
        return this.context;
    }

    public RouteInfo(String location, String time, String context) {
        this.location = location;
        this.time = time;
        this.context = context;
    }
}
