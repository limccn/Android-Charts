/*
 * DataComponent.java
 * Android-Charts
 *
 * Created by limc on 2014.
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
