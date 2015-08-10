//
// Layer.java
// cn.limc.androidcharts.component
//
// Created by limc on 2015-7-30.
//
// Copyright 2015 Shanghai Okasan-Huada Computer System Co. Ltd., All rights reserved.
//
package cn.limc.androidcharts.component;

import cn.limc.androidcharts.diagram.GridChart;
import android.graphics.Canvas;
import android.graphics.RectF;

/**
 * Layer
 * Description: <br>
 *   <p>add description here </p>
 * Tags: <br>
 *   <p> </p>
 *
 * @author limc
 * @version v1.0 
 * 
 * History: <br>
 * 2015-7-30 limc create v1.0 <br>
 *
 */
public interface Layer {
    
    GridChart getParent();
    void setParent(GridChart parent);
    void FillComponent(Component component, int divide);
    
    Component getComponent();
    void setComponent(Component component);
    int getDivide();
    void setDivide(int divide);
    
    RectF rectTopLeft();

    RectF rectTopMiddle();

    RectF rectTopRight();

    RectF rectCenterLeft();

    RectF rectCenterMiddle();

    RectF rectCenterRight();

    RectF rectBottomLeft();

    RectF rectBottomMiddle();

    RectF rectBottomRight();
    
    void draw(Canvas canvas);
    
}
