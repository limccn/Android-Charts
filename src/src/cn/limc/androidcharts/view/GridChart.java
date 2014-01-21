/*
 * GridChart.java
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

package cn.limc.androidcharts.view;

import java.util.ArrayList;
import java.util.List;

import cn.limc.androidcharts.event.ITouchEventNotify;
import cn.limc.androidcharts.event.ITouchEventResponse;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 
 * <p>
 * GridChart is base type of all the charts that use a grid to display like
 * line-chart stick-chart etc. GridChart implemented a simple grid with basic
 * functions what can be used in it's inherited charts.
 * </p>
 * <p>
 * GridChartは全部グリドチャートのベスクラスです、一部処理は共通化け実現した。
 * </p>
 * <p>
 * GridChart是所有网格图表的基础类对象，它实现了基本的网格图表功能，这些功能将被它的继承类使用
 * </p>
 * 
 * @author limc
 * @version v1.0 2011/05/30 14:19:50
 * 
 */
public class GridChart extends BaseChart implements ITouchEventNotify,
		ITouchEventResponse {

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
	 * default color of X axis
	 * </p>
	 * <p>
	 * X軸の色のデフォルト値
	 * </p>
	 * <p>
	 * 默认坐标轴X的显示颜色
	 * </p>
	 */
	public static final int DEFAULT_AXIS_X_COLOR = Color.RED;

	/**
	 * <p>
	 * default color of Y axis
	 * </p>
	 * <p>
	 * Y軸の色のデフォルト値
	 * </p>
	 * <p>
	 * 默认坐标轴Y的显示颜色
	 * </p>
	 */
	public static final int DEFAULT_AXIS_Y_COLOR = Color.RED;

	/**
	 * <p>
	 * default color of grid‘s longitude line
	 * </p>
	 * <p>
	 * 経線の色のデフォルト値
	 * </p>
	 * <p>
	 * 默认网格经线的显示颜色
	 * </p>
	 */
	public static final int DEFAULT_LONGITUDE_COLOR = Color.RED;

	/**
	 * <p>
	 * default color of grid‘s latitude line
	 * </p>
	 * <p>
	 * 緯線の色のデフォルト値
	 * </p>
	 * <p>
	 * 默认网格纬线的显示颜色
	 * </p>
	 */
	public static final int DEFAULT_LAITUDE_COLOR = Color.RED;

	/**
	 * <p>
	 * default margin of the axis to the left border
	 * </p>
	 * <p>
	 * 轴線より左枠線の距離のデフォルト値
	 * </p>
	 * <p>
	 * 默认轴线左边距
	 * </p>
	 */
	public static final float DEFAULT_AXIS_MARGIN_LEFT = 42f;

	/**
	 * <p>
	 * default margin of the axis to the bottom border
	 * </p>
	 * <p>
	 * 轴線より下枠線の距離のデフォルト値
	 * </p>
	 * <p>
	 * 默认轴线下边距
	 * </p>
	 */
	public static final float DEFAULT_AXIS_MARGIN_BOTTOM = 16f;

	/**
	 * <p>
	 * default margin of the axis to the top border
	 * </p>
	 * <p>
	 * 轴線より上枠線の距離のデフォルト値
	 * </p>
	 * <p>
	 * 默认轴线上边距
	 * </p>
	 */
	public static final float DEFAULT_AXIS_MARGIN_TOP = 5f;

	/**
	 * <p>
	 * default margin of the axis to the right border
	 * </p>
	 * <p>
	 * 轴線より右枠線の距離のデフォルト値
	 * </p>
	 * <p>
	 * 轴线右边距
	 * </p>
	 */
	public static final float DEFAULT_AXIS_MARGIN_RIGHT = 5f;

	/**
	 * <p>
	 * default numbers of grid‘s latitude line
	 * </p>
	 * <p>
	 * 緯線の数量のデフォルト値
	 * </p>
	 * <p>
	 * 网格纬线的数量
	 * </p>
	 */
	public static final int DEFAULT_LATITUDE_NUM = 4;

	/**
	 * <p>
	 * default numbers of grid‘s longitude line
	 * </p>
	 * <p>
	 * 経線の数量のデフォルト値
	 * </p>
	 * <p>
	 * 网格经线的数量
	 * </p>
	 */
	public static final int DEFAULT_LONGITUDE_NUM = 3;

	/**
	 * <p>
	 * Should display longitude line?
	 * </p>
	 * <p>
	 * 経線を表示するか?
	 * </p>
	 * <p>
	 * 默认经线是否显示
	 * </p>
	 */
	public static final boolean DEFAULT_DISPLAY_LONGITUDE = Boolean.TRUE;

	/**
	 * <p>
	 * Should display longitude as dashed line?
	 * </p>
	 * <p>
	 * 経線を点線にするか?
	 * </p>
	 * <p>
	 * 默认经线是否显示为虚线
	 * </p>
	 */
	public static final boolean DEFAULT_DASH_LONGITUDE = Boolean.TRUE;

	/**
	 * <p>
	 * Should display longitude line?
	 * </p>
	 * <p>
	 * 緯線を表示するか?
	 * </p>
	 * <p>
	 * 纬线是否显示
	 * </p>
	 */
	public static final boolean DEFAULT_DISPLAY_LATITUDE = Boolean.TRUE;

	/**
	 * <p>
	 * Should display latitude as dashed line?
	 * </p>
	 * <p>
	 * 緯線を点線にするか?
	 * </p>
	 * <p>
	 * 纬线是否显示为虚线
	 * </p>
	 */
	public static final boolean DEFAULT_DASH_LATITUDE = Boolean.TRUE;

	/**
	 * <p>
	 * Should display the degrees in X axis?
	 * </p>
	 * <p>
	 * X軸のタイトルを表示するか?
	 * </p>
	 * <p>
	 * X轴上的标题是否显示
	 * </p>
	 */
	public static final boolean DEFAULT_DISPLAY_AXIS_X_TITLE = Boolean.TRUE;

	/**
	 * <p>
	 * Should display the degrees in Y axis?
	 * </p>
	 * <p>
	 * Y軸のタイトルを表示するか?
	 * </p>
	 * <p>
	 * 默认Y轴上的标题是否显示
	 * </p>
	 */
	public static final boolean DEFAULT_DISPLAY_AXIS_Y_TITLE = Boolean.TRUE;

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
	public static final int DEFAULT_BORDER_COLOR = Color.RED;

	/**
	 * <p>
	 * default color of text for the longitude　degrees display
	 * </p>
	 * <p>
	 * 経度のタイトルの色のデフォルト値
	 * </p>
	 * <p>
	 * 经线刻度字体颜色
	 * </p>
	 */
	public static final int DEFAULT_LONGITUDE_FONT_COLOR = Color.WHITE;

	/**
	 * <p>
	 * default font size of text for the longitude　degrees display
	 * </p>
	 * <p>
	 * 経度のタイトルのフォントサイズのデフォルト値
	 * </p>
	 * <p>
	 * 经线刻度字体大小
	 * </p>
	 */
	public static final int DEFAULT_LONGITUDE_FONT_SIZE = 12;

	/**
	 * <p>
	 * default color of text for the latitude　degrees display
	 * </p>
	 * <p>
	 * 緯度のタイトルの色のデフォルト値
	 * </p>
	 * <p>
	 * 纬线刻度字体颜色
	 * </p>
	 */
	public static final int DEFAULT_LATITUDE_FONT_COLOR = Color.RED;

	/**
	 * <p>
	 * default font size of text for the latitude　degrees display
	 * </p>
	 * <p>
	 * 緯度のタイトルのフォントサイズのデフォルト値
	 * </p>
	 * <p>
	 * 默认纬线刻度字体大小
	 * </p>
	 */
	public static final int DEFAULT_LATITUDE_FONT_SIZE = 12;

	/**
	 * <p>
	 * default titles' max length for display of Y axis
	 * </p>
	 * <p>
	 * Y軸の表示用タイトルの最大文字長さのデフォルト値
	 * </p>
	 * <p>
	 * 默认Y轴标题最大文字长度
	 * </p>
	 */
	public static final int DEFAULT_AXIS_Y_MAX_TITLE_LENGTH = 5;

	/**
	 * <p>
	 * default dashed line type
	 * </p>
	 * <p>
	 * 点線タイプのデフォルト値
	 * </p>
	 * <p>
	 * 默认虚线效果
	 * </p>
	 */
	public static final PathEffect DEFAULT_DASH_EFFECT = new DashPathEffect(
			new float[] { 3, 3, 3, 3 }, 1);

	/**
	 * <p>
	 * Should display the Y cross line if grid is touched?
	 * </p>
	 * <p>
	 * タッチしたポイントがある場合、十字線の垂直線を表示するか?
	 * </p>
	 * <p>
	 * 默认在控件被点击时，显示十字竖线线
	 * </p>
	 */
	public static final boolean DEFAULT_DISPLAY_CROSS_X_ON_TOUCH = true;

	/**
	 * <p>
	 * Should display the Y cross line if grid is touched?
	 * </p>
	 * <p>
	 * タッチしたポイントがある場合、十字線の水平線を表示するか?
	 * </p>
	 * <p>
	 * 默认在控件被点击时，显示十字横线线
	 * </p>
	 */
	public static final boolean DEFAULT_DISPLAY_CROSS_Y_ON_TOUCH = true;

	/**
	 * <p>
	 * background color
	 * </p>
	 * <p>
	 * 背景の色
	 * </p>
	 * <p>
	 * 背景色
	 * </p>
	 */
	private int backgroundColor = DEFAULT_BACKGROUND_COLOR;

	/**
	 * <p>
	 * Color of X axis
	 * </p>
	 * <p>
	 * X軸の色
	 * </p>
	 * <p>
	 * 坐标轴X的显示颜色
	 * </p>
	 */
	private int axisXColor = DEFAULT_AXIS_X_COLOR;

	/**
	 * <p>
	 * Color of Y axis
	 * </p>
	 * <p>
	 * Y軸の色
	 * </p>
	 * <p>
	 * 坐标轴Y的显示颜色
	 * </p>
	 */
	private int axisYColor = DEFAULT_AXIS_Y_COLOR;

	/**
	 * <p>
	 * Color of grid‘s longitude line
	 * </p>
	 * <p>
	 * 経線の色
	 * </p>
	 * <p>
	 * 网格经线的显示颜色
	 * </p>
	 */
	private int longitudeColor = DEFAULT_LONGITUDE_COLOR;

	/**
	 * <p>
	 * Color of grid‘s latitude line
	 * </p>
	 * <p>
	 * 緯線の色
	 * </p>
	 * <p>
	 * 网格纬线的显示颜色
	 * </p>
	 */
	private int latitudeColor = DEFAULT_LAITUDE_COLOR;

	/**
	 * <p>
	 * Margin of the axis to the left border
	 * </p>
	 * <p>
	 * 轴線より左枠線の距離
	 * </p>
	 * <p>
	 * 轴线左边距
	 * </p>
	 */
	private float axisMarginLeft = DEFAULT_AXIS_MARGIN_LEFT;

	/**
	 * <p>
	 * Margin of the axis to the bottom border
	 * </p>
	 * <p>
	 * 轴線より下枠線の距離
	 * </p>
	 * <p>
	 * 轴线下边距
	 * </p>
	 */
	private float axisMarginBottom = DEFAULT_AXIS_MARGIN_BOTTOM;

	/**
	 * <p>
	 * Margin of the axis to the top border
	 * </p>
	 * <p>
	 * 轴線より上枠線の距離
	 * </p>
	 * <p>
	 * 轴线上边距
	 * </p>
	 */
	private float axisMarginTop = DEFAULT_AXIS_MARGIN_TOP;

	/**
	 * <p>
	 * Margin of the axis to the right border
	 * </p>
	 * <p>
	 * 轴線より右枠線の距離
	 * </p>
	 * <p>
	 * 轴线右边距
	 * </p>
	 */
	private float axisMarginRight = DEFAULT_AXIS_MARGIN_RIGHT;

	/**
	 * <p>
	 * Should display the degrees in X axis?
	 * </p>
	 * <p>
	 * X軸のタイトルを表示するか?
	 * </p>
	 * <p>
	 * X轴上的标题是否显示
	 * </p>
	 */
	private boolean displayAxisXTitle = DEFAULT_DISPLAY_AXIS_X_TITLE;

	/**
	 * <p>
	 * Should display the degrees in Y axis?
	 * </p>
	 * <p>
	 * Y軸のタイトルを表示するか?
	 * </p>
	 * <p>
	 * Y轴上的标题是否显示
	 * </p>
	 */
	private boolean displayAxisYTitle = DEFAULT_DISPLAY_AXIS_Y_TITLE;

	/**
	 * <p>
	 * Numbers of grid‘s latitude line
	 * </p>
	 * <p>
	 * 緯線の数量
	 * </p>
	 * <p>
	 * 网格纬线的数量
	 * </p>
	 */
	private int latitudeNum = DEFAULT_LATITUDE_NUM;

	/**
	 * <p>
	 * Numbers of grid‘s longitude line
	 * </p>
	 * <p>
	 * 経線の数量
	 * </p>
	 * <p>
	 * 网格经线的数量
	 * </p>
	 */
	private int longitudeNum = DEFAULT_LONGITUDE_NUM;

	/**
	 * <p>
	 * Should display longitude line?
	 * </p>
	 * <p>
	 * 経線を表示するか?
	 * </p>
	 * <p>
	 * 经线是否显示
	 * </p>
	 */
	private boolean displayLongitude = DEFAULT_DISPLAY_LONGITUDE;

	/**
	 * <p>
	 * Should display longitude as dashed line?
	 * </p>
	 * <p>
	 * 経線を点線にするか?
	 * </p>
	 * <p>
	 * 经线是否显示为虚线
	 * </p>
	 */
	private boolean dashLongitude = DEFAULT_DASH_LONGITUDE;

	/**
	 * <p>
	 * Should display longitude line?
	 * </p>
	 * <p>
	 * 緯線を表示するか?
	 * </p>
	 * <p>
	 * 纬线是否显示
	 * </p>
	 */
	private boolean displayLatitude = DEFAULT_DISPLAY_LATITUDE;

	/**
	 * <p>
	 * Should display latitude as dashed line?
	 * </p>
	 * <p>
	 * 緯線を点線にするか?
	 * </p>
	 * <p>
	 * 纬线是否显示为虚线
	 * </p>
	 */
	private boolean dashLatitude = DEFAULT_DASH_LATITUDE;

	/**
	 * <p>
	 * dashed line type
	 * </p>
	 * <p>
	 * 点線タイプ?
	 * </p>
	 * <p>
	 * 虚线效果
	 * </p>
	 */
	private PathEffect dashEffect = DEFAULT_DASH_EFFECT;

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
	private boolean displayBorder = DEFAULT_DISPLAY_BORDER;

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
	private int borderColor = DEFAULT_BORDER_COLOR;

	/**
	 * <p>
	 * Color of text for the longitude　degrees display
	 * </p>
	 * <p>
	 * 経度のタイトルの色
	 * </p>
	 * <p>
	 * 经线刻度字体颜色
	 * </p>
	 */
	private int longitudeFontColor = DEFAULT_LONGITUDE_FONT_COLOR;

	/**
	 * <p>
	 * Font size of text for the longitude　degrees display
	 * </p>
	 * <p>
	 * 経度のタイトルのフォントサイズ
	 * </p>
	 * <p>
	 * 经线刻度字体大小
	 * </p>
	 */
	private int longitudeFontSize = DEFAULT_LONGITUDE_FONT_SIZE;

	/**
	 * <p>
	 * Color of text for the latitude　degrees display
	 * </p>
	 * <p>
	 * 緯度のタイトルの色
	 * </p>
	 * <p>
	 * 纬线刻度字体颜色
	 * </p>
	 */
	private int latitudeFontColor = DEFAULT_LATITUDE_FONT_COLOR;

	/**
	 * <p>
	 * Font size of text for the latitude　degrees display
	 * </p>
	 * <p>
	 * 緯度のタイトルのフォントサイズ
	 * </p>
	 * <p>
	 * 纬线刻度字体大小
	 * </p>
	 */
	private int latitudeFontSize = DEFAULT_LATITUDE_FONT_SIZE;

	/**
	 * <p>
	 * Titles Array for display of X axis
	 * </p>
	 * <p>
	 * X軸の表示用タイトル配列
	 * </p>
	 * <p>
	 * X轴标题数组
	 * </p>
	 */
	private List<String> axisXTitles;

	/**
	 * <p>
	 * Titles for display of Y axis
	 * </p>
	 * <p>
	 * Y軸の表示用タイトル配列
	 * </p>
	 * <p>
	 * Y轴标题数组
	 * </p>
	 */
	private List<String> axisYTitles;

	/**
	 * <p>
	 * Titles' max length for display of Y axis
	 * </p>
	 * <p>
	 * Y軸の表示用タイトルの最大文字長さ
	 * </p>
	 * <p>
	 * Y轴标题最大文字长度
	 * </p>
	 */
	private int axisYMaxTitleLength = DEFAULT_AXIS_Y_MAX_TITLE_LENGTH;

	/**
	 * <p>
	 * Should display the Y cross line if grid is touched?
	 * </p>
	 * <p>
	 * タッチしたポイントがある場合、十字線の垂直線を表示するか?
	 * </p>
	 * <p>
	 * 在控件被点击时，显示十字竖线线
	 * </p>
	 */
	private boolean displayCrossXOnTouch = DEFAULT_DISPLAY_CROSS_X_ON_TOUCH;

	/**
	 * <p>
	 * Should display the Y cross line if grid is touched?
	 * </p>
	 * <p>
	 * タッチしたポイントがある場合、十字線の水平線を表示するか?
	 * </p>
	 * <p>
	 * 在控件被点击时，显示十字横线线
	 * </p>
	 */
	private boolean displayCrossYOnTouch = DEFAULT_DISPLAY_CROSS_Y_ON_TOUCH;

	/**
	 * <p>
	 * Touched point inside of grid
	 * </p>
	 * <p>
	 * タッチしたポイント
	 * </p>
	 * <p>
	 * 单点触控的选中点
	 * </p>
	 */
	private PointF touchPoint;

	/**
	 * <p>
	 * Touched point’s X value inside of grid
	 * </p>
	 * <p>
	 * タッチしたポイントのX
	 * </p>
	 * <p>
	 * 单点触控的选中点的X
	 * </p>
	 */
	private float clickPostX = 0f;

	/**
	 * <p>
	 * Touched point’s Y value inside of grid
	 * </p>
	 * <p>
	 * タッチしたポイントのY
	 * </p>
	 * <p>
	 * 单点触控的选中点的Y
	 * </p>
	 */
	private float clickPostY = 0f;

	/**
	 * <p>
	 * Event will notify objects' list
	 * </p>
	 * <p>
	 * イベント通知対象リスト
	 * </p>
	 * <p>
	 * 事件通知对象列表
	 * </p>
	 */
	private List<ITouchEventResponse> notifyList;

	/*
	 * (non-Javadoc)
	 * 
	 * @param context
	 * 
	 * @see cn.limc.androidcharts.view.BaseChart#BaseChart(Context)
	 */
	public GridChart(Context context) {
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
	 * @see cn.limc.androidcharts.view.BaseChart#BaseChart(Context,
	 * AttributeSet, int)
	 */
	public GridChart(Context context, AttributeSet attrs, int defStyle) {
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
	public GridChart(Context context, AttributeSet attrs) {
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

		super.setBackgroundColor(backgroundColor);

		drawXAxis(canvas);
		drawYAxis(canvas);

		if (this.displayBorder) {
			drawBorder(canvas);
		}

		if (displayLongitude || displayAxisXTitle) {
			drawAxisGridX(canvas);
		}
		if (displayLatitude || displayAxisYTitle) {
			drawAxisGridY(canvas);
		}

		if (displayCrossXOnTouch || displayCrossYOnTouch) {
			drawWithFingerClick(canvas);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * <p>Called when chart is touched<p> <p>チャートをタッチしたら、メソッドを呼ぶ<p>
	 * <p>图表点击时调用<p>
	 * 
	 * @param event
	 * 
	 * @see android.view.View#onTouchEvent(MotionEvent)
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		if (event.getY() > 0
				&& event.getY() < super.getBottom() - getAxisMarginBottom()
				&& event.getX() > super.getLeft() + getAxisMarginLeft()
				&& event.getX() < super.getRight()) {

			// touched points, if touch point is only one
			if (event.getPointerCount() == 1) {
				// 获取点击坐�?
				clickPostX = event.getX();
				clickPostY = event.getY();

				PointF point = new PointF(clickPostX, clickPostY);
				touchPoint = point;

				// redraw
				super.invalidate();

				// do notify
				notifyEventAll(this);

			} else if (event.getPointerCount() == 2) {
			}
		}
		return super.onTouchEvent(event);
	}

	/**
	 * <p>
	 * draw some text with border
	 * </p>
	 * <p>
	 * 文字を書く、枠あり
	 * </p>
	 * <p>
	 * 绘制一段文本，并增加外框
	 * </p>
	 * 
	 * @param ptStart
	 *            <p>
	 *            start point
	 *            </p>
	 *            <p>
	 *            開始ポイント
	 *            </p>
	 *            <p>
	 *            开始点
	 *            </p>
	 * 
	 * @param ptEnd
	 *            <p>
	 *            end point
	 *            </p>
	 *            <p>
	 *            結束ポイント
	 *            </p>
	 *            <p>
	 *            结束点
	 *            </p>
	 * 
	 * @param content
	 *            <p>
	 *            text content
	 *            </p>
	 *            <p>
	 *            文字内容
	 *            </p>
	 *            <p>
	 *            文字内容
	 *            </p>
	 * 
	 * @param fontSize
	 *            <p>
	 *            font size
	 *            </p>
	 *            <p>
	 *            文字フォントサイズ
	 *            </p>
	 *            <p>
	 *            字体大小
	 *            </p>
	 * 
	 * @param canvas
	 */
	private void drawAlphaTextBox(PointF ptStart, PointF ptEnd, String content,
			int fontSize, Canvas canvas) {

		Paint mPaintBox = new Paint();
		mPaintBox.setColor(Color.BLACK);
		mPaintBox.setAlpha(80);

		Paint mPaintBoxLine = new Paint();
		mPaintBoxLine.setColor(Color.CYAN);
		mPaintBoxLine.setAntiAlias(true);

		// draw a rectangle
		canvas.drawRoundRect(new RectF(ptStart.x, ptStart.y, ptEnd.x, ptEnd.y),
				20.0f, 20.0f, mPaintBox);

		// draw a rectangle' border
		canvas.drawLine(ptStart.x, ptStart.y, ptStart.x, ptEnd.y, mPaintBoxLine);
		canvas.drawLine(ptStart.x, ptEnd.y, ptEnd.x, ptEnd.y, mPaintBoxLine);
		canvas.drawLine(ptEnd.x, ptEnd.y, ptEnd.x, ptStart.y, mPaintBoxLine);
		canvas.drawLine(ptEnd.x, ptStart.y, ptStart.x, ptStart.y, mPaintBoxLine);

		// draw text
		canvas.drawText(content, ptStart.x, ptEnd.y, mPaintBoxLine);
	}

	/**
	 * <p>
	 * calculate degree title on X axis
	 * </p>
	 * <p>
	 * X軸の目盛を計算する
	 * </p>
	 * <p>
	 * 计算X轴上显示的坐标值
	 * </p>
	 * 
	 * @param value
	 *            <p>
	 *            value for calculate
	 *            </p>
	 *            <p>
	 *            計算有用データ
	 *            </p>
	 *            <p>
	 *            计算用数据
	 *            </p>
	 * 
	 * @return String
	 *         <p>
	 *         degree
	 *         </p>
	 *         <p>
	 *         目盛
	 *         </p>
	 *         <p>
	 *         坐标值
	 *         </p>
	 */
	public String getAxisXGraduate(Object value) {

		float length = super.getWidth() - axisMarginLeft - 2 * axisMarginRight;
		float valueLength = ((Float) value).floatValue() - axisMarginLeft
				- axisMarginRight;

		return String.valueOf(valueLength / length);
	}

	/**
	 * <p>
	 * calculate degree title on Y axis
	 * </p>
	 * <p>
	 * Y軸の目盛を計算する
	 * </p>
	 * <p>
	 * 计算Y轴上显示的坐标值
	 * </p>
	 * 
	 * @param value
	 *            <p>
	 *            value for calculate
	 *            </p>
	 *            <p>
	 *            計算有用データ
	 *            </p>
	 *            <p>
	 *            计算用数据
	 *            </p>
	 * 
	 * @return String
	 *         <p>
	 *         degree
	 *         </p>
	 *         <p>
	 *         目盛
	 *         </p>
	 *         <p>
	 *         坐标值
	 *         </p>
	 */
	public String getAxisYGraduate(Object value) {

		float length = super.getHeight() - axisMarginBottom - 2 * axisMarginTop;
		float valueLength = length
				- (((Float) value).floatValue() - axisMarginTop);

		return String.valueOf(valueLength / length);
	}

	/**
	 * <p>
	 * draw cross line ,called when graph is touched
	 * </p>
	 * <p>
	 * 十字線を書く、グラプをタッチたら、メソードを呼び
	 * </p>
	 * <p>
	 * 在图表被点击后绘制十字线
	 * </p>
	 * 
	 * @param canvas
	 */
	protected void drawWithFingerClick(Canvas canvas) {
		Paint mPaint = new Paint();
		mPaint.setColor(Color.CYAN);

		float lineHLength = getWidth() - 2f;
		float lineVLength = getHeight() - 2f;

		// draw text
		if (isDisplayAxisXTitle()) {
			lineVLength = lineVLength - axisMarginBottom;

			if (clickPostX > 0 && clickPostY > 0) {
				if (displayCrossXOnTouch) {
					// TODO calculate points to draw
					PointF BoxVS = new PointF(clickPostX - longitudeFontSize
							* 5f / 2f, lineVLength + 2f);
					PointF BoxVE = new PointF(clickPostX + longitudeFontSize
							* 5f / 2f, lineVLength + axisMarginBottom - 1f);

					// draw text
					drawAlphaTextBox(BoxVS, BoxVE,
							getAxisXGraduate(clickPostX), longitudeFontSize,
							canvas);
				}
			}
		}

		if (isDisplayAxisYTitle()) {
			lineHLength = lineHLength - getAxisMarginLeft();

			if (clickPostX > 0 && clickPostY > 0) {
				if (displayCrossYOnTouch) {
					// TODO calculate points to draw
					PointF BoxHS = new PointF(1f, clickPostY - latitudeFontSize
							/ 2f);
					PointF BoxHE = new PointF(axisMarginLeft, clickPostY
							+ latitudeFontSize / 2f);

					// draw text
					drawAlphaTextBox(BoxHS, BoxHE,
							getAxisYGraduate(clickPostY), latitudeFontSize,
							canvas);
				}
			}
		}

		// draw line
		if (clickPostX > 0 && clickPostY > 0) {
			if (displayCrossXOnTouch) {
				canvas.drawLine(clickPostX, 1f, clickPostX, lineVLength, mPaint);
			}

			if (displayCrossYOnTouch) {
				canvas.drawLine(axisMarginLeft, clickPostY, axisMarginLeft
						+ lineHLength, clickPostY, mPaint);
			}
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
		float width = super.getWidth() - 2;
		float height = super.getHeight() - 2;

		Paint mPaint = new Paint();
		mPaint.setColor(borderColor);

		// draw a rectangle
		canvas.drawLine(1f, 1f, 1f + width, 1f, mPaint);
		canvas.drawLine(1f + width, 1f, 1f + width, 1f + height, mPaint);
		canvas.drawLine(1f + width, 1f + height, 1f, 1f + height, mPaint);
		canvas.drawLine(1f, 1f + height, 1f, 1f, mPaint);
	}

	/**
	 * <p>
	 * draw X Axis
	 * </p>
	 * <p>
	 * X軸を書く
	 * </p>
	 * <p>
	 * 绘制X轴
	 * </p>
	 * 
	 * @param canvas
	 */
	protected void drawXAxis(Canvas canvas) {

		float length = super.getWidth();
		float postY = super.getHeight() - axisMarginBottom - 1;

		Paint mPaint = new Paint();
		mPaint.setColor(axisXColor);

		canvas.drawLine(0f, postY, length, postY, mPaint);

	}

	/**
	 * <p>
	 * draw Y Axis
	 * </p>
	 * <p>
	 * Y軸を書く
	 * </p>
	 * <p>
	 * 绘制Y轴
	 * </p>
	 * 
	 * @param canvas
	 */
	protected void drawYAxis(Canvas canvas) {

		float length = super.getHeight() - axisMarginBottom;
		float postX = axisMarginLeft + 1;

		Paint mPaint = new Paint();
		mPaint.setColor(axisXColor);

		canvas.drawLine(postX, 0f, postX, length, mPaint);
	}

	/**
	 * <p>
	 * draw longitude lines
	 * </p>
	 * <p>
	 * 経線を書く
	 * </p>
	 * <p>
	 * 绘制经线
	 * </p>
	 * 
	 * @param canvas
	 */
	protected void drawAxisGridX(Canvas canvas) {

		if (null != axisXTitles) {
			int counts = axisXTitles.size();
			float length = super.getHeight() - axisMarginBottom;

			Paint mPaintLine = new Paint();
			mPaintLine.setColor(longitudeColor);
			if (dashLongitude) {
				mPaintLine.setPathEffect(dashEffect);
			}

			Paint mPaintFont = new Paint();
			mPaintFont.setColor(longitudeFontColor);
			mPaintFont.setTextSize(longitudeFontSize);
			mPaintFont.setAntiAlias(true);
			if (counts > 1) {
				float postOffset = (super.getWidth() - axisMarginLeft - 2 * axisMarginRight)
						/ (counts - 1);
				float offset = axisMarginLeft + axisMarginRight;
				for (int i = 0; i <= counts; i++) {
					// draw line
					if (displayLongitude) {
						canvas.drawLine(offset + i * postOffset, 0f, offset + i
								* postOffset, length, mPaintLine);
					}
					// draw title
					if (displayAxisXTitle) {
						if (i < counts && i > 0) {
							canvas.drawText(axisXTitles.get(i), offset + i
									* postOffset
									- (axisXTitles.get(i).length())
									* longitudeFontSize / 2f, super.getHeight()
									- axisMarginBottom + longitudeFontSize,
									mPaintFont);
						} else if (0 == i) {
							canvas.drawText(axisXTitles.get(i),
									this.axisMarginLeft + 2f, super.getHeight()
											- axisMarginBottom
											+ longitudeFontSize, mPaintFont);
						}
					}
				}
			}
		}
	}

	/**
	 * <p>
	 * draw latitude lines
	 * </p>
	 * <p>
	 * 緯線を書く
	 * </p>
	 * <p>
	 * 绘制纬线
	 * </p>
	 * 
	 * @param canvas
	 */
	protected void drawAxisGridY(Canvas canvas) {
		if (null != axisYTitles) {
			int counts = axisYTitles.size();
			float length = super.getWidth() - axisMarginLeft;

			Paint mPaintLine = new Paint();
			mPaintLine.setColor(latitudeColor);
			if (dashLatitude) {
				mPaintLine.setPathEffect(dashEffect);
			}

			Paint mPaintFont = new Paint();
			mPaintFont.setColor(latitudeFontColor);
			mPaintFont.setTextSize(latitudeFontSize);
			mPaintFont.setAntiAlias(true);

			if (counts > 1) {
				float postOffset = (super.getHeight() - axisMarginBottom - 2 * axisMarginTop)
						/ (counts - 1);
				float offset = super.getHeight() - axisMarginBottom
						- axisMarginTop;
				for (int i = 0; i <= counts; i++) {
					// draw line
					if (displayLatitude) {
						canvas.drawLine(axisMarginLeft,
								offset - i * postOffset, axisMarginLeft
										+ length, offset - i * postOffset,
								mPaintLine);
					}
					// draw title
					if (displayAxisYTitle) {
						if (i < counts && i > 0) {
							canvas.drawText(axisYTitles.get(i), 0f, offset - i
									* postOffset + latitudeFontSize / 2f,
									mPaintFont);
						} else if (0 == i) {
							canvas.drawText(axisYTitles.get(i), 0f,
									super.getHeight() - this.axisMarginBottom
											- 2f, mPaintFont);
						}
					}
				}
			}
		}
	}

	/**
	 * <p>
	 * Zoom in the graph
	 * </p>
	 * <p>
	 * 拡大表示する。
	 * </p>
	 * <p>
	 * 放大表示
	 * </p>
	 */
	protected void zoomIn() {

	}

	/**
	 * <p>
	 * Zoom out the grid
	 * </p>
	 * <p>
	 * 縮小表示する。
	 * </p>
	 * <p>
	 * 缩小
	 * </p>
	 */
	protected void zoomOut() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @param event
	 * 
	 * @see
	 * cn.limc.androidcharts.event.ITouchEventResponse#notifyEvent(GridChart)
	 */
	public void notifyEvent(GridChart chart) {
		PointF point = chart.getTouchPoint();
		if (null != point) {
			clickPostX = point.x;
			clickPostY = point.y;
		}
		touchPoint = new PointF(clickPostX, clickPostY);
		super.invalidate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @param event
	 * 
	 * @see
	 * cn.limc.androidcharts.event.ITouchEventNotify#addNotify(ITouchEventResponse
	 * )
	 */
	public void addNotify(ITouchEventResponse notify) {
		if (null == notifyList) {
			notifyList = new ArrayList<ITouchEventResponse>();
		}
		notifyList.add(notify);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @param event
	 * 
	 * @see cn.limc.androidcharts.event.ITouchEventNotify#removeNotify(int)
	 */
	public void removeNotify(int i) {
		if (null != notifyList && notifyList.size() > i) {
			notifyList.remove(i);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @param event
	 * 
	 * @see cn.limc.androidcharts.event.ITouchEventNotify#removeNotify()
	 */
	public void removeAllNotify() {
		if (null != notifyList) {
			notifyList.clear();
		}
	}

	public void notifyEventAll(GridChart chart) {
		if (null != notifyList) {
			for (int i = 0; i < notifyList.size(); i++) {
				ITouchEventResponse ichart = notifyList.get(i);
				ichart.notifyEvent(chart);
			}
		}
	}

	/**
	 * @return the axisXColor
	 */
	public int getAxisXColor() {
		return axisXColor;
	}

	/**
	 * @param axisXColor
	 *            the axisXColor to set
	 */
	public void setAxisXColor(int axisXColor) {
		this.axisXColor = axisXColor;
	}

	/**
	 * @return the axisYColor
	 */
	public int getAxisYColor() {
		return axisYColor;
	}

	/**
	 * @param axisYColor
	 *            the axisYColor to set
	 */
	public void setAxisYColor(int axisYColor) {
		this.axisYColor = axisYColor;
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
	 * @return the axisMarginLeft
	 */
	public float getAxisMarginLeft() {
		return axisMarginLeft;
	}

	/**
	 * @param axisMarginLeft
	 *            the axisMarginLeft to set
	 */
	public void setAxisMarginLeft(float axisMarginLeft) {
		this.axisMarginLeft = axisMarginLeft;
	}

	/**
	 * @return the axisMarginBottom
	 */
	public float getAxisMarginBottom() {
		return axisMarginBottom;
	}

	/**
	 * @param axisMarginBottom
	 *            the axisMarginBottom to set
	 */
	public void setAxisMarginBottom(float axisMarginBottom) {
		this.axisMarginBottom = axisMarginBottom;
	}

	/**
	 * @return the axisMarginTop
	 */
	public float getAxisMarginTop() {
		return axisMarginTop;
	}

	/**
	 * @param axisMarginTop
	 *            the axisMarginTop to set
	 */
	public void setAxisMarginTop(float axisMarginTop) {
		this.axisMarginTop = axisMarginTop;
	}

	/**
	 * @return the axisMarginRight
	 */
	public float getAxisMarginRight() {
		return axisMarginRight;
	}

	/**
	 * @param axisMarginRight
	 *            the axisMarginRight to set
	 */
	public void setAxisMarginRight(float axisMarginRight) {
		this.axisMarginRight = axisMarginRight;
	}

	/**
	 * @return the displayAxisXTitle
	 */
	public boolean isDisplayAxisXTitle() {
		return displayAxisXTitle;
	}

	/**
	 * @param displayAxisXTitle
	 *            the displayAxisXTitle to set
	 */
	public void setDisplayAxisXTitle(boolean displayAxisXTitle) {
		this.displayAxisXTitle = displayAxisXTitle;
	}

	/**
	 * @return the displayAxisYTitle
	 */
	public boolean isDisplayAxisYTitle() {
		return displayAxisYTitle;
	}

	/**
	 * @param displayAxisYTitle
	 *            the displayAxisYTitle to set
	 */
	public void setDisplayAxisYTitle(boolean displayAxisYTitle) {
		this.displayAxisYTitle = displayAxisYTitle;
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
	 * @return the dashLongitude
	 */
	public boolean isDashLongitude() {
		return dashLongitude;
	}

	/**
	 * @param dashLongitude
	 *            the dashLongitude to set
	 */
	public void setDashLongitude(boolean dashLongitude) {
		this.dashLongitude = dashLongitude;
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
	 * @return the dashLatitude
	 */
	public boolean isDashLatitude() {
		return dashLatitude;
	}

	/**
	 * @param dashLatitude
	 *            the dashLatitude to set
	 */
	public void setDashLatitude(boolean dashLatitude) {
		this.dashLatitude = dashLatitude;
	}

	/**
	 * @return the dashEffect
	 */
	public PathEffect getDashEffect() {
		return dashEffect;
	}

	/**
	 * @param dashEffect
	 *            the dashEffect to set
	 */
	public void setDashEffect(PathEffect dashEffect) {
		this.dashEffect = dashEffect;
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
	 * @return the longitudeFontColor
	 */
	public int getLongitudeFontColor() {
		return longitudeFontColor;
	}

	/**
	 * @param longitudeFontColor
	 *            the longitudeFontColor to set
	 */
	public void setLongitudeFontColor(int longitudeFontColor) {
		this.longitudeFontColor = longitudeFontColor;
	}

	/**
	 * @return the longitudeFontSize
	 */
	public int getLongitudeFontSize() {
		return longitudeFontSize;
	}

	/**
	 * @param longitudeFontSize
	 *            the longitudeFontSize to set
	 */
	public void setLongitudeFontSize(int longitudeFontSize) {
		this.longitudeFontSize = longitudeFontSize;
	}

	/**
	 * @return the latitudeFontColor
	 */
	public int getLatitudeFontColor() {
		return latitudeFontColor;
	}

	/**
	 * @param latitudeFontColor
	 *            the latitudeFontColor to set
	 */
	public void setLatitudeFontColor(int latitudeFontColor) {
		this.latitudeFontColor = latitudeFontColor;
	}

	/**
	 * @return the latitudeFontSize
	 */
	public int getLatitudeFontSize() {
		return latitudeFontSize;
	}

	/**
	 * @param latitudeFontSize
	 *            the latitudeFontSize to set
	 */
	public void setLatitudeFontSize(int latitudeFontSize) {
		this.latitudeFontSize = latitudeFontSize;
	}

	/**
	 * @return the axisXTitles
	 */
	public List<String> getAxisXTitles() {
		return axisXTitles;
	}

	/**
	 * @param axisXTitles
	 *            the axisXTitles to set
	 */
	public void setAxisXTitles(List<String> axisXTitles) {
		this.axisXTitles = axisXTitles;
	}

	/**
	 * @return the axisYTitles
	 */
	public List<String> getAxisYTitles() {
		return axisYTitles;
	}

	/**
	 * @param axisYTitles
	 *            the axisYTitles to set
	 */
	public void setAxisYTitles(List<String> axisYTitles) {
		this.axisYTitles = axisYTitles;
	}

	/**
	 * @return the axisYMaxTitleLength
	 */
	public int getAxisYMaxTitleLength() {
		return axisYMaxTitleLength;
	}

	/**
	 * @param axisYMaxTitleLength
	 *            the axisYMaxTitleLength to set
	 */
	public void setAxisYMaxTitleLength(int axisYMaxTitleLength) {
		this.axisYMaxTitleLength = axisYMaxTitleLength;
	}

	/**
	 * @return the displayCrossXOnTouch
	 */
	public boolean isDisplayCrossXOnTouch() {
		return displayCrossXOnTouch;
	}

	/**
	 * @param displayCrossXOnTouch
	 *            the displayCrossXOnTouch to set
	 */
	public void setDisplayCrossXOnTouch(boolean displayCrossXOnTouch) {
		this.displayCrossXOnTouch = displayCrossXOnTouch;
	}

	/**
	 * @return the displayCrossYOnTouch
	 */
	public boolean isDisplayCrossYOnTouch() {
		return displayCrossYOnTouch;
	}

	/**
	 * @param displayCrossYOnTouch
	 *            the displayCrossYOnTouch to set
	 */
	public void setDisplayCrossYOnTouch(boolean displayCrossYOnTouch) {
		this.displayCrossYOnTouch = displayCrossYOnTouch;
	}

	/**
	 * @return the clickPostX
	 */
	public float getClickPostX() {
		return clickPostX;
	}

	/**
	 * @param clickPostX
	 *            the clickPostX to set
	 */
	public void setClickPostX(float clickPostX) {
		this.clickPostX = clickPostX;
	}

	/**
	 * @return the clickPostY
	 */
	public float getClickPostY() {
		return clickPostY;
	}

	/**
	 * @param clickPostY
	 *            the clickPostY to set
	 */
	public void setClickPostY(float clickPostY) {
		this.clickPostY = clickPostY;
	}

	/**
	 * @return the notifyList
	 */
	public List<ITouchEventResponse> getNotifyList() {
		return notifyList;
	}

	/**
	 * @param notifyList
	 *            the notifyList to set
	 */
	public void setNotifyList(List<ITouchEventResponse> notifyList) {
		this.notifyList = notifyList;
	}

	/**
	 * @return the touchPoint
	 */
	public PointF getTouchPoint() {
		return touchPoint;
	}

	/**
	 * @param touchPoint
	 *            the touchPoint to set
	 */
	public void setTouchPoint(PointF touchPoint) {
		this.touchPoint = touchPoint;
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
