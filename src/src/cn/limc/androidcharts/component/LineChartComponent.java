/*
 * LineChartComponent.java
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

import android.graphics.Canvas;
import android.graphics.Paint;
import cn.limc.androidcharts.model.DataCursor;
import cn.limc.androidcharts.series.ChartDataSet;
import cn.limc.androidcharts.series.IMeasurable;
import cn.limc.androidcharts.series.LineEntity;
import cn.limc.androidcharts.shape.Points;

/**
 * LineChartComponent
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
public class LineChartComponent extends DataComponent {
    
    /* (non-Javadoc)
     * @see cn.limc.androidcharts.component.Component#draw(android.graphics.Canvas)
     */
    @Override
    public void draw(Canvas canvas) {
        // TODO Auto-generated method stub
        
        drawLines(canvas);

    }
        
    protected void drawLines(Canvas canvas) {
        ChartDataSet chartData = getChartData();
        if (null == chartData) {
            return;
        }
        if (chartData.size() == 0) {
            return;
        }

        DataCursor dataCursor = getDataCursor();
        
        float stickWidth = getPaddingWidth() / dataCursor.getDisplayNumber();

        for(int i=0; i< chartData.size() ; i++){
            LineEntity table = (LineEntity)chartData.getChartTable(i);
            if (null == table) {
                continue;
            }
            if(table.size() == 0){
                continue;
            }
            
            Paint mPaint = new Paint();
            mPaint.setColor(table.getLineColor());
            mPaint.setAntiAlias(true);
            
            float stickX = getPaddingStartX() + stickWidth / 2;
            for (int j = dataCursor.getDisplayFrom()+1; j < dataCursor.getDisplayTo(); j++) {
                IMeasurable point = (IMeasurable)table.get(j-1);
                IMeasurable nextpoint = (IMeasurable)table.get(j);
                
                Points lineMole = new Points();
                lineMole.setUp(this,point.getHigh(),nextpoint.getHigh(),stickX,stickWidth);
                lineMole.setLinePaint(mPaint);
                lineMole.draw(canvas);

                // next x
                stickX = stickX + stickWidth;
            }
        }
    }
}
