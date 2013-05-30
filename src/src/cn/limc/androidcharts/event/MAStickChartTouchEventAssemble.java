/*
 * MAStickChartTouchEventAssemble.java
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

package cn.limc.androidcharts.event;

import java.util.List;

import cn.limc.androidcharts.entity.LineEntity;
import cn.limc.androidcharts.entity.StickEntity;
import cn.limc.androidcharts.view.GridChart;
import cn.limc.androidcharts.view.MAStickChart;

import android.widget.TextView;


public class MAStickChartTouchEventAssemble implements ITouchEventResponse {

	private TextView tvMA5;
	private TextView tvMA10;
	private TextView tvMA25;
	private TextView tvV;
	
	
	public void notifyEvent(GridChart chart ,int index) {
		MAStickChart machart = (MAStickChart)chart;
		
		List<StickEntity> stickData = machart.getStickData();
		List<LineEntity> lineData = machart.getLineData();
		
		if(null==stickData||null ==lineData){
			return;
		}
		
		LineEntity ma5 = lineData.get(0);
		LineEntity ma10 = lineData.get(1);
		LineEntity ma25 = lineData.get(2);
		
		if(index >= stickData.size()||index <0){
			return;
			//index = stickData.size() -1;
		}
		
		StickEntity e = stickData.get(index);
		if(null != tvV){
			tvV.setText(String.valueOf((int)(e.getHigh())));
		}
		
		tvMA5.setText(ma5.getTitle() + "=" +String.valueOf((int)ma5.getLineData().get(index).floatValue()));
		tvMA5.setTextColor(ma5.getLineColor());
		tvMA10.setText(ma10.getTitle() + "=" +String.valueOf((int)ma10.getLineData().get(index).floatValue()));
		tvMA10.setTextColor(ma10.getLineColor());
		tvMA25.setText(ma25.getTitle() + "=" +String.valueOf((int)ma25.getLineData().get(index).floatValue()));
		tvMA25.setTextColor(ma25.getLineColor());
	}

	
	public void notifyEvent(GridChart chart) {
		
		MAStickChart machart = (MAStickChart)chart;
		
		int index = machart.getSelectedIndex();
		
		notifyEvent(chart,index);
	}


	public TextView getTvMA5() {
		return tvMA5;
	}


	public void setTvMA5(TextView tvMA5) {
		this.tvMA5 = tvMA5;
	}


	public TextView getTvMA10() {
		return tvMA10;
	}


	public void setTvMA10(TextView tvMA10) {
		this.tvMA10 = tvMA10;
	}


	public TextView getTvMA25() {
		return tvMA25;
	}


	public void setTvMA25(TextView tvMA25) {
		this.tvMA25 = tvMA25;
	}


	public TextView getTvV() {
		return tvV;
	}


	public void setTvV(TextView tvV) {
		this.tvV = tvV;
	}
}
