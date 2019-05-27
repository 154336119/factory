package com.slb.factory.ui.contract;

import com.slb.frame.ui.presenter.IBasePresenter;
import com.slb.frame.ui.view.IBaseLoadingDialogView;

/**
 * Created by Gifford on 2018/6/6.
 */

public class UploadlProofsContract {
	public interface IView extends IBaseLoadingDialogView {
//		void uploadImageSuccess(OssRemoteFile file, int requestCode);
//		void sumitDrafgSuccess(InvestorIncreaseEntity entity);
	}
	public interface IPresenter<T> extends IBasePresenter<T> {
//		void uploadImageFile(String imgPath, int requestCode);
//		void submitDraft(InvestorIncreaseEntity entity);
	}
}
