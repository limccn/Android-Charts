//
// DataComponent.java
// cn.limc.androidcharts.component
//
// Created by limc on 2015-8-3.
//
// Copyright 2015 Shanghai Okasan-Huada Computer System Co. Ltd., All rights reserved.
//
package cn.limc.androidcharts.component;

import cn.limc.androidcharts.model.DataCursor;
import cn.limc.androidcharts.model.SectionDataCursor;
import cn.limc.androidcharts.model.DataRange;
import cn.limc.androidcharts.model.SimpleDataRange;
import cn.limc.androidcharts.series.ChartDataSet;

/**
 * DataComponent
 * Description: <br>
 *   <p>add description here </p>
 * Tags: <br>
 *   <p> </p>
 *
 * @author limc
 * @version v1.0 
 * 
 * History: <br>
 * 2015-8-3 limc create v1.0 <br>
 *
 */
public abstract class DataComponent extends AbstractComponent {

    protected ChartDataSet chartData;
    protected DataCursor dataCursor = new SectionDataCursor();
    protected DataRange dataRange = new SimpleDataRange();
    protected SimpleGrid dataGrid = new SimpleGrid();
    
    /**
     * @return the chartDataSet
     */
    public ChartDataSet getChartData() {
        return chartData;
    }
    /**
     * @param chartDataSet the chartDataSet to set
     */
    public void setChartData(ChartDataSet chartDataSet) {
        dataCursor.setData(chartData);
        this.chartData = chartDataSet;
    }
    /**
     * @return the dataCursor
     */
    public DataCursor getDataCursor() {
        return dataCursor;
    }
    /**
     * @param dataCursor the dataCursor to set
     */
    public void setDataCursor(DataCursor dataCursor) {
        dataCursor.setData(chartData);
        this.dataCursor = dataCursor;
    }
    /**
     * @return the valueRange
     */
    public DataRange getDataRange() {
        return dataRange;
    }
    /**
     * @param valueRange the valueRange to set
     */
    public void setDataRange(DataRange valueRange) {
        valueRange.setBindComponent(this);
        this.dataRange = valueRange;
    }
    /**
     * @return the dataGrid
     */
    public SimpleGrid getDataGrid() {
        return dataGrid;
    }
    /**
     * @param dataGrid the dataGrid to set
     */
    public void setDataGrid(SimpleGrid dataGrid) {
        this.dataGrid = dataGrid;
    }
    
    public double heightForRate(double rate){
        return dataRange.valueForRate(rate) * getPaddingHeight() + getPaddingStartY();
    }
}
