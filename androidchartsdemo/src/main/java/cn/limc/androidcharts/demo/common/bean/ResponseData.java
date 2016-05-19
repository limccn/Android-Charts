package cn.limc.androidcharts.demo.common.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ResponseData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Map<String, String>> kline;
	private List<Map<String, String>> tick;

	public List<Map<String, String>> getTick() {
		return tick;
	}

	public void setTick(List<Map<String, String>> tick) {
		this.tick = tick;
	}

	public List<Map<String, String>> getKline() {
		return kline;
	}

	public void setKline(List<Map<String, String>> kline) {
		this.kline = kline;
	}

}
