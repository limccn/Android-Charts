//
// RangeCalculator.java
// cn.limc.androidcharts.model
//
// Created by limc on 2015-8-10.
//
// Copyright 2015 Shanghai Okasan-Huada Computer System Co. Ltd., All rights reserved.
//
package cn.limc.androidcharts.model;

import cn.limc.androidcharts.series.ChartDataSet;

/**
 * RangeCalculator
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
public interface RangeCalculator {
    void calc(DataRange dataRange,ChartDataSet data);
    void optimize(DataRange dataRange);
    
    public abstract int rangeDivide(RangeCalculator calc);
//    public abstract ChartDataTable chartDataTableFor(RangeCalculator calc, int index);
//    public abstract int chartDataTableCount(RangeCalculator calc);
    public abstract int startCalcPost(RangeCalculator calc);
    public abstract int endCalcPost(RangeCalculator calc);
}