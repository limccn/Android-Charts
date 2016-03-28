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


package cn.limc.androidcharts.axis;

import android.graphics.Canvas;
import android.graphics.Paint;
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
    
    /**
     * <p>
     * default margin of the axis to the left border
     * </p>
     * <p>
     * 轴線より左枠線の距離のデフォルト値
     * </p>
     * <p>
     * 默认轴线左边距
     * </p>
     */
    public static final float DEFAULT_WIDTH = 50f;
    
	protected float width = DEFAULT_WIDTH;
	
	/** 
	 * <p>Constructor of VerticalAxis</p>
	 * <p>VerticalAxis类对象的构造函数</p>
	 * <p>VerticalAxisのコンストラクター</p>
	 *
	 * @param position 
	 */
	public VerticalAxis(GridChart inChart, int position) {
		super(inChart,position);
	}
	/* (non-Javadoc)
	 * 
	 * @return 
	 * @see cn.limc.androidcharts.common.IQuadrant#getQuadrantWidth() 
	 */
	public float getWidth() {
		return width;
	}
	/* (non-Javadoc)
	 * 
	 * @return 
	 * @see cn.limc.androidcharts.common.IQuadrant#getQuadrantHeight() 
	 */
	public float getHeight() {
		return inChart.getHeight() - 2 * inChart.getBorderWidth();
	}
	
	 /**
     * <p>
     * draw Y Axis
     * </p>
     * <p>
     * Y軸を書く
     * </p>
     * <p>
     * 绘制Y轴
     * </p>
     * 
     * @param canvas
     */
    public void draw(Canvas canvas) {
//        float length = inChart.getHeight() - inChart.getAxisX().getHeight()
//                - inChart.getBorderWidth();
//        float postX;
//        if (position == AXIS_Y_POSITION_LEFT) {
//            postX = inChart.getBorderWidth() + width + lineWidth / 2;
//        } else {
//            postX = inChart.getWidth() - inChart.getBorderWidth() - width
//                    - lineWidth / 2;
//        }
//
//        Paint mPaint = new Paint();
//        mPaint.setColor(lineColor);
//        mPaint.setStrokeWidth(lineWidth);
//
//        canvas.drawLine(postX, inChart.getBorderWidth(), postX, length, mPaint);
    }
}
