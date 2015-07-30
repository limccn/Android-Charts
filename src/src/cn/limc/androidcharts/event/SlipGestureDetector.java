/*
 * SlipGestureDetector.java
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

import cn.limc.androidcharts.diagram.GridChart;
import android.graphics.PointF;
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
 * @version v1.0 2014/06/23 16:48:07
 * 
 */
public class SlipGestureDetector extends GestureDetector{
    
    
    public static final int TOUCH_MODE_NONE = 0;
    public static final int TOUCH_MODE_SINGLE = 1;
    public static final int TOUCH_MODE_MULTI = 2;
    
    public static final int MIN_DISTANCE = 5;
    
    protected int touchMode = TOUCH_MODE_NONE;
    
    protected float olddistance;
    protected float newdistance;
    
	protected PointF startPointA;
	protected PointF startPointB;
	
	OnSlipGestureListener onSlipGestureListener;

	public SlipGestureDetector(GridChart inChart,OnSlipGestureListener listener){
	    super(inChart, listener);
	}

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
	 * 
	 * @return
	 * 
	 * @see
	 * cn.limc.androidcharts.event.IGestureDetector#onTouchEvent(android.view
	 * .MotionEvent)
	 */
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		// 设置拖拉模式
		case MotionEvent.ACTION_DOWN:
			break;
		case MotionEvent.ACTION_UP:
			startPointA = null;
			startPointB = null;
			break;
		case MotionEvent.ACTION_POINTER_UP:
			startPointA = null;
			startPointB = null;
		case MotionEvent.ACTION_POINTER_DOWN:
			olddistance = calcDistance(event);
			if (olddistance > MIN_DISTANCE) {
				touchMode = TOUCH_MODE_MULTI;
				startPointA = new PointF(event.getX(0), event.getY(0));
				startPointB = new PointF(event.getX(1), event.getY(1));
			}
			return true;
			//break;
		case MotionEvent.ACTION_MOVE:
			if (touchMode == TOUCH_MODE_MULTI) {
				newdistance = calcDistance(event);
				if (newdistance > MIN_DISTANCE) {
					if (startPointA.x >= event.getX(0)
							&& startPointB.x >= event.getX(1)) {
						if (onGestureListener != null) {
						    ((OnSlipGestureListener)onGestureListener).onMoveRight(event);
						}
					} else if (startPointA.x <= event.getX(0)
							&& startPointB.x <= event.getX(1)) {
						if (onGestureListener != null) {
						    ((OnSlipGestureListener)onGestureListener).onMoveLeft(event);
						}
					} else {
//						if (Math.abs(newdistance - olddistance) > MIN_DISTANCE) {
//							if (onZoomGestureListener != null) {
//								if (newdistance > olddistance) {
//									onZoomGestureListener.onZoomIn((Zoomable)instance,event);
//								} else {
//									onZoomGestureListener.onZoomOut((Zoomable)instance,event);
//								}
//							}
//							// reset distance
//							olddistance = newdistance;
//						}
					}
					startPointA = new PointF(event.getX(0), event.getY(0));
					startPointB = new PointF(event.getX(1), event.getY(1));

					return true;
				}
			}
			break;
		}
		return true;
	}

    /**
     * @return the onSlipGestureListener
     */
    public OnSlipGestureListener getOnSlipGestureListener() {
        return onSlipGestureListener;
    }

    /**
     * @param onSlipGestureListener the onSlipGestureListener to set
     */
    public void setOnSlipGestureListener(OnSlipGestureListener onSlipGestureListener) {
        this.onSlipGestureListener = onSlipGestureListener;
    }
    
    public interface OnSlipGestureListener extends GestureDetector.OnGestureListener {
        public void onMoveLeft(MotionEvent event) ;
        public void onMoveRight(MotionEvent event) ;
    }
}
