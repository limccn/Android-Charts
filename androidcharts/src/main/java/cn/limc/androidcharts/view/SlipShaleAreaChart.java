/*
 * SlipAreaChart.java
 * Android-Charts
 *
 * Created by zhourr on 2014.
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
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;

import java.util.List;

import cn.limc.androidcharts.common.IFlexableGrid;
import cn.limc.androidcharts.entity.DateValueEntity;
import cn.limc.androidcharts.entity.LineEntity;
import cn.limc.androidcharts.entity.ShaleData;

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
 * @author zhourr
 * @version v1.0 2016/05/30 16:19:37
 *
 */
public class SlipShaleAreaChart extends SlipSegmentChart {

	private ShaleData[] nodes;

	/**
	 * <p>
	 * Constructor of SlipAreaChart
	 * </p>
	 * <p>
	 * SlipAreaChart类对象的构造函数
	 * </p>
	 * <p>
	 * SlipAreaChartのコンストラクター
	 * </p>
	 *
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public SlipShaleAreaChart(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	/**
	 * <p>
	 * Constructor of SlipAreaChart
	 * </p>
	 * <p>
	 * SlipAreaChart类对象的构造函数
	 * </p>
	 * <p>
	 * SlipAreaChartのコンストラクター
	 * </p>
	 *
	 * @param context
	 * @param attrs
	 */
	public SlipShaleAreaChart(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	/**
	 * <p>
	 * Constructor of SlipAreaChart
	 * </p>
	 * <p>
	 * SlipAreaChart类对象的构造函数
	 * </p>
	 * <p>
	 * SlipAreaChartのコンストラクター
	 * </p>
	 *
	 * @param context
	 */
	public SlipShaleAreaChart(Context context) {
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
	}

	@Override
	public void drawData(Canvas canvas){
		super.drawData(canvas);
		drawAreas(canvas);
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
	protected void drawAreas(Canvas canvas) {
		if (null == linesData) {
			return;
		}
		if (0 == linesData.size()) {
			return;
		}

		if (nodes == null){
			drawAreas(canvas, getDisplayFrom(), getDisplayTo() - 1);
		}else {
			for (ShaleData tradeNode:nodes) {
				drawAreas(canvas, tradeNode.getStartIndex(), tradeNode.getFinalIndex());
			}
		}
	}

	protected void drawAreas(Canvas canvas, int startIndex, int finalIndex){
		// distance between two points
		float lineLength;
		// start point‘s X
		float startX;
		// draw areas
		for (int i = 0; i < linesData.size(); i++) {
			LineEntity<DateValueEntity> line = (LineEntity<DateValueEntity>) linesData
					.get(i);
			if (line == null) {
				continue;
			}
			if (line.isDisplay() == false) {
				continue;
			}
			List<DateValueEntity> lineData = line.getLineData();
			if (lineData == null) {
				continue;
			}

			Paint mPaint = new Paint();
			mPaint.setColor(line.getLineColor());
			mPaint.setAlpha(70);
			mPaint.setAntiAlias(true);

			// set start point’s X
			if (lineAlignType == IFlexableGrid.ALIGN_TYPE_CENTER) {
				lineLength= (dataQuadrant.getPaddingWidth() / getDisplayNumber());
				startX = dataQuadrant.getPaddingStartX() + lineLength / 2;
			}else {
				lineLength= (dataQuadrant.getPaddingWidth() / (getDisplayNumber() - 1));
				startX = dataQuadrant.getPaddingStartX();
			}

			Path linePath = new Path();
			for (int j = getDisplayFrom(); j < getDisplayTo(); j++) {
				float value = lineData.get(j).getValue();
				// calculate Y
				float valueY = (float) ((1f - (value - minValue)
						/ (maxValue - minValue)) * dataQuadrant.getPaddingHeight())
						+ dataQuadrant.getPaddingStartY();

				// if is not last point connect to previous point
				if (j == startIndex) {
					linePath.moveTo(startX, dataQuadrant.getPaddingEndY());
					linePath.lineTo(startX, valueY);
				} else if (j == finalIndex) {
					linePath.lineTo(startX, valueY);
					linePath.lineTo(startX, dataQuadrant.getPaddingEndY());
				} else if (j < startIndex || j > finalIndex) {
				} else {
					linePath.lineTo(startX, valueY);
				}
				startX = startX + lineLength;
			}
			linePath.close();
			canvas.drawPath(linePath, mPaint);
		}
	}

	public ShaleData[] getNodes() {
		return nodes;
	}

	public void setNodes(ShaleData[] nodes) {
		this.nodes = nodes;
	}

}
