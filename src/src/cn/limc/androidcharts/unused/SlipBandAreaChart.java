/*
 * SlipBandAreaChart.java
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

package cn.limc.androidcharts.unused;

import java.util.List;


import cn.limc.androidcharts.series.DateValueEntity;
import cn.limc.androidcharts.series.IMeasurable;
import cn.limc.androidcharts.series.LineEntity;
import cn.limc.androidcharts.shape.Area;
import cn.limc.androidcharts.shape.Points;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Paint.Style;
import android.util.AttributeSet;

/**
 * <p>
 * en
 * </p>
 * <p>
 * jp
 * </p>
 * <p>
 * cn
 * </p>
 * 
 * @author limc
 * @version v1.0 2014/01/22 18:03:26
 * 
 */
public class SlipBandAreaChart extends SlipLineChart {

	/**
	 * <p>
	 * Constructor of SlipBandAreaChart
	 * </p>
	 * <p>
	 * SlipBandChart类对象的构造函数
	 * </p>
	 * <p>
	 * SlipBandChartのコンストラクター
	 * </p>
	 * 
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public SlipBandAreaChart(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	/**
	 * <p>
	 * Constructor of SlipBandAreaChart
	 * </p>
	 * <p>
	 * SlipBandChart类对象的构造函数
	 * </p>
	 * <p>
	 * SlipBandChartのコンストラクター
	 * </p>
	 * 
	 * @param context
	 * @param attrs
	 */
	public SlipBandAreaChart(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	/**
	 * <p>
	 * Constructor of SlipBandAreaChart
	 * </p>
	 * <p>
	 * SlipBandChart类对象的构造函数
	 * </p>
	 * <p>
	 * SlipBandChartのコンストラクター
	 * </p>
	 * 
	 * @param context
	 */
	public SlipBandAreaChart(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
//		lineAlignType = IFlexableGrid.ALIGN_TYPE_CENTER;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * <p>Called when is going to draw this chart<p> <p>チャートを書く前、メソッドを呼ぶ<p>
	 * <p>绘制图表时调用<p>
	 * 
	 * @param canvas
	 * 
	 * @see android.view.View#onDraw(android.graphics.Canvas)
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// draw lines
//		drawAreas(canvas);
	}
//	
//	protected void drawAreas(Canvas canvas) {
//        if (null == chartData) {
//            return;
//        }
//        if (chartData.size() < 2 ) {
//            return;
//        }
//
//        float stickWidth = dataQuadrant.getPaddingWidth() / getDisplayNumber();
//        for(int i=1; i< chartData.size() ; i++){
//            LineEntity table1 = (LineEntity)chartData.getChartTable(i-1);
//            LineEntity table2 = (LineEntity)chartData.getChartTable(i);
//            
//            if (null == table1 || null == table2) {
//                continue;
//            }
//            if(table1.size() == 0 || table2.size() == 0){
//                continue;
//            }
//            
//            Paint mPaint = new Paint();
//            mPaint.setStyle(Style.FILL);
//            mPaint.setColor(table1.getLineColor());
//            mPaint.setAntiAlias(true);
//            
//            float stickX = dataQuadrant.getPaddingStartX() + stickWidth / 2;
//            for (int j = getDisplayFrom()+1; j < getDisplayTo(); j++) {
//
//                IMeasurable pointLow = (IMeasurable)table1.get(j-1);
//                IMeasurable nextpointLow = (IMeasurable)table1.get(j);
//                
//                IMeasurable pointHigh = (IMeasurable)table2.get(j-1);
//                IMeasurable nextpointHigh = (IMeasurable)table2.get(j);
//                
//                Area areaMole = new Area();
//                areaMole.setUp(this,pointHigh.getHigh(),pointLow.getHigh(),nextpointHigh.getHigh(),nextpointLow.getHigh(),stickX,stickWidth);
//                areaMole.setAreaPaint(mPaint);
//                areaMole.draw(canvas);
//
//                // next x
//                stickX = stickX + stickWidth;
//            }
//        }
//    }

//	/**
//	 * <p>
//	 * draw lines
//	 * </p>
//	 * <p>
//	 * ラインを書く
//	 * </p>
//	 * <p>
//	 * 绘制线条
//	 * </p>
//	 * 
//	 * @param canvas
//	 */
//	protected void drawAreas(Canvas canvas) {
//		if (null == linesData) {
//			return;
//		}
//		if (linesData.size() < 2) {
//			return;
//		}
//		LineEntity<DateValueEntity> line1 = (LineEntity<DateValueEntity>) linesData
//				.get(0);
//		LineEntity<DateValueEntity> line2 = (LineEntity<DateValueEntity>) linesData
//				.get(1);
//		List<DateValueEntity> line1Data = line1.getLineData();
//		List<DateValueEntity> line2Data = line2.getLineData();
//
//		if (line1.isDisplay() == false || line2.isDisplay() == false) {
//			return;
//		}
//		if (line1Data == null || line2Data == null) {
//			return;
//		}
//
//		Paint mPaint = new Paint();
//		mPaint.setColor(line1.getLineColor());
//		mPaint.setAlpha(70);
//		mPaint.setAntiAlias(true);
//
//		// distance between two points
//		float lineLength;
//		// start point‘s X
//		// set start point’s X
//		float startX;
//		
//		if (lineAlignType == IFlexableGrid.ALIGN_TYPE_CENTER) {
//            lineLength= (dataQuadrant.getPaddingWidth() / displayNumber);
//            startX = dataQuadrant.getPaddingStartX() + lineLength / 2;
//        }else {
//            lineLength= (dataQuadrant.getPaddingWidth() / (displayNumber - 1));
//            startX = dataQuadrant.getPaddingStartX();
//        }
//		
//		float lastY = 0;
//		float lastX = 0;
//
//		Path areaPath = new Path();
//
//		for (int j = displayFrom; j < displayFrom + displayNumber; j++) {
//			float value1 = line1Data.get(j).getValue();
//			float value2 = line2Data.get(j).getValue();
//
//			// calculate Y
//			float valueY1 = (float) ((1f - (value1 - minValue)
//					/ (maxValue - minValue)) * dataQuadrant.getPaddingHeight())
//					+ dataQuadrant.getPaddingStartY();
//			float valueY2 = (float) ((1f - (value2 - minValue)
//					/ (maxValue - minValue)) * dataQuadrant.getPaddingHeight())
//					+ dataQuadrant.getPaddingStartY();
//
//			// 绘制线条路径
//			if (j == displayFrom) {
//				areaPath.moveTo(startX, valueY1);
//				areaPath.lineTo(startX, valueY2);
//				areaPath.moveTo(startX, valueY1);
//			} else {
//				areaPath.lineTo(startX, valueY1);
//				areaPath.lineTo(startX, valueY2);
//				areaPath.lineTo(lastX, lastY);
//				areaPath.close();
//				areaPath.moveTo(startX, valueY1);
//			}
//
//			lastX = startX;
//			lastY = valueY2;
//			startX = startX + lineLength;
//		}
//		areaPath.close();
//		canvas.drawPath(areaPath, mPaint);
//	}
}
