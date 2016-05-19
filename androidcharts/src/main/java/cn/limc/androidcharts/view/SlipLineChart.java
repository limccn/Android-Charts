/*
 * SlipLineChart.java
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

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.view.MotionEvent;

import cn.limc.androidcharts.common.IFlexableGrid;
import cn.limc.androidcharts.entity.DateValueEntity;
import cn.limc.androidcharts.entity.LineEntity;
import cn.limc.androidcharts.event.IGestureDetector;
import cn.limc.androidcharts.event.ISlipable;
import cn.limc.androidcharts.event.IZoomable;
import cn.limc.androidcharts.event.LongPressSlipGestureDetector;
import cn.limc.androidcharts.event.OnSlipGestureListener;
import cn.limc.androidcharts.event.OnZoomGestureListener;
import cn.limc.androidcharts.event.SlipGestureDetector;


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
 * @version v1.0 2014/01/21 14:20:35
 * 
 */
public class SlipLineChart extends LineChart implements IZoomable,ISlipable {
	
//	public static final int DEFAULT_LINE_ALIGN_TYPE = IFlexableGrid.ALIGN_TYPE_CENTER;

//	public static final int DEFAULT_DISPLAY_FROM = 0;
//	public static final int DEFAULT_DISPLAY_NUMBER = 50;
//	public static final int DEFAULT_MIN_DISPLAY_NUMBER = 20;
//	public static final int DEFAULT_ZOOM_BASE_LINE = ZOOM_BASE_LINE_CENTER;

//	protected int getDisplayFrom() = DEFAULT_DISPLAY_FROM;
//	protected int getDisplayNumber() = DEFAULT_DISPLAY_NUMBER;
//	protected int mingetDisplayNumber() = DEFAULT_MIN_DISPLAY_NUMBER;
//	protected int zoomBaseLine = DEFAULT_ZOOM_BASE_LINE;

	/**
	 * <p>
	 * data to draw lines
	 * </p>
	 * <p>
	 * ラインを書く用データ
	 * </p>
	 * <p>
	 * 绘制线条用的数据
	 * </p>
	 */
//	protected List<LineEntity<DateValueEntity>> linesData;

//	/**
//	 * <p>
//	 * min value of Y axis
//	 * </p>
//	 * <p>
//	 * Y軸の最小値
//	 * </p>
//	 * <p>
//	 * Y的最小表示值
//	 * </p>
//	 */
//	protected double minValue;
//
//	/**
//	 * <p>
//	 * max value of Y axis
//	 * </p>
//	 * <p>
//	 * Y軸の最大値
//	 * </p>
//	 * <p>
//	 * Y的最大表示值
//	 * </p>
//	 */
//	protected double maxValue;
	
//	protected int lineAlignType = DEFAULT_LINE_ALIGN_TYPE;

	protected OnSlipGestureListener onSlipGestureListener = new OnSlipGestureListener();
	
	protected IGestureDetector slipGestureDetector = new LongPressSlipGestureDetector<ISlipable>(this);

	protected boolean detectSlipEvent = true;

	/*
	 * (non-Javadoc)
	 * 
	 * @param context
	 * 
	 * @see cn.limc.androidcharts.view.GridChart#GridChart(Context)
	 */
	public SlipLineChart(Context context) {
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
	public SlipLineChart(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @param context
	 * 
	 * @param attrs
	 * 
	 * 
	 * 
	 * @see cn.limc.androidcharts.view.GridChart#GridChart(Context,
	 * AttributeSet)
	 */
	public SlipLineChart(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

//	protected void calcDataValueRange() {
//		double maxValue = Double.MIN_VALUE;
//		double minValue = Double.MAX_VALUE;
//		// 逐条输出MA线
//		for (int i = 0; i < this.linesData.size(); i++) {
//			LineEntity<DateValueEntity> line = this.linesData.get(i);
//			if (line != null && line.getLineData().size() > 0) {
//				// 判断显示为方柱或显示为线条
//				for (int j = getDisplayFrom(); j < getDisplayTo(); j++) {
//					DateValueEntity lineData = line.getLineData().get(j);
//					if (lineData.getValue() < minValue) {
//						minValue = lineData.getValue();
//					}
//
//					if (lineData.getValue() > maxValue) {
//						maxValue = lineData.getValue();
//					}
//
//				}
//			}
//		}
//
//		this.maxValue = maxValue;
//		this.minValue = minValue;
//	}
//
//	protected void calcValueRangePaddingZero() {
//		double maxValue = this.maxValue;
//		double minValue = this.minValue;
//
//		if ((long) maxValue > (long) minValue) {
//			if ((maxValue - minValue) < 10. && minValue > 1.) {
//				this.maxValue = (long) (maxValue + 1);
//				this.minValue = (long) (minValue - 1);
//			} else {
//				this.maxValue = (long) (maxValue + (maxValue - minValue) * 0.1);
//				this.minValue = (long) (minValue - (maxValue - minValue) * 0.1);
//
//				if (this.minValue < 0) {
//					this.minValue = 0;
//				}
//			}
//		} else if ((long) maxValue == (long) minValue) {
//			if (maxValue <= 10 && maxValue > 1) {
//				this.maxValue = maxValue + 1;
//				this.minValue = minValue - 1;
//			} else if (maxValue <= 100 && maxValue > 10) {
//				this.maxValue = maxValue + 10;
//				this.minValue = minValue - 10;
//			} else if (maxValue <= 1000 && maxValue > 100) {
//				this.maxValue = maxValue + 100;
//				this.minValue = minValue - 100;
//			} else if (maxValue <= 10000 && maxValue > 1000) {
//				this.maxValue = maxValue + 1000;
//				this.minValue = minValue - 1000;
//			} else if (maxValue <= 100000 && maxValue > 10000) {
//				this.maxValue = maxValue + 10000;
//				this.minValue = minValue - 10000;
//			} else if (maxValue <= 1000000 && maxValue > 100000) {
//				this.maxValue = maxValue + 100000;
//				this.minValue = minValue - 100000;
//			} else if (maxValue <= 10000000 && maxValue > 1000000) {
//				this.maxValue = maxValue + 1000000;
//				this.minValue = minValue - 1000000;
//			} else if (maxValue <= 100000000 && maxValue > 10000000) {
//				this.maxValue = maxValue + 10000000;
//				this.minValue = minValue - 10000000;
//			}
//		} else {
//			this.maxValue = 0;
//			this.minValue = 0;
//		}
//	}
//
//	protected void calcValueRangeFormatForAxis() {
//		int rate = dataMultiple ;
//
////		if (this.maxValue < 3000) {
////			rate = 1;
////		} else if (this.maxValue >= 3000 && this.maxValue < 5000) {
////			rate = 5;
////		} else if (this.maxValue >= 5000 && this.maxValue < 30000) {
////			rate = 10;
////		} else if (this.maxValue >= 30000 && this.maxValue < 50000) {
////			rate = 50;
////		} else if (this.maxValue >= 50000 && this.maxValue < 300000) {
////			rate = 100;
////		} else if (this.maxValue >= 300000 && this.maxValue < 500000) {
////			rate = 500;
////		} else if (this.maxValue >= 500000 && this.maxValue < 3000000) {
////			rate = 1000;
////		} else if (this.maxValue >= 3000000 && this.maxValue < 5000000) {
////			rate = 5000;
////		} else if (this.maxValue >= 5000000 && this.maxValue < 30000000) {
////			rate = 10000;
////		} else if (this.maxValue >= 30000000 && this.maxValue < 50000000) {
////			rate = 50000;
////		} else {
////			rate = 100000;
////		}
//
//		// 等分轴修正
//		if (simpleGrid.getLatitudeNum() > 0 && rate > 1
//				&& (long) (this.minValue) % rate != 0) {
//			// 最大值加上轴差
//			this.minValue = (long) this.minValue
//					- ((long) (this.minValue) % rate);
//		}
//		// 等分轴修正
//		if (simpleGrid.getLatitudeNum() > 0
//				&& (long) (this.maxValue - this.minValue)
//						% (simpleGrid.getLatitudeNum() * rate) != 0) {
//			// 最大值加上轴差
//			this.maxValue = (long) this.maxValue
//					+ (simpleGrid.getLatitudeNum() * rate)
//					- ((long) (this.maxValue - this.minValue) % (simpleGrid.getLatitudeNum() * rate));
//		}
//	}

//	protected void calcValueRange() {
//		if (null == this.linesData) {
//			this.maxValue = 0;
//			this.minValue = 0;
//			return;
//		}
//		if (this.linesData.size() > 0) {
//			this.calcDataValueRange();
//			this.calcValueRangePaddingZero();
//		} else {
//			this.maxValue = 0;
//			this.minValue = 0;
//		}
//		this.calcValueRangeFormatForAxis();
//	}

//	/*
//	 * (non-Javadoc)
//	 *
//	 * <p>Called when is going to draw this chart<p> <p>チャートを書く前、メソッドを呼ぶ<p>
//	 * <p>绘制图表时调用<p>
//	 *
//	 * @param canvas
//	 *
//	 * @see android.view.View#onDraw(android.graphics.Canvas)
//	 */
//	@Override
//	protected void onDraw(Canvas canvas) {
//		initAxisY();
//		initAxisX();
//
//		super.onDraw(canvas);
//
//	}
//
//	@Override
//	public void drawData(Canvas canvas){
//		super.drawData(canvas);
//		// draw lines
//		drawLines(canvas);
//	}

//	/*
//	 * (non-Javadoc)
//	 *
//	 * @param value
//	 *
//	 * @see cn.limc.androidcharts.view.GridChart#getAxisXGraduate(Object)
//	 */
//	@Override
//	public String getAxisXGraduate(Object value) {
//		float graduate = Float.valueOf(super.getAxisXGraduate(value));
//		int index = (int) Math.floor(graduate * getDisplayNumber());
//
//		if (index >= getDisplayNumber()) {
//			index = getDisplayNumber() - 1;
//		} else if (index < 0) {
//			index = 0;
//		}
//		index = index + getDisplayFrom();
//
//		if (null == this.linesData) {
//			return "";
//		}
//		LineEntity<DateValueEntity> line = (LineEntity<DateValueEntity>) linesData
//				.get(0);
//		if (line == null) {
//			return "";
//		}
//		if (line.isDisplay() == false) {
//			return "";
//		}
//		List<DateValueEntity> lineData = line.getLineData();
//		if (lineData == null) {
//			return "";
//		}
//
//		return String.valueOf(lineData.get(index).getDate());
//	}

//	/*
//	 * (non-Javadoc)
//	 *
//	 * @param value
//	 *
//	 * @see cn.limc.androidcharts.view.GridChart#getAxisYGraduate(Object)
//	 */
//	@Override
//	public String getAxisYGraduate(Object value) {
//		float graduate = Float.valueOf(super.getAxisYGraduate(value));
//		return String.valueOf((int) Math.floor(graduate * (maxValue - minValue)
//				+ minValue));
//	}
//
//
//	public float longitudePostOffset(){
//		if (lineAlignType == IFlexableGrid.ALIGN_TYPE_CENTER) {
//			float lineLength = dataQuadrant.getPaddingWidth() / getDisplayNumber();
//			return (this.dataQuadrant.getPaddingWidth() - lineLength)/ (simpleGrid.getLongitudeTitles().size() - 1);
//	    }else{
//			return this.dataQuadrant.getPaddingWidth()/ (simpleGrid.getLongitudeTitles().size() - 1);
//	    }
//	}
//
//	public float longitudeOffset(){
//		if (lineAlignType == IFlexableGrid.ALIGN_TYPE_CENTER) {
//			float lineLength = dataQuadrant.getPaddingWidth() / getDisplayNumber();
//			return dataQuadrant.getPaddingStartX() + lineLength / 2;
//		}else{
//			return dataQuadrant.getPaddingStartX();
//		}
//	}
	
//	/**
//	 * <p>
//	 * initialize degrees on Y axis
//	 * </p>
//	 * <p>
//	 * Y軸の目盛を初期化
//	 * </p>
//	 * <p>
//	 * 初始化Y轴的坐标值
//	 * </p>
//	 */
//	protected void initAxisY() {
//		this.calcValueRange();
//		List<String> titleY = new ArrayList<String>();
//		float average = (int) ((maxValue - minValue) / simpleGrid.getLatitudeNum());
//		;
//		// calculate degrees on Y axis
//		for (int i = 0; i < simpleGrid.getLatitudeNum(); i++) {
//			String value = String.valueOf((int) Math.floor(minValue + i
//					* average));
////			if (value.length() < super.getLatitudeMaxTitleLength()) {
////				while (value.length() < super.getLatitudeMaxTitleLength()) {
////					value = " " + value;
////				}
////			}
//			titleY.add(value);
//		}
//		// calculate last degrees by use max value
//		String value = String.valueOf((int) Math.floor(((int) maxValue)));
////		if (value.length() < super.getLatitudeMaxTitleLength()) {
////			while (value.length() < super.getLatitudeMaxTitleLength()) {
////				value = " " + value;
////			}
////		}
//		titleY.add(value);
//
//		simpleGrid.setLatitudeTitles(titleY);
//	}

//	/**
//	 * <p>
//	 * initialize degrees on Y axis
//	 * </p>
//	 * <p>
//	 * Y軸の目盛を初期化
//	 * </p>
//	 * <p>
//	 * 初始化Y轴的坐标值
//	 * </p>
//	 */
//	protected void initAxisX() {
//		List<String> titleX = new ArrayList<String>();
//		if (null != linesData && linesData.size() > 0) {
//			float average = getDisplayNumber() / simpleGrid.getLongitudeNum();
//			for (int i = 0; i < simpleGrid.getLongitudeNum(); i++) {
//				int index = (int) Math.floor(i * average);
//				if (index > getDisplayNumber() - 1) {
//					index = getDisplayNumber() - 1;
//				}
//				index = index + getDisplayFrom();
//				titleX.add(String.valueOf(
//						linesData.get(0).getLineData().get(index).getDate())
//						.substring(4));
//			}
//			titleX.add(String.valueOf(
//					linesData.get(0).getLineData()
//							.get(getDisplayTo()).getDate())
//					.substring(4));
//		}
//		simpleGrid.setLongitudeTitles(titleX);
//	}

//	/**
//	 * <p>
//	 * draw lines
//	 * </p>
//	 * <p>
//	 * ラインを書く
//	 * </p>
//	 * <p>
//	 * 绘制线条
//	 * </p>
//	 *
//	 * @param canvas
//	 */
//	protected void drawLines(Canvas canvas) {
//		if (null == this.linesData) {
//			return;
//		}
//		if (0 == this.linesData.size()) {
//			return;
//		}
//		// distance between two points
//		float lineLength;
//		// start point‘s X
//		float startX;
//
//		// draw lines
//		for (int i = 0; i < linesData.size(); i++) {
//			LineEntity<DateValueEntity> line = (LineEntity<DateValueEntity>) linesData
//					.get(i);
//			if (line == null) {
//				continue;
//			}
//			if (line.isDisplay() == false) {
//				continue;
//			}
//			List<DateValueEntity> lineData = line.getLineData();
//			if (lineData == null) {
//				continue;
//			}
//			Paint mPaint = new Paint();
//			mPaint.setColor(line.getLineColor());
//			mPaint.setAntiAlias(true);
//			// set start point’s X
//			if (lineAlignType == IFlexableGrid.ALIGN_TYPE_CENTER) {
//                lineLength= (dataQuadrant.getPaddingWidth() / getDisplayNumber());
//                startX = dataQuadrant.getPaddingStartX() + lineLength / 2;
//            }else {
//                lineLength= (dataQuadrant.getPaddingWidth() / (getDisplayNumber() - 1));
//                startX = dataQuadrant.getPaddingStartX();
//            }
//			// start point
//			PointF ptFirst = null;
//			for (int j = getDisplayFrom(); j < getDisplayTo(); j++) {
//				float value = lineData.get(j).getValue();
//				// calculate Y
//				float valueY = (float) ((1f - (value - minValue)
//						/ (maxValue - minValue)) * dataQuadrant.getPaddingHeight())
//						+ dataQuadrant.getPaddingStartY();
//
//				// if is not last point connect to previous point
//				if (j > getDisplayFrom()) {
//					canvas.drawLine(ptFirst.x, ptFirst.y, startX, valueY,
//							mPaint);
//				}
//				// reset
//				ptFirst = new PointF(startX, valueY);
//				startX = startX + lineLength;
//			}
//		}
//	}


//	protected float olddistance = 0f;
//	protected float newdistance = 0f;
//
//	protected PointF startPointA;
//	protected PointF startPointB;

//	@Override
//	public boolean onTouchEvent(MotionEvent event) {
//		//valid
//		if (!isValidTouchPoint(event.getX(),event.getY())) {
//			return false;
//		}
//
//		if (null == linesData || linesData.size() == 0) {
//			return false;
//		}
//
//		return slipGestureDetector.onTouchEvent(event);
//	}

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
			return (float) Math.sqrt(x * x + y * y);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @param event
	 * @return
	 * @see cn.limc.androidcharts.view.StickChart#onTouchEvent(android.view.MotionEvent)
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// valid
		if (!isValidTouchPoint(event.getX(), event.getY())) {
			return false;
		}
		if (null == linesData || linesData.size() == 0) {
			return false;
		}
		if (detectSlipEvent) {
			return slipGestureDetector.onTouchEvent(event);
		}else{
			return true;
		}
	}

	public void moveRight() {
		if(this.forward(SLIP_STEP)) {
			this.postInvalidate();
		}
//		int dataSize = linesData.get(0).getLineData().size();
//		if (getDisplayTo() < dataSize - SLIP_STEP) {
//			getDisplayFrom() = getDisplayFrom() + SLIP_STEP;
//		} else {
//			getDisplayFrom() = dataSize - getDisplayNumber();
//		}
//
//		// 处理getDisplayFrom()越界
//		if (getDisplayTo() >= dataSize) {
//			getDisplayFrom() = dataSize - getDisplayNumber();
//		}
//
//		this.postInvalidate();
		
//		//Listener
//		if (onSlipGestureListener != null) {
//			onSlipGestureListener.onSlip(SLIP_DIRECTION_RIGHT, getDisplayFrom(), getDisplayNumber());
//		}

		if (onDisplayCursorListener != null) {
			onDisplayCursorListener.onCursorChanged(this,getDisplayFrom(), getDisplayNumber());
		}
	}

	public void moveLeft() {
		if(this.backward(SLIP_STEP)) {
			this.postInvalidate();
		}
//		int dataSize = linesData.get(0).getLineData().size();
		
//		if (getDisplayFrom() <= SLIP_STEP) {
//			getDisplayFrom() = 0;
//		} else if (getDisplayFrom() > SLIP_STEP) {
//			getDisplayFrom() = getDisplayFrom() - SLIP_STEP;
//		} else {
//
//		}
//
//		// 处理getDisplayFrom()越界
//		if (getDisplayTo() >= dataSize) {
//			getDisplayFrom() = dataSize - getDisplayNumber();
//		}
		
//		this.postInvalidate();
		
//		//Listener
//		if (onSlipGestureListener != null) {
//			onSlipGestureListener.onSlip(SLIP_DIRECTION_LEFT, getDisplayFrom(), getDisplayNumber());
//		}

		if (onDisplayCursorListener != null) {
			onDisplayCursorListener.onCursorChanged(this,getDisplayFrom(), getDisplayNumber());
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
//	public void zoomIn() {
//		if (getDisplayNumber() > mingetDisplayNumber()) {
//			// 区分缩放方向
//			if (zoomBaseLine == ZOOM_BASE_LINE_CENTER) {
//				getDisplayNumber() = getDisplayNumber() - ZOOM_STEP;
//				getDisplayFrom() = getDisplayFrom() + ZOOM_STEP / 2;
//			} else if (zoomBaseLine == ZOOM_BASE_LINE_LEFT) {
//				getDisplayNumber() = getDisplayNumber() - ZOOM_STEP;
//			} else if (zoomBaseLine == ZOOM_BASE_LINE_RIGHT) {
//				getDisplayNumber() = getDisplayNumber() - ZOOM_STEP;
//				getDisplayFrom() = getDisplayFrom() + ZOOM_STEP;
//			}
//
//			// 处理getDisplayNumber()越界
//			if (getDisplayNumber() < mingetDisplayNumber()) {
//				getDisplayNumber() = mingetDisplayNumber();
//			}
//
//			// 处理getDisplayFrom()越界
//			if (getDisplayTo() >= linesData.get(0).getLineData()
//					.size()) {
//				getDisplayFrom() = linesData.get(0).getLineData().size()
//						- getDisplayNumber();
//			}
			
//			this.postInvalidate();
			
//			//Listener
//			if (onZoomGestureListener != null) {
//				onZoomGestureListener.onZoom(ZOOM_IN, getDisplayFrom(), getDisplayNumber());
//			}
//		}
//	}

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
//	public void zoomOut() {
//		int dataSize = linesData.get(0).getLineData().size();
		
//		if (getDisplayNumber() < dataSize - 1) {
//			if (getDisplayNumber() + ZOOM_STEP > dataSize - 1) {
//				getDisplayNumber() = dataSize - 1;
//				getDisplayFrom() = 0;
//			} else {
//				// 区分缩放方向
//				if (zoomBaseLine == ZOOM_BASE_LINE_CENTER) {
//					getDisplayNumber() = getDisplayNumber() + ZOOM_STEP;
//					if (getDisplayFrom() > ZOOM_STEP / 2) {
//						getDisplayFrom() = getDisplayFrom() - ZOOM_STEP / 2;
//					} else {
//						getDisplayFrom() = 0;
//					}
//				} else if (zoomBaseLine == ZOOM_BASE_LINE_LEFT) {
//					getDisplayNumber() = getDisplayNumber() + ZOOM_STEP;
//				} else if (zoomBaseLine == ZOOM_BASE_LINE_RIGHT) {
//					getDisplayNumber() = getDisplayNumber() + ZOOM_STEP;
//					if (getDisplayFrom() > ZOOM_STEP) {
//						getDisplayFrom() = getDisplayFrom() - ZOOM_STEP;
//					} else {
//						getDisplayFrom() = 0;
//					}
//				}
//			}
//
//			if (getDisplayTo() >= dataSize) {
//				getDisplayNumber() = dataSize - getDisplayFrom();
//			}
			
//			this.postInvalidate();
			
//			//Listener
//			if (onZoomGestureListener != null) {
//				onZoomGestureListener.onZoom(ZOOM_OUT, getDisplayFrom(), getDisplayNumber());
//			}
//		}
//	}

//	public int getDisplayTo() {
//		return getDisplayTo();
//	}
//
//
//	public void setDisplayTo(int displayTo) {
//		// TODO
//	}
//
//	/**
//	 * @return the minValue
//	 */
//	public double getMinValue() {
//		return minValue;
//	}
//
//	/**
//	 * @param minValue
//	 *            the minValue to set
//	 */
//	public void setMinValue(double minValue) {
//		this.minValue = minValue;
//	}
//
//	/**
//	 * @return the maxValue
//	 */
//	public double getMaxValue() {
//		return maxValue;
//	}
//
//	/**
//	 * @param maxValue
//	 *            the maxValue to set
//	 */
//	public void setMaxValue(int maxValue) {
//		this.maxValue = maxValue;
//	}
//
//	/**
//	 * @return the getDisplayFrom()
//	 */
//	public int getDisplayFrom() {
//		return getDisplayFrom();
//	}
//
//	/**
//	 * @param getDisplayFrom()
//	 *            the getDisplayFrom() to set
//	 */
//	public void setgetDisplayFrom()(int getDisplayFrom()) {
//		this.getDisplayFrom() = getDisplayFrom();
//	}
//
//	/**
//	 * @return the getDisplayNumber()
//	 */
//	public int getDisplayNumber() {
//		return getDisplayNumber();
//	}
//
//	/**
//	 * @param getDisplayNumber()
//	 *            the getDisplayNumber() to set
//	 */
//	public void setgetDisplayNumber()(int getDisplayNumber()) {
//		this.getDisplayNumber() = getDisplayNumber();
//	}
//
//	/**
//	 * @return the mingetDisplayNumber()
//	 */
//	public int getMingetDisplayNumber() {
//		return mingetDisplayNumber();
//	}
//
//	/**
//	 * @param mingetDisplayNumber()
//	 *            the mingetDisplayNumber() to set
//	 */
//	public void setMingetDisplayNumber()(int mingetDisplayNumber()) {
//		this.mingetDisplayNumber() = mingetDisplayNumber();
//	}
//
//	/**
//	 * @return the zoomBaseLine
//	 */
//	public int getZoomBaseLine() {
//		return zoomBaseLine;
//	}
//
//	/**
//	 * @param zoomBaseLine
//	 *            the zoomBaseLine to set
//	 */
//	public void setZoomBaseLine(int zoomBaseLine) {
//		this.zoomBaseLine = zoomBaseLine;
//	}

//	/**
//	 * @return the linesData
//	 */
//	public List<LineEntity<DateValueEntity>> getLinesData() {
//		return linesData;
//	}
//
//	/**
//	 * @param linesData
//	 *            the linesData to set
//	 */
//	public void setLinesData(List<LineEntity<DateValueEntity>> linesData) {
//		this.linesData = linesData;
//	}
//
//	/**
//	 * @return the lineAlignType
//	 */
//	public int getLineAlignType() {
//		return lineAlignType;
//	}
//
//	/**
//	 * @param lineAlignType the lineAlignType to set
//	 */
//	public void setLineAlignType(int lineAlignType) {
//		this.lineAlignType = lineAlignType;
//	}

	public boolean isDetectSlipEvent() {
		return detectSlipEvent;
	}

	public void setDetectSlipEvent(boolean detectSlipEvent) {
		this.detectSlipEvent = detectSlipEvent;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @param listener 
	 * @see cn.limc.androidcharts.event.IZoomable#setOnZoomGestureListener(cn.limc.androidcharts.event.OnZoomGestureListener)
	 */
	public void setOnZoomGestureListener(OnZoomGestureListener listener) {
		this.onZoomGestureListener = listener;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @param listener 
	 * @see cn.limc.androidcharts.event.ISlipable#setOnSlipGestureListener(cn.limc.androidcharts.event.OnSlipGestureListener)
	 */
	public void setOnSlipGestureListener(OnSlipGestureListener listener) {
		this.onSlipGestureListener = listener;
	}
	
	/* (non-Javadoc)
	 * 
	 * @return 
	 * @see cn.limc.androidcharts.event.ISlipable#getOnSlipGestureListener() 
	 */
	public OnSlipGestureListener getOnSlipGestureListener() {
		return onSlipGestureListener;
	}

	/* (non-Javadoc)
	 * 
	 * @return 
	 * @see cn.limc.androidcharts.event.IZoomable#getOnZoomGestureListener() 
	 */
	public OnZoomGestureListener getOnZoomGestureListener() {
		return onZoomGestureListener;
	}
}
