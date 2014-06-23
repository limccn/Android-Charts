/*
 * OnTouchGestureListener.java
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
 * @version v1.0 2014/06/23 15:52:21 
 *  
 */
public class OnTouchGestureListener {
	ITouchable chart;
	
	public OnTouchGestureListener(ITouchable touchable) {
		chart = touchable;
	}
	
	public void onTouchDown(MotionEvent event){
		if (chart != null) { 
			chart.touchDown(new PointF(event.getX(),event.getY()));
		}
	}
	
	public void onTouchMoved(MotionEvent event){
		if (chart != null) {
			chart.touchMoved(new PointF(event.getX(),event.getY()));
		}
	}
	
	public void onTouchUp(MotionEvent event){
		if (chart != null) {
			chart.touchUp(new PointF(event.getX(),event.getY()));
		}
	}
}
