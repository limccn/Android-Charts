package cn.limc.androidcharts.model;


import cn.limc.androidcharts.diagram.GridChart;
import cn.limc.androidcharts.series.ChartDataTable;
import cn.limc.androidcharts.series.IMeasurable;

public abstract class AbstractValueRange implements ValueRange{
    

    protected int dataMultiple =  DEFAULT_DATA_MULTIPLE;
    protected double maxValue;
    protected double minValue;
    protected boolean autoCalcValueRange = DEFAULT_AUTO_CALC_VALUE_RANGE;
    protected GridChart inChart;
    
    /**
     * 
     */
    public AbstractValueRange(GridChart inChart) {
        this.inChart = inChart;
    }
    
    public abstract void initRange();
    public abstract void initFirst(IMeasurable stick) ;
    public abstract void compareValue(IMeasurable stick) ;
    
    public boolean shouldPaddingZero(){
        return true;
    }
    
    public boolean miniValueAlwaysZero(){
        return true;
    }
    
    public boolean shouldOptimizeForAxis(){
        return true;
    }
    
    public void calcValueRange() {

        this.initRange();

        for (int i = 0; i < inChart.getChartData().getChartTables().size(); i++) {
            ChartDataTable data = inChart.getChartData().getChartTables().get(i);
            if (data != null && data.size() > 0) {
                this.calcDataValueRange(data);
            }
        }

        if (shouldPaddingZero()) {
            this.calcValueRangePaddingZero();
        }
        
        if(miniValueAlwaysZero()){
            if(this.minValue < 0){
                this.minValue = 0;
            }
        }
        if (shouldOptimizeForAxis()) {
            this.calcValueRangeFormatForAxis();
        }
    }
    
    public void calcDataValueRange(ChartDataTable data) {
        IMeasurable first = (IMeasurable)data.get(inChart.getDataCursor().getDisplayFrom());
         this.initFirst(first);
        for (int i = inChart.getDataCursor().getDisplayFrom(); i < inChart.getDataCursor().getDisplayTo(); i++) {
            IMeasurable stick =  (IMeasurable)data.get(i);
            this.compareValue(stick);
        }
    }
    
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

    
    /* (non-Javadoc)
     * @see cn.limc.androidcharts.degree.IHasValueRange#getValueRange()
     */
    @Override
    public double getValueRange() {
        return maxValue - minValue;
    }
   
    /**
     * @return the dataMultiple
     */
    public int getDataMultiple() {
        return dataMultiple;
    }

    /**
     * @param dataMultiple the dataMultiple to set
     */
    public void setDataMultiple(int dataMultiple) {
        this.dataMultiple = dataMultiple;
    }

    /**
     * @return the maxValue
     */
    public double getMaxValue() {
        return maxValue;
    }

    /**
     * @param maxValue the maxValue to set
     */
    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }

    /**
     * @return the minValue
     */
    public double getMinValue() {
        return minValue;
    }

    /**
     * @param minValue the minValue to set
     */
    public void setMinValue(double minValue) {
        this.minValue = minValue;
    }

    /**
     * @return the inChart
     */
    public GridChart getInChart() {
        return inChart;
    }

    /**
     * @param inChart the inChart to set
     */
    public void setInChart(GridChart inChart) {
        this.inChart = inChart;
    }

    /**
     * @return the autoCalcValueRange
     */
    public boolean isAutoCalcValueRange() {
        return autoCalcValueRange;
    }

    /**
     * @param autoCalcValueRange the autoCalcValueRange to set
     */
    public void setAutoCalcValueRange(boolean autoCalcValueRange) {
        this.autoCalcValueRange = autoCalcValueRange;
    }
}
