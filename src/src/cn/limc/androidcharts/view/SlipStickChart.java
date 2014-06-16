/*
 * SlipStickChart.java
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

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.limc.androidcharts.entity.IChartData;
import cn.limc.androidcharts.entity.IMeasurable;
import cn.limc.androidcharts.entity.IStickEntity;
import cn.limc.androidcharts.entity.StickEntity;

import android.R.string;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.view.MotionEvent;

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
 * @version v1.0 2014-1-20 下午2:03:08
 * 
 */
public class SlipStickChart extends GridChart implements ISlipable ,IZoomable {

	public static final int DEFAULT_STICK_ALIGN_TYPE = ALIGN_TYPE_CENTER;
	
	public static final int DEFAULT_DISPLAY_FROM = 0;
	public static final int DEFAULT_DISPLAY_NUMBER = 50;
	public static final int DEFAULT_MIN_DISPLAY_NUMBER = 20;
	public static final int DEFAULT_ZOOM_BASE_LINE = ZOOM_BASE_LINE_CENTER;
	public static final boolean DEFAULT_AUTO_CALC_VALUE_RANGE = true;

	public static final int DEFAULT_STICK_SPACING = 1;

	public static final int DEFAULT_DATA_MULTIPLE = 1;
	public static final String DEFAULT_AXIS_Y_DECIMAL_FORMAT = "#,##0";
	public static final String DEFAULT_AXIS_X_DATE_TARGET_FORMAT = "yyyy/MM/dd";
	public static final String DEFAULT_AXIS_X_DATE_SOURCE_FORMAT = "yyyyMMdd";
	
	protected int displayFrom = DEFAULT_DISPLAY_FROM;
	protected int displayNumber = DEFAULT_DISPLAY_NUMBER;
	protected int minDisplayNumber = DEFAULT_MIN_DISPLAY_NUMBER;
	protected int zoomBaseLine = DEFAULT_ZOOM_BASE_LINE;
	protected boolean autoCalcValueRange = DEFAULT_AUTO_CALC_VALUE_RANGE;
	protected int stickSpacing = DEFAULT_STICK_SPACING;

	/**
	 * <p>
	 * default color for display stick border
	 * </p>
	 * <p>
	 * 表示スティックのボーダーの色のデフォルト値
	 * </p>
	 * <p>
	 * 默认表示柱条的边框颜色
	 * </p>
	 */
	public static final int DEFAULT_STICK_BORDER_COLOR = Color.RED;

	/**
	 * <p>
	 * default color for display stick
	 * </p>
	 * <p>
	 * 表示スティックの色のデフォルト値
	 * </p>
	 * <p>
	 * 默认表示柱条的填充颜色
	 * </p>
	 */
	public static final int DEFAULT_STICK_FILL_COLOR = Color.RED;

	/**
	 * <p>
	 * Color for display stick border
	 * </p>
	 * <p>
	 * 表示スティックのボーダーの色
	 * </p>
	 * <p>
	 * 表示柱条的边框颜色
	 * </p>
	 */
	private int stickBorderColor = DEFAULT_STICK_BORDER_COLOR;

	/**
	 * <p>
	 * Color for display stick
	 * </p>
	 * <p>
	 * 表示スティックの色
	 * </p>
	 * <p>
	 * 表示柱条的填充颜色
	 * </p>
	 */
	private int stickFillColor = DEFAULT_STICK_FILL_COLOR;
	
	protected int stickAlignType = DEFAULT_STICK_ALIGN_TYPE;

	/**
	 * <p>
	 * data to draw sticks
	 * </p>
	 * <p>
	 * スティックを書く用データ
	 * </p>
	 * <p>
	 * 绘制柱条用的数据
	 * </p>
	 */
	protected IChartData<IStickEntity> stickData;
	
	protected int dataMultiple =  DEFAULT_DATA_MULTIPLE;
	protected String axisYDecimalFormat = DEFAULT_AXIS_Y_DECIMAL_FORMAT;
	protected String axisXDateTargetFormat = DEFAULT_AXIS_X_DATE_TARGET_FORMAT;
	protected String axisXDateSourceFormat = DEFAULT_AXIS_X_DATE_SOURCE_FORMAT;
	
	/**
	 * <p>
	 * max value of Y axis
	 * </p>
	 * <p>
	 * Y軸の最大値
	 * </p>
	 * <p>
	 * Y的最大表示值
	 * </p>
	 */
	protected double maxValue;

	/**
	 * <p>
	 * min value of Y axis
	 * </p>
	 * <p>
	 * Y軸の最小値
	 * </p>
	 * <p>
	 * Y的最小表示值
	 * </p>
	 */
	protected double minValue;
	
	protected OnZoomGestureListener onZoomGestureListener;
	protected OnSlipGestureListener onSlipGestureListener;

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
	public SlipStickChart(Context context) {
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
	public SlipStickChart(Context context, AttributeSet attrs, int defStyle) {
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
	public SlipStickChart(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	protected void calcDataValueRange() {

		double maxValue = Double.MIN_VALUE;
		double minValue = Double.MAX_VALUE;

		IMeasurable first = this.stickData.get(0);
		// 第一个stick为停盘的情况
		if (first.getHigh() == 0 && first.getLow() == 0) {

		} else {
			maxValue = first.getHigh();
			minValue = first.getLow();
		}

		// 判断显示为方柱或显示为线条
		for (int i = this.displayFrom; i < this.displayFrom
				+ this.displayNumber; i++) {
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

	protected void calcValueRangePaddingZero() {
		double maxValue = this.maxValue;
		double minValue = this.minValue;

		if ((long) maxValue > (long) minValue) {
			if ((maxValue - minValue) < 10 && minValue > 1) {
				this.maxValue = (long) (maxValue + 1);
				this.minValue = (long) (minValue - 1);
			} else {
				this.maxValue = (long) (maxValue + (maxValue - minValue) * 0.1);
				this.minValue = (long) (minValue - (maxValue - minValue) * 0.1);
				if (this.minValue < 0) {
					this.minValue = 0;
				}
			}
		} else if ((long) maxValue == (long) minValue) {
			if (maxValue <= 10 && maxValue > 1) {
				this.maxValue = maxValue + 1;
				this.minValue = minValue - 1;
			} else if (maxValue <= 100 && maxValue > 10) {
				this.maxValue = maxValue + 10;
				this.minValue = minValue - 10;
			} else if (maxValue <= 1000 && maxValue > 100) {
				this.maxValue = maxValue + 100;
				this.minValue = minValue - 100;
			} else if (maxValue <= 10000 && maxValue > 1000) {
				this.maxValue = maxValue + 1000;
				this.minValue = minValue - 1000;
			} else if (maxValue <= 100000 && maxValue > 10000) {
				this.maxValue = maxValue + 10000;
				this.minValue = minValue - 10000;
			} else if (maxValue <= 1000000 && maxValue > 100000) {
				this.maxValue = maxValue + 100000;
				this.minValue = minValue - 100000;
			} else if (maxValue <= 10000000 && maxValue > 1000000) {
				this.maxValue = maxValue + 1000000;
				this.minValue = minValue - 1000000;
			} else if (maxValue <= 100000000 && maxValue > 10000000) {
				this.maxValue = maxValue + 10000000;
				this.minValue = minValue - 10000000;
			}
		} else {
			this.maxValue = 0;
			this.minValue = 0;
		}

	}

	protected void calcValueRangeFormatForAxis() {
		// 修正最大值和最小值
		long rate = (long) (this.maxValue - this.minValue) / (this.latitudeNum);
		String strRate = String.valueOf(rate);
		float first = Integer.parseInt(String.valueOf(strRate.charAt(0))) + 1.0f;
		if (first > 0 && strRate.length() > 1) {
			float second = Integer.parseInt(String.valueOf(strRate.charAt(1)));
			if (second < 5) {
				first = first - 0.5f;
			}
			rate = (long) (first * Math.pow(10, strRate.length() - 1));
		} else {
			rate = 1;
		}
		// 等分轴修正
		if (this.latitudeNum > 0
				&& (long) (this.maxValue - this.minValue)
						% (this.latitudeNum * rate) != 0) {
			// 最大值加上轴差
			this.maxValue = (long) this.maxValue
					+ (this.latitudeNum * rate)
					- ((long) (this.maxValue - this.minValue) % (this.latitudeNum * rate));
		}
	}

	protected void calcValueRange() {
		if (null == this.stickData) {
			this.maxValue = 0;
			this.minValue = 0;
			return;
		}

		if (this.stickData.size() > 0) {
			this.calcDataValueRange();
			this.calcValueRangePaddingZero();
		} else {
			this.maxValue = 0;
			this.minValue = 0;
		}
		this.calcValueRangeFormatForAxis();
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
		if (this.autoCalcValueRange) {
			calcValueRange();
		}
		initAxisY();
		initAxisX();
		super.onDraw(canvas);

		drawSticks(canvas);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @param value
	 * 
	 * @return
	 * 
	 * @see
	 * cn.limc.androidcharts.view.GridChart#getAxisXGraduate(java.lang.Object)
	 */
	@Override
	public String getAxisXGraduate(Object value) {
		float graduate = Float.valueOf(super.getAxisXGraduate(value));
		int index = (int) Math.floor(graduate * displayNumber);

		if (index >= displayNumber) {
			index = displayNumber - 1;
		} else if (index < 0) {
			index = 0;
		}

		index = index + displayFrom;

		return formatAxisXDegree(stickData.get(index).getDate());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @param value
	 * 
	 * @see cn.limc.androidcharts.view.GridChart#getAxisYGraduate(Object)
	 */
	@Override
	public String getAxisYGraduate(Object value) {
		float graduate = Float.valueOf(super.getAxisYGraduate(value));
		return formatAxisYDegree(graduate * (maxValue - minValue)
				+ minValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @param value
	 * 
	 * @see
	 * cn.limc.androidcharts.event.ITouchEventResponse#notifyEvent(GridChart)
	 */
	@Override
	public void notifyEvent(GridChart chart) {
		// CandleStickChart candlechart = (CandleStickChart) chart;
		// this. = candlechart.getMaxSticksNum();

		super.setDisplayCrossYOnTouch(false);
		// notifyEvent
		super.notifyEvent(chart);
		// notifyEventAll
		super.notifyEventAll(this);
	}

	public float longitudePostOffset(){
		if (stickAlignType == ALIGN_TYPE_CENTER) {
			float stickWidth = getDataQuadrantPaddingWidth() / displayNumber;
			return (this.getDataQuadrantPaddingWidth() - stickWidth)/ (longitudeTitles.size() - 1);
	    }else{
			return this.getDataQuadrantPaddingWidth()/ (longitudeTitles.size() - 1);
	    }
	}

	public float longitudeOffset(){
		if (stickAlignType == ALIGN_TYPE_CENTER) {
			float stickWidth = getDataQuadrantPaddingWidth() / displayNumber;
			return getDataQuadrantPaddingStartX() + stickWidth / 2;
		}else{
			return getDataQuadrantPaddingStartX();
		}
	}
	
	/**
	 * <p>
	 * initialize degrees on Y axis
	 * </p>
	 * <p>
	 * Y軸の目盛を初期化
	 * </p>
	 * <p>
	 * 初始化Y轴的坐标值
	 * </p>
	 */
	protected void initAxisX() {
		List<String> titleX = new ArrayList<String>();
		if (null != stickData && stickData.size() > 0) {
			float average = displayNumber / this.getLongitudeNum();
			for (int i = 0; i < this.getLongitudeNum(); i++) {
				int index = (int) Math.floor(i * average);
				if (index > displayNumber - 1) {
					index = displayNumber - 1;
				}
				index = index + displayFrom;
				titleX.add(formatAxisXDegree(stickData.get(index).getDate()));
			}
			titleX.add(formatAxisXDegree(stickData.get(displayFrom + displayNumber - 1).getDate()));
		}
		super.setLongitudeTitles(titleX);
	}

	public int getSelectedIndex() {
		if (null == super.getTouchPoint()) {
			return 0;
		}
		float graduate = Float.valueOf(super.getAxisXGraduate(super
				.getTouchPoint().x));
		int index = (int) Math.floor(graduate * displayNumber);

		if (index >= displayNumber) {
			index = displayNumber - 1;
		} else if (index < 0) {
			index = 0;
		}
		index = index + displayFrom;
		return index;
	}

	/**
	 * <p>
	 * initialize degrees on Y axis
	 * </p>
	 * <p>
	 * Y軸の目盛を初期化
	 * </p>
	 * <p>
	 * 初始化Y轴的坐标值
	 * </p>
	 */
	protected void initAxisY() {
		this.calcValueRange();
		List<String> titleY = new ArrayList<String>();
		double average = (maxValue - minValue) / this.getLatitudeNum();
		
		// calculate degrees on Y axis
		for (int i = 0; i < this.getLatitudeNum(); i++) {
			String value = formatAxisYDegree(minValue + i * average);
			// padding space
			if (value.length() < super.getLatitudeMaxTitleLength()) {
				while (value.length() < super.getLatitudeMaxTitleLength()) {
					value = " " + value;
				}
			}
			titleY.add(value);
		}
		// calculate last degrees by use max value
		String value = formatAxisYDegree(maxValue);
		// padding space
		if (value.length() < super.getLatitudeMaxTitleLength()) {
			while (value.length() < super.getLatitudeMaxTitleLength()) {
				value = " " + value;
			}
		}
		titleY.add(value);

		super.setLatitudeTitles(titleY);
	}
	
	public String formatAxisYDegree(double value) {
		return new DecimalFormat(axisYDecimalFormat).format(Math.floor(value)/dataMultiple);
	}
	
	public String formatAxisXDegree(int date) {
		try {
			Date dt = new SimpleDateFormat(axisXDateSourceFormat).parse(String
					.valueOf(date));
			return new SimpleDateFormat(axisXDateTargetFormat).format(dt);
		} catch (ParseException e) {
			return "";
		}
	}

	protected void drawSticks(Canvas canvas) {
		if (null == stickData) {
			return;
		}
		if (stickData.size() == 0) {
			return;
		}

		Paint mPaintStick = new Paint();
		mPaintStick.setColor(stickFillColor);

		float stickWidth = getDataQuadrantPaddingWidth() / displayNumber
				- stickSpacing;
		float stickX = getDataQuadrantPaddingStartX();

		for (int i = displayFrom; i < displayFrom + displayNumber; i++) {
			IMeasurable stick = stickData.get(i);
			float highY = (float) ((1f - (stick.getHigh() - minValue)
					/ (maxValue - minValue))
					* (getDataQuadrantPaddingHeight()) + getDataQuadrantPaddingStartY());
			float lowY = (float) ((1f - (stick.getLow() - minValue)
					/ (maxValue - minValue))
					* (getDataQuadrantPaddingHeight()) + getDataQuadrantPaddingStartY());

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
	protected float olddistance = 0f;
	protected float newdistance = 0f;

	protected PointF startPointA;
	protected PointF startPointB;

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		//valid
		if (!isValidTouchPoint(event.getX(),event.getY())) {
			return false;
		}
		
		if (null == stickData || stickData.size() == 0) {
			return false;
		}

		final float MIN_LENGTH = (super.getWidth() / 40) < 5 ? 5 : (super
				.getWidth() / 50);

		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		// 设置拖拉模式
		case MotionEvent.ACTION_DOWN:
			touchMode = TOUCH_MODE_SINGLE;
			if (event.getPointerCount() == 1) {
				touchPoint = new PointF(event.getX(), event.getY());
				if (onTouchGestureListener != null) {
					onTouchGestureListener.onTouchDown(touchPoint, getSelectedIndex());
				}
				super.postInvalidate();
				//Notifier
				super.notifyEventAll(this);
			}
			break;
		case MotionEvent.ACTION_UP:
			touchMode = TOUCH_MODE_NONE;
			startPointA = null;
			startPointB = null;
			if (event.getPointerCount() == 1) {
				touchPoint = new PointF(event.getX(), event.getY());
				if (onTouchGestureListener != null) {
					onTouchGestureListener.onTouchUp(touchPoint, getSelectedIndex());
				}
				super.postInvalidate();
				//Notifier
				super.notifyEventAll(this);
			}
			break;
		case MotionEvent.ACTION_POINTER_UP:
			touchMode = TOUCH_MODE_NONE;
			startPointA = null;
			startPointB = null;
			return super.onTouchEvent(event);
			// 设置多点触摸模式
		case MotionEvent.ACTION_POINTER_DOWN:
			olddistance = calcDistance(event);
			if (olddistance > MIN_LENGTH) {
				touchMode = TOUCH_MODE_MULTI;
				startPointA = new PointF(event.getX(0), event.getY(0));
				startPointB = new PointF(event.getX(1), event.getY(1));
			}
			break;
		case MotionEvent.ACTION_MOVE:
			if (touchMode == TOUCH_MODE_MULTI) {
				newdistance = calcDistance(event);
				if (newdistance > MIN_LENGTH) {
					if (startPointA.x >= event.getX(0)
							&& startPointB.x >= event.getX(1)) {
						moveRight();
					} else if (startPointA.x <= event.getX(0)
							&& startPointB.x <= event.getX(1)) {
						moveLeft();
					} else {
						if (Math.abs(newdistance - olddistance) > MIN_LENGTH) {
							if (newdistance > olddistance) {
								zoomIn();
							} else {
								zoomOut();
							}
							// 重置距离
							olddistance = newdistance;
						}
					}
					startPointA = new PointF(event.getX(0), event.getY(0));
					startPointB = new PointF(event.getX(1), event.getY(1));

					super.postInvalidate();
					super.notifyEventAll(this);
				}
			} else {
				// 单点拖动效果
				if (event.getPointerCount() == 1) {
					float moveXdistance = Math.abs(event.getX() - touchPoint.x);
					float moveYdistance = Math.abs(event.getY() - touchPoint.y);

					if (moveXdistance > TOUCH_MOVE_MIN_DISTANCE || moveYdistance > TOUCH_MOVE_MIN_DISTANCE) {
						touchPoint = new PointF(event.getX(), event.getY());
						
						// call back to listener
						if (onTouchGestureListener != null) {
							onTouchGestureListener.onTouchMoved(touchPoint,getSelectedIndex());
						}
					}
					
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
	protected float calcDistance(MotionEvent event) {
		if(event.getPointerCount() <= 1) {
			return 0f;
		}else{
			float x = event.getX(0) - event.getX(1);
			float y = event.getY(0) - event.getY(1);
			return FloatMath.sqrt(x * x + y * y);
		}
	}
	
	public void moveRight() {
		int dataSize = stickData.size();
		if (displayFrom + displayNumber < dataSize - SLIP_STEP) {
			displayFrom = displayFrom + SLIP_STEP;
		} else {
			displayFrom = dataSize - displayNumber;
		}

		// 处理displayFrom越界
		if (displayFrom + displayNumber >= dataSize) {
			displayFrom = dataSize - displayNumber;
		}
		
		//Listener
		if (onSlipGestureListener != null) {
			onSlipGestureListener.onSlip(SLIP_DIRECTION_RIGHT, displayFrom, displayNumber);
		}
	}

	public void moveLeft() {
		int dataSize = stickData.size();
		
		if (displayFrom <= SLIP_STEP) {
			displayFrom = 0;
		} else if (displayFrom > SLIP_STEP) {
			displayFrom = displayFrom - SLIP_STEP;
		} else {

		}

		// 处理displayFrom越界
		if (displayFrom + displayNumber >= dataSize) {
			displayFrom = dataSize - displayNumber;
		}
		
		//Listener
		if (onSlipGestureListener != null) {
			onSlipGestureListener.onSlip(SLIP_DIRECTION_LEFT, displayFrom, displayNumber);
		}
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
	public void zoomIn() {
		if (displayNumber > minDisplayNumber) {
			// 区分缩放方向
			if (zoomBaseLine == ZOOM_BASE_LINE_CENTER) {
				displayNumber = displayNumber - ZOOM_STEP;
				displayFrom = displayFrom + ZOOM_STEP / 2;
			} else if (zoomBaseLine == ZOOM_BASE_LINE_LEFT) {
				displayNumber = displayNumber - ZOOM_STEP;
			} else if (zoomBaseLine == ZOOM_BASE_LINE_RIGHT) {
				displayNumber = displayNumber - ZOOM_STEP;
				displayFrom = displayFrom + ZOOM_STEP;
			}

			// 处理displayNumber越界
			if (displayNumber < minDisplayNumber) {
				displayNumber = minDisplayNumber;
			}

			// 处理displayFrom越界
			if (displayFrom + displayNumber >= stickData.size()) {
				displayFrom = stickData.size() - displayNumber;
			}
			
			//Listener
			if (onZoomGestureListener != null) {
				onZoomGestureListener.onZoom(ZOOM_IN, displayFrom, displayNumber);
			}
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
	public void zoomOut() {
		if (displayNumber < stickData.size() - 1) {
			if (displayNumber + ZOOM_STEP > stickData.size() - 1) {
				displayNumber = stickData.size() - 1;
				displayFrom = 0;
			} else {
				// 区分缩放方向
				if (zoomBaseLine == ZOOM_BASE_LINE_CENTER) {
					displayNumber = displayNumber + ZOOM_STEP;
					if (displayFrom > 1) {
						displayFrom = displayFrom - ZOOM_STEP / 2;
					} else {
						displayFrom = 0;
					}
				} else if (zoomBaseLine == ZOOM_BASE_LINE_LEFT) {
					displayNumber = displayNumber + ZOOM_STEP;
				} else if (zoomBaseLine == ZOOM_BASE_LINE_RIGHT) {
					displayNumber = displayNumber + ZOOM_STEP;
					if (displayFrom > ZOOM_STEP) {
						displayFrom = displayFrom - ZOOM_STEP;
					} else {
						displayFrom = 0;
					}
				}
			}

			if (displayFrom + displayNumber >= stickData.size()) {
				displayNumber = stickData.size() - displayFrom;
			}
			
			//Listener
			if (onZoomGestureListener != null) {
				onZoomGestureListener.onZoom(ZOOM_OUT, displayFrom, displayNumber);
			}
		}
	}

	/**
	 * <p>
	 * add a new stick data to sticks and refresh this chart
	 * </p>
	 * <p>
	 * 新しいスティックデータを追加する，フラフをレフレシューする
	 * </p>
	 * <p>
	 * 追加一条新数据并刷新当前图表
	 * </p>
	 * 
	 * @param entity
	 *            <p>
	 *            data
	 *            </p>
	 *            <p>
	 *            データ
	 *            </p>
	 *            <p>
	 *            新数据
	 *            </p>
	 */
	public void pushData(StickEntity entity) {
		if (null != entity) {
			addData(entity);
			super.postInvalidate();
		}
	}

	/**
	 * <p>
	 * add a new stick data to sticks
	 * </p>
	 * <p>
	 * 新しいスティックデータを追加する
	 * </p>
	 * <p>
	 * 追加一条新数据
	 * </p>
	 * 
	 * @param entity
	 *            <p>
	 *            data
	 *            </p>
	 *            <p>
	 *            データ
	 *            </p>
	 *            <p>
	 *            新数据
	 *            </p>
	 */
	public void addData(IStickEntity entity) {
		if (null != entity) {
			// add
			stickData.add(entity);
			if (this.maxValue < entity.getHigh()) {
				this.maxValue = 100 + ((int) entity.getHigh()) / 100 * 100;
			}

		}
	}

	/**
	 * @return the displayFrom
	 */
	public int getDisplayFrom() {
		return displayFrom;
	}

	/**
	 * @param displayFrom
	 *            the displayFrom to set
	 */
	public void setDisplayFrom(int displayFrom) {
		this.displayFrom = displayFrom;
	}

	/**
	 * @return the displayNumber
	 */
	public int getDisplayNumber() {
		return displayNumber;
	}

	/**
	 * @param displayNumber
	 *            the displayNumber to set
	 */
	public void setDisplayNumber(int displayNumber) {
		this.displayNumber = displayNumber;
	}

	/**
	 * @return the minDisplayNumber
	 */
	public int getMinDisplayNumber() {
		return minDisplayNumber;
	}

	/**
	 * @param minDisplayNumber
	 *            the minDisplayNumber to set
	 */
	public void setMinDisplayNumber(int minDisplayNumber) {
		this.minDisplayNumber = minDisplayNumber;
	}

	/**
	 * @return the zoomBaseLine
	 */
	public int getZoomBaseLine() {
		return zoomBaseLine;
	}

	/**
	 * @param zoomBaseLine
	 *            the zoomBaseLine to set
	 */
	public void setZoomBaseLine(int zoomBaseLine) {
		this.zoomBaseLine = zoomBaseLine;
	}

	/**
	 * @return the stickBorderColor
	 */
	public int getStickBorderColor() {
		return stickBorderColor;
	}

	/**
	 * @param stickBorderColor
	 *            the stickBorderColor to set
	 */
	public void setStickBorderColor(int stickBorderColor) {
		this.stickBorderColor = stickBorderColor;
	}

	/**
	 * @return the stickFillColor
	 */
	public int getStickFillColor() {
		return stickFillColor;
	}

	/**
	 * @param stickFillColor
	 *            the stickFillColor to set
	 */
	public void setStickFillColor(int stickFillColor) {
		this.stickFillColor = stickFillColor;
	}

	/**
	 * @return the stickData
	 */
	public IChartData<IStickEntity> getStickData() {
		return stickData;
	}

	/**
	 * @param stickData
	 *            the stickData to set
	 */
	public void setStickData(IChartData<IStickEntity> stickData) {
		this.stickData = stickData;
	}

	/**
	 * @return the maxValue
	 */
	public double getMaxValue() {
		return maxValue;
	}

	/**
	 * @param maxValue
	 *            the maxValue to set
	 */
	public void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
	}

	/**
	 * @return the minValue
	 */
	public double getMinValue() {
		return minValue;
	}

	/**
	 * @param minValue
	 *            the minValue to set
	 */
	public void setMinValue(double minValue) {
		this.minValue = minValue;
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
	
	/**
	 * @param listener the OnZoomGestureListener to set
	 */
	public void setOnZoomGestureListener(OnZoomGestureListener listener) {
		this.onZoomGestureListener = listener;
	}
	
	/**
	 * @param listener the OnSlipGestureListener to set
	 */
	public void setOnSlipGestureListener(OnSlipGestureListener listener) {
		this.onSlipGestureListener = listener;
	}

	/**
	 * @return the stickAlignType
	 */
	public int getStickAlignType() {
		return stickAlignType;
	}

	/**
	 * @param stickAlignType the stickAlignType to set
	 */
	public void setStickAlignType(int stickAlignType) {
		this.stickAlignType = stickAlignType;
	}

	/**
	 * @return the autoCalcValueRange
	 */
	public boolean isAutoCalcValueRange() {
		return autoCalcValueRange;
	}

	/**
	 * @param autoCalcValueRange the autoCalcValueRange to set
	 */
	public void setAutoCalcValueRange(boolean autoCalcValueRange) {
		this.autoCalcValueRange = autoCalcValueRange;
	}

	/**
	 * @return the dataMultiple
	 */
	public int getDataMultiple() {
		return dataMultiple;
	}

	/**
	 * @param dataMultiple the dataMultiple to set
	 */
	public void setDataMultiple(int dataMultiple) {
		this.dataMultiple = dataMultiple;
	}

	/**
	 * @return the axisYDecimalFormat
	 */
	public String getAxisYDecimalFormat() {
		return axisYDecimalFormat;
	}

	/**
	 * @param axisYDecimalFormat the axisYDecimalFormat to set
	 */
	public void setAxisYDecimalFormat(String axisYDecimalFormat) {
		this.axisYDecimalFormat = axisYDecimalFormat;
	}

	/**
	 * @return the axisXDateTargetFormat
	 */
	public String getAxisXDateTargetFormat() {
		return axisXDateTargetFormat;
	}

	/**
	 * @param axisXDateTargetFormat the axisXDateTargetFormat to set
	 */
	public void setAxisXDateTargetFormat(String axisXDateTargetFormat) {
		this.axisXDateTargetFormat = axisXDateTargetFormat;
	}

	/**
	 * @return the axisXDateSourceFormat
	 */
	public String getAxisXDateSourceFormat() {
		return axisXDateSourceFormat;
	}

	/**
	 * @param axisXDateSourceFormat the axisXDateSourceFormat to set
	 */
	public void setAxisXDateSourceFormat(String axisXDateSourceFormat) {
		this.axisXDateSourceFormat = axisXDateSourceFormat;
	}

	/**
	 * @return the defaultDataMultiple
	 */
	public static int getDefaultDataMultiple() {
		return DEFAULT_DATA_MULTIPLE;
	}
}
