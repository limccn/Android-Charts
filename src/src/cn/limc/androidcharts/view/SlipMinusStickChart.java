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

import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;

import cn.limc.androidcharts.entity.StickEntity;

/** 
 * <p>en</p>
 * <p>jp</p>
 * <p>cn</p>
 *
 * @author limc 
 * @version v1.0 2014/01/21 14:05:50 
 *  
 */
public class SlipMinusStickChart extends SlipStickChart {

public static final int DEFAULT_STICK_SPACING = 6;
	
	private int stickSpacing = DEFAULT_STICK_SPACING;

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
		// stick width
		float stickWidth = ((super.getWidth() - super.getAxisMarginLeft()) / super
				.getDisplayNumber()) - stickSpacing;
		// start point's X
		float stickX = super.getAxisMarginLeft() + stickSpacing / 2;

		Paint mPaintFill = new Paint();
		mPaintFill.setStyle(Style.FILL);
		mPaintFill.setColor(super.getStickFillColor());

		Paint mPaintBorder = new Paint();
		mPaintBorder.setStyle(Style.STROKE);
		mPaintBorder.setStrokeWidth(2);
		mPaintBorder.setColor(super.getStickBorderColor());

		List<StickEntity> data = super.getStickData();

		if (null != data) {
			// display as stick or line
			for (int i = super
					.getDisplayFrom(); i < super
					.getDisplayFrom() + super
					.getDisplayNumber(); i++) {
				StickEntity e = data.get(i);

				float highY = (float) ((1f - (e.getHigh() - super.minValue)
						/ (maxValue - minValue))
						* (super.getHeight() - super.getAxisMarginBottom()) - super
						.getAxisMarginTop());
				float lowY = (float) ((1f - (e.getLow() - minValue)
						/ (maxValue - minValue))
						* (super.getHeight() - super.getAxisMarginBottom()) - super
						.getAxisMarginTop());

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

	/**
	 * @return the stickSpacing
	 */
	public int getStickSpacing() {
		return stickSpacing;
	}

	/**
	 * @param stickSpacing the stickSpacing to set
	 */
	public void setStickSpacing(int stickSpacing) {
		this.stickSpacing = stickSpacing;
	}
	
}
