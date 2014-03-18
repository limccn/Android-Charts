/*
 * CandleStickChart.java
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
import cn.limc.androidcharts.entity.OHLCEntity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.view.MotionEvent;

/**
 * 
 * <p>
 * CandleStickChart is a kind of graph that draw the OHLCs on a GridChart if you
 * want display some moving average lines on this graph, please use see
 * MACandleStickChart for more information
 * </p>
 * <p>
 * CandleStickChartはGridChartの表面でロウソクを書いたラインチャードです。移動平均線など
 * 分析線がお使いしたい場合、MACandleStickChartにご参照ください。
 * </p>
 * <p>
 * CandleStickChart是在GridChart上绘制K线（蜡烛线）的图表、如果需要支持显示均线，请参照 MACandleStickChart。
 * </p>
 * 
 * @author limc
 * @version v1.0 2011/05/30 16:29:41
 * @see CandleStickChart
 * @see MACandleStickChart
 * 
 */
public class CandleStickChart extends StickChart {
	/**
	 * <p>
	 * Default price up stick's border color
	 * </p>
	 * <p>
	 * 値上がりローソクのボーダー色のデフォルト値
	 * </p>
	 * <p>
	 * 默认阳线的边框颜色
	 * </p>
	 */
	public static final int DEFAULT_POSITIVE_STICK_BORDER_COLOR = Color.RED;

	/**
	 * <p>
	 * Default price up stick's fill color
	 * </p>
	 * <p>
	 * 値上がりローソクの色のデフォルト値
	 * </p>
	 * <p>
	 * 默认阳线的填充颜色
	 * </p>
	 */
	public static final int DEFAULT_POSITIVE_STICK_FILL_COLOR = Color.RED;

	/**
	 * <p>
	 * Default price down stick's border color
	 * </p>
	 * <p>
	 * 値下りローソクのボーダー色のデフォルト値
	 * </p>
	 * <p>
	 * 默认阴线的边框颜色
	 * </p>
	 */
	public static final int DEFAULT_NEGATIVE_STICK_BORDER_COLOR = Color.GREEN;

	/**
	 * <p>
	 * Default price down stick's fill color
	 * </p>
	 * <p>
	 * 値下りローソクの色のデフォルト値
	 * </p>
	 * <p>
	 * 默认阴线的填充颜色
	 * </p>
	 */
	public static final int DEFAULT_NEGATIVE_STICK_FILL_COLOR = Color.GREEN;

	/**
	 * <p>
	 * Default price no change stick's color (cross-star,T-like etc.)
	 * </p>
	 * <p>
	 * クローススターの色のデフォルト値
	 * </p>
	 * <p>
	 * 默认十字线显示颜色
	 * </p>
	 */
	public static final int DEFAULT_CROSS_STAR_COLOR = Color.LTGRAY;

	/**
	 * <p>
	 * Price up stick's border color
	 * </p>
	 * <p>
	 * 値上がりローソクのボーダー色
	 * </p>
	 * <p>
	 * 阳线的边框颜色
	 * </p>
	 */
	private int positiveStickBorderColor = DEFAULT_POSITIVE_STICK_BORDER_COLOR;

	/**
	 * <p>
	 * Price up stick's fill color
	 * </p>
	 * <p>
	 * 値上がりローソクの色
	 * </p>
	 * <p>
	 * 阳线的填充颜色
	 * </p>
	 */
	private int positiveStickFillColor = DEFAULT_POSITIVE_STICK_FILL_COLOR;

	/**
	 * <p>
	 * Price down stick's border color
	 * </p>
	 * <p>
	 * 値下りローソクのボーダー色
	 * </p>
	 * <p>
	 * 阴线的边框颜色
	 * </p>
	 */

	private int negativeStickBorderColor = DEFAULT_NEGATIVE_STICK_BORDER_COLOR;

	/**
	 * <p>
	 * Price down stick's fill color
	 * </p>
	 * <p>
	 * 値下りローソクの色
	 * </p>
	 * <p>
	 * 阴线的填充颜色
	 * </p>
	 */
	private int negativeStickFillColor = DEFAULT_NEGATIVE_STICK_FILL_COLOR;

	/**
	 * <p>
	 * Price no change stick's color (cross-star,T-like etc.)
	 * </p>
	 * <p>
	 * クローススターの色（価格変動無し）
	 * </p>
	 * <p>
	 * 十字线显示颜色（十字星,一字平线,T形线的情况）
	 * </p>
	 */
	private int crossStarColor = DEFAULT_CROSS_STAR_COLOR;

	/*
	 * (non-Javadoc)
	 * 
	 * @param context
	 * 
	 * @see cn.limc.androidcharts.view.BaseChart#BaseChart(Context)
	 */
	public CandleStickChart(Context context) {
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
	 * @see cn.limc.androidcharts.view.BaseChart#BaseChart(Context,
	 * AttributeSet, int)
	 */
	public CandleStickChart(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @param context
	 * 
	 * @param attrs
	 * 
	 * @see cn.limc.androidcharts.view.BaseChart#BaseChart(Context,
	 * AttributeSet)
	 */
	public CandleStickChart(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	protected void calcDataValueRange() {
		double maxValue = 0;
		double minValue = Integer.MAX_VALUE;

		IMeasurable first = this.stickData.get(0);

		// 第一个stick为停盘的情况
		if (first.getHigh() == 0 && first.getLow() == 0) {

		} else {
			// max取最小，min取最大
			maxValue = first.getHigh();
			minValue = first.getLow();
		}

		for (int i = 0; i < this.maxSticksNum; i++) {
			OHLCEntity stick = (OHLCEntity) this.stickData.get(i);
			if (stick.getOpen() == 0 && stick.getHigh() == 0
					&& stick.getLow() == 0) {
				// 停盘期间计算收盘价
				if (stick.getClose() > 0) {
					if (stick.getClose() < minValue) {
						minValue = stick.getClose();
					}

					if (stick.getClose() > maxValue) {
						maxValue = stick.getClose();
					}
				}
			} else {
				if (stick.getLow() < minValue) {
					minValue = stick.getLow();
				}

				if (stick.getLow() > maxValue) {
					maxValue = stick.getLow();
				}
			}
		}

		this.maxValue = maxValue;
		this.minValue = minValue;
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
	}

	// /**
	// * <p>
	// * initialize degrees on Y axis
	// * </p>
	// * <p>
	// * Y軸の目盛を初期化
	// * </p>
	// * <p>
	// * 初始化Y轴的坐标值
	// * </p>
	// */
	// protected void initAxisX() {
	// List<String> TitleX = new ArrayList<String>();
	// if (null != stickData) {
	// float average = maxSticksNum / this.getLongitudeNum();
	// // �?��刻度
	// for (int i = 0; i < this.getLongitudeNum(); i++) {
	// int index = (int) Math.floor(i * average);
	// if (index > maxSticksNum - 1) {
	// index = maxSticksNum - 1;
	// }
	// // 追�??�?
	// TitleX.add(String.valueOf(stickData.get(index).getDate())
	// .substring(4));
	// }
	// TitleX.add(String.valueOf(stickData.get(maxSticksNum - 1).getDate())
	// .substring(4));
	// }
	// super.setLongitudeTitles(TitleX);
	// }
	//
	// /**
	// * <p>
	// * initialize degrees on Y axis
	// * </p>
	// * <p>
	// * Y軸の目盛を初期化
	// * </p>
	// * <p>
	// * 初始化Y轴的坐标值
	// * </p>
	// */
	// protected void initAxisY() {
	// List<String> TitleY = new ArrayList<String>();
	// float average = (int) ((maxValue - minValue) / this.getLatitudeNum()) /
	// 10 * 10;
	// // calculate degrees on Y axis
	// for (int i = 0; i < this.getLatitudeNum(); i++) {
	// String value = String.valueOf((int) Math.floor(minValue + i
	// * average));
	// if (value.length() < super.getLatitudeMaxTitleLength()) {
	// while (value.length() < super.getLatitudeMaxTitleLength()) {
	// value = new String(" ") + value;
	// }
	// }
	// TitleY.add(value);
	// }
	// // calculate last degrees by use max value
	// String value = String.valueOf((int) Math
	// .floor(((int) maxValue) / 10 * 10));
	// if (value.length() < super.getLatitudeMaxTitleLength()) {
	// while (value.length() < super.getLatitudeMaxTitleLength()) {
	// value = new String(" ") + value;
	// }
	// }
	// TitleY.add(value);
	//
	// super.setLatitudeTitles(TitleY);
	// }

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
	@Override
	protected void drawSticks(Canvas canvas) {
		float stickWidth = ((super.getWidth() - super.getAxisMarginLeft() - super
				.getAxisMarginRight()) / maxSticksNum) - 1;
		float stickX = super.getAxisMarginLeft() + 1;

		Paint mPaintPositive = new Paint();
		mPaintPositive.setColor(positiveStickFillColor);

		Paint mPaintNegative = new Paint();
		mPaintNegative.setColor(negativeStickFillColor);

		Paint mPaintCross = new Paint();
		mPaintCross.setColor(crossStarColor);

		if (null != stickData) {
			for (int i = 0; i < stickData.size(); i++) {
				OHLCEntity ohlc = (OHLCEntity) stickData.get(i);
				float openY = (float) ((1f - (ohlc.getOpen() - minValue)
						/ (maxValue - minValue))
						* (super.getHeight() - super.getAxisMarginBottom()) - super
						.getAxisMarginTop());
				float highY = (float) ((1f - (ohlc.getHigh() - minValue)
						/ (maxValue - minValue))
						* (super.getHeight() - super.getAxisMarginBottom()) - super
						.getAxisMarginTop());
				float lowY = (float) ((1f - (ohlc.getLow() - minValue)
						/ (maxValue - minValue))
						* (super.getHeight() - super.getAxisMarginBottom()) - super
						.getAxisMarginTop());
				float closeY = (float) ((1f - (ohlc.getClose() - minValue)
						/ (maxValue - minValue))
						* (super.getHeight() - super.getAxisMarginBottom()) - super
						.getAxisMarginTop());

				if (ohlc.getOpen() < ohlc.getClose()) {
					// stick or line
					if (stickWidth >= 2f) {
						canvas.drawRect(stickX, closeY, stickX + stickWidth,
								openY, mPaintPositive);
					}
					canvas.drawLine(stickX + stickWidth / 2f, highY, stickX
							+ stickWidth / 2f, lowY, mPaintPositive);
				} else if (ohlc.getOpen() > ohlc.getClose()) {
					// stick or line
					if (stickWidth >= 2f) {
						canvas.drawRect(stickX, openY, stickX + stickWidth,
								closeY, mPaintNegative);
					}
					canvas.drawLine(stickX + stickWidth / 2f, highY, stickX
							+ stickWidth / 2f, lowY, mPaintNegative);
				} else {
					// line or point
					if (stickWidth >= 2f) {
						canvas.drawLine(stickX, closeY, stickX + stickWidth,
								openY, mPaintCross);
					}
					canvas.drawLine(stickX + stickWidth / 2f, highY, stickX
							+ stickWidth / 2f, lowY, mPaintCross);
				}

				// next x
				stickX = stickX + 1 + stickWidth;
			}
		}
	}

	private final int NONE = 0;
	private final int ZOOM = 1;
	private final int DOWN = 2;

	private float olddistance = 0f;
	private float newdistance = 0f;

	private int TOUCH_MODE;

	/*
	 * (non-Javadoc)
	 * 
	 * <p>Called when chart is touched<p> <p>チャートをタッチしたら、メソッドを呼ぶ<p>
	 * <p>图表点击时调用<p>
	 * 
	 * @param event
	 * 
	 * @see android.view.View#onTouchEvent(MotionEvent)
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		final float MIN_LENGTH = (super.getWidth() / 40) < 5 ? 5 : (super
				.getWidth() / 50);

		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN:
			TOUCH_MODE = DOWN;
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_POINTER_UP:
			TOUCH_MODE = NONE;
			return super.onTouchEvent(event);
		case MotionEvent.ACTION_POINTER_DOWN:
			olddistance = calcDistance(event);
			if (olddistance > MIN_LENGTH) {
				TOUCH_MODE = ZOOM;
			}
			break;
		case MotionEvent.ACTION_MOVE:
			if (TOUCH_MODE == ZOOM) {
				newdistance = calcDistance(event);
				if (newdistance > MIN_LENGTH
						&& Math.abs(newdistance - olddistance) > MIN_LENGTH) {

					if (newdistance > olddistance) {
						zoomIn();
					} else {
						zoomOut();
					}
					olddistance = newdistance;

					super.postInvalidate();
					super.notifyEventAll(this);
				}
			}
			break;
		}
		return true;
	}

	/**
	 * <p>
	 * calculate the distance between two touch points
	 * </p>
	 * <p>
	 * 複数タッチしたポイントの距離
	 * </p>
	 * <p>
	 * 计算两点触控时两点之间的距离
	 * </p>
	 * 
	 * @param event
	 * @return float
	 *         <p>
	 *         distance
	 *         </p>
	 *         <p>
	 *         距離
	 *         </p>
	 *         <p>
	 *         距离
	 *         </p>
	 */
	private float calcDistance(MotionEvent event) {
		float x = event.getX(0) - event.getX(1);
		float y = event.getY(0) - event.getY(1);
		return FloatMath.sqrt(x * x + y * y);
	}

	/**
	 * <p>
	 * Zoom in the graph
	 * </p>
	 * <p>
	 * 拡大表示する。
	 * </p>
	 * <p>
	 * 放大表示
	 * </p>
	 */
	protected void zoomIn() {
		if (maxSticksNum > 10) {
			maxSticksNum = maxSticksNum - 3;
		}
	}

	/**
	 * <p>
	 * Zoom out the grid
	 * </p>
	 * <p>
	 * 縮小表示する。
	 * </p>
	 * <p>
	 * 缩小
	 * </p>
	 */
	protected void zoomOut() {
		if (maxSticksNum < stickData.size() - 1) {
			maxSticksNum = maxSticksNum + 3;
		}
	}

	/**
	 * @return the positiveStickBorderColor
	 */
	public int getPositiveStickBorderColor() {
		return positiveStickBorderColor;
	}

	/**
	 * @param positiveStickBorderColor
	 *            the positiveStickBorderColor to set
	 */
	public void setPositiveStickBorderColor(int positiveStickBorderColor) {
		this.positiveStickBorderColor = positiveStickBorderColor;
	}

	/**
	 * @return the positiveStickFillColor
	 */
	public int getPositiveStickFillColor() {
		return positiveStickFillColor;
	}

	/**
	 * @param positiveStickFillColor
	 *            the positiveStickFillColor to set
	 */
	public void setPositiveStickFillColor(int positiveStickFillColor) {
		this.positiveStickFillColor = positiveStickFillColor;
	}

	/**
	 * @return the negativeStickBorderColor
	 */
	public int getNegativeStickBorderColor() {
		return negativeStickBorderColor;
	}

	/**
	 * @param negativeStickBorderColor
	 *            the negativeStickBorderColor to set
	 */
	public void setNegativeStickBorderColor(int negativeStickBorderColor) {
		this.negativeStickBorderColor = negativeStickBorderColor;
	}

	/**
	 * @return the negativeStickFillColor
	 */
	public int getNegativeStickFillColor() {
		return negativeStickFillColor;
	}

	/**
	 * @param negativeStickFillColor
	 *            the negativeStickFillColor to set
	 */
	public void setNegativeStickFillColor(int negativeStickFillColor) {
		this.negativeStickFillColor = negativeStickFillColor;
	}

	/**
	 * @return the crossStarColor
	 */
	public int getCrossStarColor() {
		return crossStarColor;
	}

	/**
	 * @param crossStarColor
	 *            the crossStarColor to set
	 */
	public void setCrossStarColor(int crossStarColor) {
		this.crossStarColor = crossStarColor;
	}

}
