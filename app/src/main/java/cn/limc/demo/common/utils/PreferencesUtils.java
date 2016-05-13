package cn.limc.demo.common.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * PreferencesUtils, easy to get or put data
 * <ul>
 * <strong>Preference Name</strong>
 * <li>you can change preference name by {@link #Preference_Name}</li>
 * </ul>
 * <ul>
 * <strong>Put Value</strong>
 * <li>put string {@link #putString(Context, String, String)}</li>
 * <li>put int {@link #putInt(Context, String, int)}</li>
 * <li>put long {@link #putLong(Context, String, long)}</li>
 * <li>put float {@link #putFloat(Context, String, float)}</li>
 * <li>put boolean {@link #putBoolean(Context, String, boolean)}</li>
 * </ul>
 * <ul>
 * <strong>Get Value</strong>
 * <li>get string {@link #getString(Context, String)}, {@link #getString(Context, String, String)}</li>
 * <li>get int {@link #getInt(Context, String)}, {@link #getInt(Context, String, int)}</li>
 * <li>get long {@link #getLong(Context, String)}, {@link #getLong(Context, String, long)}</li>
 * <li>get float {@link #getFloat(Context, String)}, {@link #getFloat(Context, String, float)}</li>
 * <li>get boolean {@link #getBoolean(Context, String)}, {@link #getBoolean(Context, String, boolean)}</li>
 * </ul>
 * 
 * @author <a href="http://www.trinea.cn" target="_blank">Trinea</a> 2013-3-6
 */
public class PreferencesUtils {

	/** 文件名 */
    public static String Preference_Name = "COCOAS_CHART";
    
    public static final String MACD_S = "MACD_S";
    public static final String MACD_L = "MACD_L";
    public static final String MACD_M = "MACD_M";

    public static final String MA1 = "MA1";
    public static final String MA2 = "MA2";
    public static final String MA3 = "MA3";
    
    public static final String VMA1 = "VMA1";
    public static final String VMA2 = "VMA2";
    public static final String VMA3 = "VMA3";
    
    public static final String KDJ_N = "KDJ_N";
    
    public static final String RSI_N1 = "RSI_N1";
    public static final String RSI_N2 = "RSI_N2";
    
    public static final String WR_N = "WR_N";
    
    public static final String CCI_N = "CCI_N";
    
    public static final String BOLL_N = "BOLL_N";
    
    public static final String THEME_MODE = "THEME_MODE";
    
    private PreferencesUtils() {
        throw new AssertionError();
    }

    /**
     * put string preferences
     * 
     * @param context
     * @param key The name of the preference to modify
     * @param value The new value for the preference
     * @return True if the new values were successfully written to persistent storage.
     */
    public static boolean putString(Context context, String key, String value) {
        SharedPreferences settings = context.getSharedPreferences(Preference_Name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    /**
     * get string preferences
     * 
     * @param context
     * @param key The name of the preference to retrieve
     * @return The preference value if it exists, or null. Throws ClassCastException if there is a preference with this
     *         name that is not a string
     * @see #getString(Context, String, String)
     */
    public static String getString(Context context, String key) {
        return getString(context, key, null);
    }

    /**
     * get string preferences
     * 
     * @param context
     * @param key The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference value if it exists, or defValue. Throws ClassCastException if there is a preference with
     *         this name that is not a string
     */
    public static String getString(Context context, String key, String defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(Preference_Name, Context.MODE_PRIVATE);
        return settings.getString(key, defaultValue);
    }

    /**
     * put int preferences
     * 
     * @param context
     * @param key The name of the preference to modify
     * @param value The new value for the preference
     * @return True if the new values were successfully written to persistent storage.
     */
    public static boolean putInt(Context context, String key, int value) {
        SharedPreferences settings = context.getSharedPreferences(Preference_Name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    /**
     * get int preferences
     * 
     * @param context
     * @param key The name of the preference to retrieve
     * @return The preference value if it exists, or -1. Throws ClassCastException if there is a preference with this
     *         name that is not a int
     * @see #getInt(Context, String, int)
     */
    public static int getInt(Context context, String key) {
        return getInt(context, key, -1);
    }

    /**
     * get int preferences
     * 
     * @param context
     * @param key The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference value if it exists, or defValue. Throws ClassCastException if there is a preference with
     *         this name that is not a int
     */
    public static int getInt(Context context, String key, int defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(Preference_Name, Context.MODE_PRIVATE);
        return settings.getInt(key, defaultValue);
    }

    /**
     * put long preferences
     * 
     * @param context
     * @param key The name of the preference to modify
     * @param value The new value for the preference
     * @return True if the new values were successfully written to persistent storage.
     */
    public static boolean putLong(Context context, String key, long value) {
        SharedPreferences settings = context.getSharedPreferences(Preference_Name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong(key, value);
        return editor.commit();
    }

    /**
     * get long preferences
     * 
     * @param context
     * @param key The name of the preference to retrieve
     * @return The preference value if it exists, or -1. Throws ClassCastException if there is a preference with this
     *         name that is not a long
     * @see #getLong(Context, String, long)
     */
    public static long getLong(Context context, String key) {
        return getLong(context, key, -1);
    }

    /**
     * get long preferences
     * 
     * @param context
     * @param key The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference value if it exists, or defValue. Throws ClassCastException if there is a preference with
     *         this name that is not a long
     */
    public static long getLong(Context context, String key, long defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(Preference_Name, Context.MODE_PRIVATE);
        return settings.getLong(key, defaultValue);
    }

    /**
     * put float preferences
     * 
     * @param context
     * @param key The name of the preference to modify
     * @param value The new value for the preference
     * @return True if the new values were successfully written to persistent storage.
     */
    public static boolean putFloat(Context context, String key, float value) {
        SharedPreferences settings = context.getSharedPreferences(Preference_Name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat(key, value);
        return editor.commit();
    }

    /**
     * get float preferences
     * 
     * @param context
     * @param key The name of the preference to retrieve
     * @return The preference value if it exists, or -1. Throws ClassCastException if there is a preference with this
     *         name that is not a float
     * @see #getFloat(Context, String, float)
     */
    public static float getFloat(Context context, String key) {
        return getFloat(context, key, -1);
    }

    /**
     * get float preferences
     * 
     * @param context
     * @param key The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference value if it exists, or defValue. Throws ClassCastException if there is a preference with
     *         this name that is not a float
     */
    public static float getFloat(Context context, String key, float defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(Preference_Name, Context.MODE_PRIVATE);
        return settings.getFloat(key, defaultValue);
    }

    /**
     * put boolean preferences
     * 
     * @param context
     * @param key The name of the preference to modify
     * @param value The new value for the preference
     * @return True if the new values were successfully written to persistent storage.
     */
    public static boolean putBoolean(Context context, String key, boolean value) {
        SharedPreferences settings = context.getSharedPreferences(Preference_Name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    /**
     * get boolean preferences, default is false
     * 
     * @param context
     * @param key The name of the preference to retrieve
     * @return The preference value if it exists, or false. Throws ClassCastException if there is a preference with this
     *         name that is not a boolean
     * @see #getBoolean(Context, String, boolean)
     */
    public static boolean getBoolean(Context context, String key) {
        return getBoolean(context, key, false);
    }

    /**
     * get boolean preferences
     * 
     * @param context
     * @param key The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference value if it exists, or defValue. Throws ClassCastException if there is a preference with
     *         this name that is not a boolean
     */
    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(Preference_Name, Context.MODE_PRIVATE);
        return settings.getBoolean(key, defaultValue);
    }
}
