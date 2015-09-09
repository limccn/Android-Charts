/*
 * KShape.java
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

package cn.limc.androidcharts.shape;
import cn.limc.androidcharts.component.DataComponent;
import cn.limc.androidcharts.series.IMeasurable;
import cn.limc.androidcharts.series.OHLCEntity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;

/**
 * KShape
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
public class KShape extends AbstractShape implements Rectangle {
    public static final int K_STYLE_CANDLE = 1;
    public static final int K_STYLE_AMAERICAN = 2;
    
    public static final int DEFAULT_K_STYLE = K_STYLE_CANDLE;
    
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

    
    protected int kStyle = DEFAULT_K_STYLE;

    protected int stickSpacing = DEFAULT_STICK_SPACING;
    
    protected Paint positiveFillPaint;
    protected Paint positiveBorderPaint;
    protected Paint negativeFillPaint;
    protected Paint negativeBorderPaint;
    protected Paint crossStarPaint;
    
    
    protected OHLCEntity stickData;
    
    protected float openY;
    protected float highY;
    protected float lowY;
    protected float closeY;
   
    
    /**
     * 
     */
    public KShape() {
        super();
        
        positiveFillPaint = new Paint();
        positiveFillPaint.setStyle(Style.FILL);
        positiveFillPaint.setColor(DEFAULT_POSITIVE_STICK_FILL_COLOR);
        
        positiveBorderPaint = new Paint();
        positiveBorderPaint.setStyle(Style.STROKE);
        positiveBorderPaint.setColor(DEFAULT_POSITIVE_STICK_BORDER_COLOR);
        
        negativeFillPaint = new Paint();
        negativeFillPaint.setStyle(Style.FILL);
        negativeFillPaint.setColor(DEFAULT_NEGATIVE_STICK_FILL_COLOR);
        
        negativeBorderPaint = new Paint();
        negativeBorderPaint.setStyle(Style.STROKE);
        negativeBorderPaint.setColor(DEFAULT_NEGATIVE_STICK_BORDER_COLOR);
        
        crossStarPaint = new Paint();
        crossStarPaint.setStyle(Style.STROKE);
        crossStarPaint.setColor(DEFAULT_CROSS_STAR_COLOR);
        
    }

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
            // stick or line
            if (width() >= 2f) {
                if (kStyle == K_STYLE_CANDLE) {
                    canvas.drawRect(left, closeY, right, openY, positiveFillPaint);
                }else if(kStyle == K_STYLE_AMAERICAN){
                    canvas.drawLine(left, openY, left + width() / 2f, openY, positiveBorderPaint);
                    canvas.drawLine(left + width() / 2f, closeY,right, closeY, positiveBorderPaint);
                }
            }
            canvas.drawLine(left + width() / 2f, top, left + width() / 2f, bottom, positiveBorderPaint);
        } else if (stickData.getOpen() > stickData.getClose()) {
            // stick or line
            if (width() >= 2f) {
                if (kStyle == K_STYLE_CANDLE) {
                    canvas.drawRect(left, openY , right, closeY, negativeFillPaint);
                }else if(kStyle == K_STYLE_AMAERICAN){
                    canvas.drawLine(left, openY, left + width() / 2f, openY, negativeBorderPaint);
                    canvas.drawLine(left + width() / 2f, closeY,right, closeY, negativeBorderPaint);
                }
            }
            canvas.drawLine(left + width() / 2f, top, left + width() / 2f, bottom, negativeBorderPaint);
        } else {
            // line or point
            if (width() >= 2f) {
                canvas.drawLine(left, openY, right , closeY, crossStarPaint);
            }
            canvas.drawLine(left + width() / 2f, top, left + width() / 2f, bottom, crossStarPaint);
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

    /**
     * @return the kStyle
     */
    public int getkStyle() {
        return kStyle;
    }

    /**
     * @param kStyle the kStyle to set
     */
    public void setkStyle(int kStyle) {
        this.kStyle = kStyle;
    }

    /**
     * @return the stickSpacing
     */
    public int getStickSpacing() {
        return stickSpacing;
    }

    /**
     * @param stickSpacing the stickSpacing to set
     */
    public void setStickSpacing(int stickSpacing) {
        this.stickSpacing = stickSpacing;
    }

    /**
     * @param stickData the stickData to set
     */
    public void setStickData(OHLCEntity stickData) {
        this.stickData = stickData;
    }

}
