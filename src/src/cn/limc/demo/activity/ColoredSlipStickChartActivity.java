/*
 * ColoredSlipStickChartActivity.java
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
import cn.limc.androidcharts.event.IZoomable;
import cn.limc.androidcharts.view.ColoredSlipStickChart;
import cn.limc.demo.common.BaseActivity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Menu;

public class ColoredSlipStickChartActivity extends BaseActivity {

    ColoredSlipStickChart coloredslipstickchart;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colored_slip_stick_chart);

        initColoredSlipStickChart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.colored_slip_stick_chart, menu);
        return true;
    }

    private void initColoredSlipStickChart() {
        this.coloredslipstickchart = (ColoredSlipStickChart) findViewById(R.id.coloredslipstickchart);

        coloredslipstickchart.setAxisXColor(Color.LTGRAY);
        coloredslipstickchart.setAxisYColor(Color.LTGRAY);
        coloredslipstickchart.setLatitudeColor(Color.GRAY);
        coloredslipstickchart.setLongitudeColor(Color.GRAY);
        coloredslipstickchart.setBorderColor(Color.LTGRAY);
        coloredslipstickchart.setLongitudeFontColor(Color.WHITE);
        coloredslipstickchart.setLatitudeFontColor(Color.WHITE);

        // // 最大纬线数
        // coloredslipstickchart.setLatitudeNum(2);
        // // 最大经线数
        // coloredslipstickchart.setLongitudeNum(3);
        // 最大价格
        coloredslipstickchart.setMaxValue(600000);
        // 最小价格
        coloredslipstickchart.setMinValue(100);

        coloredslipstickchart.setDisplayFrom(10);

        coloredslipstickchart.setDisplayNumber(30);

        coloredslipstickchart.setMinDisplayNumber(5);

        coloredslipstickchart.setZoomBaseLine(IZoomable.ZOOM_BASE_LINE_CENTER);

        coloredslipstickchart.setDisplayLongitudeTitle(true);
        coloredslipstickchart.setDisplayLatitudeTitle(true);
        coloredslipstickchart.setDisplayLatitude(true);
        coloredslipstickchart.setDisplayLongitude(true);
        coloredslipstickchart.setBackgroundColor(Color.BLACK);

        coloredslipstickchart.setDataQuadrantPaddingTop(5);
        coloredslipstickchart.setDataQuadrantPaddingBottom(5);
        coloredslipstickchart.setDataQuadrantPaddingLeft(5);
        coloredslipstickchart.setDataQuadrantPaddingRight(5);
        // coloredslipstickchart.setAxisYTitleQuadrantWidth(50);
        // coloredslipstickchart.setAxisXTitleQuadrantHeight(20);
        coloredslipstickchart.setAxisXPosition(Axis.AXIS_X_POSITION_BOTTOM);
        coloredslipstickchart.setAxisYPosition(Axis.AXIS_Y_POSITION_RIGHT);

        // 为chart1增加均线
        coloredslipstickchart.setStickData(new ListChartData<IStickEntity>(volc));
    }

}
