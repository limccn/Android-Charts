package cn.limc.demo.common.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class ResponseBodyData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<Map<String, String>> kqn;
	private ArrayList<Map<String, String>> ct;
	private ArrayList<Map<String, String>> ctt;
	
	public ArrayList<Map<String, String>> getKqn() {
		return kqn;
	}

	public void setKqn(ArrayList<Map<String, String>> kqn) {
		this.kqn = kqn;
	}

	public ArrayList<Map<String, String>> getCt() {
		return ct;
	}

	public void setCt(ArrayList<Map<String, String>> ct) {
		this.ct = ct;
	}

	public ArrayList<Map<String, String>> getCtt() {
		return ctt;
	}

	public void setCtt(ArrayList<Map<String, String>> ctt) {
		this.ctt = ctt;
	}

	public ResponseBodyData() {
		// TODO Auto-generated constructor stub
	}
}
