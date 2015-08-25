//
// CandleStickRangeCalculator.java
// cn.limc.androidcharts.degree
//
// Created by limc on 2015-7-22.
//
// Copyright 2015 Shanghai Okasan-Huada Computer System Co. Ltd., All rights reserved.
//
package cn.limc.androidcharts.model;

import cn.limc.androidcharts.series.IMeasurable;
import cn.limc.androidcharts.series.OHLCEntity;

/**
 * CandleStickRangeCalculator
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
public abstract class CandleStickRangeCalculator extends MeasuableRangeCalculator {
 

    public void compareValue(DataRange dataRange,IMeasurable ohlc) {

        double maxValue = dataRange.getMaxValue();
        double minValue = dataRange.getMinValue();

        
        OHLCEntity stick = (OHLCEntity) ohlc;
        
        if (stick.getOpen() == 0 && stick.getHigh() == 0
                && stick.getLow() == 0) {
            // 停盘期间计算收盘价
            if (stick.getClose() > 0) {
                if (stick.getClose() < minValue) {
                    dataRange.setMinValue(stick.getLow());
                }

                if (stick.getClose() > maxValue) {
                    dataRange.setMaxValue(stick.getHigh());
                }
            }
        } else {
            if (stick.getLow() < minValue) {
                dataRange.setMinValue(stick.getLow());
            }

            if (stick.getHigh() > maxValue) {
                dataRange.setMaxValue(stick.getHigh());
            }
        }
    }
}
