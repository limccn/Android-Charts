/*
 * MACDChart.java
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

import cn.limc.androidcharts.entity.IMeasurable;
import cn.limc.androidcharts.entity.MACDEntity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
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
 * @version v1.0 2014/03/17 17:19:45
 * 
 */
public class MACDChart extends SlipStickChart {

	public static final int MACD_DISPLAY_TYPE_STICK = 1 << 0;
	public static final int MACD_DISPLAY_TYPE_LINE = 1 << 1;
	public static final int MACD_DISPLAY_TYPE_LINE_STICK = 1 << 2;

	public static final int DEFAULT_POSITIVE_STICK_COLOR = Color.RED;
	public static final int DEFAULT_NEGATIVE_STICK_COLOR = Color.BLUE;
	public static final int DEFAULT_MACD_LINE_COLOR = Color.RED;
	public static final int DEFAULT_DIFF_LINE_COLOR = Color.WHITE;
	public static final int DEFAULT_DEA_LINE_COLOR = Color.YELLOW;
	public static final int DEFAULT_MACD_DISPLAY_TYPE = MACD_DISPLAY_TYPE_LINE_STICK;

	private int positiveStickColor = DEFAULT_POSITIVE_STICK_COLOR;
	private int negativeStickColor = DEFAULT_NEGATIVE_STICK_COLOR;
	private int macdLineColor = DEFAULT_MACD_LINE_COLOR;
	private int diffLineColor = DEFAULT_DIFF_LINE_COLOR;
	private int deaLineColor = DEFAULT_DEA_LINE_COLOR;
	private int macdDisplayType = DEFAULT_MACD_DISPLAY_TYPE;

	/**
	 * <p>
	 * Constructor of MACDChart
	 * </p>
	 * <p>
	 * MACDChart类对象的构造函数
	 * </p>
	 * <p>
	 * MACDChartのコンストラクター
	 * </p>
	 * 
	 * @param context
	 */
	public MACDChart(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	/**
	 * <p>
	 * Constructor of MACDChart
	 * </p>
	 * <p>
	 * MACDChart类对象的构造函数
	 * </p>
	 * <p>
	 * MACDChartのコンストラクター
	 * </p>
	 * 
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public MACDChart(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	/**
	 * <p>
	 * Constructor of MACDChart
	 * </p>
	 * <p>
	 * MACDChart类对象的构造函数
	 * </p>
	 * <p>
	 * MACDChartのコンストラクター
	 * </p>
	 * 
	 * @param context
	 * @param attrs
	 */
	public MACDChart(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void calcValueRange() {
		if (stickData == null) {
			return;
		}
		if (stickData.size() <= 0) {
			return;
		}
		double maxValue = Double.MIN_VALUE;
		double minValue = Double.MAX_VALUE;

		IMeasurable first = stickData.get(displayFrom);
		maxValue = Math.max(first.getHigh(), maxValue);
		minValue = Math.min(first.getLow(), minValue);
		// 判断显示为方柱或显示为线条
		for (int i = displayFrom; i < displayFrom + displayNumber; i++) {
			IMeasurable macd = stickData.get(i);
			maxValue = Math.max(macd.getHigh(), maxValue);
			minValue = Math.min(macd.getLow(), minValue);
		}
		this.maxValue = maxValue;
		this.minValue = minValue;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		// 在K线图上增加均线
		drawLinesData(canvas);
	}

	@Override
	protected void drawSticks(Canvas canvas) {

		if (stickData == null) {
			return;
		}
		if (stickData.size() <= 0) {
			return;
		}

		if (macdDisplayType == MACD_DISPLAY_TYPE_LINE) {
			this.drawMacdLine(canvas);
			return;
		}

		Paint mPaintStick = new Paint();
		mPaintStick.setAntiAlias(true);

		float stickWidth = dataQuadrant.getQuadrantPaddingWidth() / displayNumber
				- stickSpacing;
		float stickX = dataQuadrant.getQuadrantPaddingStartX();

		// 判断显示为方柱或显示为线条
		for (int i = displayFrom; i < displayFrom + displayNumber; i++) {
			MACDEntity stick = (MACDEntity) stickData.get(i);

			float highY;
			float lowY;
			if (stick.getMacd() == 0) {
				// 没有值的情况下不绘制
				continue;
			}
			// 柱状线颜色设定
			if (stick.getMacd() > 0) {
				mPaintStick.setColor(positiveStickColor);
				highY = (float) ((1 - (stick.getMacd() - minValue)
						/ (maxValue - minValue))
						* (dataQuadrant.getQuadrantPaddingHeight()) + dataQuadrant.getQuadrantPaddingStartY());
				lowY = (float) ((1 - (0 - minValue) / (maxValue - minValue))
						* (dataQuadrant.getQuadrantPaddingHeight()) + dataQuadrant.getQuadrantPaddingStartY());

			} else {
				mPaintStick.setColor(negativeStickColor);
				highY = (float) ((1 - (0 - minValue) / (maxValue - minValue))
						* (dataQuadrant.getQuadrantPaddingHeight()) + dataQuadrant.getQuadrantPaddingStartY());

				lowY = (float) ((1 - (stick.getMacd() - minValue)
						/ (maxValue - minValue))
						* (dataQuadrant.getQuadrantPaddingHeight()) + dataQuadrant.getQuadrantPaddingStartY());

			}

			if (macdDisplayType == MACD_DISPLAY_TYPE_STICK) {
				// 绘制数据，根据宽度判断绘制直线或方柱
				if (stickWidth >= 2) {
					canvas.drawRect(stickX, highY, stickX + stickWidth, lowY,
							mPaintStick);
				} else {
					canvas.drawLine(stickX, highY, stickX, lowY, mPaintStick);
				}
			} else if (macdDisplayType == MACD_DISPLAY_TYPE_LINE_STICK) {
				canvas.drawLine(stickX + stickWidth / 2, highY, stickX
						+ stickWidth / 2, lowY, mPaintStick);
			}

			// X位移
			stickX = stickX + stickSpacing + stickWidth;
		}
	}

	protected void drawDiffLine(Canvas canvas) {

		if (null == this.stickData) {
			return;
		}
		Paint mPaintStick = new Paint();
		mPaintStick.setAntiAlias(true);
		mPaintStick.setColor(diffLineColor);

		// distance between two points
		float lineLength = dataQuadrant.getQuadrantPaddingWidth() / displayNumber - stickSpacing;
		// start point‘s X
		float startX = dataQuadrant.getQuadrantPaddingStartX() + lineLength / 2;
		// start point
		PointF ptFirst = null;
		for (int i = displayFrom; i < displayFrom + displayNumber; i++) {
			MACDEntity entity = (MACDEntity) stickData.get(i);
			// calculate Y
			float valueY = (float) ((1f - (entity.getDiff() - minValue)
					/ (maxValue - minValue)) * dataQuadrant.getQuadrantPaddingHeight())
					+ dataQuadrant.getQuadrantPaddingStartY();

			// if is not last point connect to previous point
			if (i > displayFrom) {
				canvas.drawLine(ptFirst.x, ptFirst.y, startX, valueY,
						mPaintStick);
			}
			// reset
			ptFirst = new PointF(startX, valueY);
			startX = startX + stickSpacing + lineLength;
		}
	}

	protected void drawDeaLine(Canvas canvas) {

		Paint mPaintStick = new Paint();
		mPaintStick.setAntiAlias(true);
		mPaintStick.setColor(deaLineColor);
		// distance between two points
		float lineLength = dataQuadrant.getQuadrantPaddingWidth() / displayNumber - stickSpacing;
		// set start point’s X
		float startX = dataQuadrant.getQuadrantPaddingStartX() + lineLength / 2;
		// start point
		PointF ptFirst = null;
		for (int i = displayFrom; i < displayFrom + displayNumber; i++) {
			MACDEntity entity = (MACDEntity) stickData.get(i);
			// calculate Y
			float valueY = (float) ((1f - (entity.getDea() - minValue)
					/ (maxValue - minValue)) * dataQuadrant.getQuadrantPaddingHeight())
					+ dataQuadrant.getQuadrantPaddingStartY();

			// if is not last point connect to previous point
			if (i > displayFrom) {
				canvas.drawLine(ptFirst.x, ptFirst.y, startX, valueY,
						mPaintStick);
			}
			// reset
			ptFirst = new PointF(startX, valueY);
			startX = startX + stickSpacing + lineLength;
		}
	}

	protected void drawMacdLine(Canvas canvas) {
		Paint mPaintStick = new Paint();
		mPaintStick.setAntiAlias(true);
		mPaintStick.setColor(macdLineColor);

		// distance between two points
		float lineLength = dataQuadrant.getQuadrantPaddingWidth() / displayNumber - stickSpacing;
		// set start point’s X
		float startX = dataQuadrant.getQuadrantPaddingStartX() + lineLength / 2;
		// start point
		PointF ptFirst = null;
		for (int i = displayFrom; i < displayFrom + displayNumber; i++) {
			MACDEntity entity = (MACDEntity) stickData.get(i);
			// calculate Y
			float valueY = (float) ((1f - (entity.getMacd() - minValue)
					/ (maxValue - minValue)) * dataQuadrant.getQuadrantPaddingHeight())
					+ dataQuadrant.getQuadrantPaddingStartY();

			// if is not last point connect to previous point
			if (i > displayFrom) {
				canvas.drawLine(ptFirst.x, ptFirst.y, startX, valueY,
						mPaintStick);
			}
			// reset
			ptFirst = new PointF(startX, valueY);
			startX = startX + stickSpacing + lineLength;
		}
	}

	protected void drawLinesData(Canvas canvas) {

		if (stickData == null) {
			return;
		}
		if (stickData.size() <= 0) {
			return;
		}

		drawDeaLine(canvas);
		drawDiffLine(canvas);
	}

	/**
	 * @return the positiveStickColor
	 */
	public int getPositiveStickColor() {
		return positiveStickColor;
	}

	/**
	 * @param positiveStickColor
	 *            the positiveStickColor to set
	 */
	public void setPositiveStickColor(int positiveStickColor) {
		this.positiveStickColor = positiveStickColor;
	}

	/**
	 * @return the negativeStickColor
	 */
	public int getNegativeStickColor() {
		return negativeStickColor;
	}

	/**
	 * @param negativeStickColor
	 *            the negativeStickColor to set
	 */
	public void setNegativeStickColor(int negativeStickColor) {
		this.negativeStickColor = negativeStickColor;
	}

	/**
	 * @return the macdLineColor
	 */
	public int getMacdLineColor() {
		return macdLineColor;
	}

	/**
	 * @param macdLineColor
	 *            the macdLineColor to set
	 */
	public void setMacdLineColor(int macdLineColor) {
		this.macdLineColor = macdLineColor;
	}

	/**
	 * @return the diffLineColor
	 */
	public int getDiffLineColor() {
		return diffLineColor;
	}

	/**
	 * @param diffLineColor
	 *            the diffLineColor to set
	 */
	public void setDiffLineColor(int diffLineColor) {
		this.diffLineColor = diffLineColor;
	}

	/**
	 * @return the deaLineColor
	 */
	public int getDeaLineColor() {
		return deaLineColor;
	}

	/**
	 * @param deaLineColor
	 *            the deaLineColor to set
	 */
	public void setDeaLineColor(int deaLineColor) {
		this.deaLineColor = deaLineColor;
	}

	/**
	 * @return the macdDisplayType
	 */
	public int getMacdDisplayType() {
		return macdDisplayType;
	}

	/**
	 * @param macdDisplayType
	 *            the macdDisplayType to set
	 */
	public void setMacdDisplayType(int macdDisplayType) {
		this.macdDisplayType = macdDisplayType;
	}

}
