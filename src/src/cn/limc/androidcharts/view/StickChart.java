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

import cn.limc.androidcharts.axis.IAxis;
import cn.limc.androidcharts.common.IDataCursor;
import cn.limc.androidcharts.common.SectionDataCursor;
import cn.limc.androidcharts.entity.IChartData;
import cn.limc.androidcharts.entity.IMeasurable;
import cn.limc.androidcharts.entity.IStickEntity;
import cn.limc.androidcharts.event.IGestureDetector;
import cn.limc.androidcharts.event.IZoomable;
import cn.limc.androidcharts.event.IDisplayCursorListener;
import cn.limc.androidcharts.event.OnZoomGestureListener;
import cn.limc.androidcharts.event.ZoomGestureDetector;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
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

	protected IDataCursor dataCursor = new SectionDataCursor();

	public static final int DEFAULT_STICK_SPACING = 1;


	public static final int DEFAULT_STICK_STROKE_WIDTH = 1;

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
	public static final int DEFAULT_STICK_STROKE_COLOR = Color.WHITE;

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
	public static final int DEFAULT_STICK_FILL_COLOR = Color.BLUE;


	public static final int DEFAULT_DISPLAY_STICK_AS_LINE_NUMBER = 120;
	public static final int DEFAULT_DISPLAY_STICK_AS_LINE_COLOR = Color.WHITE;


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
	protected int stickStrokeColor = DEFAULT_STICK_STROKE_COLOR;
	protected int stickFillColor = DEFAULT_STICK_FILL_COLOR;
	protected int stickStrokeWidth = DEFAULT_STICK_STROKE_WIDTH;
	protected int stickSpacing = DEFAULT_STICK_SPACING;
	
	protected OnZoomGestureListener onZoomGestureListener = new OnZoomGestureListener();
	protected IGestureDetector zoomGestureDetector = new ZoomGestureDetector<IZoomable>(this);
	protected boolean detectZoomEvent = true;
	protected int displayStickAsLineNumber = DEFAULT_DISPLAY_STICK_AS_LINE_NUMBER;
	protected int displayStickAsLineColor = DEFAULT_DISPLAY_STICK_AS_LINE_COLOR;


	protected IDisplayCursorListener onDisplayCursorListener;
	
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

		if (null != stickData) {
			if (stickData.size() > 0) {
				dataCursor.setMaxDisplayNumber(stickData.size());
			}
		}

		if (this.autoCalcValueRange) {
			calcValueRange();
		}
		initAxisY();
		initAxisX();
		super.onDraw(canvas);

	}

	@Override
	public void drawData(Canvas canvas){
		super.drawData(canvas);
		if (getDisplayNumber() > displayStickAsLineNumber){
			drawSticksAsLine(canvas);
		}else{
			drawSticks(canvas);
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
		mPaintStroke.setStrokeWidth(stickStrokeWidth);
		mPaintStroke.setColor(displayStickAsLineColor);

		// start point
		PointF ptFirst = null;

		for (int j = getDisplayFrom(); j < getDisplayTo(); j++) {
			double value = stickData.get(j).getHigh();
			if(isNoneDisplayValue((float)value)){

			}else {
				// calculate Y
				float valueY = (float) ((1f - (value - minValue)
						/ (maxValue - minValue)) * dataQuadrant.getPaddingHeight())
						+ dataQuadrant.getPaddingStartY();

				// if is not last point connect to previous point
				if (j > getDisplayFrom() && ptFirst!=null) {
					canvas.drawLine(ptFirst.x, ptFirst.y, startX, valueY,
							mPaintStroke);
				}
				// reset
				ptFirst = new PointF(startX, valueY);
			}
			startX = startX + stickSpacing + lineLength;
		}
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

//		float stickWidth = dataQuadrant.getPaddingWidth() / getDisplayNumber();
//
//		if (axisY.getPosition() == IAxis.AXIS_Y_POSITION_LEFT) {
//
//			float stickX = dataQuadrant.getPaddingStartX();
//
//			for (int i = 0; i < stickData.size(); i++) {
//				IMeasurable stick = stickData.get(i);
//				StickMole mole = (StickMole)provider.getMole();
//				mole.setUp(this,stick,stickX,stickWidth);
//				mole.draw(canvas);
//				// next x
//				stickX = stickX + stickWidth;
//			}
//		} else {
//			float stickX = dataQuadrant.getPaddingEndX() - stickWidth;
//			for (int i = stickData.size() - 1; i >= 0; i--) {
//				IMeasurable stick = stickData.get(i);
//				StickMole mole = (StickMole)provider.getMole();
//				mole.setUp(this,stick,stickX,stickWidth);
//				mole.draw(canvas);
//				// next x
//				stickX = stickX - stickWidth;
//			}
//		}

		float stickWidth = dataQuadrant.getPaddingWidth() / getDisplayNumber()
				- stickSpacing;
		float stickX = dataQuadrant.getPaddingStartX();


		Paint mPaintFill = new Paint();
		mPaintFill.setStyle(Paint.Style.FILL);
		mPaintFill.setColor(stickFillColor);

		Paint mPaintStroke = new Paint();
		mPaintStroke.setStyle(Paint.Style.STROKE);
		mPaintStroke.setStrokeWidth(stickStrokeWidth);
		mPaintStroke.setColor(stickStrokeColor);

		for (int i = getDisplayFrom(); i < getDisplayTo(); i++) {
			IMeasurable entity = stickData.get(i);

			float highY = (float) ((1f - (entity.getHigh() - minValue)
					/ (maxValue - minValue))
					* (dataQuadrant.getPaddingHeight()) + dataQuadrant.getPaddingStartY());
			float lowY = (float) ((1f - (entity.getLow() - minValue)
					/ (maxValue - minValue))
					* (dataQuadrant.getPaddingHeight()) + dataQuadrant.getPaddingStartY());

			// stick or line?
			if (stickWidth >= 2f) {
				canvas.drawRect(stickX, highY, stickX + stickWidth, lowY,
						mPaintFill);
				canvas.drawRect(stickX, highY, stickX + stickWidth, lowY,
						mPaintStroke);
			} else {
				canvas.drawLine(stickX, highY, stickX, lowY, mPaintStroke);
			}

			// next x
			stickX = stickX + stickSpacing + stickWidth;
		}


//		for (int i = getDisplayFrom(); i < getDisplayTo(); i++) {
//			IMeasurable stick = stickData.get(i);
//
////			StickMole mole = (StickMole)provider.getMole();
////			mole.setUp(this,stick,stickX,stickWidth);
////			mole.draw(canvas);
//
//			// next x
//			stickX = stickX + stickSpacing + stickWidth;
//		}

	}

//	private float olddistance;
//	private float newdistance;

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
		if (detectZoomEvent) {
			return zoomGestureDetector.onTouchEvent(event);
		}else{
			return true;
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
		if(this.stretch(ZOOM_STEP)) {
			this.postInvalidate();
		}
//
		//Listener
		if (onDisplayCursorListener != null) {
			onDisplayCursorListener.onCursorChanged(this,getDisplayFrom(), getDisplayNumber());
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
		if(this.shrink(ZOOM_STEP)) {
			this.postInvalidate();
		}
//		if (getDisplayNumber() < stickData.size() - 1 - ZOOM_STEP) {
//			setgetDisplayNumber()(getDisplayNumber() + ZOOM_STEP);
//			this.postInvalidate();
//		}
//
		//Listener
		if (onDisplayCursorListener != null) {
			onDisplayCursorListener.onCursorChanged(this,getDisplayFrom(), getDisplayNumber());
		}
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

		if (stickData == null) {
			Log.e("","Stick data is nil");
			return;
		}
		int datasize = stickData.size();
		if (datasize == 0) {
			Log.e("","Stick data size is Zero");
			return;
		}

		this.stickData = stickData;

		if (dataCursor.getMinDisplayNumber() > datasize) {
			dataCursor.setMaxDisplayNumber(datasize);
			dataCursor.setDisplayFrom(0);
			dataCursor.setDisplayNumber(datasize);

		}else{
			dataCursor.setMaxDisplayNumber(datasize);
			//右侧显示
			dataCursor.setDisplayFrom(datasize - getDisplayNumber());
		}

		this.maxValue = Integer.MIN_VALUE;
		this.minValue = Integer.MAX_VALUE;
	}


	public IChartData<IStickEntity> getChartData() {
		return this.stickData;
	}

	public IDataCursor getDataCursor(){
		return this.dataCursor;
	}

//	/**
//	 * @return the maxSticksNum
//	 */
//	@Deprecated
//	public int getMaxSticksNum() {
//		return maxSticksNum;
//	}
//
//	/**
//	 * @param maxSticksNum
//	 *            the maxSticksNum to set
//	 */
//	@Deprecated
//	public void setMaxSticksNum(int maxSticksNum) {
//		this.maxSticksNum = maxSticksNum;
//	}

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

//	/* (non-Javadoc)
//	 *
//	 * @return
//	 * @see cn.limc.androidcharts.common.IDataCursor#getDisplayFrom()
//	 */
//	public int getDisplayFrom() {
//		if (axisY.getPosition() == IAxis.AXIS_Y_POSITION_LEFT) {
//			return 0;
//		}else{
//			return stickData.size() - maxSticksNum;
//		}
//	}
//
//	/* (non-Javadoc)
//	 *
//	 * @return
//	 * @see cn.limc.androidcharts.common.IDataCursor#getDisplayNumber()
//	 */
//	public int getDisplayNumber() {
//		return maxSticksNum;
//	}
//
//	/* (non-Javadoc)
//	 *
//	 * @return
//	 * @see cn.limc.androidcharts.common.IDataCursor#displayTo()
//	 */
//	public int getDisplayTo() {
//		if (axisY.getPosition() == IAxis.AXIS_Y_POSITION_LEFT) {
//			return maxSticksNum;
//		}else{
//			return stickData.size() - 1;
//		}
//	}
//
//	/* (non-Javadoc)
//	 *
//	 * @param getDisplayFrom()
//	 * @see cn.limc.androidcharts.common.IDataCursor#setgetDisplayFrom()(int)
//	 */
//	public void setgetDisplayFrom()(int getDisplayFrom()) {
//		//TODO
//	}
//
//	/* (non-Javadoc)
//	 *
//	 * @param getDisplayNumber()
//	 * @see cn.limc.androidcharts.common.IDataCursor#setgetDisplayNumber()(int)
//	 */
//	public void setgetDisplayNumber()(int getDisplayNumber()) {
//		maxSticksNum = getDisplayNumber();
//	}
//
//	/* (non-Javadoc)
//	 *
//	 * @param displayTo
//	 * @see cn.limc.androidcharts.common.IDataCursor#setDisplayTo(int)
//	 */
//	public void setDisplayTo(int displayTo) {
//		//TODO
//	}
//
//	/* (non-Javadoc)
//	 *
//	 * @return
//	 * @see cn.limc.androidcharts.common.IDataCursor#getMingetDisplayNumber()
//	 */
//	public int getMingetDisplayNumber() {
//		return minDisplayNum;
//	}
//
//	/* (non-Javadoc)
//	 *
//	 * @param mingetDisplayNumber()
//	 * @see cn.limc.androidcharts.common.IDataCursor#getMingetDisplayNumber()(int)
//	 */
//	public void setMingetDisplayNumber()(int mingetDisplayNumber()) {
//		this.minDisplayNum = mingetDisplayNumber();
//	}
//


	public boolean isDetectZoomEvent() {
		return detectZoomEvent;
	}

	public void setDetectZoomEvent(boolean detectZoomEvent) {
		this.detectZoomEvent = detectZoomEvent;
	}

	/* (non-Javadoc)
         *
         * @return
         * @see cn.limc.androidcharts.event.IZoomable#getOnZoomGestureListener()
         */
	public OnZoomGestureListener getOnZoomGestureListener() {
		return onZoomGestureListener;
	}
	
	/* (non-Javadoc)
	 * 
	 * @param listener 
	 * @see cn.limc.androidcharts.event.IZoomable#setOnZoomGestureListener(cn.limc.androidcharts.event.OnZoomGestureListener) 
	 */
	public void setOnZoomGestureListener(OnZoomGestureListener listener) {
		this.onZoomGestureListener = listener;
	}

	/**
	 * @return the onDisplayCursorListener
	 */
	public IDisplayCursorListener getOnDisplayCursorListener() {
		return onDisplayCursorListener;
	}

	/**
	 * @param onDisplayCursorListener the onDisplayCursorListener to set
	 */
	public void setOnDisplayCursorListener(
			IDisplayCursorListener onDisplayCursorListener) {
		this.onDisplayCursorListener = onDisplayCursorListener;
	}
}
