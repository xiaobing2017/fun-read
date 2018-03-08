package com.bing.funread.common.utils;

import org.apache.commons.lang3.time.DateUtils;

import java.util.Calendar;
import java.util.Date;

/**
 * Description:时间工具类
 * Author: zhangfusheng
 * Date: 2018/3/8 上午9:30
 */
public class DateUtil {

    private static final long ONE_DAY_MILLIS = 24 * 60 * 60 * 1000;

    /**
     * 获得系统当前时间
     * @return
     */
    public static Date getCurrentTime() {
        return Calendar.getInstance().getTime();
    }

    /**
     * 获得系统当前日期起始时间（时、分、秒、毫秒 清零）
     * @return
     */
    public static Date getCurrentDate() {
        return DateUtils.truncate(getCurrentTime(), Calendar.DAY_OF_MONTH);
    }

    /**
     * 获得系统指定日期起始时间（时、分、秒、毫秒 清零）
     * @param date
     * @return
     */
    public static Date getDate(Date date) {
        return DateUtils.truncate(date, Calendar.DAY_OF_MONTH);
    }

    /**
     * 计算两个日期相差天数，只按年、月、日计算
     * @return
     */
    public static int getDiffDays(Date beginDate, Date endDate) {
        long days = (getDate(endDate).getTime() - getDate(beginDate).getTime()) / ONE_DAY_MILLIS;
        return new Long(days).intValue();
    }
}
