package cn.limc.androidcharts.model;

import cn.limc.androidcharts.series.ChartDataSet;

public abstract class AbstractDataRange implements DataRange{
    
    protected int dataMultiple =  DEFAULT_DATA_MULTIPLE;
    protected double maxValue = -Double.MAX_VALUE;
    protected double minValue = Double.MAX_VALUE;
    protected boolean autoCalcValueRange = DEFAULT_AUTO_CALC_VALUE_RANGE;
    
    protected RangeCalculator rangeCalculator;
   
    public double valueForRate(double rate){
        if (maxValue<minValue) {
            return 1;
        }else{
            return 1f - (rate - getMinValue())/getValueRange();
        }
    }
    
    public void calcValueRange(ChartDataSet data){
        if(rangeCalculator != null && data != null){
            rangeCalculator.calc(this,data);
        }
    }
    
    public void optimizeValueRange(){
        if(rangeCalculator != null){
            rangeCalculator.optimize(this);
        }
    }
    
    /* (non-Javadoc)
     * @see cn.limc.androidcharts.degree.IHasValueRange#getValueRange()
     */
    @Override
    public double getValueRange() {
        if (maxValue<minValue) {
            return 1;
        }else{
            return maxValue - minValue;
        }
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
    
    /**
     * @return the rangeCalculator
     */
    public RangeCalculator getRangeCalculator() {
        return rangeCalculator;
    }

    /**
     * @param rangeCalculator the rangeCalculator to set
     */
    public void setRangeCalculator(RangeCalculator rangeCalculator) {
        this.rangeCalculator = rangeCalculator;
    }
    
}
