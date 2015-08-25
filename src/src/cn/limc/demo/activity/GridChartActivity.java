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

import cn.limc.androidcharts.R;
import cn.limc.androidcharts.component.AreaChartComponent;
import cn.limc.androidcharts.component.Axis;
import cn.limc.androidcharts.component.Grid;
import cn.limc.androidcharts.component.HorizontalIndicator;
import cn.limc.androidcharts.component.Indicator;
import cn.limc.androidcharts.component.DataComponent;
import cn.limc.androidcharts.component.HorizontalAxis;
import cn.limc.androidcharts.component.KChartComponent;
import cn.limc.androidcharts.component.LineChartComponent;
import cn.limc.androidcharts.component.SimpleGrid;
import cn.limc.androidcharts.component.VerticalAxis;
import cn.limc.androidcharts.component.VerticalIndicator;
import cn.limc.androidcharts.diagram.GridChart;
import cn.limc.androidcharts.handler.ComponentGroupHandler;
import cn.limc.androidcharts.model.DataCursor;
import cn.limc.androidcharts.model.DateTimeDegree;
import cn.limc.androidcharts.model.DecimalDegree;
import cn.limc.androidcharts.model.Degree;
import cn.limc.androidcharts.model.MeasuableRangeCalculator;
import cn.limc.androidcharts.model.RangeCalculator;
import cn.limc.androidcharts.model.SectionDataCursor;
import cn.limc.androidcharts.model.SimpleDataRange;
import cn.limc.androidcharts.series.ChartDataSet;
import cn.limc.androidcharts.series.ChartDataTable;
import cn.limc.androidcharts.series.LineEntity;
import cn.limc.demo.common.BaseActivity;
import android.os.Bundle;
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
        
        final ChartDataSet lines = new ChartDataSet();
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

        final ChartDataSet band = new ChartDataSet();
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
        
        final SimpleDataRange dataRange;
        final SectionDataCursor dataCursor;
        final SimpleGrid grid;
        final DataComponent stickChartComponent;
        final DataComponent lineChartComponent;
        final DataComponent bandChartComponent;
        
        final HorizontalAxis axisX;
        final VerticalAxis axisY;
        final HorizontalAxis axisXLine;
        final VerticalAxis axisYLine;
             
        dataCursor = new SectionDataCursor() {
            @Override
            public int dataSizeForCursor(DataCursor dataCursor) {
                return vol.size();
            }
        };
        
        stickChartComponent = new KChartComponent();
        lineChartComponent = new LineChartComponent();
        bandChartComponent = new AreaChartComponent();

        axisX = new HorizontalAxis() {
            
            @Override
            public Degree degreeForAxis(Axis axis) {
                return new DateTimeDegree();
            }
            
            @Override
            public DataComponent componentForAxis(Axis axis) {
                return stickChartComponent;
            }
        };
        
        axisY = new VerticalAxis() {
            
            @Override
            public Degree degreeForAxis(Axis axis) {
                return new DecimalDegree();
            }
            
            @Override
            public DataComponent componentForAxis(Axis axis) {
                return stickChartComponent;
            }
        };
        
        axisXLine = new HorizontalAxis() {
            
            @Override
            public Degree degreeForAxis(Axis axis) {
                return new DateTimeDegree();
            }
            
            @Override
            public DataComponent componentForAxis(Axis axis) {
                return lineChartComponent;
            }
        };
      
        axisYLine = new VerticalAxis() {
            
            @Override
            public Degree degreeForAxis(Axis axis) {
                return new DecimalDegree();
            }
            
            @Override
            public DataComponent componentForAxis(Axis axis) {
                return lineChartComponent;
            }
        };
        
        grid= new SimpleGrid() {
            
            @Override
            public VerticalAxis verticalAxisForGrid(Grid grid) {
                return axisY;
            }
            
            @Override
            public HorizontalAxis horizontalAxisForGrid(Grid grid) {
                return axisX;
            }
        };
        
        VerticalIndicator vIndicator = new VerticalIndicator() {
            
            @Override
            public DataCursor dataCursorForIndicator(Indicator indicator) {
                return dataCursor;
            }

            @Override
            public DataComponent componentForIndicator(Indicator indicator) {
                return stickChartComponent;
            }
            
        };
        
        vIndicator.setBindToStyle(Indicator.BIND_TO_TYPE_NONE);

        
        HorizontalIndicator hIndicator = new HorizontalIndicator() {
            
            @Override
            public DataCursor dataCursorForIndicator(Indicator indicator) {
                return dataCursor;
            }
            
            @Override
            public DataComponent componentForIndicator(Indicator indicator) {
                return stickChartComponent;
            }
        };
        
        hIndicator.setBindToStyle(Indicator.BIND_TO_TYPE_NONE);
        
        dataRange = new SimpleDataRange();
        dataRange.setRangeCalculator(new MeasuableRangeCalculator() {
            
            @Override
            public int startCalcPost(RangeCalculator calc) {
                return dataCursor.getDisplayFrom();
            }
            
            @Override
            public int endCalcPost(RangeCalculator calc) {
                // TODO Auto-generated method stub
                return dataCursor.getDisplayTo();
            }
            
            @Override
            public int rangeDivide(RangeCalculator calc) {
                return axisY.titlesNum();
            }
        });
       
        ComponentGroupHandler groupController = new ComponentGroupHandler() {
            /* (non-Javadoc)
             * @see cn.limc.androidcharts.component.DataComponent.DataComponentDataSource#dataForComponent(cn.limc.androidcharts.component.DataComponent)
             */
            @Override
            public ChartDataSet dataForComponent(DataComponent component) {
                if (component == stickChartComponent) {
                    return new ChartDataSet(new ChartDataTable(ohlc));
                }else if(component == lineChartComponent){
                    return lines;
                }else if(component == bandChartComponent){
                    return band;
                }else{
                    return null;
                }
            }
        };
        groupController.setDataGrid(grid);
        groupController.setDataRange(dataRange);
        groupController.setDataCursor(dataCursor);
        groupController.addComponent(bandChartComponent);
        groupController.addComponent(lineChartComponent);
        groupController.addComponent(stickChartComponent);

        
        gridchart.addController(groupController);
        
        gridchart.setDataGrid(grid);
        gridchart.setBottomAxis(axisX);
        gridchart.setRightAxis(axisY);
        gridchart.setLeftAxis(axisYLine);
        gridchart.setTopAxis(axisXLine);
        gridchart.setVerticalIndicator(vIndicator);
        gridchart.setHorizontalIndicator(hIndicator);
        
    }

}
