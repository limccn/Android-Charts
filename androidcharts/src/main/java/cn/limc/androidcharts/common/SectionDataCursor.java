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

import cn.limc.androidcharts.entity.IChartData;

/**
 * <p>en</p>
 * <p>jp</p>
 * <p>cn</p>
 *
 * @author limc 
 * @version v1.0 2014/06/18 16:58:21 
 *  
 */
public class SectionDataCursor extends SimpleDataCursor {
	
	public static final int DISPLAY_FROM = 0;
	public static final int DISPLAY_NUMBER = 20;

	private int displayFrom = DISPLAY_FROM;
	private int displayNumber = DISPLAY_NUMBER;

	private byte[] synlock = new byte[]{};
	
	/** 
	 * <p>Constructor of SectionDataCursor</p>
	 * <p>SectionDataCursor类对象的构造函数</p>
	 * <p>SectionDataCursorのコンストラクター</p>
	 * 
	 */
	public SectionDataCursor() {

	}

	/* (non-Javadoc)
	 * 
	 * @return 
	 * @see cn.limc.androidcharts.view.IDataCursor#getDisplayFrom()
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

	public int getDataDisplayNumber(){return displayNumber > minDisplayNumber?displayNumber:minDisplayNumber;};

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
		synchronized (synlock) {
//			if (displayFrom > 0 && displayFrom < this.maxDisplayNumber - this.minDisplayNumber) {
//				if (displayFrom + this.displayNumber <= this.maxDisplayNumber) {
//					this.displayFrom = displayFrom;
//				}
//			}
			if (displayFrom >=0) {
//				if (displayFrom + this.displayNumber <= this.maxDisplayNumber) {
					this.displayFrom = displayFrom;
//				}
			}
		}
	}

	/* (non-Javadoc)
	 * 
	 * @param displayNumber 
	 * @see cn.limc.androidcharts.common.IDataCursor#setDisplayNumber(int) 
	 */
	public void setDisplayNumber(int displayNumber) {
		synchronized (synlock) {
//			if (displayNumber >= minDisplayNumber && displayNumber <= maxDisplayNumber) {
//				if (this.displayFrom + displayNumber <= this.maxDisplayNumber) {
//					this.displayNumber = displayNumber;
//				}
//			}
			if (displayNumber >=0) {
//				if (this.displayFrom + displayNumber <= this.maxDisplayNumber) {
					this.displayNumber = displayNumber;
//				}
			}
		}
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

	public boolean forward(int step){
		if (this.displayNumber < this.minDisplayNumber){
			return false;
		}
		if (step <= 2){
			return false;
		}
		if (this.displayFrom + this.displayNumber + step > this.maxDisplayNumber) {
			if(this.displayFrom == this.maxDisplayNumber - this.displayNumber){
				return false;
			}else{
				this.displayFrom = this.maxDisplayNumber - this.displayNumber;
				return true;
			}
		}else{
			this.displayFrom = this.displayFrom + step;
			return true;
		}
	}

	public boolean backward(int step){
		if (this.displayNumber < this.minDisplayNumber){
			return false;
		}
		if (step <= 2){
			return false;
		}
		if (this.displayFrom - step < 0) {
			this.displayFrom = 0;
			return true;
		}else{
			this.displayFrom = this.displayFrom - step;
			return true;
		}
	}

	public boolean stretch(int step){
		if (this.displayNumber < this.minDisplayNumber){
			return false;
		}
		if (step <= 2){
			return false;
		}
		if (this.displayNumber == this.minDisplayNumber){
			return false;
		}else {
			int resultDisplayNumber = this.displayNumber - step;
			int resultDisplayFrom = this.displayFrom + step / 2;

			if (resultDisplayNumber <= minDisplayNumber) {
				this.displayNumber = minDisplayNumber;
			} else {
				this.displayNumber = resultDisplayNumber;
			}

			if (resultDisplayFrom >= maxDisplayNumber - minDisplayNumber){
				this.displayFrom = maxDisplayNumber - minDisplayNumber;
			}else{
				this.displayFrom = resultDisplayFrom;
			}

			return true;
		}
	}

	public boolean shrink(int step){
		if (this.displayNumber < this.minDisplayNumber){
			return false;
		}
		if (step <= 2){
			return false;
		}
		if (this.displayFrom ==0 && this.displayNumber == this.maxDisplayNumber){
			return false;
		}else {
			int resultDisplayNumber = this.displayNumber + step;
			int resultDisplayFrom = this.displayFrom - step / 2;

			if (resultDisplayFrom <= 0) {
				this.displayFrom = 0;
				if (resultDisplayNumber >= maxDisplayNumber) {
					this.displayNumber = maxDisplayNumber;
				} else {
					this.displayNumber = resultDisplayNumber;
				}
			} else {
				this.displayFrom = resultDisplayFrom;
				if (resultDisplayNumber >= maxDisplayNumber) {
					this.displayNumber = maxDisplayNumber;
					this.displayFrom = 0;
				} else{
					if(resultDisplayFrom + resultDisplayNumber >= maxDisplayNumber){
						this.displayNumber = resultDisplayNumber;
						this.displayFrom = maxDisplayNumber - resultDisplayNumber;
					} else{
						this.displayNumber = resultDisplayNumber;
					}
				}
			}
			return true;
		}
	}
}
