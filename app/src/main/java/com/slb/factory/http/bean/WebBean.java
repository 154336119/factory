package com.slb.factory.http.bean;

import java.io.Serializable;

public class WebBean implements Serializable {

    private static final long serialVersionUID = -2034838654151599403L;

    public int type;


    public int isRefund; //0右上角啥也不加， 1酒店订单退款 2团购订单退款 -1会员说明

    public String imgurl;


    public String phoneNumber;

    public String payType;// 1支付宝、2微信支付;

    public String orderCode;

    public boolean isRefresh;
    public boolean isHideNarBar; //是否隐藏导航栏

    public String linkType;//（首页：index，酒店详情：hotelDetails）。

    public String id;// 酒店id
    public String restId;
    public String price;

    //是否显示分享按钮
    public boolean isRightBtnShare;

    // 标题：
    public String title;
    //副标题：
    public String subTitle;
    // 封面图：
    public String headImg;
    //内容：
    public String content;
    //路径：
    public String url;
}