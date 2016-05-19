/*
 * IGrid.java
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


package cn.limc.androidcharts.common;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.PathEffect;

/** 
 * <p>en</p>
 * <p>jp</p>
 * <p>cn</p>
 *
 * @author limc 
 * @version v1.0 2014/06/24 17:18:55 
 *  
 */
public interface IGrid {
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
	public static final int DEFAULT_LATITUDE_NUM = 2;

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
	public static final boolean DEFAULT_DASH_LONGITUDE = Boolean.FALSE;

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
	public static final boolean DEFAULT_DASH_LATITUDE = Boolean.FALSE;

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
	public static final int DEFAULT_LONGITUDE_FONT_SIZE = 26;

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
	public static final int DEFAULT_LATITUDE_FONT_COLOR = Color.LTGRAY;

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
	public static final int DEFAULT_LATITUDE_FONT_SIZE = 26;
	
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
    public static final int DEFAULT_LONGITUDE_COLOR = Color.LTGRAY;

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
    public static final int DEFAULT_LAITUDE_COLOR = Color.LTGRAY;
    
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
}
