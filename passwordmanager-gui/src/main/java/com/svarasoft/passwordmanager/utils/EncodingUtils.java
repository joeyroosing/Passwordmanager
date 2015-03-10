package com.svarasoft.passwordmanager.utils;

import java.nio.charset.Charset;

/**
 * Created by Joey on 21-12-2014.
 */
public final class EncodingUtils {

    private static final Charset UTF8_CHARSET = Charset.forName("UTF-8");

    public static String decodeUTF8(byte[] bytes) {
        return new String(bytes, UTF8_CHARSET);
    }

    public static byte[] encodeUTF8(String string) {
        return string.getBytes(UTF8_CHARSET);
    }
}
