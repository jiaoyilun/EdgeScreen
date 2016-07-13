package com.fisher.wx;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jiaoy on 2016/7/1/.
 */
public class WeixinEntity {

    /**
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2016-07-01","title":"閽卞涓�釜濂冲瀛愭湁澶氶噸瑕�","description":"HUGO","picUrl":"http://zxpic.gtimg.com/infonew/0/wechat_pics_-6514620.jpg/640","url":"http://mp.weixin.qq.com/s?__biz=MjM5MzI5NzQ1MA==&idx=1&mid=2654624443&sn=ee6d80b825fdadd91240b0b2cb7e7be8"}]
     */

    private int code;
    private String msg;
    /**
     * ctime : 2016-07-01
     * title : 閽卞涓�釜濂冲瀛愭湁澶氶噸瑕�
     * description : HUGO
     * picUrl : http://zxpic.gtimg.com/infonew/0/wechat_pics_-6514620.jpg/640
     * url : http://mp.weixin.qq.com/s?__biz=MjM5MzI5NzQ1MA==&idx=1&mid=2654624443&sn=ee6d80b825fdadd91240b0b2cb7e7be8
     */

    @SerializedName("newslist")
    private List<Article> articleList;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }


}
