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

import cn.limc.androidcharts.common.IChart;
import cn.limc.androidcharts.diagram.GridChart;
import cn.limc.androidcharts.series.IMeasurable;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;

public class Bar extends AbstractShape implements Rectangle{

	public static final int DEFAULT_STICK_SPACING = 2;
	public static final int DEFAULT_STICK_STROKE_WIDTH = 0;
	
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
	public static final int DEFAULT_STICK_BORDER_COLOR = Color.WHITE;

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
	public static final int DEFAULT_STICK_FILL_COLOR = Color.YELLOW;

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
	protected int stickBorderColor = DEFAULT_STICK_BORDER_COLOR;

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
	protected int stickFillColor = DEFAULT_STICK_FILL_COLOR;
	
	protected int stickStrokeWidth = DEFAULT_STICK_STROKE_WIDTH;
	protected int stickSpacing = DEFAULT_STICK_SPACING;
	
	private IMeasurable stickData;
	
    /* (non-Javadoc)
     * @see cn.limc.androidcharts.shape.Rectangle#setUp(cn.limc.androidcharts.common.IChart, float, float)
     */
    @Override
    public void setUp(IChart chart, float from, float width) {
        super.setUp(chart);
        left = from + stickSpacing / 2;
        right = from + width - stickSpacing /2;
        
    }
	
	public void setUp(IChart chart ,IMeasurable data, float from , float width) {
	    this.setUp(chart,from,width);
		setStickData(data);
	}
	
	/* (non-Javadoc)
	 * 
	 * @param canvase 
	 * @see cn.limc.androidcharts.shape.Shape#draw(android.graphics.Canvas) 
	 */
	public void draw(Canvas canvas) {	
		Paint mPaintFill = new Paint();
		mPaintFill.setStyle(Style.FILL);
		mPaintFill.setColor(stickFillColor);
		
		if (width() >= 2f && width() >= 2 * stickStrokeWidth) {
			if (stickStrokeWidth  > 0) {
				Paint mPaintBorder = new Paint();
				mPaintBorder.setStyle(Style.STROKE);
				mPaintBorder.setStrokeWidth(stickStrokeWidth);
				mPaintBorder.setColor(stickBorderColor);
				
				canvas.drawRect(left + stickStrokeWidth, top + stickStrokeWidth, right - stickStrokeWidth, bottom - stickStrokeWidth, mPaintFill);
				canvas.drawRect(left + stickStrokeWidth, top + stickStrokeWidth, right - stickStrokeWidth, bottom - stickStrokeWidth, mPaintBorder);
			}else{
				canvas.drawRect(left, top, right, bottom, mPaintFill);
			}
		} else {
			canvas.drawLine(left, top, left, bottom, mPaintFill);	
		}
	}

	/**
	 * @return the stickData
	 */
	public IMeasurable getStickData() {
		return stickData;
	}

	/**
	 * @param stickData the stickData to set
	 */
	public void setStickData(IMeasurable stickData) {
		this.stickData = stickData;
		GridChart chart = (GridChart)getInChart();
		float highY = (float) ((1f - (stickData.getHigh() - chart.getDataRange().getMinValue()) / (chart.getDataRange().getValueRange()))
				* (chart.getDataQuadrant().getPaddingHeight()) + chart.getDataQuadrant().getPaddingStartY());
		float lowY = (float) ((1f - (stickData.getLow() - chart.getDataRange().getMinValue()) / (chart.getDataRange().getValueRange()))
				* (chart.getDataQuadrant().getPaddingHeight()) + chart.getDataQuadrant().getPaddingStartY());
		
		top = highY;
		bottom = lowY;
	}

    /**
     * @return the stickBorderColor
     */
    public int getStickBorderColor() {
        return stickBorderColor;
    }

    /**
     * @param stickBorderColor the stickBorderColor to set
     */
    public void setStickBorderColor(int stickBorderColor) {
        this.stickBorderColor = stickBorderColor;
    }

    /**
     * @return the stickFillColor
     */
    public int getStickFillColor() {
        return stickFillColor;
    }

    /**
     * @param stickFillColor the stickFillColor to set
     */
    public void setStickFillColor(int stickFillColor) {
        this.stickFillColor = stickFillColor;
    }

    /**
     * @return the stickStrokeWidth
     */
    public int getStickStrokeWidth() {
        return stickStrokeWidth;
    }

    /**
     * @param stickStrokeWidth the stickStrokeWidth to set
     */
    public void setStickStrokeWidth(int stickStrokeWidth) {
        this.stickStrokeWidth = stickStrokeWidth;
    }

    /**
     * @return the stickSpacing
     */
    public int getStickSpacing() {
        return stickSpacing;
    }

    /**
     * @param stickSpacing the stickSpacing to set
     */
    public void setStickSpacing(int stickSpacing) {
        this.stickSpacing = stickSpacing;
    }
}
