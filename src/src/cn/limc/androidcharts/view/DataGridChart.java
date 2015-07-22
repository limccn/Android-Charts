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

import cn.limc.androidcharts.common.IDataCursor;
import cn.limc.androidcharts.common.SimpleDataCursor;
import cn.limc.androidcharts.entity.IChartData;
import cn.limc.androidcharts.entity.IMeasurable;
import cn.limc.androidcharts.entity.IStickEntity;

import android.content.Context;
import android.graphics.Canvas;
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
public abstract class DataGridChart extends GridChart {
	
	public static final boolean DEFAULT_AUTO_CALC_VALUE_RANGE = true;
	
	public static final int DEFAULT_DATA_MULTIPLE = 1;
	public static final String DEFAULT_AXIS_Y_DECIMAL_FORMAT = "#,##0";
	public static final String DEFAULT_AXIS_X_DATE_TARGET_FORMAT = "yyyy/MM/dd";
	public static final String DEFAULT_AXIS_X_DATE_SOURCE_FORMAT = "yyyyMMdd";
	
	protected int dataMultiple =  DEFAULT_DATA_MULTIPLE;
	
	protected String axisYDecimalFormat = DEFAULT_AXIS_Y_DECIMAL_FORMAT;
	protected String axisXDateTargetFormat = DEFAULT_AXIS_X_DATE_TARGET_FORMAT;
	protected String axisXDateSourceFormat = DEFAULT_AXIS_X_DATE_SOURCE_FORMAT;
	
	protected IDataCursor dataCursor = new SimpleDataCursor(this);
	
	/**
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

	protected boolean autoCalcValueRange = DEFAULT_AUTO_CALC_VALUE_RANGE;
	
	
	protected void calcDataValueRange() {
		double maxValue = Double.MIN_VALUE;
		double minValue = Double.MAX_VALUE;
		IMeasurable first = this.stickData.get(dataCursor.getDisplayFrom());
		// 第一个stick为停盘的情况
		if (first.getHigh() == 0 && first.getLow() == 0) {

		} else {
			maxValue = first.getHigh();
			minValue = first.getLow();
		}

		for (int i = dataCursor.getDisplayFrom(); i < dataCursor.getDisplayTo(); i++) {
			IMeasurable stick;
			stick = this.stickData.get(i);
			
			if (stick.getLow() < minValue) {
				minValue = stick.getLow();
			}

			if (stick.getHigh() > maxValue) {
				maxValue = stick.getHigh();
			}

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
		if (this.stickData != null && this.stickData.size() > 0) {
			this.calcDataValueRange();
			this.calcValueRangePaddingZero();
		} else {
			this.maxValue = 0;
			this.minValue = 0;
		}
		this.calcValueRangeFormatForAxis();
	}
	
	
	/**
	 * <p>
	 * data to draw sticks
	 * </p>
	 * <p>
	 * スティックを書く用データ
	 * </p>
	 * <p>
	 * 绘制柱条用的数据
	 * </p>
	 */
	protected IChartData<IStickEntity> stickData;
	
//	protected IDataCursor dataCursor = this;
	
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
	
	protected void onDraw(Canvas canvas) {		
		super.onDraw(canvas);
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
		int index = (int) Math.floor(graduate * dataCursor.getDisplayNumber());

		if (index >= dataCursor.getDisplayNumber()) {
			index = dataCursor.getDisplayNumber() - 1;
		} else if (index < 0) {
			index = 0;
		}

		return formatAxisXDegree(stickData.get(index).getDate());
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
		return new DecimalFormat(axisYDecimalFormat).format(Math.floor(value)/dataMultiple);
	}
	
	public String formatAxisXDegree(int date) {
		try {
			Date dt = new SimpleDateFormat(axisXDateSourceFormat).parse(String.valueOf(date));
			return new SimpleDateFormat(axisXDateTargetFormat).format(dt);
		} catch (ParseException e) {
			return "";
		}
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
		int index = (int) Math.floor(graduate * dataCursor.getDisplayNumber());

		if (index >= dataCursor.getDisplayNumber()) {
			index = dataCursor.getDisplayNumber() - 1;
		} else if (index < 0) {
			index = 0;
		}
		
		return dataCursor.getDisplayFrom() + index;
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

    /**
     * @return the dataCursor
     */
    public IDataCursor getDataCursor() {
        return dataCursor;
    }

    /**
     * @param dataCursor the dataCursor to set
     */
    public void setDataCursor(IDataCursor dataCursor) {
        this.dataCursor = dataCursor;
    }

    /**
     * @return the stickData
     */
    public IChartData<IStickEntity> getStickData() {
        return stickData;
    }

    /**
     * @param stickData the stickData to set
     */
    public void setStickData(IChartData<IStickEntity> stickData) {
        this.stickData = stickData;
    }
}
