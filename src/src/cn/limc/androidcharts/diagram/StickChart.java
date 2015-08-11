/*
 * StickChart.java
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

import cn.limc.androidcharts.series.ChartDataTable;
import cn.limc.androidcharts.series.IMeasurable;
import cn.limc.androidcharts.shape.Bar;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

/**
 * <p>
 * StickChart is a kind of graph that draw the sticks on a GridChart if you want
 * display some moving average lines on this graph, please use see MAStickChart
 * for more information.
 * </p>
 * <p>
 * StickChartはGridChartの表面でスティックを書いたラインチャードです。移動平均線など
 * 分析線がお使いしたい場合、MAStickChartにご参照ください。
 * </p>
 * <p>
 * StickChart是在GridChart上绘制柱状数据的图表、如果需要支持显示均线，请参照 MAStickChart。
 * </p>
 * 
 * @author limc
 * @version v1.0 2011/05/30 14:58:59
 * 
 */
public class StickChart extends GridChart{	
	/*
	 * (non-Javadoc)
	 * 
	 * @param context
	 * 
	 * @see cn.limc.androidcharts.diagram.AbstractBaseChart#BaseChart(Context)
	 */
	public StickChart(Context context) {
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
	 * @see cn.limc.androidcharts.diagram.AbstractBaseChart#BaseChart(Context,
	 * AttributeSet, int)
	 */
	public StickChart(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @param context
	 * 
	 * @param attrs
	 * 
	 * @see cn.limc.androidcharts.diagram.AbstractBaseChart#BaseChart(Context,
	 * AttributeSet)
	 */
	public StickChart(Context context, AttributeSet attrs) {
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
//	@Override
//	protected void onDraw(Canvas canvas) {
//		if (this.dataRange.isAutoCalcValueRange()) {
//		    this.dataRange.calcValueRange();
//		}
////		initAxisY();
////		initAxisX();
//		super.onDraw(canvas);
//		//drawSticks(canvas);
//	}

	/**
	 * <p>
	 * draw sticks
	 * </p>
	 * <p>
	 * スティックを書く
	 * </p>
	 * <p>
	 * 绘制柱条
	 * </p>
	 * 
	 * @param canvas
	 */
//	protected void drawSticks(Canvas canvas) {
//		if (null == stickData) {
//			return;
//		}
//		if (stickData.size() <= 0) {
//			return;
//		}
//
//		float stickWidth = dataQuadrant.getPaddingWidth() / getDisplayNumber();
//
//		if (axisY.getPosition() == Axis.AXIS_Y_POSITION_LEFT) {
//
//			float stickX = dataQuadrant.getPaddingStartX();
//
//			for (int i = 0; i < stickData.size(); i++) {
//				IMeasurable stick = stickData.get(i);
//				Bar mole = new Bar();
//				mole.setUp(this,stick,stickX,stickWidth);
//				mole.draw(canvas);
//				// next x
//				stickX = stickX + stickWidth;
//			}
//		} else {
//			float stickX = dataQuadrant.getPaddingEndX() - stickWidth;
//			for (int i = stickData.size() - 1; i >= 0; i--) {
//				IMeasurable stick = stickData.get(i);
//				Bar mole = new Bar();
//				mole.setUp(this,stick,stickX,stickWidth);
//				mole.draw(canvas);
//				// next x
//				stickX = stickX - stickWidth;
//			}
//		}
//	}
	
//    protected void drawSticks(Canvas canvas) {
//        if (null == chartData) {
//            return;
//        }
//        if (chartData.size() == 0) {
//            return;
//        }
//
//        float stickWidth = dataQuadrant.getPaddingWidth() / getDisplayNumber();
//        float stickX = dataQuadrant.getPaddingStartX();
//
//        for(int i=0; i< chartData.size() ; i++){
//            ChartDataTable table = chartData.getChartTable(i);
//            if (null == table) {
//                continue;
//            }
//            if(table.size() == 0){
//                continue;
//            }
//            for (int j = getDisplayFrom(); j < getDisplayTo(); j++) {
//                IMeasurable stick = (IMeasurable)table.get(j);
//                
//                Bar mole = new  Bar();
//                mole.setUp(this,stick,stickX,stickWidth);
//                mole.draw(canvas);
//
//                // next x
//                stickX = stickX + stickWidth;
//            }
//        }
//    }
}
