package com.fisher.po;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/15/.
 */
public class RouteInfo implements Serializable {
        private String time;
        private String content;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

    public RouteInfo(String time, String content) {
        this.time = time;
        this.content = content;
    }
}
