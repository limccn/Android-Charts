/*
 * SpiderWebChart.java
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

import java.util.ArrayList;
import java.util.List;

import cn.limc.androidcharts.entity.TitleValueEntity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Paint.Style;
import android.util.AttributeSet;

/**
 * 
 * <p>
 * SpiderWebChart is a kind of graph that display data on web-like graph. each
 * data was displayed in the longitude lines,like a area graph.
 * </p>
 * <p>
 * SpiderWebChartは円グラフとアリアグラフを合わせるのグラフ一種です、データをワッブで表示します。
 * </p>
 * <p>
 * SpiderWebChart是一种将数据内容显示在网状图上的一种图表、是将饼图和面积图结合表示的一种图表
 * </p>
 * 
 * @author limc
 * @version v1.0 2011/05/30 14:01:54
 * 
 */
public class SpiderWebChart extends BaseChart {

	/**
	 * <p>
	 * default title
	 * </p>
	 * <p>
	 * タイトルのデフォルト値
	 * </p>
	 * <p>
	 * 默认图表标题
	 * </p>
	 */
	public static final String DEFAULT_TITLE = "Spider Web Chart";

	/**
	 * <p>
	 * default should display longitude lines
	 * </p>
	 * <p>
	 * 経線を表示する
	 * </p>
	 * <p>
	 * 默认是否显示经线
	 * </p>
	 */
	public static final boolean DEFAULT_DISPLAY_LONGITUDE = true;

	/**
	 * <p>
	 * default longitude numbers
	 * </p>
	 * <p>
	 * 経線の数量のデフォルト値
	 * </p>
	 * <p>
	 * 默认经线数量
	 * </p>
	 */
	public static final int DEFAULT_LONGITUDE_NUM = 5;

	/**
	 * <p>
	 * default longitude length
	 * </p>
	 * <p>
	 * 経線の長さのデフォルト値
	 * </p>
	 * <p>
	 * 默认经线长度
	 * </p>
	 */
	public static final int DEFAULT_LONGITUDE_LENGTH = 80;

	/**
	 * <p>
	 * default color for longitude lines
	 * </p>
	 * <p>
	 * 経線の色のデフォルト値
	 * </p>
	 * <p>
	 * 默认经线颜色
	 * </p>
	 */
	public static final int DEFAULT_LONGITUDE_COLOR = Color.BLACK;

	/**
	 * <p>
	 * default should display latitude lines
	 * </p>
	 * <p>
	 * 緯線を表示する
	 * </p>
	 * <p>
	 * 默认是否显示纬线
	 * </p>
	 */
	public static final boolean DEFAULT_DISPLAY_LATITUDE = true;

	/**
	 * <p>
	 * default latitude numbers
	 * </p>
	 * <p>
	 * 緯線の数量のデフォルト値
	 * </p>
	 * <p>
	 * 默认纬线数量
	 * </p>
	 */
	public static final int DEFAULT_LATITUDE_NUM = 5;

	/**
	 * <p>
	 * default color for latitude lines
	 * </p>
	 * <p>
	 * 緯線の色のデフォルト値
	 * </p>
	 * <p>
	 * 默认纬线颜色
	 * </p>
	 */
	public static final int DEFAULT_LATITUDE_COLOR = Color.BLACK;

	/**
	 * <p>
	 * default position
	 * </p>
	 * <p>
	 * 中心位置のデフォルト値
	 * </p>
	 * <p>
	 * 默认绘图中心位置
	 * </p>
	 */
	public static final Point DEFAULT_POSITION = new Point(0, 0);

	/**
	 * <p>
	 * default color for background
	 * </p>
	 * <p>
	 * 背景の色のデフォルト値
	 * </p>
	 * <p>
	 * 默认蛛网背景色
	 * </p>
	 */
	public static final int DEFAULT_BACKGROUND_COLOR = Color.GRAY;

	/**
	 * <p>
	 * default color for display data
	 * </p>
	 * <p>
	 * データの色のデフォルト値
	 * </p>
	 * <p>
	 * 默认数据的显示颜色
	 * </p>
	 */
	public static final int[] COLORS = { Color.RED, Color.BLUE, Color.YELLOW };

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
	private List<List<TitleValueEntity>> data;

	/**
	 * <p>
	 * title
	 * </p>
	 * <p>
	 * タイトル
	 * </p>
	 * <p>
	 * 图表标题
	 * </p>
	 */
	private String title = DEFAULT_TITLE;

	/**
	 * <p>
	 * position
	 * </p>
	 * <p>
	 * 中心位置
	 * </p>
	 * <p>
	 * 绘图中心位置
	 * </p>
	 */
	private Point position = DEFAULT_POSITION;

	/**
	 * <p>
	 * should display longitude lines
	 * </p>
	 * <p>
	 * 経線を表示する
	 * </p>
	 * <p>
	 * 是否显示经线
	 * </p>
	 */
	private boolean displayLongitude = DEFAULT_DISPLAY_LONGITUDE;

	/**
	 * <p>
	 * longitude numbers
	 * </p>
	 * <p>
	 * 経線の数量
	 * </p>
	 * <p>
	 * 经线数量
	 * </p>
	 */
	private int longitudeNum = DEFAULT_LONGITUDE_NUM;

	/**
	 * <p>
	 * color for longitude lines
	 * </p>
	 * <p>
	 * 経線の色
	 * </p>
	 * <p>
	 * 经线颜色
	 * </p>
	 */
	private int longitudeColor = DEFAULT_LONGITUDE_COLOR;

	/**
	 * <p>
	 * longitude length
	 * </p>
	 * <p>
	 * 経線の長
	 * </p>
	 * <p>
	 * 经线长度
	 * </p>
	 */
	private int longitudeLength = DEFAULT_LONGITUDE_LENGTH;

	/**
	 * <p>
	 * should display latitude lines?
	 * </p>
	 * <p>
	 * 緯線を表示する?
	 * </p>
	 * <p>
	 * 是否显示纬线
	 * </p>
	 */
	private boolean displayLatitude = DEFAULT_DISPLAY_LATITUDE;

	/**
	 * <p>
	 * latitude numbers
	 * </p>
	 * <p>
	 * 緯線の数量
	 * </p>
	 * <p>
	 * 纬线数量
	 * </p>
	 */
	private int latitudeNum = DEFAULT_LATITUDE_NUM;

	/**
	 * <p>
	 * color for latitude lines
	 * </p>
	 * <p>
	 * 緯線の色
	 * </p>
	 * <p>
	 * 纬线颜色
	 * </p>
	 */
	private int latitudeColor = DEFAULT_LATITUDE_COLOR;

	/**
	 * <p>
	 * color for background
	 * </p>
	 * <p>
	 * 背景の色
	 * </p>
	 * <p>
	 * 蛛网背景色
	 * </p>
	 */
	private int backgroundColor = DEFAULT_BACKGROUND_COLOR;

	/*
	 * (non-Javadoc)
	 * 
	 * @param context
	 * 
	 * @see cn.limc.androidcharts.view.BaseChart#BaseChart(Context)
	 */
	public SpiderWebChart(Context context) {
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
	 * @see cn.limc.androidcharts.view.BaseChart#BaseChart(Context,AttributeSet, int)
	 */
	public SpiderWebChart(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @param context
	 * 
	 * @param attrs
	 * 
	 * @see cn.limc.androidcharts.view.BaseChart#BaseChart(Context,
	 * AttributeSet)
	 */
	public SpiderWebChart(Context context, AttributeSet attrs) {
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

		// get safe rect
		int rect = super.getHeight();

		// calculate longitude length
		longitudeLength = (int) ((rect / 2f) * 0.8);

		// calculate position
		position = new Point((int) (super.getWidth() / 2f),
				(int) (super.getHeight() / 2f + 0.2 * longitudeLength));

		// draw this chart
		drawSpiderWeb(canvas);

		// draw data on chart
		drawData(canvas);
	}

	/**
	 * <p>
	 * calculate the points to draw the latitude lines
	 * </p>
	 * <p>
	 * 緯度を使って緯線のポイントを計算する
	 * </p>
	 * <p>
	 * 根据纬度参数的数据计算需要在图上绘制的纬线的点
	 * </p>
	 * 
	 * @param pos
	 *            <p>
	 *            latitude degree
	 *            </p>
	 *            <p>
	 *            緯度
	 *            </p>
	 *            <p>
	 *            纬度
	 *            </p>
	 * 
	 * @return　List<PointF>
	 *         <p>
	 *         result points
	 *         </p>
	 *         <p>
	 *         計算出たポイント
	 *         </p>
	 *         <p>
	 *         计算结果
	 *         </p>
	 */
	protected List<PointF> getWebAxisPoints(float pos) {
		List<PointF> points = new ArrayList<PointF>();
		for (int i = 0; i < longitudeNum; i++) {
			PointF pt = new PointF();
			float offsetX = (float) (position.x - longitudeLength * pos
					* Math.sin(i * 2 * Math.PI / longitudeNum));
			float offsetY = (float) (position.y - longitudeLength * pos
					* Math.cos(i * 2 * Math.PI / longitudeNum));
			pt.set(offsetX, offsetY);

			points.add(pt);
		}
		return points;
	}

	/**
	 * <p>
	 * calculate the points to draw the data
	 * </p>
	 * <p>
	 * データを使ってポイントを計算する
	 * </p>
	 * <p>
	 * 根据参数的数据计算需要在图上绘制的点
	 * </p>
	 * 
	 * @param data
	 *            <p>
	 *            data for calculation
	 *            </p>
	 *            <p>
	 *            計算用データ
	 *            </p>
	 *            <p>
	 *            计算用的数据
	 *            </p>
	 * 
	 * @return　List<PointF>
	 *         <p>
	 *         result points
	 *         </p>
	 *         <p>
	 *         計算出たポイント
	 *         </p>
	 *         <p>
	 *         计算结果
	 *         </p>
	 */
	protected List<PointF> getDataPoints(List<TitleValueEntity> data) {
		List<PointF> points = new ArrayList<PointF>();
		for (int i = 0; i < longitudeNum; i++) {
			PointF pt = new PointF();
			float offsetX = (float) (position.x - data.get(i).getValue() / 10f
					* longitudeLength
					* Math.sin(i * 2 * Math.PI / longitudeNum));
			float offsetY = (float) (position.y - data.get(i).getValue() / 10f
					* longitudeLength
					* Math.cos(i * 2 * Math.PI / longitudeNum));
			pt.set(offsetX, offsetY);

			points.add(pt);
		}
		return points;
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
	protected void drawSpiderWeb(Canvas canvas) {
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

		Path mPath = new Path();
		List<PointF> pointList = getWebAxisPoints(1);

		// draw border
		if (null != data) {
			for (int i = 0; i < pointList.size(); i++) {
				PointF pt = pointList.get(i);
				if (i == 0) {
					mPath.moveTo(pt.x, pt.y);
				} else {
					mPath.lineTo(pt.x, pt.y);
				}

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
		mPath.close();
		canvas.drawPath(mPath, mPaintWebFill);
		canvas.drawPath(mPath, mPaintWebBorder);

		// draw spider web
		for (int j = 1; j < latitudeNum; j++) {

			Path mPathInner = new Path();
			List<PointF> pointListInner = getWebAxisPoints(j * 1f / latitudeNum);

			for (int i = 0; i < pointListInner.size(); i++) {
				PointF pt = pointListInner.get(i);
				if (i == 0) {
					mPathInner.moveTo(pt.x, pt.y);
				} else {
					mPathInner.lineTo(pt.x, pt.y);
				}
			}
			mPathInner.close();
			canvas.drawPath(mPathInner, mPaintWebInnerBorder);
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

	/**
	 * @return the data
	 */
	public List<List<TitleValueEntity>> getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(List<List<TitleValueEntity>> data) {
		this.data = data;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the position
	 */
	public Point getPosition() {
		return position;
	}

	/**
	 * @param position
	 *            the position to set
	 */
	public void setPosition(Point position) {
		this.position = position;
	}

	/**
	 * @return the displayLongitude
	 */
	public boolean isDisplayLongitude() {
		return displayLongitude;
	}

	/**
	 * @param displayLongitude
	 *            the displayLongitude to set
	 */
	public void setDisplayLongitude(boolean displayLongitude) {
		this.displayLongitude = displayLongitude;
	}

	/**
	 * @return the longitudeNum
	 */
	public int getLongitudeNum() {
		return longitudeNum;
	}

	/**
	 * @param longitudeNum
	 *            the longitudeNum to set
	 */
	public void setLongitudeNum(int longitudeNum) {
		this.longitudeNum = longitudeNum;
	}

	/**
	 * @return the longitudeColor
	 */
	public int getLongitudeColor() {
		return longitudeColor;
	}

	/**
	 * @param longitudeColor
	 *            the longitudeColor to set
	 */
	public void setLongitudeColor(int longitudeColor) {
		this.longitudeColor = longitudeColor;
	}

	/**
	 * @return the longitudeLength
	 */
	public int getLongitudeLength() {
		return longitudeLength;
	}

	/**
	 * @param longitudeLength
	 *            the longitudeLength to set
	 */
	public void setLongitudeLength(int longitudeLength) {
		this.longitudeLength = longitudeLength;
	}

	/**
	 * @return the displayLatitude
	 */
	public boolean isDisplayLatitude() {
		return displayLatitude;
	}

	/**
	 * @param displayLatitude
	 *            the displayLatitude to set
	 */
	public void setDisplayLatitude(boolean displayLatitude) {
		this.displayLatitude = displayLatitude;
	}

	/**
	 * @return the latitudeNum
	 */
	public int getLatitudeNum() {
		return latitudeNum;
	}

	/**
	 * @param latitudeNum
	 *            the latitudeNum to set
	 */
	public void setLatitudeNum(int latitudeNum) {
		this.latitudeNum = latitudeNum;
	}

	/**
	 * @return the latitudeColor
	 */
	public int getLatitudeColor() {
		return latitudeColor;
	}

	/**
	 * @param latitudeColor
	 *            the latitudeColor to set
	 */
	public void setLatitudeColor(int latitudeColor) {
		this.latitudeColor = latitudeColor;
	}

	/**
	 * @return the backgroundColor
	 */
	public int getBackgroundColor() {
		return backgroundColor;
	}

	/**
	 * @param backgroundColor the backgroundColor to set
	 */
	public void setBackgroundColor(int backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	
}
