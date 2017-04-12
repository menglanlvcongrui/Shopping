package com.example.administrator.shopping.bean;

import java.util.List;

/**
 * Created by Administrator on 2017-04-11.
 */

public class CircleBean {
    private List<CircleBean.DataBean> data;

    public List<CircleBean.DataBean> getData() {
        return data;
    }

    public void setData(List<CircleBean.DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String title;
        private String content;
        private String img;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }

}
