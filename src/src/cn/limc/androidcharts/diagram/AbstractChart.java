/*
 * AbstractChart.java
 * Android-Charts
 *
 * Created by limc on 2013.
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

package cn.limc.androidcharts.diagram;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

/**
 * <p>
 * Base view of all charts
 * </p>
 * <p>
 * 全部チャートのベースオブジェクト。
 * </p>
 * <p>
 * 所有图表对象的基类
 * </p>
 * 
 * @author limc
 * @version v1.0 2011/05/29 15:17:50
 * 
 */
public abstract class AbstractChart extends View {

	public static final String LOG_TAG = "AbstractChart";
	
	/**
	 * <p>
	 * default background color
	 * </p>
	 * <p>
	 * 背景の色のデフォルト値
	 * </p>
	 * <p>
	 * 默认背景色
	 * </p>
	 */
	public static final int DEFAULT_BACKGROUND_COLOR = Color.BLACK;
	
	/**
	 * <p>
	 * Should display the border?
	 * </p>
	 * <p>
	 * 枠を表示するか?
	 * </p>
	 * <p>
	 * 默认控件是否显示边框
	 * </p>
	 */
	public static final boolean DEFAULT_DISPLAY_BORDER = Boolean.TRUE;

	/**
	 * <p>
	 * default color of text for the longitude　degrees display
	 * </p>
	 * <p>
	 * 経度のタイトルの色のデフォルト値
	 * </p>
	 * <p>
	 * 默认经线刻度字体颜色
	 * </p>
	 */
	public static final int DEFAULT_BORDER_COLOR = Color.LTGRAY;

	public static final float DEFAULT_BORDER_WIDTH = 1f;
	
	/**
	 * <p>
	 * Should display the border?
	 * </p>
	 * <p>
	 * 枠を表示するか?
	 * </p>
	 * <p>
	 * 控件是否显示边框
	 * </p>
	 */
	protected boolean displayBorder = DEFAULT_DISPLAY_BORDER;

	/**
	 * <p>
	 * Color of grid‘s border line
	 * </p>
	 * <p>
	 * 枠線の色
	 * </p>
	 * <p>
	 * 图边框的颜色
	 * </p>
	 */
	protected int borderColor = DEFAULT_BORDER_COLOR;

	protected float borderWidth = DEFAULT_BORDER_WIDTH;

	/*
	 * (non-Javadoc)
	 * 
	 * @param context
	 * 
	 * @see android.view.View#BaseChart(Context)
	 */
	public AbstractChart(Context context) {
		super(context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @param context
	 * 
	 * @param attrs
	 * 
	 * @see android.view.View#BaseChart(Context, AttributeSet)
	 */
	public AbstractChart(Context context, AttributeSet attrs) {
		super(context, attrs);
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
	 * @see android.view.View#BaseChart(Context, AttributeSet, int)
	 */
	public AbstractChart(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (this.displayBorder) {
			drawBorder(canvas);
		}
	}
	
	/**
	 * <p>
	 * draw border
	 * </p>
	 * <p>
	 * グラプのボーダーを書く
	 * </p>
	 * <p>
	 * 绘制边框
	 * </p>
	 * 
	 * @param canvas
	 */
	protected void drawBorder(Canvas canvas) {
		Paint mPaint = new Paint();
		mPaint.setColor(borderColor);
		mPaint.setStrokeWidth(borderWidth);
		mPaint.setStyle(Style.STROKE);
		// draw a rectangle
		canvas.drawRect(borderWidth / 2, borderWidth / 2, super.getWidth()
				- borderWidth / 2, super.getHeight() - borderWidth / 2, mPaint);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @param widthMeasureSpec
	 * 
	 * @param heightMeasureSpec
	 * 
	 * @see android.view.View#onMeasure(int, int)
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		setMeasuredDimension(measureWidth(widthMeasureSpec),
				measureHeight(heightMeasureSpec));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @param gainFocus
	 * 
	 * @param direction
	 * 
	 * @param previouslyFocusedRect
	 * 
	 * @see android.view.View#onFocusChanged(boolean, int,
	 * android.graphics.Rect)
	 */
	@Override
	protected void onFocusChanged(boolean gainFocus, int direction,
			Rect previouslyFocusedRect) {
		super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
	}

	private int measureWidth(int measureSpec) {
		int result = 0;
		int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);

		if (specMode == MeasureSpec.EXACTLY) {
			result = specSize;
		} else if (specMode == MeasureSpec.AT_MOST) {
			result = Math.min(result, specSize);
		}
		return result;
	}

	private int measureHeight(int measureSpec) {
		int result = 0;
		int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);

		if (specMode == MeasureSpec.EXACTLY) {
			result = specSize;
		} else if (specMode == MeasureSpec.AT_MOST) {
			result = Math.min(result, specSize);
		}
		return result;
	}	
	
	/**
	 * @return the displayBorder
	 */
	public boolean isDisplayBorder() {
		return displayBorder;
	}

	/**
	 * @param displayBorder
	 *            the displayBorder to set
	 */
	public void setDisplayBorder(boolean displayBorder) {
		this.displayBorder = displayBorder;
	}

	/**
	 * @return the borderColor
	 */
	public int getBorderColor() {
		return borderColor;
	}

	/**
	 * @param borderColor
	 *            the borderColor to set
	 */
	public void setBorderColor(int borderColor) {
		this.borderColor = borderColor;
	}

	/**
	 * @return the borderWidth
	 */
	public float getBorderWidth() {
		return borderWidth;
	}

	/**
	 * @param borderWidth
	 *            the borderWidth to set
	 */
	public void setBorderWidth(float borderWidth) {
		this.borderWidth = borderWidth;
	}
}
