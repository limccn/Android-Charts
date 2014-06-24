/*
 * ITouchable.java
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

/** 
 * <p>en</p>
 * <p>jp</p>
 * <p>cn</p>
 *
 * @author limc 
 * @version v1.0 2014/05/30 16:26:03 
 *  
 */
public interface ITouchable {
	
	static final int TOUCH_NO_SELECTED_INDEX = -1;
	
	static final int TOUCH_MODE_NONE = 0;
	static final int TOUCH_MODE_SINGLE = 1;
	static final int TOUCH_MODE_MULTI = 2;
	
	static final int TOUCH_MOVE_MIN_DISTANCE = 6;
	
	void touchDown(PointF pt);
	void touchMoved(PointF pt);
	void touchUp(PointF pt);

	void setOnTouchGestureListener(OnTouchGestureListener listener);
	OnTouchGestureListener getOnTouchGestureListener();
}
