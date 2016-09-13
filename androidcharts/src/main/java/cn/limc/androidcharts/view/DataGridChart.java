/*
 * DataGridChart.java
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

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.limc.androidcharts.common.CustomLines;
import cn.limc.androidcharts.common.IDataCursor;
import cn.limc.androidcharts.common.SectionDataCursor;
import cn.limc.androidcharts.entity.IChartData;
import cn.limc.androidcharts.entity.IMeasurable;
import cn.limc.androidcharts.entity.IStickEntity;
import cn.limc.androidcharts.event.IDisplayCursorListener;
import cn.limc.androidcharts.event.ITouchedIndexListener;
import cn.limc.androidcharts.event.ITouchedValueListener;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.util.AttributeSet;

/** 
 * <p>en</p>
 * <p>jp</p>
 * <p>cn</p>
 *
 * @author limc 
 * @version v1.0 2014/06/18 15:38:33 
 *  
 */
public abstract class DataGridChart extends GridChart implements IDataCursor {
	
	public static final boolean DEFAULT_AUTO_CALC_VALUE_RANGE = true;
	public static final boolean DEFAULT_AUTO_CALC_LONGITUDE_TITLES = true;
	public static final boolean DEFAULT_AUTO_CALC_LATITUDE_TITLES = true;
	public static final boolean DEFAULT_AUTO_CALC_BALANCE_RANGE = false;
	public static final float[] DEFAULT_NONE_DISPLAY_VALUE = new float[]{};

	public static final int DEFAULT_DATA_MULTIPLE = 1;
	public static final String DEFAULT_AXIS_Y_DECIMAL_FORMAT = "#,##0";
	public static final String DEFAULT_AXIS_X_DATE_TARGET_FORMAT = "yyyy/MM/dd";
	public static final String DEFAULT_AXIS_X_DATE_SOURCE_FORMAT = "yyyyMMdd";
	
	public int dataMultiple =  DEFAULT_DATA_MULTIPLE;
	
	protected String axisYDecimalFormat = DEFAULT_AXIS_Y_DECIMAL_FORMAT;
	protected String axisXDateTargetFormat = DEFAULT_AXIS_X_DATE_TARGET_FORMAT;
	protected String axisXDateSourceFormat = DEFAULT_AXIS_X_DATE_SOURCE_FORMAT;

	public float[] noneDisplayValue = DEFAULT_NONE_DISPLAY_VALUE;

	public abstract IDataCursor getDataCursor();

	public abstract IChartData<IStickEntity> getChartData();
	
	protected  ITouchedIndexListener touchedIndexListener;
	protected  ITouchedValueListener touchedValueListener;

	protected CustomLines customLines = new CustomLines(this);
	public static final boolean DEFAULT_DISPLAY_CUSTOM_LINES = true;
	protected boolean displayCustomLines = DEFAULT_DISPLAY_CUSTOM_LINES;

	/**
	 *
	 * <p>
	 * max value of Y axis
	 * </p>
	 * <p>
	 * Y軸の最大値
	 * </p>
	 * <p>
	 * Y的最大表示值
	 * </p>
	 */
	protected double maxValue;

	/**
	 * <p>
	 * min value of Y axis
	 * </p>
	 * <p>
	 * Y軸の最小値
	 * </p>
	 * <p>
	 * Y的最小表示值
	 * </p>
	 */
	protected double minValue;

	protected double maxDataValue;
	protected double minDataValue;

	protected double midValue;

	protected boolean autoCalcValueRange = DEFAULT_AUTO_CALC_VALUE_RANGE;
	protected boolean autoBalanceValueRange = DEFAULT_AUTO_CALC_BALANCE_RANGE;

	protected boolean autoCalcLongitudeTitle = DEFAULT_AUTO_CALC_LONGITUDE_TITLES;
	protected boolean autoCalcLatitudeTitle = DEFAULT_AUTO_CALC_LATITUDE_TITLES;


	protected void calcDataValueRange() {
		double maxValue = Double.MIN_VALUE;
		double minValue = Double.MAX_VALUE;
		IMeasurable first = getChartData().get(getDataCursor().getDisplayFrom());
		// 第一个stick为停盘的情况
		if (first.getHigh() == 0 && first.getLow() == 0) {

		} else {
			maxValue = first.getHigh();
			minValue = first.getLow();
		}

		for (int i = getDataCursor().getDisplayFrom(); i < getDataCursor().getDisplayTo(); i++) {
			IMeasurable stick = getChartData().get(i);
			
			if (stick.getLow() < minValue) {
				minValue = stick.getLow();
			}

			if (stick.getHigh() > maxValue) {
				maxValue = stick.getHigh();
			}

		}

		if (maxValue < minValue ){
			maxValue = 0;
			minValue = 0;
		}

		this.maxValue = maxValue;
		this.minValue = minValue;
	}

	protected void calcValueRangePaddingZero() {
		double maxValue = this.maxValue;
		double minValue = this.minValue;

		if ((long) maxValue > (long) minValue) {
			if ((maxValue - minValue) < 10 && minValue > 1) {
				this.maxValue = (long) (maxValue + 1);
				this.minValue = (long) (minValue - 1);
			} else {
				this.maxValue = (long) (maxValue + (maxValue - minValue) * 0.1);
				this.minValue = (long) (minValue - (maxValue - minValue) * 0.1);
				if (this.minValue < 0) {
					this.minValue = 0;
				}
			}
		} else if ((long) maxValue == (long) minValue) {
			if (maxValue <= 10 && maxValue > 1) {
				this.maxValue = maxValue + 1;
				this.minValue = minValue - 1;
			} else if (maxValue <= 100 && maxValue > 10) {
				this.maxValue = maxValue + 10;
				this.minValue = minValue - 10;
			} else if (maxValue <= 1000 && maxValue > 100) {
				this.maxValue = maxValue + 100;
				this.minValue = minValue - 100;
			} else if (maxValue <= 10000 && maxValue > 1000) {
				this.maxValue = maxValue + 1000;
				this.minValue = minValue - 1000;
			} else if (maxValue <= 100000 && maxValue > 10000) {
				this.maxValue = maxValue + 10000;
				this.minValue = minValue - 10000;
			} else if (maxValue <= 1000000 && maxValue > 100000) {
				this.maxValue = maxValue + 100000;
				this.minValue = minValue - 100000;
			} else if (maxValue <= 10000000 && maxValue > 1000000) {
				this.maxValue = maxValue + 1000000;
				this.minValue = minValue - 1000000;
			} else if (maxValue <= 100000000 && maxValue > 10000000) {
				this.maxValue = maxValue + 10000000;
				this.minValue = minValue - 10000000;
			}
		} else {
			this.maxValue = 0;
			this.minValue = 0;
		}

	}

	protected void calcValueRangeFormatForAxis() {
		// 修正最大值和最小值
		long rate = (long) (this.maxValue - this.minValue) / (simpleGrid.getLatitudeNum());
		String strRate = String.valueOf(rate);
		float first = Integer.parseInt(String.valueOf(strRate.charAt(0))) + 1.0f;
		if (first > 0 && strRate.length() > 1) {
			float second = Integer.parseInt(String.valueOf(strRate.charAt(1)));
			if (second < 5) {
				first = first - 0.5f;
			}
			rate = (long) (first * Math.pow(10, strRate.length() - 1));
		} else {
			rate = 1;
		}
		// 等分轴修正
		if (simpleGrid.getLatitudeNum() > 0
				&& (long) (this.maxValue - this.minValue)
						% (simpleGrid.getLatitudeNum() * rate) != 0) {
			// 最大值加上轴差
			this.maxValue = (long) this.maxValue
					+ (simpleGrid.getLatitudeNum() * rate)
					- ((long) (this.maxValue - this.minValue) % (simpleGrid.getLatitudeNum() * rate));
		}
	}

	protected void calcValueRange() {
		if (getChartData() != null && getChartData().size() > 0) {
			this.calcDataValueRange();
			this.calcValueRangePaddingZero();
		} else {
			this.maxValue = 0;
			this.minValue = 0;
		}
		this.calcValueRangeFormatForAxis();
		if (autoBalanceValueRange){
			this.balanceRange();
		}
	}

	protected void balanceRange(){
		// auto balance
		this.maxValue = Math.max(Math.abs(this.maxValue),Math.abs(this.minValue));
		this.minValue = -Math.max(Math.abs(this.maxValue),Math.abs(this.minValue));
	}

//	protected IDataCursor getDataCursor() = this;
	
	/** 
	 * <p>Constructor of DataGridChart</p>
	 * <p>DataGridChart类对象的构造函数</p>
	 * <p>DataGridChartのコンストラクター</p>
	 *
	 * @param context 
	 */
	public DataGridChart(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	/** 
	 * <p>Constructor of DataGridChart</p>
	 * <p>DataGridChart类对象的构造函数</p>
	 * <p>DataGridChartのコンストラクター</p>
	 *
	 * @param context
	 * @param attrs
	 * @param defStyle 
	 */
	public DataGridChart(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	/** 
	 * <p>Constructor of DataGridChart</p>
	 * <p>DataGridChart类对象的构造函数</p>
	 * <p>DataGridChartのコンストラクター</p>
	 *
	 * @param context
	 * @param attrs 
	 */
	public DataGridChart(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {

		super.onDraw(canvas);

		this.customLines.draw(canvas);
	}

	public boolean isNoneDisplayValue(double value){
		if (noneDisplayValue == null){
			return false;
		}
		if (noneDisplayValue.length == 0){
			return false;
		}
		for(int i = 0 ; i < noneDisplayValue.length; i++){
			if (value - noneDisplayValue[i] == 0){
				return true;
			}
		}
		return false;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @param value
	 * 
	 * @see cn.limc.androidcharts.view.GridChart#getAxisXGraduate(Object)
	 */
	@Override
	public String getAxisXGraduate(Object value) {
		float graduate = Float.valueOf(super.getAxisXGraduate(value));
		int index = (int) Math.floor(graduate * getDataCursor().getDataDisplayNumber());

		if (index >= getDataCursor().getDisplayNumber()) {
			index = getDataCursor().getDisplayNumber() - 1;
		} else if (index < 0) {
			index = 0;
		}

		return formatAxisXDegree(getChartData().get(index).getDate());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @param value
	 * 
	 * @see cn.limc.androidcharts.view.GridChart#getAxisYGraduate(Object)
	 */
	@Override
	public String getAxisYGraduate(Object value) {
		float graduate = Float.valueOf(super.getAxisYGraduate(value));
		return formatAxisYDegree(graduate * (maxValue - minValue) + minValue);
	}
	
	
	public String formatAxisYDegree(double value) {
		//TODO:格式改成属性
		//数据
		double displayValue = Math.floor(value) / dataMultiple;
		if(displayValue < 10000){
			return new DecimalFormat(axisYDecimalFormat).format(displayValue);
		}else if(displayValue < 100000000){
			return new DecimalFormat("#,##0.00").format(displayValue/10000) + "万";
		}else {
			return new DecimalFormat("#,##0.00").format(displayValue/100000000) + "亿";
		}
	}

	public String formatAxisYDegreePercent(double value , double midValue) {
		if (midValue == 0) return "";
		//数据
		double displayValue = (value - midValue) * 100/ midValue;
		if(displayValue < 0){
			return new DecimalFormat("#,##0.00").format(displayValue) + "%";
		}else {
			return new DecimalFormat("-#,##0.00").format(displayValue) + "%";
		}
	}

	public String formatAxisXDegree(long date) {
		try {
			Date dt = new SimpleDateFormat(axisXDateSourceFormat).parse(String.valueOf(date));
			return new SimpleDateFormat(axisXDateTargetFormat).format(dt);
		} catch (ParseException e) {
			return "";
		}
	}

	@Override
	public String calcAxisXGraduate() {
		long date = touchPointAxisXValue();
		return  date >0 ? formatAxisXDegree(date):"";
	}

	@Override
	public String calcAxisYGraduate() {
		return  formatAxisYDegree(this.touchPointAxisYValue());
	}

	@Override
	public long touchPointAxisXValue() {
		if (null == touchPoint) {
			return 0;
		}
		return getChartData().get(getSelectedIndex()).getDate();
	}

	@Override
	public double touchPointAxisYValue() {
		if (null == touchPoint) {
			return 0;
		}
		return (1 - (touchPoint.y - dataQuadrant.getPaddingStartY()) / dataQuadrant.getPaddingHeight())  * (maxValue - minValue) + minValue;
	}

	/**
	 * <p>
	 * get current selected data index
	 * </p>
	 * <p>
	 * 選択したスティックのインデックス
	 * </p>
	 * <p>
	 * 获取当前选中的柱条的index
	 * </p>
	 * 
	 * @return int
	 *         <p>
	 *         index
	 *         </p>
	 *         <p>
	 *         インデックス
	 *         </p>
	 *         <p>
	 *         index
	 *         </p>
	 */
	public int getSelectedIndex() {
		if (null == touchPoint) {
			return 0;
		}
		return calcSelectedIndex(touchPoint.x, touchPoint.y);
	}
	
	protected int calcSelectedIndex(float x ,float y) {
		if (!isValidTouchPoint(x,y)) {
			return 0;
		}
		float graduate = Float.valueOf(super.getAxisXGraduate(x));
		int index = (int) Math.floor(graduate * getDataCursor().getDataDisplayNumber());

		if (index >= getDataCursor().getDisplayNumber()) {
			index = getDataCursor().getDisplayNumber() - 1;
		} else if (index < 0) {
			index = 0;
		}
		
		return getDataCursor().getDisplayFrom() + index;
	}	
	
	/**
	 * @return the dataMultiple
	 */
	public int getDataMultiple() {
		return dataMultiple;
	}

	/**
	 * @param dataMultiple the dataMultiple to set
	 */
	public void setDataMultiple(int dataMultiple) {
		this.dataMultiple = dataMultiple;
	}

	/**
	 * @return the axisYDecimalFormat
	 */
	public String getAxisYDecimalFormat() {
		return axisYDecimalFormat;
	}

	/**
	 * @param axisYDecimalFormat the axisYDecimalFormat to set
	 */
	public void setAxisYDecimalFormat(String axisYDecimalFormat) {
		this.axisYDecimalFormat = axisYDecimalFormat;
	}

	/**
	 * @return the axisXDateTargetFormat
	 */
	public String getAxisXDateTargetFormat() {
		return axisXDateTargetFormat;
	}

	/**
	 * @param axisXDateTargetFormat the axisXDateTargetFormat to set
	 */
	public void setAxisXDateTargetFormat(String axisXDateTargetFormat) {
		this.axisXDateTargetFormat = axisXDateTargetFormat;
	}

	/**
	 * @return the axisXDateSourceFormat
	 */
	public String getAxisXDateSourceFormat() {
		return axisXDateSourceFormat;
	}

	/**
	 * @param axisXDateSourceFormat the axisXDateSourceFormat to set
	 */
	public void setAxisXDateSourceFormat(String axisXDateSourceFormat) {
		this.axisXDateSourceFormat = axisXDateSourceFormat;
	}
	
	/**
	 * @return the maxValue
	 */
	public double getMaxValue() {
		return maxValue;
	}

	/**
	 * @param maxValue
	 *            the maxValue to set
	 */
	public void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
	}

	/**
	 * @return the minValue
	 */
	public double getMinValue() {
		return minValue;
	}

	/**
	 * @param minValue
	 *            the minValue to set
	 */
	public void setMinValue(double minValue) {
		this.minValue = minValue;
	}

	public double getMaxDataValue() {
		return maxDataValue;
	}

	public void setMaxDataValue(double maxDataValue) {
		this.maxDataValue = maxDataValue;
	}

	public double getMinDataValue() {
		return minDataValue;
	}

	public void setMinDataValue(double minDataValue) {
		this.minDataValue = minDataValue;
	}

	public double getMidValue() {
		return midValue;
	}

	public void setMidValue(double midValue) {
		this.midValue = midValue;
	}

	/**
	 * @return the autoCalcValueRange
	 */
	public boolean isAutoCalcValueRange() {
		return autoCalcValueRange;
	}

	/**
	 * @param autoCalcValueRange
	 *            the autoCalcValueRange to set
	 */
	public void setAutoCalcValueRange(boolean autoCalcValueRange) {
		this.autoCalcValueRange = autoCalcValueRange;
	}


	public int getDisplayFrom() {
		return getDataCursor().getDisplayFrom();
	}

	/* (non-Javadoc)
	 *
	 * @return
	 * @see cn.limc.androidcharts.view.IDataCursor#getDisplayNumber()
	 */
	public int getDisplayNumber() {
		return getDataCursor().getDisplayNumber();
	}

	/* (non-Javadoc)
	 *
	 * @return
	 * @see cn.limc.androidcharts.view.IDataCursor#displayTo()
	 */
	public int getDisplayTo() {
		return getDataCursor().getDisplayTo();
	}

	public boolean forward(int step){
		return getDataCursor().forward(step);
	}

	public boolean backward(int step){
		return getDataCursor().backward(step);
	}

	public boolean stretch(int step){
		return getDataCursor().stretch(step);
	}

	public boolean shrink(int step){
		return getDataCursor().shrink(step);
	}

	/* (non-Javadoc)
	 *
	 * @param getDisplayFrom()
	 * @see cn.limc.androidcharts.common.IDataCursor#setgetDisplayFrom()(int)
	 */
	public void setDisplayFrom(int displayFrom) {
		getDataCursor().setDisplayFrom(displayFrom);

	}

	/* (non-Javadoc)
	 *
	 * @param getDisplayNumber()
	 * @see cn.limc.androidcharts.common.IDataCursor#setgetDisplayNumber()(int)
	 */
	public void setDisplayNumber(int displayNumber) {
		getDataCursor().setDisplayNumber(displayNumber);
	}

	/* (non-Javadoc)
    *
    * @param mingetDisplayNumber()
    * @see cn.limc.androidcharts.common.IDataCursor#setMingetDisplayNumber()(int)
    */
	public void setMinDisplayNumber(int minDisplayNumber) {
		getDataCursor().setMinDisplayNumber(minDisplayNumber);
	}

	/* (non-Javadoc)
	 *
	 * @return
	 * @see cn.limc.androidcharts.common.IDataCursor#getMaxDisplayNumber()
	 */
	public int getMaxDisplayNumber() {
		return getDataCursor().getMaxDisplayNumber();
	}

	/* (non-Javadoc)
    *
    * @param mingetDisplayNumber()
    * @see cn.limc.androidcharts.common.IDataCursor#setMaxDisplayNumber()(int)
    */
	public void setMaxDisplayNumber(int maxDisplayNumber) {
		getDataCursor().setMaxDisplayNumber(maxDisplayNumber);
	}

	/* (non-Javadoc)
	 *
	 * @return
	 * @see cn.limc.androidcharts.common.IDataCursor#getMinDisplayNumber()
	 */
	public int getMinDisplayNumber() {
		return getDataCursor().getMinDisplayNumber();
	}

	/* (non-Javadoc)
	 *
	 * @return
	 * @see cn.limc.androidcharts.common.IDataCursor#getMinDisplayNumber()
	 */
	public int getDataDisplayNumber() {
		return getDataCursor().getDataDisplayNumber();
	}


	/**
	 * @return the bindCrossLinesToStick
	 */
	public int getBindCrossLinesToStick() {
		return this.crossLines.getBindCrossLinesToStick();
	}

	/**
	 * @param bindCrossLinesToStick the bindCrossLinesToStick to set
	 */
	public void setBindCrossLinesToStick(int bindCrossLinesToStick) {
		this.crossLines.setBindCrossLinesToStick(bindCrossLinesToStick);
	}

	/**
	 * @return the maxPointNum
	 */
	@Deprecated
	public int getMaxPointNum() {
		return getDisplayNumber();
	}

	/**
	 * @param maxPointNum
	 *            the maxPointNum to set
	 */
	@Deprecated
	public void setMaxPointNum(int maxPointNum) {
		setDisplayNumber(maxPointNum);
	}


	public float[] getNoneDisplayValue() {
		return noneDisplayValue;
	}

	public void setNoneDisplayValue(float[] noneDisplayValue) {
		this.noneDisplayValue = noneDisplayValue;
	}

	public boolean isAutoBalanceValueRange() {
		return autoBalanceValueRange;
	}

	public void setAutoBalanceValueRange(boolean autoBalanceValueRange) {
		this.autoBalanceValueRange = autoBalanceValueRange;
	}

	public ITouchedIndexListener getTouchedIndexListener() {
		return touchedIndexListener;
	}

	public void setTouchedIndexListener(ITouchedIndexListener touchedIndexListener) {
		this.touchedIndexListener = touchedIndexListener;
	}

	public ITouchedValueListener getTouchedValueListener() {
		return touchedValueListener;
	}

	public void setTouchedValueListener(ITouchedValueListener touchedValueListener) {
		this.touchedValueListener = touchedValueListener;
	}

	public boolean isAutoCalcLongitudeTitle() {
		return autoCalcLongitudeTitle;
	}

	public void setAutoCalcLongitudeTitle(boolean autoCalcLongitudeTitle) {
		this.autoCalcLongitudeTitle = autoCalcLongitudeTitle;
	}

	public boolean isAutoCalcLatitudeTitle() {
		return autoCalcLatitudeTitle;
	}

	public void setAutoCalcLatitudeTitle(boolean autoCalcLatitudeTitle) {
		this.autoCalcLatitudeTitle = autoCalcLatitudeTitle;
	}

	public CustomLines getCustomLines() {
		return customLines;
	}

	public void setCustomLines(CustomLines customLines) {
		this.customLines = customLines;
	}

	public boolean isDisplayCustomLines() {
		return displayCustomLines;
	}

	public void setDisplayCustomLines(boolean displayCustomLines) {
		this.displayCustomLines = displayCustomLines;
	}

	public float computeValueY(float value){
		return (float)((1f - (value - minValue)
				/ (maxValue - minValue))
				* (dataQuadrant.getPaddingHeight()) + dataQuadrant.getPaddingStartY());
	}

	public double computeValueY(double value){
		return ((1f - (value - minValue)
				/ (maxValue - minValue))
				* (dataQuadrant.getPaddingHeight()) + dataQuadrant.getPaddingStartY());
	}

	public double computeY2Value(double pty){
		return (1f - (pty - dataQuadrant.getPaddingStartY()) / dataQuadrant.getPaddingHeight())
				* (maxValue - minValue)
				+ minValue;
	}
}
