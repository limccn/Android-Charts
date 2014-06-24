/*
 * IAxis.java
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


package cn.limc.androidcharts.common;

import android.graphics.Canvas;

/** 
 * <p>en</p>
 * <p>jp</p>
 * <p>cn</p>
 *
 * @author limc 
 * @version v1.0 2014/06/24 17:17:55 
 *  
 */
public interface IAxis {
	static final int AXIS_X_POSITION_BOTTOM = 1 << 0;
	static final int AXIS_X_POSITION_TOP = 1 << 1;
	static final int AXIS_Y_POSITION_LEFT = 1 << 2;
	static final int AXIS_Y_POSITION_RIGHT = 1 << 3;
	
	void drawAxis(Canvas canvas);
}
