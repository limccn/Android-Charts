/*
 * IQuadrant.java
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

/** 
 * <p>en</p>
 * <p>jp</p>
 * <p>cn</p>
 *
 * @author limc 
 * @version v1.0 2014/06/24 17:19:47 
 *  
 */
public interface IQuadrant {
    
	 static final float DEFAULT_PADDING_TOP = 10f;
	 static final float DEFAULT_PADDING_BOTTOM = 10f;
	 static final float DEFAULT_PADDING_LEFT = 10f;
	 static final float DEFAULT_PADDING_RIGHT = 10f;
	
	float getPaddingTop();

	float getPaddingLeft();

	float getPaddingBottom();

	float getPaddingRight();
	
	void setPaddingTop(float value);

	void setPaddingLeft(float value);

	void setPaddingBottom(float value);

	void setPaddingRight(float value);

	float getWidth();

	float getHeight();

	float getStartX();

	float getStartY();

	float getEndX();

	float getEndY();

	float getPaddingStartX();

	float getPaddingEndX();

	float getPaddingStartY();

	float getPaddingEndY();

	float getPaddingWidth();

	float getPaddingHeight();
}
