/*
 * ChartDataSet.java
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
package cn.limc.androidcharts.series;

import java.util.ArrayList;
import java.util.List;

/**
 * ChartDataSet
 * Description: <br>
 *   <p>add description here </p>
 * Tags: <br>
 *   <p> </p>
 *
 * @author limc
 * @version v1.0 
 * 
 * History: <br>
 * 2015-7-27 limc create v1.0 <br>
 *
 */
public class ChartDataSet {

    protected List<ChartDataTable> tables;
    
    /**
     * 
     */
    public ChartDataSet() {
        tables = new ArrayList<ChartDataTable>();
    }
    
    public ChartDataSet(ChartDataTable table) {
        tables = new ArrayList<ChartDataTable>();
        tables.add(table);
    }
    
    public ChartDataSet(List<ChartDataTable> tables) {
        this.tables = tables;
    }
    
    //return first table only
    public List<ChartDataTable> getChartTables(){
        return tables;
    }
    
    //return first table only
    public ChartDataTable getChartTable(){
        return tables.get(0);
    }
    
    public ChartDataTable getChartTable(int index){
        return tables.get(index);
    }
    
    public int size(){
        return tables.size();
    }
    
    public void add(ChartDataTable table){
        this.tables.add(table);
    }
    
    public void addAll(List<ChartDataTable> data){
        this.tables.addAll(data);
    }
}
