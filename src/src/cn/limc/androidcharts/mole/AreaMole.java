//
// AreaMole.java
// cn.limc.androidcharts.mole
//
// Created by limc on 2015-7-28.
//
// Copyright 2015 Shanghai Okasan-Huada Computer System Co. Ltd., All rights reserved.
//
package cn.limc.androidcharts.mole;

import cn.limc.androidcharts.common.IChart;
import cn.limc.androidcharts.entity.IMeasurable;
import cn.limc.androidcharts.view.GridChart;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Paint.Style;

/**
 * AreaMole
 * Description: <br>
 *   <p>add description here </p>
 * Tags: <br>
 *   <p> </p>
 *
 * @author limc
 * @version v1.0 
 * 
 * History: <br>
 * 2015-7-28 limc create v1.0 <br>
 *
 */
public class AreaMole extends AbstractMole implements RectMole {

    
    private Paint areaPaint = new Paint();
    
    private double currentHigh;
    private double currentLow;
    private double nextHigh;
    private double nextLow;
    
    /**
     * 
     */
    public AreaMole() {
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.mole.IMole#draw(android.graphics.Canvas)
     */
    @Override
    public void draw(Canvas canvas) {

        GridChart chart = (GridChart) getInChart();
        // calculate Y
        float currentHighY = (float) ((1f - (currentHigh - chart.getDataRange().getMinValue())
                / (chart.getDataRange().getValueRange()))
                * (chart.getDataQuadrant().getPaddingHeight()) + chart.getDataQuadrant()
                .getPaddingStartY());
        float currentLowY = (float) ((1f - (currentLow - chart.getDataRange().getMinValue())
                / (chart.getDataRange().getValueRange()))
                * (chart.getDataQuadrant().getPaddingHeight()) + chart.getDataQuadrant()
                .getPaddingStartY());
        float nextHighY = (float) ((1f - (nextHigh - chart.getDataRange().getMinValue())
                / (chart.getDataRange().getValueRange()))
                * (chart.getDataQuadrant().getPaddingHeight()) + chart.getDataQuadrant()
                .getPaddingStartY());
        float nextLowY = (float) ((1f - (nextLow - chart.getDataRange().getMinValue())
                / (chart.getDataRange().getValueRange()))
                * (chart.getDataQuadrant().getPaddingHeight()) + chart.getDataQuadrant()
                .getPaddingStartY());

        Path areaPath = new Path();
        areaPath.moveTo(left, currentHighY);
        areaPath.lineTo(left, currentLowY);
        areaPath.lineTo(right, nextLowY);
        areaPath.lineTo(right, nextHighY);
        areaPath.close();

        areaPaint.setStyle(Style.FILL_AND_STROKE);
        areaPaint.setAlpha(62);
        canvas.drawPath(areaPath, areaPaint);

    }

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.mole.RectMole#setUp(cn.limc.androidcharts.common.IChart, float, float)
     */
    @Override
    public void setUp(IChart chart, float from, float width) {
        super.setUp(chart);
        left = (float)Math.ceil(from);
        right = (float)Math.ceil(from + width);
        
    }
    
    public void setUp(IChart chart, double currentHigh, double currrentLow, double nextHigt,
            double nextLow, float from, float width) {
        this.setUp(chart, from, width);
        this.currentHigh = currentHigh;
        this.currentLow = currrentLow;
        this.nextHigh = nextHigt;
        this.nextLow = nextLow;
    }

    public void setUp(IChart chart, IMeasurable current, IMeasurable next, float from, float width) {
        this.setUp(chart, current.getHigh(), current.getLow(), next.getHigh(), next.getLow(), from,
                width);
    }

    /**
     * @return the areaPaint
     */
    public Paint getAreaPaint() {
        return areaPaint;
    }

    /**
     * @param areaPaint the areaPaint to set
     */
    public void setAreaPaint(Paint areaPaint) {
        this.areaPaint = areaPaint;
    }

    /**
     * @return the currentHigh
     */
    public double getCurrentHigh() {
        return currentHigh;
    }

    /**
     * @param currentHigh the currentHigh to set
     */
    public void setCurrentHigh(double currentHigh) {
        this.currentHigh = currentHigh;
    }

    /**
     * @return the currentLow
     */
    public double getCurrentLow() {
        return currentLow;
    }

    /**
     * @param currentLow the currentLow to set
     */
    public void setCurrentLow(double currentLow) {
        this.currentLow = currentLow;
    }

    /**
     * @return the nextHigh
     */
    public double getNextHigh() {
        return nextHigh;
    }

    /**
     * @param nextHigh the nextHigh to set
     */
    public void setNextHigh(double nextHigh) {
        this.nextHigh = nextHigh;
    }

    /**
     * @return the nextLow
     */
    public double getNextLow() {
        return nextLow;
    }

    /**
     * @param nextLow the nextLow to set
     */
    public void setNextLow(double nextLow) {
        this.nextLow = nextLow;
    }
    
    
}
