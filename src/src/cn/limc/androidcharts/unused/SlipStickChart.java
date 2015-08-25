/*
 * SlipStickChart.java
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

package cn.limc.androidcharts.unused;

import cn.limc.androidcharts.event.GestureDetector;
import cn.limc.androidcharts.event.Slipable;
import cn.limc.androidcharts.event.SlipGestureDetector.OnSlipGestureListener;
import cn.limc.androidcharts.event.SlipGestureDetector;
import cn.limc.androidcharts.model.SectionDataCursor;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * <p>
 * en
 * </p>
 * <p>
 * jp
 * </p>
 * <p>
 * cn
 * </p>
 * 
 * @author limc
 * @version v1.0 2014-1-20 下午2:03:08
 * 
 */
public class SlipStickChart extends StickChart {
	/**
	 * <p>
	 * Constructor of SlipStickChart
	 * </p>
	 * <p>
	 * SlipStickChart类对象的构造函数
	 * </p>
	 * <p>
	 * SlipStickChartのコンストラクター
	 * </p>
	 * 
	 * @param context
	 */
	public SlipStickChart(Context context) {
		super(context);
	    //this.dataCursor = new SectionDataCursor(this);
	}

	/**
	 * <p>
	 * Constructor of SlipStickChart
	 * </p>
	 * <p>
	 * SlipStickChart类对象的构造函数
	 * </p>
	 * <p>
	 * SlipStickChartのコンストラクター
	 * </p>
	 * 
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public SlipStickChart(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	     // this.dataCursor = new SectionDataCursor(this);
	}

	/**
	 * <p>
	 * Constructor of SlipStickChart
	 * </p>
	 * <p>
	 * SlipStickChart类对象的构造函数
	 * </p>
	 * <p>
	 * SlipStickChartのコンストラクター
	 * </p>
	 * 
	 * @param context
	 * @param attrs
	 */
	public SlipStickChart(Context context, AttributeSet attrs) {
		super(context, attrs);
	     // this.dataCursor = new SectionDataCursor(this);
	}
}
