//
// CandleStickValueRangeCalc.java
// cn.limc.androidcharts.degree
//
// Created by limc on 2015-7-22.
//
// Copyright 2015 Shanghai Okasan-Huada Computer System Co. Ltd., All rights reserved.
//
package cn.limc.androidcharts.model;

import cn.limc.androidcharts.diagram.GridChart;
import cn.limc.androidcharts.series.IMeasurable;
import cn.limc.androidcharts.series.OHLCEntity;

/**
 * CandleStickValueRangeCalc
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
public class CandleStickValueRangeCalc extends StickValueRangeCalc {
 
    /**
     * @param inChart
     */
    public CandleStickValueRangeCalc(GridChart inChart) {
        super(inChart);
    }

    public void compareValue(IMeasurable ohlc) {

        OHLCEntity stick = (OHLCEntity) ohlc;
        
        if (stick.getOpen() == 0 && stick.getHigh() == 0
                && stick.getLow() == 0) {
            // 停盘期间计算收盘价
            if (stick.getClose() > 0) {
                if (stick.getClose() < minValue) {
                    minValue = stick.getClose();
                }

                if (stick.getClose() > maxValue) {
                    maxValue = stick.getClose();
                }
            }
        } else {
            if (stick.getLow() < minValue) {
                minValue = stick.getLow();
            }

            if (stick.getHigh() > maxValue) {
                maxValue = stick.getHigh();
            }
        }
    }

}
