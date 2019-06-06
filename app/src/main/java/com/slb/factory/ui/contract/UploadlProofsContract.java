package com.slb.factory.ui.contract;

import com.slb.frame.ui.presenter.IBasePresenter;
import com.slb.frame.ui.view.IBaseLoadingDialogView;

import java.io.File;

/**
 * Created by Gifford on 2018/6/6.
 */

public class UploadlProofsContract {
	public interface IView extends IBaseLoadingDialogView {
		void uploadImageSuccess();
		void getPicTokenSuccess(String token);
		void uploadQiNiuSuccess(String img);
	}
	public interface IPresenter<T> extends IBasePresenter<T> {
		void uploadQiNiu(File data , String token);
		void uploadlProofs(String orderId,String businessLicense);
		void getPicToken(String token);
	}
}
