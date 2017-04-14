package com.example.administrator.shopping.bean;

/**
 * 购物车数组集合
 * Created by GX on 2017/4/14 0014.
 */
public class CartBean {
    boolean isselect;//表示是否选中数组
    int  moneylist;//表示价格数组
    int numberlist;//表示数量数组
    int finish_isVisible;//是否显示完成页面
    int edit_isVisible;//是否显示编辑页面
    int finishlist;//数据更新，计数数组（是否更新数据）

    public boolean isselect() {
        return isselect;
    }

    public void setIsselect(boolean isselect) {
        this.isselect = isselect;
    }

    public int getMoneylist() {
        return moneylist;
    }

    public void setMoneylist(int moneylist) {
        this.moneylist = moneylist;
    }

    public int getNumberlist() {
        return numberlist;
    }

    public void setNumberlist(int numberlist) {
        this.numberlist = numberlist;
    }

    public int getFinish_isVisible() {
        return finish_isVisible;
    }

    public void setFinish_isVisible(int finish_isVisible) {
        this.finish_isVisible = finish_isVisible;
    }

    public int getEdit_isVisible() {
        return edit_isVisible;
    }

    public void setEdit_isVisible(int edit_isVisible) {
        this.edit_isVisible = edit_isVisible;
    }

    public int getFinishlist() {
        return finishlist;
    }

    public void setFinishlist(int finishlist) {
        this.finishlist = finishlist;
    }
    public CartBean(){

    }
}