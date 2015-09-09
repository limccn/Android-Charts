/*
 * ComponentGroupHandler.java
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

import java.util.ArrayList;
import java.util.List;

import cn.limc.androidcharts.component.Component;
import cn.limc.androidcharts.component.DataComponent;
import cn.limc.androidcharts.component.SimpleGrid;
import cn.limc.androidcharts.model.DataCursor;
import cn.limc.androidcharts.model.DataRange;
import cn.limc.androidcharts.model.SimpleDataRange;
import cn.limc.androidcharts.series.ChartDataSet;

/**
 * ComponentGroupHandler
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
public abstract class ComponentGroupHandler extends DataComponetHandler{
    
    protected List<Component> componentList = new ArrayList<Component>();
    
    public ComponentGroupHandler(){
    }
    
    public void addComponent(DataComponent component){
        component.setComponentHandler(this);
        componentList.add(component);
        
        SimpleDataRange tempDataRange = new SimpleDataRange();
        tempDataRange.setRangeCalculator(dataRange.getRangeCalculator());
        tempDataRange.calcValueRange(((DataComponent)component).getChartData());
        if (tempDataRange.getMaxValue() > dataRange.getMaxValue()) {
            dataRange.setMaxValue(tempDataRange.getMaxValue());
        }
        if (tempDataRange.getMinValue() < dataRange.getMinValue()) {
            dataRange.setMinValue(tempDataRange.getMinValue());
        }
        
        //dataRange.optimizeValueRange();
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
     * @return the componentList
     */
    public List<Component> getComponentList() {
        return componentList;
    }

    /**
     * @param componentList the componentList to set
     */
    public void setComponentList(List<Component> componentList) {
        this.componentList = componentList;
    }

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.controller.AbstractComponetController#getComponent()
     */
    @Override
    public Component getComponent() {
        return componentList == null ? null:componentList.get(0);
    }

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.controller.AbstractComponetController#setComponent(cn.limc.androidcharts.component.Component)
     */
    @Override
    public void setComponent(Component component) {
        if(component != null){
            componentList.set(0, component);
        }
    }
}
