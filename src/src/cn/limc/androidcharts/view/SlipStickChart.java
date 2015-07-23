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

import cn.limc.androidcharts.common.SectionDataCursor;
import cn.limc.androidcharts.event.GestureDetector;
import cn.limc.androidcharts.event.ISlipable;
import cn.limc.androidcharts.event.SlipGestureDetector.OnSlipGestureListener;
import cn.limc.androidcharts.event.SlipGestureDetector;

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
public class SlipStickChart extends StickChart {

	
    protected OnSlipGestureListener onSlipListener = new OnSlipListener();
    
    protected GestureDetector slipGestureDetector = new SlipGestureDetector(this,onSlipListener);
	

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
	    this.dataCursor = new SectionDataCursor(this);
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
	      this.dataCursor = new SectionDataCursor(this);
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
	      this.dataCursor = new SectionDataCursor(this);
	}

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
//		super.onDraw(canvas);
//	}

//	@Override
//	protected void drawSticks(Canvas canvas) {
//		if (null == stickData) {
//			return;
//		}
//		if (stickData.size() == 0) {
//			return;
//		}
//
//		float stickWidth = dataQuadrant.getPaddingWidth() / getDisplayNumber();
//		float stickX = dataQuadrant.getPaddingStartX();
//
//		for (int i = getDisplayFrom(); i < getDisplayTo(); i++) {
//			IMeasurable stick = stickData.get(i);
//			
//			StickMole mole = new  StickMole();
//			mole.setUp(this,stick,stickX,stickWidth);
//			mole.draw(canvas);
//
//			// next x
//			stickX = stickX + stickWidth;
//		}
//	}

//	protected float olddistance = 0f;
//	protected float newdistance = 0f;
//
//	protected PointF startPointA;
//	protected PointF startPointB;


//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @param event
//	 * @return 
//	 * @see cn.limc.androidcharts.view.StickChart#onTouchEvent(android.view.MotionEvent)
//	 */
//	@Override
//	public boolean onTouchEvent(MotionEvent event) {
//		// valid
//		if (!isValidTouchPoint(event.getX(), event.getY())) {
//			return false;
//		}
//		if (null == stickData || stickData.size() == 0) {
//			return false;
//		}
//		return zoomGestureDetector.onTouchEvent(event) && slipGestureDetector.onTouchEvent(event);
//	}
//
//	/* (non-Javadoc)
//	 *  
//	 * @see cn.limc.androidcharts.common.ISlipable#moveRight() 
//	 */
//	public void moveRight() {
//		
//		this.postInvalidate();
//
//		//Listener
//		if (onDisplayCursorListener != null) {
//			onDisplayCursorListener.onCursorChanged(this.dataCursor,getDisplayFrom(), getDisplayNumber());
//		}
//	}
//
//	/* (non-Javadoc)
//	 *  
//	 * @see cn.limc.androidcharts.common.ISlipable#moveLeft() 
//	 */
//	public void moveLeft() {
//		
//		this.postInvalidate();
//
//		//Listener
//		if (onDisplayCursorListener != null) {
//			onDisplayCursorListener.onCursorChanged(this.dataCursor,getDisplayFrom(), getDisplayNumber());
//		}
//	}
//	
//	/*
//	 * (non-Javadoc)
//	 *  
//	 * @see cn.limc.androidcharts.view.StickChart#zoomIn()
//	 */
//	@Override
//	public void zoomIn() {
//
//			this.postInvalidate();
//
//			//Listener
//			if (onDisplayCursorListener != null) {
//				onDisplayCursorListener.onCursorChanged(this.dataCursor,getDisplayFrom(), getDisplayNumber());
//			}
//	}
//
//
//	/*
//	 * (non-Javadoc)
//	 *  
//	 * @see cn.limc.androidcharts.view.StickChart#zoomOut()
//	 */
//	@Override
//	public void zoomOut() {
//		this.postInvalidate();
//		
//		//Listener
//		if (onDisplayCursorListener != null) {
//			onDisplayCursorListener.onCursorChanged(this.dataCursor,getDisplayFrom(), getDisplayNumber());
//		}
//	}
	
	   /**
     * @return the zoomBaseLine
     */
	@Deprecated
    public int getZoomBaseLine() {
        return ((SectionDataCursor)dataCursor).getZoomBaseLine();
    }

    /**
     * @param zoomBaseLine
     *            the zoomBaseLine to set
     */
	   @Deprecated
    public void setZoomBaseLine(int zoomBaseLine) {
	    ((SectionDataCursor)dataCursor).setZoomBaseLine(zoomBaseLine);
    }
	
    /**
     * @return the onSlipListener
     */
    public OnSlipGestureListener getOnSlipListener() {
        return onSlipListener;
    }

    /**
     * @param onSlipListener the onSlipListener to set
     */
    public void setOnSlipListener(OnSlipGestureListener onSlipListener) {
        this.onSlipListener = onSlipListener;
    }
	
	
	
    public class OnSlipListener implements OnSlipGestureListener {

        /*
         * (non-Javadoc)
         * 
         * @see
         * cn.limc.androidcharts.event.SlipGestureDetector.OnSlipGestureListener
         * #onMoveLeft(android.view.MotionEvent)
         */
        @Override
        public void onMoveLeft(MotionEvent event) {
            ISlipable dataCursor = (ISlipable) SlipStickChart.this.getDataCursor();
            if (dataCursor != null) {
                dataCursor.moveLeft();
            }
            SlipStickChart.this.postInvalidate();

        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * cn.limc.androidcharts.event.SlipGestureDetector.OnSlipGestureListener
         * #onMoveRight(android.view.MotionEvent)
         */
        @Override
        public void onMoveRight(MotionEvent event) {
            ISlipable dataCursor = (ISlipable) SlipStickChart.this.getDataCursor();
            if (dataCursor != null) {
                dataCursor.moveRight();
            }
            SlipStickChart.this.postInvalidate();

        }
    }

}
