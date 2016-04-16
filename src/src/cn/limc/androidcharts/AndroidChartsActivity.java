/*
 * AndroidChartsActivity.java
 * Android-Charts
 *
 * Created by limc on 2011/05/29.
 *
 * Copyright 2011 limc.cn All rights reserved.
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

package cn.limc.androidcharts;

import java.util.ArrayList;
import java.util.List;

import cn.limc.androidcharts.axis.Axis;
import cn.limc.androidcharts.common.IDataCursor;
import cn.limc.androidcharts.entity.ColoredStickEntity;
import cn.limc.androidcharts.entity.DateValueEntity;
import cn.limc.androidcharts.entity.IChartData;
import cn.limc.androidcharts.entity.IStickEntity;
import cn.limc.androidcharts.entity.LineEntity;
import cn.limc.androidcharts.entity.ListChartData;
import cn.limc.androidcharts.entity.MACDEntity;
import cn.limc.androidcharts.entity.OHLCEntity;
import cn.limc.androidcharts.entity.StickEntity;
import cn.limc.androidcharts.entity.TitleValueColorEntity;
import cn.limc.androidcharts.entity.TitleValueEntity;
import cn.limc.androidcharts.event.ITouchable;
import cn.limc.androidcharts.event.IZoomable;
import cn.limc.androidcharts.event.IDisplayCursorListener;
import cn.limc.androidcharts.event.OnTouchGestureListener;
import cn.limc.androidcharts.view.BOLLMASlipCandleStickChart;
import cn.limc.androidcharts.view.CandleStickChart;
import cn.limc.androidcharts.view.ColoredSlipStickChart;
import cn.limc.androidcharts.view.GridChart;
import cn.limc.androidcharts.view.LineChart;
import cn.limc.androidcharts.view.MACDChart;
import cn.limc.androidcharts.view.MACandleStickChart;
import cn.limc.androidcharts.view.MASlipCandleStickChart;
import cn.limc.androidcharts.view.MASlipStickChart;
import cn.limc.androidcharts.view.MAStickChart;
import cn.limc.androidcharts.view.MinusStickChart;
import cn.limc.androidcharts.view.PieChart;
import cn.limc.androidcharts.view.RadarChart;
import cn.limc.androidcharts.view.RoseChart;
import cn.limc.androidcharts.view.SlipAreaChart;
import cn.limc.androidcharts.view.SlipBandAreaChart;
import cn.limc.androidcharts.view.SlipCandleStickChart;
import cn.limc.androidcharts.view.SlipLineChart;
import cn.limc.androidcharts.view.SlipMinusStickChart;
import cn.limc.androidcharts.view.SlipStickChart;
import cn.limc.androidcharts.view.SpiderWebChart;
import cn.limc.androidcharts.view.StickChart;

import cn.limc.androidcharts.R;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.MotionEvent;

public class AndroidChartsActivity extends Activity {

	List<IStickEntity> ohlc;
	List<IStickEntity> vol;
	List<IStickEntity> volc;
	List<DateValueEntity> dv1;
	List<DateValueEntity> dv2;
	List<IStickEntity> macd;

	GridChart gridchart;
	LineChart linechart;
	SlipLineChart sliplinechart;
	SlipAreaChart slipareachart;
	SlipBandAreaChart slipbandchart;
	StickChart stickchart;
	SlipStickChart slipstickchart;
	ColoredSlipStickChart coloredslipstickchart;
	MAStickChart mastickchart;
	MASlipStickChart maslipstickchart;
	MinusStickChart minusstickchart;
	SlipMinusStickChart slipminusstickchart;
	CandleStickChart candlestickchart;
	SlipCandleStickChart slipcandlestickchart;
	MACandleStickChart macandlestickchart;
	MASlipCandleStickChart maslipcandlestickchart;
	BOLLMASlipCandleStickChart bollmaslipcandlestickchart;
	PieChart piechart;
	RoseChart rosechart;
	SpiderWebChart spiderwebchart;
	RadarChart radarchart;
	MACDChart macdChart;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		initVOL();
		initOHLC();
		initVOLC();
		initDV1();
		initDV2();
		initMACD();

		initGridChart();
		initLineChart();
		initSlipLineChart();
		initSlipAreaChart();
		initSlipBandChart();
//		initStickChart();
		initSlipStickChart();
		initColoredSlipStickChart();
//		initMAStickChart();
		initMASlipStickChart();
		initMinusStickChart();
		initSlipMinusStickChart();
//		initCandleStickChart();
		initSlipCandleStickChart();
//		initMACandleStickChart();
		initMASlipCandleStickChart();
		initBOLLMASlipCandleStickChart();
		initPieChart();
		initRoseChart();
		initSpiderWebChart();
		initRadarChart();
		initMACDChart();
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
//		gridchart.setAxisYTitleQuadrantWidth(50);
//		gridchart.setAxisXTitleQuadrantHeight(20);
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
		//gridchart.setAxisWidth(1);
	}

	private void initLineChart() {
		this.linechart = (LineChart) findViewById(R.id.linechart);
		List<LineEntity<DateValueEntity>> lines = new ArrayList<LineEntity<DateValueEntity>>();

		// 计算5日均线
		LineEntity<DateValueEntity> MA5 = new LineEntity<DateValueEntity>();
		MA5.setTitle("MA5");
		MA5.setLineColor(Color.WHITE);
		MA5.setLineData(initMA(5));
		lines.add(MA5);

		// 计算10日均线
		LineEntity<DateValueEntity> MA10 = new LineEntity<DateValueEntity>();
		MA10.setTitle("MA10");
		MA10.setLineColor(Color.RED);
		MA10.setLineData(initMA(10));
		lines.add(MA10);

		// 计算25日均线
		LineEntity<DateValueEntity> MA25 = new LineEntity<DateValueEntity>();
		MA25.setTitle("MA25");
		MA25.setLineColor(Color.GREEN);
		MA25.setLineData(initMA(25));
		lines.add(MA25);

		//linechart.setAxisXColor(Color.LTGRAY);
		//linechart.setAxisYColor(Color.LTGRAY);
		linechart.setBorderColor(Color.LTGRAY);
		//linechart.setLongitudeFontSize(14);
		//linechart.setLongitudeFontColor(Color.WHITE);
		//linechart.setLatitudeColor(Color.GRAY);
		//linechart.setLatitudeFontColor(Color.WHITE);
		//linechart.setLongitudeColor(Color.GRAY);
		linechart.setMaxValue(280);
		linechart.setMinValue(240);
		linechart.setMaxPointNum(36);
		//linechart.setDisplayLongitudeTitle(true);
		//linechart.setDisplayLatitudeTitle(true);
		//linechart.setDisplayLatitude(true);
		//linechart.setDisplayLongitude(true);
		//linechart.setLatitudeNum(5);
		//linechart.setLongitudeNum(6);
		linechart.setDataQuadrantPaddingTop(5);
		linechart.setDataQuadrantPaddingBottom(5);
		linechart.setDataQuadrantPaddingLeft(5);
		linechart.setDataQuadrantPaddingRight(5);
		//linechart.setAxisYTitleQuadrantWidth(50);
		//linechart.setAxisXTitleQuadrantHeight(20);
		//linechart.setAxisXPosition(GridChart.AXIS_X_POSITION_BOTTOM);
		//linechart.setAxisYPosition(GridChart.AXIS_Y_POSITION_RIGHT);

		// 为chart1增加均线
		linechart.setLinesData(lines);
	}

	private void initSlipLineChart() {
		this.sliplinechart = (SlipLineChart) findViewById(R.id.sliplinechart);
		List<LineEntity<DateValueEntity>> lines = new ArrayList<LineEntity<DateValueEntity>>();

		// 计算5日均线
		LineEntity<DateValueEntity> MA5 = new LineEntity<DateValueEntity>();
		MA5.setTitle("HIGH");
		MA5.setLineColor(Color.WHITE);
		MA5.setLineData(dv1);
		lines.add(MA5);

		// 计算10日均线
		LineEntity<DateValueEntity> MA10 = new LineEntity<DateValueEntity>();
		MA10.setTitle("LOW");
		MA10.setLineColor(Color.RED);
		MA10.setLineData(dv2);
		lines.add(MA10);

		//sliplinechart.setAxisXColor(Color.LTGRAY);
		//sliplinechart.setAxisYColor(Color.LTGRAY);
		sliplinechart.setBorderColor(Color.LTGRAY);
		//sliplinechart.setLongitudeFontSize(14);
		//sliplinechart.setLongitudeFontColor(Color.WHITE);
		//sliplinechart.setLatitudeColor(Color.GRAY);
		//sliplinechart.setLatitudeFontColor(Color.WHITE);
		//sliplinechart.setLongitudeColor(Color.GRAY);
		sliplinechart.setMaxValue(1300);
		sliplinechart.setMinValue(700);
		sliplinechart.setDisplayFrom(10);
		sliplinechart.setDisplayNumber(30);
		sliplinechart.setMinDisplayNumber(5);
		sliplinechart.setZoomBaseLine(IZoomable.ZOOM_BASE_LINE_CENTER);
		//sliplinechart.setDisplayLongitudeTitle(true);
		//sliplinechart.setDisplayLatitudeTitle(true);
		//sliplinechart.setDisplayLatitude(true);
		//sliplinechart.setDisplayLongitude(true);
		sliplinechart.setDataQuadrantPaddingTop(5);
		sliplinechart.setDataQuadrantPaddingBottom(5);
		sliplinechart.setDataQuadrantPaddingLeft(5);
		sliplinechart.setDataQuadrantPaddingRight(5);
		//sliplinechart.setAxisYTitleQuadrantWidth(50);
		//sliplinechart.setAxisXTitleQuadrantHeight(20);
//		sliplinechart.setAxisXPosition(GridChart.AXIS_X_POSITION_BOTTOM);
//		sliplinechart.setAxisYPosition(GridChart.AXIS_Y_POSITION_RIGHT);

		sliplinechart.setLinesData(lines);
	}

	private void initSlipAreaChart() {
		this.slipareachart = (SlipAreaChart) findViewById(R.id.slipareachart);
		List<LineEntity<DateValueEntity>> lines = new ArrayList<LineEntity<DateValueEntity>>();

		// 计算5日均线
		LineEntity<DateValueEntity> MA5 = new LineEntity<DateValueEntity>();
		MA5.setTitle("HIGH");
		MA5.setLineColor(Color.WHITE);
		MA5.setLineData(dv1);
		lines.add(MA5);

		// 计算10日均线
		LineEntity<DateValueEntity> MA10 = new LineEntity<DateValueEntity>();
		MA10.setTitle("LOW");
		MA10.setLineColor(Color.RED);
		MA10.setLineData(dv2);
		lines.add(MA10);

		//slipareachart.setAxisXColor(Color.LTGRAY);
		//slipareachart.setAxisYColor(Color.LTGRAY);
		slipareachart.setBorderColor(Color.LTGRAY);
		//slipareachart.setLongitudeFontSize(14);
		//slipareachart.setLongitudeFontColor(Color.WHITE);
		//slipareachart.setLatitudeColor(Color.GRAY);
		//slipareachart.setLatitudeFontColor(Color.WHITE);
		//slipareachart.setLongitudeColor(Color.GRAY);
		slipareachart.setMaxValue(1300);
		slipareachart.setMinValue(700);
		slipareachart.setDisplayFrom(10);
		slipareachart.setDisplayNumber(30);
		slipareachart.setMinDisplayNumber(5);
		slipareachart.setZoomBaseLine(IZoomable.ZOOM_BASE_LINE_CENTER);
		//slipareachart.setDisplayLongitudeTitle(true);
		//slipareachart.setDisplayLatitudeTitle(true);
		//slipareachart.setDisplayLatitude(true);
		//slipareachart.setDisplayLongitude(true);
		slipareachart.setDataQuadrantPaddingTop(5);
		slipareachart.setDataQuadrantPaddingBottom(5);
		slipareachart.setDataQuadrantPaddingLeft(5);
		slipareachart.setDataQuadrantPaddingRight(5);
		//slipareachart.setAxisYTitleQuadrantWidth(50);
		//slipareachart.setAxisXTitleQuadrantHeight(20);
		//slipareachart.setAxisXPosition(GridChart.AXIS_X_POSITION_BOTTOM);
		//slipareachart.setAxisYPosition(GridChart.AXIS_Y_POSITION_RIGHT);

		slipareachart.setLinesData(lines);
	}

	private void initSlipBandChart() {
		this.slipbandchart = (SlipBandAreaChart) findViewById(R.id.slipbandchart);
		List<LineEntity<DateValueEntity>> lines = new ArrayList<LineEntity<DateValueEntity>>();

		// 计算5日均线
		LineEntity<DateValueEntity> MA5 = new LineEntity<DateValueEntity>();
		MA5.setTitle("HIGH");
		MA5.setLineColor(Color.WHITE);
		MA5.setLineData(dv1);
		lines.add(MA5);

		// 计算10日均线
		LineEntity<DateValueEntity> MA10 = new LineEntity<DateValueEntity>();
		MA10.setTitle("LOW");
		MA10.setLineColor(Color.RED);
		MA10.setLineData(dv2);
		lines.add(MA10);

		//slipbandchart.setAxisXColor(Color.LTGRAY);
		//slipbandchart.setAxisYColor(Color.LTGRAY);
		slipbandchart.setBorderColor(Color.LTGRAY);
		//slipbandchart.setLongitudeFontSize(14);
		//slipbandchart.setLongitudeFontColor(Color.WHITE);
		//slipbandchart.setLatitudeColor(Color.GRAY);
		//slipbandchart.setLatitudeFontColor(Color.WHITE);
		//slipbandchart.setLongitudeColor(Color.GRAY);
		slipbandchart.setMaxValue(1300);
		slipbandchart.setMinValue(700);
		slipbandchart.setDisplayFrom(10);
		slipbandchart.setDisplayNumber(30);
		slipbandchart.setMinDisplayNumber(5);
		slipbandchart.setZoomBaseLine(IZoomable.ZOOM_BASE_LINE_CENTER);
		//slipbandchart.setDisplayLongitudeTitle(true);
		//slipbandchart.setDisplayLatitudeTitle(true);
		//slipbandchart.setDisplayLatitude(true);
		//slipbandchart.setDisplayLongitude(true);
		slipbandchart.setDataQuadrantPaddingTop(5);
		slipbandchart.setDataQuadrantPaddingBottom(5);
		slipbandchart.setDataQuadrantPaddingLeft(5);
		slipbandchart.setDataQuadrantPaddingRight(5);
		//slipbandchart.setAxisYTitleQuadrantWidth(50);
		//slipbandchart.setAxisXTitleQuadrantHeight(20);
		//slipbandchart.setAxisXPosition(GridChart.AXIS_X_POSITION_BOTTOM);
		//slipbandchart.setAxisYPosition(GridChart.AXIS_Y_POSITION_RIGHT);

		slipbandchart.setLinesData(lines);
	}

	private void initStickChart() {
		this.stickchart = (StickChart) findViewById(R.id.stickchart);

//		stickchart.setAxisXColor(Color.LTGRAY);
//		stickchart.setAxisYColor(Color.LTGRAY);
//		stickchart.setLatitudeColor(Color.GRAY);
//		stickchart.setLongitudeColor(Color.GRAY);
		stickchart.setBorderColor(Color.LTGRAY);
//		stickchart.setLongitudeFontColor(Color.WHITE);
//		stickchart.setLatitudeFontColor(Color.WHITE);
		stickchart.setDataQuadrantPaddingTop(6);
		stickchart.setDataQuadrantPaddingBottom(1);
		stickchart.setDataQuadrantPaddingLeft(1);
		stickchart.setDataQuadrantPaddingRight(1);
//		stickchart.setAxisYTitleQuadrantWidth(50);s
//		stickchart.setAxisXTitleQuadrantHeight(20);
//		stickchart.setAxisXPosition(GridChart.AXIS_X_POSITION_BOTTOM);
//		stickchart.setAxisYPosition(GridChart.AXIS_Y_POSITION_RIGHT);

		// 最大显示足数
//		stickchart.setMaxSticksNum(52);
		// 最大纬线数
//		stickchart.setLatitudeNum(2);
		// 最大经线数
//		stickchart.setLongitudeNum(3);
		// 最大价格
		stickchart.setMaxValue(10000);
		// 最小价格
		stickchart.setMinValue(100);

//		stickchart.setDisplayLongitudeTitle(true);
//		stickchart.setDisplayLatitudeTitle(true);
//		stickchart.setDisplayLatitude(true);
//		stickchart.setDisplayLongitude(true);
		stickchart.setBackgroundColor(Color.BLACK);

		stickchart.setDataMultiple(100);
		stickchart.setAxisYDecimalFormat("#,##0.00");
		stickchart.setAxisXDateTargetFormat("yyyy/MM/dd");
		stickchart.setAxisXDateSourceFormat("yyyyMMdd");
		

		// 为chart1增加均线
		stickchart.setStickData(new ListChartData<IStickEntity>(vol));
	}

	private void initSlipStickChart() {
		this.slipstickchart = (SlipStickChart) findViewById(R.id.slipstickchart);

//		slipstickchart.setAxisXColor(Color.LTGRAY);
//		slipstickchart.setAxisYColor(Color.LTGRAY);
//		slipstickchart.setLatitudeColor(Color.GRAY);
//		slipstickchart.setLongitudeColor(Color.GRAY);
		slipstickchart.setBorderColor(Color.LTGRAY);
//		slipstickchart.setLongitudeFontColor(Color.WHITE);
//		slipstickchart.setLatitudeFontColor(Color.WHITE);
		slipstickchart.setDataQuadrantPaddingTop(6);
		slipstickchart.setDataQuadrantPaddingBottom(1);
		slipstickchart.setDataQuadrantPaddingLeft(1);
		slipstickchart.setDataQuadrantPaddingRight(1);
//		slipstickchart.setAxisYTitleQuadrantWidth(50);
//		slipstickchart.setAxisXTitleQuadrantHeight(20);
//		slipstickchart.setAxisXPosition(GridChart.AXIS_X_POSITION_BOTTOM);
//		slipstickchart.setAxisYPosition(GridChart.AXIS_Y_POSITION_RIGHT);

//		// 最大纬线数
//		slipstickchart.setLatitudeNum(2);
//		// 最大经线数
//		slipstickchart.setLongitudeNum(3);
		// 最大价格
		slipstickchart.setMaxValue(600000);
		// 最小价格
		slipstickchart.setMinValue(100);

		slipstickchart.setDisplayFrom(10);

		slipstickchart.setDisplayNumber(30);

		slipstickchart.setMinDisplayNumber(5);

		slipstickchart.setZoomBaseLine(IZoomable.ZOOM_BASE_LINE_CENTER);

//		slipstickchart.setDisplayLongitudeTitle(true);
//		slipstickchart.setDisplayLatitudeTitle(true);
//		slipstickchart.setDisplayLatitude(true);
//		slipstickchart.setDisplayLongitude(true);
		slipstickchart.setBackgroundColor(Color.BLACK);

		slipstickchart.setDataMultiple(100);
		slipstickchart.setAxisYDecimalFormat("#,##0.00");
		slipstickchart.setAxisXDateTargetFormat("yyyy/MM/dd");
		slipstickchart.setAxisXDateSourceFormat("yyyyMMdd");

		IChartData<IStickEntity> vol = new ListChartData<IStickEntity>(this.vol);
		// 为chart1增加均线
		slipstickchart.setStickData(vol);
	}

	private void initColoredSlipStickChart() {
		this.coloredslipstickchart = (ColoredSlipStickChart) findViewById(R.id.coloredslipstickchart);

//		coloredslipstickchart.setAxisXColor(Color.LTGRAY);
//		coloredslipstickchart.setAxisYColor(Color.LTGRAY);
//		coloredslipstickchart.setLatitudeColor(Color.GRAY);
//		coloredslipstickchart.setLongitudeColor(Color.GRAY);
		coloredslipstickchart.setBorderColor(Color.LTGRAY);
//		coloredslipstickchart.setLongitudeFontColor(Color.WHITE);
//		coloredslipstickchart.setLatitudeFontColor(Color.WHITE);

//		// 最大纬线数
//		coloredslipstickchart.setLatitudeNum(2);
//		// 最大经线数
//		coloredslipstickchart.setLongitudeNum(3);
		// 最大价格
		coloredslipstickchart.setMaxValue(600000);
		// 最小价格
		coloredslipstickchart.setMinValue(100);

		coloredslipstickchart.setDisplayFrom(10);

		coloredslipstickchart.setDisplayNumber(30);

		coloredslipstickchart.setMinDisplayNumber(5);

		coloredslipstickchart.setZoomBaseLine(IZoomable.ZOOM_BASE_LINE_CENTER);

//		coloredslipstickchart.setDisplayLongitudeTitle(true);
//		coloredslipstickchart.setDisplayLatitudeTitle(true);
//		coloredslipstickchart.setDisplayLatitude(true);
//		coloredslipstickchart.setDisplayLongitude(true);
		coloredslipstickchart.setBackgroundColor(Color.BLACK);

		coloredslipstickchart.setDataQuadrantPaddingTop(5);
		coloredslipstickchart.setDataQuadrantPaddingBottom(5);
		coloredslipstickchart.setDataQuadrantPaddingLeft(5);
		coloredslipstickchart.setDataQuadrantPaddingRight(5);
//		coloredslipstickchart.setAxisYTitleQuadrantWidth(50);
//		coloredslipstickchart.setAxisXTitleQuadrantHeight(20);
		coloredslipstickchart
				.setAxisXPosition(Axis.AXIS_X_POSITION_BOTTOM);
		coloredslipstickchart.setAxisYPosition(Axis.AXIS_Y_POSITION_RIGHT);
		
		// 为chart1增加均线
		coloredslipstickchart
				.setStickData(new ListChartData<IStickEntity>(volc));
	}

	private void initMAStickChart() {
		this.mastickchart = (MAStickChart) findViewById(R.id.mastickchart);

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

		mastickchart.setAxisXColor(Color.LTGRAY);
		mastickchart.setAxisYColor(Color.LTGRAY);
		mastickchart.setLatitudeColor(Color.GRAY);
		mastickchart.setLongitudeColor(Color.GRAY);
		mastickchart.setBorderColor(Color.LTGRAY);
		mastickchart.setLongitudeFontColor(Color.WHITE);
		mastickchart.setLatitudeFontColor(Color.WHITE);

		// 最大显示足数
		mastickchart.setMaxSticksNum(52);
		// 最大纬线数
		mastickchart.setLatitudeNum(2);
		// 最大经线数
		mastickchart.setLongitudeNum(3);
		// 最大价格
		mastickchart.setMaxValue(10000);
		// 最小价格
		mastickchart.setMinValue(100);

		mastickchart.setDisplayLongitudeTitle(true);
		mastickchart.setDisplayLatitudeTitle(true);
		mastickchart.setDisplayLatitude(true);
		mastickchart.setDisplayLongitude(true);
		mastickchart.setBackgroundColor(Color.BLACK);

		mastickchart.setDataQuadrantPaddingTop(5);
		mastickchart.setDataQuadrantPaddingBottom(5);
		mastickchart.setDataQuadrantPaddingLeft(5);
		mastickchart.setDataQuadrantPaddingRight(5);
//		mastickchart.setAxisYTitleQuadrantWidth(50);
//		mastickchart.setAxisXTitleQuadrantHeight(20);
		mastickchart.setAxisXPosition(Axis.AXIS_X_POSITION_BOTTOM);
		mastickchart.setAxisYPosition(Axis.AXIS_Y_POSITION_RIGHT);

		// 为chart1增加均线
		mastickchart.setLinesData(vlines);
		// 为chart1增加均线
		mastickchart.setStickData(new ListChartData<IStickEntity>(vol));

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
//		maslipstickchart.setAxisYTitleQuadrantWidth(50);
//		maslipstickchart.setAxisXTitleQuadrantHeight(20);
		maslipstickchart.setAxisXPosition(Axis.AXIS_X_POSITION_BOTTOM);
		maslipstickchart.setAxisYPosition(Axis.AXIS_Y_POSITION_RIGHT);

		// 为chart1增加均线
		maslipstickchart.setLineData(vlines);
		// 为chart1增加均线
		maslipstickchart.setStickData(new ListChartData<IStickEntity>(vol));
	}

	private void initMinusStickChart() {
		this.minusstickchart = (MinusStickChart) findViewById(R.id.minusstickchart);

		List<IStickEntity> data = new ArrayList<IStickEntity>();
		data.add(new StickEntity(50000, 0, 20110603));
		data.add(new StickEntity(42000, 0, 20110703));
		data.add(new StickEntity(32000, 0, 20110803));
		data.add(new StickEntity(21000, 0, 20110903));
		data.add(new StickEntity(0, -12000, 20111003));
		data.add(new StickEntity(0, -28000, 20111103));
		data.add(new StickEntity(0, -41000, 20111203));
		data.add(new StickEntity(0, -25000, 20120103));
		data.add(new StickEntity(0, -18000, 20120203));
		data.add(new StickEntity(14000, 0, 20120303));
		data.add(new StickEntity(24000, 0, 20120303));
		data.add(new StickEntity(36000, 0, 20120303));
		data.add(new StickEntity(46000, 0, 20120303));
		minusstickchart.setStickData(new ListChartData<IStickEntity>(data));

		minusstickchart.setMaxSticksNum(data.size());
		minusstickchart.setMaxValue(50000);
		minusstickchart.setMinValue(-50000);

		minusstickchart.setBorderColor(Color.GRAY);
		minusstickchart.setAxisXColor(Color.WHITE);
		minusstickchart.setAxisYColor(Color.WHITE);
		minusstickchart.setLatitudeFontColor(Color.WHITE);
		minusstickchart.setLatitudeColor(Color.GRAY);
		minusstickchart.setLongitudeFontColor(Color.WHITE);
		minusstickchart.setLongitudeColor(Color.GRAY);
		// 最大纬线数
		minusstickchart.setLatitudeNum(3);
		// 最大经线数
		minusstickchart.setLongitudeNum(2);
		minusstickchart.setDisplayLongitudeTitle(true);
		minusstickchart.setDisplayLatitudeTitle(true);
		//minusstickchart.setDisplayCrossXOnTouch(false);
		//minusstickchart.setDisplayCrossYOnTouch(false);
		minusstickchart.setDisplayLatitude(true);
		minusstickchart.setDisplayLongitude(true);

		minusstickchart.setDataQuadrantPaddingTop(5);
		minusstickchart.setDataQuadrantPaddingBottom(5);
		minusstickchart.setDataQuadrantPaddingLeft(5);
		minusstickchart.setDataQuadrantPaddingRight(5);
//		minusstickchart.setAxisYTitleQuadrantWidth(50);
//		minusstickchart.setAxisXTitleQuadrantHeight(20);
		minusstickchart.setAxisXPosition(Axis.AXIS_X_POSITION_BOTTOM);
		minusstickchart.setAxisYPosition(Axis.AXIS_Y_POSITION_RIGHT);

	}

	private void initSlipMinusStickChart() {
		this.slipminusstickchart = (SlipMinusStickChart) findViewById(R.id.slipminusstickchart);

		List<IStickEntity> data = new ArrayList<IStickEntity>();
		data.add(new StickEntity(50000, 0, 20110603));
		data.add(new StickEntity(42000, 0, 20110703));
		data.add(new StickEntity(32000, 0, 20110803));
		data.add(new StickEntity(21000, 0, 20110903));
		data.add(new StickEntity(0, -12000, 20111003));
		data.add(new StickEntity(0, -28000, 20111103));
		data.add(new StickEntity(0, -41000, 20111203));
		data.add(new StickEntity(0, -25000, 20120103));
		data.add(new StickEntity(0, -18000, 20120203));
		data.add(new StickEntity(14000, 0, 20120303));
		data.add(new StickEntity(24000, 0, 20120303));
		data.add(new StickEntity(36000, 0, 20120303));
		data.add(new StickEntity(46000, 0, 20120303));
		data.add(new StickEntity(50000, 0, 20110603));
		data.add(new StickEntity(42000, 0, 20110703));
		data.add(new StickEntity(32000, 0, 20110803));
		data.add(new StickEntity(21000, 0, 20110903));
		data.add(new StickEntity(0, -12000, 20111003));
		data.add(new StickEntity(0, -28000, 20111103));
		data.add(new StickEntity(0, -41000, 20111203));
		data.add(new StickEntity(0, -25000, 20120103));
		data.add(new StickEntity(0, -18000, 20120203));
		data.add(new StickEntity(14000, 0, 20120303));
		data.add(new StickEntity(24000, 0, 20120303));
		data.add(new StickEntity(36000, 0, 20120303));
		data.add(new StickEntity(46000, 0, 20120303));
		data.add(new StickEntity(50000, 0, 20110603));
		data.add(new StickEntity(42000, 0, 20110703));
		data.add(new StickEntity(32000, 0, 20110803));
		data.add(new StickEntity(21000, 0, 20110903));
		data.add(new StickEntity(0, -12000, 20111003));
		data.add(new StickEntity(0, -28000, 20111103));
		data.add(new StickEntity(0, -41000, 20111203));
		data.add(new StickEntity(0, -25000, 20120103));
		data.add(new StickEntity(0, -18000, 20120203));
		data.add(new StickEntity(14000, 0, 20120303));
		data.add(new StickEntity(24000, 0, 20120303));
		data.add(new StickEntity(36000, 0, 20120303));
		data.add(new StickEntity(46000, 0, 20120303));

		slipminusstickchart.setMaxValue(50000);
		slipminusstickchart.setMinValue(-50000);

		slipminusstickchart.setBorderColor(Color.GRAY);
		slipminusstickchart.setAxisXColor(Color.WHITE);
		slipminusstickchart.setAxisYColor(Color.WHITE);
		slipminusstickchart.setLatitudeFontColor(Color.WHITE);
		slipminusstickchart.setLatitudeColor(Color.GRAY);
		slipminusstickchart.setLongitudeFontColor(Color.WHITE);
		slipminusstickchart.setLongitudeColor(Color.GRAY);
		// 最大纬线数
		slipminusstickchart.setLatitudeNum(3);
		// 最大经线数
		slipminusstickchart.setLongitudeNum(2);
		slipminusstickchart.setDisplayFrom(0);
		slipminusstickchart.setDisplayNumber(10);
		slipminusstickchart.setMinDisplayNumber(5);

		slipminusstickchart.setZoomBaseLine(IZoomable.ZOOM_BASE_LINE_CENTER);
		slipminusstickchart.setDisplayLongitudeTitle(true);
		slipminusstickchart.setDisplayLatitudeTitle(true);
		//slipminusstickchart.setDisplayCrossXOnTouch(false);
		//slipminusstickchart.setDisplayCrossYOnTouch(false);
		slipminusstickchart.setDisplayLatitude(true);
		slipminusstickchart.setDisplayLongitude(true);
		slipminusstickchart.setStickSpacing(10);

		slipminusstickchart.setDataQuadrantPaddingTop(5);
		slipminusstickchart.setDataQuadrantPaddingBottom(5);
		slipminusstickchart.setDataQuadrantPaddingLeft(5);
		slipminusstickchart.setDataQuadrantPaddingRight(5);
//		slipminusstickchart.setAxisYTitleQuadrantWidth(50);
//		slipminusstickchart.setAxisXTitleQuadrantHeight(20);
		slipminusstickchart.setAxisXPosition(Axis.AXIS_X_POSITION_BOTTOM);
		slipminusstickchart.setAxisYPosition(Axis.AXIS_Y_POSITION_RIGHT);

		IChartData<IStickEntity> datas = new ListChartData<IStickEntity>(data);
		slipminusstickchart.setStickData(datas);
	}

	private void initCandleStickChart() {
		this.candlestickchart = (CandleStickChart) findViewById(R.id.candlestickchart);
		candlestickchart.setAxisXColor(Color.LTGRAY);
		candlestickchart.setAxisYColor(Color.LTGRAY);
		candlestickchart.setLatitudeColor(Color.GRAY);
		candlestickchart.setLongitudeColor(Color.GRAY);
		candlestickchart.setBorderColor(Color.LTGRAY);
		candlestickchart.setLongitudeFontColor(Color.WHITE);
		candlestickchart.setLatitudeFontColor(Color.WHITE);

		// 最大显示足数
		candlestickchart.setMaxSticksNum(52);
		// 最大纬线数
		candlestickchart.setLatitudeNum(5);
		// 最大经线数
		candlestickchart.setLongitudeNum(3);
		// 最大价格
		candlestickchart.setMaxValue(1200);
		// 最小价格
		candlestickchart.setMinValue(200);

		candlestickchart.setDisplayLongitudeTitle(true);
		candlestickchart.setDisplayLatitudeTitle(true);
		candlestickchart.setDisplayLatitude(true);
		candlestickchart.setDisplayLongitude(true);
		candlestickchart.setBackgroundColor(Color.BLACK);

		candlestickchart.setDataQuadrantPaddingTop(5);
		candlestickchart.setDataQuadrantPaddingBottom(5);
		candlestickchart.setDataQuadrantPaddingLeft(5);
		candlestickchart.setDataQuadrantPaddingRight(5);
//		candlestickchart.setAxisYTitleQuadrantWidth(50);
//		candlestickchart.setAxisXTitleQuadrantHeight(20);
		candlestickchart.setAxisXPosition(Axis.AXIS_X_POSITION_BOTTOM);
		candlestickchart.setAxisYPosition(Axis.AXIS_Y_POSITION_RIGHT);

		// 为chart2增加均线
		candlestickchart.setStickData(new ListChartData<IStickEntity>(ohlc));
	}

	private void initSlipCandleStickChart() {
		this.slipcandlestickchart = (SlipCandleStickChart) findViewById(R.id.slipcandlestickchart);
		slipcandlestickchart.setAxisXColor(Color.LTGRAY);
		slipcandlestickchart.setAxisYColor(Color.LTGRAY);
		slipcandlestickchart.setLatitudeColor(Color.GRAY);
		slipcandlestickchart.setLongitudeColor(Color.GRAY);
		slipcandlestickchart.setBorderColor(Color.LTGRAY);
		slipcandlestickchart.setLongitudeFontColor(Color.WHITE);
		slipcandlestickchart.setLatitudeFontColor(Color.WHITE);

		// 最大纬线数
		slipcandlestickchart.setLatitudeNum(5);
		// 最大经线数
		slipcandlestickchart.setLongitudeNum(3);
		// 最大价格
		slipcandlestickchart.setMaxValue(1200);
		// 最小价格
		slipcandlestickchart.setMinValue(200);

		slipcandlestickchart.setDisplayFrom(10);

		slipcandlestickchart.setDisplayNumber(30);

		slipcandlestickchart.setMinDisplayNumber(5);

		slipcandlestickchart.setZoomBaseLine(IZoomable.ZOOM_BASE_LINE_CENTER);

		slipcandlestickchart.setDisplayLongitudeTitle(true);
		slipcandlestickchart.setDisplayLatitudeTitle(true);
		slipcandlestickchart.setDisplayLatitude(true);
		slipcandlestickchart.setDisplayLongitude(true);
		slipcandlestickchart.setBackgroundColor(Color.BLACK);

		slipcandlestickchart.setDataQuadrantPaddingTop(5);
		slipcandlestickchart.setDataQuadrantPaddingBottom(5);
		slipcandlestickchart.setDataQuadrantPaddingLeft(5);
		slipcandlestickchart.setDataQuadrantPaddingRight(5);
//		slipcandlestickchart.setAxisYTitleQuadrantWidth(50);
//		slipcandlestickchart.setAxisXTitleQuadrantHeight(20);
		slipcandlestickchart.setAxisXPosition(Axis.AXIS_X_POSITION_BOTTOM);
		slipcandlestickchart.setAxisYPosition(Axis.AXIS_Y_POSITION_RIGHT);

		// 为chart2增加均线
		slipcandlestickchart
				.setStickData(new ListChartData<IStickEntity>(ohlc));
	}

	private void initMACandleStickChart() {
		this.macandlestickchart = (MACandleStickChart) findViewById(R.id.macandlestickchart);
		List<LineEntity<DateValueEntity>> lines = new ArrayList<LineEntity<DateValueEntity>>();

		// 计算5日均线
		LineEntity<DateValueEntity> MA5 = new LineEntity<DateValueEntity>();
		MA5.setTitle("MA5");
		MA5.setLineColor(Color.WHITE);
		MA5.setLineData(initMA(5));
		lines.add(MA5);

		// 计算10日均线
		LineEntity<DateValueEntity> MA10 = new LineEntity<DateValueEntity>();
		MA10.setTitle("MA10");
		MA10.setLineColor(Color.RED);
		MA10.setLineData(initMA(10));
		lines.add(MA10);

		// 计算25日均线
		LineEntity<DateValueEntity> MA25 = new LineEntity<DateValueEntity>();
		MA25.setTitle("MA25");
		MA25.setLineColor(Color.GREEN);
		MA25.setLineData(initMA(25));
		lines.add(MA25);

		macandlestickchart.setAxisXColor(Color.LTGRAY);
		macandlestickchart.setAxisYColor(Color.LTGRAY);
		macandlestickchart.setLatitudeColor(Color.GRAY);
		macandlestickchart.setLongitudeColor(Color.GRAY);
		macandlestickchart.setBorderColor(Color.LTGRAY);
		macandlestickchart.setLongitudeFontColor(Color.WHITE);
		macandlestickchart.setLatitudeFontColor(Color.WHITE);

		// 最大显示足数
		macandlestickchart.setMaxSticksNum(52);
		// 最大纬线数
		macandlestickchart.setLatitudeNum(5);
		// 最大经线数
		macandlestickchart.setLongitudeNum(3);
		// 最大价格
		macandlestickchart.setMaxValue(1200);
		// 最小价格
		macandlestickchart.setMinValue(200);

		macandlestickchart.setDisplayLongitudeTitle(true);
		macandlestickchart.setDisplayLatitudeTitle(true);
		macandlestickchart.setDisplayLatitude(true);
		macandlestickchart.setDisplayLongitude(true);
		macandlestickchart.setBackgroundColor(Color.BLACK);

		macandlestickchart.setDataQuadrantPaddingTop(5);
		macandlestickchart.setDataQuadrantPaddingBottom(5);
		macandlestickchart.setDataQuadrantPaddingLeft(5);
		macandlestickchart.setDataQuadrantPaddingRight(5);
//		macandlestickchart.setAxisYTitleQuadrantWidth(50);
//		macandlestickchart.setAxisXTitleQuadrantHeight(20);
		macandlestickchart.setAxisXPosition(Axis.AXIS_X_POSITION_BOTTOM);
		macandlestickchart.setAxisYPosition(Axis.AXIS_Y_POSITION_RIGHT);

		// 为chart2增加均线
		macandlestickchart.setLinesData(lines);

		// 为chart2增加均线
		macandlestickchart.setStickData(new ListChartData<IStickEntity>(ohlc));

	}

	private void initMASlipCandleStickChart() {
		this.maslipcandlestickchart = (MASlipCandleStickChart) findViewById(R.id.maslipcandlestickchart);
		List<LineEntity<DateValueEntity>> lines = new ArrayList<LineEntity<DateValueEntity>>();

		// 计算5日均线
		LineEntity<DateValueEntity> MA5 = new LineEntity<DateValueEntity>();
		MA5.setTitle("MA5");
		MA5.setLineColor(Color.WHITE);
		MA5.setLineData(initMA(5));
		lines.add(MA5);

		// 计算10日均线
		LineEntity<DateValueEntity> MA10 = new LineEntity<DateValueEntity>();
		MA10.setTitle("MA10");
		MA10.setLineColor(Color.RED);
		MA10.setLineData(initMA(10));
		lines.add(MA10);

		// 计算25日均线
		LineEntity<DateValueEntity> MA25 = new LineEntity<DateValueEntity>();
		MA25.setTitle("MA25");
		MA25.setLineColor(Color.GREEN);
		MA25.setLineData(initMA(25));
		lines.add(MA25);

		maslipcandlestickchart.setAxisXColor(Color.LTGRAY);
		maslipcandlestickchart.setAxisYColor(Color.LTGRAY);
		maslipcandlestickchart.setLatitudeColor(Color.GRAY);
		maslipcandlestickchart.setLongitudeColor(Color.GRAY);
		maslipcandlestickchart.setBorderColor(Color.LTGRAY);
		maslipcandlestickchart.setLongitudeFontColor(Color.WHITE);
		maslipcandlestickchart.setLatitudeFontColor(Color.WHITE);

		// 最大纬线数
		maslipcandlestickchart.setLatitudeNum(5);
		// 最大经线数
		maslipcandlestickchart.setLongitudeNum(3);
		// 最大价格
		maslipcandlestickchart.setMaxValue(1200);
		// 最小价格
		maslipcandlestickchart.setMinValue(200);

		maslipcandlestickchart.setDisplayFrom(10);

		maslipcandlestickchart.setDisplayNumber(30);

		maslipcandlestickchart.setMinDisplayNumber(5);

		maslipcandlestickchart.setZoomBaseLine(IZoomable.ZOOM_BASE_LINE_CENTER);

		maslipcandlestickchart.setDisplayLongitudeTitle(true);
		maslipcandlestickchart.setDisplayLatitudeTitle(true);
		maslipcandlestickchart.setDisplayLatitude(true);
		maslipcandlestickchart.setDisplayLongitude(true);
		maslipcandlestickchart.setBackgroundColor(Color.BLACK);

		maslipcandlestickchart.setDataQuadrantPaddingTop(5);
		maslipcandlestickchart.setDataQuadrantPaddingBottom(5);
		maslipcandlestickchart.setDataQuadrantPaddingLeft(5);
		maslipcandlestickchart.setDataQuadrantPaddingRight(5);
//		maslipcandlestickchart.setAxisYTitleQuadrantWidth(50);
//		maslipcandlestickchart.setAxisXTitleQuadrantHeight(20);
		maslipcandlestickchart
				.setAxisXPosition(Axis.AXIS_X_POSITION_BOTTOM);
		maslipcandlestickchart
				.setAxisYPosition(Axis.AXIS_Y_POSITION_RIGHT);

		// 为chart2增加均线
		maslipcandlestickchart.setLinesData(lines);

		// 为chart2增加均线
		maslipcandlestickchart.setStickData(new ListChartData<IStickEntity>(
				ohlc));

		maslipcandlestickchart.setOnDisplayCursorListener(new IDisplayCursorListener() {
			public void onCursorChanged(IDataCursor dataCursor, int displayFrom,
					int displayNumber) {
				bollmaslipcandlestickchart.setDisplayFrom(displayFrom);
				bollmaslipcandlestickchart.setDisplayNumber(displayNumber);
				bollmaslipcandlestickchart.postInvalidate();
			}
		});

		maslipcandlestickchart.setOnTouchGestureListener(new OnTouchGestureListener(){

			/* (non-Javadoc)
			 * 
			 * @param touchable
			 * @param event 
			 * @see cn.limc.androidcharts.event.OnTouchGestureListener#onTouchDown(cn.limc.androidcharts.event.ITouchable, android.view.MotionEvent) 
			 */
			@Override
			public void onTouchDown(ITouchable touchable, MotionEvent event) {
				super.onTouchDown(touchable, event);
				bollmaslipcandlestickchart.touchDown(new PointF(event.getX(),event.getY()));
			}

			/* (non-Javadoc)
			 * 
			 * @param touchable
			 * @param event 
			 * @see cn.limc.androidcharts.event.OnTouchGestureListener#onTouchMoved(cn.limc.androidcharts.event.ITouchable, android.view.MotionEvent) 
			 */
			@Override
			public void onTouchMoved(ITouchable touchable, MotionEvent event) {
				super.onTouchMoved(touchable, event);
				bollmaslipcandlestickchart.touchMoved(new PointF(event.getX(),event.getY()));
			}

			/* (non-Javadoc)
			 * 
			 * @param touchable
			 * @param event 
			 * @see cn.limc.androidcharts.event.OnTouchGestureListener#onTouchUp(cn.limc.androidcharts.event.ITouchable, android.view.MotionEvent) 
			 */
			@Override
			public void onTouchUp(ITouchable touchable, MotionEvent event) {
				super.onTouchUp(touchable, event);
				bollmaslipcandlestickchart.touchUp(new PointF(event.getX(),event.getY()));
			}
			
		});
	}

	private void initBOLLMASlipCandleStickChart() {
		this.bollmaslipcandlestickchart = (BOLLMASlipCandleStickChart) findViewById(R.id.bollmaslipcandlestickchart);
		List<LineEntity<DateValueEntity>> lines = new ArrayList<LineEntity<DateValueEntity>>();

		// 计算5日均线
		LineEntity<DateValueEntity> MA5 = new LineEntity<DateValueEntity>();
		MA5.setTitle("MA5");
		MA5.setLineColor(Color.WHITE);
		MA5.setLineData(initMA(5));
		lines.add(MA5);

		// 计算10日均线
		LineEntity<DateValueEntity> MA10 = new LineEntity<DateValueEntity>();
		MA10.setTitle("MA10");
		MA10.setLineColor(Color.RED);
		MA10.setLineData(initMA(10));
		lines.add(MA10);

		// 计算25日均线
		LineEntity<DateValueEntity> MA25 = new LineEntity<DateValueEntity>();
		MA25.setTitle("MA25");
		MA25.setLineColor(Color.GREEN);
		MA25.setLineData(initMA(25));
		lines.add(MA25);

		List<LineEntity<DateValueEntity>> band = new ArrayList<LineEntity<DateValueEntity>>();
		LineEntity<DateValueEntity> LOWER = new LineEntity<DateValueEntity>();
		LOWER.setTitle("LOWER");
		LOWER.setLineColor(Color.YELLOW);
		LOWER.setLineData(dv1);
		band.add(LOWER);

		LineEntity<DateValueEntity> UPPER = new LineEntity<DateValueEntity>();
		UPPER.setTitle("UPPER");
		UPPER.setLineColor(Color.CYAN);
		UPPER.setLineData(dv2);
		band.add(UPPER);

		bollmaslipcandlestickchart.setAxisXColor(Color.LTGRAY);
		bollmaslipcandlestickchart.setAxisYColor(Color.LTGRAY);
		bollmaslipcandlestickchart.setLatitudeColor(Color.GRAY);
		bollmaslipcandlestickchart.setLongitudeColor(Color.GRAY);
		bollmaslipcandlestickchart.setBorderColor(Color.LTGRAY);
		bollmaslipcandlestickchart.setLongitudeFontColor(Color.WHITE);
		bollmaslipcandlestickchart.setLatitudeFontColor(Color.WHITE);

		// 最大纬线数
		bollmaslipcandlestickchart.setLatitudeNum(5);
		// 最大经线数
		bollmaslipcandlestickchart.setLongitudeNum(3);
		// 最大价格
		bollmaslipcandlestickchart.setMaxValue(1200);
		// 最小价格
		bollmaslipcandlestickchart.setMinValue(200);

		bollmaslipcandlestickchart.setDisplayFrom(10);

		bollmaslipcandlestickchart.setDisplayNumber(30);

		bollmaslipcandlestickchart.setMinDisplayNumber(5);

		bollmaslipcandlestickchart
				.setZoomBaseLine(IZoomable.ZOOM_BASE_LINE_CENTER);

		bollmaslipcandlestickchart.setDisplayLongitudeTitle(true);
		bollmaslipcandlestickchart.setDisplayLatitudeTitle(true);
		bollmaslipcandlestickchart.setDisplayLatitude(true);
		bollmaslipcandlestickchart.setDisplayLongitude(true);
		bollmaslipcandlestickchart.setBackgroundColor(Color.BLACK);

		bollmaslipcandlestickchart.setDataQuadrantPaddingTop(5);
		bollmaslipcandlestickchart.setDataQuadrantPaddingBottom(5);
		bollmaslipcandlestickchart.setDataQuadrantPaddingLeft(5);
		bollmaslipcandlestickchart.setDataQuadrantPaddingRight(5);
//		bollmaslipcandlestickchart.setAxisYTitleQuadrantWidth(50);
//		bollmaslipcandlestickchart.setAxisXTitleQuadrantHeight(20);
		bollmaslipcandlestickchart
				.setAxisXPosition(Axis.AXIS_X_POSITION_BOTTOM);
		bollmaslipcandlestickchart
				.setAxisYPosition(Axis.AXIS_Y_POSITION_RIGHT);

		// 为chart2增加均线
		bollmaslipcandlestickchart.setLinesData(lines);
		bollmaslipcandlestickchart.setBandData(band);

		// 为chart2增加均线
		bollmaslipcandlestickchart
				.setStickData(new ListChartData<IStickEntity>(ohlc));

		bollmaslipcandlestickchart.setOnDisplayCursorListener(new IDisplayCursorListener() {
			public void onCursorChanged(IDataCursor dataCursor, int displayFrom,
					int displayNumber) {
				maslipcandlestickchart.setDisplayFrom(displayFrom);
				maslipcandlestickchart.setDisplayNumber(displayNumber);
				maslipcandlestickchart.postInvalidate();
			}
		});

		bollmaslipcandlestickchart.setOnTouchGestureListener(new OnTouchGestureListener(){

			/* (non-Javadoc)
			 * 
			 * @param touchable
			 * @param event 
			 * @see cn.limc.androidcharts.event.OnTouchGestureListener#onTouchDown(cn.limc.androidcharts.event.ITouchable, android.view.MotionEvent) 
			 */
			@Override
			public void onTouchDown(ITouchable touchable, MotionEvent event) {
				super.onTouchDown(touchable, event);
				maslipcandlestickchart.touchDown(new PointF(event.getX(),event.getY()));
			}

			/* (non-Javadoc)
			 * 
			 * @param touchable
			 * @param event 
			 * @see cn.limc.androidcharts.event.OnTouchGestureListener#onTouchMoved(cn.limc.androidcharts.event.ITouchable, android.view.MotionEvent) 
			 */
			@Override
			public void onTouchMoved(ITouchable touchable, MotionEvent event) {
				super.onTouchMoved(touchable, event);
				maslipcandlestickchart.touchMoved(new PointF(event.getX(),event.getY()));
			}

			/* (non-Javadoc)
			 * 
			 * @param touchable
			 * @param event 
			 * @see cn.limc.androidcharts.event.OnTouchGestureListener#onTouchUp(cn.limc.androidcharts.event.ITouchable, android.view.MotionEvent) 
			 */
			@Override
			public void onTouchUp(ITouchable touchable, MotionEvent event) {
				super.onTouchUp(touchable, event);
				maslipcandlestickchart.touchUp(new PointF(event.getX(),event.getY()));
			}
		});

	}

	private void initPieChart() {
		this.piechart = (PieChart) findViewById(R.id.piechart);
		List<TitleValueColorEntity> data3 = new ArrayList<TitleValueColorEntity>();
		data3.add(new TitleValueColorEntity(getResources().getString(
				R.string.piechart_title1), 2, getResources().getColor(
				R.drawable.red)));
		data3.add(new TitleValueColorEntity(getResources().getString(
				R.string.piechart_title2), 3, getResources().getColor(
				R.drawable.orange)));
		data3.add(new TitleValueColorEntity(getResources().getString(
				R.string.piechart_title3), 6, getResources().getColor(
				R.drawable.yellow)));
		data3.add(new TitleValueColorEntity(getResources().getString(
				R.string.piechart_title4), 2, getResources().getColor(
				R.drawable.lightgreen)));
		data3.add(new TitleValueColorEntity(getResources().getString(
				R.string.piechart_title5), 2, getResources().getColor(
				R.drawable.green)));
		piechart.setData(data3);
	}
	
    private void initRoseChart() {
        this.rosechart = (RoseChart) findViewById(R.id.rosechart);
        List<TitleValueColorEntity> data3 = new ArrayList<TitleValueColorEntity>();
        data3.add(new TitleValueColorEntity(getResources().getString(R.string.rosechart_title1), 1,
                getResources().getColor(R.drawable.red)));
        data3.add(new TitleValueColorEntity(getResources().getString(R.string.rosechart_title1), 2,
                getResources().getColor(R.drawable.orange)));
        data3.add(new TitleValueColorEntity(getResources().getString(R.string.rosechart_title1), 3,
                getResources().getColor(R.drawable.yellow)));
        data3.add(new TitleValueColorEntity(getResources().getString(R.string.rosechart_title4), 4,
                getResources().getColor(R.drawable.green)));
        data3.add(new TitleValueColorEntity(getResources().getString(R.string.rosechart_title5), 5,
                getResources().getColor(R.drawable.blue)));
        data3.add(new TitleValueColorEntity(getResources().getString(R.string.rosechart_title6), 6,
                getResources().getColor(R.drawable.pink)));
        rosechart.setData(data3);
    }


	private void initSpiderWebChart() {
		this.spiderwebchart = (SpiderWebChart) findViewById(R.id.spiderwebchart);

		List<TitleValueEntity> data1 = new ArrayList<TitleValueEntity>();
		data1.add(new TitleValueEntity(getResources().getString(
				R.string.spiderwebchart_title1), 3));
		data1.add(new TitleValueEntity(getResources().getString(
				R.string.spiderwebchart_title2), 4));
		data1.add(new TitleValueEntity(getResources().getString(
				R.string.spiderwebchart_title3), 9));
		data1.add(new TitleValueEntity(getResources().getString(
				R.string.spiderwebchart_title4), 8));
		data1.add(new TitleValueEntity(getResources().getString(
				R.string.spiderwebchart_title5), 10));

		List<TitleValueEntity> data2 = new ArrayList<TitleValueEntity>();
		data2.add(new TitleValueEntity(getResources().getString(
				R.string.spiderwebchart_title5), 3));
		data2.add(new TitleValueEntity(getResources().getString(
				R.string.spiderwebchart_title5), 4));
		data2.add(new TitleValueEntity(getResources().getString(
				R.string.spiderwebchart_title5), 5));
		data2.add(new TitleValueEntity(getResources().getString(
				R.string.spiderwebchart_title5), 6));
		data2.add(new TitleValueEntity(getResources().getString(
				R.string.spiderwebchart_title5), 7));

		List<List<TitleValueEntity>> data = new ArrayList<List<TitleValueEntity>>();
		data.add(data1);
		data.add(data2);

		spiderwebchart.setData(data);
		spiderwebchart.setLatitudeNum(5);
	}
	
	   private void initRadarChart() {
	        this.radarchart = (RadarChart) findViewById(R.id.radarchart);

	        List<TitleValueEntity> data1 = new ArrayList<TitleValueEntity>();
	        data1.add(new TitleValueEntity(getResources().getString(
	                R.string.radarchart_title1), 3));
	        data1.add(new TitleValueEntity(getResources().getString(
	                R.string.radarchart_title2), 4));
	        data1.add(new TitleValueEntity(getResources().getString(
	                R.string.radarchart_title3), 9));
	        data1.add(new TitleValueEntity(getResources().getString(
	                R.string.radarchart_title4), 8));
	        data1.add(new TitleValueEntity(getResources().getString(
	                R.string.radarchart_title5), 10));

	        List<TitleValueEntity> data2 = new ArrayList<TitleValueEntity>();
	        data2.add(new TitleValueEntity(getResources().getString(
	                R.string.radarchart_title1), 3));
	        data2.add(new TitleValueEntity(getResources().getString(
	                R.string.radarchart_title2), 4));
	        data2.add(new TitleValueEntity(getResources().getString(
	                R.string.radarchart_title3), 5));
	        data2.add(new TitleValueEntity(getResources().getString(
	                R.string.radarchart_title4), 6));
	        data2.add(new TitleValueEntity(getResources().getString(
	                R.string.radarchart_title5), 7));

	        List<List<TitleValueEntity>> data = new ArrayList<List<TitleValueEntity>>();
	        data.add(data1);
	        data.add(data2);

	        radarchart.setData(data);
	        radarchart.setLatitudeNum(5);
	    }

	private void initMACDChart() {

		macdChart = (MACDChart) findViewById(R.id.macdchart);
		macdChart.setMaxValue(300000);
		macdChart.setMinValue(-300000);
		//macdChart.setDisplayCrossXOnTouch(true);
		//macdChart.setDisplayCrossYOnTouch(true);
		macdChart.setLatitudeNum(4);
		macdChart.setLongitudeNum(3);
		macdChart.setDisplayFrom(0);
		macdChart.setDisplayNumber(10);
		macdChart.setMinDisplayNumber(5);
		macdChart.setZoomBaseLine(IZoomable.ZOOM_BASE_LINE_CENTER);
		macdChart.setAxisXColor(Color.LTGRAY);
		macdChart.setAxisYColor(Color.LTGRAY);
		macdChart.setLatitudeColor(Color.GRAY);
		macdChart.setLongitudeColor(Color.GRAY);
		macdChart.setBorderColor(Color.LTGRAY);
		macdChart.setLongitudeFontColor(Color.WHITE);
		macdChart.setLatitudeFontColor(Color.WHITE);
		macdChart.setMacdDisplayType(MACDChart.MACD_DISPLAY_TYPE_STICK);
		macdChart.setPositiveStickColor(Color.RED);
		macdChart.setNegativeStickColor(Color.CYAN);
		macdChart.setMacdLineColor(Color.CYAN);
		macdChart.setDeaLineColor(Color.YELLOW);
		macdChart.setDiffLineColor(Color.WHITE);
		macdChart.setDataQuadrantPaddingTop(5);
		macdChart.setDataQuadrantPaddingBottom(5);
		macdChart.setDataQuadrantPaddingLeft(5);
		macdChart.setDataQuadrantPaddingRight(5);
//		macdChart.setAxisYTitleQuadrantWidth(50);
//		macdChart.setAxisXTitleQuadrantHeight(20);
		macdChart.setAxisXPosition(Axis.AXIS_X_POSITION_BOTTOM);
		macdChart.setAxisYPosition(Axis.AXIS_Y_POSITION_RIGHT);

		// 设置stickData
		macdChart.setStickData(new ListChartData<IStickEntity>(macd));
	}

	private void initVOLC() {
		List<IStickEntity> stick = new ArrayList<IStickEntity>();
		this.volc = new ArrayList<IStickEntity>();

		stick.add(new ColoredStickEntity(406000, 0, 20110825, Color.RED));
		stick.add(new ColoredStickEntity(232000, 0, 20110824, Color.RED));
		stick.add(new ColoredStickEntity(355000, 0, 20110823, Color.BLUE));
		stick.add(new ColoredStickEntity(437000, 0, 20110822, Color.RED));
		stick.add(new ColoredStickEntity(460000, 0, 20110819, Color.BLUE));
		stick.add(new ColoredStickEntity(422000, 0, 20110818, Color.LTGRAY));
		stick.add(new ColoredStickEntity(502000, 0, 20110817, Color.RED));
		stick.add(new ColoredStickEntity(509000, 0, 20110816, Color.RED));
		stick.add(new ColoredStickEntity(110000, 0, 20110815, Color.RED));
		stick.add(new ColoredStickEntity(110000, 0, 20110812, Color.BLUE));
		stick.add(new ColoredStickEntity(310000, 0, 20110811, Color.RED));
		stick.add(new ColoredStickEntity(210000, 0, 20110810, Color.BLUE));
		stick.add(new ColoredStickEntity(211000, 0, 20110809, Color.BLUE));
		stick.add(new ColoredStickEntity(577000, 0, 20110808, Color.RED));
		stick.add(new ColoredStickEntity(493000, 0, 20110805, Color.BLUE));
		stick.add(new ColoredStickEntity(433000, 0, 20110804, Color.LTGRAY));
		stick.add(new ColoredStickEntity(479000, 0, 20110803, Color.BLUE));
		stick.add(new ColoredStickEntity(360000, 0, 20110802, Color.RED));
		stick.add(new ColoredStickEntity(437000, 0, 20110801, Color.BLUE));
		stick.add(new ColoredStickEntity(504000, 0, 20110729, Color.BLUE));
		stick.add(new ColoredStickEntity(520000, 0, 20110728, Color.BLUE));
		stick.add(new ColoredStickEntity(494000, 0, 20110727, Color.BLUE));
		stick.add(new ColoredStickEntity(312000, 0, 20110726, Color.RED));
		stick.add(new ColoredStickEntity(424000, 0, 20110725, Color.BLUE));
		stick.add(new ColoredStickEntity(557000, 0, 20110722, Color.RED));
		stick.add(new ColoredStickEntity(596000, 0, 20110721, Color.RED));
		stick.add(new ColoredStickEntity(311000, 0, 20110720, Color.LTGRAY));
		stick.add(new ColoredStickEntity(312000, 0, 20110719, Color.BLUE));
		stick.add(new ColoredStickEntity(312000, 0, 20110718, Color.RED));
		stick.add(new ColoredStickEntity(312000, 0, 20110715, Color.BLUE));
		stick.add(new ColoredStickEntity(410000, 0, 20110714, Color.BLUE));
		stick.add(new ColoredStickEntity(311000, 0, 20110713, Color.BLUE));
		stick.add(new ColoredStickEntity(210000, 0, 20110712, Color.BLUE));
		stick.add(new ColoredStickEntity(410000, 0, 20110711, Color.RED));
		stick.add(new ColoredStickEntity(214000, 0, 20110708, Color.RED));
		stick.add(new ColoredStickEntity(312000, 0, 20110707, Color.BLUE));
		stick.add(new ColoredStickEntity(212000, 0, 20110706, Color.RED));
		stick.add(new ColoredStickEntity(414000, 0, 20110705, Color.RED));
		stick.add(new ColoredStickEntity(310000, 0, 20110704, Color.BLUE));
		stick.add(new ColoredStickEntity(210000, 0, 20110701, Color.RED));
		stick.add(new ColoredStickEntity(190000, 0, 20110630, Color.RED));
		stick.add(new ColoredStickEntity(210000, 0, 20110629, Color.BLUE));
		stick.add(new ColoredStickEntity(116000, 0, 20110628, Color.BLUE));
		stick.add(new ColoredStickEntity(142000, 0, 20110627, Color.BLUE));
		stick.add(new ColoredStickEntity(524000, 0, 20110624, Color.RED));
		stick.add(new ColoredStickEntity(246000, 0, 20110623, Color.BLUE));
		stick.add(new ColoredStickEntity(432000, 0, 20110622, Color.RED));
		stick.add(new ColoredStickEntity(352000, 0, 20110621, Color.RED));
		stick.add(new ColoredStickEntity(243000, 0, 20110620, Color.RED));
		stick.add(new ColoredStickEntity(165000, 0, 20110617, Color.BLUE));
		stick.add(new ColoredStickEntity(554000, 0, 20110616, Color.BLUE));
		stick.add(new ColoredStickEntity(552000, 0, 20110615, Color.BLUE));
		stick.add(new ColoredStickEntity(431000, 0, 20110614, Color.LTGRAY));
		stick.add(new ColoredStickEntity(317000, 0, 20110613, Color.BLUE));
		stick.add(new ColoredStickEntity(512000, 0, 20110610, Color.BLUE));
		stick.add(new ColoredStickEntity(283000, 0, 20110609, Color.RED));
		stick.add(new ColoredStickEntity(144000, 0, 20110608, Color.RED));
		stick.add(new ColoredStickEntity(273000, 0, 20110607, Color.RED));
		stick.add(new ColoredStickEntity(278000, 0, 20110603, Color.RED));
		stick.add(new ColoredStickEntity(624000, 0, 20110602, Color.RED));
		stick.add(new ColoredStickEntity(217000, 0, 20110601, Color.BLUE));
		stick.add(new ColoredStickEntity(116000, 0, 20110531, Color.BLUE));
		stick.add(new ColoredStickEntity(191000, 0, 20110530, Color.RED));
		stick.add(new ColoredStickEntity(204000, 0, 20110527, Color.BLUE));
		stick.add(new ColoredStickEntity(236000, 0, 20110526, Color.BLUE));
		stick.add(new ColoredStickEntity(421000, 0, 20110525, Color.LTGRAY));
		stick.add(new ColoredStickEntity(114000, 0, 20110524, Color.BLUE));
		stick.add(new ColoredStickEntity(403000, 0, 20110523, Color.RED));
		stick.add(new ColoredStickEntity(205000, 0, 20110520, Color.RED));
		stick.add(new ColoredStickEntity(328000, 0, 20110519, Color.BLUE));
		stick.add(new ColoredStickEntity(109000, 0, 20110518, Color.RED));
		stick.add(new ColoredStickEntity(286000, 0, 20110517, Color.RED));
		stick.add(new ColoredStickEntity(103000, 0, 20110516, Color.BLUE));
		stick.add(new ColoredStickEntity(114000, 0, 20110513, Color.BLUE));
		stick.add(new ColoredStickEntity(107000, 0, 20110512, Color.RED));
		stick.add(new ColoredStickEntity(106000, 0, 20110511, Color.BLUE));
		stick.add(new ColoredStickEntity(146000, 0, 20110510, Color.LTGRAY));
		stick.add(new ColoredStickEntity(105000, 0, 20110509, Color.RED));
		stick.add(new ColoredStickEntity(312000, 0, 20110506, Color.RED));
		stick.add(new ColoredStickEntity(530000, 0, 20110505, Color.RED));
		stick.add(new ColoredStickEntity(275000, 0, 20110504, Color.BLUE));
		stick.add(new ColoredStickEntity(432000, 0, 20110503, Color.BLUE));
		// stick.add(new StickEntity(157000,0,20110429));
		// stick.add(new StickEntity(148000,0,20110428));
		// stick.add(new StickEntity(224000,0,20110427));
		// stick.add(new StickEntity(405000,0,20110426));
		// stick.add(new StickEntity(374000,0,20110425));
		// stick.add(new StickEntity(473000,0,20110422));
		// stick.add(new StickEntity(437000,0,20110421));
		// stick.add(new StickEntity(121000,0,20110420));
		// stick.add(new StickEntity(208000,0,20110419));
		// stick.add(new StickEntity(486000,0,20110418));
		// stick.add(new StickEntity(486000,0,20110415));
		// stick.add(new StickEntity(473000,0,20110414));
		// stick.add(new StickEntity(256000,0,20110413));
		// stick.add(new StickEntity(275000,0,20110412));
		// stick.add(new StickEntity(471000,0,20110411));
		// stick.add(new StickEntity(529000,0,20110408));
		// stick.add(new StickEntity(564000,0,20110407));
		// stick.add(new StickEntity(257000,0,20110406));
		// stick.add(new StickEntity(344000,0,20110404));
		// stick.add(new StickEntity(525000,0,20110401));

		for (int i = stick.size(); i > 0; i--) {
			this.volc.add(stick.get(i - 1));
		}

		// this.volc.addAll(stick);
	}

	private void initVOL() {
		List<IStickEntity> stick = new ArrayList<IStickEntity>();

		stick.add(new StickEntity(406000, 0, 20110825));
		stick.add(new StickEntity(232000, 0, 20110824));
		stick.add(new StickEntity(355000, 0, 20110823));
		stick.add(new StickEntity(437000, 0, 20110822));
		stick.add(new StickEntity(460000, 0, 20110819));
		stick.add(new StickEntity(422000, 0, 20110818));
		stick.add(new StickEntity(502000, 0, 20110817));
		stick.add(new StickEntity(509000, 0, 20110816));
		stick.add(new StickEntity(110000, 0, 20110815));
		stick.add(new StickEntity(110000, 0, 20110812));
		stick.add(new StickEntity(310000, 0, 20110811));
		stick.add(new StickEntity(210000, 0, 20110810));
		stick.add(new StickEntity(211000, 0, 20110809));
		stick.add(new StickEntity(577000, 0, 20110808));
		stick.add(new StickEntity(493000, 0, 20110805));
		stick.add(new StickEntity(433000, 0, 20110804));
		stick.add(new StickEntity(479000, 0, 20110803));
		stick.add(new StickEntity(360000, 0, 20110802));
		stick.add(new StickEntity(437000, 0, 20110801));
		stick.add(new StickEntity(504000, 0, 20110729));
		stick.add(new StickEntity(520000, 0, 20110728));
		stick.add(new StickEntity(494000, 0, 20110727));
		stick.add(new StickEntity(312000, 0, 20110726));
		stick.add(new StickEntity(424000, 0, 20110725));
		stick.add(new StickEntity(557000, 0, 20110722));
		stick.add(new StickEntity(596000, 0, 20110721));
		stick.add(new StickEntity(311000, 0, 20110720));
		stick.add(new StickEntity(312000, 0, 20110719));
		stick.add(new StickEntity(312000, 0, 20110718));
		stick.add(new StickEntity(312000, 0, 20110715));
		stick.add(new StickEntity(410000, 0, 20110714));
		stick.add(new StickEntity(311000, 0, 20110713));
		stick.add(new StickEntity(210000, 0, 20110712));
		stick.add(new StickEntity(410000, 0, 20110711));
		stick.add(new StickEntity(214000, 0, 20110708));
		stick.add(new StickEntity(312000, 0, 20110707));
		stick.add(new StickEntity(212000, 0, 20110706));
		stick.add(new StickEntity(414000, 0, 20110705));
		stick.add(new StickEntity(310000, 0, 20110704));
		stick.add(new StickEntity(210000, 0, 20110701));
		stick.add(new StickEntity(190000, 0, 20110630));
		stick.add(new StickEntity(210000, 0, 20110629));
		stick.add(new StickEntity(116000, 0, 20110628));
		stick.add(new StickEntity(142000, 0, 20110627));
		stick.add(new StickEntity(524000, 0, 20110624));
		stick.add(new StickEntity(246000, 0, 20110623));
		stick.add(new StickEntity(432000, 0, 20110622));
		stick.add(new StickEntity(352000, 0, 20110621));
		stick.add(new StickEntity(243000, 0, 20110620));
		stick.add(new StickEntity(165000, 0, 20110617));
		stick.add(new StickEntity(554000, 0, 20110616));
		stick.add(new StickEntity(552000, 0, 20110615));
		stick.add(new StickEntity(431000, 0, 20110614));
		stick.add(new StickEntity(317000, 0, 20110613));
		stick.add(new StickEntity(512000, 0, 20110610));
		stick.add(new StickEntity(283000, 0, 20110609));
		stick.add(new StickEntity(144000, 0, 20110608));
		stick.add(new StickEntity(273000, 0, 20110607));
		stick.add(new StickEntity(278000, 0, 20110603));
		stick.add(new StickEntity(624000, 0, 20110602));
		stick.add(new StickEntity(217000, 0, 20110601));
		stick.add(new StickEntity(116000, 0, 20110531));
		stick.add(new StickEntity(191000, 0, 20110530));
		stick.add(new StickEntity(204000, 0, 20110527));
		stick.add(new StickEntity(236000, 0, 20110526));
		stick.add(new StickEntity(421000, 0, 20110525));
		stick.add(new StickEntity(114000, 0, 20110524));
		stick.add(new StickEntity(403000, 0, 20110523));
		stick.add(new StickEntity(205000, 0, 20110520));
		stick.add(new StickEntity(328000, 0, 20110519));
		stick.add(new StickEntity(109000, 0, 20110518));
		stick.add(new StickEntity(286000, 0, 20110517));
		stick.add(new StickEntity(103000, 0, 20110516));
		stick.add(new StickEntity(114000, 0, 20110513));
		stick.add(new StickEntity(107000, 0, 20110512));
		stick.add(new StickEntity(106000, 0, 20110511));
		stick.add(new StickEntity(146000, 0, 20110510));
		stick.add(new StickEntity(105000, 0, 20110509));
		stick.add(new StickEntity(312000, 0, 20110506));
		stick.add(new StickEntity(530000, 0, 20110505));
		stick.add(new StickEntity(275000, 0, 20110504));
		stick.add(new StickEntity(432000, 0, 20110503));
		// stick.add(new StickEntity(157000,0,20110429));
		// stick.add(new StickEntity(148000,0,20110428));
		// stick.add(new StickEntity(224000,0,20110427));
		// stick.add(new StickEntity(405000,0,20110426));
		// stick.add(new StickEntity(374000,0,20110425));
		// stick.add(new StickEntity(473000,0,20110422));
		// stick.add(new StickEntity(437000,0,20110421));
		// stick.add(new StickEntity(121000,0,20110420));
		// stick.add(new StickEntity(208000,0,20110419));
		// stick.add(new StickEntity(486000,0,20110418));
		// stick.add(new StickEntity(486000,0,20110415));
		// stick.add(new StickEntity(473000,0,20110414));
		// stick.add(new StickEntity(256000,0,20110413));
		// stick.add(new StickEntity(275000,0,20110412));
		// stick.add(new StickEntity(471000,0,20110411));
		// stick.add(new StickEntity(529000,0,20110408));
		// stick.add(new StickEntity(564000,0,20110407));
		// stick.add(new StickEntity(257000,0,20110406));
		// stick.add(new StickEntity(344000,0,20110404));
		// stick.add(new StickEntity(525000,0,20110401));

		this.vol = new ArrayList<IStickEntity>();
		for (int i = stick.size(); i > 0; i--) {
			this.vol.add(stick.get(i - 1));
		}

		// this.vol.addAll(stick);
	}

	private List<DateValueEntity> initVMA(int days) {
		if (days < 2) {
			return null;
		}

		List<DateValueEntity> MA5Values = new ArrayList<DateValueEntity>();

		float sum = 0;
		float avg = 0;
		for (int i = 0; i < this.vol.size(); i++) {
			float close = (float) vol.get(i).getHigh();
			if (i < days) {
				sum = sum + close;
				avg = sum / (i + 1f);
			} else {
				sum = sum + close - (float) vol.get(i - days).getHigh();
				avg = sum / days;
			}
			MA5Values.add(new DateValueEntity(avg, vol.get(i).getDate()));
		}

		return MA5Values;
	}

	private void initDV1() {
		List<DateValueEntity> dv = new ArrayList<DateValueEntity>();

		this.dv1 = new ArrayList<DateValueEntity>();
		dv.add(new DateValueEntity(947.3056f, 20130424));
		dv.add(new DateValueEntity(952.2242f, 20130425));
		dv.add(new DateValueEntity(963.2635f, 20130426));
		dv.add(new DateValueEntity(961.9385f, 20130502));
		dv.add(new DateValueEntity(962.3391f, 20130503));
		dv.add(new DateValueEntity(961.9631f, 20130506));
		dv.add(new DateValueEntity(961.916f, 20130507));
		dv.add(new DateValueEntity(961.9375f, 20130508));
		dv.add(new DateValueEntity(962.1758f, 20130509));
		dv.add(new DateValueEntity(962.1837f, 20130510));
		dv.add(new DateValueEntity(962.1995f, 20130513));
		dv.add(new DateValueEntity(962.1158f, 20130514));
		dv.add(new DateValueEntity(962.2931f, 20130515));
		dv.add(new DateValueEntity(963.1225f, 20130516));
		dv.add(new DateValueEntity(965.0629f, 20130517));
		dv.add(new DateValueEntity(969.385f, 20130520));
		dv.add(new DateValueEntity(975.5116f, 20130521));
		dv.add(new DateValueEntity(974.0666f, 20130522));
		dv.add(new DateValueEntity(974.2079f, 20130523));
		dv.add(new DateValueEntity(977.2924f, 20130524));
		dv.add(new DateValueEntity(977.4907f, 20130527));
		dv.add(new DateValueEntity(976.429f, 20130528));
		dv.add(new DateValueEntity(977.8235f, 20130529));
		dv.add(new DateValueEntity(981.4609f, 20130530));
		dv.add(new DateValueEntity(983.0612f, 20130531));
		dv.add(new DateValueEntity(978.343f, 20130603));
		dv.add(new DateValueEntity(972.4412f, 20130604));
		dv.add(new DateValueEntity(965.072f, 20130605));
		dv.add(new DateValueEntity(954.1762f, 20130606));
		dv.add(new DateValueEntity(941.5963f, 20130607));
		dv.add(new DateValueEntity(921.8664f, 20130613));
		dv.add(new DateValueEntity(905.6599f, 20130614));
		dv.add(new DateValueEntity(891.2146f, 20130617));
		dv.add(new DateValueEntity(879.2878f, 20130618));
		dv.add(new DateValueEntity(865.2361f, 20130619));
		dv.add(new DateValueEntity(843.2399f, 20130620));
		dv.add(new DateValueEntity(821.4298f, 20130621));
		dv.add(new DateValueEntity(784.0339f, 20130624));
		dv.add(new DateValueEntity(759.5865f, 20130625));
		dv.add(new DateValueEntity(738.5209f, 20130626));
		dv.add(new DateValueEntity(723.5436f, 20130627));
		dv.add(new DateValueEntity(720.2877f, 20130628));
		dv.add(new DateValueEntity(718.5511f, 20130701));
		dv.add(new DateValueEntity(720.9672f, 20130702));
		dv.add(new DateValueEntity(725.9567f, 20130703));
		dv.add(new DateValueEntity(726.3284f, 20130704));
		dv.add(new DateValueEntity(728.0508f, 20130705));
		dv.add(new DateValueEntity(728.961f, 20130708));
		dv.add(new DateValueEntity(730.1062f, 20130709));
		dv.add(new DateValueEntity(734.6287f, 20130710));
		dv.add(new DateValueEntity(736.1662f, 20130711));
		dv.add(new DateValueEntity(739.5985f, 20130712));
		dv.add(new DateValueEntity(743.5045f, 20130715));
		dv.add(new DateValueEntity(749.4669f, 20130716));
		dv.add(new DateValueEntity(753.7623f, 20130717));
		dv.add(new DateValueEntity(753.6917f, 20130718));
		dv.add(new DateValueEntity(752.4678f, 20130719));
		dv.add(new DateValueEntity(760.7568f, 20130722));
		dv.add(new DateValueEntity(765.0131f, 20130723));
		dv.add(new DateValueEntity(768.8569f, 20130724));
		dv.add(new DateValueEntity(770.9514f, 20130725));
		dv.add(new DateValueEntity(768.5318f, 20130726));
		dv.add(new DateValueEntity(762.7225f, 20130729));
		dv.add(new DateValueEntity(759.3295f, 20130730));
		dv.add(new DateValueEntity(757.1793f, 20130731));
		dv.add(new DateValueEntity(756.1526f, 20130801));
		dv.add(new DateValueEntity(755.1125f, 20130802));
		dv.add(new DateValueEntity(756.6308f, 20130805));
		dv.add(new DateValueEntity(757.8153f, 20130806));
		dv.add(new DateValueEntity(757.0371f, 20130807));
		dv.add(new DateValueEntity(763.2288f, 20130808));
		dv.add(new DateValueEntity(764.5119f, 20130809));
		dv.add(new DateValueEntity(767.9202f, 20130812));
		dv.add(new DateValueEntity(770.146f, 20130813));
		dv.add(new DateValueEntity(772.2369f, 20130814));
		dv.add(new DateValueEntity(772.1298f, 20130815));
		dv.add(new DateValueEntity(771.5269f, 20130816));
		dv.add(new DateValueEntity(770.4365f, 20130819));
		dv.add(new DateValueEntity(767.9823f, 20130820));
		dv.add(new DateValueEntity(767.901f, 20130821));
		dv.add(new DateValueEntity(768.2333f, 20130822));
		dv.add(new DateValueEntity(769.7356f, 20130823));
		dv.add(new DateValueEntity(772.7566f, 20130826));
		dv.add(new DateValueEntity(771.9353f, 20130827));
		dv.add(new DateValueEntity(772.5748f, 20130828));
		dv.add(new DateValueEntity(774.17f, 20130829));
		dv.add(new DateValueEntity(776.6239f, 20130830));
		dv.add(new DateValueEntity(779.4005f, 20130902));
		dv.add(new DateValueEntity(782.8205f, 20130903));
		dv.add(new DateValueEntity(787.7852f, 20130904));
		dv.add(new DateValueEntity(795.1398f, 20130905));
		dv.add(new DateValueEntity(798.0329f, 20130906));
		dv.add(new DateValueEntity(777.0803f, 20130909));
		dv.add(new DateValueEntity(745.4303f, 20130910));
		dv.add(new DateValueEntity(733.794f, 20130911));
		dv.add(new DateValueEntity(713.0938f, 20130912));
		dv.add(new DateValueEntity(709.4212f, 20130913));
		dv.add(new DateValueEntity(715.0446f, 20130916));
		dv.add(new DateValueEntity(727.5064f, 20130917));
		dv.add(new DateValueEntity(742.578f, 20130918));
		dv.add(new DateValueEntity(759.8558f, 20130923));
		dv.add(new DateValueEntity(781.4722f, 20130924));
		dv.add(new DateValueEntity(799.6322f, 20130925));
		dv.add(new DateValueEntity(813.7519f, 20130926));
		dv.add(new DateValueEntity(828.4345f, 20130927));
		dv.add(new DateValueEntity(844.6599f, 20130930));
		dv.add(new DateValueEntity(861.8906f, 20131008));
		dv.add(new DateValueEntity(881.4863f, 20131009));
		dv.add(new DateValueEntity(897.0036f, 20131010));
		dv.add(new DateValueEntity(918.4781f, 20131011));
		dv.add(new DateValueEntity(940.6985f, 20131014));
		dv.add(new DateValueEntity(951.0224f, 20131015));
		dv.add(new DateValueEntity(942.7723f, 20131016));
		dv.add(new DateValueEntity(932.7551f, 20131017));
		dv.add(new DateValueEntity(924.7807f, 20131018));
		dv.add(new DateValueEntity(936.6127f, 20131021));
		dv.add(new DateValueEntity(945.5508f, 20131022));
		dv.add(new DateValueEntity(952.1615f, 20131023));
		dv.add(new DateValueEntity(950.4466f, 20131024));
		dv.add(new DateValueEntity(953.2289f, 20131025));
		dv.add(new DateValueEntity(963.9264f, 20131028));
		dv.add(new DateValueEntity(968.6712f, 20131029));
		dv.add(new DateValueEntity(972.3124f, 20131030));
		dv.add(new DateValueEntity(972.3439f, 20131031));
		dv.add(new DateValueEntity(971.8104f, 20131101));
		dv.add(new DateValueEntity(972.5886f, 20131104));

		// for (int i = dv.size(); i > 0; i--) {
		// this.dv1.add(dv.get(i - 1));
		// }

		this.dv1.addAll(dv);
	}

	private void initDV2() {
		List<DateValueEntity> dv = new ArrayList<DateValueEntity>();

		this.dv2 = new ArrayList<DateValueEntity>();
		dv.add(new DateValueEntity(1059.5943f, 20130424));
		dv.add(new DateValueEntity(1046.7757f, 20130425));
		dv.add(new DateValueEntity(1026.9364f, 20130426));
		dv.add(new DateValueEntity(1026.2614f, 20130502));
		dv.add(new DateValueEntity(1024.6608f, 20130503));
		dv.add(new DateValueEntity(1025.8368f, 20130506));
		dv.add(new DateValueEntity(1026.1839f, 20130507));
		dv.add(new DateValueEntity(1026.0624f, 20130508));
		dv.add(new DateValueEntity(1026.3241f, 20130509));
		dv.add(new DateValueEntity(1026.2162f, 20130510));
		dv.add(new DateValueEntity(1026.4004f, 20130513));
		dv.add(new DateValueEntity(1025.9841f, 20130514));
		dv.add(new DateValueEntity(1026.3068f, 20130515));
		dv.add(new DateValueEntity(1028.6774f, 20130516));
		dv.add(new DateValueEntity(1031.737f, 20130517));
		dv.add(new DateValueEntity(1035.6149f, 20130520));
		dv.add(new DateValueEntity(1036.5883f, 20130521));
		dv.add(new DateValueEntity(1040.2333f, 20130522));
		dv.add(new DateValueEntity(1039.392f, 20130523));
		dv.add(new DateValueEntity(1039.9075f, 20130524));
		dv.add(new DateValueEntity(1042.3092f, 20130527));
		dv.add(new DateValueEntity(1049.7709f, 20130528));
		dv.add(new DateValueEntity(1054.7764f, 20130529));
		dv.add(new DateValueEntity(1058.339f, 20130530));
		dv.add(new DateValueEntity(1061.3387f, 20130531));
		dv.add(new DateValueEntity(1063.1569f, 20130603));
		dv.add(new DateValueEntity(1065.8587f, 20130604));
		dv.add(new DateValueEntity(1069.2279f, 20130605));
		dv.add(new DateValueEntity(1074.9237f, 20130606));
		dv.add(new DateValueEntity(1080.7036f, 20130607));
		dv.add(new DateValueEntity(1090.4335f, 20130613));
		dv.add(new DateValueEntity(1097.14f, 20130614));
		dv.add(new DateValueEntity(1101.7853f, 20130617));
		dv.add(new DateValueEntity(1102.9121f, 20130618));
		dv.add(new DateValueEntity(1103.4638f, 20130619));
		dv.add(new DateValueEntity(1105.56f, 20130620));
		dv.add(new DateValueEntity(1106.7701f, 20130621));
		dv.add(new DateValueEntity(1115.766f, 20130624));
		dv.add(new DateValueEntity(1116.7134f, 20130625));
		dv.add(new DateValueEntity(1113.479f, 20130626));
		dv.add(new DateValueEntity(1104.3563f, 20130627));
		dv.add(new DateValueEntity(1084.9122f, 20130628));
		dv.add(new DateValueEntity(1063.1488f, 20130701));
		dv.add(new DateValueEntity(1036.8327f, 20130702));
		dv.add(new DateValueEntity(1007.5432f, 20130703));
		dv.add(new DateValueEntity(989.9715f, 20130704));
		dv.add(new DateValueEntity(971.9491f, 20130705));
		dv.add(new DateValueEntity(953.6389f, 20130708));
		dv.add(new DateValueEntity(937.1937f, 20130709));
		dv.add(new DateValueEntity(920.2712f, 20130710));
		dv.add(new DateValueEntity(917.1337f, 20130711));
		dv.add(new DateValueEntity(908.4014f, 20130712));
		dv.add(new DateValueEntity(899.9954f, 20130715));
		dv.add(new DateValueEntity(888.733f, 20130716));
		dv.add(new DateValueEntity(880.0376f, 20130717));
		dv.add(new DateValueEntity(877.9082f, 20130718));
		dv.add(new DateValueEntity(876.6321f, 20130719));
		dv.add(new DateValueEntity(872.6431f, 20130722));
		dv.add(new DateValueEntity(871.7868f, 20130723));
		dv.add(new DateValueEntity(870.243f, 20130724));
		dv.add(new DateValueEntity(869.5485f, 20130725));
		dv.add(new DateValueEntity(868.8681f, 20130726));
		dv.add(new DateValueEntity(870.3774f, 20130729));
		dv.add(new DateValueEntity(870.6704f, 20130730));
		dv.add(new DateValueEntity(871.0206f, 20130731));
		dv.add(new DateValueEntity(870.7473f, 20130801));
		dv.add(new DateValueEntity(870.4874f, 20130802));
		dv.add(new DateValueEntity(870.4691f, 20130805));
		dv.add(new DateValueEntity(870.3846f, 20130806));
		dv.add(new DateValueEntity(870.1628f, 20130807));
		dv.add(new DateValueEntity(855.2711f, 20130808));
		dv.add(new DateValueEntity(849.188f, 20130809));
		dv.add(new DateValueEntity(843.2797f, 20130812));
		dv.add(new DateValueEntity(839.8539f, 20130813));
		dv.add(new DateValueEntity(836.363f, 20130814));
		dv.add(new DateValueEntity(836.6701f, 20130815));
		dv.add(new DateValueEntity(840.073f, 20130816));
		dv.add(new DateValueEntity(846.1634f, 20130819));
		dv.add(new DateValueEntity(852.3176f, 20130820));
		dv.add(new DateValueEntity(856.8989f, 20130821));
		dv.add(new DateValueEntity(860.7666f, 20130822));
		dv.add(new DateValueEntity(863.7643f, 20130823));
		dv.add(new DateValueEntity(870.7433f, 20130826));
		dv.add(new DateValueEntity(882.6646f, 20130827));
		dv.add(new DateValueEntity(893.4251f, 20130828));
		dv.add(new DateValueEntity(901.1299f, 20130829));
		dv.add(new DateValueEntity(908.776f, 20130830));
		dv.add(new DateValueEntity(915.0994f, 20130902));
		dv.add(new DateValueEntity(922.2794f, 20130903));
		dv.add(new DateValueEntity(928.6147f, 20130904));
		dv.add(new DateValueEntity(932.6601f, 20130905));
		dv.add(new DateValueEntity(945.367f, 20130906));
		dv.add(new DateValueEntity(988.5196f, 20130909));
		dv.add(new DateValueEntity(1049.9696f, 20130910));
		dv.add(new DateValueEntity(1091.2059f, 20130911));
		dv.add(new DateValueEntity(1152.2061f, 20130912));
		dv.add(new DateValueEntity(1191.8787f, 20130913));
		dv.add(new DateValueEntity(1216.6553f, 20130916));
		dv.add(new DateValueEntity(1227.3935f, 20130917));
		dv.add(new DateValueEntity(1237.8219f, 20130918));
		dv.add(new DateValueEntity(1247.9441f, 20130923));
		dv.add(new DateValueEntity(1250.2277f, 20130924));
		dv.add(new DateValueEntity(1252.4677f, 20130925));
		dv.add(new DateValueEntity(1250.548f, 20130926));
		dv.add(new DateValueEntity(1248.2654f, 20130927));
		dv.add(new DateValueEntity(1243.74f, 20130930));
		dv.add(new DateValueEntity(1238.9093f, 20131008));
		dv.add(new DateValueEntity(1232.3136f, 20131009));
		dv.add(new DateValueEntity(1225.6963f, 20131010));
		dv.add(new DateValueEntity(1218.3218f, 20131011));
		dv.add(new DateValueEntity(1207.7014f, 20131014));
		dv.add(new DateValueEntity(1202.7775f, 20131015));
		dv.add(new DateValueEntity(1204.8276f, 20131016));
		dv.add(new DateValueEntity(1199.0448f, 20131017));
		dv.add(new DateValueEntity(1193.1192f, 20131018));
		dv.add(new DateValueEntity(1159.9872f, 20131021));
		dv.add(new DateValueEntity(1131.2491f, 20131022));
		dv.add(new DateValueEntity(1109.9384f, 20131023));
		dv.add(new DateValueEntity(1103.1533f, 20131024));
		dv.add(new DateValueEntity(1091.071f, 20131025));
		dv.add(new DateValueEntity(1070.0735f, 20131028));
		dv.add(new DateValueEntity(1062.0287f, 20131029));
		dv.add(new DateValueEntity(1056.4875f, 20131030));
		dv.add(new DateValueEntity(1058.056f, 20131031));
		dv.add(new DateValueEntity(1060.3895f, 20131101));
		dv.add(new DateValueEntity(1061.7113f, 20131104));

		// for (int i = dv.size(); i > 0; i--) {
		// this.dv2.add(dv.get(i - 1));
		// }

		this.dv2.addAll(dv);
	}

	private void initOHLC() {
		List<IStickEntity> ohlc = new ArrayList<IStickEntity>();

		this.ohlc = new ArrayList<IStickEntity>();
		ohlc.add(new OHLCEntity(986, 1015, 977, 1003, 20130424));
		ohlc.add(new OHLCEntity(1000, 1007, 982, 991, 20130425));
		ohlc.add(new OHLCEntity(996, 1001, 985, 988, 20130426));
		ohlc.add(new OHLCEntity(977, 986, 966, 982, 20130502));
		ohlc.add(new OHLCEntity(987, 1017, 983, 1001, 20130503));
		ohlc.add(new OHLCEntity(1003, 1021, 997, 1013, 20130506));
		ohlc.add(new OHLCEntity(1009, 1010, 998, 1006, 20130507));
		ohlc.add(new OHLCEntity(1012, 1020, 1001, 1005, 20130508));
		ohlc.add(new OHLCEntity(1006, 1008, 989, 997, 20130509));
		ohlc.add(new OHLCEntity(993, 1006, 989, 1003, 20130510));
		ohlc.add(new OHLCEntity(1002, 1011, 993, 1002, 20130513));
		ohlc.add(new OHLCEntity(1003, 1005, 993, 997, 20130514));
		ohlc.add(new OHLCEntity(998, 1002, 993, 999, 20130515));
		ohlc.add(new OHLCEntity(999, 1016, 984, 1015, 20130516));
		ohlc.add(new OHLCEntity(1015, 1028, 1005, 1024, 20130517));
		ohlc.add(new OHLCEntity(1026, 1054, 1020, 1041, 20130520));
		ohlc.add(new OHLCEntity(1038, 1042, 1024, 1034, 20130521));
		ohlc.add(new OHLCEntity(1033, 1038, 1028, 1036, 20130522));
		ohlc.add(new OHLCEntity(1029, 1033, 1015, 1015, 20130523));
		ohlc.add(new OHLCEntity(1020, 1028, 1010, 1020, 20130524));
		ohlc.add(new OHLCEntity(1021, 1033, 1018, 1029, 20130527));
		ohlc.add(new OHLCEntity(1030, 1056, 1025, 1055, 20130528));
		ohlc.add(new OHLCEntity(1058, 1062, 1051, 1052, 20130529));
		ohlc.add(new OHLCEntity(1048, 1062, 1047, 1054, 20130530));
		ohlc.add(new OHLCEntity(1056, 1062, 1046, 1047, 20130531));
		ohlc.add(new OHLCEntity(997, 1001, 981, 984, 20130603));
		ohlc.add(new OHLCEntity(989, 989, 970, 974, 20130604));
		ohlc.add(new OHLCEntity(974, 977, 960, 965, 20130605));
		ohlc.add(new OHLCEntity(961, 967, 942, 945, 20130606));
		ohlc.add(new OHLCEntity(951, 957, 932, 935, 20130607));
		ohlc.add(new OHLCEntity(925, 925, 891, 902, 20130613));
		ohlc.add(new OHLCEntity(907, 907, 898, 902, 20130614));
		ohlc.add(new OHLCEntity(905, 910, 896, 901, 20130617));
		ohlc.add(new OHLCEntity(905, 912, 901, 907, 20130618));
		ohlc.add(new OHLCEntity(905, 905, 882, 889, 20130619));
		ohlc.add(new OHLCEntity(886, 886, 840, 842, 20130620));
		ohlc.add(new OHLCEntity(831, 847, 822, 828, 20130621));
		ohlc.add(new OHLCEntity(829, 829, 750, 752, 20130624));
		ohlc.add(new OHLCEntity(745, 784, 718, 780, 20130625));
		ohlc.add(new OHLCEntity(790, 795, 763, 777, 20130626));
		ohlc.add(new OHLCEntity(785, 792, 770, 788, 20130627));
		ohlc.add(new OHLCEntity(782, 830, 776, 828, 20130628));
		ohlc.add(new OHLCEntity(822, 827, 807, 817, 20130701));
		ohlc.add(new OHLCEntity(818, 822, 795, 815, 20130702));
		ohlc.add(new OHLCEntity(810, 811, 797, 804, 20130703));
		ohlc.add(new OHLCEntity(806, 828, 802, 812, 20130704));
		ohlc.add(new OHLCEntity(811, 822, 808, 811, 20130705));
		ohlc.add(new OHLCEntity(800, 805, 790, 791, 20130708));
		ohlc.add(new OHLCEntity(792, 796, 788, 792, 20130709));
		ohlc.add(new OHLCEntity(795, 813, 790, 811, 20130710));
		ohlc.add(new OHLCEntity(817, 892, 817, 886, 20130711));
		ohlc.add(new OHLCEntity(876, 885, 843, 849, 20130712));
		ohlc.add(new OHLCEntity(855, 871, 841, 856, 20130715));
		ohlc.add(new OHLCEntity(852, 855, 841, 854, 20130716));
		ohlc.add(new OHLCEntity(852, 855, 838, 845, 20130717));
		ohlc.add(new OHLCEntity(841, 845, 816, 820, 20130718));
		ohlc.add(new OHLCEntity(822, 824, 802, 803, 20130719));
		ohlc.add(new OHLCEntity(790, 799, 782, 795, 20130722));
		ohlc.add(new OHLCEntity(799, 823, 794, 814, 20130723));
		ohlc.add(new OHLCEntity(804, 809, 790, 800, 20130724));
		ohlc.add(new OHLCEntity(802, 811, 796, 802, 20130725));
		ohlc.add(new OHLCEntity(798, 801, 794, 797, 20130726));
		ohlc.add(new OHLCEntity(790, 790, 771, 774, 20130729));
		ohlc.add(new OHLCEntity(778, 796, 774, 784, 20130730));
		ohlc.add(new OHLCEntity(791, 802, 782, 786, 20130731));
		ohlc.add(new OHLCEntity(792, 802, 787, 799, 20130801));
		ohlc.add(new OHLCEntity(806, 812, 797, 798, 20130802));
		ohlc.add(new OHLCEntity(798, 807, 795, 806, 20130805));
		ohlc.add(new OHLCEntity(803, 808, 798, 803, 20130806));
		ohlc.add(new OHLCEntity(803, 814, 800, 801, 20130807));
		ohlc.add(new OHLCEntity(801, 807, 795, 799, 20130808));
		ohlc.add(new OHLCEntity(805, 808, 796, 801, 20130809));
		ohlc.add(new OHLCEntity(804, 832, 801, 831, 20130812));
		ohlc.add(new OHLCEntity(830, 843, 827, 842, 20130813));
		ohlc.add(new OHLCEntity(844, 853, 830, 831, 20130814));
		ohlc.add(new OHLCEntity(831, 837, 820, 822, 20130815));
		ohlc.add(new OHLCEntity(817, 904, 815, 831, 20130816));
		ohlc.add(new OHLCEntity(824, 850, 823, 845, 20130819));
		ohlc.add(new OHLCEntity(842, 878, 839, 851, 20130820));
		ohlc.add(new OHLCEntity(853, 858, 837, 845, 20130821));
		ohlc.add(new OHLCEntity(841, 862, 840, 844, 20130822));
		ohlc.add(new OHLCEntity(854, 863, 825, 842, 20130823));
		ohlc.add(new OHLCEntity(845, 878, 840, 874, 20130826));
		ohlc.add(new OHLCEntity(875, 905, 870, 895, 20130827));
		ohlc.add(new OHLCEntity(888, 915, 879, 900, 20130828));
		ohlc.add(new OHLCEntity(911, 921, 886, 892, 20130829));
		ohlc.add(new OHLCEntity(886, 905, 876, 899, 20130830));
		ohlc.add(new OHLCEntity(911, 929, 895, 897, 20130902));
		ohlc.add(new OHLCEntity(896, 912, 889, 909, 20130903));
		ohlc.add(new OHLCEntity(904, 924, 903, 914, 20130904));
		ohlc.add(new OHLCEntity(919, 919, 906, 913, 20130905));
		ohlc.add(new OHLCEntity(915, 987, 912, 957, 20130906));
		ohlc.add(new OHLCEntity(1028, 1053, 1018, 1053, 20130909));
		ohlc.add(new OHLCEntity(1100, 1149, 1077, 1140, 20130910));
		ohlc.add(new OHLCEntity(1121, 1147, 1120, 1127, 20130911));
		ohlc.add(new OHLCEntity(1130, 1240, 1116, 1225, 20130912));
		ohlc.add(new OHLCEntity(1208, 1227, 1173, 1191, 20130913));
		ohlc.add(new OHLCEntity(1200, 1202, 1123, 1149, 20130916));
		ohlc.add(new OHLCEntity(1141, 1148, 1077, 1083, 20130917));
		ohlc.add(new OHLCEntity(1095, 1119, 1083, 1100, 20130918));
		ohlc.add(new OHLCEntity(1105, 1120, 1080, 1118, 20130923));
		ohlc.add(new OHLCEntity(1119, 1120, 1057, 1081, 20130924));
		ohlc.add(new OHLCEntity(1074, 1118, 1069, 1078, 20130925));
		ohlc.add(new OHLCEntity(1075, 1076, 1007, 1017, 20130926));
		ohlc.add(new OHLCEntity(1011, 1033, 1005, 1024, 20130927));
		ohlc.add(new OHLCEntity(1034, 1037, 1002, 1009, 20130930));
		ohlc.add(new OHLCEntity(1003, 1033, 988, 1023, 20131008));
		ohlc.add(new OHLCEntity(1010, 1046, 1007, 1027, 20131009));
		ohlc.add(new OHLCEntity(1030, 1035, 993, 998, 20131010));
		ohlc.add(new OHLCEntity(1010, 1065, 1000, 1055, 20131011));
		ohlc.add(new OHLCEntity(1045, 1050, 1025, 1029, 20131014));
		ohlc.add(new OHLCEntity(1030, 1035, 1002, 1011, 20131015));
		ohlc.add(new OHLCEntity(1009, 1009, 982, 991, 20131016));
		ohlc.add(new OHLCEntity(1001, 1007, 981, 982, 20131017));
		ohlc.add(new OHLCEntity(982, 1006, 980, 988, 20131018));
		ohlc.add(new OHLCEntity(995, 1016, 980, 1012, 20131021));
		ohlc.add(new OHLCEntity(1011, 1011, 986, 993, 20131022));
		ohlc.add(new OHLCEntity(995, 1035, 991, 1002, 20131023));
		ohlc.add(new OHLCEntity(996, 1016, 982, 998, 20131024));
		ohlc.add(new OHLCEntity(1001, 1026, 999, 1007, 20131025));
		ohlc.add(new OHLCEntity(1008, 1022, 992, 1015, 20131028));
		ohlc.add(new OHLCEntity(1022, 1069, 1018, 1048, 20131029));
		ohlc.add(new OHLCEntity(1048, 1062, 1031, 1059, 20131030));
		ohlc.add(new OHLCEntity(1058, 1060, 1031, 1033, 20131031));
		ohlc.add(new OHLCEntity(1032, 1053, 1023, 1042, 20131101));
		ohlc.add(new OHLCEntity(1048, 1054, 1026, 1030, 20131104));

		// for (int i = ohlc.size(); i > 0; i--) {
		// this.ohlc.add(ohlc.get(i - 1));
		// }

		this.ohlc.addAll(ohlc);
	}

	private List<DateValueEntity> initMA(int days) {

		if (days < 2) {
			return null;
		}

		List<DateValueEntity> MA5Values = new ArrayList<DateValueEntity>();

		float sum = 0;
		float avg = 0;
		for (int i = 0; i < this.ohlc.size(); i++) {
			float close = (float) ((OHLCEntity) ohlc.get(i)).getClose();
			if (i < days) {
				sum = sum + close;
				avg = sum / (i + 1f);
			} else {
				sum = sum + close
						- (float) ((OHLCEntity) ohlc.get(i - days)).getClose();
				avg = sum / days;
			}
			MA5Values.add(new DateValueEntity(avg, ohlc.get(i).getDate()));
		}

		return MA5Values;
	}

	private void initMACD() {
		List<IStickEntity> macd = new ArrayList<IStickEntity>();
		macd.add(new MACDEntity(46934, 7297, -79272, 20130604));
		macd.add(new MACDEntity(30276, -36354, -133260, 20130605));
		macd.add(new MACDEntity(7002, -86094, -186192, 20130606));
		macd.add(new MACDEntity(-20810, -132060, -222500, 20130607));
		macd.add(new MACDEntity(-55227, -192894, -275332, 20130613));
		macd.add(new MACDEntity(-91853, -238357, -293008, 20130614));
		macd.add(new MACDEntity(-127894, -272058, -288326, 20130617));
		macd.add(new MACDEntity(-160430, -290575, -260288, 20130618));
		macd.add(new MACDEntity(-191570, -316130, -249118, 20130619));
		macd.add(new MACDEntity(-227264, -370042, -285554, 20130620));
		macd.add(new MACDEntity(-265658, -419232, -307148, 20130621));
		macd.add(new MACDEntity(-315250, -513620, -396738, 20130624));
		macd.add(new MACDEntity(-364077, -559382, -390610, 20130625));
		macd.add(new MACDEntity(-409512, -591253, -363482, 20130626));
		macd.add(new MACDEntity(-447752, -600711, -305918, 20130627));
		macd.add(new MACDEntity(-472075, -569366, -194582, 20130628));
		macd.add(new MACDEntity(-487079, -547095, -120032, 20130701));
		macd.add(new MACDEntity(-494664, -525007, -60684, 20130702));
		macd.add(new MACDEntity(-497830, -510493, -25324, 20130703));
		macd.add(new MACDEntity(-495648, -486922, 17452, 20130704));
		macd.add(new MACDEntity(-489260, -463704, 51110, 20130705));
		macd.add(new MACDEntity(-482644, -456183, 52922, 20130708));
		macd.add(new MACDEntity(-474974, -444294, 61358, 20130709));
		macd.add(new MACDEntity(-462931, -414760, 96342, 20130710));
		macd.add(new MACDEntity(-435758, -327065, 217386, 20130711));
		macd.add(new MACDEntity(-405436, -284146, 242578, 20130712));
		macd.add(new MACDEntity(-372688, -241698, 261980, 20130715));
		macd.add(new MACDEntity(-339607, -207282, 264648, 20130716));
		macd.add(new MACDEntity(-308713, -185136, 247154, 20130717));
		macd.add(new MACDEntity(-284094, -185617, 196952, 20130718));
		macd.add(new MACDEntity(-266763, -197441, 138644, 20130719));
		macd.add(new MACDEntity(-255578, -210836, 89482, 20130722));
		macd.add(new MACDEntity(-245216, -203771, 82890, 20130723));
		macd.add(new MACDEntity(-237590, -207082, 61014, 20130724));
		macd.add(new MACDEntity(-231216, -205721, 50988, 20130725));
		macd.add(new MACDEntity(-226232, -206298, 39866, 20130726));
		macd.add(new MACDEntity(-225535, -222748, 5574, 20130729));
		macd.add(new MACDEntity(-225452, -225119, 664, 20130730));
		macd.add(new MACDEntity(-224925, -222817, 4216, 20130731));
		macd.add(new MACDEntity(-221561, -208103, 26914, 20130801));
		macd.add(new MACDEntity(-216249, -195002, 42494, 20130802));
		macd.add(new MACDEntity(-208226, -176133, 64184, 20130805));
		macd.add(new MACDEntity(-198928, -161735, 74384, 20130806));
		macd.add(new MACDEntity(-189184, -150208, 77950, 20130807));
		macd.add(new MACDEntity(-179559, -141060, 76998, 20130808));
		macd.add(new MACDEntity(-169785, -130690, 78190, 20130809));
		macd.add(new MACDEntity(-155257, -97144, 116224, 20130812));
		macd.add(new MACDEntity(-136401, -60980, 150842, 20130813));
		macd.add(new MACDEntity(-117266, -40726, 153080, 20130814));
		macd.add(new MACDEntity(-100128, -31573, 137108, 20130815));
		macd.add(new MACDEntity(-83475, -16862, 133224, 20130816));
		macd.add(new MACDEntity(-65575, 6022, 143196, 20130819));
		macd.add(new MACDEntity(-46726, 28670, 150792, 20130820));
		macd.add(new MACDEntity(-29120, 41301, 140844, 20130821));
		macd.add(new MACDEntity(-13310, 49929, 126480, 20130822));
		macd.add(new MACDEntity(256, 54524, 108536, 20130823));
		macd.add(new MACDEntity(16811, 83030, 132438, 20130826));
		macd.add(new MACDEntity(37683, 121170, 166974, 20130827));
		macd.add(new MACDEntity(60878, 153659, 185562, 20130828));
		macd.add(new MACDEntity(82898, 170981, 176164, 20130829));
		macd.add(new MACDEntity(103956, 188187, 168462, 20130830));
		macd.add(new MACDEntity(122751, 197928, 150354, 20130902));
		macd.add(new MACDEntity(140776, 212877, 144202, 20130903));
		macd.add(new MACDEntity(157851, 226152, 136600, 20130904));
		macd.add(new MACDEntity(172916, 233177, 120520, 20130905));
		macd.add(new MACDEntity(192558, 271124, 157132, 20130906));
		macd.add(new MACDEntity(228915, 374346, 290860, 20130909));
		macd.add(new MACDEntity(287203, 520353, 466300, 20130910));
		macd.add(new MACDEntity(353452, 618446, 529988, 20130911));
		macd.add(new MACDEntity(436047, 766428, 660762, 20130912));
		macd.add(new MACDEntity(518140, 846512, 656744, 20130913));
		macd.add(new MACDEntity(587733, 866105, 556742, 20130916));
		macd.add(new MACDEntity(633973, 818935, 369922, 20130917));
		macd.add(new MACDEntity(664420, 786208, 243574, 20130918));
		macd.add(new MACDEntity(684729, 765966, 162472, 20130923));
		macd.add(new MACDEntity(690156, 711862, 43412, 20130924));
		macd.add(new MACDEntity(683918, 658968, -49900, 20130925));
		macd.add(new MACDEntity(659406, 561356, -196100, 20130926));
		macd.add(new MACDEntity(624338, 484066, -280542, 20130927));
		macd.add(new MACDEntity(580676, 406029, -349294, 20130930));
		macd.add(new MACDEntity(534827, 351430, -366792, 20131008));
		macd.add(new MACDEntity(489429, 307839, -363180, 20131009));
		macd.add(new MACDEntity(440952, 247044, -387816, 20131010));
		macd.add(new MACDEntity(401175, 242068, -318214, 20131011));
		macd.add(new MACDEntity(363874, 214670, -298408, 20131014));
		macd.add(new MACDEntity(326379, 176398, -299960, 20131015));
		macd.add(new MACDEntity(286793, 128449, -316686, 20131016));
		macd.add(new MACDEntity(245882, 82239, -327286, 20131017));
		macd.add(new MACDEntity(206682, 49883, -313598, 20131018));
		macd.add(new MACDEntity(173968, 43110, -261714, 20131021));
		macd.add(new MACDEntity(143606, 22156, -242898, 20131022));
		macd.add(new MACDEntity(117418, 12666, -209504, 20131023));
		macd.add(new MACDEntity(94313, 1895, -184836, 20131024));
		macd.add(new MACDEntity(75573, 614, -149918, 20131025));
		macd.add(new MACDEntity(61656, 5986, -111340, 20131028));
		macd.add(new MACDEntity(56615, 36451, -40328, 20131029));
		macd.add(new MACDEntity(59027, 68679, 19302, 20131030));
		macd.add(new MACDEntity(61703, 72405, 21404, 20131031));
		macd.add(new MACDEntity(65698, 81679, 31960, 20131101));
		macd.add(new MACDEntity(68247, 78442, 20388, 20131104));

		this.macd = macd;
	}
}