package com.slb.factory.http.service;

import com.slb.factory.http.bean.Brand;
import com.slb.factory.http.bean.Goods;
import com.slb.factory.http.bean.MsgEntity;
import com.slb.factory.http.bean.OrderEntity;
import com.slb.factory.http.bean.Seckill;
import com.slb.factory.http.bean.UserEntity;
import com.slb.frame.http2.retrofit.HttpMjListResult;
import com.slb.frame.http2.retrofit.HttpMjResult;
import com.slb.frame.http2.retrofit.HttpResult;
import com.slb.frame.http2.rxjava.HttpMjEntityFun;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

public interface ComService {

    /**
     * 用户-登录
     */
    @FormUrlEncoded
    @POST("app/user/login")
    Observable<HttpMjResult<UserEntity>> login(@Field("mobile") String mobile,
                                               @Field("verifyCode") String verifyCode,
                                               @Field("platform") int platform);

    /**
     * 用户-发短信验证码
     */
    @FormUrlEncoded
    @POST("app/common/sms")
    Observable<HttpMjResult<Object>> sendMsgCode(@Field("mobile") String mobile);

    /**
     * 用户-个人账号信息
     */
    @FormUrlEncoded
    @POST("app/user/info")
    Observable<HttpMjResult<UserEntity>> getUserINfo(@Field("mobile") String mobile);

    /**
     * 用户-上传营业执照
     */
    @FormUrlEncoded
    @POST("app/user/uploadLicense")
    Observable<HttpMjResult<Object>> uploadLicense(@Field("token") String token,@Field("businessLicense") String businessLicense);

    /**
     * 用户-七牛获取上传凭证token
     */
    @FormUrlEncoded
    @POST("/app/qiniu/uptoken"  )
    Observable<HttpMjResult<String>> getPicToken(@Field("token") String token);


    /**
     * 消息列表
     */
    @FormUrlEncoded
    @POST("app/message/list")
    Observable<HttpResult<Object, MsgEntity>> getMsgList(
            @Field("pageSize") int pageSize,
            @Field("pageIndex") int pageNum
    );

    /**
     * 订单列表
     * 状态：0已下单、1待发货、3待收货、4已完成、5已取消
     */
    @FormUrlEncoded
    @POST("app/order/list")
    Observable<HttpResult<Object, OrderEntity>> getOrderList(
            @Field("token") String token,
            @Field("state") int state,
            @Field("pageSize") int pageSize,
            @Field("pageIndex") int pageNum
    );

    /**
     * 首页 热门品牌列表
     */
    @FormUrlEncoded
    @POST("/app/product/brand/hotList"  )
    Observable<HttpMjResult<List<Brand>>> getHotBrandList(@Field("token") String token);

    /**
     * 首页 限时秒杀
     */
    @FormUrlEncoded
    @POST("/app/qiniu/uptoken"  )
    Observable<HttpMjResult<List<Seckill>>> getLimited(@Field("token") String token);

    /**
     * 用户- 热门商品
     */
    @FormUrlEncoded
    @POST("/app/qiniu/uptoken"  )
    Observable<HttpMjResult<HttpMjListResult<Goods>>> getHotGoods(@Field("pageSize") int pageSize,
                                                    @Field("pageIndex") int pageNum);

}
