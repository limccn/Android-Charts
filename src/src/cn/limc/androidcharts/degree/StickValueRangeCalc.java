package cn.limc.androidcharts.degree;

import cn.limc.androidcharts.entity.IMeasurable;
import cn.limc.androidcharts.view.DataGridChart;

public class StickValueRangeCalc extends ValueRangeCalc {

    /**
     * @param inChart
     */
    public StickValueRangeCalc(DataGridChart inChart) {
        super(inChart);
    }

    @Override
    public void calcDataValueRange() {
        double maxValue = Double.MIN_VALUE;
        double minValue = Double.MAX_VALUE;
        IMeasurable first = inChart.getStickData().get(inChart.getDataCursor().getDisplayFrom());
        // 第一个stick为停盘的情况
        if (first.getHigh() == 0 && first.getLow() == 0) {

        } else {
            maxValue = first.getHigh();
            minValue = first.getLow();
        }

        for (int i = inChart.getDataCursor().getDisplayFrom(); i < inChart.getDataCursor().getDisplayTo(); i++) {
            IMeasurable stick;
            stick = inChart.getStickData().get(i);
            
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
    public void calcValueRangePaddingZero() {
        double maxValue = this.maxValue;
        double minValue = this.minValue;

        if ((long) maxValue > (long) minValue) {
            if ((maxValue - minValue) < 10 && minValue > 1) {
                this.maxValue = (long) (maxValue + 1);
                this.minValue = (long) (minValue - 1);
            } else {
                this.maxValue = (long) (maxValue + (maxValue - minValue) * 0.1);
                this.minValue = (long) (minValue - (maxValue - minValue) * 0.1);
                if (this.minValue < 0) {
                    this.minValue = 0;
                }
            }
        } else if ((long) maxValue == (long) minValue) {
            if (maxValue <= 10 && maxValue > 1) {
                this.maxValue = maxValue + 1;
                this.minValue = minValue - 1;
            } else if (maxValue <= 100 && maxValue > 10) {
                this.maxValue = maxValue + 10;
                this.minValue = minValue - 10;
            } else if (maxValue <= 1000 && maxValue > 100) {
                this.maxValue = maxValue + 100;
                this.minValue = minValue - 100;
            } else if (maxValue <= 10000 && maxValue > 1000) {
                this.maxValue = maxValue + 1000;
                this.minValue = minValue - 1000;
            } else if (maxValue <= 100000 && maxValue > 10000) {
                this.maxValue = maxValue + 10000;
                this.minValue = minValue - 10000;
            } else if (maxValue <= 1000000 && maxValue > 100000) {
                this.maxValue = maxValue + 100000;
                this.minValue = minValue - 100000;
            } else if (maxValue <= 10000000 && maxValue > 1000000) {
                this.maxValue = maxValue + 1000000;
                this.minValue = minValue - 1000000;
            } else if (maxValue <= 100000000 && maxValue > 10000000) {
                this.maxValue = maxValue + 10000000;
                this.minValue = minValue - 10000000;
            }
        } else {
            this.maxValue = 0;
            this.minValue = 0;
        }

    }

    @Override
    public void calcValueRangeFormatForAxis() {
        // 修正最大值和最小值
        long rate = (long) (this.maxValue - this.minValue) / (inChart.getSimpleGrid().getLatitudeNum());
        String strRate = String.valueOf(rate);
        float first = Integer.parseInt(String.valueOf(strRate.charAt(0))) + 1.0f;
        if (first > 0 && strRate.length() > 1) {
            float second = Integer.parseInt(String.valueOf(strRate.charAt(1)));
            if (second < 5) {
                first = first - 0.5f;
            }
            rate = (long) (first * Math.pow(10, strRate.length() - 1));
        } else {
            rate = 1;
        }
        // 等分轴修正
        if (inChart.getSimpleGrid().getLatitudeNum() > 0
                && (long) (this.maxValue - this.minValue)
                        % (inChart.getSimpleGrid().getLatitudeNum() * rate) != 0) {
            // 最大值加上轴差
            this.maxValue = (long) this.maxValue
                    + (inChart.getSimpleGrid().getLatitudeNum() * rate)
                    - ((long) (this.maxValue - this.minValue) % (inChart.getSimpleGrid().getLatitudeNum() * rate));
        }
    }

    @Override
    public void calcValueRange() {
        if (inChart.getStickData() != null && inChart.getStickData().size() > 0) {
            this.calcDataValueRange();
            this.calcValueRangePaddingZero();
        } else {
            this.maxValue = 0;
            this.minValue = 0;
        }
        this.calcValueRangeFormatForAxis();
    }

}
