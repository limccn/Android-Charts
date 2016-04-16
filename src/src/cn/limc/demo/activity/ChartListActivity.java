/*
 * ChartListActivity.java
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
import java.util.HashMap;
import java.util.Map;

import cn.limc.androidcharts.R;
import android.os.Bundle;
import android.content.Intent;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ChartListActivity extends Activity {

    private String[] mListTitle = { "GridChart", "LineChart", "SlipLineChart", "SlipAreaChart",
            "SlipBandAreaChart", "StickChart", "SlipStickChart", "ColoredSlipStickChart",
            "MAStickChart", "MASlipStickChart", "MinusStickChart", "SlipMinusStickChart",
            "CandleStickChart", "SlipCandleStickChart", "MACandleStickChart",
            "MASlipCandleStickChart", "BOLLMASlipCandleStckChart", "MACDChart", "PieChart",
            "RoseChart", "SpiderWebChart", "RadarChart", "DonutChart" };

    private String[] mListStr = { "cn.limc.androidcharts.view.GridChart",
            "cn.limc.androidcharts.view.LineChart", "cn.limc.androidcharts.view.SlipLineChart",
            "cn.limc.androidcharts.view.SlipAreaChart",
            "cn.limc.androidcharts.view.SlipBandAreaChart",
            "cn.limc.androidcharts.view.StickChart", "cn.limc.androidcharts.view.SlipStickChart",
            "cn.limc.androidcharts.view.ColoredSlipStickChart",
            "cn.limc.androidcharts.view.MAStickChart",
            "cn.limc.androidcharts.view.MASlipStickChart",
            "cn.limc.androidcharts.view.MinusStickChart",
            "cn.limc.androidcharts.view.SlipMinusStickChart",
            "cn.limc.androidcharts.view.CandleStickChart",
            "cn.limc.androidcharts.view.SlipCandleStickChart",
            "cn.limc.androidcharts.view.MACandleStickChart",
            "cn.limc.androidcharts.view.MASlipCandleStickChart",
            "cn.limc.androidcharts.view.BOLLMASlipCandleStickChart",
            "cn.limc.androidcharts.view.MACDChart", "cn.limc.androidcharts.view.PieChart",
            "cn.limc.androidcharts.view.RoseChart", "cn.limc.androidcharts.view.SpiderWebChart",
            "cn.limc.androidcharts.view.RadarChart", "cn.limc.androidcharts.view.DonutChart" };
    ListView mListView = null;
    ArrayList<Map<String, Object>> mData = new ArrayList<Map<String, Object>>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_list);

        mListView = (ListView) findViewById(R.id.ChartListView);

        int lengh = mListTitle.length;
        for (int i = 0; i < lengh; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("title", mListTitle[i]);
            item.put("text", mListStr[i]);
            mData.add(item);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, mData, android.R.layout.simple_list_item_2,
                new String[] { "title", "text" }, new int[] { android.R.id.text1,
                        android.R.id.text2 });
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent();
                switch (position) {
                    case 0:
                        intent.setClass(ChartListActivity.this,
                                cn.limc.demo.activity.GridChartActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent.setClass(ChartListActivity.this,
                                cn.limc.demo.activity.LineChartActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent.setClass(ChartListActivity.this,
                                cn.limc.demo.activity.SlipLineChartActivity.class);
                        startActivity(intent);
                        break;
                    case 3:
                        intent.setClass(ChartListActivity.this,
                                cn.limc.demo.activity.SlipAreaChartActivity.class);
                        startActivity(intent);
                        break;
                    case 4:
                        intent.setClass(ChartListActivity.this,
                                cn.limc.demo.activity.SlipBandAreaChartActivity.class);
                        startActivity(intent);
                        break;
                    case 5:
                        intent.setClass(ChartListActivity.this,
                                cn.limc.demo.activity.StickChartActivity.class);
                        startActivity(intent);
                        break;
                    case 6:
                        intent.setClass(ChartListActivity.this,
                                cn.limc.demo.activity.SlipStickChartActivity.class);
                        startActivity(intent);
                        break;
                    case 7:
                        intent.setClass(ChartListActivity.this,
                                cn.limc.demo.activity.ColoredSlipStickChartActivity.class);
                        startActivity(intent);
                        break;
                    case 8:
                        intent.setClass(ChartListActivity.this,
                                cn.limc.demo.activity.MAStickChartActivity.class);
                        startActivity(intent);
                        break;
                    case 9:
                        intent.setClass(ChartListActivity.this,
                                cn.limc.demo.activity.MASlipStickChartActivity.class);
                        startActivity(intent);
                        break;
                    case 10:
                        intent.setClass(ChartListActivity.this,
                                cn.limc.demo.activity.MinusStickChartActivity.class);
                        startActivity(intent);
                        break;
                    case 11:
                        intent.setClass(ChartListActivity.this,
                                cn.limc.demo.activity.SlipMinusStickChartActivity.class);
                        startActivity(intent);
                        break;
                    case 12:
                        intent.setClass(ChartListActivity.this,
                                cn.limc.demo.activity.CandleStickChartActivity.class);
                        startActivity(intent);
                        break;
                    case 13:
                        intent.setClass(ChartListActivity.this,
                                cn.limc.demo.activity.SlipCandleStickChartActivity.class);
                        startActivity(intent);
                        break;
                    case 14:
                        intent.setClass(ChartListActivity.this,
                                cn.limc.demo.activity.MACandleStickChartActivity.class);
                        startActivity(intent);
                        break;
                    case 15:
                        intent.setClass(ChartListActivity.this,
                                cn.limc.demo.activity.MASlipCandleStickChartActivity.class);
                        startActivity(intent);
                        break;
                    case 16:
                        intent.setClass(ChartListActivity.this,
                                cn.limc.demo.activity.BOLLMASlipCandleStickChartActivity.class);
                        startActivity(intent);
                        break;
                    case 17:
                        intent.setClass(ChartListActivity.this,
                                cn.limc.demo.activity.MACDChartActivity.class);
                        startActivity(intent);
                        break;
                    case 18:
                        intent.setClass(ChartListActivity.this,
                                cn.limc.demo.activity.PieChartActivity.class);
                        startActivity(intent);
                        break;
                    case 19:
                        intent.setClass(ChartListActivity.this,
                                cn.limc.demo.activity.RoseChartActivity.class);
                        startActivity(intent);
                        break;
                    case 20:
                        intent.setClass(ChartListActivity.this,
                                cn.limc.demo.activity.SpiderWebChartActivity.class);
                        startActivity(intent);
                        break;
                    case 21:
                        intent.setClass(ChartListActivity.this,
                                cn.limc.demo.activity.RadarChartActivity.class);
                        startActivity(intent);
                        break;
                    case 22:
                        intent.setClass(ChartListActivity.this,
                                cn.limc.demo.activity.DonutChartActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.chart_list, menu);
        return true;
    }

}
