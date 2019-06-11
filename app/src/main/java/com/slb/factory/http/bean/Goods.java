package com.slb.factory.http.bean;

public class Goods {

    /**
     * id : 6
     * name : 车窗自动升降器obd关窗器 老款和新款科鲁兹
     * head_img : https://img14.360buyimg.com/n0/jfs/t1/44317/14/3743/190497/5ccfd6a3E73157811/567d3edc74a7b40b.jpg
     * original_price : 77
     * discount_price : 44
     */

    private Long id;
    private String name;
    private String head_img;
    private Double original_price;
    private Double discount_price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHead_img() {
        return head_img;
    }

    public void setHead_img(String head_img) {
        this.head_img = head_img;
    }

    public Double getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(Double original_price) {
        this.original_price = original_price;
    }

    public Double getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(Double discount_price) {
        this.discount_price = discount_price;
    }
}
