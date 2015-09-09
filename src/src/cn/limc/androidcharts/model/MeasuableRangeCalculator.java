/*
 * MeasuableRangeCalculator.java
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

package cn.limc.androidcharts.model;

import cn.limc.androidcharts.model.AbstractRangeCalculator;
import cn.limc.androidcharts.series.IMeasurable;

public abstract class MeasuableRangeCalculator extends AbstractRangeCalculator {

    @Override
    public void initRange(DataRange dataRange) {
        dataRange.setMaxValue(-Double.MAX_VALUE);
        dataRange.setMinValue(Double.MAX_VALUE);
    }

    @Override
    public void initFirst(DataRange dataRange,IMeasurable stick) {
        
        double maxValue = dataRange.getMaxValue();
        double minValue = dataRange.getMinValue();
        
        // 第一个stick为停盘的情况
        if (stick.getHigh() == 0 && stick.getLow() == 0) {

        } else {
            if (stick.getLow() < minValue) {
                dataRange.setMinValue(stick.getLow());
            }

            if (stick.getHigh() > maxValue) {
                dataRange.setMaxValue(stick.getHigh());
            }
        }
    }
    
    @Override
    public void compareValue(DataRange dataRange,IMeasurable stick) {
        
        double maxValue = dataRange.getMaxValue();
        double minValue = dataRange.getMinValue();

        if (stick.getLow() < minValue) {
            dataRange.setMinValue(stick.getLow());
        }

        if (stick.getHigh() > maxValue) {
            dataRange.setMaxValue(stick.getHigh());
        }
    }
    
    @Override
    public boolean shouldPaddingZero(){
        return true;
    }
    
    @Override
    public boolean miniValueAlwaysZero(){
        return true;
    }
    
    @Override
    public boolean shouldOptimizeForAxis(){
        return true;
    }
    
}
