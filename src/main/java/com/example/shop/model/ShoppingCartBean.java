package com.example.shop.model;

public class ShoppingCartBean {

    private int orderid;

    private int productid;

    private String name;

    private int buynum;

    private float price;

    private float allprice;

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getAllprice() {
        return allprice;
    }

    public void setAllprice(float allprice) {
        this.allprice = allprice;
    }
}
