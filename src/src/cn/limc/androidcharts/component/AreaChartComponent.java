/*
 * AreaChartComponent.java
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
import cn.limc.androidcharts.series.ChartDataSet;
import cn.limc.androidcharts.series.IMeasurable;
import cn.limc.androidcharts.series.LineEntity;
import cn.limc.androidcharts.shape.Area;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;

public class AreaChartComponent extends DataComponent {

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.component.Component#draw(android.graphics.Canvas)
     */
    @Override
    public void draw(Canvas canvas) {
        drawAreas(canvas);
    }
    
    protected void drawAreas(Canvas canvas) {
        ChartDataSet chartData = getChartData();
        
        if (null == chartData) {
            return;
        }
        if (chartData.size() == 0) {
            return;
        }
        
        DataCursor dataCursor = getDataCursor();

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
            mPaint.setAntiAlias(true);
            mPaint.setStyle(Style.FILL_AND_STROKE);
            mPaint.setColor(table1.getLineColor());
            mPaint.setAlpha(Area.DEFAULT_AREA_FILL_ALPHA);

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
