/*
 * GridChart.java
 * Android-Charts
 *
 * Created by limc on 2011/05/29.
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

package cn.limc.androidcharts.view;

import java.util.List;

import cn.limc.androidcharts.axis.Axis;
import cn.limc.androidcharts.axis.HorizontalAxis;
import cn.limc.androidcharts.axis.VerticalAxis;
import cn.limc.androidcharts.common.CrossLines;
import cn.limc.androidcharts.common.DataQuadrant;
import cn.limc.androidcharts.common.IQuadrant;
import cn.limc.androidcharts.common.SimpleGrid;
import cn.limc.androidcharts.event.IGestureDetector;
import cn.limc.androidcharts.event.ITouchable;
import cn.limc.androidcharts.event.OnTouchGestureListener;
import cn.limc.androidcharts.event.TouchGestureDetector;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PathEffect;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * 
 * <p>
 * GridChart is base type of all the charts that use a grid to display like
 * line-chart stick-chart etc. GridChart implemented a simple grid with basic
 * functions what can be used in it's inherited charts.
 * </p>
 * <p>
 * GridChartは全部グリドチャートのベスクラスです、一部処理は共通化け実現した。
 * </p>
 * <p>
 * GridChart是所有网格图表的基础类对象，它实现了基本的网格图表功能，这些功能将被它的继承类使用
 * </p>
 * 
 * @author limc
 * @version v1.0 2011/05/30 14:19:50
 * 
 */
public class GridChart extends AbstractBaseChart implements ITouchable {
	/**
	 * <p>
	 * Touched point inside of grid
	 * </p>
	 * <p>
	 * タッチしたポイント
	 * </p>
	 * <p>
	 * 单点触控的选中点
	 * </p>
	 */
	protected PointF touchPoint;

	/**
	 * <p>
	 * Event will notify objects' list
	 * </p>
	 * <p>
	 * イベント通知対象リスト
	 * </p>
	 * <p>
	 * 事件通知对象列表
	 * </p>
	 */
	
	protected OnTouchGestureListener onTouchGestureListener = new OnTouchGestureListener();
	protected IGestureDetector touchGestureDetector = new TouchGestureDetector<ITouchable>(this);
	protected IQuadrant dataQuadrant = new DataQuadrant(this);
	protected HorizontalAxis axisX = new HorizontalAxis(this,Axis.AXIS_X_POSITION_BOTTOM);
	protected VerticalAxis axisY = new VerticalAxis(this, Axis.AXIS_Y_POSITION_LEFT);
	protected CrossLines crossLines = new CrossLines(this);
	protected SimpleGrid simpleGrid = new SimpleGrid(this);
	
	/*
	 * (non-Javadoc)
	 * 
	 * @param context
	 * 
	 * @see cn.limc.androidcharts.view.AbstractBaseChart#BaseChart(Context)
	 */
	public GridChart(Context context) {
		super(context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @param context
	 * 
	 * @param attrs
	 * 
	 * @param defStyle
	 * 
	 * @see cn.limc.androidcharts.view.AbstractBaseChart#BaseChart(Context,
	 * AttributeSet, int)
	 */
	public GridChart(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @param context
	 * 
	 * @param attrs
	 * 
	 * @see cn.limc.androidcharts.view.AbstractBaseChart#BaseChart(Context,
	 * AttributeSet)
	 */
	public GridChart(Context context, AttributeSet attrs) {
		super(context, attrs);
	}


	public void drawData(Canvas canvas){

	}
	/*
	 * (non-Javadoc)
	 * 
	 * <p>Called when is going to draw this chart<p> <p>チャートを書く前、メソッドを呼ぶ<p>
	 * <p>绘制图表时调用<p>
	 * 
	 * @param canvas
	 * 
	 * @see android.view.View#onDraw(android.graphics.Canvas)
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		axisX.draw(canvas);
		axisY.draw(canvas);
		simpleGrid.drawGrid(canvas);

		this.drawData(canvas);

		simpleGrid.drawTitles(canvas);
		crossLines.draw(canvas);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * <p>Called when chart is touched<p> <p>チャートをタッチしたら、メソッドを呼ぶ<p>
	 * <p>图表点击时调用<p>
	 * 
	 * @param event
	 * 
	 * @see android.view.View#onTouchEvent(MotionEvent)
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (!isValidTouchPoint(event.getX(),event.getY())) {
			return false;
		}
		return touchGestureDetector.onTouchEvent(event);
	}
	
	protected boolean isValidTouchPoint (float x , float y) {
		if (x < dataQuadrant.getPaddingStartX()
				|| x > dataQuadrant.getPaddingEndX()) {
			return false;
		}
		if (y < dataQuadrant.getPaddingStartY()
				|| y > dataQuadrant.getPaddingEndY()) {
			return false;
		}
		return true;
	}
	/**
	 * <p>
	 * calculate degree title on X axis
	 * </p>
	 * <p>
	 * X軸の目盛を計算する
	 * </p>
	 * <p>
	 * 计算X轴上显示的坐标值
	 * </p>
	 * 
	 * @param value
	 *            <p>
	 *            value for calculate
	 *            </p>
	 *            <p>
	 *            計算有用データ
	 *            </p>
	 *            <p>
	 *            计算用数据
	 *            </p>
	 * 
	 * @return String
	 *         <p>
	 *         degree
	 *         </p>
	 *         <p>
	 *         目盛
	 *         </p>
	 *         <p>
	 *         坐标值
	 *         </p>
	 */
	public String getAxisXGraduate(Object value) {
		float valueLength = ((Float) value).floatValue()
				- dataQuadrant.getPaddingStartX();
		return String.valueOf(valueLength / this.dataQuadrant.getPaddingWidth());
	}

	/**
	 * <p>
	 * calculate degree title on Y axis
	 * </p>
	 * <p>
	 * Y軸の目盛を計算する
	 * </p>
	 * <p>
	 * 计算Y轴上显示的坐标值
	 * </p>
	 * 
	 * @param value
	 *            <p>
	 *            value for calculate
	 *            </p>
	 *            <p>
	 *            計算有用データ
	 *            </p>
	 *            <p>
	 *            计算用数据
	 *            </p>
	 * 
	 * @return String
	 *         <p>
	 *         degree
	 *         </p>
	 *         <p>
	 *         目盛
	 *         </p>
	 *         <p>
	 *         坐标值
	 *         </p>
	 */
	public String getAxisYGraduate(Object value) {
		float valueLength = ((Float) value).floatValue()
				- dataQuadrant.getPaddingStartY();
		return String
				.valueOf(1f - valueLength / this.dataQuadrant.getPaddingHeight());
	}



	public String calcAxisXGraduate() {
		return "";
	}

	public String calcAxisYGraduate() {
		return "";
	}

	public long touchPointAxisXValue() {
		return 0;
	}

	public double touchPointAxisYValue() {
		return 0;
	}

	/**
	 * @return the axisXColor
	 */
	public int getAxisXColor() {
		return axisX.getLineColor();
	}

	/**
	 * @param axisXColor
	 *            the axisXColor to set
	 */
	public void setAxisXColor(int axisXColor) {
		this.axisX.setLineColor(axisXColor);
	}

	/**
	 * @return the axisYColor
	 */
	public int getAxisYColor() {
		return axisY.getLineColor();
	}

	/**
	 * @param axisYColor
	 *            the axisYColor to set
	 */
	public void setAxisYColor(int axisYColor) {
		this.axisY.setLineColor(axisYColor);
	}
	/**
	 * @return the axisWidth
	 */
	public float getAxisXWidth() {
		return axisX.getLineWidth();
	}

	/**
	 * @param axisWidth
	 *            the axisWidth to set
	 */
	public void setAxisXWidth(float axisWidth) {
		this.axisX.setLineWidth(axisWidth);
	}
//
//	/**
//	 * @return the axisMarginLeft
//	 */
//	public float getAxisYTitleQuadrantWidth() {
//		return axisYTitleQuadrantWidth;
//	}
//
//	/**
//	 * @param axisYTitleQuadrantWidth
//	 *            the axisYTitleQuadrantWidth to set
//	 */
//	public void setAxisYTitleQuadrantWidth(float axisYTitleQuadrantWidth) {
//		this.axisYTitleQuadrantWidth = axisYTitleQuadrantWidth;
//	}
//
//	/**
//	 * @return the axisXTitleQuadrantHeight
//	 */
//	public float getAxisXTitleQuadrantHeight() {
//		return axisXTitleQuadrantHeight;
//	}
//
//	/**
//	 * @param axisXTitleQuadrantHeight
//	 *            the axisXTitleQuadrantHeight to set
//	 */
//	public void setAxisXTitleQuadrantHeight(float axisXTitleQuadrantHeight) {
//		this.axisXTitleQuadrantHeight = axisXTitleQuadrantHeight;
//	}



	/**
	 * @return the longitudeTitles
	 */
	public List<String> getLongitudeTitles() {
		return simpleGrid.getLongitudeTitles();
	}

	/**
	 * @param longitudeTitles
	 *            the longitudeTitles to set
	 */
	public void setLongitudeTitles(List<String> longitudeTitles) {
		this.simpleGrid.setLongitudeTitles(longitudeTitles);
	}

	/**
	 * @return the latitudeTitles
	 */
	public List<String> getLatitudeTitles() {
		return simpleGrid.getLatitudeTitles();
	}

	/**
	 * @param latitudeTitles
	 *            the latitudeTitles to set
	 */
	public void setLatitudeTitles(List<String> latitudeTitles) {
	    this.simpleGrid.setLatitudeTitles(latitudeTitles);
	}

	/**
	 * @return the latitudeMaxTitleLength
	 */
	public int getLatitudeMaxTitleLength() {
		return simpleGrid.getLatitudeMaxTitleLength();
	}

	/**
	 * @param latitudeMaxTitleLength
	 *            the latitudeMaxTitleLength to set
	 */
	public void setLatitudeMaxTitleLength(int latitudeMaxTitleLength) {
		this.simpleGrid.setLatitudeMaxTitleLength(latitudeMaxTitleLength) ;
	}


	/**
	 * @return the clickPostX
	 */
	@Deprecated
	public float getClickPostX() {
		if (touchPoint == null) {
			return 0f;
		}else{
			return touchPoint.x;
		}

	}

	/**
	 * @param clickPostX
	 *            the clickPostX to set
	 */
	@Deprecated
	public void setClickPostX(float clickPostX) {
		if (clickPostX >= 0) {
			this.touchPoint.x = clickPostX;
		}
	}

	/**
	 * @return the clickPostY
	 */
	@Deprecated
	public float getClickPostY() {
		if (touchPoint == null) {
			return 0f;
		} else {
			return touchPoint.y;
		}
	}

	/**
	 * @param touchPoint.y
	 *            the clickPostY to set
	 */
	@Deprecated
	public void setClickPostY(float clickPostY) {
		if (clickPostY >= 0) {
			this.touchPoint.y = clickPostY;
		}
	}

	/**
	 * @return the touchPoint
	 */
	public PointF getTouchPoint() {
		return touchPoint;
	}

	/**
	 * @param touchPoint
	 *            the touchPoint to set
	 */
	public void setTouchPoint(PointF touchPoint) {
		this.touchPoint = touchPoint;
	}

	/**
	 * @return the axisXPosition
	 */
	public int getAxisXPosition() {
		return axisX.getPosition();
	}

	/**
	 * @param axisXPosition
	 *            the axisXPosition to set
	 */
	public void setAxisXPosition(int axisXPosition) {
		this.axisX.setPosition(axisXPosition);
	}

	/**
	 * @return the axisYPosition
	 */
	public int getAxisYPosition() {
		return axisY.getPosition();
	}

	/**
	 * @param axisYPosition
	 *            the axisYPosition to set
	 */
	public void setAxisYPosition(int axisYPosition) {
		this.axisY.setPosition(axisYPosition);
	}

	/* (non-Javadoc)
	 *  
	 * @see cn.limc.androidcharts.event.ITouchable#touchDown() 
	 */
	public void touchDown(PointF pt) {
		this.touchPoint = pt;
		this.postInvalidate();
	}

	/* (non-Javadoc)
	 *  
	 * @see cn.limc.androidcharts.event.ITouchable#touchMoved() 
	 */
	public void touchMoved(PointF pt) {
		this.touchPoint = pt;
		this.postInvalidate();
	}

	/* (non-Javadoc)
	 *  
	 * @see cn.limc.androidcharts.event.ITouchable#touchUp() 
	 */
	public void touchUp(PointF pt) {
		this.touchPoint = pt;
		this.postInvalidate();
	}

	/* (non-Javadoc)
	 *
	 * @see cn.limc.androidcharts.event.ITouchable#longPressDown()
	 */
	public void longPressDown(PointF pt) {
		this.touchPoint = pt;
		this.crossLines.setDisplayCrossXOnTouch(true);
		this.crossLines.setDisplayCrossYOnTouch(true);
		this.postInvalidate();
	}

	/* (non-Javadoc)
	 *
	 * @see cn.limc.androidcharts.event.ITouchable#longPressMoved()
	 */
	public void longPressMoved(PointF pt) {
		this.touchPoint = pt;
		this.postInvalidate();
	}

	/* (non-Javadoc)
	 *
	 * @see cn.limc.androidcharts.event.ITouchable#longPressUp()
	 */
	public void longPressUp(PointF pt) {
		this.touchPoint = pt;
		this.crossLines.setDisplayCrossXOnTouch(false);
		this.crossLines.setDisplayCrossYOnTouch(false);
		this.postInvalidate();
	}

	/* (non-Javadoc)
	 * 
	 * @param listener 
	 * @see cn.limc.androidcharts.event.ITouchable#setOnTouchGestureListener(cn.limc.androidcharts.event.OnTouchGestureListener) 
	 */
	public void setOnTouchGestureListener(OnTouchGestureListener listener) {
		this.onTouchGestureListener = listener;
	}

	/* (non-Javadoc)
	 * 
	 * @return 
	 * @see cn.limc.androidcharts.event.ITouchable#getOnTouchGestureListener() 
	 */
	public OnTouchGestureListener getOnTouchGestureListener() {
		return onTouchGestureListener;
	}

	/**
	 * @return the touchGestureDetector
	 */
	public IGestureDetector getTouchGestureDetector() {
		return touchGestureDetector;
	}

	/**
	 * @param touchGestureDetector the touchGestureDetector to set
	 */
	public void setTouchGestureDetector(IGestureDetector touchGestureDetector) {
		this.touchGestureDetector = touchGestureDetector;
	}

	/**
	 * @return the dataQuadrant
	 */
	public IQuadrant getDataQuadrant() {
		return dataQuadrant;
	}

	/**
	 * @param dataQuadrant the dataQuadrant to set
	 */
	public void setDataQuadrant(IQuadrant dataQuadrant) {
		this.dataQuadrant = dataQuadrant;
	}
	
	/**
	 * @return the paddingTop
	 */
	public float getDataQuadrantPaddingTop() {
		return dataQuadrant.getPaddingTop();
	}

	/**
	 * @param paddingTop
	 *            the paddingTop to set
	 */
	public void setDataQuadrantPaddingTop(float quadrantPaddingTop) {
		dataQuadrant.setPaddingTop(quadrantPaddingTop);
	}

	/**
	 * @return the paddingLeft
	 */
	public float getDataQuadrantPaddingLeft() {
		return dataQuadrant.getPaddingLeft();
	}

	/**
	 * @param paddingLeft
	 *            the paddingLeft to set
	 */
	public void setDataQuadrantPaddingLeft(float quadrantPaddingLeft) {
		dataQuadrant.setPaddingLeft(quadrantPaddingLeft);
	}

	/**
	 * @return the paddingBottom
	 */
	public float getDataQuadrantPaddingBottom() {
		return dataQuadrant.getPaddingBottom();
	}

	/**
	 * @param paddingBottom
	 *            the paddingBottom to set
	 */
	public void setDataQuadrantPaddingBottom(float quadrantPaddingBottom) {
		dataQuadrant.setPaddingBottom(quadrantPaddingBottom);
	}

	/**
	 * @return the paddingRight
	 */
	public float getDataQuadrantPaddingRight() {
		return dataQuadrant.getPaddingRight();
	}

	/**
	 * @param paddingRight
	 *            the paddingRight to set
	 */
	public void setDataQuadrantPaddingRight(float quadrantPaddingRight) {
		dataQuadrant.setPaddingRight(quadrantPaddingRight);
	}

    /**
     * @return the axisX
     */
    public HorizontalAxis getAxisX() {
        return axisX;
    }

    /**
     * @param axisX the axisX to set
     */
    public void setAxisX(HorizontalAxis axisX) {
        this.axisX = axisX;
    }

    /**
     * @return the axisY
     */
    public VerticalAxis getAxisY() {
        return axisY;
    }

    /**
     * @param axisY the axisY to set
     */
    public void setAxisY(VerticalAxis axisY) {
        this.axisY = axisY;
    }

    /**
     * @return the crossLines
     */
    public CrossLines getCrossLines() {
        return crossLines;
    }

    /**
     * @param crossLines the crossLines to set
     */
    public void setCrossLines(CrossLines crossLines) {
        this.crossLines = crossLines;
    }

    /**
     * @return the simpleGrid
     */
    public SimpleGrid getSimpleGrid() {
        return simpleGrid;
    }

    /**
     * @param simpleGrid the simpleGrid to set
     */
    public void setSimpleGrid(SimpleGrid simpleGrid) {
        this.simpleGrid = simpleGrid;
    }
	
	/**
	 * @return the crossLinesColor
	 */
	public int getCrossLinesColor() {
		return crossLines.getCrossLinesColor();
	}

	/**
	 * @param crossLinesColor
	 *            the crossLinesColor to set
	 */
	public void setCrossLinesColor(int crossLinesColor) {
		this.crossLines.setCrossLinesColor(crossLinesColor);
	}

	/**
	 * @return the crossLinesFontColor
	 */
	public int getCrossLinesFontColor() {
		return crossLines.getCrossLinesFontColor();
	}

	/**
	 * @param crossLinesFontColor
	 *            the crossLinesFontColor to set
	 */
	public void setCrossLinesFontColor(int crossLinesFontColor) {
		this.crossLines.setCrossLinesFontColor(crossLinesFontColor);
	}
	
	/**
	 * @return the displayCrossXOnTouch
	 */
	public boolean isDisplayCrossXOnTouch() {
		return crossLines.isDisplayCrossXOnTouch();
	}

	/**
	 * @param displayCrossXOnTouch
	 *            the displayCrossXOnTouch to set
	 */
	public void setDisplayCrossXOnTouch(boolean displayCrossXOnTouch) {
		this.crossLines.setDisplayCrossXOnTouch(displayCrossXOnTouch) ;
	}

	/**
	 * @return the displayCrossYOnTouch
	 */
	public boolean isDisplayCrossYOnTouch() {
		return crossLines.isDisplayCrossYOnTouch();
	}

	/**
	 * @param displayCrossYOnTouch
	 *            the displayCrossYOnTouch to set
	 */
	public void setDisplayCrossYOnTouch(boolean displayCrossYOnTouch) {
	    this.crossLines.setDisplayCrossYOnTouch(displayCrossYOnTouch) ;
	}
	
    /**
     * @return the displayLongitudeTitle
     */
    public boolean isDisplayLongitudeTitle() {
        return this.simpleGrid.isDisplayLongitude();
    }

    /**
     * @param displayLongitudeTitle
     *            the displayLongitudeTitle to set
     */
    public void setDisplayLongitudeTitle(boolean displayLongitudeTitle) {
        this.simpleGrid.setDisplayLongitudeTitle(displayLongitudeTitle);
    }

    /**
     * @return the displayAxisYTitle
     */
    public boolean isDisplayLatitudeTitle() {
        return this.simpleGrid.isDisplayLatitudeTitle();
    }

    /**
     * @param displayLatitudeTitle
     *            the displayLatitudeTitle to set
     */
    public void setDisplayLatitudeTitle(boolean displayLatitudeTitle) {
        this.simpleGrid.setDisplayLatitudeTitle(displayLatitudeTitle);
    }

    /**
     * @return the latitudeNum
     */
    public int getLatitudeNum() {
        return this.simpleGrid.getLatitudeNum();
    }

    /**
     * @param latitudeNum
     *            the latitudeNum to set
     */
    public void setLatitudeNum(int latitudeNum) {
        this.simpleGrid.setLatitudeNum(latitudeNum);
    }

    /**
     * @return the longitudeNum
     */
    public int getLongitudeNum() {
        return this.simpleGrid.getLongitudeNum();
    }

    /**
     * @param longitudeNum
     *            the longitudeNum to set
     */
    public void setLongitudeNum(int longitudeNum) {
        this.simpleGrid.setLongitudeNum(longitudeNum);
    }

    /**
     * @return the displayLongitude
     */
    public boolean isDisplayLongitude() {
        return this.simpleGrid.isDisplayLongitude();
    }

    /**
     * @param displayLongitude
     *            the displayLongitude to set
     */
    public void setDisplayLongitude(boolean displayLongitude) {
        this.simpleGrid.setDisplayLongitude(displayLongitude);
    }

    /**
     * @return the dashLongitude
     */
    public boolean isDashLongitude() {
        return this.simpleGrid.isDashLatitude();
    }

    /**
     * @param dashLongitude
     *            the dashLongitude to set
     */
    public void setDashLongitude(boolean dashLongitude) {
        this.simpleGrid.setDashLongitude(dashLongitude);
    }

    /**
     * @return the displayLatitude
     */
    public boolean isDisplayLatitude() {
        return this.simpleGrid.isDisplayLatitude();
    }

    /**
     * @param displayLatitude
     *            the displayLatitude to set
     */
    public void setDisplayLatitude(boolean displayLatitude) {
        this.simpleGrid.setDisplayLatitude(displayLatitude);
    }

    /**
     * @return the dashLatitude
     */
    public boolean isDashLatitude() {
        return this.simpleGrid.isDashLatitude();
    }

    /**
     * @param dashLatitude
     *            the dashLatitude to set
     */
    public void setDashLatitude(boolean dashLatitude) {
        this.simpleGrid.setDashLatitude(dashLatitude);
    }

    /**
     * @return the dashEffect
     */
    public PathEffect getDashEffect() {
        return this.simpleGrid.getDashEffect();
    }

    /**
     * @param dashEffect
     *            the dashEffect to set
     */
    public void setDashEffect(PathEffect dashEffect) {
        this.setDashEffect(dashEffect);
    }
    
    /**
     * @return the longitudeWidth
     */
    public float getLongitudeWidth() {
        return this.simpleGrid.getLongitudeWidth();
    }

    /**
     * @param longitudeWidth the longitudeWidth to set
     */
    public void setLongitudeWidth(float longitudeWidth) {
        this.simpleGrid.setLongitudeWidth(longitudeWidth);
    }

    /**
     * @return the latitudeWidth
     */
    public float getLatitudeWidth() {
        return this.simpleGrid.getLatitudeWidth();
    }

    /**
     * @param latitudeWidth the latitudeWidth to set
     */
    public void setLatitudeWidth(float latitudeWidth) {
        this.simpleGrid.setLatitudeWidth(latitudeWidth);
    }

    /**
     * @return the longitudeFontColor
     */
    public int getLongitudeFontColor() {
        return this.simpleGrid.getLongitudeFontColor();
    }

    /**
     * @param longitudeFontColor
     *            the longitudeFontColor to set
     */
    public void setLongitudeFontColor(int longitudeFontColor) {
        this.simpleGrid.setLongitudeFontColor(longitudeFontColor);
    }

    /**
     * @return the longitudeFontSize
     */
    public int getLongitudeFontSize() {
        return this.simpleGrid.getLongitudeFontSize();
    }

    /**
     * @param longitudeFontSize
     *            the longitudeFontSize to set
     */
    public void setLongitudeFontSize(int longitudeFontSize) {
        this.simpleGrid.setLongitudeFontSize(longitudeFontSize);
    }

    /**
     * @return the latitudeFontColor
     */
    public int getLatitudeFontColor() {
        return this.simpleGrid.getLatitudeFontColor();
    }

    /**
     * @param latitudeFontColor
     *            the latitudeFontColor to set
     */
    public void setLatitudeFontColor(int latitudeFontColor) {
        this.simpleGrid.setLatitudeFontColor(latitudeFontColor);
    }

    /**
     * @return the latitudeFontSize
     */
    public int getLatitudeFontSize() {
        return this.simpleGrid.getLatitudeFontSize();
    }

    /**
     * @param latitudeFontSize
     *            the latitudeFontSize to set
     */
    public void setLatitudeFontSize(int latitudeFontSize) {
        this.simpleGrid.setLatitudeFontSize(latitudeFontSize);
    }
    
    /**
     * @return the longitudeColor
     */
    public int getLongitudeColor() {
        return this.simpleGrid.getLongitudeColor();
    }

    /**
     * @param longitudeColor
     *            the longitudeColor to set
     */
    public void setLongitudeColor(int longitudeColor) {
        this.simpleGrid.setLongitudeColor(longitudeColor);
    }

    /**
     * @return the latitudeColor
     */
    public int getLatitudeColor() {
        return this.simpleGrid.getLatitudeColor();
    }

    /**
     * @param latitudeColor
     *            the latitudeColor to set
     */
    public void setLatitudeColor(int latitudeColor) {
        this.simpleGrid.setLatitudeColor(latitudeColor);
    }
}
