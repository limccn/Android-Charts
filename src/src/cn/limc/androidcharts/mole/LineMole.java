//
// LineMole.java
// cn.limc.androidcharts.mole
//
// Created by limc on 2015-7-24.
//
// Copyright 2015 Shanghai Okasan-Huada Computer System Co. Ltd., All rights reserved.
//
package cn.limc.androidcharts.mole;

import cn.limc.androidcharts.common.IChart;
import cn.limc.androidcharts.view.GridChart;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * LineMole
 * Description: <br>
 *   <p>add description here </p>
 * Tags: <br>
 *   <p> </p>
 *
 * @author limc
 * @version v1.0 
 * 
 * History: <br>
 * 2015-7-24 limc create v1.0 <br>
 *
 */
public class LineMole extends AbstractMole implements RectMole{

    public static double NON_DISPLAY_VALUE = 0; 
    private Paint linePaint = new Paint();
    
    private double current;
    private double next;
    
    /**
     * 
     */
    public LineMole() {
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.mole.IMole#draw(android.graphics.Canvas)
     */
    @Override
    public void draw(Canvas canvas) {
        GridChart chart = (GridChart)getInChart();
        if (current!=NON_DISPLAY_VALUE && next!=NON_DISPLAY_VALUE) {
            float valueY = (float) ((1f - (current - chart.getDataRange().getMinValue()) / (chart.getDataRange().getValueRange()))
                    * (chart.getDataQuadrant().getPaddingHeight()) + chart.getDataQuadrant().getPaddingStartY());
            
            float nextValueY = (float) ((1f - (next - chart.getDataRange().getMinValue()) / (chart.getDataRange().getValueRange()))
                    * (chart.getDataQuadrant().getPaddingHeight()) + chart.getDataQuadrant().getPaddingStartY());
            
            canvas.drawLine(left, valueY, right, nextValueY, linePaint);
        }
    }

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.mole.RectMole#setUp(cn.limc.androidcharts.common.IChart, float, float)
     */
    @Override
    public void setUp(IChart chart, float from, float width) {
        super.setUp(chart);
        left = from ;
        right = from + width;
        
    }
    
    
    public void setUp(IChart chart ,double lineData,double nextData, float from , float width) {
        this.setUp(chart,from,width);
        this.current = lineData;
        this.next = nextData;
    }


    /**
     * @return the linePaint
     */
    public Paint getLinePaint() {
        return linePaint;
    }

    /**
     * @param linePaint the linePaint to set
     */
    public void setLinePaint(Paint linePaint) {
        this.linePaint = linePaint;
    }
}
