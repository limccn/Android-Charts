//
// ChartDataSet.java
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

    List<ChartDataTable> tables;
    
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
