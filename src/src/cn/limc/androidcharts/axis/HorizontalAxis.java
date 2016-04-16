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
 * @version v1.0 2014/06/24 19:58:57 
 *  
 */
public class HorizontalAxis extends Axis {
    /**
     * <p>
     * default margin of the axis to the bottom border
     * </p>
     * <p>
     * 轴線より下枠線の距離のデフォルト値
     * </p>
     * <p>
     * 默认轴线下边距
     * </p>
     */
    public static final float DEFAULT_HEIGHT = 16f;

	protected float height = DEFAULT_HEIGHT;
	/** 
	 * <p>Constructor of HorizontalAxis</p>
	 * <p>HorizontalAxis类对象的构造函数</p>
	 * <p>HorizontalAxisのコンストラクター</p>
	 *
	 * @param inChart
	 * @param position 
	 */
	public HorizontalAxis(GridChart inChart, int position) {
		super(inChart, position);
	}

	/* (non-Javadoc)
	 * 
	 * @return 
	 * @see cn.limc.androidcharts.common.IQuadrant#getWidth() 
	 */
	public float getWidth() {
		return inChart.getWidth() - 2 * inChart.getBorderWidth();
	}

	/* (non-Javadoc)
	 * 
	 * @return 
	 * @see cn.limc.androidcharts.common.IQuadrant#getHeight() 
	 */
	public float getHeight() {
		return height;
	}
	
	   /**
     * <p>
     * draw X Axis
     * </p>
     * <p>
     * X軸を書く
     * </p>
     * <p>
     * 绘制X轴
     * </p>
     * 
     * @param canvas
     */
    public void draw(Canvas canvas) {  
        float length = inChart.getWidth();
        float postY;
        if (position == AXIS_X_POSITION_BOTTOM) {
            postY = inChart.getHeight() - height - inChart.getBorderWidth()
                    - lineWidth / 2;
        } else {
            postY = inChart.getHeight() - inChart.getBorderWidth() - lineWidth / 2;
        }

        Paint mPaint = new Paint();
        mPaint.setColor(lineColor);
        mPaint.setStrokeWidth(lineWidth);

        canvas.drawLine(inChart.getBorderWidth(), postY, length, postY, mPaint);
    }
}
