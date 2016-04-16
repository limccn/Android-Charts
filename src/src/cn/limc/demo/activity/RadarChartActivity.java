/*
 * RadarChartActivity.java
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
import cn.limc.androidcharts.view.RadarChart;
import cn.limc.androidcharts.entity.*;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class RadarChartActivity extends Activity {

    RadarChart radarchart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar_chart);

        initRadarChart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.radar_chart, menu);
        return true;
    }

    private void initRadarChart() {
        this.radarchart = (RadarChart) findViewById(R.id.radarchart);

        final List<TitleValueEntity> data1 = new ArrayList<TitleValueEntity>();
        data1.add(new TitleValueEntity(getResources().getString(R.string.radarchart_title1), 3));
        data1.add(new TitleValueEntity(getResources().getString(R.string.radarchart_title2), 4));
        data1.add(new TitleValueEntity(getResources().getString(R.string.radarchart_title3), 9));
        data1.add(new TitleValueEntity(getResources().getString(R.string.radarchart_title4), 8));
        data1.add(new TitleValueEntity(getResources().getString(R.string.radarchart_title5), 10));

        final List<TitleValueEntity> data2 = new ArrayList<TitleValueEntity>();
        data2.add(new TitleValueEntity(getResources().getString(R.string.radarchart_title1), 3));
        data2.add(new TitleValueEntity(getResources().getString(R.string.radarchart_title2), 4));
        data2.add(new TitleValueEntity(getResources().getString(R.string.radarchart_title3), 5));
        data2.add(new TitleValueEntity(getResources().getString(R.string.radarchart_title4), 6));
        data2.add(new TitleValueEntity(getResources().getString(R.string.radarchart_title5), 7));

        final List<List<TitleValueEntity>> data = new ArrayList<List<TitleValueEntity>>();
        data.add(data1);
        data.add(data2);

        radarchart.setData(data);
        radarchart.setLatitudeNum(5);
    }
}
