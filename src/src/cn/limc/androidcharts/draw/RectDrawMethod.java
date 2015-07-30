//
// RectDrawMethod.java
// cn.limc.androidcharts.draw
//
// Created by limc on 2015-7-29.
//
// Copyright 2015 Shanghai Okasan-Huada Computer System Co. Ltd., All rights reserved.
//
package cn.limc.androidcharts.draw;

import android.R.integer;
import android.graphics.Canvas;
import cn.limc.androidcharts.common.IDataCursor;
import cn.limc.androidcharts.common.IQuadrant;
import cn.limc.androidcharts.entity.ChartDataRow;
import cn.limc.androidcharts.entity.ChartDataSet;
import cn.limc.androidcharts.entity.ChartDataTable;
import cn.limc.androidcharts.entity.IMeasurable;
import cn.limc.androidcharts.mole.IMole;
import cn.limc.androidcharts.mole.StickMole;

/**
 * RectDrawMethod
 * Description: <br>
 *   <p>add description here </p>
 * Tags: <br>
 *   <p> </p>
 *
 * @author limc
 * @version v1.0 
 * 
 * History: <br>
 * 2015-7-29 limc create v1.0 <br>
 *
 */
public class RectDrawMethod extends DrawMethod {

    IDataCursor dataCursor;
    ChartDataSet dataSet;
    IQuadrant quadrant;
   
    /**
     * 
     */
    public RectDrawMethod(IQuadrant quadrant,ChartDataSet dataSet, IDataCursor dataCursor) {
        super();
        this.quadrant = quadrant;
        this.dataCursor = dataCursor;
        this.dataSet = dataSet;
    }
    
    protected void draw(Canvas canvas) {
        if (null == dataSet) {
            return;
        }
        if (dataSet.size() == 0) {
            return;
        }

        float stickWidth = quadrant.getPaddingWidth() / dataCursor.getDisplayNumber();
        float stickX = quadrant.getPaddingStartX();

        for(int i=0; i< dataSet.size() ; i++){
            ChartDataTable table = dataTableForIndex(dataSet,i);
            if (null == table) {
                continue;
            }
            if(table.size() == 0){
                continue;
            }
            for (int j = dataCursor.getDisplayFrom(); j < dataCursor.getDisplayTo(); j++) {
                IMeasurable stick = (IMeasurable)table.get(j);
                
                StickMole mole = new StickMole();
//                mole.setUp(this,stick,stickX,stickWidth);
                mole.draw(canvas);

                // next x
                stickX = stickX + stickWidth;
            }
        }
    }
    
    public ChartDataTable dataTableForIndex(ChartDataSet dataSet, int index){
        return dataSet.getChartTable(index);
    }
    
    public ChartDataRow dataRowForIndex(ChartDataTable dataTable, int index){
        return dataTable.get(index);
    }
    
}
