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
	
	public static final int DISPLAY_FROM = 0;
	public static final int DISPLAY_NUMBER = 20;	

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
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * 
	 * @return 
	 * @see cn.limc.androidcharts.view.IDataCursor#displayFrom() 
	 */
	public int getDisplayFrom() {
		return displayFrom;
	}

	/* (non-Javadoc)
	 * 
	 * @return 
	 * @see cn.limc.androidcharts.view.IDataCursor#displayNumber() 
	 */
	public int getDisplayNumber() {
		return displayNumber;
	}

	/* (non-Javadoc)
	 * 
	 * @return 
	 * @see cn.limc.androidcharts.view.IDataCursor#displayTo() 
	 */
	public int getDisplayTo() {
		return displayFrom + displayNumber;
	}

	/* (non-Javadoc)
	 * 
	 * @param displayFrom 
	 * @see cn.limc.androidcharts.common.IDataCursor#setDisplayFrom(int) 
	 */
	public void setDisplayFrom(int displayFrom) {
		// TODO Auto-generated method stub
		this.displayFrom = displayFrom;
	}

	/* (non-Javadoc)
	 * 
	 * @param displayNumber 
	 * @see cn.limc.androidcharts.common.IDataCursor#setDisplayNumber(int) 
	 */
	public void setDisplayNumber(int displayNumber) {
		// TODO Auto-generated method stub
		this.displayNumber = displayNumber;
	}

//	/* (non-Javadoc)
//	 * 
//	 * @param displayTo 
//	 * @see cn.limc.androidcharts.common.IDataCursor#setDisplayTo(int) 
//	 */
//	public void setDisplayTo(int displayTo) {
//		// TODO Auto-generated method stub
//		
//	}

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

}
