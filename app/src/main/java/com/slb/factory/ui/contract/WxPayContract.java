package com.slb.factory.ui.contract;

import com.slb.frame.ui.presenter.IBaseFragmentPresenter;
import com.slb.frame.ui.presenter.IBasePresenter;
import com.slb.frame.ui.view.IBaseLoadingDialogView;

import java.io.File;

/**
 * Created by Gifford on 2017/11/29.
 */

public class WxPayContract {
	public interface IView extends IBaseLoadingDialogView {
	}
	public interface IPresenter<T> extends IBaseFragmentPresenter<T> {
	}
}
