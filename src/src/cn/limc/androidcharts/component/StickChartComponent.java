/*
 * StickChartComponent.java
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

package cn.limc.androidcharts.component;

import android.graphics.Canvas;
import cn.limc.androidcharts.model.DataCursor;
import cn.limc.androidcharts.series.ChartDataSet;
import cn.limc.androidcharts.series.ChartDataTable;
import cn.limc.androidcharts.series.IMeasurable;
import cn.limc.androidcharts.shape.Bar;

/**
 * StickChartComponent
 * Description: <br>
 *   <p>add description here </p>
 * Tags: <br>
 *   <p> </p>
 *
 * @author limc
 * @version v1.0 
 * 
 * History: <br>
 * 2014/09/01 limc create v1.0 <br>
 *
 */
public class StickChartComponent extends DataComponent {

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.component.Component#draw(android.graphics.Canvas)
     */
    @Override
    public void draw(Canvas canvas) {
        drawSticks(canvas);
    }
    
    protected void drawSticks(Canvas canvas) {
        ChartDataSet chartData = getChartData();
        if (null == chartData) {
            return;
        }
        if (chartData.size() == 0) {
            return;
        }

        DataCursor dataCursor = getDataCursor();
        
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
                
                Bar mole = new Bar();
                mole.setUp(this,stick,stickX,stickWidth);
                mole.draw(canvas);

                // next x
                stickX = stickX + stickWidth;
            }
        }
    }
}
