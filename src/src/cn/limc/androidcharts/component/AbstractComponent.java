/*
 * AbstractComponent.java
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
import android.graphics.PointF;
import android.graphics.RectF;
import cn.limc.androidcharts.diagram.GridChart;
import cn.limc.androidcharts.model.DataCursor;
import cn.limc.androidcharts.series.ChartDataRow;
import cn.limc.androidcharts.series.ChartDataSet;
import cn.limc.androidcharts.series.ChartDataTable;
import cn.limc.androidcharts.series.IMeasurable;
import cn.limc.androidcharts.shape.Bar;
import cn.limc.androidcharts.shape.Shape;

/** 
 * <p>en</p>
 * <p>jp</p>
 * <p>cn</p>
 *
 * @author limc 
 * @version v1.0 2014/06/24 17:29:01 
 *  
 */
public abstract class AbstractComponent implements Component{
    
    protected RectF frame;
	protected GridChart parent;
	
	/**
     * @param parent
     */
    public AbstractComponent(GridChart parent) {
        super();
        this.parent = parent;
    }

    public AbstractComponent() {
	}
    
    public boolean isValidTouchPoint(PointF pt){
        return isValidTouchPoint(pt.x,pt.y);
    }

    public boolean isValidTouchPoint(float x, float y) {
        if (x < getPaddingStartX() || x > getPaddingEndX()) {
            return false;
        }
        if (y < getPaddingStartY() || y > getPaddingEndY()) {
            return false;
        }
        return true;
    }

    public float getWidthRate(float value) {
        float valueLength = value - getPaddingStartX();
        return valueLength / getPaddingWidth();
    }

    public float getHeightRate(float value) {
        float valueLength = value - getPaddingStartY();
        return 1f - valueLength / getPaddingHeight();
    }
	
	/**
	 * <p>
	 * Margin of the axis to the top border
	 * </p>
	 * <p>
	 * 轴線より上枠線の距離
	 * </p>
	 * <p>
	 * 轴线上边距
	 * </p>
	 */
	protected float paddingTop = DEFAULT_PADDING_TOP;

	/**
	 * <p>
	 * Margin of the axis to the right border
	 * </p>
	 * <p>
	 * 轴線より右枠線の距離
	 * </p>
	 * <p>
	 * 轴线右边距
	 * </p>
	 */
	protected float paddingLeft = DEFAULT_PADDING_LEFT;
	protected float paddingBottom = DEFAULT_PADDING_BOTTOM;

	/**
	 * <p>
	 * Margin of the axis to the right border
	 * </p>
	 * <p>
	 * 轴線より右枠線の距離
	 * </p>
	 * <p>
	 * 轴线右边距
	 * </p>
	 */
	protected float paddingRight = DEFAULT_PADDING_RIGHT;

	/**
	 * @return the paddingTop
	 */
	public float getPaddingTop() {
		return paddingTop;
	}

	/**
	 * @param paddingTop
	 *            the paddingTop to set
	 */
	public void setPaddingTop(float quadrantPaddingTop) {
		this.paddingTop = quadrantPaddingTop;
	}

	/**
	 * @return the paddingLeft
	 */
	public float getPaddingLeft() {
		return paddingLeft;
	}

	/**
	 * @param paddingLeft
	 *            the paddingLeft to set
	 */
	public void setPaddingLeft(float quadrantPaddingLeft) {
		this.paddingLeft = quadrantPaddingLeft;
	}

	/**
	 * @return the paddingBottom
	 */
	public float getPaddingBottom() {
		return paddingBottom;
	}

	/**
	 * @param paddingBottom
	 *            the paddingBottom to set
	 */
	public void setPaddingBottom(float quadrantPaddingBottom) {
		this.paddingBottom = quadrantPaddingBottom;
	}

	/**
	 * @return the paddingRight
	 */
	public float getPaddingRight() {
		return paddingRight;
	}

	/**
	 * @param paddingRight
	 *            the paddingRight to set
	 */
	public void setPaddingRight(float quadrantPaddingRight) {
		this.paddingRight = quadrantPaddingRight;
	}

	/**
	 * @param padding
	 *            the paddingTop paddingBottom
	 *            paddingLeft paddingRight to set
	 * 
	 */
	public void setPadding(float padding) {
		this.paddingTop = padding;
		this.paddingLeft = padding;
		this.paddingBottom = padding;
		this.paddingRight = padding;
	}

	/**
	 * @param topnbottom
	 *            the paddingTop paddingBottom to set
	 * @param leftnright
	 *            the paddingLeft paddingRight to set
	 * 
	 */
	public void setPadding(float topnbottom, float leftnright) {
		this.paddingTop = topnbottom;
		this.paddingLeft = leftnright;
		this.paddingBottom = topnbottom;
		this.paddingRight = leftnright;
	}

	/**
	 * @param top
	 *            the paddingTop to set
	 * @param right
	 *            the paddingLeft to set
	 * @param bottom
	 *            the paddingBottom to set
	 * @param left
	 *            the paddingRight to set
	 * 
	 */
	public void setPadding(float top, float right, float bottom,
			float left) {
		this.paddingTop = top;
		this.paddingLeft = right;
		this.paddingBottom = bottom;
		this.paddingRight = left;
	}
	
	public float getEndX(){
		return getStartX() + getWidth();
	}

	public float getEndY(){
		return getStartY() + getHeight();
	}
	
	public float getPaddingStartX() {
		return getStartX() + paddingLeft;
	}

	public float getPaddingEndX() {
		return getEndX() - paddingRight;
	}

	public float getPaddingStartY() {
		return getStartY() + paddingTop;
	}

	public float getPaddingEndY() {
		return getEndY() - paddingBottom;
	}

	public float getPaddingWidth() {
		return getWidth() - paddingLeft
				- paddingRight;
	}

	public float getPaddingHeight() {
		return getHeight() - paddingTop
				- paddingBottom;
	}

    /**
     * @return the frame
     */
    public RectF getFrame() {
        return frame;
    }

    /**
     * @param frame the frame to set
     */
    public void setFrame(RectF frame) {
        this.frame = frame;
    }

    /**
     * @return the orgin
     */
    public PointF getOrigin() {
        return new PointF(frame.left,frame.top);
    }

    /**
     * @return the center
     */
    public PointF getCenter() {
        return new PointF(frame.centerX(), frame.centerY());
    }

    
    /* (non-Javadoc)
     * @see cn.limc.androidcharts.common.IQuadrant#getWidth()
     */
    @Override
    public float getWidth() {
        return frame.right - frame.left;
    }

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.common.IQuadrant#getHeight()
     */
    @Override
    public float getHeight() {
        return frame.bottom - frame.top;
    }

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.common.IQuadrant#getStartX()
     */
    @Override
    public float getStartX() {
        // TODO Auto-generated method stub
        return getOrigin().x;
    }

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.common.IQuadrant#getStartY()
     */
    @Override
    public float getStartY() {
        // TODO Auto-generated method stub
        return getOrigin().y;
    }
    
    /**
     * AbstractDrawer
     * Description: <br>
     *   <p>add description here </p>
     * Tags: <br>
     *   <p> </p>
     *
     * @author limc
     * @version v1.0 
     * 
     * History: <br>
     * 2015-7-29 limc create v1.0 <br>
     *
     */
    public abstract class AbstractDrawer {
        
        public static final int LEFT_TO_RIGHT = 1;
        public static final int RIGHT_TO_LEFT = 2;
        public static final int BOTTOM_TO_TOP = 3;
        public static final int TOP_TO_BOTTOM = 4;
        
        public static final int CENTER_TO_EDGE = 5;
        public static final int EDGE_TO_CENTER = 6;
        
        public static final int CLOCK_WISE = 7;
        public static final int COUNTER_CLOCK_WISE = 8;
       
        
        protected DataCursor dataCursor;
        protected ChartDataSet dataSet;
        
        /**
         * 
         */
        public AbstractDrawer(ChartDataSet dataSet, DataCursor dataCursor) {
            super();
            this.dataCursor = dataCursor;
            this.dataSet = dataSet;
        }
        
        public void draw(Canvas canvas) {
            if (null == dataSet) {
                return;
            }
            if (dataSet.size() == 0) {
                return;
            }

            for(int i=0; i< dataSet.size() ; i++){
                ChartDataTable table = dataTableForIndex(dataSet,i);
                if (null == table) {
                    continue;
                }
                if(table.size() == 0){
                    continue;
                }
                for (int j = dataCursor.getDisplayFrom(); j < dataCursor.getDisplayTo(); j++) {
                    drawShape(canvas,table,j);
                }
            }
        }
        
        public abstract ChartDataTable dataTableForIndex(ChartDataSet dataSet, int index);
        public abstract ChartDataRow dataRowForIndex(ChartDataTable dataTable, int index);
        public abstract Shape shapeForDataRow(ChartDataTable dataTable, int index);
        public abstract void drawShape(Canvas canvas, ChartDataTable dataTable,int index);
        
        public abstract float getStepX();
        public abstract float getStartX();
        public abstract float getNextX(float base);
        public abstract float getNextX(float base, int i);
        public abstract float getStepY();
        public abstract float getStartY();
        public abstract float getNextY(float base);
        public abstract float getNextY(float base, int i);
    }
    
    public class StepDrawer extends AbstractDrawer {
        
        /**
         * @param quadrant
         * @param dataSet
         * @param dataCursor
         */
        public StepDrawer(ChartDataSet dataSet, DataCursor dataCursor) {
            super(dataSet, dataCursor);
        }

        /*
         * (non-Javadoc)
         * @see cn.limc.androidcharts.draw.AbstractDrawer#dataTableForIndex(cn.limc.androidcharts.series.ChartDataSet, int)
         */
        public ChartDataTable dataTableForIndex(ChartDataSet dataSet, int index){
            return dataSet.getChartTable(index);
        }
        
        /*
         * (non-Javadoc)
         * @see cn.limc.androidcharts.draw.AbstractDrawer#dataRowForIndex(cn.limc.androidcharts.series.ChartDataTable, int)
         */
        public ChartDataRow dataRowForIndex(ChartDataTable dataTable, int index){
            return dataTable.get(index);
        }
        
        /*
         * (non-Javadoc)
         * @see cn.limc.androidcharts.draw.AbstractDrawer#shapeForDataRow(cn.limc.androidcharts.series.ChartDataTable, int)
         */
        public Shape shapeForDataRow(ChartDataTable dataTable, int index){
            return new Bar();
        }
        
        /*
         * (non-Javadoc)
         * @see cn.limc.androidcharts.draw.AbstractDrawer#drawShape(android.graphics.Canvas, cn.limc.androidcharts.series.ChartDataTable, int)
         */
        public void drawShape(Canvas canvas,ChartDataTable dataTable, int index)
        {
            IMeasurable data = (IMeasurable)dataRowForIndex(dataTable,index);
            Shape shape = shapeForDataRow(dataTable, index);
           // shape.setUp(this.quadrant,mData,getStartX() + index * getStepX(),getStepX());
            shape.draw(canvas);
        }

        /* (non-Javadoc)
         * @see cn.limc.androidcharts.draw.AbstractDrawer#getStepX()
         */
        @Override
        public float getStepX() {
            return  AbstractComponent.this.getPaddingWidth() / dataCursor.getDisplayNumber();
        }

        /* (non-Javadoc)
         * @see cn.limc.androidcharts.draw.AbstractDrawer#getStartX()
         */
        @Override
        public float getStartX() {
            return  AbstractComponent.this.getPaddingStartX();
        }

        /* (non-Javadoc)
         * @see cn.limc.androidcharts.draw.AbstractDrawer#getNextX(float)
         */
        @Override
        public float getNextX(float base) {
            return base + getStepX();
        }

        /* (non-Javadoc)
         * @see cn.limc.androidcharts.draw.AbstractDrawer#getNextX(float, int)
         */
        @Override
        public float getNextX(float base, int i) {
            return base + i * getStepX();
        }

        /* (non-Javadoc)
         * @see cn.limc.androidcharts.draw.AbstractDrawer#getStepY()
         */
        @Override
        public float getStepY() {
            return AbstractComponent.this.getPaddingHeight() / dataCursor.getDisplayNumber();
        }

        /* (non-Javadoc)
         * @see cn.limc.androidcharts.draw.AbstractDrawer#getStartY()
         */
        @Override
        public float getStartY() {
            return AbstractComponent.this.getPaddingStartY();
        }

        /* (non-Javadoc)
         * @see cn.limc.androidcharts.draw.AbstractDrawer#getNextY(float)
         */
        @Override
        public float getNextY(float base) {
            return base + getStepY();
        }

        /* (non-Javadoc)
         * @see cn.limc.androidcharts.draw.AbstractDrawer#getNextY(float, int)
         */
        @Override
        public float getNextY(float base, int i) {
            return base + i * getStepY();
        }
           
    }

    /**
     * @return the parent
     */
    public GridChart getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(GridChart parent) {
        this.parent = parent;
    }
}
