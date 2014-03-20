/*
 * SlipMinusStickChart.java
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

package cn.limc.androidcharts.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;

import cn.limc.androidcharts.entity.IMeasurable;

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
 * @version v1.0 2014/01/21 14:05:50
 * 
 */
public class SlipMinusStickChart extends SlipStickChart {

	/*
	 * (non-Javadoc)
	 * 
	 * @param context
	 * 
	 * @see cn.limc.androidcharts.view.GridChart#GridChart(Context)
	 */
	public SlipMinusStickChart(Context context) {
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
	public SlipMinusStickChart(Context context, AttributeSet attrs, int defStyle) {
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
	public SlipMinusStickChart(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void calcDataValueRange() {

		double maxValue = Integer.MIN_VALUE;
		double minValue = Integer.MAX_VALUE;

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

		float stickWidth = getDataQuadrantPaddingWidth() / displayNumber
				- stickSpacing;
		float stickX = getDataQuadrantPaddingStartX();

		Paint mPaintFill = new Paint();
		mPaintFill.setStyle(Style.FILL);
		mPaintFill.setColor(super.getStickFillColor());

		Paint mPaintBorder = new Paint();
		mPaintBorder.setStyle(Style.STROKE);
		mPaintBorder.setStrokeWidth(2);
		mPaintBorder.setColor(super.getStickBorderColor());

		if (null != stickData) {
			// display as stick or line
			for (int i = super.getDisplayFrom(); i < super.getDisplayFrom()
					+ super.getDisplayNumber(); i++) {
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
		}
	}
}
