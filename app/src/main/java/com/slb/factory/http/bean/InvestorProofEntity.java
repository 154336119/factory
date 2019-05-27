package com.slb.factory.http.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Gifford on 2017/11/23.
 */

public class InvestorProofEntity implements Parcelable {
	@JSONField(serialize=false)
	private boolean isNeed;
	private String materialCode;
	private OssRemoteFile materialValue=new OssRemoteFile();
	@JSONField(serialize=false)
	private String name;
	@JSONField(serialize=false)
	private boolean isLocalImg;
	@JSONField(serialize=false)
	private String localurl;

	public String getLocalurl() {
		return localurl;
	}

	public void setLocalurl(String localurl) {
		this.localurl = localurl;
	}

	public boolean isNeed() {
		return isNeed;
	}

	public void setNeed(boolean need) {
		isNeed = need;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public OssRemoteFile getMaterialValue() {
		return materialValue;
	}

	public void setMaterialValue(OssRemoteFile materialValue) {
		this.materialValue = materialValue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isLocalImg() {
		return isLocalImg;
	}

	public void setLocalImg(boolean localImg) {
		isLocalImg = localImg;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeByte(this.isNeed ? (byte) 1 : (byte) 0);
		dest.writeString(this.materialCode);
		dest.writeParcelable(this.materialValue, flags);
		dest.writeString(this.name);
		dest.writeByte(this.isLocalImg ? (byte) 1 : (byte) 0);
		dest.writeString(this.localurl);
	}

	public InvestorProofEntity() {
	}

	protected InvestorProofEntity(Parcel in) {
		this.isNeed = in.readByte() != 0;
		this.materialCode = in.readString();
		this.materialValue = in.readParcelable(OssRemoteFile.class.getClassLoader());
		this.name = in.readString();
		this.isLocalImg = in.readByte() != 0;
		this.localurl = in.readString();
	}

	public static final Creator<InvestorProofEntity> CREATOR = new Creator<InvestorProofEntity>() {
		@Override
		public InvestorProofEntity createFromParcel(Parcel source) {
			return new InvestorProofEntity(source);
		}

		@Override
		public InvestorProofEntity[] newArray(int size) {
			return new InvestorProofEntity[size];
		}
	};

	public InvestorProofEntity(String materialCode, OssRemoteFile materialValue) {
		this.materialCode = materialCode;
		this.materialValue = materialValue;
	}
}
