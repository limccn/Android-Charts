package cn.limc.demo.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;

import cn.limc.androidcharts.entity.DateValueEntity;
import cn.limc.androidcharts.entity.IStickEntity;
import cn.limc.androidcharts.entity.LineEntity;
import cn.limc.androidcharts.entity.ListChartData;

/**
 * @author zhourr
 *
 */
public class GroupChartData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Context mContext;
	
	private List<OHLCVData> ohlcData;
	
	/**
	 * CandleStickChart
	 */
	private ListChartData<IStickEntity> candleStickData;
	private List<LineEntity<DateValueEntity>> candleStickLinesData;
	private List<LineEntity<DateValueEntity>> candleBandData;

	/**
	 * VOLChart
	 */
	private ListChartData<IStickEntity> volData;
	
	/**
	 * macd
	 */
	private ListChartData<IStickEntity> macdData;
	
	/**
	 * kdj
	 */
	private List<LineEntity<DateValueEntity>> kdjData;
	
	/**
	 * rsi
	 */
	private List<LineEntity<DateValueEntity>> rsiData;
	
	/**
	 * wr
	 */
	private List<LineEntity<DateValueEntity>> wrData;
	
	/**
	 * wr
	 */
	private List<LineEntity<DateValueEntity>> cciData;
	
	/**
	 * boll
	 */
	private List<LineEntity<DateValueEntity>> bollData;
	
	public GroupChartData() {
		
	}
	
	public GroupChartData(List<OHLCVData> ohlcData, Context context) {
		mContext = context;
		this.ohlcData = ohlcData;
		
		int ma1 = PreferencesUtils.getInt(mContext, PreferencesUtils.MA1);
		if (ma1 == -1) {
			ma1 = 5;
			PreferencesUtils.putInt(mContext, PreferencesUtils.MA1, ma1);
		}
		int ma2 = PreferencesUtils.getInt(mContext, PreferencesUtils.MA2);
		if (ma2 == -1) {
			ma2 = 10;
			PreferencesUtils.putInt(mContext, PreferencesUtils.MA2, ma2);
		}
		int ma3 = PreferencesUtils.getInt(mContext, PreferencesUtils.MA3);
		if (ma3 == -1) {
			ma3 = 25;
			PreferencesUtils.putInt(mContext, PreferencesUtils.MA3, ma3);
		}
		
		this.candleStickData = TAComputeUtils.convertCandleStickData(ohlcData);
		this.candleStickLinesData = TAComputeUtils.convertCandleStickLinesData(ohlcData,ma1,ma2,ma3);
		this.candleBandData = TAComputeUtils.convertCandleBandData(ohlcData);
		this.volData = TAComputeUtils.convertVOLData(ohlcData);
		this.macdData = TAComputeUtils.convertMACDData(ohlcData, mContext);
		this.kdjData = TAComputeUtils.convertKDJData(ohlcData, mContext);
		this.rsiData = TAComputeUtils.convertRSIData(ohlcData, mContext);
		this.wrData = TAComputeUtils.convertWRData(ohlcData, mContext);
		this.cciData = TAComputeUtils.convertCCIData(ohlcData, mContext);
		this.bollData = TAComputeUtils.convertBOLLData(ohlcData, mContext);
	}
	
	public void updateMAData(int ma1, int ma2, int ma3){
		PreferencesUtils.putInt(mContext, PreferencesUtils.MA1, ma1);
		PreferencesUtils.putInt(mContext, PreferencesUtils.MA2, ma2);
		PreferencesUtils.putInt(mContext, PreferencesUtils.MA3, ma3);
		
		this.candleStickLinesData = TAComputeUtils.convertCandleStickLinesData(ohlcData, ma1, ma2, ma3);
	}
	
	public void updateBandData(int bollN){
		PreferencesUtils.putInt(mContext, PreferencesUtils.BOLL_N, bollN);
		
		List<LineEntity<DateValueEntity>> band = new ArrayList<LineEntity<DateValueEntity>>();
		List<List<DateValueEntity>> bollData = TAComputeUtils.computeBOLLData(ohlcData, bollN);
        
        LineEntity<DateValueEntity> LOWER = new LineEntity<DateValueEntity>();
        LOWER.setTitle("LOWER");
        LOWER.setLineColor(Color.YELLOW);
        LOWER.setLineData(bollData.get(0));
        band.add(LOWER);
        
        LineEntity<DateValueEntity> UPP = new LineEntity<DateValueEntity>();
        UPP.setTitle("L");
        UPP.setLineColor(Color.BLUE);
        UPP.setLineData(bollData.get(2));
        band.add(UPP);

        LineEntity<DateValueEntity> UPPER = new LineEntity<DateValueEntity>();
        UPPER.setTitle("UPPER");
        UPPER.setLineColor(Color.CYAN);
        UPPER.setLineData(bollData.get(1));
        band.add(UPPER);
        
		this.candleBandData = band;
	}
	
	public void updateMACDData(int macdS, int macdL, int macdM){
		PreferencesUtils.putInt(mContext, PreferencesUtils.MACD_M, macdL);
		PreferencesUtils.putInt(mContext, PreferencesUtils.MACD_S, macdM);
		PreferencesUtils.putInt(mContext, PreferencesUtils.MACD_L, macdS);
		
		this.macdData = new ListChartData<IStickEntity>(TAComputeUtils.computeMACDData(ohlcData, macdS, macdL, macdM));
	}
	
	public void updateKDJData(int kdjN) {
		PreferencesUtils.putInt(mContext, PreferencesUtils.KDJ_N, kdjN);
		
		List<LineEntity<DateValueEntity>> lines = new ArrayList<LineEntity<DateValueEntity>>();

    	List<List<DateValueEntity>> kdjData = TAComputeUtils.computeKDJData(ohlcData, kdjN);
        // 计算5日均线
        LineEntity<DateValueEntity> K = new LineEntity<DateValueEntity>();
        K.setTitle("K");
        K.setLineColor(Color.WHITE);
        K.setLineData(kdjData.get(0));
        lines.add(K);

        // 计算10日均线
        LineEntity<DateValueEntity> D = new LineEntity<DateValueEntity>();
        D.setTitle("D");
        D.setLineColor(Color.RED);
        D.setLineData(kdjData.get(1));
        lines.add(D);
        
        // 计算10日均线
        LineEntity<DateValueEntity> J = new LineEntity<DateValueEntity>();
        J.setTitle("J");
        J.setLineColor(Color.BLUE);
        J.setLineData(kdjData.get(2));
        lines.add(J);
        
		this.kdjData = lines;
	}
	
	public void updateRSIData(int rsiN1, int rsiN2) {
		PreferencesUtils.putInt(mContext, PreferencesUtils.RSI_N1, rsiN1);
		PreferencesUtils.putInt(mContext, PreferencesUtils.RSI_N2, rsiN2);
		
		List<LineEntity<DateValueEntity>> lines = new ArrayList<LineEntity<DateValueEntity>>();

        // 计算5日均线
        LineEntity<DateValueEntity> R = new LineEntity<DateValueEntity>();
        R.setTitle("R");
        R.setLineColor(Color.WHITE);
        R.setLineData(TAComputeUtils.computeRSIData(ohlcData, rsiN1));
        lines.add(R);

        // 计算10日均线
        LineEntity<DateValueEntity> S = new LineEntity<DateValueEntity>();
        S.setTitle("S");
        S.setLineColor(Color.RED);
        S.setLineData(TAComputeUtils.computeRSIData(ohlcData, rsiN2));
        lines.add(S);
        
        // 计算10日均线
        LineEntity<DateValueEntity> I = new LineEntity<DateValueEntity>();
        I.setTitle("I");
        I.setLineColor(Color.BLUE);
        I.setLineData(TAComputeUtils.computeRSIData(ohlcData, 24));
        lines.add(I);
        
		this.rsiData = lines;
	}
	
	public void updateWRData(int wrN) {
		PreferencesUtils.putInt(mContext, PreferencesUtils.WR_N, wrN);
		
		List<LineEntity<DateValueEntity>> lines = new ArrayList<LineEntity<DateValueEntity>>();

        // 计算5日均线
        LineEntity<DateValueEntity> WR = new LineEntity<DateValueEntity>();
        WR.setTitle("WR");
        WR.setLineColor(Color.WHITE);
        WR.setLineData(TAComputeUtils.computeWRData(ohlcData, wrN));
        lines.add(WR);
        
		this.wrData = lines;
	}
	
	public void updateCCIData(int cciN) {
		PreferencesUtils.putInt(mContext, PreferencesUtils.CCI_N, cciN);
		
		List<LineEntity<DateValueEntity>> lines = new ArrayList<LineEntity<DateValueEntity>>();

        // 计算5日均线
        LineEntity<DateValueEntity> CCI = new LineEntity<DateValueEntity>();
        CCI.setTitle("CCI");
        CCI.setLineColor(Color.WHITE);
        CCI.setLineData(TAComputeUtils.computeCCIData(ohlcData, cciN));
        lines.add(CCI);
        
		this.cciData = lines;
	}
	
	public void updateBOLLData(int bollN) {
		PreferencesUtils.putInt(mContext, PreferencesUtils.BOLL_N, bollN);
		
		List<LineEntity<DateValueEntity>> lines = new ArrayList<LineEntity<DateValueEntity>>();

    	List<List<DateValueEntity>> bollData = TAComputeUtils.computeBOLLData(ohlcData, bollN);
        // 计算5日均线
        LineEntity<DateValueEntity> upper = new LineEntity<DateValueEntity>();
        upper.setTitle("B");
        upper.setLineColor(Color.WHITE);
        upper.setLineData(bollData.get(0));
        lines.add(upper);

        // 计算10日均线
        LineEntity<DateValueEntity> middle = new LineEntity<DateValueEntity>();
        middle.setTitle("O");
        middle.setLineColor(Color.RED);
        middle.setLineData(bollData.get(1));
        lines.add(middle);
        
        // 计算10日均线
        LineEntity<DateValueEntity> lower = new LineEntity<DateValueEntity>();
        lower.setTitle("L");
        lower.setLineColor(Color.BLUE);
        lower.setLineData(bollData.get(2));
        lines.add(lower);
        
		this.bollData = lines;
	}
	
	public ListChartData<IStickEntity> getCandleStickData() {
		return candleStickData;
	}

	public void setCandleStickData(ListChartData<IStickEntity> candleStickData) {
		this.candleStickData = candleStickData;
	}
	
	public List<LineEntity<DateValueEntity>> getCandleStickLinesData() {
		return candleStickLinesData;
	}

	public void setCandleStickLinesData(
			List<LineEntity<DateValueEntity>> candleStickLinesData) {
		this.candleStickLinesData = candleStickLinesData;
	}

	public List<LineEntity<DateValueEntity>> getCandleBandData() {
		return candleBandData;
	}

	public void setCandleBandData(List<LineEntity<DateValueEntity>> candleBandData) {
		this.candleBandData = candleBandData;
	}

	public ListChartData<IStickEntity> getVolData() {
		return volData;
	}

	public void setVolData(ListChartData<IStickEntity> volData) {
		this.volData = volData;
	}

	public ListChartData<IStickEntity> getMacdData() {
		return macdData;
	}

	public void setMacdData(ListChartData<IStickEntity> macdData) {
		this.macdData = macdData;
	}

	public List<LineEntity<DateValueEntity>> getKdjData() {
		return kdjData;
	}

	public void setKdjData(List<LineEntity<DateValueEntity>> kdjData) {
		this.kdjData = kdjData;
	}

	public List<LineEntity<DateValueEntity>> getRsiData() {
		return rsiData;
	}

	public void setRsiData(List<LineEntity<DateValueEntity>> rsiData) {
		this.rsiData = rsiData;
	}

	public List<LineEntity<DateValueEntity>> getWrData() {
		return wrData;
	}

	public void setWrData(List<LineEntity<DateValueEntity>> wrData) {
		this.wrData = wrData;
	}

	public List<LineEntity<DateValueEntity>> getCciData() {
		return cciData;
	}

	public void setCciData(List<LineEntity<DateValueEntity>> cciData) {
		this.cciData = cciData;
	}

	public List<LineEntity<DateValueEntity>> getBollData() {
		return bollData;
	}

	public void setBollData(List<LineEntity<DateValueEntity>> bollData) {
		this.bollData = bollData;
	}
}
