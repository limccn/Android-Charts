/*
 * ColoredSlipStickChart.java
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

import cn.limc.androidcharts.entity.ColoredStickEntity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
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
 * @version v1.0 2014/01/20 16:20:40
 * 
 */
public class ColoredSlipStickChart extends SlipStickChart {

	public static final int DEFAULT_COLORED_STICK_STYLE_WITH_BORDER = 0;
	public static final int DEFAULT_COLORED_STICK_STYLE_NO_BORDER = 1;
	public static final int DEFAULT_COLORED_STICK_STYLE = DEFAULT_COLORED_STICK_STYLE_NO_BORDER;

	private int coloredStickStyle = DEFAULT_COLORED_STICK_STYLE_NO_BORDER;

	/**
	 * <p>
	 * Constructor of SlipStickChart
	 * </p>
	 * <p>
	 * SlipStickChart类对象的构造函数
	 * </p>
	 * <p>
	 * SlipStickChartのコンストラクター
	 * </p>
	 * 
	 * @param context
	 */
	public ColoredSlipStickChart(Context context) {
		super(context);
	}

	/**
	 * <p>
	 * Constructor of SlipStickChart
	 * </p>
	 * <p>
	 * SlipStickChart类对象的构造函数
	 * </p>
	 * <p>
	 * SlipStickChartのコンストラクター
	 * </p>
	 * 
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public ColoredSlipStickChart(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	/**
	 * <p>
	 * Constructor of SlipStickChart
	 * </p>
	 * <p>
	 * SlipStickChart类对象的构造函数
	 * </p>
	 * <p>
	 * SlipStickChartのコンストラクター
	 * </p>
	 * 
	 * @param context
	 * @param attrs
	 */
	public ColoredSlipStickChart(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
	}

	@Override
	protected void drawSticks(Canvas canvas) {
		if (null == stickData) {
			return;
		}
		if (stickData.size() == 0) {
			return;
		}

		float stickWidth = dataQuadrant.getPaddingWidth() / displayNumber
				- stickSpacing;
		float stickX = dataQuadrant.getPaddingStartX();

		Paint mPaintStick = new Paint();
		for (int i = displayFrom; i < displayFrom + displayNumber; i++) {
			ColoredStickEntity entity = (ColoredStickEntity) stickData.get(i);

			float highY = (float) ((1f - (entity.getHigh() - minValue)
					/ (maxValue - minValue))
					* (dataQuadrant.getPaddingHeight()) + dataQuadrant.getPaddingStartY());
			float lowY = (float) ((1f - (entity.getLow() - minValue)
					/ (maxValue - minValue))
					* (dataQuadrant.getPaddingHeight()) + dataQuadrant.getPaddingStartY());

			mPaintStick.setColor(entity.getColor());
			// stick or line?
			if (stickWidth >= 2f) {
				canvas.drawRect(stickX, highY, stickX + stickWidth, lowY,
						mPaintStick);
			} else {
				canvas.drawLine(stickX, highY, stickX, lowY, mPaintStick);
			}

			// next x
			stickX = stickX + stickSpacing + stickWidth;
		}
	}

	/**
	 * @return the coloredStickStyle
	 */
	public int getColoredStickStyle() {
		return coloredStickStyle;
	}

	/**
	 * @param coloredStickStyle
	 *            the coloredStickStyle to set
	 */
	public void setColoredStickStyle(int coloredStickStyle) {
		this.coloredStickStyle = coloredStickStyle;
	}
}
