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
	public Quadrant() {
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
	protected float quadrantPaddingTop = DEFAULT_QUADRANT_PADDING_TOP;

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
	protected float quadrantPaddingLeft = DEFAULT_QUADRANT_PADDING_LEFT;
	protected float quadrantPaddingBottom = DEFAULT_QUADRANT_PADDING_BOTTOM;

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
	protected float quadrantPaddingRight = DEFAULT_QUADRANT_PADDING_RIGHT;

	/**
	 * @return the quadrantPaddingTop
	 */
	public float getQuadrantPaddingTop() {
		return quadrantPaddingTop;
	}

	/**
	 * @param quadrantPaddingTop
	 *            the quadrantPaddingTop to set
	 */
	public void setQuadrantPaddingTop(float quadrantPaddingTop) {
		this.quadrantPaddingTop = quadrantPaddingTop;
	}

	/**
	 * @return the quadrantPaddingLeft
	 */
	public float getQuadrantPaddingLeft() {
		return quadrantPaddingLeft;
	}

	/**
	 * @param quadrantPaddingLeft
	 *            the quadrantPaddingLeft to set
	 */
	public void setQuadrantPaddingLeft(float quadrantPaddingLeft) {
		this.quadrantPaddingLeft = quadrantPaddingLeft;
	}

	/**
	 * @return the quadrantPaddingBottom
	 */
	public float getQuadrantPaddingBottom() {
		return quadrantPaddingBottom;
	}

	/**
	 * @param quadrantPaddingBottom
	 *            the quadrantPaddingBottom to set
	 */
	public void setQuadrantPaddingBottom(float quadrantPaddingBottom) {
		this.quadrantPaddingBottom = quadrantPaddingBottom;
	}

	/**
	 * @return the quadrantPaddingRight
	 */
	public float getQuadrantPaddingRight() {
		return quadrantPaddingRight;
	}

	/**
	 * @param quadrantPaddingRight
	 *            the quadrantPaddingRight to set
	 */
	public void setQuadrantPaddingRight(float quadrantPaddingRight) {
		this.quadrantPaddingRight = quadrantPaddingRight;
	}

	/**
	 * @param padding
	 *            the quadrantPaddingTop quadrantPaddingBottom
	 *            quadrantPaddingLeft quadrantPaddingRight to set
	 * 
	 */
	public void setQuadrantPadding(float padding) {
		this.quadrantPaddingTop = padding;
		this.quadrantPaddingLeft = padding;
		this.quadrantPaddingBottom = padding;
		this.quadrantPaddingRight = padding;
	}

	/**
	 * @param topnbottom
	 *            the quadrantPaddingTop quadrantPaddingBottom to set
	 * @param leftnright
	 *            the quadrantPaddingLeft quadrantPaddingRight to set
	 * 
	 */
	public void setQuadrantPadding(float topnbottom, float leftnright) {
		this.quadrantPaddingTop = topnbottom;
		this.quadrantPaddingLeft = leftnright;
		this.quadrantPaddingBottom = topnbottom;
		this.quadrantPaddingRight = leftnright;
	}

	/**
	 * @param top
	 *            the quadrantPaddingTop to set
	 * @param right
	 *            the quadrantPaddingLeft to set
	 * @param bottom
	 *            the quadrantPaddingBottom to set
	 * @param left
	 *            the quadrantPaddingRight to set
	 * 
	 */
	public void setQuadrantPadding(float top, float right, float bottom,
			float left) {
		this.quadrantPaddingTop = top;
		this.quadrantPaddingLeft = right;
		this.quadrantPaddingBottom = bottom;
		this.quadrantPaddingRight = left;
	}
	
	public float getQuadrantPaddingStartX() {
		return getQuadrantStartX() + quadrantPaddingLeft;
	}

	public float getQuadrantPaddingEndX() {
		return getQuadrantEndX() - quadrantPaddingRight;
	}

	public float getQuadrantPaddingStartY() {
		return getQuadrantStartY() + quadrantPaddingTop;
	}

	public float getQuadrantPaddingEndY() {
		return getQuadrantEndY() - quadrantPaddingBottom;
	}

	public float getQuadrantPaddingWidth() {
		return getQuadrantWidth() - quadrantPaddingLeft
				- quadrantPaddingRight;
	}

	public float getQuadrantPaddingHeight() {
		return getQuadrantHeight() - quadrantPaddingTop
				- quadrantPaddingBottom;
	}

}
