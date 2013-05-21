package cn.limc.androidcharts.view;

import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;

import cn.limc.androidcharts.entity.LineEntity;

public class MAStickChart extends StickChart {
	
	/** 是否显示全部 */
	private boolean displayAll = true;
	
	/** �?��显示数据 */
	private List<LineEntity> lineData;
		

	public MAStickChart(Context context) {
		super(context);
	}

	public MAStickChart(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public MAStickChart(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	////////////方�?//////////////
	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		//绘制平�?��
		if(null != this.lineData){
			if (0 != this.lineData.size()){
				drawLines(canvas);
			}
		}
	}
	
	protected void drawLines(Canvas canvas){
		// 点线距离
		float lineLength = ((super.getWidth() - super.getAxisMarginLeft()-super.getAxisMarginRight()) / super.getMaxStickDataNum())-1;
		// 起始位置
		float startX;
		
		//逐条输�?MA线
		for (int i = 0; i < lineData.size(); i++) {
			LineEntity line = (LineEntity)lineData.get(i);
			if(line.isDisplay()){
				Paint mPaint = new Paint();
				mPaint.setColor(line.getLineColor());
				mPaint.setAntiAlias(true);
				List<Float> lineData = line.getLineData();
				//输�?�?��线
				startX = super.getAxisMarginLeft() + lineLength / 2f;
				//定义起始点
				PointF ptFirst = null;
				if(lineData !=null){
					for(int j=0 ; j <lineData.size();j++){
						float value = lineData.get(j).floatValue();
						//获取终点Y坐�?
						float valueY = (float) ((1f - (value - super.getMinValue())
								/ (super.getMaxValue() - super.getMinValue())) 
								* (super.getHeight() - super.getAxisMarginBottom()));
						
						//绘制线条
						if (j > 0){
							canvas.drawLine(ptFirst.x,ptFirst.y,startX,valueY,mPaint);
						}
						//重置起始点
						ptFirst = new PointF(startX , valueY);
						//X位移
						startX = startX + 1 + lineLength;
					}
				}
			}
		}
	}
	
	////////////属�?GetterSetter//////////////
	public boolean isDisplayAll() {
		return displayAll;
	}

	public void setDisplayAll(boolean displayAll) {
		this.displayAll = displayAll;
	}

	public List<LineEntity> getLineData() {
		return lineData;
	}

	public void setLineData(List<LineEntity> lineData) {
		this.lineData = lineData;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		//屏蔽控件点击事件
		return true;
	}
	
	
}
