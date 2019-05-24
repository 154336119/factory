package com.slb.factory.ui.adapter.old;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.slb.factory.R;
import com.slb.factory.http.bean.ObdEntity;

import java.util.List;


public class MineObdAdapter extends BaseQuickAdapter<ObdEntity,BaseViewHolder> {


    public MineObdAdapter(List<ObdEntity> data) {
        super(R.layout.adapter_mine_car_list, data);
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, final ObdEntity entity) {
//        baseViewHolder.setText(R.id.TvName, Base.getContext().getString(R.string.Product_serial_number)+" : "+entity.getSerialsNumber());
//        baseViewHolder.setText(R.id.TvName1,Base.getContext().getString(R.string.Product_Model)+" : "+entity.getProductModel());
    }

}
