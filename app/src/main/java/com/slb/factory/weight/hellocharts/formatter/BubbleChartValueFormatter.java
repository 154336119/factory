package com.slb.factory.weight.hellocharts.formatter;

import com.slb.factory.weight.hellocharts.model.BubbleValue;

public interface BubbleChartValueFormatter {

    public int formatChartValue(char[] formattedValue, BubbleValue value);
}
