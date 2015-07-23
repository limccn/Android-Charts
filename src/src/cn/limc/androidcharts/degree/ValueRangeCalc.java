package cn.limc.androidcharts.degree;

import cn.limc.androidcharts.view.DataGridChart;

public abstract class ValueRangeCalc implements IHasValueRange{
    

    protected int dataMultiple =  DEFAULT_DATA_MULTIPLE;
    protected double maxValue;
    protected double minValue;
    protected boolean autoCalcValueRange = DEFAULT_AUTO_CALC_VALUE_RANGE;
    protected DataGridChart inChart;
    
    /**
     * 
     */
    public ValueRangeCalc(DataGridChart inChart) {
        this.inChart = inChart;
        this.calcValueRange();
    }
    
    public abstract void calcDataValueRange();
    public abstract void calcValueRangePaddingZero() ;
    public abstract void calcValueRangeFormatForAxis() ;
    public abstract void calcValueRange();
    
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
    public DataGridChart getInChart() {
        return inChart;
    }

    /**
     * @param inChart the inChart to set
     */
    public void setInChart(DataGridChart inChart) {
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
