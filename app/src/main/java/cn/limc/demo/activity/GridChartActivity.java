/*
 * GridChartActivity.java
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
import cn.limc.androidcharts.view.GridChart;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;

public class GridChartActivity extends Activity {
    GridChart gridchart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_chart);

        initGridChart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.grid_chart, menu);
        return true;
    }

    private void initGridChart() {
        this.gridchart = (GridChart) findViewById(R.id.gridchart);

        List<String> ytitle = new ArrayList<String>();
        ytitle.add("241");
        ytitle.add("242");
        ytitle.add("243");
        ytitle.add("244");
        ytitle.add("245");
        List<String> xtitle = new ArrayList<String>();
        xtitle.add("9:00");
        xtitle.add("10:00");
        xtitle.add("11:00");
        xtitle.add("13:00");
        xtitle.add("14:00");
        xtitle.add("15:00");

        gridchart.setAxisXColor(Color.LTGRAY);
        gridchart.setAxisYColor(Color.LTGRAY);
        gridchart.setBorderColor(Color.LTGRAY);
        gridchart.setLatitudeNum(5);
        gridchart.setLongitudeNum(6);
        gridchart.setDataQuadrantPaddingTop(5);
        gridchart.setDataQuadrantPaddingBottom(5);
        gridchart.setDataQuadrantPaddingLeft(5);
        gridchart.setDataQuadrantPaddingRight(5);
        // gridchart.setAxisYTitleQuadrantWidth(50);
        // gridchart.setAxisXTitleQuadrantHeight(20);
        gridchart.setAxisXPosition(Axis.AXIS_X_POSITION_BOTTOM);
        gridchart.setAxisYPosition(Axis.AXIS_Y_POSITION_RIGHT);
        gridchart.getSimpleGrid().setLatitudeTitles(ytitle);
        gridchart.getSimpleGrid().setLongitudeTitles(xtitle);
        gridchart.setLongitudeFontSize(14);
        gridchart.setLatitudeFontSize(14);
        gridchart.setLongitudeFontColor(Color.WHITE);
        gridchart.setLatitudeColor(Color.GRAY);
        gridchart.setLatitudeFontColor(Color.WHITE);
        gridchart.setLongitudeColor(Color.GRAY);
        gridchart.setDisplayLongitudeTitle(true);
        gridchart.setDisplayLatitudeTitle(true);
        gridchart.setDisplayLatitude(true);
        gridchart.setDisplayLongitude(true);
        gridchart.setCrossLinesColor(Color.BLUE);
        gridchart.setCrossLinesFontColor(Color.GREEN);
        gridchart.setBorderWidth(1);
        // gridchart.setAxisWidth(1);
    }

}
