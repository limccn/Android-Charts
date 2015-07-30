/*
 * BOLLMASlipCandleStickChartActivity.java
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
import cn.limc.androidcharts.entity.ChartDataSet;
import cn.limc.androidcharts.entity.ChartDataTable;
import cn.limc.androidcharts.entity.DateValueEntity;
import cn.limc.androidcharts.entity.LineEntity;
import cn.limc.androidcharts.event.IZoomable;
import cn.limc.androidcharts.view.BOLLMASlipCandleStickChart;
import cn.limc.demo.common.BaseActivity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Menu;

public class BOLLMASlipCandleStickChartActivity extends BaseActivity {

    BOLLMASlipCandleStickChart bollmaslipcandlestickchart;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bollmaslip_candle_stick_chart);
        
        initBOLLMASlipCandleStickChart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.bollmaslip_candle_stick_chart, menu);
        return true;
    }
    
    private void initBOLLMASlipCandleStickChart() {
        this.bollmaslipcandlestickchart = (BOLLMASlipCandleStickChart) findViewById(R.id.bollmaslipcandlestickchart);
        ChartDataSet lines = new ChartDataSet();

        // 计算5日均线
        LineEntity ma5 = new LineEntity();
        ma5.setTitle("MA5");
        ma5.setLineColor(Color.WHITE);
        ma5.setTableData(initMA(5));
        lines.add(ma5);

        // 计算10日均线
        LineEntity ma10 = new LineEntity();
        ma10.setTitle("MA10");
        ma10.setLineColor(Color.RED);
        ma10.setTableData(initMA(10));
        lines.add(ma10);

        // 计算25日均线
        LineEntity ma25 = new LineEntity();
        ma25.setTitle("MA25");
        ma25.setLineColor(Color.GREEN);
        ma25.setTableData(initMA(25));
        lines.add(ma25);

        ChartDataSet band = new ChartDataSet();
        LineEntity lower = new LineEntity();
        lower.setTitle("LOWER");
        lower.setLineColor(Color.YELLOW);
        lower.setTableData(dv1);
        band.add(lower);

        LineEntity upper = new LineEntity();
        upper.setTitle("UPPER");
        upper.setLineColor(Color.CYAN);
        upper.setTableData(dv2);
        band.add(upper);

        bollmaslipcandlestickchart.setAxisXColor(Color.LTGRAY);
        bollmaslipcandlestickchart.setAxisYColor(Color.LTGRAY);
        bollmaslipcandlestickchart.setLatitudeColor(Color.GRAY);
        bollmaslipcandlestickchart.setLongitudeColor(Color.GRAY);
        bollmaslipcandlestickchart.setBorderColor(Color.LTGRAY);
        bollmaslipcandlestickchart.setLongitudeFontColor(Color.WHITE);
        bollmaslipcandlestickchart.setLatitudeFontColor(Color.WHITE);

        // 最大纬线数
        bollmaslipcandlestickchart.setLatitudeNum(5);
        // 最大经线数
        bollmaslipcandlestickchart.setLongitudeNum(3);
        // 最大价格
        bollmaslipcandlestickchart.setMaxValue(1200);
        // 最小价格
        bollmaslipcandlestickchart.setMinValue(200);

        bollmaslipcandlestickchart.setDisplayFrom(10);

        bollmaslipcandlestickchart.setDisplayNumber(30);

        bollmaslipcandlestickchart.setMinDisplayNumber(5);

        bollmaslipcandlestickchart
                .setZoomBaseLine(IZoomable.ZOOM_BASE_LINE_CENTER);

        bollmaslipcandlestickchart.setDisplayLongitudeTitle(true);
        bollmaslipcandlestickchart.setDisplayLatitudeTitle(true);
        bollmaslipcandlestickchart.setDisplayLatitude(true);
        bollmaslipcandlestickchart.setDisplayLongitude(true);
        bollmaslipcandlestickchart.setBackgroundColor(Color.BLACK);

        bollmaslipcandlestickchart.setDataQuadrantPaddingTop(5);
        bollmaslipcandlestickchart.setDataQuadrantPaddingBottom(5);
        bollmaslipcandlestickchart.setDataQuadrantPaddingLeft(5);
        bollmaslipcandlestickchart.setDataQuadrantPaddingRight(5);
//      bollmaslipcandlestickchart.setAxisYTitleQuadrantWidth(50);
//      bollmaslipcandlestickchart.setAxisXTitleQuadrantHeight(20);
        bollmaslipcandlestickchart
                .setAxisXPosition(Axis.AXIS_X_POSITION_BOTTOM);
        bollmaslipcandlestickchart
                .setAxisYPosition(Axis.AXIS_Y_POSITION_RIGHT);

        // 为chart2增加均线
        bollmaslipcandlestickchart.setLinesData(lines);
        bollmaslipcandlestickchart.setBandData(band);

        // 为chart2增加均线
        bollmaslipcandlestickchart
                .setChartData(new ChartDataSet(new ChartDataTable(ohlc)));
    }

}
