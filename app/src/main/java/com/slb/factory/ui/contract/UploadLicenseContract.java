package com.slb.factory.ui.contract;

import com.slb.frame.ui.presenter.IBaseFragmentPresenter;
import com.slb.frame.ui.presenter.IBasePresenter;
import com.slb.frame.ui.view.IBaseLoadingDialogView;

import java.io.File;

/**
 * Created by Gifford on 2017/11/29.
 */

public class UploadLicenseContract {
	public interface IView extends IBaseLoadingDialogView {
		void uploadImageSuccess();
	}
	public interface IPresenter<T> extends IBasePresenter<T> {
		void uploadImageFile(File file);
	}
}
