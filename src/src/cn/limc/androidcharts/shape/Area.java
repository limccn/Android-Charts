//
// Area.java
// cn.limc.androidcharts.shape
//
// Created by limc on 2015-7-28.
//
// Copyright 2015 Shanghai Okasan-Huada Computer System Co. Ltd., All rights reserved.
//
package cn.limc.androidcharts.shape;

import cn.limc.androidcharts.component.DataComponent;
import cn.limc.androidcharts.series.IMeasurable;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Paint.Style;

/**
 * Area
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
public class Area extends AbstractShape implements Rectangle {
    
    public static final int DEFAULT_AREA_STROKE_WIDTH = 0;
    public static final int DEFAULT_AREA_FILL_COLOR = Color.CYAN;
    public static final int DEFAULT_AREA_FILL_ALPHA = 62;

    protected double currentHigh;
    protected double currentLow;
    protected double nextHigh;
    protected double nextLow;
    
    protected Paint areaPaint;
    
    /**
     * 
     */
    public Area() {
        super();
        areaPaint = new Paint();
        areaPaint.setStrokeWidth(DEFAULT_AREA_STROKE_WIDTH);
        areaPaint.setColor(DEFAULT_AREA_FILL_COLOR);
        areaPaint.setStyle(Style.FILL_AND_STROKE);
        areaPaint.setAlpha(DEFAULT_AREA_FILL_ALPHA);
    }

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.shape.Shape#draw(android.graphics.Canvas)
     */
    @Override
    public void draw(Canvas canvas) {
        drawArea(canvas);
    }
    
    public void drawArea(Canvas canvas){
        // calculate Y
        float currentHighY = (float)component.heightForRate(currentHigh);
        float currentLowY = (float)component.heightForRate(currentLow);
        float nextHighY = (float)component.heightForRate(nextHigh);
        float nextLowY = (float)component.heightForRate(nextLow);

        Path areaPath = new Path();
        areaPath.moveTo(left, currentHighY);
        areaPath.lineTo(left, currentLowY);
        areaPath.lineTo(right, nextLowY);
        areaPath.lineTo(right, nextHighY);
        areaPath.close();
        
        canvas.drawPath(areaPath, areaPaint);
    }

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.shape.Rectangle#setUp(cn.limc.androidcharts.common.IChart, float, float)
     */
    @Override
    public void setUp(DataComponent component, float from, float width) {
        super.setUp(component);
        left = (float)Math.ceil(from);
        right = (float)Math.ceil(from + width);
        
    }
    
    public void setUp(DataComponent component, double currentHigh, double currrentLow, double nextHigt,
            double nextLow, float from, float width) {
        this.setUp(component, from, width);
        this.currentHigh = currentHigh;
        this.currentLow = currrentLow;
        this.nextHigh = nextHigt;
        this.nextLow = nextLow;
    }

    public void setUp(DataComponent component, IMeasurable current, IMeasurable next, float from, float width) {
        this.setUp(component, current.getHigh(), current.getLow(), next.getHigh(), next.getLow(), from,
                width);
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
}
