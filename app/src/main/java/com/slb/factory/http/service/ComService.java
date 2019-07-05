package com.slb.factory.http.service;

import com.slb.factory.http.bean.Brand;
import com.slb.factory.http.bean.ConfigEntity;
import com.slb.factory.http.bean.Goods;
import com.slb.factory.http.bean.MsgEntity;
import com.slb.factory.http.bean.OrderEntity;
import com.slb.factory.http.bean.PayEntity;
import com.slb.factory.http.bean.PayTypeEntity;
import com.slb.factory.http.bean.Seckill;
import com.slb.factory.http.bean.UpdateEntity;
import com.slb.factory.http.bean.UserEntity;
import com.slb.frame.http2.retrofit.HttpMjListResult;
import com.slb.frame.http2.retrofit.HttpMjResult;
import com.slb.frame.http2.retrofit.HttpResult;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
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
     * 第三方登录
     *
     * @param
     * @return
     */
    @FormUrlEncoded
    @POST("/app/user/login/wechat")
    Observable<HttpMjResult<UserEntity>> loginThird(@Field("openid") String openid,
                                                    @Field("nickName") String nickName,
                                                    @Field("logo") String logo,
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
     * 状态：0待支付、1待发货、3待收货、4已完成、5已取消
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
    @POST("/app/seckill/list"  )
    Observable<HttpMjResult<List<Seckill>>> getLimited(@Field("token") String token);

    /**
     * 首页 热门商品
     */
    @FormUrlEncoded
    @POST("/app/product/hotList" )
    Observable<HttpMjListResult<Goods>> getHotGoods(@Field("pageSize") int pageSize,
                                                    @Field("pageIndex") int pageNum);

    /**
     * 首页 - 导航条
     */
    @FormUrlEncoded
    @POST("/app/banner/list"  )
    Observable<HttpMjResult<List<String>>> getBannerList(@Field("token") String token);

    /**
     * 订单 - 上传打款凭证
     */
    @FormUrlEncoded
    @POST("/app/order/upload/certificate"  )
    Observable<HttpMjResult<Object>> uploadProofs(@Field("orderId") String orderId,
                                                 @Field("imgList") String imgList);

    /**
     * 订单 - 确认收货
     */
    @FormUrlEncoded
    @POST("/app/order/finish"  )
    Observable<HttpMjResult<Object>> orderFinish(@Field("orderId") String orderId);

    /**
     * 升级
     */
    @FormUrlEncoded
    @POST("/app/version/check"  )
    Observable<HttpMjResult<UpdateEntity>> getUpdateInfo(@Field("platform") int platform);

    /**
     * 支付
     */
    @FormUrlEncoded
    @POST("/app/config/paymentCode"  )
    Observable<HttpMjResult<PayTypeEntity>> getPayTypeConfig(@Field("token") Integer token);

    /**
     * 我的- 用户协议
     */
    @FormUrlEncoded
    @POST("/app/config/aboutus"  )
    Observable<HttpMjResult<ConfigEntity>> getConfig(@Field("token") Integer token);

    /**
     * 订单数目
     */
    @FormUrlEncoded
    @POST("/app/order/num"  )
    Observable<HttpMjResult<Integer>> getOrderNum(@Field("token") String token);

    /**
     *  用户信息
     */
    @FormUrlEncoded
    @POST("/app/user/info"  )
    Observable<HttpMjResult<UserEntity>> getUserInfo(@Field("token") String token);

    /**
     *  支付参数
     */
    @FormUrlEncoded
    @POST("/app/order/getParams"  )
    Observable<HttpMjResult<PayEntity>> getPayParams(@Field("payType") int payType , @Field("orderCode") String orderCode);



    /**
     *  支付结果
     */
    @GET("/app/order/query/payStatus"  )
    Observable<HttpMjResult<String>> getPayState(@Query("payType") int payType , @Query("orderCode") String orderCode);




//    /**
//     *  用户信息
//     */
//    @FormUrlEncoded
//    @POST("/app/user/info"  )
//    Observable<HttpMjResult<UserEntity>> getUserInfo(@Field("token") String token);


//    /app/order/query/payStatus
}
