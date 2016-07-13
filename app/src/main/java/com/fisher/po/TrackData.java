package com.fisher.po;

import java.io.Serializable;
import java.util.List;


public class TrackData implements Serializable {

    /**
     * id : zjs
     * name : 宅急送快递
     * order : 3944490863
     * message :
     * errcode : 0000
     * status : 4
     * data : [{"time":"2016-05-19 20:15:20","content":"已取件，离开山东_潍坊运转中心"},{"time":"2016-05-20 03:13:54","content":"离开山东_潍坊运转中心"},{"time":"2016-05-20 08:31:51","content":"离开山东_配送区部_潍坊营业所_河滩镇加盟商"},{"time":"2016-05-20 13:11:46","content":"离开郑州运转中心"},{"time":"2016-05-21 01:01:07","content":"离开郑州运转中心"},{"time":"2016-05-21 07:52:15","content":"离开河南_配送区部_南阳分拨站"},{"time":"2016-05-21 08:33:57","content":"离开河南_配送区部_南阳分拨站"},{"time":"2016-05-21 10:14:40","content":"离开河南_配送区部_南阳分拨站_白河南A加盟商"},{"time":"2016-05-21 12:50:00","content":"客户已签收"}]
     */

    private String id;
    private String name;
    private String order;
    private String message;
    private String errcode;
    private int status;
    /**
     * time : 2016-05-19 20:15:20
     * content : 已取件，离开山东_潍坊运转中心
     */

    private List<RouteInfo> data;

    private String context;
    private String time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<RouteInfo> getData() {
        return data;
    }

    public void setData(List<RouteInfo> data) {
        this.data = data;
    }

    public String getContext() {
        if (data != null && data.size() > 0) {
            context = data.get(0).getContent();
        }
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getTime() {
        if (data != null && data.size() > 0) {
            time = data.get(0).getTime();
        }
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
