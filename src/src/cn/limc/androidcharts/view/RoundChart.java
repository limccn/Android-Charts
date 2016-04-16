/*
 * RoundChart.java
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

import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.util.AttributeSet;


public class RoundChart extends AbstractBaseChart {
    
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
    public static final String DEFAULT_TITLE = "Round Chart";

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
     * default radius length
     * </p>
     * <p>
     * 半径の長さのデフォルト値
     * </p>
     * <p>
     * 默认半径长度
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
    public static final int DEFAULT_LONGITUDE_COLOR = Color.WHITE;

    /**
     * <p>
     * default color for circle's border
     * </p>
     * <p>
     * 円弧の色のデフォルト値
     * </p>
     * <p>
     * 默认圆弧的颜色
     * </p>
     */
    public static final int DEFAULT_CIRCLE_BORDER_COLOR = Color.WHITE;
    
    
    /**
     * <p>
     * default color for circle's border
     * </p>
     * <p>
     * 円弧の色のデフォルト値
     * </p>
     * <p>
     * 默认圆弧的颜色
     * </p>
     */
    public static final int DEFAULT_CIRCLE_BORDER_WIDTH = 4;

    /**
     * <p>
     * default position
     * </p>
     * <p>
     * 中心の位置のデフォルト値
     * </p>
     * <p>
     * 默认绘图中心位置
     * </p>
     */
    public static final Point DEFAULT_POSITION = new Point(0, 0);
   
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
    protected String title = DEFAULT_TITLE;

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
    protected Point position = DEFAULT_POSITION;

    /**
     * <p>
     * radius length
     * </p>
     * <p>
     * 半径の長さ
     * </p>
     * <p>
     * 半径长度
     * </p>
     */
    protected float longitudeLength = DEFAULT_LONGITUDE_LENGTH;

    /**
     * <p>
     * Color for longitude lines
     * </p>
     * <p>
     * 経線の色
     * </p>
     * <p>
     * 经线颜色
     * </p>
     */
    protected int longitudeColor = DEFAULT_LONGITUDE_COLOR;

    /**
     * <p>
     * Color for circle's border
     * </p>
     * <p>
     * 円弧の色
     * </p>
     * <p>
     * 圆弧的颜色
     * </p>
     */
    protected int circleBorderColor = DEFAULT_CIRCLE_BORDER_COLOR;
    
    protected int circleBorderWidth = DEFAULT_CIRCLE_BORDER_WIDTH;

    /**
     * <p>
     * should display the longitude lines
     * </p>
     * <p>
     * 経線を表示する?
     * </p>
     * <p>
     * 是否显示经线
     * </p>
     */
    protected boolean displayLongitude = DEFAULT_DISPLAY_LONGITUDE;
    

    /**
     * @param context
     * @param attrs
     * @param defStyle
     */
    public RoundChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param context
     * @param attrs
     */
    public RoundChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param context
     */
    public RoundChart(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
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
     * @return the longitudeLength
     */
    public float getLongitudeLength() {
        return longitudeLength;
    }

    /**
     * @param longitudeLength
     *            the longitudeLength to set
     */
    public void setLongitudeLength(float radiusLength) {
        this.longitudeLength = radiusLength;
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
    public void setLongitudeColor(int radiusColor) {
        this.longitudeColor = radiusColor;
    }

    /**
     * @return the circleBorderColor
     */
    public int getCircleBorderColor() {
        return circleBorderColor;
    }

    /**
     * @param circleBorderColor
     *            the circleBorderColor to set
     */
    public void setCircleBorderColor(int circleBorderColor) {
        this.circleBorderColor = circleBorderColor;
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
    public void setDisplayLongitude(boolean displayRadius) {
        this.displayLongitude = displayRadius;
    }

    /**
     * @return the circleBorderWidth
     */
    public int getCircleBorderWidth() {
        return circleBorderWidth;
    }

    /**
     * @param circleBorderWidth the circleBorderWidth to set
     */
    public void setCircleBorderWidth(int circleBorderWidth) {
        this.circleBorderWidth = circleBorderWidth;
    }

}
