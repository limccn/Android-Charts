/*
 * TouchGestureDetector.java
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
import android.graphics.PointF;
import android.view.MotionEvent;

/** 
 * <p>en</p>
 * <p>jp</p>
 * <p>cn</p>
 *
 * @author limc 
 * @version v1.0 2014/06/23 16:37:37 
 *  
 */
public class TouchGestureDetector<T extends ITouchable> implements IGestureDetector{
	
	protected PointF touchPoint;
	static final int TOUCH_MOVE_MIN_DISTANCE = 6;
	
	protected T instance;
	protected OnTouchGestureListener onTouchGestureListener;
	
	/**
	 * <p>Constructor of TouchGestureDetector</p>
	 * <p>TouchGestureDetector类对象的构造函数</p>
	 * <p>TouchGestureDetectorのコンストラクター</p>
	 *
	 */
	public TouchGestureDetector(T touchable) {
		instance = touchable;
		if (touchable != null) {
			this.onTouchGestureListener = touchable.getOnTouchGestureListener();
		}
	}

	/**
	 * 
	 * <p>Constructor of TouchGestureDetector</p>
	 * <p>TouchGestureDetector类对象的构造函数</p>
	 * <p>TouchGestureDetectorのコンストラクター</p>
	 *
	 * @param touchGestureListener
	 */
	public TouchGestureDetector(OnTouchGestureListener touchListener) {
		super();
		this.onTouchGestureListener = touchListener;
	}

	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN:
			if (event.getPointerCount() == 1) {
				touchPoint = new PointF(event.getX(),event.getY());
				if (onTouchGestureListener != null) {
					onTouchGestureListener.onTouchDown(instance,event);
				}
			}
			break;
		case MotionEvent.ACTION_UP:
			if (event.getPointerCount() == 1) {
				touchPoint = new PointF(event.getX(),event.getY());
				if (onTouchGestureListener != null) {
					onTouchGestureListener.onTouchUp(instance,event);
				}
			}
			break;
		case MotionEvent.ACTION_POINTER_UP:
			break;
		case MotionEvent.ACTION_POINTER_DOWN:
			break;
		case MotionEvent.ACTION_MOVE:
			// single touch point moved
			if (event.getPointerCount() == 1) {
				float moveXdistance = Math.abs(event.getX() - touchPoint.x);
				float moveYdistance = Math.abs(event.getY() - touchPoint.y);
				if (moveXdistance > TOUCH_MOVE_MIN_DISTANCE || 
						moveYdistance > TOUCH_MOVE_MIN_DISTANCE) {
					//reset touchPoint
					touchPoint = new PointF(event.getX(),event.getY());
					// call back to listener
					if (onTouchGestureListener != null) {
						onTouchGestureListener.onTouchMoved(instance,event);
					}
				}
			}
			break;
		}
		return true;
	}

	/**
	 * @return the onTouchGestureListener
	 */
	public OnTouchGestureListener getOnTouchGestureListener() {
		return onTouchGestureListener;
	}

	/**
	 * @param onTouchGestureListener the onTouchGestureListener to set
	 */
	public void setOnTouchGestureListener(
			OnTouchGestureListener onTouchGestureListener) {
		this.onTouchGestureListener = onTouchGestureListener;
	}
}
