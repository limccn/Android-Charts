/*
 * SectionDataCursor.java
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


package cn.limc.androidcharts.model;

import cn.limc.androidcharts.event.Slipable;

/** 
 * <p>en</p>
 * <p>jp</p>
 * <p>cn</p>
 *
 * @author limc 
 * @version v1.0 2014/06/18 16:58:21 
 *  
 */
public class SectionDataCursor extends AbstractDataCursor implements DataCursor,Slipable{
    
    public static final int DEFAULT_ZOOM_BASE_LINE = ZOOM_BASE_LINE_CENTER;

    protected int zoomBaseLine = DEFAULT_ZOOM_BASE_LINE;
    
	public static final int DISPLAY_FROM = 0;
	public static final int DISPLAY_NUMBER = 50;	

	private int displayFrom = DISPLAY_FROM;
	private int displayNumber = DISPLAY_NUMBER;
	private int minDisplayNumber = MINI_DISPLAY_NUM;
	
	/** 
	 * <p>Constructor of SectionDataCursor</p>
	 * <p>SectionDataCursor类对象的构造函数</p>
	 * <p>SectionDataCursorのコンストラクター</p>
	 * 
	 */
	public SectionDataCursor() {
	}

    /*
     * (non-Javadoc)
     * 
     * @return 
     * @see cn.limc.androidcharts.diagram.StickChart#getDisplayFrom()
     */
    @Override
    public int getDisplayFrom() {
        return displayFrom;
    }


    /*
     * (non-Javadoc)
     * 
     * @param displayFrom 
     * @see cn.limc.androidcharts.diagram.StickChart#setDisplayFrom(int)
     */
    @Override
    public void setDisplayFrom(int displayFrom) {
        this.displayFrom = displayFrom;
    }


    /*
     * (non-Javadoc)
     * 
     * @return 
     * @see cn.limc.androidcharts.diagram.StickChart#getDisplayTo()
     */
    @Override
    public int getDisplayTo() {
        return displayFrom + displayNumber;
    }

    /*
     * (non-Javadoc)
     * 
     * @return 
     * @see cn.limc.androidcharts.diagram.StickChart#getDisplayNumber()
     */
    @Override
    public int getDisplayNumber() {
        return displayNumber;
    }

    /*
     * (non-Javadoc)
     * 
     * @param displayNumber 
     * @see cn.limc.androidcharts.diagram.StickChart#setDisplayNumber(int)
     */
    @Override
    public void setDisplayNumber(int displayNumber) {
        this.displayNumber = displayNumber;
    }

    /*
     * (non-Javadoc)
     * 
     * @return 
     * @see cn.limc.androidcharts.diagram.StickChart#getMinDisplayNumber()
     */
    @Override
    public int getMinDisplayNumber() {
        return minDisplayNumber;
    }

    /*
     * (non-Javadoc)
     * 
     * @param minDisplayNumber 
     * @see cn.limc.androidcharts.diagram.StickChart#setMinDisplayNumber(int)
     */
    @Override
    public void setMinDisplayNumber(int minDisplayNumber) {
        this.minDisplayNumber = minDisplayNumber;
    }

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.event.Zoomable#zoomIn()
     */
    @Override
    public void zoomIn() {
        if (getDisplayNumber() > getMinDisplayNumber()) {
            // 区分缩放方向
            if (zoomBaseLine == ZOOM_BASE_LINE_CENTER) {
                setDisplayNumber(getDisplayNumber() - ZOOM_STEP);
                setDisplayFrom(getDisplayFrom() + ZOOM_STEP / 2);
            } else if (zoomBaseLine == ZOOM_BASE_LINE_LEFT) {
                setDisplayNumber(getDisplayNumber() - ZOOM_STEP);
            } else if (zoomBaseLine == ZOOM_BASE_LINE_RIGHT) {
                setDisplayNumber(getDisplayNumber() - ZOOM_STEP);
                setDisplayFrom(getDisplayFrom() + ZOOM_STEP);
            }

            // 处理displayNumber越界
            if (getDisplayNumber() < getMinDisplayNumber()) {
                setDisplayNumber(getMinDisplayNumber());
            }

            int dataSize = mData.getChartTable().size();
            // 处理displayFrom越界
            if (getDisplayTo() >= dataSize) {
                setDisplayFrom(dataSize - getDisplayNumber());
            }
        }
    }

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.event.Zoomable#zoomOut()
     */
    @Override
    public void zoomOut() {
        int dataSize = mData.getChartTable().size();
        if (getDisplayNumber() < dataSize - 1) {
            if (getDisplayNumber() + ZOOM_STEP > dataSize - 1) {
                setDisplayNumber(dataSize - 1);
                setDisplayFrom(0);
            } else {
                // 区分缩放方向
                if (zoomBaseLine == ZOOM_BASE_LINE_CENTER) {
                    setDisplayNumber(getDisplayNumber() + ZOOM_STEP);
                    if (getDisplayFrom() > 1) {
                        setDisplayFrom(getDisplayFrom() - ZOOM_STEP / 2);
                    } else {
                        setDisplayFrom(0);
                    }
                } else if (zoomBaseLine == ZOOM_BASE_LINE_LEFT) {
                    setDisplayNumber(getDisplayNumber() + ZOOM_STEP);
                } else if (zoomBaseLine == ZOOM_BASE_LINE_RIGHT) {
                    setDisplayNumber(getDisplayNumber() + ZOOM_STEP);
                    if (getDisplayFrom() > ZOOM_STEP) {
                        setDisplayFrom(getDisplayFrom() - ZOOM_STEP);
                    } else {
                        setDisplayFrom(0);
                    }
                }
            }
            if (getDisplayTo() >= dataSize) {
                setDisplayNumber(dataSize - getDisplayFrom());
            }
        }
    }

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.event.Slipable#moveLeft()
     */
    @Override
    public void moveLeft() {
        int dataSize = mData.getChartTable().size();

        if (getDisplayFrom() <= SLIP_STEP) {
            setDisplayFrom(0);
        } else if (getDisplayFrom() > SLIP_STEP) {
            setDisplayFrom(getDisplayFrom() - SLIP_STEP);
        } else {

        }

        // 处理displayFrom越界
        if (getDisplayTo() >= dataSize) {
            setDisplayFrom(dataSize - getDisplayNumber());
        }
    }

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.event.Slipable#moveRight()
     */
    @Override
    public void moveRight() {
        int dataSize = mData.getChartTable().size();
        if (getDisplayTo() < dataSize - SLIP_STEP) {
            setDisplayFrom(getDisplayFrom() + SLIP_STEP);
        } else {
            setDisplayFrom(dataSize - getDisplayNumber());
        }

        // 处理displayFrom越界
        if (getDisplayTo() >= dataSize) {
            setDisplayFrom(dataSize - getDisplayNumber());
        }
    }
    
    
    /**
     * @return the zoomBaseLine
     */
    public int getZoomBaseLine() {
        return zoomBaseLine;
    }

    /**
     * @param zoomBaseLine
     *            the zoomBaseLine to set
     */
    public void setZoomBaseLine(int zoomBaseLine) {
        this.zoomBaseLine = zoomBaseLine;
    }
   
}
