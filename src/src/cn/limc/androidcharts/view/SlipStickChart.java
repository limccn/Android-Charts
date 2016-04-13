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

import cn.limc.androidcharts.entity.IMeasurable;
import cn.limc.androidcharts.event.IGestureDetector;
import cn.limc.androidcharts.event.ISlipable;
import cn.limc.androidcharts.event.OnSlipGestureListener;
import cn.limc.androidcharts.event.LongPressSlipGestureDetector;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.util.AttributeSet;
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
public class SlipStickChart extends StickChart implements ISlipable {

//	public static final int DEFAULT_DISPLAY_FROM = 0;
//	public static final int DEFAULT_DISPLAY_NUMBER = 50;
//	public static final int DEFAULT_MIN_DISPLAY_NUMBER = 20;
//	public static final int DEFAULT_ZOOM_BASE_LINE = ZOOM_BASE_LINE_CENTER;
//
//	protected int getDisplayFrom() = DEFAULT_DISPLAY_FROM;
//	protected int getDisplayNumber() = DEFAULT_DISPLAY_NUMBER;
//	protected int mingetDisplayNumber() = DEFAULT_MIN_DISPLAY_NUMBER;
//	protected int zoomBaseLine = DEFAULT_ZOOM_BASE_LINE;
	
	protected OnSlipGestureListener onSlipGestureListener = new OnSlipGestureListener();
	protected IGestureDetector slipGestureDetector = new LongPressSlipGestureDetector<ISlipable>(this);

	protected boolean detectSlipEvent = true;

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
		super.onDraw(canvas);
	}

//	@Override
//	protected void drawSticks(Canvas canvas) {
//		if (null == stickData) {
//			return;
//		}
//		if (stickData.size() == 0) {
//			return;
//		}
//
//		float stickWidth = dataQuadrant.getPaddingWidth() / getDisplayNumber()
//				- stickSpacing;
//		float stickX = dataQuadrant.getPaddingStartX();
//
//		for (int i = getDisplayFrom(); i < getDisplayTo(); i++) {
//			IMeasurable stick = stickData.get(i);
//
//			StickMole mole = (StickMole)provider.getMole();
//			mole.setUp(this,stick,stickX,stickWidth);
//			mole.draw(canvas);
//
//			// next x
//			stickX = stickX + stickSpacing + stickWidth;
//		}
//	}

//	protected float olddistance = 0f;
//	protected float newdistance = 0f;

//	protected PointF startPointA;
//	protected PointF startPointB;


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
		if (null == stickData || stickData.size() == 0) {
			return false;
		}

		if (detectSlipEvent) {
			return slipGestureDetector.onTouchEvent(event);
		}else{
			return true;
		}
	}

	/* (non-Javadoc)
	 *  
	 * @see cn.limc.androidcharts.common.ISlipable#moveRight() 
	 */
	public void moveRight() {
		if(this.forward(SLIP_STEP)) {
			this.postInvalidate();
		}
//		int dataSize = stickData.size();
//		if (getDisplayTo() < dataSize - SLIP_STEP) {
//			setgetDisplayFrom()(getDisplayFrom() + SLIP_STEP);
//		} else {
//			setgetDisplayFrom()(dataSize - getDisplayNumber());
//		}
//
//		// 处理getDisplayFrom()越界
//		if (getDisplayTo() >= dataSize) {
//			setgetDisplayFrom()(dataSize - getDisplayNumber());
//		}
//
//		this.postInvalidate();
//
		//Listener
		if (onDisplayCursorListener != null) {
			onDisplayCursorListener.onCursorChanged(this,getDisplayFrom(), getDisplayNumber());
		}
	}

	/* (non-Javadoc)
	 *  
	 * @see cn.limc.androidcharts.common.ISlipable#moveLeft() 
	 */
	public void moveLeft() {
		if(this.backward(SLIP_STEP)) {
			this.postInvalidate();
		}
//		int dataSize = stickData.size();
//
//		if (getDisplayFrom() <= SLIP_STEP) {
//			setgetDisplayFrom()(0);
//		} else if (getDisplayFrom() > SLIP_STEP) {
//			setgetDisplayFrom()(getDisplayFrom() - SLIP_STEP);
//		} else {
//
//		}
//
//		// 处理getDisplayFrom()越界
//		if (getDisplayTo() >= dataSize) {
//			setgetDisplayFrom()(dataSize - getDisplayNumber());
//		}
//
//		this.postInvalidate();
//
		//Listener
		if (onDisplayCursorListener != null) {
			onDisplayCursorListener.onCursorChanged(this,getDisplayFrom(), getDisplayNumber());
		}
	}
	
//	/*
//	 * (non-Javadoc)
//	 *
//	 * @see cn.limc.androidcharts.view.StickChart#zoomIn()
//	 */
//	@Override
//	public void zoomIn() {
//		if (getDisplayNumber() > getMingetDisplayNumber()) {
//			// 区分缩放方向
//			if (zoomBaseLine == ZOOM_BASE_LINE_CENTER) {
//				setgetDisplayNumber()(getDisplayNumber() - ZOOM_STEP);
//				setgetDisplayFrom()(getDisplayFrom() + ZOOM_STEP / 2);
//			} else if (zoomBaseLine == ZOOM_BASE_LINE_LEFT) {
//				setgetDisplayNumber()(getDisplayNumber() - ZOOM_STEP);
//			} else if (zoomBaseLine == ZOOM_BASE_LINE_RIGHT) {
//				setgetDisplayNumber()(getDisplayNumber() - ZOOM_STEP);
//				setgetDisplayFrom()(getDisplayFrom() + ZOOM_STEP);
//			}
//
//			// 处理getDisplayNumber()越界
//			if (getDisplayNumber() < getMingetDisplayNumber()) {
//				setgetDisplayNumber()(getMingetDisplayNumber());
//			}
//
//			// 处理getDisplayFrom()越界
//			if (getDisplayTo() >= stickData.size()) {
//				setgetDisplayFrom()(stickData.size() - getDisplayNumber());
//			}
//
//			this.postInvalidate();
//
//			//Listener
//			if (onDisplayCursorListener != null) {
//				onDisplayCursorListener.onCursorChanged(this,getDisplayFrom(), getDisplayNumber());
//			}
//	    }
//	}


//	/*
//	 * (non-Javadoc)
//	 *
//	 * @see cn.limc.androidcharts.view.StickChart#zoomOut()
//	 */
//	@Override
//	public void zoomOut() {
////		if (getDisplayNumber() < stickData.size() - 1) {
//			if (getDisplayNumber() + ZOOM_STEP > stickData.size() - 1) {
//				setgetDisplayNumber()(stickData.size() - 1);
//				setgetDisplayFrom()(0);
//			} else {
//				// 区分缩放方向
//				if (zoomBaseLine == ZOOM_BASE_LINE_CENTER) {
//					setgetDisplayNumber()(getDisplayNumber() + ZOOM_STEP);
//					if (getDisplayFrom() > 1) {
//						setgetDisplayFrom()(getDisplayFrom() - ZOOM_STEP / 2);
//					} else {
//						setgetDisplayFrom()(0);
//					}
//				} else if (zoomBaseLine == ZOOM_BASE_LINE_LEFT) {
//					setgetDisplayNumber()(getDisplayNumber() + ZOOM_STEP);
//				} else if (zoomBaseLine == ZOOM_BASE_LINE_RIGHT) {
//					setgetDisplayNumber()(getDisplayNumber() + ZOOM_STEP);
//					if (getDisplayFrom() > ZOOM_STEP) {
//						setgetDisplayFrom()(getDisplayFrom() - ZOOM_STEP);
//					} else {
//						setgetDisplayFrom()(0);
//					}
//				}
//			}
//
//			if (getDisplayTo() >= stickData.size()) {
//				setgetDisplayNumber()(stickData.size() - getDisplayFrom());
//			}
//			this.postInvalidate();
//
//			Listener
//			if (onDisplayCursorListener != null) {
//				onDisplayCursorListener.onCursorChanged(this,getDisplayFrom(), getDisplayNumber());
//			}
//		}
//	}


//	/*
//	 * (non-Javadoc)
//	 *
//	 * @return
//	 * @see cn.limc.androidcharts.view.StickChart#getDisplayFrom()
//	 */
//	@Override
//	public int getDisplayFrom() {
//		return getDisplayFrom();
//	}
//
//
//	/*
//	 * (non-Javadoc)
//	 *
//	 * @param getDisplayFrom()
//	 * @see cn.limc.androidcharts.view.StickChart#setgetDisplayFrom()(int)
//	 */
//	@Override
//	public void setgetDisplayFrom()(int getDisplayFrom()) {
//		this.getDisplayFrom() = getDisplayFrom();
//	}
//
//
//	/*
//	 * (non-Javadoc)
//	 *
//	 * @return
//	 * @see cn.limc.androidcharts.view.StickChart#getDisplayTo()
//	 */
//	@Override
//	public int getDisplayTo() {
//		return getDisplayTo();
//	}
//
//	/*
//	 * (non-Javadoc)
//	 *
//	 * @param displayTo
//	 * @see cn.limc.androidcharts.view.StickChart#setDisplayTo(int)
//	 */
//	@Override
//	public void setDisplayTo(int displayTo) {
//		// TODO
//	}
//
//	/*
//	 * (non-Javadoc)
//	 *
//	 * @return
//	 * @see cn.limc.androidcharts.view.StickChart#getDisplayNumber()
//	 */
//	@Override
//	public int getDisplayNumber() {
//		return getDisplayNumber();
//	}
//
//	/*
//	 * (non-Javadoc)
//	 *
//	 * @param getDisplayNumber()
//	 * @see cn.limc.androidcharts.view.StickChart#setgetDisplayNumber()(int)
//	 */
//	@Override
//	public void setgetDisplayNumber()(int getDisplayNumber()) {
//		this.getDisplayNumber() = getDisplayNumber();
//	}
//
//	/*
//	 * (non-Javadoc)
//	 *
//	 * @return
//	 * @see cn.limc.androidcharts.view.StickChart#getMingetDisplayNumber()
//	 */
//	@Override
//	public int getMingetDisplayNumber() {
//		return mingetDisplayNumber();
//	}
//
//	/*
//	 * (non-Javadoc)
//	 *
//	 * @param mingetDisplayNumber()
//	 * @see cn.limc.androidcharts.view.StickChart#setMingetDisplayNumber()(int)
//	 */
//	@Override
//	public void setMingetDisplayNumber()(int mingetDisplayNumber()) {
//		this.mingetDisplayNumber() = mingetDisplayNumber();
//	}
//
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
	public boolean isDetectSlipEvent() {
		return detectSlipEvent;
	}

	public void setDetectSlipEvent(boolean detectSlipEvent) {
		this.detectSlipEvent = detectSlipEvent;
	}


	/* (non-Javadoc)
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
}
