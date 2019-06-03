package com.slb.factory.http.bean;

import java.util.List;

public class HomeMergeEntity {
    List<Brand> mBrandList;
    List<Seckill> mSeckillList;
    List<Goods> mGoodsList;


    public List<Brand> getmBrandList() {
        return mBrandList;
    }

    public void setmBrandList(List<Brand> mBrandList) {
        this.mBrandList = mBrandList;
    }

    public List<Seckill> getmSeckillList() {
        return mSeckillList;
    }

    public void setmSeckillList(List<Seckill> mSeckillList) {
        this.mSeckillList = mSeckillList;
    }

    public List<Goods> getmGoodsList() {
        return mGoodsList;
    }

    public void setmGoodsList(List<Goods> mGoodsList) {
        this.mGoodsList = mGoodsList;
    }
}
