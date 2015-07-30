/*
 * CandleStickChartActivity.java
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
import cn.limc.androidcharts.component.Axis;
import cn.limc.androidcharts.diagram.CandleStickChart;
import cn.limc.androidcharts.series.ChartDataSet;
import cn.limc.androidcharts.series.ChartDataTable;
import cn.limc.demo.common.BaseActivity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Menu;

public class CandleStickChartActivity extends BaseActivity {

    CandleStickChart candlestickchart;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candle_stick_chart);
        
        initCandleStickChart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.candle_stick_chart, menu);
        return true;
    }

    private void initCandleStickChart() {
        this.candlestickchart = (CandleStickChart) findViewById(R.id.candlestickchart);
        candlestickchart.setAxisXColor(Color.LTGRAY);
        candlestickchart.setAxisYColor(Color.LTGRAY);
        candlestickchart.setLatitudeColor(Color.GRAY);
        candlestickchart.setLongitudeColor(Color.GRAY);
        candlestickchart.setBorderColor(Color.LTGRAY);
        candlestickchart.setLongitudeFontColor(Color.WHITE);
        candlestickchart.setLatitudeFontColor(Color.WHITE);

        // 最大显示足数
        //candlestickchart.setMaxSticksNum(52);
        candlestickchart.setDisplayNumber(52);
        // 最大纬线数
        candlestickchart.setLatitudeNum(5);
        // 最大经线数
        candlestickchart.setLongitudeNum(3);
        // 最大价格
        candlestickchart.setMaxValue(1200);
        // 最小价格
        candlestickchart.setMinValue(200);

        candlestickchart.setDisplayLongitudeTitle(true);
        candlestickchart.setDisplayLatitudeTitle(true);
        candlestickchart.setDisplayLatitude(true);
        candlestickchart.setDisplayLongitude(true);
        candlestickchart.setBackgroundColor(Color.BLACK);

        candlestickchart.setDataQuadrantPaddingTop(5);
        candlestickchart.setDataQuadrantPaddingBottom(5);
        candlestickchart.setDataQuadrantPaddingLeft(5);
        candlestickchart.setDataQuadrantPaddingRight(5);
//      candlestickchart.setAxisYTitleQuadrantWidth(50);
//      candlestickchart.setAxisXTitleQuadrantHeight(20);
        candlestickchart.setAxisXPosition(Axis.AXIS_X_POSITION_BOTTOM);
        candlestickchart.setAxisYPosition(Axis.AXIS_Y_POSITION_RIGHT);

        // 为chart2增加均线
        candlestickchart.setChartData(new ChartDataSet(new ChartDataTable(ohlc)));
    }
}
