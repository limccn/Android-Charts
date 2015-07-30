/*
 * DateValueEntity.java
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

package cn.limc.androidcharts.series;

/**
 * <p>
 * en
 * </p>
 * <p>
 * jp
 * </p>
 * <p>
 * cn
 * </p>
 * 
 * @author limc
 * @version v1.0 2014/01/21 15:29:26
 * 
 */
public class DateValueEntity implements ChartDataRow,IMeasurable,IHasDate,IHasValue {    
	private Object date;
	private double value;

	/**
	 * <p>
	 * Constructor of DateValueEntity
	 * </p>
	 * <p>
	 * DateValueEntity类对象的构造函数
	 * </p>
	 * <p>
	 * DateValueEntityのコンストラクター
	 * </p>
	 * 
	 * @param date
	 * @param value
	 */
	public DateValueEntity(double value, Object date) {
		super();
		this.value = value;
		this.date = date;
	}

	/**
	 * @return the date
	 */
	public Object getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Object date) {
		this.date = (Integer)date;
	}

	/**
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(Object value) {
		this.value = (Double)value;
	}

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.series.IMeasurable#getHigh()
     */
    @Override
    public double getHigh() {
        return value;
    }

    /* (non-Javadoc)
     * @see cn.limc.androidcharts.series.IMeasurable#getLow()
     */
    @Override
    public double getLow() {
        return value;
    }

}
