/*
 * MACandleStickChartActivity.java
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
import cn.limc.androidcharts.series.LineEntity;
import cn.limc.demo.common.BaseActivity;
import cn.limc.demo.controller.MACandleStickChartController;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Menu;

public class MACandleStickChartActivity extends BaseActivity {

    GridChart macandlestickchart;
    MACandleStickChartController stickChartController;
    ChartDataSet stickData;
    ChartDataSet lineData;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maslip_candle_stick_chart);
        initData();
        initMACandleStickChart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.maslip_candle_stick_chart, menu);
        return true;
    }
    
    private void initData() {
        lineData = new ChartDataSet();

        // 计算5日均线
        LineEntity ma5 = new LineEntity();
        ma5.setTitle("MA5");
        ma5.setLineColor(Color.WHITE);
        ma5.setTableData(initMA(5));
        lineData.add(ma5);

        // 计算10日均线
        LineEntity ma10 = new LineEntity();
        ma10.setTitle("MA10");
        ma10.setLineColor(Color.CYAN);
        ma10.setTableData(initMA(10));
        lineData.add(ma10);

        // 计算25日均线
        LineEntity ma25 = new LineEntity();
        ma25.setTitle("MA25");
        ma25.setLineColor(Color.BLUE);
        ma25.setTableData(initMA(25));
        lineData.add(ma25);
        
        stickData = new ChartDataSet(new ChartDataTable(ohlc));
        
    }
    
    private void initMACandleStickChart() {
        this.macandlestickchart = (GridChart) findViewById(R.id.maslipcandlestickchart);
        stickChartController = new MACandleStickChartController();
        stickChartController.setStickData(stickData);
        stickChartController.setLineData(lineData);
        stickChartController.applyController(macandlestickchart);
    }

}
