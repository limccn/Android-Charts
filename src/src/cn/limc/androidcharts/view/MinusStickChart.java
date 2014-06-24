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
	@Override
	protected void calcDataValueRange() {

		double maxValue = -Double.MAX_VALUE;
		double minValue = Double.MAX_VALUE;

		IMeasurable first = this.stickData.get(0);
		// 第一个stick为停盘的情况
		if (first.getHigh() == 0 && first.getLow() == 0) {

		} else {
			maxValue = first.getHigh();
			minValue = first.getLow();
		}

		// 判断显示为方柱或显示为线条
		for (int i = 0; i < this.stickData.size(); i++) {
			IMeasurable stick = this.stickData.get(i);
			if (stick.getLow() < minValue) {
				minValue = stick.getLow();
			}

			if (stick.getHigh() > maxValue) {
				maxValue = stick.getHigh();
			}

		}

		this.maxValue = maxValue;
		this.minValue = minValue;
	}
	
	@Override
	protected void calcValueRangePaddingZero(){
		
	}
	
	@Override
	protected void calcValueRangeFormatForAxis() {
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @param context
	 * 
	 * @see cn.limc.androidcharts.view.GridChart#GridChart(Context)
	 */
	public MinusStickChart(Context context) {
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
	 * @see cn.limc.androidcharts.view.GridChart#GridChart(Context,
	 * AttributeSet, int)
	 */
	public MinusStickChart(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
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
	@Override
	protected void drawSticks(Canvas canvas) {
		if (null == stickData) {
			return;
		}
		if (stickData.size() <= 0) {
			return;
		}

		float stickWidth = dataQuadrant.getQuadrantPaddingWidth() / maxSticksNum
				- stickSpacing;

		if (axisYPosition == AXIS_Y_POSITION_LEFT) {

			float stickX = dataQuadrant.getQuadrantPaddingStartX();
			for (int i = 0; i < stickData.size(); i++) {
				IMeasurable stick = stickData.get(i);
				StickMole mole = (StickMole)provider.getMole();
				mole.setUp(this,stick,stickX,stickWidth);
				mole.draw(canvas);
				// next x
				stickX = stickX + stickSpacing + stickWidth;
			}
		} else {
			float stickX = dataQuadrant.getQuadrantPaddingEndX() - stickWidth;
			for (int i = stickData.size() - 1; i >= 0; i--) {
				IMeasurable stick = stickData.get(i);
				StickMole mole = (StickMole)provider.getMole();
				mole.setUp(this,stick,stickX,stickWidth);
				mole.draw(canvas);
				// next x
				stickX = stickX - stickSpacing - stickWidth;
			}
		}
	}
}
