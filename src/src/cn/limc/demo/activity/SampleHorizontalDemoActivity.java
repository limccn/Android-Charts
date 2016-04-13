package cn.limc.demo.activity;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.limc.androidcharts.R;
import cn.limc.demo.common.DipUtils;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

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
		tvBuyPrice = (TextView) findViewById(R.id.tv_buy_price);
		tvSellPrice = (TextView) findViewById(R.id.tv_sell_price);
		tvHighPrice = (TextView) findViewById(R.id.tv_high_price);
		tvLowPrice = (TextView) findViewById(R.id.tv_low_price);
		tvDateTime = (TextView) findViewById(R.id.tv_date_time);
		
		tvProductInfo.setText("龙天勇银");
		tvCurrentPrice.setText("3500");
		tvChangePrice.setText("35");
		tvChangePercent.setText("(10%)");
		tvBuyPrice.setText("3499");
		tvSellPrice.setText("3450");
		tvHighPrice.setText("3559");
		tvLowPrice.setText("3444");
		
		SimpleDateFormat dt = new SimpleDateFormat("HH:mm:ss");
		tvDateTime.setText(dt.format(new Date()));
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
		
		tv1Minute.setTextColor(Color.LTGRAY);
		tv5Minute.setTextColor(Color.LTGRAY);
		tv15Minute.setTextColor(Color.LTGRAY);
		tv30Minute.setTextColor(Color.LTGRAY);
		tv1Hour.setTextColor(Color.LTGRAY);
		tv2Hour.setTextColor(Color.LTGRAY);
		tv4Hour.setTextColor(Color.LTGRAY);
    	
    	tvSelected.setTextColor(Color.WHITE);
	}
	
	@Override
	public void initHandicap() {
		this.mLinHandicap = (LinearLayout) findViewById(R.id.lin_handicap);
		
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
        	tvLeftLeftLabel.setTextColor(Color.LTGRAY);
        	tvLeftLeftLabel.setTextSize(12.0f);
        	linHandicap.addView(tvLeftLeftLabel);
        	
        	TextView tvLeftLeftValue = new TextView(this);
        	tvLeftLeftValue.setLayoutParams(new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT));
        	((LinearLayout.LayoutParams)tvLeftLeftValue.getLayoutParams()).weight = 1;
        	tvLeftLeftValue.setGravity(Gravity.LEFT);
        	((LinearLayout.LayoutParams)tvLeftLeftValue.getLayoutParams()).leftMargin = DipUtils.dip2px(this, 5);
        	tvLeftLeftValue.setTextColor(Color.LTGRAY);
        	tvLeftLeftValue.setTextSize(12.0f);
        	linHandicap.addView(tvLeftLeftValue);
        	
        	TextView tvLeftRightLabel = new TextView(this);
        	tvLeftRightLabel.setLayoutParams(new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT));
        	((LinearLayout.LayoutParams)tvLeftRightLabel.getLayoutParams()).weight = 1;
        	tvLeftRightLabel.setGravity(Gravity.RIGHT);
        	((LinearLayout.LayoutParams)tvLeftRightLabel.getLayoutParams()).rightMargin = DipUtils.dip2px(this, 5);
        	tvLeftRightLabel.setTextColor(Color.LTGRAY);
        	tvLeftRightLabel.setTextSize(12.0f);
        	linHandicap.addView(tvLeftRightLabel);
        	
        	TextView tvLeftRightValue = new TextView(this);
        	tvLeftRightValue.setLayoutParams(new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT));
        	((LinearLayout.LayoutParams)tvLeftRightValue.getLayoutParams()).weight = 1;
        	tvLeftRightValue.setGravity(Gravity.LEFT);
        	((LinearLayout.LayoutParams)tvLeftRightValue.getLayoutParams()).rightMargin = DipUtils.dip2px(this, 5);
        	tvLeftRightValue.setTextColor(Color.LTGRAY);
        	tvLeftRightValue.setTextSize(12.0f);
        	linHandicap.addView(tvLeftRightValue);
        	
        	TextView tvRightLeftLabel = new TextView(this);
        	tvRightLeftLabel.setLayoutParams(new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT));
        	((LinearLayout.LayoutParams)tvRightLeftLabel.getLayoutParams()).weight = 1;
        	((LinearLayout.LayoutParams)tvRightLeftLabel.getLayoutParams()).rightMargin = DipUtils.dip2px(this, 5);
        	tvRightLeftLabel.setGravity(Gravity.RIGHT);
        	tvRightLeftLabel.setTextColor(Color.LTGRAY);
        	tvRightLeftLabel.setTextSize(12.0f);
        	linHandicap.addView(tvRightLeftLabel);
        	
        	TextView tvRightLeftValue = new TextView(this);
        	tvRightLeftValue.setLayoutParams(new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT));
        	((LinearLayout.LayoutParams)tvRightLeftValue.getLayoutParams()).weight = 1;
        	tvRightLeftValue.setGravity(Gravity.LEFT);
        	((LinearLayout.LayoutParams)tvRightLeftValue.getLayoutParams()).rightMargin = DipUtils.dip2px(this, 5);
        	tvRightLeftValue.setTextColor(Color.LTGRAY);
        	tvRightLeftValue.setTextSize(12.0f);
        	linHandicap.addView(tvRightLeftValue);
        	
        	TextView tvRightRightLabel = new TextView(this);
        	tvRightRightLabel.setLayoutParams(new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT));
        	((LinearLayout.LayoutParams)tvRightRightLabel.getLayoutParams()).weight = 1;
        	tvRightRightLabel.setGravity(Gravity.RIGHT);
        	((LinearLayout.LayoutParams)tvRightRightLabel.getLayoutParams()).rightMargin = DipUtils.dip2px(this, 5);
        	tvRightRightLabel.setTextColor(Color.LTGRAY);
        	tvRightRightLabel.setTextSize(12.0f);
        	linHandicap.addView(tvRightRightLabel);
        	
        	TextView tvRightRightValue = new TextView(this);
        	tvRightRightValue.setLayoutParams(new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT));
        	((LinearLayout.LayoutParams)tvRightRightValue.getLayoutParams()).weight = 1;
        	tvRightRightValue.setGravity(Gravity.LEFT);
        	((LinearLayout.LayoutParams)tvRightRightValue.getLayoutParams()).rightMargin = DipUtils.dip2px(this, 5);
        	tvRightRightValue.setTextColor(Color.LTGRAY);
        	tvRightRightValue.setTextSize(12.0f);
        	linHandicap.addView(tvRightRightValue);
        	switch (i) {
			case 0:
				tvLeftLeftLabel.setText("卖价:");
				tvLeftLeftValue.setText("3559");
				tvLeftRightLabel.setText("卖量:");
				tvLeftRightValue.setText("2");
				tvRightLeftLabel.setText("最高:");
				tvRightLeftValue.setText("3559");
				tvRightRightLabel.setText("最低:");
				tvRightRightValue.setText("3559");
				break;
			case 1:
				tvLeftLeftLabel.setText("买价:");
				tvLeftLeftValue.setText("3559");
				tvLeftRightLabel.setText("买量:");
				tvLeftRightValue.setText("1");
				tvRightLeftLabel.setText("开盘:");
				tvRightLeftValue.setText("3559");
				tvRightRightLabel.setText("总量:");
				tvRightRightValue.setText("3559");
				break;
			case 2:
				tvLeftLeftLabel.setText("最新:");
				tvLeftLeftValue.setText("3559");
				tvLeftRightLabel.setText("涨跌:");
				tvLeftRightValue.setText("11");
				tvRightLeftLabel.setText("昨收:");
				tvRightLeftValue.setText("3559");
				tvRightRightLabel.setText("总额:");
				tvRightRightValue.setText("3559");
				break;
			case 3:
				tvLeftLeftLabel.setText("昨结:");
				tvLeftLeftValue.setText("3559");
				tvLeftRightLabel.setText("持货:");
				tvLeftRightValue.setText("3559");
				break;
			default:
				break;
			}
		}
	}
}
