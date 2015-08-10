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
import cn.limc.androidcharts.component.AbstractAxis;
import cn.limc.androidcharts.component.Axis;
import cn.limc.androidcharts.component.DataComponent;
import cn.limc.androidcharts.component.DividedLayer;
import cn.limc.androidcharts.component.HorizontalAxis;
import cn.limc.androidcharts.component.Layer;
import cn.limc.androidcharts.component.LineChartComponent;
import cn.limc.androidcharts.component.SimpleGrid;
import cn.limc.androidcharts.component.StickChartComponent;
import cn.limc.androidcharts.component.VerticalAxis;
import cn.limc.androidcharts.diagram.GridChart;
import cn.limc.androidcharts.model.DataCursor;
import cn.limc.androidcharts.model.DateTimeDegree;
import cn.limc.androidcharts.model.DecimalDegree;
import cn.limc.androidcharts.model.SectionDataCursor;
import cn.limc.androidcharts.model.MeasuableRangeCalculator;
import cn.limc.androidcharts.model.DataRange;
import cn.limc.androidcharts.model.SimpleDataRange;
import cn.limc.androidcharts.series.ChartDataSet;
import cn.limc.androidcharts.series.ChartDataTable;
import cn.limc.androidcharts.series.LineEntity;
import cn.limc.demo.common.BaseActivity;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.Menu;

public class GridChartActivity extends BaseActivity {
    GridChart gridchart;

    @Override
    public void onCreate(Bundle savedInstanceState) {
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

//        List<String> ytitle = new ArrayList<String>();
//        ytitle.add("241");
//        ytitle.add("242");
//        ytitle.add("243");
//        ytitle.add("244");
//        ytitle.add("245");
//        List<String> xtitle = new ArrayList<String>();
//        xtitle.add("9:00");
//        xtitle.add("10:00");
//        xtitle.add("11:00");
//        xtitle.add("13:00");
//        xtitle.add("14:00");
//        xtitle.add("15:00");

////        gridchart.setAxisXColor(Color.LTGRAY);
////        gridchart.setAxisYColor(Color.LTGRAY);
//        gridchart.setBorderColor(Color.LTGRAY);
//        gridchart.setLatitudeNum(5);
//        gridchart.setLongitudeNum(6);
//        gridchart.setDataQuadrantPaddingTop(5);
//        gridchart.setDataQuadrantPaddingBottom(5);
//        gridchart.setDataQuadrantPaddingLeft(5);
//        gridchart.setDataQuadrantPaddingRight(5);
////        gridchart.setAxisXPosition(AbstractAxis.AXIS_X_POSITION_BOTTOM);
////        gridchart.setAxisYPosition(AbstractAxis.AXIS_Y_POSITION_RIGHT);
//        gridchart.getSimpleGrid().setLatitudeTitles(ytitle);
//        gridchart.getSimpleGrid().setLongitudeTitles(xtitle);
//        gridchart.setLongitudeFontSize(14);
//        gridchart.setLatitudeFontSize(14);
//        gridchart.setLongitudeFontColor(Color.WHITE);
//        gridchart.setLatitudeColor(Color.GRAY);
//        gridchart.setLatitudeFontColor(Color.WHITE);
//        gridchart.setLongitudeColor(Color.GRAY);
//        gridchart.setDisplayLongitudeTitle(true);
//        gridchart.setDisplayLatitudeTitle(true);
//        gridchart.setDisplayLatitude(true);
//        gridchart.setDisplayLongitude(true);
//        gridchart.setCrossLinesColor(Color.BLUE);
//        gridchart.setCrossLinesFontColor(Color.GREEN);
//        gridchart.setBorderWidth(1);
        
     // 以下计算VOL
        ChartDataSet vlines = new ChartDataSet();

        // 计算5日均线
        LineEntity vma5 = new LineEntity();
        vma5.setTitle("MA5");
        vma5.setLineColor(Color.WHITE);
        vma5.setTableData(initVMA(5));
        vlines.add(vma5);

        // 计算10日均线
        LineEntity vma10 = new LineEntity();
        vma10.setTitle("MA10");
        vma10.setLineColor(Color.CYAN);
        vma10.setTableData(initVMA(10));
        vlines.add(vma10);

        
        // 计算25日均线
        LineEntity vma25 = new LineEntity();
        vma25.setTitle("MA25");
        vma25.setLineColor(Color.BLUE);
        vma25.setTableData(initVMA(25));
        vlines.add(vma25);
        
        SimpleGrid grid= new SimpleGrid();
        gridchart.setDataGrid(grid);
        
        SimpleDataRange dataRange = new SimpleDataRange();
        dataRange.setRangeCalculator(new MeasuableRangeCalculator());
  
        DataComponent stickDataComponent = new StickChartComponent();
        stickDataComponent.setChartData(new ChartDataSet(new ChartDataTable(vol)));
        stickDataComponent.setDataCursor(new SectionDataCursor());
        stickDataComponent.setDataGrid(grid);
        stickDataComponent.setDataRange(dataRange);
        
        DataComponent lineDataComponent = new LineChartComponent();
        lineDataComponent.setChartData(vlines);
        lineDataComponent.setDataCursor(new SectionDataCursor());
        lineDataComponent.setDataGrid(grid);
        lineDataComponent.setDataRange(dataRange);
        
        gridchart.addComponent(stickDataComponent);
        gridchart.addComponent(lineDataComponent);
        
        HorizontalAxis axisX = new HorizontalAxis();
        axisX.setBindComponent(stickDataComponent);
        axisX.setDegree(new DateTimeDegree());
        
        VerticalAxis axisY = new VerticalAxis();
        axisY.setBindComponent(stickDataComponent);
        axisY.setDegree(new DecimalDegree());
        
        VerticalAxis axisYLine = new VerticalAxis();
        axisYLine.setBindComponent(lineDataComponent);
        axisYLine.setDegree(new DecimalDegree());
       
        gridchart.setBottomAxis(axisX);
        gridchart.setRightAxis(axisY);
        gridchart.setLeftAxis(axisYLine);
    }

}
