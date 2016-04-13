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


package cn.limc.androidcharts.common;

/** 
 * <p>en</p>
 * <p>jp</p>
 * <p>cn</p>
 *
 * @author limc 
 * @version v1.0 2014/06/18 16:00:16 
 *  
 */
public class SimpleDataCursor implements IDataCursor {

	protected int minDisplayNumber = MIN_DISPLAY_NUM;
	protected int maxDisplayNumber = MAX_DISPLAY_NUM;

	private byte[] synlock = new byte[]{};
	
	/** 
	 * <p>Constructor of SimpleDataCursor</p>
	 * <p>SimpleDataCursor类对象的构造函数</p>
	 * <p>SimpleDataCursorのコンストラクター</p>
	 * 
	 */
	public SimpleDataCursor() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * 
	 * @return 
	 * @see cn.limc.androidcharts.view.IDataCursor#getDisplayFrom()
	 */
	public int getDisplayFrom() {
		return 0;
	}

	/* (non-Javadoc)
	 * 
	 * @return 
	 * @see cn.limc.androidcharts.view.IDataCursor#displayNumber() 
	 */
	public int getDisplayNumber() {
		return maxDisplayNumber;
	}

	public int getDataDisplayNumber() {
		return maxDisplayNumber;
	}

	/* (non-Javadoc)
	 * 
	 * @return 
	 * @see cn.limc.androidcharts.view.IDataCursor#displayTo() 
	 */
	public int getDisplayTo() {
		return maxDisplayNumber;
	}

	/* (non-Javadoc)
	 * 
	 * @param displayFrom 
	 * @see cn.limc.androidcharts.common.IDataCursor#setDisplayFrom(int) 
	 */
	public void setDisplayFrom(int displayFrom) {
	}

	/* (non-Javadoc)
	 * 
	 * @param displayNumber 
	 * @see cn.limc.androidcharts.common.IDataCursor#setDisplayNumber(int) 
	 */
	public void setDisplayNumber(int displayNumber) {
	}

	/* (non-Javadoc)
	 * 
	 * @param displayTo 
	 * @see cn.limc.androidcharts.common.IDataCursor#setDisplayTo(int) 
	 */
	public void setDisplayTo(int displayTo) {
		// TODO Auto-generated method stub
		
	}

	public boolean forward(int step){
		return  true;
	}
	public boolean backward(int step){
		return  true;
	}
	public boolean stretch(int step){
		return  true;
	}
	public boolean shrink(int step){
		return  true;
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
		synchronized (synlock){
			if (minDisplayNumber >=0 && minDisplayNumber <= this.maxDisplayNumber){
				this.minDisplayNumber = minDisplayNumber;
			}
		}

	}

	/* (non-Javadoc)
	 *
	 * @return
	 * @see cn.limc.androidcharts.common.IDataCursor#getMaxDisplayNumber()
	 */
	public int getMaxDisplayNumber() {
		return maxDisplayNumber;
	}

	/* (non-Javadoc)
	 *
	 * @param minDisplayNumber
	 * @see cn.limc.androidcharts.common.IDataCursor#setMaxDisplayNumber(int)
	 */
	public void setMaxDisplayNumber(int maxDisplayNumber) {
		synchronized (synlock) {
			if (maxDisplayNumber >= 0  && maxDisplayNumber >= this.minDisplayNumber) {
				this.maxDisplayNumber = maxDisplayNumber;
			}
		}
	}
}
