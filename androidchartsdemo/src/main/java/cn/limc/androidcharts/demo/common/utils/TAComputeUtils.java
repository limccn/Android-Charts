package cn.limc.androidcharts.demo.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import cn.limc.androidcharts.entity.ColoredStickEntity;
import cn.limc.androidcharts.entity.DateValueEntity;
import cn.limc.androidcharts.entity.IStickEntity;
import cn.limc.androidcharts.entity.LineEntity;
import cn.limc.androidcharts.entity.ListChartData;
import cn.limc.androidcharts.entity.MACDEntity;
import cn.limc.androidcharts.entity.OHLCEntity;
import cn.limc.androidcharts.demo.common.bean.OHLCVData;

import com.tictactec.ta.lib.Core;
import com.tictactec.ta.lib.MAType;
import com.tictactec.ta.lib.MInteger;
import com.tictactec.ta.lib.RetCode;

/**
 * TA 计算工具类
 * @author zhourr
 *
 */
@SuppressLint("SimpleDateFormat")
public class TAComputeUtils {

	public static String[] LINE_COLORS = {"#ed4d4d", "#52ba27", "#00a0e9"};
	
	public static String SourceDateformatString = "yyyy-MM-dd HH:mm:ss";
	public static String ToDateformatString = "yyyyMMdd";
	
	/**
	 * 不允许实例
	 */
	public TAComputeUtils() {
		throw new AssertionError();
	}

	/**
     * 使用ta计算macd
     * @param arrayData
     * @return
     */
    public static List<IStickEntity> computeMACDData(List<OHLCVData> arrayData,int macdS, int macdL, int macdM) {
    	List<IStickEntity> macd = new ArrayList<IStickEntity>();
		
    	double[] inReal = new double[arrayData.size()];
    	for (int i = 0; i < inReal.length; i++) {
    		inReal[i] = arrayData.get(i).getClose();
		}
		
		double[] outMACD = new double[arrayData.size()];
		double[] outMACDSignal = new double[arrayData.size()];
		double[] outMACDHist = new double[arrayData.size()];
		
		MInteger outBegIdx = new MInteger();
		outBegIdx.value = 0;
		MInteger outNBElement = new MInteger();
		outNBElement.value = 0;
		
		Core core = new Core();
    	RetCode retCode = core.macd(0, arrayData.size() - 1, inReal, macdS, macdL, macdM, outBegIdx, outNBElement, outMACD, outMACDSignal, outMACDHist);
    	if (RetCode.Success == retCode) {
    		SimpleDateFormat df = new SimpleDateFormat(SourceDateformatString);
            SimpleDateFormat dt = new SimpleDateFormat(ToDateformatString);
    		for (int i = 0; i < inReal.length; i++) {
    			OHLCVData data = arrayData.get(i);
    			try {

					int dateValue = Integer.parseInt(dt.format(df.parse(data.getDate())));
					if (i<outBegIdx.value){
						// macd == 0
						MACDEntity macdEntity = new MACDEntity(Float.MAX_VALUE, Float.MAX_VALUE, 0, dateValue);
						macd.add(macdEntity);
					}else {
						int index = i-outBegIdx.value;
						MACDEntity macdEntity = new MACDEntity(outMACDSignal[index], outMACD[index], outMACDHist[index] * 2, dateValue);
						macd.add(macdEntity);
					}
    			} catch (NumberFormatException e) {
					e.printStackTrace();
					continue;
				} catch (ParseException e) {
					e.printStackTrace();
					continue;
				}
			}
		}
    	return macd;
	}
    
	/**
     * 使用ta计算kdj
     * @param arrayData
     * @return
     */
	public static List<List<DateValueEntity>> computeKDJData(List<OHLCVData> arrayData, int kdjN){
		List<DateValueEntity> dv1 = new ArrayList<DateValueEntity>();
		List<DateValueEntity> dv2 = new ArrayList<DateValueEntity>();
		List<DateValueEntity> dv3 = new ArrayList<DateValueEntity>();
    	
    	double[] inHigh = new double[arrayData.size()];
    	for (int i = 0; i < inHigh.length; i++) {
    		inHigh[i] = arrayData.get(i).getHigh();
		}
    	
    	double[] inLow = new double[arrayData.size()];
    	for (int i = 0; i < inLow.length; i++) {
    		inLow[i] = arrayData.get(i).getLow();
		}
    	
    	double[] inClose = new double[arrayData.size()];
    	for (int i = 0; i < inClose.length; i++) {
    		inClose[i] = arrayData.get(i).getClose();
		}
    	
    	MInteger outBegIdx = new MInteger();
		outBegIdx.value = 0;
		MInteger outNBElement = new MInteger();
		outNBElement.value = 0;
		
		double[] outSlowK = new double[arrayData.size()];
		double[] outSlowD = new double[arrayData.size()];
		
    	Core core = new Core();
    	RetCode retCode = core.stoch(0, arrayData.size()-1, inHigh, inLow, inClose, kdjN, 3, MAType.Ema, 3, MAType.Ema, outBegIdx, outNBElement, outSlowK, outSlowD);
    	if (RetCode.Success == retCode) {
    		SimpleDateFormat df = new SimpleDateFormat(SourceDateformatString);
            SimpleDateFormat dt = new SimpleDateFormat(ToDateformatString);
    		for (int i = 0; i < arrayData.size(); i++) {
    			OHLCVData data = arrayData.get(i);
				try {
					int dateValue = Integer.parseInt(dt.format(df.parse(data.getDate())));
					if (i<outBegIdx.value){
						dv1.add(new DateValueEntity(0, dateValue));
						dv2.add(new DateValueEntity(0, dateValue));
						dv3.add(new DateValueEntity(0, dateValue));
					}else {
						int index = i-outBegIdx.value;
						dv1.add(new DateValueEntity((float) outSlowK[index], dateValue));
						dv2.add(new DateValueEntity((float) outSlowD[index], dateValue));
						double slowKLine3k2d = 3 * outSlowK[index] - 2 * outSlowD[index];
						dv3.add(new DateValueEntity((float) slowKLine3k2d, dateValue));
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
					continue;
				} catch (ParseException e) {
					e.printStackTrace();
					continue;
				}
			}
    	}
    	
    	List<List<DateValueEntity>> kdjData = new ArrayList<List<DateValueEntity>>();
    	kdjData.add(dv1);
    	kdjData.add(dv2);
    	kdjData.add(dv3);
    	
    	return kdjData;
    }
    
	/**
     * 使用ta计算rsi
     * @param arrayData
     * @return
     */
    public static List<DateValueEntity> computeRSIData(List<OHLCVData> arrayData, int optInTimePeriod){
    	List<DateValueEntity> dv = new ArrayList<DateValueEntity>();
    	
    	double[] inReal = new double[arrayData.size()];
    	for (int i = 0; i < inReal.length; i++) {
    		inReal[i] = arrayData.get(i).getClose();
		}
    	
    	MInteger outBegIdx = new MInteger();
		outBegIdx.value = 0;
		MInteger outNBElement = new MInteger();
		outNBElement.value = 0;
		
		double[] outReal = new double[arrayData.size()];
		
    	Core core = new Core();
    	RetCode retCode = core.rsi(0, arrayData.size() - 1, inReal, optInTimePeriod, outBegIdx, outNBElement, outReal);
    	if (RetCode.Success == retCode) {
    		SimpleDateFormat df = new SimpleDateFormat(SourceDateformatString);
            SimpleDateFormat dt = new SimpleDateFormat(ToDateformatString);
    		for (int i = 0; i < arrayData.size(); i++) {
    			OHLCVData data = arrayData.get(i);
				try {

					if (i<outBegIdx.value){
						dv.add(new DateValueEntity(0, Integer.parseInt(dt.format(df.parse(data.getDate())))));
					}else {
						int index = i-outBegIdx.value;
						dv.add(new DateValueEntity((float) outReal[index], Integer.parseInt(dt.format(df.parse(data.getDate())))));

					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
					continue;
				} catch (ParseException e) {
					e.printStackTrace();
					continue;
				}
			}
    	}
    	
    	return dv;
    }
    
    /**
     * 使用ta计算wr
     * @param arrayData
     * @return
     */
    public static List<DateValueEntity> computeWRData(List<OHLCVData> arrayData, int wrN){
    	List<DateValueEntity> dv = new ArrayList<DateValueEntity>();
    	
    	double[] inHigh = new double[arrayData.size()];
    	for (int i = 0; i < inHigh.length; i++) {
    		inHigh[i] = arrayData.get(i).getHigh();
		}
    	
    	double[] inLow = new double[arrayData.size()];
    	for (int i = 0; i < inLow.length; i++) {
    		inLow[i] = arrayData.get(i).getLow();
		}
    	
    	double[] inClose = new double[arrayData.size()];
    	for (int i = 0; i < inClose.length; i++) {
    		inClose[i] = arrayData.get(i).getClose();
		}
    	
    	MInteger outBegIdx = new MInteger();
		outBegIdx.value = 0;
		MInteger outNBElement = new MInteger();
		outNBElement.value = 0;
		
		double[] outReal = new double[arrayData.size()];
		
    	Core core = new Core();
    	RetCode retCode = core.willR(0, arrayData.size() - 1, inHigh, inLow, inClose, wrN, outBegIdx, outNBElement, outReal);
    	if (RetCode.Success == retCode) {
    		SimpleDateFormat df = new SimpleDateFormat(SourceDateformatString);
            SimpleDateFormat dt = new SimpleDateFormat(ToDateformatString);
    		for (int i = 0; i < arrayData.size(); i++) {
    			OHLCVData data = arrayData.get(i);
				try {
					if (i<outBegIdx.value){
						//101 as nonedisplay value
						dv.add(new DateValueEntity(101, Integer.parseInt(dt.format(df.parse(data.getDate())))));
					}else {
						int index = i-outBegIdx.value;
						dv.add(new DateValueEntity((float) outReal[index] * -1, Integer.parseInt(dt.format(df.parse(data.getDate())))));
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
					continue;
				} catch (ParseException e) {
					e.printStackTrace();
					continue;
				}
			}
    	}
    	
    	return dv;
    }
    
    /**
     * 使用ta计算cci
     * @param arrayData
     * @return
     */
    public static List<DateValueEntity> computeCCIData(List<OHLCVData> arrayData, int cciN){
    	List<DateValueEntity> dv = new ArrayList<DateValueEntity>();
    	
    	double[] inHigh = new double[arrayData.size()];
    	for (int i = 0; i < inHigh.length; i++) {
    		inHigh[i] = arrayData.get(i).getHigh();
		}
    	
    	double[] inLow = new double[arrayData.size()];
    	for (int i = 0; i < inLow.length; i++) {
    		inLow[i] = arrayData.get(i).getLow();
		}
    	
    	double[] inClose = new double[arrayData.size()];
    	for (int i = 0; i < inClose.length; i++) {
    		inClose[i] = arrayData.get(i).getClose();
		}
    	
    	MInteger outBegIdx = new MInteger();
		outBegIdx.value = 0;
		MInteger outNBElement = new MInteger();
		outNBElement.value = 0;
		
		double[] outReal = new double[arrayData.size()];
		
    	Core core = new Core();
    	RetCode retCode = core.cci(0, arrayData.size() - 1, inHigh, inLow, inClose, cciN, outBegIdx, outNBElement, outReal);
    	if (RetCode.Success == retCode) {
    		SimpleDateFormat df = new SimpleDateFormat(SourceDateformatString);
            SimpleDateFormat dt = new SimpleDateFormat(ToDateformatString);
    		for (int i = 0; i < arrayData.size(); i++) {
    			OHLCVData data = arrayData.get(i);
				try {
					if (i<outBegIdx.value){
						dv.add(new DateValueEntity(Float.MAX_VALUE, Integer.parseInt(dt.format(df.parse(data.getDate())))));
					}else {
						int index = i-outBegIdx.value;
						dv.add(new DateValueEntity((float) outReal[index], Integer.parseInt(dt.format(df.parse(data.getDate())))));
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
					continue;
				} catch (ParseException e) {
					e.printStackTrace();
					continue;
				}
			}
    	}
    	
    	return dv;
    }
    
    /**
     * 使用ta计算boll
     * @param arrayData
     * @return
     */
	public static List<List<DateValueEntity>> computeBOLLData(List<OHLCVData> arrayData, int bollN){
    	List<DateValueEntity> dv1 = new ArrayList<DateValueEntity>();
    	List<DateValueEntity> dv2 = new ArrayList<DateValueEntity>();
    	List<DateValueEntity> dv3 = new ArrayList<DateValueEntity>();
    	
    	double[] inReal = new double[arrayData.size()];
    	for (int i = 0; i < inReal.length; i++) {
    		inReal[i] = arrayData.get(i).getClose();
		}
    	
    	MInteger outBegIdx = new MInteger();
		outBegIdx.value = 0;
		MInteger outNBElement = new MInteger();
		outNBElement.value = 0;
		
		double[] outRealUpperBand = new double[arrayData.size()];
		double[] outRealMiddleBand = new double[arrayData.size()];
		double[] outRealLowerBand = new double[arrayData.size()];
		
    	Core core = new Core();
    	RetCode retCode = core.bbands(0, arrayData.size() - 1, inReal, bollN, 2, 2, MAType.Sma, outBegIdx, outNBElement, outRealUpperBand, outRealMiddleBand, outRealLowerBand);
    	if (RetCode.Success == retCode) {
    		SimpleDateFormat df = new SimpleDateFormat(SourceDateformatString);
            SimpleDateFormat dt = new SimpleDateFormat(ToDateformatString);
    		for (int i = 0; i < arrayData.size(); i++) {
    			OHLCVData data = arrayData.get(i);
				try {
					int dateValue = Integer.parseInt(dt.format(df.parse(data.getDate())));
					if (i<outBegIdx.value){
						dv1.add(new DateValueEntity(Float.MAX_VALUE, dateValue));
						dv2.add(new DateValueEntity(Float.MAX_VALUE, dateValue));
						dv3.add(new DateValueEntity(Float.MAX_VALUE, dateValue));
					}else {
						int index = i-outBegIdx.value;
						dv1.add(new DateValueEntity((float) outRealUpperBand[index], dateValue));
						dv2.add(new DateValueEntity((float) outRealMiddleBand[index], dateValue));
						dv3.add(new DateValueEntity((float) outRealLowerBand[index], dateValue));
					}
				} catch (NumberFormatException e) {
					e.printStackTrace();
					continue;
				} catch (ParseException e) {
					e.printStackTrace();
					continue;
				}
			}
    	}
    	
    	List<List<DateValueEntity>> bollData = new ArrayList<List<DateValueEntity>>();
    	bollData.add(dv1);
    	bollData.add(dv2);
    	bollData.add(dv3);
    	
    	return bollData;
    }
	
	public static List<DateValueEntity> computeMA(List<IStickEntity> candleStickData, int days) {

        if (days < 2) {
            return null;
        }

        List<DateValueEntity> MA5Values = new ArrayList<DateValueEntity>();

        float sum = 0;
        float avg = 0;
        for (int i = 0; i < candleStickData.size(); i++) {
            float close = (float) ((OHLCEntity) candleStickData.get(i)).getClose();
            if (i < days) {
                sum = sum + close;
                avg = sum / (i + 1f);
            } else {
                sum = sum + close
                        - (float) ((OHLCEntity) candleStickData.get(i - days)).getClose();
                avg = sum / days;
            }
            MA5Values.add(new DateValueEntity(avg, candleStickData.get(i).getDate()));
        }

        return MA5Values;
    }
	
	public static ListChartData<IStickEntity> convertCandleStickData(List<OHLCVData> ohlcData){
		List<IStickEntity> candleStickData = new ArrayList<IStickEntity>();
		
        SimpleDateFormat df = new SimpleDateFormat(SourceDateformatString);
        SimpleDateFormat dt = new SimpleDateFormat(ToDateformatString);
        for (OHLCVData data : ohlcData) {
			try {
				candleStickData.add(new OHLCEntity(data.getOpen(), data.getHigh(), data.getLow(),data.getClose(), Integer.parseInt(dt.format(df.parse(data.getDate())))));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				continue;
			} catch (ParseException e) {
				e.printStackTrace();
				continue;
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
        
        return new ListChartData<IStickEntity>(candleStickData);
	}
	
	public static List<LineEntity<DateValueEntity>> convertCandleStickLinesData(List<OHLCVData> ohlcData,int ma1, int ma2, int ma3){
		List<IStickEntity> candleStickData = new ArrayList<IStickEntity>();
		
        SimpleDateFormat df = new SimpleDateFormat(SourceDateformatString);
        SimpleDateFormat dt = new SimpleDateFormat(ToDateformatString);
        for (OHLCVData data : ohlcData) {
			try {
				candleStickData.add(new OHLCEntity(data.getOpen(), data.getHigh(), data.getLow(),data.getClose(), Integer.parseInt(dt.format(df.parse(data.getDate())))));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				continue;
			} catch (ParseException e) {
				e.printStackTrace();
				continue;
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
		
		List<LineEntity<DateValueEntity>> candleStickLines = new ArrayList<LineEntity<DateValueEntity>>();

        // 计算5日均线
        LineEntity<DateValueEntity> MA5 = new LineEntity<DateValueEntity>();
        MA5.setTitle("MA5");
        MA5.setLineColor(Color.parseColor(LINE_COLORS[0]));
        MA5.setLineData(computeMA(candleStickData, ma1));
        candleStickLines.add(MA5);

        // 计算10日均线
        LineEntity<DateValueEntity> MA10 = new LineEntity<DateValueEntity>();
        MA10.setTitle("MA10");
        MA10.setLineColor(Color.parseColor(LINE_COLORS[1]));
        MA10.setLineData(computeMA(candleStickData, ma2));
        candleStickLines.add(MA10);

        // 计算25日均线
        LineEntity<DateValueEntity> MA25 = new LineEntity<DateValueEntity>();
        MA25.setTitle("MA25");
        MA25.setLineColor(Color.parseColor(LINE_COLORS[2]));
        MA25.setLineData(computeMA(candleStickData, ma3));
        candleStickLines.add(MA25);

        return candleStickLines;
	}
	
	public static List<LineEntity<DateValueEntity>> convertCandleBandData(List<OHLCVData> ohlcData){
		List<LineEntity<DateValueEntity>> band = new ArrayList<LineEntity<DateValueEntity>>();
		List<List<DateValueEntity>> bollData = TAComputeUtils.computeBOLLData(ohlcData, 20);
        
        LineEntity<DateValueEntity> LOWER = new LineEntity<DateValueEntity>();
        LOWER.setTitle("LOWER");
        LOWER.setLineColor(Color.parseColor(LINE_COLORS[0]));
        LOWER.setLineData(bollData.get(0));
        band.add(LOWER);
        
        LineEntity<DateValueEntity> UPP = new LineEntity<DateValueEntity>();
        UPP.setTitle("L");
        UPP.setLineColor(Color.parseColor(LINE_COLORS[1]));
        UPP.setLineData(bollData.get(2));
        band.add(UPP);

        LineEntity<DateValueEntity> UPPER = new LineEntity<DateValueEntity>();
        UPPER.setTitle("UPPER");
        UPPER.setLineColor(Color.parseColor(LINE_COLORS[2]));
        UPPER.setLineData(bollData.get(1));
        band.add(UPPER);
        
        return band;
	}
	
	public static ListChartData<IStickEntity> convertVOLData(List<OHLCVData> ohlcData){
		List<IStickEntity> stick = new ArrayList<IStickEntity>();

        SimpleDateFormat df = new SimpleDateFormat(SourceDateformatString);
        SimpleDateFormat dt = new SimpleDateFormat(ToDateformatString);
        for (OHLCVData data : ohlcData) {
			try {
				if(data.getOpen() - data.getClose() > 0){
					stick.add(new ColoredStickEntity(data.getVol(), 0, Integer.parseInt(dt.format(df.parse(data.getDate()))), Color.parseColor(LINE_COLORS[1])));
				}else if (data.getOpen() - data.getClose() < 0) {
					stick.add(new ColoredStickEntity(data.getVol(), 0, Integer.parseInt(dt.format(df.parse(data.getDate()))), Color.parseColor(LINE_COLORS[0])));
				}else {
					stick.add(new ColoredStickEntity(data.getVol(), 0, Integer.parseInt(dt.format(df.parse(data.getDate()))), Color.LTGRAY));
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
				continue;
			} catch (ParseException e) {
				e.printStackTrace();
				continue;
			}
		}
        
        return new ListChartData<IStickEntity>(stick);
	}
	
	public static List<LineEntity<DateValueEntity>> convertVOLMAData(List<OHLCVData> ohlcData,int ma1, int ma2, int ma3){
		List<IStickEntity> candleStickData = new ArrayList<IStickEntity>();
		
        SimpleDateFormat df = new SimpleDateFormat(SourceDateformatString);
        SimpleDateFormat dt = new SimpleDateFormat(ToDateformatString);
        for (OHLCVData data : ohlcData) {
			try {
				candleStickData.add(new OHLCEntity(data.getOpen(), data.getHigh(), data.getLow(),data.getVol(), Integer.parseInt(dt.format(df.parse(data.getDate())))));
			} catch (NumberFormatException e) {
				e.printStackTrace();
				continue;
			} catch (ParseException e) {
				e.printStackTrace();
				continue;
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
		
		List<LineEntity<DateValueEntity>> candleStickLines = new ArrayList<LineEntity<DateValueEntity>>();

        // 计算5日均线
        LineEntity<DateValueEntity> MA5 = new LineEntity<DateValueEntity>();
        MA5.setTitle("MA5");
        MA5.setLineColor(Color.parseColor(LINE_COLORS[0]));
        MA5.setLineData(computeMA(candleStickData, ma1));
        candleStickLines.add(MA5);

        // 计算10日均线
        LineEntity<DateValueEntity> MA10 = new LineEntity<DateValueEntity>();
        MA10.setTitle("MA10");
        MA10.setLineColor(Color.parseColor(LINE_COLORS[1]));
        MA10.setLineData(computeMA(candleStickData, ma2));
        candleStickLines.add(MA10);

        // 计算25日均线
        LineEntity<DateValueEntity> MA25 = new LineEntity<DateValueEntity>();
        MA25.setTitle("MA25");
        MA25.setLineColor(Color.parseColor(LINE_COLORS[2]));
        MA25.setLineData(computeMA(candleStickData, ma3));
        candleStickLines.add(MA25);

        return candleStickLines;
	}
	
	public static ListChartData<IStickEntity> convertMACDData(List<OHLCVData> ohlcData, Context context){
		int macdL = PreferencesUtils.getInt(context, PreferencesUtils.MACD_L);
		if (macdL == -1) {
			macdL = 12;
			PreferencesUtils.putInt(context, PreferencesUtils.MACD_L, macdL);
		}
		
		int macdM = PreferencesUtils.getInt(context, PreferencesUtils.MACD_M);
		if (macdM == -1) {
			macdM = 26;
			PreferencesUtils.putInt(context, PreferencesUtils.MACD_M, macdM);
		}
		
		int macdS = PreferencesUtils.getInt(context, PreferencesUtils.MACD_S);
		if (macdS == -1) {
			macdS = 9;
			PreferencesUtils.putInt(context, PreferencesUtils.MACD_S, macdS);
		}
		
        return new ListChartData<IStickEntity>(TAComputeUtils.computeMACDData(ohlcData, macdL, macdM, macdS));
	}
	
	public static List<LineEntity<DateValueEntity>> convertKDJData(List<OHLCVData> ohlcData, Context context){
		int kdjN = PreferencesUtils.getInt(context, PreferencesUtils.KDJ_N);
		if (kdjN == -1) {
			kdjN = 9;
			PreferencesUtils.putInt(context, PreferencesUtils.KDJ_N, kdjN);
		}
		
		List<LineEntity<DateValueEntity>> lines = new ArrayList<LineEntity<DateValueEntity>>();

    	List<List<DateValueEntity>> kdjData = TAComputeUtils.computeKDJData(ohlcData, kdjN);
        // 计算5日均线
        LineEntity<DateValueEntity> K = new LineEntity<DateValueEntity>();
        K.setTitle("K");
        K.setLineColor(Color.parseColor(LINE_COLORS[0]));
        K.setLineData(kdjData.get(0));
        lines.add(K);

        // 计算10日均线
        LineEntity<DateValueEntity> D = new LineEntity<DateValueEntity>();
        D.setTitle("D");
        D.setLineColor(Color.parseColor(LINE_COLORS[1]));
        D.setLineData(kdjData.get(1));
        lines.add(D);
        
        // 计算10日均线
        LineEntity<DateValueEntity> J = new LineEntity<DateValueEntity>();
        J.setTitle("J");
        J.setLineColor(Color.parseColor(LINE_COLORS[2]));
        J.setLineData(kdjData.get(2));
        lines.add(J);
		return lines;
	}
	
	public static List<LineEntity<DateValueEntity>> convertRSIData(List<OHLCVData> ohlcData, Context context){
		int rsiN1 = PreferencesUtils.getInt(context, PreferencesUtils.RSI_N1);
		if (rsiN1 == -1) {
			rsiN1 = 6;
			PreferencesUtils.putInt(context, PreferencesUtils.RSI_N1, rsiN1);
		}
		
		int rsiN2 = PreferencesUtils.getInt(context, PreferencesUtils.RSI_N2);
		if (rsiN2 == -1) {
			rsiN2 = 12;
			PreferencesUtils.putInt(context, PreferencesUtils.RSI_N2, rsiN2);
		}
		
		List<LineEntity<DateValueEntity>> lines = new ArrayList<LineEntity<DateValueEntity>>();

        // 计算5日均线
        LineEntity<DateValueEntity> R = new LineEntity<DateValueEntity>();
        R.setTitle("R");
        R.setLineColor(Color.parseColor(LINE_COLORS[0]));
        R.setLineData(TAComputeUtils.computeRSIData(ohlcData, rsiN1));
        lines.add(R);

        // 计算10日均线
        LineEntity<DateValueEntity> S = new LineEntity<DateValueEntity>();
        S.setTitle("S");
        S.setLineColor(Color.parseColor(LINE_COLORS[1]));
        S.setLineData(TAComputeUtils.computeRSIData(ohlcData, rsiN2));
        lines.add(S);
        
        // 计算10日均线
        LineEntity<DateValueEntity> I = new LineEntity<DateValueEntity>();
        I.setTitle("I");
        I.setLineColor(Color.parseColor(LINE_COLORS[2]));
        I.setLineData(TAComputeUtils.computeRSIData(ohlcData, 24));
        lines.add(I);
        
		return lines;
	}
	
	public static List<LineEntity<DateValueEntity>> convertWRData(List<OHLCVData> ohlcData, Context context){
		int wrN = PreferencesUtils.getInt(context, PreferencesUtils.WR_N);
		if (wrN == -1) {
			wrN = 10;
			PreferencesUtils.putInt(context, PreferencesUtils.WR_N, wrN);
		}
		
		List<LineEntity<DateValueEntity>> lines = new ArrayList<LineEntity<DateValueEntity>>();

        // 计算5日均线
        LineEntity<DateValueEntity> WR = new LineEntity<DateValueEntity>();
        WR.setTitle("WR");
        WR.setLineColor(Color.parseColor(LINE_COLORS[0]));
        WR.setLineData(TAComputeUtils.computeWRData(ohlcData, wrN));
        lines.add(WR);
    	
    	return lines;
	}
	
	public static List<LineEntity<DateValueEntity>> convertCCIData(List<OHLCVData> ohlcData, Context context){
		int cciN = PreferencesUtils.getInt(context, PreferencesUtils.CCI_N);
		if (cciN == -1) {
			cciN = 14;
			PreferencesUtils.putInt(context, PreferencesUtils.CCI_N, cciN);
		}
		
		List<LineEntity<DateValueEntity>> lines = new ArrayList<LineEntity<DateValueEntity>>();

        // 计算5日均线
        LineEntity<DateValueEntity> CCI = new LineEntity<DateValueEntity>();
        CCI.setTitle("CCI");
        CCI.setLineColor(Color.parseColor(LINE_COLORS[0]));
        CCI.setLineData(TAComputeUtils.computeCCIData(ohlcData, cciN));
        lines.add(CCI);
    	
    	return lines;
	}
	
	public static List<LineEntity<DateValueEntity>> convertBOLLData(List<OHLCVData> ohlcData, Context context){
		int bollN = PreferencesUtils.getInt(context, PreferencesUtils.BOLL_N);
		if (bollN == -1) {
			bollN = 20;
			PreferencesUtils.putInt(context, PreferencesUtils.BOLL_N, bollN);
		}
		
		List<LineEntity<DateValueEntity>> lines = new ArrayList<LineEntity<DateValueEntity>>();

    	List<List<DateValueEntity>> bollData = TAComputeUtils.computeBOLLData(ohlcData, bollN);
        // 计算5日均线
        LineEntity<DateValueEntity> upper = new LineEntity<DateValueEntity>();
        upper.setTitle("B");
        upper.setLineColor(Color.parseColor(LINE_COLORS[0]));
        upper.setLineData(bollData.get(0));
        lines.add(upper);

        // 计算10日均线
        LineEntity<DateValueEntity> middle = new LineEntity<DateValueEntity>();
        middle.setTitle("O");
        middle.setLineColor(Color.parseColor(LINE_COLORS[1]));
        middle.setLineData(bollData.get(1));
        lines.add(middle);
        
        // 计算10日均线
        LineEntity<DateValueEntity> lower = new LineEntity<DateValueEntity>();
        lower.setTitle("L");
        lower.setLineColor(Color.parseColor(LINE_COLORS[2]));
        lower.setLineData(bollData.get(2));
        lines.add(lower);
    	
    	return lines;
	}
	
	
}
