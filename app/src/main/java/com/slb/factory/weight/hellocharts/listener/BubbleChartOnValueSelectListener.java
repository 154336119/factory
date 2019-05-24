package com.slb.factory.weight.hellocharts.listener;


import com.slb.factory.weight.hellocharts.model.BubbleValue;

public interface BubbleChartOnValueSelectListener extends OnValueDeselectListener {

    public void onValueSelected(int bubbleIndex, BubbleValue value);

}
