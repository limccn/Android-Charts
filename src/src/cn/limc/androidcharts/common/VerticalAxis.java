/*
 * VerticalAxis.java
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

import cn.limc.androidcharts.view.GridChart;

/** 
 * <p>en</p>
 * <p>jp</p>
 * <p>cn</p>
 *
 * @author limc 
 * @version v1.0 2014/06/24 19:43:01 
 *  
 */
public class VerticalAxis extends Axis {

	protected float width;
	/** 
	 * <p>Constructor of VerticalAxis</p>
	 * <p>VerticalAxis类对象的构造函数</p>
	 * <p>VerticalAxisのコンストラクター</p>
	 *
	 * @param position 
	 */
	public VerticalAxis(GridChart inChart, int position , float width) {
		super(inChart,position);
		this.width = width;
	}
	/* (non-Javadoc)
	 * 
	 * @return 
	 * @see cn.limc.androidcharts.common.IQuadrant#getQuadrantWidth() 
	 */
	public float getQuadrantWidth() {
		return width;
	}
	/* (non-Javadoc)
	 * 
	 * @return 
	 * @see cn.limc.androidcharts.common.IQuadrant#getQuadrantHeight() 
	 */
	public float getQuadrantHeight() {
		return inChart.getHeight() - 2 * inChart.getBorderWidth();
	}
}
