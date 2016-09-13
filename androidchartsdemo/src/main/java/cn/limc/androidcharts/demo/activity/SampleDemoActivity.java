package cn.limc.androidcharts.demo.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.limc.androidcharts.common.CustomLines;
import cn.limc.androidcharts.demo.R;
import cn.limc.androidcharts.axis.Axis;
import cn.limc.androidcharts.common.ICrossLines;
import cn.limc.androidcharts.common.IDataCursor;
import cn.limc.androidcharts.entity.DateValueEntity;
import cn.limc.androidcharts.entity.IStickEntity;
import cn.limc.androidcharts.entity.LineEntity;
import cn.limc.androidcharts.entity.ListChartData;
import cn.limc.androidcharts.entity.MACDEntity;
import cn.limc.androidcharts.entity.ShaleData;
import cn.limc.androidcharts.event.IDisplayCursorListener;
import cn.limc.androidcharts.event.ITouchable;
import cn.limc.androidcharts.event.ITouchedIndexListener;
import cn.limc.androidcharts.event.ITouchedValueListener;
import cn.limc.androidcharts.view.BOLLMASlipCandleStickChart;
import cn.limc.androidcharts.view.MACDChart;
import cn.limc.androidcharts.view.MAColoredSlipStickChart;
import cn.limc.androidcharts.view.SlipLineChart;
import cn.limc.androidcharts.view.SlipShaleAreaChart;
import cn.limc.androidcharts.demo.common.BaseActivity;
import cn.limc.androidcharts.demo.common.EnumType.ChartDataType;
import cn.limc.androidcharts.demo.common.EnumType.ChartViewType;
import cn.limc.androidcharts.demo.common.EnumType.DisplayType;
import cn.limc.androidcharts.demo.common.EnumType.IndicatorType;
import cn.limc.androidcharts.demo.common.EnumType.ThemeModeType;
import cn.limc.androidcharts.demo.common.bean.GroupChartData;
import cn.limc.androidcharts.demo.common.bean.OHLCVData;
import cn.limc.androidcharts.demo.common.bean.ResponseData;
import cn.limc.androidcharts.demo.common.utils.DateTimeUtils;
import cn.limc.androidcharts.demo.common.utils.FastJsonPaser;
import cn.limc.androidcharts.demo.common.utils.PreferencesUtils;
import cn.limc.androidcharts.demo.common.utils.TAComputeUtils;

public class SampleDemoActivity extends BaseActivity {

	private static final int REFRESH_SECOND = 10;
	private static final String VALUE_FORMAT = "#.00";

	/**
	 * 商品数据
	 */
	public static final String PRODUCT_DATA = "PRODUCT_DATA";
	/**
	 * 盘口数据
	 */
	public static final String HANDICAP_DATA = "HANDICAP_DATA";
	/**
	 * 分时数据
	 */
	public static final String TICK_DATA = "TICK_DATA";
	/**
	 * 明细数据
	 */
	public static final String DETAIL_DATA = "DETAIL_DATA";

	/**
	 * 自动刷新
	 */
	private Handler mRefreshHandler = new Handler();
	private Runnable mRefreshRunnable;

	private BroadcastReciver mBroadcastReciver;
	boolean isRefresh;

	int layoutResID;

	int mMACDS;
	int mMACDL;
	int mMACDM;

	int mMA1;
	int mMA2;
	int mMA3;

	int mVMA1;
	int mVMA2;
	int mVMA3;

	int mKDJN;

	int mRSIN1;
	int mRSIN2;

	int mWRN;

	int mCCIN;

	int mBOLLN;

	/**
	 * 主题
	 */
	ThemeModeType mThemeMode;

	ChartDataType mDisplayDataType;
	GroupChartData mCurrentData;

	GroupChartData mDayData;
	GroupChartData mWeekData;
	GroupChartData mMonthData;

	DisplayType mDisplayType;
	ChartViewType mDisplayChart;

	RelativeLayout mRelBackground;
	LinearLayout mLinPdtBackground;

	TextView mTvCurrentPrice;
	TextView mTvCurrentPercent;
	TextView mTvNewestPercent;
	TextView mTvDateTime;
	TextView mTvBuyPrice;
	TextView mTvHigh;
	TextView mTvSellPrice;
	TextView mTvLow;

	TextView mTvTick;
	TextView mTvDay;
	TextView mTvWeek;
	TextView mTvMonth;

	ITouchedIndexListener mITouchedIndexListener;
	ITouchedValueListener mITouchedValueListener;

	LinearLayout mLinChartsContainer;
	BOLLMASlipCandleStickChart mBollmaslipcandlestickchart;
	TextView mTvFloatCandleStick;
	SlipShaleAreaChart mTickChart;

	LinearLayout mLinBottomChartContainer;
	MAColoredSlipStickChart mVOLchart;
	TextView mTvVOLFloat;
	MACDChart mMacdChart;
	TextView mTvMACDFloat;
	SlipLineChart mKDJchart;
	TextView mTvKDJFloat;
	SlipLineChart mRSIchart;
	TextView mTvRSIFloat;
	SlipLineChart mWRchart;
	TextView mTvWRFloat;
	SlipLineChart mCCIchart;
	TextView mTvCCIFloat;
	SlipLineChart mBOLLchart;
	TextView mTvBOLLFloat;

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
		// TODO OnCreate
		super.onCreate(savedInstanceState);

		if (layoutResID == 0) {
			setContentView(R.layout.activity_sample_demo);
		}

		initValues();
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

		initProductInfo();

		initTopViews();
		initBottomViews();

		mDisplayDataType = ChartDataType.DAY;

		registerReceiver();

		mRefreshHandler.postDelayed(mRefreshRunnable, (long) (1000 * 0.5));
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

	private void initValues() {
		mDisplayType = DisplayType.ALL;
		int themeMode = PreferencesUtils.getInt(this, PreferencesUtils.THEME_MODE);
		mThemeMode = themeMode == -1 ? ThemeModeType.THEME_DAY : themeMode == 0 ? ThemeModeType.THEME_DAY : ThemeModeType.THEME_NIGHT;

		mThemeMode = ThemeModeType.THEME_DAY;

		PreferencesUtils.putInt(this, PreferencesUtils.THEME_MODE, mThemeMode.ordinal());

		mRelBackground = (RelativeLayout) findViewById(R.id.rel_background);
		mLinPdtBackground = (LinearLayout) findViewById(R.id.lin_product_info);

		mBtnHorizontal = (Button) findViewById(R.id.btn_horizontal);
		mTvFloatCandleStick = (TextView) findViewById(R.id.tv_candlestick);
		mTvVOLFloat = (TextView) findViewById(R.id.tv_vol_float);
		mTvMACDFloat = (TextView) findViewById(R.id.tv_macd_float);
		mTvKDJFloat = (TextView) findViewById(R.id.tv_kdj_float);
		mTvRSIFloat = (TextView) findViewById(R.id.tv_rsi_float);
		mTvWRFloat = (TextView) findViewById(R.id.tv_wr_float);
		mTvCCIFloat = (TextView) findViewById(R.id.tv_cci_float);
		mTvBOLLFloat = (TextView) findViewById(R.id.tv_boll_float);

		mRefreshRunnable = new Runnable() {
			public void run() {
				this.update();
				// 间隔 REFRESH_SECOND 秒
				mRefreshHandler.postDelayed(this, 1000 * REFRESH_SECOND);
			}

			void update() {
				isRefresh = true;

				if (mDisplayType == DisplayType.ALL) {
					mDisplayType = DisplayType.KLINECHART;

					loadKLineData(ChartDataType.DAY);
				} else if (mDisplayType == DisplayType.TICKCHART) {
					loadTickData();
				} else if (mDisplayType == DisplayType.KLINECHART) {
					loadKLineData(ChartDataType.DAY);
				} else {
				}
			}
		};

		mITouchedIndexListener = new ITouchedIndexListener() {
			@Override
			public void onSelectedIndexChanged(ITouchable touchable, int index) {
				DecimalFormat df = new DecimalFormat(VALUE_FORMAT);
				if (touchable == mBollmaslipcandlestickchart) {
					SpannableStringBuilder strFloatCandleStick = new SpannableStringBuilder();

					OHLCVData ohlcvData = mCurrentData.getOhlcData().get(index);
					OHLCVData lastOhlcvData = mCurrentData.getOhlcData().get(index == 0 ? index : index - 1);

					strFloatCandleStick.append("开:");
					strFloatCandleStick.append(df.format(ohlcvData.getOpen()));
					strFloatCandleStick.append(" 收:");
					strFloatCandleStick.append(df.format(ohlcvData.getClose()));
					strFloatCandleStick.append(" 高:");
					strFloatCandleStick.append(df.format(ohlcvData.getHigh()));
					strFloatCandleStick.append(" 低:");
					strFloatCandleStick.append(df.format(ohlcvData.getLow()));

					String strOhlc = strFloatCandleStick.toString();
					// 设置标签文本颜色
					if (ohlcvData.getOpen() == 0) {
						strFloatCandleStick.setSpan(new ForegroundColorSpan(Color.LTGRAY), strOhlc.indexOf("开:") + 2, strOhlc.indexOf(" 收:"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
					} else {
						if (ohlcvData.getOpen() == lastOhlcvData.getOpen()) {
							strFloatCandleStick.setSpan(new ForegroundColorSpan(Color.LTGRAY), strOhlc.indexOf("开:") + 2, strOhlc.indexOf(" 收:"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
						} else if (ohlcvData.getOpen() > lastOhlcvData.getOpen()) {
							strFloatCandleStick.setSpan(new ForegroundColorSpan(Color.parseColor(TAComputeUtils.LINE_COLORS[0])), strOhlc.indexOf("开:") + 2, strOhlc.indexOf(" 收:"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
						} else if (ohlcvData.getOpen() < lastOhlcvData.getOpen()) {
							strFloatCandleStick.setSpan(new ForegroundColorSpan(Color.parseColor(TAComputeUtils.LINE_COLORS[1])), strOhlc.indexOf("开:") + 2, strOhlc.indexOf(" 收:"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
						}
					}

					if (ohlcvData.getClose() == 0) {
						strFloatCandleStick.setSpan(new ForegroundColorSpan(Color.LTGRAY), strOhlc.indexOf("收:") + 2, strOhlc.indexOf(" 高:"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
					} else {
						if (ohlcvData.getClose() == lastOhlcvData.getClose()) {
							strFloatCandleStick.setSpan(new ForegroundColorSpan(Color.LTGRAY), strOhlc.indexOf("收:") + 2, strOhlc.indexOf(" 高:"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
						} else if (ohlcvData.getClose() > lastOhlcvData.getClose()) {
							strFloatCandleStick.setSpan(new ForegroundColorSpan(Color.parseColor(TAComputeUtils.LINE_COLORS[0])), strOhlc.indexOf("收:") + 2, strOhlc.indexOf(" 高:"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
						} else if (ohlcvData.getClose() < lastOhlcvData.getClose()) {
							strFloatCandleStick.setSpan(new ForegroundColorSpan(Color.parseColor(TAComputeUtils.LINE_COLORS[1])), strOhlc.indexOf("收:") + 2, strOhlc.indexOf(" 高:"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
						}
					}

					if (ohlcvData.getHigh() == 0) {
						strFloatCandleStick.setSpan(new ForegroundColorSpan(Color.LTGRAY), strOhlc.indexOf("高:") + 2, strOhlc.indexOf(" 低:"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
					} else {
						if (ohlcvData.getHigh() == lastOhlcvData.getHigh()) {
							strFloatCandleStick.setSpan(new ForegroundColorSpan(Color.LTGRAY), strOhlc.indexOf("高:") + 2, strOhlc.indexOf(" 低:"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
						} else if (ohlcvData.getHigh() > lastOhlcvData.getHigh()) {
							strFloatCandleStick.setSpan(new ForegroundColorSpan(Color.parseColor(TAComputeUtils.LINE_COLORS[0])), strOhlc.indexOf("高:") + 2, strOhlc.indexOf(" 低:"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
						} else if (ohlcvData.getHigh() < lastOhlcvData.getHigh()) {
							strFloatCandleStick.setSpan(new ForegroundColorSpan(Color.parseColor(TAComputeUtils.LINE_COLORS[1])), strOhlc.indexOf("高:") + 2, strOhlc.indexOf(" 低:"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
						}
					}

					if (ohlcvData.getLow() == 0) {
						strFloatCandleStick.setSpan(new ForegroundColorSpan(Color.LTGRAY), strOhlc.indexOf("低:") + 2, strOhlc.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
					} else {
						if (ohlcvData.getLow() == lastOhlcvData.getLow()) {
							strFloatCandleStick.setSpan(new ForegroundColorSpan(Color.LTGRAY), strOhlc.indexOf("低:") + 2, strOhlc.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
						} else if (ohlcvData.getLow() > lastOhlcvData.getLow()) {
							strFloatCandleStick.setSpan(new ForegroundColorSpan(Color.parseColor(TAComputeUtils.LINE_COLORS[0])), strOhlc.indexOf("低:") + 2, strOhlc.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
						} else if (ohlcvData.getLow() < lastOhlcvData.getLow()) {
							strFloatCandleStick.setSpan(new ForegroundColorSpan(Color.parseColor(TAComputeUtils.LINE_COLORS[1])), strOhlc.indexOf("低:") + 2, strOhlc.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
						}
					}

					mTvFloatCandleStick.setText(strFloatCandleStick);
				} else if (touchable == mVOLchart) {
					OHLCVData ohlcvData = mCurrentData.getOhlcData().get(index);

					SpannableStringBuilder strVOLFloat = new SpannableStringBuilder();
					strVOLFloat.append("VOL:");
					strVOLFloat.append(df.format(ohlcvData.getVol()));

					String strVOL = strVOLFloat.toString();
					strVOLFloat.setSpan(new ForegroundColorSpan(Color.parseColor(TAComputeUtils.LINE_COLORS[0])), strVOL.indexOf("VOL:"), strVOL.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

					mTvVOLFloat.setText(strVOLFloat);
				} else if (touchable == mMacdChart) {
					int macdS = PreferencesUtils.getInt(SampleDemoActivity.this, PreferencesUtils.MACD_S);
					int macdL = PreferencesUtils.getInt(SampleDemoActivity.this, PreferencesUtils.MACD_L);
					int macdM = PreferencesUtils.getInt(SampleDemoActivity.this, PreferencesUtils.MACD_M);
					SpannableStringBuilder strMACDFloat = new SpannableStringBuilder();

					ListChartData<IStickEntity> macdData = mCurrentData.getMacdData();
					if (macdData.size() > 0) {
						strMACDFloat.append("MACD(");
						strMACDFloat.append(String.valueOf(macdS));
						strMACDFloat.append(",");
						strMACDFloat.append(String.valueOf(macdL));
						strMACDFloat.append(",");
						strMACDFloat.append(String.valueOf(macdM));
						strMACDFloat.append("): ");

						MACDEntity macd = (MACDEntity) macdData.get(index);

						strMACDFloat.append(df.format(macd.getMacd()));
						strMACDFloat.append(" DIF:");

						strMACDFloat.append(df.format(macd.getDiff()));

						strMACDFloat.append(" DEA:");
						strMACDFloat.append(df.format(macd.getDea()));
					} else {
						strMACDFloat.append("MACD(");
						strMACDFloat.append(String.valueOf(macdS));
						strMACDFloat.append(",");
						strMACDFloat.append(String.valueOf(macdL));
						strMACDFloat.append(",");
						strMACDFloat.append(String.valueOf(macdM));
						strMACDFloat.append("): ");
						strMACDFloat.append("0.00");
						strMACDFloat.append(" DIF:");

						strMACDFloat.append("0.00");

						strMACDFloat.append(" DEA:");
						strMACDFloat.append("0.00");
					}

					String strMacd = strMACDFloat.toString();

					strMACDFloat.setSpan(new ForegroundColorSpan(Color.parseColor(TAComputeUtils.LINE_COLORS[0])), strMacd.indexOf("): ") + 3, strMacd.indexOf(" DIF:"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
					strMACDFloat.setSpan(new ForegroundColorSpan(Color.parseColor(TAComputeUtils.LINE_COLORS[1])), strMacd.indexOf("DIF:"), strMacd.indexOf("DEA:"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
					strMACDFloat.setSpan(new ForegroundColorSpan(Color.parseColor(TAComputeUtils.LINE_COLORS[2])), strMacd.indexOf("DEA:"), strMacd.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

					mTvMACDFloat.setText(strMACDFloat);
				} else if (touchable == mKDJchart) {
					int kdjN = PreferencesUtils.getInt(SampleDemoActivity.this, PreferencesUtils.KDJ_N);
					SpannableStringBuilder strKDJFloat = new SpannableStringBuilder();
					strKDJFloat.append("KDJ(");
					strKDJFloat.append(String.valueOf(kdjN));
					strKDJFloat.append(",3,3): ");

					List<LineEntity<DateValueEntity>> kdjData = mCurrentData.getKdjData();
					if (kdjData.size() == 3) {
						strKDJFloat.append("K:");
						strKDJFloat.append(df.format(kdjData.get(0).getLineData().get(index).getValue()));

						strKDJFloat.append(" D:");
						strKDJFloat.append(df.format(kdjData.get(1).getLineData().get(index).getValue()));

						strKDJFloat.append(" J:");
						strKDJFloat.append(df.format(kdjData.get(2).getLineData().get(index).getValue()));
					} else {
						strKDJFloat.append("K:");
						strKDJFloat.append("0.00");

						strKDJFloat.append(" D:");
						strKDJFloat.append("0.00");

						strKDJFloat.append(" J:");
						strKDJFloat.append("0.00");
					}

					String strKDJ = strKDJFloat.toString();

					strKDJFloat.setSpan(new ForegroundColorSpan(Color.parseColor(TAComputeUtils.LINE_COLORS[0])), strKDJ.indexOf("K:"), strKDJ.indexOf(" D:"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
					strKDJFloat.setSpan(new ForegroundColorSpan(Color.parseColor(TAComputeUtils.LINE_COLORS[1])), strKDJ.indexOf(" D:"), strKDJ.indexOf(" J:"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
					strKDJFloat.setSpan(new ForegroundColorSpan(Color.parseColor(TAComputeUtils.LINE_COLORS[2])), strKDJ.indexOf(" J:"), strKDJ.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

					mTvKDJFloat.setText(strKDJFloat);
				} else if (touchable == mRSIchart) {
					int rsiN1 = PreferencesUtils.getInt(SampleDemoActivity.this, PreferencesUtils.RSI_N1);
					int rsiN2 = PreferencesUtils.getInt(SampleDemoActivity.this, PreferencesUtils.RSI_N2);
					SpannableStringBuilder strRSIFloat = new SpannableStringBuilder();

					List<LineEntity<DateValueEntity>> rsiData = mCurrentData.getRsiData();
					if (rsiData.size() == 3) {
						strRSIFloat.append("RSI");
						strRSIFloat.append(String.valueOf(rsiN1));
						strRSIFloat.append(": ");
						strRSIFloat.append(df.format(rsiData.get(0).getLineData().get(index).getValue()));

						strRSIFloat.append(" RSI");
						strRSIFloat.append(String.valueOf(rsiN2));
						strRSIFloat.append(": ");
						strRSIFloat.append(df.format(rsiData.get(1).getLineData().get(index).getValue()));

						strRSIFloat.append(" RSI");
						strRSIFloat.append("24");
						strRSIFloat.append(": ");
						strRSIFloat.append(df.format(rsiData.get(2).getLineData().get(index).getValue()));
					} else {
						strRSIFloat.append(" RSI");
						strRSIFloat.append(String.valueOf(rsiN1));
						strRSIFloat.append(": ");
						strRSIFloat.append("0.00");

						strRSIFloat.append(" RSI");
						strRSIFloat.append(String.valueOf(rsiN2));
						strRSIFloat.append(": ");
						strRSIFloat.append("0.00");

						strRSIFloat.append(" RSI");
						strRSIFloat.append("24");
						strRSIFloat.append(": ");
						strRSIFloat.append("0.00");
					}

					String strRSI = strRSIFloat.toString();

					strRSIFloat.setSpan(new ForegroundColorSpan(Color.parseColor(TAComputeUtils.LINE_COLORS[0])), 0, strRSI.indexOf("RSI" + String.valueOf(rsiN2)), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
					strRSIFloat.setSpan(new ForegroundColorSpan(Color.parseColor(TAComputeUtils.LINE_COLORS[1])), strRSI.indexOf("RSI" + String.valueOf(rsiN2)), strRSI.indexOf("RSI24:"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
					strRSIFloat.setSpan(new ForegroundColorSpan(Color.parseColor(TAComputeUtils.LINE_COLORS[2])), strRSI.indexOf("RSI24:"), strRSI.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

					mTvRSIFloat.setText(strRSIFloat);
				} else if (touchable == mWRchart) {
					int wrN = PreferencesUtils.getInt(SampleDemoActivity.this, PreferencesUtils.WR_N);
					SpannableStringBuilder strWRFloat = new SpannableStringBuilder();

					List<LineEntity<DateValueEntity>> wrData = mCurrentData.getWrData();
					if (wrData.size() == 1) {
						strWRFloat.append("WR(");
						strWRFloat.append(String.valueOf(wrN));
						strWRFloat.append("): ");
						strWRFloat.append(df.format(wrData.get(0).getLineData().get(index).getValue()));
					} else {
						strWRFloat.append("WR(");
						strWRFloat.append(String.valueOf(wrN));
						strWRFloat.append("): ");
						strWRFloat.append("0.00");
					}

					String strWR = strWRFloat.toString();

					strWRFloat.setSpan(new ForegroundColorSpan(Color.parseColor(TAComputeUtils.LINE_COLORS[0])), strWR.indexOf(":") + 1, strWR.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

					mTvWRFloat.setText(strWRFloat);
				} else if (touchable == mCCIchart) {
					int cciN = PreferencesUtils.getInt(SampleDemoActivity.this, PreferencesUtils.CCI_N);
					SpannableStringBuilder strCCIFloat = new SpannableStringBuilder();

					List<LineEntity<DateValueEntity>> cciData = mCurrentData.getCciData();
					if (cciData.size() == 1) {
						strCCIFloat.append("CCI(");
						strCCIFloat.append(String.valueOf(cciN));
						strCCIFloat.append("): ");
						strCCIFloat.append(df.format(cciData.get(0).getLineData().get(index).getValue()));
					} else {
						strCCIFloat.append("WR(");
						strCCIFloat.append(String.valueOf(cciN));
						strCCIFloat.append("): ");
						strCCIFloat.append("0.00");
					}

					String strCCI = strCCIFloat.toString();

					strCCIFloat.setSpan(new ForegroundColorSpan(Color.parseColor(TAComputeUtils.LINE_COLORS[0])), strCCI.indexOf(":") + 1, strCCI.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

					mTvCCIFloat.setText(strCCIFloat);
				} else if (touchable == mBOLLchart) {
					int bollN = PreferencesUtils.getInt(SampleDemoActivity.this, PreferencesUtils.BOLL_N);
					SpannableStringBuilder strBOLLFloat = new SpannableStringBuilder();

					List<LineEntity<DateValueEntity>> bollData = mCurrentData.getBollData();
					if (bollData.size() == 3) {
						strBOLLFloat.append("BOLL(");
						strBOLLFloat.append(String.valueOf(bollN));
						strBOLLFloat.append(",2,2): ");
						strBOLLFloat.append(df.format(bollData.get(0).getLineData().get(index).getValue()));

						strBOLLFloat.append(" ");
						strBOLLFloat.append(df.format(bollData.get(1).getLineData().get(index).getValue()));

						strBOLLFloat.append(" ");
						strBOLLFloat.append(df.format(bollData.get(2).getLineData().get(index).getValue()));
					} else {
						strBOLLFloat.append("BOLL(");
						strBOLLFloat.append(String.valueOf(bollN));
						strBOLLFloat.append(",2,2): ");
						strBOLLFloat.append("0.00");

						strBOLLFloat.append(" ");
						strBOLLFloat.append("0.00");

						strBOLLFloat.append(" ");
						strBOLLFloat.append("0.00");
					}

					String strBOLL = strBOLLFloat.toString();

					int index0Space = 0;
					int index1Space = 0;
					int index2Space = 0;
					for (int i = 0; i < strBOLL.toCharArray().length; i++) {
						char c = strBOLL.charAt(i);
						if (c == ' ') {
							if (index0Space == 0) {
								index0Space = i;
							} else if (index1Space == 0) {
								index1Space = i;
							} else if (index2Space == 0) {
								index2Space = i;
								break;
							}
						}
					}

					strBOLLFloat.setSpan(new ForegroundColorSpan(Color.parseColor(TAComputeUtils.LINE_COLORS[0])), index0Space, index1Space, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
					strBOLLFloat.setSpan(new ForegroundColorSpan(Color.parseColor(TAComputeUtils.LINE_COLORS[1])), index1Space, index2Space, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
					strBOLLFloat.setSpan(new ForegroundColorSpan(Color.parseColor(TAComputeUtils.LINE_COLORS[2])), index2Space, strBOLL.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

					mTvBOLLFloat.setText(strBOLLFloat);
				} else {

				}
			}
		};

		mITouchedValueListener = new ITouchedValueListener() {
			@Override
			public void onLastTouchedValueChanged(ITouchable touchable, double value) {
				Log.d("LastTouchedValue", "value = "+ value);
			}
		};
	}

	private void initWidgets() {
		if (mBtnHorizontal != null) {
			mBtnHorizontal.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setClass(SampleDemoActivity.this,
							SampleHorizontalDemoActivity.class);

					startActivity(intent);
				}
			});
		}

		mRelBackground.setBackgroundResource(mThemeMode == ThemeModeType.THEME_DAY ? R.color.content_background_day : R.color.content_background_night);
		mLinPdtBackground.setBackgroundResource(mThemeMode == ThemeModeType.THEME_DAY ? R.color.product_background_day : R.color.product_background_night);
	}

	public void initProductInfo() {
		mTvCurrentPrice = (TextView) findViewById(R.id.tv_current_price);
		mTvCurrentPercent = (TextView) findViewById(R.id.tv_current_percent);
		mTvNewestPercent = (TextView) findViewById(R.id.tv_newest_percent);
		mTvDateTime = (TextView) findViewById(R.id.tv_date_time);
		mTvBuyPrice = (TextView) findViewById(R.id.tv_buy_price);
		mTvHigh = (TextView) findViewById(R.id.tv_high);
		mTvSellPrice = (TextView) findViewById(R.id.tv_sell_price);
		mTvLow = (TextView) findViewById(R.id.tv_low);

		mTvCurrentPrice.setText("3500");
		mTvCurrentPercent.setText("+5");
		mTvNewestPercent.setText("0.3%");

		mTvDateTime.setText(DateTimeUtils.currrentDateFormat("MM/dd HH:mm:ss"));

		mTvBuyPrice.setText("3496");
		mTvHigh.setText("3510");
		mTvSellPrice.setText("3505");
		mTvLow.setText("3490");
	}

	public void initTopViews() {
		mTvTick = (TextView) findViewById(R.id.tv_tick);
		mTvDay = (TextView) findViewById(R.id.tv_day);
		mTvWeek = (TextView) findViewById(R.id.tv_week);
		mTvMonth = (TextView) findViewById(R.id.tv_month);

		changeTopButtonsColor(mTvDay);

		mTvTick.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mDisplayType = DisplayType.TICKCHART;

				changeTopButtonsColor((TextView) v);

				mTickChart.setVisibility(View.VISIBLE);
				mLinChartsContainer.setVisibility(View.INVISIBLE);
				mLinBottomChartContainer.setVisibility(View.INVISIBLE);
				mLinBottomButtons.setVisibility(View.INVISIBLE);

//				loadTickData();
			}
		});

		mTvDay.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mDisplayType = DisplayType.KLINECHART;

				changeTopButtonsColor((TextView) v);

				loadKLineData(ChartDataType.DAY);
				mTickChart.setVisibility(View.INVISIBLE);

				mLinChartsContainer.setVisibility(View.VISIBLE);
				mLinBottomChartContainer.setVisibility(View.VISIBLE);
				mLinBottomButtons.setVisibility(View.VISIBLE);
			}
		});

		mTvWeek.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mDisplayType = DisplayType.KLINECHART;

				changeTopButtonsColor((TextView) v);

				loadKLineData(ChartDataType.WEEK);
				mTickChart.setVisibility(View.INVISIBLE);

				mLinChartsContainer.setVisibility(View.VISIBLE);
				mLinBottomChartContainer.setVisibility(View.VISIBLE);
				mLinBottomButtons.setVisibility(View.VISIBLE);
			}
		});

		mTvMonth.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mDisplayType = DisplayType.KLINECHART;

				changeTopButtonsColor((TextView) v);

				loadKLineData(ChartDataType.MONTH);
				mTickChart.setVisibility(View.INVISIBLE);

				mLinChartsContainer.setVisibility(View.VISIBLE);
				mLinBottomChartContainer.setVisibility(View.VISIBLE);
				mLinBottomButtons.setVisibility(View.VISIBLE);
			}
		});
	}

	private void initBottomViews() {
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

	public void changeTopButtonsColor(TextView tvSelected) {
		int resIDNomalColor = mThemeMode == ThemeModeType.THEME_DAY ? getResources().getColor(R.color.button_normal_text_day) : getResources().getColor(R.color.button_normal_text_night);
		int resIDSelectedColor = mThemeMode == ThemeModeType.THEME_DAY ? getResources().getColor(R.color.button_selected_text_day) : getResources().getColor(R.color.button_selected_text_night);

		mTvTick.setTextColor(resIDNomalColor);
		mTvDay.setTextColor(resIDNomalColor);
		mTvWeek.setTextColor(resIDNomalColor);
		mTvMonth.setTextColor(resIDNomalColor);

		tvSelected.setTextColor(resIDSelectedColor);
	}

	private void hideBottomChart() {
		int resIDNomalColor = mThemeMode == ThemeModeType.THEME_DAY ? getResources().getColor(R.color.button_normal_text_day) : getResources().getColor(R.color.button_normal_text_night);
		int resIDSelectedColor = mThemeMode == ThemeModeType.THEME_DAY ? getResources().getColor(R.color.button_selected_text_day) : getResources().getColor(R.color.button_selected_text_night);

		if (mDisplayChart == ChartViewType.VOL) {
			mLinBottomChartContainer.scrollTo(0, 0);
			mTvVOL.setTextColor(resIDSelectedColor);
		} else {
			mTvVOL.setTextColor(resIDNomalColor);
		}
		if (mDisplayChart == ChartViewType.MACD) {
			mLinBottomChartContainer.scrollTo(screenWidth, 0);
			mTvMACD.setTextColor(resIDSelectedColor);
		} else {
			mTvMACD.setTextColor(resIDNomalColor);
		}
		if (mDisplayChart == ChartViewType.KDJ) {
			mLinBottomChartContainer.scrollTo(screenWidth * 2, 0);
			mTvKDJ.setTextColor(resIDSelectedColor);
		} else {
			mTvKDJ.setTextColor(resIDNomalColor);
		}
		if (mDisplayChart == ChartViewType.RSI) {
			mLinBottomChartContainer.scrollTo(screenWidth * 3, 0);
			mTvRSI.setTextColor(resIDSelectedColor);
		} else {
			mTvRSI.setTextColor(resIDNomalColor);
		}
		if (mDisplayChart == ChartViewType.WR) {
			mLinBottomChartContainer.scrollTo(screenWidth * 4, 0);
			mTvWR.setTextColor(resIDSelectedColor);
		} else {
			mTvWR.setTextColor(resIDNomalColor);
		}
		if (mDisplayChart == ChartViewType.CCI) {
			mLinBottomChartContainer.scrollTo(screenWidth * 5, 0);
			mTvCCI.setTextColor(resIDSelectedColor);
		} else {
			mTvCCI.setTextColor(resIDNomalColor);
		}
		if (mDisplayChart == ChartViewType.BOLL) {
			mLinBottomChartContainer.scrollTo(screenWidth * 6, 0);
			mTvBOLL.setTextColor(resIDSelectedColor);
		} else {
			mTvBOLL.setTextColor(resIDNomalColor);
		}
	}

	private void initBOLLMASlipCandleStickChart() {
		this.mLinChartsContainer = (LinearLayout) findViewById(R.id.lin_charts_container);
		this.mBollmaslipcandlestickchart = (BOLLMASlipCandleStickChart) findViewById(R.id.bollmaslipcandlestickchart);

		mBollmaslipcandlestickchart.setBackgroundResource(mThemeMode == ThemeModeType.THEME_DAY ? R.color.chart_background_day : R.color.chart_background_night);
		mLinChartsContainer.setBackgroundResource(mThemeMode == ThemeModeType.THEME_DAY ? R.color.chart_background_day : R.color.chart_background_night);

		mBollmaslipcandlestickchart.setAxisXColor(Color.LTGRAY);
		mBollmaslipcandlestickchart.setAxisYColor(Color.LTGRAY);
		mBollmaslipcandlestickchart.setLatitudeColor(Color.GRAY);
		mBollmaslipcandlestickchart.setLongitudeColor(Color.GRAY);
		mBollmaslipcandlestickchart.setBorderColor(Color.LTGRAY);
		mBollmaslipcandlestickchart.setLongitudeFontColor(Color.LTGRAY);
		mBollmaslipcandlestickchart.setLatitudeFontColor(Color.LTGRAY);

		mBollmaslipcandlestickchart.setCrossLinesColor(Color.LTGRAY);

		// 最大纬线数
		mBollmaslipcandlestickchart.setLatitudeNum(3);
//        // 最大经线数
//        mBollmaslipcandlestickchart.setLongitudeNum(3);
		// 最大价格
		mBollmaslipcandlestickchart.setMaxValue(1200);
		// 最小价格
		mBollmaslipcandlestickchart.setMinValue(200);

		mBollmaslipcandlestickchart.setDisplayFrom(10);

		mBollmaslipcandlestickchart.setDisplayNumber(30);

		mBollmaslipcandlestickchart.setMinDisplayNumber(10);

//        mBollmaslipcandlestickchart
//                .setZoomBaseLine(IZoomable.ZOOM_BASE_LINE_CENTER);

		mBollmaslipcandlestickchart.setDisplayLongitudeTitle(true);
		mBollmaslipcandlestickchart.setDisplayLatitudeTitle(true);
		mBollmaslipcandlestickchart.setDisplayLatitude(true);
		mBollmaslipcandlestickchart.setDisplayLongitude(true);

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
		mBollmaslipcandlestickchart.setNoneDisplayValue(new float[]{Float.MAX_VALUE});

		mBollmaslipcandlestickchart.setTouchedIndexListener(mITouchedIndexListener);
	}

	final IDisplayCursorListener displayCursorListener = new IDisplayCursorListener() {
		@Override
		public void onCursorChanged(IDataCursor cursor, int displayFrom, int displayNumber) {

			if (cursor == mBollmaslipcandlestickchart) {

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
			} else {
				mBollmaslipcandlestickchart.setDisplayFrom(displayFrom);
				mBollmaslipcandlestickchart.setDisplayNumber(displayNumber);
				mBollmaslipcandlestickchart.postInvalidate();
			}
		}
	};

	private void initTickChart() {
		this.mTickChart = (SlipShaleAreaChart) findViewById(R.id.timeschart);

		mTickChart.setBackgroundResource(mThemeMode == ThemeModeType.THEME_DAY ? R.color.chart_background_day : R.color.chart_background_night);

		mTickChart.setAxisXColor(Color.LTGRAY);
		mTickChart.setAxisYColor(Color.LTGRAY);
		mTickChart.setBorderColor(Color.LTGRAY);
		mTickChart.setLongitudeFontSize(14);
		mTickChart.setLongitudeFontColor(Color.LTGRAY);
		mTickChart.setLatitudeColor(Color.GRAY);
		mTickChart.setLatitudeFontColor(Color.LTGRAY);
		mTickChart.setLongitudeColor(Color.GRAY);
		mTickChart.setDisplayFrom(0);
		mTickChart.setDisplayNumber(24 * 60);
		mTickChart.setMinDisplayNumber(24 * 60);
		mTickChart.setDisplayLongitudeTitle(true);
		mTickChart.setDisplayLatitudeTitle(true);
		mTickChart.setDisplayLatitude(true);
		mTickChart.setDisplayLongitude(true);
		mTickChart.setDataQuadrantPaddingTop(5);
		mTickChart.setDataQuadrantPaddingBottom(5);
		mTickChart.setDataQuadrantPaddingLeft(5);
		mTickChart.setDataQuadrantPaddingRight(5);
		mTickChart.setAxisXPosition(Axis.AXIS_X_POSITION_BOTTOM);
		mTickChart.setAxisYPosition(Axis.AXIS_Y_POSITION_RIGHT);
		mTickChart.setNoneDisplayValue(new float[]{0.0f});

		mTickChart.setBindCrossLinesToStick(ICrossLines.BIND_TO_TYPE_BOTH);
		mTickChart.setTouchedValueListener(mITouchedValueListener);


//        CustomLines.CustomLine lastCloseLine1 = new CustomLines.CustomLine();
//        lastCloseLine1.setLineColor(Color.BLUE);
//        lastCloseLine1.setLabelBackgroudColor(Color.BLUE);
//        lastCloseLine1.setLineValue(4300);
//        lastCloseLine1.setLabelPosition(CustomLines.CustomLine.LABEL_POSITION_BOTH);
//
//        CustomLines.CustomLine lastCloseLine2 = new CustomLines.CustomLine();
//        lastCloseLine2.setLineColor(Color.RED);
//        lastCloseLine2.setLabelBackgroudColor(Color.RED);
//        lastCloseLine2.setLineValue(4290);
//        lastCloseLine2.setLabelPosition(CustomLines.CustomLine.LABEL_POSITION_LEFT);
//
//        CustomLines.CustomLine lastCloseLine3 = new CustomLines.CustomLine();
//        lastCloseLine3.setLineColor(Color.YELLOW);
//        lastCloseLine3.setLabelBackgroudColor(Color.YELLOW);
//        lastCloseLine3.setLineValue(4280);
//        lastCloseLine3.setLabelPosition(CustomLines.CustomLine.LABEL_POSITION_RIGHT);
//
//        CustomLines.CustomLine lastCloseLine4 = new CustomLines.CustomLine();
//        lastCloseLine4.setLineColor(Color.MAGENTA);
//        lastCloseLine4.setLabelBackgroudColor(Color.MAGENTA);
//        lastCloseLine4.setLineValue(4270);
//        lastCloseLine4.setLabelPosition(CustomLines.CustomLine.LABEL_POSITION_NONE);
//
//        mTickChart.getCustomLines().getHorizontalLines().add(lastCloseLine1);
//        mTickChart.getCustomLines().getHorizontalLines().add(lastCloseLine2);
//        mTickChart.getCustomLines().getHorizontalLines().add(lastCloseLine3);
//        mTickChart.getCustomLines().getHorizontalLines().add(lastCloseLine4);

        initTickChartLines();
	}

	private void initTickChartLines() {
		DecimalFormat df = new DecimalFormat("00");

		List<LineEntity<DateValueEntity>> lines = new ArrayList<LineEntity<DateValueEntity>>();

		List<DateValueEntity> arrMinsLineDatas = new ArrayList<DateValueEntity>();

		for (int i = 0; i <= 60 * 24; i++) {
			int hour = i / 60;
			int min = i - hour * 60;

			String time = "2016" + df.format(hour) + df.format(min);
			arrMinsLineDatas.add(new DateValueEntity(0.0f, Integer.parseInt(time)));
		}

		LineEntity<DateValueEntity> line = new LineEntity<DateValueEntity>();
		line.setTitle("line");
		line.setLineColor(Color.BLUE);
		line.setLineData(arrMinsLineDatas);

		lines.add(line);
		mTickChart.setLinesData(lines);
	}

	private void initVOLChart() {
		this.mVOLchart = (MAColoredSlipStickChart) findViewById(R.id.slipstickchart);

		mVOLchart.setAxisXColor(Color.LTGRAY);
		mVOLchart.setAxisYColor(Color.LTGRAY);
		mVOLchart.setLatitudeColor(Color.GRAY);
		mVOLchart.setLongitudeColor(Color.GRAY);
		mVOLchart.setBorderColor(Color.LTGRAY);
		mVOLchart.setLongitudeFontColor(Color.LTGRAY);
		mVOLchart.setLatitudeFontColor(Color.LTGRAY);
		mVOLchart.setDataQuadrantPaddingTop(6);
		mVOLchart.setDataQuadrantPaddingBottom(1);
		mVOLchart.setDataQuadrantPaddingLeft(1);
		mVOLchart.setDataQuadrantPaddingRight(1);
		// mVOLchart.setAxisYTitleQuadrantWidth(50);
		// mVOLchart.setAxisXTitleQuadrantHeight(20);
		mVOLchart.setAxisXPosition(Axis.AXIS_X_POSITION_BOTTOM);
		mVOLchart.setAxisYPosition(Axis.AXIS_Y_POSITION_RIGHT);
//        // 最大纬线数
//        mVOLchart.setLatitudeNum(3);
//        // 最大经线数
//        mVOLchart.setLongitudeNum(3);
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

//        mVOLchart.setDataMultiple(100);
//        mVOLchart.setAxisYDecimalFormat("#,##0");
		mVOLchart.setAxisXDateTargetFormat("yyyy/MM/dd");
		mVOLchart.setAxisXDateSourceFormat("yyyyMMdd");
		mVOLchart.setOnDisplayCursorListener(displayCursorListener);
		mVOLchart.setTouchedIndexListener(mITouchedIndexListener);
	}

	@SuppressWarnings("static-access")
	private void initMACDChart() {
		mMacdChart = (MACDChart) findViewById(R.id.macdchart);
		mMacdChart.setMaxValue(300000);
		mMacdChart.setMinValue(-300000);
		// mMacdChart.setDisplayCrossXOnTouch(true);
		// mMacdChart.setDisplayCrossYOnTouch(true);
//        mMacdChart.setLatitudeNum(4);
//        mMacdChart.setLongitudeNum(3);
//        mMacdChart.setDisplayFrom(0);
//        mMacdChart.setDisplayNumber(10);
//        mMacdChart.setMinDisplayNumber(10);
//        mMacdChart.setZoomBaseLine(IZoomable.ZOOM_BASE_LINE_CENTER);
		mMacdChart.setAxisXColor(Color.LTGRAY);
		mMacdChart.setAxisYColor(Color.LTGRAY);
		mMacdChart.setLatitudeColor(Color.GRAY);
		mMacdChart.setLongitudeColor(Color.GRAY);
		mMacdChart.setBorderColor(Color.LTGRAY);
		mMacdChart.setLongitudeFontColor(Color.LTGRAY);
		mMacdChart.setLatitudeFontColor(Color.LTGRAY);
		mMacdChart.setMacdDisplayType(mMacdChart.MACD_DISPLAY_TYPE_STICK);
//        mMacdChart.setPositiveStickColor(Color.RED);
//        mMacdChart.setNegativeStickColor(Color.CYAN);
		mMacdChart.setMacdLineColor(Color.parseColor(TAComputeUtils.LINE_COLORS[0]));
		mMacdChart.setDeaLineColor(Color.parseColor(TAComputeUtils.LINE_COLORS[1]));
		mMacdChart.setDiffLineColor(Color.parseColor(TAComputeUtils.LINE_COLORS[2]));
		mMacdChart.setPositiveStickFillColor(Color.parseColor(TAComputeUtils.LINE_COLORS[0]));
		mMacdChart.setPositiveStickStrokeColor(Color.parseColor(TAComputeUtils.LINE_COLORS[0]));
		mMacdChart.setNegativeStickFillColor(Color.parseColor(TAComputeUtils.LINE_COLORS[1]));
		mMacdChart.setNegativeStickStrokeColor(Color.parseColor(TAComputeUtils.LINE_COLORS[1]));
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
		mMacdChart.setTouchedIndexListener(mITouchedIndexListener);
	}

	private void initKDJChart() {
		mKDJchart = (SlipLineChart) findViewById(R.id.kdjchart);

		mKDJchart.setAxisXColor(Color.LTGRAY);
		mKDJchart.setAxisYColor(Color.LTGRAY);
		mKDJchart.setBorderColor(Color.LTGRAY);
		mKDJchart.setLongitudeFontSize(14);
		mKDJchart.setLongitudeFontColor(Color.LTGRAY);
		mKDJchart.setLatitudeColor(Color.GRAY);
		mKDJchart.setLatitudeFontColor(Color.LTGRAY);
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
		mKDJchart.setTouchedIndexListener(mITouchedIndexListener);
	}

	private void initRSIChart() {
		mRSIchart = (SlipLineChart) findViewById(R.id.rsichart);

		mRSIchart.setAxisXColor(Color.LTGRAY);
		mRSIchart.setAxisYColor(Color.LTGRAY);
		mRSIchart.setBorderColor(Color.LTGRAY);
		mRSIchart.setLongitudeFontSize(14);
		mRSIchart.setLongitudeFontColor(Color.LTGRAY);
		mRSIchart.setLatitudeColor(Color.GRAY);
		mRSIchart.setLatitudeFontColor(Color.LTGRAY);
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
		mRSIchart.setTouchedIndexListener(mITouchedIndexListener);
	}

	private void initWRChart() {
		mWRchart = (SlipLineChart) findViewById(R.id.wrchart);

		mWRchart.setAxisXColor(Color.LTGRAY);
		mWRchart.setAxisYColor(Color.LTGRAY);
		mWRchart.setBorderColor(Color.LTGRAY);
		mWRchart.setLongitudeFontSize(14);
		mWRchart.setLongitudeFontColor(Color.LTGRAY);
		mWRchart.setLatitudeColor(Color.GRAY);
		mWRchart.setLatitudeFontColor(Color.LTGRAY);
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
		mWRchart.setTouchedIndexListener(mITouchedIndexListener);
	}

	private void initCCIChart() {
		mCCIchart = (SlipLineChart) findViewById(R.id.ccichart);

		mCCIchart.setAxisXColor(Color.LTGRAY);
		mCCIchart.setAxisYColor(Color.LTGRAY);
		mCCIchart.setBorderColor(Color.LTGRAY);
		mCCIchart.setLongitudeFontSize(14);
		mCCIchart.setLongitudeFontColor(Color.LTGRAY);
		mCCIchart.setLatitudeColor(Color.GRAY);
		mCCIchart.setLatitudeFontColor(Color.LTGRAY);
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
		mCCIchart.setTouchedIndexListener(mITouchedIndexListener);
	}

	private void initBOLLChart() {
		mBOLLchart = (SlipLineChart) findViewById(R.id.bollchart);

		mBOLLchart.setAxisXColor(Color.LTGRAY);
		mBOLLchart.setAxisYColor(Color.LTGRAY);
		mBOLLchart.setBorderColor(Color.LTGRAY);
		mBOLLchart.setLongitudeFontSize(14);
		mBOLLchart.setLongitudeFontColor(Color.LTGRAY);
		mBOLLchart.setLatitudeColor(Color.GRAY);
		mBOLLchart.setLatitudeFontColor(Color.LTGRAY);
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
		mBOLLchart.setTouchedIndexListener(mITouchedIndexListener);
	}

	/**
	 * 请求K线数据
	 *
	 * @return
	 */
	public void loadKLineData(ChartDataType dataType) {
		GroupChartData currentChartData = dataType == ChartDataType.DAY ? mDayData :
				dataType == ChartDataType.WEEK ? mWeekData : mMonthData;

		if (currentChartData == null || isRefresh) {
			System.out.println("...refresh...");

			isRefresh = false;

			String strFileNm = "KLineDayData.txt";

			String strData = getStringFromAssets(strFileNm);
			// TODO 请求K线数据

			ResponseData responseData = (ResponseData) FastJsonPaser.parse(strData, ResponseData.class);
			List<Map<String, String>> arrayData = responseData.getKline();

			List<OHLCVData> ohlcvDatas = new ArrayList<OHLCVData>();

			for (Map<String, String> map : arrayData) {
				OHLCVData data = new OHLCVData();

				data.setOpen(Float.parseFloat(map.get("open")));
				data.setHigh(Float.parseFloat(map.get("high")));
				data.setLow(Float.parseFloat(map.get("low")));
				data.setClose(Float.parseFloat(map.get("close")));
				data.setVol(Float.parseFloat(map.get("volume")));
				data.setDate(map.get("day"));
				data.setCurrent(Float.parseFloat(map.get("close")));
				data.setPreclose(0.0f);
				data.setChange(0.0f);

				ohlcvDatas.add(data);
			}

			currentChartData = new GroupChartData(ohlcvDatas, this);

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
				default:
					break;
			}
		}

		mDisplayDataType = dataType;
		mCurrentData = currentChartData;

		mBollmaslipcandlestickchart.setStickData(currentChartData.getCandleStickData());
		mBollmaslipcandlestickchart.setLinesData(currentChartData.getCandleStickLinesData());
		mBollmaslipcandlestickchart.setBandData(currentChartData.getCandleBandData());

		DecimalFormat df = new DecimalFormat(VALUE_FORMAT);

		if (currentChartData.getOhlcData() != null && currentChartData.getOhlcData().size() > 0) {
			SpannableStringBuilder strFloatCandleStick = new SpannableStringBuilder();

			int index = currentChartData.getOhlcData().size() - 1;

			OHLCVData ohlcvData = currentChartData.getOhlcData().get(index);
			OHLCVData lastOhlcvData = currentChartData.getOhlcData().get(index == 0 ? index : index - 1);

			strFloatCandleStick.append("开:");
			strFloatCandleStick.append(df.format(ohlcvData.getOpen()));
			strFloatCandleStick.append(" 收:");
			strFloatCandleStick.append(df.format(ohlcvData.getClose()));
			strFloatCandleStick.append(" 高:");
			strFloatCandleStick.append(df.format(ohlcvData.getHigh()));
			strFloatCandleStick.append(" 低:");
			strFloatCandleStick.append(df.format(ohlcvData.getLow()));

			String strOhlc = strFloatCandleStick.toString();
			// 设置标签文本颜色
			if (ohlcvData.getOpen() == 0) {
				strFloatCandleStick.setSpan(new ForegroundColorSpan(Color.LTGRAY), strOhlc.indexOf("开:") + 2, strOhlc.indexOf(" 收:"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			} else {
				if (ohlcvData.getOpen() == lastOhlcvData.getOpen()) {
					strFloatCandleStick.setSpan(new ForegroundColorSpan(Color.LTGRAY), strOhlc.indexOf("开:") + 2, strOhlc.indexOf(" 收:"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				} else if (ohlcvData.getOpen() > lastOhlcvData.getOpen()) {
					strFloatCandleStick.setSpan(new ForegroundColorSpan(Color.parseColor(TAComputeUtils.LINE_COLORS[0])), strOhlc.indexOf("开:") + 2, strOhlc.indexOf(" 收:"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				} else if (ohlcvData.getOpen() < lastOhlcvData.getOpen()) {
					strFloatCandleStick.setSpan(new ForegroundColorSpan(Color.parseColor(TAComputeUtils.LINE_COLORS[1])), strOhlc.indexOf("开:") + 2, strOhlc.indexOf(" 收:"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				}
			}

			if (ohlcvData.getClose() == 0) {
				strFloatCandleStick.setSpan(new ForegroundColorSpan(Color.LTGRAY), strOhlc.indexOf("收:") + 2, strOhlc.indexOf(" 高:"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			} else {
				if (ohlcvData.getClose() == lastOhlcvData.getClose()) {
					strFloatCandleStick.setSpan(new ForegroundColorSpan(Color.LTGRAY), strOhlc.indexOf("收:") + 2, strOhlc.indexOf(" 高:"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				} else if (ohlcvData.getClose() > lastOhlcvData.getClose()) {
					strFloatCandleStick.setSpan(new ForegroundColorSpan(Color.parseColor(TAComputeUtils.LINE_COLORS[0])), strOhlc.indexOf("收:") + 2, strOhlc.indexOf(" 高:"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				} else if (ohlcvData.getClose() < lastOhlcvData.getClose()) {
					strFloatCandleStick.setSpan(new ForegroundColorSpan(Color.parseColor(TAComputeUtils.LINE_COLORS[1])), strOhlc.indexOf("收:") + 2, strOhlc.indexOf(" 高:"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				}
			}

			if (ohlcvData.getHigh() == 0) {
				strFloatCandleStick.setSpan(new ForegroundColorSpan(Color.LTGRAY), strOhlc.indexOf("高:") + 2, strOhlc.indexOf(" 低:"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			} else {
				if (ohlcvData.getHigh() == lastOhlcvData.getHigh()) {
					strFloatCandleStick.setSpan(new ForegroundColorSpan(Color.LTGRAY), strOhlc.indexOf("高:") + 2, strOhlc.indexOf(" 低:"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				} else if (ohlcvData.getHigh() > lastOhlcvData.getHigh()) {
					strFloatCandleStick.setSpan(new ForegroundColorSpan(Color.parseColor(TAComputeUtils.LINE_COLORS[0])), strOhlc.indexOf("高:") + 2, strOhlc.indexOf(" 低:"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				} else if (ohlcvData.getHigh() < lastOhlcvData.getHigh()) {
					strFloatCandleStick.setSpan(new ForegroundColorSpan(Color.parseColor(TAComputeUtils.LINE_COLORS[1])), strOhlc.indexOf("高:") + 2, strOhlc.indexOf(" 低:"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				}
			}

			if (ohlcvData.getLow() == 0) {
				strFloatCandleStick.setSpan(new ForegroundColorSpan(Color.LTGRAY), strOhlc.indexOf("低:") + 2, strOhlc.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			} else {
				if (ohlcvData.getLow() == lastOhlcvData.getLow()) {
					strFloatCandleStick.setSpan(new ForegroundColorSpan(Color.LTGRAY), strOhlc.indexOf("低:") + 2, strOhlc.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				} else if (ohlcvData.getLow() > lastOhlcvData.getLow()) {
					strFloatCandleStick.setSpan(new ForegroundColorSpan(Color.parseColor(TAComputeUtils.LINE_COLORS[0])), strOhlc.indexOf("低:") + 2, strOhlc.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				} else if (ohlcvData.getLow() < lastOhlcvData.getLow()) {
					strFloatCandleStick.setSpan(new ForegroundColorSpan(Color.parseColor(TAComputeUtils.LINE_COLORS[1])), strOhlc.indexOf("低:") + 2, strOhlc.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				}
			}

			mTvFloatCandleStick.setText(strFloatCandleStick);

			SpannableStringBuilder strVOLFloat = new SpannableStringBuilder();
			strVOLFloat.append("VOL: ");
			strVOLFloat.append(df.format(ohlcvData.getVol()));

			String strVOL = strVOLFloat.toString();
			strVOLFloat.setSpan(new ForegroundColorSpan(Color.parseColor(TAComputeUtils.LINE_COLORS[0])), strVOL.indexOf("VOL:") + 5, strVOL.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

			mTvVOLFloat.setText(strVOLFloat);

			int macdS = PreferencesUtils.getInt(this, PreferencesUtils.MACD_S);
			int macdL = PreferencesUtils.getInt(this, PreferencesUtils.MACD_L);
			int macdM = PreferencesUtils.getInt(this, PreferencesUtils.MACD_M);
			SpannableStringBuilder strMACDFloat = new SpannableStringBuilder();

			ListChartData<IStickEntity> macdData = currentChartData.getMacdData();
			if (macdData.size() > 0) {
				strMACDFloat.append("MACD(");
				strMACDFloat.append(String.valueOf(macdS));
				strMACDFloat.append(",");
				strMACDFloat.append(String.valueOf(macdL));
				strMACDFloat.append(",");
				strMACDFloat.append(String.valueOf(macdM));
				strMACDFloat.append("): ");

				MACDEntity macd = (MACDEntity) macdData.get(macdData.size() - 1);

				strMACDFloat.append(df.format(macd.getMacd()));
				strMACDFloat.append(" DIF:");

				strMACDFloat.append(df.format(macd.getDiff()));

				strMACDFloat.append(" DEA:");
				strMACDFloat.append(df.format(macd.getDea()));
			} else {
				strMACDFloat.append("MACD(");
				strMACDFloat.append(String.valueOf(macdS));
				strMACDFloat.append(",");
				strMACDFloat.append(String.valueOf(macdL));
				strMACDFloat.append(",");
				strMACDFloat.append(String.valueOf(macdM));
				strMACDFloat.append("): ");
				strMACDFloat.append("0.00");
				strMACDFloat.append(" DIF:");

				strMACDFloat.append("0.00");

				strMACDFloat.append(" DEA:");
				strMACDFloat.append("0.00");
			}

			String strMacd = strMACDFloat.toString();

			strMACDFloat.setSpan(new ForegroundColorSpan(Color.parseColor(TAComputeUtils.LINE_COLORS[0])), strMacd.indexOf("): ") + 3, strMacd.indexOf(" DIF:"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			strMACDFloat.setSpan(new ForegroundColorSpan(Color.parseColor(TAComputeUtils.LINE_COLORS[1])), strMacd.indexOf("DIF:"), strMacd.indexOf("DEA:"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			strMACDFloat.setSpan(new ForegroundColorSpan(Color.parseColor(TAComputeUtils.LINE_COLORS[2])), strMacd.indexOf("DEA:"), strMacd.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

			mTvMACDFloat.setText(strMACDFloat);

			int kdjN = PreferencesUtils.getInt(this, PreferencesUtils.KDJ_N);
			SpannableStringBuilder strKDJFloat = new SpannableStringBuilder();
			strKDJFloat.append("KDJ(");
			strKDJFloat.append(String.valueOf(kdjN));
			strKDJFloat.append(",3,3): ");

			List<LineEntity<DateValueEntity>> kdjData = currentChartData.getKdjData();
			if (kdjData.size() == 3) {
				strKDJFloat.append("K:");
				float k = kdjData.get(0).getLineData().get(kdjData.get(0).getLineData().size() - 1).getValue();
				strKDJFloat.append(df.format(k));

				strKDJFloat.append(" D:");
				float d = kdjData.get(1).getLineData().get(kdjData.get(0).getLineData().size() - 1).getValue();
				strKDJFloat.append(df.format(d));

				strKDJFloat.append(" J:");
				float j = kdjData.get(2).getLineData().get(kdjData.get(0).getLineData().size() - 1).getValue();
				strKDJFloat.append(df.format(j));
			} else {
				strKDJFloat.append("K:");
				strKDJFloat.append("0.00");

				strKDJFloat.append(" D:");
				strKDJFloat.append("0.00");

				strKDJFloat.append(" J:");
				strKDJFloat.append("0.00");
			}

			String strKDJ = strKDJFloat.toString();

			strKDJFloat.setSpan(new ForegroundColorSpan(Color.parseColor(TAComputeUtils.LINE_COLORS[0])), strKDJ.indexOf("K:"), strKDJ.indexOf(" D:"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			strKDJFloat.setSpan(new ForegroundColorSpan(Color.parseColor(TAComputeUtils.LINE_COLORS[1])), strKDJ.indexOf(" D:"), strKDJ.indexOf(" J:"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			strKDJFloat.setSpan(new ForegroundColorSpan(Color.parseColor(TAComputeUtils.LINE_COLORS[2])), strKDJ.indexOf(" J:"), strKDJ.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

			mTvKDJFloat.setText(strKDJFloat);

			int rsiN1 = PreferencesUtils.getInt(this, PreferencesUtils.RSI_N1);
			int rsiN2 = PreferencesUtils.getInt(this, PreferencesUtils.RSI_N2);
			SpannableStringBuilder strRSIFloat = new SpannableStringBuilder();

			List<LineEntity<DateValueEntity>> rsiData = currentChartData.getRsiData();
			if (rsiData.size() == 3) {
				strRSIFloat.append("RSI");
				strRSIFloat.append(String.valueOf(rsiN1));
				strRSIFloat.append(": ");
				strRSIFloat.append(df.format(rsiData.get(0).getLineData().get(rsiData.get(0).getLineData().size() - 1).getValue()));

				strRSIFloat.append(" RSI");
				strRSIFloat.append(String.valueOf(rsiN2));
				strRSIFloat.append(": ");
				strRSIFloat.append(df.format(rsiData.get(1).getLineData().get(rsiData.get(0).getLineData().size() - 1).getValue()));

				strRSIFloat.append(" RSI");
				strRSIFloat.append("24");
				strRSIFloat.append(": ");
				strRSIFloat.append(df.format(rsiData.get(2).getLineData().get(rsiData.get(0).getLineData().size() - 1).getValue()));
			} else {
				strRSIFloat.append(" RSI");
				strRSIFloat.append(String.valueOf(rsiN1));
				strRSIFloat.append(": ");
				strRSIFloat.append("0.00");

				strRSIFloat.append(" RSI");
				strRSIFloat.append(String.valueOf(rsiN2));
				strRSIFloat.append(": ");
				strRSIFloat.append("0.00");

				strRSIFloat.append(" RSI");
				strRSIFloat.append("24");
				strRSIFloat.append(": ");
				strRSIFloat.append("0.00");
			}

			String strRSI = strRSIFloat.toString();

			strRSIFloat.setSpan(new ForegroundColorSpan(Color.parseColor(TAComputeUtils.LINE_COLORS[0])), 0, strRSI.indexOf("RSI" + String.valueOf(rsiN2)), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			strRSIFloat.setSpan(new ForegroundColorSpan(Color.parseColor(TAComputeUtils.LINE_COLORS[1])), strRSI.indexOf("RSI" + String.valueOf(rsiN2)), strRSI.indexOf("RSI24:"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			strRSIFloat.setSpan(new ForegroundColorSpan(Color.parseColor(TAComputeUtils.LINE_COLORS[2])), strRSI.indexOf("RSI24:"), strRSI.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

			mTvRSIFloat.setText(strRSIFloat);

			int wrN = PreferencesUtils.getInt(this, PreferencesUtils.WR_N);
			SpannableStringBuilder strWRFloat = new SpannableStringBuilder();

			List<LineEntity<DateValueEntity>> wrData = currentChartData.getWrData();
			if (wrData.size() == 1) {
				strWRFloat.append("WR(");
				strWRFloat.append(String.valueOf(wrN));
				strWRFloat.append("): ");
				strWRFloat.append(df.format(wrData.get(0).getLineData().get(wrData.get(0).getLineData().size() - 1).getValue()));
			} else {
				strWRFloat.append("WR(");
				strWRFloat.append(String.valueOf(wrN));
				strWRFloat.append("): ");
				strWRFloat.append("0.00");
			}

			String strWR = strWRFloat.toString();

			strWRFloat.setSpan(new ForegroundColorSpan(Color.parseColor(TAComputeUtils.LINE_COLORS[0])), strWR.indexOf(":") + 1, strWR.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

			mTvWRFloat.setText(strWRFloat);

			int cciN = PreferencesUtils.getInt(this, PreferencesUtils.CCI_N);
			SpannableStringBuilder strCCIFloat = new SpannableStringBuilder();

			List<LineEntity<DateValueEntity>> cciData = currentChartData.getCciData();
			if (cciData.size() == 1) {
				strCCIFloat.append("CCI(");
				strCCIFloat.append(String.valueOf(cciN));
				strCCIFloat.append("): ");
				strCCIFloat.append(df.format(cciData.get(0).getLineData().get(cciData.get(0).getLineData().size() - 1).getValue()));
			} else {
				strCCIFloat.append("WR(");
				strCCIFloat.append(String.valueOf(cciN));
				strCCIFloat.append("): ");
				strCCIFloat.append("0.00");
			}

			String strCCI = strCCIFloat.toString();

			strCCIFloat.setSpan(new ForegroundColorSpan(Color.parseColor(TAComputeUtils.LINE_COLORS[0])), strCCI.indexOf(":") + 1, strCCI.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

			mTvCCIFloat.setText(strCCIFloat);

			int bollN = PreferencesUtils.getInt(this, PreferencesUtils.BOLL_N);
			SpannableStringBuilder strBOLLFloat = new SpannableStringBuilder();

			List<LineEntity<DateValueEntity>> bollData = currentChartData.getBollData();
			if (bollData.size() == 3) {
				strBOLLFloat.append("BOLL(");
				strBOLLFloat.append(String.valueOf(bollN));
				strBOLLFloat.append(",2,2): ");
				strBOLLFloat.append(df.format(bollData.get(0).getLineData().get(bollData.get(0).getLineData().size() - 1).getValue()));

				strBOLLFloat.append(" ");
				strBOLLFloat.append(df.format(bollData.get(1).getLineData().get(bollData.get(0).getLineData().size() - 1).getValue()));

				strBOLLFloat.append(" ");
				strBOLLFloat.append(df.format(bollData.get(2).getLineData().get(bollData.get(0).getLineData().size() - 1).getValue()));
			} else {
				strBOLLFloat.append("BOLL(");
				strBOLLFloat.append(String.valueOf(bollN));
				strBOLLFloat.append(",2,2): ");
				strBOLLFloat.append("0.00");

				strBOLLFloat.append(" ");
				strBOLLFloat.append("0.00");

				strBOLLFloat.append(" ");
				strBOLLFloat.append("0.00");
			}

			String strBOLL = strBOLLFloat.toString();

			int index0Space = 0;
			int index1Space = 0;
			int index2Space = 0;
			for (int i = 0; i < strBOLL.toCharArray().length; i++) {
				char c = strBOLL.charAt(i);
				if (c == ' ') {
					if (index0Space == 0) {
						index0Space = i;
					} else if (index1Space == 0) {
						index1Space = i;
					} else if (index2Space == 0) {
						index2Space = i;
						break;
					}
				}
			}

			strBOLLFloat.setSpan(new ForegroundColorSpan(Color.parseColor(TAComputeUtils.LINE_COLORS[0])), index0Space, index1Space, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			strBOLLFloat.setSpan(new ForegroundColorSpan(Color.parseColor(TAComputeUtils.LINE_COLORS[1])), index1Space, index2Space, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			strBOLLFloat.setSpan(new ForegroundColorSpan(Color.parseColor(TAComputeUtils.LINE_COLORS[2])), index2Space, strBOLL.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

			mTvBOLLFloat.setText(strBOLLFloat);
		}

		// 为chart1增加均线
		mVOLchart.setStickData(currentChartData.getVolData());
		mVOLchart.setLineData(currentChartData.getVolMAData());

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
	 * 请求分时数据
	 *
	 * @return
	 */
	public void loadTickData() {
		String strFileNm = "TickData.txt";

		String strData = getStringFromAssets(strFileNm);
		// TODO 请求分时数据

		ResponseData responseData = (ResponseData) FastJsonPaser.parse(strData, ResponseData.class);
		List<Map<String, String>> arrayData = responseData.getTick();

		List<OHLCVData> timesData = new ArrayList<OHLCVData>();

		for (Map<String, String> map : arrayData) {
			OHLCVData data = new OHLCVData();

			try {
				data.setOpen(Float.parseFloat(map.get("o")));
				data.setHigh(Float.parseFloat(map.get("h")));
				data.setLow(Float.parseFloat(map.get("l")));
				data.setClose(Float.parseFloat(map.get("c")));
				data.setVol(Float.parseFloat(map.get("tr")));
				data.setDate(map.get("qt"));
				data.setCurrent(Float.parseFloat(map.get("c")));
				data.setPreclose(0.0f);
				data.setChange(0.0f);

				timesData.add(data);
			} catch (Exception e) {
				continue;
			}
		}

		replaceTickValue(timesData);
	}

	private void replaceTickValue(List<OHLCVData> ohlcvDatas) {
		DecimalFormat df = new DecimalFormat("00000000");

		Map<String, OHLCVData> mapOhlcvDatas = new HashMap<String, OHLCVData>();
		for (OHLCVData ohlcvData : ohlcvDatas) {
			mapOhlcvDatas.put(DateTimeUtils.dateFormat(ohlcvData.getDate(), "yyyy-MM-ddHH: mm: ss", "2016HHmm"), ohlcvData);
		}

		long finalTime = Long.parseLong(DateTimeUtils.dateFormat(ohlcvDatas.get(ohlcvDatas.size() - 1).getDate(), "yyyy-MM-ddHH: mm: ss", "2016HHmm"));

		ShaleData[] shaleDatas = new ShaleData[]{new ShaleData(), new ShaleData()};

		int startIndex = -1;
		int finalIndex = -1;

		List<DateValueEntity> arrMinsLineDatas = (List<DateValueEntity>) mTickChart.getLinesData().get(0).getLineData();
		for (int i = 0; i < arrMinsLineDatas.size(); i++) {
			DateValueEntity lineData = arrMinsLineDatas.get(i);

			if (mapOhlcvDatas.get(df.format(lineData.getDate())) != null) {
				lineData.setValue((float) mapOhlcvDatas.get(df.format(lineData.getDate())).getClose());

				if (startIndex == -1 && mapOhlcvDatas.get(df.format(lineData.getDate())).getClose() != 0) {
					startIndex = i;
				} else if (startIndex != -1 && mapOhlcvDatas.get(df.format(lineData.getDate())).getClose() == 0) {
					finalIndex = i - 1;

					if (shaleDatas[0].getStartIndex() == -1) {
						shaleDatas[0].setStartIndex(startIndex);
						shaleDatas[0].setFinalIndex(finalIndex);
					} else {
						shaleDatas[1].setStartIndex(startIndex);
						shaleDatas[1].setFinalIndex(finalIndex);
					}

					startIndex = -1;
					finalIndex = -1;
				} else if (startIndex != -1 && lineData.getDate() == finalTime) {
					finalIndex = i - 1;

					if (shaleDatas[0].getStartIndex() == -1) {
						shaleDatas[0].setStartIndex(startIndex);
						shaleDatas[0].setFinalIndex(finalIndex);
					} else {
						shaleDatas[1].setStartIndex(startIndex);
						shaleDatas[1].setFinalIndex(finalIndex);
					}

					startIndex = -1;
					finalIndex = -1;
				}
			} else {
				lineData.setValue(0.0f);
			}
		}

		mTickChart.setNodes(shaleDatas);

		mTickChart.postInvalidate();
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

	/**
	 * 动态注册广播接收器
	 */
	private void registerReceiver() {
		if (mBroadcastReciver == null) {
			mBroadcastReciver = new BroadcastReciver();
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

			mMacdChart.setStickData(mCurrentData.getMacdData());
			mMacdChart.postInvalidate();
		} else if (indicatorType == IndicatorType.IndicatorMA) {
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

			mBollmaslipcandlestickchart.setLinesData(mCurrentData.getCandleStickLinesData());
			mBollmaslipcandlestickchart.postInvalidate();
		} else if (indicatorType == IndicatorType.IndicatorVMA) {
			mVMA1 = indicators[0];
			mVMA2 = indicators[1];
			mVMA3 = indicators[2];

			if (mDayData != null) {
				mDayData.updateVMAData(mVMA1, mVMA2, mVMA3);
			}
			if (mWeekData != null) {
				mWeekData.updateVMAData(mVMA1, mVMA2, mVMA3);
			}
			if (mMonthData != null) {
				mMonthData.updateVMAData(mVMA1, mVMA2, mVMA3);
			}

			mVOLchart.setLineData(mCurrentData.getVolMAData());
			mVOLchart.postInvalidate();
		} else if (indicatorType == IndicatorType.IndicatorKDJ) {
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

			mKDJchart.setLinesData(mCurrentData.getKdjData());
			mKDJchart.postInvalidate();
		} else if (indicatorType == IndicatorType.IndicatorRSI) {
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

			mRSIchart.setLinesData(mCurrentData.getRsiData());
			mRSIchart.postInvalidate();
		} else if (indicatorType == IndicatorType.IndicatorWR) {
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

			mWRchart.setLinesData(mCurrentData.getWrData());
			mWRchart.postInvalidate();
		} else if (indicatorType == IndicatorType.IndicatorCCI) {
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

			mCCIchart.setLinesData(mCurrentData.getCciData());
			mCCIchart.postInvalidate();
		} else if (indicatorType == IndicatorType.IndicatorBOLL) {
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
	 * 接收后台广播
	 *
	 * @author zhourr_
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

			IndicatorType indicator = indicatorType == 0 ? IndicatorType.IndicatorMACD : indicatorType == 1 ? IndicatorType.IndicatorMA : indicatorType == 2 ? IndicatorType.IndicatorVMA : indicatorType == 3 ? IndicatorType.IndicatorKDJ : indicatorType == 4 ? IndicatorType.IndicatorRSI : indicatorType == 5 ? IndicatorType.IndicatorWR : indicatorType == 6 ? IndicatorType.IndicatorCCI : IndicatorType.IndicatorBOLL;
			;

			updateChart(indicator, indicators);
		}
	}
}