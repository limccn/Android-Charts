/*
 * DonutChartActivity.java
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
import cn.limc.androidcharts.entity.TitleValueColorEntity;
import cn.limc.androidcharts.view.DonutChart;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class DonutChartActivity extends Activity {

    DonutChart donutchart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut_chart);

        initDonutChart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.donut_chart, menu);
        return true;
    }

    private void initDonutChart() {
        this.donutchart = (DonutChart) findViewById(R.id.donutchart);
        List<TitleValueColorEntity> data3 = new ArrayList<TitleValueColorEntity>();
        data3.add(new TitleValueColorEntity(getResources().getString(R.string.piechart_title1),
                800, getResources().getColor(R.drawable.red)));
        data3.add(new TitleValueColorEntity(getResources().getString(R.string.piechart_title2),
                900, getResources().getColor(R.drawable.orange)));
        data3.add(new TitleValueColorEntity(getResources().getString(R.string.piechart_title3),
                200, getResources().getColor(R.drawable.yellow)));
        data3.add(new TitleValueColorEntity(getResources().getString(R.string.piechart_title4),
                400, getResources().getColor(R.drawable.lightgreen)));
        data3.add(new TitleValueColorEntity(getResources().getString(R.string.piechart_title5),
                700, getResources().getColor(R.drawable.green)));
        donutchart.setData(data3);
    }

}
