//
// MACandleStickValueRangeCalc.java
// cn.limc.androidcharts.degree
//
// Created by limc on 2015-7-22.
//
// Copyright 2015 Shanghai Okasan-Huada Computer System Co. Ltd., All rights reserved.
//
package cn.limc.androidcharts.degree;

import java.util.List;

import cn.limc.androidcharts.axis.IAxis;
import cn.limc.androidcharts.entity.DateValueEntity;
import cn.limc.androidcharts.entity.LineEntity;
import cn.limc.androidcharts.view.DataGridChart;

/**
 * MACandleStickValueRangeCalc
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
public class MACandleStickValueRangeCalc extends CandleStickValueRangeCalc {

    /**
     * @param inChart
     */
    public MACandleStickValueRangeCalc(DataGridChart inChart) {
        super(inChart);
        // TODO Auto-generated constructor stub
    }
    
//    @Override
//    protected void calcDataValueRange() {
//        super.calcDataValueRange();
//
//        double maxValue = this.maxValue;
//        double minValue = this.minValue;
//        // 逐条输出MA线
//        for (int i = 0; i < this.linesData.size(); i++) {
//            LineEntity<DateValueEntity> line = (LineEntity<DateValueEntity>) linesData
//                    .get(i);
//            if (line == null) {
//                continue;
//            }
//            if (line.isDisplay() == false) {
//                continue;
//            }
//            List<DateValueEntity> lineData = line.getLineData();
//            if (lineData == null) {
//                continue;
//            }
//
//            // 判断显示为方柱或显示为线条
//            for (int j = 0; j < dataCursor.getDisplayNumber(); j++) {
//                DateValueEntity entity;
//                if (axisY.getPosition() == IAxis.AXIS_Y_POSITION_LEFT) {
//                    entity = line.getLineData().get(j);
//                } else {
//                    entity = line.getLineData().get(lineData.size() - 1 - j);
//                }
//                if (entity.getValue() < minValue) {
//                    minValue = entity.getValue();
//                }
//                if (entity.getValue() > maxValue) {
//                    maxValue = entity.getValue();
//                }
//            }
//        }
//        this.maxValue = maxValue;
//        this.minValue = minValue;
//    }
}
