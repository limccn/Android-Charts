/*
 * SlipCandleStickChartActivity.java
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
import cn.limc.androidcharts.view.SlipCandleStickChart;
import cn.limc.demo.common.BaseActivity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Menu;

public class SlipCandleStickChartActivity extends BaseActivity {

    SlipCandleStickChart slipcandlestickchart;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slip_candle_stick_chart);
        
        initSlipCandleStickChart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.slip_candle_stick_chart, menu);
        return true;
    }
    
    private void initSlipCandleStickChart() {
        this.slipcandlestickchart = (SlipCandleStickChart) findViewById(R.id.slipcandlestickchart);
        slipcandlestickchart.setAxisXColor(Color.LTGRAY);
        slipcandlestickchart.setAxisYColor(Color.LTGRAY);
        slipcandlestickchart.setLatitudeColor(Color.GRAY);
        slipcandlestickchart.setLongitudeColor(Color.GRAY);
        slipcandlestickchart.setBorderColor(Color.LTGRAY);
        slipcandlestickchart.setLongitudeFontColor(Color.WHITE);
        slipcandlestickchart.setLatitudeFontColor(Color.WHITE);

        // 最大纬线数
        slipcandlestickchart.setLatitudeNum(5);
        // 最大经线数
        slipcandlestickchart.setLongitudeNum(3);
        // 最大价格
        slipcandlestickchart.setMaxValue(1200);
        // 最小价格
        slipcandlestickchart.setMinValue(200);

        slipcandlestickchart.setDisplayFrom(10);

        slipcandlestickchart.setDisplayNumber(30);

        slipcandlestickchart.setMinDisplayNumber(5);

        slipcandlestickchart.setZoomBaseLine(IZoomable.ZOOM_BASE_LINE_CENTER);

        slipcandlestickchart.setDisplayLongitudeTitle(true);
        slipcandlestickchart.setDisplayLatitudeTitle(true);
        slipcandlestickchart.setDisplayLatitude(true);
        slipcandlestickchart.setDisplayLongitude(true);
        slipcandlestickchart.setBackgroundColor(Color.BLACK);

        slipcandlestickchart.setDataQuadrantPaddingTop(5);
        slipcandlestickchart.setDataQuadrantPaddingBottom(5);
        slipcandlestickchart.setDataQuadrantPaddingLeft(5);
        slipcandlestickchart.setDataQuadrantPaddingRight(5);
//      slipcandlestickchart.setAxisYTitleQuadrantWidth(50);
//      slipcandlestickchart.setAxisXTitleQuadrantHeight(20);
        slipcandlestickchart.setAxisXPosition(Axis.AXIS_X_POSITION_BOTTOM);
        slipcandlestickchart.setAxisYPosition(Axis.AXIS_Y_POSITION_RIGHT);

        // 为chart2增加均线
        slipcandlestickchart
                .setStickData(new ListChartData<IStickEntity>(ohlc));
    }

}
