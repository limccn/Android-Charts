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

import java.util.ArrayList;
import java.util.List;

import cn.limc.androidcharts.R;
import cn.limc.androidcharts.component.Axis;
import cn.limc.androidcharts.diagram.MAStickChart;
import cn.limc.androidcharts.series.ChartDataSet;
import cn.limc.androidcharts.series.ChartDataTable;
import cn.limc.androidcharts.series.DateValueEntity;
import cn.limc.androidcharts.series.LineEntity;
import cn.limc.demo.common.BaseActivity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Menu;

public class MAStickChartActivity extends BaseActivity {

    MAStickChart mastickchart;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mastick_chart);
        
        initMAStickChart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mastick_chart, menu);
        return true;
    }
    
    private void initMAStickChart() {
        this.mastickchart = (MAStickChart) findViewById(R.id.mastickchart);

        // 以下计算VOL
        ChartDataSet vlines = new ChartDataSet();

        // 计算5日均线
        LineEntity vma5 = new LineEntity();
        vma5.setTitle("MA5");
        vma5.setLineColor(Color.WHITE);
        vma5.setTableData(initVMA(5));
        vlines.add(vma5);

        // 计算10日均线
        LineEntity vma10 = new LineEntity();
        vma10.setTitle("MA10");
        vma10.setLineColor(Color.CYAN);
        vma10.setTableData(initVMA(10));
        vlines.add(vma10);

        
        // 计算25日均线
        LineEntity vma25 = new LineEntity();
        vma25.setTitle("MA25");
        vma25.setLineColor(Color.BLUE);
        vma25.setTableData(initVMA(25));
        vlines.add(vma25);

        mastickchart.setAxisXColor(Color.LTGRAY);
        mastickchart.setAxisYColor(Color.LTGRAY);
        mastickchart.setLatitudeColor(Color.GRAY);
        mastickchart.setLongitudeColor(Color.GRAY);
        mastickchart.setBorderColor(Color.LTGRAY);
        mastickchart.setLongitudeFontColor(Color.WHITE);
        mastickchart.setLatitudeFontColor(Color.WHITE);

        // 最大显示足数
        //mastickchart.setMaxSticksNum(52);
        mastickchart.setDisplayNumber(52);
        // 最大纬线数
        mastickchart.setLatitudeNum(2);
        // 最大经线数
        mastickchart.setLongitudeNum(3);
        // 最大价格
        mastickchart.setMaxValue(10000);
        // 最小价格
        mastickchart.setMinValue(100);

        mastickchart.setDisplayLongitudeTitle(true);
        mastickchart.setDisplayLatitudeTitle(true);
        mastickchart.setDisplayLatitude(true);
        mastickchart.setDisplayLongitude(true);
        mastickchart.setBackgroundColor(Color.BLACK);

        mastickchart.setDataQuadrantPaddingTop(5);
        mastickchart.setDataQuadrantPaddingBottom(5);
        mastickchart.setDataQuadrantPaddingLeft(5);
        mastickchart.setDataQuadrantPaddingRight(5);
//      mastickchart.setAxisYTitleQuadrantWidth(50);
//      mastickchart.setAxisXTitleQuadrantHeight(20);
        mastickchart.setAxisXPosition(Axis.AXIS_X_POSITION_BOTTOM);
        mastickchart.setAxisYPosition(Axis.AXIS_Y_POSITION_RIGHT);

        // 为chart1增加均线
        mastickchart.setLinesData(vlines);
        // 为chart1增加均线
        mastickchart.setChartData(new ChartDataSet(new ChartDataTable(vol)));

    }

}
