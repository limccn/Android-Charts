/*
 * TickChart.java
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
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

import cn.limc.androidcharts.common.IFlexableGrid;
import cn.limc.androidcharts.common.SimpleSplitedGrid;
import cn.limc.androidcharts.entity.DateValueEntity;
import cn.limc.androidcharts.entity.LineEntity;

/**
 * Created by limc on 16/4/6.
 */
public class TickChart extends SlipLineChart {

    protected double lastClose = 0.;
    protected boolean limitRangeSupport = false;
    protected double limitMaxValue = 0.;
    protected double limitMinValue = 0.;

    /*
	 * (non-Javadoc)
	 *
	 * @param context
	 *
	 * @see cn.limc.androidcharts.view.GridChart#GridChart(Context)
	 */
    public TickChart(Context context) {
        super(context);
        this.simpleGrid = new SimpleSplitedGrid(this);
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
    public TickChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.simpleGrid = new SimpleSplitedGrid(this);
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
    public TickChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.simpleGrid = new SimpleSplitedGrid(this);
    }


    protected void calcValueRangeFormatForAxis() {
        int rate = getDataMultiple();

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
        if (simpleGrid.getLatitudeNum() > 0 && rate > 1
                && (long) (this.minValue) % rate != 0) {
            // 最大值加上轴差
            this.minValue = (long) this.minValue
                    - (long) (this.minValue) % rate;
        }
        // 等分轴修正
        if (simpleGrid.getLatitudeNum() > 0
                && (long) (this.maxValue - this.minValue)
                % (simpleGrid.getLatitudeNum() * rate) != 0) {
            // 最大值加上轴差
            this.maxValue = (long) this.maxValue
                    + simpleGrid.getLatitudeNum() * rate
                    - (long) (this.maxValue - this.minValue) % (simpleGrid.getLatitudeNum() * rate);
        }
    }

    @Override
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
        if (autoBalanceValueRange){
            this.balanceRange();
        }

        if (this.limitRangeSupport) {
            this.calcLimitRange();
        }
    }

    @Override
    protected void balanceRange(){
        if(this.lastClose > 0 && this.maxValue > 0 && this.minValue > 0){
            double gap = Math.max(Math.abs(this.maxValue - this.lastClose), Math.abs(this.minValue - this.lastClose));
            this.maxValue = this.lastClose + gap;
            this.minValue = this.lastClose - gap;
        }
    }

    protected  void calcLimitRange(){
        if (this.limitMinValue >= 0 && this.limitMaxValue >= 0) {
            if(this.maxValue > this.limitMaxValue){
                this.maxValue = this.limitMaxValue;
            }
            if(this.minValue < this.limitMinValue){
                this.minValue = this.limitMinValue;
            }
        }
    }

    @Override
    protected void initAxisX(){
        if (this.autoCalcLongitudeTitle == false) {
            return;
        }
        int[] longitudeSplitor = ((SimpleSplitedGrid) simpleGrid).getLongitudeSplitor();

        if (null == linesData) {
            return;
        }
        if (0 == linesData.size()) {
            return;
        }

        List<String> titleX = new ArrayList<String>();
        if (null != getChartData() && getChartData().size() > 0 && longitudeSplitor.length > 0) {
            //以第1条线作为X轴的标示
            LineEntity line = this.linesData.get(0);
            if (line.getLineData().size() > 0 && longitudeSplitor.length >0) {
                int counter = 0;
                do{
                    for(int i = 0; i < longitudeSplitor.length;i++){
                        int index = counter;
                        if (index > getDisplayNumber() - 1) {
                            index = getDisplayNumber() - 1;
                        }
                        titleX.add(formatAxisXDegree(linesData.get(0).getLineData().get(index).getDate()));
                        //计数器重新设置
                        counter = counter + longitudeSplitor[i];
                    }
                }while(counter < getDisplayNumber());
            }
        }
        simpleGrid.setLongitudeTitles(titleX);
    }

    public int[] getLongitudeSplitor() {
        return ((SimpleSplitedGrid) simpleGrid).getLongitudeSplitor();
    }

    public void setLongitudeSplitor(int[] longitudeSplitor) {
        ((SimpleSplitedGrid) simpleGrid).setLongitudeSplitor(longitudeSplitor);
    }

    public double getLastClose() {
        return lastClose;
    }

    public void setLastClose(double lastClose) {
        this.lastClose = lastClose;
    }

    public boolean isLimitRangeSupport() {
        return limitRangeSupport;
    }

    public void setLimitRangeSupport(boolean limitRangeSupport) {
        this.limitRangeSupport = limitRangeSupport;
    }

    public double getLimitMaxValue() {
        return limitMaxValue;
    }

    public void setLimitMaxValue(double limitMaxValue) {
        this.limitMaxValue = limitMaxValue;
    }

    public double getLimitMinValue() {
        return limitMinValue;
    }

    public void setLimitMinValue(double limitMinValue) {
        this.limitMinValue = limitMinValue;
    }
}
