package cn.limc.demo.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.limc.androidcharts.R;
import cn.limc.demo.common.BaseActivity;
import cn.limc.demo.common.BaseLvAdapter;
import cn.limc.demo.common.EnumType.ThemeModeType;
import cn.limc.demo.common.utils.PreferencesUtils;

public class SettingActivity extends BaseActivity {

	private BroadcastReciver mBroadcastReciver;
	
	/** 主题 */
	ThemeModeType mThemeMode;
	
	List<Map<String, String>> mIndicatorData;
	
	RelativeLayout mRelBackground;
	ListView mLvIndicator;
	LvAdapter mAdapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        
        initValues();
        
        initWidgets();
        
        registerReceiver();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void onResume() {
		super.onResume();
		
		mAdapter.reSet(mIndicatorData);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		unregisterReceiver(mBroadcastReciver);
		mBroadcastReciver = null;
	}
	
	private void initValues() {
		int themeMode =  PreferencesUtils.getInt(this, PreferencesUtils.THEME_MODE);
		mThemeMode = themeMode == -1?ThemeModeType.THEME_DAY:themeMode == 0?ThemeModeType.THEME_DAY:ThemeModeType.THEME_NIGHT;

		this.mRelBackground = (RelativeLayout) findViewById(R.id.rel_background);
		this.mLvIndicator = (ListView) findViewById(R.id.lv_indicator);
    	
		mIndicatorData = new ArrayList<Map<String, String>>();
    	
    	Map<String, String> macdIndicator = new HashMap<String, String>();
    	macdIndicator.put("title", "MACD指标");
    	mIndicatorData.add(macdIndicator);
    	Map<String, String> maIndicator = new HashMap<String, String>();
    	maIndicator.put("title", "MA均线");
    	mIndicatorData.add(maIndicator);
    	Map<String, String> vmaIndicator = new HashMap<String, String>();
    	vmaIndicator.put("title", "VMA均线");
    	mIndicatorData.add(vmaIndicator);
    	Map<String, String> kdjIndicator = new HashMap<String, String>();
    	kdjIndicator.put("title", "KDJ随机指标");
    	mIndicatorData.add(kdjIndicator);
    	Map<String, String> rsiIndicator = new HashMap<String, String>();
    	rsiIndicator.put("title", "RSI强弱指标");
    	mIndicatorData.add(rsiIndicator);
    	Map<String, String> wrIndicator = new HashMap<String, String>();
    	wrIndicator.put("title", "WR指标");
    	mIndicatorData.add(wrIndicator);
    	Map<String, String> cciIndicator = new HashMap<String, String>();
    	cciIndicator.put("title", "CCI指标");
    	mIndicatorData.add(cciIndicator);
    	Map<String, String> bollIndicator = new HashMap<String, String>();
    	bollIndicator.put("title", "BOLL指标");
    	mIndicatorData.add(bollIndicator);
    	
    	mAdapter = new LvAdapter(this, mIndicatorData, mLvIndicator);
	}
	
	private void initWidgets() {
    	mLvIndicator.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				arg1.setBackgroundResource(R.drawable.lightgray);
				
				Intent intent = new Intent();
				intent.setClass(SettingActivity.this, SettingDetailActivity.class);
				
				Bundle bundle = new Bundle();
				bundle.putInt("indicatorType",arg2);
				intent.putExtras(bundle);
				
                startActivity(intent);
			}
		});
    	
    	mRelBackground.setBackgroundResource(mThemeMode==ThemeModeType.THEME_DAY?R.color.chart_background_day:R.color.chart_background_night);
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
 			res = R.layout.activity_setting_list_item;
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
			
			convertView.setBackgroundResource(mThemeMode == ThemeModeType.THEME_DAY?R.color.cell_background_day:R.color.cell_background_night);
			
			@SuppressWarnings("unchecked")
			Map<String, String> data = (Map<String, String>) dataList.get(position);
			
			viewHolder.tvIndicator.setText(data.get("title"));
			viewHolder.tvIndicator.setTextColor(mThemeMode == ThemeModeType.THEME_DAY?getResources().getColor(R.color.cell_text_day):getResources().getColor(R.color.cell_text_night));
 			
 			return convertView;
 		}
 	}

 	/**
 	 * ViewHolder
 	 * @author zhourr_
 	 *
 	 */
 	class ViewHolder{
 		TextView tvIndicator;
		
 		public ViewHolder(View convertView){
 			tvIndicator = (TextView) convertView.findViewById(R.id.tv_indicator);
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
			
			finish();
		}
	}
}
