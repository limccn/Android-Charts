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
public class SlipGestureDetector<T extends ISlipable> extends ZoomGestureDetector<IZoomable> {
	protected PointF startPointA;
	protected PointF startPointB;
	
	private OnSlipGestureListener onSlipGestureListener;

	public SlipGestureDetector(ISlipable slipable){
		super(slipable);
		if (slipable != null) {
			this.onSlipGestureListener = slipable.getOnSlipGestureListener();
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
						if (onSlipGestureListener != null) {
							onSlipGestureListener.onMoveRight((ISlipable)instance,event);
						}
					} else if (startPointA.x <= event.getX(0)
							&& startPointB.x <= event.getX(1)) {
						if (onSlipGestureListener != null) {
							onSlipGestureListener.onMoveLeft((ISlipable)instance,event);
						}
					} else {
						if (Math.abs(newdistance - olddistance) > MIN_DISTANCE) {
							if (onZoomGestureListener != null) {
								if (newdistance > olddistance) {
									onZoomGestureListener.onZoomIn((IZoomable)instance,event);
								} else {
									onZoomGestureListener.onZoomOut((IZoomable)instance,event);
								}
							}
							// reset distance
							olddistance = newdistance;
						}
					}
					startPointA = new PointF(event.getX(0), event.getY(0));
					startPointB = new PointF(event.getX(1), event.getY(1));

					return true;
				}
			}
			break;
		}
		return super.onTouchEvent(event);
	}

}
