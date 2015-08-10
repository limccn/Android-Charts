/*
 * CrossLines.java
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
import android.graphics.Paint;
import android.graphics.PointF;
import cn.limc.androidcharts.model.DataCursor;
import cn.limc.androidcharts.series.IMeasurable;

/** 
 * <p>en</p>
 * <p>jp</p>
 * <p>cn</p>
 *
 * @author limc 
 * @version v1.0 2014/06/24 18:30:44 
 *  
 */
public class CrossLines extends AbstractComponent {
    
    public static final int BIND_TO_TYPE_NONE = 0;
    public static final int BIND_TO_TYPE_HIRIZIONAL = 1;
    public static final int BIND_TO_TYPE_VERTICAL = 2;
    public static final int BIND_TO_TYPE_BOTH = 3;
    
    public static final int DISPLAY_NONE = 0;
    public static final int DISPLAY_HIRIZIONAL = 1;
    public static final int DISPLAY_VERTICAL = 2;
    public static final int DISPLAY_BOTH = 3;
    
    public static final int DEFAULT_CROSS_LINES_COLOR = Color.CYAN;
    public static final int DEFAULT_CROSS_LINES_FONT_COLOR = Color.CYAN;
    
    /**
     * <p>
     * Should display the Y cross line if grid is touched?
     * </p>
     * <p>
     * タッチしたポイントがある場合、十字線の垂直線を表示するか?
     * </p>
     * <p>
     * 默认在控件被点击时，显示十字竖线线
     * </p>
     */
    public static final boolean DEFAULT_DISPLAY_CROSS_X_ON_TOUCH = true;

    /**
     * <p>
     * Should display the Y cross line if grid is touched?
     * </p>
     * <p>
     * タッチしたポイントがある場合、十字線の水平線を表示するか?
     * </p>
     * <p>
     * 默认在控件被点击时，显示十字横线线
     * </p>
     */
    public static final boolean DEFAULT_DISPLAY_CROSS_Y_ON_TOUCH = true;
    
    
    public static final int DEFAULT_BIND_CROSS_LINES_TO_STICK = BIND_TO_TYPE_BOTH;
    
    protected DataComponent bindComponent;
    
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
	private int crossLinesColor = DEFAULT_CROSS_LINES_COLOR;

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
	private int crossLinesFontColor = DEFAULT_CROSS_LINES_FONT_COLOR;
	
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
	private boolean displayCrossXOnTouch = DEFAULT_DISPLAY_CROSS_X_ON_TOUCH;

	/**
	 * <p>
	 * Should display the Y cross line if grid is touched?
	 * </p>
	 * <p>
	 * タッチしたポイントがある場合、十字線の水平線を表示するか?
	 * </p>
	 * <p>
	 * 在控件被点击时，显示十字横线线
	 * </p>
	 */
	private boolean displayCrossYOnTouch = DEFAULT_DISPLAY_CROSS_Y_ON_TOUCH;
	
	
	protected int bindCrossLinesToStick = DEFAULT_BIND_CROSS_LINES_TO_STICK;
	
	
	
	/**
	 * @return the displayCrossXOnTouch
	 */
	public boolean isDisplayCrossXOnTouch() {
		return displayCrossXOnTouch;
	}

	/**
	 * @param displayCrossXOnTouch
	 *            the displayCrossXOnTouch to set
	 */
	public void setDisplayCrossXOnTouch(boolean displayCrossXOnTouch) {
		this.displayCrossXOnTouch = displayCrossXOnTouch;
	}

	/**
	 * @return the displayCrossYOnTouch
	 */
	public boolean isDisplayCrossYOnTouch() {
		return displayCrossYOnTouch;
	}

	/**
	 * @param displayCrossYOnTouch
	 *            the displayCrossYOnTouch to set
	 */
	public void setDisplayCrossYOnTouch(boolean displayCrossYOnTouch) {
		this.displayCrossYOnTouch = displayCrossYOnTouch;
	}
	
	/**
	 * @return the crossLinesColor
	 */
	public int getCrossLinesColor() {
		return crossLinesColor;
	}

	/**
	 * @param crossLinesColor
	 *            the crossLinesColor to set
	 */
	public void setCrossLinesColor(int crossLinesColor) {
		this.crossLinesColor = crossLinesColor;
	}

	/**
	 * @return the crossLinesFontColor
	 */
	public int getCrossLinesFontColor() {
		return crossLinesFontColor;
	}

	/**
	 * @param crossLinesFontColor
	 *            the crossLinesFontColor to set
	 */
	public void setCrossLinesFontColor(int crossLinesFontColor) {
		this.crossLinesFontColor = crossLinesFontColor;
	}

    /**
     * @return the bindCrossLinesToStick
     */
    public int getBindCrossLinesToStick() {
        return bindCrossLinesToStick;
    }

    /**
     * @param bindCrossLinesToStick the bindCrossLinesToStick to set
     */
    public void setBindCrossLinesToStick(int bindCrossLinesToStick) {
        this.bindCrossLinesToStick = bindCrossLinesToStick;
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
    protected void drawVerticalLine(Canvas canvas) {

//        if (!displayLongitudeTitle) {
//            return;
//        }
        
        PointF touchPoint = bindComponent.getParent().getTouchPoint();
        if (!displayCrossXOnTouch) {
            return;
        }
        if (touchPoint == null) {
            return;
        }
        if (touchPoint.x <= 0) {
            return;
        }

        Paint mPaint = new Paint();
        mPaint.setColor(crossLinesColor);

//        float lineVLength = inChart.getDataQuadrant().getHeight() 
//                + inChart.getAxisX().getLineWidth();
//        float lineVLength = getHeight()

//        // TODO calculate points to draw
//        PointF boxVS = new PointF(touchPoint.x - longitudeFontSize * 5f / 2f,
//                borderWidth + lineVLength);
//        PointF boxVE = new PointF(touchPoint.x + longitudeFontSize * 5f / 2f,
//                borderWidth + lineVLength + axisXTitleQuadrantHeight);
//
//        // draw text
//        drawAlphaTextBox(boxVS, boxVE, getAxisXGraduate(touchPoint.x),
//                longitudeFontSize, canvas);

        canvas.drawLine(touchPoint.x, getStartY(), touchPoint.x, getEndY(),
                mPaint);
    }

    protected void drawHorizontalLine(Canvas canvas) {

//        if (!displayLatitudeTitle) {
//            return;
//        }
        
        PointF touchPoint = bindComponent.getParent().getTouchPoint();
        
        if (!displayCrossYOnTouch) {
            return;
        }
        if (touchPoint == null) {
            return;
        }
        if (touchPoint.y <= 0) {
            return;
        }

        Paint mPaint = new Paint();
        mPaint.setColor(crossLinesColor);

//        float lineHLength = inChart.getDataQuadrant().getWidth() + inChart.getAxisY().getLineWidth();
//
//        if (inChart.getAxisY().getPosition() == AbstractAxis.AXIS_Y_POSITION_LEFT) {
////            PointF boxHS = new PointF(borderWidth, touchPoint.y
////                    - latitudeFontSize / 2f - 2);
////            PointF boxHE = new PointF(borderWidth + axisYTitleQuadrantWidth,
////                    touchPoint.y + latitudeFontSize / 2f + 2);
////
////            // draw text
////            drawAlphaTextBox(boxHS, boxHE, getAxisYGraduate(touchPoint.y),
////                    latitudeFontSize, canvas);
//
//            canvas.drawLine(inChart.getBorderWidth() + inChart.getAxisY().getWidth(), touchPoint.y,
//                    inChart.getBorderWidth() + inChart.getAxisY().getWidth() + lineHLength,
//                    touchPoint.y, mPaint);
//        } else {
////            PointF boxHS = new PointF(super.getWidth() - borderWidth
////                    - axisYTitleQuadrantWidth, touchPoint.y - latitudeFontSize
////                    / 2f - 2);
////            PointF boxHE = new PointF(super.getWidth() - borderWidth,
////                    touchPoint.y + latitudeFontSize / 2f + 2);
////
////            // draw text
////            drawAlphaTextBox(boxHS, boxHE, getAxisYGraduate(touchPoint.y),
////                    latitudeFontSize, canvas);
//
//            
//            canvas.drawLine(inChart.getBorderWidth(), inChart.getTouchPoint().y , inChart.getBorderWidth() + lineHLength,
//                    inChart.getTouchPoint().y , mPaint);
//        }
        
        canvas.drawLine(getStartX(), bindComponent.getParent().getTouchPoint().y , getEndX(), bindComponent.getParent().getTouchPoint().y , mPaint);

    }
    
    public void draw(Canvas canvas){
        if (displayCrossXOnTouch|| displayCrossYOnTouch) {
            drawHorizontalLine(canvas);
            drawVerticalLine(canvas);
        }
    }
    
    public int calcSelectedIndex(float x ,float y) {
        DataCursor dataCursor = bindComponent.getDataCursor();
        float graduate = bindComponent.getWidthRate(y);
        int index = (int) Math.floor(graduate * dataCursor.getDisplayNumber());

        if (index >= dataCursor.getDisplayNumber()) {
            index = dataCursor.getDisplayNumber() - 1;
        } else if (index < 0) {
            index = 0;
        }
        return dataCursor.getDisplayFrom() + index;
    }   
    
    public PointF calcTouchedPoint(float x ,float y) {
//        if (!isValidTouchPoint(x,y)) {
//            return new PointF(0,0);
//        }
        if (bindCrossLinesToStick== CrossLines.BIND_TO_TYPE_NONE) {
            return new PointF(x, y);
        } else if (bindCrossLinesToStick == CrossLines.BIND_TO_TYPE_BOTH) {
            PointF bindPointF = calcBindPoint(x, y);
            return bindPointF;
        } else if (bindCrossLinesToStick == CrossLines.BIND_TO_TYPE_HIRIZIONAL) {
            PointF bindPointF = calcBindPoint(x, y);
            return new PointF(bindPointF.x, y);
        } else if (bindCrossLinesToStick == CrossLines.BIND_TO_TYPE_VERTICAL) {
            PointF bindPointF = calcBindPoint(x, y);
            return new PointF(x, bindPointF.y);
        } else {
            return new PointF(x, y);
        }   
    }
    
    public PointF calcBindPoint(float x ,float y) {
        float calcX = 0;
        float calcY = 0;
        
        int index = calcSelectedIndex(x,y);
        float stickWidth = getPaddingWidth() / bindComponent.getDataCursor().getDisplayNumber();
        IMeasurable stick = (IMeasurable)bindComponent.getChartData().getChartTable().get(index);
        calcY = (float) (bindComponent.getDataRange().valueForRate(stick.getHigh()) * (getPaddingHeight()) + getPaddingStartY());
        calcX = getPaddingStartX() + stickWidth * (index - bindComponent.getDataCursor().getDisplayFrom()) + stickWidth / 2;
        
        return new PointF(calcX,calcY);
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
}
