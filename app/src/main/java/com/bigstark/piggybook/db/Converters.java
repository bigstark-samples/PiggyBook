package com.bigstark.piggybook.db;

import android.arch.persistence.room.TypeConverter;
import android.text.TextUtils;

import java.util.Date;

public class Converters {

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }


    @TypeConverter
    public static AmountCategory fromString(String value) {
        return TextUtils.isEmpty(value) ? null : AmountCategory.valueOf(value);
    }


    @TypeConverter
    public static String categoryToString(AmountCategory category) {
        return category == null ? null : category.name();
    }

}