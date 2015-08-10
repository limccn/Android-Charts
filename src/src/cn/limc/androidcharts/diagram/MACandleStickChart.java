/*
 * MACandleStickChart.java
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

package cn.limc.androidcharts.diagram;

import java.util.List;

import cn.limc.androidcharts.component.Axis;
import cn.limc.androidcharts.series.ChartDataSet;
import cn.limc.androidcharts.series.ChartDataTable;
import cn.limc.androidcharts.series.DateValueEntity;
import cn.limc.androidcharts.series.IMeasurable;
import cn.limc.androidcharts.series.LineEntity;
import cn.limc.androidcharts.shape.Points;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;

/**
 * 
 * <p>
 * MACandleStickChart is inherits from CandleStickChart which can display moving
 * average lines on this graph.
 * </p>
 * <p>
 * MACandleStickChartはグラフの一種です、移動平均線など分析線がこのグラフで表示は可能です。
 * </p>
 * <p>
 * MACandleStickChart继承于CandleStickChart的，可以在CandleStickChart基础上
 * 显示移动平均等各种分析指标数据。
 * </p>
 * 
 * @author limc
 * @version v1.0 2011/05/30 14:49:02
 * @see CandleStickChart
 * @see StickChart
 * 
 */
public class MACandleStickChart extends CandleStickChart {

	/**
	 * <p>
	 * mData to draw lines
	 * </p>
	 * <p>
	 * ラインを書く用データ
	 * </p>
	 * <p>
	 * 绘制线条用的数据
	 * </p>
	 */
	private ChartDataSet linesData;

	/*
	 * (non-Javadoc)
	 * 
	 * @param context
	 * 
	 * @see cn.limc.androidcharts.diagram.GridChart#GridChart(Context)
	 */
	public MACandleStickChart(Context context) {
		super(context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @param context
	 * 
	 * @param attrs
	 * 
	 * @param defStyle
	 * 
	 * @see cn.limc.androidcharts.diagram.GridChart#GridChart(Context,
	 * AttributeSet, int)
	 */
	public MACandleStickChart(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @param context
	 * 
	 * @param attrs
	 * 
	 * 
	 * 
	 * @see cn.limc.androidcharts.diagram.GridChart#GridChart(Context,
	 * AttributeSet)
	 */
	public MACandleStickChart(Context context, AttributeSet attrs) {
		super(context, attrs);
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
//        drawLines(canvas);
    }
    
//     protected void drawLines(Canvas canvas) {
//            if (null == linesData) {
//                return;
//            }
//            if (linesData.size() == 0) {
//                return;
//            }
//
//            float stickWidth = dataQuadrant.getPaddingWidth() / getDisplayNumber();
//
//            for(int i=0; i< linesData.size() ; i++){
//                LineEntity table = (LineEntity)linesData.getChartTable(i);
//                if (null == table) {
//                    continue;
//                }
//                if(table.size() == 0){
//                    continue;
//                }
//                
//                Paint mPaint = new Paint();
//                mPaint.setColor(table.getLineColor());
//                mPaint.setAntiAlias(true);
//                float stickX = dataQuadrant.getPaddingStartX() + stickWidth / 2;
//                for (int j = getDisplayFrom()+1; j < getDisplayTo(); j++) {
//                    IMeasurable point = (IMeasurable)table.get(j-1);
//                    IMeasurable nextpoint = (IMeasurable)table.get(j);
//                    
//                    Points lineMole = new Points();
//                    lineMole.setUp(this,point.getHigh(),nextpoint.getHigh(),stickX,stickWidth);
//                    lineMole.setLinePaint(mPaint);
//                    lineMole.draw(canvas);
//
//                    // next x
//                    stickX = stickX + stickWidth;
//                }
//            }
//        }
     
//	@Override
//	protected void onDraw(Canvas canvas) {
//		super.onDraw(canvas);
//
//		// draw lines
//		if (null != this.linesData) {
//			if (0 != this.linesData.size()) {
//				drawLines(canvas);
//			}
//		}
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
//	protected void drawLines(Canvas canvas) {
//		if (null == this.linesData) {
//			return;
//		}
//		// distance between two points
//		float lineLength = dataQuadrant.getPaddingWidth() / dataCursor.getDisplayNumber() - barSpacing;
//		// start point‘s X
//		float startX;
//
//		// draw MA lines
//		for (int i = 0; i < linesData.size(); i++) {
//			LineEntity<DateValueEntity> line = (LineEntity<DateValueEntity>) linesData
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
//
//			Paint mPaint = new Paint();
//			mPaint.setColor(line.getLineColor());
//			mPaint.setAntiAlias(true);
//
//			// start point
//			PointF ptFirst = null;
//			if (axisY.getPosition() == Axis.AXIS_Y_POSITION_LEFT) {
//				// set start point’s X
//				startX = dataQuadrant.getPaddingStartX() + lineLength / 2;
//				for (int j = 0; j < lineData.size(); j++) {
//					float value = lineData.get(j).getValue();
//					// calculate Y
//					float valueY = (float) ((1f - (value - dataRange.getMinValue())
//	                        / (dataRange.getValueRange())) * dataQuadrant.getPaddingHeight())
//	                        + dataQuadrant.getPaddingStartY();
//
//					// if is not last point connect to previous point
//					if (j > 0) {
//						canvas.drawLine(ptFirst.x, ptFirst.y, startX, valueY,
//								mPaint);
//					}
//					// reset
//					ptFirst = new PointF(startX, valueY);
//					startX = startX + barSpacing + lineLength;
//				}
//			} else {
//				// set start point’s X
//				startX = dataQuadrant.getPaddingEndX() - lineLength / 2;
//				for (int j = lineData.size() - 1; j >= 0; j--) {
//					float value = lineData.get(j).getValue();
//					// calculate Y
//					float valueY = (float) ((1f - (value - dataRange.getMinValue())
//	                        / (dataRange.getValueRange())) * dataQuadrant.getPaddingHeight())
//	                        + dataQuadrant.getPaddingStartY();
//
//					// if is not last point connect to previous point
//					if (j < lineData.size() - 1) {
//						canvas.drawLine(ptFirst.x, ptFirst.y, startX, valueY,
//								mPaint);
//					}
//					// reset
//					ptFirst = new PointF(startX, valueY);
//					startX = startX - barSpacing - lineLength;
//				}
//			}
//		}
//	}

	/**
	 * @return the linesData
	 */
	public ChartDataSet getLinesData() {
		return linesData;
	}

	/**
	 * @param linesData
	 *            the linesData to set
	 */
	public void setLinesData(ChartDataSet linesData) {
		this.linesData = linesData;
	}
}
