/*
 * SimpleDataCursor.java
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

import cn.limc.androidcharts.component.IAxis;
import cn.limc.androidcharts.diagram.GridChart;
import cn.limc.androidcharts.event.Zoomable;

/** 
 * <p>en</p>
 * <p>jp</p>
 * <p>cn</p>
 *
 * @author limc 
 * @version v1.0 2014/06/18 16:00:16 
 *  
 */
public class SimpleDataCursor implements IDataCursor,Zoomable {
    public static int MAX_DISPLAY_NUM = 30;
    
    protected GridChart inChart;

	private int maxDisplayNum = MAX_DISPLAY_NUM; 
	private int minDisplayNumber = MINI_DISPLAY_NUM;
	
	/** 
	 * <p>Constructor of SimpleDataCursor</p>
	 * <p>SimpleDataCursor类对象的构造函数</p>
	 * <p>SimpleDataCursorのコンストラクター</p>
	 * 
	 */
	public SimpleDataCursor(GridChart inChart) {
	    this.inChart = inChart;
	}

	/* (non-Javadoc)
	 * 
	 * @return 
	 * @see cn.limc.androidcharts.diagram.IDataCursor#displayFrom() 
	 */
	public int getDisplayFrom() {
	     if (inChart.getAxisY().getPosition() == IAxis.AXIS_Y_POSITION_LEFT) {
	            return 0;
	     }else{
	            return inChart.getChartData().getChartTable().size() - maxDisplayNum;
	     }
	}

	/* (non-Javadoc)
	 * 
	 * @return 
	 * @see cn.limc.androidcharts.diagram.IDataCursor#displayNumber() 
	 */
	public int getDisplayNumber() {
		return maxDisplayNum;
	}

	/* (non-Javadoc)
	 * 
	 * @return 
	 * @see cn.limc.androidcharts.diagram.IDataCursor#displayTo() 
	 */
	public int getDisplayTo() {
	    if (inChart.getAxisY().getPosition() == IAxis.AXIS_Y_POSITION_LEFT) {
            return maxDisplayNum;
        }else{
            return inChart.getChartData().getChartTable().size() - 1;
        }
	}

	/* (non-Javadoc)
	 * 
	 * @param displayFrom 
	 * @see cn.limc.androidcharts.common.IDataCursor#setDisplayFrom(int) 
	 */
	@Deprecated
	public void setDisplayFrom(int displayFrom) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * 
	 * @param displayNumber 
	 * @see cn.limc.androidcharts.common.IDataCursor#setDisplayNumber(int) 
	 */
	public void setDisplayNumber(int displayNumber) {
		// TODO Auto-generated method stub
		this.maxDisplayNum = displayNumber;
	}

	/* (non-Javadoc)
	 * 
	 * @return 
	 * @see cn.limc.androidcharts.common.IDataCursor#getMinDisplayNumber() 
	 */
	public int getMinDisplayNumber() {
		return minDisplayNumber;
	}

	/* (non-Javadoc)
	 * 
	 * @param minDisplayNumber 
	 * @see cn.limc.androidcharts.common.IDataCursor#setMinDisplayNumber(int) 
	 */
	public void setMinDisplayNumber(int minDisplayNumber) {
		this.minDisplayNumber = minDisplayNumber;
	}

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.event.Zoomable#zoomIn()
     */
    @Override
    public void zoomIn() {
        // TODO Auto-generated method stub
        if (getDisplayNumber() > getMinDisplayNumber()) {
            setDisplayNumber(getDisplayNumber() - ZOOM_STEP);
        }
    }

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.event.Zoomable#zoomOut()
     */
    @Override
    public void zoomOut() {
        // TODO Auto-generated method stub
        if (getDisplayNumber() < inChart.getChartData().getChartTable().size() - 1 - ZOOM_STEP) {
            setDisplayNumber(getDisplayNumber() + ZOOM_STEP);
        }
    }

}
