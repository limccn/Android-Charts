//
// LineValueRangeCalc.java
// cn.limc.androidcharts.degree
//
// Created by limc on 2015-7-23.
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
 * LineValueRangeCalc
 * Description: <br>
 *   <p>add description here </p>
 * Tags: <br>
 *   <p> </p>
 *
 * @author limc
 * @version v1.0 
 * 
 * History: <br>
 * 2015-7-23 limc create v1.0 <br>
 *
 */
//public class LineValueRangeCalc extends ValueRangeCalc{
//
//    /**
//     * 
//     */
//    public LineValueRangeCalc(LineChart inChart) {
//        super(inChart);
//        // TODO Auto-generated constructor stub
//    }
//    
//    
//    public void calcDataValueRange() {
//        double maxValue = Double.MIN_VALUE;
//        double minValue = Double.MAX_VALUE;
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
//            // 判断显示为方柱或显示为线条
//            for (int j = 0; j < lineData.size(); j++) {
//                DateValueEntity entity;
//                if (axisY.getPosition() == IAxis.AXIS_Y_POSITION_LEFT) {
//                    entity = line.getLineData().get(j);
//                } else {
//                    entity = line.getLineData().get(lineData.size() - 1 - j);
//                }
//
//                if (entity.getValue() < minValue) {
//                    minValue = entity.getValue();
//                }
//                if (entity.getValue() > maxValue) {
//                    maxValue = entity.getValue();
//                }
//            }
//        }
//
//        this.maxValue = maxValue;
//        this.minValue = minValue;
//    }
//
//    public void calcValueRangePaddingZero() {
//        double maxValue = this.maxValue;
//        double minValue = this.minValue;
//
//        if ((long) maxValue > (long) minValue) {
//            if ((maxValue - minValue) < 10. && minValue > 1.) {
//                this.maxValue = (long) (maxValue + 1);
//                this.minValue = (long) (minValue - 1);
//            } else {
//                this.maxValue = (long) (maxValue + (maxValue - minValue) * 0.1);
//                this.minValue = (long) (minValue - (maxValue - minValue) * 0.1);
//
//                if (this.minValue < 0) {
//                    this.minValue = 0;
//                }
//            }
//        } else if ((long) maxValue == (long) minValue) {
//            if (maxValue <= 10 && maxValue > 1) {
//                this.maxValue = maxValue + 1;
//                this.minValue = minValue - 1;
//            } else if (maxValue <= 100 && maxValue > 10) {
//                this.maxValue = maxValue + 10;
//                this.minValue = minValue - 10;
//            } else if (maxValue <= 1000 && maxValue > 100) {
//                this.maxValue = maxValue + 100;
//                this.minValue = minValue - 100;
//            } else if (maxValue <= 10000 && maxValue > 1000) {
//                this.maxValue = maxValue + 1000;
//                this.minValue = minValue - 1000;
//            } else if (maxValue <= 100000 && maxValue > 10000) {
//                this.maxValue = maxValue + 10000;
//                this.minValue = minValue - 10000;
//            } else if (maxValue <= 1000000 && maxValue > 100000) {
//                this.maxValue = maxValue + 100000;
//                this.minValue = minValue - 100000;
//            } else if (maxValue <= 10000000 && maxValue > 1000000) {
//                this.maxValue = maxValue + 1000000;
//                this.minValue = minValue - 1000000;
//            } else if (maxValue <= 100000000 && maxValue > 10000000) {
//                this.maxValue = maxValue + 10000000;
//                this.minValue = minValue - 10000000;
//            }
//        } else {
//            this.maxValue = 0;
//            this.minValue = 0;
//        }
//    }
//
//    public void calcValueRangeFormatForAxis() {
//        int rate = 1;
//
//        if (this.maxValue < 3000) {
//            rate = 1;
//        } else if (this.maxValue >= 3000 && this.maxValue < 5000) {
//            rate = 5;
//        } else if (this.maxValue >= 5000 && this.maxValue < 30000) {
//            rate = 10;
//        } else if (this.maxValue >= 30000 && this.maxValue < 50000) {
//            rate = 50;
//        } else if (this.maxValue >= 50000 && this.maxValue < 300000) {
//            rate = 100;
//        } else if (this.maxValue >= 300000 && this.maxValue < 500000) {
//            rate = 500;
//        } else if (this.maxValue >= 500000 && this.maxValue < 3000000) {
//            rate = 1000;
//        } else if (this.maxValue >= 3000000 && this.maxValue < 5000000) {
//            rate = 5000;
//        } else if (this.maxValue >= 5000000 && this.maxValue < 30000000) {
//            rate = 10000;
//        } else if (this.maxValue >= 30000000 && this.maxValue < 50000000) {
//            rate = 50000;
//        } else {
//            rate = 100000;
//        }
//
//        // 等分轴修正
//        if (simpleGrid.getLatitudeNum() > 0 && rate > 1
//                && (long) (this.minValue) % rate != 0) {
//            // 最大值加上轴差
//            this.minValue = (long) this.minValue
//                    - (long) (this.minValue) % rate;
//        }
//        // 等分轴修正
//        if (simpleGrid.getLatitudeNum() > 0
//                && (long) (this.maxValue - this.minValue)
//                        % (simpleGrid.getLatitudeNum() * rate) != 0) {
//            // 最大值加上轴差
//            this.maxValue = (long) this.maxValue
//                    + simpleGrid.getLatitudeNum() * rate
//                    - (long) (this.maxValue - this.minValue) % (simpleGrid.getLatitudeNum() * rate);
//        }
//    }
//
//    public void calcValueRange() {
//        if (null == this.linesData) {
//            this.maxValue = 0;
//            this.minValue = 0;
//            return;
//        }
//        if (this.linesData.size() > 0) {
//            this.calcDataValueRange();
//            this.calcValueRangePaddingZero();
//        } else {
//            this.maxValue = 0;
//            this.minValue = 0;
//        }
//        this.calcValueRangeFormatForAxis();
//    }


//}
