package com.slb.factory.http.bean.old;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * Created by Gifford on 2017/12/1.
 */

public class OssRemoteFile implements Parcelable {

	/**
	 * bucketName : xxx
	 * objectKey : xxxx
	 * url : xxxxxx
	 */

	private String bucketName;
	private String objectKey;
	@JSONField(serialize=false)
	private String url;
	@JSONField(serialize=false)
	private Integer pdfPage;
	public Integer getPdfPage() {
		return pdfPage;
	}

	public void setPdfPage(Integer pdfPage) {
		this.pdfPage = pdfPage;
	}



	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getObjectKey() {
		return objectKey;
	}

	public void setObjectKey(String objectKey) {
		this.objectKey = objectKey;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public OssRemoteFile(String bucketName, String objectKey, String url, Integer pdfPage) {
		this.bucketName = bucketName;
		this.objectKey = objectKey;
		this.url = url;
		this.pdfPage = pdfPage;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.bucketName);
		dest.writeString(this.objectKey);
		dest.writeString(this.url);
		dest.writeValue(this.pdfPage);
	}

	public OssRemoteFile() {
	}

	protected OssRemoteFile(Parcel in) {
		this.bucketName = in.readString();
		this.objectKey = in.readString();
		this.url = in.readString();
		this.pdfPage = (Integer) in.readValue(Integer.class.getClassLoader());
	}

	public static final Parcelable.Creator<OssRemoteFile> CREATOR = new Parcelable.Creator<OssRemoteFile>() {
		@Override
		public OssRemoteFile createFromParcel(Parcel source) {
			return new OssRemoteFile(source);
		}

		@Override
		public OssRemoteFile[] newArray(int size) {
			return new OssRemoteFile[size];
		}
	};
}
