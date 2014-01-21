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

import java.util.ArrayList;
import java.util.List;

import cn.limc.androidcharts.entity.StickEntity;

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
public class SlipStickChart extends GridChart {

	public static final int ZOOM_BASE_LINE_CENTER = 0;
	public static final int ZOOM_BASE_LINE_LEFT = 1;
	public static final int ZOOM_BASE_LINE_RIGHT = 2;

	public static final int DEFAULT_DISPLAY_FROM = 0;
	public static final int DEFAULT_DISPLAY_NUMBER = 50;
	public static final int DEFAULT_MIN_DISPLAY_NUMBER = 20;
	public static final int DEFAULT_ZOOM_BASE_LINE = 20;

	protected int displayFrom = DEFAULT_DISPLAY_FROM;
	protected int displayNumber = DEFAULT_DISPLAY_NUMBER;
	protected int minDisplayNumber = DEFAULT_MIN_DISPLAY_NUMBER;
	protected int zoomBaseLine = DEFAULT_ZOOM_BASE_LINE;

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
	private List<StickEntity> stickData;

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
	protected float maxValue;

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
	protected float minValue;

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

		return String.valueOf(stickData.get(index).getDate());
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
		return String.valueOf((int) Math.floor(graduate * (maxValue - minValue)
				+ minValue));
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
//		CandleStickChart candlechart = (CandleStickChart) chart;
		// this. = candlechart.getMaxSticksNum();

		super.setDisplayCrossYOnTouch(false);
		// notifyEvent
		super.notifyEvent(chart);
		// notifyEventAll
		super.notifyEventAll(this);
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
		List<String> TitleX = new ArrayList<String>();
		if (null != stickData) {
			float average = displayNumber / this.getLongitudeNum();
			for (int i = 0; i < this.getLongitudeNum(); i++) {
				int index = (int) Math.floor(i * average);
				if (index > displayNumber - 1) {
					index = displayNumber - 1;
				}
				index = index + displayFrom;
				TitleX.add(String.valueOf(stickData.get(index).getDate())
						.substring(4));
			}
			TitleX.add(String.valueOf(
					stickData.get(displayFrom + displayNumber - 1).getDate())
					.substring(4));
		}
		super.setAxisXTitles(TitleX);
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
		List<String> TitleY = new ArrayList<String>();
		float average = (int) ((maxValue - minValue) / this.getLatitudeNum()) / 100 * 100;
		;
		// calculate degrees on Y axis
		for (int i = 0; i < this.getLatitudeNum(); i++) {
			String value = String.valueOf((int) Math.floor(minValue + i
					* average));
			if (value.length() < super.getAxisYMaxTitleLength()) {
				while (value.length() < super.getAxisYMaxTitleLength()) {
					value = new String(" ") + value;
				}
			}
			TitleY.add(value);
		}
		// calculate last degrees by use max value
		String value = String.valueOf((int) Math
				.floor(((int) maxValue) / 100 * 100));
		if (value.length() < super.getAxisYMaxTitleLength()) {
			while (value.length() < super.getAxisYMaxTitleLength()) {
				value = new String(" ") + value;
			}
		}
		TitleY.add(value);

		super.setAxisYTitles(TitleY);
	}

	protected void drawSticks(Canvas canvas) {
		float stickWidth = ((super.getWidth() - super.getAxisMarginLeft() - super
				.getAxisMarginRight()) / displayNumber) - 1;
		float stickX = super.getAxisMarginLeft() + 1;

		Paint mPaintStick = new Paint();
		mPaintStick.setColor(stickFillColor);

		if (null != stickData) {

			for (int i = displayFrom; i < displayFrom + displayNumber; i++) {
				StickEntity ohlc = stickData.get(i);

				float highY = (float) ((1f - (ohlc.getHigh() - minValue)
						/ (maxValue - minValue))
						* (super.getHeight() - super.getAxisMarginBottom()) - super
						.getAxisMarginTop());
				float lowY = (float) ((1f - (ohlc.getLow() - minValue)
						/ (maxValue - minValue))
						* (super.getHeight() - super.getAxisMarginBottom()) - super
						.getAxisMarginTop());

				// stick or line?
				if (stickWidth >= 2f) {
					canvas.drawRect(stickX, highY, stickX + stickWidth, lowY,
							mPaintStick);
				} else {
					canvas.drawLine(stickX, highY, stickX, lowY, mPaintStick);
				}

				// next x
				stickX = stickX + 1 + stickWidth;
			}
		}
	}

	protected final int NONE = 0;
	protected final int ZOOM = 1;
	protected final int DOWN = 2;

	protected float olddistance = 0f;
	protected float newdistance = 0f;

	protected int TOUCH_MODE;

	protected PointF startPoint;
	protected PointF startPointA;
	protected PointF startPointB;

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (null == stickData || stickData.size() == 0) {
			return false;
		}

		final float MIN_LENGTH = (super.getWidth() / 40) < 5 ? 5 : (super
				.getWidth() / 50);

		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		// 设置拖拉模式
		case MotionEvent.ACTION_DOWN:
			TOUCH_MODE = DOWN;
			if (event.getPointerCount() == 1) {
				startPoint = new PointF(event.getX(), event.getY());
			}
			break;
		case MotionEvent.ACTION_UP:
			startPointA = null;
			startPointB = null;
		case MotionEvent.ACTION_POINTER_UP:
			TOUCH_MODE = NONE;
			startPointA = null;
			startPointB = null;
			return super.onTouchEvent(event);
			// 设置多点触摸模式
		case MotionEvent.ACTION_POINTER_DOWN:
			olddistance = calcDistance(event);
			if (olddistance > MIN_LENGTH) {
				TOUCH_MODE = ZOOM;
				startPointA = new PointF(event.getX(0), event.getY(0));
				startPointB = new PointF(event.getX(1), event.getY(1));
			}
			break;
		case MotionEvent.ACTION_MOVE:
			if (TOUCH_MODE == ZOOM) {
				newdistance = calcDistance(event);
				if (newdistance > MIN_LENGTH) {
					if (startPointA.x >= event.getX(0)
							&& startPointB.x >= event.getX(1)) {
						if (displayFrom + displayNumber + 2 < stickData.size()) {
							displayFrom = displayFrom + 2;
						}
					} else if (startPointA.x <= event.getX(0)
							&& startPointB.x <= event.getX(1)) {
						if (displayFrom > 2) {
							displayFrom = displayFrom - 2;
						}
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
					float moveXdistance = Math.abs(event.getX() - startPoint.x);
					float moveYdistance = Math.abs(event.getY() - startPoint.y);

					if (moveXdistance > 1 || moveYdistance > 1) {

						super.onTouchEvent(event);

						startPoint = new PointF(event.getX(), event.getY());
					}
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
		if (displayNumber > minDisplayNumber) {
			// 区分缩放方向
			if (zoomBaseLine == ZOOM_BASE_LINE_CENTER) {
				displayNumber = displayNumber - 2;
				displayFrom = displayFrom + 1;
			} else if (zoomBaseLine == ZOOM_BASE_LINE_LEFT) {
				displayNumber = displayNumber - 2;
			} else if (zoomBaseLine == ZOOM_BASE_LINE_RIGHT) {
				displayNumber = displayNumber - 2;
				displayFrom = displayFrom + 2;
			}

			// 处理displayNumber越界
			if (displayNumber < minDisplayNumber) {
				displayNumber = minDisplayNumber;
			}

			// 处理displayFrom越界
			if (displayFrom + displayNumber >= stickData.size()) {
				displayFrom = stickData.size() - displayNumber;
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
	protected void zoomOut() {
		if (displayNumber < stickData.size() - 1) {
			if (displayNumber + 2 > stickData.size() - 1) {
				displayNumber = stickData.size() - 1;
				displayFrom = 0;
			} else {
				// 区分缩放方向
				if (zoomBaseLine == ZOOM_BASE_LINE_CENTER) {
					displayNumber = displayNumber + 2;
					if (displayFrom > 1) {
						displayFrom = displayFrom - 1;
					} else {
						displayFrom = 0;
					}
				} else if (zoomBaseLine == ZOOM_BASE_LINE_LEFT) {
					displayNumber = displayNumber + 2;
				} else if (zoomBaseLine == ZOOM_BASE_LINE_RIGHT) {
					displayNumber = displayNumber + 2;
					if (displayFrom > 2) {
						displayFrom = displayFrom - 2;
					} else {
						displayFrom = 0;
					}
				}
			}

			if (displayFrom + displayNumber >= stickData.size()) {
				displayNumber = stickData.size() - displayFrom;
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
	public void addData(StickEntity entity) {
		if (null != entity) {
			// data is null or empty
			if (null == stickData || 0 == stickData.size()) {
				stickData = new ArrayList<StickEntity>();
				this.maxValue = ((int) entity.getHigh()) / 100 * 100;
			}

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
	public List<StickEntity> getStickData() {
		return stickData;
	}

	/**
	 * @param stickData
	 *            the stickData to set
	 */
	public void setStickData(List<StickEntity> stickData) {
		this.stickData = stickData;
	}

	/**
	 * @return the maxValue
	 */
	public float getMaxValue() {
		return maxValue;
	}

	/**
	 * @param maxValue
	 *            the maxValue to set
	 */
	public void setMaxValue(float maxValue) {
		this.maxValue = maxValue;
	}

	/**
	 * @return the minValue
	 */
	public float getMinValue() {
		return minValue;
	}

	/**
	 * @param minValue
	 *            the minValue to set
	 */
	public void setMinValue(float minValue) {
		this.minValue = minValue;
	}
}
