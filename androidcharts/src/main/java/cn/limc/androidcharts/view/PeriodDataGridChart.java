/*
 * PeriodDataGridChart.java
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

import java.util.ArrayList;
import java.util.List;

import cn.limc.androidcharts.common.ICrossLines;
import cn.limc.androidcharts.common.IFlexableGrid;
import cn.limc.androidcharts.common.IGrid;
import cn.limc.androidcharts.entity.IMeasurable;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.view.MotionEvent;

/** 
 * <p>en</p>
 * <p>jp</p>
 * <p>cn</p>
 *
 * @author limc 
 * @version v1.0 2014/06/19 14:44:11 
 *  
 */
public abstract class PeriodDataGridChart extends DataGridChart {
	

	public static final int DEFAULT_ALIGN_TYPE = IFlexableGrid.ALIGN_TYPE_CENTER;

	
	protected int gridAlignType = DEFAULT_ALIGN_TYPE;


	/** 
	 * <p>Constructor of PeriodDataGridChart</p>
	 * <p>PeriodDataGridChart类对象的构造函数</p>
	 * <p>PeriodDataGridChartのコンストラクター</p>
	 *
	 * @param context 
	 */
	public PeriodDataGridChart(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	/** 
	 * <p>Constructor of PeriodDataGridChart</p>
	 * <p>PeriodDataGridChart类对象的构造函数</p>
	 * <p>PeriodDataGridChartのコンストラクター</p>
	 *
	 * @param context
	 * @param attrs
	 * @param defStyle 
	 */
	public PeriodDataGridChart(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	/** 
	 * <p>Constructor of PeriodDataGridChart</p>
	 * <p>PeriodDataGridChart类对象的构造函数</p>
	 * <p>PeriodDataGridChartのコンストラクター</p>
	 *
	 * @param context
	 * @param attrs 
	 */
	public PeriodDataGridChart(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * <p>
	 * initialize degrees on X axis
	 * </p>
	 * <p>
	 * X軸の目盛を初期化
	 * </p>
	 * <p>
	 * 初始化X轴的坐标值
	 * </p>
	 */
	protected void initAxisX() {
		if (this.autoCalcLongitudeTitle == false) {
			return;
		}
		List<String> titleX = new ArrayList<String>();
		if (null != getChartData() && getChartData().size() > 0) {
			float average = getDataDisplayNumber() / simpleGrid.getLongitudeNum();
			for (int i = 0; i < simpleGrid.getLongitudeNum(); i++) {
				int index = getDisplayFrom() + (int) Math.floor(i * average);
				if (index > getDisplayTo() - 1) {
					index = getDisplayTo() - 1;
				}
				titleX.add(formatAxisXDegree(getChartData().get(index).getDate()));
			}
			titleX.add(formatAxisXDegree(getChartData().get(getDisplayTo() - 1).getDate()));
		}
		simpleGrid.setLongitudeTitles(titleX);
	}

	/**
	 * <p>
	 * initialize degrees on Y axis
	 * </p>
	 * <p>
	 * Y軸の目盛を初期化
	 * </p>
	 * <p>
	 * 初始化Y轴的坐标值
	 * </p>
	 */
	protected void initAxisY() {
		if (this.autoBalanceValueRange) {
			this.calcValueRange();
		}

		//test for axis color
//		this.midValue = (this.maxValue + this.minValue) / 2.0f;

		initAxisYLeft();
		// midValue != 0
		if (this.midValue != 0) {
			initAxisYLeftColor();
		}

		initAxisYRight();
		// midValue != 0
		if (this.midValue != 0) {
			initAxisYRightColor();
		}
	}


	protected void initAxisYLeftColor() {
		List<Integer> titleYColor = new ArrayList<Integer>();

		double average = (maxValue - minValue) / simpleGrid.getLatitudeNum();
		;
		// calculate degrees on Y axis
		for (int i = 0; i < simpleGrid.getLatitudeNum(); i++) {
			double valueToDisplay = minValue + i * average;
			if (valueToDisplay - this.midValue > 0){
				titleYColor.add(Color.RED);
			}else if(valueToDisplay - this.midValue == 0){
				titleYColor.add(Color.LTGRAY);
			}else {
				titleYColor.add(Color.GREEN);
			}
		}

		if (maxValue - this.midValue > 0){
			titleYColor.add(Color.RED);
		}else if(maxValue - this.midValue == 0){
			titleYColor.add(Color.LTGRAY);
		}else {
			titleYColor.add(Color.GREEN);
		}
		simpleGrid.setLeftLatitudeTitleColors(titleYColor);
	}

	protected void initAxisYRightColor() {
		List<Integer> titleYColor = new ArrayList<Integer>();

		double average = (maxValue - minValue) / simpleGrid.getLatitudeNum();
		;
		// calculate degrees on Y axis
		for (int i = 0; i < simpleGrid.getLatitudeNum(); i++) {
			double valueToDisplay = minValue + i * average;
			if (valueToDisplay - this.midValue > 0){
				titleYColor.add(Color.RED);
			}else if(valueToDisplay - this.midValue == 0){
				titleYColor.add(Color.LTGRAY);
			}else {
				titleYColor.add(Color.GREEN);
			}
		}

		if (maxValue - this.midValue > 0){
			titleYColor.add(Color.RED);
		}else if(maxValue - this.midValue == 0){
			titleYColor.add(Color.LTGRAY);
		}else {
			titleYColor.add(Color.GREEN);
		}
		simpleGrid.setRightLatitudeTitleColors(titleYColor);
	}

	protected void initAxisYLeft() {
		List<String> titleYLeft = new ArrayList<String>();

		double average = (maxValue - minValue) / simpleGrid.getLatitudeNum();
		;
		// calculate degrees on Y axis
		for (int i = 0; i < simpleGrid.getLatitudeNum(); i++) {
			double valueToDisplay = minValue + i * average;
			String valueLeft;
			if (IGrid.TITLE_FORMAT_DECIMAL==simpleGrid.getLeftLatitudeTitlesFormat()) {
				valueLeft = formatAxisYDegree(valueToDisplay);
			}else if (IGrid.TITLE_FORMAT_PERCENT==simpleGrid.getLeftLatitudeTitlesFormat()) {
				valueLeft = formatAxisYDegreePercent(valueToDisplay , this.midValue);
			}else{
				valueLeft = formatAxisYDegree(valueToDisplay);
			}

			titleYLeft.add(valueLeft);
		}
		// calculate last degrees by use max value
		String valueLeft;
		if (IGrid.TITLE_FORMAT_DECIMAL==simpleGrid.getLeftLatitudeTitlesFormat()) {
			valueLeft = formatAxisYDegree(maxValue);
		}else if (IGrid.TITLE_FORMAT_PERCENT==simpleGrid.getLeftLatitudeTitlesFormat()) {
			valueLeft = formatAxisYDegreePercent(maxValue , this.midValue);
		}else{
			valueLeft = formatAxisYDegree(maxValue);
		}

		titleYLeft.add(valueLeft);
		simpleGrid.setLeftLatitudeTitles(titleYLeft);
	}

	protected void initAxisYRight() {
		List<String> titleYRight = new ArrayList<String>();

		double average = (maxValue - minValue) / simpleGrid.getLatitudeNum();
		;
		// calculate degrees on Y axis
		for (int i = 0; i < simpleGrid.getLatitudeNum(); i++) {
			double valueToDisplay = minValue + i * average;
			String valueRight;
			if (IGrid.TITLE_FORMAT_DECIMAL==simpleGrid.getRightLatitudeTitlesFormat()) {
				valueRight = formatAxisYDegree(valueToDisplay);
			}else if (IGrid.TITLE_FORMAT_PERCENT==simpleGrid.getRightLatitudeTitlesFormat()) {
				valueRight = formatAxisYDegreePercent(valueToDisplay , this.midValue);
			}else{
				valueRight = formatAxisYDegree(valueToDisplay);
			}
			titleYRight.add(valueRight);
		}

		String valueRight;
		if (IGrid.TITLE_FORMAT_DECIMAL==simpleGrid.getRightLatitudeTitlesFormat()) {
			valueRight = formatAxisYDegree(maxValue);
		}else if (IGrid.TITLE_FORMAT_PERCENT==simpleGrid.getRightLatitudeTitlesFormat()) {
			valueRight = formatAxisYDegreePercent(maxValue , this.midValue);
		}else{
			valueRight = formatAxisYDegree(maxValue);
		}

		titleYRight.add(valueRight);

		simpleGrid.setRightLatitudeTitles(titleYRight);
	}



	public float longitudePostOffset(){
		if (gridAlignType == IFlexableGrid.ALIGN_TYPE_CENTER) {
			float stickWidth = dataQuadrant.getPaddingWidth() / getDataDisplayNumber();
			return (this.dataQuadrant.getPaddingWidth() - stickWidth)/ (simpleGrid.getLongitudeTitles().size() - 1);
	    }else{
			return this.dataQuadrant.getPaddingWidth()/ (simpleGrid.getLongitudeTitles().size() - 1);
	    }
	}
	
	public float longitudeOffset(){
		if (gridAlignType ==IFlexableGrid.ALIGN_TYPE_CENTER) {
			float stickWidth = dataQuadrant.getPaddingWidth() / getDataDisplayNumber();
			return dataQuadrant.getPaddingStartX() + stickWidth / 2;
		}else{
			return dataQuadrant.getPaddingStartX();
		}
	}


	/* (non-Javadoc)
	 *
	 * @see cn.limc.androidcharts.event.ITouchable#touchDown()
	 */
	@Override
	public void touchDown(PointF pt) {
		this.touchPoint =  calcTouchedPoint(pt.x,pt.y);
		
		this.touchedIndexChanged();
		
		this.postInvalidate();
	}

	/* (non-Javadoc)
	 *
	 * @see cn.limc.androidcharts.event.ITouchable#touchMoved()
	 */
	@Override
	public void touchMoved(PointF pt) {
		this.touchPoint =  calcTouchedPoint(pt.x,pt.y);
		
		this.touchedIndexChanged();
		
		this.postInvalidate();
	}

	/* (non-Javadoc)
	 *
	 * @see cn.limc.androidcharts.event.ITouchable#touchUp()
	 */
	@Override
	public void touchUp(PointF pt) {
		this.touchPoint =  calcTouchedPoint(pt.x,pt.y);
		
//		this.touchedIndexChanged();
		
		this.postInvalidate();
	}

	/* (non-Javadoc)
	 *
	 * @see cn.limc.androidcharts.event.ITouchable#touchDown()
	 */
	@Override
	public void longPressDown(PointF pt) {
		this.touchPoint = calcTouchedPoint(pt.x,pt.y);

		this.crossLines.setDisplayCrossXOnTouch(true);
		this.crossLines.setDisplayCrossYOnTouch(true);
		
		this.touchedIndexChanged();
		
		this.postInvalidate();
	}

	/* (non-Javadoc)
	 *
	 * @see cn.limc.androidcharts.event.ITouchable#touchMoved()
	 */
	@Override
	public void longPressMoved(PointF pt) {
		this.touchPoint = calcTouchedPoint(pt.x,pt.y);
		
		this.touchedIndexChanged();
		
		this.postInvalidate();
	}

	/* (non-Javadoc)
	 *
	 * @see cn.limc.androidcharts.event.ITouchable#touchUp()
	 */
	@Override
	public void longPressUp(PointF pt) {
		this.touchPoint =  calcTouchedPoint(pt.x,pt.y);;
		this.crossLines.setDisplayCrossXOnTouch(false);
		this.crossLines.setDisplayCrossYOnTouch(false);
		
//		this.touchedIndexChanged();
		
		this.postInvalidate();
	}
	
	protected void touchedIndexChanged(){
		int index = getSelectedIndex();
		if(index >= getDataCursor().getDisplayFrom() && index < getDataCursor().getDisplayTo()){
			if(this.touchedIndexListener != null){
				this.touchedIndexListener.onSelectedIndexChanged(this, index);
			}
		}
	}
	
	protected PointF calcTouchedPoint(float x ,float y) {
		if (!isValidTouchPoint(x,y)) {
			return new PointF(0,0);
		}
		if (crossLines.getBindCrossLinesToStick() == ICrossLines.BIND_TO_TYPE_NONE) {
			return new PointF(x, y);
		} else if (crossLines.getBindCrossLinesToStick() == ICrossLines.BIND_TO_TYPE_BOTH) {
			PointF bindPointF = calcBindPoint(x, y);
			return bindPointF;
		} else if (crossLines.getBindCrossLinesToStick() == ICrossLines.BIND_TO_TYPE_HIRIZIONAL) {
			PointF bindPointF = calcBindPoint(x, y);
			return new PointF(bindPointF.x, y);
		} else if (crossLines.getBindCrossLinesToStick() == ICrossLines.BIND_TO_TYPE_VERTICAL) {
			PointF bindPointF = calcBindPoint(x, y);
			return new PointF(x, bindPointF.y);
		} else {
			return new PointF(x, y);
		}	
	}
	
	protected PointF calcBindPoint(float x ,float y) {
		float calcX = 0;
		float calcY = 0;
		
		int index = calcSelectedIndex(x,y);
		
		float stickWidth = dataQuadrant.getPaddingWidth() / getDataDisplayNumber();
		if (index > getDisplayFrom() && index < getDisplayTo() - 1) {
			IMeasurable stick = getChartData().get(index);
			calcY = (float) ((1f - (stick.getHigh() - minValue)
					/ (maxValue - minValue))
					* (dataQuadrant.getPaddingHeight()) + dataQuadrant.getPaddingStartY());
			calcX = dataQuadrant.getPaddingStartX() + stickWidth * (index - getDisplayFrom()) + stickWidth / 2;
		}
		
		return new PointF(calcX,calcY);
	}
	/**
	 * <p>
	 * calculate the distance between two touch points
	 * </p>
	 * <p>
	 * 複数タッチしたポイントの距離
	 * </p>
	 * <p>
	 * 计算两点触控时两点之间的距离
	 * </p>
	 * 
	 * @param event
	 * @return float
	 *         <p>
	 *         distance
	 *         </p>
	 *         <p>
	 *         距離
	 *         </p>
	 *         <p>
	 *         距离
	 *         </p>
	 */
	protected float calcDistance(MotionEvent event) {
		if(event.getPointerCount() <= 1) {
			return 0f;
		}else{
			float x = event.getX(0) - event.getX(1);
			float y = event.getY(0) - event.getY(1);
			return (float) Math.sqrt(x * x + y * y);
		}
	}
	
	/**
	 * @return the gridAlignType
	 */
	public int getStickAlignType() {
		return gridAlignType;
	}

	/**
	 * @param gridAlignType the gridAlignType to set
	 */
	public void setStickAlignType(int stickAlignType) {
		this.gridAlignType = stickAlignType;
	}
}
