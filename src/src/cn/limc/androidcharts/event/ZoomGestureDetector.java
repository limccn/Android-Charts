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
public class ZoomGestureDetector extends GestureDetector{
	
    
	public static final int TOUCH_MODE_NONE = 0;
	public static final int TOUCH_MODE_SINGLE = 1;
	public static final int TOUCH_MODE_MULTI = 2;
	
	public static final int MIN_DISTANCE = 5;
	
	protected int touchMode = TOUCH_MODE_NONE;

	protected float olddistance;
	protected float newdistance;

	
	public ZoomGestureDetector(GridChart inChart, OnZoomGestureListener listener){
	    super(inChart,listener);
	}
	
	public boolean onTouchEvent(MotionEvent event) {

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
			if (olddistance > MIN_DISTANCE) {
				touchMode = TOUCH_MODE_MULTI;
			}
			return true;
			//break;
		case MotionEvent.ACTION_MOVE:
			if (touchMode == TOUCH_MODE_MULTI) {
				newdistance = calcDistance(event);
				if (newdistance > MIN_DISTANCE
						&& Math.abs(newdistance - olddistance) > MIN_DISTANCE) {
					if (onGestureListener != null) {
						if (newdistance > olddistance) {
							((OnZoomGestureListener)onGestureListener).onZoomIn(event);
						} else {
						    ((OnZoomGestureListener)onGestureListener).onZoomOut(event);
						}
					}
					olddistance = newdistance;
				}
				
				return true;
			}
			break;
		}
		return true;
	}

	/**
	 * @return the onZoomGestureListener
	 */
	public OnZoomGestureListener getOnZoomGestureListener() {
		return (OnZoomGestureListener)onGestureListener;
	}

	/**
	 * @param onZoomGestureListener the onZoomGestureListener to set
	 */
	public void setOnZoomGestureListener(OnZoomGestureListener onZoomGestureListener) {
		this.onGestureListener = onZoomGestureListener;
	}
	
	public interface OnZoomGestureListener extends GestureDetector.OnGestureListener{ 
	    void onZoomIn(MotionEvent event);
	    void onZoomOut(MotionEvent event);
	}
}
