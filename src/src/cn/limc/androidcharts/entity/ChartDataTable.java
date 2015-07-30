//
// ChartDataTable.java
// cn.limc.androidcharts.entity
//
// Created by limc on 2015-7-27.
//
// Copyright 2015 Shanghai Okasan-Huada Computer System Co. Ltd., All rights reserved.
//
package cn.limc.androidcharts.entity;

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
