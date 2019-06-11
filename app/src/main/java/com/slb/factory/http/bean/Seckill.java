package com.slb.factory.http.bean;

/**
 * 限时秒杀对象
 */
public class Seckill {


    /**
     * id : 1
     * product_id : 2
     * seckill_price : 22
     * start_time : 2019-06-01 12:00:00
     * stop_time : 2019-06-25 12:00:00
     * total_stock : 1000
     * remain_stock : 1000
     * purchase_limit : 2
     * is_delete : 0
     * create_time : 2019-05-31 15:33:53
     * product_name : 车窗自动升降器obd关窗器 老款和新款科鲁兹
     * head_img : https://img14.360buyimg.com/n0/jfs/t1/44317/14/3743/190497/5ccfd6a3E73157811/567d3edc74a7b40b.jpg
     * original_price : 77
     * serverTime : 1560140176685
     */

    private int id;
    private int product_id;
    private Double seckill_price;
    private String start_time;
    private String stop_time;
    private int total_stock;
    private int remain_stock;
    private int purchase_limit;
    private int is_delete;
    private String create_time;
    private String product_name;
    private String head_img;
    private Double original_price;
    private long serverTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public Double getSeckill_price() {
        return seckill_price;
    }

    public void setSeckill_price(Double seckill_price) {
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

    public int getTotal_stock() {
        return total_stock;
    }

    public void setTotal_stock(int total_stock) {
        this.total_stock = total_stock;
    }

    public int getRemain_stock() {
        return remain_stock;
    }

    public void setRemain_stock(int remain_stock) {
        this.remain_stock = remain_stock;
    }

    public int getPurchase_limit() {
        return purchase_limit;
    }

    public void setPurchase_limit(int purchase_limit) {
        this.purchase_limit = purchase_limit;
    }

    public int getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(int is_delete) {
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

    public Double getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(Double original_price) {
        this.original_price = original_price;
    }

    public long getServerTime() {
        return serverTime;
    }

    public void setServerTime(long serverTime) {
        this.serverTime = serverTime;
    }
}
