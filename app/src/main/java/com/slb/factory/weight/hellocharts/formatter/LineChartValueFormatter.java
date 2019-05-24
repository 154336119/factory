package com.slb.factory.weight.hellocharts.formatter;


import com.slb.factory.weight.hellocharts.model.PointValue;

public interface LineChartValueFormatter {

    public int formatChartValue(char[] formattedValue, PointValue value);
}
