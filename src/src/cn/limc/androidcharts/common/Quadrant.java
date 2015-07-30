/*
 * Quadrant.java
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

import android.graphics.PointF;
import android.graphics.RectF;
import cn.limc.androidcharts.view.GridChart;

/** 
 * <p>en</p>
 * <p>jp</p>
 * <p>cn</p>
 *
 * @author limc 
 * @version v1.0 2014/06/24 17:29:01 
 *  
 */
public abstract class Quadrant implements IQuadrant{
    protected RectF frame;
	
	protected GridChart inChart;
	
	public Quadrant(GridChart inChart) {
		this.inChart = inChart;
		
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
        return frame.left - frame.right;
    }

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.common.IQuadrant#getHeight()
     */
    @Override
    public float getHeight() {
        return frame.top - frame.bottom;
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
}
