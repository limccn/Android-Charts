/*
 * SlipAreaChartActivity.java
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
import cn.limc.androidcharts.event.IZoomable;
import cn.limc.androidcharts.view.SlipAreaChart;
import cn.limc.demo.common.BaseActivity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Menu;

public class SlipAreaChartActivity extends BaseActivity {

    SlipAreaChart slipareachart;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slip_area_chart);
        initSlipAreaChart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.slip_area_chart, menu);
        return true;
    }

    private void initSlipAreaChart() {
        
        this.slipareachart = (SlipAreaChart) findViewById(R.id.slipareachart);
        List<LineEntity<DateValueEntity>> lines = new ArrayList<LineEntity<DateValueEntity>>();

        // 计算5日均线
        LineEntity<DateValueEntity> MA5 = new LineEntity<DateValueEntity>();
        MA5.setTitle("HIGH");
        MA5.setLineColor(Color.WHITE);
        MA5.setLineData(dv1);
        lines.add(MA5);

        // 计算10日均线
        LineEntity<DateValueEntity> MA10 = new LineEntity<DateValueEntity>();
        MA10.setTitle("LOW");
        MA10.setLineColor(Color.RED);
        MA10.setLineData(dv2);
        lines.add(MA10);

        slipareachart.setAxisXColor(Color.LTGRAY);
        slipareachart.setAxisYColor(Color.LTGRAY);
        slipareachart.setBorderColor(Color.LTGRAY);
        slipareachart.setLongitudeFontSize(14);
        slipareachart.setLongitudeFontColor(Color.WHITE);
        slipareachart.setLatitudeColor(Color.GRAY);
        slipareachart.setLatitudeFontColor(Color.WHITE);
        slipareachart.setLongitudeColor(Color.GRAY);
        slipareachart.setMaxValue(1300);
        slipareachart.setMinValue(700);
        slipareachart.setDisplayFrom(10);
        slipareachart.setDisplayNumber(30);
        slipareachart.setMinDisplayNumber(5);
        slipareachart.setZoomBaseLine(IZoomable.ZOOM_BASE_LINE_CENTER);
        slipareachart.setDisplayLongitudeTitle(true);
        slipareachart.setDisplayLatitudeTitle(true);
        slipareachart.setDisplayLatitude(true);
        slipareachart.setDisplayLongitude(true);
        slipareachart.setDataQuadrantPaddingTop(5);
        slipareachart.setDataQuadrantPaddingBottom(5);
        slipareachart.setDataQuadrantPaddingLeft(5);
        slipareachart.setDataQuadrantPaddingRight(5);
        // slipareachart.setAxisYTitleQuadrantWidth(50);
        // slipareachart.setAxisXTitleQuadrantHeight(20);
        slipareachart.setAxisXPosition(Axis.AXIS_X_POSITION_BOTTOM);
        slipareachart.setAxisYPosition(Axis.AXIS_Y_POSITION_RIGHT);

        slipareachart.setLinesData(lines);
    }
}
