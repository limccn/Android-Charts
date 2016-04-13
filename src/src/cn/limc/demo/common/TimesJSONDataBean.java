package cn.limc.demo.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class TimesJSONDataBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ArrayList<Map<String, String>> timedivisionline;
	
	

	public TimesJSONDataBean() {
		// TODO Auto-generated constructor stub
	}



	public ArrayList<Map<String, String>> getTimedivisionline() {
		return timedivisionline;
	}



	public void setTimedivisionline(ArrayList<Map<String, String>> timedivisionline) {
		this.timedivisionline = timedivisionline;
	}



}
