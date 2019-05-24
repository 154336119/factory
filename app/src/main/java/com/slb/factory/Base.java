package com.slb.factory;

import android.content.Context;
import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSONObject;
import com.slb.factory.http.bean.UserEntity;
import com.slb.factory.util.SharedPreferencesUtils;
import com.slb.factory.util.config.BizcContant;
import com.slb.factory.util.io.AbstractGatewayService;

import static com.slb.factory.util.config.BizcContant.SP_USER;


/**
 * 刁剑
 * 2017/11/1
 * 注释:
 */

public class Base {

    /** 测试环境*/
    public static final int DEBUG = 999;
    /** 预发布环境*/
    public static final int LIVE = 998;
    /** 线上环境*/
    public static final int RELEASE = 997;
    private static Context mContext;
    /** APP环境：debug、live、release */
    private static int mEnvironment;

    private static UserEntity mUserEntity;
    private static AbstractGatewayService service;


    public void setService(AbstractGatewayService service) {
        this.service = service;
    }

    public static void  initialize(@NonNull Context context) {
        mContext = context;
    }

    public static Context getContext() {
        synchronized (Base.class){
            if (Base.mContext == null){
                throw new NullPointerException("Call Base.initialize(context) within your Application onCreate() method.");
            }
            return Base.mContext;
        }
    }

    /**
     * 获取用户信息
     * @return
     */
    public static UserEntity getUserEntity() {

        if(mUserEntity == null){
            String userJsonStr = (String) SharedPreferencesUtils.getParam(Base.getContext(), BizcContant.SP_USER, "");
            UserEntity entity =JSONObject.parseObject(userJsonStr,UserEntity.class);
            Base.setUserEntity(entity);
        }
        return mUserEntity;
    }

    /**
     * 保存用户信息信息
     * @return
     */
    public static void setUserEntity(final UserEntity mUserEntity) {
        String userJsonStr = JSONObject.toJSONString(mUserEntity);//将java对象转换为json对象
        SharedPreferencesUtils.setParam(mContext, SP_USER,userJsonStr);
        Base.mUserEntity = mUserEntity;
    }



}
