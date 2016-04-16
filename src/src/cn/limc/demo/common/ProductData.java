package cn.limc.demo.common;

import java.io.Serializable;

public class ProductData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 商品id */
	private String producID;
	/** 商品名 */
	private String productName;
	/** 当前价 */
	private String currentPrice;
	/** 涨跌 */
	private String changePrice;
	/** 涨跌幅 */
	private String changePercent;
	/** 买价 */
	private String buyPrice;
	/** 卖价 */
	private String sellPrice;
	/** 最高 */
	private String highPrice;
	/** 最低 */
	private String lowPrice;

	public ProductData(){
		producID = "0";
		productName = "";
		currentPrice = "0";
		changePrice = "0";
		changePercent = "0.0%";
		buyPrice = "0";
		sellPrice = "0";
		highPrice = "0";
		lowPrice = "0";
	}
	
	public String getProducID() {
		return producID;
	}
	public void setProducID(String producID) {
		this.producID = producID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
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
	public String getChangePercent() {
		return changePercent;
	}
	public void setChangePercent(String changePercent) {
		this.changePercent = changePercent;
	}
	public String getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(String buyPrice) {
		this.buyPrice = buyPrice;
	}
	public String getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(String sellPrice) {
		this.sellPrice = sellPrice;
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

}
