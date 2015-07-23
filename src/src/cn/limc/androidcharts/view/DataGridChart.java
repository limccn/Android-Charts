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

import cn.limc.androidcharts.common.IDataCursor;
import cn.limc.androidcharts.common.SimpleDataCursor;
import cn.limc.androidcharts.degree.DateTimeDegree;
import cn.limc.androidcharts.degree.DecimalDegree;
import cn.limc.androidcharts.degree.IDegree;
import cn.limc.androidcharts.degree.IHasValueRange;
import cn.limc.androidcharts.degree.StickValueRangeCalc;
import cn.limc.androidcharts.degree.ValueRangeCalc;
import cn.limc.androidcharts.entity.IChartData;
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
	
//	public static final boolean DEFAULT_AUTO_CALC_VALUE_RANGE = true;
//	
//	public static final int DEFAULT_DATA_MULTIPLE = 1;
//	public static final String DEFAULT_AXIS_Y_DECIMAL_FORMAT = "#,##0";
//	public static final String DEFAULT_AXIS_X_DATE_TARGET_FORMAT = "yyyy/MM/dd";
//	public static final String DEFAULT_AXIS_X_DATE_SOURCE_FORMAT = "yyyyMMdd";
//	
//	protected int dataMultiple =  DEFAULT_DATA_MULTIPLE;
	
//	protected String axisYDecimalFormat = DEFAULT_AXIS_Y_DECIMAL_FORMAT;
//	protected String axisXDateTargetFormat = DEFAULT_AXIS_X_DATE_TARGET_FORMAT;
//	protected String axisXDateSourceFormat = DEFAULT_AXIS_X_DATE_SOURCE_FORMAT;
	
	protected IDataCursor dataCursor = new SimpleDataCursor(this);
	protected IDegree axisYDegree = new DecimalDegree(this);
	protected IDegree axisXDegree = new DateTimeDegree(this);
    protected IHasValueRange dataRange = new StickValueRangeCalc(this);
	
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

		return axisXDegree.valueForDegree(stickData.get(index).getDate());
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
		return axisYDegree.valueForDegree(graduate * dataRange.getValueRange() + dataRange.getMinValue());
	}
	
	
//	public String formatAxisYDegree(double value) {
//		return new DecimalFormat(axisYDecimalFormat).format(Math.floor(value)/dataMultiple);
//	}
//	
//	public String formatAxisXDegree(int date) {
//		try {
//			Date dt = new SimpleDateFormat(axisXDateSourceFormat).parse(String.valueOf(date));
//			return new SimpleDateFormat(axisXDateTargetFormat).format(dt);
//		} catch (ParseException e) {
//			return "";
//		}
//	}
	
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
		return dataRange.getDataMultiple();
	}

	/**
	 * @param dataMultiple the dataMultiple to set
	 */
	public void setDataMultiple(int dataMultiple) {
		((ValueRangeCalc)this.dataRange).setDataMultiple(dataMultiple);
	}

	/**
	 * @return the axisYDecimalFormat
	 */
	@Deprecated
	public String getAxisYDecimalFormat() {
		return ((DecimalDegree)axisYDegree).getTargetFormat();
	}

	/**
	 * @param axisYDecimalFormat the axisYDecimalFormat to set
	 */
	@Deprecated
	public void setAxisYDecimalFormat(String axisYDecimalFormat) {
	    ((DecimalDegree)axisYDegree).setTargetFormat(axisYDecimalFormat);
	}

	/**
	 * @return the axisXDateTargetFormat
	 */
	@Deprecated
	public String getAxisXDateTargetFormat() {
	    return ((DateTimeDegree)axisXDegree).getTargetFormat();
	}

	/**
	 * @param axisXDateTargetFormat the axisXDateTargetFormat to set
	 */
	@Deprecated
	public void setAxisXDateTargetFormat(String axisXDateTargetFormat) {
	    ((DateTimeDegree)axisXDegree).setTargetFormat(axisXDateTargetFormat);
	}

	/**
	 * @return the axisXDateSourceFormat
	 */
	@Deprecated
	public String getAxisXDateSourceFormat() {
	    return ((DateTimeDegree)axisXDegree).getSourceFormat();
	}

	/**
	 * @param axisXDateSourceFormat the axisXDateSourceFormat to set
	 */
	@Deprecated
	public void setAxisXDateSourceFormat(String axisXDateSourceFormat) {
	    ((DateTimeDegree)axisXDegree).setSourceFormat(axisXDateSourceFormat);
	}
	
	/**
	 * @return the maxValue
	 */
	public double getMaxValue() {
		return dataRange.getMaxValue();
	}

	/**
	 * @param maxValue
	 *            the maxValue to set
	 */
	public void setMaxValue(double maxValue) {
	    ((ValueRangeCalc)this.dataRange).setMaxValue(maxValue);
	}

	/**
	 * @return the minValue
	 */
	public double getMinValue() {
		return dataRange.getMinValue();
	}

	/**
	 * @param minValue
	 *            the minValue to set
	 */
	public void setMinValue(double minValue) {
	    ((ValueRangeCalc)this.dataRange).setMinValue(minValue);
	}

	/**
	 * @return the autoCalcValueRange
	 */
	public boolean isAutoCalcValueRange() {
		return this.dataRange.isAutoCalcValueRange();
	}

	/**
	 * @param autoCalcValueRange
	 *            the autoCalcValueRange to set
	 */
	public void setAutoCalcValueRange(boolean autoCalcValueRange) {
	    ((ValueRangeCalc)this.dataRange).setAutoCalcValueRange(autoCalcValueRange);
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

    /**
     * @return the dataRange
     */
    public IHasValueRange getDataRange() {
        return dataRange;
    }

    /**
     * @param dataRange the dataRange to set
     */
    public void setDataRange(IHasValueRange dataRange) {
        this.dataRange = dataRange;
    }

    /**
     * @return the axisYDegree
     */
    public IDegree getAxisYDegree() {
        return axisYDegree;
    }

    /**
     * @param axisYDegree the axisYDegree to set
     */
    public void setAxisYDegree(IDegree axisYDegree) {
        this.axisYDegree = axisYDegree;
    }

    /**
     * @return the axisXDegree
     */
    public IDegree getAxisXDegree() {
        return axisXDegree;
    }

    /**
     * @param axisXDegree the axisXDegree to set
     */
    public void setAxisXDegree(IDegree axisXDegree) {
        this.axisXDegree = axisXDegree;
    }
}
