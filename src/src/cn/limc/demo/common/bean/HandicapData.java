package cn.limc.demo.common.bean;

import java.io.Serializable;

public class HandicapData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 卖价 */
	private String sellPrice;
	/** 卖量 */
	private String sellCount;
	/** 买价 */
	private String buyPrice;
	/** 买量 */
	private String buyCount;
	/** 最新 */
	private String currentPrice;
	/** 涨跌 */
	private String changePrice;
	/** 最高 */
	private String highPrice;
	/** 最低 */
	private String lowPrice;
	/** 开盘 */
	private String openPrice;
	/** 总量 */
	private String openSumCount;
	/** 昨收 */
	private String closePrice;
	/** 总额 */
	private String closeSumCount;
	/** 昨结 */
	private String yesterdayClosePrice;
	/** 持货 */
	private String holdSumCount;
	
	public HandicapData(){
		sellPrice = "0";
		sellCount = "0";
		buyPrice = "0";
		buyCount = "0";
		currentPrice = "0";
		changePrice = "0";
		highPrice = "0";
		lowPrice = "0";
		openPrice = "0";
		openSumCount = "0";
		closePrice = "0";
		closeSumCount = "0";
		yesterdayClosePrice = "0";
		holdSumCount = "0";
	}
	
	public String getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(String sellPrice) {
		this.sellPrice = sellPrice;
	}
	public String getSellCount() {
		return sellCount;
	}
	public void setSellCount(String sellCount) {
		this.sellCount = sellCount;
	}
	public String getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(String buyPrice) {
		this.buyPrice = buyPrice;
	}
	public String getBuyCount() {
		return buyCount;
	}
	public void setBuyCount(String buyCount) {
		this.buyCount = buyCount;
	}
	public String getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(String currentPrice) {
		this.currentPrice = currentPrice;
	}
	public String getChangePrice() {
		return changePrice;
	}
	public void setChangePrice(String changePrice) {
		this.changePrice = changePrice;
	}
	public String getHighPrice() {
		return highPrice;
	}
	public void setHighPrice(String highPrice) {
		this.highPrice = highPrice;
	}
	public String getLowPrice() {
		return lowPrice;
	}
	public void setLowPrice(String lowPrice) {
		this.lowPrice = lowPrice;
	}
	public String getOpenPrice() {
		return openPrice;
	}
	public void setOpenPrice(String openPrice) {
		this.openPrice = openPrice;
	}
	public String getOpenSumCount() {
		return openSumCount;
	}
	public void setOpenSumCount(String openSumCount) {
		this.openSumCount = openSumCount;
	}
	public String getClosePrice() {
		return closePrice;
	}
	public void setClosePrice(String closePrice) {
		this.closePrice = closePrice;
	}
	public String getCloseSumCount() {
		return closeSumCount;
	}
	public void setCloseSumCount(String closeSumCount) {
		this.closeSumCount = closeSumCount;
	}
	public String getYesterdayClosePrice() {
		return yesterdayClosePrice;
	}
	public void setYesterdayClosePrice(String yesterdayClosePrice) {
		this.yesterdayClosePrice = yesterdayClosePrice;
	}
	public String getHoldSumCount() {
		return holdSumCount;
	}
	public void setHoldSumCount(String holdSumCount) {
		this.holdSumCount = holdSumCount;
	}

	 
}
