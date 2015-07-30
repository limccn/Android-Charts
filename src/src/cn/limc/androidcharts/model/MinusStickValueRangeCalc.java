//
// MinusStickValueRangeCalc.java
// cn.limc.androidcharts.degree
//
// Created by limc on 2015-7-22.
//
// Copyright 2015 Shanghai Okasan-Huada Computer System Co. Ltd., All rights reserved.
//
package cn.limc.androidcharts.model;

import cn.limc.androidcharts.diagram.GridChart;

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
    public MinusStickValueRangeCalc(GridChart inChart) {
        super(inChart);
        // TODO Auto-generated constructor stub
    }
    
    public boolean shouldPaddingZero(){
        return false;
    }
    
    public boolean shouldOptimizeForAxis(){
        return false;
    }
    
    public boolean miniValueAlwaysZero(){
        return false;
    }
}
