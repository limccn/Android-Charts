/*
 * PieChart.java
 * Android-Charts
 *
 * Created by limc on 2011/05/29.
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

/**
 * 
 */
package cn.limc.androidcharts.view;

import java.util.List;

import cn.limc.androidcharts.entity.TitleValueColorEntity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import android.util.AttributeSet;

/**
 * 
 * <p>
 * PieChart is a kind of graph that display all in a pie-like graph, each of the
 * data will get a part of the pie. another kind of pie chart you can refer from
 * PizzaChart
 * </p>
 * <p>
 * PieChartは円グラフの一種です、分割表示をお使い場合、PizzaChartを利用してください
 * </p>
 * <p>
 * PieChart是最简单的饼图，如果您需要可以分割表示的饼图，请参照PizzaChart
 * </p>
 * 
 * @author limc
 * @version v1.0 2011/05/29 15:15:03
 * 
 */
public class PieChart extends RoundChart {

	/**
	 * <p>
	 * Data
	 * </p>
	 * <p>
	 * データ
	 * </p>
	 * <p>
	 * 图表数据
	 * </p>
	 */
	protected List<TitleValueColorEntity> data;

	/*
	 * (non-Javadoc)
	 * 
	 * @param context
	 * 
	 * @see cn.limc.androidcharts.view.AbstractBaseChart#BaseChart(Context)
	 */
	public PieChart(Context context) {
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
	 * @see cn.limc.androidcharts.view.AbstractBaseChart#BaseChart(Context,
	 * AttributeSet, int)
	 */
	public PieChart(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @param context
	 * 
	 * @param attrs
	 * 
	 * @see cn.limc.androidcharts.view.AbstractBaseChart#BaseChart(Context,
	 * AttributeSet)
	 */
	public PieChart(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * <p>Called when is going to draw this chart</p> <p>チャートを書く前、メソッドを呼ぶ</p>
	 * <p>绘制图表时调用</p>
	 * 
	 * @param canvas
	 * 
	 * @see android.view.View#onDraw(android.graphics.Canvas)
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		// get safe rect
		int rect = super.getWidth() > super.getHeight() ? super.getHeight()
				: super.getWidth();

		// calculate radius length
		longitudeLength = (int) ((rect / 2f) * 0.90);

		// calculate position
		position = new Point((int) (getWidth() / 2f), (int) (getHeight() / 2f));

		// draw this chart
		drawCircle(canvas);

		// draw data on chart
		drawData(canvas);
	}

	/**
	 * <p>
	 * Draw a circle
	 * </p>
	 * <p>
	 * 満丸を書く
	 * </p>
	 * <p>
	 * 绘制一个圆
	 * </p>
	 * 
	 * @param canvas
	 */
	protected void drawCircle(Canvas canvas) {

		Paint mPaintCircleBorder = new Paint();
		mPaintCircleBorder.setColor(circleBorderColor);
		mPaintCircleBorder.setStyle(Style.STROKE);
		mPaintCircleBorder.setStrokeWidth(2);
		mPaintCircleBorder.setAntiAlias(true);

		// draw a circle
		canvas.drawCircle(position.x, position.y, longitudeLength,
				mPaintCircleBorder);
	}

	/**
	 * <p>
	 * Draw the data
	 * </p>
	 * <p>
	 * チャートでデータを書く
	 * </p>
	 * <p>
	 * 将数据绘制在图表上
	 * </p>
	 * 
	 * @param canvas
	 */
	protected void drawData(Canvas canvas) {
		if (null != data) {
			// sum all data's value
			float sum = 0;
			for (int i = 0; i < data.size(); i++) {
				sum = sum + data.get(i).getValue();
			}

			Paint mPaintFill = new Paint();
			mPaintFill.setStyle(Style.FILL);
			mPaintFill.setAntiAlias(true);

			Paint mPaintBorder = new Paint();
			mPaintBorder.setStyle(Style.STROKE);
			mPaintBorder.setColor(longitudeColor);
			mPaintBorder.setAntiAlias(true);

			int offset = -90;
			// draw arcs of every piece
			for (int j = 0; j < data.size(); j++) {
				TitleValueColorEntity e = data.get(j);

				// get color
				mPaintFill.setColor(e.getColor());

				RectF oval = new RectF(position.x - longitudeLength, position.y
						- longitudeLength, position.x + longitudeLength, position.y
						+ longitudeLength);
				int sweep = Math.round(e.getValue() / sum * 360f);
				canvas.drawArc(oval, offset, sweep, true, mPaintFill);
				canvas.drawArc(oval, offset, sweep, true, mPaintBorder);
				offset = offset + sweep;
			}

			float sumvalue = 0f;
			for (int k = 0; k < data.size(); k++) {
				TitleValueColorEntity e = data.get(k);
				float value = e.getValue();
				sumvalue = sumvalue + value;
				float rate = (sumvalue - value / 2) / sum;
				mPaintFill.setColor(Color.BLUE);

				// percentage
				float percentage = (int) (value / sum * 10000) / 100f;

				float offsetX = (float) (position.x - longitudeLength * 0.5
						* Math.sin(rate * -2 * Math.PI));
				float offsetY = (float) (position.y - longitudeLength * 0.5
						* Math.cos(rate * -2 * Math.PI));

				Paint mPaintFont = new Paint();
				mPaintFont.setColor(Color.LTGRAY);

				// draw titles
				String title = e.getTitle();
				float realx = 0;
				float realy = 0;

				// TODO title position
				if (offsetX < position.x) {
					realx = offsetX - mPaintFont.measureText(title) - 5;
				} else if (offsetX > position.x) {
					realx = offsetX + 5;
				}

				if (offsetY > position.y) {
					if (value / sum < 0.2f) {
						realy = offsetY + 10;
					} else {
						realy = offsetY + 5;
					}
				} else if (offsetY < position.y) {
					if (value / sum < 0.2f) {
						realy = offsetY - 10;
					} else {
						realy = offsetY + 5;
					}
				}

				canvas.drawText(title, realx, realy, mPaintFont);
				canvas.drawText(String.valueOf(percentage) + "%", realx,
						realy + 12, mPaintFont);
			}
		}
	}

	/**
	 * @return the data
	 */
	public List<TitleValueColorEntity> getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(List<TitleValueColorEntity> data) {
		this.data = data;
	}
}
