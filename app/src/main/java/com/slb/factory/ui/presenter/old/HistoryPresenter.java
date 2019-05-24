package com.slb.factory.ui.presenter.old;

import android.text.TextUtils;

import com.alibaba.fastjson.JSONObject;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.slb.frame.ui.presenter.AbstractBaseFragmentPresenter;
import com.slb.factory.Base;
import com.slb.factory.R;
import com.slb.factory.http.bean.HistoryErrorCodeEntity;
import com.slb.factory.http.bean.ObdEntity;
import com.slb.factory.http.bean.VehicleEntity;
import com.slb.factory.http.callback.DialogCallback;
import com.slb.factory.http.dns.DnsFactory;
import com.slb.factory.http.model.LzyResponse;
import com.slb.factory.ui.contract.old.HistoryContract;

import java.util.List;

/**
 * Created by Administrator on 2018/4/10.
 */

public class HistoryPresenter extends AbstractBaseFragmentPresenter<HistoryContract.IView>
        implements HistoryContract.IPresenter<HistoryContract.IView>{
    @Override
    public void getHistoryErrorCode(ObdEntity obd , VehicleEntity vehicle, String startDate, String endDate) {
        if(obd == null){
            mView.showMsg(Base.getContext().getString(R.string.Please_choise_the_obd));
            return;
        }
        if(vehicle == null){
            mView.showMsg(Base.getContext().getString(R.string.Please_choise_the_vehicle));
            return;
        }
        if(TextUtils.isEmpty(startDate)){
            mView.showMsg(Base.getContext().getString(R.string.Please_choise_start_date));
            return;
        }
        if(TextUtils.isEmpty(endDate)){
            mView.showMsg(Base.getContext().getString(R.string.Please_choise_end_date));
            return;
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("userId", Base.getUserEntity().getId());
        jsonObject.put("obdId", obd.getId());
        jsonObject.put("vehicleId", vehicle.getId());
        jsonObject.put("startDate", startDate);
        jsonObject.put("endDate", endDate);

        OkGo.<LzyResponse<List<HistoryErrorCodeEntity>>>post(DnsFactory.getInstance().getDns().getCommonBaseUrl()+"api/command-log/history")
                .tag(this)
//                .upJson(jsonObject.toString())
                .params("userId",Base.getUserEntity().getId())
                .params("obdId",obd.getId())
                .params("vehicleId",vehicle.getId())
                .params("startDate",startDate)
                .params("endDate",endDate)
                .isMultipart(true)
                .headers("Authorization","Bearer "+Base.getUserEntity().getToken())
                .execute(new DialogCallback<LzyResponse<List<HistoryErrorCodeEntity>>>(this.mView) {
                    @Override
                    public void onSuccess(Response<LzyResponse<List<HistoryErrorCodeEntity>>> response) {
//                        RxBus.get().post(new HistoryErrorEvent(response.body().data));
                        mView.getHistoryErrorCodeSuccess(response.body().data);
                    }
                });
    }
}
