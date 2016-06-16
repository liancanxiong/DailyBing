package com.brilliantbear.dailybing.utils;

import android.util.Log;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Bear on 2016-6-16.
 */
public class DateUtilsTest {

    @Test
    public void testChangeDateFormat() throws Exception {
        String date = DateUtils.changeDateFormat("20160615", "yyyyMMdd", "yyyy年M月d日 E");
        System.out.println(date);
    }

    @Before
    public void setUp() throws Exception {

    }


}