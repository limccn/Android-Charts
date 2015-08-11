//
// KChartComponent.java
// cn.limc.androidcharts.component
//
// Created by limc on 2015-8-3.
//
// Copyright 2015 Shanghai Okasan-Huada Computer System Co. Ltd., All rights reserved.
//
package cn.limc.androidcharts.component;

import android.graphics.Canvas;
import cn.limc.androidcharts.series.ChartDataTable;
import cn.limc.androidcharts.series.IMeasurable;
import cn.limc.androidcharts.shape.KShape;

/**
 * KChartComponent
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
public class KChartComponent extends DataComponent {

    /**
     * 
     */
    public KChartComponent() {
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.component.Component#draw(android.graphics.Canvas)
     */
    @Override
    public void draw(Canvas canvas) {
        // TODO Auto-generated method stub
        drawK(canvas);
    }
    
    protected void drawK(Canvas canvas) {
        if (null == chartData) {
            return;
        }
        if (chartData.size() == 0) {
            return;
        }

        float stickWidth = getPaddingWidth() / dataCursor.getDisplayNumber();
        float stickX = getPaddingStartX();

        for(int i=0; i< chartData.size() ; i++){
            ChartDataTable table = chartData.getChartTable(i);
            if (null == table) {
                continue;
            }
            if(table.size() == 0){
                continue;
            }
            for (int j = dataCursor.getDisplayFrom(); j < dataCursor.getDisplayTo(); j++) {
                IMeasurable stick = (IMeasurable)table.get(j);
                
                KShape mole = new KShape();
                mole.setUp(this,stick,stickX,stickWidth);
                mole.draw(canvas);

                // next x
                stickX = stickX + stickWidth;
            }
        }
    }
}
