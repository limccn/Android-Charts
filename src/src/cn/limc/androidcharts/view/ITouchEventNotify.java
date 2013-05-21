package cn.limc.androidcharts.view;


public interface ITouchEventNotify {

	public void notifyEventAll(GridChart chart);
	
	public void addNotify(ITouchEventResponse notify);
	
	public void removeNotify(int i);
	
	public void removeAllNotify();
}
