package com.slb.factory.ui.activity.old;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.gson.internal.LinkedTreeMap;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.slb.frame.ui.activity.BaseActivity;
import com.slb.factory.Base;
import com.slb.factory.R;
import com.slb.factory.http.bean.ErrorCodeEntity;
import com.slb.factory.http.bean.HistoryErrorCodeInsideEntity;
import com.slb.factory.http.callback.ActivityDialogCallback;
import com.slb.factory.http.dns.DnsFactory;
import com.slb.factory.http.model.LzyResponse;
import com.slb.factory.ui.adapter.old.HistoryErrorDetailAdapter;
import com.slb.factory.util.config.BizcContant;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by juan on 2018/9/16.
 *
 */

public class HistoryDetailActivity extends BaseActivity {
    @BindView(R.id.TvCarNum)
    TextView mTvCarNum;
    @BindView(R.id.TvObdNum)
    TextView mTvObdNum;
    @BindView(R.id.mRecyclerView)
    RecyclerView mRecyclerView;
    private List<HistoryErrorCodeInsideEntity> mDtcs;
    private HistoryErrorDetailAdapter mAdapter01;
    private List<ErrorCodeEntity> mConfirmErrorCodeList = new ArrayList<>();
    @Override
    public void getIntentExtras() {
        super.getIntentExtras();
        mDtcs = getIntent().getParcelableArrayListExtra(BizcContant.PARA_HISTORY_DETAIL);
    }

    @Override
    protected String setToolbarTitle() {
        return "历史故障详情";
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_history_detail;
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        if(mDtcs!=null && mDtcs.size()>0){
            mTvCarNum.setText(mDtcs.get(0).getVehicleLicenseNo());
            mTvObdNum.setText(mDtcs.get(0).getObdSn());
            for(HistoryErrorCodeInsideEntity errorCodeInsideEntity : mDtcs){
                ErrorCodeEntity errorCodeEntity = new ErrorCodeEntity();
                errorCodeEntity.setTitle(errorCodeInsideEntity.getPid());
                mConfirmErrorCodeList.add(errorCodeEntity);
            }
            gethttpdataOkCodePidDetails();
        }

        mAdapter01 = new HistoryErrorDetailAdapter(mConfirmErrorCodeList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter01);
        mRecyclerView.addItemDecoration(
                new HorizontalDividerItemDecoration.Builder(this)
                        .color(Color.parseColor("#2B3139"))
                        .sizeResId(R.dimen.distance_20)
                        .build());


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
    }

    private void gethttpdataOkCodePidDetails(){
            StringBuilder confirmPidsSb = new StringBuilder();
            for(HistoryErrorCodeInsideEntity errorCodeInsideEntity : mDtcs){
                confirmPidsSb.append(errorCodeInsideEntity.getPid());
                confirmPidsSb.append(",");
            }
        String confirmPids = confirmPidsSb.substring(0,confirmPidsSb.length()-1);
//        Logger.d("================="+confirmPids);
            OkGo.<LzyResponse<Object>>post(DnsFactory.getInstance().getDns().getCommonBaseUrl()+"api/command/dtc")
                    .tag(this)
                    .params("userId", Base.getUserEntity().getId())
                    .params("pids",confirmPids)
                    .isMultipart(true)
                    .headers("Authorization","Bearer "+Base.getUserEntity().getToken())
                    .execute(new ActivityDialogCallback<LzyResponse<Object>>(this) {
                        @Override
                        public void onSuccess(Response<LzyResponse<Object>> response) {
                            LinkedTreeMap map =  (LinkedTreeMap)response.body().data;
                            for(int i=0;i<mAdapter01.getData().size();i++){
                                ErrorCodeEntity errorCodeEntity= mAdapter01.getData().get(i);
                                if( map.containsKey(errorCodeEntity.getTitle())){
                                    Object object = map.get(errorCodeEntity.getTitle());
                                    if(object!=null){
                                        LinkedTreeMap map1  = (LinkedTreeMap)map.get(errorCodeEntity.getTitle());
                                        String va =  (String)map1.get("description");
                                        errorCodeEntity.setDes(va);
                                        mAdapter01.setData(i,errorCodeEntity);
                                    }
                                }
                            }
                            hideWaitDialog();
                        }

                        @Override
                        public void onStart(Request<LzyResponse<Object>, ? extends Request> request) {
                            super.onStart(request);
                            showWaitDialog("loading...");
                        }
                    });
    }
}
