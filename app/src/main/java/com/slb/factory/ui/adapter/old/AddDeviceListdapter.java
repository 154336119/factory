package com.slb.factory.ui.adapter.old;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.slb.factory.R;
import com.slb.factory.http.bean.ObdEntity;

import java.util.List;


public class AddDeviceListdapter extends BaseItemDraggableAdapter<ObdEntity,BaseViewHolder> {


    public AddDeviceListdapter(List<ObdEntity> data) {
        super(R.layout.adapter_add_device_list, data);
    }

    @Override
    protected void convert(final BaseViewHolder baseViewHolder, final ObdEntity entity) {
        baseViewHolder.setText(R.id.TvName,entity.getBluetoothName());
        baseViewHolder.setText(R.id.TvDes,entity.getObdModel());
        if(entity.getContect()){
            baseViewHolder.setText(R.id.TvState,"Disconnect");
        }else{
            baseViewHolder.setText(R.id.TvState,"Connect");
        }
    }

}
