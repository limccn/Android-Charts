/*
 * SlipLineChart.java
 * Android-Charts
 *
 * Created by zhourr on 2016.
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
import android.graphics.PointF;
import android.util.AttributeSet;

import java.util.List;

import cn.limc.androidcharts.common.IFlexableGrid;
import cn.limc.androidcharts.entity.DateValueEntity;
import cn.limc.androidcharts.entity.LineEntity;


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
 * @version v1.0 2014/01/21 14:20:35
 * 
 */
public class SlipSegmentChart extends SlipLineChart {

	/*
	 * (non-Javadoc)
	 *
	 * @param context
	 *
	 * @see cn.limc.androidcharts.view.GridChart#GridChart(Context)
	 */
	public SlipSegmentChart(Context context) {
		super(context);
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
	 * @see cn.limc.androidcharts.view.GridChart#GridChart(Context,
	 * AttributeSet, int)
	 */
	public SlipSegmentChart(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @param context
	 *
	 * @param attrs
	 *
	 *
	 *
	 * @see cn.limc.androidcharts.view.GridChart#GridChart(Context,
	 * AttributeSet)
	 */
	public SlipSegmentChart(Context context, AttributeSet attrs) {
		super(context, attrs);
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
	@Override
	protected void drawLines(Canvas canvas) {
		if (null == this.linesData) {
			return;
		}
		if (0 == this.linesData.size()) {
			return;
		}
		// distance between two points
		float lineLength;
		// start point‘s X
		float startX;

		// draw lines
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
			mPaint.setAntiAlias(true);
			// start point
			PointF ptFirst = null;
//			if (axisY.getPosition() == IAxis.AXIS_Y_POSITION_LEFT) {
			if (lineAlignType == IFlexableGrid.ALIGN_TYPE_CENTER) {
				lineLength = (dataQuadrant.getPaddingWidth() / getDataDisplayNumber());
				startX = dataQuadrant.getPaddingStartX() + lineLength / 2;
			} else {
				lineLength = (dataQuadrant.getPaddingWidth() / (getDataDisplayNumber() - 1));
				startX = dataQuadrant.getPaddingStartX();
			}

			for (int j = getDisplayFrom(); j < getDisplayTo(); j++) {
				float value = lineData.get(j).getValue();
				if (isNoneDisplayValue(value)) {
					//无需显示
					ptFirst = null;
				} else {
					// calculate Y
					float valueY = (float) ((1f - (value - minValue)
							/ (maxValue - minValue)) * dataQuadrant.getPaddingHeight())
							+ dataQuadrant.getPaddingStartY();

					// if is not last point connect to previous point
					if (j > getDisplayFrom() && ptFirst != null) {
						canvas.drawLine(ptFirst.x, ptFirst.y, startX, valueY,
								mPaint);
					}
					// reset
					ptFirst = new PointF(startX, valueY);
				}
				startX = startX + lineLength;
			}
		}
	}
}
