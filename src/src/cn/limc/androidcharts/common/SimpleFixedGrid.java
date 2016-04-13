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
public class SimpleFixedGrid extends SimpleGrid {
    /**
     * @param inChart
     */
    public SimpleFixedGrid(GridChart inChart) {
        super(inChart);
    }

    /**
     * <p>
     * draw longitude lines
     * </p>
     * <p>
     * 経線を書く
     * </p>
     * <p>
     * 绘制经线
     * </p>
     * 
     * @param canvas
     */
    protected void drawLongitudeLine(Canvas canvas) {
        if (null == longitudeTitles) {
            return;
        }
        if (!displayLongitude) {
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
        if (counts > 1) {
            float postOffset = longitudePostOffset();
            float offset = longitudeOffset();

            for (int i = 0; i < counts; i++) {
                Path path = new Path();
                path.moveTo(offset + i * postOffset, inChart.getBorderWidth());
                path.lineTo(offset + i * postOffset, length);
                canvas.drawPath(path, mPaintLine);
            }
        }
    }

    /**
     * <p>
     * draw longitude lines
     * </p>
     * <p>
     * 経線を書く
     * </p>
     * <p>
     * 绘制经线
     * </p>
     * 
     * @param canvas
     */
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

        Paint mPaintFont = new Paint();
        mPaintFont.setColor(longitudeFontColor);
        mPaintFont.setTextSize(longitudeFontSize);
        mPaintFont.setAntiAlias(true);

        float postOffset = longitudePostOffset();

        float offset = longitudeOffset();
        for (int i = 0; i < longitudeTitles.size(); i++) {
            if (0 == i) {
                canvas.drawText(longitudeTitles.get(i), offset + 2f,
                        inChart.getHeight() - inChart.getAxisX().getHeight()
                                + longitudeFontSize, mPaintFont);
            } else {
                canvas.drawText(longitudeTitles.get(i), offset + i * postOffset
                        - (longitudeTitles.get(i).length()) * longitudeFontSize
                        / 2f, inChart.getHeight() - inChart.getAxisX().getHeight()
                        + longitudeFontSize, mPaintFont);
            }
        }
    }

//    /**
//     * <p>
//     * draw latitude lines
//     * </p>
//     * <p>
//     * 緯線を書く
//     * </p>
//     * <p>
//     * 绘制纬线
//     * </p>
//     *
//     * @param canvas
//     */
//    protected void drawLatitudeLine(Canvas canvas) {
//
//        if (null == latitudeTitles) {
//            return;
//        }
//        if (!displayLatitude) {
//            return;
//        }
//        if (!displayLatitudeTitle) {
//            return;
//        }
//        if (latitudeTitles.size() <= 1) {
//            return;
//        }
//
//        float length = inChart.getDataQuadrant().getWidth();
//
//        Paint mPaintLine = new Paint();
//        mPaintLine.setStyle(Style.STROKE);
//        mPaintLine.setColor(latitudeColor);
//        mPaintLine.setStrokeWidth(latitudeWidth);
//        mPaintLine.setAntiAlias(true);
//        if (dashLatitude) {
//            mPaintLine.setPathEffect(dashEffect);
//        }
//
//        Paint mPaintFont = new Paint();
//        mPaintFont.setColor(latitudeFontColor);
//        mPaintFont.setTextSize(latitudeFontSize);
//        mPaintFont.setAntiAlias(true);
//
//        float postOffset = inChart.getDataQuadrant().getPaddingHeight()
//                / (latitudeTitles.size() - 1);
//
//        float offset = inChart.getHeight() - inChart.getBorderWidth()
//                - inChart.getAxisX().getHeight() - inChart.getAxisX().getLineWidth()
//                - inChart.getDataQuadrant().getPaddingBottom();
//
////        if (inChart.getAxisY().getPosition() == IAxis.AXIS_Y_POSITION_LEFT) {
////            float startFrom = inChart.getBorderWidth() + inChart.getAxisY().getWidth()  + inChart.getAxisY().getLineWidth();
////            for (int i = 0; i < latitudeTitles.size(); i++) {
////                Path path = new Path();
////                path.moveTo(startFrom, offset - i * postOffset);
////                path.lineTo(startFrom + length, offset - i * postOffset);
////                canvas.drawPath(path, mPaintLine);
////            }
////        } else {
//            float startFrom = inChart.getBorderWidth();
//            for (int i = 0; i < latitudeTitles.size(); i++) {
//                Path path = new Path();
//                path.moveTo(startFrom, offset - i * postOffset);
//                path.lineTo(startFrom + length, offset - i * postOffset);
//                canvas.drawPath(path, mPaintLine);
//            }
////        }
//    }
//
//    /**
//     * <p>
//     * draw latitude lines
//     * </p>
//     * <p>
//     * 緯線を書く
//     * </p>
//     * <p>
//     * 绘制纬线
//     * </p>
//     *
//     * @param canvas
//     */
//    protected void drawLatitudeTitle(Canvas canvas) {
//        if (null == latitudeTitles) {
//            return;
//        }
//        if (!displayLatitudeTitle) {
//            return;
//        }
//        if (latitudeTitles.size() <= 1) {
//            return;
//        }
//        Paint mPaintFont = new Paint();
//        mPaintFont.setColor(latitudeFontColor);
//        mPaintFont.setTextSize(latitudeFontSize);
//        mPaintFont.setAntiAlias(true);
//        mPaintFont.setTextAlign(Paint.Align.LEFT);
//
//        float postOffset = inChart.getDataQuadrant().getPaddingHeight()
//                / (latitudeTitles.size() - 1);
//
//        float offset = inChart.getHeight() - inChart.getBorderWidth()
//                - inChart.getAxisX().getHeight() - inChart.getAxisX().getLineWidth()
//                - inChart.getDataQuadrant().getPaddingBottom();
//
////        if (inChart.getAxisY().getPosition() == IAxis.AXIS_Y_POSITION_LEFT) {
//            float startFromLeft = inChart.getBorderWidth();
//            for (int i = 0; i < latitudeTitles.size(); i++) {
//                if (latitudeTitles.size() - 1 == i) {
//                    canvas.drawText(latitudeTitles.get(i), startFromLeft, offset
//                                    - i * postOffset + latitudeFontSize + 2,
//                            mPaintFont);
//                } else {
//                    canvas.drawText(latitudeTitles.get(i), startFromLeft, offset
//                            - i * postOffset - 2,
//                            mPaintFont);
//                }
//            }
////        } else {
//
//
//        mPaintFont.setTextAlign(Paint.Align.RIGHT);
//
//            float startFromRight = inChart.getWidth() - inChart.getBorderWidth() ;
//
//            for (int i = 0; i < latitudeTitles.size(); i++) {
//
//                if (latitudeTitles.size() - 1 == i) {
////                    canvas.drawText(latitudeTitles.get(i), startFromRight,
////                            inChart.getHeight() - - inChart.getAxisX().getHeight() - inChart.getAxisX().getLineWidth()
////                            - inChart.getBorderWidth() - 2f, mPaintFont);
//                    canvas.drawText(latitudeTitles.get(i), startFromRight, offset
//                                    - i * postOffset + latitudeFontSize + 2,
//                            mPaintFont);
//                } else {
//                    canvas.drawText(latitudeTitles.get(i), startFromRight, offset
//                            - i * postOffset - 2,
//                            mPaintFont);
//                }
//            }
////        }
//    }
}
