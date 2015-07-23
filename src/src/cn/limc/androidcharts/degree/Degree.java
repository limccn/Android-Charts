
package cn.limc.androidcharts.degree;

import cn.limc.androidcharts.view.DataGridChart;

public abstract class Degree implements IDegree {

    public static final boolean DEFAULT_AUTO_FORMAT_DEGREE = true;
    
    protected boolean autoFormatDegree = DEFAULT_AUTO_FORMAT_DEGREE;
    
    
    protected String sourceFormat;
    
    protected String targetFormat;
    
    protected DataGridChart inChart;
    
    /**
     * 
     */
    public Degree(DataGridChart inChart) {
        this.inChart = inChart;
    }

    /**
     * @return the autoFormatDegree
     */
    public boolean isAutoFormatDegree() {
        return autoFormatDegree;
    }

    /**
     * @param autoFormatDegree the autoFormatDegree to set
     */
    public void setAutoFormatDegree(boolean autoFormatDegree) {
        this.autoFormatDegree = autoFormatDegree;
    }

    /**
     * @return the sourceFormat
     */
    public String getSourceFormat() {
        return sourceFormat;
    }

    /**
     * @param sourceFormat the sourceFormat to set
     */
    public void setSourceFormat(String sourceFormat) {
        this.sourceFormat = sourceFormat;
    }

    /**
     * @return the targetFormat
     */
    public String getTargetFormat() {
        return targetFormat;
    }

    /**
     * @param targetFormat the targetFormat to set
     */
    public void setTargetFormat(String targetFormat) {
        this.targetFormat = targetFormat;
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
}
