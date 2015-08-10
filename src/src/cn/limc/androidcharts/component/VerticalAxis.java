/*
 * VerticalAxis.java
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

/** 
 * <p>en</p>
 * <p>jp</p>
 * <p>cn</p>
 *
 * @author limc 
 * @version v1.0 2014/06/24 19:43:01 
 *  
 */
public class VerticalAxis extends AbstractAxis {
    
	    
    public void drawLine(Canvas canvas){
        float postX;
        if (position == AXIS_Y_POSITION_LEFT) {
            postX = getEndX() - lineWidth / 2;
        } else {
            postX = getStartX() + lineWidth / 2;
        }

        Paint mPaint = new Paint();
        mPaint.setColor(lineColor);
        mPaint.setStrokeWidth(lineWidth);

        canvas.drawLine(postX, getStartY() , postX, getEndY(), mPaint);
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
        
        float postX;

        if (position == AXIS_Y_POSITION_LEFT) {
            mPaintFont.setTextAlign(Align.RIGHT);
            postX = getEndX() - 2f;
        } else {
            mPaintFont.setTextAlign(Align.LEFT);
            postX = getStartX() + 2f;
        }
        Grid grid = bindComponent.getDataGrid();
        for (int i = 0; i < degrees.size(); i++) {
            if (0 == i) {
                canvas.drawText(degrees.get(i), postX, grid.latitudePostForIndex(i) - 2f,
                        mPaintFont);
            } else {
                canvas.drawText(degrees.get(i), postX, grid.latitudePostForIndex(i)
                        + degreeFontSize / 2f, mPaintFont);
            }
        }
    }
    
    /**
     * <p>
     * calculate degree title on Y axis
     * </p>
     * <p>
     * Y軸の目盛を計算する
     * </p>
     * <p>
     * 计算Y轴上显示的坐标值
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
    public String getHeightDegree(float value) {
        float graduate = bindComponent.getHeightRate(value);
        return degree.valueForDegree(graduate * bindComponent.getDataRange().getValueRange() + bindComponent.getDataRange().getMinValue());
    }
}
