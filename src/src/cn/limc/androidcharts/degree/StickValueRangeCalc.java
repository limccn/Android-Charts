package cn.limc.androidcharts.degree;

import cn.limc.androidcharts.entity.IMeasurable;
import cn.limc.androidcharts.view.GridChart;

public class StickValueRangeCalc extends ValueRangeCalc {

    /**
     * @param inChart
     */
    public StickValueRangeCalc(GridChart inChart) {
        super(inChart);
    }

    public void initRange() {
        
        maxValue = -Double.MAX_VALUE;
        minValue = Double.MAX_VALUE;
    }

    public void initFirst(IMeasurable stick) {
        // 第一个stick为停盘的情况
        if (stick.getHigh() == 0 && stick.getLow() == 0) {

        } else {
            if (stick.getLow() < minValue) {
                minValue = stick.getLow();
            }

            if (stick.getHigh() > maxValue) {
                maxValue = stick.getHigh();
            }
        }
    }

    public void compareValue(IMeasurable stick) {

        if (stick.getLow() < minValue) {
            minValue = stick.getLow();
        }

        if (stick.getHigh() > maxValue) {
            maxValue = stick.getHigh();
        }
    }
}
