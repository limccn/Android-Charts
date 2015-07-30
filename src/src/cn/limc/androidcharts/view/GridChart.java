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

import cn.limc.androidcharts.common.CrossLines;
import cn.limc.androidcharts.common.DataQuadrant;
import cn.limc.androidcharts.common.ICrossLines;
import cn.limc.androidcharts.common.IDataCursor;
import cn.limc.androidcharts.common.IFlexableGrid;
import cn.limc.androidcharts.common.IQuadrant;
import cn.limc.androidcharts.common.SectionDataCursor;
import cn.limc.androidcharts.common.SimpleDataCursor;
import cn.limc.androidcharts.common.SimpleGrid;
import cn.limc.androidcharts.component.Axis;
import cn.limc.androidcharts.component.HorizontalAxis;
import cn.limc.androidcharts.component.VerticalAxis;
import cn.limc.androidcharts.degree.DateTimeDegree;
import cn.limc.androidcharts.degree.DecimalDegree;
import cn.limc.androidcharts.degree.IDegree;
import cn.limc.androidcharts.degree.IHasValueRange;
import cn.limc.androidcharts.degree.StickValueRangeCalc;
import cn.limc.androidcharts.degree.ValueRangeCalc;
import cn.limc.androidcharts.entity.ChartDataSet;
import cn.limc.androidcharts.entity.IHasDate;
import cn.limc.androidcharts.entity.IMeasurable;
import cn.limc.androidcharts.event.GestureDetector;
import cn.limc.androidcharts.event.IDisplayCursorListener;
import cn.limc.androidcharts.event.ISlipable;
import cn.limc.androidcharts.event.IZoomable;
import cn.limc.androidcharts.event.SlipGestureDetector;
import cn.limc.androidcharts.event.ZoomGestureDetector;
import cn.limc.androidcharts.event.SlipGestureDetector.OnSlipGestureListener;
import cn.limc.androidcharts.event.TouchGestureDetector.OnTouchGestureListener;
import cn.limc.androidcharts.event.ZoomGestureDetector.OnZoomGestureListener;
import cn.limc.androidcharts.event.TouchGestureDetector;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PathEffect;
import android.graphics.PointF;
import android.util.AttributeSet;
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
public class GridChart extends AbstractBaseChart  {
    
    
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
	
	protected OnTouchGestureListener onTouchListener = new OnTouchListener() {};
	protected GestureDetector touchGestureDetector = new TouchGestureDetector(this,onTouchListener);
	protected OnZoomGestureListener onZoomListener = new OnZoomListener(){};
	protected GestureDetector zoomGestureDetector = new ZoomGestureDetector(this, onZoomListener);
    protected OnSlipGestureListener onSlipListener = new OnSlipListener();
    protected GestureDetector slipGestureDetector = new SlipGestureDetector(this,onSlipListener);
	
	protected IQuadrant dataQuadrant = new DataQuadrant(this);
	protected HorizontalAxis axisX = new HorizontalAxis(this,Axis.AXIS_X_POSITION_BOTTOM);
	protected VerticalAxis axisY = new VerticalAxis(this, Axis.AXIS_Y_POSITION_LEFT);
	protected CrossLines crossLines = new CrossLines(this);
	protected SimpleGrid simpleGrid = new SimpleGrid(this);
	
	protected IDataCursor dataCursor = new SimpleDataCursor(this);
	protected IDegree axisYDegree = new DecimalDegree(this);
	protected IDegree axisXDegree = new DateTimeDegree(this);
	protected IHasValueRange dataRange = new StickValueRangeCalc(this);
	    
    public static final int DEFAULT_ALIGN_TYPE = IFlexableGrid.ALIGN_TYPE_CENTER;

    protected int gridAlignType = DEFAULT_ALIGN_TYPE;

    public static final int DEFAULT_STICK_SPACING = 1;

    protected int stickSpacing = DEFAULT_STICK_SPACING;

    protected IDisplayCursorListener onDisplayCursorListener;
	    
	    /**
	     * <p>
	     * data to draw sticks
	     * </p>
	     * <p>
	     * スティックを書く用データ
	     * </p>
	     * <p>
	     * 绘制柱条用的数据
	     * </p>
	     */
	    protected ChartDataSet chartData;
	
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
		simpleGrid.draw(canvas);
		crossLines.draw(canvas);
		
	}
	
	@Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isValidTouchPoint(event.getX(),event.getY())) {
            return false;
        }
        
        if (null == chartData || chartData.size() == 0) {
            return false;
        }
        
        if (null == chartData.getChartTable() || chartData.getChartTable().size() == 0) {
            return false;
        }
        
        return touchGestureDetector.onTouchEvent(event) && zoomGestureDetector.onTouchEvent(event);
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
     * initialize degrees on X axis
     * </p>
     * <p>
     * X軸の目盛を初期化
     * </p>
     * <p>
     * 初始化X轴的坐标值
     * </p>
     */
    protected void initAxisX() {
        simpleGrid.setLongitudeTitles(axisXDegree.getDegrees());
    }

    /**
     * <p>
     * initialize degrees on Y axis
     * </p>
     * <p>
     * Y軸の目盛を初期化
     * </p>
     * <p>
     * 初始化Y轴的坐标值
     * </p>
     */
    protected void initAxisY() {
        if (dataRange.isAutoCalcValueRange()) {
            dataRange.calcValueRange();
        }
        simpleGrid.setLatitudeTitles(axisYDegree.getDegrees());
    }
    

    
    public float longitudePostOffset(){
        if (gridAlignType == IFlexableGrid.ALIGN_TYPE_CENTER) {
            float stickWidth = dataQuadrant.getPaddingWidth() / dataCursor.getDisplayNumber();
            return (this.dataQuadrant.getPaddingWidth() - stickWidth)/ (simpleGrid.getLongitudeTitles().size() - 1);
        }else{
            return this.dataQuadrant.getPaddingWidth()/ (simpleGrid.getLongitudeTitles().size() - 1);
        }
    }
    
    public float longitudeOffset(){
        if (gridAlignType ==IFlexableGrid.ALIGN_TYPE_CENTER) {
            float stickWidth = dataQuadrant.getPaddingWidth() / dataCursor.getDisplayNumber();
            return dataQuadrant.getPaddingStartX() + stickWidth / 2;
        }else{
            return dataQuadrant.getPaddingStartX();
        }
    }
    
    
    protected PointF calcTouchedPoint(float x ,float y) {
        if (!isValidTouchPoint(x,y)) {
            return new PointF(0,0);
        }
        if (crossLines.getBindCrossLinesToStick() == ICrossLines.BIND_TO_TYPE_NONE) {
            return new PointF(x, y);
        } else if (crossLines.getBindCrossLinesToStick() == ICrossLines.BIND_TO_TYPE_BOTH) {
            PointF bindPointF = calcBindPoint(x, y);
            return bindPointF;
        } else if (crossLines.getBindCrossLinesToStick() == ICrossLines.BIND_TO_TYPE_HIRIZIONAL) {
            PointF bindPointF = calcBindPoint(x, y);
            return new PointF(bindPointF.x, y);
        } else if (crossLines.getBindCrossLinesToStick() == ICrossLines.BIND_TO_TYPE_VERTICAL) {
            PointF bindPointF = calcBindPoint(x, y);
            return new PointF(x, bindPointF.y);
        } else {
            return new PointF(x, y);
        }   
    }
    
    protected PointF calcBindPoint(float x ,float y) {
        float calcX = 0;
        float calcY = 0;
        
        int index = calcSelectedIndex(x,y);
        
        float stickWidth = dataQuadrant.getPaddingWidth() / dataCursor.getDisplayNumber();
        IMeasurable stick = (IMeasurable)getChartData().getChartTable().get(index);
        calcY = (float) ((1f - (stick.getHigh() - dataRange.getMinValue())
                / dataRange.getValueRange())
                * (dataQuadrant.getPaddingHeight()) + dataQuadrant.getPaddingStartY());
        calcX = dataQuadrant.getPaddingStartX() + stickWidth * (index - dataCursor.getDisplayFrom()) + stickWidth / 2;
        
        return new PointF(calcX,calcY);
    }

	public float getAxisXRate(Object value) {
		float valueLength = ((Float) value).floatValue()
				- dataQuadrant.getPaddingStartX();
		return valueLength / this.dataQuadrant.getPaddingWidth();
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
        float graduate = getAxisXRate(value);
        int index = (int) Math.floor(graduate * dataCursor.getDisplayNumber());

        if (index >= dataCursor.getDisplayNumber()) {
            index = dataCursor.getDisplayNumber() - 1;
        } else if (index < 0) {
            index = 0;
        }

        IHasDate dataRow= (IHasDate)chartData.getChartTable().get(index);
        return axisXDegree.valueForDegree(dataRow.getDate());
    }


	
	public float getAxisYRate(Object value) {
		float valueLength = ((Float) value).floatValue()
				- dataQuadrant.getPaddingStartY();
		return 1f - valueLength / this.dataQuadrant.getPaddingHeight();
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
        float graduate = getAxisYRate(value);
        return axisYDegree.valueForDegree(graduate * dataRange.getValueRange() + dataRange.getMinValue());
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

    /**
     * @return the touchGestureDetector
     */
    public GestureDetector getTouchGestureDetector() {
        return touchGestureDetector;
    }

    /**
     * @param touchGestureDetector the touchGestureDetector to set
     */
    public void setTouchGestureDetector(GestureDetector touchGestureDetector) {
        this.touchGestureDetector = touchGestureDetector;
    }
    


    
//  public String formatAxisYDegree(double value) {
//      return new DecimalFormat(axisYDecimalFormat).format(Math.floor(value)/dataMultiple);
//  }
//  
//  public String formatAxisXDegree(int date) {
//      try {
//          Date dt = new SimpleDateFormat(axisXDateSourceFormat).parse(String.valueOf(date));
//          return new SimpleDateFormat(axisXDateTargetFormat).format(dt);
//      } catch (ParseException e) {
//          return "";
//      }
//  }
    
    /**
     * <p>
     * get current selected data index
     * </p>
     * <p>
     * 選択したスティックのインデックス
     * </p>
     * <p>
     * 获取当前选中的柱条的index
     * </p>
     * 
     * @return int
     *         <p>
     *         index
     *         </p>
     *         <p>
     *         インデックス
     *         </p>
     *         <p>
     *         index
     *         </p>
     */
    public int getSelectedIndex() {
        if (null == touchPoint) {
            return 0;
        }
        return calcSelectedIndex(touchPoint.x, touchPoint.y);
    }
    
    protected int calcSelectedIndex(float x ,float y) {
        if (!isValidTouchPoint(x,y)) {
            return 0;
        }
        float graduate = getAxisXRate(x);
        int index = (int) Math.floor(graduate * dataCursor.getDisplayNumber());

        if (index >= dataCursor.getDisplayNumber()) {
            index = dataCursor.getDisplayNumber() - 1;
        } else if (index < 0) {
            index = 0;
        }
        
        return dataCursor.getDisplayFrom() + index;
    }   
    
    /**
     * @return the dataMultiple
     */
    public int getDataMultiple() {
        return dataRange.getDataMultiple();
    }

    /**
     * @param dataMultiple the dataMultiple to set
     */
    public void setDataMultiple(int dataMultiple) {
        ((ValueRangeCalc)this.dataRange).setDataMultiple(dataMultiple);
    }

    /**
     * @return the axisYDecimalFormat
     */
    @Deprecated
    public String getAxisYDecimalFormat() {
        return ((DecimalDegree)axisYDegree).getTargetFormat();
    }

    /**
     * @param axisYDecimalFormat the axisYDecimalFormat to set
     */
    @Deprecated
    public void setAxisYDecimalFormat(String axisYDecimalFormat) {
        ((DecimalDegree)axisYDegree).setTargetFormat(axisYDecimalFormat);
    }

    /**
     * @return the axisXDateTargetFormat
     */
    @Deprecated
    public String getAxisXDateTargetFormat() {
        return ((DateTimeDegree)axisXDegree).getTargetFormat();
    }

    /**
     * @param axisXDateTargetFormat the axisXDateTargetFormat to set
     */
    @Deprecated
    public void setAxisXDateTargetFormat(String axisXDateTargetFormat) {
        ((DateTimeDegree)axisXDegree).setTargetFormat(axisXDateTargetFormat);
    }

    /**
     * @return the axisXDateSourceFormat
     */
    @Deprecated
    public String getAxisXDateSourceFormat() {
        return ((DateTimeDegree)axisXDegree).getSourceFormat();
    }

    /**
     * @param axisXDateSourceFormat the axisXDateSourceFormat to set
     */
    @Deprecated
    public void setAxisXDateSourceFormat(String axisXDateSourceFormat) {
        ((DateTimeDegree)axisXDegree).setSourceFormat(axisXDateSourceFormat);
    }
    
    /**
     * @return the maxValue
     */
    public double getMaxValue() {
        return dataRange.getMaxValue();
    }

    /**
     * @param maxValue
     *            the maxValue to set
     */
    public void setMaxValue(double maxValue) {
        ((ValueRangeCalc)this.dataRange).setMaxValue(maxValue);
    }

    /**
     * @return the minValue
     */
    public double getMinValue() {
        return dataRange.getMinValue();
    }

    /**
     * @param minValue
     *            the minValue to set
     */
    public void setMinValue(double minValue) {
        ((ValueRangeCalc)this.dataRange).setMinValue(minValue);
    }

    /**
     * @return the autoCalcValueRange
     */
    public boolean isAutoCalcValueRange() {
        return this.dataRange.isAutoCalcValueRange();
    }

    /**
     * @param autoCalcValueRange
     *            the autoCalcValueRange to set
     */
    public void setAutoCalcValueRange(boolean autoCalcValueRange) {
        ((ValueRangeCalc)this.dataRange).setAutoCalcValueRange(autoCalcValueRange);
    }

    /**
     * @return the dataCursor
     */
    public IDataCursor getDataCursor() {
        return dataCursor;
    }

    /**
     * @param dataCursor the dataCursor to set
     */
    public void setDataCursor(IDataCursor dataCursor) {
        this.dataCursor = dataCursor;
    }

    /**
     * @return the dataRange
     */
    public IHasValueRange getDataRange() {
        return dataRange;
    }

    /**
     * @param dataRange the dataRange to set
     */
    public void setDataRange(IHasValueRange dataRange) {
        this.dataRange = dataRange;
    }

    /**
     * @return the axisYDegree
     */
    public IDegree getAxisYDegree() {
        return axisYDegree;
    }

    /**
     * @param axisYDegree the axisYDegree to set
     */
    public void setAxisYDegree(IDegree axisYDegree) {
        this.axisYDegree = axisYDegree;
    }

    /**
     * @return the axisXDegree
     */
    public IDegree getAxisXDegree() {
        return axisXDegree;
    }

    /**
     * @param axisXDegree the axisXDegree to set
     */
    public void setAxisXDegree(IDegree axisXDegree) {
        this.axisXDegree = axisXDegree;
    }

    /**
     * @return the chartData
     */
    public ChartDataSet getChartData() {
        return chartData;
    }

    /**
     * @param chartData the chartData to set
     */
    public void setChartData(ChartDataSet chartData) {
        this.chartData = chartData;
    }
    
    /**
     * @return the gridAlignType
     */
    public int getStickAlignType() {
        return gridAlignType;
    }

    /**
     * @param gridAlignType the gridAlignType to set
     */
    public void setStickAlignType(int stickAlignType) {
        this.gridAlignType = stickAlignType;
    }
    
    /**
     * @return the maxSticksNum
     */
    @Deprecated
    public int getMaxSticksNum() {
        return dataCursor.getDisplayNumber();
    }

    /**
     * @param maxSticksNum
     *            the maxSticksNum to set
     */
    @Deprecated
    public void setMaxSticksNum(int maxSticksNum) {
        this.dataCursor.setDisplayNumber(maxSticksNum);
    }

    /**
     * @return the stickSpacing
     */
    @Deprecated
    public int getStickSpacing() {
        return stickSpacing;
    }

    /**
     * @param stickSpacing the stickSpacing to set
     */
    @Deprecated
    public void setStickSpacing(int stickSpacing) {
        this.stickSpacing = stickSpacing;
    }

    /* (non-Javadoc)
     * 
     * @return 
     * @see cn.limc.androidcharts.common.IDataCursor#displayFrom() 
     */
    public int getDisplayFrom() {
        return this.dataCursor.getDisplayFrom();
    }

    /* (non-Javadoc)
     * 
     * @return 
     * @see cn.limc.androidcharts.common.IDataCursor#displayNumber() 
     */
    public int getDisplayNumber() {
        return this.dataCursor.getDisplayNumber();
    }

    /* (non-Javadoc)
     * 
     * @return 
     * @see cn.limc.androidcharts.common.IDataCursor#displayTo() 
     */
    public int getDisplayTo() {
        return this.dataCursor.getDisplayTo();
    }

    /* (non-Javadoc)
     * 
     * @param displayFrom 
     * @see cn.limc.androidcharts.common.IDataCursor#setDisplayFrom(int) 
     */
    public void setDisplayFrom(int displayFrom) {
        this.dataCursor.setDisplayFrom(displayFrom);
    }

    /* (non-Javadoc)
     * 
     * @param displayNumber 
     * @see cn.limc.androidcharts.common.IDataCursor#setDisplayNumber(int) 
     */
    public void setDisplayNumber(int displayNumber) {
        this.dataCursor.setDisplayNumber(displayNumber);
    }

    /* (non-Javadoc)
     * 
     * @return 
     * @see cn.limc.androidcharts.common.IDataCursor#getMinDisplayNumber() 
     */
    public int getMinDisplayNumber() {
        return this.dataCursor.getMinDisplayNumber();
    }

    /* (non-Javadoc)
     * 
     * @param minDisplayNumber 
     * @see cn.limc.androidcharts.common.IDataCursor#getMinDisplayNumber(int) 
     */
    public void setMinDisplayNumber(int minDisplayNumber) {
        this.dataCursor.setMinDisplayNumber(minDisplayNumber);
    }
    
        /**
      * @return the zoomBaseLine
      */
     @Deprecated
     public int getZoomBaseLine() {
         return ((SectionDataCursor)dataCursor).getZoomBaseLine();
     }
    
     /**
      * @param zoomBaseLine
      *            the zoomBaseLine to set
      */
        @Deprecated
     public void setZoomBaseLine(int zoomBaseLine) {
         ((SectionDataCursor)dataCursor).setZoomBaseLine(zoomBaseLine);
     }
     
     /**
      * @return the onSlipListener
      */
     public OnSlipGestureListener getOnSlipListener() {
         return onSlipListener;
     }
    
     /**
      * @param onSlipListener the onSlipListener to set
      */
     public void setOnSlipListener(OnSlipGestureListener onSlipListener) {
         this.onSlipListener = onSlipListener;
     }
        
    public abstract class OnTouchListener implements OnTouchGestureListener{

        /* (non-Javadoc)
         * @see cn.limc.androidcharts.event.TouchGestureDetector.OnTouchGestureListener#onTouchDown(android.view.MotionEvent)
         */
        @Override
        public void onTouchDown(MotionEvent event , PointF pt) {
            GridChart.this.touchPoint = pt;
            GridChart.this.postInvalidate();
            
        }

        /* (non-Javadoc)
         * @see cn.limc.androidcharts.event.TouchGestureDetector.OnTouchGestureListener#onTouchMoved(android.view.MotionEvent)
         */
        @Override
        public void onTouchMoved(MotionEvent event , PointF pt) {
            GridChart.this.touchPoint = pt;
            GridChart.this.postInvalidate();
        }

        /* (non-Javadoc)
         * @see cn.limc.androidcharts.event.TouchGestureDetector.OnTouchGestureListener#onTouchUp(android.view.MotionEvent)
         */
        @Override
        public void onTouchUp(MotionEvent event , PointF pt) {
            GridChart.this.touchPoint = pt;
            GridChart.this.postInvalidate();
        }
        
    }
    
    public abstract class OnZoomListener implements OnZoomGestureListener{

        /* (non-Javadoc)
         * @see cn.limc.androidcharts.event.ZoomGestureDetector.OnZoomGestureListener#onZoomIn(android.view.MotionEvent)
         */
        @Override
        public void onZoomIn(MotionEvent event) {
            IZoomable dataCursor = (IZoomable) GridChart.this.getDataCursor();
            if (dataCursor != null) {
                dataCursor.zoomIn();
            }
            GridChart.this.postInvalidate();
        }

        /* (non-Javadoc)
         * @see cn.limc.androidcharts.event.ZoomGestureDetector.OnZoomGestureListener#onZoomOut(android.view.MotionEvent)
         */
        @Override
        public void onZoomOut(MotionEvent event) {
           
            IZoomable dataCursor = (IZoomable) GridChart.this.getDataCursor();
            if (dataCursor != null) {
                dataCursor.zoomOut();
            }
            GridChart.this.postInvalidate();
        }   
    }
    
    public class OnSlipListener implements OnSlipGestureListener {

        /*
         * (non-Javadoc)
         * 
         * @see
         * cn.limc.androidcharts.event.SlipGestureDetector.OnSlipGestureListener
         * #onMoveLeft(android.view.MotionEvent)
         */
        @Override
        public void onMoveLeft(MotionEvent event) {
            ISlipable dataCursor = (ISlipable) GridChart.this.getDataCursor();
            if (dataCursor != null) {
                dataCursor.moveLeft();
            }
            GridChart.this.postInvalidate();

        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * cn.limc.androidcharts.event.SlipGestureDetector.OnSlipGestureListener
         * #onMoveRight(android.view.MotionEvent)
         */
        @Override
        public void onMoveRight(MotionEvent event) {
            ISlipable dataCursor = (ISlipable) GridChart.this.getDataCursor();
            if (dataCursor != null) {
                dataCursor.moveRight();
            }
            GridChart.this.postInvalidate();

        }
    }
    
    public interface onDrawingListener{
        
    }
}
