package cn.limc.androidcharts.view;

import android.widget.ListView;

public class MAChartTouchEventAssemble implements ITouchEventResponse {

	private ListView listview;
	
	public void notifyEvent(GridChart chart) {

	}
	
	public ListView getListview() {
		return listview;
	}
	
	public void setListview(ListView listview) {
		this.listview = listview;
	}

}
