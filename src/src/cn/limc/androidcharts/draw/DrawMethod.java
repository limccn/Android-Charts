//
// DrawMethod.java
// cn.limc.androidcharts.draw
//
// Created by limc on 2015-7-29.
//
// Copyright 2015 Shanghai Okasan-Huada Computer System Co. Ltd., All rights reserved.
//
package cn.limc.androidcharts.draw;

import android.R.integer;

/**
 * DrawMethod
 * Description: <br>
 *   <p>add description here </p>
 * Tags: <br>
 *   <p> </p>
 *
 * @author limc
 * @version v1.0 
 * 
 * History: <br>
 * 2015-7-29 limc create v1.0 <br>
 *
 */
public abstract class DrawMethod {
    
    public static final int LEFT_TO_RIGHT = 1;
    public static final int RIGHT_TO_LEFT = 2;
    public static final int BOTTOM_TO_TOP = 3;
    public static final int TOP_TO_BOTTOM = 4;
    
    public static final int CENTER_TO_EDGE = 5;
    public static final int EDGE_TO_CENTER = 6;
    
    public static final int CLOCK_WISE = 7;
    public static final int COUNTER_CLOCK_WISE = 8;
   
    
    /**
     * 
     */
    public DrawMethod() {
        // TODO Auto-generated constructor stub
    }
}
