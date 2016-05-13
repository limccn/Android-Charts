package cn.limc.androidcharts.demo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.limc.androidcharts.demo.R;
import cn.limc.androidcharts.demo.common.BaseActivity;
import cn.limc.androidcharts.demo.common.EnumType.IndicatorType;
import cn.limc.androidcharts.demo.common.EnumType.ThemeModeType;
import cn.limc.androidcharts.demo.common.utils.PreferencesUtils;

public class SettingDetailActivity extends BaseActivity {
	
	/** 主题 */
	ThemeModeType mThemeMode;

	RelativeLayout mRelBackground;

	LinearLayout mLinFirstContainer;
	LinearLayout mLinSecondContainer;
	LinearLayout mLinThirdContainer;
	View mCenterLine;
	
	TextView mTvFirstIndicator;
	TextView mTvSecondIndicator;
	TextView mTvThirdIndicator;
	
	EditText mEtFirstIndicator;
	EditText mEtSecondIndicator;
	EditText mEtThirdIndicator;
	
	Button mBtnSetting;
	
	IndicatorType mIndicatorType;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_detail);
        
        initValues();
        
        initWidgets();
	}
	
	private void initValues() {
		int themeMode =  PreferencesUtils.getInt(this, PreferencesUtils.THEME_MODE);
		mThemeMode = themeMode == -1?ThemeModeType.THEME_DAY:themeMode == 0?ThemeModeType.THEME_DAY:ThemeModeType.THEME_NIGHT;

		mRelBackground = (RelativeLayout) findViewById(R.id.rel_background);

		mLinFirstContainer = (LinearLayout) findViewById(R.id.lin_first_conrainer);
		mLinSecondContainer = (LinearLayout) findViewById(R.id.lin_second_conrainer);
		mLinThirdContainer = (LinearLayout) findViewById(R.id.lin_third_conrainer);
		mCenterLine = (View) findViewById(R.id.v_thirdline);
		
		mTvFirstIndicator = (TextView) findViewById(R.id.tv_first_indicator);
		mTvSecondIndicator = (TextView) findViewById(R.id.tv_second_indicator);
		mTvThirdIndicator = (TextView) findViewById(R.id.tv_third_indicator);
		
		mEtFirstIndicator = (EditText) findViewById(R.id.et_first_indicator);
		mEtSecondIndicator = (EditText) findViewById(R.id.et_second_indicator);
		mEtThirdIndicator = (EditText) findViewById(R.id.et_third_indicator);
		
		mBtnSetting = (Button) findViewById(R.id.btn_setting);
		
		int indicatorType = (int) getIntent().getExtras().getInt("indicatorType", 0);
		
		mIndicatorType = indicatorType == 0?IndicatorType.IndicatorMACD:indicatorType == 1?IndicatorType.IndicatorMA:indicatorType == 2?IndicatorType.IndicatorKDJ:indicatorType == 3?IndicatorType.IndicatorRSI:indicatorType == 4?IndicatorType.IndicatorWR:indicatorType == 5?IndicatorType.IndicatorCCI:IndicatorType.IndicatorBOLL;
	}
	
	private void initWidgets() {
		initViews(mIndicatorType);
		
    	mRelBackground.setBackgroundResource(mThemeMode==ThemeModeType.THEME_DAY?R.color.chart_background_day:R.color.chart_background_night);

    	mLinFirstContainer.setBackgroundResource(mThemeMode==ThemeModeType.THEME_DAY?R.color.cell_background_day:R.color.cell_background_night);
    	mLinSecondContainer.setBackgroundResource(mThemeMode==ThemeModeType.THEME_DAY?R.color.cell_background_day:R.color.cell_background_night);
    	mLinThirdContainer.setBackgroundResource(mThemeMode==ThemeModeType.THEME_DAY?R.color.cell_background_day:R.color.cell_background_night);
    	
    	mTvFirstIndicator.setTextColor(mThemeMode==ThemeModeType.THEME_DAY?getResources().getColor(R.color.cell_text_day):getResources().getColor(R.color.cell_text_night));
    	mTvSecondIndicator.setTextColor(mThemeMode==ThemeModeType.THEME_DAY?getResources().getColor(R.color.cell_text_day):getResources().getColor(R.color.cell_text_night));
    	mTvThirdIndicator.setTextColor(mThemeMode==ThemeModeType.THEME_DAY?getResources().getColor(R.color.cell_text_day):getResources().getColor(R.color.cell_text_night));

		mEtFirstIndicator.setTextColor(mThemeMode==ThemeModeType.THEME_DAY?getResources().getColor(R.color.cell_text_day):getResources().getColor(R.color.cell_text_night));
		mEtSecondIndicator.setTextColor(mThemeMode==ThemeModeType.THEME_DAY?getResources().getColor(R.color.cell_text_day):getResources().getColor(R.color.cell_text_night));
		mEtThirdIndicator.setTextColor(mThemeMode==ThemeModeType.THEME_DAY?getResources().getColor(R.color.cell_text_day):getResources().getColor(R.color.cell_text_night));

		mBtnSetting.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
                Intent intent = new Intent();
				intent.setAction("cn.abel.action.broadcast");

				intent.putExtra("indicators", verfiyIndicatorInput(mIndicatorType));
				intent.putExtra("indicatorType", mIndicatorType.ordinal());
				SettingDetailActivity.this.sendBroadcast(intent);
				
                finish();
			}
		});
    }
	
	private void initViews(IndicatorType indicatorType) {
		if (indicatorType == IndicatorType.IndicatorMACD) {
			mTvFirstIndicator.setText("S:");
			mTvSecondIndicator.setText("L:");
			mTvThirdIndicator.setText("M:");
			
			mEtFirstIndicator.setHint("MACD-S");
			mEtSecondIndicator.setHint("MACD-L");
			mEtThirdIndicator.setHint("MACD-M");
			
			mEtFirstIndicator.setText(PreferencesUtils.getInt(this, PreferencesUtils.MACD_S)+"");
			mEtSecondIndicator.setText(PreferencesUtils.getInt(this, PreferencesUtils.MACD_L)+"");
			mEtThirdIndicator.setText(PreferencesUtils.getInt(this, PreferencesUtils.MACD_M)+"");
		}else if (indicatorType == IndicatorType.IndicatorVMA) {
			mTvFirstIndicator.setText("VMA1:");
			mTvSecondIndicator.setText("VMA2:");
			mTvThirdIndicator.setText("VMA3:");
			
			mEtFirstIndicator.setHint("VMA1");
			mEtSecondIndicator.setHint("VMA2");
			mEtThirdIndicator.setHint("VMA3");
			
			mEtFirstIndicator.setText(PreferencesUtils.getInt(this, PreferencesUtils.MA1)+"");
			mEtSecondIndicator.setText(PreferencesUtils.getInt(this, PreferencesUtils.MA2)+"");
			mEtThirdIndicator.setText(PreferencesUtils.getInt(this, PreferencesUtils.MA3)+"");
		}else if (indicatorType == IndicatorType.IndicatorMA) {
			mTvFirstIndicator.setText("MA1:");
			mTvSecondIndicator.setText("MA2:");
			mTvThirdIndicator.setText("MA3:");
			
			mEtFirstIndicator.setHint("MA1");
			mEtSecondIndicator.setHint("MA2");
			mEtThirdIndicator.setHint("MA3");
			
			mEtFirstIndicator.setText(PreferencesUtils.getInt(this, PreferencesUtils.MA1)+"");
			mEtSecondIndicator.setText(PreferencesUtils.getInt(this, PreferencesUtils.MA2)+"");
			mEtThirdIndicator.setText(PreferencesUtils.getInt(this, PreferencesUtils.MA3)+"");
		}else if (indicatorType == IndicatorType.IndicatorKDJ) {
			mTvFirstIndicator.setText("KDJ:");
			
			mEtFirstIndicator.setHint("KDJ-N");
			
			mLinSecondContainer.setVisibility(View.INVISIBLE);
			mLinThirdContainer.setVisibility(View.INVISIBLE);
			mCenterLine.setVisibility(View.INVISIBLE);
			
			mEtFirstIndicator.setText(PreferencesUtils.getInt(this, PreferencesUtils.KDJ_N)+"");
		}else if (indicatorType == IndicatorType.IndicatorRSI) {
			mTvFirstIndicator.setText("N1:");
			mTvSecondIndicator.setText("N2:");
			
			mEtFirstIndicator.setHint("RSI-N1");
			mEtSecondIndicator.setHint("RSI-N2");
			
			mLinThirdContainer.setVisibility(View.INVISIBLE);
			
			mEtFirstIndicator.setText(PreferencesUtils.getInt(this, PreferencesUtils.RSI_N1)+"");
			mEtSecondIndicator.setText(PreferencesUtils.getInt(this, PreferencesUtils.RSI_N2)+"");
		}else if (indicatorType == IndicatorType.IndicatorWR) {
			mTvFirstIndicator.setText("WR:");
			
			mEtFirstIndicator.setHint("WR-N");
			
			mLinSecondContainer.setVisibility(View.INVISIBLE);
			mLinThirdContainer.setVisibility(View.INVISIBLE);
			mCenterLine.setVisibility(View.INVISIBLE);
			
			mEtFirstIndicator.setText(PreferencesUtils.getInt(this, PreferencesUtils.WR_N)+"");
		}else if (indicatorType == IndicatorType.IndicatorCCI) {
			mTvFirstIndicator.setText("CCI:");
			
			mEtFirstIndicator.setHint("CCI-N");
			
			mLinSecondContainer.setVisibility(View.INVISIBLE);
			mLinThirdContainer.setVisibility(View.INVISIBLE);
			mCenterLine.setVisibility(View.INVISIBLE);
			
			mEtFirstIndicator.setText(PreferencesUtils.getInt(this, PreferencesUtils.CCI_N)+"");
		}else if (indicatorType == IndicatorType.IndicatorBOLL) {
			mTvFirstIndicator.setText("BOLL:");
			
			mEtFirstIndicator.setHint("BOLL-N");
			
			mLinSecondContainer.setVisibility(View.INVISIBLE);
			mLinThirdContainer.setVisibility(View.INVISIBLE);
			mCenterLine.setVisibility(View.INVISIBLE);
			
			mEtFirstIndicator.setText(PreferencesUtils.getInt(this, PreferencesUtils.BOLL_N)+"");
		}
	}
	
	private int[] verfiyIndicatorInput(IndicatorType indicatorType) {
		int[] indicators = null;
		
		if (indicatorType == IndicatorType.IndicatorMACD) {
			indicators = new int[]{Integer.parseInt(mEtFirstIndicator.getEditableText().toString()), Integer.parseInt(mEtSecondIndicator.getEditableText().toString()), Integer.parseInt(mEtThirdIndicator.getEditableText().toString())};
		}else if (indicatorType == IndicatorType.IndicatorMA) {
			indicators = new int[]{Integer.parseInt(mEtFirstIndicator.getEditableText().toString()), Integer.parseInt(mEtSecondIndicator.getEditableText().toString()), Integer.parseInt(mEtThirdIndicator.getEditableText().toString())};
		}else if (indicatorType == IndicatorType.IndicatorVMA) {
			indicators = new int[]{Integer.parseInt(mEtFirstIndicator.getEditableText().toString()), Integer.parseInt(mEtSecondIndicator.getEditableText().toString()), Integer.parseInt(mEtThirdIndicator.getEditableText().toString())};
		}else if (indicatorType == IndicatorType.IndicatorKDJ) {
			indicators = new int[]{Integer.parseInt(mEtFirstIndicator.getEditableText().toString())};
		}else if (indicatorType == IndicatorType.IndicatorRSI) {
			indicators = new int[]{Integer.parseInt(mEtFirstIndicator.getEditableText().toString()), Integer.parseInt(mEtSecondIndicator.getEditableText().toString())};
		}else if (indicatorType == IndicatorType.IndicatorWR) {
			indicators = new int[]{Integer.parseInt(mEtFirstIndicator.getEditableText().toString())};
		}else if (indicatorType == IndicatorType.IndicatorCCI) {
			indicators = new int[]{Integer.parseInt(mEtFirstIndicator.getEditableText().toString())};
		}else if (indicatorType == IndicatorType.IndicatorBOLL) {
			indicators = new int[]{Integer.parseInt(mEtFirstIndicator.getEditableText().toString())};
		}
		
		return indicators;
	}
}
