package com.slb.factory.http.bean.old;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Gifford on 2017/11/23.
 */

public class InvestorProofEntity implements Parcelable {
	@JSONField(serialize=false)
	private String name;
	@JSONField(serialize=false)
	private boolean isLocalImg;
	@JSONField(serialize=false)
	private String url;

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.name);
		dest.writeByte(this.isLocalImg ? (byte) 1 : (byte) 0);
		dest.writeString(this.url);
	}

	public InvestorProofEntity() {
	}

	protected InvestorProofEntity(Parcel in) {
		this.name = in.readString();
		this.isLocalImg = in.readByte() != 0;
		this.url = in.readString();
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
