package cn.limc.androidcharts.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import cn.limc.androidcharts.entity.StickEntity;

public class StickChart extends GridChart {
	
	////////////默认值///////////////
	/** 显示纬线数 */
	public static final int DEFAULT_LATITUDE_NUM = 4;
	
	/** 显示纬线数 */
	public static final int DEFAULT_LONGTITUDE_NUM = 3;
	
	/** 柱条边�?��色 */
	public static final int DEFAULT_STICK_BORDER_COLOR = Color.RED;
	
	/** 柱条填�?��色 */
	public static final int DEFAULT_STICK_FILL_COLOR = Color.RED;

	////////////属�?列表/////////////////

	/** 柱条边�?��色 */
	private int stickBorderColor = DEFAULT_STICK_BORDER_COLOR ;

	/** 柱条填�?��色 */
	private int stickFillColor = DEFAULT_STICK_FILL_COLOR;
	
	/** 显示纬线数 */
	private int latitudeNum = DEFAULT_LATITUDE_NUM;
	
	/** 显示经线数 */
	private int longtitudeNum = DEFAULT_LONGTITUDE_NUM;
	
	/** K线数据 */
	private List<StickEntity> StickData;
	
	/** 图表中�?��蜡烛线 */
	private int maxStickDataNum;

	/** K线显示�?��价格 */
	protected float maxValue;

	/** K线显示�?��价格 */
	protected float minValue;	
	
	/////////////////�??函数///////////////

	public StickChart(Context context) {
		super(context);
	}

	public StickChart(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public StickChart(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	
	///////////////函数方�?////////////////

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		initAxisY();
		initAxisX();
		super.onDraw(canvas);

		drawSticks(canvas);
	}
	
	
	/**
	 * 获取X轴刻度位置,�?�??�最大1
	 * @param value
	 * @return
	 */
	@Override
	public String getAxisXGraduate(Object value){
		float graduate = Float.valueOf(super.getAxisXGraduate(value));
		int index = (int) Math.floor(graduate*maxStickDataNum);
		
		if(index >= maxStickDataNum){
			index = maxStickDataNum -1;
		}else if(index < 0){
			index = 0;
		}
		
		return String.valueOf(StickData.get(index).getDate());
	}
	
	/**
	 * 获取Y轴刻度位置,�?�??�最大1
	 * @param value
	 * @return
	 */
	@Override
	public String getAxisYGraduate(Object value){
		float graduate = Float.valueOf(super.getAxisYGraduate(value));
		return  String.valueOf((int)Math.floor(graduate * (maxValue - minValue) + minValue));
	}
	
	/**
	 * 获得来自其他图�?��通知
	 */
	@Override
	public void notifyEvent(GridChart chart) {
		
		CandleStickChart candlechart = (CandleStickChart)chart;
		
		this.maxStickDataNum = candlechart.getMaxCandleSticksNum();
		
		//不显示Y轴信息
		super.setDisplayCrossYOnTouch(false);
		//�?���??通知
		super.notifyEvent(chart);
		//对外�??通知
		super.notifyEventAll(this);
	}
	
	/**
	 * 初始化X轴
	 */
	protected void initAxisX() {
		List<String> TitleX = new ArrayList<String>();
		if(null != StickData){
			float average = maxStickDataNum / longtitudeNum;
			//�?��刻度
			for (int i = 0; i < longtitudeNum; i++) {
				int index = (int) Math.floor(i * average);
				if(index > maxStickDataNum-1){
					index = maxStickDataNum-1;
				}
				//追�??�?
				TitleX.add(String.valueOf(StickData.get(index).getDate()).substring(4));
			}
			TitleX.add(String.valueOf(StickData.get(maxStickDataNum-1).getDate()).substring(4));
		}
		super.setAxisXTitles(TitleX);
	}
	
	public int getSelectedIndex() {
		if(null == super.getTouchPoint()){
			return 0;
		}
		float graduate = Float.valueOf(super.getAxisXGraduate(super.getTouchPoint().x));
		int index = (int) Math.floor(graduate*maxStickDataNum);
		
		if(index >= maxStickDataNum){
			index = maxStickDataNum -1;
		}else if(index < 0){
			index = 0;
		}
		
		return index;
	}
	
	/**
	 * 多点触控事件
	 */
	protected void drawWithFingerMove() {
	}
	
	/**
	 * 初始化Y轴
	 */
	protected void initAxisY() {
		List<String> TitleY = new ArrayList<String>();
		float average = (int)((maxValue - minValue) / latitudeNum)/100 * 100;;
		//�?��刻度
		for (int i = 0; i < latitudeNum; i++) {
			String value = String.valueOf((int)Math.floor(minValue + i * average));
			if(value.length() < super.getAxisYMaxTitleLength()){
				while(value.length() < super.getAxisYMaxTitleLength()){
					value = new String(" ") + value;
				}
			}
			TitleY.add(value);
		}
		//�?���?��值
		String value = String.valueOf((int)Math.floor(((int)maxValue) / 100 * 100));
		if(value.length() < super.getAxisYMaxTitleLength()){
			while(value.length() < super.getAxisYMaxTitleLength()){
				value = new String(" ") + value;
			}
		}
		TitleY.add(value);

		super.setAxisYTitles(TitleY);
	}

	/**
	 * 绘制柱状线
	 * @param canvas
	 */
	protected void drawSticks(Canvas canvas) {
		// 蜡烛棒宽度
		float stickWidth = ((super.getWidth() - super.getAxisMarginLeft()-super.getAxisMarginRight()) / maxStickDataNum) - 1;
		// 蜡烛棒起始绘制位置
		float stickX = super.getAxisMarginLeft() + 1;

		Paint mPaintStick = new Paint();
		mPaintStick.setColor(stickFillColor);

		if(null != StickData){
			
			//判断显示为方柱或显示为线条
			for (int i = 0; i < StickData.size(); i++) {
				StickEntity ohlc = StickData.get(i);
	
				float highY = (float) ((1f - (ohlc.getHigh() - minValue)
						/ (maxValue - minValue)) * (super.getHeight() - super
						.getAxisMarginBottom()) - super.getAxisMarginTop());
				float lowY = (float) ((1f - (ohlc.getLow() - minValue)
						/ (maxValue - minValue)) * (super.getHeight() - super
						.getAxisMarginBottom()) - super.getAxisMarginTop());
	
				//绘制数据?��?据宽度判断绘制直线或方柱
				if(stickWidth >= 2f){
					canvas.drawRect(stickX, highY, stickX + stickWidth, lowY, mPaintStick);
				}else{
					canvas.drawLine(stickX, highY, stickX , lowY, mPaintStick);
				}
				
				//X位移
				stickX = stickX + 1 + stickWidth;
			}
		}
	}
	
	//Push数据绘制K线图
	public void pushData(StickEntity entity){
		if(null != entity){
			//追�?��据到数据列表
			addData(entity);
			//强制重�?
			super.postInvalidate();
		}
	}
	
	//Push数据绘制K线图
	public void addData(StickEntity entity){
		if(null != entity){
			//追�?��据
			if(null == StickData || 0 == StickData.size() ){
				StickData = new ArrayList<StickEntity>();
				this.maxValue = ((int)entity.getHigh()) / 100 * 100;
			}
			
			this.StickData.add(entity);
			
			if (this.maxValue < entity.getHigh()){
				this.maxValue = 100 + ((int)entity.getHigh()) / 100 * 100;
			}
			
			if(StickData.size() > maxStickDataNum){
				maxStickDataNum = maxStickDataNum +1;
			}
		}
	}
	
	//////////////属�?GetterSetter/////////////////
	
	public List<StickEntity> getStickData() {
		return StickData;
	}

	public void setStickData(List<StickEntity> stickData) {
		//�?��已有数据
		if(null != StickData){
			StickData.clear();
		}
		for(StickEntity e :stickData){
			addData(e);
		}
	}

	public int getStickFillColor() {
		return stickFillColor;
	}

	public void setStickFillColor(int stickFillColor) {
		this.stickFillColor = stickFillColor;
	}

	public int getLatitudeNum() {
		return latitudeNum;
	}

	public void setLatitudeNum(int latitudeNum) {
		this.latitudeNum = latitudeNum;
	}

	public int getMaxStickDataNum() {
		return maxStickDataNum;
	}

	public void setMaxStickDataNum(int maxStickDataNum) {
		this.maxStickDataNum = maxStickDataNum;
	}

	public float getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(float maxValue) {
		this.maxValue = maxValue;
	}

	public float getMinValue() {
		return minValue;
	}

	public void setMinValue(float minValue) {
		this.minValue = minValue;
	}

	public int getStickBorderColor() {
		return stickBorderColor;
	}

	public void setStickBorderColor(int stickBorderColor) {
		this.stickBorderColor = stickBorderColor;
	}

	public int getLongtitudeNum() {
		return longtitudeNum;
	}

	public void setLongtitudeNum(int longtitudeNum) {
		this.longtitudeNum = longtitudeNum;
	}
}
