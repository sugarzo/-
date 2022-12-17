package com.example.shop.model;

public class FinishOrderBean {
    private int finishorderid;
    private int productid;
    private String userid;
    private String name;
    private int buynum;
    private float allprice;

    public int getFinishorderid() {
        return finishorderid;
    }

    public void setFinishorderid(int finishorderid) {
        this.finishorderid = finishorderid;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBuynum() {
        return buynum;
    }

    public void setBuynum(int buynum) {
        this.buynum = buynum;
    }

    public float getAllprice() {
        return allprice;
    }

    public void setAllprice(float allprice) {
        this.allprice = allprice;
    }
}
