package com.slb.factory.http.bean;

/**
 * 限时秒杀对象
 */
public class Seckill {

    /**
     * id : 3
     * product_id : 4
     * seckill_price : 10
     * start_time : 2019-05-31 04:36:03
     * stop_time : 2019-06-10 04:36:14
     * total_stock : 10
     * remain_stock : 0
     * purchase_limit : 1
     * is_delete : 0
     * create_time : 2019-06-01 04:33:58
     * product_name : 车窗自动升降器obd关窗器 老款和新款科鲁兹
     * head_img : https://img14.360buyimg.com/n0/jfs/t1/44317/14/3743/190497/5ccfd6a3E73157811/567d3edc74a7b40b.jpg
     * original_price : 77
     */

    private Integer id;
    private Integer product_id;
    private Integer seckill_price;
    private String start_time;
    private String stop_time;
    private Integer total_stock;
    private Integer remain_stock;
    private Integer purchase_limit;
    private Integer is_delete;
    private String create_time;
    private String product_name;
    private String head_img;
    private Integer original_price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Integer getSeckill_price() {
        return seckill_price;
    }

    public void setSeckill_price(Integer seckill_price) {
        this.seckill_price = seckill_price;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getStop_time() {
        return stop_time;
    }

    public void setStop_time(String stop_time) {
        this.stop_time = stop_time;
    }

    public Integer getTotal_stock() {
        return total_stock;
    }

    public void setTotal_stock(Integer total_stock) {
        this.total_stock = total_stock;
    }

    public Integer getRemain_stock() {
        return remain_stock;
    }

    public void setRemain_stock(Integer remain_stock) {
        this.remain_stock = remain_stock;
    }

    public Integer getPurchase_limit() {
        return purchase_limit;
    }

    public void setPurchase_limit(Integer purchase_limit) {
        this.purchase_limit = purchase_limit;
    }

    public Integer getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(Integer is_delete) {
        this.is_delete = is_delete;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getHead_img() {
        return head_img;
    }

    public void setHead_img(String head_img) {
        this.head_img = head_img;
    }

    public Integer getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(Integer original_price) {
        this.original_price = original_price;
    }
}
