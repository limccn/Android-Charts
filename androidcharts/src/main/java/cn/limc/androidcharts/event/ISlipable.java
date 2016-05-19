/*
 * ISlipable.java
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

/** 
 * <p>en</p>
 * <p>jp</p>
 * <p>cn</p>
 *
 * @author limc 
 * @version v1.0 2014/05/29 16:49:51 
 *  
 */
public interface ISlipable extends IZoomable {	
	
	static final int SLIP_DIRECTION_NONE = 0;
	static final int SLIP_DIRECTION_TOP = 1;
	static final int SLIP_DIRECTION_RIGHT = 2;
	static final int SLIP_DIRECTION_BOTTOM = 3;
	static final int SLIP_DIRECTION_LEFT = 4;
	
	static final int SLIP_STEP = 4;
	
	void moveLeft();
	void moveRight();
	
	void setOnSlipGestureListener(OnSlipGestureListener listener);
	OnSlipGestureListener getOnSlipGestureListener();
}
