/*
 * LineChartActivity.java
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
import cn.limc.androidcharts.axis.Axis;
import cn.limc.androidcharts.entity.DateValueEntity;
import cn.limc.androidcharts.entity.LineEntity;
import cn.limc.androidcharts.view.LineChart;
import cn.limc.demo.common.BaseActivity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Menu;

public class LineChartActivity extends BaseActivity {

    LineChart linechart;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_chart);
        initLineChart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.line_chart, menu);
        return true;
    }

    private void initLineChart() {
        this.linechart = (LineChart) findViewById(R.id.linechart);
        List<LineEntity<DateValueEntity>> lines = new ArrayList<LineEntity<DateValueEntity>>();

        // 计算5日均线
        LineEntity<DateValueEntity> MA5 = new LineEntity<DateValueEntity>();
        MA5.setTitle("MA5");
        MA5.setLineColor(Color.WHITE);
        MA5.setLineData(initMA(5));
        lines.add(MA5);

        // 计算10日均线
        LineEntity<DateValueEntity> MA10 = new LineEntity<DateValueEntity>();
        MA10.setTitle("MA10");
        MA10.setLineColor(Color.RED);
        MA10.setLineData(initMA(10));
        lines.add(MA10);

        // 计算25日均线
        LineEntity<DateValueEntity> MA25 = new LineEntity<DateValueEntity>();
        MA25.setTitle("MA25");
        MA25.setLineColor(Color.GREEN);
        MA25.setLineData(initMA(25));
        lines.add(MA25);

        linechart.setAxisXColor(Color.LTGRAY);
        linechart.setAxisYColor(Color.LTGRAY);
        linechart.setBorderColor(Color.LTGRAY);
        linechart.setLongitudeFontSize(14);
        linechart.setLongitudeFontColor(Color.WHITE);
        linechart.setLatitudeColor(Color.GRAY);
        linechart.setLatitudeFontColor(Color.WHITE);
        linechart.setLongitudeColor(Color.GRAY);
        linechart.setMaxValue(280);
        linechart.setMinValue(240);
        linechart.setMaxPointNum(36);
        linechart.setDisplayLongitudeTitle(true);
        linechart.setDisplayLatitudeTitle(true);
        linechart.setDisplayLatitude(true);
        linechart.setDisplayLongitude(true);
        linechart.setLatitudeNum(5);
        linechart.setLongitudeNum(6);
        linechart.setDataQuadrantPaddingTop(5);
        linechart.setDataQuadrantPaddingBottom(5);
        linechart.setDataQuadrantPaddingLeft(5);
        linechart.setDataQuadrantPaddingRight(5);
        // linechart.setAxisYTitleQuadrantWidth(50);
        // linechart.setAxisXTitleQuadrantHeight(20);
        linechart.setAxisXPosition(Axis.AXIS_X_POSITION_BOTTOM);
        linechart.setAxisYPosition(Axis.AXIS_Y_POSITION_RIGHT);

        // 为chart1增加均线
        linechart.setLinesData(lines);
    }
}
