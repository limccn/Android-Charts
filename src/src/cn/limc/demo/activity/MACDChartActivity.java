/*
 * MACDChartActivity.java
 * Android-Charts Demo
 *
 * Created by limc on 2014/04/29.
 *
 * Copyright 2014 limc.cn All rights reserved.
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

package cn.limc.demo.activity;

import cn.limc.androidcharts.R;
import cn.limc.androidcharts.diagram.GridChart;
import cn.limc.androidcharts.series.ChartDataSet;
import cn.limc.androidcharts.series.ChartDataTable;
import cn.limc.demo.common.BaseActivity;
import cn.limc.demo.controller.MACDChartController;
import android.os.Bundle;
import android.view.Menu;

public class MACDChartActivity extends BaseActivity {

    GridChart macdChart;
    MACDChartController stickChartController;
    ChartDataSet stickData;
    ChartDataSet lineData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_macdchart);
        initData();
        initMACDChart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.macdchart, menu);
        return true;
    }
    
    private void initData(){
        stickData = new ChartDataSet(new ChartDataTable(macd));
    }

    private void initMACDChart() {
        this.macdChart = (GridChart) findViewById(R.id.macdchart);
        stickChartController = new MACDChartController();
        stickChartController.setMacdData(stickData);
        stickChartController.applyController(macdChart);
    }
}
