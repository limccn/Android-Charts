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

package cn.limc.androidcharts.diagram;

import java.util.ArrayList;
import java.util.List;

import cn.limc.androidcharts.component.Axis;
import cn.limc.androidcharts.component.DividedLayout;
import cn.limc.androidcharts.component.HorizontalIndicator;
import cn.limc.androidcharts.component.Grid;
import cn.limc.androidcharts.component.HorizontalAxis;
import cn.limc.androidcharts.component.Component;
import cn.limc.androidcharts.component.SimpleGrid;
import cn.limc.androidcharts.component.VerticalAxis;
import cn.limc.androidcharts.component.VerticalIndicator;
import cn.limc.androidcharts.controller.DiagramController;
import cn.limc.androidcharts.event.GestureDetector;
import cn.limc.androidcharts.event.Slipable;
import cn.limc.androidcharts.event.Zoomable;
import cn.limc.androidcharts.event.SlipGestureDetector;
import cn.limc.androidcharts.event.ZoomGestureDetector;
import cn.limc.androidcharts.event.SlipGestureDetector.OnSlipGestureListener;
import cn.limc.androidcharts.event.TouchGestureDetector.OnTouchGestureListener;
import cn.limc.androidcharts.event.ZoomGestureDetector.OnZoomGestureListener;
import cn.limc.androidcharts.event.TouchGestureDetector;
import cn.limc.androidcharts.handler.ComponentHandler;
import cn.limc.androidcharts.handler.ComponentGroupHandler;
import cn.limc.androidcharts.handler.DataComponetHandler;
import cn.limc.androidcharts.model.DataCursorChangedListener;

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
public class GridChart extends AbstractChart  {
    
    
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
   
	protected OnTouchGestureListener onTouchListener = new OnTouchListener() {};
	protected GestureDetector touchGestureDetector = new TouchGestureDetector(this,onTouchListener);
	protected OnZoomGestureListener onZoomListener = new OnZoomListener(){};
	protected GestureDetector zoomGestureDetector = new ZoomGestureDetector(this, onZoomListener);
    protected OnSlipGestureListener onSlipListener = new OnSlipListener(){};
    protected GestureDetector slipGestureDetector = new SlipGestureDetector(this,onSlipListener);
    protected DataCursorChangedListener onDisplayCursorListener;
	
	protected HorizontalAxis topAxis;
	protected HorizontalAxis bottomAxis;
	protected VerticalAxis leftAxis;
	protected VerticalAxis rightAxis;
	
	protected DividedLayout layout = new DividedLayout(this);
    
	protected VerticalIndicator vIndicator;
	protected HorizontalIndicator hIndicator;
	protected SimpleGrid dataGrid;
	
	protected List<ComponentHandler> controllerList = new ArrayList<ComponentHandler>();
	
	protected DiagramController diagramController;
	
//	protected DataCursor dataCursor = new SimpleDataCursor();
//	protected Degree axisYDegree = new DecimalDegree();
//	protected Degree axisXDegree = new DateTimeDegree();
//	protected DataRange dataRange;
	    
//    public static final int DEFAULT_STICK_SPACING = 1;
//    protected int stickSpacing = DEFAULT_STICK_SPACING;
	
    public void setTopAxis(HorizontalAxis axis){
        axis.setParent(this);
        axis.setPosition(Axis.AXIS_X_POSITION_TOP);
        this.topAxis = axis;
        layout.ResizeComponent(axis,DividedLayout.TOP_MIDDLE);
    }
    public void setTopAxis(HorizontalAxis axis,int divide){
        axis.setParent(this);
        axis.setPosition(Axis.AXIS_X_POSITION_TOP);
        this.topAxis = axis;
        layout.ResizeComponent(axis, divide);
    }
    public void setBottomAxis(HorizontalAxis axis){
        axis.setParent(this);
        axis.setPosition(Axis.AXIS_X_POSITION_BOTTOM);
        this.bottomAxis = axis;
        layout.ResizeComponent(axis,DividedLayout.BOTTOM_MIDDLE);
    }
    public void setBottomAxis(HorizontalAxis axis,int divide){
        axis.setParent(this);
        axis.setPosition(Axis.AXIS_X_POSITION_BOTTOM);
        this.bottomAxis = axis;
        layout.ResizeComponent(axis,divide);
    }
    public void setLeftAxis(VerticalAxis axis){ 
        axis.setParent(this);
        axis.setPosition(Axis.AXIS_Y_POSITION_LEFT);
        this.leftAxis = axis;
        layout.ResizeComponent(axis,DividedLayout.CENTER_LEFT);
    }
    
    public void setLeftAxis(VerticalAxis axis,int divide){
        axis.setParent(this);
        axis.setPosition(Axis.AXIS_Y_POSITION_LEFT);
        this.leftAxis = axis;
        layout.ResizeComponent(axis,divide);
    }
    
    public void setRightAxis(VerticalAxis axis){
        axis.setParent(this);
        axis.setPosition(Axis.AXIS_Y_POSITION_RIGHT);
        this.rightAxis = axis;
        layout.ResizeComponent(axis,DividedLayout.CENTER_RIGHT);
    }
    
    public void setRightAxis(VerticalAxis axis,int divide){
        axis.setParent(this);
        axis.setPosition(Axis.AXIS_Y_POSITION_RIGHT);
        this.rightAxis = axis;
        layout.ResizeComponent(axis,divide);
    }
    
    public void setDataGrid(SimpleGrid grid){
        grid.setParent(this);
        this.dataGrid = grid;
        layout.ResizeComponent(grid,DividedLayout.CENTER_MIDDLE);
    }
    
    public void setDataGrid(SimpleGrid grid, int divide){
        grid.setParent(this);
        this.dataGrid = grid;
        layout.ResizeComponent(grid,divide);
    }
    
    public void setVerticalIndicator(VerticalIndicator indicator){
        indicator.setParent(this);
        this.vIndicator = indicator;
        layout.ResizeComponent(indicator,DividedLayout.CENTER_MIDDLE);
    }
    
    public void setVerticalIndicator(VerticalIndicator indicator,int divide){
        indicator.setParent(this);
        this.vIndicator = indicator;
        layout.ResizeComponent(indicator,divide);
    }
    
    public void setHorizontalIndicator(HorizontalIndicator indicator){
        indicator.setParent(this);
        this.hIndicator = indicator;
        layout.ResizeComponent(indicator,DividedLayout.CENTER_MIDDLE);
    }
    
    public void setHorizontalIndicator(HorizontalIndicator indicator,int divide){
        indicator.setParent(this);
        this.hIndicator = indicator;
        layout.ResizeComponent(indicator,divide);
    }
    
    public void addComponent(Component component){
        if(component == null){
            return;
        }
        component.setParent(this);
        layout.ResizeComponent(component, DividedLayout.CENTER_MIDDLE);
    }
    
    public void addComponent(Component component, int divide){
        if(component == null){
            return;
        }
        component.setParent(this);
        layout.ResizeComponent(component, divide);
    }
    
    public void addController(ComponentHandler controller){
        if (controller == null) {
            return;
        }
        controller.setParent(this);
        controllerList.add(controller);
        if(controller instanceof ComponentGroupHandler){
            for (Component component : ((ComponentGroupHandler)controller).getComponentList()) {
                addComponent(component);
            }
        }else{
            addComponent(controller.getComponent());
        }
    }
    
	/*
	 * (non-Javadoc)
	 * 
	 * @param context
	 * 
	 * @see cn.limc.androidcharts.diagram.AbstractChart#BaseChart(Context)
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
	 * @see cn.limc.androidcharts.diagram.AbstractChart#BaseChart(Context,
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
	 * @see cn.limc.androidcharts.diagram.AbstractChart#BaseChart(Context,
	 * AttributeSet)
	 */
	public GridChart(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	
	/* (non-Javadoc)
     * @see android.view.View#onSizeChanged(int, int, int, int)
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        
        //TODO some codes
        
        if (diagramController != null) {
            diagramController.didSizeChanged();
        }
    }
    

	
	/* (non-Javadoc)
     * @see android.view.View#onLayout(boolean, int, int, int, int)
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        
        if (dataGrid != null) {
            layout.ResizeComponent(dataGrid,DividedLayout.CENTER_MIDDLE);
        }
        if (leftAxis != null) {
            layout.ResizeComponent(leftAxis,DividedLayout.CENTER_LEFT);
        }
        if (topAxis != null) {
            layout.ResizeComponent(topAxis,DividedLayout.TOP_MIDDLE);
        }
        if (rightAxis != null) {
            layout.ResizeComponent(rightAxis,DividedLayout.CENTER_RIGHT);
        }
        if (bottomAxis != null) {
            layout.ResizeComponent(bottomAxis,DividedLayout.BOTTOM_MIDDLE);
        }
        if(vIndicator != null){
            layout.ResizeComponent(vIndicator,DividedLayout.CENTER_MIDDLE);
        }
        if(hIndicator != null){
            layout.ResizeComponent(hIndicator,DividedLayout.CENTER_MIDDLE);
        }
        for (ComponentHandler ctrl:controllerList) {
            
            if(ctrl instanceof ComponentGroupHandler){
                for (Component component : ((ComponentGroupHandler)ctrl).getComponentList()) {
                    if (component != null) {
                        layout.ResizeComponent(component,DividedLayout.CENTER_MIDDLE);
                    }
                }
            }else{
                if (ctrl.getComponent() !=null) {
                    layout.ResizeComponent(ctrl.getComponent(),DividedLayout.CENTER_MIDDLE);
                }
            } 
        }
        
        if (diagramController != null && changed) {
            diagramController.didLayoutChanged();
        }
    }
    /**
     * @return the diagramController
     */
    public DiagramController getDiagramController() {
        return diagramController;
    }
    /**
     * @param diagramController the diagramController to set
     */
    public void setDiagramController(DiagramController diagramController) {
        this.diagramController = diagramController;
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
	    
	    if (diagramController != null) {
            diagramController.willDraw();
        }
		super.onDraw(canvas);
		
	      
	    if (dataGrid != null) {
	        dataGrid.draw(canvas);
	    }
		if (leftAxis != null) {
            leftAxis.draw(canvas);
        }
		if (topAxis != null) {
		    topAxis.draw(canvas);
        }
		if (rightAxis != null) {
		    rightAxis.draw(canvas);
        }
		if (bottomAxis != null) {
		    bottomAxis.draw(canvas);
        }
       for (ComponentHandler ctrl:controllerList) {
            
            if(ctrl instanceof ComponentGroupHandler){
                for (Component component : ((ComponentGroupHandler)ctrl).getComponentList()) {
                    if (component != null) {
                        component.draw(canvas);
                    }
                }
            }else{
                if (ctrl.getComponent() !=null) {
                    ctrl.getComponent().draw(canvas);
                }
            } 
        }
		if(vIndicator != null){
		    vIndicator.draw(canvas);
		}
        if(hIndicator != null){
            hIndicator.draw(canvas);
        }
        
        if (diagramController != null) {
            diagramController.didDrawn();
        }
	}
	
	@Override
    public boolean onTouchEvent(MotionEvent event) {
        return touchGestureDetector.onTouchEvent(event) &&
                zoomGestureDetector.onTouchEvent(event) && 
                slipGestureDetector.onTouchEvent(event);
    }
	

	    
    /**
     * <p>
     * get current selected mData index
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
        if (!((Component)vIndicator).isValidTouchPoint(touchPoint)) {
            return 0;
        }
        return vIndicator.calcSelectedIndex(touchPoint);
    }
    
//	   /**
//     * <p>
//     * initialize degrees on X axis
//     * </p>
//     * <p>
//     * X軸の目盛を初期化
//     * </p>
//     * <p>
//     * 初始化X轴的坐标值
//     * </p>
//     */
//    protected void initAxisX() {
//        dataGrid.setLongitudeTitles(axisXDegree.getDegrees());
//    }
//
//    /**
//     * <p>
//     * initialize degrees on Y axis
//     * </p>
//     * <p>
//     * Y軸の目盛を初期化
//     * </p>
//     * <p>
//     * 初始化Y轴的坐标值
//     * </p>
//     */
//    protected void initAxisY() {
//        dataGrid.setLatitudeTitles(axisYDegree.getDegrees());
//    }  

//	/**
//	 * @return the axisXColor
//	 */
//	public int getAxisXColor() {
//		return axisBottom.getLineColor();
//	}
//
//	/**
//	 * @param axisXColor
//	 *            the axisXColor to set
//	 */
//	public void setAxisXColor(int axisXColor) {
//		this.axisX.setLineColor(axisXColor);
//	}
//
//	/**
//	 * @return the axisYColor
//	 */
//	public int getAxisYColor() {
//		return axisRight.getLineColor();
//	}
//
//	/**
//	 * @param axisYColor
//	 *            the axisYColor to set
//	 */
//	public void setAxisYColor(int axisYColor) {
//		this.axisY.setLineColor(axisYColor);
//	}
//	/**
//	 * @return the axisWidth
//	 */
//	public float getAxisXWidth() {
//		return axisBottom.getLineWidth();
//	}
//
//	/**
//	 * @param axisWidth
//	 *            the axisWidth to set
//	 */
//	public void setAxisXWidth(float axisWidth) {
//		this.axisX.setLineWidth(axisWidth);
//	}
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



//	/**
//	 * @return the longitudeTitles
//	 */
//	public List<String> getLongitudeTitles() {
//		return dataGrid.getLongitudeTitles();
//	}
//
//	/**
//	 * @param longitudeTitles
//	 *            the longitudeTitles to set
//	 */
//	public void setLongitudeTitles(List<String> longitudeTitles) {
//		this.dataGrid.setLongitudeTitles(longitudeTitles);
//	}
//
//	/**
//	 * @return the latitudeTitles
//	 */
//	public List<String> getLatitudeTitles() {
//		return dataGrid.getLatitudeTitles();
//	}
//
//	/**
//	 * @param latitudeTitles
//	 *            the latitudeTitles to set
//	 */
//	public void setLatitudeTitles(List<String> latitudeTitles) {
//	    this.dataGrid.setLatitudeTitles(latitudeTitles);
//	}


//	/**
//	 * @return the clickPostX
//	 */
//	@Deprecated
//	public float getClickPostX() {
//		if (touchPoint == null) {
//			return 0f;
//		}else{
//			return touchPoint.x;
//		}
//
//	}
//
//	/**
//	 * @param clickPostX
//	 *            the clickPostX to set
//	 */
//	@Deprecated
//	public void setClickPostX(float clickPostX) {
//		if (clickPostX >= 0) {
//			this.touchPoint.x = clickPostX;
//		}
//	}
//
//	/**
//	 * @return the clickPostY
//	 */
//	@Deprecated
//	public float getClickPostY() {
//		if (touchPoint == null) {
//			return 0f;
//		} else {
//			return touchPoint.y;
//		}
//	}
//
//	/**
//	 * @param touchPoint.y
//	 *            the clickPostY to set
//	 */
//	@Deprecated
//	public void setClickPostY(float clickPostY) {
//		if (clickPostY >= 0) {
//			this.touchPoint.y = clickPostY;
//		}
//	}

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

//	/**
//	 * @return the axisXPosition
//	 */
//	public int getAxisXPosition() {
//		return axisBottom.getPosition();
//	}
//
//	/**
//	 * @param axisXPosition
//	 *            the axisXPosition to set
//	 */
//	public void setAxisXPosition(int axisXPosition) {
//		this.axisX.setPosition(axisXPosition);
//	}
//
//	/**
//	 * @return the axisYPosition
//	 */
//	public int getAxisYPosition() {
//		return axisRight.getPosition();
//	}
//
//	/**
//	 * @param axisYPosition
//	 *            the axisYPosition to set
//	 */
//	public void setAxisYPosition(int axisYPosition) {
//		this.axisY.setPosition(axisYPosition);
//	}	

//	/**
//	 * @return the dataQuadrant
//	 */
//	public Component getDataQuadrant() {
//		return dataQuadrant;
//	}
//
//	/**
//	 * @param dataQuadrant the dataQuadrant to set
//	 */
//	public void setDataQuadrant(Component dataQuadrant) {
//		this.dataQuadrant = dataQuadrant;
//	}
//	
//	/**
//	 * @return the paddingTop
//	 */
//	public float getDataQuadrantPaddingTop() {
//		return dataQuadrant.getPaddingTop();
//	}
//
//	/**
//	 * @param paddingTop
//	 *            the paddingTop to set
//	 */
//	public void setDataQuadrantPaddingTop(float quadrantPaddingTop) {
//		dataQuadrant.setPaddingTop(quadrantPaddingTop);
//	}
//
//	/**
//	 * @return the paddingLeft
//	 */
//	public float getDataQuadrantPaddingLeft() {
//		return dataQuadrant.getPaddingLeft();
//	}
//
//	/**
//	 * @param paddingLeft
//	 *            the paddingLeft to set
//	 */
//	public void setDataQuadrantPaddingLeft(float quadrantPaddingLeft) {
//		dataQuadrant.setPaddingLeft(quadrantPaddingLeft);
//	}
//
//	/**
//	 * @return the paddingBottom
//	 */
//	public float getDataQuadrantPaddingBottom() {
//		return dataQuadrant.getPaddingBottom();
//	}
//
//	/**
//	 * @param paddingBottom
//	 *            the paddingBottom to set
//	 */
//	public void setDataQuadrantPaddingBottom(float quadrantPaddingBottom) {
//		dataQuadrant.setPaddingBottom(quadrantPaddingBottom);
//	}
//
//	/**
//	 * @return the paddingRight
//	 */
//	public float getDataQuadrantPaddingRight() {
//		return dataQuadrant.getPaddingRight();
//	}
//
//	/**
//	 * @param paddingRight
//	 *            the paddingRight to set
//	 */
//	public void setDataQuadrantPaddingRight(float quadrantPaddingRight) {
//		dataQuadrant.setPaddingRight(quadrantPaddingRight);
//	}

//    /**
//     * @return the axisBottom
//     */
//    public HorizontalAxis getAxisX() {
//        return axisBottom;
//    }
//
//    /**
//     * @param axisBottom the axisBottom to set
//     */
//    public void setAxisX(HorizontalAxis axisBottom) {
//        this.axisX = axisBottom;
//    }
//
//    /**
//     * @return the axisRight
//     */
//    public VerticalAxis getAxisY() {
//        return axisRight;
//    }
//
//    /**
//     * @param axisRight the axisRight to set
//     */
//    public void setAxisY(VerticalAxis axisRight) {
//        this.axisY = axisRight;
//    }

//    /**
//     * @return the crossLines
//     */
//    public Indicator getCrossLines() {
//        return crossLines;
//    }


    /**
     * @return the dataGrid
     */
    public Grid getDataGrid() {
        return dataGrid;
    }

//	/**
//	 * @return the crossLinesColor
//	 */
//	public int getCrossLinesColor() {
//		return crossLines.getCrossLinesColor();
//	}
//
//	/**
//	 * @param crossLinesColor
//	 *            the crossLinesColor to set
//	 */
//	public void setCrossLinesColor(int crossLinesColor) {
//		this.crossLines.setCrossLinesColor(crossLinesColor);
//	}
//
//	/**
//	 * @return the crossLinesFontColor
//	 */
//	public int getCrossLinesFontColor() {
//		return crossLines.getCrossLinesFontColor();
//	}
//
//	/**
//	 * @param crossLinesFontColor
//	 *            the crossLinesFontColor to set
//	 */
//	public void setCrossLinesFontColor(int crossLinesFontColor) {
//		this.crossLines.setCrossLinesFontColor(crossLinesFontColor);
//	}
//	
//	/**
//	 * @return the displayCrossXOnTouch
//	 */
//	public boolean isDisplayCrossXOnTouch() {
//		return crossLines.isDisplayCrossXOnTouch();
//	}
//
//	/**
//	 * @param displayCrossXOnTouch
//	 *            the displayCrossXOnTouch to set
//	 */
//	public void setDisplayCrossXOnTouch(boolean displayCrossXOnTouch) {
//		this.crossLines.setDisplayCrossXOnTouch(displayCrossXOnTouch) ;
//	}
//
//	/**
//	 * @return the displayCrossYOnTouch
//	 */
//	public boolean isDisplayCrossYOnTouch() {
//		return crossLines.isDisplayCrossYOnTouch();
//	}
//
//	/**
//	 * @param displayCrossYOnTouch
//	 *            the displayCrossYOnTouch to set
//	 */
//	public void setDisplayCrossYOnTouch(boolean displayCrossYOnTouch) {
//	    this.crossLines.setDisplayCrossYOnTouch(displayCrossYOnTouch) ;
//	}
	
    /**
     * @return the displayLongitudeTitle
     */
    public boolean isDisplayLongitudeTitle() {
        return this.dataGrid.isDisplayLongitude();
    }

//    /**
//     * @param displayLongitudeTitle
//     *            the displayLongitudeTitle to set
//     */
//    public void setDisplayLongitudeTitle(boolean displayLongitudeTitle) {
//        this.dataGrid.setDisplayLongitudeTitle(displayLongitudeTitle);
//    }
//
//    /**
//     * @return the displayAxisYTitle
//     */
//    public boolean isDisplayLatitudeTitle() {
//        return this.dataGrid.isDisplayLatitudeTitle();
//    }
//
//    /**
//     * @param displayLatitudeTitle
//     *            the displayLatitudeTitle to set
//     */
//    public void setDisplayLatitudeTitle(boolean displayLatitudeTitle) {
//        this.dataGrid.setDisplayLatitudeTitle(displayLatitudeTitle);
//    }
//
//    /**
//     * @return the latitudeNum
//     */
//    public int getLatitudeNum() {
//        return this.dataGrid.getLatitudeNum();
//    }
//
//    /**
//     * @param latitudeNum
//     *            the latitudeNum to set
//     */
//    public void setLatitudeNum(int latitudeNum) {
//        this.dataGrid.setLatitudeNum(latitudeNum);
//    }
//
//    /**
//     * @return the longitudeNum
//     */
//    public int getLongitudeNum() {
//        return this.dataGrid.getLongitudeNum();
//    }
//
//    /**
//     * @param longitudeNum
//     *            the longitudeNum to set
//     */
//    public void setLongitudeNum(int longitudeNum) {
//        this.dataGrid.setLongitudeNum(longitudeNum);
//    }

    /**
     * @return the displayLongitude
     */
    public boolean isDisplayLongitude() {
        return this.dataGrid.isDisplayLongitude();
    }

    /**
     * @param displayLongitude
     *            the displayLongitude to set
     */
    public void setDisplayLongitude(boolean displayLongitude) {
        this.dataGrid.setDisplayLongitude(displayLongitude);
    }

    /**
     * @return the dashLongitude
     */
    public boolean isDashLongitude() {
        return this.dataGrid.isDashLatitude();
    }

    /**
     * @param dashLongitude
     *            the dashLongitude to set
     */
    public void setDashLongitude(boolean dashLongitude) {
        this.dataGrid.setDashLongitude(dashLongitude);
    }

    /**
     * @return the displayLatitude
     */
    public boolean isDisplayLatitude() {
        return this.dataGrid.isDisplayLatitude();
    }

    /**
     * @param displayLatitude
     *            the displayLatitude to set
     */
    public void setDisplayLatitude(boolean displayLatitude) {
        this.dataGrid.setDisplayLatitude(displayLatitude);
    }

    /**
     * @return the dashLatitude
     */
    public boolean isDashLatitude() {
        return this.dataGrid.isDashLatitude();
    }

    /**
     * @param dashLatitude
     *            the dashLatitude to set
     */
    public void setDashLatitude(boolean dashLatitude) {
        this.dataGrid.setDashLatitude(dashLatitude);
    }

    /**
     * @return the dashEffect
     */
    public PathEffect getDashEffect() {
        return this.dataGrid.getDashEffect();
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
        return this.dataGrid.getLongitudeWidth();
    }

    /**
     * @param longitudeWidth the longitudeWidth to set
     */
    public void setLongitudeWidth(float longitudeWidth) {
        this.dataGrid.setLongitudeWidth(longitudeWidth);
    }

    /**
     * @return the latitudeWidth
     */
    public float getLatitudeWidth() {
        return this.dataGrid.getLatitudeWidth();
    }

    /**
     * @param latitudeWidth the latitudeWidth to set
     */
    public void setLatitudeWidth(float latitudeWidth) {
        this.dataGrid.setLatitudeWidth(latitudeWidth);
    }

//    /**
//     * @return the longitudeFontColor
//     */
//    public int getLongitudeFontColor() {
//        return this.dataGrid.getLongitudeFontColor();
//    }
//
//    /**
//     * @param longitudeFontColor
//     *            the longitudeFontColor to set
//     */
//    public void setLongitudeFontColor(int longitudeFontColor) {
//        this.dataGrid.setLongitudeFontColor(longitudeFontColor);
//    }
//
//    /**
//     * @return the longitudeFontSize
//     */
//    public int getLongitudeFontSize() {
//        return this.dataGrid.getLongitudeFontSize();
//    }
//
//    /**
//     * @param longitudeFontSize
//     *            the longitudeFontSize to set
//     */
//    public void setLongitudeFontSize(int longitudeFontSize) {
//        this.dataGrid.setLongitudeFontSize(longitudeFontSize);
//    }
//
//    /**
//     * @return the latitudeFontColor
//     */
//    public int getLatitudeFontColor() {
//        return this.dataGrid.getLatitudeFontColor();
//    }
//
//    /**
//     * @param latitudeFontColor
//     *            the latitudeFontColor to set
//     */
//    public void setLatitudeFontColor(int latitudeFontColor) {
//        this.dataGrid.setLatitudeFontColor(latitudeFontColor);
//    }
//
//    /**
//     * @return the latitudeFontSize
//     */
//    public int getLatitudeFontSize() {
//        return this.dataGrid.getLatitudeFontSize();
//    }
//
//    /**
//     * @param latitudeFontSize
//     *            the latitudeFontSize to set
//     */
//    public void setLatitudeFontSize(int latitudeFontSize) {
//        this.dataGrid.setLatitudeFontSize(latitudeFontSize);
//    }
//    
    /**
     * @return the longitudeColor
     */
    public int getLongitudeColor() {
        return this.dataGrid.getLongitudeColor();
    }

    /**
     * @param longitudeColor
     *            the longitudeColor to set
     */
    public void setLongitudeColor(int longitudeColor) {
        this.dataGrid.setLongitudeColor(longitudeColor);
    }

    /**
     * @return the latitudeColor
     */
    public int getLatitudeColor() {
        return this.dataGrid.getLatitudeColor();
    }

    /**
     * @param latitudeColor
     *            the latitudeColor to set
     */
    public void setLatitudeColor(int latitudeColor) {
        this.dataGrid.setLatitudeColor(latitudeColor);
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
    
//    /**
//     * @return the dataMultiple
//     */
//    public int getDataMultiple() {
//        return dataRange.getDataMultiple();
//    }
//
//    /**
//     * @param dataMultiple the dataMultiple to set
//     */
//    public void setDataMultiple(int dataMultiple) {
//        ((AbstractDataRange)this.dataRange).setDataMultiple(dataMultiple);
//    }
//
//    /**
//     * @return the axisYDecimalFormat
//     */
//    @Deprecated
//    public String getAxisYDecimalFormat() {
//        return ((DecimalDegree)axisYDegree).getTargetFormat();
//    }
//
//    /**
//     * @param axisYDecimalFormat the axisYDecimalFormat to set
//     */
//    @Deprecated
//    public void setAxisYDecimalFormat(String axisYDecimalFormat) {
//        ((DecimalDegree)axisYDegree).setTargetFormat(axisYDecimalFormat);
//    }
//
//    /**
//     * @return the axisXDateTargetFormat
//     */
//    @Deprecated
//    public String getAxisXDateTargetFormat() {
//        return ((DateTimeDegree)axisXDegree).getTargetFormat();
//    }
//
//    /**
//     * @param axisXDateTargetFormat the axisXDateTargetFormat to set
//     */
//    @Deprecated
//    public void setAxisXDateTargetFormat(String axisXDateTargetFormat) {
//        ((DateTimeDegree)axisXDegree).setTargetFormat(axisXDateTargetFormat);
//    }
//
//    /**
//     * @return the axisXDateSourceFormat
//     */
//    @Deprecated
//    public String getAxisXDateSourceFormat() {
//        return ((DateTimeDegree)axisXDegree).getSourceFormat();
//    }
//
//    /**
//     * @param axisXDateSourceFormat the axisXDateSourceFormat to set
//     */
//    @Deprecated
//    public void setAxisXDateSourceFormat(String axisXDateSourceFormat) {
//        ((DateTimeDegree)axisXDegree).setSourceFormat(axisXDateSourceFormat);
//    }
//    
//    /**
//     * @return the maxValue
//     */
//    public double getMaxValue() {
//        return dataRange.getMaxValue();
//    }
//
//    /**
//     * @param maxValue
//     *            the maxValue to set
//     */
//    public void setMaxValue(double maxValue) {
//        ((AbstractDataRange)this.dataRange).setMaxValue(maxValue);
//    }
//
//    /**
//     * @return the minValue
//     */
//    public double getMinValue() {
//        return dataRange.getMinValue();
//    }
//
//    /**
//     * @param minValue
//     *            the minValue to set
//     */
//    public void setMinValue(double minValue) {
//        ((AbstractDataRange)this.dataRange).setMinValue(minValue);
//    }
//
//    /**
//     * @return the autoCalcValueRange
//     */
//    public boolean isAutoCalcValueRange() {
//        return this.dataRange.isAutoCalcValueRange();
//    }
//
//    /**
//     * @param autoCalcValueRange
//     *            the autoCalcValueRange to set
//     */
//    public void setAutoCalcValueRange(boolean autoCalcValueRange) {
//        ((AbstractDataRange)this.dataRange).setAutoCalcValueRange(autoCalcValueRange);
//    }

//    /**
//     * @return the dataCursor
//     */
//    public DataCursor getDataCursor() {
//        return dataCursor;
//    }
//
//    /**
//     * @param dataCursor the dataCursor to set
//     */
//    public void setDataCursor(DataCursor dataCursor) {
//        this.dataCursor = dataCursor;
//    }
//
//    /**
//     * @return the dataRange
//     */
//    public DataRange getDataRange() {
//        return dataRange;
//    }
//
//    /**
//     * @param dataRange the dataRange to set
//     */
//    public void setDataRange(DataRange dataRange) {
//        this.dataRange = dataRange;
//    }

//    /**
//     * @return the axisYDegree
//     */
//    public Degree getAxisYDegree() {
//        return axisYDegree;
//    }
//
//    /**
//     * @param axisYDegree the axisYDegree to set
//     */
//    public void setAxisYDegree(Degree axisYDegree) {
//        this.axisYDegree = axisYDegree;
//    }
//
//    /**
//     * @return the axisXDegree
//     */
//    public Degree getAxisXDegree() {
//        return axisXDegree;
//    }
//
//    /**
//     * @param axisXDegree the axisXDegree to set
//     */
//    public void setAxisXDegree(Degree axisXDegree) {
//        this.axisXDegree = axisXDegree;
//    }

//    /**
//     * @return the chartData
//     */
//    public ChartDataSet getChartData() {
//        return chartData;
//    }
//
//    /**
//     * @param chartData the chartData to set
//     */
//    public void setChartData(ChartDataSet chartData) {
//        this.chartData = chartData;
//    }
//    
//    /**
//     * @return the gridAlignType
//     */
//    public int getStickAlignType() {
//        return gridAlignType;
//    }
//
//    /**
//     * @param gridAlignType the gridAlignType to set
//     */
//    public void setStickAlignType(int stickAlignType) {
//        this.gridAlignType = stickAlignType;
//    }
//    
//    /**
//     * @return the maxSticksNum
//     */
//    @Deprecated
//    public int getMaxSticksNum() {
//        return dataCursor.getDisplayNumber();
//    }
//
//    /**
//     * @param maxSticksNum
//     *            the maxSticksNum to set
//     */
//    @Deprecated
//    public void setMaxSticksNum(int maxSticksNum) {
//        this.dataCursor.setDisplayNumber(maxSticksNum);
//    }
//
//    /**
//     * @return the barSpacing
//     */
//    @Deprecated
//    public int getStickSpacing() {
//        return stickSpacing;
//    }
//
//    /**
//     * @param barSpacing the barSpacing to set
//     */
//    @Deprecated
//    public void setStickSpacing(int stickSpacing) {
//        this.stickSpacing = stickSpacing;
//    }

//    /* (non-Javadoc)
//     * 
//     * @return 
//     * @see cn.limc.androidcharts.common.IDataCursor#displayFrom() 
//     */
//    public int getDisplayFrom() {
//        return this.dataCursor.getDisplayFrom();
//    }
//
//    /* (non-Javadoc)
//     * 
//     * @return 
//     * @see cn.limc.androidcharts.common.IDataCursor#displayNumber() 
//     */
//    public int getDisplayNumber() {
//        return this.dataCursor.getDisplayNumber();
//    }
//
//    /* (non-Javadoc)
//     * 
//     * @return 
//     * @see cn.limc.androidcharts.common.IDataCursor#displayTo() 
//     */
//    public int getDisplayTo() {
//        return this.dataCursor.getDisplayTo();
//    }
//
//    /* (non-Javadoc)
//     * 
//     * @param displayFrom 
//     * @see cn.limc.androidcharts.common.IDataCursor#setDisplayFrom(int) 
//     */
//    public void setDisplayFrom(int displayFrom) {
//        this.dataCursor.setDisplayFrom(displayFrom);
//    }
//
//    /* (non-Javadoc)
//     * 
//     * @param displayNumber 
//     * @see cn.limc.androidcharts.common.IDataCursor#setDisplayNumber(int) 
//     */
//    public void setDisplayNumber(int displayNumber) {
//        this.dataCursor.setDisplayNumber(displayNumber);
//    }
//
//    /* (non-Javadoc)
//     * 
//     * @return 
//     * @see cn.limc.androidcharts.common.IDataCursor#getMinDisplayNumber() 
//     */
//    public int getMinDisplayNumber() {
//        return this.dataCursor.getMinDisplayNumber();
//    }
//
//    /* (non-Javadoc)
//     * 
//     * @param minDisplayNumber 
//     * @see cn.limc.androidcharts.common.IDataCursor#getMinDisplayNumber(int) 
//     */
//    public void setMinDisplayNumber(int minDisplayNumber) {
//        this.dataCursor.setMinDisplayNumber(minDisplayNumber);
//    }
//    
//        /**
//      * @return the zoomBaseLine
//      */
//     @Deprecated
//     public int getZoomBaseLine() {
//         return ((SectionDataCursor)dataCursor).getZoomBaseLine();
//     }
//    
//     /**
//      * @param zoomBaseLine
//      *            the zoomBaseLine to set
//      */
//        @Deprecated
//     public void setZoomBaseLine(int zoomBaseLine) {
//         ((SectionDataCursor)dataCursor).setZoomBaseLine(zoomBaseLine);
//     }
//     
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
            touchPoint = pt;
            GridChart.this.postInvalidate();
            
        }

        /* (non-Javadoc)
         * @see cn.limc.androidcharts.event.TouchGestureDetector.OnTouchGestureListener#onTouchMoved(android.view.MotionEvent)
         */
        @Override
        public void onTouchMoved(MotionEvent event , PointF pt) {
            touchPoint = pt;
            GridChart.this.postInvalidate();
        }

        /* (non-Javadoc)
         * @see cn.limc.androidcharts.event.TouchGestureDetector.OnTouchGestureListener#onTouchUp(android.view.MotionEvent)
         */
        @Override
        public void onTouchUp(MotionEvent event , PointF pt) {
            touchPoint = pt;
            GridChart.this.postInvalidate();
        }
        
    }
    
    public abstract class OnZoomListener implements OnZoomGestureListener{

        /* (non-Javadoc)
         * @see cn.limc.androidcharts.event.ZoomGestureDetector.OnZoomGestureListener#onZoomIn(android.view.MotionEvent)
         */
        @Override
        public void onZoomIn(MotionEvent event) {
            for (ComponentHandler ctrl:controllerList) {
                if (ctrl != null && ctrl instanceof DataComponetHandler) {
                    Zoomable dataCursor = (Zoomable) ((DataComponetHandler)ctrl).getDataCursor();
                    if (dataCursor != null) {
                        dataCursor.zoomIn();
                    }
                }
            }
            
            GridChart.this.postInvalidate();
        }

        /* (non-Javadoc)
         * @see cn.limc.androidcharts.event.ZoomGestureDetector.OnZoomGestureListener#onZoomOut(android.view.MotionEvent)
         */
        @Override
        public void onZoomOut(MotionEvent event) {
            for (ComponentHandler ctrl:controllerList) {
                if (ctrl != null && ctrl instanceof DataComponetHandler) {
                    Zoomable dataCursor = (Zoomable) ((DataComponetHandler)ctrl).getDataCursor();
                    if (dataCursor != null) {
                        dataCursor.zoomOut();
                    }
                }
            }
            GridChart.this.postInvalidate();
        }   
    }
    
    public abstract class OnSlipListener implements OnSlipGestureListener {

        /*
         * (non-Javadoc)
         * 
         * @see
         * cn.limc.androidcharts.event.SlipGestureDetector.OnSlipGestureListener
         * #onMoveLeft(android.view.MotionEvent)
         */
        @Override
        public void onMoveLeft(MotionEvent event) {
            for (ComponentHandler ctrl:controllerList) {
                if (ctrl != null && ctrl instanceof DataComponetHandler) {
                    Slipable dataCursor = (Slipable) ((DataComponetHandler)ctrl).getDataCursor();
                    if (dataCursor != null) {
                        dataCursor.moveLeft();
                    }
                }
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
            for (ComponentHandler ctrl:controllerList) {
                if (ctrl != null && ctrl instanceof DataComponetHandler) {
                    Slipable dataCursor = (Slipable) ((DataComponetHandler)ctrl).getDataCursor();
                    if (dataCursor != null) {
                        dataCursor.moveRight();
                    }
                }
            }
            GridChart.this.postInvalidate();
        }
    }
    
    public interface Layout {
        void ResizeComponent(Component component, int divide);
    }
}
