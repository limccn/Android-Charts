package cn.limc.demo.activity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.graphics.Color;
import android.os.Bundle;
import cn.limc.androidcharts.R;
import cn.limc.androidcharts.axis.Axis;
import cn.limc.androidcharts.common.IDataCursor;
import cn.limc.androidcharts.entity.ColoredStickEntity;
import cn.limc.androidcharts.entity.DateValueEntity;
import cn.limc.androidcharts.entity.IStickEntity;
import cn.limc.androidcharts.entity.LineEntity;
import cn.limc.androidcharts.entity.ListChartData;
import cn.limc.androidcharts.event.IDisplayCursorListener;
import cn.limc.androidcharts.view.ColoredSlipStickChart;
import cn.limc.androidcharts.view.TickChart;
import cn.limc.demo.common.BaseActivity;
import cn.limc.demo.common.TimesJSONBean;
import cn.limc.demo.common.bean.OHLCVData;
import cn.limc.demo.common.utils.FastJsonPaser;

public class SampleTickDemoActivity extends BaseActivity {

	TickChart mTimesChart;
	ColoredSlipStickChart mVOLchart;
	
	final IDisplayCursorListener displayCursorListener =  new IDisplayCursorListener() {
        @Override
        public void onCursorChanged(IDataCursor cursor, int displayFrom, int displayNumber) {

        }
    };
    
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_times_demo);
        
        initTimesChart();
        initVOLChart();
        
        loadTimesJSONData();
    }
	
	private void initTimesChart() {
		this.mTimesChart = (TickChart) findViewById(R.id.timeschart);

    	mTimesChart.setAxisXColor(Color.LTGRAY);
        mTimesChart.setAxisYColor(Color.LTGRAY);
        mTimesChart.setBorderColor(Color.LTGRAY);
        mTimesChart.setLongitudeFontSize(14);
        mTimesChart.setLongitudeFontColor(Color.WHITE);
        mTimesChart.setLatitudeColor(Color.GRAY);
        mTimesChart.setLatitudeFontColor(Color.WHITE);
        mTimesChart.setLongitudeColor(Color.GRAY);
        mTimesChart.setMaxValue(1300);
        mTimesChart.setMinValue(700);
        mTimesChart.setDisplayFrom(10);
        mTimesChart.setDisplayNumber(30);
        mTimesChart.setMinDisplayNumber(5);
//        mTimesChart.setZoomBaseLine(IZoomable.ZOOM_BASE_LINE_CENTER);
        mTimesChart.setDisplayLongitudeTitle(true);
        mTimesChart.setDisplayLatitudeTitle(true);
        mTimesChart.setDisplayLatitude(true);
        mTimesChart.setDisplayLongitude(true);
        mTimesChart.setDataQuadrantPaddingTop(5);
        mTimesChart.setDataQuadrantPaddingBottom(5);
        mTimesChart.setDataQuadrantPaddingLeft(5);
        mTimesChart.setDataQuadrantPaddingRight(5);
        // mTimesChart.setAxisYTitleQuadrantWidth(50);
        // mTimesChart.setAxisXTitleQuadrantHeight(20);
        mTimesChart.setAxisXPosition(Axis.AXIS_X_POSITION_BOTTOM);
        mTimesChart.setAxisYPosition(Axis.AXIS_Y_POSITION_RIGHT);
        mTimesChart.setAxisXDateTargetFormat("HH:mm");
        mTimesChart.setAxisXDateSourceFormat("yyyyMMddHHmm");
        mTimesChart.setDetectSlipEvent(false);
        mTimesChart.setDetectZoomEvent(false);
	}
	
	private void initVOLChart() {
		this.mVOLchart = (ColoredSlipStickChart) findViewById(R.id.slipstickchart);

        mVOLchart.setAxisXColor(Color.LTGRAY);
        mVOLchart.setAxisYColor(Color.LTGRAY);
        mVOLchart.setLatitudeColor(Color.GRAY);
        mVOLchart.setLongitudeColor(Color.GRAY);
        mVOLchart.setBorderColor(Color.LTGRAY);
        mVOLchart.setLongitudeFontColor(Color.WHITE);
        mVOLchart.setLatitudeFontColor(Color.WHITE);
        mVOLchart.setDataQuadrantPaddingTop(6);
        mVOLchart.setDataQuadrantPaddingBottom(1);
        mVOLchart.setDataQuadrantPaddingLeft(1);
        mVOLchart.setDataQuadrantPaddingRight(1);
        // mVOLchart.setAxisYTitleQuadrantWidth(50);
        // mVOLchart.setAxisXTitleQuadrantHeight(20);
        mVOLchart.setAxisXPosition(Axis.AXIS_X_POSITION_BOTTOM);
        mVOLchart.setAxisYPosition(Axis.AXIS_Y_POSITION_RIGHT);
        // 最大纬线数
        mVOLchart.setLatitudeNum(3);
        // 最大经线数
        mVOLchart.setLongitudeNum(3);
        // 最大价格
        mVOLchart.setMaxValue(340);
        // 最小价格
        mVOLchart.setMinValue(240);

//        mVOLchart.setDisplayFrom(0);
//
//        mVOLchart.setDisplayNumber(50);
//
//        mVOLchart.setMinDisplayNumber(10);

//        mVOLchart.setZoomBaseLine(IZoomable.ZOOM_BASE_LINE_CENTER);

        mVOLchart.setDisplayLongitudeTitle(false);
        mVOLchart.setDisplayLatitudeTitle(true);
        mVOLchart.setDisplayLatitude(true);
        mVOLchart.setDisplayLongitude(true);
        mVOLchart.setBackgroundColor(Color.BLACK);

        mVOLchart.setDataMultiple(100);
        mVOLchart.setAxisYDecimalFormat("#,##0.00");
        mVOLchart.setAxisXDateTargetFormat("HH:mm");
        mVOLchart.setAxisXDateSourceFormat("yyyyMMddHHmm");
        mVOLchart.setOnDisplayCursorListener(displayCursorListener);
        mVOLchart.setDetectSlipEvent(false);
        mVOLchart.setDetectZoomEvent(false);
    }
	
	/**
     * 加载JSON
     * @return
     */
    public void loadTimesJSONData(){
    	String strFileNm = "TimesJSONData.txt";
    	String strData = getStringFromAssets(strFileNm);
    	
    	TimesJSONBean bean = (TimesJSONBean)FastJsonPaser.parse(strData, TimesJSONBean.class);
    	    	
    	List<OHLCVData> chartData = new ArrayList<OHLCVData>();
    	for (Map<String, String> map : bean.getData().getTimedivisionline()) {
    		OHLCVData data = new OHLCVData();
			data.setOpen(Float.parseFloat(map.get("o")));
			data.setHigh(Float.parseFloat(map.get("h")));
			data.setLow(Float.parseFloat(map.get("l")));
			data.setClose(Float.parseFloat(map.get("paid_price")));
			data.setVol(Float.parseFloat(map.get("paid_quantity")));
			data.setDate(TimeStamp2Date(map.get("datetime"), "yyyyMMddHHmm"));
			data.setCurrent(Float.parseFloat(map.get("n")));
			data.setPreclose(0.0f);
			data.setChange(Float.parseFloat(map.get("change_percent")));
			
			System.out.println(data.getClose());
			System.out.println(data.getVol());
			System.out.println(data.getDate());
			
			chartData.add(data);
		}
    	    	
    	mTimesChart.setLinesData(getTimesLines(chartData));


        ListChartData<IStickEntity> stickdatas = new ListChartData<IStickEntity>(getVOL(chartData));
        // 为chart1增加均线
        mVOLchart.setStickData(stickdatas);
        mVOLchart.setMaxDisplayNumber(stickdatas.size());
        mVOLchart.setDisplayFrom(0);
        mVOLchart.setDisplayNumber(stickdatas.size());

    }
    
    protected List<LineEntity<DateValueEntity>> getTimesLines(List<OHLCVData> arrayData) {
    	List<LineEntity<DateValueEntity>> lines = new ArrayList<LineEntity<DateValueEntity>>();

        // 计算5日均线
        LineEntity<DateValueEntity> MA5 = new LineEntity<DateValueEntity>();
        MA5.setTitle("HIGH");
        MA5.setLineColor(Color.WHITE);
        
        List<DateValueEntity> dateValues = new ArrayList<DateValueEntity>();
        
        for (OHLCVData data : arrayData) {
        	try {
				dateValues.add(new DateValueEntity((float) data.getClose(), Long.parseLong(data.getDate())));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				continue;
			}
		}
        
        MA5.setLineData(dateValues);
        lines.add(MA5);

        return lines;
	}
    
    protected List<IStickEntity> getVOL(List<OHLCVData> arrayData) {
        List<IStickEntity> stick = new ArrayList<IStickEntity>();
        
        int index = 0;
        for (OHLCVData data : arrayData) {
        	int lastIndex = index;
        	try {
        		if (lastIndex >= 0) {
    				if(data.getVol() > arrayData.get(lastIndex).getVol()){
    					stick.add(new ColoredStickEntity(data.getVol(), 0, (int) Long.parseLong(data.getDate()), Color.RED));
    				}else if(data.getVol() < arrayData.get(lastIndex).getVol()){
    					stick.add(new ColoredStickEntity(data.getVol(), 0, (int) Long.parseLong(data.getDate()), Color.GREEN));
    				}else{
    					stick.add(new ColoredStickEntity(data.getVol(), 0, (int) Long.parseLong(data.getDate()), Color.LTGRAY));
    				}
    			}else{
    				stick.add(new ColoredStickEntity(data.getVol(), 0, (int) Long.parseLong(data.getDate()), Color.LTGRAY));
    			}
			} catch (NumberFormatException e) {
				e.printStackTrace();
				continue;
			}
		}
        return stick;
    }
}
