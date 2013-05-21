/**
 * 
 */
package cn.limc.androidcharts.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

import cn.limc.androidcharts.entity.TitleValueEntity;

/**
 * @author limc
 * 
 */
public class SpiderWebChart extends View {

	////////////////默认属�?////////////
	
	/** 默认蛛网背景色 */
	public static final String DEFAULT_TITLE = "Spider Web Chart";
	
	/** 默认是否显示蛛网经线 */
	public static final boolean DEFAULT_DISPLAY_LONGTITUDE = true;

	/** 默认蛛网经线数 */
	public static final int DEFAULT_LONGTITUDE_NUM = 5;
	
	/** 默认蛛网经线长度 */
	public static final int DEFAULT_LONGTITUDE_LENGTH = 80;
	
	/** 默认蛛网经线颜色 */
	public static final int DEFAULT_LONGTITUDE_COLOR = Color.BLACK;
	
	/** 默认是否显示蛛网纬线 */
	public static final boolean DEFAULT_DISPLAY_LATITUDE = true;

	/** 默认蛛网显示纬线数 */
	public static final int DEFAULT_LATITUDE_NUM = 5;
	
	/** 默认蛛网纬线颜色 */
	public static final int DEFAULT_LATITUDE_COLOR = Color.BLACK;
	
	/** 默认蛛网位置 */
	public static final Point DEFAULT_POSITION = new Point(0,0);
	
	/** 默认蛛网背景色 */
	public static final int DEFAULT_BACKGROUD_COLOR = Color.GRAY;
	
	/** 数据显示默认颜色 */
	public static final int[] COLORS = {Color.RED,Color.BLUE,Color.YELLOW};
	
	// ///////////////属�?/////////////////

	/** 图表数据  */
	private List<List<TitleValueEntity>> data;
	
	/** 图表�?�?*/
	private String title = DEFAULT_TITLE;

	/** 绘图位置 */
	private Point position = DEFAULT_POSITION;
	
	/** 是否显示蛛网经线 */
	private boolean displayLongtitude =DEFAULT_DISPLAY_LONGTITUDE;

	/** 蛛网线维数 */
	private int longtitudeNum = DEFAULT_LONGTITUDE_NUM;
	
	/** 蛛网经线颜色 */
	private int longtitudeColor = DEFAULT_LONGTITUDE_COLOR;
	
	/** 蛛网图经线长度 */
	private int longtitudeLength = DEFAULT_LONGTITUDE_LENGTH;
	
	/** 是否显示蛛网纬线 */
	private boolean displayLatitude = DEFAULT_DISPLAY_LATITUDE;
	
	/** 蛛网纬线数 */
	private int latitudeNum = DEFAULT_LATITUDE_NUM;
	
	/** 蛛网纬线颜色 */
	private int latitudeColor = DEFAULT_LATITUDE_COLOR;
	
	/** 蛛网背景色 */
	private int backgroudColor = DEFAULT_BACKGROUD_COLOR;

	// ////////////�??函数/////////////////

	public SpiderWebChart(Context context) {
		super(context);
	}

	public SpiderWebChart(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}


	public SpiderWebChart(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	// ////////////方�?///////////////////
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		//获得安�?高度宽度
		int rect = super.getHeight();
		
		//绘图高宽度
		longtitudeLength = (int)((rect / 2f) * 0.8); 
		
		//绘制点
		position = new Point((int)(super.getWidth() / 2f),(int)(super.getHeight() / 2f + 0.2 * longtitudeLength));
		
		//绘制图表
		drawSpiderWeb(canvas);
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
	 * 获得在经线上的坐�?点
	 * @param pos
	 * @return
	 */
	protected List<PointF> getWebAxisPoints(float pos) {
		List<PointF> points = new ArrayList<PointF>();
		for (int i = 0; i < longtitudeNum; i++) {
			PointF pt = new PointF();
			float offsetX = (float) (position.x - longtitudeLength * pos
					* Math.sin(i * 2 * Math.PI / longtitudeNum));
			float offsetY = (float) (position.y - longtitudeLength * pos
					* Math.cos(i * 2 * Math.PI / longtitudeNum));
			pt.set(offsetX, offsetY);

			points.add(pt);
		}
		return points;
	}

	/**
	 * 获取数据对应的路�?��
	 * @param data
	 * @return
	 */
	protected List<PointF> getDataPoints(List<TitleValueEntity> data) {
		List<PointF> points = new ArrayList<PointF>();
		for (int i = 0; i < longtitudeNum; i++) {
			PointF pt = new PointF();
			float offsetX = (float) (position.x - data.get(i).getValue()
					/ 10f
					* longtitudeLength
					* Math.sin(i * 2 * Math.PI / longtitudeNum));
			float offsetY = (float) (position.y - data.get(i).getValue()
					/ 10f
					* longtitudeLength
					* Math.cos(i * 2 * Math.PI / longtitudeNum));
			pt.set(offsetX, offsetY);

			points.add(pt);
		}
		return points;
	}
	
	
	/**
	 * 绘制蛛�?
	 * @param canvas
	 */
	protected void drawSpiderWeb(Canvas canvas){
		Paint mPaintWebFill =new Paint();
		mPaintWebFill.setColor(Color.GRAY);
		mPaintWebFill.setAntiAlias(true);
		
		Paint mPaintWebBorder =new Paint();
		mPaintWebBorder.setColor(Color.WHITE);
		//填�?��制边�?
		mPaintWebBorder.setStyle(Style.STROKE);
		mPaintWebBorder.setStrokeWidth(2);
		mPaintWebBorder.setAntiAlias(true);
		
		Paint mPaintWebInnerBorder =new Paint();
		mPaintWebInnerBorder.setColor(Color.LTGRAY);
		//填�?��制边�?
		mPaintWebInnerBorder.setStyle(Style.STROKE);
		mPaintWebInnerBorder.setAntiAlias(true);
		
		Paint mPaintLine =new Paint();
		mPaintLine.setColor(Color.LTGRAY);
		
		Paint mPaintFont =new Paint();
		mPaintFont.setColor(Color.LTGRAY);
		
		Path mPath = new Path();
		List<PointF> pointList = getWebAxisPoints(1);
		
		//绘制蛛网图外围边�?��填�?
		if(null != data){
			for (int i = 0; i < pointList.size(); i++) {
				PointF pt = pointList.get(i);
				if (i == 0) {
					mPath.moveTo(pt.x, pt.y);
				} else {
					mPath.lineTo(pt.x, pt.y);
				}
				
				//绘制�?�?
				String title = data.get(0).get(i).getTitle();
				float  realx = 0;
				float  realy = 0;
				
				//重新计算坐�?
				//TODO 计算算法日后完善
				if(pt.x < position.x){
					realx = pt.x - mPaintFont.measureText(title) -5;
				}else if(pt.x > position.x){
					realx  = pt.x + 5;
				}else{
					realx  = pt.x - mPaintFont.measureText(title) / 2;
				}
				
				if(pt.y > position.y){
					realy = pt.y + 10;
				}else if(pt.y < position.y){
					realy = pt.y - 2;
				}else{
					realy = pt.y - 5;
				}
				
				canvas.drawText(title,realx,realy, mPaintFont);
			}
		}
		mPath.close();
		canvas.drawPath(mPath, mPaintWebFill);
		canvas.drawPath(mPath, mPaintWebBorder);
		
		//绘制�?��蜘蛛�?
		for(int j = 1; j <latitudeNum ;j++){
		
			Path mPathInner = new Path();
			List<PointF> pointListInner = getWebAxisPoints(j * 1f/ latitudeNum);
			
			//绘制Web图
			for (int i = 0; i < pointListInner.size(); i++) {
				PointF pt = pointListInner.get(i);
				if (i == 0) {
					mPathInner.moveTo(pt.x, pt.y);
				} else {
					mPathInner.lineTo(pt.x, pt.y);
				}
			}
			mPathInner.close();
			canvas.drawPath(mPathInner, mPaintWebInnerBorder);
		}
		
		//绘制经线
		for (int i = 0; i < pointList.size(); i++) {
			PointF pt = pointList.get(i);
			//绘制经线
			canvas.drawLine(position.x, position.y, pt.x, pt.y, mPaintLine);
		}
	}
	
	/**
	 * 绘制数据线条
	 * 
	 * @param canvas
	 */
	protected void drawData(Canvas canvas) {
		if (null != data) {
			// 遍历每�?��数据列表
			for (int j = 0; j < data.size(); j++) {
				List<TitleValueEntity> list = data.get(j);

				Paint mPaintFill = new Paint();
				mPaintFill.setColor(COLORS[j]);
				mPaintFill.setStyle(Style.FILL);
				mPaintFill.setAntiAlias(true);
				mPaintFill.setAlpha(70);
				
				Paint mPaintBorder = new Paint();
				mPaintBorder.setColor(COLORS[j]);
				mPaintBorder.setStyle(Style.STROKE);
				mPaintBorder.setStrokeWidth(2);
				mPaintBorder.setAntiAlias(true);
				
				//绘制�?��文�?
				Paint mPaintFont = new Paint();
				mPaintFont.setColor(Color.WHITE);
				
				//绘制数据点
				Paint mPaintPoint = new Paint();
				mPaintPoint.setColor(COLORS[j]);
				
				Path mPath = new Path();
				
				
				// 获取数据点列表
				List<PointF> pointList = getDataPoints(list);
				// 获取Path
				for (int i = 0; i < pointList.size(); i++) {
					PointF pt = pointList.get(i);
					if (i == 0) {
						mPath.moveTo(pt.x, pt.y);
					} else {
						mPath.lineTo(pt.x, pt.y);
					}
					canvas.drawCircle(pt.x, pt.y, 3, mPaintPoint);
				}
				mPath.close();

				canvas.drawPath(mPath, mPaintFill);
				canvas.drawPath(mPath, mPaintBorder);
			}
		}
	}
	
	///////////////Getter Setter////////////////

	public List<List<TitleValueEntity>> getData() {
		return data;
	}

	public void setData(List<List<TitleValueEntity>> data) {
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

	public boolean isDisplayLongtitude() {
		return displayLongtitude;
	}

	public void setDisplayLongtitude(boolean displayLongtitude) {
		this.displayLongtitude = displayLongtitude;
	}

	public int getLongtitudeNum() {
		return longtitudeNum;
	}

	public void setLongtitudeNum(int longtitudeNum) {
		this.longtitudeNum = longtitudeNum;
	}

	public int getLongtitudeColor() {
		return longtitudeColor;
	}

	public void setLongtitudeColor(int longtitudeColor) {
		this.longtitudeColor = longtitudeColor;
	}

	public int getLongtitudeLength() {
		return longtitudeLength;
	}

	public void setLongtitudeLength(int longtitudeLength) {
		this.longtitudeLength = longtitudeLength;
	}

	public boolean isDisplayLatitude() {
		return displayLatitude;
	}

	public void setDisplayLatitude(boolean displayLatitude) {
		this.displayLatitude = displayLatitude;
	}

	public int getLatitudeNum() {
		return latitudeNum;
	}

	public void setLatitudeNum(int latitudeNum) {
		this.latitudeNum = latitudeNum;
	}

	public int getLatitudeColor() {
		return latitudeColor;
	}

	public void setLatitudeColor(int latitudeColor) {
		this.latitudeColor = latitudeColor;
	}

	public int getBackgroudColor() {
		return backgroudColor;
	}

	public void setBackgroudColor(int backgroudColor) {
		this.backgroudColor = backgroudColor;
	}
	
	
}
