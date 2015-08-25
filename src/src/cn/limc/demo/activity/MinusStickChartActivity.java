/*
 * MinusStickChartActivity.java
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

import java.util.ArrayList;
import java.util.List;

import cn.limc.androidcharts.R;
import cn.limc.androidcharts.diagram.GridChart;
import cn.limc.androidcharts.series.ChartDataRow;
import cn.limc.androidcharts.series.ChartDataSet;
import cn.limc.androidcharts.series.ChartDataTable;
import cn.limc.androidcharts.series.StickEntity;
import cn.limc.demo.common.BaseActivity;
import cn.limc.demo.controller.StickChartController;
import android.os.Bundle;
import android.view.Menu;

public class MinusStickChartActivity extends BaseActivity {    
    GridChart minusstickchart;
    StickChartController stickChartController;
    ChartDataSet stickData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slip_minus_stick_chart);

        initData();
        initStickChart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.slip_minus_stick_chart, menu);
        return true;
    }

    private void initData() {

        List<ChartDataRow> data = new ArrayList<ChartDataRow>();
        data.add(new StickEntity(50000, 0, 20110603));
        data.add(new StickEntity(42000, 0, 20110703));
        data.add(new StickEntity(32000, 0, 20110803));
        data.add(new StickEntity(21000, 0, 20110903));
        data.add(new StickEntity(0, -12000, 20111003));
        data.add(new StickEntity(0, -28000, 20111103));
        data.add(new StickEntity(0, -41000, 20111203));
        data.add(new StickEntity(0, -25000, 20120103));
        data.add(new StickEntity(0, -18000, 20120203));
        data.add(new StickEntity(14000, 0, 20120303));
        data.add(new StickEntity(24000, 0, 20120403));
        data.add(new StickEntity(36000, 0, 20120503));
        data.add(new StickEntity(46000, 0, 20120603));
        data.add(new StickEntity(50000, 0, 20110703));
        data.add(new StickEntity(42000, 0, 20110803));
        data.add(new StickEntity(32000, 0, 20110903));
        data.add(new StickEntity(21000, 0, 20111003));
        data.add(new StickEntity(0, -12000, 20111003));
        data.add(new StickEntity(0, -28000, 20111103));
        data.add(new StickEntity(0, -41000, 20111203));
        data.add(new StickEntity(0, -25000, 20120103));
        data.add(new StickEntity(0, -18000, 20120203));
        data.add(new StickEntity(14000, 0, 20120303));
        data.add(new StickEntity(24000, 0, 20120303));
        data.add(new StickEntity(36000, 0, 20120303));
        data.add(new StickEntity(46000, 0, 20120303));
        data.add(new StickEntity(50000, 0, 20110603));
        data.add(new StickEntity(42000, 0, 20110703));
        data.add(new StickEntity(32000, 0, 20110803));
        data.add(new StickEntity(21000, 0, 20110903));
        data.add(new StickEntity(0, -12000, 20111003));
        data.add(new StickEntity(0, -28000, 20111103));
        data.add(new StickEntity(0, -41000, 20111203));
        data.add(new StickEntity(0, -25000, 20120103));
        data.add(new StickEntity(0, -18000, 20120203));
        data.add(new StickEntity(14000, 0, 20120303));
        data.add(new StickEntity(24000, 0, 20120303));
        data.add(new StickEntity(36000, 0, 20120303));
        data.add(new StickEntity(46000, 0, 20120303));
        data.add(new StickEntity(0, -12000, 20111003));
        data.add(new StickEntity(0, -28000, 20111103));
        data.add(new StickEntity(0, -41000, 20111203));
        data.add(new StickEntity(0, -25000, 20120103));
        data.add(new StickEntity(0, -18000, 20120203));
        data.add(new StickEntity(14000, 0, 20120303));
        data.add(new StickEntity(24000, 0, 20120303));
        data.add(new StickEntity(36000, 0, 20120303));
        data.add(new StickEntity(46000, 0, 20120303));
        data.add(new StickEntity(0, -12000, 20111003));
        data.add(new StickEntity(0, -28000, 20111103));
        data.add(new StickEntity(0, -41000, 20111203));
        data.add(new StickEntity(0, -25000, 20120103));
        data.add(new StickEntity(0, -18000, 20120203));
        data.add(new StickEntity(14000, 0, 20120303));
        data.add(new StickEntity(24000, 0, 20120303));
        data.add(new StickEntity(36000, 0, 20120303));
        data.add(new StickEntity(46000, 0, 20120303));

        stickData = new ChartDataSet(new ChartDataTable(data));
    }
    
    private void initStickChart() {
        this.minusstickchart = (GridChart) findViewById(R.id.slipminusstickchart);
        stickChartController = new StickChartController();
        stickChartController.setStickData(stickData);
        stickChartController.applyController(minusstickchart);
    }

}
