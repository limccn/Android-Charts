//
// DecimalDegree.java
// cn.limc.androidcharts.degree
//
// Created by limc on 2015-7-22.
//
// Copyright 2015 Shanghai Okasan-Huada Computer System Co. Ltd., All rights reserved.
//
package cn.limc.androidcharts.model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import cn.limc.androidcharts.component.Axis;
import cn.limc.androidcharts.component.SimpleGrid;
import cn.limc.androidcharts.diagram.GridChart;

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
public class DecimalDegree extends AbstractDegree {
    
    public static final String DEFAULT_SOURCE_FORMAT = "#,##0";
    public static final String DEFAULT_TARGET_FORMAT = "#,##0";

    /**
     * @param parent
     */
    public DecimalDegree() {
        sourceFormat = DEFAULT_SOURCE_FORMAT;
        targetFormat = DEFAULT_TARGET_FORMAT;
    }

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.degree.IDegree#getDegrees()
     */
    @Override
    public List<String> getDegrees() {
        List<String> titleY = new ArrayList<String>();
        DataRange dataRange = axis.getBindComponent().getDataRange();
        if (dataRange.isAutoCalcValueRange()) {
            dataRange.calcValueRange();
        }
        SimpleGrid grid = axis.getBindComponent().getDataGrid();
        double average = dataRange.getValueRange() / grid.getLatitudeNum();
        // calculate degrees on Y axis
        for (int i = 0; i < grid.getLatitudeNum(); i++) {
            String value = valueForDegree(dataRange.getMinValue() + i * average);

            titleY.add(value);
        }
        // calculate last degrees by use max value
        String value = valueForDegree(dataRange.getMaxValue());
        titleY.add(value);
        return titleY;
    }

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.degree.IDegree#valueForDegree(java.lang.Object)
     */
    @Override
    public String valueForDegree(Object value) {
        DecimalFormat formator = new DecimalFormat(targetFormat);
        DataRange dataRange = axis.getBindComponent().getDataRange();
        if (value instanceof Integer) {
            return formator.format(Math.floor((Integer)value)/ dataRange.getDataMultiple());
        }else if (value instanceof Float) {
            return formator.format(Math.floor((Float)value)/ dataRange.getDataMultiple());
        }else if (value instanceof Double) {
            return formator.format(Math.floor((Double)value)/ dataRange.getDataMultiple());
        }else{
            return "";
        }
    }
}
