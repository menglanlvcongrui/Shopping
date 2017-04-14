package com.example.administrator.shopping.bean;

import java.util.List;

/**
 * @Description: 描述
 * @AUTHOR   Create By 2016/10/9 0009 15:17
 */
public class RegionJson {


    /**
     * 省
     */
    public int id;
    public String name;


    public List<ChildEntity> child;

    /**
     * 市
     */
    public static class ChildEntity {
        public int    id;
        public String name;

        public List<ChildEntity2> child;

        /**
         * 区
         */
        public static class ChildEntity2 {
            public int    id;
            public String name;
            public String zipcode;
        }
    }
}
