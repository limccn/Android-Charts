package cn.limc.androidcharts.entity;

public class StickEntity {

	/** Highest Value */
	private double high;
	
	/** Lowest Value */
	private double low;
	
	/** Date */
	private int date;

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}
	
	public StickEntity(double high, double low, int date) {
		super();
		this.high = high;
		this.low = low;
		this.date = date;
	}

	public StickEntity() {
		super();
	}
}
