/*
 * SlipMinusStickChart.java
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

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import cn.limc.androidcharts.model.MinusStickRangeCalculator;
import cn.limc.androidcharts.series.ChartDataTable;
import cn.limc.androidcharts.series.IMeasurable;
import cn.limc.androidcharts.shape.Bar;

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
 * @version v1.0 2014/01/21 14:05:50
 * 
 */
public class SlipMinusStickChart extends SlipStickChart {
    

	/*
	 * (non-Javadoc)
	 * 
	 * @param context
	 * 
	 * @see cn.limc.androidcharts.diagram.GridChart#GridChart(Context)
	 */
	public SlipMinusStickChart(Context context) {
		super(context);
//		this.dataRange = new MinusStickRangeCalculator(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @param context
	 * 
	 * @param attrs
	 * 
	 * @param defStyle
	 * 
	 * @see cn.limc.androidcharts.diagram.GridChart#GridChart(Context,
	 * AttributeSet, int)
	 */
	public SlipMinusStickChart(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
//		this.dataRange = new MinusStickRangeCalculator(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @param context
	 * 
	 * @param attrs
	 * 
	 * @see cn.limc.androidcharts.diagram.GridChart#GridChart(Context,
	 * AttributeSet)
	 */
	public SlipMinusStickChart(Context context, AttributeSet attrs) {
		super(context, attrs);
//		this.dataRange = new MinusStickRangeCalculator(this);
	}

//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @param canvas
//	 * 
//	 * 
//	 * 
//	 * @see cn.limc.androidcharts.diagram.StickChart#drawSticks(Canvas)
//	 */
//	@Override
//	protected void drawSticks(Canvas canvas) {
//        if (null == chartData) {
//            return;
//        }
//        if (chartData.size() == 0) {
//            return;
//        }
//
//        float stickWidth = dataQuadrant.getPaddingWidth() / getDisplayNumber();
//        float stickX = dataQuadrant.getPaddingStartX();
//
//        for(int i=0; i< chartData.size() ; i++){
//            ChartDataTable table = chartData.getChartTable(i);
//            if (null == table) {
//                continue;
//            }
//            if(table.size() == 0){
//                continue;
//            }
//            for (int j = getDisplayFrom(); j < getDisplayTo(); j++) {
//                IMeasurable stick = (IMeasurable)table.get(j);
//                
//                Bar mole = new  Bar();
//                mole.setUp(this,stick,stickX,stickWidth);
//                mole.draw(canvas);
//
//                // next x
//                stickX = stickX + stickWidth;
//            }
//        }
//    }
}
