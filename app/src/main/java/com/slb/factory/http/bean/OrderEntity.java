package com.slb.factory.http.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class OrderEntity implements Parcelable {

    /**
     * id : 3
     * user_id : 3
     * order_code : 19053085974987
     * address_id : 2
     * remark : 留言啦111
     * delivery_money : 12
     * product_money : 44
     * pay_money : 56
     * create_time : 2019-05-30 23:53:08
     * pay_type : 0
     * pay_certificate :
     * pay_time :
     * refuse_reason :
     * state : 0
     * productList : [{"single_price":44,"num":1,"spec_value":"绿","product_id":6,"name":"车窗自动升降器obd关窗器 老款和新款科鲁兹","head_img":"https://img14.360buyimg.com/n0/jfs/t1/44317/14/3743/190497/5ccfd6a3E73157811/567d3edc74a7b40b.jpg"}]
     */

    private Long id;
    private Long user_id;
    private String order_code;
    private Long address_id;
    private String remark;
    private Double delivery_money;
    private Double product_money;
    private Double pay_money;
    private String create_time;
    private Long pay_type;
    private String pay_certificate;
    private String pay_time;
    private String refuse_reason;
    private Long state;
    private List<ProductEntity> productList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getOrder_code() {
        return order_code;
    }

    public void setOrder_code(String order_code) {
        this.order_code = order_code;
    }

    public Long getAddress_id() {
        return address_id;
    }

    public void setAddress_id(Long address_id) {
        this.address_id = address_id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Double getDelivery_money() {
        return delivery_money;
    }

    public void setDelivery_money(Double delivery_money) {
        this.delivery_money = delivery_money;
    }

    public Double getProduct_money() {
        return product_money;
    }

    public void setProduct_money(Double product_money) {
        this.product_money = product_money;
    }

    public Double getPay_money() {
        return pay_money;
    }

    public void setPay_money(Double pay_money) {
        this.pay_money = pay_money;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public Long getPay_type() {
        return pay_type;
    }

    public void setPay_type(Long pay_type) {
        this.pay_type = pay_type;
    }

    public String getPay_certificate() {
        return pay_certificate;
    }

    public void setPay_certificate(String pay_certificate) {
        this.pay_certificate = pay_certificate;
    }

    public String getPay_time() {
        return pay_time;
    }

    public void setPay_time(String pay_time) {
        this.pay_time = pay_time;
    }

    public String getRefuse_reason() {
        return refuse_reason;
    }

    public void setRefuse_reason(String refuse_reason) {
        this.refuse_reason = refuse_reason;
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

    public List<ProductEntity> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductEntity> productList) {
        this.productList = productList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeValue(this.user_id);
        dest.writeString(this.order_code);
        dest.writeValue(this.address_id);
        dest.writeString(this.remark);
        dest.writeValue(this.delivery_money);
        dest.writeValue(this.product_money);
        dest.writeValue(this.pay_money);
        dest.writeString(this.create_time);
        dest.writeValue(this.pay_type);
        dest.writeString(this.pay_certificate);
        dest.writeString(this.pay_time);
        dest.writeString(this.refuse_reason);
        dest.writeValue(this.state);
        dest.writeList(this.productList);
    }

    public OrderEntity() {
    }

    protected OrderEntity(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.user_id = (Long) in.readValue(Long.class.getClassLoader());
        this.order_code = in.readString();
        this.address_id = (Long) in.readValue(Long.class.getClassLoader());
        this.remark = in.readString();
        this.delivery_money = (Double) in.readValue(Double.class.getClassLoader());
        this.product_money = (Double) in.readValue(Double.class.getClassLoader());
        this.pay_money = (Double) in.readValue(Double.class.getClassLoader());
        this.create_time = in.readString();
        this.pay_type = (Long) in.readValue(Long.class.getClassLoader());
        this.pay_certificate = in.readString();
        this.pay_time = in.readString();
        this.refuse_reason = in.readString();
        this.state = (Long) in.readValue(Long.class.getClassLoader());
        this.productList = new ArrayList<ProductEntity>();
        in.readList(this.productList, ProductEntity.class.getClassLoader());
    }

    public static final Creator<OrderEntity> CREATOR = new Creator<OrderEntity>() {
        @Override
        public OrderEntity createFromParcel(Parcel source) {
            return new OrderEntity(source);
        }

        @Override
        public OrderEntity[] newArray(int size) {
            return new OrderEntity[size];
        }
    };
}
