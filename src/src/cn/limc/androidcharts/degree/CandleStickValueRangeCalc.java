//
// CandleStickValueRangeCalc.java
// cn.limc.androidcharts.degree
//
// Created by limc on 2015-7-22.
//
// Copyright 2015 Shanghai Okasan-Huada Computer System Co. Ltd., All rights reserved.
//
package cn.limc.androidcharts.degree;

import cn.limc.androidcharts.axis.IAxis;
import cn.limc.androidcharts.entity.IMeasurable;
import cn.limc.androidcharts.entity.OHLCEntity;
import cn.limc.androidcharts.view.DataGridChart;

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
    public CandleStickValueRangeCalc(DataGridChart inChart) {
        super(inChart);
        // TODO Auto-generated constructor stub
    }

    
    @Override
    public void calcDataValueRange() {
        double maxValue = Double.MIN_VALUE;
        double minValue = Double.MAX_VALUE;
        IMeasurable first;
        if (inChart.getAxisY().getPosition() == IAxis.AXIS_Y_POSITION_LEFT) {
            first = inChart.getStickData().get(0);
        } else {
            first = inChart.getStickData().get(inChart.getStickData().size() - 1);
        }
        // 第一个stick为停盘的情况
        if (first.getHigh() == 0 && first.getLow() == 0) {

        } else {
            maxValue = first.getHigh();
            minValue = first.getLow();
        }
        for (int i = 0; i < inChart.getDataCursor().getDisplayNumber(); i++) {
            OHLCEntity stick;
            if (inChart.getAxisY().getPosition() == IAxis.AXIS_Y_POSITION_LEFT) {
                stick = (OHLCEntity) inChart.getStickData().get(i);
            } else {
                stick = (OHLCEntity) inChart.getStickData().get(inChart.getStickData().size()
                        - 1 - i);
            }

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

        this.maxValue = maxValue;
        this.minValue = minValue;
    }

}
