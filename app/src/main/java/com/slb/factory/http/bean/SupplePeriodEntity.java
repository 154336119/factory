package com.slb.factory.http.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class SupplePeriodEntity implements Parcelable {
    private Integer supplePeriodSign;

    public Integer getSupplePeriodSign() {
        return supplePeriodSign;
    }

    public void setSupplePeriodSign(Integer supplePeriodSign) {
        this.supplePeriodSign = supplePeriodSign;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.supplePeriodSign);
    }

    public SupplePeriodEntity() {
    }

    protected SupplePeriodEntity(Parcel in) {
        this.supplePeriodSign = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<SupplePeriodEntity> CREATOR = new Parcelable.Creator<SupplePeriodEntity>() {
        @Override
        public SupplePeriodEntity createFromParcel(Parcel source) {
            return new SupplePeriodEntity(source);
        }

        @Override
        public SupplePeriodEntity[] newArray(int size) {
            return new SupplePeriodEntity[size];
        }
    };
}
