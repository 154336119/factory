package com.slb.factory.ui.activity.old;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.slb.frame.ui.activity.BaseMvpActivity;
import com.slb.factory.R;
import com.slb.factory.ui.contract.old.ModPasswordContract;
import com.slb.factory.ui.presenter.old.ModPwPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ModPasswordActivity extends BaseMvpActivity<ModPasswordContract.IView, ModPasswordContract.IPresenter>
        implements ModPasswordContract.IView {
    @BindView(R.id.edCurPw)
    EditText edCurPw;
    @BindView(R.id.edtNewPw)
    EditText edtNewPw;
    @BindView(R.id.edtAgainPw)
    EditText edtAgainPw;
    @BindView(R.id.btnComfirm)
    Button btnComfirm;
    @Override
    protected String setToolbarTitle() {
        return getString(R.string.Change_your_password);
    }
    @Override
    public void resetSuccess() {
        finish();
    }

    @Override
    public ModPasswordContract.IPresenter initPresenter() {
        return new ModPwPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_mod_pw;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnComfirm)
    public void onViewClicked() {
        mPresenter.reset(edCurPw.getText().toString(),edtNewPw.getText().toString(),edtAgainPw.getText().toString());
    }
}
