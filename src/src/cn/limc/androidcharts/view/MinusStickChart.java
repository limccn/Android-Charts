package cn.limc.androidcharts.view;

import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;

import cn.limc.androidcharts.entity.StickEntity;

public class MinusStickChart extends StickChart {
	
	/////////////////�??函数///////////////

	public MinusStickChart(Context context) {
		super(context);
	}

	public MinusStickChart(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public MinusStickChart(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	///////////////函数方�?////////////////


	/**
	 * 绘制柱状线
	 * @param canvas
	 */
	@Override
	protected void drawSticks(Canvas canvas) {
		// 蜡烛棒宽度
		float stickWidth = ((super.getWidth() - super.getAxisMarginLeft()) / super.getMaxStickDataNum()) - 6;
		// 蜡烛棒起始绘制位置
		float stickX = super.getAxisMarginLeft() + 3;

		Paint mPaintFill = new Paint();
		mPaintFill.setStyle(Style.FILL);
		mPaintFill.setColor(super.getStickFillColor());
		
		Paint mPaintBorder = new Paint();
		mPaintBorder.setStyle(Style.STROKE);
		mPaintBorder.setStrokeWidth(2);
		mPaintBorder.setColor(super.getStickBorderColor());

		List<StickEntity> data = super.getStickData();
		
		if(null != data){
			//判断显示为方柱或显示为线条
			for (int i = 0; i < data.size(); i++) {
				StickEntity e = data.get(i);
	
				float highY = (float) ((1f - (e.getHigh() - super.minValue)
						/ (maxValue - minValue)) * (super.getHeight() - super
						.getAxisMarginBottom()) - super.getAxisMarginTop());
				float lowY = (float) ((1f - (e.getLow() - minValue)
						/ (maxValue - minValue)) * (super.getHeight() - super
						.getAxisMarginBottom()) - super.getAxisMarginTop());
	
				//绘制立柱
				canvas.drawRect(stickX, highY, stickX + stickWidth, lowY, mPaintFill);
				canvas.drawRect(stickX, highY, stickX + stickWidth, lowY, mPaintBorder);
				
				//X位移
				stickX = stickX + 6 + stickWidth;
			}
		}
	}
}
