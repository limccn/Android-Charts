/*
 * RadarChart.java
 * Android-Charts
 *
 * Created by limc on 2014/07/29.
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
package cn.limc.androidcharts.view;

import cn.limc.androidcharts.entity.TitleValueColorEntity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import android.util.AttributeSet;

/**
 * RoseChart
 * Description: <br>
 *   <p>add description here </p>
 * Tags: <br>
 *   <p> </p>
 *
 * @author limc
 * @version v1.0 
 * 
 * History: <br>
 * 2014/08/26 limc create v1.0 <br>
 *
 */
public class RoseChart extends PieChart {

    /**
     * @param context
     */
    public RoseChart(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public RoseChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param context
     * @param attrs
     */
    public RoseChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }
    
    
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
    
    /**
     * <p>
     * Draw a circle
     * </p>
     * <p>
     * 満丸を書く
     * </p>
     * <p>
     * 绘制一个圆
     * </p>
     * 
     * @param canvas
     */
    @Override
    protected void drawCircle(Canvas canvas) {
    }

    /**
     * <p>
     * Draw the data
     * </p>
     * <p>
     * チャートでデータを書く
     * </p>
     * <p>
     * 将数据绘制在图表上
     * </p>
     * 
     * @param canvas
     */
    @Override
    protected void drawData(Canvas canvas) {
        if (null != data) {
            // data's value max
            float max = 0;
            for (int i = 0; i < data.size(); i++) {
                max = Math.max(max,data.get(i).getValue());
            }

            Paint mPaintFill = new Paint();
            mPaintFill.setStyle(Style.FILL);
            mPaintFill.setAntiAlias(true);

            Paint mPaintBorder = new Paint();
            mPaintBorder.setStyle(Style.STROKE);
            mPaintBorder.setColor(longitudeColor);
            mPaintBorder.setAntiAlias(true);
            
            Paint mPaintArcBorder = new Paint();
            mPaintArcBorder.setColor(circleBorderColor);
            mPaintArcBorder.setStyle(Style.STROKE);
            mPaintArcBorder.setAntiAlias(true);

            int offset = -90;
            //fixed sweep
            int sweep = Math.round(1.0f/data.size() * 360f);
            // draw arcs of every piece
            for (int j = 0; j < data.size(); j++) {
                TitleValueColorEntity e = data.get(j);
                // get color
                mPaintFill.setColor(e.getColor());
                //arc radius
                float radiusLength = e.getValue() / max * longitudeLength;
                // RectF for draw arc
                RectF oval = new RectF(position.x - radiusLength, position.y
                        - radiusLength, position.x + radiusLength, position.y
                        + radiusLength);
                
                //draw board
                canvas.drawArc(oval, offset, sweep, true, mPaintFill);
                canvas.drawArc(oval, offset, sweep, true, mPaintBorder);
                
                offset = offset + sweep;
            }

//            float sumvalue = 0f;
//            for (int k = 0; k < data.size(); k++) {
//                TitleValueColorEntity e = data.get(k);
//                float value = e.getValue();
//                sumvalue = sumvalue + value;
//                float rate = (sumvalue - value / 2) / sum;
//                mPaintFill.setColor(Color.BLUE);
//
//                // percentage
//                float percentage = (int) (value / sum * 10000) / 100f;
//
//                float offsetX = (float) (position.x - longitudeLength * 0.5
//                        * Math.sin(rate * -2 * Math.PI));
//                float offsetY = (float) (position.y - longitudeLength * 0.5
//                        * Math.cos(rate * -2 * Math.PI));
//
//                Paint mPaintFont = new Paint();
//                mPaintFont.setColor(Color.LTGRAY);
//
//                // draw titles
//                String title = e.getTitle();
//                float realx = 0;
//                float realy = 0;
//
//                // TODO title position
//                if (offsetX < position.x) {
//                    realx = offsetX - mPaintFont.measureText(title) - 5;
//                } else if (offsetX > position.x) {
//                    realx = offsetX + 5;
//                }
//
//                if (offsetY > position.y) {
//                    if (value / sum < 0.2f) {
//                        realy = offsetY + 10;
//                    } else {
//                        realy = offsetY + 5;
//                    }
//                } else if (offsetY < position.y) {
//                    if (value / sum < 0.2f) {
//                        realy = offsetY - 10;
//                    } else {
//                        realy = offsetY + 5;
//                    }
//                }
//
//                canvas.drawText(title, realx, realy, mPaintFont);
//                canvas.drawText(String.valueOf(percentage) + "%", realx,
//                        realy + 12, mPaintFont);
//            }
        }
    }
}
