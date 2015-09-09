/*
 * AbstractRangeCalculator.java
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

package cn.limc.androidcharts.model;

import cn.limc.androidcharts.series.ChartDataSet;
import cn.limc.androidcharts.series.ChartDataTable;
import cn.limc.androidcharts.series.IMeasurable;

/**
 * AbstractRangeCalculator
 * Description: <br>
 *   <p>add description here </p>
 * Tags: <br>
 *   <p> </p>
 *
 * @author limc
 * @version v1.0 
 * 
 * History: <br>
 * 2015-8-10 limc create v1.0 <br>
 *
 */
public abstract class AbstractRangeCalculator implements RangeCalculator {
    
    /**
     * 
     */
    public AbstractRangeCalculator() {
        super();
        // TODO Auto-generated constructor stub
    }


    /* (non-Javadoc)
     * @see cn.limc.androidcharts.model.RangeCalculator#calc(cn.limc.androidcharts.model.DataRange)
     */
    @Override
    public void calc(DataRange dataRange, ChartDataSet datas) {
        this.initRange(dataRange);
        for (int i = 0; i < datas.size(); i++) {
            ChartDataTable data = datas.getChartTable(i);
            if (data != null && data.size() > 0) {
                this.calcDataValueRange(dataRange,data);
            }
        }        
    }
    
    public void optimize(DataRange dataRange) {
        if (shouldPaddingZero()) {
            this.calcValueRangePaddingZero(dataRange);
        }
        
        if(miniValueAlwaysZero()){
            if(dataRange.getMinValue() < 0){
                dataRange.setMinValue(0);
            }
        }
        if (shouldOptimizeForAxis()) {
            this.calcValueRangeFormatForAxis(dataRange);
        }
    }
    
    
    public abstract void initRange(DataRange dataRange);
    public abstract void initFirst(DataRange dataRange,IMeasurable stick) ;
    public abstract void compareValue(DataRange dataRange ,IMeasurable stick) ;
    
    public boolean shouldPaddingZero(){
        return false;
    }
    
    public boolean miniValueAlwaysZero(){
        return false;
    }
    
    public boolean shouldOptimizeForAxis(){
        return false;
    }
    
    public void calcDataValueRange(DataRange dataRange,ChartDataTable data) {
        
        IMeasurable first = (IMeasurable)data.get(startCalcPost(this));
         this.initFirst(dataRange,first);
        for (int i = startCalcPost(this); i < endCalcPost(this); i++) {
            IMeasurable stick =  (IMeasurable)data.get(i);
            this.compareValue(dataRange,stick);
        }
    }
    
    public void calcValueRangePaddingZero(DataRange dataRange) {
        double maxValue = dataRange.getMaxValue();
        double minValue = dataRange.getMinValue();

        if ((long) maxValue > (long) minValue) {
            if ((maxValue - minValue) < 10 && minValue > 1) {
                dataRange.setMaxValue((long) (maxValue + 1));
                dataRange.setMinValue((long) (minValue - 1));
            } else {
                dataRange.setMaxValue((long) (maxValue + (maxValue - minValue) * 0.1));
                dataRange.setMinValue((long) (minValue - (maxValue - minValue) * 0.1));
            }
        } else if ((long) maxValue == (long) minValue) {
            if (maxValue <= 10 && maxValue > 1) {
                dataRange.setMaxValue(maxValue + 1);
                dataRange.setMinValue(minValue - 1);
            } else if (maxValue <= 100 && maxValue > 10) {
                dataRange.setMaxValue(maxValue + 10);
                dataRange.setMinValue(minValue - 10);
            } else if (maxValue <= 1000 && maxValue > 100) {
                dataRange.setMaxValue(maxValue + 100);
                dataRange.setMinValue(minValue - 100);
            } else if (maxValue <= 10000 && maxValue > 1000) {
                dataRange.setMaxValue(maxValue + 1000);
                dataRange.setMinValue(minValue - 1000);
            } else if (maxValue <= 100000 && maxValue > 10000) {
                dataRange.setMaxValue(maxValue + 10000);
                dataRange.setMinValue(minValue - 10000);
            } else if (maxValue <= 1000000 && maxValue > 100000) {
                dataRange.setMaxValue(maxValue + 100000);
                dataRange.setMinValue(minValue - 100000);
            } else if (maxValue <= 10000000 && maxValue > 1000000) {
                dataRange.setMaxValue(maxValue + 1000000);
                dataRange.setMinValue(minValue - 1000000);
            } else if (maxValue <= 100000000 && maxValue > 10000000) {
                dataRange.setMaxValue(maxValue + 10000000);
                dataRange.setMinValue(minValue - 10000000);
            }
        } else {
            dataRange.setMaxValue(0);
            dataRange.setMinValue(0);
        }

    }
    
    public void calcValueRangeFormatForAxis(DataRange dataRange) {
        
        // 修正最大值和最小值
        long rate = (long) (dataRange.getValueRange()) / (rangeDivide(this));
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
        if (rangeDivide(this) > 0
                && (long) (dataRange.getValueRange())
                        % (rangeDivide(this) * rate) != 0) {
            // 最大值加上轴差
            long maxValue =  (long) dataRange.getMaxValue()
                    + (rangeDivide(this) * rate)
                    - ((long) (dataRange.getValueRange()) % (rangeDivide(this) * rate));
            
            dataRange.setMaxValue(maxValue);
        }
    }
   
    
}
