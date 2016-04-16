/*
 * MASlipStickChartActivity.java
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
import cn.limc.androidcharts.entity.DateValueEntity;
import cn.limc.androidcharts.entity.IStickEntity;
import cn.limc.androidcharts.entity.LineEntity;
import cn.limc.androidcharts.entity.ListChartData;
import cn.limc.androidcharts.event.IZoomable;
import cn.limc.androidcharts.view.MASlipStickChart;
import cn.limc.demo.common.BaseActivity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Menu;

public class MASlipStickChartActivity extends BaseActivity {

    MASlipStickChart maslipstickchart;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maslip_stick_chart);
        
        initMASlipStickChart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.maslip_stick_chart, menu);
        return true;
    }

    private void initMASlipStickChart() {
        this.maslipstickchart = (MASlipStickChart) findViewById(R.id.maslipstickchart);

        // 以下计算VOL
        List<LineEntity<DateValueEntity>> vlines = new ArrayList<LineEntity<DateValueEntity>>();

        // 计算5日均线
        LineEntity<DateValueEntity> VMA5 = new LineEntity<DateValueEntity>();
        VMA5.setTitle("MA5");
        VMA5.setLineColor(Color.WHITE);
        VMA5.setLineData(initVMA(5));
        vlines.add(VMA5);

        // 计算10日均线
        LineEntity<DateValueEntity> VMA10 = new LineEntity<DateValueEntity>();
        VMA10.setTitle("MA10");
        VMA10.setLineColor(Color.RED);
        VMA10.setLineData(initVMA(10));
        vlines.add(VMA10);

        // 计算25日均线
        LineEntity<DateValueEntity> VMA25 = new LineEntity<DateValueEntity>();
        VMA25.setTitle("MA25");
        VMA25.setLineColor(Color.GREEN);
        VMA25.setLineData(initVMA(25));
        vlines.add(VMA25);

        maslipstickchart.setAxisXColor(Color.LTGRAY);
        maslipstickchart.setAxisYColor(Color.LTGRAY);
        maslipstickchart.setLatitudeColor(Color.GRAY);
        maslipstickchart.setLongitudeColor(Color.GRAY);
        maslipstickchart.setBorderColor(Color.LTGRAY);
        maslipstickchart.setLongitudeFontColor(Color.WHITE);
        maslipstickchart.setLatitudeFontColor(Color.WHITE);

        // 最大纬线数
        maslipstickchart.setLatitudeNum(2);
        // 最大经线数
        maslipstickchart.setLongitudeNum(3);
        // 最大价格
        maslipstickchart.setMaxValue(600000);
        // 最小价格
        maslipstickchart.setMinValue(100);

        maslipstickchart.setDisplayFrom(10);

        maslipstickchart.setDisplayNumber(30);

        maslipstickchart.setMinDisplayNumber(5);

        maslipstickchart.setZoomBaseLine(IZoomable.ZOOM_BASE_LINE_CENTER);

        maslipstickchart.setDisplayLongitudeTitle(true);
        maslipstickchart.setDisplayLatitudeTitle(true);
        maslipstickchart.setDisplayLatitude(true);
        maslipstickchart.setDisplayLongitude(true);
        maslipstickchart.setBackgroundColor(Color.BLACK);

        maslipstickchart.setDataQuadrantPaddingTop(5);
        maslipstickchart.setDataQuadrantPaddingBottom(5);
        maslipstickchart.setDataQuadrantPaddingLeft(5);
        maslipstickchart.setDataQuadrantPaddingRight(5);
//      maslipstickchart.setAxisYTitleQuadrantWidth(50);
//      maslipstickchart.setAxisXTitleQuadrantHeight(20);
        maslipstickchart.setAxisXPosition(Axis.AXIS_X_POSITION_BOTTOM);
        maslipstickchart.setAxisYPosition(Axis.AXIS_Y_POSITION_RIGHT);

        // 为chart1增加均线
        maslipstickchart.setLineData(vlines);
        // 为chart1增加均线
        maslipstickchart.setStickData(new ListChartData<IStickEntity>(vol));
    }
}
