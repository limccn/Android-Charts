/*
 * ICrossLines.java
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

import android.graphics.Color;

/** 
 * <p>en</p>
 * <p>jp</p>
 * <p>cn</p>
 *
 * @author limc 
 * @version v1.0 2014/06/18 14:46:30 
 *  
 */
public interface ICrossLines {
	static final int BIND_TO_TYPE_NONE = 0;
	static final int BIND_TO_TYPE_HIRIZIONAL = 1;
	static final int BIND_TO_TYPE_VERTICAL = 2;
	static final int BIND_TO_TYPE_BOTH = 3;
	
	static final int DISPLAY_NONE = 0;
	static final int DISPLAY_HIRIZIONAL = 1;
	static final int DISPLAY_VERTICAL = 2;
	static final int DISPLAY_BOTH = 3;
	
	static final int DEFAULT_CROSS_LINES_COLOR = Color.CYAN;
	static final int DEFAULT_CROSS_LINES_FONT_COLOR = Color.CYAN;
	
	/**
	 * <p>
	 * Should display the Y cross line if grid is touched?
	 * </p>
	 * <p>
	 * タッチしたポイントがある場合、十字線の垂直線を表示するか?
	 * </p>
	 * <p>
	 * 默认在控件被点击时，显示十字竖线线
	 * </p>
	 */
	public static final boolean DEFAULT_DISPLAY_CROSS_X_ON_TOUCH = true;

	/**
	 * <p>
	 * Should display the Y cross line if grid is touched?
	 * </p>
	 * <p>
	 * タッチしたポイントがある場合、十字線の水平線を表示するか?
	 * </p>
	 * <p>
	 * 默认在控件被点击时，显示十字横线线
	 * </p>
	 */
	public static final boolean DEFAULT_DISPLAY_CROSS_Y_ON_TOUCH = true;
	
}
