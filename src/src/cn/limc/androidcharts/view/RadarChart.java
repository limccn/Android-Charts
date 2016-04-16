/*
 * RadarChart.java
 * Android-Charts
 *
 * Created by limc on 2014/07/29.
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

import cn.limc.androidcharts.entity.TitleValueEntity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Paint.Style;
import android.util.AttributeSet;

/**
 * 
 * <p>
 * RadarChart is a kind of graph that display data on web-like graph. each
 * data was displayed in the longitude lines,like a area graph.
 * </p>
 * <p>
 * RadarChartは円グラフとアリアグラフを合わせるのグラフ一種です、データをワッブで表示します。
 * </p>
 * <p>
 * RadarChart是一种将数据内容显示在网状图上的一种图表、是将饼图和面积图结合表示的一种图表
 * </p>
 * 
 * @author limc
 * @version v1.0 2011/05/30 14:01:54
 * 
 */
public class RadarChart extends SpiderWebChart {

	/*
	 * (non-Javadoc)
	 * 
	 * @param context
	 * 
	 * @see cn.limc.androidcharts.view.AbstractBaseChart#BaseChart(Context)
	 */
	public RadarChart(Context context) {
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
	 * @see cn.limc.androidcharts.view.AbstractBaseChart#BaseChart(Context,AttributeSet,
	 * int)
	 */
	public RadarChart(Context context, AttributeSet attrs, int defStyle) {
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
	public RadarChart(Context context, AttributeSet attrs) {
		super(context, attrs);
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

	/**
	 * <p>
	 * draw spider web
	 * </p>
	 * <p>
	 * ウェブを書く
	 * </p>
	 * <p>
	 * 绘制蜘蛛网
	 * </p>
	 * 
	 * @param canvas
	 *            Canvas
	 */
	protected void drawWeb(Canvas canvas) {
		Paint mPaintWebFill = new Paint();
		mPaintWebFill.setColor(Color.GRAY);
		mPaintWebFill.setAntiAlias(true);

		Paint mPaintWebBorder = new Paint();
		mPaintWebBorder.setColor(Color.WHITE);
		mPaintWebBorder.setStyle(Style.STROKE);
		mPaintWebBorder.setStrokeWidth(2);
		mPaintWebBorder.setAntiAlias(true);

		Paint mPaintWebInnerBorder = new Paint();
		mPaintWebInnerBorder.setColor(Color.LTGRAY);
		mPaintWebInnerBorder.setStyle(Style.STROKE);
		mPaintWebInnerBorder.setAntiAlias(true);

		Paint mPaintLine = new Paint();
		mPaintLine.setColor(Color.LTGRAY);

		Paint mPaintFont = new Paint();
		mPaintFont.setColor(Color.LTGRAY);

		List<PointF> pointList = getWebAxisPoints(1);

		// draw border
		if (null != data) {
			for (int i = 0; i < pointList.size(); i++) {
				PointF pt = pointList.get(i);
				// draw title
				String title = data.get(0).get(i).getTitle();
				float realx = 0;
				float realy = 0;

				// TODO title position
				if (pt.x < position.x) {
					realx = pt.x - mPaintFont.measureText(title) - 5;
				} else if (pt.x > position.x) {
					realx = pt.x + 5;
				} else {
					realx = pt.x - mPaintFont.measureText(title) / 2;
				}

				if (pt.y > position.y) {
					realy = pt.y + 10;
				} else if (pt.y < position.y) {
					realy = pt.y - 2;
				} else {
					realy = pt.y - 5;
				}

				canvas.drawText(title, realx, realy, mPaintFont);
			}
		}
		
		// draw background
		canvas.drawCircle(position.x, position.y, 
                longitudeLength, mPaintWebBorder);
		canvas.drawCircle(position.x, position.y, 
                longitudeLength, mPaintWebFill);

		// draw web
		for (int j = 1; j < latitudeNum; j++) {
		    canvas.drawCircle(position.x, position.y, 
		            longitudeLength * j / latitudeNum, mPaintWebInnerBorder);
		}

		// draw longitude lines
		for (int i = 0; i < pointList.size(); i++) {
			PointF pt = pointList.get(i);
			canvas.drawLine(position.x, position.y, pt.x, pt.y, mPaintLine);
		}
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
			for (int j = 0; j < data.size(); j++) {
				List<TitleValueEntity> list = data.get(j);

				Paint mPaintFill = new Paint();
				mPaintFill.setColor(COLORS[j]);
				mPaintFill.setStyle(Style.FILL);
				mPaintFill.setAntiAlias(true);
				mPaintFill.setAlpha(70);

				Paint mPaintBorder = new Paint();
				mPaintBorder.setColor(COLORS[j]);
				mPaintBorder.setStyle(Style.STROKE);
				mPaintBorder.setStrokeWidth(2);
				mPaintBorder.setAntiAlias(true);

				// paint to draw fonts
				Paint mPaintFont = new Paint();
				mPaintFont.setColor(Color.WHITE);

				// paint to draw points
				Paint mPaintPoint = new Paint();
				mPaintPoint.setColor(COLORS[j]);

				Path mPath = new Path();

				// get points to draw
				List<PointF> pointList = getDataPoints(list);
				// initialize path
				for (int i = 0; i < pointList.size(); i++) {
					PointF pt = pointList.get(i);
					if (i == 0) {
						mPath.moveTo(pt.x, pt.y);
					} else {
						mPath.lineTo(pt.x, pt.y);
					}
					canvas.drawCircle(pt.x, pt.y, 3, mPaintPoint);
				}
				mPath.close();

				canvas.drawPath(mPath, mPaintFill);
				canvas.drawPath(mPath, mPaintBorder);
			}
		}
	}
}
