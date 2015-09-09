/*
 * DecimalDegree.java
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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import cn.limc.androidcharts.component.Axis;

/**
 * DecimalDegree
 * Description: <br>
 *   <p>add description here </p>
 * Tags: <br>
 *   <p> </p>
 *
 * @author limc
 * @version v1.0 
 * 
 * History: <br>
 * 2015-7-22 limc create v1.0 <br>
 *
 */
public class DecimalDegree extends AbstractDegree {
    
    public static final String DEFAULT_SOURCE_FORMAT = "#,##0";
    public static final String DEFAULT_TARGET_FORMAT = "#,##0";

    /**
     * @param parent
     */
    public DecimalDegree() {
        sourceFormat = DEFAULT_SOURCE_FORMAT;
        targetFormat = DEFAULT_TARGET_FORMAT;
    }

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.degree.IDegree#getDegrees()
     */
    @Override
    public List<String> getDegrees(Axis axis) {
        List<String> titleY = new ArrayList<String>();
        DataRange dataRange = axis.getBindComponent().getDataRange();
//        if (dataRange.isAutoCalcValueRange()) {
//            dataRange.calcValueRange(axis.getBindComponent().getChartData());
//        }
        double average = dataRange.getValueRange() / (axis.titlesNum() - 1);
        // calculate degrees on Y axis
        for (int i = 0; i < axis.titlesNum(); i++) {
            String value = valueForDegree(axis ,dataRange.getMinValue() + i * average);
            titleY.add(value);
        }
        // calculate last degrees by use max value
//        String value = valueForDegree(axis, dataRange.getMaxValue());
//        titleY.add(value);
        return titleY;
    }

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.degree.IDegree#valueForDegree(java.lang.Object)
     */
    @Override
    public String valueForDegree(Axis axis , Object value) {
        DecimalFormat formator = new DecimalFormat(targetFormat);
        DataRange dataRange = axis.getBindComponent().getDataRange();
        if (value instanceof Integer) {
            return formator.format(Math.floor((Integer)value)/ dataRange.getDataMultiple());
        }else if (value instanceof Float) {
            return formator.format(Math.floor((Float)value)/ dataRange.getDataMultiple());
        }else if (value instanceof Double) {
            return formator.format(Math.floor((Double)value)/ dataRange.getDataMultiple());
        }else{
            return "";
        }
    }
}
