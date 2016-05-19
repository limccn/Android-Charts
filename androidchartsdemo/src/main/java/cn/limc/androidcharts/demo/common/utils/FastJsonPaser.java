package cn.limc.androidcharts.demo.common.utils;

import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * 利用fastjson 解析json数据
 * @author zhourr_
 *
 */
public class FastJsonPaser {
	
	/**
	 * 不允许实例
	 */
	private FastJsonPaser(){
		throw new AssertionError();
	}
	
	/**
	 * 解析jsonString
	 * @param <T>
	 * @param jsonString
	 * @param bean
	 * @return
	 */
	public static <T> T parse(String jsonString, Class<T> clsBean){
		try {
			return (T) JSON.parseObject(jsonString, clsBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 解析jsonString
	 * @param jsonString
	 * @param clsT
	 * @return
	 */
	public static <T> List<T> parseList(String jsonString, Class<T> clsBean){
		try {
			return JSON.parseArray(jsonString, clsBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 对象转jsonstring
	 * @param o
	 */
	public static String objectToString(Object o){
		String result = "";
		try {
			result = JSON.toJSONString(o);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
