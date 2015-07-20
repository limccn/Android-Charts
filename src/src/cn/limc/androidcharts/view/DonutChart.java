/*
 * DonutChart.java
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

package cn.limc.androidcharts.view;

import java.util.List;

import cn.limc.androidcharts.entity.TitleValueColorEntity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import android.util.AttributeSet;

/**
 * DonutChart
 * Description: <br>
 *   <p>add description here </p>
 * Tags: <br>
 *   <p> </p>
 *
 * @author limc
 * @version v1.0 
 * 
 * History: <br>
 * 2015-7-17 limc create v1.0 <br>
 *
 */
public class DonutChart extends RoundChart {
    
    public static final int DEFAULT_TITLE_FONT_SIZE = 20;

    /**
     * <p>
     * default radius length
     * </p>
     * <p>
     * 半径の長さのデフォルト値
     * </p>
     * <p>
     * 默认半径长度
     * </p>
     */
    public static final int DEFAULT_DONUT_WIDTH = 80;
    
    /**
     * <p>
     * Data
     * </p>
     * <p>
     * データ
     * </p>
     * <p>
     * 图表数据
     * </p>
     */
    protected List<TitleValueColorEntity> data;
    
    /**
     * <p>
     * radius length
     * </p>
     * <p>
     * 半径の長さ
     * </p>
     * <p>
     * 半径长度
     * </p>
     */
    protected float donutWidth = DEFAULT_DONUT_WIDTH;
    
    protected float titleFontSize = DEFAULT_TITLE_FONT_SIZE;

    
    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public DonutChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param context
     * @param attrs
     */
    public DonutChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param context
     */
    public DonutChart(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }
    
    /*
     * (non-Javadoc)
     * 
     * <p>Called when is going to draw this chart</p> <p>チャートを書く前、メソッドを呼ぶ</p>
     * <p>绘制图表时调用</p>
     * 
     * @param canvas
     * 
     * @see android.view.View#onDraw(android.graphics.Canvas)
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // get safe rect
        int rect = super.getWidth() > super.getHeight() ? super.getHeight()
                : super.getWidth();

        // calculate radius length
        longitudeLength = (int) ((rect / 2f) * 0.90);
        
        donutWidth = longitudeLength * 0.382f;

        // calculate position
        position = new Point((int) (getWidth() / 2f), (int) (getHeight() / 2f));

        // draw this chart
        drawOuterCircle(canvas);
        
        drawInnerCircle(canvas);

        // draw data on chart
        drawData(canvas);
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
    protected void drawOuterCircle(Canvas canvas) {

        Paint mPaintCircleBorder = new Paint();
        mPaintCircleBorder.setColor(circleBorderColor);
        mPaintCircleBorder.setStyle(Style.STROKE);
        mPaintCircleBorder.setStrokeWidth(circleBorderWidth);
        mPaintCircleBorder.setAntiAlias(true);

        // draw a circle
        canvas.drawCircle(position.x, position.y, longitudeLength - borderWidth / 2,
                mPaintCircleBorder);
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
    protected void drawInnerCircle(Canvas canvas) {

        Paint mPaintCircleBorder = new Paint();
        mPaintCircleBorder.setColor(circleBorderColor);
        mPaintCircleBorder.setStyle(Style.STROKE);
        mPaintCircleBorder.setStrokeWidth(circleBorderWidth);
        mPaintCircleBorder.setAntiAlias(true);

        // draw a circle
        canvas.drawCircle(position.x, position.y, longitudeLength - donutWidth - borderWidth,
                mPaintCircleBorder);
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
    protected void drawData(Canvas canvas) {
        if (null != data) {
            // sum all data's value
            float sum = 0;
            for (int i = 0; i < data.size(); i++) {
                sum = sum + data.get(i).getValue();
            }

            Paint mPaintDonut = new Paint();
            mPaintDonut.setStyle(Style.STROKE);
            mPaintDonut.setAntiAlias(true);
            mPaintDonut.setStrokeWidth(donutWidth);

            Paint mPaintBorder = new Paint();
            mPaintBorder.setStyle(Style.STROKE);
            mPaintBorder.setColor(longitudeColor);
            mPaintBorder.setAntiAlias(true);

            float offset = -90;
            // draw arcs of every piece
            for (int j = 0; j < data.size(); j++) {
                TitleValueColorEntity e = data.get(j);

                // get color
                mPaintDonut.setColor(e.getColor());

                RectF oval = new RectF(position.x - longitudeLength + borderWidth /2 + donutWidth / 2, position.y
                        - longitudeLength + borderWidth / 2 + donutWidth / 2, position.x + longitudeLength - borderWidth / 2 - donutWidth / 2, position.y
                        + longitudeLength - borderWidth / 2 - donutWidth / 2);
                float sweep = e.getValue() * 360f / sum;
                
                //some android device may have bug when use sweep instead of sweep + 1
                canvas.drawArc(oval, offset, sweep + 1, false, mPaintDonut);
                offset = offset + sweep;
            }
            
            Paint mPaintFont = new Paint();
            mPaintFont.setColor(Color.LTGRAY);
            mPaintFont.setTextSize(titleFontSize);
            
            Paint mPaintRect = new Paint();
            mPaintRect.setStyle(Style.FILL);
            mPaintRect.setAntiAlias(true);
            mPaintRect.setStrokeWidth(titleFontSize);
            
            float startX = position.x - (longitudeLength - donutWidth - 2 * borderWidth) / 1.4f;
            float startY = position.y - data.size() * titleFontSize /2  ;
            
            for (int k = 0; k < data.size(); k++) {
               TitleValueColorEntity e = data.get(k);
                float value = e.getValue();

                // percentage
                float percentage = (int) (value / sum * 10000) / 100f;

                // draw titles
                String title = e.getTitle();
               
                mPaintRect.setColor(e.getColor());
                canvas.drawRect(startX + 1, startY+k*titleFontSize +1 , startX+titleFontSize -1, startY+k*titleFontSize+titleFontSize -1, mPaintRect);
                canvas.drawText(String.valueOf(percentage) + "%", startX + titleFontSize+2 ,
                        startY + k * titleFontSize + titleFontSize -2 , mPaintFont);
                canvas.drawText(title, startX + titleFontSize + 4 + mPaintFont.measureText(String.valueOf(percentage) + "%"), startY + k * titleFontSize + titleFontSize-2, mPaintFont);
            }
        }
    }

    /**
     * @return the data
     */
    public List<TitleValueColorEntity> getData() {
        return data;
    }

    /**
     * @param data
     *            the data to set
     */
    public void setData(List<TitleValueColorEntity> data) {
        this.data = data;
    }

    /**
     * @return the donutWidth
     */
    public float getDonutWidth() {
        return donutWidth;
    }

    /**
     * @param donutWidth the donutWidth to set
     */
    public void setDonutWidth(float donutWidth) {
        this.donutWidth = donutWidth;
    }

    /**
     * @return the titleFontSize
     */
    public float getTitleFontSize() {
        return titleFontSize;
    }

    /**
     * @param titleFontSize the titleFontSize to set
     */
    public void setTitleFontSize(float titleFontSize) {
        this.titleFontSize = titleFontSize;
    }
    
    

}
