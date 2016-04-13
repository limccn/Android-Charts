package cn.limc.demo.common;

import java.io.Serializable;

public class OHLCVData  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String open;
	private String high;
	private String low;
	private String close;
	private String vol;
	private String date;
	private String current;
	private String change;
	private String preclose;
	
	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getHigh() {
		return high;
	}

	public void setHigh(String high) {
		this.high = high;
	}

	public String getLow() {
		return low;
	}

	public void setLow(String low) {
		this.low = low;
	}

	public String getClose() {
		return close;
	}

	public void setClose(String close) {
		this.close = close;
	}

	public String getVol() {
		return vol;
	}

	public void setVol(String vol) {
		this.vol = vol;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCurrent() {
		return current;
	}

	public void setCurrent(String current) {
		this.current = current;
	}

	public String getChange() {
		return change;
	}

	public void setChange(String change) {
		this.change = change;
	}

	public String getPreclose() {
		return preclose;
	}

	public void setPreclose(String preclose) {
		this.preclose = preclose;
	}

	public OHLCVData() {
		// TODO Auto-generated constructor stub
	}

}
