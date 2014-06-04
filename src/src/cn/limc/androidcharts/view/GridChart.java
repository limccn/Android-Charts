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
import android.graphics.Path;
import android.graphics.Paint.Style;
import android.graphics.PathEffect;
import android.graphics.PointF;
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
public class GridChart extends AbstractBaseChart implements ITouchEventNotify,
		ITouchEventResponse, ITouchable {

	public static final int AXIS_X_POSITION_BOTTOM = 1 << 0;
	@Deprecated
	public static final int AXIS_X_POSITION_TOP = 1 << 1;
	public static final int AXIS_Y_POSITION_LEFT = 1 << 2;
	public static final int AXIS_Y_POSITION_RIGHT = 1 << 3;
	
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
	public static final float DEFAULT_AXIS_WIDTH = 1;

	public static final int DEFAULT_AXIS_X_POSITION = AXIS_X_POSITION_BOTTOM;

	public static final int DEFAULT_AXIS_Y_POSITION = AXIS_Y_POSITION_LEFT;

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
	@Deprecated
	public static final float DEFAULT_AXIS_MARGIN_LEFT = 42f;
	public static final float DEFAULT_AXIS_Y_TITLE_QUADRANT_WIDTH = 16f;

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
	@Deprecated
	public static final float DEFAULT_AXIS_MARGIN_BOTTOM = 16f;
	public static final float DEFAULT_AXIS_X_TITLE_QUADRANT_HEIGHT = 16f;

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
	@Deprecated
	public static final float DEFAULT_AXIS_MARGIN_TOP = 5f;
	public static final float DEFAULT_DATA_QUADRANT_PADDING_TOP = 5f;
	public static final float DEFAULT_DATA_QUADRANT_PADDING_BOTTOM = 5f;

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
	@Deprecated
	public static final float DEFAULT_AXIS_MARGIN_RIGHT = 5f;
	public static final float DEFAULT_DATA_QUADRANT_PADDING_LEFT = 5f;
	public static final float DEFAULT_DATA_QUADRANT_PADDING_RIGHT = 5f;

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
	public static final boolean DEFAULT_DISPLAY_LONGITUDE_TITLE = Boolean.TRUE;
	
	public static final float DEFAULT_LONGITUDE_WIDTH = 1f;
	

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
	public static final boolean DEFAULT_DISPLAY_LATITUDE_TITLE = Boolean.TRUE;
	
	public static final float DEFAULT_LATITUDE_WIDTH = 1f;

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

	public static final float DEFAULT_BORDER_WIDTH = 1f;

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

	public static final int DEFAULT_CROSS_LINES_COLOR = Color.CYAN;
	public static final int DEFAULT_CROSS_LINES_FONT_COLOR = Color.CYAN;

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
	public static final int DEFAULT_LATITUDE_MAX_TITLE_LENGTH = 5;

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
			new float[] { 6, 3, 6, 3 }, 1);

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

	private float axisWidth = DEFAULT_AXIS_WIDTH;

	protected int axisXPosition = DEFAULT_AXIS_X_POSITION;

	protected int axisYPosition = DEFAULT_AXIS_Y_POSITION;

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
	protected float axisYTitleQuadrantWidth = DEFAULT_AXIS_Y_TITLE_QUADRANT_WIDTH;

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
	protected float axisXTitleQuadrantHeight = DEFAULT_AXIS_X_TITLE_QUADRANT_HEIGHT;

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
	protected float dataQuadrantPaddingTop = DEFAULT_DATA_QUADRANT_PADDING_TOP;

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
	protected float dataQuadrantPaddingLeft = DEFAULT_DATA_QUADRANT_PADDING_LEFT;
	protected float dataQuadrantPaddingBottom = DEFAULT_DATA_QUADRANT_PADDING_BOTTOM;

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
	protected float dataQuadrantPaddingRight = DEFAULT_DATA_QUADRANT_PADDING_RIGHT;

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
	private boolean displayLongitudeTitle = DEFAULT_DISPLAY_LONGITUDE_TITLE;
	
	private float longitudeWidth = DEFAULT_LONGITUDE_WIDTH;

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
	private boolean displayLatitudeTitle = DEFAULT_DISPLAY_LATITUDE_TITLE;
	
	private float latitudeWidth = DEFAULT_LATITUDE_WIDTH;

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
	protected int latitudeNum = DEFAULT_LATITUDE_NUM;

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
	protected int longitudeNum = DEFAULT_LONGITUDE_NUM;

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

	protected float borderWidth = DEFAULT_BORDER_WIDTH;

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
	 * Color of cross line inside grid when touched
	 * </p>
	 * <p>
	 * タッチしたポイント表示用十字線の色
	 * </p>
	 * <p>
	 * 十字交叉线颜色
	 * </p>
	 */
	private int crossLinesColor = DEFAULT_CROSS_LINES_COLOR;

	/**
	 * <p>
	 * Color of cross line degree text when touched
	 * </p>
	 * <p>
	 * タッチしたポイント表示用十字線度数文字の色
	 * </p>
	 * <p>
	 * 十字交叉线坐标轴字体颜色
	 * </p>
	 */
	private int crossLinesFontColor = DEFAULT_CROSS_LINES_FONT_COLOR;

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
	private List<String> longitudeTitles;

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
	private List<String> latitudeTitles;

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
	private int latitudeMaxTitleLength = DEFAULT_LATITUDE_MAX_TITLE_LENGTH;

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
	private float clickPostX;

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
	private float clickPostY;

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
	
	protected OnTouchGestureListener onTouchGestureListener;
	
	protected int touchMode = TOUCH_MODE_NONE;

	/*
	 * (non-Javadoc)
	 * 
	 * @param context
	 * 
	 * @see cn.limc.androidcharts.view.AbstractBaseChart#BaseChart(Context)
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
	 * @see cn.limc.androidcharts.view.AbstractBaseChart#BaseChart(Context,
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
	 * @see cn.limc.androidcharts.view.AbstractBaseChart#BaseChart(Context,
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

		drawXAxis(canvas);
		drawYAxis(canvas);

		if (this.displayBorder) {
			drawBorder(canvas);
		}

		if (displayLongitude || displayLongitudeTitle) {
			drawLongitudeLine(canvas);
			drawLongitudeTitle(canvas);
		}
		if (displayLatitude || displayLatitudeTitle) {
			drawLatitudeLine(canvas);
			drawLatitudeTitle(canvas);
		}

		if (displayCrossXOnTouch || displayCrossYOnTouch) {
			// drawWithFingerClick(canvas);
			drawHorizontalLine(canvas);
			drawVerticalLine(canvas);

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

		if (event.getX() < getDataQuadrantPaddingStartX()
				|| event.getX() > getDataQuadrantPaddingEndX()) {
			return false;
		}

		if (event.getY() < getDataQuadrantPaddingStartY()
				|| event.getY() > getDataQuadrantPaddingEndY()) {
			return false;
		}

		// touched points, if touch point is only one
		if (event.getPointerCount() == 1) {
			// 获取点击坐标

			clickPostX = event.getX();
			clickPostY = event.getY();

			PointF point = new PointF(clickPostX, clickPostY);
			touchPoint = point;

			// redraw
			super.invalidate();

			// do notify
			notifyEventAll(this);
			
			// call back to listener
			if (onTouchGestureListener != null) {
				onTouchGestureListener.onTouchDown(point, TOUCH_NO_SELECTED_INDEX);
			}

		} else if (event.getPointerCount() == 2) {
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
		mPaintBox.setColor(Color.WHITE);
		mPaintBox.setAlpha(80);
		mPaintBox.setStyle(Style.FILL);

		Paint mPaintBoxLine = new Paint();
		mPaintBoxLine.setColor(crossLinesColor);
		mPaintBoxLine.setAntiAlias(true);
		mPaintBoxLine.setTextSize(fontSize);

		// draw a rectangle
		canvas.drawRect(ptStart.x, ptStart.y, ptEnd.x, ptEnd.y, mPaintBox);

		// draw a rectangle' border
		canvas.drawLine(ptStart.x, ptStart.y, ptStart.x, ptEnd.y, mPaintBoxLine);
		canvas.drawLine(ptStart.x, ptEnd.y, ptEnd.x, ptEnd.y, mPaintBoxLine);
		canvas.drawLine(ptEnd.x, ptEnd.y, ptEnd.x, ptStart.y, mPaintBoxLine);
		canvas.drawLine(ptEnd.x, ptStart.y, ptStart.x, ptStart.y, mPaintBoxLine);

		mPaintBoxLine.setColor(crossLinesFontColor);
		// draw text
		canvas.drawText(content, ptStart.x, ptStart.y + fontSize, mPaintBoxLine);
	}

	protected float getDataQuadrantWidth() {
		return super.getWidth() - axisYTitleQuadrantWidth - 2 * borderWidth
				- axisWidth;
	}

	protected float getDataQuadrantHeight() {
		return super.getHeight() - axisXTitleQuadrantHeight - 2 * borderWidth
				- axisWidth;
	}

	protected float getDataQuadrantStartX() {
		if (axisYPosition == AXIS_Y_POSITION_LEFT) {
			return borderWidth + axisYTitleQuadrantWidth + axisWidth;
		} else {
			return borderWidth;
		}
	}

	protected float getDataQuadrantPaddingStartX() {
		return getDataQuadrantStartX() + dataQuadrantPaddingLeft;
	}

	protected float getDataQuadrantEndX() {
		if (axisYPosition == AXIS_Y_POSITION_LEFT) {
			return super.getWidth() - borderWidth;
		} else {
			return super.getWidth() - borderWidth - axisYTitleQuadrantWidth
					- axisWidth;
		}
	}

	protected float getDataQuadrantPaddingEndX() {
		return getDataQuadrantEndX() - dataQuadrantPaddingRight;
	}

	protected float getDataQuadrantStartY() {
		return borderWidth;
	}

	protected float getDataQuadrantPaddingStartY() {
		return getDataQuadrantStartY() + dataQuadrantPaddingTop;
	}

	protected float getDataQuadrantEndY() {
		return super.getHeight() - borderWidth - axisXTitleQuadrantHeight
				- axisWidth;
	}

	protected float getDataQuadrantPaddingEndY() {
		return getDataQuadrantEndY() - dataQuadrantPaddingBottom;
	}

	protected float getDataQuadrantPaddingWidth() {
		return getDataQuadrantWidth() - dataQuadrantPaddingLeft
				- dataQuadrantPaddingRight;
	}

	protected float getDataQuadrantPaddingHeight() {
		return getDataQuadrantHeight() - dataQuadrantPaddingTop
				- dataQuadrantPaddingBottom;
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
		float valueLength = ((Float) value).floatValue()
				- getDataQuadrantPaddingStartX();
		return String.valueOf(valueLength / this.getDataQuadrantPaddingWidth());
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
		float valueLength = ((Float) value).floatValue()
				- getDataQuadrantPaddingStartY();
		return String
				.valueOf(valueLength / this.getDataQuadrantPaddingHeight());
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
	protected void drawVerticalLine(Canvas canvas) {

		if (!displayLongitudeTitle) {
			return;
		}
		if (!displayCrossXOnTouch) {
			return;
		}
		if (clickPostX <= 0) {
			return;
		}

		Paint mPaint = new Paint();
		mPaint.setColor(crossLinesColor);

		float lineVLength = getDataQuadrantHeight() + axisWidth;

		// TODO calculate points to draw
		PointF boxVS = new PointF(clickPostX - longitudeFontSize * 5f / 2f,
				borderWidth + lineVLength);
		PointF boxVE = new PointF(clickPostX + longitudeFontSize * 5f / 2f,
				borderWidth + lineVLength + axisXTitleQuadrantHeight);

		// draw text
		drawAlphaTextBox(boxVS, boxVE, getAxisXGraduate(clickPostX),
				longitudeFontSize, canvas);

		canvas.drawLine(clickPostX, borderWidth, clickPostX, lineVLength,
				mPaint);
	}

	protected void drawHorizontalLine(Canvas canvas) {

		if (!displayLatitudeTitle) {
			return;
		}
		if (!displayCrossYOnTouch) {
			return;
		}
		if (clickPostY <= 0) {
			return;
		}

		Paint mPaint = new Paint();
		mPaint.setColor(crossLinesColor);

		float lineHLength = getDataQuadrantWidth() + axisWidth;

		if (axisYPosition == AXIS_Y_POSITION_LEFT) {
			PointF boxHS = new PointF(borderWidth, clickPostY
					- latitudeFontSize / 2f - 2);
			PointF boxHE = new PointF(borderWidth + axisYTitleQuadrantWidth,
					clickPostY + latitudeFontSize / 2f + 2);

			// draw text
			drawAlphaTextBox(boxHS, boxHE, getAxisYGraduate(clickPostY),
					latitudeFontSize, canvas);

			canvas.drawLine(borderWidth + axisYTitleQuadrantWidth, clickPostY,
					borderWidth + axisYTitleQuadrantWidth + lineHLength,
					clickPostY, mPaint);
		} else {
			PointF boxHS = new PointF(super.getWidth() - borderWidth
					- axisYTitleQuadrantWidth, clickPostY - latitudeFontSize
					/ 2f - 2);
			PointF boxHE = new PointF(super.getWidth() - borderWidth,
					clickPostY + latitudeFontSize / 2f + 2);

			// draw text
			drawAlphaTextBox(boxHS, boxHE, getAxisYGraduate(clickPostY),
					latitudeFontSize, canvas);

			canvas.drawLine(borderWidth, clickPostY, borderWidth + lineHLength,
					clickPostY, mPaint);
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
		float postY;
		if (axisXPosition == AXIS_X_POSITION_BOTTOM) {
			postY = super.getHeight() - axisXTitleQuadrantHeight - borderWidth
					- axisWidth / 2;
		} else {
			postY = super.getHeight() - borderWidth - axisWidth / 2;
		}

		Paint mPaint = new Paint();
		mPaint.setColor(axisXColor);
		mPaint.setStrokeWidth(axisWidth);

		canvas.drawLine(borderWidth, postY, length, postY, mPaint);

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

		float length = super.getHeight() - axisXTitleQuadrantHeight
				- borderWidth;
		float postX;
		if (axisYPosition == AXIS_Y_POSITION_LEFT) {
			postX = borderWidth + axisYTitleQuadrantWidth + axisWidth / 2;
		} else {
			postX = super.getWidth() - borderWidth - axisYTitleQuadrantWidth
					- axisWidth / 2;
		}

		Paint mPaint = new Paint();
		mPaint.setColor(axisXColor);
		mPaint.setStrokeWidth(axisWidth);

		canvas.drawLine(postX, borderWidth, postX, length, mPaint);
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
	protected void drawLongitudeLine(Canvas canvas) {
		if (null == longitudeTitles) {
			return;
		}
		if (!displayLongitude) {
			return;
		}
		int counts = longitudeTitles.size();
		float length = getDataQuadrantHeight();

		Paint mPaintLine = new Paint();
		mPaintLine.setStyle(Style.STROKE);
		mPaintLine.setColor(longitudeColor);
		mPaintLine.setStrokeWidth(longitudeWidth);
		mPaintLine.setAntiAlias(true);
		if (dashLongitude) {
			mPaintLine.setPathEffect(dashEffect);
		}
		if (counts > 1) {
			float postOffset = this.getDataQuadrantPaddingWidth()
					/ (counts - 1);

			float offset;
			if (axisYPosition == AXIS_Y_POSITION_LEFT) {
				offset = borderWidth + axisYTitleQuadrantWidth + axisWidth
						+ dataQuadrantPaddingLeft;
			} else {
				offset = borderWidth + dataQuadrantPaddingLeft;
			}

			for (int i = 0; i < counts; i++) {
				Path path = new Path();
				path.moveTo(offset + i * postOffset, borderWidth);
				path.lineTo(offset + i * postOffset, length);
				canvas.drawPath(path, mPaintLine);
//				canvas.drawLine(offset + i * postOffset, borderWidth, offset
//						+ i * postOffset, length, mPaintLine);
			}
		}
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
	protected void drawLongitudeTitle(Canvas canvas) {

		if (null == longitudeTitles) {
			return;
		}
		if (!displayLongitude) {
			return;
		}
		if (!displayLongitudeTitle) {
			return;
		}

		if (longitudeTitles.size() <= 1) {
			return;
		}

		Paint mPaintFont = new Paint();
		mPaintFont.setColor(longitudeFontColor);
		mPaintFont.setTextSize(longitudeFontSize);
		mPaintFont.setAntiAlias(true);

		float postOffset = this.getDataQuadrantPaddingWidth()
				/ (longitudeTitles.size() - 1);

		if (axisYPosition == AXIS_Y_POSITION_LEFT) {
			float offset = borderWidth + axisYTitleQuadrantWidth + axisWidth
					+ dataQuadrantPaddingLeft;
			for (int i = 0; i < longitudeTitles.size(); i++) {
				if (0 == i) {
					canvas.drawText(longitudeTitles.get(i), offset + 2f,
							super.getHeight() - axisXTitleQuadrantHeight
									+ longitudeFontSize, mPaintFont);
				} else {
					canvas.drawText(longitudeTitles.get(i), offset + i
							* postOffset - (longitudeTitles.get(i).length())
							* longitudeFontSize / 2f, super.getHeight()
							- axisXTitleQuadrantHeight + longitudeFontSize,
							mPaintFont);
				}
			}

		} else {
			float offset = borderWidth + dataQuadrantPaddingLeft;
			for (int i = 0; i < longitudeTitles.size(); i++) {
				if (0 == i) {
					canvas.drawText(longitudeTitles.get(i), offset + 2f,
							super.getHeight() - axisXTitleQuadrantHeight
									+ longitudeFontSize, mPaintFont);
				} else {
					canvas.drawText(longitudeTitles.get(i), offset + i
							* postOffset - (longitudeTitles.get(i).length())
							* longitudeFontSize / 2f, super.getHeight()
							- axisXTitleQuadrantHeight + longitudeFontSize,
							mPaintFont);
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
	protected void drawLatitudeLine(Canvas canvas) {

		if (null == latitudeTitles) {
			return;
		}
		if (!displayLatitude) {
			return;
		}
		if (!displayLatitudeTitle) {
			return;
		}
		if (latitudeTitles.size() <= 1) {
			return;
		}

		float length = getDataQuadrantWidth();
		
		Paint mPaintLine = new Paint();
		mPaintLine.setStyle(Style.STROKE);
		mPaintLine.setColor(latitudeColor);
		mPaintLine.setStrokeWidth(latitudeWidth);
		mPaintLine.setAntiAlias(true);
		if (dashLatitude) {
			mPaintLine.setPathEffect(dashEffect);
		}

		Paint mPaintFont = new Paint();
		mPaintFont.setColor(latitudeFontColor);
		mPaintFont.setTextSize(latitudeFontSize);
		mPaintFont.setAntiAlias(true);

		float postOffset = this.getDataQuadrantPaddingHeight()
				/ (latitudeTitles.size() - 1);

		float offset = super.getHeight() - borderWidth
				- axisXTitleQuadrantHeight - axisWidth
				- dataQuadrantPaddingBottom;

		if (axisYPosition == AXIS_Y_POSITION_LEFT) {
			float startFrom = borderWidth + axisYTitleQuadrantWidth + axisWidth;
			for (int i = 0; i < latitudeTitles.size(); i++) {
				Path path = new Path();
				path.moveTo(startFrom, offset - i * postOffset);
				path.lineTo(startFrom + length, offset - i * postOffset);
				canvas.drawPath(path, mPaintLine);
//				canvas.drawLine(startFrom, offset - i * postOffset, startFrom
//						+ length, offset - i * postOffset, mPaintLine);
			}
		} else {
			float startFrom = borderWidth;
			for (int i = 0; i < latitudeTitles.size(); i++) {
				Path path = new Path();
				path.moveTo(startFrom, offset - i * postOffset);
				path.lineTo(startFrom + length, offset - i * postOffset);
				canvas.drawPath(path, mPaintLine);
//				canvas.drawLine(startFrom, offset - i * postOffset, startFrom
//						+ length, offset - i * postOffset, mPaintLine);
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
	protected void drawLatitudeTitle(Canvas canvas) {
		if (null == latitudeTitles) {
			return;
		}
		if (!displayLatitudeTitle) {
			return;
		}
		if (latitudeTitles.size() <= 1) {
			return;
		}
		Paint mPaintFont = new Paint();
		mPaintFont.setColor(latitudeFontColor);
		mPaintFont.setTextSize(latitudeFontSize);
		mPaintFont.setAntiAlias(true);

		float postOffset = this.getDataQuadrantPaddingHeight()
				/ (latitudeTitles.size() - 1);

		float offset = super.getHeight() - borderWidth
				- axisXTitleQuadrantHeight - axisWidth
				- dataQuadrantPaddingBottom;

		if (axisYPosition == AXIS_Y_POSITION_LEFT) {
			float startFrom = borderWidth;
			for (int i = 0; i < latitudeTitles.size(); i++) {
				if (0 == i) {
					canvas.drawText(latitudeTitles.get(i), startFrom,
							super.getHeight() - this.axisXTitleQuadrantHeight
									- borderWidth - axisWidth - 2f, mPaintFont);
				} else {
					canvas.drawText(latitudeTitles.get(i), startFrom, offset
							- i * postOffset + latitudeFontSize / 2f,
							mPaintFont);
				}
			}
		} else {
			float startFrom = super.getWidth() - borderWidth
					- axisYTitleQuadrantWidth;
			for (int i = 0; i < latitudeTitles.size(); i++) {

				if (0 == i) {
					canvas.drawText(latitudeTitles.get(i), startFrom,
							super.getHeight() - this.axisXTitleQuadrantHeight
									- borderWidth - axisWidth - 2f, mPaintFont);
				} else {
					canvas.drawText(latitudeTitles.get(i), startFrom, offset
							- i * postOffset + latitudeFontSize / 2f,
							mPaintFont);
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
		//DO NOTHING
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
		//DO NOTHING
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
	public void removeNotify(int index) {
		if (null != notifyList && notifyList.size() > index) {
			notifyList.remove(index);
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
	 * @return the axisWidth
	 */
	public float getAxisWidth() {
		return axisWidth;
	}

	/**
	 * @param axisWidth
	 *            the axisWidth to set
	 */
	public void setAxisWidth(float axisWidth) {
		this.axisWidth = axisWidth;
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
	@Deprecated
	public float getAxisMarginLeft() {
		return axisYTitleQuadrantWidth;
	}

	/**
	 * @param axisMarginLeft
	 *            the axisMarginLeft to set
	 */
	@Deprecated
	public void setAxisMarginLeft(float axisMarginLeft) {
		this.axisYTitleQuadrantWidth = axisMarginLeft;
	}

	/**
	 * @return the axisMarginLeft
	 */
	public float getAxisYTitleQuadrantWidth() {
		return axisYTitleQuadrantWidth;
	}

	/**
	 * @param axisYTitleQuadrantWidth
	 *            the axisYTitleQuadrantWidth to set
	 */
	public void setAxisYTitleQuadrantWidth(float axisYTitleQuadrantWidth) {
		this.axisYTitleQuadrantWidth = axisYTitleQuadrantWidth;
	}

	/**
	 * @return the axisXTitleQuadrantHeight
	 */
	@Deprecated
	public float getAxisMarginBottom() {
		return axisXTitleQuadrantHeight;
	}

	/**
	 * @param axisXTitleQuadrantHeight
	 *            the axisXTitleQuadrantHeight to set
	 */
	@Deprecated
	public void setAxisMarginBottom(float axisXTitleQuadrantHeight) {
		this.axisXTitleQuadrantHeight = axisXTitleQuadrantHeight;
	}

	/**
	 * @return the axisXTitleQuadrantHeight
	 */
	public float getAxisXTitleQuadrantHeight() {
		return axisXTitleQuadrantHeight;
	}

	/**
	 * @param axisXTitleQuadrantHeight
	 *            the axisXTitleQuadrantHeight to set
	 */
	public void setAxisXTitleQuadrantHeight(float axisXTitleQuadrantHeight) {
		this.axisXTitleQuadrantHeight = axisXTitleQuadrantHeight;
	}

	/**
	 * @return the dataQuadrantPaddingTop
	 */
	@Deprecated
	public float getAxisMarginTop() {
		return dataQuadrantPaddingTop;
	}

	/**
	 * @param axisMarginTop
	 *            the axisMarginTop to set
	 */
	@Deprecated
	public void setAxisMarginTop(float axisMarginTop) {
		this.dataQuadrantPaddingTop = axisMarginTop;
		this.dataQuadrantPaddingBottom = axisMarginTop;
	}

	/**
	 * @return the dataQuadrantPaddingRight
	 */
	@Deprecated
	public float getAxisMarginRight() {
		return dataQuadrantPaddingRight;
	}

	/**
	 * @param axisMarginRight
	 *            the axisMarginRight to set
	 */
	@Deprecated
	public void setAxisMarginRight(float axisMarginRight) {
		this.dataQuadrantPaddingRight = axisMarginRight;
		this.dataQuadrantPaddingLeft = axisMarginRight;
	}

	/**
	 * @return the dataQuadrantPaddingTop
	 */
	public float getDataQuadrantPaddingTop() {
		return dataQuadrantPaddingTop;
	}

	/**
	 * @param dataQuadrantPaddingTop
	 *            the dataQuadrantPaddingTop to set
	 */
	public void setDataQuadrantPaddingTop(float dataQuadrantPaddingTop) {
		this.dataQuadrantPaddingTop = dataQuadrantPaddingTop;
	}

	/**
	 * @return the dataQuadrantPaddingLeft
	 */
	public float getDataQuadrantPaddingLeft() {
		return dataQuadrantPaddingLeft;
	}

	/**
	 * @param dataQuadrantPaddingLeft
	 *            the dataQuadrantPaddingLeft to set
	 */
	public void setDataQuadrantPaddingLeft(float dataQuadrantPaddingLeft) {
		this.dataQuadrantPaddingLeft = dataQuadrantPaddingLeft;
	}

	/**
	 * @return the dataQuadrantPaddingBottom
	 */
	public float getDataQuadrantPaddingBottom() {
		return dataQuadrantPaddingBottom;
	}

	/**
	 * @param dataQuadrantPaddingBottom
	 *            the dataQuadrantPaddingBottom to set
	 */
	public void setDataQuadrantPaddingBottom(float dataQuadrantPaddingBottom) {
		this.dataQuadrantPaddingBottom = dataQuadrantPaddingBottom;
	}

	/**
	 * @return the dataQuadrantPaddingRight
	 */
	public float getDataQuadrantPaddingRight() {
		return dataQuadrantPaddingRight;
	}

	/**
	 * @param dataQuadrantPaddingRight
	 *            the dataQuadrantPaddingRight to set
	 */
	public void setDataQuadrantPaddingRight(float dataQuadrantPaddingRight) {
		this.dataQuadrantPaddingRight = dataQuadrantPaddingRight;
	}

	/**
	 * @param padding
	 *            the dataQuadrantPaddingTop dataQuadrantPaddingBottom
	 *            dataQuadrantPaddingLeft dataQuadrantPaddingRight to set
	 * 
	 */
	public void setDataQuadrantPadding(float padding) {
		this.dataQuadrantPaddingTop = padding;
		this.dataQuadrantPaddingLeft = padding;
		this.dataQuadrantPaddingBottom = padding;
		this.dataQuadrantPaddingRight = padding;
	}

	/**
	 * @param topnbottom
	 *            the dataQuadrantPaddingTop dataQuadrantPaddingBottom to set
	 * @param leftnright
	 *            the dataQuadrantPaddingLeft dataQuadrantPaddingRight to set
	 * 
	 */
	public void setDataQuadrantPadding(float topnbottom, float leftnright) {
		this.dataQuadrantPaddingTop = topnbottom;
		this.dataQuadrantPaddingLeft = leftnright;
		this.dataQuadrantPaddingBottom = topnbottom;
		this.dataQuadrantPaddingRight = leftnright;
	}

	/**
	 * @param top
	 *            the dataQuadrantPaddingTop to set
	 * @param right
	 *            the dataQuadrantPaddingLeft to set
	 * @param bottom
	 *            the dataQuadrantPaddingBottom to set
	 * @param left
	 *            the dataQuadrantPaddingRight to set
	 * 
	 */
	public void setDataQuadrantPadding(float top, float right, float bottom,
			float left) {
		this.dataQuadrantPaddingTop = top;
		this.dataQuadrantPaddingLeft = right;
		this.dataQuadrantPaddingBottom = bottom;
		this.dataQuadrantPaddingRight = left;
	}

	/**
	 * @return the displayLongitudeTitle
	 */
	public boolean isDisplayLongitudeTitle() {
		return displayLongitudeTitle;
	}

	/**
	 * @param displayLongitudeTitle
	 *            the displayLongitudeTitle to set
	 */
	public void setDisplayLongitudeTitle(boolean displayLongitudeTitle) {
		this.displayLongitudeTitle = displayLongitudeTitle;
	}

	/**
	 * @return the displayAxisYTitle
	 */
	public boolean isDisplayLatitudeTitle() {
		return displayLatitudeTitle;
	}

	/**
	 * @param displayLatitudeTitle
	 *            the displayLatitudeTitle to set
	 */
	public void setDisplayLatitudeTitle(boolean displayLatitudeTitle) {
		this.displayLatitudeTitle = displayLatitudeTitle;
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
	 * @return the longitudeWidth
	 */
	public float getLongitudeWidth() {
		return longitudeWidth;
	}

	/**
	 * @param longitudeWidth the longitudeWidth to set
	 */
	public void setLongitudeWidth(float longitudeWidth) {
		this.longitudeWidth = longitudeWidth;
	}

	/**
	 * @return the latitudeWidth
	 */
	public float getLatitudeWidth() {
		return latitudeWidth;
	}

	/**
	 * @param latitudeWidth the latitudeWidth to set
	 */
	public void setLatitudeWidth(float latitudeWidth) {
		this.latitudeWidth = latitudeWidth;
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
	 * @return the crossLinesColor
	 */
	public int getCrossLinesColor() {
		return crossLinesColor;
	}

	/**
	 * @param crossLinesColor
	 *            the crossLinesColor to set
	 */
	public void setCrossLinesColor(int crossLinesColor) {
		this.crossLinesColor = crossLinesColor;
	}

	/**
	 * @return the crossLinesFontColor
	 */
	public int getCrossLinesFontColor() {
		return crossLinesFontColor;
	}

	/**
	 * @param crossLinesFontColor
	 *            the crossLinesFontColor to set
	 */
	public void setCrossLinesFontColor(int crossLinesFontColor) {
		this.crossLinesFontColor = crossLinesFontColor;
	}

	/**
	 * @return the longitudeTitles
	 */
	public List<String> getLongitudeTitles() {
		return longitudeTitles;
	}

	/**
	 * @param longitudeTitles
	 *            the longitudeTitles to set
	 */
	public void setLongitudeTitles(List<String> longitudeTitles) {
		this.longitudeTitles = longitudeTitles;
	}

	/**
	 * @return the latitudeTitles
	 */
	public List<String> getLatitudeTitles() {
		return latitudeTitles;
	}

	/**
	 * @param latitudeTitles
	 *            the latitudeTitles to set
	 */
	public void setLatitudeTitles(List<String> latitudeTitles) {
		this.latitudeTitles = latitudeTitles;
	}

	/**
	 * @return the latitudeMaxTitleLength
	 */
	public int getLatitudeMaxTitleLength() {
		return latitudeMaxTitleLength;
	}

	/**
	 * @param latitudeMaxTitleLength
	 *            the latitudeMaxTitleLength to set
	 */
	public void setLatitudeMaxTitleLength(int latitudeMaxTitleLength) {
		this.latitudeMaxTitleLength = latitudeMaxTitleLength;
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
	 * @return the axisXPosition
	 */
	public int getAxisXPosition() {
		return axisXPosition;
	}

	/**
	 * @param axisXPosition
	 *            the axisXPosition to set
	 */
	public void setAxisXPosition(int axisXPosition) {
		this.axisXPosition = axisXPosition;
	}

	/**
	 * @return the axisYPosition
	 */
	public int getAxisYPosition() {
		return axisYPosition;
	}

	/**
	 * @param axisYPosition
	 *            the axisYPosition to set
	 */
	public void setAxisYPosition(int axisYPosition) {
		this.axisYPosition = axisYPosition;
	}
	
	
	/**
	 * @param listener
	 *            the OnTouchGestureListener to set
	 */
	public void setOnTouchGestureListener(OnTouchGestureListener listener) {
		this.onTouchGestureListener = listener;
	}
}
