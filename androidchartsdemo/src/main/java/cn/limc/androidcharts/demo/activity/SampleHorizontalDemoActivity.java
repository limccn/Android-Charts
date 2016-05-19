package cn.limc.androidcharts.demo.activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import cn.limc.androidcharts.demo.R;
import cn.limc.androidcharts.demo.common.utils.DateTimeUtils;

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
    	
    	tvProductInfo.setText("上证指数");
    	tvCurrentPrice.setText("3500");
    	tvChangePrice.setText("+5");
    	tvChangePercent.setText("0.3%");

    	tvDateTime.setText(DateTimeUtils.currrentDateFormat("HH:mm:ss"));

    	tvBuyPrice.setText("3496");
    	tvHighPrice.setText("3510");
    	tvSellPrice.setText("3505");
    	tvLowPrice.setText("3490");
	}
}
