/*
 * Indicator.java
 * Android-Charts
 *
 * Created by limc on 2014.
 *
 * Copyright 2011 limc.cn All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package cn.limc.androidcharts.component;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PointF;


/** 
 * <p>en</p>
 * <p>jp</p>
 * <p>cn</p>
 *
 * @author limc 
 * @version v1.0 2014/06/24 18:30:44 
 *  
 */
public interface Indicator {
    
    public static final int BIND_TO_TYPE_NONE = 0;
    public static final int BIND_TO_TYPE_BOTOM = 1;
    public static final int BIND_TO_TYPE_CENTER = 2;
    public static final int BIND_TO_TYPE_TOP = 3;
    public static final int BIND_TO_TYPE_LEFT = 4;
    public static final int BIND_TO_TYPE_MIDDLE = 5;
    public static final int BIND_TO_TYPE_RIGHT = 6;
    
    public static final int DEFAULT_INDICATOR_LINE_COLOR = Color.CYAN;
    public static final int DEFAULT_INDICATOR_TEXT_FONT_COLOR = Color.CYAN;
    public static final int DEFAULT_INDICATOR_TEXT_FONT_SIZE = Color.CYAN;
    
    
    public static final boolean DEFAULT_DISPLAY_INDICATOR_LINE = true;
    public static final boolean DEFAULT_DISPLAY_INDICATOR_TEXT = true;
    
    public static final int DEFAULT_BIND_TO_STYLE = BIND_TO_TYPE_NONE;

    
    void draw(Canvas canvas);
    
    int calcSelectedIndex(float x, float y);
    int calcSelectedIndex(PointF pt);
    PointF calcTouchedPoint(float x, float y);
    PointF calcBindPoint(float x, float y);
    
    int getLineColor();
    void setLineColor(int color);
    int getTextFontColor();
    void setTextFontColor(int color);
    boolean isDisplayLine();
    void setDisplayLine(boolean displayLine);
    boolean isDisplayText();
    void setDisplayText(boolean displayText);
    int getBindToStyle();
    void setBindToStyle(int style);
   
}
