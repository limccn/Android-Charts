/*
 * SlipCandleStickChart.java
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
import cn.limc.androidcharts.entity.OHLCEntity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
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
 * @version v1.0 2014/01/21 11:37:51
 * 
 */
public class SlipCandleStickChart extends SlipStickChart {

	//Standard Candle Style
	public static final int CANDLE_STICK_STYLE_STANDARD  = 0;
	//American Bar Style
	public static final int CANDLE_STICK_STYLE_BAR = 1;
	//Close Line Style
	public static final int CANDLE_STICK_STYLE_LINE = 2;

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
	public static final int DEFAULT_POSITIVE_STICK_BORDER_COLOR = Color.parseColor("#ed4d4d");

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
	public static final int DEFAULT_POSITIVE_STICK_FILL_COLOR = Color.parseColor("#ed4d4d");

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
	public static final int DEFAULT_NEGATIVE_STICK_BORDER_COLOR = Color.parseColor("#52ba27");

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
	public static final int DEFAULT_NEGATIVE_STICK_FILL_COLOR = Color.parseColor("#52ba27");

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

	public static final int DEFAULT_CANDLE_STICK_STYLE = CANDLE_STICK_STYLE_STANDARD;

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


	private int candleStickStyle = DEFAULT_CANDLE_STICK_STYLE;

	/**
	 * <p>
	 * Constructor of SlipCandleStickChart
	 * </p>
	 * <p>
	 * SlipCandleStickChart类对象的构造函数
	 * </p>
	 * <p>
	 * SlipCandleStickChartのコンストラクター
	 * </p>
	 * 
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public SlipCandleStickChart(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	/**
	 * <p>
	 * Constructor of SlipCandleStickChart
	 * </p>
	 * <p>
	 * SlipCandleStickChart类对象的构造函数
	 * </p>
	 * <p>
	 * SlipCandleStickChartのコンストラクター
	 * </p>
	 * 
	 * @param context
	 * @param attrs
	 */
	public SlipCandleStickChart(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	/**
	 * <p>
	 * Constructor of SlipCandleStickChart
	 * </p>
	 * <p>
	 * SlipCandleStickChart类对象的构造函数
	 * </p>
	 * <p>
	 * SlipCandleStickChartのコンストラクター
	 * </p>
	 * 
	 * @param context
	 */
	public SlipCandleStickChart(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void calcDataValueRange() {
		double maxValue = Double.MIN_VALUE;
		double minValue = Double.MAX_VALUE;

		IMeasurable first = this.stickData.get(getDisplayFrom());
		// 第一个stick为停盘的情况
		if (first.getHigh() == 0 && first.getLow() == 0) {

		} else {
			maxValue = first.getHigh();
			minValue = first.getLow();
		}
		for (int i = getDisplayFrom(); i < getDisplayTo(); i++) {
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

				if (stick.getHigh() > maxValue) {
					maxValue = stick.getHigh();
				}
			}
		}

		this.maxDataValue = maxValue;
		this.minDataValue = minValue;

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

//	@Override
//	public void drawData(Canvas canvas){
//		super.drawData(canvas);
//		if (getDisplayNumber() > displayStickAsLineNumber){
//			drawSticksAsLine(canvas);
//		}else{
//			drawSticks(canvas);
//		}
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
	@Override
	protected void drawSticks(Canvas canvas) {
		if (null == stickData) {
			return;
		}
		if (stickData.size() <= 0) {
			return;
		}

		float stickWidth = dataQuadrant.getPaddingWidth() / getDataDisplayNumber()
				- stickSpacing;
		float stickX = dataQuadrant.getPaddingStartX();

		Paint mPaintPositiveFill = new Paint();
		mPaintPositiveFill.setColor(positiveStickFillColor);
		mPaintPositiveFill.setStyle(Paint.Style.FILL);

		Paint mPaintPositiveBorder = new Paint();
		mPaintPositiveBorder.setColor(positiveStickBorderColor);
		mPaintPositiveBorder.setStyle(Paint.Style.STROKE);

		Paint mPaintNegativeFill = new Paint();
		mPaintNegativeFill.setColor(negativeStickFillColor);
		mPaintNegativeFill.setStyle(Paint.Style.FILL);

		Paint mPaintNegativeBorder = new Paint();
		mPaintNegativeBorder.setColor(negativeStickBorderColor);
		mPaintNegativeBorder.setStyle(Paint.Style.STROKE);

		Paint mPaintCross = new Paint();
		mPaintCross.setColor(crossStarColor);

		boolean maxValueDrawn = false;
		boolean minValueDrawn = false;

		for (int i = getDisplayFrom(); i < getDisplayTo(); i++) {
			OHLCEntity ohlc = (OHLCEntity) stickData.get(i);
			float openY = (float) ((1f - (ohlc.getOpen() - minValue)
					/ (maxValue - minValue))
					* (dataQuadrant.getPaddingHeight()) + dataQuadrant.getPaddingStartY());
			float highY = (float) ((1f - (ohlc.getHigh() - minValue)
					/ (maxValue - minValue))
					* (dataQuadrant.getPaddingHeight()) + dataQuadrant.getPaddingStartY());
			float lowY = (float) ((1f - (ohlc.getLow() - minValue)
					/ (maxValue - minValue))
					* (dataQuadrant.getPaddingHeight()) + dataQuadrant.getPaddingStartY());
			float closeY = (float) ((1f - (ohlc.getClose() - minValue)
					/ (maxValue - minValue))
					* (dataQuadrant.getPaddingHeight()) + dataQuadrant.getPaddingStartY());

			float stickCenterX = stickX + stickWidth / 2;

			if (ohlc.getOpen() < ohlc.getClose()) {
				if (this.candleStickStyle == CANDLE_STICK_STYLE_STANDARD) {
					// stick or line
					if (stickWidth >= 2f) {
						canvas.drawRect(stickX, closeY, stickX + stickWidth, openY,
								mPaintPositiveFill);
						canvas.drawRect(stickX, closeY, stickX + stickWidth, openY,
								mPaintPositiveBorder);
					}
					canvas.drawLine(stickCenterX, highY, stickCenterX, closeY, mPaintPositiveBorder);
					canvas.drawLine(stickCenterX, openY, stickCenterX, lowY, mPaintPositiveBorder);
				}else if(this.candleStickStyle == CANDLE_STICK_STYLE_BAR){
					canvas.drawLine(stickX, openY, stickCenterX, openY, mPaintPositiveBorder);
					canvas.drawLine(stickCenterX, highY, stickCenterX, lowY, mPaintPositiveBorder);
					canvas.drawLine(stickCenterX, closeY, stickX + stickWidth, closeY, mPaintPositiveBorder);
				}else{
					//noop
				}
			} else if (ohlc.getOpen() > ohlc.getClose()) {
				if (this.candleStickStyle == CANDLE_STICK_STYLE_STANDARD) {
					// stick or line
					if (stickWidth >= 2f) {
						canvas.drawRect(stickX, openY, stickX + stickWidth, closeY,
								mPaintNegativeFill);
						canvas.drawRect(stickX, openY, stickX + stickWidth, closeY,
								mPaintNegativeBorder);
					}
					canvas.drawLine(stickCenterX, highY, stickCenterX , openY, mPaintNegativeBorder);
					canvas.drawLine(stickCenterX, closeY, stickCenterX , lowY, mPaintNegativeBorder);
				}else if(this.candleStickStyle == CANDLE_STICK_STYLE_BAR){
					canvas.drawLine(stickX, openY, stickCenterX, openY, mPaintNegativeBorder);
					canvas.drawLine(stickCenterX, highY, stickCenterX, lowY, mPaintNegativeBorder);
					canvas.drawLine(stickCenterX, closeY, stickX + stickWidth, closeY, mPaintNegativeBorder);
				}else{
					//noop
				}
			} else {
				// line or point
				if (stickWidth >= 2f) {
					canvas.drawLine(stickX, closeY, stickX + stickWidth, openY,
							mPaintCross);
				}
				canvas.drawLine(stickCenterX, highY, stickCenterX, lowY, mPaintCross);
			}

			if (ohlc.getHigh() - this.maxDataValue == 0 && maxValueDrawn == false) {
				this.drawMaxLabel(canvas, ohlc.getHigh(), new PointF(stickCenterX, highY));
				maxValueDrawn = true;
			}

			if (ohlc.getLow() - this.minDataValue == 0 && minValueDrawn == false) {
				this.drawMinLabel(canvas, ohlc.getLow(), new PointF(stickCenterX, lowY));
				minValueDrawn = true;
			}


			// next x
			stickX = stickX + stickSpacing + stickWidth;
		}
	}

	protected void drawMaxLabel(Canvas canvas, double value , PointF point){
		String valueStr = this.formatAxisYDegree(value);

		int textFontSize = 28;

		Paint mPaintBoxLine = new Paint();
		mPaintBoxLine.setColor(Color.LTGRAY);
		// mPaintBoxLine.setAlpha(80);
		mPaintBoxLine.setStyle(Paint.Style.FILL);

		Paint mPaintBoxText = new Paint();
		mPaintBoxText.setColor(Color.BLACK);
		mPaintBoxText.setAntiAlias(true);
		mPaintBoxText.setTextSize(textFontSize);

		Rect textSize = new Rect();
		mPaintBoxText.getTextBounds(valueStr, 0, valueStr.length(), textSize);

		float spaceToTopBottom = textFontSize ;
		float spaceToRect = textFontSize * 1.48f;

		if(point.x > dataQuadrant.getStartX() + textSize.width() + spaceToRect){
			//画左边
			//向上画
			canvas.drawLine(point.x, point.y, point.x, point.y - spaceToTopBottom, mPaintBoxLine);
			canvas.drawLine(point.x, point.y - spaceToTopBottom, point.x - spaceToRect, point.y - spaceToTopBottom, mPaintBoxLine);

			PointF boxHS = new PointF(point.x - textSize.width() - spaceToRect, point.y - textSize.height() / 2.f - spaceToTopBottom - 4);
			PointF boxHE = new PointF(point.x - spaceToRect, point.y + textSize.height() / 2.f - spaceToTopBottom + 4);

			// draw a rectangle
			canvas.drawRect(boxHS.x, boxHS.y, boxHE.x, boxHE.y, mPaintBoxLine);
			// draw text
			canvas.drawText(valueStr, boxHS.x, boxHS.y + textFontSize, mPaintBoxText);

		}else{

			// 画左边
			if(point.x > dataQuadrant.getEndX() - textSize.width() - spaceToRect){
				canvas.drawLine(point.x, point.y, point.x, point.y - spaceToTopBottom, mPaintBoxLine);
				canvas.drawLine(point.x, point.y - spaceToTopBottom, point.x - spaceToRect, point.y - spaceToTopBottom, mPaintBoxLine);

				PointF boxHS = new PointF(point.x - textSize.width() - spaceToRect, point.y - textSize.height() / 2.f - spaceToTopBottom - 4);
				PointF boxHE = new PointF(point.x - spaceToRect, point.y + textSize.height() / 2.f - spaceToTopBottom + 4);

				// draw a rectangle
				canvas.drawRect(boxHS.x, boxHS.y, boxHE.x, boxHE.y, mPaintBoxLine);
				// draw text
				canvas.drawText(valueStr, boxHS.x, boxHS.y + textFontSize, mPaintBoxText);

			}else{
				canvas.drawLine(point.x, point.y, point.x, point.y - spaceToTopBottom, mPaintBoxLine);
				canvas.drawLine(point.x, point.y - spaceToTopBottom, point.x + spaceToRect, point.y - spaceToTopBottom, mPaintBoxLine);

				//画右边
//				CGRect textRect = CGRectMake(pt.x + spaceToRect, pt.y - textSize.height / 2.f - spaceToTopBottom, textSize.width, textSize.height);

				PointF boxHS = new PointF(point.x + spaceToRect, point.y - textSize.height() / 2.f - spaceToTopBottom - 4);
				PointF boxHE = new PointF(point.x + textSize.width() + spaceToRect, point.y + textSize.height() / 2.f - spaceToTopBottom + 4);

				// draw a rectangle
				canvas.drawRect(boxHS.x, boxHS.y, boxHE.x, boxHE.y, mPaintBoxLine);
				// draw text
				canvas.drawText(valueStr, boxHS.x, boxHS.y + textFontSize, mPaintBoxText);
			}
		}
	}

	protected void drawMinLabel(Canvas canvas, double value , PointF point){
		String valueStr = this.formatAxisYDegree(value);

		int textFontSize = 28;

		Paint mPaintBoxLine = new Paint();
		mPaintBoxLine.setColor(Color.LTGRAY);
		// mPaintBoxLine.setAlpha(80);
		mPaintBoxLine.setStyle(Paint.Style.FILL);

		Paint mPaintBoxText = new Paint();
		mPaintBoxText.setColor(Color.BLACK);
		mPaintBoxText.setAntiAlias(true);
		mPaintBoxText.setTextSize(textFontSize);

		Rect textSize = new Rect();
		mPaintBoxText.getTextBounds(valueStr, 0, valueStr.length(), textSize);

		float spaceToTopBottom = textFontSize ;
		float spaceToRect = textFontSize * 1.48f;

		if(point.x > dataQuadrant.getStartX() + textSize.width() + spaceToRect){
			//画左边
			//向上画
			canvas.drawLine(point.x, point.y, point.x, point.y + spaceToTopBottom, mPaintBoxLine);
			canvas.drawLine(point.x, point.y + spaceToTopBottom, point.x - spaceToRect, point.y + spaceToTopBottom, mPaintBoxLine);

			PointF boxHS = new PointF(point.x - textSize.width() - spaceToRect, point.y - textSize.height() / 2.f + spaceToTopBottom - 4);
			PointF boxHE = new PointF(point.x - spaceToRect, point.y + textSize.height() / 2.f + spaceToTopBottom + 4);

			// draw a rectangle
			canvas.drawRect(boxHS.x, boxHS.y, boxHE.x, boxHE.y, mPaintBoxLine);
			// draw text
			canvas.drawText(valueStr, boxHS.x, boxHS.y + textFontSize, mPaintBoxText);

		}else{

			// 画左边
			if(point.x > dataQuadrant.getEndX() - textSize.width() - spaceToRect){
				canvas.drawLine(point.x, point.y, point.x, point.y + spaceToTopBottom, mPaintBoxLine);
				canvas.drawLine(point.x, point.y + spaceToTopBottom, point.x - spaceToRect, point.y + spaceToTopBottom, mPaintBoxLine);

				PointF boxHS = new PointF(point.x - textSize.width() - spaceToRect, point.y - textSize.height() / 2.f + spaceToTopBottom - 4);
				PointF boxHE = new PointF(point.x - spaceToRect, point.y + textSize.height() / 2.f + spaceToTopBottom + 4);

				// draw a rectangle
				canvas.drawRect(boxHS.x, boxHS.y, boxHE.x, boxHE.y, mPaintBoxLine);
				// draw text
				canvas.drawText(valueStr, boxHS.x, boxHS.y + textFontSize, mPaintBoxText);

			}else{
				canvas.drawLine(point.x, point.y, point.x, point.y + spaceToTopBottom, mPaintBoxLine);
				canvas.drawLine(point.x, point.y + spaceToTopBottom, point.x + spaceToRect, point.y + spaceToTopBottom, mPaintBoxLine);

				//画右边
				PointF boxHS = new PointF(point.x + spaceToRect, point.y - textSize.height() / 2.f + spaceToTopBottom - 4);
				PointF boxHE = new PointF(point.x + textSize.width() + spaceToRect, point.y + textSize.height() / 2.f + spaceToTopBottom + 4);

				// draw a rectangle
				canvas.drawRect(boxHS.x, boxHS.y, boxHE.x, boxHE.y, mPaintBoxLine);
				// draw text
				canvas.drawText(valueStr, boxHS.x, boxHS.y + textFontSize, mPaintBoxText);
			}
		}
	}

	protected void drawSticksAsLine(Canvas canvas) {
		if (null == stickData) {
			return;
		}
		if (stickData.size() <= 0) {
			return;
		}

		float lineLength = dataQuadrant.getPaddingWidth() / getDisplayNumber()
				- stickSpacing;
		float startX = dataQuadrant.getPaddingStartX() + lineLength / 2;

		Paint mPaintStroke = new Paint();
		mPaintStroke.setStyle(Paint.Style.STROKE);
		mPaintStroke.setStrokeWidth(2.0f);
		mPaintStroke.setColor(displayStickAsLineColor);

		// start point
		PointF ptFirst = null;

		for (int j = getDisplayFrom(); j < getDisplayTo(); j++) {

			OHLCEntity ohlc = (OHLCEntity) stickData.get(j);
			double value = ohlc.getClose();

			if(isNoneDisplayValue((float)value)){

			}else {
//				// calculate Y
//				float valueY = (float) ((1f - (value - minValue)
//						/ (maxValue - minValue)) * dataQuadrant.getPaddingHeight())
//						+ dataQuadrant.getPaddingStartY();

				float closeY = (float) ((1f - (ohlc.getClose() - minValue)
						/ (maxValue - minValue))
						* (dataQuadrant.getPaddingHeight()) + dataQuadrant.getPaddingStartY());

				// if is not last point connect to previous point
				if (j > getDisplayFrom() && ptFirst!=null) {
					canvas.drawLine(ptFirst.x, ptFirst.y, startX, closeY,
							mPaintStroke);
				}
				// reset
				ptFirst = new PointF(startX, closeY);
			}
			startX = startX + stickSpacing + lineLength;
		}
	}
	
	@Override
	protected PointF calcBindPoint(float x ,float y) {
		float calcX = 0;
		float calcY = 0;
		int index = calcSelectedIndex(x,y);

		float stickWidth = dataQuadrant.getPaddingWidth() / getDataDisplayNumber();

		if (index >= getDisplayFrom() && index <= getDisplayTo() - 1) {
			OHLCEntity stick = (OHLCEntity) stickData.get(index);
			calcY = (float) ((1f - (stick.getClose() - minValue)
					/ (maxValue - minValue))
					* (dataQuadrant.getPaddingHeight()) + dataQuadrant.getPaddingStartY());
			calcX = dataQuadrant.getPaddingStartX() + stickWidth * (index - getDisplayFrom()) + stickWidth / 2;
		}
		return new PointF(calcX,calcY);
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

	public int getCandleStickStyle() {
		return candleStickStyle;
	}

	public void setCandleStickStyle(int candleStickStyle) {
		this.candleStickStyle = candleStickStyle;
	}
}
