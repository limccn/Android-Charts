/*
 * MACDChartController.java
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
package cn.limc.demo.controller;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;

import cn.limc.androidcharts.component.Axis;
import cn.limc.androidcharts.component.DataComponent;
import cn.limc.androidcharts.component.Grid;
import cn.limc.androidcharts.component.HorizontalAxis;
import cn.limc.androidcharts.component.HorizontalIndicator;
import cn.limc.androidcharts.component.Indicator;
import cn.limc.androidcharts.component.LineChartComponent;
import cn.limc.androidcharts.component.SimpleGrid;
import cn.limc.androidcharts.component.StickChartComponent;
import cn.limc.androidcharts.component.VerticalAxis;
import cn.limc.androidcharts.component.VerticalIndicator;
import cn.limc.androidcharts.controller.AbstractDiagramController;
import cn.limc.androidcharts.handler.ComponentGroupHandler;
import cn.limc.androidcharts.model.DataCursor;
import cn.limc.androidcharts.model.DateTimeDegree;
import cn.limc.androidcharts.model.DecimalDegree;
import cn.limc.androidcharts.model.Degree;
import cn.limc.androidcharts.model.MeasuableRangeCalculator;
import cn.limc.androidcharts.model.RangeCalculator;
import cn.limc.androidcharts.model.SectionDataCursor;
import cn.limc.androidcharts.model.SimpleDataRange;
import cn.limc.androidcharts.series.ChartDataAdapter;
import cn.limc.androidcharts.series.ChartDataRow;
import cn.limc.androidcharts.series.ChartDataSet;
import cn.limc.androidcharts.series.ChartDataTable;
import cn.limc.androidcharts.series.DateValueEntity;
import cn.limc.androidcharts.series.LineEntity;
import cn.limc.androidcharts.series.MACDEntity;
import cn.limc.androidcharts.series.StickEntity;

/**
 * MACDChartController
 * Description: <br>
 *   <p>add description here </p>
 * Tags: <br>
 *   <p> </p>
 *
 * @author limc
 * @version v1.0 
 * 
 * History: <br>
 * 2015-8-25 limc create v1.0 <br>
 *
 */
public class MACDChartController extends AbstractDiagramController {

    SimpleDataRange dataRange;
    SectionDataCursor dataCursor;
    SimpleGrid grid;
    DataComponent stickChartComponent;
    DataComponent lineChartComponent;

    HorizontalAxis axisBottom;
    VerticalAxis axisRight;
    HorizontalAxis axisTop;
    VerticalAxis axisLeft;
    VerticalIndicator vIndicator;
    HorizontalIndicator hIndicator;
    ComponentGroupHandler componetController;
    
    ChartDataSet macdData;
    MACDAdapter macdAdapter = new MACDAdapter();
    LinesAdapter lineAdapter = new LinesAdapter();
    
    
    public MACDChartController() {
        super();
        this.initController();
    }

    protected void initAxis() {
        axisBottom = new HorizontalAxis() {

            @Override
            public Degree degreeForAxis(Axis axis) {
                return new DateTimeDegree();
            }

            @Override
            public DataComponent componentForAxis(Axis axis) {
                return stickChartComponent;
            }
        };

        axisRight = new VerticalAxis() {

            @Override
            public Degree degreeForAxis(Axis axis) {
                return new DecimalDegree();
            }

            @Override
            public DataComponent componentForAxis(Axis axis) {
                return stickChartComponent;
            }
        };

        axisTop = new HorizontalAxis() {

            @Override
            public Degree degreeForAxis(Axis axis) {
                return new DateTimeDegree();
            }

            @Override
            public DataComponent componentForAxis(Axis axis) {
                return lineChartComponent;
            }
        };

        axisLeft = new VerticalAxis() {

            @Override
            public Degree degreeForAxis(Axis axis) {
                return new DecimalDegree();
            }

            @Override
            public DataComponent componentForAxis(Axis axis) {
                return lineChartComponent;
            }
        };
    }

    protected void initIndicator() {

        vIndicator = new VerticalIndicator() {

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

        hIndicator = new HorizontalIndicator() {

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
    }

    protected void initDataCursor() {
        dataCursor = new SectionDataCursor() {
            @Override
            public int dataSizeForCursor(DataCursor dataCursor) {
                return macdData.getChartTable().size();
            }
        };

    }


    protected void initDataComponent() {
        stickChartComponent = new StickChartComponent();
        lineChartComponent = new LineChartComponent();
    }

    protected void initGrid() {
        grid = new SimpleGrid() {

            @Override
            public VerticalAxis verticalAxisForGrid(Grid grid) {
                return axisRight;
            }

            @Override
            public HorizontalAxis horizontalAxisForGrid(Grid grid) {
                return axisBottom;
            }
        };
    }

    protected void initDataRange() {
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
                return axisRight.titlesNum();
            }
        });
    }

    public void initComponentController() {
        componetController = new ComponentGroupHandler() {
            @Override
            public ChartDataSet dataForComponent(DataComponent component) {
                if (component instanceof StickChartComponent) {
                    return macdAdapter;
                }else{
                    return lineAdapter;
                }
            }
        };
    }

    protected void initController() {

        this.initDataComponent();
        this.initDataCursor();
        this.initAxis();
        this.initGrid();
        this.initIndicator();
        this.initDataRange();
        this.initComponentController();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.limc.androidcharts.controller.DiagramController#didApplyController()
     */
    @Override
    public void didApplyController() {
        componetController.setDataGrid(grid);
        componetController.setDataRange(dataRange);
        componetController.setDataCursor(dataCursor);
        componetController.addComponent(stickChartComponent);
        componetController.addComponent(lineChartComponent);
 
        gridChart.addController(componetController);
        gridChart.setDataGrid(grid);
        gridChart.setBottomAxis(axisBottom);
        gridChart.setRightAxis(axisRight);
        gridChart.setLeftAxis(axisLeft);
        gridChart.setTopAxis(axisTop);
        gridChart.setVerticalIndicator(vIndicator);
        gridChart.setHorizontalIndicator(hIndicator);
    }

    /**
     * @return the macdData
     */
    public ChartDataSet getMacdData() {
        return macdData;
    }

    /**
     * @param macdData the macdData to set
     */
    public void setMacdData(ChartDataSet macdData) {
        this.macdData = macdData;
        macdAdapter.fill(macdData);
        lineAdapter.fill(macdData);
    }
    
    public class MACDAdapter extends ChartDataSet implements ChartDataAdapter{

        /* (non-Javadoc)
         * @see cn.limc.androidcharts.series.ChartDataAdapter#Fill(cn.limc.androidcharts.series.ChartDataSet)
         */
        @Override
        public void fill(ChartDataSet dataSet) {
            if (dataSet != null) {
                List<ChartDataRow> sticks = new ArrayList<ChartDataRow>();
                for (ChartDataRow row: dataSet.getChartTable().getTableData()) {
                    StickEntity stickEntity = new StickEntity();
                    MACDEntity macd = (MACDEntity)row;
                    if (macd.getMacd()>0) {
                        stickEntity.setHigh(macd.getMacd());
                        stickEntity.setLow(0);
                    }else{
                        stickEntity.setHigh(0);
                        stickEntity.setLow(macd.getMacd());
                    }
                    stickEntity.setDate(macd.getDate());
                    sticks.add(stickEntity);
                }
                add(new ChartDataTable(sticks));
            }
            
        }
    }
    
    public class LinesAdapter extends ChartDataSet implements ChartDataAdapter{
        /* (non-Javadoc)
         * @see cn.limc.androidcharts.series.ChartDataAdapter#Fill(cn.limc.androidcharts.series.ChartDataSet)
         */
        @Override
        public void fill(ChartDataSet dataSet) {
            if (dataSet != null) {
                List<ChartDataRow> diff = new ArrayList<ChartDataRow>();
                List<ChartDataRow> dea = new ArrayList<ChartDataRow>();
                for (ChartDataRow row: dataSet.getChartTable().getTableData()) {
                    MACDEntity macd = (MACDEntity)row;
                    DateValueEntity diffEntity = new DateValueEntity(macd.getDiff(),macd.getDate());
                    DateValueEntity deaEntity = new DateValueEntity(macd.getDea(),macd.getDate());
                    diff.add(diffEntity);
                    dea.add(deaEntity);
                }
            
                LineEntity diffLine = new LineEntity(diff);
                diffLine.setLineColor(Color.RED);
                LineEntity deaLine = new LineEntity(dea);
                deaLine.setLineColor(Color.BLUE);
                add(diffLine);
                add(deaLine);
            }
        }
    }
}
