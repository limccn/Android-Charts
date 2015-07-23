//
// DecimalDegree.java
// cn.limc.androidcharts.degree
//
// Created by limc on 2015-7-22.
//
// Copyright 2015 Shanghai Okasan-Huada Computer System Co. Ltd., All rights reserved.
//
package cn.limc.androidcharts.degree;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import cn.limc.androidcharts.view.DataGridChart;

/**
 * DecimalDegree
 * Description: <br>
 *   <p>add description here </p>
 * Tags: <br>
 *   <p> </p>
 *
 * @author limc
 * @version v1.0 
 * 
 * History: <br>
 * 2015-7-22 limc create v1.0 <br>
 *
 */
public class DecimalDegree extends Degree {
    
    public static final String DEFAULT_SOURCE_FORMAT = "#,##0";
    public static final String DEFAULT_TARGET_FORMAT = "#,##0";

    /**
     * @param inChart
     */
    public DecimalDegree(DataGridChart inChart) {
        super(inChart);
        sourceFormat = DEFAULT_SOURCE_FORMAT;
        targetFormat = DEFAULT_TARGET_FORMAT;
    }

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.degree.IDegree#getDegrees()
     */
    @Override
    public List<String> getDegrees() {
        List<String> titleY = new ArrayList<String>();
        double average = inChart.getDataRange().getValueRange() / inChart.getSimpleGrid().getLatitudeNum();
        // calculate degrees on Y axis
        for (int i = 0; i < inChart.getSimpleGrid().getLatitudeNum(); i++) {
            String value = valueForDegree(inChart.getDataRange().getMinValue() + i * average);

            titleY.add(value);
        }
        // calculate last degrees by use max value
        String value = valueForDegree(inChart.getDataRange().getMaxValue());
        titleY.add(value);
        return titleY;
    }

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.degree.IDegree#valueForDegree(java.lang.Object)
     */
    @Override
    public String valueForDegree(Object value) {
        DecimalFormat formator = new DecimalFormat(targetFormat);
        if (value instanceof Integer) {
            return formator.format(Math.floor((Integer)value)/ inChart.getDataRange().getDataMultiple());
        }else if (value instanceof Float) {
            return formator.format(Math.floor((Float)value)/ inChart.getDataRange().getDataMultiple());
        }else if (value instanceof Double) {
            return formator.format(Math.floor((Double)value)/ inChart.getDataRange().getDataMultiple());
        }else{
            return "";
        }
    }
}
