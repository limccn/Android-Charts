/*
 * AreaChartActivity.java
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
import cn.limc.androidcharts.series.LineEntity;
import cn.limc.demo.common.BaseActivity;
import cn.limc.demo.controller.AreaChartController;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Menu;

public class AreaChartActivity extends BaseActivity {

    GridChart arechart;
    
    AreaChartController areChartController;
    ChartDataSet lineData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slip_area_chart);
        initData();
        initAreaChart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.slip_area_chart, menu);
        return true;
    }
    
    private void initData(){
        
        lineData = new ChartDataSet();

        // 计算5日均线
        LineEntity ma5 = new LineEntity();
        ma5.setTitle("HIGH");
        ma5.setLineColor(Color.WHITE);
        ma5.setTableData(dv1);
        lineData.add(ma5);

        // 计算10日均线
        LineEntity ma10 = new LineEntity();
        ma10.setTitle("LOW");
        ma10.setLineColor(Color.RED);
        ma10.setTableData(dv2);
        lineData.add(ma10);
    }
    
    private void initAreaChart(){
        this.arechart = (GridChart) findViewById(R.id.slipareachart);
        areChartController = new AreaChartController();
        areChartController.setLineData(lineData);
        areChartController.applyController(arechart);
    }
}
