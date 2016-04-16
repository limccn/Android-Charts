/*
 * IZoomable.java
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
 * @version v1.0 2014/05/29 16:49:01 
 *  
 */
public interface IZoomable extends ITouchable {
	
	static final int ZOOM_BASE_LINE_CENTER = 0;
	static final int ZOOM_BASE_LINE_LEFT = 1;
	static final int ZOOM_BASE_LINE_RIGHT = 2;
	
	static final int ZOOM_NONE = 0;
	static final int ZOOM_IN = 1;
	static final int ZOOM_OUT = 2;
	
	static final int ZOOM_STEP = 4;
	
	/**
	 * <p>
	 * Zoom in the graph
	 * </p>
	 * <p>
	 * 拡大表示する。
	 * </p>
	 * <p>
	 * 放大表示
	 * </p>
	 */
	void zoomIn();

	/**
	 * <p>
	 * Zoom out the grid
	 * </p>
	 * <p>
	 * 縮小表示する。
	 * </p>
	 * <p>
	 * 缩小
	 * </p>
	 */
	void zoomOut();
	
	void setOnZoomGestureListener(OnZoomGestureListener listener);
	OnZoomGestureListener getOnZoomGestureListener();
}
