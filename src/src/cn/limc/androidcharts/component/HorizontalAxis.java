/*
 * HorizontalAxis.java
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

import java.util.List;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import cn.limc.androidcharts.diagram.GridChart;
import cn.limc.androidcharts.series.IHasDate;

/** 
 * <p>en</p>
 * <p>jp</p>
 * <p>cn</p>
 *
 * @author limc 
 * @version v1.0 2014/06/24 19:58:57 
 *  
 */
public class HorizontalAxis extends AbstractAxis {

    public void drawLine(Canvas canvas) { 
        float postY;
        if (position == AXIS_X_POSITION_BOTTOM) {
            postY = getStartY() +  lineWidth / 2;
        } else {
            postY = getEndY() - lineWidth / 2;
        }

        Paint mPaint = new Paint();
        mPaint.setColor(lineColor);
        mPaint.setStrokeWidth(lineWidth);

        canvas.drawLine(getStartX(), postY, getEndX(), postY, mPaint);
    }
    
    public void drawDegrees(Canvas canvas) {

        if (degree == null) {
            return;
        }
        
        List<String> degrees = degree.getDegrees();

        if (degrees == null) {
            return;
        }

        if (degrees.isEmpty()) {
            return;
        }

        Paint mPaintFont = new Paint();
        mPaintFont.setColor(degreeFontColor);
        mPaintFont.setTextSize(degreeFontSize);
        mPaintFont.setAntiAlias(true);
        
        float postY;
        if (position == AXIS_X_POSITION_BOTTOM) {
            postY = getStartY() +  degreeFontSize;
        } else {
            postY = getEndY() - degreeFontSize  -2;
        }

        Grid grid = bindComponent.getDataGrid();
        for (int i = 0; i < degrees.size(); i++) {
            String mDegree = degrees.get(i);
            if (0 == i) {
                mPaintFont.setTextAlign(Align.LEFT);
            } else if (i == (degrees.size() - 1 ) ) {
                mPaintFont.setTextAlign(Align.RIGHT);
            } else{
                mPaintFont.setTextAlign(Align.CENTER);
            }
            
            canvas.drawText(mDegree, grid.longitudePostForIndex(i) , postY, mPaintFont);
        }
    }
    

    
    /**
     * <p>
     * calculate degree title on X axis
     * </p>
     * <p>
     * X軸の目盛を計算する
     * </p>
     * <p>
     * 计算X轴上显示的坐标值
     * </p>
     * 
     * @param value
     *            <p>
     *            value for calculate
     *            </p>
     *            <p>
     *            計算有用データ
     *            </p>
     *            <p>
     *            计算用数据
     *            </p>
     * 
     * @return String
     *         <p>
     *         degree
     *         </p>
     *         <p>
     *         目盛
     *         </p>
     *         <p>
     *         坐标值
     *         </p>
     */
    public String getWidthDegree(float value) {
        float graduate = bindComponent.getWidthRate(value);
        int index = (int) Math.floor(graduate * bindComponent.getDataCursor().getDisplayNumber());

        if (index >= bindComponent.getDataCursor().getDisplayNumber()) {
            index = bindComponent.getDataCursor().getDisplayNumber() - 1;
        } else if (index < 0) {
            index = 0;
        }

        IHasDate dataRow= (IHasDate)bindComponent.getChartData().getChartTable().get(index);
        return degree.valueForDegree(dataRow.getDate());
    }
}
