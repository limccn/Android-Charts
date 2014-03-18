/*
 * BaseChart.java
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

package cn.limc.androidcharts.view;

import android.content.Context;
import android.graphics.Rect;
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
public abstract class BaseChart extends View implements IChart {

	public static final String LOG_TAG = "BaseChart";

	/*
	 * (non-Javadoc)
	 * 
	 * @param context
	 * 
	 * @see android.view.View#BaseChart(Context)
	 */
	public BaseChart(Context context) {
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
	public BaseChart(Context context, AttributeSet attrs) {
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
	public BaseChart(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
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

	// protected int[] getStyleableName(Context context) {
	// try {
	// StringBuilder sbStyleable = new StringBuilder();
	// sbStyleable.append(context.getApplicationContext().getPackageName());
	// sbStyleable.append(".R$styleable");
	// String styleable = sbStyleable.toString();
	// int[] id =
	// (int[])Class.forName(styleable).getField(STYLEABLE_NAME).get(null);
	// return id;
	// }catch (Exception e) {
	// return null;
	// }
	// }
	//
	// protected int getStyleableAttrId(Context context , String attr) {
	// try {
	// StringBuilder sbStyleable = new StringBuilder();
	// sbStyleable.append(context.getApplicationContext().getPackageName());
	// sbStyleable.append(".R$styleable");
	// String styleable = sbStyleable.toString();
	//
	// Class<?> clazz = Class.forName(styleable);
	// int[] id = (int[])clazz.getField(STYLEABLE_NAME).get(null);
	//
	// StringBuilder sbAttribute = new StringBuilder();
	// sbAttribute.append(STYLEABLE_NAME);
	// sbAttribute.append("_");
	// sbAttribute.append(attr);
	// String attribute = sbAttribute.toString();
	//
	// int index = clazz.getField(attribute).getInt(null);
	// return id[index];
	// }catch (Exception e) {
	// return 0;
	// }
	// }
}
