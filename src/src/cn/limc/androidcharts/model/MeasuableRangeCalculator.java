package cn.limc.androidcharts.model;

import cn.limc.androidcharts.series.IMeasurable;

public class MeasuableRangeCalculator extends AbstractRangeCalculator {

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
