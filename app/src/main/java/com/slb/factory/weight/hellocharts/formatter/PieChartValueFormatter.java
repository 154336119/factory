package com.slb.factory.weight.hellocharts.formatter;

import com.slb.factory.weight.hellocharts.model.SliceValue;

public interface PieChartValueFormatter {

    public int formatChartValue(char[] formattedValue, SliceValue value);
}
