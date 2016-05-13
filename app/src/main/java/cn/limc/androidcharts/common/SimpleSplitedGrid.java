/*
 * SimpleGrid.java
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

package cn.limc.androidcharts.common;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PathEffect;

import java.util.List;

import cn.limc.androidcharts.view.DataGridChart;
import cn.limc.androidcharts.view.GridChart;


/**
 * SimpleGrid
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
public class SimpleSplitedGrid extends SimpleGrid {

    protected int[] longitudeSplitor = new int[]{};
    protected double[] latitudeSplitor = new double[]{};

    /**
     *
     */
    public SimpleSplitedGrid (DataGridChart inChart) {
        super(inChart);
    }


    public int[] getLongitudeSplitor() {
        return longitudeSplitor;
    }

    public void setLongitudeSplitor(int[] longitudeSplitor) {
        this.longitudeSplitor = longitudeSplitor;
    }

    public double[] getLatitudeSplitor() {
        return latitudeSplitor;
    }

    public void setLatitudeSplitor(double[] latitudeSplitor) {
        this.latitudeSplitor = latitudeSplitor;
    }


    @Override
    protected void drawLongitudeTitle(Canvas canvas) {

        if (null == longitudeTitles) {
            return;
        }
        if (!displayLongitude) {
            return;
        }
        if (!displayLongitudeTitle) {
            return;
        }
        if (longitudeTitles.size() <= 1) {
            return;
        }
        if(longitudeSplitor == null){
            return;
        }
        if(longitudeSplitor.length == 0){
            return;
        }

        Paint mPaintFont = new Paint();
        mPaintFont.setColor(longitudeFontColor);
        mPaintFont.setTextSize(longitudeFontSize);
        mPaintFont.setAntiAlias(true);

        DataGridChart chart = (DataGridChart)this.inChart;
        float lineLength = (chart.getDataQuadrant().getPaddingWidth() / chart.getDisplayNumber());
        float offset = longitudeOffset() + lineLength / 2;

        int counter = 0;
        int titleIndex = 0;
        do{
            for(int i=0; i < longitudeSplitor.length && titleIndex< this.longitudeTitles.size() ;i++){
                String str = this.longitudeTitles.get(i);

                float postOffset = counter * lineLength;
                float titleLength = mPaintFont.measureText(str);

                if (titleIndex > 0) {
                    float titleX= offset + postOffset - titleLength / 2.0f;
                    //处理最后一条轴线越线问题
                    if (titleX + titleLength > inChart.getDataQuadrant().getEndX()) {
                        titleX= inChart.getDataQuadrant().getEndX() -  titleLength;
                    }
                    canvas.drawText(str,titleX,inChart.getHeight() - inChart.getAxisX().getHeight()
                            + longitudeFontSize,mPaintFont);
                }else{

                    canvas.drawText(str,inChart.getDataQuadrant().getStartX(), inChart.getHeight() - inChart.getAxisX().getHeight()
                            + longitudeFontSize ,mPaintFont);
                }
                //计数器重新设置
                counter = counter + longitudeSplitor[i];
                titleIndex = titleIndex + 1;
            }
            if (titleIndex ==  this.longitudeTitles.size()){
                break;
            }
        }while(counter < chart.getDisplayNumber());
    }

    @Override
    protected void drawLongitudeLine(Canvas canvas) {
        if (null == longitudeTitles) {
            return;
        }
        if (!displayLongitude) {
            return;
        }
        if(longitudeSplitor == null){
            return;
        }
        if(longitudeSplitor.length == 0){
            return;
        }
        int counts = longitudeTitles.size();
        float length = inChart.getDataQuadrant().getHeight();

        Paint mPaintLine = new Paint();
        mPaintLine.setStyle(Style.STROKE);
        mPaintLine.setColor(longitudeColor);
        mPaintLine.setStrokeWidth(longitudeWidth);
        mPaintLine.setAntiAlias(true);
        if (dashLongitude) {
            mPaintLine.setPathEffect(dashEffect);
        }

        DataGridChart chart = (DataGridChart)this.inChart;
        float lineLength = (chart.getDataQuadrant().getPaddingWidth() / chart.getDisplayNumber());
        float offset = longitudeOffset() + lineLength / 2;

        int counter = 0;
        do{
            for(int i=0; i < longitudeSplitor.length ;i++){

                float postOffset = counter * lineLength;

                Path path = new Path();
                path.moveTo(offset + postOffset, inChart.getBorderWidth());
                path.lineTo(offset + postOffset, length);
                canvas.drawPath(path, mPaintLine);

                //计数器重新设置
                counter = counter + longitudeSplitor[i];
            }

        }while(counter < chart.getDisplayNumber());
    }
}
