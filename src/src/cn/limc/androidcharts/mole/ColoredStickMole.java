//
// ColoredStickMole.java
// cn.limc.androidcharts.mole
//
// Created by limc on 2015-7-22.
//
// Copyright 2015 Shanghai Okasan-Huada Computer System Co. Ltd., All rights reserved.
//
package cn.limc.androidcharts.mole;

import cn.limc.androidcharts.entity.ColoredStickEntity;
import cn.limc.androidcharts.entity.IMeasurable;

/**
 * ColoredStickMole
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
public class ColoredStickMole extends StickMole {
    
    public static final int DEFAULT_COLORED_STICK_STYLE_WITH_BORDER = 0;
    public static final int DEFAULT_COLORED_STICK_STYLE_NO_BORDER = 1;
    public static final int DEFAULT_COLORED_STICK_STYLE = DEFAULT_COLORED_STICK_STYLE_NO_BORDER;

    private int coloredStickStyle = DEFAULT_COLORED_STICK_STYLE_NO_BORDER;
    
    /**
     * @return the coloredStickStyle
     */
    public int getColoredStickStyle() {
        return coloredStickStyle;
    }

    /**
     * @param coloredStickStyle
     *            the coloredStickStyle to set
     */
    public void setColoredStickStyle(int coloredStickStyle) {
        this.coloredStickStyle = coloredStickStyle;
    }
    
    @Override
    public void setStickData(IMeasurable stickData) {
        super.setStickData(stickData);  
        this.stickFillColor = ((ColoredStickEntity)stickData).getColor();
                
        if (coloredStickStyle == DEFAULT_COLORED_STICK_STYLE_NO_BORDER) {
            this.stickBorderColor = this.stickFillColor;
        }
    }
}
