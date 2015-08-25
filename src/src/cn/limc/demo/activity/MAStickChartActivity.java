/*
 * MAStickChartActivity.java
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
import cn.limc.demo.controller.MAStickChartController;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Menu;

public class MAStickChartActivity extends BaseActivity {

    GridChart maslipstickchart;
    
    MAStickChartController stickChartController;
    ChartDataSet stickData;
    ChartDataSet lineData;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maslip_stick_chart);
        initDatas();
        initMAStickChart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.maslip_stick_chart, menu);
        return true;
    }
    
    private void initDatas(){
        

        // 以下计算VOL
        lineData = new ChartDataSet();

        // 计算5日均线
        LineEntity vma5 = new LineEntity();
        vma5.setTitle("MA5");
        vma5.setLineColor(Color.WHITE);
        vma5.setTableData(initVMA(5));
        lineData.add(vma5);

        // 计算10日均线
        LineEntity vma10 = new LineEntity();
        vma10.setTitle("MA10");
        vma10.setLineColor(Color.CYAN);
        vma10.setTableData(initVMA(10));
        lineData.add(vma10);

        
        // 计算25日均线
        LineEntity vma25 = new LineEntity();
        vma25.setTitle("MA25");
        vma25.setLineColor(Color.BLUE);
        vma25.setTableData(initVMA(25));
        lineData.add(vma25);
        
        stickData = new ChartDataSet(new ChartDataTable(vol));
    }

    private void initMAStickChart() {
        this.maslipstickchart = (GridChart) findViewById(R.id.maslipstickchart);
        stickChartController = new MAStickChartController();
        stickChartController.setStickData(stickData);
        stickChartController.setLineData(lineData);
        stickChartController.applyController(maslipstickchart);
    }
}
