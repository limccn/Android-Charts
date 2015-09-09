/*
 * AbstractIndicator.java
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

import cn.limc.androidcharts.model.DataCursor;
import android.graphics.Canvas;
import android.graphics.PointF;

public abstract class AbstractIndicator extends AbstractComponent implements Indicator {
    /**
     * <p>
     * Color of cross line inside grid when touched
     * </p>
     * <p>
     * タッチしたポイント表示用十字線の色
     * </p>
     * <p>
     * 十字交叉线颜色
     * </p>
     */
    protected int lineColor = DEFAULT_INDICATOR_LINE_COLOR;

    /**
     * <p>
     * Color of cross line degree text when touched
     * </p>
     * <p>
     * タッチしたポイント表示用十字線度数文字の色
     * </p>
     * <p>
     * 十字交叉线坐标轴字体颜色
     * </p>
     */
    protected int textFontColor = DEFAULT_INDICATOR_TEXT_FONT_COLOR;
    
    /**
     * <p>
     * Should display the Y cross line if grid is touched?
     * </p>
     * <p>
     * タッチしたポイントがある場合、十字線の垂直線を表示するか?
     * </p>
     * <p>
     * 在控件被点击时，显示十字竖线线
     * </p>
     */
    protected boolean displayLine = DEFAULT_DISPLAY_INDICATOR_LINE;
    protected boolean displayText = DEFAULT_DISPLAY_INDICATOR_TEXT;
    
    protected int bindToStyle = DEFAULT_BIND_TO_STYLE;
    
    
    public abstract void draw(Canvas canvas);
    
    /**
     * 
     */
    public AbstractIndicator() {
        // TODO Auto-generated constructor stub
    }
    
   public int calcSelectedIndex(float x ,float y) {
////        DataCursor dataCursor = dataCursorForIndicator(this);
////        float graduate = bindComponent.getWidthRate(y);
////        int index = (int) Math.floor(graduate * dataCursor.getDisplayNumber());
////
////        if (index >= dataCursor.getDisplayNumber()) {
////            index = dataCursor.getDisplayNumber() - 1;
////        } else if (index < 0) {
////            index = 0;
////        }
////        return dataCursor.getDisplayFrom() + index;
       
       return 0;
    }   
    
    public int calcSelectedIndex(PointF pt) {
        return calcSelectedIndex(pt.x,pt.y);
    }   
    
    public PointF calcTouchedPoint(float x ,float y) {

        //TODO BIND 
//         BIND_TO_TYPE_NONE = 0;
//         BIND_TO_TYPE_BOTOM = 1;
//         BIND_TO_TYPE_CENTER = 2;
//         BIND_TO_TYPE_TOP = 3;
//         BIND_TO_TYPE_LEFT = 4;
//         BIND_TO_TYPE_MIDDLE = 5;
//         BIND_TO_TYPE_RIGHT = 6;
        
       // if (bindToStyle== Indicator.BIND_TO_TYPE_NONE) {
            return new PointF(x, y);
        //} 
//        else if (bindToStyle == Indicator.BIND_TO_TYPE_BOTH) {
//            PointF bindPointF = calcBindPoint(x, y);
//            return bindPointF;
//        } else if (bindToStyle == Indicator.BIND_TO_TYPE_HIRIZIONAL) {
//            PointF bindPointF = calcBindPoint(x, y);
//            return new PointF(bindPointF.x, y);
//        } else if (bindToStyle == Indicator.BIND_TO_TYPE_VERTICAL) {
//            PointF bindPointF = calcBindPoint(x, y);
//            return new PointF(x, bindPointF.y);
//        } else {
//            return new PointF(x, y);
//        }   
    }
    
    public PointF calcBindPoint(float x ,float y) {
        DataCursor dataCursor = dataCursorForIndicator(this);
        float calcX = 0;
        float calcY = 0;
        
        int index = calcSelectedIndex(x,y);
//        float stickWidth = getPaddingWidth() / bindComponent.getDataCursor().getDisplayNumber();
//        IMeasurable stick = (IMeasurable)bindComponent.getChartData().getChartTable().get(index);
//        calcY = (float) (bindComponent.getDataRange().valueForRate(stick.getHigh()) * (getPaddingHeight()) + getPaddingStartY());
//        calcX = getPaddingStartX() + stickWidth * (index - bindComponent.getDataCursor().getDisplayFrom()) + stickWidth / 2;
//        
        return new PointF(calcX,calcY);
    }

    /**
     * @return the lineColor
     */
    public int getLineColor() {
        return lineColor;
    }

    /**
     * @param lineColor the lineColor to set
     */
    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
    }

    /**
     * @return the textFontColor
     */
    public int getTextFontColor() {
        return textFontColor;
    }

    /**
     * @param textFontColor the textFontColor to set
     */
    public void setTextFontColor(int textFontColor) {
        this.textFontColor = textFontColor;
    }

    /**
     * @return the displayLine
     */
    public boolean isDisplayLine() {
        return displayLine;
    }

    /**
     * @param displayLine the displayLine to set
     */
    public void setDisplayLine(boolean displayLine) {
        this.displayLine = displayLine;
    }

    /**
     * @return the displayText
     */
    public boolean isDisplayText() {
        return displayText;
    }

    /**
     * @param displayText the displayText to set
     */
    public void setDisplayText(boolean displayText) {
        this.displayText = displayText;
    }

    /**
     * @return the bindToStyle
     */
    public int getBindToStyle() {
        return bindToStyle;
    }

    /**
     * @param bindToStyle the bindToStyle to set
     */
    public void setBindToStyle(int bindToStyle) {
        this.bindToStyle = bindToStyle;
    }
    
    /**
     * <p>
     * draw some text with border
     * </p>
     * <p>
     * 文字を書く、枠あり
     * </p>
     * <p>
     * 绘制一段文本，并增加外框
     * </p>
     * 
     * @param ptStart
     *            <p>
     *            start point
     *            </p>
     *            <p>
     *            開始ポイント
     *            </p>
     *            <p>
     *            开始点
     *            </p>
     * 
     * @param ptEnd
     *            <p>
     *            end point
     *            </p>
     *            <p>
     *            結束ポイント
     *            </p>
     *            <p>
     *            结束点
     *            </p>
     * 
     * @param content
     *            <p>
     *            text content
     *            </p>
     *            <p>
     *            文字内容
     *            </p>
     *            <p>
     *            文字内容
     *            </p>
     * 
     * @param fontSize
     *            <p>
     *            font size
     *            </p>
     *            <p>
     *            文字フォントサイズ
     *            </p>
     *            <p>
     *            字体大小
     *            </p>
     * 
     * @param canvas
     */
//    private void drawAlphaTextBox(PointF ptStart, PointF ptEnd, String content, int color, int fontSize, Canvas canvas) {
//
//        Paint mPaintBox = new Paint();
//        mPaintBox.setColor(Color.WHITE);
//        mPaintBox.setAlpha(80);
//        mPaintBox.setStyle(Style.FILL);
//
//        Paint mPaintBoxLine = new Paint();
//        mPaintBoxLine.setColor(color);
//        mPaintBoxLine.setAntiAlias(true);
//        mPaintBoxLine.setTextSize(fontSize);
//
//        // draw a rectangle
//        canvas.drawRect(ptStart.x, ptStart.y, ptEnd.x, ptEnd.y, mPaintBox);
//
//        // draw a rectangle' border
//        canvas.drawLine(ptStart.x, ptStart.y, ptStart.x, ptEnd.y, mPaintBoxLine);
//        canvas.drawLine(ptStart.x, ptEnd.y, ptEnd.x, ptEnd.y, mPaintBoxLine);
//        canvas.drawLine(ptEnd.x, ptEnd.y, ptEnd.x, ptStart.y, mPaintBoxLine);
//        canvas.drawLine(ptEnd.x, ptStart.y, ptStart.x, ptStart.y, mPaintBoxLine);
//
//        mPaintBoxLine.setColor(color);
//        // draw text
//        canvas.drawText(content, ptStart.x, ptStart.y + fontSize, mPaintBoxLine);
//    }
    
        public abstract DataCursor dataCursorForIndicator(Indicator indicator);
        public abstract DataComponent componentForIndicator(Indicator indicator);

}
