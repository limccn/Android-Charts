//
// AreaChartComponent.java
// cn.limc.androidcharts.component
//
// Created by limc on 2015-8-10.
//
// Copyright 2015 Shanghai Okasan-Huada Computer System Co. Ltd., All rights reserved.
//
package cn.limc.androidcharts.component;

import cn.limc.androidcharts.series.IMeasurable;
import cn.limc.androidcharts.series.LineEntity;
import cn.limc.androidcharts.shape.Area;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;

/**
 * AreaChartComponent
 * Description: <br>
 *   <p>add description here </p>
 * Tags: <br>
 *   <p> </p>
 *
 * @author limc
 * @version v1.0 
 * 
 * History: <br>
 * 2015-8-10 limc create v1.0 <br>
 *
 */
public class AreaChartComponent extends DataComponent {

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.component.Component#draw(android.graphics.Canvas)
     */
    @Override
    public void draw(Canvas canvas) {
        drawAreas(canvas);
    }
    
    protected void drawAreas(Canvas canvas) {
        if (null == chartData) {
            return;
        }
        if (chartData.size() == 0) {
            return;
        }

        float stickWidth = getPaddingWidth() / dataCursor.getDisplayNumber();
        for (int i = 1; i < chartData.size(); i++) {
            LineEntity table1 = (LineEntity) chartData.getChartTable(i);
            LineEntity table2 = (LineEntity) chartData.getChartTable(i-1);
            

            if (null == table1) {
                continue;
            }
            if (table1.size() == 0) {
                continue;
            }
            
            if (null == table2) {
                continue;
            }
            if (table2.size() == 0) {
                continue;
            }

            Paint mPaint = new Paint();
            mPaint.setStyle(Style.FILL_AND_STROKE);
            mPaint.setColor(table1.getLineColor());
            mPaint.setAntiAlias(true);

            float stickX = getPaddingStartX() + stickWidth / 2;
            for (int j = dataCursor.getDisplayFrom() + 1; j < dataCursor.getDisplayTo(); j++) {

                IMeasurable currentHigh = (IMeasurable) table1.get(j - 1);
                IMeasurable nextHigh = (IMeasurable) table1.get(j);
                IMeasurable currentLow = (IMeasurable) table2.get(j - 1);
                IMeasurable nextLow = (IMeasurable) table2.get(j);

                Area areaMole = new Area();
                areaMole.setUp(this, currentHigh.getHigh(), currentLow.getHigh(), nextHigh.getHigh(),
                        nextLow.getHigh(), stickX, stickWidth);
                areaMole.setAreaPaint(mPaint);
                areaMole.draw(canvas);

                // next x
                stickX = stickX + stickWidth;
            }
        }
    }
}
