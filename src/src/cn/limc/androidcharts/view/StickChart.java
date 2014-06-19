/*
 * StickChart.java
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

import cn.limc.androidcharts.common.IZoomable;
import cn.limc.androidcharts.entity.IChartData;
import cn.limc.androidcharts.entity.IMeasurable;
import cn.limc.androidcharts.entity.IStickEntity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * <p>
 * StickChart is a kind of graph that draw the sticks on a GridChart if you want
 * display some moving average lines on this graph, please use see MAStickChart
 * for more information.
 * </p>
 * <p>
 * StickChartはGridChartの表面でスティックを書いたラインチャードです。移動平均線など
 * 分析線がお使いしたい場合、MAStickChartにご参照ください。
 * </p>
 * <p>
 * StickChart是在GridChart上绘制柱状数据的图表、如果需要支持显示均线，请参照 MAStickChart。
 * </p>
 * 
 * @author limc
 * @version v1.0 2011/05/30 14:58:59
 * 
 */
public class StickChart extends PeriodDataGridChart implements IZoomable{		

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
	protected int stickBorderColor = DEFAULT_STICK_BORDER_COLOR;

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
	protected int stickFillColor = DEFAULT_STICK_FILL_COLOR;

	public static final int DEFAULT_STICK_SPACING = 1;
	

	/**
	 * <p>
	 * max number of sticks
	 * </p>
	 * <p>
	 * スティックの最大表示数
	 * </p>
	 * <p>
	 * 柱条的最大表示数
	 * </p>
	 */
	protected int maxSticksNum;
	
	protected int minDisplayNum = MINI_DISPLAY_NUM;

	protected int stickSpacing = DEFAULT_STICK_SPACING;
	
	protected OnZoomGestureListener onZoomGestureListener;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @param context
	 * 
	 * @see cn.limc.androidcharts.view.AbstractBaseChart#BaseChart(Context)
	 */
	public StickChart(Context context) {
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
	 * @see cn.limc.androidcharts.view.AbstractBaseChart#BaseChart(Context,
	 * AttributeSet, int)
	 */
	public StickChart(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @param context
	 * 
	 * @param attrs
	 * 
	 * @see cn.limc.androidcharts.view.AbstractBaseChart#BaseChart(Context,
	 * AttributeSet)
	 */
	public StickChart(Context context, AttributeSet attrs) {
		super(context, attrs);
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
	protected void drawSticks(Canvas canvas) {
		if (null == stickData) {
			return;
		}
		if (stickData.size() <= 0) {
			return;
		}

		Paint mPaintStick = new Paint();
		mPaintStick.setColor(stickFillColor);

		float stickWidth = getDataQuadrantPaddingWidth() / getDisplayNumber()
				- stickSpacing;

		if (axisYPosition == AXIS_Y_POSITION_LEFT) {

			float stickX = getDataQuadrantPaddingStartX();

			for (int i = 0; i < stickData.size(); i++) {
				IMeasurable stick = stickData.get(i);

				float highY = (float) ((1f - (stick.getHigh() - minValue)
						/ (maxValue - minValue))
						* (getDataQuadrantPaddingHeight()) + getDataQuadrantPaddingStartY());
				float lowY = (float) ((1f - (stick.getLow() - minValue)
						/ (maxValue - minValue))
						* (getDataQuadrantPaddingHeight()) + getDataQuadrantPaddingStartY());

				if (stickWidth >= 2f) {
					canvas.drawRect(stickX, highY, stickX + stickWidth, lowY,
							mPaintStick);
				} else {
					canvas.drawLine(stickX, highY, stickX, lowY, mPaintStick);
				}

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

				if (stickWidth >= 2f) {
					canvas.drawRect(stickX, highY, stickX + stickWidth, lowY,
							mPaintStick);
				} else {
					canvas.drawLine(stickX, highY, stickX, lowY, mPaintStick);
				}

				// next x
				stickX = stickX - stickSpacing - stickWidth;
			}
		}

	}

	private float olddistance;
	private float newdistance;

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
		case MotionEvent.ACTION_DOWN:
			if (event.getPointerCount() == 1) {
				touchMode = TOUCH_MODE_SINGLE;
				touchPoint = calcTouchedPoint(event.getX(), event.getY());
				if (onTouchGestureListener != null) {
					onTouchGestureListener.onTouchDown(touchPoint,
							getSelectedIndex());
				}
				super.postInvalidate();
				// Notifier
				super.notifyEventAll(this);
			}
			break;
		case MotionEvent.ACTION_UP:
			touchMode = TOUCH_MODE_NONE;
			if (event.getPointerCount() == 1) {
				touchPoint = calcTouchedPoint(event.getX(), event.getY());
				if (onTouchGestureListener != null) {
					onTouchGestureListener.onTouchUp(touchPoint,
							getSelectedIndex());
				}
				super.postInvalidate();
				// Notifier
				super.notifyEventAll(this);
			}
			break;
		case MotionEvent.ACTION_POINTER_UP:
			touchMode = TOUCH_MODE_NONE;
			break;
		case MotionEvent.ACTION_POINTER_DOWN:
			olddistance = calcDistance(event);
			if (olddistance > MIN_LENGTH) {
				touchMode = TOUCH_MODE_MULTI;
			}
			break;
		case MotionEvent.ACTION_MOVE:
			if (touchMode == TOUCH_MODE_MULTI) {
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
					// Notifier
					super.notifyEventAll(this);
				}
			} else {
				// single touch point moved
				if (event.getPointerCount() == 1) {
					float moveXdistance = Math.abs(event.getX() - touchPoint.x);
					float moveYdistance = Math.abs(event.getY() - touchPoint.y);
					if (moveXdistance > TOUCH_MOVE_MIN_DISTANCE || moveYdistance > TOUCH_MOVE_MIN_DISTANCE) {
//						touchPoint = new PointF(event.getX(), event.getY());
						touchPoint = calcTouchedPoint(event.getX(), event.getY());
						// call back to listener
						if (onTouchGestureListener != null) {
							onTouchGestureListener.onTouchMoved(touchPoint,
									getSelectedIndex());
						}
					}
					// redraw
					super.postInvalidate();
					// Notifier
					super.notifyEventAll(this);
				}
			}
			break;
		}
		return true;
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
		if (getDisplayNumber() > getMinDisplayNumber()) {
			setDisplayNumber(getDisplayNumber() - ZOOM_STEP);;
		}
		
		//Listener
		if (onZoomGestureListener != null) {
			onZoomGestureListener.onZoom(ZOOM_IN, getDisplayFrom(), getDisplayNumber());
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
		if (getDisplayNumber() < stickData.size() - 1 - ZOOM_STEP) {
			setDisplayNumber(getDisplayNumber() + ZOOM_STEP);
		}
		
		//Listener
		if (onZoomGestureListener != null) {
			onZoomGestureListener.onZoom(ZOOM_OUT, getDisplayFrom(), getDisplayNumber());
		}
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
	 * @return the maxSticksNum
	 */
	@Deprecated
	public int getMaxSticksNum() {
		return maxSticksNum;
	}

	/**
	 * @param maxSticksNum
	 *            the maxSticksNum to set
	 */
	@Deprecated
	public void setMaxSticksNum(int maxSticksNum) {
		this.maxSticksNum = maxSticksNum;
	}
	
	/**
	 * @param listener the OnZoomGestureListener to set
	 */
	public void setOnZoomGestureListener(OnZoomGestureListener listener) {
		this.onZoomGestureListener = listener;
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

	/**
	 * @return the bindCrossLinesToStick
	 */
	public int getBindCrossLinesToStick() {
		return bindCrossLinesToStick;
	}

	/**
	 * @param bindCrossLinesToStick the bindCrossLinesToStick to set
	 */
	public void setBindCrossLinesToStick(int bindCrossLinesToStick) {
		this.bindCrossLinesToStick = bindCrossLinesToStick;
	}

	/* (non-Javadoc)
	 * 
	 * @return 
	 * @see cn.limc.androidcharts.common.IDataCursor#displayFrom() 
	 */
	public int getDisplayFrom() {
		if (axisYPosition == AXIS_Y_POSITION_LEFT) {
			return 0;
		}else{
			return stickData.size() - maxSticksNum;
		}
	}

	/* (non-Javadoc)
	 * 
	 * @return 
	 * @see cn.limc.androidcharts.common.IDataCursor#displayNumber() 
	 */
	public int getDisplayNumber() {
		return maxSticksNum;
	}

	/* (non-Javadoc)
	 * 
	 * @return 
	 * @see cn.limc.androidcharts.common.IDataCursor#displayTo() 
	 */
	public int getDisplayTo() {
		if (axisYPosition == AXIS_Y_POSITION_LEFT) {
			return maxSticksNum;
		}else{
			return stickData.size() - 1;
		}
	}

	/* (non-Javadoc)
	 * 
	 * @param displayFrom 
	 * @see cn.limc.androidcharts.common.IDataCursor#setDisplayFrom(int) 
	 */
	public void setDisplayFrom(int displayFrom) {
		//TODO
	}

	/* (non-Javadoc)
	 * 
	 * @param displayNumber 
	 * @see cn.limc.androidcharts.common.IDataCursor#setDisplayNumber(int) 
	 */
	public void setDisplayNumber(int displayNumber) {
		maxSticksNum = displayNumber;
	}

	/* (non-Javadoc)
	 * 
	 * @param displayTo 
	 * @see cn.limc.androidcharts.common.IDataCursor#setDisplayTo(int) 
	 */
	public void setDisplayTo(int displayTo) {
		//TODO
	}

	/* (non-Javadoc)
	 * 
	 * @return 
	 * @see cn.limc.androidcharts.common.IDataCursor#getMinDisplayNumber() 
	 */
	public int getMinDisplayNumber() {
		return minDisplayNum;
	}

	/* (non-Javadoc)
	 * 
	 * @param minDisplayNumber 
	 * @see cn.limc.androidcharts.common.IDataCursor#getMinDisplayNumber(int) 
	 */
	public void setMinDisplayNumber(int minDisplayNumber) {
		this.minDisplayNum = minDisplayNumber;
	}
}
