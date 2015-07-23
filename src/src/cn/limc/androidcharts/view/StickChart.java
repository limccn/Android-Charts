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

import cn.limc.androidcharts.entity.IChartData;
import cn.limc.androidcharts.entity.IMeasurable;
import cn.limc.androidcharts.entity.IStickEntity;
import cn.limc.androidcharts.event.GestureDetector;
import cn.limc.androidcharts.event.IDisplayCursorListener;
import cn.limc.androidcharts.event.IZoomable;
import cn.limc.androidcharts.event.ZoomGestureDetector;
import cn.limc.androidcharts.event.ZoomGestureDetector.OnZoomGestureListener;
import cn.limc.androidcharts.mole.StickMole;

import android.content.Context;
import android.graphics.Canvas;
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
public class StickChart extends PeriodDataGridChart{		
		
	public static final int DEFAULT_STICK_SPACING = 1;
	

	protected int stickSpacing = DEFAULT_STICK_SPACING;
//	
	protected OnZoomGestureListener onZoomListener = new OnZoomListener(){};
	protected GestureDetector zoomGestureDetector = new ZoomGestureDetector(this, onZoomListener);
	
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
		if (this.dataRange.isAutoCalcValueRange()) {
		    this.dataRange.calcValueRange();
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
//	protected void drawSticks(Canvas canvas) {
//		if (null == stickData) {
//			return;
//		}
//		if (stickData.size() <= 0) {
//			return;
//		}
//
//		float stickWidth = dataQuadrant.getPaddingWidth() / getDisplayNumber();
//
//		if (axisY.getPosition() == IAxis.AXIS_Y_POSITION_LEFT) {
//
//			float stickX = dataQuadrant.getPaddingStartX();
//
//			for (int i = 0; i < stickData.size(); i++) {
//				IMeasurable stick = stickData.get(i);
//				StickMole mole = new StickMole();
//				mole.setUp(this,stick,stickX,stickWidth);
//				mole.draw(canvas);
//				// next x
//				stickX = stickX + stickWidth;
//			}
//		} else {
//			float stickX = dataQuadrant.getPaddingEndX() - stickWidth;
//			for (int i = stickData.size() - 1; i >= 0; i--) {
//				IMeasurable stick = stickData.get(i);
//				StickMole mole = new StickMole();
//				mole.setUp(this,stick,stickX,stickWidth);
//				mole.draw(canvas);
//				// next x
//				stickX = stickX - stickWidth;
//			}
//		}
//	}
	
    protected void drawSticks(Canvas canvas) {
        if (null == stickData) {
            return;
        }
        if (stickData.size() == 0) {
            return;
        }

        float stickWidth = dataQuadrant.getPaddingWidth() / getDisplayNumber();
        float stickX = dataQuadrant.getPaddingStartX();

        for (int i = getDisplayFrom(); i < getDisplayTo(); i++) {
            IMeasurable stick = stickData.get(i);
            
            StickMole mole = new  StickMole();
            mole.setUp(this,stick,stickX,stickWidth);
            mole.draw(canvas);

            // next x
            stickX = stickX + stickWidth;
        }
    }

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
        if (!isValidTouchPoint(event.getX(),event.getY())) {
            return false;
        }
	    
		if (null == stickData || stickData.size() == 0) {
			return false;
		}
		
		return touchGestureDetector.onTouchEvent(event) && zoomGestureDetector.onTouchEvent(event);
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
		this.postInvalidate();
		
		//Listener
		if (onDisplayCursorListener != null) {
			onDisplayCursorListener.onCursorChanged(this.dataCursor,getDisplayFrom(), getDisplayNumber());
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
		
	    this.postInvalidate();
		//Listener
		if (onDisplayCursorListener != null) {
			onDisplayCursorListener.onCursorChanged(this.dataCursor,getDisplayFrom(), getDisplayNumber());
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
		this.stickData = stickData;
	}

	/**
	 * @return the maxSticksNum
	 */
	@Deprecated
	public int getMaxSticksNum() {
		return dataCursor.getDisplayNumber();
	}

	/**
	 * @param maxSticksNum
	 *            the maxSticksNum to set
	 */
	@Deprecated
	public void setMaxSticksNum(int maxSticksNum) {
		this.dataCursor.setDisplayNumber(maxSticksNum);
	}

	/**
	 * @return the stickSpacing
	 */
	@Deprecated
	public int getStickSpacing() {
		return stickSpacing;
	}

	/**
	 * @param stickSpacing the stickSpacing to set
	 */
	@Deprecated
	public void setStickSpacing(int stickSpacing) {
		this.stickSpacing = stickSpacing;
	}

	/* (non-Javadoc)
	 * 
	 * @return 
	 * @see cn.limc.androidcharts.common.IDataCursor#displayFrom() 
	 */
	public int getDisplayFrom() {
		return this.dataCursor.getDisplayFrom();
	}

	/* (non-Javadoc)
	 * 
	 * @return 
	 * @see cn.limc.androidcharts.common.IDataCursor#displayNumber() 
	 */
	public int getDisplayNumber() {
		return this.dataCursor.getDisplayNumber();
	}

	/* (non-Javadoc)
	 * 
	 * @return 
	 * @see cn.limc.androidcharts.common.IDataCursor#displayTo() 
	 */
	public int getDisplayTo() {
	    return this.dataCursor.getDisplayTo();
	}

	/* (non-Javadoc)
	 * 
	 * @param displayFrom 
	 * @see cn.limc.androidcharts.common.IDataCursor#setDisplayFrom(int) 
	 */
	public void setDisplayFrom(int displayFrom) {
		this.dataCursor.setDisplayFrom(displayFrom);
	}

	/* (non-Javadoc)
	 * 
	 * @param displayNumber 
	 * @see cn.limc.androidcharts.common.IDataCursor#setDisplayNumber(int) 
	 */
	public void setDisplayNumber(int displayNumber) {
		this.dataCursor.setDisplayNumber(displayNumber);
	}

	/* (non-Javadoc)
	 * 
	 * @return 
	 * @see cn.limc.androidcharts.common.IDataCursor#getMinDisplayNumber() 
	 */
	public int getMinDisplayNumber() {
		return this.dataCursor.getMinDisplayNumber();
	}

	/* (non-Javadoc)
	 * 
	 * @param minDisplayNumber 
	 * @see cn.limc.androidcharts.common.IDataCursor#getMinDisplayNumber(int) 
	 */
	public void setMinDisplayNumber(int minDisplayNumber) {
		this.dataCursor.setMinDisplayNumber(minDisplayNumber);
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
	
	public abstract class OnZoomListener implements OnZoomGestureListener{

        /* (non-Javadoc)
         * @see cn.limc.androidcharts.event.ZoomGestureDetector.OnZoomGestureListener#onZoomIn(android.view.MotionEvent)
         */
        @Override
        public void onZoomIn(MotionEvent event) {
            IZoomable dataCursor = (IZoomable) StickChart.this.getDataCursor();
            if (dataCursor != null) {
                dataCursor.zoomIn();
            }
            StickChart.this.postInvalidate();
        }

        /* (non-Javadoc)
         * @see cn.limc.androidcharts.event.ZoomGestureDetector.OnZoomGestureListener#onZoomOut(android.view.MotionEvent)
         */
        @Override
        public void onZoomOut(MotionEvent event) {
           
            IZoomable dataCursor = (IZoomable) StickChart.this.getDataCursor();
            if (dataCursor != null) {
                dataCursor.zoomOut();
            }
            StickChart.this.postInvalidate();
        }   
	}
}
