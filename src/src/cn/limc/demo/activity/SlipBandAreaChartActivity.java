/*
 * SlipBandAreaChartActivity.java
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
import cn.limc.androidcharts.R.layout;
import cn.limc.androidcharts.R.menu;
import cn.limc.androidcharts.axis.Axis;
import cn.limc.androidcharts.entity.DateValueEntity;
import cn.limc.androidcharts.entity.LineEntity;
import cn.limc.androidcharts.event.IZoomable;
import cn.limc.androidcharts.view.SlipBandAreaChart;
import cn.limc.demo.common.BaseActivity;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;

public class SlipBandAreaChartActivity extends BaseActivity {

    SlipBandAreaChart slipbandchart;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slip_band_area_chart);

        initSlipBandChart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.slip_band_area_chart, menu);
        return true;
    }

    private void initSlipBandChart() {
        this.slipbandchart = (SlipBandAreaChart) findViewById(R.id.slipbandchart);
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

        slipbandchart.setAxisXColor(Color.LTGRAY);
        slipbandchart.setAxisYColor(Color.LTGRAY);
        slipbandchart.setBorderColor(Color.LTGRAY);
        slipbandchart.setLongitudeFontSize(14);
        slipbandchart.setLongitudeFontColor(Color.WHITE);
        slipbandchart.setLatitudeColor(Color.GRAY);
        slipbandchart.setLatitudeFontColor(Color.WHITE);
        slipbandchart.setLongitudeColor(Color.GRAY);
        slipbandchart.setMaxValue(1300);
        slipbandchart.setMinValue(700);
        slipbandchart.setDisplayFrom(10);
        slipbandchart.setDisplayNumber(30);
        slipbandchart.setMinDisplayNumber(5);
        slipbandchart.setZoomBaseLine(IZoomable.ZOOM_BASE_LINE_CENTER);
        slipbandchart.setDisplayLongitudeTitle(true);
        slipbandchart.setDisplayLatitudeTitle(true);
        slipbandchart.setDisplayLatitude(true);
        slipbandchart.setDisplayLongitude(true);
        slipbandchart.setDataQuadrantPaddingTop(5);
        slipbandchart.setDataQuadrantPaddingBottom(5);
        slipbandchart.setDataQuadrantPaddingLeft(5);
        slipbandchart.setDataQuadrantPaddingRight(5);
        // slipbandchart.setAxisYTitleQuadrantWidth(50);
        // slipbandchart.setAxisXTitleQuadrantHeight(20);
        slipbandchart.setAxisXPosition(Axis.AXIS_X_POSITION_BOTTOM);
        slipbandchart.setAxisYPosition(Axis.AXIS_Y_POSITION_RIGHT);

        slipbandchart.setLinesData(lines);
    }
}
