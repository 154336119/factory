package com.slb.factory.ui.contract;

import com.slb.frame.http2.retrofit.HttpResult;
import com.slb.frame.ui.presenter.IBaseFragmentPresenter;
import com.slb.frame.ui.presenter.IBasePresenter;
import com.slb.frame.ui.view.IBaseLoadingDialogView;

import java.io.File;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Gifford on 2017/11/29.
 */

public class UploadLicenseContract {
	public interface IView extends IBaseLoadingDialogView {
		void uploadImageSuccess();
		void getPicTokenSuccess(String token);
		 void uploadQiNiuSuccess(String img);
	}
	public interface IPresenter<T> extends IBasePresenter<T> {
		void uploadQiNiu(File data ,String token);
		void uploadLicense(String token,String businessLicense);
		void getPicToken(String token);
	}
}
