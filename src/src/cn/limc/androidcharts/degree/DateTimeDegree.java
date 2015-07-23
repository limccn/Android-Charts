//
// DateTimeDegree.java
// cn.limc.androidcharts.degree
//
// Created by limc on 2015-7-22.
//
// Copyright 2015 Shanghai Okasan-Huada Computer System Co. Ltd., All rights reserved.
//
package cn.limc.androidcharts.degree;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cn.limc.androidcharts.view.DataGridChart;

/**
 * DateTimeDegree
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
public class DateTimeDegree extends Degree {

    public static final String DEFAULT_SOURCE_FORMAT = "yyyyMMdd";
    public static final String DEFAULT_TARGET_FORMAT = "yyyy/MM/dd";
    /**
     * @param inChart
     */
    public DateTimeDegree(DataGridChart inChart) {
        super(inChart);
        
        sourceFormat = DEFAULT_SOURCE_FORMAT;
        targetFormat = DEFAULT_TARGET_FORMAT;
    }

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.degree.IDegree#getDegrees()
     */
    @Override
    public List<String> getDegrees() {
        List<String> titleX = new ArrayList<String>();
        if (null != inChart.getStickData() && inChart.getStickData().size() > 0) {
            float average = inChart.getDataCursor().getDisplayNumber() / inChart.getSimpleGrid().getLongitudeNum();
            for (int i = 0; i < inChart.getSimpleGrid().getLongitudeNum(); i++) {
                int index = (int) Math.floor(i * average);
                if (index > inChart.getDataCursor().getDisplayNumber() - 1) {
                    index = inChart.getDataCursor().getDisplayNumber() - 1;
                }
                titleX.add(this.valueForDegree(inChart.getStickData().get(index).getDate()));
            }
            titleX.add(this.valueForDegree(inChart.getStickData().get(inChart.getDataCursor().getDisplayNumber() - 1).getDate()));
        }
        return titleX;
    }

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.degree.IDegree#valueForDegree(java.lang.Object)
     */
    @Override
    public String valueForDegree(Object value) {
        //TOOD location
        final SimpleDateFormat sourceFormator = new SimpleDateFormat(sourceFormat,Locale.ENGLISH);
        final SimpleDateFormat targetFormator = new SimpleDateFormat(targetFormat,Locale.ENGLISH);      
        try {
            Date dt = sourceFormator.parse(String.valueOf(value));
            return targetFormator.format(dt);
        } catch (ParseException e) {
            return "";
        }
    }
}
