package com.slb.factory.weight.hellocharts.listener;


import com.slb.factory.weight.hellocharts.model.PointValue;

public interface LineChartOnValueSelectListener extends OnValueDeselectListener {

    public void onValueSelected(int lineIndex, int pointIndex, PointValue value);

}
