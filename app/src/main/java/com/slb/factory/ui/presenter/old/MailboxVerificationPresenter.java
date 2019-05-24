package com.slb.factory.ui.presenter.old;

import android.text.TextUtils;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.slb.frame.ui.presenter.AbstractBasePresenter;
import com.slb.factory.http.callback.DialogCallback;
import com.slb.factory.http.dns.DnsFactory;
import com.slb.factory.http.model.LzyResponse;
import com.slb.factory.http.service.ComServiceUrl;
import com.slb.factory.ui.contract.old.MailboxVerificationContract;


/**
 * Created by Gifford on 2017/11/7.
 */

public class MailboxVerificationPresenter extends AbstractBasePresenter<MailboxVerificationContract.IView>
		implements MailboxVerificationContract.IPresenter<MailboxVerificationContract.IView>{

	@Override
	public void getCode(String email) {
		if(TextUtils.isEmpty(email)){
			mView.showMsg("请输入邮箱");
			return;
		}
		OkGo.<LzyResponse<Object>>post(DnsFactory.getInstance().getDns().getCommonBaseUrl()+ ComServiceUrl.verifycodeReset)//
				.tag(this)//
				.params("email", email)//
				.isMultipart(true)         //强制使用 multipart/form-data 表单上传（只是演示，不需要的话不要设置。默认就是false）
				.execute(new DialogCallback<LzyResponse<Object>>(this.mView) {
					@Override
					public void onSuccess(Response<LzyResponse<Object>> response) {
						mView.showCountdown();
					}
				});

	}
	}
