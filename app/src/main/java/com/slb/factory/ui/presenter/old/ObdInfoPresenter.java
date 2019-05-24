package com.slb.factory.ui.presenter.old;


import android.text.TextUtils;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.slb.frame.ui.presenter.AbstractBasePresenter;
import com.slb.factory.Base;
import com.slb.factory.http.bean.UserEntity;
import com.slb.factory.http.callback.DialogCallback;
import com.slb.factory.http.dns.DnsFactory;
import com.slb.factory.http.model.LzyResponse;
import com.slb.factory.http.service.ComServiceUrl;
import com.slb.factory.ui.contract.old.ObdInfoContract;

/**
 * 李彬杰
 * 注释: Obd相关信息
 */

public class ObdInfoPresenter extends AbstractBasePresenter<ObdInfoContract.IView>
        implements ObdInfoContract.IPresenter<ObdInfoContract.IView> {
    @Override
    public void addObd(String serialsNumber, String productModel) {
        if(TextUtils.isEmpty(serialsNumber)){
            mView.showMsg("请输入产品序列号");
            return;
        }
        if(TextUtils.isEmpty(productModel)){
            mView.showMsg("请输入型号");
            return;
        }
        String id = Base.getUserEntity().getId();
        OkGo.<LzyResponse<UserEntity>>post(DnsFactory.getInstance().getDns().getCommonBaseUrl()+ ComServiceUrl.addObd)//
                .tag(this)//
                .params("serialsNumber",serialsNumber)
                .params("productModel",productModel)
                .params("userId",id)
                .isMultipart(true)         //强制使用 multipart/form-data 表单上传（只是演示，不需要的话不要设置。默认就是false）
                .headers("Authorization","Bearer "+Base.getUserEntity().getToken())
                .execute(new DialogCallback<LzyResponse<UserEntity>>(this.mView) {
                    @Override
                    public void onSuccess(Response<LzyResponse<UserEntity>> response) {
                        mView.addObdSuccess();
                    }
                });
    }

    @Override
    public void editObd(String obdId, String serialsNumber, String productModel) {
        if(TextUtils.isEmpty(serialsNumber)){
            mView.showMsg("请输入产品序列号");
            return;
        }
        if(TextUtils.isEmpty(productModel)){
            mView.showMsg("请输入型号");
            return;
        }
        String id = Base.getUserEntity().getId();
        OkGo.<LzyResponse<UserEntity>>post(DnsFactory.getInstance().getDns().getCommonBaseUrl()+ ComServiceUrl.editObd)//
                .tag(this)//
                .params("serialsNumber",serialsNumber)
                .params("productModel",productModel)
                .params("obdId ",obdId)
                .params("userId",id)
                .isMultipart(true)         //强制使用 multipart/form-data 表单上传（只是演示，不需要的话不要设置。默认就是false）
                .headers("Authorization","Bearer "+Base.getUserEntity().getToken())
                .execute(new DialogCallback<LzyResponse<UserEntity>>(this.mView) {
                    @Override
                    public void onSuccess(Response<LzyResponse<UserEntity>> response) {
                        mView.edidObdCarSuccess();
                    }
                });
    }

    @Override
    public void delectObd(String obdId) {
        String id = Base.getUserEntity().getId();
        OkGo.<LzyResponse<UserEntity>>post(DnsFactory.getInstance().getDns().getCommonBaseUrl()+ ComServiceUrl.delectObd)//
                .tag(this)//
                .params("obdId ",obdId)
                .params("userId",id)
                .isMultipart(true)         //强制使用 multipart/form-data 表单上传（只是演示，不需要的话不要设置。默认就是false）
                .headers("Authorization","Bearer "+Base.getUserEntity().getToken())
                .execute(new DialogCallback<LzyResponse<UserEntity>>(this.mView) {
                    @Override
                    public void onSuccess(Response<LzyResponse<UserEntity>> response) {
                        mView.delectObdSuccess();
                    }
                });
    }
}
