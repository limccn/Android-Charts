/*
 * AbstractAxis.java
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

/** 
 * <p>en</p>
 * <p>jp</p>
 * <p>cn</p>
 *
 * @author limc 
 * @version v1.0 2014/06/24 18:59:50 
 *  
 */
public abstract class AbstractAxis extends AbstractComponent implements Axis {	
	protected int lineColor = DEFAULT_LINE_COLOR;
	protected float lineWidth = DEFAULT_LINE_WIDTH;
	protected int position = DEFAULT_POSITION;
	protected int degreeFontColor = DEFAULT_DEGREE_FONT_COLOR;
	protected int degreeFontSize = DEFAULT_DEGREE_FONT_SIZE;
	
    protected int gridAlignType = DEFAULT_ALIGN_TYPE;
	
    /**
     * <p>
     * Numbers of grid‘s latitude line
     * </p>
     * <p>
     * 緯線の数量
     * </p>
     * <p>
     * 网格纬线的数量
     * </p>
     */
    protected int titlesNum = DEFAULT_TITLES_NUM;


	
	public abstract void drawLine(Canvas canvas);
	public abstract void drawDegrees(Canvas canvas);
    
    public AbstractAxis() {
        this.paddingTop = 0;
        this.paddingLeft = 0;
        this.paddingBottom = 0;
        this.paddingRight = 0;
    }
    
    
    public void draw(Canvas canvas) {
        drawLine(canvas);
        drawDegrees(canvas);
    }
    
    @Override
    public int titlesNum(){
        return titlesNum;
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
	 * @return the lineWidth
	 */
	public float getLineWidth() {
		return lineWidth;
	}

	/**
	 * @param lineWidth the lineWidth to set
	 */
	public void setLineWidth(float lineWidth) {
		this.lineWidth = lineWidth;
	}

	/**
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(int position) {
		this.position = position;
	}
    /**
     * @return the degreeFontColor
     */
    public int getDegreeFontColor() {
        return degreeFontColor;
    }
    /**
     * @param degreeFontColor the degreeFontColor to set
     */
    public void setDegreeFontColor(int degreeFontColor) {
        this.degreeFontColor = degreeFontColor;
    }
    /**
     * @return the degreeFontSize
     */
    public int getDegreeFontSize() {
        return degreeFontSize;
    }
    /**
     * @param degreeFontSize the degreeFontSize to set
     */
    public void setDegreeFontSize(int degreeFontSize) {
        this.degreeFontSize = degreeFontSize;
    }
    /**
     * @return the degree
     */
    public Degree getDegree() {
        return degreeForAxis(this);
    }
    /**
     * @return the bindComponent
     */
    public DataComponent getBindComponent() {
        return componentForAxis(this);
    }
    
     public abstract Degree degreeForAxis(Axis axis);
     public abstract DataComponent componentForAxis(Axis axis);
     
}
