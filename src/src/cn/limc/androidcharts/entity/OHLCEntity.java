package cn.limc.androidcharts.entity;

public class OHLCEntity {
	
	/** Open Session Value */
	private double open;
	
	/** Highest Value */
	private double high;
	
	/** Lowest Value */
	private double low;
	
	/** Close Session Value */
	private double close;
	
	/** Date */
	private int date;

	public double getOpen() {
		return open;
	}

	public void setOpen(double open) {
		this.open = open;
	}

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

	public double getClose() {
		return close;
	}

	public void setClose(double close) {
		this.close = close;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public OHLCEntity(double open, double high, double low, double close,
			int date) {
		super();
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.date = date;
	}

	public OHLCEntity() {
		super();
	}
}
