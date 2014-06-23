/*
 * ZoomGestureDetector.java
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


package cn.limc.androidcharts.event;
import cn.limc.androidcharts.view.GridChart;
import android.util.FloatMath;
import android.view.MotionEvent;


/** 
 * <p>en</p>
 * <p>jp</p>
 * <p>cn</p>
 *
 * @author limc 
 * @version v1.0 2014/06/20 17:33:01 
 *  
 */
public class ZoomGestureDetector extends TouchGestureDetector{
	
	static final int TOUCH_MODE_NONE = 0;
	static final int TOUCH_MODE_SINGLE = 1;
	static final int TOUCH_MODE_MULTI = 2;
	
	
	protected int touchMode = TOUCH_MODE_NONE;
	
	protected GridChart chart;
	
	protected float olddistance;
	protected float newdistance;
	
	protected OnZoomGestureListener onZoomGestureListener;
	
	public ZoomGestureDetector(GridChart chart, IZoomable zoomable){
		super(zoomable);
		this.chart = chart;
		if (zoomable != null) {
			this.onZoomGestureListener = zoomable.getOnZoomGestureListener();
		}
	}
	
	protected float calcMinLength(){
		return (chart.getWidth() / 40) < 5 ? 5 : (chart.getWidth() / 50);
	}
	
	protected float calcDistance(MotionEvent event) {
		if(event.getPointerCount() <= 1) {
			return 0f;
		}else{
			float x = event.getX(0) - event.getX(1);
			float y = event.getY(0) - event.getY(1);
			return FloatMath.sqrt(x * x + y * y);
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		final float MIN_LENGTH = calcMinLength();

		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN:
			if (event.getPointerCount() == 1) {
				touchMode = TOUCH_MODE_SINGLE;
			}
			break;
		case MotionEvent.ACTION_UP:
			touchMode = TOUCH_MODE_NONE;
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
					if (onZoomGestureListener != null) {
						if (newdistance > olddistance) {
							onZoomGestureListener.onZoomIn(event);
						} else {
							onZoomGestureListener.onZoomOut(event);
						}
					}
					olddistance = newdistance;
				}
				
				return true;
			}
			break;
		}
		return super.onTouchEvent(event);
	}

	/**
	 * @return the onZoomGestureListener
	 */
	public OnZoomGestureListener getOnZoomGestureListener() {
		return onZoomGestureListener;
	}

	/**
	 * @param onZoomGestureListener the onZoomGestureListener to set
	 */
	public void setOnZoomGestureListener(OnZoomGestureListener onZoomGestureListener) {
		this.onZoomGestureListener = onZoomGestureListener;
	}

	/**
	 * @return the chart
	 */
	public GridChart getChart() {
		return chart;
	}

	/**
	 * @param chart the chart to set
	 */
	public void setChart(GridChart chart) {
		this.chart = chart;
	}
}
