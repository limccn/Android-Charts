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
import cn.limc.androidcharts.model.DataRange;
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

//    protected ChartDataSet chartData;
    
    /**
     * @return the chartDataSet
     */
    public ChartDataSet getChartData() {
        DataComponentDataSource delegate = ((DataComponentDataSource)componentController);
        return delegate.dataForComponent(this);
    }

    /**
     * @return the dataCursor
     */
    public DataCursor getDataCursor() {
        DataComponentDelegate delegate = ((DataComponentDelegate)componentController);
        return delegate.dataCursorForData(this,getChartData());
    }

    /**
     * @return the valueRange
     */
    public DataRange getDataRange() {
        DataComponentDelegate delegate = ((DataComponentDelegate)componentController);
        return delegate.dataRangeForComponent(this);
    }

    /**
     * @return the dataGrid
     */
    public SimpleGrid getDataGrid() {
        DataComponentDelegate delegate = ((DataComponentDelegate)componentController);
        return delegate.dataGridForComponent(this);
    }
    
    public double heightForRate(double rate){
        return getDataRange().valueForRate(rate) * getPaddingHeight() + getPaddingStartY();
    }
    
    public interface DataComponentDelegate{
        DataCursor dataCursorForData(DataComponent component , ChartDataSet dataSet);
        DataRange dataRangeForComponent(DataComponent component);
        SimpleGrid dataGridForComponent(DataComponent component);  
    }
    
    public interface DataComponentDataSource{
        ChartDataSet dataForComponent(DataComponent component);
    }
}
