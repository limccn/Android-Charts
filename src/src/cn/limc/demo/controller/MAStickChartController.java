/*
 * MAStickChartController.java
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
import cn.limc.androidcharts.series.ChartDataSet;


/**
 * StickChartController
 * Description: <br>
 *   <p>add description here </p>
 * Tags: <br>
 *   <p> </p>
 *
 * @author limc
 * @version v1.0 
 * 
 * History: <br>
 * 2015-8-19 limc create v1.0 <br>
 *
 */
public class MAStickChartController extends AbstractDiagramController {

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
    

    ChartDataSet stickData;
    ChartDataSet lineData;

    public MAStickChartController() {
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
                return stickData.getChartTable().size();
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
                    return stickData;
                }else{
                    return lineData;
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
     * @return the stickData
     */
    public ChartDataSet getStickData() {
        return stickData;
    }

    /**
     * @param stickData the stickData to set
     */
    public void setStickData(ChartDataSet stickData) {
        this.stickData = stickData;
    }
    
    /**
     * @return the lineData
     */
    public ChartDataSet getLineData() {
        return lineData;
    }

    /**
     * @param lineData the lineData to set
     */
    public void setLineData(ChartDataSet lineData) {
        this.lineData = lineData;
    }
}
