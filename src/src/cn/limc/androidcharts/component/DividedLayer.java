//
// DividedLayer.java
// cn.limc.androidcharts.component
//
// Created by limc on 2015-7-30.
//
// Copyright 2015 Shanghai Okasan-Huada Computer System Co. Ltd., All rights reserved.
//
package cn.limc.androidcharts.component;

import android.graphics.Canvas;
import android.graphics.RectF;

/**
 * DividedLayer Description: <br>
 * <p>
 * add description here
 * </p>
 * Tags: <br>
 * <p>
 * </p>
 * 
 * @author limc
 * @version v1.0
 * 
 *          History: <br>
 *          2015-7-30 limc create v1.0 <br>
 * 
 */
public class DividedLayer extends AbstractLayer {
    
    public static final int DEFAULT_TO_LEFT = 50;
    public static final int DEFAULT_TO_TOP = 20;
    public static final int DEFAULT_TO_RIGHT = 50;
    public static final int DEFAULT_TO_BOTTOM = 20;
    
    public static final int TOP_LEFT = 1 << 0;
    public static final int TOP_MIDDLE = 1 << 1;
    public static final int TOP_RIGHT = 1 << 2;
    public static final int CENTER_LEFT = 1 << 3;
    public static final int CENTER_MIDDLE = 1 << 4;
    public static final int CENTER_RIGHT = 1 << 5;
    public static final int BOTTOM_LEFT = 1 << 6;
    public static final int BOTTOM_MIDDLE = 1 << 7;
    public static final int BOTTOM_RIGHT = 1 << 8;  
    public static final int TOP = TOP_LEFT|TOP_MIDDLE|TOP_RIGHT;
    public static final int CENTER = CENTER_LEFT|CENTER_MIDDLE|CENTER_RIGHT;
    public static final int BOTTOM = BOTTOM_LEFT|BOTTOM_MIDDLE|BOTTOM_RIGHT;
    public static final int LEFT = TOP_LEFT|CENTER_LEFT|BOTTOM_LEFT;
    public static final int MIDDLE = TOP_MIDDLE|CENTER_MIDDLE|BOTTOM_MIDDLE;
    public static final int RIGHT = TOP_RIGHT|CENTER_RIGHT|BOTTOM_RIGHT;
    public static final int FULL = TOP_LEFT|TOP_MIDDLE|TOP_RIGHT|TOP_MIDDLE|CENTER_MIDDLE|BOTTOM_MIDDLE|TOP_RIGHT|CENTER_RIGHT|BOTTOM_RIGHT;    
    
    protected int toLeft = DEFAULT_TO_LEFT;
    protected int toTop = DEFAULT_TO_TOP;
    protected int toRight = DEFAULT_TO_RIGHT;
    protected int toBottom = DEFAULT_TO_BOTTOM;
    
    private Component component;
    private int divide;
    
    /**
     * 
     */
    public DividedLayer() {
        super();
    }

    public DividedLayer( int toLeft, int toTop , int toRight , int toBottom) {
        this.toLeft = toLeft;
        this.toTop = toTop;
        this.toRight = toRight;
        this.toBottom = toBottom;
    }

    public void FillComponent(Component component, int divide) {
        this.component = component;
        this.divide = divide;
        
        if (null != component) {
            this.component.setFrame(getCombinedRectF());
        }
    }
    
    public RectF getCombinedRectF(){
        RectF rectF = new RectF();
        rectF.union(combineRect(divide & TOP_LEFT));
        rectF.union(combineRect(divide & TOP_MIDDLE));
        rectF.union(combineRect(divide & TOP_RIGHT));
        rectF.union(combineRect(divide & CENTER_LEFT));
        rectF.union(combineRect(divide & CENTER_MIDDLE));
        rectF.union(combineRect(divide & CENTER_RIGHT));
        rectF.union(combineRect(divide & BOTTOM_LEFT));
        rectF.union(combineRect(divide & BOTTOM_MIDDLE));
        rectF.union(combineRect(divide & BOTTOM_RIGHT));
        return rectF;
    }

    public RectF combineRect(int type) {
        switch (type) {
            case TOP_LEFT:
                return rectTopLeft();
            case TOP_MIDDLE:
                return rectTopMiddle();
            case TOP_RIGHT:
                return rectTopRight();
            case CENTER_LEFT:
                return rectCenterLeft();
            case CENTER_MIDDLE:
                return rectCenterMiddle();
            case CENTER_RIGHT:
                return rectCenterRight();
            case BOTTOM_LEFT:
                return rectBottomLeft();
            case BOTTOM_MIDDLE:
                return rectBottomMiddle();
            case BOTTOM_RIGHT:
                return rectBottomRight();
            default:
                return new RectF(0, 0, 0, 0);
        }
    }

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.component.Layer1#rectTopLeft()
     */
    public RectF rectTopLeft() {
        return new RectF(parent.getBorderWidth(), parent.getBorderWidth(), toLeft,
                toTop);
    }

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.component.Layer1#rectTopMiddle()
     */
    public RectF rectTopMiddle() {
        return new RectF(toLeft, parent.getBorderWidth(), parent.getWidth()-toRight,
                toTop);
    }

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.component.Layer1#rectTopRight()
     */
    public RectF rectTopRight() {
        return new RectF(parent.getWidth()-toRight, parent.getBorderWidth(), parent.getWidth()
                - parent.getBorderWidth(), toTop);
    }

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.component.Layer1#rectCenterLeft()
     */
    public RectF rectCenterLeft() {
        return new RectF(parent.getBorderWidth(), toTop, toLeft,
                parent.getHeight()-toBottom);
    }

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.component.Layer1#rectCenterMiddle()
     */
    public RectF rectCenterMiddle() {
        return new RectF(toLeft, toTop, parent.getWidth()-toRight, parent.getHeight()-toBottom);
    }

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.component.Layer1#rectCenterRight()
     */
    public RectF rectCenterRight() {
        return new RectF(parent.getWidth()-toRight, toTop, parent.getWidth()
                - parent.getBorderWidth(), parent.getHeight()-toBottom);
    }

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.component.Layer1#rectBottomLeft()
     */
    public RectF rectBottomLeft() {
        return new RectF(parent.getBorderWidth(), parent.getHeight()-toBottom, toLeft,
                parent.getHeight() - parent.getBorderWidth());
    }

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.component.Layer1#rectBottomMiddle()
     */
    public RectF rectBottomMiddle() {
        return new RectF(toLeft, parent.getHeight()-toBottom, parent.getWidth()-toRight,
                parent.getHeight() - parent.getBorderWidth());
    }

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.component.Layer1#rectBottomRight()
     */
    public RectF rectBottomRight() {
        return new RectF(parent.getWidth()-toRight, parent.getHeight()-toBottom, parent.getWidth()
                - parent.getBorderWidth(), parent.getHeight()
                - parent.getBorderWidth());
    }

    /**
     * @return the component
     */
    public Component getComponent() {
        return component;
    }

    /**
     * @param component the component to set
     */
    public void setComponent(Component component) {
        this.component = component;
    }

    /**
     * @return the divide
     */
    public int getDivide() {
        return divide;
    }

    /**
     * @param divide the divide to set
     */
    public void setDivide(int divide) {
        this.divide = divide;
    }

    /**
     * @return the toTop
     */
    public int getToTop() {
        return toTop;
    }

    /**
     * @param toTop the toTop to set
     */
    public void setToTop(int toTop) {
        this.toTop = toTop;
    }

    /**
     * @return the toLeft
     */
    public int getToLeft() {
        return toLeft;
    }

    /**
     * @param toLeft the toLeft to set
     */
    public void setToLeft(int toLeft) {
        this.toLeft = toLeft;
    }

    /**
     * @return the toBottom
     */
    public int getToBottom() {
        return toBottom;
    }

    /**
     * @param toBottom the toBottom to set
     */
    public void setToBottom(int toBottom) {
        this.toBottom = toBottom;
    }

    /**
     * @return the toRight
     */
    public int getToRight() {
        return toRight;
    }

    /**
     * @param toRight the toRight to set
     */
    public void setToRight(int toRight) {
        this.toRight = toRight;
    }

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.component.Layer#draw(android.graphics.Canvas)
     */
    @Override
    public void draw(Canvas canvas) {
        if (null != component) {
            this.component.setFrame(getCombinedRectF());
            this.component.draw(canvas);       
        }
    }
}
