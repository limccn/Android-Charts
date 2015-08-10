/*
 * SlipMinusStickChartActivity.java
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
import cn.limc.androidcharts.component.AbstractAxis;
import cn.limc.androidcharts.diagram.SlipMinusStickChart;
import cn.limc.androidcharts.event.Zoomable;
import cn.limc.androidcharts.series.ChartDataRow;
import cn.limc.androidcharts.series.ChartDataSet;
import cn.limc.androidcharts.series.ChartDataTable;
import cn.limc.androidcharts.series.StickEntity;
import cn.limc.demo.common.BaseActivity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Menu;

public class SlipMinusStickChartActivity extends BaseActivity {

    SlipMinusStickChart slipminusstickchart;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slip_minus_stick_chart);

        initSlipMinusStickChart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.slip_minus_stick_chart, menu);
        return true;
    }

    private void initSlipMinusStickChart() {
        this.slipminusstickchart = (SlipMinusStickChart) findViewById(R.id.slipminusstickchart);

        List<ChartDataRow> data = new ArrayList<ChartDataRow>();
        data.add(new StickEntity(50000, 0, 20110603));
        data.add(new StickEntity(42000, 0, 20110703));
        data.add(new StickEntity(32000, 0, 20110803));
        data.add(new StickEntity(21000, 0, 20110903));
        data.add(new StickEntity(0, -12000, 20111003));
        data.add(new StickEntity(0, -28000, 20111103));
        data.add(new StickEntity(0, -41000, 20111203));
        data.add(new StickEntity(0, -25000, 20120103));
        data.add(new StickEntity(0, -18000, 20120203));
        data.add(new StickEntity(14000, 0, 20120303));
        data.add(new StickEntity(24000, 0, 20120303));
        data.add(new StickEntity(36000, 0, 20120303));
        data.add(new StickEntity(46000, 0, 20120303));
        data.add(new StickEntity(50000, 0, 20110603));
        data.add(new StickEntity(42000, 0, 20110703));
        data.add(new StickEntity(32000, 0, 20110803));
        data.add(new StickEntity(21000, 0, 20110903));
        data.add(new StickEntity(0, -12000, 20111003));
        data.add(new StickEntity(0, -28000, 20111103));
        data.add(new StickEntity(0, -41000, 20111203));
        data.add(new StickEntity(0, -25000, 20120103));
        data.add(new StickEntity(0, -18000, 20120203));
        data.add(new StickEntity(14000, 0, 20120303));
        data.add(new StickEntity(24000, 0, 20120303));
        data.add(new StickEntity(36000, 0, 20120303));
        data.add(new StickEntity(46000, 0, 20120303));
        data.add(new StickEntity(50000, 0, 20110603));
        data.add(new StickEntity(42000, 0, 20110703));
        data.add(new StickEntity(32000, 0, 20110803));
        data.add(new StickEntity(21000, 0, 20110903));
        data.add(new StickEntity(0, -12000, 20111003));
        data.add(new StickEntity(0, -28000, 20111103));
        data.add(new StickEntity(0, -41000, 20111203));
        data.add(new StickEntity(0, -25000, 20120103));
        data.add(new StickEntity(0, -18000, 20120203));
        data.add(new StickEntity(14000, 0, 20120303));
        data.add(new StickEntity(24000, 0, 20120303));
        data.add(new StickEntity(36000, 0, 20120303));
        data.add(new StickEntity(46000, 0, 20120303));

//        slipminusstickchart.setMaxValue(50000);
//        slipminusstickchart.setMinValue(-50000);
//
//        slipminusstickchart.setBorderColor(Color.GRAY);
////        slipminusstickchart.setAxisXColor(Color.WHITE);
////        slipminusstickchart.setAxisYColor(Color.WHITE);
//        slipminusstickchart.setLatitudeFontColor(Color.WHITE);
//        slipminusstickchart.setLatitudeColor(Color.GRAY);
//        slipminusstickchart.setLongitudeFontColor(Color.WHITE);
//        slipminusstickchart.setLongitudeColor(Color.GRAY);
//        // 最大纬线数
//        slipminusstickchart.setLatitudeNum(3);
//        // 最大经线数
//        slipminusstickchart.setLongitudeNum(2);
//        slipminusstickchart.setDisplayFrom(0);
//        slipminusstickchart.setDisplayNumber(10);
//        slipminusstickchart.setMinDisplayNumber(5);
//
//        slipminusstickchart.setZoomBaseLine(Zoomable.ZOOM_BASE_LINE_CENTER);
//        slipminusstickchart.setDisplayLongitudeTitle(true);
//        slipminusstickchart.setDisplayLatitudeTitle(true);
//        // slipminusstickchart.setDisplayCrossXOnTouch(false);
//        // slipminusstickchart.setDisplayCrossYOnTouch(false);
//        slipminusstickchart.setDisplayLatitude(true);
//        slipminusstickchart.setDisplayLongitude(true);
//        slipminusstickchart.setStickSpacing(10);
//
//        slipminusstickchart.setDataQuadrantPaddingTop(5);
//        slipminusstickchart.setDataQuadrantPaddingBottom(5);
//        slipminusstickchart.setDataQuadrantPaddingLeft(5);
//        slipminusstickchart.setDataQuadrantPaddingRight(5);
//        // slipminusstickchart.setAxisYTitleQuadrantWidth(50);
//        // slipminusstickchart.setAxisXTitleQuadrantHeight(20);
////        slipminusstickchart.setAxisXPosition(AbstractAxis.AXIS_X_POSITION_BOTTOM);
////        slipminusstickchart.setAxisYPosition(AbstractAxis.AXIS_Y_POSITION_RIGHT);

        ChartDataSet datas = new ChartDataSet(new ChartDataTable(data));
       // slipminusstickchart.setChartData(datas);
    }

}
