/*
 * HorizontalAxis.java
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
 * @version v1.0 2014/06/24 19:58:57 
 *  
 */
public class HorizontalAxis extends Axis {
	protected float height;
	/** 
	 * <p>Constructor of HorizontalAxis</p>
	 * <p>HorizontalAxis类对象的构造函数</p>
	 * <p>HorizontalAxisのコンストラクター</p>
	 *
	 * @param inChart
	 * @param position 
	 */
	public HorizontalAxis(GridChart inChart, int position , float height) {
		super(inChart, position);
		this.height = height;
	}

	/* (non-Javadoc)
	 * 
	 * @return 
	 * @see cn.limc.androidcharts.common.IQuadrant#getQuadrantWidth() 
	 */
	public float getQuadrantWidth() {
		return inChart.getWidth() - 2 * inChart.getBorderWidth();
	}

	/* (non-Javadoc)
	 * 
	 * @return 
	 * @see cn.limc.androidcharts.common.IQuadrant#getQuadrantHeight() 
	 */
	public float getQuadrantHeight() {
		return height;
	}

}
