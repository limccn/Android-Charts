/*
 * MinusStickChart.java
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

import cn.limc.androidcharts.degree.MinusStickValueRangeCalc;
import cn.limc.androidcharts.entity.ChartDataTable;
import cn.limc.androidcharts.entity.IMeasurable;
import cn.limc.androidcharts.mole.StickMole;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

/**
 * 
 * <p>
 * MinusStickChart is inherits from StickChart which its data can be minus value
 * </p>
 * <p>
 * MinusStickChartはグラフの一種です、マイナーデータをチャートで表示は可能です
 * </p>
 * <p>
 * MinusStickChart继承于StickChart的，可以在StickChart基础上绘制包含负数的柱状条。
 * </p>
 * 
 * @author limc
 * @version v1.0 2011/05/30 15:11:43
 * 
 */
public class MinusStickChart extends StickChart {
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @param context
	 * 
	 * @see cn.limc.androidcharts.view.GridChart#GridChart(Context)
	 */
	public MinusStickChart(Context context) {
		super(context);
		this.dataRange = new MinusStickValueRangeCalc(this);
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
	 * @see cn.limc.androidcharts.view.GridChart#GridChart(Context,
	 * AttributeSet, int)
	 */
	public MinusStickChart(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		this.dataRange = new MinusStickValueRangeCalc(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @param context
	 * 
	 * @param attrs
	 * 
	 * @see cn.limc.androidcharts.view.GridChart#GridChart(Context,
	 * AttributeSet)
	 */
	public MinusStickChart(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.dataRange = new MinusStickValueRangeCalc(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @param canvas
	 * 
	 * 
	 * 
	 * @see cn.limc.androidcharts.view.StickChart#drawSticks(Canvas)
	 */
//	@Override
//	protected void drawSticks(Canvas canvas) {
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
//                StickMole mole = new  StickMole();
//                mole.setUp(this,stick,stickX,stickWidth);
//                mole.draw(canvas);
//
//                // next x
//                stickX = stickX + stickWidth;
//            }
//        }
//    }
}
