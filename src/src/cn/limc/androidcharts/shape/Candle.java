//
// Candle.java
// cn.limc.androidcharts.shape
//
// Created by limc on 2015-7-22.
//
// Copyright 2015 Shanghai Okasan-Huada Computer System Co. Ltd., All rights reserved.
//
package cn.limc.androidcharts.shape;
import cn.limc.androidcharts.component.DataComponent;
import cn.limc.androidcharts.series.IMeasurable;
import cn.limc.androidcharts.series.OHLCEntity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;

/**
 * Candle
 * Description: <br>
 *   <p>add description here </p>
 * Tags: <br>
 *   <p> </p>
 *
 * @author limc
 * @version v1.0 
 * 
 * History: <br>
 * 2015-7-22 limc create v1.0 <br>
 *
 */
public class Candle extends AbstractShape implements Rectangle {
    

    public static final int DEFAULT_STICK_SPACING = 1;

    /**
     * <p>
     * Default price up stick's border color
     * </p>
     * <p>
     * 値上がりローソクのボーダー色のデフォルト値
     * </p>
     * <p>
     * 默认阳线的边框颜色
     * </p>
     */
    public static final int DEFAULT_POSITIVE_STICK_BORDER_COLOR = Color.RED;

    /**
     * <p>
     * Default price up stick's fill color
     * </p>
     * <p>
     * 値上がりローソクの色のデフォルト値
     * </p>
     * <p>
     * 默认阳线的填充颜色
     * </p>
     */
    public static final int DEFAULT_POSITIVE_STICK_FILL_COLOR = Color.RED;

    /**
     * <p>
     * Default price down stick's border color
     * </p>
     * <p>
     * 値下りローソクのボーダー色のデフォルト値
     * </p>
     * <p>
     * 默认阴线的边框颜色
     * </p>
     */
    public static final int DEFAULT_NEGATIVE_STICK_BORDER_COLOR = Color.GREEN;

    /**
     * <p>
     * Default price down stick's fill color
     * </p>
     * <p>
     * 値下りローソクの色のデフォルト値
     * </p>
     * <p>
     * 默认阴线的填充颜色
     * </p>
     */
    public static final int DEFAULT_NEGATIVE_STICK_FILL_COLOR = Color.GREEN;

    /**
     * <p>
     * Default price no change stick's color (cross-star,T-like etc.)
     * </p>
     * <p>
     * クローススターの色のデフォルト値
     * </p>
     * <p>
     * 默认十字线显示颜色
     * </p>
     */
    public static final int DEFAULT_CROSS_STAR_COLOR = Color.LTGRAY;

    /**
     * <p>
     * Price up stick's border color
     * </p>
     * <p>
     * 値上がりローソクのボーダー色
     * </p>
     * <p>
     * 阳线的边框颜色
     * </p>
     */
    private int positiveStickBorderColor = DEFAULT_POSITIVE_STICK_BORDER_COLOR;

    /**
     * <p>
     * Price up stick's fill color
     * </p>
     * <p>
     * 値上がりローソクの色
     * </p>
     * <p>
     * 阳线的填充颜色
     * </p>
     */
    private int positiveStickFillColor = DEFAULT_POSITIVE_STICK_FILL_COLOR;

    /**
     * <p>
     * Price down stick's border color
     * </p>
     * <p>
     * 値下りローソクのボーダー色
     * </p>
     * <p>
     * 阴线的边框颜色
     * </p>
     */

    private int negativeStickBorderColor = DEFAULT_NEGATIVE_STICK_BORDER_COLOR;

    /**
     * <p>
     * Price down stick's fill color
     * </p>
     * <p>
     * 値下りローソクの色
     * </p>
     * <p>
     * 阴线的填充颜色
     * </p>
     */
    private int negativeStickFillColor = DEFAULT_NEGATIVE_STICK_FILL_COLOR;

    /**
     * <p>
     * Price no change stick's color (cross-star,T-like etc.)
     * </p>
     * <p>
     * クローススターの色（価格変動無し）
     * </p>
     * <p>
     * 十字线显示颜色（十字星,一字平线,T形线的情况）
     * </p>
     */
    private int crossStarColor = DEFAULT_CROSS_STAR_COLOR;
    
    
    protected int stickSpacing = DEFAULT_STICK_SPACING;
    
    private OHLCEntity stickData;
    
    private float openY;
    private float highY;
    private float lowY;
    private float closeY;
    
    /* (non-Javadoc)
     * @see cn.limc.androidcharts.shape.Rectangle#setUp(cn.limc.androidcharts.common.IChart, float, float)
     */
    @Override
    public void setUp(DataComponent component, float from, float width) {
        super.setUp(component);
        left = from + stickSpacing / 2;
        right = from + width - stickSpacing /2;
        
    }
    
    /*
     * (non-Javadoc)
     * @see cn.limc.androidcharts.shape.Rectangle#setUp(cn.limc.androidcharts.common.IChart, cn.limc.androidcharts.series.IMeasurable, float, float)
     */
    public void setUp(DataComponent component ,IMeasurable data, float from , float width) {
        this.setUp(component,from,width);
        setStickData(data);
    }
    
    /* (non-Javadoc)
     * 
     * @param canvase 
     * @see cn.limc.androidcharts.shape.Shape#draw(android.graphics.Canvas) 
     */
    public void draw(Canvas canvas) {   

        if (stickData.getOpen() < stickData.getClose()) {
            Paint mPaintPositiveFill = new Paint();
            mPaintPositiveFill.setStyle(Style.FILL);
            mPaintPositiveFill.setColor(positiveStickFillColor);
            
            Paint mPaintPositiveBorder = new Paint();
            mPaintPositiveBorder.setStyle(Style.STROKE);
            mPaintPositiveBorder.setColor(positiveStickBorderColor);
            
            // stick or line
            if (width() >= 2f) {
                canvas.drawRect(left, openY, right, closeY, mPaintPositiveFill);
            }
            canvas.drawLine(left + width() / 2f, top, left + width() / 2f, bottom, mPaintPositiveBorder);
        } else if (stickData.getOpen() > stickData.getClose()) {
            
            Paint mPaintNegativeFill = new Paint();
            mPaintNegativeFill.setStyle(Style.FILL);
            mPaintNegativeFill.setColor(negativeStickFillColor);
            
            Paint mPaintNegativeBorder = new Paint();
            mPaintNegativeBorder.setStyle(Style.STROKE);
            mPaintNegativeBorder.setColor(negativeStickBorderColor);
            
            // stick or line
            if (width() >= 2f) {
                canvas.drawRect(left, openY,right, closeY, mPaintNegativeFill);
            }
            canvas.drawLine(left + width() / 2f, top, left + width() / 2f, bottom, mPaintNegativeBorder);
        } else {
            Paint mPaintCrossStar = new Paint();
            mPaintCrossStar.setStyle(Style.STROKE);
            mPaintCrossStar.setColor(crossStarColor);
            
            // line or point
            if (width() >= 2f) {
                canvas.drawRect(left, openY, right,closeY, mPaintCrossStar);
            }
            canvas.drawLine(left + width() / 2f, top, left + width() / 2f, bottom, mPaintCrossStar);
        }
    }

    /**
     * @return the stickData
     */
    public IMeasurable getStickData() {
        return stickData;
    }

    /**
     * @param stickData the stickData to set
     */
    public void setStickData(IMeasurable stickData) {
        this.stickData = (OHLCEntity)stickData;
        float openY = (float) component.heightForRate(this.stickData.getOpen());
        float highY = (float) component.heightForRate(this.stickData.getHigh());
        float lowY = (float) component.heightForRate(this.stickData.getLow());
        float closeY = (float) component.heightForRate(this.stickData.getClose());
        
        this.openY = openY;
        this.top = highY;
        this.highY = highY;
        this.bottom = lowY;
        this.lowY = lowY;
        this.closeY = closeY;
    }

    /**
     * @return the positiveStickBorderColor
     */
    public int getPositiveStickBorderColor() {
        return positiveStickBorderColor;
    }

    /**
     * @param positiveStickBorderColor the positiveStickBorderColor to set
     */
    public void setPositiveStickBorderColor(int positiveStickBorderColor) {
        this.positiveStickBorderColor = positiveStickBorderColor;
    }

    /**
     * @return the positiveStickFillColor
     */
    public int getPositiveStickFillColor() {
        return positiveStickFillColor;
    }

    /**
     * @param positiveStickFillColor the positiveStickFillColor to set
     */
    public void setPositiveStickFillColor(int positiveStickFillColor) {
        this.positiveStickFillColor = positiveStickFillColor;
    }

    /**
     * @return the negativeStickBorderColor
     */
    public int getNegativeStickBorderColor() {
        return negativeStickBorderColor;
    }

    /**
     * @param negativeStickBorderColor the negativeStickBorderColor to set
     */
    public void setNegativeStickBorderColor(int negativeStickBorderColor) {
        this.negativeStickBorderColor = negativeStickBorderColor;
    }

    /**
     * @return the negativeStickFillColor
     */
    public int getNegativeStickFillColor() {
        return negativeStickFillColor;
    }

    /**
     * @param negativeStickFillColor the negativeStickFillColor to set
     */
    public void setNegativeStickFillColor(int negativeStickFillColor) {
        this.negativeStickFillColor = negativeStickFillColor;
    }

    /**
     * @return the crossStarColor
     */
    public int getCrossStarColor() {
        return crossStarColor;
    }

    /**
     * @param crossStarColor the crossStarColor to set
     */
    public void setCrossStarColor(int crossStarColor) {
        this.crossStarColor = crossStarColor;
    }

    /**
     * @return the openY
     */
    public float getOpenY() {
        return openY;
    }

    /**
     * @param openY the openY to set
     */
    public void setOpenY(float openY) {
        this.openY = openY;
    }

    /**
     * @return the highY
     */
    public float getHighY() {
        return highY;
    }

    /**
     * @param highY the highY to set
     */
    public void setHighY(float highY) {
        this.highY = highY;
    }

    /**
     * @return the lowY
     */
    public float getLowY() {
        return lowY;
    }

    /**
     * @param lowY the lowY to set
     */
    public void setLowY(float lowY) {
        this.lowY = lowY;
    }

    /**
     * @return the closeY
     */
    public float getCloseY() {
        return closeY;
    }

    /**
     * @param closeY the closeY to set
     */
    public void setCloseY(float closeY) {
        this.closeY = closeY;
    }

}
