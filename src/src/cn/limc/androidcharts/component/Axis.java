/*
 * Axis.java
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

import cn.limc.androidcharts.model.Degree;
import android.graphics.Canvas;
import android.graphics.Color;

/**
 * <p>
 * en
 * </p>
 * <p>
 * jp
 * </p>
 * <p>
 * cn
 * </p>
 * 
 * @author limc
 * @version v1.0 2014/06/24 17:17:55
 * 
 */
public interface Axis {
    public static final int AXIS_X_POSITION_BOTTOM = 1 << 0;
    public static final int AXIS_X_POSITION_TOP = 1 << 1;
    public static final int AXIS_Y_POSITION_LEFT = 1 << 2;
    public static final int AXIS_Y_POSITION_RIGHT = 1 << 3;

    static final int DEFAULT_LINE_COLOR = Color.LTGRAY;
    static final float DEFAULT_LINE_WIDTH = 1f;
    static final int DEFAULT_POSITION = AXIS_X_POSITION_BOTTOM;

    /**
     * <p>
     * default color of text for the longitude　degrees display
     * </p>
     * <p>
     * 経度のタイトルの色のデフォルト値
     * </p>
     * <p>
     * 经线刻度字体颜色
     * </p>
     */
    static final int DEFAULT_DEGREE_FONT_COLOR = Color.WHITE;
    
    
    
    static final int ALIGN_TYPE_CENTER = 0;
    static final int ALIGN_TYPE_JUSTIFY = 1;
    
    
    public static final int DEFAULT_ALIGN_TYPE = ALIGN_TYPE_CENTER;
    
    /**
     * <p>
     * default numbers of grid‘s latitude line
     * </p>
     * <p>
     * 緯線の数量のデフォルト値
     * </p>
     * <p>
     * 网格纬线的数量
     * </p>
     */
    public static final int DEFAULT_TITLES_NUM = 4;


    /**
     * <p>
     * default font size of text for the longitude　degrees display
     * </p>
     * <p>
     * 経度のタイトルのフォントサイズのデフォルト値
     * </p>
     * <p>
     * 经线刻度字体大小
     * </p>
     */
    static final int DEFAULT_DEGREE_FONT_SIZE = 12;
    
    void drawLine(Canvas canvas);
    void drawDegrees(Canvas canvas);
    void draw(Canvas canvas);
    int getLineColor();
    void setLineColor(int color);
    float getLineWidth();
    void setLineWidth(float width);
    int getPosition();
    void setPosition(int position);
    int getDegreeFontColor();
    void setDegreeFontColor(int color);
    int getDegreeFontSize();
    void setDegreeFontSize(int size);
    Degree getDegree();
   // void setDegree(Degree degree);
    DataComponent getBindComponent();
   // void setBindComponent(DataComponent component);
    
    float postForIndex(int index);
    int titlesNum();
}
