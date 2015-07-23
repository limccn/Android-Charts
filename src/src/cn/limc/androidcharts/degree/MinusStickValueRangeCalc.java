//
// MinusStickValueRangeCalc.java
// cn.limc.androidcharts.degree
//
// Created by limc on 2015-7-22.
//
// Copyright 2015 Shanghai Okasan-Huada Computer System Co. Ltd., All rights reserved.
//
package cn.limc.androidcharts.degree;

import cn.limc.androidcharts.entity.IMeasurable;
import cn.limc.androidcharts.view.DataGridChart;

/**
 * MinusStickValueRangeCalc
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
public class MinusStickValueRangeCalc extends StickValueRangeCalc {

    /**
     * @param inChart
     */
    public MinusStickValueRangeCalc(DataGridChart inChart) {
        super(inChart);
        // TODO Auto-generated constructor stub
    }

//    @Override
//    public void calcDataValueRange() {
//
//        double maxValue = -Double.MAX_VALUE;
//        double minValue = Double.MAX_VALUE;
//
//        IMeasurable first = inChart.getStickData().get(0);
//        // 第一个stick为停盘的情况
//        if (first.getHigh() == 0 && first.getLow() == 0) {
//
//        } else {
//            maxValue = first.getHigh();
//            minValue = first.getLow();
//        }
//
//        // 判断显示为方柱或显示为线条
//        for (int i = 0; i < inChart.getStickData().size(); i++) {
//            IMeasurable stick = inChart.getStickData().get(i);
//            if (stick.getLow() < minValue) {
//                minValue = stick.getLow();
//            }
//
//            if (stick.getHigh() > maxValue) {
//                maxValue = stick.getHigh();
//            }
//
//        }
//
//        this.maxValue = maxValue;
//        this.minValue = minValue;
//    }
    
    
    @Override
    public void calcDataValueRange() {

        double maxValue = -Double.MAX_VALUE;
        double minValue = Double.MAX_VALUE;

        IMeasurable first = inChart.getStickData().get(0);
        // 第一个stick为停盘的情况
        if (first.getHigh() == 0 && first.getLow() == 0) {

        } else {
            maxValue = first.getHigh();
            minValue = first.getLow();
        }

        // 判断显示为方柱或显示为线条
        for (int i = inChart.getDataCursor().getDisplayFrom(); i < inChart.getDataCursor().getDisplayTo(); i++) {
            IMeasurable stick = inChart.getStickData().get(i);
            if (stick.getLow() < minValue) {
                minValue = stick.getLow();
            }

            if (stick.getHigh() > maxValue) {
                maxValue = stick.getHigh();
            }

        }

        this.maxValue = maxValue;
        this.minValue = minValue;
    }
    
    @Override
    public void calcValueRangePaddingZero(){
        
    }
    
    @Override
    public void calcValueRangeFormatForAxis() {
        
    }
}
