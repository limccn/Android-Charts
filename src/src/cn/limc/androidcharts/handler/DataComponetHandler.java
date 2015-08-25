//
// DataComponetHandler.java
// cn.limc.androidcharts.controller
//
// Created by limc on 2015-8-11.
//
// Copyright 2015 Shanghai Okasan-Huada Computer System Co. Ltd., All rights reserved.
//
package cn.limc.androidcharts.handler;

import cn.limc.androidcharts.component.Component;
import cn.limc.androidcharts.component.DataComponent;
import cn.limc.androidcharts.component.DataComponent.DataComponentDataSource;
import cn.limc.androidcharts.component.DataComponent.DataComponentDelegate;
import cn.limc.androidcharts.component.SimpleGrid;
import cn.limc.androidcharts.model.DataCursor;
import cn.limc.androidcharts.model.DataRange;
import cn.limc.androidcharts.series.ChartDataSet;

/**
 * DataComponetHandler
 * Description: <br>
 *   <p>add description here </p>
 * Tags: <br>
 *   <p> </p>
 *
 * @author limc
 * @version v1.0 
 * 
 * History: <br>
 * 2015-8-11 limc create v1.0 <br>
 *
 */
public abstract class DataComponetHandler extends AbstractComponetHandler implements DataComponentDelegate, DataComponentDataSource{

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.component.DataComponent.DataComponentDataSource#dataForComponent(cn.limc.androidcharts.component.DataComponent)
     */
    @Override
    public ChartDataSet dataForComponent(DataComponent component) {
        // TODO Auto-generated method stub
        return null;
    }


    protected DataCursor dataCursor;
    protected DataRange dataRange;
    protected SimpleGrid dataGrid;
    
    public DataComponetHandler(){
        super();
    }
    
    public DataComponetHandler(DataComponent component) {
        super(component);
        dataRange.calcValueRange(component.getChartData());
    }

    @Override
    public void didLoad() {
        super.didLoad();
    }
    
    /* (non-Javadoc)
     * @see cn.limc.androidcharts.controller.AbstractComponetController#setComponent(cn.limc.androidcharts.component.Component)
     */
    @Override
    public void setComponent(Component component) {
        // TODO Auto-generated method stub
        super.setComponent(component);
        dataRange.calcValueRange(((DataComponent)component).getChartData());
    }

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.component.DataComponent.DataComponentDelegate#dataCursorForData(cn.limc.androidcharts.component.DataComponent, cn.limc.androidcharts.series.ChartDataSet)
     */
    @Override
    public DataCursor dataCursorForData(DataComponent component, ChartDataSet dataSet) {
        return dataCursor;
    }

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.component.DataComponent.DataComponentDelegate#dataRangeForComponent(cn.limc.androidcharts.component.DataComponent)
     */
    @Override
    public DataRange dataRangeForComponent(DataComponent component) {
         return dataRange;
    }

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.component.DataComponent.DataComponentDelegate#dataGridForComponent(cn.limc.androidcharts.component.DataComponent)
     */
    @Override
    public SimpleGrid dataGridForComponent(DataComponent component) {
         return dataGrid;
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
        this.dataCursor = dataCursor;
    }

    /**
     * @return the dataRange
     */
    public DataRange getDataRange() {
        return dataRange;
    }

    /**
     * @param dataRange the dataRange to set
     */
    public void setDataRange(DataRange dataRange) {
        this.dataRange = dataRange;
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
}
