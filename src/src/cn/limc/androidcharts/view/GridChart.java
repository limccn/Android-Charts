package cn.limc.androidcharts.view;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 坐�?轴使用的View
 * 
 * @author limc
 * 
 */
public class GridChart extends View implements IViewConst, ITouchEventNotify,ITouchEventResponse {

	// ////////////默认值////////////////
	/** 默认背景色 */
	public static final int DEFAULT_BACKGROUD_COLOR = Color.BLACK;

	/** 默认X坐�?轴颜色 */
	public static final int DEFAULT_AXIS_X_COLOR = Color.RED;

	/** 默认Y坐�?轴颜色 */
	public static final int DEFAULT_AXIS_Y_COLOR = Color.RED;

	/** 默认经线颜色 */
	public static final int DEFAULT_LONGITUDE_COLOR = Color.RED;

	/** 默认纬线颜色 */
	public static final int DEFAULT_LAITUDE_COLOR = Color.RED;

	/** 默认轴线左边�?*/
	public static final float DEFAULT_AXIS_MARGIN_LEFT = 42f;

	/** 默认轴线底边�?*/
	public static final float DEFAULT_AXIS_MARGIN_BOTTOM = 16f;

	/** 默认轴线上边�?*/
	public static final float DEFAULT_AXIS_MARGIN_TOP = 5f;

	/** 默认轴线右边�?*/
	public static final float DEFAULT_AXIS_MARGIN_RIGHT = 5f;

	/** 默认经线是否显示刻度 */
	public static final boolean DEFAULT_DISPLAY_LONGTITUDE = Boolean.TRUE;

	/** 默认经线是否使用虚线 */
	public static final boolean DEFAULT_DASH_LONGTITUDE = Boolean.TRUE;

	/** 默认纬线是否显示刻度 */
	public static final boolean DEFAULT_DISPLAY_LATITUDE = Boolean.TRUE;

	/** 默认纬线是否使用虚线 */
	public static final boolean DEFAULT_DASH_LATITUDE = Boolean.TRUE;

	/** 默认是否显示X轴刻度 */
	public static final boolean DEFAULT_DISPLAY_AXIS_X_TITLE = Boolean.TRUE;

	/** 默认是否显示X轴刻度 */
	public static final boolean DEFAULT_DISPLAY_AXIS_Y_TITLE = Boolean.TRUE;

	/** 默认是否显示边�?*/
	public static final boolean DEFAULT_DISPLAY_BORDER = Boolean.TRUE;

	/** 默认是否显示边�?*/
	public static final int DEFAULT_BORDER_COLOR = Color.RED;

	/** 默认经线刻度字体颜色 **/
	private int DEFAULT_LONGTITUDE_FONT_COLOR = Color.WHITE;

	/** 默认经线刻度字体颜色 **/
	private int DEFAULT_LONGTITUDE_FONT_SIZE = 12;

	/** 默认经线刻度字体颜色 **/
	private int DEFAULT_LATITUDE_FONT_COLOR = Color.RED;;

	/** 默认经线刻度字体颜色 **/
	private int DEFAULT_LATITUDE_FONT_SIZE = 12;

	/** 默认Y轴�?��刻度�?��显示长度 */
	private int DEFAULT_AXIS_Y_MAX_TITLE_LENGTH = 5;

	/** 默认虚线效果 */
	public static final PathEffect DEFAULT_DASH_EFFECT = new DashPathEffect(
			new float[] { 3, 3, 3, 3 }, 1);

	/** 在控件被点击时?�显示十字�?�?��线 */
	public static final boolean DEFAULT_DISPLAY_CROSS_X_ON_TOUCH = true;

	/** 在控件被点击时?�显示十字�?�?��线 */
	public static final boolean DEFAULT_DISPLAY_CROSS_Y_ON_TOUCH = true;

	/**
	 * // /////////////属�?////////////////
	 * 
	 * /** 背景色
	 */
	private int backgroudColor = DEFAULT_BACKGROUD_COLOR;

	/** 坐�?轴X颜色 */
	private int axisXColor = DEFAULT_AXIS_X_COLOR;

	/** 坐�?轴Y颜色 */
	private int axisYColor = DEFAULT_AXIS_Y_COLOR;

	/** 经线颜色 */
	private int longitudeColor = DEFAULT_LONGITUDE_COLOR;

	/** 纬线颜色 */
	private int latitudeColor = DEFAULT_LAITUDE_COLOR;

	/** 轴线左边�?*/
	private float axisMarginLeft = DEFAULT_AXIS_MARGIN_LEFT;

	/** 轴线底边�?*/
	private float axisMarginBottom = DEFAULT_AXIS_MARGIN_BOTTOM;

	/** 轴线上边�?*/
	private float axisMarginTop = DEFAULT_AXIS_MARGIN_TOP;

	/** 轴线右边�?*/
	private float axisMarginRight = DEFAULT_AXIS_MARGIN_RIGHT;

	/** 经线是否显示 */
	private boolean displayAxisXTitle = DEFAULT_DISPLAY_AXIS_X_TITLE;

	/** 经线是否显示 */
	private boolean displayAxisYTitle = DEFAULT_DISPLAY_AXIS_Y_TITLE;

	/** 经线是否显示 */
	private boolean displayLongitude = DEFAULT_DISPLAY_LONGTITUDE;

	/** 经线是否使用虚线 */
	private boolean dashLongitude = DEFAULT_DASH_LONGTITUDE;

	/** 纬线是否显示 */
	private boolean displayLatitude = DEFAULT_DISPLAY_LATITUDE;

	/** 纬线是否使用虚线 */
	private boolean dashLatitude = DEFAULT_DASH_LATITUDE;

	/** 虚线效果 */
	private PathEffect dashEffect = DEFAULT_DASH_EFFECT;

	/** 显示边�?*/
	private boolean displayBorder = DEFAULT_DISPLAY_BORDER;

	/** 边�?��色 */
	private int borderColor = DEFAULT_BORDER_COLOR;

	/** 经线刻度字体颜色 **/
	private int longtitudeFontColor = DEFAULT_LONGTITUDE_FONT_COLOR;

	/** 经线刻度字体颜色 **/
	private int longtitudeFontSize = DEFAULT_LONGTITUDE_FONT_SIZE;

	/** 经线刻度字体颜色 **/
	private int latitudeFontColor = DEFAULT_LATITUDE_FONT_COLOR;

	/** 经线刻度字体颜色 **/
	private int latitudeFontSize = DEFAULT_LATITUDE_FONT_SIZE;

	/** 横轴刻度�?�?*/
	private List<String> axisXTitles;

	/** 纵轴刻度�?�?*/
	private List<String> axisYTitles;

	/** 纵轴刻度�?��字符数 */
	private int axisYMaxTitleLength = DEFAULT_AXIS_Y_MAX_TITLE_LENGTH;

	/** 在控件被点击时?�显示十字竖线 */
	private boolean displayCrossXOnTouch = DEFAULT_DISPLAY_CROSS_X_ON_TOUCH;

	/** 在控件被点击时?�显示十字横线线 */
	private boolean displayCrossYOnTouch = DEFAULT_DISPLAY_CROSS_Y_ON_TOUCH;

	/** 选中位置X坐�? */
	private float clickPostX = 0f;

	/** 选中位置X坐�? */
	private float clickPostY = 0f;

	/** 通知对象列表 */
	private List<ITouchEventResponse> notifyList;
	
	/** 当前被选中�?�� */
	private PointF touchPoint;

	// ////////////�??方�?//////////////
	public GridChart(Context context) {
		super(context);
	}

	public GridChart(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public GridChart(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	// //////////////方�?//////////////
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		// 设置背景色
		super.setBackgroundColor(backgroudColor);

		// 绘制XY轴
		drawXAxis(canvas);
		drawYAxis(canvas);

		// 绘制边�?
		if (this.displayBorder) {
			drawBorder(canvas);
		}

		// 绘制经线纬线
		if (displayLongitude || displayAxisXTitle) {
			drawAxisGridX(canvas);
		}
		if (displayLatitude || displayAxisYTitle) {
			drawAxisGridY(canvas);
		}

		// 绘制十字坐�?
		if (displayCrossXOnTouch || displayCrossYOnTouch) {
			drawWithFingerClick(canvas);
		}
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
	 * 失去焦点事件
	 */
	@Override
	protected void onFocusChanged(boolean gainFocus, int direction,
			Rect previouslyFocusedRect) {
		super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
	}

	/**
	 * 触摸事件
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		if (event.getY() > 0
				&& event.getY() < super.getBottom() - getAxisMarginBottom()
				&& event.getX() > super.getLeft() + getAxisMarginLeft()
				&& event.getX() < super.getRight()) {

			/*
			 * 判定用户是否触摸到�?���?如果是单点触摸则�?��绘制十字线 如果是2点触控则�?��K线放大
			 */
			if (event.getPointerCount() == 1) {
				// 获取点击坐�?
				clickPostX = event.getX();
				clickPostY = event.getY();
				
				PointF point = new PointF(clickPostX,clickPostY);
				touchPoint = point;
				// super.invalidate();
				super.invalidate();

				// 通知�?��其他�?联Chart
				notifyEventAll(this);

			} else if (event.getPointerCount() == 2) {
			}
		}
		return super.onTouchEvent(event);
	}

	/**
	 * 绘制半�?明文本�?
	 * 
	 * @param ptStart
	 * @param ptEnd
	 * @param content
	 * @param fontSize
	 * @param canvas
	 */

	private void drawAlphaTextBox(PointF ptStart, PointF ptEnd, String content,
			int fontSize, Canvas canvas) {

		Paint mPaintBox = new Paint();
		mPaintBox.setColor(Color.BLACK);
		mPaintBox.setAlpha(80);
		

		Paint mPaintBoxLine = new Paint();
		mPaintBoxLine.setColor(Color.CYAN);
		mPaintBoxLine.setAntiAlias(true);

		// 绘制矩形填�?
		canvas.drawRoundRect(new RectF(ptStart.x, ptStart.y, ptEnd.x, ptEnd.y),
				20.0f, 20.0f, mPaintBox);

		// 绘制矩形�?
		canvas.drawLine(ptStart.x, ptStart.y, ptStart.x, ptEnd.y,mPaintBoxLine);
		canvas.drawLine(ptStart.x, ptEnd.y, ptEnd.x, ptEnd.y, mPaintBoxLine);
		canvas.drawLine(ptEnd.x, ptEnd.y, ptEnd.x, ptStart.y, mPaintBoxLine);
		canvas.drawLine(ptEnd.x, ptStart.y, ptStart.x, ptStart.y,mPaintBoxLine);

		// 绘制�?��
		canvas.drawText(content, ptStart.x, ptEnd.y, mPaintBoxLine);
	}

	/**
	 * 获取X轴刻度�??,�?�??�最大1
	 * 
	 * @param value
	 * @return
	 */
	public String getAxisXGraduate(Object value) {

		float length = super.getWidth() - axisMarginLeft - 2 * axisMarginRight;
		float valueLength = ((Float) value).floatValue() - axisMarginLeft
				- axisMarginRight;

		return String.valueOf(valueLength / length);
	}

	/**
	 * 获取Y轴刻度�??,�?�??�最大1
	 * 
	 * @param value
	 * @return
	 */
	public String getAxisYGraduate(Object value) {

		float length = super.getHeight() - axisMarginBottom - 2 * axisMarginTop;
		float valueLength = length
				- (((Float) value).floatValue() - axisMarginTop);

		return String.valueOf(valueLength / length);
	}

	/**
	 * 单点击事件
	 */
	protected void drawWithFingerClick(Canvas canvas) {
		Paint mPaint = new Paint();
		mPaint.setColor(Color.CYAN);

		// 水平线长度
		float lineHLength = getWidth() - 2f;
		// 垂直线高度
		float lineVLength = getHeight() - 2f;

		// 绘制横纵线
		if (isDisplayAxisXTitle()) {
			lineVLength = lineVLength - axisMarginBottom;

			if (clickPostX > 0 && clickPostY > 0) {
				// 绘制X轴�?���?
				if (displayCrossXOnTouch) {
					// TODO �?���?��小控制�?�?��
					PointF BoxVS = new PointF(clickPostX - longtitudeFontSize
							* 5f / 2f, lineVLength + 2f);
					PointF BoxVE = new PointF(clickPostX + longtitudeFontSize
							* 5f / 2f, lineVLength + axisMarginBottom - 1f);

					// 绘制�?���?
					drawAlphaTextBox(BoxVS, BoxVE,
							getAxisXGraduate(clickPostX), longtitudeFontSize,
							canvas);
				}
			}
		}

		if (isDisplayAxisYTitle()) {
			lineHLength = lineHLength - getAxisMarginLeft();

			if (clickPostX > 0 && clickPostY > 0) {
				// 绘制Y轴�?���?
				if (displayCrossYOnTouch) {
					PointF BoxHS = new PointF(1f, clickPostY - latitudeFontSize
							/ 2f);
					PointF BoxHE = new PointF(axisMarginLeft, clickPostY
							+ latitudeFontSize / 2f);

					// 绘制�?���?
					drawAlphaTextBox(BoxHS, BoxHE,
							getAxisYGraduate(clickPostY), latitudeFontSize,
							canvas);
				}
			}
		}

		if (clickPostX > 0 && clickPostY > 0) {
			// 显示纵线
			if (displayCrossXOnTouch) {
				canvas
						.drawLine(clickPostX, 1f, clickPostX, lineVLength,
								mPaint);
			}

			// 显示横线
			if (displayCrossYOnTouch) {
				canvas.drawLine(axisMarginLeft, clickPostY, axisMarginLeft
						+ lineHLength, clickPostY, mPaint);
			}
		}
	}

	/**
	 * 绘制边�?
	 * 
	 * @param canvas
	 */
	protected void drawBorder(Canvas canvas) {
		float width = super.getWidth() - 2;
		float height = super.getHeight() - 2;

		Paint mPaint = new Paint();
		mPaint.setColor(borderColor);

		// 绘制边�?��
		canvas.drawLine(1f, 1f, 1f + width, 1f, mPaint);
		canvas.drawLine(1f + width, 1f, 1f + width, 1f + height, mPaint);
		canvas.drawLine(1f + width, 1f + height, 1f, 1f + height, mPaint);
		canvas.drawLine(1f, 1f + height, 1f, 1f, mPaint);
	}

	/**
	 * 绘制X轴
	 * 
	 * @param canvas
	 */
	protected void drawXAxis(Canvas canvas) {

		float length = super.getWidth();
		float postY = super.getHeight() - axisMarginBottom - 1;

		Paint mPaint = new Paint();
		mPaint.setColor(axisXColor);

		canvas.drawLine(0f, postY, length, postY, mPaint);

	}

	/**
	 * 绘制Y轴
	 * 
	 * @param canvas
	 */
	protected void drawYAxis(Canvas canvas) {

		float length = super.getHeight() - axisMarginBottom;
		float postX = axisMarginLeft + 1;

		Paint mPaint = new Paint();
		mPaint.setColor(axisXColor);

		canvas.drawLine(postX, 0f, postX, length, mPaint);
	}

	/**
	 * 绘制经线
	 * 
	 * @param canvas
	 */
	protected void drawAxisGridX(Canvas canvas) {

		if (null != axisXTitles) {

			int counts = axisXTitles.size();
			float length = super.getHeight() - axisMarginBottom;
			Paint mPaintLine = new Paint();
			mPaintLine.setColor(longitudeColor);
			if (dashLongitude) {
				mPaintLine.setPathEffect(dashEffect);
			}

			// �?��Paint
			Paint mPaintFont = new Paint();
			mPaintFont.setColor(longtitudeFontColor);
			mPaintFont.setTextSize(longtitudeFontSize);
			mPaintFont.setAntiAlias(true);
			if (counts > 1) {
				float postOffset = (super.getWidth() - axisMarginLeft - 2 * axisMarginRight)
						/ (counts - 1);
				float offset = axisMarginLeft + axisMarginRight;
				for (int i = 0; i <= counts; i++) {
					// 绘制线条
					if (displayLongitude) {
						canvas.drawLine(offset + i * postOffset, 0f, offset + i
								* postOffset, length, mPaintLine);
					}
					// 绘制刻度
					if (displayAxisXTitle) {
						if (i < counts && i > 0) {
							canvas.drawText(axisXTitles.get(i), offset + i
									* postOffset
									- (axisXTitles.get(i).length())
									* longtitudeFontSize / 2f, super
									.getHeight()
									- axisMarginBottom + longtitudeFontSize,
									mPaintFont);
						} else if (0 == i) {
							canvas.drawText(axisXTitles.get(i),
									this.axisMarginLeft + 2f, super.getHeight()
											- axisMarginBottom
											+ longtitudeFontSize, mPaintFont);
						}
					}
				}
			}
		}
	}

	/**
	 * 绘制纬线
	 * 
	 * @param canvas
	 */
	protected void drawAxisGridY(Canvas canvas) {
		if (null != axisYTitles) {
			int counts = axisYTitles.size();
			float length = super.getWidth() - axisMarginLeft;
			// 线条Paint
			Paint mPaintLine = new Paint();
			mPaintLine.setColor(latitudeColor);
			if (dashLatitude) {
				mPaintLine.setPathEffect(dashEffect);
			}
			// �?��Paint
			Paint mPaintFont = new Paint();
			mPaintFont.setColor(latitudeFontColor);
			mPaintFont.setTextSize(latitudeFontSize);
			mPaintFont.setAntiAlias(true);

			// 绘制线条坐�?轴
			if (counts > 1) {
				float postOffset = (super.getHeight() - axisMarginBottom - 2 * axisMarginTop)
						/ (counts - 1);
				float offset = super.getHeight() - axisMarginBottom
						- axisMarginTop;
				for (int i = 0; i <= counts; i++) {
					// 绘制线条
					if (displayLatitude) {
						canvas.drawLine(axisMarginLeft,
								offset - i * postOffset, axisMarginLeft
										+ length, offset - i * postOffset,
								mPaintLine);
					}
					// 绘制刻度
					if (displayAxisYTitle) {
						if (i < counts && i > 0) {
							canvas.drawText(axisYTitles.get(i), 0f, offset - i
									* postOffset + latitudeFontSize / 2f,
									mPaintFont);
						} else if (0 == i) {
							canvas.drawText(axisYTitles.get(i), 0f, super
									.getHeight()
									- this.axisMarginBottom - 2f, mPaintFont);
						}
					}
				}
			}
		}
	}
	
	protected void zoomIn(){
		
	}
	
	protected void zoomOut(){
		
	}

	// 获得来自其他图表�??知
	public void notifyEvent(GridChart chart) {
		PointF point = chart.getTouchPoint();
		//如果没有�?中点
		if(null != point){
			// 获取点击坐�?
			clickPostX = point.x;
			clickPostY = point.y;
		}
		//设置当前控件�?��摸点
		touchPoint = new PointF(clickPostX , clickPostY);	
		super.invalidate();
	}

	public void addNotify(ITouchEventResponse notify) {
		if (null == notifyList) {
			notifyList = new ArrayList<ITouchEventResponse>();
		}
		notifyList.add(notify);
	}

	public void removeNotify(int i) {
		if (null != notifyList && notifyList.size() > i) {
			notifyList.remove(i);
		}
	}

	public void removeAllNotify() {
		if (null != notifyList) {
			notifyList.clear();
		}
	}

	public void notifyEventAll(GridChart chart) {
		if (null != notifyList) {
			for (int i = 0; i < notifyList.size(); i++) {
				ITouchEventResponse ichart = notifyList.get(i);
				ichart.notifyEvent(chart);
			}
		}
	}

	// /////////属�?getter与setter方�?////////////////

	public int getBackgroudColor() {
		return backgroudColor;
	}

	public void setBackgroudColor(int backgroudColor) {
		this.backgroudColor = backgroudColor;
	}

	public int getAxisXColor() {
		return axisXColor;
	}

	public void setAxisXColor(int axisXColor) {
		this.axisXColor = axisXColor;
	}

	public int getAxisYColor() {
		return axisYColor;
	}

	public void setAxisYColor(int axisYColor) {
		this.axisYColor = axisYColor;
	}

	public int getLongitudeColor() {
		return longitudeColor;
	}

	public void setLongitudeColor(int longitudeColor) {
		this.longitudeColor = longitudeColor;
	}

	public int getLatitudeColor() {
		return latitudeColor;
	}

	public void setLatitudeColor(int latitudeColor) {
		this.latitudeColor = latitudeColor;
	}

	public float getAxisMarginLeft() {
		return axisMarginLeft;
	}

	public void setAxisMarginLeft(float axisMarginLeft) {
		this.axisMarginLeft = axisMarginLeft;

		// 如果左边距为0?��?不显示Y坐�?轴
		if (0f == axisMarginLeft) {
			this.displayAxisYTitle = Boolean.FALSE;
		}
	}

	public float getAxisMarginBottom() {
		return axisMarginBottom;
	}

	public void setAxisMarginBottom(float axisMarginBottom) {
		this.axisMarginBottom = axisMarginBottom;

		// 如果下边距为0?��?不显示X坐�?轴
		if (0f == axisMarginBottom) {
			this.displayAxisXTitle = Boolean.FALSE;
		}
	}

	public float getAxisMarginTop() {
		return axisMarginTop;
	}

	public void setAxisMarginTop(float axisMarginTop) {
		this.axisMarginTop = axisMarginTop;
	}

	public float getAxisMarginRight() {
		return axisMarginRight;
	}

	public void setAxisMarginRight(float axisMarginRight) {
		this.axisMarginRight = axisMarginRight;
	}

	public List<String> getAxisXTitles() {
		return axisXTitles;
	}

	public void setAxisXTitles(List<String> axisXTitles) {
		this.axisXTitles = axisXTitles;
	}

	public List<String> getAxisYTitles() {
		return axisYTitles;
	}

	public void setAxisYTitles(List<String> axisYTitles) {
		this.axisYTitles = axisYTitles;
	}

	public boolean isDisplayLongitude() {
		return displayLongitude;
	}

	public void setDisplayLongitude(boolean displayLongitude) {
		this.displayLongitude = displayLongitude;
	}

	public boolean isDashLongitude() {
		return dashLongitude;
	}

	public void setDashLongitude(boolean dashLongitude) {
		this.dashLongitude = dashLongitude;
	}

	public boolean isDisplayLatitude() {
		return displayLatitude;
	}

	public void setDisplayLatitude(boolean displayLatitude) {
		this.displayLatitude = displayLatitude;
	}

	public boolean isDashLatitude() {
		return dashLatitude;
	}

	public void setDashLatitude(boolean dashLatitude) {
		this.dashLatitude = dashLatitude;
	}

	public PathEffect getDashEffect() {
		return dashEffect;
	}

	public void setDashEffect(PathEffect dashEffect) {
		this.dashEffect = dashEffect;
	}

	public boolean isDisplayAxisXTitle() {
		return displayAxisXTitle;
	}

	public void setDisplayAxisXTitle(boolean displayAxisXTitle) {
		this.displayAxisXTitle = displayAxisXTitle;

		// 如果不显示X轴刻度,则底边边距为0
		if (false == displayAxisXTitle) {
			this.axisMarginBottom = 0;
		}
	}

	public boolean isDisplayAxisYTitle() {
		return displayAxisYTitle;
	}

	public void setDisplayAxisYTitle(boolean displayAxisYTitle) {
		this.displayAxisYTitle = displayAxisYTitle;

		// 如果不显示Y轴刻度,则左边边距为0
		if (false == displayAxisYTitle) {
			this.axisMarginLeft = 0;
		}
	}

	public boolean isDisplayBorder() {
		return displayBorder;
	}

	public void setDisplayBorder(boolean displayBorder) {
		this.displayBorder = displayBorder;
	}

	public int getBorderColor() {
		return borderColor;
	}

	public void setBorderColor(int borderColor) {
		this.borderColor = borderColor;
	}

	public int getLongtitudeFontColor() {
		return longtitudeFontColor;
	}

	public void setLongtitudeFontColor(int longtitudeFontColor) {
		this.longtitudeFontColor = longtitudeFontColor;
	}

	public int getLongtitudeFontSize() {
		return longtitudeFontSize;
	}

	public void setLongtitudeFontSize(int longtitudeFontSize) {
		this.longtitudeFontSize = longtitudeFontSize;
	}

	public int getLatitudeFontColor() {
		return latitudeFontColor;
	}

	public void setLatitudeFontColor(int latitudeFontColor) {
		this.latitudeFontColor = latitudeFontColor;
	}

	public int getLatitudeFontSize() {
		return latitudeFontSize;
	}

	public void setLatitudeFontSize(int latitudeFontSize) {
		this.latitudeFontSize = latitudeFontSize;
	}

	public int getAxisYMaxTitleLength() {
		return axisYMaxTitleLength;
	}

	public void setAxisYMaxTitleLength(int axisYMaxTitleLength) {
		this.axisYMaxTitleLength = axisYMaxTitleLength;
	}

	public boolean isDisplayCrossXOnTouch() {
		return displayCrossXOnTouch;
	}

	public void setDisplayCrossXOnTouch(boolean displayCrossXOnTouch) {
		this.displayCrossXOnTouch = displayCrossXOnTouch;
	}

	public boolean isDisplayCrossYOnTouch() {
		return displayCrossYOnTouch;
	}

	public void setDisplayCrossYOnTouch(boolean displayCrossYOnTouch) {
		this.displayCrossYOnTouch = displayCrossYOnTouch;
	}

	public PointF getTouchPoint() {
		return touchPoint;
	}

	public void setTouchPoint(PointF touchPoint) {
		this.touchPoint = touchPoint;
	}
}
