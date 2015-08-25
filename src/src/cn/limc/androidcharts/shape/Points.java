//
// Points.java
// cn.limc.androidcharts.shape
//
// Created by limc on 2015-7-24.
//
// Copyright 2015 Shanghai Okasan-Huada Computer System Co. Ltd., All rights reserved.
//
package cn.limc.androidcharts.shape;

import cn.limc.androidcharts.component.DataComponent;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Points
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
public class Points extends AbstractShape implements Rectangle{

    public static double NON_DISPLAY_VALUE = 0; 
    private Paint linePaint;
    
    private double current;
    private double next;
    
    /**
     * 
     */
    public Points() {
        super();
        linePaint = new Paint();
    }

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.shape.Shape#draw(android.graphics.Canvas)
     */
    @Override
    public void draw(Canvas canvas) {
        if (current!=NON_DISPLAY_VALUE && next!=NON_DISPLAY_VALUE) {
            float valueY = (float) component.heightForRate(current);
            float nextValueY = (float) component.heightForRate(next);
            canvas.drawLine(left, valueY, right, nextValueY, linePaint);
        }
    }

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.shape.Rectangle#setUp(cn.limc.androidcharts.common.IChart, float, float)
     */
    @Override
    public void setUp(DataComponent component, float from, float width) {
        super.setUp(component);
        left = from ;
        right = from + width;
        
    }
    
    
    public void setUp(DataComponent component ,double lineData,double nextData, float from , float width) {
        this.setUp(component,from,width);
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
