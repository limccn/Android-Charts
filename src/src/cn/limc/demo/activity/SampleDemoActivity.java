package cn.limc.demo.activity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import cn.limc.androidcharts.R;
import cn.limc.androidcharts.axis.Axis;
import cn.limc.androidcharts.common.IDataCursor;
import cn.limc.androidcharts.entity.DateValueEntity;
import cn.limc.androidcharts.entity.LineEntity;
import cn.limc.androidcharts.event.IDisplayCursorListener;
import cn.limc.androidcharts.view.BOLLMASlipCandleStickChart;
import cn.limc.androidcharts.view.ColoredSlipStickChart;
import cn.limc.androidcharts.view.MACDChart;
import cn.limc.androidcharts.view.SlipLineChart;
import cn.limc.androidcharts.view.TickChart;
import cn.limc.demo.common.BaseActivity;
import cn.limc.demo.common.BaseLvAdapter;
import cn.limc.demo.common.DipUtils;
import cn.limc.demo.common.FastJsonPaser;
import cn.limc.demo.common.GroupChartData;
import cn.limc.demo.common.JSONBean;
import cn.limc.demo.common.OHLCVData;

@SuppressLint("SimpleDateFormat")
public class SampleDemoActivity extends BaseActivity {
	
	private static final int REFRESH_SECOND = 5;
	
	/**
	 * 自动刷新
	 */
	private Handler mRefreshHandler = new Handler();
    private Runnable mRefreshRunnable = new Runnable() {
        public void run() {
            this.update();
            mRefreshHandler.postDelayed(this, 1000 * REFRESH_SECOND);// 间隔120秒
        }
        void update() {
        	isRefresh = true;
            loadJSONData(mDisplayDataType);
        }
    }; 
    
	public enum ChartDataType {
		DAY,
		WEEK,
		MONTH,
		ONE_MINUTE,
		FIVE_MINUTE,
		FIFTEEN_MINUTE,
		THIRTY_MINUTE,
		ONE_HOUR,
		TWO_HOUR,
		FOUR_HOUR
	}
	
	public enum ChartViewType { 
		VOL,
		MACD,
		KDJ,
		RSI,
		WR,
		CCI,
		BOLL
	}
	
	private BroadcastReciver mBroadcastReciver;
	boolean isRefresh;
	
	int layoutResID;
	
	private List<OHLCVData> chartData;
	
	int mMACDS;
	int mMACDL;
	int mMACDM;
	
	int mMA1;
	int mMA2;
	int mMA3;
	
	int mKDJN;
	
	int mRSIN1;
	int mRSIN2;
	
	int mWRN;
	
	int mCCIN;
	
	int mBOLLN;
	
	ChartDataType mDisplayDataType;
	GroupChartData mCurrentData;
	
	GroupChartData mDayData;
	GroupChartData mWeekData;
	GroupChartData mMonthData;
	GroupChartData m1MinuteData;
	GroupChartData m5MinuteData;
	GroupChartData m15MinuteData;
	GroupChartData m30MinuteData;
	GroupChartData m1HourData;
	GroupChartData m2HourData;
	GroupChartData m4HourData;
	
	ChartViewType mDisplayChart;
	
	TextView mTvCurrentPrice;
	TextView mTvCurrentPercent;
	TextView mTvNewestPercent;
	TextView mTvDateTime;
	TextView mTvBuyPrice;
	TextView mTvHigh;
	TextView mTvSellPrice;
	TextView mTvLow;
	
	TextView mTvHandicap;
	TextView mTvTick;
	TextView mTvDetail;
	TextView mTvDay;
	TextView mTvWeek;
	TextView mTvMonth;
	TextView mTvMinute;
	
	PopupWindow mPopup;
	LinearLayout mLinPopup;
	
	BOLLMASlipCandleStickChart mBollmaslipcandlestickchart;
	TickChart mTickChart;
	LinearLayout mLinOrderContainer;
	int[] mOrdersId = {R.id.tickchart_order1, R.id.tickchart_order2, R.id.tickchart_order3, R.id.tickchart_order4, R.id.tickchart_order5, R.id.tickchart_order6,
			R.id.tickchart_order7, R.id.tickchart_order8, R.id.tickchart_order9, R.id.tickchart_order10};
	LinearLayout[] mLinOrders;
	
	LinearLayout mLinBottomChartContainer;
	ColoredSlipStickChart mVOLchart;
	MACDChart mMacdChart;
	SlipLineChart mKDJchart;
	SlipLineChart mRSIchart;
	SlipLineChart mWRchart;
	SlipLineChart mCCIchart;
	SlipLineChart mBOLLchart;
	
	LinearLayout mLinHandicap;
	
	LinearLayout mLinDetailTop;
	ListView mLvDetail;
	LvAdapter mLvAdapter;
	
	LinearLayout mLinBottomButtons;
	TextView mTvVOL;
	TextView mTvMACD;
	TextView mTvKDJ;
	TextView mTvRSI;
	TextView mTvWR;
	TextView mTvCCI;
	TextView mTvBOLL;
	TextView mTvSetting;
	
	Button mBtnHorizontal;
	
    @Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        if (layoutResID == 0) {
        	setContentView(R.layout.activity_sample_demo);
		}
        
        initWidgets();
        
        initBOLLMASlipCandleStickChart();
        initTickChart();
        
        initVOLChart();
        initMACDChart();
        initKDJChart();
        initRSIChart();
        initWRChart();
        initCCIChart();
        initBOLLChart();
        
        initHandicap();
        
        initDetail();
        
        initProductInfo();
        initTopViews();
        initPopupWindow();
        initBottomViews();
        
        mDisplayDataType = ChartDataType.DAY;
//        loadJSONData(ChartDataType.DAY);
        loadTickJSONData();
        
        registerReceiver();
        
        mRefreshHandler.postDelayed(mRefreshRunnable, 1000 * REFRESH_SECOND);
	}
    
    @Override
	protected void onDestroy() {
		super.onDestroy();
		
		unregisterReceiver(mBroadcastReciver);
		mBroadcastReciver = null;
		
		// 停止刷新
		mRefreshHandler.removeCallbacks(mRefreshRunnable);
	}
    
    @Override
    public void setContentView(int layoutResID) {
    	super.setContentView(layoutResID);
    	this.layoutResID = layoutResID;
    }
    
    private void initWidgets() {
    	this.mBtnHorizontal = (Button) findViewById(R.id.btn_horizontal);
    	if (mBtnHorizontal == null) {
			return;
		}
    	
    	mBtnHorizontal.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(SampleDemoActivity.this,
                        cn.limc.demo.activity.SampleHorizontalDemoActivity.class);
                startActivity(intent);
			}
		});
    }
    
    public void initProductInfo(){
    	mTvCurrentPrice = (TextView) findViewById(R.id.tv_current_price);
    	mTvCurrentPercent = (TextView) findViewById(R.id.tv_current_percent);
    	mTvNewestPercent = (TextView) findViewById(R.id.tv_newest_percent);
    	mTvDateTime = (TextView) findViewById(R.id.tv_date_time);
    	mTvBuyPrice = (TextView) findViewById(R.id.tv_buy_price);
    	mTvHigh = (TextView) findViewById(R.id.tv_high);
    	mTvSellPrice = (TextView) findViewById(R.id.tv_sell_price);
    	mTvLow = (TextView) findViewById(R.id.tv_low);
    	
    	mTvCurrentPrice.setText("3500");
    	mTvCurrentPercent.setText("-29.25");
    	mTvNewestPercent.setText("-1.25%");
    	
    	SimpleDateFormat dt = new SimpleDateFormat("MM月dd HH:mm:ss");
    	mTvDateTime.setText(dt.format(new Date()));

    	mTvBuyPrice.setText("3499");
    	mTvHigh.setText("3559");
    	mTvSellPrice.setText("3554");
    	mTvLow.setText("3444");
    }
    
    public void initTopViews(){
    	mTvHandicap = (TextView) findViewById(R.id.tv_handicap);
    	mTvTick = (TextView) findViewById(R.id.tv_tick);
    	mTvDetail = (TextView) findViewById(R.id.tv_detail);
    	mTvDay = (TextView) findViewById(R.id.tv_day);
    	mTvWeek = (TextView) findViewById(R.id.tv_week);
    	mTvMonth = (TextView) findViewById(R.id.tv_month);
    	mTvMinute = (TextView) findViewById(R.id.tv_minute);
    	
    	changeTopButtonsColor(mTvDay);
    	
    	mTvHandicap.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				changeTopButtonsColor((TextView) v);
				
				mLinHandicap.setVisibility(View.VISIBLE);
				
				mLinDetailTop.setVisibility(View.INVISIBLE);
				mLvDetail.setVisibility(View.INVISIBLE);
				mTickChart.setVisibility(View.INVISIBLE);
				mLinOrderContainer.setVisibility(View.INVISIBLE);
				mBollmaslipcandlestickchart.setVisibility(View.INVISIBLE);
				mLinBottomChartContainer.setVisibility(View.INVISIBLE);
				mLinBottomButtons.setVisibility(View.INVISIBLE);
			}
		});
    	
    	mTvTick.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				changeTopButtonsColor((TextView) v);
				
				mLinHandicap.setVisibility(View.INVISIBLE);
				
				mTickChart.setVisibility(View.VISIBLE);
				mLinOrderContainer.setVisibility(View.VISIBLE);
				
				mLinDetailTop.setVisibility(View.INVISIBLE);
				mLvDetail.setVisibility(View.INVISIBLE);
				mBollmaslipcandlestickchart.setVisibility(View.INVISIBLE);
				mLinBottomChartContainer.setVisibility(View.INVISIBLE);
				mLinBottomButtons.setVisibility(View.INVISIBLE);
			}
		});
    	
    	mTvDetail.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				changeTopButtonsColor((TextView) v);
				
				mLinHandicap.setVisibility(View.INVISIBLE);
				mTickChart.setVisibility(View.INVISIBLE);
				mLinOrderContainer.setVisibility(View.INVISIBLE);
				
				mLinDetailTop.setVisibility(View.VISIBLE);
				mLvDetail.setVisibility(View.VISIBLE);
				
				mBollmaslipcandlestickchart.setVisibility(View.INVISIBLE);
				mLinBottomChartContainer.setVisibility(View.INVISIBLE);
				mLinBottomButtons.setVisibility(View.INVISIBLE);
			}
		});

    	mTvDay.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	changeTopButtonsColor((TextView) v);
            	
                loadJSONData(ChartDataType.DAY);
            	mLinHandicap.setVisibility(View.INVISIBLE);
                mTickChart.setVisibility(View.INVISIBLE);
                mLinOrderContainer.setVisibility(View.INVISIBLE);
                mLinDetailTop.setVisibility(View.INVISIBLE);
				mLvDetail.setVisibility(View.INVISIBLE);
				mBollmaslipcandlestickchart.setVisibility(View.VISIBLE);
                mLinBottomChartContainer.setVisibility(View.VISIBLE);
                mLinBottomButtons.setVisibility(View.VISIBLE);
            }
        });
    	
    	mTvWeek.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	changeTopButtonsColor((TextView) v);
            	
                loadJSONData(ChartDataType.WEEK);
            	mLinHandicap.setVisibility(View.INVISIBLE);
                mTickChart.setVisibility(View.INVISIBLE);
                mLinOrderContainer.setVisibility(View.INVISIBLE);
                mLinDetailTop.setVisibility(View.INVISIBLE);
				mLvDetail.setVisibility(View.INVISIBLE);
				mBollmaslipcandlestickchart.setVisibility(View.VISIBLE);
                mLinBottomChartContainer.setVisibility(View.VISIBLE);
                mLinBottomButtons.setVisibility(View.VISIBLE);
            }
        });
    	
    	mTvMonth.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	changeTopButtonsColor((TextView) v);
            	
                loadJSONData(ChartDataType.MONTH);
            	mLinHandicap.setVisibility(View.INVISIBLE);
                mTickChart.setVisibility(View.INVISIBLE);
                mLinOrderContainer.setVisibility(View.INVISIBLE);
                mLinDetailTop.setVisibility(View.INVISIBLE);
				mLvDetail.setVisibility(View.INVISIBLE);
				mBollmaslipcandlestickchart.setVisibility(View.VISIBLE);
                mLinBottomChartContainer.setVisibility(View.VISIBLE);
                mLinBottomButtons.setVisibility(View.VISIBLE);
            }
        });
    	
    	mTvMinute.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	if(mPopup.isShowing()) {  
                    // 隐藏窗口，如果设置了点击窗口外小时即不需要此方式隐藏  
            		mPopup.dismiss();
                } else {
                    // 显示窗口 
                	mPopup.showAsDropDown(v);
                }
            }
        });
    }
    
    @SuppressLint("InflateParams")
    private void initPopupWindow(){
    	LayoutInflater inflater = LayoutInflater.from(this);  
        // 引入窗口配置文件  
        mLinPopup = (LinearLayout) inflater.inflate(R.layout.activity_sample_demo_popup_layout, null, false);  
        // 创建PopupWindow对象  
        mPopup = new PopupWindow(mLinPopup, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, false);
        // 需要设置一下此参数，点击外边可消失  
        mPopup.setBackgroundDrawable(new BitmapDrawable());  
        //设置点击窗口外边窗口消失  
        mPopup.setOutsideTouchable(true);  
        // 设置此参数获得焦点，否则无法点击  
        mPopup.setFocusable(true);
        
        for (int i = 0; i <  mLinPopup.getChildCount(); i++) {
			View view = mLinPopup.getChildAt(i);
			view.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if (mPopup.isShowing()) {
						mPopup.dismiss();
					}
				}
			});
		}
    }
    
    private void initBottomViews(){
    	mDisplayChart = ChartViewType.VOL;
    	
    	mLinBottomChartContainer = (LinearLayout) findViewById(R.id.lin_mask);
        
        for (int i = 0; i < 7; i++) {
			View view = mLinBottomChartContainer.getChildAt(i);
			view.setLayoutParams(new LinearLayout.LayoutParams(screenWidth, LayoutParams.MATCH_PARENT));
		}
    	
    	mLinBottomButtons = (LinearLayout) findViewById(R.id.lin_chart_type);
    	    	
    	mTvVOL = (TextView) findViewById(R.id.tv_vol);
    	mTvVOL.setTextColor(Color.LTGRAY);
    	mTvMACD = (TextView) findViewById(R.id.tv_macd);
    	mTvMACD.setTextColor(Color.LTGRAY);
    	mTvKDJ = (TextView) findViewById(R.id.tv_kdj);
    	mTvKDJ.setTextColor(Color.LTGRAY);
    	mTvRSI = (TextView) findViewById(R.id.tv_rsi);
    	mTvRSI.setTextColor(Color.LTGRAY);
    	mTvWR = (TextView) findViewById(R.id.tv_wr);
    	mTvWR.setTextColor(Color.LTGRAY);
    	mTvCCI = (TextView) findViewById(R.id.tv_cci);
    	mTvCCI.setTextColor(Color.LTGRAY);
    	mTvBOLL = (TextView) findViewById(R.id.tv_boll);
    	mTvBOLL.setTextColor(Color.LTGRAY);
    	mTvSetting = (TextView) findViewById(R.id.tv_setting);
    	
    	hideBottomChart();
    	
//    	mLinBottomChartContainer.setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				switch (mDisplayChart) {
//				case VOL:
//					hideBottomChart();
//					mMacdChart.setVisibility(View.VISIBLE);
//					mDisplayChart = ChartViewType.MACD;
//					break;
//				case MACD:
//					hideBottomChart();
//					mKDJchart.setVisibility(View.VISIBLE);
//					mDisplayChart = ChartViewType.KDJ;
//					break;
//				case KDJ:
//					hideBottomChart();
//					mRSIchart.setVisibility(View.VISIBLE);
//					mDisplayChart = ChartViewType.RSI;
//					break;
//				case RSI:
//					hideBottomChart();
//					mWRchart.setVisibility(View.VISIBLE);
//					mDisplayChart = ChartViewType.WR;
//					break;
//				case WR:
//					hideBottomChart();
//					mCCIchart.setVisibility(View.VISIBLE);
//					mDisplayChart = ChartViewType.CCI;
//					break;
//				case CCI:
//					hideBottomChart();
//					mBOLLchart.setVisibility(View.VISIBLE);
//					mDisplayChart = ChartViewType.BOLL;
//					break;
//				case BOLL:
//					hideBottomChart();
//					mVOLchart.setVisibility(View.VISIBLE);
//					mDisplayChart = ChartViewType.VOL;
//					break;
//					
//				default:
//					break;
//				}
//			}
//		});
    	
    	mTvVOL.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mDisplayChart = ChartViewType.VOL;
                hideBottomChart();
                mVOLchart.setDisplayFrom(mBollmaslipcandlestickchart.getDisplayFrom());
                mVOLchart.setDisplayNumber(mBollmaslipcandlestickchart.getDisplayNumber());
                mVOLchart.postInvalidate();
                mBollmaslipcandlestickchart.setDisplayBollBand(false);
                mBollmaslipcandlestickchart.postInvalidate();
			}
		});
    	mTvMACD.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
                mDisplayChart = ChartViewType.MACD;
                hideBottomChart();
                mMacdChart.setDisplayFrom(mBollmaslipcandlestickchart.getDisplayFrom());
                mMacdChart.setDisplayNumber(mBollmaslipcandlestickchart.getDisplayNumber());
                mMacdChart.postInvalidate();
                mBollmaslipcandlestickchart.setDisplayBollBand(false);
                mBollmaslipcandlestickchart.postInvalidate();
			}
		});
    	mTvKDJ.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
                mDisplayChart = ChartViewType.KDJ;
                hideBottomChart();
                mKDJchart.setDisplayFrom(mBollmaslipcandlestickchart.getDisplayFrom());
                mKDJchart.setDisplayNumber(mBollmaslipcandlestickchart.getDisplayNumber());
                mKDJchart.postInvalidate();
                mBollmaslipcandlestickchart.setDisplayBollBand(false);
                mBollmaslipcandlestickchart.postInvalidate();
			}
		});
    	mTvRSI.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
                mDisplayChart = ChartViewType.RSI;
                hideBottomChart();
                mRSIchart.setDisplayFrom(mBollmaslipcandlestickchart.getDisplayFrom());
                mRSIchart.setDisplayNumber(mBollmaslipcandlestickchart.getDisplayNumber());
                mRSIchart.postInvalidate();
                mBollmaslipcandlestickchart.setDisplayBollBand(false);
                mBollmaslipcandlestickchart.postInvalidate();
			}
		});
    	mTvWR.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
                mDisplayChart = ChartViewType.WR;
                hideBottomChart();
                mWRchart.setVisibility(View.VISIBLE);
                mWRchart.setDisplayFrom(mBollmaslipcandlestickchart.getDisplayFrom());
                mWRchart.setDisplayNumber(mBollmaslipcandlestickchart.getDisplayNumber());
                mWRchart.postInvalidate();
                mBollmaslipcandlestickchart.setDisplayBollBand(false);
                mBollmaslipcandlestickchart.postInvalidate();
			}
		});
    	mTvCCI.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
                mDisplayChart = ChartViewType.CCI;
                hideBottomChart();
                mCCIchart.setDisplayFrom(mBollmaslipcandlestickchart.getDisplayFrom());
                mCCIchart.setDisplayNumber(mBollmaslipcandlestickchart.getDisplayNumber());
                mCCIchart.postInvalidate();
                mBollmaslipcandlestickchart.setDisplayBollBand(false);
                mBollmaslipcandlestickchart.postInvalidate();
			}
		});
    	mTvBOLL.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
                mDisplayChart = ChartViewType.BOLL;
                hideBottomChart();
                mBOLLchart.setDisplayFrom(mBollmaslipcandlestickchart.getDisplayFrom());
                mBOLLchart.setDisplayNumber(mBollmaslipcandlestickchart.getDisplayNumber());
                mBOLLchart.postInvalidate();
                mBollmaslipcandlestickchart.setDisplayBollBand(true);
                mBollmaslipcandlestickchart.postInvalidate();
			}
		});
    	mTvSetting.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(SampleDemoActivity.this, SettingActivity.class);
				
                startActivity(intent);
			}
		});
    }
    
    public void changeTopButtonsColor(TextView tvSelected){
    	mTvHandicap.setTextColor(Color.LTGRAY);
    	mTvTick.setTextColor(Color.LTGRAY);
    	mTvDetail.setTextColor(Color.LTGRAY);
    	mTvDay.setTextColor(Color.LTGRAY);
    	mTvWeek.setTextColor(Color.LTGRAY);
    	mTvMonth.setTextColor(Color.LTGRAY);
    	mTvMinute.setTextColor(Color.LTGRAY);
    	
    	tvSelected.setTextColor(Color.WHITE);
    }
    
    private void hideBottomChart(){
        if (mDisplayChart == ChartViewType.VOL){
        	mLinBottomChartContainer.scrollTo(0, 0);
            mTvVOL.setTextColor(Color.WHITE);
        }else {
            mTvVOL.setTextColor(Color.LTGRAY);
        }
        if (mDisplayChart == ChartViewType.MACD) {
        	mLinBottomChartContainer.scrollTo(screenWidth, 0);
            mTvMACD.setTextColor(Color.WHITE);
        }else{
            mTvMACD.setTextColor(Color.LTGRAY);
        }
        if (mDisplayChart == ChartViewType.KDJ){
        	mLinBottomChartContainer.scrollTo(screenWidth*2, 0);
            mTvKDJ.setTextColor(Color.WHITE);
        }else {
            mTvKDJ.setTextColor(Color.LTGRAY);
        }
        if (mDisplayChart == ChartViewType.RSI){
        	mLinBottomChartContainer.scrollTo(screenWidth*3, 0);
            mTvRSI.setTextColor(Color.WHITE);
        }else {
            mTvRSI.setTextColor(Color.LTGRAY);
        }
        if (mDisplayChart == ChartViewType.WR){
        	mLinBottomChartContainer.scrollTo(screenWidth*4, 0);
            mTvWR.setTextColor(Color.WHITE);
        }else {
            mTvWR.setTextColor(Color.LTGRAY);
        }
        if (mDisplayChart == ChartViewType.CCI){
        	mLinBottomChartContainer.scrollTo(screenWidth*5, 0);
            mTvCCI.setTextColor(Color.WHITE);
        }else {
            mTvCCI.setTextColor(Color.LTGRAY);
        }
        if (mDisplayChart == ChartViewType.BOLL){
        	mLinBottomChartContainer.scrollTo(screenWidth*6, 0);
            mTvBOLL.setTextColor(Color.WHITE);
        }else {
            mTvBOLL.setTextColor(Color.LTGRAY);
        }
    }
    
    private void initBOLLMASlipCandleStickChart() {
        this.mBollmaslipcandlestickchart = (BOLLMASlipCandleStickChart) findViewById(R.id.bollmaslipcandlestickchart);

        mBollmaslipcandlestickchart.setAxisXColor(Color.LTGRAY);
        mBollmaslipcandlestickchart.setAxisYColor(Color.LTGRAY);
        mBollmaslipcandlestickchart.setLatitudeColor(Color.GRAY);
        mBollmaslipcandlestickchart.setLongitudeColor(Color.GRAY);
        mBollmaslipcandlestickchart.setBorderColor(Color.LTGRAY);
        mBollmaslipcandlestickchart.setLongitudeFontColor(Color.WHITE);
        mBollmaslipcandlestickchart.setLatitudeFontColor(Color.WHITE);

        // 最大纬线数
        mBollmaslipcandlestickchart.setLatitudeNum(5);
        // 最大经线数
        mBollmaslipcandlestickchart.setLongitudeNum(3);
        // 最大价格
        mBollmaslipcandlestickchart.setMaxValue(1200);
        // 最小价格
        mBollmaslipcandlestickchart.setMinValue(200);

//		mBollmaslipcandlestickchart.setDisplayFrom(10);

//		mBollmaslipcandlestickchart.setDisplayNumber(30);

//		mBollmaslipcandlestickchart.setMinDisplayNumber(10);

//        mBollmaslipcandlestickchart
//                .setZoomBaseLine(IZoomable.ZOOM_BASE_LINE_CENTER);

        mBollmaslipcandlestickchart.setDisplayLongitudeTitle(true);
        mBollmaslipcandlestickchart.setDisplayLatitudeTitle(true);
        mBollmaslipcandlestickchart.setDisplayLatitude(true);
        mBollmaslipcandlestickchart.setDisplayLongitude(true);
        mBollmaslipcandlestickchart.setBackgroundColor(Color.BLACK);

        mBollmaslipcandlestickchart.setDataQuadrantPaddingTop(5);
        mBollmaslipcandlestickchart.setDataQuadrantPaddingBottom(5);
        mBollmaslipcandlestickchart.setDataQuadrantPaddingLeft(5);
        mBollmaslipcandlestickchart.setDataQuadrantPaddingRight(5);
//      mBollmaslipcandlestickchart.setAxisYTitleQuadrantWidth(50);
//      mBollmaslipcandlestickchart.setAxisXTitleQuadrantHeight(20);
        mBollmaslipcandlestickchart
                .setAxisXPosition(Axis.AXIS_X_POSITION_BOTTOM);
        mBollmaslipcandlestickchart
                .setAxisYPosition(Axis.AXIS_Y_POSITION_RIGHT);

        mBollmaslipcandlestickchart.setOnDisplayCursorListener(displayCursorListener);
        mBollmaslipcandlestickchart.setNoneDisplayValue(new float[]{0,Float.MAX_VALUE});
    }

    final IDisplayCursorListener displayCursorListener =  new IDisplayCursorListener() {
        @Override
        public void onCursorChanged(IDataCursor cursor, int displayFrom, int displayNumber) {

            if (cursor ==  mBollmaslipcandlestickchart){

                mVOLchart.setDisplayFrom(displayFrom);
                mVOLchart.setDisplayNumber(displayNumber);
                mMacdChart.setDisplayFrom(displayFrom);
                mMacdChart.setDisplayNumber(displayNumber);
                mKDJchart.setDisplayFrom(displayFrom);
                mKDJchart.setDisplayNumber(displayNumber);
                mRSIchart.setDisplayFrom(displayFrom);
                mRSIchart.setDisplayNumber(displayNumber);
                mWRchart.setDisplayFrom(displayFrom);
                mWRchart.setDisplayNumber(displayNumber);
                mCCIchart.setDisplayFrom(displayFrom);
                mCCIchart.setDisplayNumber(displayNumber);
                mBOLLchart.setDisplayFrom(displayFrom);
                mBOLLchart.setDisplayNumber(displayNumber);

                switch (mDisplayChart) {
                    case VOL:
                        mVOLchart.postInvalidate();
                        break;
                    case MACD:
                        mMacdChart.postInvalidate();
                        break;
                    case KDJ:
                        mKDJchart.postInvalidate();
                        break;
                    case RSI:
                        mRSIchart.postInvalidate();
                        break;
                    case WR:
                        mWRchart.postInvalidate();
                        break;
                    case CCI:
                        mCCIchart.postInvalidate();
                        break;
                    case BOLL:
                        mBOLLchart.postInvalidate();
                        break;
                    default:
                        break;
                }
            }else{
                mBollmaslipcandlestickchart.setDisplayFrom(displayFrom);
                mBollmaslipcandlestickchart.setDisplayNumber(displayNumber);
                mBollmaslipcandlestickchart.postInvalidate();
            }
        }
    };
    
    private void initTickChart(){
    	this.mTickChart = (TickChart) findViewById(R.id.timeschart);
    	this.mLinOrderContainer = (LinearLayout) findViewById(R.id.lin_order_container);
    	
    	mTickChart.setAxisXColor(Color.LTGRAY);
    	mTickChart.setAxisYColor(Color.LTGRAY);
    	mTickChart.setBorderColor(Color.LTGRAY);
    	mTickChart.setLongitudeFontSize(14);
    	mTickChart.setLongitudeFontColor(Color.WHITE);
    	mTickChart.setLatitudeColor(Color.GRAY);
        mTickChart.setLatitudeFontColor(Color.WHITE);
        mTickChart.setLongitudeColor(Color.GRAY);
        mTickChart.setMaxValue(1300);
        mTickChart.setMinValue(700);
//        mTickChart.setDisplayFrom(10);
//        mTickChart.setDisplayNumber(30);
//        mTickChart.setMinDisplayNumber(5);
//        mTickChart.setZoomBaseLine(IZoomable.ZOOM_BASE_LINE_CENTER);
        mTickChart.setDisplayLongitudeTitle(true);
        mTickChart.setDisplayLatitudeTitle(true);
        mTickChart.setDisplayLatitude(true);
        mTickChart.setDisplayLongitude(true);
        mTickChart.setDataQuadrantPaddingTop(5);
        mTickChart.setDataQuadrantPaddingBottom(5);
        mTickChart.setDataQuadrantPaddingLeft(5);
        mTickChart.setDataQuadrantPaddingRight(5);
        // mTickChart.setAxisYTitleQuadrantWidth(50);
        // mTickChart.setAxisXTitleQuadrantHeight(20);
        mTickChart.setAxisXPosition(Axis.AXIS_X_POSITION_BOTTOM);
        mTickChart.setAxisYPosition(Axis.AXIS_Y_POSITION_RIGHT);
        
        mLinOrders = new LinearLayout[10];
        for (int i = 0; i < mOrdersId.length; i++) {
        	mLinOrders[i] = (LinearLayout) findViewById(mOrdersId[i]);
        	
        	TextView tvTime = new TextView(this);
        	tvTime.setLayoutParams(new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT));
        	((LinearLayout.LayoutParams)tvTime.getLayoutParams()).weight = 1;
        	tvTime.setGravity(Gravity.CENTER);
           	tvTime.setTextColor(Color.LTGRAY);
        	tvTime.setText("09:36");
        	tvTime.setTextSize(12.0f);
        	mLinOrders[i].addView(tvTime);
        	
        	TextView tvPrice = new TextView(this);
        	tvPrice.setLayoutParams(new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT));
        	((LinearLayout.LayoutParams)tvPrice.getLayoutParams()).weight = 1;
        	tvPrice.setGravity(Gravity.CENTER);
        	tvPrice.setTextColor(Color.LTGRAY);
        	tvPrice.setText("3408");
        	tvPrice.setTextSize(12.0f);
        	mLinOrders[i].addView(tvPrice);
        	
        	TextView tvCount = new TextView(this);
        	tvCount.setLayoutParams(new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT));
        	((LinearLayout.LayoutParams)tvCount.getLayoutParams()).weight = 1;
        	tvCount.setGravity(Gravity.CENTER);
        	tvCount.setTextColor(Color.LTGRAY);
        	tvCount.setText("3");
        	tvCount.setTextSize(12.0f);
        	mLinOrders[i].addView(tvCount);
		}
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
        mVOLchart.setAxisXDateTargetFormat("yyyy/MM/dd");
        mVOLchart.setAxisXDateSourceFormat("yyyyMMdd");
        mVOLchart.setOnDisplayCursorListener(displayCursorListener);
    }
    
    @SuppressWarnings("static-access")
	private void initMACDChart() {
    	mMacdChart = (MACDChart) findViewById(R.id.macdchart);
        mMacdChart.setMaxValue(300000);
        mMacdChart.setMinValue(-300000);
        // mMacdChart.setDisplayCrossXOnTouch(true);
        // mMacdChart.setDisplayCrossYOnTouch(true);
        mMacdChart.setLatitudeNum(4);
        mMacdChart.setLongitudeNum(3);
//        mMacdChart.setDisplayFrom(0);
//        mMacdChart.setDisplayNumber(10);
//        mMacdChart.setMinDisplayNumber(10);
//        mMacdChart.setZoomBaseLine(IZoomable.ZOOM_BASE_LINE_CENTER);
        mMacdChart.setAxisXColor(Color.LTGRAY);
        mMacdChart.setAxisYColor(Color.LTGRAY);
        mMacdChart.setLatitudeColor(Color.GRAY);
        mMacdChart.setLongitudeColor(Color.GRAY);
        mMacdChart.setBorderColor(Color.LTGRAY);
        mMacdChart.setLongitudeFontColor(Color.WHITE);
        mMacdChart.setLatitudeFontColor(Color.WHITE);
        mMacdChart.setMacdDisplayType(mMacdChart.MACD_DISPLAY_TYPE_STICK);
//        mMacdChart.setPositiveStickColor(Color.RED);
//        mMacdChart.setNegativeStickColor(Color.CYAN);
        mMacdChart.setMacdLineColor(Color.CYAN);
        mMacdChart.setDeaLineColor(Color.YELLOW);
        mMacdChart.setDiffLineColor(Color.WHITE);
        mMacdChart.setDataQuadrantPaddingTop(5);
        mMacdChart.setDataQuadrantPaddingBottom(5);
        mMacdChart.setDataQuadrantPaddingLeft(5);
        mMacdChart.setDataQuadrantPaddingRight(5);
        // mMacdChart.setAxisYTitleQuadrantWidth(50);
        // mMacdChart.setAxisXTitleQuadrantHeight(20);
        mMacdChart.setAxisXPosition(Axis.AXIS_X_POSITION_BOTTOM);
        mMacdChart.setAxisYPosition(Axis.AXIS_Y_POSITION_RIGHT);
        mMacdChart.setOnDisplayCursorListener(displayCursorListener);
        mMacdChart.setNoneDisplayValue(new float[]{Float.MAX_VALUE});
    }
    
    private void initKDJChart() {
        mKDJchart = (SlipLineChart) findViewById(R.id.kdjchart);

        mKDJchart.setAxisXColor(Color.LTGRAY);
        mKDJchart.setAxisYColor(Color.LTGRAY);
        mKDJchart.setBorderColor(Color.LTGRAY);
        mKDJchart.setLongitudeFontSize(14);
        mKDJchart.setLongitudeFontColor(Color.WHITE);
        mKDJchart.setLatitudeColor(Color.GRAY);
        mKDJchart.setLatitudeFontColor(Color.WHITE);
        mKDJchart.setLongitudeColor(Color.GRAY);
        mKDJchart.setMaxValue(100);
        mKDJchart.setMinValue(0);
//        mKDJchart.setDisplayFrom(10);
//        mKDJchart.setDisplayNumber(30);
//        mKDJchart.setMinDisplayNumber(10);
//        mKDJchart.setZoomBaseLine(IZoomable.ZOOM_BASE_LINE_CENTER);
        mKDJchart.setDisplayLongitudeTitle(true);
        mKDJchart.setDisplayLatitudeTitle(true);
        mKDJchart.setDisplayLatitude(true);
        mKDJchart.setDisplayLongitude(true);
        mKDJchart.setDataQuadrantPaddingTop(5);
        mKDJchart.setDataQuadrantPaddingBottom(5);
        mKDJchart.setDataQuadrantPaddingLeft(5);
        mKDJchart.setDataQuadrantPaddingRight(5);
        // mKDJchart.setAxisYTitleQuadrantWidth(50);
        // mKDJchart.setAxisXTitleQuadrantHeight(20);
        mKDJchart.setAxisXPosition(Axis.AXIS_X_POSITION_BOTTOM);
        mKDJchart.setAxisYPosition(Axis.AXIS_Y_POSITION_RIGHT);
        mKDJchart.setOnDisplayCursorListener(displayCursorListener);
        mKDJchart.setNoneDisplayValue(new float[]{0});
//        mKDJchart.setAutoCalcValueRange(false);
    }
    
    private void initRSIChart() {
    	mRSIchart = (SlipLineChart) findViewById(R.id.rsichart);

    	mRSIchart.setAxisXColor(Color.LTGRAY);
        mRSIchart.setAxisYColor(Color.LTGRAY);
        mRSIchart.setBorderColor(Color.LTGRAY);
        mRSIchart.setLongitudeFontSize(14);
        mRSIchart.setLongitudeFontColor(Color.WHITE);
        mRSIchart.setLatitudeColor(Color.GRAY);
        mRSIchart.setLatitudeFontColor(Color.WHITE);
        mRSIchart.setLongitudeColor(Color.GRAY);
        mRSIchart.setMaxValue(100);
        mRSIchart.setMinValue(0);
//        mRSIchart.setDisplayFrom(10);
//        mRSIchart.setDisplayNumber(30);
//        mRSIchart.setMinDisplayNumber(10);
//        mRSIchart.setZoomBaseLine(IZoomable.ZOOM_BASE_LINE_CENTER);
        mRSIchart.setDisplayLongitudeTitle(true);
        mRSIchart.setDisplayLatitudeTitle(true);
        mRSIchart.setDisplayLatitude(true);
        mRSIchart.setDisplayLongitude(true);
        mRSIchart.setDataQuadrantPaddingTop(5);
        mRSIchart.setDataQuadrantPaddingBottom(5);
        mRSIchart.setDataQuadrantPaddingLeft(5);
        mRSIchart.setDataQuadrantPaddingRight(5);
        // mRSIchart.setAxisYTitleQuadrantWidth(50);
        // mRSIchart.setAxisXTitleQuadrantHeight(20);
        mRSIchart.setAxisXPosition(Axis.AXIS_X_POSITION_BOTTOM);
        mRSIchart.setAxisYPosition(Axis.AXIS_Y_POSITION_RIGHT);
        mRSIchart.setOnDisplayCursorListener(displayCursorListener);
        mRSIchart.setAutoCalcValueRange(false);
        mRSIchart.setNoneDisplayValue(new float[]{0});
    }
    
    private void initWRChart() {
    	mWRchart = (SlipLineChart) findViewById(R.id.wrchart);

    	mWRchart.setAxisXColor(Color.LTGRAY);
        mWRchart.setAxisYColor(Color.LTGRAY);
        mWRchart.setBorderColor(Color.LTGRAY);
        mWRchart.setLongitudeFontSize(14);
        mWRchart.setLongitudeFontColor(Color.WHITE);
        mWRchart.setLatitudeColor(Color.GRAY);
        mWRchart.setLatitudeFontColor(Color.WHITE);
        mWRchart.setLongitudeColor(Color.GRAY);
        mWRchart.setMaxValue(100);
        mWRchart.setMinValue(0);
//        mWRchart.setDisplayFrom(10);
//        mWRchart.setDisplayNumber(30);
//        mWRchart.setMinDisplayNumber(10);
//        mWRchart.setZoomBaseLine(IZoomable.ZOOM_BASE_LINE_CENTER);
        mWRchart.setDisplayLongitudeTitle(true);
        mWRchart.setDisplayLatitudeTitle(true);
        mWRchart.setDisplayLatitude(true);
        mWRchart.setDisplayLongitude(true);
        mWRchart.setDataQuadrantPaddingTop(5);
        mWRchart.setDataQuadrantPaddingBottom(5);
        mWRchart.setDataQuadrantPaddingLeft(5);
        mWRchart.setDataQuadrantPaddingRight(5);
        // mWRchart.setAxisYTitleQuadrantWidth(50);
        // mWRchart.setAxisXTitleQuadrantHeight(20);
        mWRchart.setAxisXPosition(Axis.AXIS_X_POSITION_BOTTOM);
        mWRchart.setAxisYPosition(Axis.AXIS_Y_POSITION_RIGHT);
        mWRchart.setOnDisplayCursorListener(displayCursorListener);
        mWRchart.setAutoCalcValueRange(false);
        mWRchart.setNoneDisplayValue(new float[]{101});
    }
    
    private void initCCIChart() {
    	mCCIchart = (SlipLineChart) findViewById(R.id.ccichart);

    	mCCIchart.setAxisXColor(Color.LTGRAY);
        mCCIchart.setAxisYColor(Color.LTGRAY);
        mCCIchart.setBorderColor(Color.LTGRAY);
        mCCIchart.setLongitudeFontSize(14);
        mCCIchart.setLongitudeFontColor(Color.WHITE);
        mCCIchart.setLatitudeColor(Color.GRAY);
        mCCIchart.setLatitudeFontColor(Color.WHITE);
        mCCIchart.setLongitudeColor(Color.GRAY);
        mCCIchart.setMaxValue(1300);
        mCCIchart.setMinValue(700);
//        mCCIchart.setDisplayFrom(10);
//        mCCIchart.setDisplayNumber(30);
//        mCCIchart.setMinDisplayNumber(10);
//        mCCIchart.setZoomBaseLine(IZoomable.ZOOM_BASE_LINE_CENTER);
        mCCIchart.setDisplayLongitudeTitle(true);
        mCCIchart.setDisplayLatitudeTitle(true);
        mCCIchart.setDisplayLatitude(true);
        mCCIchart.setDisplayLongitude(true);
        mCCIchart.setDataQuadrantPaddingTop(5);
        mCCIchart.setDataQuadrantPaddingBottom(5);
        mCCIchart.setDataQuadrantPaddingLeft(5);
        mCCIchart.setDataQuadrantPaddingRight(5);
        // mCCIchart.setAxisYTitleQuadrantWidth(50);
        // mCCIchart.setAxisXTitleQuadrantHeight(20);
        mCCIchart.setAxisXPosition(Axis.AXIS_X_POSITION_BOTTOM);
        mCCIchart.setAxisYPosition(Axis.AXIS_Y_POSITION_RIGHT);
        mCCIchart.setOnDisplayCursorListener(displayCursorListener);
        mCCIchart.setAutoCalcValueRange(true);
        mCCIchart.setNoneDisplayValue(new float[]{Float.MAX_VALUE});
    }
    
    private void initBOLLChart() {
    	mBOLLchart = (SlipLineChart) findViewById(R.id.bollchart);

    	mBOLLchart.setAxisXColor(Color.LTGRAY);
        mBOLLchart.setAxisYColor(Color.LTGRAY);
        mBOLLchart.setBorderColor(Color.LTGRAY);
        mBOLLchart.setLongitudeFontSize(14);
        mBOLLchart.setLongitudeFontColor(Color.WHITE);
        mBOLLchart.setLatitudeColor(Color.GRAY);
        mBOLLchart.setLatitudeFontColor(Color.WHITE);
        mBOLLchart.setLongitudeColor(Color.GRAY);
        mBOLLchart.setMaxValue(1300);
        mBOLLchart.setMinValue(700);
//        mBOLLchart.setDisplayFrom(10);
//        mBOLLchart.setDisplayNumber(30);
//        mBOLLchart.setMinDisplayNumber(10);
//        mBOLLchart.setZoomBaseLine(IZoomable.ZOOM_BASE_LINE_CENTER);
        mBOLLchart.setDisplayLongitudeTitle(true);
        mBOLLchart.setDisplayLatitudeTitle(true);
        mBOLLchart.setDisplayLatitude(true);
        mBOLLchart.setDisplayLongitude(true);
        mBOLLchart.setDataQuadrantPaddingTop(5);
        mBOLLchart.setDataQuadrantPaddingBottom(5);
        mBOLLchart.setDataQuadrantPaddingLeft(5);
        mBOLLchart.setDataQuadrantPaddingRight(5);
        // mBOLLchart.setAxisYTitleQuadrantWidth(50);
        // mBOLLchart.setAxisXTitleQuadrantHeight(20);
        mBOLLchart.setAxisXPosition(Axis.AXIS_X_POSITION_BOTTOM);
        mBOLLchart.setAxisYPosition(Axis.AXIS_Y_POSITION_RIGHT);
        mBOLLchart.setOnDisplayCursorListener(displayCursorListener);
        mBOLLchart.setNoneDisplayValue(new float[]{Float.MAX_VALUE});
    }
    
    public void initHandicap() {
		this.mLinHandicap = (LinearLayout) findViewById(R.id.lin_handicap);
		
		for (int i = 0; i < 7; i++) {
			LinearLayout linHandicap = new LinearLayout(this);
			linHandicap.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 0));
        	((LinearLayout.LayoutParams)linHandicap.getLayoutParams()).weight = 1;
        	linHandicap.setOrientation(LinearLayout.HORIZONTAL);
        	mLinHandicap.addView(linHandicap);
        	
			TextView tvLeftLabel = new TextView(this);
			tvLeftLabel.setLayoutParams(new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT));
        	((LinearLayout.LayoutParams)tvLeftLabel.getLayoutParams()).weight = 1;
        	((LinearLayout.LayoutParams)tvLeftLabel.getLayoutParams()).rightMargin = DipUtils.dip2px(this, 5);
        	tvLeftLabel.setGravity(Gravity.RIGHT);
        	tvLeftLabel.setTextColor(Color.LTGRAY);
        	tvLeftLabel.setTextSize(12.0f);
        	linHandicap.addView(tvLeftLabel);
        	
        	TextView tvLeftValue = new TextView(this);
        	tvLeftValue.setLayoutParams(new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT));
        	((LinearLayout.LayoutParams)tvLeftValue.getLayoutParams()).weight = 1;
        	((LinearLayout.LayoutParams)tvLeftValue.getLayoutParams()).leftMargin = DipUtils.dip2px(this, 5);
        	tvLeftValue.setGravity(Gravity.LEFT);
        	tvLeftValue.setTextColor(Color.LTGRAY);
        	tvLeftValue.setTextSize(12.0f);
        	linHandicap.addView(tvLeftValue);
        	
        	TextView tvRightLabel = new TextView(this);
        	tvRightLabel.setLayoutParams(new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT));
        	((LinearLayout.LayoutParams)tvRightLabel.getLayoutParams()).weight = 1;
        	((LinearLayout.LayoutParams)tvRightLabel.getLayoutParams()).rightMargin = DipUtils.dip2px(this, 5);
        	tvRightLabel.setGravity(Gravity.RIGHT);
        	tvRightLabel.setTextColor(Color.LTGRAY);
        	tvRightLabel.setTextSize(12.0f);
        	linHandicap.addView(tvRightLabel);
        	
        	TextView tvRightValue = new TextView(this);
        	tvRightValue.setLayoutParams(new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT));
        	((LinearLayout.LayoutParams)tvRightValue.getLayoutParams()).weight = 1;
        	((LinearLayout.LayoutParams)tvRightValue.getLayoutParams()).leftMargin = DipUtils.dip2px(this, 5);
        	tvRightValue.setGravity(Gravity.LEFT);
        	tvRightValue.setTextColor(Color.LTGRAY);
        	tvRightValue.setTextSize(12.0f);
        	linHandicap.addView(tvRightValue);
        	
        	switch (i) {
			case 0:
				tvLeftLabel.setText("卖价:");
				tvLeftValue.setText("3559");
				tvRightLabel.setText("卖量:");
				tvRightValue.setText("2");
				break;
			case 1:
				tvLeftLabel.setText("买价:");
				tvLeftValue.setText("3559");
				tvRightLabel.setText("买量:");
				tvRightValue.setText("1");
				break;
			case 2:
				tvLeftLabel.setText("最新:");
				tvLeftValue.setText("3559");
				tvRightLabel.setText("涨跌:");
				tvRightValue.setText("2");
				break;
			case 3:
				tvLeftLabel.setText("最高:");
				tvLeftValue.setText("3559");
				tvRightLabel.setText("最低:");
				tvRightValue.setText("3559");
				break;
			case 4:
				tvLeftLabel.setText("开盘:");
				tvLeftValue.setText("3559");
				tvRightLabel.setText("总量:");
				tvRightValue.setText("3559");
				break;
			case 5:
				tvLeftLabel.setText("昨收:");
				tvLeftValue.setText("3559");
				tvRightLabel.setText("总额:");
				tvRightValue.setText("3559");
				break;
			case 6:
				tvLeftLabel.setText("昨结:");
				tvLeftValue.setText("3559");
				tvRightLabel.setText("持货:");
				tvRightValue.setText("3559");
				break;
			default:
				break;
			}
		}
	}
    
    private void initDetail() {
    	this.mLinDetailTop = (LinearLayout) findViewById(R.id.lin_detail_top);
    	this.mLvDetail = (ListView) findViewById(R.id.lv_detail);
    	
    	List<Map<String, String>> detailData = new ArrayList<Map<String, String>>();
    	
    	Map<String, String> detail1 = new HashMap<String, String>();
    	detail1.put("time", "14:02:53");
    	detail1.put("price", "2742");
    	detail1.put("count", "10");
    	detailData.add(detail1);
    	
    	Map<String, String> detail2 = new HashMap<String, String>();
    	detail2.put("time", "14:02:53");
    	detail2.put("price", "2742");
    	detail2.put("count", "10");
    	detailData.add(detail2);
    	
    	Map<String, String> detail3 = new HashMap<String, String>();
    	detail3.put("time", "14:02:53");
    	detail3.put("price", "2742");
    	detail3.put("count", "10");
    	detailData.add(detail3);
    	
    	Map<String, String> detail4 = new HashMap<String, String>();
    	detail4.put("time", "14:02:53");
    	detail4.put("price", "2742");
    	detail4.put("count", "10");
    	detailData.add(detail4);
    	
    	Map<String, String> detail5 = new HashMap<String, String>();
    	detail5.put("time", "14:02:53");
    	detail5.put("price", "2742");
    	detail5.put("count", "10");
    	detailData.add(detail5);
    	
    	Map<String, String> detail6 = new HashMap<String, String>();
    	detail6.put("time", "14:02:53");
    	detail6.put("price", "2742");
    	detail6.put("count", "10");
    	detailData.add(detail6);
    	
    	Map<String, String> detail7 = new HashMap<String, String>();
    	detail7.put("time", "14:02:53");
    	detail7.put("price", "2742");
    	detail7.put("count", "10");
    	detailData.add(detail7);
    	
    	Map<String, String> detail8 = new HashMap<String, String>();
    	detail8.put("time", "14:02:53");
    	detail8.put("price", "2742");
    	detail8.put("count", "10");
    	detailData.add(detail8);
    	
    	mLvAdapter = new LvAdapter(this, detailData, mLvDetail);
	}
    
    /**
     * 加载JSON
     * @return
     */
    public void loadJSONData(ChartDataType dataType){
    	GroupChartData currentChartData = dataType == ChartDataType.DAY?mDayData:dataType == ChartDataType.WEEK?mWeekData:dataType == ChartDataType.MONTH?mMonthData:dataType == ChartDataType.ONE_MINUTE?m1MinuteData:dataType == ChartDataType.FIVE_MINUTE?m5MinuteData:dataType == ChartDataType.FIFTEEN_MINUTE?m15MinuteData:dataType == ChartDataType.THIRTY_MINUTE?m30MinuteData:dataType == ChartDataType.ONE_HOUR?m1HourData:dataType == ChartDataType.TWO_HOUR?m2HourData:m4HourData;
    	
    	if (currentChartData == null || isRefresh) {
    		System.out.println("...refresh...");
    		
    		isRefresh = false;
    		
    		String strFileNm = dataType == ChartDataType.DAY?"15min.txt":dataType == ChartDataType.WEEK?"1min.txt":"time.txt";
        	String strData = getStringFromAssets(strFileNm);
        	
        	JSONBean bean = (JSONBean)FastJsonPaser.parse(strData, JSONBean.class);
        	List<Map<String, String>> arrayData = bean.getKqn() != null ? bean.getKqn():bean.getCt() != null?bean.getCt():bean.getCtt() != null? bean.getCtt():null;
        	
        	if (chartData == null) {
        		chartData = new ArrayList<OHLCVData>();
    		}else{
    			chartData.clear();
    		}
        	
        	for (Map<String, String> map : arrayData) {
        		OHLCVData data = new OHLCVData();
    			data.setOpen(map.get("o"));
    			data.setHigh(map.get("h"));
    			data.setLow(map.get("l"));
    			data.setClose(map.get("c"));
    			data.setVol(map.get("tr"));
    			data.setDate(map.get("qt"));
    			data.setCurrent(map.get("n"));
    			data.setPreclose(null);
    			data.setChange(map.get("changesPercent"));
    			
    			chartData.add(data);
    		}
        	
    		currentChartData = new GroupChartData(chartData, this);
    		
    		switch (dataType) {
			case DAY:
				mDayData = currentChartData;
				break;
			case WEEK:
				mWeekData = currentChartData;
				break;
			case MONTH:
				mMonthData = currentChartData;
				break;
			case ONE_MINUTE:
				m1MinuteData = currentChartData;
				break;
			case FIVE_MINUTE:
				m15MinuteData = currentChartData;
				break;
			case THIRTY_MINUTE:
				m30MinuteData = currentChartData;
				break;
			case ONE_HOUR:
				m1HourData = currentChartData;
				break;
			case TWO_HOUR:
				m2HourData = currentChartData;
				break;
			default:
				m4HourData = currentChartData;
				break;
			}
		}
    	
    	mDisplayDataType = dataType;
    	mCurrentData = currentChartData;
    	
    	mBollmaslipcandlestickchart.setStickData(currentChartData.getCandleStickData());
    	mBollmaslipcandlestickchart.setLinesData(currentChartData.getCandleStickLinesData());
        mBollmaslipcandlestickchart.setBandData(currentChartData.getCandleBandData());

        // 为chart1增加均线
        mVOLchart.setStickData(currentChartData.getVolData());
        
        // 设置stickData
        mMacdChart.setStickData(currentChartData.getMacdData());
        mKDJchart.setLinesData(currentChartData.getKdjData());
        mRSIchart.setLinesData(currentChartData.getRsiData());
        mWRchart.setLinesData(currentChartData.getWrData());
        mCCIchart.setLinesData(currentChartData.getCciData());
        mBOLLchart.setLinesData(currentChartData.getBollData());
        
        updateCharts();
    }
    
    /**
     * 加载JSON
     * @return
     */
    public void loadTickJSONData(){
    	String strFileNm = "time.txt";
    	String strData = getStringFromAssets(strFileNm);
    	
    	JSONBean bean = (JSONBean)FastJsonPaser.parse(strData, JSONBean.class);
    	List<Map<String, String>> arrayData = bean.getKqn() != null ? bean.getKqn():bean.getCt() != null?bean.getCt():bean.getCtt() != null? bean.getCtt():null;
    	
    	List<OHLCVData> timesData = new ArrayList<OHLCVData>();
    	
    	for (Map<String, String> map : arrayData) {
    		OHLCVData data = new OHLCVData();
			data.setOpen(map.get("o"));
			data.setHigh(map.get("h"));
			data.setLow(map.get("l"));
			data.setClose(map.get("c"));
			data.setVol(map.get("tr"));
			data.setDate(map.get("qt"));
			data.setCurrent(map.get("n"));
			data.setPreclose(null);
			data.setChange(map.get("changesPercent"));
			
			timesData.add(data);
		}
    	
    	mTickChart.setLinesData(initTickLines(timesData));
    }
    
    protected void updateCharts() {
    	mBollmaslipcandlestickchart.postInvalidate();
    	mVOLchart.postInvalidate();
    	mMacdChart.postInvalidate();
    	mKDJchart.postInvalidate();
    	mRSIchart.postInvalidate();
    	mWRchart.postInvalidate();
    	mCCIchart.postInvalidate();
    	mBOLLchart.postInvalidate();
	}
    
    protected List<LineEntity<DateValueEntity>> initTickLines(List<OHLCVData> arrayData) {
    	List<LineEntity<DateValueEntity>> lines = new ArrayList<LineEntity<DateValueEntity>>();
        // 计算5日均线
        LineEntity<DateValueEntity> MA5 = new LineEntity<DateValueEntity>();
        MA5.setTitle("HIGH");
        MA5.setLineColor(Color.WHITE);
        
        List<DateValueEntity> dateValues = new ArrayList<DateValueEntity>();
        
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-ddHH: mm: ss");
        SimpleDateFormat dt = new SimpleDateFormat("yyyyMMdd");
        for (OHLCVData data : arrayData) {
        	try {
				dateValues.add(new DateValueEntity(Float.parseFloat(data.getClose()), Integer.parseInt(dt.format(df.parse(data.getDate())))));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				continue;
			} catch (ParseException e) {
				e.printStackTrace();
				continue;
			}
		}
        
        MA5.setLineData(dateValues);
        lines.add(MA5);

        return lines;
	}
    
    /**
	 * 动态注册广播接收器
	 */
	private void registerReceiver() {
		if(mBroadcastReciver == null){
			mBroadcastReciver= new BroadcastReciver();
			IntentFilter intentFilter = new IntentFilter();
			intentFilter.addAction("cn.abel.action.broadcast");
			this.registerReceiver(mBroadcastReciver, intentFilter);
		}
	}
	
	private void updateChart(IndicatorType indicatorType, int[] indicators) {
		if (indicatorType == IndicatorType.IndicatorMACD) {
			mMACDS = indicators[0];
			mMACDL = indicators[1];
			mMACDM = indicators[2];
			
			if (mDayData != null) {
				mDayData.updateMACDData(mMACDL, mMACDM, mMACDS);
			}
			if (mWeekData != null) {
				mWeekData.updateMACDData(mMACDL, mMACDM, mMACDS);
			}
			if (mMonthData != null) {
				mMonthData.updateMACDData(mMACDL, mMACDM, mMACDS);
			}
			if (m1MinuteData != null) {
				m1MinuteData.updateMACDData(mMACDL, mMACDM, mMACDS);
			}
			if (m15MinuteData != null) {
				m15MinuteData.updateMACDData(mMACDL, mMACDM, mMACDS);
			}
			if (m30MinuteData != null) {
				m30MinuteData.updateMACDData(mMACDL, mMACDM, mMACDS);
			}
			if (m1HourData != null) {
				m1HourData.updateMACDData(mMACDL, mMACDM, mMACDS);
			}
			if (m2HourData != null) {
				m2HourData.updateMACDData(mMACDL, mMACDM, mMACDS);
			}
			if (m4HourData != null) {
				m4HourData.updateMACDData(mMACDL, mMACDM, mMACDS);
			}
			
			mMacdChart.setStickData(mCurrentData.getMacdData());
			mMacdChart.postInvalidate();
		}else if (indicatorType == IndicatorType.IndicatorMA) {
			mMA1 = indicators[0];
			mMA2 = indicators[1];
			mMA3 = indicators[2];
			
			if (mDayData != null) {
				mDayData.updateMAData(mMA1, mMA2, mMA3);
			}
			if (mWeekData != null) {
				mWeekData.updateMAData(mMA1, mMA2, mMA3);
			}
			if (mMonthData != null) {
				mMonthData.updateMAData(mMA1, mMA2, mMA3);
			}
			if (m1MinuteData != null) {
				m1MinuteData.updateMAData(mMA1, mMA2, mMA3);
			}
			if (m15MinuteData != null) {
				m15MinuteData.updateMAData(mMA1, mMA2, mMA3);
			}
			if (m30MinuteData != null) {
				m30MinuteData.updateMAData(mMA1, mMA2, mMA3);
			}
			if (m1HourData != null) {
				m1HourData.updateMAData(mMA1, mMA2, mMA3);
			}
			if (m2HourData != null) {
				m2HourData.updateMAData(mMA1, mMA2, mMA3);
			}
			if (m4HourData != null) {
				m4HourData.updateMAData(mMA1, mMA2, mMA3);
			}
			
			mBollmaslipcandlestickchart.setLinesData(mCurrentData.getCandleStickLinesData());
			mBollmaslipcandlestickchart.postInvalidate();
		}else if (indicatorType == IndicatorType.IndicatorKDJ) {
			mKDJN = indicators[0];
			
			if (mDayData != null) {
				mDayData.updateKDJData(mKDJN);
			}
			if (mWeekData != null) {
				mWeekData.updateKDJData(mKDJN);
			}
			if (mMonthData != null) {
				mMonthData.updateKDJData(mKDJN);
			}
			if (m1MinuteData != null) {
				m1MinuteData.updateKDJData(mKDJN);
			}
			if (m15MinuteData != null) {
				m15MinuteData.updateKDJData(mKDJN);
			}
			if (m30MinuteData != null) {
				m30MinuteData.updateKDJData(mKDJN);
			}
			if (m1HourData != null) {
				m1HourData.updateKDJData(mKDJN);
			}
			if (m2HourData != null) {
				m2HourData.updateKDJData(mKDJN);
			}
			if (m4HourData != null) {
				m4HourData.updateKDJData(mKDJN);
			}
			
			mKDJchart.setLinesData(mCurrentData.getKdjData());
			mKDJchart.postInvalidate();
		}else if (indicatorType == IndicatorType.IndicatorRSI) {
			mRSIN1 = indicators[0];
			mRSIN2 = indicators[1];
			
			if (mDayData != null) {
				mDayData.updateRSIData(mRSIN1, mRSIN2);
			}
			if (mWeekData != null) {
				mWeekData.updateRSIData(mRSIN1, mRSIN2);
			}
			if (mMonthData != null) {
				mMonthData.updateRSIData(mRSIN1, mRSIN2);
			}
			if (m1MinuteData != null) {
				m1MinuteData.updateRSIData(mRSIN1, mRSIN2);
			}
			if (m15MinuteData != null) {
				m15MinuteData.updateRSIData(mRSIN1, mRSIN2);
			}
			if (m30MinuteData != null) {
				m30MinuteData.updateRSIData(mRSIN1, mRSIN2);
			}
			if (m1HourData != null) {
				m1HourData.updateRSIData(mRSIN1, mRSIN2);
			}
			if (m2HourData != null) {
				m2HourData.updateRSIData(mRSIN1, mRSIN2);
			}
			if (m4HourData != null) {
				m4HourData.updateRSIData(mRSIN1, mRSIN2);
			}
			
			mRSIchart.setLinesData(mCurrentData.getRsiData());
			mRSIchart.postInvalidate();
		}else if (indicatorType == IndicatorType.IndicatorWR) {
			mWRN = indicators[0];
			
			if (mDayData != null) {
				mDayData.updateWRData(mWRN);
			}
			if (mWeekData != null) {
				mWeekData.updateWRData(mWRN);
			}
			if (mMonthData != null) {
				mMonthData.updateWRData(mWRN);
			}
			if (m1MinuteData != null) {
				m1MinuteData.updateWRData(mWRN);
			}
			if (m15MinuteData != null) {
				m15MinuteData.updateWRData(mWRN);
			}
			if (m30MinuteData != null) {
				m30MinuteData.updateWRData(mWRN);
			}
			if (m1HourData != null) {
				m1HourData.updateWRData(mWRN);
			}
			if (m2HourData != null) {
				m2HourData.updateWRData(mWRN);
			}
			if (m4HourData != null) {
				m4HourData.updateWRData(mWRN);
			}
			
			mWRchart.setLinesData(mCurrentData.getWrData());
			mWRchart.postInvalidate();
		}else if (indicatorType == IndicatorType.IndicatorCCI) {
			mCCIN = indicators[0];
			
			if (mDayData != null) {
				mDayData.updateCCIData(mCCIN);
			}
			if (mWeekData != null) {
				mWeekData.updateCCIData(mCCIN);
			}
			if (mMonthData != null) {
				mMonthData.updateCCIData(mCCIN);
			}
			if (m1MinuteData != null) {
				m1MinuteData.updateCCIData(mCCIN);
			}
			if (m15MinuteData != null) {
				m15MinuteData.updateCCIData(mCCIN);
			}
			if (m30MinuteData != null) {
				m30MinuteData.updateCCIData(mCCIN);
			}
			if (m1HourData != null) {
				m1HourData.updateCCIData(mCCIN);
			}
			if (m2HourData != null) {
				m2HourData.updateCCIData(mCCIN);
			}
			if (m4HourData != null) {
				m4HourData.updateCCIData(mCCIN);
			}
			
			mCCIchart.setLinesData(mCurrentData.getCciData());
			mCCIchart.postInvalidate();
		}else if (indicatorType == IndicatorType.IndicatorBOLL) {
			mBOLLN = indicators[0];
			
			if (mDayData != null) {
				mDayData.updateBandData(mBOLLN);
				mDayData.updateBOLLData(mBOLLN);
			}
			if (mWeekData != null) {
				mWeekData.updateBandData(mBOLLN);
				mWeekData.updateBOLLData(mBOLLN);
			}
			if (mMonthData != null) {
				mMonthData.updateBandData(mBOLLN);
				mMonthData.updateBOLLData(mBOLLN);
			}
			if (m1MinuteData != null) {
				m1MinuteData.updateBandData(mBOLLN);
				m1MinuteData.updateBOLLData(mBOLLN);
			}
			if (m15MinuteData != null) {
				m15MinuteData.updateBandData(mBOLLN);
				m15MinuteData.updateBOLLData(mBOLLN);
			}
			if (m30MinuteData != null) {
				m30MinuteData.updateBandData(mBOLLN);
				m30MinuteData.updateBOLLData(mBOLLN);
			}
			if (m1HourData != null) {
				m1HourData.updateBandData(mBOLLN);
				m1HourData.updateBOLLData(mBOLLN);
			}
			if (m2HourData != null) {
				m2HourData.updateBandData(mBOLLN);
				m2HourData.updateBOLLData(mBOLLN);
			}
			if (m4HourData != null) {
				m4HourData.updateBandData(mBOLLN);
				m4HourData.updateBOLLData(mBOLLN);
			}
			
			mBollmaslipcandlestickchart.setBandData(mCurrentData.getCandleBandData());
			mBollmaslipcandlestickchart.postInvalidate();
			
			mBOLLchart.setLinesData(mCurrentData.getBollData());
			mBOLLchart.postInvalidate();
		}
	}
	
    /*******************************************************************************
     *	Internal Class,Interface
     *******************************************************************************/

 	/**
 	 * 自定义Adapter类
 	 * @author zhourr
 	 *
 	 */
 	@SuppressWarnings("rawtypes")
	class LvAdapter extends BaseLvAdapter{
 		
 		@SuppressWarnings("unchecked")
		public LvAdapter(Context context, List<Map<String, String>> data, View viewLv){
 			super(context, data, viewLv);
 			res = R.layout.activity_sample_demo_list_item;
 		}
 		
 		@Override
 		public View getView(int position, View convertView, ViewGroup parent) {
 			ViewHolder viewHolder;
			if(convertView == null){
				convertView = inflater.inflate(res, parent, false);
				viewHolder = new ViewHolder(convertView);
				convertView.setTag(viewHolder);
			}else{
				viewHolder = (ViewHolder) convertView.getTag();
			}
			
			@SuppressWarnings("unchecked")
			Map<String, String> data = (Map<String, String>) dataList.get(position);
			
			viewHolder.tvTime.setText(data.get("time"));
			viewHolder.tvPrice.setText(data.get("price"));
			viewHolder.tvCount.setText(data.get("count"));
 			
 			return convertView;
 		}
 	}

 	/**
 	 * ViewHolder
 	 * @author zhourr_
 	 *
 	 */
 	class ViewHolder{
 		TextView tvTime;
 		TextView tvPrice;
 		TextView tvCount;
 		
 		public ViewHolder(View convertView){
 			tvTime = (TextView) convertView.findViewById(R.id.tv_time);
 			tvPrice = (TextView) convertView.findViewById(R.id.tv_price);
 			tvCount = (TextView) convertView.findViewById(R.id.tv_count);
 		}
 	}
 	
 	/**
	 * 接收后台广播
	 * 
	 * @author zhourr_
	 * 
	 */
	private class BroadcastReciver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (!action.equals("cn.abel.action.broadcast")) {
				return;
			}
			
			int[] indicators = intent.getIntArrayExtra("indicators");
			
			int indicatorType = intent.getIntExtra("indicatorType", 0);
			
			IndicatorType indicator = indicatorType == 0?IndicatorType.IndicatorMACD:indicatorType == 1?IndicatorType.IndicatorMA:indicatorType == 2?IndicatorType.IndicatorKDJ:indicatorType == 3?IndicatorType.IndicatorRSI:indicatorType == 4?IndicatorType.IndicatorWR:indicatorType == 5?IndicatorType.IndicatorCCI:IndicatorType.IndicatorBOLL;;
			
			updateChart(indicator, indicators);
		}
	}
}