/*
 * LineChart.java
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

package cn.limc.androidcharts.diagram;

import cn.limc.androidcharts.series.ChartDataTable;
import cn.limc.androidcharts.series.IMeasurable;
import cn.limc.androidcharts.series.LineEntity;
import cn.limc.androidcharts.shape.Points;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;



/**
 * 
 * <p>
 * LineChart is a kind of graph that draw some lines on a GridChart
 * </p>
 * <p>
 * LineChartはGridChartの表面でラインを書いたラインチャードです。
 * </p>
 * <p>
 * LineChart是在GridChart上绘制一条或多条线条的图
 * </p>
 * 
 * @author limc
 * @version v1.0 2011/05/30 14:23:53
 * @see GridChart
 */
public class LineChart extends StickChart {
//	public static final int DEFAULT_LINE_ALIGN_TYPE = IFlexableGrid.ALIGN_TYPE_JUSTIFY;
//	/**
//	 * <p>
//	 * mData to draw lines
//	 * </p>
//	 * <p>
//	 * ラインを書く用データ
//	 * </p>
//	 * <p>
//	 * 绘制线条用的数据
//	 * </p>
//	 */
//	private List<LineEntity<DateValueEntity<Integer,Double>>> linesData;
//
//	/**
//	 * <p>
//	 * max points of a single line
//	 * </p>
//	 * <p>
//	 * ラインの最大ポイント数
//	 * </p>
//	 * <p>
//	 * 线条的最大表示点数
//	 * </p>
//	 */
//	private int maxPointNum;
//
//	/**
//	 * <p>
//	 * min value of Y axis
//	 * </p>
//	 * <p>
//	 * Y軸の最小値
//	 * </p>
//	 * <p>
//	 * Y的最小表示值
//	 * </p>
//	 */
//	private double minValue;
//
//	/**
//	 * <p>
//	 * max value of Y axis
//	 * </p>
//	 * <p>
//	 * Y軸の最大値
//	 * </p>
//	 * <p>
//	 * Y的最大表示值
//	 * </p>
//	 */
//	private double maxValue;
//	
//	private int lineAlignType = DEFAULT_LINE_ALIGN_TYPE;
//
//	public static final boolean DEFAULT_AUTO_CALC_VALUE_RANGE = true;
//	private boolean autoCalcValueRange = DEFAULT_AUTO_CALC_VALUE_RANGE;
//	
//    protected OnZoomGestureListener onZoomListener = new OnZoomListener(){};
//    protected GestureDetector zoomGestureDetector = new ZoomGestureDetector(this, onZoomListener);

	/*
	 * (non-Javadoc)
	 * 
	 * @param context
	 * 
	 * @see cn.limc.androidcharts.diagram.GridChart#GridChart(Context)
	 */
	public LineChart(Context context) {
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
	 * @see cn.limc.androidcharts.diagram.GridChart#GridChart(Context,
	 * AttributeSet, int)
	 */
	public LineChart(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @param context
	 * 
	 * @param attrs
	 * 
	 * 
	 * 
	 * @see cn.limc.androidcharts.diagram.GridChart#GridChart(Context,
	 * AttributeSet)
	 */
	public LineChart(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
//	protected void drawSticks(Canvas canvas) {
//        if (null == chartData) {
//            return;
//        }
//        if (chartData.size() == 0) {
//            return;
//        }
//
//        float stickWidth = dataQuadrant.getPaddingWidth() / getDisplayNumber();
//
//        for(int i=0; i< chartData.size() ; i++){
//            LineEntity table = (LineEntity)chartData.getChartTable(i);
//            if (null == table) {
//                continue;
//            }
//            if(table.size() == 0){
//                continue;
//            }
//            
//            Paint mPaint = new Paint();
//            mPaint.setColor(table.getLineColor());
//            mPaint.setAntiAlias(true);
//            
//            float stickX = dataQuadrant.getPaddingStartX() + stickWidth / 2;
//            for (int j = getDisplayFrom()+1; j < getDisplayTo(); j++) {
//                IMeasurable point = (IMeasurable)table.get(j-1);
//                IMeasurable nextpoint = (IMeasurable)table.get(j);
//                
//                Points lineMole = new Points();
//                lineMole.setUp(this,point.getHigh(),nextpoint.getHigh(),stickX,stickWidth);
//                lineMole.setLinePaint(mPaint);
//                lineMole.draw(canvas);
//
//                // next x
//                stickX = stickX + stickWidth;
//            }
//        }
//    }

//	protected void calcDataValueRange() {
//		double maxValue = Double.MIN_VALUE;
//		double minValue = Double.MAX_VALUE;
//		// 逐条输出MA线
//		for (int i = 0; i < this.linesData.size(); i++) {
//			LineEntity<DateValueEntity<Integer,Double>> line = (LineEntity<DateValueEntity<Integer,Double>>) linesData
//					.get(i);
//			if (line == null) {
//				continue;
//			}
//			if (line.isDisplay() == false) {
//				continue;
//			}
//			List<DateValueEntity<Integer,Double>> lineData = line.getLineData();
//			if (lineData == null) {
//				continue;
//			}
//			// 判断显示为方柱或显示为线条
//			for (int j = 0; j < lineData.size(); j++) {
//				DateValueEntity<Integer,Double> entity;
//				if (axisY.getPosition() == Axis.AXIS_Y_POSITION_LEFT) {
//					entity = line.getLineData().get(j);
//				} else {
//					entity = line.getLineData().get(lineData.size() - 1 - j);
//				}
//
//				if (entity.getValue() < minValue) {
//					minValue = entity.getValue();
//				}
//				if (entity.getValue() > maxValue) {
//					maxValue = entity.getValue();
//				}
//			}
//		}
//
//		this.maxValue = maxValue;
//		this.minValue = minValue;
//	}
//
//	protected void calcValueRangePaddingZero() {
//		double maxValue = this.maxValue;
//		double minValue = this.minValue;
//
//		if ((long) maxValue > (long) minValue) {
//			if ((maxValue - minValue) < 10. && minValue > 1.) {
//				this.maxValue = (long) (maxValue + 1);
//				this.minValue = (long) (minValue - 1);
//			} else {
//				this.maxValue = (long) (maxValue + (maxValue - minValue) * 0.1);
//				this.minValue = (long) (minValue - (maxValue - minValue) * 0.1);
//
//				if (this.minValue < 0) {
//					this.minValue = 0;
//				}
//			}
//		} else if ((long) maxValue == (long) minValue) {
//			if (maxValue <= 10 && maxValue > 1) {
//				this.maxValue = maxValue + 1;
//				this.minValue = minValue - 1;
//			} else if (maxValue <= 100 && maxValue > 10) {
//				this.maxValue = maxValue + 10;
//				this.minValue = minValue - 10;
//			} else if (maxValue <= 1000 && maxValue > 100) {
//				this.maxValue = maxValue + 100;
//				this.minValue = minValue - 100;
//			} else if (maxValue <= 10000 && maxValue > 1000) {
//				this.maxValue = maxValue + 1000;
//				this.minValue = minValue - 1000;
//			} else if (maxValue <= 100000 && maxValue > 10000) {
//				this.maxValue = maxValue + 10000;
//				this.minValue = minValue - 10000;
//			} else if (maxValue <= 1000000 && maxValue > 100000) {
//				this.maxValue = maxValue + 100000;
//				this.minValue = minValue - 100000;
//			} else if (maxValue <= 10000000 && maxValue > 1000000) {
//				this.maxValue = maxValue + 1000000;
//				this.minValue = minValue - 1000000;
//			} else if (maxValue <= 100000000 && maxValue > 10000000) {
//				this.maxValue = maxValue + 10000000;
//				this.minValue = minValue - 10000000;
//			}
//		} else {
//			this.maxValue = 0;
//			this.minValue = 0;
//		}
//	}
//
//	protected void calcValueRangeFormatForAxis() {
//		int rate = 1;
//
//		if (this.maxValue < 3000) {
//			rate = 1;
//		} else if (this.maxValue >= 3000 && this.maxValue < 5000) {
//			rate = 5;
//		} else if (this.maxValue >= 5000 && this.maxValue < 30000) {
//			rate = 10;
//		} else if (this.maxValue >= 30000 && this.maxValue < 50000) {
//			rate = 50;
//		} else if (this.maxValue >= 50000 && this.maxValue < 300000) {
//			rate = 100;
//		} else if (this.maxValue >= 300000 && this.maxValue < 500000) {
//			rate = 500;
//		} else if (this.maxValue >= 500000 && this.maxValue < 3000000) {
//			rate = 1000;
//		} else if (this.maxValue >= 3000000 && this.maxValue < 5000000) {
//			rate = 5000;
//		} else if (this.maxValue >= 5000000 && this.maxValue < 30000000) {
//			rate = 10000;
//		} else if (this.maxValue >= 30000000 && this.maxValue < 50000000) {
//			rate = 50000;
//		} else {
//			rate = 100000;
//		}
//
//		// 等分轴修正
//		if (simpleGrid.getLatitudeNum() > 0 && rate > 1
//				&& (long) (this.minValue) % rate != 0) {
//			// 最大值加上轴差
//			this.minValue = (long) this.minValue
//					- (long) (this.minValue) % rate;
//		}
//		// 等分轴修正
//		if (simpleGrid.getLatitudeNum() > 0
//				&& (long) (this.maxValue - this.minValue)
//						% (simpleGrid.getLatitudeNum() * rate) != 0) {
//			// 最大值加上轴差
//			this.maxValue = (long) this.maxValue
//					+ simpleGrid.getLatitudeNum() * rate
//					- (long) (this.maxValue - this.minValue) % (simpleGrid.getLatitudeNum() * rate);
//		}
//	}
//
//	protected void calcValueRange() {
//		if (null == this.linesData) {
//			this.maxValue = 0;
//			this.minValue = 0;
//			return;
//		}
//		if (this.linesData.size() > 0) {
//			this.calcDataValueRange();
//			this.calcValueRangePaddingZero();
//		} else {
//			this.maxValue = 0;
//			this.minValue = 0;
//		}
//		this.calcValueRangeFormatForAxis();
//	}

//	/*
//	 * (non-Javadoc)
//	 * 
//	 * <p>Called when is going to draw this chart<p> <p>チャートを書く前、メソッドを呼ぶ<p>
//	 * <p>绘制图表时调用<p>
//	 * 
//	 * @param canvas
//	 * 
//	 * @see android.view.View#onDraw(android.graphics.Canvas)
//	 */
//	@Override
//	protected void onDraw(Canvas canvas) {
//		if (autoCalcValueRange) {
//			calcValueRange();
//		}
//
//		initAxisY();
//		initAxisX();
//
//		super.onDraw(canvas);
//		drawLines(canvas);
//
//	}

//	/**
//	 * <p>
//	 * draw lines
//	 * </p>
//	 * <p>
//	 * ラインを書く
//	 * </p>
//	 * <p>
//	 * 绘制线条
//	 * </p>
//	 * 
//	 * @param canvas
//	 */
//	protected void drawLines(Canvas canvas) {
//		if (null == this.linesData) {
//			return;
//		}
//		// distance between two points
//		float lineLength;
//		// start point‘s X
//		float startX;
//
//		// draw lines
//		for (int i = 0; i < linesData.size(); i++) {
//			LineEntity<DateValueEntity<Integer,Double>> line = (LineEntity<DateValueEntity<Integer,Double>>) linesData
//					.get(i);
//			if (line == null) {
//				continue;
//			}
//			if (line.isDisplay() == false) {
//				continue;
//			}
//			List<DateValueEntity<Integer,Double>> lineData = line.getLineData();
//			if (lineData == null) {
//				continue;
//			}
//
//			Paint mPaint = new Paint();
//			mPaint.setColor(line.getLineColor());
//			mPaint.setAntiAlias(true);
//			// start point
//			PointF ptFirst = null;
//			if (axisY.getPosition() == Axis.AXIS_Y_POSITION_LEFT) {
//	            if (lineAlignType == IFlexableGrid.ALIGN_TYPE_CENTER) {
//	                lineLength= (dataQuadrant.getPaddingWidth() / maxPointNum);
//	                startX = dataQuadrant.getPaddingStartX() + lineLength / 2;
//	            }else {
//	                lineLength= (dataQuadrant.getPaddingWidth() / (maxPointNum - 1));
//	                startX = dataQuadrant.getPaddingStartX();
//	            }
//				
//				for (int j = 0; j < maxPointNum; j++) {
//					double value = lineData.get(j).getValue();
//					// calculate Y
//					float valueY = (float) ((1f - (value - minValue)
//							/ (maxValue - minValue)) * dataQuadrant.getPaddingHeight())
//							+ dataQuadrant.getPaddingStartY();
//
//					// if is not last point connect to previous point
//					if (j > 0) {
//						canvas.drawLine(ptFirst.x, ptFirst.y, startX, valueY,
//								mPaint);
//					}
//					// reset
//					ptFirst = new PointF(startX, valueY);
//					startX = startX + lineLength;
//				}
//			} else {
//	            if (lineAlignType == IFlexableGrid.ALIGN_TYPE_CENTER) {
//	                lineLength= (dataQuadrant.getPaddingWidth() / maxPointNum);
//	                startX = dataQuadrant.getPaddingEndX() - lineLength / 2;
//	            }else {
//	                lineLength= (dataQuadrant.getPaddingWidth() / (maxPointNum - 1));
//	                startX = dataQuadrant.getPaddingEndX();
//	            }
//	            
//				for (int j = maxPointNum - 1; j >= 0; j--) {
//					double value = lineData.get(j).getValue();
//					// calculate Y
//					float valueY = (float) ((1f - (value - minValue)
//							/ (maxValue - minValue)) * dataQuadrant.getPaddingHeight())
//							+ dataQuadrant.getPaddingStartY();
//
//					// if is not last point connect to previous point
//					if (j < maxPointNum - 1) {
//						canvas.drawLine(ptFirst.x, ptFirst.y, startX, valueY,
//								mPaint);
//					}
//					// reset
//					ptFirst = new PointF(startX, valueY);
//					startX = startX - lineLength;
//				}
//			}
//		}
//	}

//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @param value
//	 * 
//	 * @see cn.limc.androidcharts.diagram.GridChart#getAxisXGraduate(Object)
//	 */
//	@Override
//	public String getAxisXGraduate(Object value) {
//
//		float graduate = Float.valueOf(super.getAxisXGraduate(value));
//		int index = (int) Math.floor(graduate * maxPointNum);
//
//		if (index >= maxPointNum) {
//			index = maxPointNum - 1;
//		} else if (index < 0) {
//			index = 0;
//		}
//
//		if (null == this.linesData) {
//			return "";
//		}
//		LineEntity<DateValueEntity<Integer,Double>> line = (LineEntity<DateValueEntity<Integer,Double>>) linesData
//				.get(0);
//		if (line == null) {
//			return "";
//		}
//		if (line.isDisplay() == false) {
//			return "";
//		}
//		List<DateValueEntity<Integer,Double>> lineData = line.getLineData();
//		if (lineData == null) {
//			return "";
//		}
//		return String.valueOf(lineData.get(index).getDate());
//	}
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * @param value
//	 * 
//	 * @see cn.limc.androidcharts.diagram.GridChart#getAxisYGraduate(Object)
//	 */
//	@Override
//	public String getAxisYGraduate(Object value) {
//		float graduate = Float.valueOf(super.getAxisYGraduate(value));
//		return String.valueOf((int) Math.floor(graduate * (maxValue - minValue)
//				+ minValue));
//	}
//	
//	public float longitudePostOffset(){
//		if (lineAlignType == IFlexableGrid.ALIGN_TYPE_CENTER) {
//			float lineLength = dataQuadrant.getPaddingWidth() / maxPointNum;
//			return (this.dataQuadrant.getPaddingWidth() - lineLength)/ (simpleGrid.getLongitudeTitles().size() - 1);
//	    }else{
//			return this.dataQuadrant.getPaddingWidth()/ (simpleGrid.getLongitudeTitles().size() - 1);
//	    }
//	}
//	
//	public float longitudeOffset(){
//		if (lineAlignType == IFlexableGrid.ALIGN_TYPE_CENTER) {
//			float lineLength = dataQuadrant.getPaddingWidth() / maxPointNum;
//			return dataQuadrant.getPaddingStartX() + lineLength / 2;
//		}else{
//			return dataQuadrant.getPaddingStartX();
//		}
//	}

//	/**
//	 * <p>
//	 * initialize degrees on Y axis
//	 * </p>
//	 * <p>
//	 * Y軸の目盛を初期化
//	 * </p>
//	 * <p>
//	 * 初始化Y轴的坐标值
//	 * </p>
//	 */
//	protected void initAxisY() {
//		this.calcValueRange();
//		List<String> titleY = new ArrayList<String>();
//		float average = (int) ((maxValue - minValue) / simpleGrid.getLatitudeNum());
//		;
//		// calculate degrees on Y axis
//		for (int i = 0; i < simpleGrid.getLatitudeNum(); i++) {
//			String value = String.valueOf((int) Math.floor(minValue + i
//					* average));
////			if (value.length() < super.getLatitudeMaxTitleLength()) {
////				while (value.length() < super.getLatitudeMaxTitleLength()) {
////					value = " " + value;
////				}
////			}
//			titleY.add(value);
//		}
//		// calculate last degrees by use max value
//		String value = String.valueOf((int) Math.floor(((int) maxValue)));
////		if (value.length() < super.getLatitudeMaxTitleLength()) {
////			while (value.length() < super.getLatitudeMaxTitleLength()) {
////				value = " " + value;
////			}
////		}
//		titleY.add(value);
//
//		simpleGrid.setLatitudeTitles(titleY);
//	}

//	/**
//	 * <p>
//	 * initialize degrees on Y axis
//	 * </p>
//	 * <p>
//	 * Y軸の目盛を初期化
//	 * </p>
//	 * <p>
//	 * 初始化Y轴的坐标值
//	 * </p>
//	 */
//	protected void initAxisX() {
//		List<String> titleX = new ArrayList<String>();
//		if (null != linesData && linesData.size() > 0) {
//			float average = maxPointNum / simpleGrid.getLongitudeNum();
//			for (int i = 0; i < simpleGrid.getLongitudeNum(); i++) {
//				int index = (int) Math.floor(i * average);
//				if (index > maxPointNum - 1) {
//					index = maxPointNum - 1;
//				}
//				titleX.add(String.valueOf(
//						linesData.get(0).getLineData().get(index).getDate())
//						.substring(4));
//			}
//			titleX.add(String.valueOf(
//					linesData.get(0).getLineData().get(maxPointNum - 1)
//							.getDate()).substring(4));
//		}
//		simpleGrid.setLongitudeTitles(titleX);
//	}
//
////	private float olddistance = 0f;
////	private float newdistance = 0f;
//
//	/*
//	 * (non-Javadoc)
//	 * 
//	 * <p>Called when chart is touched<p> <p>チャートをタッチしたら、メソッドを呼ぶ<p>
//	 * <p>图表点击时调用<p>
//	 * 
//	 * @param event
//	 * 
//	 * @see android.view.View#onTouchEvent(MotionEvent)
//	 */
//	@Override
//	public boolean onTouchEvent(MotionEvent event) {
//		//valid
//		if (!isValidTouchPoint(event.getX(),event.getY())) {
//			return false;
//		}
//		if (null == linesData || linesData.size() == 0) {
//			return false;
//		}
//		
//		return zoomGestureDetector.onTouchEvent(event);
//	}
//
//	/**
//	 * <p>
//	 * calculate the distance between two touch points
//	 * </p>
//	 * <p>
//	 * 複数タッチしたポイントの距離
//	 * </p>
//	 * <p>
//	 * 计算两点触控时两点之间的距离
//	 * </p>
//	 * 
//	 * @param event
//	 * @return float
//	 *         <p>
//	 *         distance
//	 *         </p>
//	 *         <p>
//	 *         距離
//	 *         </p>
//	 *         <p>
//	 *         距离
//	 *         </p>
//	 */
//	protected float calcDistance(MotionEvent event) {
//		if(event.getPointerCount() <= 1) {
//			return 0f;
//		}else{
//			float x = event.getX(0) - event.getX(1);
//			float y = event.getY(0) - event.getY(1);
//			return FloatMath.sqrt(x * x + y * y);
//		}
//	}
//
//	/**
//	 * <p>
//	 * Zoom in the graph
//	 * </p>
//	 * <p>
//	 * 拡大表示する。
//	 * </p>
//	 * <p>
//	 * 放大表示
//	 * </p>
//	 */
//	public void zoomIn() {
//		if (null == linesData || linesData.size() <= 0) {
//			return;
//		}
//		if (maxPointNum > 10) {
//			maxPointNum = maxPointNum - ZOOM_STEP;
//		}
//		
//		this.postInvalidate();
//		
////		//Listener
////		if (onZoomGestureListener != null) {
////			onZoomGestureListener.onZoom(ZOOM_IN, 0, maxPointNum);
////		}
//	}
//
//	/**
//	 * <p>
//	 * Zoom out the grid
//	 * </p>
//	 * <p>
//	 * 縮小表示する。
//	 * </p>
//	 * <p>
//	 * 缩小
//	 * </p>
//	 */
//	public void zoomOut() {
//		if (null == linesData || linesData.size() <= 0) {
//			return;
//		}
//		if (maxPointNum < linesData.get(0).getLineData().size() - 1 - ZOOM_STEP) {
//			maxPointNum = maxPointNum + ZOOM_STEP;
//		}
//		
//		this.postInvalidate();
//		
////		//Listener
////		if (onZoomGestureListener != null) {
////			onZoomGestureListener.onZoom(ZOOM_OUT, 0, maxPointNum);
////		}
//	}
//
//	/**
//	 * @return the linesData
//	 */
//	public List<LineEntity<DateValueEntity<Integer,Double>>> getLinesData() {
//		return linesData;
//	}
//
//	/**
//	 * @param linesData
//	 *            the linesData to set
//	 */
//	public void setLinesData(List<LineEntity<DateValueEntity<Integer,Double>>> linesData) {
//		this.linesData = linesData;
//	}
//
//	/**
//	 * @return the maxPointNum
//	 */
//	public int getMaxPointNum() {
//		return maxPointNum;
//	}
//
//	/**
//	 * @param maxPointNum
//	 *            the maxPointNum to set
//	 */
//	public void setMaxPointNum(int maxPointNum) {
//		this.maxPointNum = maxPointNum;
//	}
//
//	/**
//	 * @return the minValue
//	 */
//	public double getMinValue() {
//		return minValue;
//	}
//
//	/**
//	 * @param minValue
//	 *            the minValue to set
//	 */
//	public void setMinValue(double minValue) {
//		this.minValue = minValue;
//	}
//
//	/**
//	 * @return the maxValue
//	 */
//	public double getMaxValue() {
//		return maxValue;
//	}
//
//	/**
//	 * @param maxValue
//	 *            the maxValue to set
//	 */
//	public void setMaxValue(double maxValue) {
//		this.maxValue = maxValue;
//	}
//
//	/**
//	 * @return the autoCalcValueRange
//	 */
//	public boolean isAutoCalcValueRange() {
//		return autoCalcValueRange;
//	}
//
//	/**
//	 * @param autoCalcValueRange
//	 *            the autoCalcValueRange to set
//	 */
//	public void setAutoCalcValueRange(boolean autoCalcValueRange) {
//		this.autoCalcValueRange = autoCalcValueRange;
//	}
//
//	/**
//	 * @return the lineAlignType
//	 */
//	public int getLineAlignType() {
//		return lineAlignType;
//	}
//
//	/**
//	 * @param lineAlignType the lineAlignType to set
//	 */
//	public void setLineAlignType(int lineAlignType) {
//		this.lineAlignType = lineAlignType;
//	}
//
//	
//	public abstract class OnZoomListener implements OnZoomGestureListener{
//
//        /* (non-Javadoc)
//         * @see cn.limc.androidcharts.event.ZoomGestureDetector.OnZoomGestureListener#onZoomIn(android.view.MotionEvent)
//         */
//        @Override
//        public void onZoomIn(MotionEvent event) {
////            Zoomable dataCursor = (Zoomable) LineChart.this.getDataCursor();
////            if (dataCursor != null) {
////                dataCursor.zoomOut();
////            }
//            LineChart.this.postInvalidate();
//        }
//
//        /* (non-Javadoc)
//         * @see cn.limc.androidcharts.event.ZoomGestureDetector.OnZoomGestureListener#onZoomOut(android.view.MotionEvent)
//         */
//        @Override
//        public void onZoomOut(MotionEvent event) {
//           
////            Zoomable dataCursor = (Zoomable) LineChart.this.getDataCursor();
////            if (dataCursor != null) {
////                dataCursor.zoomOut();
////            }
//            LineChart.this.postInvalidate();
//        }   
//    }
}
