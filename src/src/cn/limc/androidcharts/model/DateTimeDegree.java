//
// DateTimeDegree.java
// cn.limc.androidcharts.degree
//
// Created by limc on 2015-7-22.
//
// Copyright 2015 Shanghai Okasan-Huada Computer System Co. Ltd., All rights reserved.
//
package cn.limc.androidcharts.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cn.limc.androidcharts.component.Grid;
import cn.limc.androidcharts.component.SimpleGrid;
import cn.limc.androidcharts.diagram.GridChart;
import cn.limc.androidcharts.series.ChartDataSet;
import cn.limc.androidcharts.series.ChartDataTable;
import cn.limc.androidcharts.series.IHasDate;

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
public class DateTimeDegree extends AbstractDegree {

    public static final String DEFAULT_SOURCE_FORMAT = "yyyyMMdd";
    public static final String DEFAULT_TARGET_FORMAT = "yyyy/MM/dd";
    /**
     * @param parent
     */
    public DateTimeDegree() {
        sourceFormat = DEFAULT_SOURCE_FORMAT;
        targetFormat = DEFAULT_TARGET_FORMAT;
    }

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.degree.IDegree#getDegrees()
     */
    @Override
    public List<String> getDegrees() {
        List<String> titleX = new ArrayList<String>();
        ChartDataSet chartData = axis.getBindComponent().getChartData();
        DataCursor dataCursor = axis.getBindComponent().getDataCursor();
        SimpleGrid grid = axis.getBindComponent().getDataGrid();
        if (chartData != null) {
            ChartDataTable table = chartData.getChartTable();
            if (null != table && table.size() > 0) {
                //TODO How to get grid
                float average = dataCursor.getDisplayNumber() / grid.getLongitudeNum();
                for (int i = 0; i < grid.getLongitudeNum(); i++) {
                    int index = (int) Math.floor(i * average);
                    if (index > dataCursor.getDisplayNumber() - 1) {
                        index = dataCursor.getDisplayNumber() - 1;
                    }

                    IHasDate rowDate = (IHasDate) table.get(index);
                    titleX.add(this.valueForDegree(rowDate.getDate()));
                }
                IHasDate rowDate = (IHasDate) table
                        .get(dataCursor.getDisplayNumber() - 1);
                titleX.add(this.valueForDegree(rowDate.getDate()));
            }
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
