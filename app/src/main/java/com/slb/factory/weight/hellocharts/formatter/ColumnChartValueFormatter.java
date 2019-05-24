package com.slb.factory.weight.hellocharts.formatter;

import com.slb.factory.weight.hellocharts.model.SubcolumnValue;

public interface ColumnChartValueFormatter {

    public int formatChartValue(char[] formattedValue, SubcolumnValue value);

}
