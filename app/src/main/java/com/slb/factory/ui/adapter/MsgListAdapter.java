package com.slb.factory.ui.adapter;

import android.text.TextUtils;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.slb.factory.R;
import com.slb.factory.http.bean.MsgEntity;
import java.util.List;


/**
 * 刁剑
 * Created on 2017/1/3.
 * 注释:
 */

public class MsgListAdapter extends BaseQuickAdapter<MsgEntity, BaseViewHolder> {

    public MsgListAdapter(List<MsgEntity> data) {
        super(R.layout.adapter_msg_list, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, final MsgEntity msgEntity) {
        baseViewHolder.setText(R.id.TvTitle, msgEntity.getTitle())
                .setText(R.id.TvContent, msgEntity.getContent())
                .setText(R.id.TvDate,msgEntity.getCreate_time());
    }
}
