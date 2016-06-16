package com.fisher.po;

import java.util.List;

/**
 * Created by Administrator on 2016/6/15/.
 *
 *
 * {"nu":"3944490863","comcontact":"400-6789-000","companytype":"zhaijisong","com":"zhaijisong","condition":"F00","status":"1","codenumber":"3944490863","state":"3","data":[{"time":"2016-05-21 12:50:00","location":"","context":"客户已签收"},{"time":"2016-05-21 10:14:40","location":"","context":"离开 [河南_配送区部_南阳分拨站_白河南A加盟商] 派送中，递送员[薛甫]，电话[15738092568]"},{"time":"2016-05-21 08:33:57","location":"","context":"离开 [河南_配送区部_南阳分拨站] 发往 [河南_配送区部_南阳分拨站_白河南A加盟商]"},{"time":"2016-05-21 07:52:15","location":"","context":"到达 [河南_配送区部_南阳分拨站]"},{"time":"2016-05-21 01:01:07","location":"","context":"离开 [郑州运转中心] 发往 [河南_配送区部_南阳分拨站]"},{"time":"2016-05-20 13:11:46","location":"","context":"到达 [郑州运转中心]"},{"time":"2016-05-20 08:31:51","location":"","context":"到达 [山东_配送区部_潍坊营业所_河滩镇加盟商]"},{"time":"2016-05-20 03:13:54","location":"","context":"离开 [山东_潍坊运转中心] 发往 [郑州运转中心]"},{"time":"2016-05-19 20:15:20","location":"","context":"已取件，到达 [山东_潍坊运转中心]"}],"message":"ok","ischeck":"1","comurl":"http://www.zjs.com.cn"}
 *
 *
 */
public class TrackData {
    private String nu;

    private String comcontact;

    private String companytype;

    private String com;

    private String condition;

    private String status;

    private String codenumber;

    private String state;

    private List<RouteInfo> data;

    private String message;

    private String ischeck;

    private String comurl;

    public String getNu() {
        return nu;
    }

    public void setNu(String nu) {
        this.nu = nu;
    }

    public String getComcontact() {
        return comcontact;
    }

    public void setComcontact(String comcontact) {
        this.comcontact = comcontact;
    }

    public String getCompanytype() {
        return companytype;
    }

    public void setCompanytype(String companytype) {
        this.companytype = companytype;
    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCodenumber() {
        return codenumber;
    }

    public void setCodenumber(String codenumber) {
        this.codenumber = codenumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<RouteInfo> getData() {
        return data;
    }

    public void setData(List<RouteInfo> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIscheck() {
        return ischeck;
    }

    public void setIscheck(String ischeck) {
        this.ischeck = ischeck;
    }

    public String getComurl() {
        return comurl;
    }

    public void setComurl(String comurl) {
        this.comurl = comurl;
    }
}
