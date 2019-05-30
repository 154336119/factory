package com.slb.factory.ui.activity;

import android.widget.Button;
import android.widget.TextView;

import com.slb.factory.BizsConstant;
import com.slb.factory.R;
import com.slb.factory.http.bean.old.SuccessTypeEnum;
import com.slb.frame.ui.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SuccessActivity extends BaseActivity {
    public static final int TYPE_100 = 100; //使用申请
    public static final int TYPE_101 = 101; //意见反馈
    public static final int TYPE_102 = 102; //上传凭证

    @BindView(R.id.TvTitle)
    TextView TvTitle;
    @BindView(R.id.TvContent)
    TextView TvContent;
    @BindView(R.id.Btn)
    Button Btn;
    private int type;
    private SuccessTypeEnum mSuccessTypeEnum;

    @Override
    protected String setToolbarTitle() {
        return mSuccessTypeEnum.getTitle();
    }


    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        TvTitle.setText(mSuccessTypeEnum.getTitleContent());
        TvContent.setText(mSuccessTypeEnum.getContent());
        Btn.setText(mSuccessTypeEnum.getBtnText());
    }

    @Override
    public void getIntentExtras() {
        super.getIntentExtras();
        type = getIntent().getIntExtra(BizsConstant.TYPE,TYPE_100);
//        type = TYPE_100;
        mSuccessTypeEnum = SuccessTypeEnum.getEnumForType(type);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_success;
    }

    @OnClick(R.id.Btn)
    public void onViewClicked() {
        if(type == TYPE_100){

        }else if(type == TYPE_100){

        }else if(type == TYPE_101){

        }
    }
}
