package com.dh.sp.rabbit.util;

import java.util.UUID;

import static java.lang.String.format;

public class TestUtil {

    public static String genField(final String field, final UUID seed){
        return format("%s-%s",field, seed.toString().substring(seed.toString().length() - 4));
    }

    public static String genEmail(final UUID seed){
        return format("email%s@gmail.com", seed.toString().substring(seed.toString().length() - 4));
    }
}
