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


package cn.limc.androidcharts.common;

import cn.limc.androidcharts.view.DataGridChart;

/** 
 * <p>en</p>
 * <p>jp</p>
 * <p>cn</p>
 *
 * @author limc 
 * @version v1.0 2014/06/18 16:58:21 
 *  
 */
public class SectionDataCursor implements IDataCursor {
	
    protected DataGridChart inChart;
    
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
	public SectionDataCursor(DataGridChart inChart) {
	    this.inChart = inChart;
	}

    /*
     * (non-Javadoc)
     * 
     * @return 
     * @see cn.limc.androidcharts.view.StickChart#getDisplayFrom()
     */
    @Override
    public int getDisplayFrom() {
        return displayFrom;
    }


    /*
     * (non-Javadoc)
     * 
     * @param displayFrom 
     * @see cn.limc.androidcharts.view.StickChart#setDisplayFrom(int)
     */
    @Override
    public void setDisplayFrom(int displayFrom) {
        this.displayFrom = displayFrom;
    }


    /*
     * (non-Javadoc)
     * 
     * @return 
     * @see cn.limc.androidcharts.view.StickChart#getDisplayTo()
     */
    @Override
    public int getDisplayTo() {
        return displayFrom + displayNumber;
    }

    /*
     * (non-Javadoc)
     * 
     * @return 
     * @see cn.limc.androidcharts.view.StickChart#getDisplayNumber()
     */
    @Override
    public int getDisplayNumber() {
        return displayNumber;
    }

    /*
     * (non-Javadoc)
     * 
     * @param displayNumber 
     * @see cn.limc.androidcharts.view.StickChart#setDisplayNumber(int)
     */
    @Override
    public void setDisplayNumber(int displayNumber) {
        this.displayNumber = displayNumber;
    }

    /*
     * (non-Javadoc)
     * 
     * @return 
     * @see cn.limc.androidcharts.view.StickChart#getMinDisplayNumber()
     */
    @Override
    public int getMinDisplayNumber() {
        return minDisplayNumber;
    }

    /*
     * (non-Javadoc)
     * 
     * @param minDisplayNumber 
     * @see cn.limc.androidcharts.view.StickChart#setMinDisplayNumber(int)
     */
    @Override
    public void setMinDisplayNumber(int minDisplayNumber) {
        this.minDisplayNumber = minDisplayNumber;
    }


}
