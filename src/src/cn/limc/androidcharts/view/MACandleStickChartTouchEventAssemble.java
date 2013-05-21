package cn.limc.androidcharts.view;

import java.util.List;

import android.graphics.Color;
import android.widget.TextView;

import cn.limc.androidcharts.entity.LineEntity;
import cn.limc.androidcharts.entity.OHLCEntity;

public class MACandleStickChartTouchEventAssemble implements ITouchEventResponse {

	private TextView tvMA5;
	private TextView tvMA10;
	private TextView tvMA25;
	private TextView tvO;
	private TextView tvH;
	private TextView tvL;
	private TextView tvC;
	private TextView tvD;
	
	private TextView tvValue;
	private TextView tvChange;
	
	
	public void notifyEvent(GridChart chart ,int index) {
		
		MACandleStickChart machart = (MACandleStickChart)chart;
		
		List<OHLCEntity> OHLCData = machart.getOHLCData();
		List<LineEntity> lineData = machart.getLineData();
		
		if(null==OHLCData||null ==lineData){
			return;
		}
		
		LineEntity ma5 = lineData.get(0);
		LineEntity ma10 = lineData.get(1);
		LineEntity ma25 = lineData.get(2);
		
		if(index >= OHLCData.size()||index <0){
			return;
			//index = OHLCData.size() -1;
		}
		
		OHLCEntity e = OHLCData.get(index);
		OHLCEntity e1;
		
		if(index >= OHLCData.size()){
			index = OHLCData.size() -1;
		}
			
		if(index > 0){
			e1 = OHLCData.get(index -1);
		}else{
			e1 = e;
		}
		double close = e1.getClose();
		
		if(null != tvO){
			tvO.setText(String.valueOf((int)(e .getOpen())));
			tvO.setTextColor(getColor(e.getOpen() , close));
		}
		if(null != tvH){
			tvH.setText(String.valueOf((int)(e .getHigh())));
			tvH.setTextColor(getColor(e.getHigh() , close));
		}
		if(null != tvL){
			tvL.setText(String.valueOf((int)(e .getLow())));
			tvL.setTextColor(getColor(e.getLow() , close));
		}
		if(null != tvC){
			tvC.setText(String.valueOf((int)(e .getClose())));
			tvC.setTextColor(getColor(e.getClose() , close));
		}
		if(null != tvD){
			String date = String.valueOf((int)(e .getDate())).substring(2);
			tvD.setText(new StringBuffer()
							.append(date.substring(0,2))
							.append("/")
							.append(date.substring(2,4))
							.append("/")
							.append(date.substring(4,6))
							.toString());
		}
		
		int change = (int)(e .getClose() - close);
		float rate = ((int)(change / close * 10000)) / 100f;
		String strRate = String.valueOf(rate) + "%";
		
						
		if(null != tvValue){
			tvValue.setText(String.valueOf((int)(e.getClose())));
			tvValue.setTextColor(getColor(e.getClose() , close));
		}
		if(null != tvChange){
			tvChange.setText(new StringBuffer()
							.append(change > 0?"+":"")
							.append(String.valueOf(change))
							.append("(")
							.append(strRate)
							.append(")")
							.toString()
							);
			tvChange.setTextColor(getColor(e.getClose() , close));
		}
		
		
		tvMA5.setText(ma5.getTitle() + "=" +String.valueOf((int)ma5.getLineData().get(index).floatValue()));
		tvMA5.setTextColor(ma5.getLineColor());
		tvMA10.setText(ma10.getTitle() + "=" +String.valueOf((int)ma10.getLineData().get(index).floatValue()));
		tvMA10.setTextColor(ma10.getLineColor());
		tvMA25.setText(ma25.getTitle() + "=" +String.valueOf((int)ma25.getLineData().get(index).floatValue()));
		tvMA25.setTextColor(ma25.getLineColor());
	}
	
	public void notifyEvent(GridChart chart) {
		
		MACandleStickChart machart = (MACandleStickChart)chart;
		
		int index = machart.getSelectedIndex();
		
		notifyEvent(chart ,index);

	}

	/**
	 * 判断并获取颜色
	 * @param value
	 * @param yesterclose
	 * @return
	 */
	private int  getColor(double value, double yesterclose){
		int color = Color.WHITE;
		if(value < yesterclose){
			color = Color.GREEN;
		}else if(value > yesterclose){
			color = Color.RED;
		}else{
			color = Color.WHITE;
		}
		return color;
	}
	
	public TextView getTvMA5() {
		return tvMA5;
	}


	public void setTvMA5(TextView tvMA5) {
		this.tvMA5 = tvMA5;
	}


	public TextView getTvMA10() {
		return tvMA10;
	}


	public void setTvMA10(TextView tvMA10) {
		this.tvMA10 = tvMA10;
	}


	public TextView getTvMA25() {
		return tvMA25;
	}


	public void setTvMA25(TextView tvMA25) {
		this.tvMA25 = tvMA25;
	}


	public TextView getTvO() {
		return tvO;
	}


	public void setTvO(TextView tvO) {
		this.tvO = tvO;
	}


	public TextView getTvH() {
		return tvH;
	}


	public void setTvH(TextView tvH) {
		this.tvH = tvH;
	}


	public TextView getTvL() {
		return tvL;
	}


	public void setTvL(TextView tvL) {
		this.tvL = tvL;
	}


	public TextView getTvC() {
		return tvC;
	}


	public void setTvC(TextView tvC) {
		this.tvC = tvC;
	}


	public TextView getTvD() {
		return tvD;
	}


	public void setTvD(TextView tvD) {
		this.tvD = tvD;
	}


	public TextView getTvValue() {
		return tvValue;
	}


	public void setTvValue(TextView tvValue) {
		this.tvValue = tvValue;
	}


	public TextView getTvChange() {
		return tvChange;
	}


	public void setTvChange(TextView tvChange) {
		this.tvChange = tvChange;
	}

}
