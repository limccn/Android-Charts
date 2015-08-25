//
// VerticalIndicator.java
// cn.limc.androidcharts.component
//
// Created by limc on 2015-8-10.
//
// Copyright 2015 Shanghai Okasan-Huada Computer System Co. Ltd., All rights reserved.
//
package cn.limc.androidcharts.component;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

/**
 * VerticalIndicator
 * Description: <br>
 *   <p>add description here </p>
 * Tags: <br>
 *   <p> </p>
 *
 * @author limc
 * @version v1.0 
 * 
 * History: <br>
 * 2015-8-10 limc create v1.0 <br>
 *
 */
public abstract class VerticalIndicator extends AbstractIndicator{

    /**
     * 
     */
    public VerticalIndicator() {
        // TODO Auto-generated constructor stub
    }
    
    /* (non-Javadoc)
     * @see cn.limc.androidcharts.component.AbstractIndicator#draw(android.graphics.Canvas)
     */
    @Override
    public void draw(Canvas canvas) {
        // TODO Auto-generated method stub
        drawLine(canvas);
    }
    
    /**
     * <p>
     * draw cross line ,called when graph is touched
     * </p>
     * <p>
     * 十字線を書く、グラプをタッチたら、メソードを呼び
     * </p>
     * <p>
     * 在图表被点击后绘制十字线
     * </p>
     * 
     * @param canvas
     */
    protected void drawLine(Canvas canvas) {
        
        DataComponent bindComponent = componentForIndicator(this);

        if(bindComponent == null){
            return;
        }
        if(bindComponent.getParent() == null){
            return;
        }
        PointF touchPoint = bindComponent.getParent().getTouchPoint();
        if (touchPoint == null) {
            return;
        }
        if (!isValidTouchPoint(touchPoint)) {
            return;
        }

        Paint mPaint = new Paint();
        mPaint.setColor(lineColor);

        if (displayLine) {
            canvas.drawLine(touchPoint.x, getStartY(), touchPoint.x, getEndY(),
                    mPaint);
        }
        
        if (displayText) {
            //TODO: WHERE to draw
        }
    }
}
