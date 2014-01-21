/*
 * MASlipCandleStickChart.java
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


package cn.limc.androidcharts.view;

import java.util.List;

import cn.limc.androidcharts.entity.LineEntity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;

/** 
 * <p>en</p>
 * <p>jp</p>
 * <p>cn</p>
 *
 * @author limc 
 * @version v1.0 2014/01/21 12:03:25 
 *  
 */
public class MASlipCandleStickChart extends SlipCandleStickChart {

	/**
	 * <p>
	 * data to draw lines
	 * </p>
	 * <p>
	 * ラインを書く用データ
	 * </p>
	 * <p>
	 * 绘制线条用的数据
	 * </p>
	 */
	private List<LineEntity<Float>> lineData;
	
	/** 
	 * <p>Constructor of MASlipCandleStickChart</p>
	 * <p>MASlipCandleStickChart类对象的构造函数</p>
	 * <p>MASlipCandleStickChartのコンストラクター</p>
	 *
	 * @param context
	 * @param attrs
	 * @param defStyle 
	 */
	public MASlipCandleStickChart(Context context, AttributeSet attrs,
			int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	/** 
	 * <p>Constructor of MASlipCandleStickChart</p>
	 * <p>MASlipCandleStickChart类对象的构造函数</p>
	 * <p>MASlipCandleStickChartのコンストラクター</p>
	 *
	 * @param context
	 * @param attrs 
	 */
	public MASlipCandleStickChart(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	/** 
	 * <p>Constructor of MASlipCandleStickChart</p>
	 * <p>MASlipCandleStickChart类对象的构造函数</p>
	 * <p>MASlipCandleStickChartのコンストラクター</p>
	 *
	 * @param context 
	 */
	public MASlipCandleStickChart(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * <p>Called when is going to draw this chart<p> <p>チャートを書く前、メソッドを呼ぶ<p>
	 * <p>绘制图表时调用<p>
	 * 
	 * @param canvas
	 * 
	 * @see android.view.View#onDraw(android.graphics.Canvas)
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		// draw lines
		if (null != this.lineData) {
			if (0 != this.lineData.size()) {
				drawLines(canvas);
			}
		}
	}

	/**
	 * <p>
	 * draw lines
	 * </p>
	 * <p>
	 * ラインを書く
	 * </p>
	 * <p>
	 * 绘制线条
	 * </p>
	 * 
	 * @param canvas
	 */
	protected void drawLines(Canvas canvas) {
		// distance between two points
		float lineLength = ((super.getWidth() - super.getAxisMarginLeft() - super
				.getAxisMarginRight()) / super.getDisplayNumber()) - 1;
		// start point‘s X
		float startX;

		// draw MA lines
		for (int i = 0; i < lineData.size(); i++) {
			LineEntity<Float> line = (LineEntity<Float>) lineData.get(i);
			if (line.isDisplay()) {
				Paint mPaint = new Paint();
				mPaint.setColor(line.getLineColor());
				mPaint.setAntiAlias(true);
				List<Float> lineData = line.getLineData();
				// set start point’s X
				startX = super.getAxisMarginLeft() + lineLength / 2f;
				// start point
				PointF ptFirst = null;
				if (lineData != null) {
					for (int j = super.getDisplayFrom(); j < super.getDisplayFrom() + super.getDisplayNumber(); j++) {
						float value = lineData.get(j).floatValue();
						// calculate Y
						float valueY = (float) ((1f - (value - super
								.getMinValue())
								/ (super.getMaxValue() - super.getMinValue())) * (super
								.getHeight() - super.getAxisMarginBottom()));

						// if is not last point connect to previous point
						if (j > super.getDisplayFrom()) {
							canvas.drawLine(ptFirst.x, ptFirst.y, startX,
									valueY, mPaint);
						}
						// reset
						ptFirst = new PointF(startX, valueY);
						startX = startX + 1 + lineLength;
					}
				}
			}
		}
	}

	/**
	 * @return the lineData
	 */
	public List<LineEntity<Float>> getLineData() {
		return lineData;
	}

	/**
	 * @param lineData
	 *            the lineData to set
	 */
	public void setLineData(List<LineEntity<Float>> lineData) {
		this.lineData = lineData;
	}

}
