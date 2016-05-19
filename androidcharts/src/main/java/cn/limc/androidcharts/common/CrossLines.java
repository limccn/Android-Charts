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

package cn.limc.androidcharts.common;

import android.R.color;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Paint.Style;
import android.util.Log;

import cn.limc.androidcharts.axis.Axis;
import cn.limc.androidcharts.view.DataGridChart;
import cn.limc.androidcharts.view.GridChart;

/** 
 * <p>en</p>
 * <p>jp</p>
 * <p>cn</p>
 *
 * @author limc 
 * @version v1.0 2014/06/24 18:30:44 
 *  
 */
public class CrossLines implements ICrossLines {
    protected GridChart inChart;
    
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

    private boolean displayCrossXDegreeOnTouch = DEFAULT_DISPLAY_CROSS_X_DEGREE_ON_TOUCH;



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

    private boolean displayCrossYDegreeOnTouch = DEFAULT_DISPLAY_CROSS_Y_DEGREE_ON_TOUCH;

    protected int bindCrossLinesToStick = DEFAULT_BIND_CROSS_LINES_TO_STICK;
	
	
    public CrossLines(GridChart inChart){
        super();
        this.inChart = inChart;
    }
	
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

    public boolean isDisplayCrossXDegreeOnTouch() {
        return displayCrossXDegreeOnTouch;
    }

    public void setDisplayCrossXDegreeOnTouch(boolean displayCrossXDegreeOnTouch) {
        this.displayCrossXDegreeOnTouch = displayCrossXDegreeOnTouch;
    }

    public boolean isDisplayCrossYDegreeOnTouch() {
        return displayCrossYDegreeOnTouch;
    }

    public void setDisplayCrossYDegreeOnTouch(boolean displayCrossYDegreeOnTouch) {
        this.displayCrossYDegreeOnTouch = displayCrossYDegreeOnTouch;
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

        
        PointF touchPoint = inChart.getTouchPoint();
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
        mPaint.setStyle(Style.STROKE);
        mPaint.setStrokeWidth(2.0f);
        mPaint.setPathEffect(new DashPathEffect(
                new float[]{6, 3, 6, 3}, 1));

        float lineVLength = inChart.getDataQuadrant().getHeight() 
                + inChart.getAxisX().getLineWidth();


        Path path = new Path();
        path.moveTo(touchPoint.x, inChart.getBorderWidth());
        path.lineTo(touchPoint.x, lineVLength);
        canvas.drawPath(path, mPaint);


        if (displayCrossXDegreeOnTouch) {
            int fontSize = inChart.getSimpleGrid().getLongitudeFontSize();
            String textToDraw = inChart.calcAxisXGraduate();

            Paint mPaintBox = new Paint();
            mPaintBox.setColor(Color.LTGRAY);
            // mPaintBox.setAlpha(80);
            mPaintBox.setStyle(Style.FILL);

            Paint mPaintBoxText = new Paint();
            mPaintBoxText.setColor(Color.BLACK);
            mPaintBoxText.setAntiAlias(true);
            mPaintBoxText.setTextSize(fontSize);

            float textWidth = mPaintBoxText.measureText(textToDraw);

            PointF boxHS = new PointF(inChart.getTouchPoint().x - textWidth / 2.0f, inChart.getBorderWidth());
            PointF boxHE = new PointF(inChart.getTouchPoint().x + textWidth / 2.0f, inChart.getBorderWidth() + fontSize + 4);

            // draw a rectangle
            canvas.drawRect(boxHS.x, boxHS.y, boxHE.x, boxHE.y, mPaintBox);
            // draw text
            canvas.drawText(textToDraw, boxHS.x, boxHS.y + fontSize, mPaintBoxText);
        }
    }

    protected void drawHorizontalLine(Canvas canvas) {

        
        PointF touchPoint = inChart.getTouchPoint();
        
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
        mPaint.setStyle(Style.STROKE);
        mPaint.setStrokeWidth(2.0f);
        mPaint.setPathEffect(new DashPathEffect(
                new float[] { 6, 3, 6, 3 }, 1));

        float lineHLength = inChart.getDataQuadrant().getWidth() + inChart.getAxisY().getLineWidth();

        Path path = new Path();
        path.moveTo(inChart.getBorderWidth(), inChart.getTouchPoint().y);
        path.lineTo(inChart.getBorderWidth() + lineHLength,
                inChart.getTouchPoint().y);
        canvas.drawPath(path, mPaint);

        if (displayCrossYDegreeOnTouch) {
            int fontSize = inChart.getSimpleGrid().getLatitudeFontSize();
            String textToDraw = inChart.calcAxisYGraduate();

            Paint mPaintBox = new Paint();
            mPaintBox.setColor(Color.LTGRAY);
            // mPaintBox.setAlpha(80);
            mPaintBox.setStyle(Style.FILL);

            Paint mPaintBoxText = new Paint();
            mPaintBoxText.setColor(Color.BLACK);
            mPaintBoxText.setAntiAlias(true);
            mPaintBoxText.setTextSize(fontSize);

            float textWidth = mPaintBoxText.measureText(textToDraw);

            PointF boxHS = new PointF(inChart.getBorderWidth(), inChart.getTouchPoint().y - fontSize / 2.f - 4);
            PointF boxHE = new PointF(inChart.getBorderWidth() + textWidth, inChart.getTouchPoint().y + fontSize / 2.f + 4);

            // draw a rectangle
            canvas.drawRect(boxHS.x, boxHS.y, boxHE.x, boxHE.y, mPaintBox);
            // draw text
            canvas.drawText(textToDraw, boxHS.x, boxHS.y + fontSize, mPaintBoxText);
        }
    }
    
    public void draw(Canvas canvas){
        if (displayCrossXOnTouch|| displayCrossYOnTouch) {
            drawHorizontalLine(canvas);
            drawVerticalLine(canvas);
        }
    }
}
