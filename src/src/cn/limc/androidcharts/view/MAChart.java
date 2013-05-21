package cn.limc.androidcharts.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;

import cn.limc.androidcharts.entity.LineEntity;

public class MAChart extends GridChart {
	/** �?��显示数据 */
	private List<LineEntity> lineData;

	/** �?��点数 */
	private int maxPointNum;

	/** �?��价格 */
	private int minValue;

	/** �?��价格 */
	private int maxValue;
	
	ArrayList<HashMap<String, Object>> listItem;

	public MAChart(Context context) {
		super(context);
	}

	public MAChart(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public MAChart(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	// //////////方�?//////////////
	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// 绘制平�?��
		if (null != this.lineData) {
			drawLines(canvas);
		}
	}
	
	protected void drawLines(Canvas canvas){
		// 点线距离
		float lineLength = ((super.getWidth() - super.getAxisMarginLeft()-this.getMaxPointNum()) / this.getMaxPointNum())-1;
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
						float valueY = (float) ((1f - (value - this.getMinValue())
								/ (this.getMaxValue() - this.getMinValue())) 
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

	// //////////属�?GetterSetter//////////////

	public List<LineEntity> getLineData() {
		return lineData;
	}

	public void setLineData(List<LineEntity> lineData) {
		this.lineData = lineData;
	}

	public int getMaxPointNum() {
		return maxPointNum;
	}

	public void setMaxPointNum(int maxPointNum) {
		this.maxPointNum = maxPointNum;
	}

	public int getMinValue() {
		return minValue;
	}

	public void setMinValue(int minValue) {
		this.minValue = minValue;
	}

	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}
}
