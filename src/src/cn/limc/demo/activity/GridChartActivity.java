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
import cn.limc.androidcharts.component.AreaChartComponent;
import cn.limc.androidcharts.component.Axis;
import cn.limc.androidcharts.component.HorizontalIndicator;
import cn.limc.androidcharts.component.Indicator;
import cn.limc.androidcharts.component.DataComponent;
import cn.limc.androidcharts.component.DividedLayer;
import cn.limc.androidcharts.component.HorizontalAxis;
import cn.limc.androidcharts.component.KChartComponent;
import cn.limc.androidcharts.component.Layer;
import cn.limc.androidcharts.component.LineChartComponent;
import cn.limc.androidcharts.component.SimpleGrid;
import cn.limc.androidcharts.component.StickChartComponent;
import cn.limc.androidcharts.component.VerticalAxis;
import cn.limc.androidcharts.component.VerticalIndicator;
import cn.limc.androidcharts.diagram.GridChart;
import cn.limc.androidcharts.model.DataCursor;
import cn.limc.androidcharts.model.DateTimeDegree;
import cn.limc.androidcharts.model.DecimalDegree;
import cn.limc.androidcharts.model.MeasuableRangeCalculator;
import cn.limc.androidcharts.model.SectionDataCursor;
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
        
        SimpleGrid grid= new SimpleGrid();
        gridchart.setDataGrid(grid);
        
        SimpleDataRange dataRange1 = new SimpleDataRange();
        dataRange1.setRangeCalculator(new MeasuableRangeCalculator());
        
        SimpleDataRange dataRange2 = new SimpleDataRange();
        dataRange2.setRangeCalculator(new MeasuableRangeCalculator());
        
        SimpleDataRange dataRange3 = new SimpleDataRange();
        dataRange3.setRangeCalculator(new MeasuableRangeCalculator());
        
        SimpleDataRange dataRange4 = new SimpleDataRange();
        dataRange4.setRangeCalculator(new MeasuableRangeCalculator());
        
        DataComponent kChartComponent = new StickChartComponent();
        kChartComponent.setChartData(new ChartDataSet(new ChartDataTable(vol)));
        kChartComponent.setDataCursor(new SectionDataCursor());
        kChartComponent.setDataGrid(grid);
        kChartComponent.setDataRange(dataRange1);
        
//        DataComponent kChartComponent = new KChartComponent();
//        kChartComponent.setChartData(new ChartDataSet(new ChartDataTable(ohlc)));
//        kChartComponent.setDataCursor(new SectionDataCursor());
//        kChartComponent.setDataGrid(grid);
//        kChartComponent.setDataRange(dataRange1);
        
        DataComponent lineDataComponent = new LineChartComponent();
        lineDataComponent.setChartData(lines);
        lineDataComponent.setDataCursor(new SectionDataCursor());
        lineDataComponent.setDataGrid(grid);
        lineDataComponent.setDataRange(dataRange2);
        
        DataComponent bandDataComponent = new LineChartComponent();
        bandDataComponent.setChartData(band);
        bandDataComponent.setDataCursor(new SectionDataCursor());
        bandDataComponent.setDataGrid(grid);
        bandDataComponent.setDataRange(dataRange3);
        
        DataComponent bandChartComponent = new AreaChartComponent();
        bandChartComponent.setChartData(band);
        bandChartComponent.setDataCursor(new SectionDataCursor());
        bandChartComponent.setDataGrid(grid);
        bandChartComponent.setDataRange(dataRange4);
        
        gridchart.addComponent(kChartComponent);
        gridchart.addComponent(lineDataComponent);
        gridchart.addComponent(bandDataComponent);     
        gridchart.addComponent(bandChartComponent);     
        
        HorizontalAxis axisX = new HorizontalAxis();
        axisX.setBindComponent(kChartComponent);
        axisX.setDegree(new DateTimeDegree());
        
        VerticalAxis axisY = new VerticalAxis();
        axisY.setBindComponent(kChartComponent);
        axisY.setDegree(new DecimalDegree());
        
        VerticalAxis axisYLine = new VerticalAxis();
        axisYLine.setBindComponent(lineDataComponent);
        axisYLine.setDegree(new DecimalDegree());
        
        HorizontalAxis axisXLine = new HorizontalAxis();
        axisXLine.setBindComponent(lineDataComponent);
        axisXLine.setDegree(new DateTimeDegree());
        
        VerticalIndicator vIndicator = new VerticalIndicator();
        vIndicator.setBindComponent(lineDataComponent);
        vIndicator.setBindToStyle(Indicator.BIND_TO_TYPE_NONE);
        
        HorizontalIndicator hIndicator = new HorizontalIndicator();
        hIndicator.setBindComponent(lineDataComponent);
        hIndicator.setBindToStyle(Indicator.BIND_TO_TYPE_NONE);
       
        gridchart.setBottomAxis(axisX);
        gridchart.setRightAxis(axisY);
        gridchart.setLeftAxis(axisYLine);
        gridchart.setTopAxis(axisXLine);
        gridchart.setVerticalIndicator(vIndicator);
        gridchart.setHorizontalIndicator(hIndicator);
        
    }

}
