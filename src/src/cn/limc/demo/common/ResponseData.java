package cn.limc.demo.common;

import java.io.Serializable;

public class ResponseData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String hd;
	
	private ResponseBodyData bd;
	
	public String getHd() {
		return hd;
	}

	public void setHd(String hd) {
		this.hd = hd;
	}

	public ResponseBodyData getBd() {
		return bd;
	}

	public void setBd(ResponseBodyData bd) {
		this.bd = bd;
	}
}
