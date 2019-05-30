package com.slb.factory.http.bean;

public class ProductEntity {

    /**
     * single_price : 44
     * num : 1
     * spec_value : 绿
     * product_id : 6
     * name : 车窗自动升降器obd关窗器 老款和新款科鲁兹
     * head_img : https://img14.360buyimg.com/n0/jfs/t1/44317/14/3743/190497/5ccfd6a3E73157811/567d3edc74a7b40b.jpg
     */

    private Integer single_price;
    private Integer num;
    private String spec_value;
    private Integer product_id;
    private String name;
    private String head_img;

    public Integer getSingle_price() {
        return single_price;
    }

    public void setSingle_price(Integer single_price) {
        this.single_price = single_price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getSpec_value() {
        return spec_value;
    }

    public void setSpec_value(String spec_value) {
        this.spec_value = spec_value;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
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
}
