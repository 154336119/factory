package com.slb.factory.weight.hellocharts.listener;


import com.slb.factory.weight.hellocharts.model.SliceValue;

public interface PieChartOnValueSelectListener extends OnValueDeselectListener {

    public void onValueSelected(int arcIndex, SliceValue value);

}
