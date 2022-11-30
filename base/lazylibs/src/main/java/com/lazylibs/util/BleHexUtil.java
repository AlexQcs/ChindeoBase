package com.lazylibs.util;

import com.clj.fastble.utils.HexUtil;

public class BleHexUtil {
    //    HexUtil
//    byte[]转String，参数addSpace表示每一位之间是否增加空格，常用于打印日志。
//

    public static String formatHexString(byte[] data, boolean addSpace) {
        return HexUtil.formatHexString(data, addSpace);
    }


//    String转byte[]

    public static byte[] hexStringToBytes(String hexString) {

        return HexUtil.hexStringToBytes(hexString);
    }


    //    byte[]转char[]，参数toLowerCase表示大小写
    public static char[] encodeHex(byte[] data, boolean toLowerCase) {
        return HexUtil.encodeHex(data, toLowerCase);
    }

    /**
     * 代码来自：java.lang.Long
     * 因为要跟踪看变量的值，所以要copy出来，或者是去附加源码，否则 eclipse 调试时查看变量的值会提示 xxx cannot be resolved to a variable
     * @author 微wx笑
     * @date   2017年12月6日下午5:19:40
     * @param s
     * @param radix
     * @return
     * @throws NumberFormatException
     */
    public static long parseLong(String s, int radix) throws NumberFormatException {
        if (s == null) {
            throw new NumberFormatException("null");
        }

        if (radix < Character.MIN_RADIX) {
            throw new NumberFormatException("radix " + radix + " less than Character.MIN_RADIX");
        }
        if (radix > Character.MAX_RADIX) {
            throw new NumberFormatException("radix " + radix + " greater than Character.MAX_RADIX");
        }

        long result = 0;
        boolean negative = false;
        int i = 0, len = s.length();
        long limit = -Long.MAX_VALUE;
        long multmin;
        int digit;

        if (len > 0) {
            char firstChar = s.charAt(0);
            if (firstChar < '0') { // Possible leading "+" or "-"
                if (firstChar == '-') {
                    negative = true;
                    limit = Long.MIN_VALUE;
                } else if (firstChar != '+')
                    throw new NumberFormatException(s);

                if (len == 1) // Cannot have lone "+" or "-"
                    throw new NumberFormatException(s);
                i++;
            }
            multmin = limit / radix;
            while (i < len) {
                // Accumulating negatively avoids surprises near MAX_VALUE
                digit = Character.digit(s.charAt(i++), radix);
                if (digit < 0) {
                    throw new NumberFormatException(s);
                }
                if (result < multmin) {
                    throw new NumberFormatException(s);
                }
                result *= radix;
                if (result < limit + digit) {
                    throw new NumberFormatException(s);
                }
                result -= digit;
            }
        } else {
            throw new NumberFormatException(s);
        }
        return negative ? result : -result;
    }

}
