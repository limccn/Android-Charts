/*
 * ListChartData.java
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

package cn.limc.androidcharts.entity;

import java.util.ArrayList;
import java.util.List;

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
 * @version v1.0 2014/03/18 15:18:47
 * 
 */
public class ListChartData<T> implements IChartData<T> {
	private List<T> datas;

	public int size() {
		return datas.size();
	}

	public T get(int index) {
		return datas.get(index);
	}

	public boolean hasData() {
		return datas != null && !datas.isEmpty();
	}

	public boolean hasNoData() {
		return !hasData();
	}

	public void add(T data) {
		if (null == datas || datas.isEmpty()) {
			datas = new ArrayList<T>();
		}
		datas.add(data);
	}

	/**
	 * <p>
	 * Constructor of ListChartData
	 * </p>
	 * <p>
	 * IChartData类对象的构造函数
	 * </p>
	 * <p>
	 * IChartDataのコンストラクター
	 * </p>
	 * 
	 */
	public ListChartData() {
		datas = new ArrayList<T>();
	}

	public ListChartData(List<T> data) {
		datas = new ArrayList<T>();
		datas.addAll(data);
	}
}
