package cn.limc.demo.activity;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.limc.androidcharts.R;
import cn.limc.demo.common.EnumType.ThemeModeType;
import cn.limc.demo.common.bean.HandicapData;
import cn.limc.demo.common.bean.ProductData;
import cn.limc.demo.common.utils.DipUtils;

public class SampleHorizontalDemoActivity extends SampleDemoActivity implements OnClickListener{

	private TextView tvProductInfo;
	private TextView tvCurrentPrice;
	private TextView tvChangePrice;
	private TextView tvChangePercent;
	private TextView tvBuyPrice;
	private TextView tvSellPrice;
	private TextView tvHighPrice;
	private TextView tvLowPrice;
	private TextView tvDateTime;
	
	private TextView tv1Minute;
	private TextView tv5Minute;
	private TextView tv15Minute;
	private TextView tv30Minute;
	private TextView tv1Hour;
	private TextView tv2Hour;
	private TextView tv4Hour;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		isHorizontal = true;
		setContentView(R.layout.activity_sample_horizontal_demo);
		super.onCreate(savedInstanceState);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
	@Override
	public void onClick(View v) {
		changeTopButtonsColor((TextView) v);
	}
	
	@SuppressLint("SimpleDateFormat")
	@Override
	public void initProductInfo() {
		tvProductInfo = (TextView) findViewById(R.id.tv_product_name);
		tvCurrentPrice = (TextView) findViewById(R.id.tv_current_price);
		tvChangePrice = (TextView) findViewById(R.id.tv_change_price);
		tvChangePercent = (TextView) findViewById(R.id.tv_change_percent);
		tvDateTime = (TextView) findViewById(R.id.tv_date_time);
    	tvBuyPrice = (TextView) findViewById(R.id.tv_buy_price);
    	tvHighPrice = (TextView) findViewById(R.id.tv_high_price);
    	tvSellPrice = (TextView) findViewById(R.id.tv_sell_price);
    	tvLowPrice = (TextView) findViewById(R.id.tv_low_price);
    	
    	try {
    		mProductData = (ProductData) getIntent().getExtras().getSerializable(PRODUCT_DATA);
		} catch (NullPointerException e) {
		}
    	
    	if (mProductData == null) {
    		mProductData = new ProductData();
		}
    	
    	tvProductInfo.setText(mProductData.getProductName());
    	tvCurrentPrice.setText(mProductData.getCurrentPrice());
    	tvChangePrice.setText(mProductData.getChangePrice());
    	tvChangePercent.setText(mProductData.getChangePercent());
    	
    	SimpleDateFormat dt = new SimpleDateFormat("HH:mm:ss");
    	tvDateTime.setText(dt.format(new Date()));

    	tvBuyPrice.setText(mProductData.getBuyPrice());
    	tvHighPrice.setText(mProductData.getHighPrice());
    	tvSellPrice.setText(mProductData.getSellPrice());
    	tvLowPrice.setText(mProductData.getLowPrice());
	}
	
	@Override
	public void initTopViews() {
		super.initTopViews();
		tv1Minute = (TextView) findViewById(R.id.tv_1minute);
		tv5Minute = (TextView) findViewById(R.id.tv_5minute);
		tv15Minute = (TextView) findViewById(R.id.tv_15minute);
		tv30Minute = (TextView) findViewById(R.id.tv_30minute);
		tv1Hour = (TextView) findViewById(R.id.tv_1hour);
		tv2Hour = (TextView) findViewById(R.id.tv_2hour);
		tv4Hour = (TextView) findViewById(R.id.tv_4hour);
		
		changeTopButtonsColor(mTvDay);
		
		tv1Minute.setOnClickListener(this);
		tv5Minute.setOnClickListener(this);
		tv15Minute.setOnClickListener(this);
		tv30Minute.setOnClickListener(this);
		tv1Hour.setOnClickListener(this);
		tv2Hour.setOnClickListener(this);
		tv4Hour.setOnClickListener(this);
	}
	
	@Override
	public void changeTopButtonsColor(TextView tvSelected) {
		super.changeTopButtonsColor(tvSelected);
		
		if (tv1Minute == null) {
			return;
		}
		
    	int resIDNomalColor = mThemeMode == ThemeModeType.THEME_DAY?getResources().getColor(R.color.button_normal_text_day):getResources().getColor(R.color.button_normal_text_night);
    	int resIDSelectedColor = mThemeMode == ThemeModeType.THEME_DAY?getResources().getColor(R.color.button_selected_text_day):getResources().getColor(R.color.button_selected_text_night);

		tv1Minute.setTextColor(resIDNomalColor);
		tv5Minute.setTextColor(resIDNomalColor);
		tv15Minute.setTextColor(resIDNomalColor);
		tv30Minute.setTextColor(resIDNomalColor);
		tv1Hour.setTextColor(resIDNomalColor);
		tv2Hour.setTextColor(resIDNomalColor);
		tv4Hour.setTextColor(resIDNomalColor);
    	
    	tvSelected.setTextColor(resIDSelectedColor);
	}
	
	@Override
	public void initHandicap() {
		this.mLinHandicap = (LinearLayout) findViewById(R.id.lin_handicap);
		
		try {
			mHandicapData = (HandicapData) getIntent().getExtras().getSerializable(HANDICAP_DATA);
		} catch (NullPointerException e) {
		}
		
		if (mHandicapData == null) {
			mHandicapData = new HandicapData();
		}
		
		for (int i = 0; i < 4; i++) {
			LinearLayout linHandicap = new LinearLayout(this);
			linHandicap.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 0));
        	((LinearLayout.LayoutParams)linHandicap.getLayoutParams()).weight = 1;
        	linHandicap.setOrientation(LinearLayout.HORIZONTAL);
        	mLinHandicap.addView(linHandicap);
        	
			TextView tvLeftLeftLabel = new TextView(this);
			tvLeftLeftLabel.setLayoutParams(new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT));
        	((LinearLayout.LayoutParams)tvLeftLeftLabel.getLayoutParams()).weight = 1;
        	tvLeftLeftLabel.setGravity(Gravity.RIGHT);
        	((LinearLayout.LayoutParams)tvLeftLeftLabel.getLayoutParams()).rightMargin = DipUtils.dip2px(this, 5);
        	tvLeftLeftLabel.setTextColor(getResources().getColor(R.drawable.lightgray));
        	tvLeftLeftLabel.setTextSize(12.0f);
        	linHandicap.addView(tvLeftLeftLabel);
        	
        	TextView tvLeftLeftValue = new TextView(this);
        	tvLeftLeftValue.setLayoutParams(new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT));
        	((LinearLayout.LayoutParams)tvLeftLeftValue.getLayoutParams()).weight = 1;
        	tvLeftLeftValue.setGravity(Gravity.LEFT);
        	((LinearLayout.LayoutParams)tvLeftLeftValue.getLayoutParams()).leftMargin = DipUtils.dip2px(this, 5);
        	tvLeftLeftValue.setTextColor(getResources().getColor(R.drawable.lightgray));
        	tvLeftLeftValue.setTextSize(12.0f);
        	linHandicap.addView(tvLeftLeftValue);
        	
        	TextView tvLeftRightLabel = new TextView(this);
        	tvLeftRightLabel.setLayoutParams(new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT));
        	((LinearLayout.LayoutParams)tvLeftRightLabel.getLayoutParams()).weight = 1;
        	tvLeftRightLabel.setGravity(Gravity.RIGHT);
        	((LinearLayout.LayoutParams)tvLeftRightLabel.getLayoutParams()).rightMargin = DipUtils.dip2px(this, 5);
        	tvLeftRightLabel.setTextColor(getResources().getColor(R.drawable.lightgray));
        	tvLeftRightLabel.setTextSize(12.0f);
        	linHandicap.addView(tvLeftRightLabel);
        	
        	TextView tvLeftRightValue = new TextView(this);
        	tvLeftRightValue.setLayoutParams(new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT));
        	((LinearLayout.LayoutParams)tvLeftRightValue.getLayoutParams()).weight = 1;
        	tvLeftRightValue.setGravity(Gravity.LEFT);
        	((LinearLayout.LayoutParams)tvLeftRightValue.getLayoutParams()).rightMargin = DipUtils.dip2px(this, 5);
        	tvLeftRightValue.setTextColor(getResources().getColor(R.drawable.lightgray));
        	tvLeftRightValue.setTextSize(12.0f);
        	linHandicap.addView(tvLeftRightValue);
        	
        	TextView tvRightLeftLabel = new TextView(this);
        	tvRightLeftLabel.setLayoutParams(new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT));
        	((LinearLayout.LayoutParams)tvRightLeftLabel.getLayoutParams()).weight = 1;
        	((LinearLayout.LayoutParams)tvRightLeftLabel.getLayoutParams()).rightMargin = DipUtils.dip2px(this, 5);
        	tvRightLeftLabel.setGravity(Gravity.RIGHT);
        	tvRightLeftLabel.setTextColor(getResources().getColor(R.drawable.lightgray));
        	tvRightLeftLabel.setTextSize(12.0f);
        	linHandicap.addView(tvRightLeftLabel);
        	
        	TextView tvRightLeftValue = new TextView(this);
        	tvRightLeftValue.setLayoutParams(new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT));
        	((LinearLayout.LayoutParams)tvRightLeftValue.getLayoutParams()).weight = 1;
        	tvRightLeftValue.setGravity(Gravity.LEFT);
        	((LinearLayout.LayoutParams)tvRightLeftValue.getLayoutParams()).rightMargin = DipUtils.dip2px(this, 5);
        	tvRightLeftValue.setTextColor(getResources().getColor(R.drawable.lightgray));
        	tvRightLeftValue.setTextSize(12.0f);
        	linHandicap.addView(tvRightLeftValue);
        	
        	TextView tvRightRightLabel = new TextView(this);
        	tvRightRightLabel.setLayoutParams(new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT));
        	((LinearLayout.LayoutParams)tvRightRightLabel.getLayoutParams()).weight = 1;
        	tvRightRightLabel.setGravity(Gravity.RIGHT);
        	((LinearLayout.LayoutParams)tvRightRightLabel.getLayoutParams()).rightMargin = DipUtils.dip2px(this, 5);
        	tvRightRightLabel.setTextColor(getResources().getColor(R.drawable.lightgray));
        	tvRightRightLabel.setTextSize(12.0f);
        	linHandicap.addView(tvRightRightLabel);
        	
        	TextView tvRightRightValue = new TextView(this);
        	tvRightRightValue.setLayoutParams(new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT));
        	((LinearLayout.LayoutParams)tvRightRightValue.getLayoutParams()).weight = 1;
        	tvRightRightValue.setGravity(Gravity.LEFT);
        	((LinearLayout.LayoutParams)tvRightRightValue.getLayoutParams()).rightMargin = DipUtils.dip2px(this, 5);
        	tvRightRightValue.setTextColor(getResources().getColor(R.drawable.lightgray));
        	tvRightRightValue.setTextSize(12.0f);
        	linHandicap.addView(tvRightRightValue);
        	switch (i) {
			case 0:
				tvLeftLeftLabel.setText("卖价:");
				tvLeftLeftValue.setText(mHandicapData.getSellPrice());
				tvLeftRightLabel.setText("卖量:");
				tvLeftRightValue.setText(mHandicapData.getSellCount());
				tvRightLeftLabel.setText("最高:");
				tvRightLeftValue.setText(mHandicapData.getHighPrice());
				tvRightRightLabel.setText("最低:");
				tvRightRightValue.setText(mHandicapData.getLowPrice());
				break;
			case 1:
				tvLeftLeftLabel.setText("买价:");
				tvLeftLeftValue.setText(mHandicapData.getBuyPrice());
				tvLeftRightLabel.setText("买量:");
				tvLeftRightValue.setText(mHandicapData.getBuyCount());
				tvRightLeftLabel.setText("开盘:");
				tvRightLeftValue.setText(mHandicapData.getOpenPrice());
				tvRightRightLabel.setText("总量:");
				tvRightRightValue.setText(mHandicapData.getOpenSumCount());
				break;
			case 2:
				tvLeftLeftLabel.setText("最新:");
				tvLeftLeftValue.setText(mHandicapData.getCurrentPrice());
				tvLeftRightLabel.setText("涨跌:");
				tvLeftRightValue.setText(mHandicapData.getChangePrice());
				tvRightLeftLabel.setText("昨收:");
				tvRightLeftValue.setText(mHandicapData.getClosePrice());
				tvRightRightLabel.setText("总额:");
				tvRightRightValue.setText(mHandicapData.getCloseSumCount());
				break;
			case 3:
				tvLeftLeftLabel.setText("昨结:");
				tvLeftLeftValue.setText(mHandicapData.getYesterdayClosePrice());
				tvLeftRightLabel.setText("持货:");
				tvLeftRightValue.setText(mHandicapData.getHoldSumCount());
				break;
			default:
				break;
			}
		}
	}
	
	public void updateHandicap(){    	
    	for (int i = 0; i < mLinHandicap.getChildCount(); i++) {
			LinearLayout linHandicap = (LinearLayout) mLinHandicap.getChildAt(i);
			TextView tvLeftLeftValue = (TextView) linHandicap.getChildAt(1);
			TextView tvLeftRightValue = (TextView) linHandicap.getChildAt(3);
			TextView tvRightLeftValue = (TextView) linHandicap.getChildAt(5);
			TextView tvRightRightValue = (TextView) linHandicap.getChildAt(7);
			
			switch (i) {
			case 0:
				tvLeftLeftValue.setText(mHandicapData.getSellPrice());
				tvLeftRightValue.setText(mHandicapData.getSellCount());
				tvRightLeftValue.setText(mHandicapData.getHighPrice());
				tvRightRightValue.setText(mHandicapData.getLowPrice());
				break;
			case 1:
				tvLeftLeftValue.setText(mHandicapData.getBuyPrice());
				tvLeftRightValue.setText(mHandicapData.getBuyCount());
				tvRightLeftValue.setText(mHandicapData.getOpenPrice());
				tvRightRightValue.setText(mHandicapData.getOpenSumCount());
				break;
			case 2:
				tvLeftLeftValue.setText(mHandicapData.getCurrentPrice());
				tvLeftRightValue.setText(mHandicapData.getChangePrice());
				tvRightLeftValue.setText(mHandicapData.getClosePrice());
				tvRightRightValue.setText(mHandicapData.getCloseSumCount());
				break;
			case 3:
				tvLeftLeftValue.setText(mHandicapData.getYesterdayClosePrice());
				tvLeftRightValue.setText(mHandicapData.getHoldSumCount());
				break;
			default:
				break;
			}
		}
    }
}
