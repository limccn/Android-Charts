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

package cn.limc.androidcharts.view;

import java.util.List;

import cn.limc.androidcharts.entity.DateValueEntity;
import cn.limc.androidcharts.entity.LineEntity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
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
public class LineChart extends GridChart {
	/**
	 * <p>
	 * data to draw lines
	 * </p>
	 * <p>
	 * ラインを書く用データ
	 * </p>
	 * <p>
	 * 绘制线条用的数据
	 * </p>
	 */
	private List<LineEntity<DateValueEntity>> linesData;

	/**
	 * <p>
	 * max points of a single line
	 * </p>
	 * <p>
	 * ラインの最大ポイント数
	 * </p>
	 * <p>
	 * 线条的最大表示点数
	 * </p>
	 */
	private int maxPointNum;

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
	private double minValue;

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
	private double maxValue;

	public static final boolean DEFAULT_AUTO_CALC_VALUE_RANGE = true;
	private boolean autoCalcValueRange = DEFAULT_AUTO_CALC_VALUE_RANGE;

	/*
	 * (non-Javadoc)
	 * 
	 * @param context
	 * 
	 * @see cn.limc.androidcharts.view.GridChart#GridChart(Context)
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
	 * @see cn.limc.androidcharts.view.GridChart#GridChart(Context,
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
	 * @see cn.limc.androidcharts.view.GridChart#GridChart(Context,
	 * AttributeSet)
	 */
	public LineChart(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	protected void calcDataValueRange() {
		double maxValue = 0;
		double minValue = Integer.MAX_VALUE;
		// 逐条输出MA线
		for (int i = 0; i < this.linesData.size(); i++) {
			LineEntity<DateValueEntity> line = this.linesData.get(i);
			if (line != null && line.getLineData().size() > 0) {
				// 判断显示为方柱或显示为线条
				for (int j = 0; j < line.getLineData().size(); j++) {
					DateValueEntity lineData = line.getLineData().get(j);
					if (lineData.getValue() < minValue) {
						minValue = lineData.getValue();
					}

					if (lineData.getValue() > maxValue) {
						maxValue = lineData.getValue();
					}

				}
			}
		}

		this.maxValue = maxValue;
		this.minValue = minValue;
	}

	protected void calcValueRangePaddingZero() {
		double maxValue = this.maxValue;
		double minValue = this.minValue;

		if ((long) maxValue > (long) minValue) {
			if ((maxValue - minValue) < 10. && minValue > 1.) {
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
		int rate = 1;

		if (this.maxValue < 3000) {
			rate = 1;
		} else if (this.maxValue >= 3000 && this.maxValue < 5000) {
			rate = 5;
		} else if (this.maxValue >= 5000 && this.maxValue < 30000) {
			rate = 10;
		} else if (this.maxValue >= 30000 && this.maxValue < 50000) {
			rate = 50;
		} else if (this.maxValue >= 50000 && this.maxValue < 300000) {
			rate = 100;
		} else if (this.maxValue >= 300000 && this.maxValue < 500000) {
			rate = 500;
		} else if (this.maxValue >= 500000 && this.maxValue < 3000000) {
			rate = 1000;
		} else if (this.maxValue >= 3000000 && this.maxValue < 5000000) {
			rate = 5000;
		} else if (this.maxValue >= 5000000 && this.maxValue < 30000000) {
			rate = 10000;
		} else if (this.maxValue >= 30000000 && this.maxValue < 50000000) {
			rate = 50000;
		} else {
			rate = 100000;
		}

		// 等分轴修正
		if (this.latitudeNum > 0 && rate > 1
				&& (long) (this.minValue) % rate != 0) {
			// 最大值加上轴差
			this.minValue = (long) this.minValue
					- ((long) (this.minValue) % rate);
		}
		// 等分轴修正
		if (this.latitudeNum > 0
				&& (long) (this.maxValue - this.minValue)
						% (this.latitudeNum * rate) != 0) {
			// 最大值加上轴差
			this.maxValue = (long) this.maxValue
					+ (this.latitudeNum * rate)
					- ((long) (this.maxValue - this.minValue) % (this.latitudeNum * rate));
		}
	}

	protected void calcValueRange() {
		if (null == this.linesData) {
			this.maxValue = 0;
			this.minValue = 0;
			return;
		}
		if (this.linesData.size() > 0) {
			this.calcDataValueRange();
			this.calcValueRangePaddingZero();
		} else {
			this.maxValue = 0;
			this.minValue = 0;
		}
		this.calcValueRangeFormatForAxis();
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
		if (autoCalcValueRange) {
			calcValueRange();
		}
		super.onDraw(canvas);
		// draw lines
		if (null != this.linesData) {
			drawLines(canvas);
		}
	}

	/**
	 * <p>
	 * draw lines
	 * </p>
	 * <p>
	 * ラインを書く
	 * </p>
	 * <p>
	 * 绘制线条
	 * </p>
	 * 
	 * @param canvas
	 */
	protected void drawLines(Canvas canvas) {
		// distance between two points
		float lineLength = ((super.getWidth() - super.getAxisMarginLeft() - this
				.getMaxPointNum()) / this.getMaxPointNum()) - 1;
		// start point‘s X
		float startX;

		// draw lines
		for (int i = 0; i < linesData.size(); i++) {
			LineEntity<DateValueEntity> line = (LineEntity<DateValueEntity>) linesData
					.get(i);
			if (line.isDisplay()) {
				Paint mPaint = new Paint();
				mPaint.setColor(line.getLineColor());
				mPaint.setAntiAlias(true);
				List<DateValueEntity> lineData = line.getLineData();
				// set start point’s X
				startX = super.getAxisMarginLeft() + lineLength / 2f;
				// start point
				PointF ptFirst = null;
				if (lineData != null) {
					for (int j = 0; j < lineData.size(); j++) {
						float value = lineData.get(j).getValue();
						// calculate Y
						float valueY = (float) ((1f - (value - this
								.getMinValue())
								/ (this.getMaxValue() - this.getMinValue())) * (super
								.getHeight() - super.getAxisMarginBottom()));

						// if is not last point connect to previous point
						if (j > 0) {
							canvas.drawLine(ptFirst.x, ptFirst.y, startX,
									valueY, mPaint);
						}
						// reset
						ptFirst = new PointF(startX, valueY);
						startX = startX + 1 + lineLength;
					}
				}
			}
		}
	}

	/**
	 * @return the linesData
	 */
	public List<LineEntity<DateValueEntity>> getLinesData() {
		return linesData;
	}

	/**
	 * @param linesData
	 *            the linesData to set
	 */
	public void setLinesData(List<LineEntity<DateValueEntity>> linesData) {
		this.linesData = linesData;
	}

	/**
	 * @return the maxPointNum
	 */
	public int getMaxPointNum() {
		return maxPointNum;
	}

	/**
	 * @param maxPointNum
	 *            the maxPointNum to set
	 */
	public void setMaxPointNum(int maxPointNum) {
		this.maxPointNum = maxPointNum;
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

}
