package com.slb.factory.ui.adapter.old;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.slb.factory.R;
import com.slb.factory.http.bean.TroubleLightSEntity;

import java.util.List;

/**
 * 故障灯
 */
public class TroubleLightSAdapter extends BaseQuickAdapter<TroubleLightSEntity,BaseViewHolder> {


    public TroubleLightSAdapter(List<TroubleLightSEntity> data) {
        super(R.layout.adapter_trouble_lights, data);
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, final TroubleLightSEntity entity) {
        baseViewHolder.setText(R.id.TvTitle,entity.getValue());
        baseViewHolder.setText(R.id.TvContent,entity.getName());
    }

}
