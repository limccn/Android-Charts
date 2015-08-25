/*
 * MASlipCandleStickChart.java
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

import cn.limc.androidcharts.series.ChartDataSet;
import cn.limc.androidcharts.series.ChartDataTable;
import cn.limc.androidcharts.series.DateValueEntity;
import cn.limc.androidcharts.series.IMeasurable;
import cn.limc.androidcharts.series.LineEntity;
import cn.limc.androidcharts.shape.Points;
import cn.limc.androidcharts.shape.Bar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
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
 * @version v1.0 2014/01/21 12:03:25
 * 
 */
public class MASlipCandleStickChart extends SlipCandleStickChart {

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

	/**
	 * <p>
	 * Constructor of MASlipCandleStickChart
	 * </p>
	 * <p>
	 * MASlipCandleStickChart类对象的构造函数
	 * </p>
	 * <p>
	 * MASlipCandleStickChartのコンストラクター
	 * </p>
	 * 
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public MASlipCandleStickChart(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	/**
	 * <p>
	 * Constructor of MASlipCandleStickChart
	 * </p>
	 * <p>
	 * MASlipCandleStickChart类对象的构造函数
	 * </p>
	 * <p>
	 * MASlipCandleStickChartのコンストラクター
	 * </p>
	 * 
	 * @param context
	 * @param attrs
	 */
	public MASlipCandleStickChart(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	/**
	 * <p>
	 * Constructor of MASlipCandleStickChart
	 * </p>
	 * <p>
	 * MASlipCandleStickChart类对象的构造函数
	 * </p>
	 * <p>
	 * MASlipCandleStickChartのコンストラクター
	 * </p>
	 * 
	 * @param context
	 */
	public MASlipCandleStickChart(Context context) {
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
//		for (int i = 0; i < this.linesData.size(); i++) {
//			LineEntity<DateValueEntity> line = this.linesData.get(i);
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

	
	   @Override
	    protected void onDraw(Canvas canvas) {
	        super.onDraw(canvas);
//	        drawLines(canvas);
	    }
//	    
//	     protected void drawLines(Canvas canvas) {
//	            if (null == linesData) {
//	                return;
//	            }
//	            if (linesData.size() == 0) {
//	                return;
//	            }
//
//	            float stickWidth = dataQuadrant.getPaddingWidth() / getDisplayNumber();
//	           
//	            for(int i=0; i< linesData.size() ; i++){
//	                LineEntity table = (LineEntity)linesData.getChartTable(i);
//	                if (null == table) {
//	                    continue;
//	                }
//	                if(table.size() == 0){
//	                    continue;
//	                }
//	                
//	                Paint mPaint = new Paint();
//	                mPaint.setColor(table.getLineColor());
//	                mPaint.setAntiAlias(true);
//	                float stickX = dataQuadrant.getPaddingStartX() + stickWidth / 2;
//	                for (int j = getDisplayFrom()+1; j < getDisplayTo(); j++) {
//	                    IMeasurable point = (IMeasurable)table.get(j-1);
//	                    IMeasurable nextpoint = (IMeasurable)table.get(j);
//	                    
//	                    Points lineMole = new Points();
//	                    lineMole.setUp(this,point.getHigh(),nextpoint.getHigh(),stickX,stickWidth);
//	                    lineMole.setLinePaint(mPaint);
//	                    lineMole.draw(canvas);
//
//	                    // next x
//	                    stickX = stickX + stickWidth;
//	                }
//	            }
//	        }
//	     
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
