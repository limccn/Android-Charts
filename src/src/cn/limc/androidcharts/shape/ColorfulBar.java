//
// ColorfulBar.java
// cn.limc.androidcharts.shape
//
// Created by limc on 2015-7-22.
//
// Copyright 2015 Shanghai Okasan-Huada Computer System Co. Ltd., All rights reserved.
//
package cn.limc.androidcharts.shape;

import cn.limc.androidcharts.series.ColoredStickEntity;
import cn.limc.androidcharts.series.IMeasurable;

/**
 * ColorfulBar
 * Description: <br>
 *   <p>add description here </p>
 * Tags: <br>
 *   <p> </p>
 *
 * @author limc
 * @version v1.0 
 * 
 * History: <br>
 * 2015-7-22 limc create v1.0 <br>
 *
 */
public class ColorfulBar extends Bar {
    
    public static final int DEFAULT_BAR_STYLE_WITH_BORDER = 0;
    public static final int DEFAULT_BAR_STYLE_NO_BORDER = 1;
    
    public static final int DEFAULT_BAR_STYLE = DEFAULT_BAR_STYLE_NO_BORDER;

    private int barStyle = DEFAULT_BAR_STYLE_NO_BORDER;
    
    /**
     * @return the barStyle
     */
    public int getColoredStickStyle() {
        return barStyle;
    }

    /**
     * @param barStyle
     *            the barStyle to set
     */
    public void setColoredStickStyle(int coloredStickStyle) {
        this.barStyle = coloredStickStyle;
    }
    
    @Override
    public void setStickData(IMeasurable stickData) {
        super.setStickData(stickData);  
        this.barFillColor = ((ColoredStickEntity)stickData).getColor();
                
        if (barStyle == DEFAULT_BAR_STYLE_NO_BORDER) {
            this.barBorderColor = this.barFillColor;
        }
    }
}
