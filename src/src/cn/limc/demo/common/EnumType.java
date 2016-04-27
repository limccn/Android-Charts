package cn.limc.demo.common;

public class EnumType {

	/**
	 * 主题
	 *
	 */
	public enum ThemeModeType {
		THEME_DAY,
		THEME_NIGHT
	}

	/**
	 * K线数据类型
	 *
	 */
	public enum ChartDataType {
		DAY,
		WEEK,
		MONTH,
		ONE_MINUTE,
		FIVE_MINUTE,
		FIFTEEN_MINUTE,
		THIRTY_MINUTE,
		ONE_HOUR,
		TWO_HOUR,
		FOUR_HOUR
	}
	
	/**
	 * 图表类型
	 *
	 */
	public enum ChartViewType { 
		VOL,
		MACD,
		KDJ,
		RSI,
		WR,
		CCI,
		BOLL
	}
	
	/**
	 * 显示类型
	 *
	 */
	public enum DisplayType {
		ALL,
		HANDICAP,
		TICKCHART,
		DAYS2CHART,
		DAYS3CHART,
		DETAIL,
		KLINECHART
	}
	
	/**
	 * 指标类型
	 *
	 */
	public enum IndicatorType {  
		IndicatorMACD,
		IndicatorMA,
		IndicatorVMA,
		IndicatorKDJ,
		IndicatorRSI,
		IndicatorWR,
		IndicatorCCI,
		IndicatorBOLL
	}
}
