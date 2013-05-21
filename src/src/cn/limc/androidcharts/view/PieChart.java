/**
 * 
 */
package cn.limc.androidcharts.view;

import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

import cn.limc.androidcharts.entity.TitleValueColorEntity;

/**
 * @author limc
 * 
 */
public class PieChart extends View {

	////////////////默认属�?////////////
	
	/** 默认蛛网背景色 */
	public static final String DEFAULT_TITLE = "Pie Chart";
	
	/** 默认是否显示蛛网经线 */
	public static final boolean DEFAULT_DISPLAY_RADIUS = true;
	
	/** 默认半圆半�?*/
	public static final int DEFAULT_RADIUS_LENGTH = 80;
	
	/** 默认经线颜色 */
	public static final int DEFAULT_RADIUS_COLOR = Color.WHITE;
	
	/** 默认�?���?��色 */
	public static final int DEFAULT_CIRCLE_BORDER_COLOR = Color.WHITE;
	
	/** 默认中�?��置 */
	public static final Point DEFAULT_POSITION = new Point(0,0);
	
	// ///////////////属�?/////////////////

	/** 图表数据  */
	private List<TitleValueColorEntity> data;
	
	/** 图表�?�?*/
	private String title = DEFAULT_TITLE;

	/** 绘图位置 */
	private Point position = DEFAULT_POSITION;
	
	/** �?��半�?*/
	private int radiusLength = DEFAULT_RADIUS_LENGTH;
	
	/** 经线颜色 */
	private int radiusColor = DEFAULT_RADIUS_COLOR;
	
	/** 经线颜色 */
	private int circleBorderColor = DEFAULT_CIRCLE_BORDER_COLOR;
	
	/** 显示经线 */
	private boolean displayRadius = DEFAULT_DISPLAY_RADIUS;
	

	// ////////////�??函数/////////////////

	public PieChart(Context context) {
		super(context);
	}

	public PieChart(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}


	public PieChart(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	// ////////////方�?///////////////////
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		//获得安�?高度宽度
		int rect = super.getWidth() > super.getHeight()? super.getHeight(): super.getWidth();
		
		//绘图高宽度
		radiusLength = (int)((rect / 2f) * 0.90); 
		
		position = new Point((int)(getWidth() / 2f),(int)(getHeight() / 2f));
		
		//绘制图表
		drawCircle(canvas);
		
		drawData(canvas);
	}
	
	/**
	 * 重新控件大�?
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		setMeasuredDimension(measureWidth(widthMeasureSpec),
				measureHeight(heightMeasureSpec));
	}

	private int measureWidth(int measureSpec) {
		int result = 0;
		int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);

		if (specMode == MeasureSpec.EXACTLY) {
			result = specSize;
		} else if (specMode == MeasureSpec.AT_MOST) {
			result = Math.min(result, specSize);
		}
		return result;
	}

	private int measureHeight(int measureSpec) {
		int result = 0;
		int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);

		if (specMode == MeasureSpec.EXACTLY) {
			result = specSize;
		} else if (specMode == MeasureSpec.AT_MOST) {
			result = Math.min(result, specSize);
		}
		return result;
	}

	
	/**
	 * 绘制外围�?��
	 * @param canvas
	 */
	protected void drawCircle(Canvas canvas){
	
		Paint mPaintCircleBorder =new Paint();
		mPaintCircleBorder.setColor(Color.WHITE);
		//填�?��制边�?
		mPaintCircleBorder.setStyle(Style.STROKE);
		mPaintCircleBorder.setStrokeWidth(2);
		mPaintCircleBorder.setAntiAlias(true);
		
		//绘制�?��
		canvas.drawCircle(position.x, position.y, radiusLength, mPaintCircleBorder);
	}
	
	/**
	 * 绘制数据
	 * 
	 * @param canvas
	 */
	protected void drawData(Canvas canvas) {
		if (null != data) {
			
			//获得�?数
			float sum = 0;
			for (int i = 0; i < data.size(); i++) {
				sum = sum + data.get(i).getValue();
			}
			
			Paint mPaintFill = new Paint();
			mPaintFill.setStyle(Style.FILL);
			mPaintFill.setAntiAlias(true);
			
			Paint mPaintBorder = new Paint();
			mPaintBorder.setStyle(Style.STROKE);
			mPaintBorder.setColor(radiusColor);
			mPaintBorder.setAntiAlias(true);
			
			int offset = -90;
			// 遍历每�?��数据列表
			for (int j = 0; j < data.size(); j++) {
				TitleValueColorEntity e = data.get(j);
				
				//�?��填�?��
				mPaintFill.setColor(e.getColor());

				RectF oval = new RectF(position.x - radiusLength,
									   position.y - radiusLength,
									   position.x + radiusLength,
									   position.y + radiusLength);
				//角度
				int sweep = Math.round(e.getValue() / sum * 360f);
				//绘制�?��
				canvas.drawArc(oval, offset, sweep, true, mPaintFill);
				//绘制�?��
				canvas.drawArc(oval, offset, sweep, true, mPaintBorder);
				//�?��偏移
				offset = offset + sweep;
			}
			
			float sumvalue = 0f;
			//
			for (int k = 0; k < data.size(); k++) {
				TitleValueColorEntity e = data.get(k);
				//值
				float value = e.getValue();
				//添�?��移
				sumvalue = sumvalue + value;
				//比�?
				float rate = (sumvalue - value /2)/ sum ;
				//�?��填�?��
				mPaintFill.setColor(Color.BLUE);
				
				//百�?�?
				float percentage = (int)( value / sum * 10000) / 100f;
				
				float offsetX = (float) (position.x - radiusLength * 0.5 * Math.sin(rate * -2 * Math.PI ));
				float offsetY = (float) (position.y - radiusLength * 0.5 * Math.cos(rate * -2 * Math.PI ));
				
				
				Paint mPaintFont =new Paint();
				mPaintFont.setColor(Color.LTGRAY);
				
				//绘制�?�?
				String title =e.getTitle();
				float  realx = 0;
				float  realy = 0;
				
				//重新计算坐�?
				//TODO 计算算法日后完善
				if(offsetX < position.x){
					realx = offsetX - mPaintFont.measureText(title) -5;
				}else if(offsetX > position.x){
					realx  = offsetX + 5;
				}
				
				if(offsetY > position.y){
					if(value / sum < 0.2f){
						realy = offsetY + 10;
					}else{
						realy = offsetY + 5;
					}
				}else if(offsetY < position.y){
					if(value / sum < 0.2f){
						realy = offsetY - 10;
					}else{
						realy = offsetY + 5;
					}
				}
				
								
				canvas.drawText(title ,realx , realy ,mPaintFont );
				
				canvas.drawText(String.valueOf(percentage)+ "%",realx,realy+12, mPaintFont);
				
			}
		}
	}

	///////////////Getter Setter////////////////
	
	public List<TitleValueColorEntity> getData() {
		return data;
	}

	public void setData(List<TitleValueColorEntity> data) {
		this.data = data;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public int getRadiusLength() {
		return radiusLength;
	}

	public void setRadiusLength(int radiusLength) {
		this.radiusLength = radiusLength;
	}

	public int getRadiusColor() {
		return radiusColor;
	}

	public void setRadiusColor(int radiusColor) {
		this.radiusColor = radiusColor;
	}

	public int getCircleBorderColor() {
		return circleBorderColor;
	}

	public void setCircleBorderColor(int circleBorderColor) {
		this.circleBorderColor = circleBorderColor;
	}

	public boolean isDisplayRadius() {
		return displayRadius;
	}

	public void setDisplayRadius(boolean displayRadius) {
		this.displayRadius = displayRadius;
	}

}
