package com.slb.factory.http.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class ConfigEntity implements Parcelable {

    /**
     * aboutus : <p>这是关于我们<br/></p>
     * declare : <p>特约声明</p>
     * xieyi : <p>这是用户协议修改之后的</p>
     */

    private String aboutus;
    private String declare;
    private String xieyi;
    private String kefu;

    public String getAboutus() {
        return aboutus;
    }

    public void setAboutus(String aboutus) {
        this.aboutus = aboutus;
    }

    public String getDeclare() {
        return declare;
    }

    public void setDeclare(String declare) {
        this.declare = declare;
    }

    public String getXieyi() {
        return xieyi;
    }

    public void setXieyi(String xieyi) {
        this.xieyi = xieyi;
    }

    public String getKefu() {
        return kefu;
    }

    public void setKefu(String kefu) {
        this.kefu = kefu;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.aboutus);
        dest.writeString(this.declare);
        dest.writeString(this.xieyi);
        dest.writeString(this.kefu);
    }

    public ConfigEntity() {
    }

    protected ConfigEntity(Parcel in) {
        this.aboutus = in.readString();
        this.declare = in.readString();
        this.xieyi = in.readString();
        this.kefu = in.readString();
    }

    public static final Parcelable.Creator<ConfigEntity> CREATOR = new Parcelable.Creator<ConfigEntity>() {
        @Override
        public ConfigEntity createFromParcel(Parcel source) {
            return new ConfigEntity(source);
        }

        @Override
        public ConfigEntity[] newArray(int size) {
            return new ConfigEntity[size];
        }
    };
}
