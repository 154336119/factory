package com.slb.factory.http.bean.old;

import com.slb.factory.ui.activity.SuccessActivity;

/**
 * Created by Gifford on 2017/11/21.
 *
 * 系统投资者分类
 */

public enum SuccessTypeEnum {
	TYPE_100("提交成功","恭喜您，信息已提交成功！","我们会在1-3个工作日对您的信息进行审核","去首页", SuccessActivity.TYPE_100),
	TYPE_101("提交成功","恭喜您，提交成功！","感谢您对本产品的支持","返回个人中心", SuccessActivity.TYPE_101),
	TYPE_102("上传成功","恭喜您，上传成功！","转账凭证已上传，请耐心等待卖家发货","查看订单", SuccessActivity.TYPE_102),
	TYPE_103("支付成功","恭喜您，支付成功！","","返回首页", SuccessActivity.TYPE_103);
	/**
	 * TYPE_100 - 成功页面——使用申请
	 * TYPE_101 - 成功页面——意见反馈
	 * TYPE_102 - 成功页面——上传凭证
	 */
	private String title;
	private String titleContent;
	private String content;
	private String btnText;
	private int type;
	SuccessTypeEnum(String title, String titleContent, String content, String btnText, int type) {
		this.title = title;
		this.titleContent = titleContent;
		this.content = content;
		this.btnText = btnText;
		this.type = type;
	}

	public static SuccessTypeEnum getEnumForType(int type){
		SuccessTypeEnum data=null;
		for (SuccessTypeEnum successTypeEnum : SuccessTypeEnum.values()){
			if(type==(successTypeEnum.getType())){
				data= successTypeEnum;
				break;
			}
		}return data;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitleContent() {
		return titleContent;
	}

	public void setTitleContent(String titleContent) {
		this.titleContent = titleContent;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getBtnText() {
		return btnText;
	}

	public void setBtnText(String btnText) {
		this.btnText = btnText;
	}



	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}



}
