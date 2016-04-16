/*
 * SlipStickChartActivity.java
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
import cn.limc.androidcharts.axis.Axis;
import cn.limc.androidcharts.entity.IChartData;
import cn.limc.androidcharts.entity.IStickEntity;
import cn.limc.androidcharts.entity.ListChartData;
import cn.limc.androidcharts.event.IZoomable;
import cn.limc.androidcharts.view.SlipStickChart;
import cn.limc.demo.common.BaseActivity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Menu;

public class SlipStickChartActivity extends BaseActivity {

    SlipStickChart slipstickchart;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slip_stick_chart);

        initSlipStickChart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.slip_stick_chart, menu);
        return true;
    }

    private void initSlipStickChart() {
        this.slipstickchart = (SlipStickChart) findViewById(R.id.slipstickchart);

        slipstickchart.setAxisXColor(Color.LTGRAY);
        slipstickchart.setAxisYColor(Color.LTGRAY);
        slipstickchart.setLatitudeColor(Color.GRAY);
        slipstickchart.setLongitudeColor(Color.GRAY);
        slipstickchart.setBorderColor(Color.LTGRAY);
        slipstickchart.setLongitudeFontColor(Color.WHITE);
        slipstickchart.setLatitudeFontColor(Color.WHITE);
        slipstickchart.setDataQuadrantPaddingTop(6);
        slipstickchart.setDataQuadrantPaddingBottom(1);
        slipstickchart.setDataQuadrantPaddingLeft(1);
        slipstickchart.setDataQuadrantPaddingRight(1);
        // slipstickchart.setAxisYTitleQuadrantWidth(50);
        // slipstickchart.setAxisXTitleQuadrantHeight(20);
        slipstickchart.setAxisXPosition(Axis.AXIS_X_POSITION_BOTTOM);
        slipstickchart.setAxisYPosition(Axis.AXIS_Y_POSITION_RIGHT);

        // // 最大纬线数
        // slipstickchart.setLatitudeNum(2);
        // // 最大经线数
        // slipstickchart.setLongitudeNum(3);
        // 最大价格
        slipstickchart.setMaxValue(600000);
        // 最小价格
        slipstickchart.setMinValue(100);

        slipstickchart.setDisplayFrom(10);

        slipstickchart.setDisplayNumber(30);

        slipstickchart.setMinDisplayNumber(5);

        slipstickchart.setZoomBaseLine(IZoomable.ZOOM_BASE_LINE_CENTER);

        slipstickchart.setDisplayLongitudeTitle(true);
        slipstickchart.setDisplayLatitudeTitle(true);
        slipstickchart.setDisplayLatitude(true);
        slipstickchart.setDisplayLongitude(true);
        slipstickchart.setBackgroundColor(Color.BLACK);

        slipstickchart.setDataMultiple(100);
        slipstickchart.setAxisYDecimalFormat("#,##0.00");
        slipstickchart.setAxisXDateTargetFormat("yyyy/MM/dd");
        slipstickchart.setAxisXDateSourceFormat("yyyyMMdd");

        IChartData<IStickEntity> vol = new ListChartData<IStickEntity>(this.vol);
        // 为chart1增加均线
        slipstickchart.setStickData(vol);
    }

}
