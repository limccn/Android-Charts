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

import cn.limc.androidcharts.entity.ColoredStickEntity;
import cn.limc.androidcharts.entity.DateValueEntity;
import cn.limc.androidcharts.entity.LineEntity;
import cn.limc.androidcharts.entity.OHLCEntity;
import cn.limc.androidcharts.entity.StickEntity;
import cn.limc.androidcharts.entity.TitleValueColorEntity;
import cn.limc.androidcharts.entity.TitleValueEntity;
import cn.limc.androidcharts.view.CandleStickChart;
import cn.limc.androidcharts.view.ColoredSlipStickChart;
import cn.limc.androidcharts.view.GridChart;
import cn.limc.androidcharts.view.LineChart;
import cn.limc.androidcharts.view.MACandleStickChart;
import cn.limc.androidcharts.view.MASlipCandleStickChart;
import cn.limc.androidcharts.view.MASlipStickChart;
import cn.limc.androidcharts.view.MAStickChart;
import cn.limc.androidcharts.view.MinusStickChart;
import cn.limc.androidcharts.view.PieChart;
import cn.limc.androidcharts.view.SlipCandleStickChart;
import cn.limc.androidcharts.view.SlipLineChart;
import cn.limc.androidcharts.view.SlipMinusStickChart;
import cn.limc.androidcharts.view.SlipStickChart;
import cn.limc.androidcharts.view.SpiderWebChart;
import cn.limc.androidcharts.view.StickChart;


import cn.limc.androidcharts.R;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;


public class AndroidChartsActivity extends Activity {
	
	List<OHLCEntity> ohlc;
	List<StickEntity> vol;
	List<ColoredStickEntity> volc;
	List<DateValueEntity> dv1;
	List<DateValueEntity> dv2;
	
	GridChart gridchart;
	LineChart linechart;
	SlipLineChart sliplinechart;
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
	PieChart piechart;
	SpiderWebChart spiderwebchart;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        initVOL();
        initOHLC();
        initVOLC();
        initDV1();
        initDV2();
       
        initGridChart();
	    initLineChart();
	    initSlipLineChart();
	    initStickChart();
	    initSlipStickChart();
	    initColoredSlipStickChart();
	    initMAStickChart();
	    initMASlipStickChart();
	    initMinusStickChart();
	    initSlipMinusStickChart();
	    initCandleStickChart();
	    initSlipCandleStickChart();
	    initMACandleStickChart();
	    initMASlipCandleStickChart();
        initPieChart();
        initSpiderWebChart();
        
    }
    
    private void initGridChart()
	{
    		this.gridchart= (GridChart)findViewById(R.id.gridchart);

		List<String> ytitle=new ArrayList<String>();
		ytitle.add("241");
		ytitle.add("242");
		ytitle.add("243");
		ytitle.add("244");
		ytitle.add("245");
		List<String> xtitle=new ArrayList<String>();
		xtitle.add("9:00");
		xtitle.add("10:00");
		xtitle.add("11:00");
		xtitle.add("13:00");
		xtitle.add("14:00");
		xtitle.add("15:00");
		xtitle.add(" ");
        
		gridchart.setAxisXColor(Color.LTGRAY);
		gridchart.setAxisYColor(Color.LTGRAY);
		gridchart.setBorderColor(Color.LTGRAY);
		gridchart.setAxisMarginTop(10);
		gridchart.setAxisMarginLeft(20);
		gridchart.setAxisYTitles(ytitle);
		gridchart.setAxisXTitles(xtitle);
		gridchart.setLongitudeFontSize(10);
		gridchart.setLongitudeFontColor(Color.WHITE);
		gridchart.setLatitudeColor(Color.GRAY);
		gridchart.setLatitudeFontColor(Color.WHITE);
		gridchart.setLongitudeColor(Color.GRAY);
		gridchart.setDisplayAxisXTitle(true);
		gridchart.setDisplayAxisYTitle(true);
		gridchart.setDisplayLatitude(true);
		gridchart.setDisplayLongitude(true);
	}
    
	private void initLineChart()
	{
        this.linechart = (LineChart)findViewById(R.id.linechart);
        List<LineEntity<Float>> lines = new ArrayList<LineEntity<Float>>();
        
        //计算5日均线
        LineEntity<Float> MA5 = new LineEntity<Float>();
        MA5.setTitle("MA5");
        MA5.setLineColor(Color.WHITE);
        MA5.setLineData(initMA(5));
        lines.add(MA5);
        
        //计算10日均线
        LineEntity<Float> MA10 = new LineEntity<Float>();
        MA10.setTitle("MA10");
        MA10.setLineColor(Color.RED);
        MA10.setLineData(initMA(10));
        lines.add(MA10);
        
        //计算25日均线
        LineEntity<Float> MA25 = new LineEntity<Float>();
        MA25.setTitle("MA25");
        MA25.setLineColor(Color.GREEN);
        MA25.setLineData(initMA(25));
        lines.add(MA25);
        
		List<String> ytitle=new ArrayList<String>();
		ytitle.add("240");
		ytitle.add("250");
		ytitle.add("260");
		ytitle.add("270");
		ytitle.add("280");
		List<String> xtitle=new ArrayList<String>();
		xtitle.add("9:00");
		xtitle.add("10:00");
		xtitle.add("11:00");
		xtitle.add("13:00");
		xtitle.add("14:00");
		xtitle.add("15:00");
		xtitle.add(" ");
        
		linechart.setAxisXColor(Color.LTGRAY);
		linechart.setAxisYColor(Color.LTGRAY);
		linechart.setBorderColor(Color.LTGRAY);
		linechart.setAxisMarginTop(10);
		linechart.setAxisMarginLeft(20);
		linechart.setAxisYTitles(ytitle);
		linechart.setAxisXTitles(xtitle);
		linechart.setLongitudeFontSize(10);
		linechart.setLongitudeFontColor(Color.WHITE);
		linechart.setLatitudeColor(Color.GRAY);
		linechart.setLatitudeFontColor(Color.WHITE);
		linechart.setLongitudeColor(Color.GRAY);
		linechart.setMaxValue(280);
		linechart.setMinValue(240);
		linechart.setMaxPointNum(36);
		linechart.setDisplayAxisXTitle(true);
		linechart.setDisplayAxisYTitle(true);
		linechart.setDisplayLatitude(true);
		linechart.setDisplayLongitude(true);
        
        //为chart1增加均线
		linechart.setLineData(lines);
	}
	
	private void initSlipLineChart()
	{
        this.sliplinechart = (SlipLineChart)findViewById(R.id.sliplinechart);
        List<LineEntity<DateValueEntity>> lines = new ArrayList<LineEntity<DateValueEntity>>();
        
        //计算5日均线
        LineEntity<DateValueEntity> MA5 = new LineEntity<DateValueEntity>();
        MA5.setTitle("HIGH");
        MA5.setLineColor(Color.WHITE);
        MA5.setLineData(dv1);
        lines.add(MA5);
        
        //计算10日均线
        LineEntity<DateValueEntity> MA10 = new LineEntity<DateValueEntity>();
        MA10.setTitle("LOW");
        MA10.setLineColor(Color.RED);
        MA10.setLineData(dv2);
        lines.add(MA10);
        
		sliplinechart.setAxisXColor(Color.LTGRAY);
		sliplinechart.setAxisYColor(Color.LTGRAY);
		sliplinechart.setBorderColor(Color.LTGRAY);
		sliplinechart.setAxisMarginTop(10);
		sliplinechart.setAxisMarginLeft(20);
//		sliplinechart.setLatitudeNum(2);
//		sliplinechart.setLongitudeNum(3);
		sliplinechart.setLongitudeFontSize(10);
		sliplinechart.setLongitudeFontColor(Color.WHITE);
		sliplinechart.setLatitudeColor(Color.GRAY);
		sliplinechart.setLatitudeFontColor(Color.WHITE);
		sliplinechart.setLongitudeColor(Color.GRAY);
		sliplinechart.setMaxValue(280);
		sliplinechart.setMinValue(220);
		sliplinechart.setDisplayFrom(10);
		sliplinechart.setDisplayNumber(30);
		sliplinechart.setMinDisplayNumber(5);
		sliplinechart.setZoomBaseLine(0);
		sliplinechart.setDisplayAxisXTitle(true);
		sliplinechart.setDisplayAxisYTitle(true);
		sliplinechart.setDisplayLatitude(true);
		sliplinechart.setDisplayLongitude(true);
        
		sliplinechart.setLinesData(lines);
	}
	
	private void initStickChart()
	{
       this.stickchart = (StickChart)findViewById(R.id.stickchart);
     
       stickchart.setAxisXColor(Color.LTGRAY);
       stickchart.setAxisYColor(Color.LTGRAY);
       stickchart.setLatitudeColor(Color.GRAY);
       stickchart.setLongitudeColor(Color.GRAY);
       stickchart.setBorderColor(Color.LTGRAY);
       stickchart.setLongitudeFontColor(Color.WHITE);
       stickchart.setLatitudeFontColor(Color.WHITE);
       stickchart.setStickFillColor(getResources().getColor(R.drawable.yellow));
       stickchart.setAxisMarginTop(5);
       stickchart.setAxisMarginRight(1);
       
       //最大显示足数
       stickchart.setMaxSticksNum(52);
       //最大纬线数
       stickchart.setLatitudeNum(2);
       //最大经线数
       stickchart.setLongitudeNum(3);
       //最大价格
       stickchart.setMaxValue(10000);
       //最小价格
       stickchart.setMinValue(100);
       
       stickchart.setDisplayAxisXTitle(true);
       stickchart.setDisplayAxisYTitle(true);
       stickchart.setDisplayLatitude(true);
       stickchart.setDisplayLongitude(true);
       stickchart.setBackgroundColor(Color.BLACK);
       
      //为chart1增加均线
       stickchart.setStickData(vol);
	}
	
	private void initSlipStickChart()
	{
       this.slipstickchart = (SlipStickChart)findViewById(R.id.slipstickchart);
     
       slipstickchart.setAxisXColor(Color.LTGRAY);
       slipstickchart.setAxisYColor(Color.LTGRAY);
       slipstickchart.setLatitudeColor(Color.GRAY);
       slipstickchart.setLongitudeColor(Color.GRAY);
       slipstickchart.setBorderColor(Color.LTGRAY);
       slipstickchart.setLongitudeFontColor(Color.WHITE);
       slipstickchart.setLatitudeFontColor(Color.WHITE);
       slipstickchart.setStickFillColor(getResources().getColor(R.drawable.yellow));
       slipstickchart.setAxisMarginTop(5);
       slipstickchart.setAxisMarginRight(1);
       
       //最大纬线数
       slipstickchart.setLatitudeNum(2);
       //最大经线数
       slipstickchart.setLongitudeNum(3);
       //最大价格
       slipstickchart.setMaxValue(600000);
       //最小价格
       slipstickchart.setMinValue(100);
       
       slipstickchart.setDisplayFrom(10);
       
       slipstickchart.setDisplayNumber(30);
       
       slipstickchart.setMinDisplayNumber(5);
       
       slipstickchart.setZoomBaseLine(0);
       
       slipstickchart.setDisplayAxisXTitle(true);
       slipstickchart.setDisplayAxisYTitle(true);
       slipstickchart.setDisplayLatitude(true);
       slipstickchart.setDisplayLongitude(true);
       slipstickchart.setBackgroundColor(Color.BLACK);
       
      //为chart1增加均线
       slipstickchart.setStickData(vol);
	}
	
	private void initColoredSlipStickChart()
	{
       this.coloredslipstickchart = (ColoredSlipStickChart)findViewById(R.id.coloredslipstickchart);
     
       coloredslipstickchart.setAxisXColor(Color.LTGRAY);
       coloredslipstickchart.setAxisYColor(Color.LTGRAY);
       coloredslipstickchart.setLatitudeColor(Color.GRAY);
       coloredslipstickchart.setLongitudeColor(Color.GRAY);
       coloredslipstickchart.setBorderColor(Color.LTGRAY);
       coloredslipstickchart.setLongitudeFontColor(Color.WHITE);
       coloredslipstickchart.setLatitudeFontColor(Color.WHITE);
       coloredslipstickchart.setAxisMarginTop(5);
       coloredslipstickchart.setAxisMarginRight(1);
       
       //最大纬线数
       coloredslipstickchart.setLatitudeNum(2);
       //最大经线数
       coloredslipstickchart.setLongitudeNum(3);
       //最大价格
       coloredslipstickchart.setMaxValue(600000);
       //最小价格
       coloredslipstickchart.setMinValue(100);
       
       coloredslipstickchart.setDisplayFrom(10);
       
       coloredslipstickchart.setDisplayNumber(30);
       
       coloredslipstickchart.setMinDisplayNumber(5);
       
       coloredslipstickchart.setZoomBaseLine(0);
       
       coloredslipstickchart.setDisplayAxisXTitle(true);
       coloredslipstickchart.setDisplayAxisYTitle(true);
       coloredslipstickchart.setDisplayLatitude(true);
       coloredslipstickchart.setDisplayLongitude(true);
       coloredslipstickchart.setBackgroundColor(Color.BLACK);
       
      //为chart1增加均线
       coloredslipstickchart.setStickData(volc);
	}
	
	private void initMAStickChart()
	{
        this.mastickchart = (MAStickChart)findViewById(R.id.mastickchart);
        
        //以下计算VOL
        List<LineEntity<Float>> vlines = new ArrayList<LineEntity<Float>>();
        
        //计算5日均线
        LineEntity<Float> VMA5 = new LineEntity<Float>();
        VMA5.setTitle("MA5");
        VMA5.setLineColor(Color.WHITE);
        VMA5.setLineData(initVMA(5));
        vlines.add(VMA5);
        
        //计算10日均线
        LineEntity<Float> VMA10 = new LineEntity<Float>();
        VMA10.setTitle("MA10");
        VMA10.setLineColor(Color.RED);
        VMA10.setLineData(initVMA(10));
        vlines.add(VMA10);
        
        //计算25日均线
        LineEntity<Float> VMA25 = new LineEntity<Float>();
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
        mastickchart.setStickFillColor(getResources().getColor(R.drawable.yellow));
        mastickchart.setAxisMarginTop(5);
        mastickchart.setAxisMarginRight(1);
        
        //最大显示足数
        mastickchart.setMaxSticksNum(52);
        //最大纬线数
        mastickchart.setLatitudeNum(2);
        //最大经线数
        mastickchart.setLongitudeNum(3);
        //最大价格
        mastickchart.setMaxValue(10000);
        //最小价格
        mastickchart.setMinValue(100);
        
        mastickchart.setDisplayAxisXTitle(true);
        mastickchart.setDisplayAxisYTitle(true);
        mastickchart.setDisplayLatitude(true);
        mastickchart.setDisplayLongitude(true);
        mastickchart.setBackgroundColor(Color.BLACK);

        //为chart1增加均线
        mastickchart.setLineData(vlines);
        //为chart1增加均线
        mastickchart.setStickData(vol);
	}
	
	private void initMASlipStickChart()
	{
        this.maslipstickchart = (MASlipStickChart)findViewById(R.id.maslipstickchart);
        
        //以下计算VOL
        List<LineEntity<Float>> vlines = new ArrayList<LineEntity<Float>>();
        
        //计算5日均线
        LineEntity<Float> VMA5 = new LineEntity<Float>();
        VMA5.setTitle("MA5");
        VMA5.setLineColor(Color.WHITE);
        VMA5.setLineData(initVMA(5));
        vlines.add(VMA5);
        
        //计算10日均线
        LineEntity<Float> VMA10 = new LineEntity<Float>();
        VMA10.setTitle("MA10");
        VMA10.setLineColor(Color.RED);
        VMA10.setLineData(initVMA(10));
        vlines.add(VMA10);
        
        //计算25日均线
        LineEntity<Float> VMA25 = new LineEntity<Float>();
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
        maslipstickchart.setStickFillColor(getResources().getColor(R.drawable.yellow));
        maslipstickchart.setAxisMarginTop(5);
        maslipstickchart.setAxisMarginRight(1);
        
        //最大纬线数
        maslipstickchart.setLatitudeNum(2);
        //最大经线数
        maslipstickchart.setLongitudeNum(3);
        //最大价格
        maslipstickchart.setMaxValue(600000);
        //最小价格
        maslipstickchart.setMinValue(100);
        
        maslipstickchart.setDisplayFrom(10);
        
        maslipstickchart.setDisplayNumber(30);
        
        maslipstickchart.setMinDisplayNumber(5);
        
        maslipstickchart.setZoomBaseLine(0);
        
        maslipstickchart.setDisplayAxisXTitle(true);
        maslipstickchart.setDisplayAxisYTitle(true);
        maslipstickchart.setDisplayLatitude(true);
        maslipstickchart.setDisplayLongitude(true);
        maslipstickchart.setBackgroundColor(Color.BLACK);

        //为chart1增加均线
        maslipstickchart.setLineData(vlines);
        //为chart1增加均线
        maslipstickchart.setStickData(vol);
	}
	
	private void initMinusStickChart()
	{
        this.minusstickchart = (MinusStickChart)findViewById(R.id.minusstickchart);
		
		List<StickEntity> data = new ArrayList<StickEntity>();
		data.add(new StickEntity(50000,0,20110603));
		data.add(new StickEntity(42000,0,20110703));
		data.add(new StickEntity(32000,0,20110803));
		data.add(new StickEntity(21000,0,20110903));
		data.add(new StickEntity(0,-12000,20111003));
		data.add(new StickEntity(0,-28000,20111103));
		data.add(new StickEntity(0,-41000,20111203));
		data.add(new StickEntity(0,-25000,20120103));
		data.add(new StickEntity(0,-18000,20120203));
		data.add(new StickEntity(14000,0,20120303));
		data.add(new StickEntity(24000,0,20120303));
		data.add(new StickEntity(36000,0,20120303));
		data.add(new StickEntity(46000,0,20120303));
		minusstickchart.setStickData(data);
		minusstickchart.setMaxValue(50000);
		minusstickchart.setMinValue(-50000);
		minusstickchart.setAxisMarginRight(1);
		minusstickchart.setAxisMarginTop(5);
		
		minusstickchart.setBorderColor(Color.GRAY);
		minusstickchart.setAxisXColor(Color.WHITE);
		minusstickchart.setAxisYColor(Color.WHITE);
		minusstickchart.setLatitudeFontColor(Color.WHITE);
		minusstickchart.setLatitudeColor(Color.GRAY);
		minusstickchart.setLongitudeFontColor(Color.WHITE);
		minusstickchart.setLongitudeColor(Color.GRAY);
		//最大纬线数
		minusstickchart.setLatitudeNum(3);
		//最大经线数
		minusstickchart.setLongitudeNum(2);
		minusstickchart.setDisplayAxisXTitle(true);
		minusstickchart.setDisplayAxisYTitle(true);
		minusstickchart.setDisplayCrossXOnTouch(false);
		minusstickchart.setDisplayCrossYOnTouch(false);
		minusstickchart.setDisplayLatitude(true);
		minusstickchart.setDisplayLongitude(true);
		minusstickchart.setStickBorderColor(Color.WHITE);
		minusstickchart.setStickFillColor(Color.BLUE);
	}
	
	private void initSlipMinusStickChart()
	{
        this.slipminusstickchart = (SlipMinusStickChart)findViewById(R.id.slipminusstickchart);
		
		List<StickEntity> data = new ArrayList<StickEntity>();
		data.add(new StickEntity(50000,0,20110603));
		data.add(new StickEntity(42000,0,20110703));
		data.add(new StickEntity(32000,0,20110803));
		data.add(new StickEntity(21000,0,20110903));
		data.add(new StickEntity(0,-12000,20111003));
		data.add(new StickEntity(0,-28000,20111103));
		data.add(new StickEntity(0,-41000,20111203));
		data.add(new StickEntity(0,-25000,20120103));
		data.add(new StickEntity(0,-18000,20120203));
		data.add(new StickEntity(14000,0,20120303));
		data.add(new StickEntity(24000,0,20120303));
		data.add(new StickEntity(36000,0,20120303));
		data.add(new StickEntity(46000,0,20120303));
		data.add(new StickEntity(50000,0,20110603));
		data.add(new StickEntity(42000,0,20110703));
		data.add(new StickEntity(32000,0,20110803));
		data.add(new StickEntity(21000,0,20110903));
		data.add(new StickEntity(0,-12000,20111003));
		data.add(new StickEntity(0,-28000,20111103));
		data.add(new StickEntity(0,-41000,20111203));
		data.add(new StickEntity(0,-25000,20120103));
		data.add(new StickEntity(0,-18000,20120203));
		data.add(new StickEntity(14000,0,20120303));
		data.add(new StickEntity(24000,0,20120303));
		data.add(new StickEntity(36000,0,20120303));
		data.add(new StickEntity(46000,0,20120303));
		data.add(new StickEntity(50000,0,20110603));
		data.add(new StickEntity(42000,0,20110703));
		data.add(new StickEntity(32000,0,20110803));
		data.add(new StickEntity(21000,0,20110903));
		data.add(new StickEntity(0,-12000,20111003));
		data.add(new StickEntity(0,-28000,20111103));
		data.add(new StickEntity(0,-41000,20111203));
		data.add(new StickEntity(0,-25000,20120103));
		data.add(new StickEntity(0,-18000,20120203));
		data.add(new StickEntity(14000,0,20120303));
		data.add(new StickEntity(24000,0,20120303));
		data.add(new StickEntity(36000,0,20120303));
		data.add(new StickEntity(46000,0,20120303));
		slipminusstickchart.setStickData(data);
		slipminusstickchart.setMaxValue(50000);
		slipminusstickchart.setMinValue(-50000);
		slipminusstickchart.setAxisMarginRight(1);
		slipminusstickchart.setAxisMarginTop(5);
		
		slipminusstickchart.setBorderColor(Color.GRAY);
		slipminusstickchart.setAxisXColor(Color.WHITE);
		slipminusstickchart.setAxisYColor(Color.WHITE);
		slipminusstickchart.setLatitudeFontColor(Color.WHITE);
		slipminusstickchart.setLatitudeColor(Color.GRAY);
		slipminusstickchart.setLongitudeFontColor(Color.WHITE);
		slipminusstickchart.setLongitudeColor(Color.GRAY);
		//最大纬线数
		slipminusstickchart.setLatitudeNum(3);
		//最大经线数
		slipminusstickchart.setLongitudeNum(2);
		slipminusstickchart.setDisplayFrom(0);
		slipminusstickchart.setDisplayNumber(10);
		slipminusstickchart.setMinDisplayNumber(5);
        
		slipminusstickchart.setZoomBaseLine(0);
		slipminusstickchart.setDisplayAxisXTitle(true);
		slipminusstickchart.setDisplayAxisYTitle(true);
		slipminusstickchart.setDisplayCrossXOnTouch(false);
		slipminusstickchart.setDisplayCrossYOnTouch(false);
		slipminusstickchart.setDisplayLatitude(true);
		slipminusstickchart.setDisplayLongitude(true);
		slipminusstickchart.setStickBorderColor(Color.WHITE);
		slipminusstickchart.setStickFillColor(Color.BLUE);
	}
	
	private void initCandleStickChart()
	{
        this.candlestickchart = (CandleStickChart)findViewById(R.id.candlestickchart);
        candlestickchart.setAxisXColor(Color.LTGRAY);
        candlestickchart.setAxisYColor(Color.LTGRAY);
        candlestickchart.setLatitudeColor(Color.GRAY);
        candlestickchart.setLongitudeColor(Color.GRAY);
        candlestickchart.setBorderColor(Color.LTGRAY);
        candlestickchart.setLongitudeFontColor(Color.WHITE);
        candlestickchart.setLatitudeFontColor(Color.WHITE);
        candlestickchart.setAxisMarginRight(1);
        
        //最大显示足数
        candlestickchart.setMaxSticksNum(52);
        //最大纬线数
        candlestickchart.setLatitudeNum(5);
        //最大经线数
        candlestickchart.setLongitudeNum(3);
        //最大价格
        candlestickchart.setMaxValue(1000);
        //最小价格
        candlestickchart.setMinValue(200);
        
        candlestickchart.setDisplayAxisXTitle(true);
        candlestickchart.setDisplayAxisYTitle(true);
        candlestickchart.setDisplayLatitude(true);
        candlestickchart.setDisplayLongitude(true);
        candlestickchart.setBackgroundColor(Color.BLACK);
        
        //为chart2增加均线
        candlestickchart.setOHLCData(ohlc);
	}
	
	private void initSlipCandleStickChart()
	{
        this.slipcandlestickchart = (SlipCandleStickChart)findViewById(R.id.slipcandlestickchart);
        slipcandlestickchart.setAxisXColor(Color.LTGRAY);
        slipcandlestickchart.setAxisYColor(Color.LTGRAY);
        slipcandlestickchart.setLatitudeColor(Color.GRAY);
        slipcandlestickchart.setLongitudeColor(Color.GRAY);
        slipcandlestickchart.setBorderColor(Color.LTGRAY);
        slipcandlestickchart.setLongitudeFontColor(Color.WHITE);
        slipcandlestickchart.setLatitudeFontColor(Color.WHITE);
        slipcandlestickchart.setAxisMarginRight(1);
        
        //最大纬线数
        slipcandlestickchart.setLatitudeNum(5);
        //最大经线数
        slipcandlestickchart.setLongitudeNum(3);
        //最大价格
        slipcandlestickchart.setMaxValue(1000);
        //最小价格
        slipcandlestickchart.setMinValue(200);
        
        slipcandlestickchart.setDisplayFrom(10);
        
        slipcandlestickchart.setDisplayNumber(30);
        
        slipcandlestickchart.setMinDisplayNumber(5);
        
        slipcandlestickchart.setZoomBaseLine(0);
        
        slipcandlestickchart.setDisplayAxisXTitle(true);
        slipcandlestickchart.setDisplayAxisYTitle(true);
        slipcandlestickchart.setDisplayLatitude(true);
        slipcandlestickchart.setDisplayLongitude(true);
        slipcandlestickchart.setBackgroundColor(Color.BLACK);
        
        //为chart2增加均线
        slipcandlestickchart.setOHLCData(ohlc);
	}
	
	private void initMACandleStickChart()
	{
        this.macandlestickchart = (MACandleStickChart)findViewById(R.id.macandlestickchart);
      List<LineEntity<Float>> lines = new ArrayList<LineEntity<Float>>();
      
      //计算5日均线
      LineEntity<Float> MA5 = new LineEntity<Float>();
      MA5.setTitle("MA5");
      MA5.setLineColor(Color.WHITE);
      MA5.setLineData(initMA(5));
      lines.add(MA5);
      
      //计算10日均线
      LineEntity<Float> MA10 = new LineEntity<Float>();
      MA10.setTitle("MA10");
      MA10.setLineColor(Color.RED);
      MA10.setLineData(initMA(10));
      lines.add(MA10);
      
      //计算25日均线
      LineEntity<Float> MA25 = new LineEntity<Float>();
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
      macandlestickchart.setAxisMarginRight(1);
      
      //最大显示足数
      macandlestickchart.setMaxSticksNum(52);
      //最大纬线数
      macandlestickchart.setLatitudeNum(5);
      //最大经线数
      macandlestickchart.setLongitudeNum(3);
      //最大价格
      macandlestickchart.setMaxValue(1000);
      //最小价格
      macandlestickchart.setMinValue(200);
      
      macandlestickchart.setDisplayAxisXTitle(true);
      macandlestickchart.setDisplayAxisYTitle(true);
      macandlestickchart.setDisplayLatitude(true);
      macandlestickchart.setDisplayLongitude(true);
      macandlestickchart.setBackgroundColor(Color.BLACK);

      
    //为chart2增加均线
    macandlestickchart.setLineData(lines);
    
    //为chart2增加均线
    macandlestickchart.setOHLCData(ohlc);
        
	}
	
	private void initMASlipCandleStickChart()
	{
        this.maslipcandlestickchart = (MASlipCandleStickChart)findViewById(R.id.maslipcandlestickchart);
      List<LineEntity<Float>> lines = new ArrayList<LineEntity<Float>>();
      
      //计算5日均线
      LineEntity<Float> MA5 = new LineEntity<Float>();
      MA5.setTitle("MA5");
      MA5.setLineColor(Color.WHITE);
      MA5.setLineData(initMA(5));
      lines.add(MA5);
      
      //计算10日均线
      LineEntity<Float> MA10 = new LineEntity<Float>();
      MA10.setTitle("MA10");
      MA10.setLineColor(Color.RED);
      MA10.setLineData(initMA(10));
      lines.add(MA10);
      
      //计算25日均线
      LineEntity<Float> MA25 = new LineEntity<Float>();
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
      maslipcandlestickchart.setAxisMarginRight(1);
      
      //最大纬线数
      maslipcandlestickchart.setLatitudeNum(5);
      //最大经线数
      maslipcandlestickchart.setLongitudeNum(3);
      //最大价格
      maslipcandlestickchart.setMaxValue(1000);
      //最小价格
      maslipcandlestickchart.setMinValue(200);
      
      maslipcandlestickchart.setDisplayFrom(10);
      
      maslipcandlestickchart.setDisplayNumber(30);
      
      maslipcandlestickchart.setMinDisplayNumber(5);
      
      maslipcandlestickchart.setZoomBaseLine(0);
      
      maslipcandlestickchart.setDisplayAxisXTitle(true);
      maslipcandlestickchart.setDisplayAxisYTitle(true);
      maslipcandlestickchart.setDisplayLatitude(true);
      maslipcandlestickchart.setDisplayLongitude(true);
      maslipcandlestickchart.setBackgroundColor(Color.BLACK);

      
    //为chart2增加均线
      maslipcandlestickchart.setLineData(lines);
    
    //为chart2增加均线
      maslipcandlestickchart.setOHLCData(ohlc);
        
	}
    
    private void initPieChart()
    {
        this.piechart = (PieChart)findViewById(R.id.piechart);
		List<TitleValueColorEntity> data3 = new ArrayList<TitleValueColorEntity>();
		data3.add(new TitleValueColorEntity(getResources().getString(R.string.piechart_title1),2,getResources().getColor(R.drawable.red)));
		data3.add(new TitleValueColorEntity(getResources().getString(R.string.piechart_title2),3,getResources().getColor(R.drawable.orange)));
		data3.add(new TitleValueColorEntity(getResources().getString(R.string.piechart_title3),6,getResources().getColor(R.drawable.yellow)));
		data3.add(new TitleValueColorEntity(getResources().getString(R.string.piechart_title4),2,getResources().getColor(R.drawable.lightgreen)));
		data3.add(new TitleValueColorEntity(getResources().getString(R.string.piechart_title5),2,getResources().getColor(R.drawable.green)));
		piechart.setData(data3);
    }
    
    private void initSpiderWebChart()
    {
        this.spiderwebchart = (SpiderWebChart)findViewById(R.id.spiderwebchart);
        
		List<TitleValueEntity> data1 = new ArrayList<TitleValueEntity>();
		data1.add(new TitleValueEntity(getResources().getString(R.string.spiderwebchart_title1),3));
		data1.add(new TitleValueEntity(getResources().getString(R.string.spiderwebchart_title2),4));
		data1.add(new TitleValueEntity(getResources().getString(R.string.spiderwebchart_title3),9));
		data1.add(new TitleValueEntity(getResources().getString(R.string.spiderwebchart_title4),8));
		data1.add(new TitleValueEntity(getResources().getString(R.string.spiderwebchart_title5),10));
		
		List<TitleValueEntity> data2 = new ArrayList<TitleValueEntity>();
		data2.add(new TitleValueEntity(getResources().getString(R.string.spiderwebchart_title5),3));
		data2.add(new TitleValueEntity(getResources().getString(R.string.spiderwebchart_title5),4));
		data2.add(new TitleValueEntity(getResources().getString(R.string.spiderwebchart_title5),5));
		data2.add(new TitleValueEntity(getResources().getString(R.string.spiderwebchart_title5),6));
		data2.add(new TitleValueEntity(getResources().getString(R.string.spiderwebchart_title5),7));
		
		List<List<TitleValueEntity>> data = new ArrayList<List<TitleValueEntity>>();
		data.add(data1);
		data.add(data2);
		
		spiderwebchart.setData(data);
		spiderwebchart.setLatitudeNum(5);
    }
	
	private void initVOLC(){
		List<ColoredStickEntity> stick = new ArrayList<ColoredStickEntity>();
		this.volc = new ArrayList<ColoredStickEntity>();
		
		stick.add(new ColoredStickEntity(406000,0,20110825,Color.RED));
		stick.add(new ColoredStickEntity(232000,0,20110824,Color.RED));
		stick.add(new ColoredStickEntity(355000,0,20110823,Color.BLUE));
		stick.add(new ColoredStickEntity(437000,0,20110822,Color.RED));
		stick.add(new ColoredStickEntity(460000,0,20110819,Color.BLUE));
		stick.add(new ColoredStickEntity(422000,0,20110818,Color.LTGRAY));
		stick.add(new ColoredStickEntity(502000,0,20110817,Color.RED));
		stick.add(new ColoredStickEntity(509000,0,20110816,Color.RED));
		stick.add(new ColoredStickEntity(110000,0,20110815,Color.RED));
		stick.add(new ColoredStickEntity(110000,0,20110812,Color.BLUE));
		stick.add(new ColoredStickEntity(310000,0,20110811,Color.RED));
		stick.add(new ColoredStickEntity(210000,0,20110810,Color.BLUE));
		stick.add(new ColoredStickEntity(211000,0,20110809,Color.BLUE));
		stick.add(new ColoredStickEntity(577000,0,20110808,Color.RED));
		stick.add(new ColoredStickEntity(493000,0,20110805,Color.BLUE));
		stick.add(new ColoredStickEntity(433000,0,20110804,Color.LTGRAY));
		stick.add(new ColoredStickEntity(479000,0,20110803,Color.BLUE));
		stick.add(new ColoredStickEntity(360000,0,20110802,Color.RED));
		stick.add(new ColoredStickEntity(437000,0,20110801,Color.BLUE));
		stick.add(new ColoredStickEntity(504000,0,20110729,Color.BLUE));
		stick.add(new ColoredStickEntity(520000,0,20110728,Color.BLUE));
		stick.add(new ColoredStickEntity(494000,0,20110727,Color.BLUE));
		stick.add(new ColoredStickEntity(312000,0,20110726,Color.RED));
		stick.add(new ColoredStickEntity(424000,0,20110725,Color.BLUE));
		stick.add(new ColoredStickEntity(557000,0,20110722,Color.RED));
		stick.add(new ColoredStickEntity(596000,0,20110721,Color.RED));
		stick.add(new ColoredStickEntity(311000,0,20110720,Color.LTGRAY));
		stick.add(new ColoredStickEntity(312000,0,20110719,Color.BLUE));
		stick.add(new ColoredStickEntity(312000,0,20110718,Color.RED));
		stick.add(new ColoredStickEntity(312000,0,20110715,Color.BLUE));
		stick.add(new ColoredStickEntity(410000,0,20110714,Color.BLUE));
		stick.add(new ColoredStickEntity(311000,0,20110713,Color.BLUE));
		stick.add(new ColoredStickEntity(210000,0,20110712,Color.BLUE));
		stick.add(new ColoredStickEntity(410000,0,20110711,Color.RED));
		stick.add(new ColoredStickEntity(214000,0,20110708,Color.RED));
		stick.add(new ColoredStickEntity(312000,0,20110707,Color.BLUE));
		stick.add(new ColoredStickEntity(212000,0,20110706,Color.RED));
		stick.add(new ColoredStickEntity(414000,0,20110705,Color.RED));
		stick.add(new ColoredStickEntity(310000,0,20110704,Color.BLUE));
		stick.add(new ColoredStickEntity(210000,0,20110701,Color.RED));
		stick.add(new ColoredStickEntity(190000,0,20110630,Color.RED));
		stick.add(new ColoredStickEntity(210000,0,20110629,Color.BLUE));
		stick.add(new ColoredStickEntity(116000,0,20110628,Color.BLUE));
		stick.add(new ColoredStickEntity(142000,0,20110627,Color.BLUE));
		stick.add(new ColoredStickEntity(524000,0,20110624,Color.RED));
		stick.add(new ColoredStickEntity(246000,0,20110623,Color.BLUE));
		stick.add(new ColoredStickEntity(432000,0,20110622,Color.RED));
		stick.add(new ColoredStickEntity(352000,0,20110621,Color.RED));
		stick.add(new ColoredStickEntity(243000,0,20110620,Color.RED));
		stick.add(new ColoredStickEntity(165000,0,20110617,Color.BLUE));
		stick.add(new ColoredStickEntity(554000,0,20110616,Color.BLUE));
		stick.add(new ColoredStickEntity(552000,0,20110615,Color.BLUE));
		stick.add(new ColoredStickEntity(431000,0,20110614,Color.LTGRAY));
		stick.add(new ColoredStickEntity(317000,0,20110613,Color.BLUE));
		stick.add(new ColoredStickEntity(512000,0,20110610,Color.BLUE));
		stick.add(new ColoredStickEntity(283000,0,20110609,Color.RED));
		stick.add(new ColoredStickEntity(144000,0,20110608,Color.RED));
		stick.add(new ColoredStickEntity(273000,0,20110607,Color.RED));
		stick.add(new ColoredStickEntity(278000,0,20110603,Color.RED));
		stick.add(new ColoredStickEntity(624000,0,20110602,Color.RED));
		stick.add(new ColoredStickEntity(217000,0,20110601,Color.BLUE));
		stick.add(new ColoredStickEntity(116000,0,20110531,Color.BLUE));
		stick.add(new ColoredStickEntity(191000,0,20110530,Color.RED));
		stick.add(new ColoredStickEntity(204000,0,20110527,Color.BLUE));
		stick.add(new ColoredStickEntity(236000,0,20110526,Color.BLUE));
		stick.add(new ColoredStickEntity(421000,0,20110525,Color.LTGRAY));
		stick.add(new ColoredStickEntity(114000,0,20110524,Color.BLUE));
		stick.add(new ColoredStickEntity(403000,0,20110523,Color.RED));
		stick.add(new ColoredStickEntity(205000,0,20110520,Color.RED));
		stick.add(new ColoredStickEntity(328000,0,20110519,Color.BLUE));
		stick.add(new ColoredStickEntity(109000,0,20110518,Color.RED));
		stick.add(new ColoredStickEntity(286000,0,20110517,Color.RED));
		stick.add(new ColoredStickEntity(103000,0,20110516,Color.BLUE));
		stick.add(new ColoredStickEntity(114000,0,20110513,Color.BLUE));
		stick.add(new ColoredStickEntity(107000,0,20110512,Color.RED));
		stick.add(new ColoredStickEntity(106000,0,20110511,Color.BLUE));
		stick.add(new ColoredStickEntity(146000,0,20110510,Color.LTGRAY));
		stick.add(new ColoredStickEntity(105000,0,20110509,Color.RED));
		stick.add(new ColoredStickEntity(312000,0,20110506,Color.RED));
		stick.add(new ColoredStickEntity(530000,0,20110505,Color.RED));
		stick.add(new ColoredStickEntity(275000,0,20110504,Color.BLUE));
		stick.add(new ColoredStickEntity(432000,0,20110503,Color.BLUE));
//		stick.add(new StickEntity(157000,0,20110429));
//		stick.add(new StickEntity(148000,0,20110428));
//		stick.add(new StickEntity(224000,0,20110427));
//		stick.add(new StickEntity(405000,0,20110426));
//		stick.add(new StickEntity(374000,0,20110425));
//		stick.add(new StickEntity(473000,0,20110422));
//		stick.add(new StickEntity(437000,0,20110421));
//		stick.add(new StickEntity(121000,0,20110420));
//		stick.add(new StickEntity(208000,0,20110419));
//		stick.add(new StickEntity(486000,0,20110418));
//		stick.add(new StickEntity(486000,0,20110415));
//		stick.add(new StickEntity(473000,0,20110414));
//		stick.add(new StickEntity(256000,0,20110413));
//		stick.add(new StickEntity(275000,0,20110412));
//		stick.add(new StickEntity(471000,0,20110411));
//		stick.add(new StickEntity(529000,0,20110408));
//		stick.add(new StickEntity(564000,0,20110407));
//		stick.add(new StickEntity(257000,0,20110406));
//		stick.add(new StickEntity(344000,0,20110404));
//		stick.add(new StickEntity(525000,0,20110401));
		
        for(int i= stick.size(); i > 0 ; i--){
        	this.volc.add(stick.get(i -1));
        }
	}
	
	private void initVOL(){
		List<StickEntity> stick = new ArrayList<StickEntity>();
		this.vol = new ArrayList<StickEntity>();
		
		stick.add(new StickEntity(406000,0,20110825));
		stick.add(new StickEntity(232000,0,20110824));
		stick.add(new StickEntity(355000,0,20110823));
		stick.add(new StickEntity(437000,0,20110822));
		stick.add(new StickEntity(460000,0,20110819));
		stick.add(new StickEntity(422000,0,20110818));
		stick.add(new StickEntity(502000,0,20110817));
		stick.add(new StickEntity(509000,0,20110816));
		stick.add(new StickEntity(110000,0,20110815));
		stick.add(new StickEntity(110000,0,20110812));
		stick.add(new StickEntity(310000,0,20110811));
		stick.add(new StickEntity(210000,0,20110810));
		stick.add(new StickEntity(211000,0,20110809));
		stick.add(new StickEntity(577000,0,20110808));
		stick.add(new StickEntity(493000,0,20110805));
		stick.add(new StickEntity(433000,0,20110804));
		stick.add(new StickEntity(479000,0,20110803));
		stick.add(new StickEntity(360000,0,20110802));
		stick.add(new StickEntity(437000,0,20110801));
		stick.add(new StickEntity(504000,0,20110729));
		stick.add(new StickEntity(520000,0,20110728));
		stick.add(new StickEntity(494000,0,20110727));
		stick.add(new StickEntity(312000,0,20110726));
		stick.add(new StickEntity(424000,0,20110725));
		stick.add(new StickEntity(557000,0,20110722));
		stick.add(new StickEntity(596000,0,20110721));
		stick.add(new StickEntity(311000,0,20110720));
		stick.add(new StickEntity(312000,0,20110719));
		stick.add(new StickEntity(312000,0,20110718));
		stick.add(new StickEntity(312000,0,20110715));
		stick.add(new StickEntity(410000,0,20110714));
		stick.add(new StickEntity(311000,0,20110713));
		stick.add(new StickEntity(210000,0,20110712));
		stick.add(new StickEntity(410000,0,20110711));
		stick.add(new StickEntity(214000,0,20110708));
		stick.add(new StickEntity(312000,0,20110707));
		stick.add(new StickEntity(212000,0,20110706));
		stick.add(new StickEntity(414000,0,20110705));
		stick.add(new StickEntity(310000,0,20110704));
		stick.add(new StickEntity(210000,0,20110701));
		stick.add(new StickEntity(190000,0,20110630));
		stick.add(new StickEntity(210000,0,20110629));
		stick.add(new StickEntity(116000,0,20110628));
		stick.add(new StickEntity(142000,0,20110627));
		stick.add(new StickEntity(524000,0,20110624));
		stick.add(new StickEntity(246000,0,20110623));
		stick.add(new StickEntity(432000,0,20110622));
		stick.add(new StickEntity(352000,0,20110621));
		stick.add(new StickEntity(243000,0,20110620));
		stick.add(new StickEntity(165000,0,20110617));
		stick.add(new StickEntity(554000,0,20110616));
		stick.add(new StickEntity(552000,0,20110615));
		stick.add(new StickEntity(431000,0,20110614));
		stick.add(new StickEntity(317000,0,20110613));
		stick.add(new StickEntity(512000,0,20110610));
		stick.add(new StickEntity(283000,0,20110609));
		stick.add(new StickEntity(144000,0,20110608));
		stick.add(new StickEntity(273000,0,20110607));
		stick.add(new StickEntity(278000,0,20110603));
		stick.add(new StickEntity(624000,0,20110602));
		stick.add(new StickEntity(217000,0,20110601));
		stick.add(new StickEntity(116000,0,20110531));
		stick.add(new StickEntity(191000,0,20110530));
		stick.add(new StickEntity(204000,0,20110527));
		stick.add(new StickEntity(236000,0,20110526));
		stick.add(new StickEntity(421000,0,20110525));
		stick.add(new StickEntity(114000,0,20110524));
		stick.add(new StickEntity(403000,0,20110523));
		stick.add(new StickEntity(205000,0,20110520));
		stick.add(new StickEntity(328000,0,20110519));
		stick.add(new StickEntity(109000,0,20110518));
		stick.add(new StickEntity(286000,0,20110517));
		stick.add(new StickEntity(103000,0,20110516));
		stick.add(new StickEntity(114000,0,20110513));
		stick.add(new StickEntity(107000,0,20110512));
		stick.add(new StickEntity(106000,0,20110511));
		stick.add(new StickEntity(146000,0,20110510));
		stick.add(new StickEntity(105000,0,20110509));
		stick.add(new StickEntity(312000,0,20110506));
		stick.add(new StickEntity(530000,0,20110505));
		stick.add(new StickEntity(275000,0,20110504));
		stick.add(new StickEntity(432000,0,20110503));
//		stick.add(new StickEntity(157000,0,20110429));
//		stick.add(new StickEntity(148000,0,20110428));
//		stick.add(new StickEntity(224000,0,20110427));
//		stick.add(new StickEntity(405000,0,20110426));
//		stick.add(new StickEntity(374000,0,20110425));
//		stick.add(new StickEntity(473000,0,20110422));
//		stick.add(new StickEntity(437000,0,20110421));
//		stick.add(new StickEntity(121000,0,20110420));
//		stick.add(new StickEntity(208000,0,20110419));
//		stick.add(new StickEntity(486000,0,20110418));
//		stick.add(new StickEntity(486000,0,20110415));
//		stick.add(new StickEntity(473000,0,20110414));
//		stick.add(new StickEntity(256000,0,20110413));
//		stick.add(new StickEntity(275000,0,20110412));
//		stick.add(new StickEntity(471000,0,20110411));
//		stick.add(new StickEntity(529000,0,20110408));
//		stick.add(new StickEntity(564000,0,20110407));
//		stick.add(new StickEntity(257000,0,20110406));
//		stick.add(new StickEntity(344000,0,20110404));
//		stick.add(new StickEntity(525000,0,20110401));
		
        for(int i= stick.size(); i > 0 ; i--){
        	this.vol.add(stick.get(i -1));
        }
	}
	
	private List<Float> initVMA(int days){
		if (days < 2){
			return null;
		}
		
        List<Float> MA5Values = new ArrayList<Float>();
        
    	float sum = 0;
    	float avg = 0;
        for(int i = 0 ; i < this.vol.size(); i++){
        	float close =(float)vol.get(i).getHigh();
        	if(i< days){
        		sum = sum + close;
        		avg = sum / (i + 1f);
        	}else{
        		sum = sum + close - (float)vol.get(i-days).getHigh();
        		avg = sum / days;
        	}
        	MA5Values.add(avg);
        }
        
        return MA5Values;
	}
	
	private void initDV1() {
		List<DateValueEntity> dv = new ArrayList<DateValueEntity>();

		this.dv1 = new ArrayList<DateValueEntity>();
		dv.add(new DateValueEntity(246, 20110825));
		dv.add(new DateValueEntity(242, 20110824));
		dv.add(new DateValueEntity(240, 20110823));
		dv.add(new DateValueEntity(236, 20110822));
		dv.add(new DateValueEntity(240, 20110819));
		dv.add(new DateValueEntity(241, 20110818));
		dv.add(new DateValueEntity(243, 20110817));
		dv.add(new DateValueEntity(242, 20110816));
		dv.add(new DateValueEntity(240, 20110815));
		dv.add(new DateValueEntity(238, 20110812));
		dv.add(new DateValueEntity(237, 20110811));
		dv.add(new DateValueEntity(233, 20110810));
		dv.add(new DateValueEntity(241, 20110809));
		dv.add(new DateValueEntity(244, 20110808));
		dv.add(new DateValueEntity(249, 20110805));
		dv.add(new DateValueEntity(248, 20110804));
		dv.add(new DateValueEntity(249, 20110803));
		dv.add(new DateValueEntity(251, 20110802));
		dv.add(new DateValueEntity(252, 20110801));
		dv.add(new DateValueEntity(251, 20110729));
		dv.add(new DateValueEntity(252, 20110728));
		dv.add(new DateValueEntity(250, 20110727));
		dv.add(new DateValueEntity(256, 20110726));
		dv.add(new DateValueEntity(258, 20110725));
		dv.add(new DateValueEntity(260, 20110722));
		dv.add(new DateValueEntity(261, 20110721));
		dv.add(new DateValueEntity(260, 20110720));
		dv.add(new DateValueEntity(262, 20110719));
		dv.add(new DateValueEntity(262, 20110718));
		dv.add(new DateValueEntity(261, 20110715));
		dv.add(new DateValueEntity(259, 20110714));
		dv.add(new DateValueEntity(258, 20110713));
		dv.add(new DateValueEntity(260, 20110712));
		dv.add(new DateValueEntity(260, 20110711));
		dv.add(new DateValueEntity(262, 20110708));
		dv.add(new DateValueEntity(261, 20110707));
		dv.add(new DateValueEntity(261, 20110706));
		dv.add(new DateValueEntity(261, 20110705));
		dv.add(new DateValueEntity(257, 20110704));
		dv.add(new DateValueEntity(257, 20110701));
		dv.add(new DateValueEntity(255, 20110630));
		dv.add(new DateValueEntity(256, 20110629));
		dv.add(new DateValueEntity(256, 20110628));
		dv.add(new DateValueEntity(256, 20110627));
		dv.add(new DateValueEntity(249, 20110624));
		dv.add(new DateValueEntity(245, 20110623));
		dv.add(new DateValueEntity(244, 20110622));
		dv.add(new DateValueEntity(243, 20110621));
		dv.add(new DateValueEntity(247, 20110620));
		dv.add(new DateValueEntity(249, 20110617));
		dv.add(new DateValueEntity(253, 20110616));
		dv.add(new DateValueEntity(253, 20110615));
		dv.add(new DateValueEntity(250, 20110614));
		dv.add(new DateValueEntity(250, 20110613));
		dv.add(new DateValueEntity(254, 20110610));
		dv.add(new DateValueEntity(255, 20110609));
		dv.add(new DateValueEntity(254, 20110608));
		dv.add(new DateValueEntity(253, 20110607));
		dv.add(new DateValueEntity(252, 20110603));
		dv.add(new DateValueEntity(254, 20110602));
		dv.add(new DateValueEntity(254, 20110601));
		dv.add(new DateValueEntity(252, 20110531));
		dv.add(new DateValueEntity(254, 20110530));
		dv.add(new DateValueEntity(256, 20110527));
		dv.add(new DateValueEntity(257, 20110526));
		dv.add(new DateValueEntity(257, 20110525));
		dv.add(new DateValueEntity(265, 20110524));
		dv.add(new DateValueEntity(266, 20110523));
		dv.add(new DateValueEntity(268, 20110520));
		dv.add(new DateValueEntity(267, 20110519));
		dv.add(new DateValueEntity(266, 20110518));
		dv.add(new DateValueEntity(267, 20110517));
		dv.add(new DateValueEntity(267, 20110516));
		dv.add(new DateValueEntity(267, 20110513));
		dv.add(new DateValueEntity(269, 20110512));
		dv.add(new DateValueEntity(269, 20110511));
		dv.add(new DateValueEntity(268, 20110510));
		dv.add(new DateValueEntity(268, 20110509));
		dv.add(new DateValueEntity(268, 20110506));
		dv.add(new DateValueEntity(271, 20110505));
		dv.add(new DateValueEntity(273, 20110504));
		dv.add(new DateValueEntity(271, 20110503));

		for (int i = dv.size(); i > 0; i--) {
			this.dv1.add(dv.get(i - 1));
		}
	}

	private void initDV2() {
		List<DateValueEntity> dv = new ArrayList<DateValueEntity>();

		this.dv2 = new ArrayList<DateValueEntity>();
		dv.add(new DateValueEntity(246, 20110825));
		dv.add(new DateValueEntity(242, 20110824));
		dv.add(new DateValueEntity(235, 20110823));
		dv.add(new DateValueEntity(231, 20110822));
		dv.add(new DateValueEntity(240, 20110819));
		dv.add(new DateValueEntity(239, 20110818));
		dv.add(new DateValueEntity(240, 20110817));
		dv.add(new DateValueEntity(238, 20110816));
		dv.add(new DateValueEntity(238, 20110815));
		dv.add(new DateValueEntity(230, 20110812));
		dv.add(new DateValueEntity(234, 20110811));
		dv.add(new DateValueEntity(223, 20110810));
		dv.add(new DateValueEntity(229, 20110809));
		dv.add(new DateValueEntity(240, 20110808));
		dv.add(new DateValueEntity(247, 20110805));
		dv.add(new DateValueEntity(245, 20110804));
		dv.add(new DateValueEntity(245, 20110803));
		dv.add(new DateValueEntity(248, 20110802));
		dv.add(new DateValueEntity(248, 20110801));
		dv.add(new DateValueEntity(248, 20110729));
		dv.add(new DateValueEntity(248, 20110728));
		dv.add(new DateValueEntity(247, 20110727));
		dv.add(new DateValueEntity(248, 20110726));
		dv.add(new DateValueEntity(256, 20110725));
		dv.add(new DateValueEntity(256, 20110722));
		dv.add(new DateValueEntity(257, 20110721));
		dv.add(new DateValueEntity(259, 20110720));
		dv.add(new DateValueEntity(260, 20110719));
		dv.add(new DateValueEntity(259, 20110718));
		dv.add(new DateValueEntity(258, 20110715));
		dv.add(new DateValueEntity(255, 20110714));
		dv.add(new DateValueEntity(255, 20110713));
		dv.add(new DateValueEntity(258, 20110712));
		dv.add(new DateValueEntity(258, 20110711));
		dv.add(new DateValueEntity(259, 20110708));
		dv.add(new DateValueEntity(258, 20110707));
		dv.add(new DateValueEntity(259, 20110706));
		dv.add(new DateValueEntity(257, 20110705));
		dv.add(new DateValueEntity(255, 20110704));
		dv.add(new DateValueEntity(253, 20110701));
		dv.add(new DateValueEntity(252, 20110630));
		dv.add(new DateValueEntity(253, 20110629));
		dv.add(new DateValueEntity(254, 20110628));
		dv.add(new DateValueEntity(247, 20110627));
		dv.add(new DateValueEntity(243, 20110624));
		dv.add(new DateValueEntity(243, 20110623));
		dv.add(new DateValueEntity(241, 20110622));
		dv.add(new DateValueEntity(241, 20110621));
		dv.add(new DateValueEntity(244, 20110620));
		dv.add(new DateValueEntity(246, 20110617));
		dv.add(new DateValueEntity(250, 20110616));
		dv.add(new DateValueEntity(249, 20110615));
		dv.add(new DateValueEntity(246, 20110614));
		dv.add(new DateValueEntity(247, 20110613));
		dv.add(new DateValueEntity(250, 20110610));
		dv.add(new DateValueEntity(251, 20110609));
		dv.add(new DateValueEntity(251, 20110608));
		dv.add(new DateValueEntity(250, 20110607));
		dv.add(new DateValueEntity(247, 20110603));
		dv.add(new DateValueEntity(252, 20110602));
		dv.add(new DateValueEntity(250, 20110601));
		dv.add(new DateValueEntity(248, 20110531));
		dv.add(new DateValueEntity(250, 20110530));
		dv.add(new DateValueEntity(253, 20110527));
		dv.add(new DateValueEntity(253, 20110526));
		dv.add(new DateValueEntity(254, 20110525));
		dv.add(new DateValueEntity(257, 20110524));
		dv.add(new DateValueEntity(265, 20110523));
		dv.add(new DateValueEntity(265, 20110520));
		dv.add(new DateValueEntity(264, 20110519));
		dv.add(new DateValueEntity(262, 20110518));
		dv.add(new DateValueEntity(264, 20110517));
		dv.add(new DateValueEntity(263, 20110516));
		dv.add(new DateValueEntity(264, 20110513));
		dv.add(new DateValueEntity(266, 20110512));
		dv.add(new DateValueEntity(266, 20110511));
		dv.add(new DateValueEntity(266, 20110510));
		dv.add(new DateValueEntity(263, 20110509));
		dv.add(new DateValueEntity(265, 20110506));
		dv.add(new DateValueEntity(266, 20110505));
		dv.add(new DateValueEntity(269, 20110504));
		dv.add(new DateValueEntity(267, 20110503));

		for (int i = dv.size(); i > 0; i--) {
			this.dv2.add(dv.get(i - 1));
		}
	}
	
	private void initOHLC(){
        List<OHLCEntity> ohlc = new ArrayList<OHLCEntity>();
        
        this.ohlc = new ArrayList<OHLCEntity>();
        ohlc.add(new OHLCEntity(246 ,248 ,235 ,235 ,20110825));
        ohlc.add(new OHLCEntity(240 ,242 ,236 ,242 ,20110824));
        ohlc.add(new OHLCEntity(236 ,240 ,235 ,240 ,20110823));
        ohlc.add(new OHLCEntity(232 ,236 ,231 ,236 ,20110822));
        ohlc.add(new OHLCEntity(240 ,240 ,235 ,235 ,20110819));
        ohlc.add(new OHLCEntity(240 ,241 ,239 ,240 ,20110818));
        ohlc.add(new OHLCEntity(242 ,243 ,240 ,240 ,20110817));
        ohlc.add(new OHLCEntity(239 ,242 ,238 ,242 ,20110816));
        ohlc.add(new OHLCEntity(239 ,240 ,238 ,239 ,20110815));
        ohlc.add(new OHLCEntity(230 ,238 ,230 ,238 ,20110812));
        ohlc.add(new OHLCEntity(236 ,237 ,234 ,234 ,20110811));
        ohlc.add(new OHLCEntity(226 ,233 ,223 ,232 ,20110810));
        ohlc.add(new OHLCEntity(239 ,241 ,229 ,232 ,20110809));
        ohlc.add(new OHLCEntity(242 ,244 ,240 ,242 ,20110808));
        ohlc.add(new OHLCEntity(248 ,249 ,247 ,248 ,20110805));
        ohlc.add(new OHLCEntity(245 ,248 ,245 ,247 ,20110804));
        ohlc.add(new OHLCEntity(249 ,249 ,245 ,247 ,20110803));
        ohlc.add(new OHLCEntity(249 ,251 ,248 ,250 ,20110802));
        ohlc.add(new OHLCEntity(250 ,252 ,248 ,250 ,20110801));
        ohlc.add(new OHLCEntity(250 ,251 ,248 ,250 ,20110729));
        ohlc.add(new OHLCEntity(249 ,252 ,248 ,252 ,20110728));
        ohlc.add(new OHLCEntity(248 ,250 ,247 ,250 ,20110727));
        ohlc.add(new OHLCEntity(256 ,256 ,248 ,248 ,20110726));
        ohlc.add(new OHLCEntity(257 ,258 ,256 ,257 ,20110725));
        ohlc.add(new OHLCEntity(259 ,260 ,256 ,256 ,20110722));
        ohlc.add(new OHLCEntity(261 ,261 ,257 ,259 ,20110721));
        ohlc.add(new OHLCEntity(260 ,260 ,259 ,259 ,20110720));
        ohlc.add(new OHLCEntity(262 ,262 ,260 ,261 ,20110719));
        ohlc.add(new OHLCEntity(260 ,262 ,259 ,262 ,20110718));
        ohlc.add(new OHLCEntity(259 ,261 ,258 ,261 ,20110715));
        ohlc.add(new OHLCEntity(255 ,259 ,255 ,259 ,20110714));
        ohlc.add(new OHLCEntity(258 ,258 ,255 ,255 ,20110713));
        ohlc.add(new OHLCEntity(258 ,260 ,258 ,260 ,20110712));
        ohlc.add(new OHLCEntity(259 ,260 ,258 ,259 ,20110711));
        ohlc.add(new OHLCEntity(261 ,262 ,259 ,259 ,20110708));
        ohlc.add(new OHLCEntity(261 ,261 ,258 ,261 ,20110707));
        ohlc.add(new OHLCEntity(261 ,261 ,259 ,261 ,20110706));
        ohlc.add(new OHLCEntity(257 ,261 ,257 ,261 ,20110705));
        ohlc.add(new OHLCEntity(256 ,257 ,255 ,255 ,20110704));
        ohlc.add(new OHLCEntity(253 ,257 ,253 ,256 ,20110701));
        ohlc.add(new OHLCEntity(255 ,255 ,252 ,252 ,20110630));
        ohlc.add(new OHLCEntity(256 ,256 ,253 ,255 ,20110629));
        ohlc.add(new OHLCEntity(254 ,256 ,254 ,255 ,20110628));
        ohlc.add(new OHLCEntity(247 ,256 ,247 ,254 ,20110627));
        ohlc.add(new OHLCEntity(244 ,249 ,243 ,248 ,20110624));
        ohlc.add(new OHLCEntity(244 ,245 ,243 ,244 ,20110623));
        ohlc.add(new OHLCEntity(242 ,244 ,241 ,244 ,20110622));
        ohlc.add(new OHLCEntity(243 ,243 ,241 ,242 ,20110621));
        ohlc.add(new OHLCEntity(246 ,247 ,244 ,244 ,20110620));
        ohlc.add(new OHLCEntity(248 ,249 ,246 ,246 ,20110617));
        ohlc.add(new OHLCEntity(251 ,253 ,250 ,250 ,20110616));
        ohlc.add(new OHLCEntity(249 ,253 ,249 ,253 ,20110615));
        ohlc.add(new OHLCEntity(248 ,250 ,246 ,250 ,20110614));
        ohlc.add(new OHLCEntity(249 ,250 ,247 ,250 ,20110613));
        ohlc.add(new OHLCEntity(254 ,254 ,250 ,250 ,20110610));
        ohlc.add(new OHLCEntity(254 ,255 ,251 ,255 ,20110609));
        ohlc.add(new OHLCEntity(252 ,254 ,251 ,254 ,20110608));
        ohlc.add(new OHLCEntity(250 ,253 ,250 ,252 ,20110607));
        ohlc.add(new OHLCEntity(251 ,252 ,247 ,250 ,20110603));
        ohlc.add(new OHLCEntity(253 ,254 ,252 ,254 ,20110602));
        ohlc.add(new OHLCEntity(250 ,254 ,250 ,254 ,20110601));
        ohlc.add(new OHLCEntity(250 ,252 ,248 ,250 ,20110531));
        ohlc.add(new OHLCEntity(253 ,254 ,250 ,251 ,20110530));
        ohlc.add(new OHLCEntity(255 ,256 ,253 ,253 ,20110527));
        ohlc.add(new OHLCEntity(256 ,257 ,253 ,254 ,20110526));
        ohlc.add(new OHLCEntity(256 ,257 ,254 ,256 ,20110525));
        ohlc.add(new OHLCEntity(265 ,265 ,257 ,257 ,20110524));
        ohlc.add(new OHLCEntity(265 ,266 ,265 ,265 ,20110523));
        ohlc.add(new OHLCEntity(267 ,268 ,265 ,266 ,20110520));
        ohlc.add(new OHLCEntity(264 ,267 ,264 ,267 ,20110519));
        ohlc.add(new OHLCEntity(264 ,266 ,262 ,265 ,20110518));
        ohlc.add(new OHLCEntity(266 ,267 ,264 ,264 ,20110517));
        ohlc.add(new OHLCEntity(264 ,267 ,263 ,267 ,20110516));
        ohlc.add(new OHLCEntity(266 ,267 ,264 ,264 ,20110513));
        ohlc.add(new OHLCEntity(269 ,269 ,266 ,268 ,20110512));
        ohlc.add(new OHLCEntity(267 ,269 ,266 ,269 ,20110511));
        ohlc.add(new OHLCEntity(266 ,268 ,266 ,267 ,20110510));
        ohlc.add(new OHLCEntity(264 ,268 ,263 ,266 ,20110509));
        ohlc.add(new OHLCEntity(265 ,268 ,265 ,267 ,20110506));
        ohlc.add(new OHLCEntity(271 ,271 ,266 ,266 ,20110505));
        ohlc.add(new OHLCEntity(271 ,273 ,269 ,273 ,20110504));
        ohlc.add(new OHLCEntity(268 ,271 ,267 ,271 ,20110503));
        
		for (int i = ohlc.size(); i > 0; i--) {
			this.ohlc.add(ohlc.get(i - 1));
		}
	}
	
	private List<Float> initMA(int days){
		
		if (days < 2){
			return null;
		}
		
        List<Float> MA5Values = new ArrayList<Float>();
        
    	float sum = 0;
    	float avg = 0;
        for(int i = 0 ; i < this.ohlc.size(); i++){
        	float close =(float)ohlc.get(i).getClose();
        	if(i< days){
        		sum = sum + close;
        		avg = sum / (i + 1f);
        	}else{
        		sum = sum + close - (float)ohlc.get(i-days).getClose();
        		avg = sum / days;
        	}
        	MA5Values.add(avg);
        }
        
        return MA5Values;
	}
}