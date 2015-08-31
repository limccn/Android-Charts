/*
 * SimpleGrid.java
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

package cn.limc.androidcharts.component;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Paint.Style;

/**
 * SimpleGrid
 * Description: <br>
 *   <p>add description here </p>
 * Tags: <br>
 *   <p> </p>
 *
 * @author limc
 * @version v1.0 
 * 
 * History: <br>
 * 2014/09/01 limc create v1.0 <br>
 *
 */
public abstract class SimpleGrid extends AbstractComponent implements Grid {

    
    /**
     * <p>
     * Color of grid‘s longitude line
     * </p>
     * <p>
     * 経線の色
     * </p>
     * <p>
     * 网格经线的显示颜色
     * </p>
     */
    private int longitudeColor = DEFAULT_LONGITUDE_COLOR;

    /**
     * <p>
     * Color of grid‘s latitude line
     * </p>
     * <p>
     * 緯線の色
     * </p>
     * <p>
     * 网格纬线的显示颜色
     * </p>
     */
    private int latitudeColor = DEFAULT_LAITUDE_COLOR;
    
 
    
    private float longitudeWidth = DEFAULT_LONGITUDE_WIDTH;
    
    
    
    private float latitudeWidth = DEFAULT_LATITUDE_WIDTH;
    
   

    /**
     * <p>
     * Should display longitude line?
     * </p>
     * <p>
     * 経線を表示するか?
     * </p>
     * <p>
     * 经线是否显示
     * </p>
     */
    private boolean displayLongitude = DEFAULT_DISPLAY_LONGITUDE;

    /**
     * <p>
     * Should display longitude as dashed line?
     * </p>
     * <p>
     * 経線を点線にするか?
     * </p>
     * <p>
     * 经线是否显示为虚线
     * </p>
     */
    private boolean dashLongitude = DEFAULT_DASH_LONGITUDE;

    /**
     * <p>
     * Should display longitude line?
     * </p>
     * <p>
     * 緯線を表示するか?
     * </p>
     * <p>
     * 纬线是否显示
     * </p>
     */
    private boolean displayLatitude = DEFAULT_DISPLAY_LATITUDE;

    /**
     * <p>
     * Should display latitude as dashed line?
     * </p>
     * <p>
     * 緯線を点線にするか?
     * </p>
     * <p>
     * 纬线是否显示为虚线
     * </p>
     */
    private boolean dashLatitude = DEFAULT_DASH_LATITUDE;

    /**
     * <p>
     * dashed line type
     * </p>
     * <p>
     * 点線タイプ?
     * </p>
     * <p>
     * 虚线效果
     * </p>
     */
    private PathEffect dashEffect = DEFAULT_DASH_EFFECT;

//    /**
//     * <p>
//     * Color of text for the longitude　degrees display
//     * </p>
//     * <p>
//     * 経度のタイトルの色
//     * </p>
//     * <p>
//     * 经线刻度字体颜色
//     * </p>
//     */
//    private int longitudeFontColor = DEFAULT_LONGITUDE_FONT_COLOR;
//
//    /**
//     * <p>
//     * Font size of text for the longitude　degrees display
//     * </p>
//     * <p>
//     * 経度のタイトルのフォントサイズ
//     * </p>
//     * <p>
//     * 经线刻度字体大小
//     * </p>
//     */
//    private int longitudeFontSize = DEFAULT_LONGITUDE_FONT_SIZE;
//
//    /**
//     * <p>
//     * Color of text for the latitude　degrees display
//     * </p>
//     * <p>
//     * 緯度のタイトルの色
//     * </p>
//     * <p>
//     * 纬线刻度字体颜色
//     * </p>
//     */
//    private int latitudeFontColor = DEFAULT_LATITUDE_FONT_COLOR;
//
//    /**
//     * <p>
//     * Font size of text for the latitude　degrees display
//     * </p>
//     * <p>
//     * 緯度のタイトルのフォントサイズ
//     * </p>
//     * <p>
//     * 纬线刻度字体大小
//     * </p>
//     */
//    private int latitudeFontSize = DEFAULT_LATITUDE_FONT_SIZE;
    
    
//    /**
//     * <p>
//     * Titles Array for display of X axis
//     * </p>
//     * <p>
//     * X軸の表示用タイトル配列
//     * </p>
//     * <p>
//     * X轴标题数组
//     * </p>
//     */
//    protected List<String> longitudeTitles;
//
//    /**
//     * <p>
//     * Titles for display of Y axis
//     * </p>
//     * <p>
//     * Y軸の表示用タイトル配列
//     * </p>
//     * <p>
//     * Y轴标题数组
//     * </p>
//     */
//    protected List<String> latitudeTitles;
    

    
    public void draw(Canvas canvas){        
        drawLongitudeLine(canvas);
        drawLatitudeLine(canvas);
    }
    
//    public float longitudePostOffset(){
//        return getPaddingWidth() / (longitudeNum - 1);
//    }
//    
//    public float longitudeOffset(){
//        return getPaddingStartX();
//    }
//    
//    public float longitudePostForIndex(int index){
//        float postOffset = longitudePostOffset();
//        float offset = longitudeOffset();
//        return offset + index * postOffset;
//    }
//    
//    public float latitudePostOffset(){
//        return getPaddingHeight() / (latitudeNum - 1);
//    }
//    
//    public float latitudeOffset(){
//        return getPaddingEndY();
//    }
//    public float latitudePostForIndex(int index){
//        float postOffset = latitudePostOffset();
//        float offset = latitudeOffset();
//        return offset - index * postOffset;
//    }
    
//  public float longitudePostOffset(){
//  if (gridAlignType == IFlexableGrid.ALIGN_TYPE_CENTER) {
//      float stickWidth = dataQuadrant.getPaddingWidth() / dataCursor.getDisplayNumber();
//      return (this.dataQuadrant.getPaddingWidth() - stickWidth)/ (simpleGrid.getLongitudeTitles().size() - 1);
//  }else{
//      return this.dataQuadrant.getPaddingWidth()/ (simpleGrid.getLongitudeTitles().size() - 1);
//  }
//}
//
//public float longitudeOffset(){
//  if (gridAlignType ==IFlexableGrid.ALIGN_TYPE_CENTER) {
//      float stickWidth = dataQuadrant.getPaddingWidth() / dataCursor.getDisplayNumber();
//      return dataQuadrant.getPaddingStartX() + stickWidth / 2;
//  }else{
//      return dataQuadrant.getPaddingStartX();
//  }
//}
    

    /**
     * <p>
     * draw longitude lines
     * </p>
     * <p>
     * 経線を書く
     * </p>
     * <p>
     * 绘制经线
     * </p>
     * 
     * @param canvas
     */
    protected void drawLongitudeLine(Canvas canvas) {
        if (!displayLongitude) {
            return;
        }

        Paint mPaintLine = new Paint();
        mPaintLine.setStyle(Style.STROKE);
        mPaintLine.setColor(longitudeColor);
        mPaintLine.setStrokeWidth(longitudeWidth);
        mPaintLine.setAntiAlias(true);
        if (dashLongitude) {
            mPaintLine.setPathEffect(dashEffect);
        }
        HorizontalAxis mAxis = horizontalAxisForGrid(this);
        if (mAxis.titlesNum() > 1) {
            for (int i = 0; i < mAxis.titlesNum(); i++) {
                Path path = new Path();
                
                
                path.moveTo(mAxis.postForIndex(i), getStartY());
                path.lineTo(mAxis.postForIndex(i), getEndY());
                canvas.drawPath(path, mPaintLine);
            }
        }
    }
    
    public abstract HorizontalAxis horizontalAxisForGrid(Grid grid);
    public abstract VerticalAxis verticalAxisForGrid(Grid grid);
        
    /**
     * <p>
     * draw latitude lines
     * </p>
     * <p>
     * 緯線を書く
     * </p>
     * <p>
     * 绘制纬线
     * </p>
     * 
     * @param canvas
     */
    protected void drawLatitudeLine(Canvas canvas) {

        if (!displayLatitude) {
            return;
        }
 
        Paint mPaintLine = new Paint();
        mPaintLine.setStyle(Style.STROKE);
        mPaintLine.setColor(latitudeColor);
        mPaintLine.setStrokeWidth(latitudeWidth);
        mPaintLine.setAntiAlias(true);
        if (dashLatitude) {
            mPaintLine.setPathEffect(dashEffect);
        }
//        Paint mPaintFont = new Paint();
//        mPaintFont.setColor(latitudeFontColor);
//        mPaintFont.setTextSize(latitudeFontSize);
//        mPaintFont.setAntiAlias(true);

        VerticalAxis mAxis = verticalAxisForGrid(this);
        for (int i = 0; i < mAxis.titlesNum(); i++) {
                Path path = new Path();
                path.moveTo(getStartX(), mAxis.postForIndex(i));
                path.lineTo(getEndX(),  mAxis.postForIndex(i));
                canvas.drawPath(path, mPaintLine);
        }
    }
    
//    /**
//     * @return the displayLongitudeTitle
//     */
//    public boolean isDisplayLongitudeTitle() {
//        return displayLongitudeTitle;
//    }
//
//    /**
//     * @param displayLongitudeTitle
//     *            the displayLongitudeTitle to set
//     */
//    public void setDisplayLongitudeTitle(boolean displayLongitudeTitle) {
//        this.displayLongitudeTitle = displayLongitudeTitle;
//    }
//
//    /**
//     * @return the displayAxisYTitle
//     */
//    public boolean isDisplayLatitudeTitle() {
//        return displayLatitudeTitle;
//    }
//
//    /**
//     * @param displayLatitudeTitle
//     *            the displayLatitudeTitle to set
//     */
//    public void setDisplayLatitudeTitle(boolean displayLatitudeTitle) {
//        this.displayLatitudeTitle = displayLatitudeTitle;
//    }

//    /**
//     * @return the latitudeNum
//     */
//    public int getLatitudeNum() {
//        return latitudeNum;
//    }
//
//    /**
//     * @param latitudeNum
//     *            the latitudeNum to set
//     */
//    public void setLatitudeNum(int latitudeNum) {
//        this.latitudeNum = latitudeNum;
//    }
//
//    /**
//     * @return the longitudeNum
//     */
//    public int getLongitudeNum() {
//        return longitudeNum;
//    }
//
//    /**
//     * @param longitudeNum
//     *            the longitudeNum to set
//     */
//    public void setLongitudeNum(int longitudeNum) {
//        this.longitudeNum = longitudeNum;
//    }

    /**
     * @return the displayLongitude
     */
    public boolean isDisplayLongitude() {
        return displayLongitude;
    }

    /**
     * @param displayLongitude
     *            the displayLongitude to set
     */
    public void setDisplayLongitude(boolean displayLongitude) {
        this.displayLongitude = displayLongitude;
    }

    /**
     * @return the dashLongitude
     */
    public boolean isDashLongitude() {
        return dashLongitude;
    }

    /**
     * @param dashLongitude
     *            the dashLongitude to set
     */
    public void setDashLongitude(boolean dashLongitude) {
        this.dashLongitude = dashLongitude;
    }

    /**
     * @return the displayLatitude
     */
    public boolean isDisplayLatitude() {
        return displayLatitude;
    }

    /**
     * @param displayLatitude
     *            the displayLatitude to set
     */
    public void setDisplayLatitude(boolean displayLatitude) {
        this.displayLatitude = displayLatitude;
    }

    /**
     * @return the dashLatitude
     */
    public boolean isDashLatitude() {
        return dashLatitude;
    }

    /**
     * @param dashLatitude
     *            the dashLatitude to set
     */
    public void setDashLatitude(boolean dashLatitude) {
        this.dashLatitude = dashLatitude;
    }

    /**
     * @return the dashEffect
     */
    public PathEffect getDashEffect() {
        return dashEffect;
    }

    /**
     * @param dashEffect
     *            the dashEffect to set
     */
    public void setDashEffect(PathEffect dashEffect) {
        this.dashEffect = dashEffect;
    }
    
    /**
     * @return the longitudeWidth
     */
    public float getLongitudeWidth() {
        return longitudeWidth;
    }

    /**
     * @param longitudeWidth the longitudeWidth to set
     */
    public void setLongitudeWidth(float longitudeWidth) {
        this.longitudeWidth = longitudeWidth;
    }

    /**
     * @return the latitudeWidth
     */
    public float getLatitudeWidth() {
        return latitudeWidth;
    }

    /**
     * @param latitudeWidth the latitudeWidth to set
     */
    public void setLatitudeWidth(float latitudeWidth) {
        this.latitudeWidth = latitudeWidth;
    }

//    /**
//     * @return the longitudeFontColor
//     */
//    public int getLongitudeFontColor() {
//        return longitudeFontColor;
//    }
//
//    /**
//     * @param longitudeFontColor
//     *            the longitudeFontColor to set
//     */
//    public void setLongitudeFontColor(int longitudeFontColor) {
//        this.longitudeFontColor = longitudeFontColor;
//    }
//
//    /**
//     * @return the longitudeFontSize
//     */
//    public int getLongitudeFontSize() {
//        return longitudeFontSize;
//    }
//
//    /**
//     * @param longitudeFontSize
//     *            the longitudeFontSize to set
//     */
//    public void setLongitudeFontSize(int longitudeFontSize) {
//        this.longitudeFontSize = longitudeFontSize;
//    }

//    /**
//     * @return the latitudeFontColor
//     */
//    public int getLatitudeFontColor() {
//        return latitudeFontColor;
//    }
//
//    /**
//     * @param latitudeFontColor
//     *            the latitudeFontColor to set
//     */
//    public void setLatitudeFontColor(int latitudeFontColor) {
//        this.latitudeFontColor = latitudeFontColor;
//    }
//
//    /**
//     * @return the latitudeFontSize
//     */
//    public int getLatitudeFontSize() {
//        return latitudeFontSize;
//    }
//
//    /**
//     * @param latitudeFontSize
//     *            the latitudeFontSize to set
//     */
//    public void setLatitudeFontSize(int latitudeFontSize) {
//        this.latitudeFontSize = latitudeFontSize;
//    }
//    
    /**
     * @return the longitudeColor
     */
    public int getLongitudeColor() {
        return longitudeColor;
    }

    /**
     * @param longitudeColor
     *            the longitudeColor to set
     */
    public void setLongitudeColor(int longitudeColor) {
        this.longitudeColor = longitudeColor;
    }

    /**
     * @return the latitudeColor
     */
    public int getLatitudeColor() {
        return latitudeColor;
    }

    /**
     * @param latitudeColor
     *            the latitudeColor to set
     */
    public void setLatitudeColor(int latitudeColor) {
        this.latitudeColor = latitudeColor;
    }
    
    
//    /**
//     * @return the longitudeTitles
//     */
//    public List<String> getLongitudeTitles() {
//        return longitudeTitles;
//    }
//
//    /**
//     * @param longitudeTitles
//     *            the longitudeTitles to set
//     */
//    public void setLongitudeTitles(List<String> longitudeTitles) {
//        this.longitudeTitles = longitudeTitles;
//    }
//
//    /**
//     * @return the latitudeTitles
//     */
//    public List<String> getLatitudeTitles() {
//        return latitudeTitles;
//    }
//
//    /**
//     * @param latitudeTitles
//     *            the latitudeTitles to set
//     */
//    public void setLatitudeTitles(List<String> latitudeTitles) {
//        this.latitudeTitles = latitudeTitles;
//    }
}
