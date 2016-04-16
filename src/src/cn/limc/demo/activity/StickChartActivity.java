/*
 * StickChartActivity.java
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
import cn.limc.androidcharts.entity.IStickEntity;
import cn.limc.androidcharts.entity.ListChartData;
import cn.limc.androidcharts.view.StickChart;
import cn.limc.demo.common.BaseActivity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Menu;

public class StickChartActivity extends BaseActivity {

    StickChart stickchart;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stick_chart);

        initStickChart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.stick_chart, menu);
        return true;
    }

    private void initStickChart() {
        this.stickchart = (StickChart) findViewById(R.id.stickchart);

        stickchart.setAxisXColor(Color.LTGRAY);
        stickchart.setAxisYColor(Color.LTGRAY);
        stickchart.setLatitudeColor(Color.GRAY);
        stickchart.setLongitudeColor(Color.GRAY);
        stickchart.setBorderColor(Color.LTGRAY);
        stickchart.setLongitudeFontColor(Color.WHITE);
        stickchart.setLatitudeFontColor(Color.WHITE);
        stickchart.setDataQuadrantPaddingTop(6);
        stickchart.setDataQuadrantPaddingBottom(1);
        stickchart.setDataQuadrantPaddingLeft(1);
        stickchart.setDataQuadrantPaddingRight(1);
        // stickchart.setAxisYTitleQuadrantWidth(50);
        // stickchart.setAxisXTitleQuadrantHeight(20);
        stickchart.setAxisXPosition(Axis.AXIS_X_POSITION_BOTTOM);
        stickchart.setAxisYPosition(Axis.AXIS_Y_POSITION_RIGHT);

        // 最大显示足数
        // stickchart.setMaxSticksNum(52);
        // 最大纬线数
        // stickchart.setLatitudeNum(2);
        // 最大经线数
        // stickchart.setLongitudeNum(3);
        // 最大价格
        stickchart.setMaxValue(10000);
        // 最小价格
        stickchart.setMinValue(100);

        stickchart.setDisplayLongitudeTitle(true);
        stickchart.setDisplayLatitudeTitle(true);
        stickchart.setDisplayLatitude(true);
        stickchart.setDisplayLongitude(true);
        stickchart.setBackgroundColor(Color.BLACK);

        stickchart.setDataMultiple(100);
        stickchart.setAxisYDecimalFormat("#,##0.00");
        stickchart.setAxisXDateTargetFormat("yyyy/MM/dd");
        stickchart.setAxisXDateSourceFormat("yyyyMMdd");

        // 为chart1增加均线
        stickchart.setStickData(new ListChartData<IStickEntity>(vol));
    }

}
