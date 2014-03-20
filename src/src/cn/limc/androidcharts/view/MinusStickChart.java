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

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
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

	public static final int DEFAULT_STICK_SPACING = 6;

	private int stickSpacing = DEFAULT_STICK_SPACING;

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

	// @Override
	// protected void calcDataValueRange() {
	// }

	@Override
	protected void calcValueRangePaddingZero() {
	}

	@Override
	protected void calcValueRangeFormatForAxis() {
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

		Paint mPaintFill = new Paint();
		mPaintFill.setStyle(Style.FILL);
		mPaintFill.setColor(super.getStickFillColor());

		Paint mPaintBorder = new Paint();
		mPaintBorder.setStyle(Style.STROKE);
		mPaintBorder.setStrokeWidth(2);
		mPaintBorder.setColor(super.getStickBorderColor());

		float stickWidth = getDataQuadrantPaddingWidth() / maxSticksNum
				- stickSpacing;

		if (axisYPosition == AXIS_Y_POSITION_LEFT) {

			float stickX = getDataQuadrantPaddingStartX();
			for (int i = 0; i < stickData.size(); i++) {
				IMeasurable entity = stickData.get(i);

				float highY = (float) ((1f - (entity.getHigh() - minValue)
						/ (maxValue - minValue))
						* (getDataQuadrantPaddingHeight()) + getDataQuadrantPaddingStartY());
				float lowY = (float) ((1f - (entity.getLow() - minValue)
						/ (maxValue - minValue))
						* (getDataQuadrantPaddingHeight()) + getDataQuadrantPaddingStartY());

				// draw stick
				canvas.drawRect(stickX, highY, stickX + stickWidth, lowY,
						mPaintFill);
				canvas.drawRect(stickX, highY, stickX + stickWidth, lowY,
						mPaintBorder);

				// next x
				stickX = stickX + stickSpacing + stickWidth;
			}
		} else {

			float stickX = getDataQuadrantPaddingEndX() - stickWidth;
			for (int i = stickData.size() - 1; i >= 0; i--) {
				IMeasurable stick = stickData.get(i);

				float highY = (float) ((1f - (stick.getHigh() - minValue)
						/ (maxValue - minValue))
						* (getDataQuadrantPaddingHeight()) + getDataQuadrantPaddingStartY());
				float lowY = (float) ((1f - (stick.getLow() - minValue)
						/ (maxValue - minValue))
						* (getDataQuadrantPaddingHeight()) + getDataQuadrantPaddingStartY());

				// draw stick
				canvas.drawRect(stickX, highY, stickX + stickWidth, lowY,
						mPaintFill);
				canvas.drawRect(stickX, highY, stickX + stickWidth, lowY,
						mPaintBorder);

				// next x
				stickX = stickX - stickSpacing - stickWidth;
			}
		}
	}

	/**
	 * @return the stickSpacing
	 */
	public int getStickSpacing() {
		return stickSpacing;
	}

	/**
	 * @param stickSpacing
	 *            the stickSpacing to set
	 */
	public void setStickSpacing(int stickSpacing) {
		this.stickSpacing = stickSpacing;
	}
}
