/*
 * DataComponetHandler.java
 * Android-Charts
 *
 * Created by limc on 2011/05/29.
 *
 * Copyright 2011 limc.cn All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.limc.androidcharts.handler;

import cn.limc.androidcharts.component.Component;
import cn.limc.androidcharts.component.DataComponent;
import cn.limc.androidcharts.component.DataComponent.DataComponentDataSource;
import cn.limc.androidcharts.component.DataComponent.DataComponentDelegate;
import cn.limc.androidcharts.component.SimpleGrid;
import cn.limc.androidcharts.model.DataCursor;
import cn.limc.androidcharts.model.DataCursorChangedListener;
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

    protected DataCursor dataCursor;
    protected DataRange dataRange;
    protected SimpleGrid dataGrid;
    
    public DataComponetHandler(){
        super();
    }
    
    public DataComponetHandler(DataComponent component) {
        super(component);
        if (dataRange != null) {
            dataRange.calcValueRange(component.getChartData());
        }
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
        if (dataRange != null) {
            dataRange.calcValueRange(((DataComponent)component).getChartData());
        }
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
    
    /* (non-Javadoc)
     * @see cn.limc.androidcharts.component.DataComponent.DataComponentDataSource#dataForComponent(cn.limc.androidcharts.component.DataComponent)
     */
    @Override
    public ChartDataSet dataForComponent(DataComponent component) {
        // TODO Auto-generated method stub
        return null;
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
        if (this.dataCursor != null && this.dataRange != null && dataCursor.getDataCursorChangedListener() == null) {
            dataCursor.setDataCursorChangedListener(new DataCursorChangedListener() {
                @Override
                public void onCursorChanged(DataCursor dataCursor, int displayFrom, int displayNumber) {
                    dataRange.calcValueRange(((DataComponent)getComponent()).getChartData());
                }
            });
        }
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
