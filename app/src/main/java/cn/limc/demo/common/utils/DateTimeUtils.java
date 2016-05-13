package cn.limc.demo.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhourr on 16/5/12.
 */
public class DateTimeUtils {

    public static String currrentDateFormat(String target){
        return dateFormat(new Date(), target);
    }

    public static String dateFormat(Date date, String target){
        SimpleDateFormat simpleDateFormatTarget = new SimpleDateFormat(target);

        return simpleDateFormatTarget.format(date);
    }

    public static String dateFormat(String date, String source, String target){
        SimpleDateFormat simpleDateFormatSource = new SimpleDateFormat(source);
        SimpleDateFormat simpleDateFormatTarget = new SimpleDateFormat(target);

        try {
            return simpleDateFormatTarget.format(simpleDateFormatSource.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return null;
    }
}
