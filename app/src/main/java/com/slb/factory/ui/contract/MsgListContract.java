package com.slb.factory.ui.contract;

import com.slb.frame.ui.presenter.IBaseFragmentPresenter;
import com.slb.frame.ui.presenter.IBasePresenter;
import com.slb.frame.ui.view.IBaseLoadingView;

/**
 * 刁剑
 * Created on 2017/1/3.
 * 注释:
 */

public class MsgListContract {
    public interface IView extends IBaseLoadingView {
    }
    public interface IPresenter<T> extends IBaseFragmentPresenter<T> {
    }
}
