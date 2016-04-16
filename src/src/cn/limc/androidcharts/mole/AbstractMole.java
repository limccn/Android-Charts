/*
 * AbstractMole.java
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


package cn.limc.androidcharts.mole;

import cn.limc.androidcharts.common.IChart;
import android.graphics.RectF;

/** 
 * <p>en</p>
 * <p>jp</p>
 * <p>cn</p>
 *
 * @author limc 
 * @version v1.0 2014/06/19 16:49:33 
 *  
 */
public abstract class AbstractMole  extends RectF implements IMole {
	private IChart inChart;
	
	public void setUp(IChart chart){
		setInChart(chart);
	} 

	/**
	 * @return the inChart
	 */
	public IChart getInChart() {
		return inChart;
	}

	/**
	 * @param inChart the inChart to set
	 */
	public void setInChart(IChart inChart) {
		this.inChart = inChart;
	}
}
