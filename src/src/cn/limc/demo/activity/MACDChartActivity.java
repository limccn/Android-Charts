/*
 * MACDChartActivity.java
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
import cn.limc.androidcharts.view.MACDChart;
import cn.limc.demo.common.BaseActivity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Menu;

public class MACDChartActivity extends BaseActivity {

    MACDChart macdChart;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_macdchart);

        initMACDChart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.macdchart, menu);
        return true;
    }

    private void initMACDChart() {

        macdChart = (MACDChart) findViewById(R.id.macdchart);
        macdChart.setMaxValue(300000);
        macdChart.setMinValue(-300000);
        // macdChart.setDisplayCrossXOnTouch(true);
        // macdChart.setDisplayCrossYOnTouch(true);
        macdChart.setLatitudeNum(4);
        macdChart.setLongitudeNum(3);
        macdChart.setDisplayFrom(0);
        macdChart.setDisplayNumber(10);
        macdChart.setMinDisplayNumber(5);
        macdChart.setZoomBaseLine(IZoomable.ZOOM_BASE_LINE_CENTER);
        macdChart.setAxisXColor(Color.LTGRAY);
        macdChart.setAxisYColor(Color.LTGRAY);
        macdChart.setLatitudeColor(Color.GRAY);
        macdChart.setLongitudeColor(Color.GRAY);
        macdChart.setBorderColor(Color.LTGRAY);
        macdChart.setLongitudeFontColor(Color.WHITE);
        macdChart.setLatitudeFontColor(Color.WHITE);
        macdChart.setMacdDisplayType(MACDChart.MACD_DISPLAY_TYPE_STICK);
        macdChart.setPositiveStickColor(Color.RED);
        macdChart.setNegativeStickColor(Color.CYAN);
        macdChart.setMacdLineColor(Color.CYAN);
        macdChart.setDeaLineColor(Color.YELLOW);
        macdChart.setDiffLineColor(Color.WHITE);
        macdChart.setDataQuadrantPaddingTop(5);
        macdChart.setDataQuadrantPaddingBottom(5);
        macdChart.setDataQuadrantPaddingLeft(5);
        macdChart.setDataQuadrantPaddingRight(5);
        // macdChart.setAxisYTitleQuadrantWidth(50);
        // macdChart.setAxisXTitleQuadrantHeight(20);
        macdChart.setAxisXPosition(Axis.AXIS_X_POSITION_BOTTOM);
        macdChart.setAxisYPosition(Axis.AXIS_Y_POSITION_RIGHT);

        // 设置stickData
        macdChart.setStickData(new ListChartData<IStickEntity>(macd));
    }

}
