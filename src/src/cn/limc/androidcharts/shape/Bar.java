/*
 * Bar.java
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


package cn.limc.androidcharts.shape;

import cn.limc.androidcharts.component.DataComponent;
import cn.limc.androidcharts.series.IMeasurable;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;

public class Bar extends AbstractShape implements Rectangle{

	public static final int DEFAULT_BAR_SPACING = 2;
	public static final int DEFAULT_BAR_STROKE_WIDTH = 0;
	
	/**
	 * <p>
	 * default color for display stick border
	 * </p>
	 * <p>
	 * 表示スティックのボーダーの色のデフォルト値
	 * </p>
	 * <p>
	 * 默认表示柱条的边框颜色
	 * </p>
	 */
	public static final int DEFAULT_BAR_BORDER_COLOR = Color.WHITE;

	/**
	 * <p>
	 * default color for display stick
	 * </p>
	 * <p>
	 * 表示スティックの色のデフォルト値
	 * </p>
	 * <p>
	 * 默认表示柱条的填充颜色
	 * </p>
	 */
	public static final int DEFAULT_BAR_FILL_COLOR = Color.YELLOW;

	/**
	 * <p>
	 * Color for display stick border
	 * </p>
	 * <p>
	 * 表示スティックのボーダーの色
	 * </p>
	 * <p>
	 * 表示柱条的边框颜色
	 * </p>
	 */
	protected int barBorderColor = DEFAULT_BAR_BORDER_COLOR;

	/**
	 * <p>
	 * Color for display stick
	 * </p>
	 * <p>
	 * 表示スティックの色
	 * </p>
	 * <p>
	 * 表示柱条的填充颜色
	 * </p>
	 */
	protected int barFillColor = DEFAULT_BAR_FILL_COLOR;
	
	protected int barStrokeWidth = DEFAULT_BAR_STROKE_WIDTH;
	protected int barSpacing = DEFAULT_BAR_SPACING;
	
	private IMeasurable barData;
	
    /* (non-Javadoc)
     * @see cn.limc.androidcharts.shape.Rectangle#setUp(cn.limc.androidcharts.common.IChart, float, float)
     */
    @Override
    public void setUp(DataComponent component, float from, float width) {
        super.setUp(component);
        left = from + barSpacing / 2;
        right = from + width - barSpacing /2;
        
    }
	
	public void setUp(DataComponent component ,IMeasurable data, float from , float width) {
	    this.setUp(component,from,width);
		setStickData(data);
	}
	
//	public void setUp(AbstractComponent quadrant ,IMeasurable mData, float from , float width) {
//	    setStickData(mData);
//	}
	
	/* (non-Javadoc)
	 * 
	 * @param canvase 
	 * @see cn.limc.androidcharts.shape.Shape#draw(android.graphics.Canvas) 
	 */
	public void draw(Canvas canvas) {	
		Paint mPaintFill = new Paint();
		mPaintFill.setStyle(Style.FILL);
		mPaintFill.setColor(barFillColor);
		
		if (width() >= 2f && width() >= 2 * barStrokeWidth) {
			if (barStrokeWidth  > 0) {
				Paint mPaintBorder = new Paint();
				mPaintBorder.setStyle(Style.STROKE);
				mPaintBorder.setStrokeWidth(barStrokeWidth);
				mPaintBorder.setColor(barBorderColor);
				
				canvas.drawRect(left + barStrokeWidth, top + barStrokeWidth, right - barStrokeWidth, bottom - barStrokeWidth, mPaintFill);
				canvas.drawRect(left + barStrokeWidth, top + barStrokeWidth, right - barStrokeWidth, bottom - barStrokeWidth, mPaintBorder);
			}else{
				canvas.drawRect(left, top, right, bottom, mPaintFill);
			}
		} else {
			canvas.drawLine(left, top, left, bottom, mPaintFill);	
		}
	}

	/**
	 * @return the barData
	 */
	public IMeasurable getStickData() {
		return barData;
	}

	/**
	 * @param barData the barData to set
	 */
	public void setStickData(IMeasurable stickData) {
		this.barData = stickData;
		float highY = (float) component.heightForRate(stickData.getHigh());
		float lowY = (float)component.heightForRate(stickData.getLow());
		
		top = highY;
		bottom = lowY;
	}
}
