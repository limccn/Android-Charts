/*
 * BOLLMASlipCandleStickChart.java
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


import cn.limc.androidcharts.series.ChartDataSet;
import cn.limc.androidcharts.series.IMeasurable;
import cn.limc.androidcharts.series.LineEntity;
import cn.limc.androidcharts.shape.Area;
import cn.limc.androidcharts.shape.Points;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
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
 * @version v1.0 2014/01/23 16:27:58
 * 
 */
public class BOLLMASlipCandleStickChart extends MASlipCandleStickChart {

	private ChartDataSet bandData;

	/**
	 * <p>
	 * Constructor of BOLLMASlipCandleStickChart
	 * </p>
	 * <p>
	 * BOLLMASlipCandleStickChart类对象的构造函数
	 * </p>
	 * <p>
	 * BOLLMASlipCandleStickChartのコンストラクター
	 * </p>
	 * 
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public BOLLMASlipCandleStickChart(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	/**
	 * <p>
	 * Constructor of BOLLMASlipCandleStickChart
	 * </p>
	 * <p>
	 * BOLLMASlipCandleStickChart类对象的构造函数
	 * </p>
	 * <p>
	 * BOLLMASlipCandleStickChartのコンストラクター
	 * </p>
	 * 
	 * @param context
	 * @param attrs
	 */
	public BOLLMASlipCandleStickChart(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	/**
	 * <p>
	 * Constructor of BOLLMASlipCandleStickChart
	 * </p>
	 * <p>
	 * BOLLMASlipCandleStickChart类对象的构造函数
	 * </p>
	 * <p>
	 * BOLLMASlipCandleStickChartのコンストラクター
	 * </p>
	 * 
	 * @param context
	 */
	public BOLLMASlipCandleStickChart(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

//	@Override
//	protected void calcDataValueRange() {
//		super.calcDataValueRange();
//
//		double maxValue = this.maxValue;
//		double minValue = this.minValue;
//		// 逐条输出MA线
//		for (int i = 0; i < this.bandData.size(); i++) {
//			LineEntity<DateValueEntity> line = this.bandData.get(i);
//			if (line != null && line.getLineData().size() > 0) {
//				// 判断显示为方柱或显示为线条
//				for (int j = dataCursor.getDisplayFrom(); j < dataCursor.getDisplayFrom() + dataCursor.getDisplayNumber(); j++) {
//					DateValueEntity lineData = line.getLineData().get(j);
//					if (lineData.getValue() < minValue) {
//						minValue = lineData.getValue();
//					}
//
//					if (lineData.getValue() > maxValue) {
//						maxValue = lineData.getValue();
//					}
//
//				}
//			}
//		}
//		this.maxValue = maxValue;
//		this.minValue = minValue;
//	}

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
//		// draw lines
//		if (null != bandData && bandData.size() >= 2) {
//			drawAreas(canvas);
//			drawBandBorder(canvas);
//		}
		
//		
//
//		drawAreas(canvas);
//		drawBandBorder(canvas);
	}
//	
//    protected void drawBandBorder(Canvas canvas) {
//        if (null == bandData) {
//            return;
//        }
//        if (bandData.size() == 0) {
//            return;
//        }
//
//        float stickWidth = dataQuadrant.getPaddingWidth() / getDisplayNumber();
//
//        for(int i=0; i< bandData.size() ; i++){
//            LineEntity table = (LineEntity)bandData.getChartTable(i);
//            if (null == table) {
//                continue;
//            }
//            if(table.size() == 0){
//                continue;
//            }
//            
//            Paint mPaint = new Paint();
//            mPaint.setColor(table.getLineColor());
//            mPaint.setAntiAlias(true);
//            
//            float stickX = dataQuadrant.getPaddingStartX() + stickWidth / 2;
//            for (int j = getDisplayFrom()+1; j < getDisplayTo(); j++) {
//                IMeasurable point = (IMeasurable)table.get(j-1);
//                IMeasurable nextpoint = (IMeasurable)table.get(j);
//                
//                Points lineMole = new Points();
//                lineMole.setUp(this,point.getHigh(),nextpoint.getHigh(),stickX,stickWidth);
//                lineMole.setLinePaint(mPaint);
//                lineMole.draw(canvas);
//
//                // next x
//                stickX = stickX + stickWidth;
//            }
//        }
//    }
//	   protected void drawAreas(Canvas canvas) {
//	        if (null == bandData) {
//	            return;
//	        }
//	        if (bandData.size() < 2 ) {
//	            return;
//	        }
//
//	        float stickWidth = dataQuadrant.getPaddingWidth() / getDisplayNumber();
//	        for(int i=1; i< bandData.size() ; i++){
//	            LineEntity table1 = (LineEntity)bandData.getChartTable(i-1);
//	            LineEntity table2 = (LineEntity)bandData.getChartTable(i);
//	            
//	            if (null == table1 || null == table2) {
//	                continue;
//	            }
//	            if(table1.size() == 0 || table2.size() == 0){
//	                continue;
//	            }
//	            
//	            Paint mPaint = new Paint();
//	            mPaint.setStyle(Style.FILL);
//	            mPaint.setColor(table1.getLineColor());
//	            mPaint.setAntiAlias(true);
//	            
//	            float stickX = dataQuadrant.getPaddingStartX() + stickWidth / 2;
//	            for (int j = getDisplayFrom()+1; j < getDisplayTo(); j++) {
//
//	                IMeasurable pointLow = (IMeasurable)table1.get(j-1);
//	                IMeasurable nextpointLow = (IMeasurable)table1.get(j);
//	                
//	                IMeasurable pointHigh = (IMeasurable)table2.get(j-1);
//	                IMeasurable nextpointHigh = (IMeasurable)table2.get(j);
//	                
//	                Area areaMole = new Area();
//	                areaMole.setUp(this,pointHigh.getHigh(),pointLow.getHigh(),nextpointHigh.getHigh(),nextpointLow.getHigh(),stickX,stickWidth);
//	                areaMole.setAreaPaint(mPaint);
//	                areaMole.draw(canvas);
//
//	                // next x
//	                stickX = stickX + stickWidth;
//	            }
//	        }
//	    }

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
//		if (null == bandData) {
//			return;
//		}
//		// distance between two points
//		float lineLength;
//		// start point‘s X
//		float startX;
//		float lastY = 0;
//		float lastX = 0;
//
//		LineEntity<DateValueEntity> line1 = (LineEntity<DateValueEntity>) bandData
//				.get(0);
//		LineEntity<DateValueEntity> line2 = (LineEntity<DateValueEntity>) bandData
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
//		// set start point’s X
//		if (gridAlignType == IFlexableGrid.ALIGN_TYPE_CENTER) {
//            lineLength= (dataQuadrant.getPaddingWidth() / dataCursor.getDisplayNumber()) - barSpacing;
//            startX = dataQuadrant.getPaddingStartX() + lineLength / 2;
//        }else {
//            lineLength= (dataQuadrant.getPaddingWidth() / (dataCursor.getDisplayNumber() - 1)) - barSpacing;
//            startX = dataQuadrant.getPaddingStartX();
//        }
//		Path areaPath = new Path();
//		for (int j = dataCursor.getDisplayFrom(); j < dataCursor.getDisplayFrom() + dataCursor.getDisplayNumber(); j++) {
//			float value1 = (Float)line1Data.get(j).getValue();
//			float value2 = (Float)line2Data.get(j).getValue();
//
//			// calculate Y
//			float valueY1 = (float) ((1f - (value1 - dataRange.getMinValue())
//                    / (dataRange.getValueRange())) * dataQuadrant.getPaddingHeight())
//                    + dataQuadrant.getPaddingStartY();
//			float valueY2 = (float) ((1f - (value2 - dataRange.getMinValue())
//                    / (dataRange.getValueRange())) * dataQuadrant.getPaddingHeight())
//                    + dataQuadrant.getPaddingStartY();
//
//			// 绘制线条路径
//			if (j == dataCursor.getDisplayFrom()) {
//				areaPath.moveTo(startX, valueY1);
//				areaPath.lineTo(startX, valueY2);
//				areaPath.moveTo(startX, valueY1);
//			} else {
//				areaPath.lineTo(startX, valueY1);
//				areaPath.lineTo(startX, valueY2);
//				areaPath.lineTo(lastX, lastY);
//
//				areaPath.close();
//				areaPath.moveTo(startX, valueY1);
//			}
//
//			lastX = startX;
//			lastY = valueY2;
//			startX = startX + barSpacing + lineLength;
//		}
//		areaPath.close();
//		canvas.drawPath(areaPath, mPaint);
//	}
//
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
//	protected void drawBandBorder(Canvas canvas) {
//
//		if (null == this.bandData) {
//			return;
//		}
//
//		if (bandData.size() <= 0) {
//			return;
//		}
//		// distance between two points
//		float lineLength = dataQuadrant.getPaddingWidth() / dataCursor.getDisplayNumber() - barSpacing;
//		// start point‘s X
//		float startX;
//
//		// draw lines
//		for (int i = 0; i < bandData.size(); i++) {
//			LineEntity<DateValueEntity> line = (LineEntity<DateValueEntity>) bandData
//					.get(i);
//			if (line == null) {
//				continue;
//			}
//			if (line.isDisplay() == false) {
//				continue;
//			}
//			List<DateValueEntity> lineData = line.getLineData();
//			if (lineData == null) {
//				continue;
//			}
//			Paint mPaint = new Paint();
//			mPaint.setColor(line.getLineColor());
//			mPaint.setAntiAlias(true);
//			// set start point’s X
//			startX = dataQuadrant.getPaddingStartX() + lineLength / 2;
//			// start point
//			PointF ptFirst = null;
//			for (int j = dataCursor.getDisplayFrom(); j < dataCursor.getDisplayFrom() + dataCursor.getDisplayNumber(); j++) {
//				float value = (Float)lineData.get(j).getValue();
//				// calculate Y
//				float valueY = (float) ((1f - (value - dataRange.getMinValue())
//						/ (dataRange.getValueRange())) * dataQuadrant.getPaddingHeight())
//						+ dataQuadrant.getPaddingStartY();
//
//				// if is not last point connect to previous point
//				if (j > dataCursor.getDisplayFrom()) {
//					canvas.drawLine(ptFirst.x, ptFirst.y, startX, valueY,
//							mPaint);
//				}
//				// reset
//				ptFirst = new PointF(startX, valueY);
//				startX = startX + barSpacing + lineLength;
//			}
//		}
//	}

	/**
	 * @return the bandData
	 */
	public ChartDataSet getBandData() {
		return bandData;
	}

	/**
	 * @param bandData
	 *            the bandData to set
	 */
	public void setBandData(ChartDataSet bandData) {
		this.bandData = bandData;
	}

}
