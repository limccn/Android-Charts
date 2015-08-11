package cn.limc.androidcharts.model;

import cn.limc.androidcharts.component.DataComponent;

public abstract class AbstractDataRange implements DataRange{
    
    protected int dataMultiple =  DEFAULT_DATA_MULTIPLE;
    protected double maxValue = 1;
    protected double minValue = 0;
    protected DataComponent bindComponent;
    protected boolean autoCalcValueRange = DEFAULT_AUTO_CALC_VALUE_RANGE;
    
    protected RangeCalculator rangeCalculator;
   
    public double valueForRate(double rate){
        return 1f - (rate - getMinValue())/getValueRange();
    }
    
    public void calcValueRange(){
        if(rangeCalculator != null){
            rangeCalculator.calc(this);
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
     * @return the bindComponent
     */
    public DataComponent getBindComponent() {
        return bindComponent;
    }
    /**
     * @param bindComponent the bindComponent to set
     */
    public void setBindComponent(DataComponent bindComponent) {
        this.bindComponent = bindComponent;
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
