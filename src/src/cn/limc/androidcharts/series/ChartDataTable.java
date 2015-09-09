/*
 * ChartDataTable.java
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
 * ChartDataTable
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
public class ChartDataTable {

    List<ChartDataRow> tableData;
    
    /**
     * 
     */
    public ChartDataTable() {
        tableData = new ArrayList<ChartDataRow>();
    }
    
    public ChartDataTable(List<ChartDataRow> tableData){
        this.tableData = tableData;
    }
    
    public int size(){
        return tableData.size();
    }

    public ChartDataRow get(int index){
        return tableData.get(index);
    }

    public boolean hasData(){
        return !hasData();
    }

    public boolean hasNoData(){
        return tableData.isEmpty();
    }

    public void add(ChartDataRow data){
        this.tableData.add(data);
    }
    
    public void addAll(List<ChartDataRow> data){
        this.tableData.addAll(data);
    }

    /**
     * @return the tableData
     */
    public List<ChartDataRow> getTableData() {
        return tableData;
    }

    /**
     * @param tableData the tableData to set
     */
    public void setTableData(List<ChartDataRow> tableData) {
        this.tableData = tableData;
    }
}
