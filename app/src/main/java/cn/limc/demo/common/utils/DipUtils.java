package cn.limc.demo.common.utils;

import android.content.Context;

/**
 * dip,px转换
 * @author zhourr
 *
 */
public class DipUtils {

	/**
	 * 不允许实例
	 */
	private DipUtils(){
		throw new AssertionError();
	}
	
	/**
	 * dip转px
	 * @param context
	 * @param dipValue
	 * @return
	 */
	public static int dip2px(Context context, float dipValue){
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int)(dipValue * scale + 0.5f);
	}
	
	/**
	 * px转dip
	 * @param context
	 * @param pxValue
	 * @return
	 */
	public static int px2dip(Context context, float pxValue){
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int)(pxValue / scale + 0.5f);
	}
	
	/**
	 * dip转px
	 * @param context
	 * @param dipValue
	 * @return
	 */
	public static float dip2pxF(Context context, float dipValue){
		final float scale = context.getResources().getDisplayMetrics().density;
		return (float)(dipValue * scale + 0.5f);
	}
	
	/**
     * 将px值转换为sp值，保证文字大小不变
     * 
     * @param pxValue
     * @param fontScale
     *            （DisplayMetrics类中属性scaledDensity）
     * @return
     */ 
    public static int px2sp(Context context, float pxValue) { 
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity; 
        return (int) (pxValue / fontScale + 0.5f); 
    } 
   
    /**
     * 将sp值转换为px值，保证文字大小不变
     * 
     * @param spValue
     * @param fontScale
     *            （DisplayMetrics类中属性scaledDensity）
     * @return
     */ 
    public static int sp2px(Context context, float spValue) { 
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity; 
        return (int) (spValue * fontScale + 0.5f); 
    }
}
