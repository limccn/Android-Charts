package cn.limc.androidcharts.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.view.MotionEvent;
import cn.limc.androidcharts.entity.OHLCEntity;


public class CandleStickChart extends GridChart {
	
	////////////默认值///////////////
	/** 显示纬线数 */
	public static final int DEFAULT_LATITUDE_NUM = 4;
	
	/** 显示纬线数 */
	public static final int DEFAULT_LONGTITUDE_NUM = 3;
	
	/** 阳线边�?��色 */
	public static final int DEFAULT_POSITIVE_STICK_BORDER_COLOR = Color.RED;
	
	/** 阳线填�?��色 */
	public static final int DEFAULT_POSITIVE_STICK_FILL_COLOR = Color.RED;
	
	/** 阴线边�?��色 */
	public static final int DEFAULT_NEGATIVE_STICK_BORDER_COLOR = Color.GREEN;
	
	/** 阴线填�?��色 */
	public static final int DEFAULT_NEGATIVE_STICK_FILL_COLOR = Color.GREEN;
	
	/** 十字线颜色 */
	public static final int DEFAULT_CROSS_STICK_COLOR = DEFAULT_POSITIVE_STICK_BORDER_COLOR;

	////////////属�?列表/////////////////

	/** 阳线颜色 */
	private int positiveStickBorderColor = DEFAULT_POSITIVE_STICK_BORDER_COLOR ;

	/** 阳线填�?��色 */
	private int positiveStickFillColor = DEFAULT_POSITIVE_STICK_FILL_COLOR;

	/** 阴线颜色 */
	private int negativeStickBorderColor = DEFAULT_NEGATIVE_STICK_BORDER_COLOR;

	/** 阴线填�?��色 */
	private int negativeStickFillColor = DEFAULT_NEGATIVE_STICK_FILL_COLOR;

	/** 十字线颜色 */
	private int crossStickColor = DEFAULT_CROSS_STICK_COLOR;
	
	/** 显示纬线数 */
	private int latitudeNum = DEFAULT_LATITUDE_NUM;
	
	/** 显示经线数 */
	private int longtitudeNum = DEFAULT_LONGTITUDE_NUM;
	
	/** K线数据 */
	private List<OHLCEntity> OHLCData;
	
	/** 图表中�?��蜡烛线 */
	private int maxCandleSticksNum;

	/** K线显示�?��价格 */
	private float maxPrice = 0;

	/** K线显示�?��价格 */
	private float minPrice = 0;	
	
	/////////////////�??函数///////////////

	public CandleStickChart(Context context) {
		super(context);
	}

	public CandleStickChart(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public CandleStickChart(Context context, AttributeSet attrs) {
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

		drawCandleSticks(canvas);
	}

	/**
	 * 获取X轴刻度位置,�?�??�最大1
	 * @param value
	 * @return
	 */
	@Override
	public String getAxisXGraduate(Object value){
		float graduate = Float.valueOf(super.getAxisXGraduate(value));
		int index = (int) Math.floor(graduate*maxCandleSticksNum);
		
		if(index >= maxCandleSticksNum){
			index = maxCandleSticksNum -1;
		}else if(index < 0){
			index = 0;
		}
		
		//返回X轴值
		return String.valueOf(OHLCData.get(index).getDate());
	}
	
	public int getSelectedIndex() {
		if(null == super.getTouchPoint()){
			return 0;
		}
		float graduate = Float.valueOf(super.getAxisXGraduate(super.getTouchPoint().x));
		int index = (int) Math.floor(graduate*maxCandleSticksNum);
		
		if(index >= maxCandleSticksNum){
			index = maxCandleSticksNum -1;
		}else if(index < 0){
			index = 0;
		}
		
		return index;
	}
	
	/**
	 * 获取Y轴刻度位置,�?�??�最大1
	 * @param value
	 * @return
	 */
	@Override
	public String getAxisYGraduate(Object value){
		float graduate = Float.valueOf(super.getAxisYGraduate(value));
		return  String.valueOf((int)Math.floor(graduate * (maxPrice - minPrice) + minPrice));
	}
	
	/**
	 * 多点触控事件
	 */
	protected void drawWithFingerMove() {
	}
	
	/**
	 * 初始化X轴
	 */
	protected void initAxisX() {
		List<String> TitleX = new ArrayList<String>();
		if(null != OHLCData){
			float average = maxCandleSticksNum / longtitudeNum;
			//�?��刻度
			for (int i = 0; i < longtitudeNum; i++) {
				int index = (int) Math.floor(i * average);
				if(index > maxCandleSticksNum-1){
					index = maxCandleSticksNum-1;
				}
				//追�??�?
				TitleX.add(String.valueOf(OHLCData.get(index).getDate()).substring(4));
			}
			TitleX.add(String.valueOf(OHLCData.get(maxCandleSticksNum-1).getDate()).substring(4));
		}
		super.setAxisXTitles(TitleX);
	}
	
	/**
	 * 初始化Y轴
	 */
	protected void initAxisY() {
		List<String> TitleY = new ArrayList<String>();
		float average = (int)((maxPrice - minPrice) / latitudeNum)/10 * 10;
		//�?��刻度
		for (int i = 0; i < latitudeNum; i++) {
			String value = String.valueOf((int)Math.floor(minPrice + i * average));
			if(value.length() < super.getAxisYMaxTitleLength()){
				while(value.length() < super.getAxisYMaxTitleLength()){
					value = new String(" ") + value;
				}
			}
			TitleY.add(value);
		}
		//�?���?��值
		String value = String.valueOf((int)Math.floor(((int)maxPrice) / 10 * 10));
		if(value.length() < super.getAxisYMaxTitleLength()){
			while(value.length() < super.getAxisYMaxTitleLength()){
				value = new String(" ") + value;
			}
		}
		TitleY.add(value);

		super.setAxisYTitles(TitleY);
	}

	/**
	 * 绘制蜡烛线
	 * @param canvas
	 */
	protected void drawCandleSticks(Canvas canvas) {
		// 蜡烛棒宽度
		float stickWidth = ((super.getWidth() - super.getAxisMarginLeft()-super.getAxisMarginRight()) / maxCandleSticksNum) - 1;
		// 蜡烛棒起始绘制位置
		float stickX = super.getAxisMarginLeft() + 1;

		Paint mPaintPositive = new Paint();
		mPaintPositive.setColor(positiveStickFillColor);

		Paint mPaintNegative = new Paint();
		mPaintNegative.setColor(negativeStickFillColor);
		
		Paint mPaintCross = new Paint();
		mPaintCross.setColor(crossStickColor);

		if(null !=  OHLCData){
			for (int i = 0; i < OHLCData.size(); i++) {
				OHLCEntity ohlc = OHLCData.get(i);
				float openY = (float) ((1f - (ohlc.getOpen() - minPrice)
						/ (maxPrice - minPrice)) * (super.getHeight() - super
						.getAxisMarginBottom()) - super.getAxisMarginTop());
				float highY = (float) ((1f - (ohlc.getHigh() - minPrice)
						/ (maxPrice - minPrice)) * (super.getHeight() - super
						.getAxisMarginBottom()) - super.getAxisMarginTop());
				float lowY = (float) ((1f - (ohlc.getLow() - minPrice)
						/ (maxPrice - minPrice)) * (super.getHeight() - super
						.getAxisMarginBottom()) - super.getAxisMarginTop());
				float closeY = (float) ((1f - (ohlc.getClose() - minPrice)
						/ (maxPrice - minPrice)) * (super.getHeight() - super
						.getAxisMarginBottom()) - super.getAxisMarginTop());
	
				// �?��和生产K线中�?��线和阳线
				if (ohlc.getOpen() < ohlc.getClose()) {
				//阳线
					//根据宽度判断是否绘制立柱
					if(stickWidth >= 2f){
						canvas.drawRect(stickX, closeY, stickX + stickWidth, openY,
								mPaintPositive);
					}
					canvas.drawLine(stickX + stickWidth / 2f, highY, stickX
							+ stickWidth / 2f, lowY, mPaintPositive);
				} else if (ohlc.getOpen() > ohlc.getClose()) {
				//阴线
					//根据宽度判断是否绘制立柱
					if(stickWidth >= 2f){
						canvas.drawRect(stickX, openY, stickX + stickWidth, closeY,
								mPaintNegative);
					}
					canvas.drawLine(stickX + stickWidth / 2f, highY, stickX
							+ stickWidth / 2f, lowY, mPaintNegative);
				} else {
				//十字线
					//根据宽度判断是否绘制横线
					if(stickWidth >= 2f){
						canvas.drawLine(stickX, closeY, stickX + stickWidth, openY,
								mPaintCross);
					}
					canvas.drawLine(stickX + stickWidth / 2f, highY, stickX
							+ stickWidth / 2f, lowY, mPaintCross);
				}
	
				//X位移
				stickX = stickX + 1 + stickWidth;
			}
		}
	}
	
	//Push数据绘制K线图
	public void pushData(OHLCEntity entity){
		if(null != entity){
			//追�?��据到数据列表
			addData(entity);
			//强制重�?
			super.postInvalidate();
		}
	}
	
	public void addData(OHLCEntity entity){
		if(null != entity){
			//追�?��据
			if(null == OHLCData || 0==OHLCData.size()){
				OHLCData = new ArrayList<OHLCEntity>();
				this.minPrice = ((int)entity.getLow()) / 10 * 10;
				this.maxPrice = ((int)entity.getHigh()) / 10 * 10;
			}
			
			this.OHLCData.add(entity);
			
			if (this.minPrice > entity.getLow()){
				this.minPrice = ((int)entity.getLow()) / 10 * 10;
			}
			
			if (this.maxPrice < entity.getHigh()){
				this.maxPrice = 10 + ((int)entity.getHigh()) / 10 * 10;
			}
			
			if(OHLCData.size() > maxCandleSticksNum){
				maxCandleSticksNum = maxCandleSticksNum +1;
			}
		}
	}
	
	
	
	//////////////属�?GetterSetter/////////////////
	
	public List<OHLCEntity> getOHLCData() {
		return OHLCData;
	}

	public void setOHLCData(List<OHLCEntity> data) {
		//�?��已有数据
		if(null != OHLCData){
			OHLCData.clear();
		}
		for(OHLCEntity e :data){
			addData(e);
		}
	}
	
	public int getPositiveStickBorderColor() {
		return positiveStickBorderColor;
	}

	public void setPositiveStickBorderColor(int positiveStickBorderColor) {
		this.positiveStickBorderColor = positiveStickBorderColor;
	}

	public int getPositiveStickFillColor() {
		return positiveStickFillColor;
	}

	public void setPositiveStickFillColor(int positiveStickFillColor) {
		this.positiveStickFillColor = positiveStickFillColor;
	}

	public int getNegativeStickBorderColor() {
		return negativeStickBorderColor;
	}

	public void setNegativeStickBorderColor(int negativeStickBorderColor) {
		this.negativeStickBorderColor = negativeStickBorderColor;
	}

	public int getNegativeStickFillColor() {
		return negativeStickFillColor;
	}

	public void setNegativeStickFillColor(int negativeStickFillColor) {
		this.negativeStickFillColor = negativeStickFillColor;
	}

	public int getCrossStickColor() {
		return crossStickColor;
	}

	public void setCrossStickColor(int crossStickColor) {
		this.crossStickColor = crossStickColor;
	}

	public int getLatitudeNum() {
		return latitudeNum;
	}

	public void setLatitudeNum(int latitudeNum) {
		this.latitudeNum = latitudeNum;
	}

	public int getMaxCandleSticksNum() {
		return maxCandleSticksNum;
	}

	public void setMaxCandleSticksNum(int maxCandleSticksNum) {
		this.maxCandleSticksNum = maxCandleSticksNum;
	}

	public float getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(float maxPrice) {
		this.maxPrice = maxPrice;
	}

	public float getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(float minPrice) {
		this.minPrice = minPrice;
	}

	public int getLongtitudeNum() {
		return longtitudeNum;
	}

	public void setLongtitudeNum(int longtitudeNum) {
		this.longtitudeNum = longtitudeNum;
	}

	
	private final int NONE = 0;
	private final int ZOOM = 1;
	private final int DOWN = 2;
	
	private float olddistance = 0f;
	private float newdistance = 0f;
	
	
	private int TOUCH_MODE;
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		final float MIN_LENGTH = (super.getWidth()/40)<5?5:(super.getWidth()/50);
		
		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		// 设置拖拉模�?
		case MotionEvent.ACTION_DOWN:
			TOUCH_MODE = DOWN;
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_POINTER_UP:
			TOUCH_MODE = NONE;
			return super.onTouchEvent(event);
		// 设置多点触摸模�?
		case MotionEvent.ACTION_POINTER_DOWN:
			olddistance = spacing(event);
			if (olddistance > MIN_LENGTH) {
				TOUCH_MODE = ZOOM;
			}
			break;
		case MotionEvent.ACTION_MOVE:
			if(TOUCH_MODE == ZOOM){
				newdistance = spacing(event);
				if (newdistance > MIN_LENGTH && Math.abs(newdistance - olddistance) > MIN_LENGTH) {
					
					if(newdistance > olddistance){
						zoomIn();
					}else{
						zoomOut();
					}
					//重置距离
					olddistance = newdistance;
										
					super.postInvalidate();
					super.notifyEventAll(this);
				}
			}
			break;
		}
		return true;
	}
	
	protected void zoomIn(){
		if(maxCandleSticksNum > 10){
			maxCandleSticksNum = maxCandleSticksNum -3;
		}
	}
	
	protected void zoomOut(){
		if(maxCandleSticksNum < OHLCData.size()-1){
			maxCandleSticksNum = maxCandleSticksNum +3;
		}
	}

	// 计算移动距离
	private float spacing(MotionEvent event) {
		float x = event.getX(0) - event.getX(1);
		float y = event.getY(0) - event.getY(1);
		return FloatMath.sqrt(x * x + y * y);
	} 
	
}
