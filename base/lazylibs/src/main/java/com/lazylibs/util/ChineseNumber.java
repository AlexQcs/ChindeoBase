package com.lazylibs.util;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public interface ChineseNumber {

    String[] BEFORE_SCALE = {"万", "仟", "佰", "拾", "亿", "仟", "佰", "拾", "万", "仟", "佰", "拾", ""};

    String[] AFTER_SCALE = {"角", "分"};

    String DEFAULT_PATH_SEPARATOR = "\\.";

    Map<String, String> NUMBER_MAPPING = new HashMap<String, String>() {{
        put("0", "零");
        put("1", "壹");
        put("2", "贰");
        put("3", "叁");
        put("4", "肆");
        put("5", "伍");
        put("6", "陆");
        put("7", "柒");
        put("8", "捌");
        put("9", "玖");
    }};

    static String getChineseNumber(String number) {
        return getChineseNumber(number, null, null);
    }

    static String getChineseNumber(String number, String unit, String postfix) {

        String[] numbers = number.split(DEFAULT_PATH_SEPARATOR);
        if (numbers.length > 2) {
            new NumberFormatException("数字格式错误!");
        }
        int length = numbers[0].length();
        int isZero = 0;
        StringBuffer result = new StringBuffer();

        for (int i = 0; i < length; i++) {
            String digit = String.valueOf(numbers[0].charAt(i));

            boolean allZero = true; // 如果后继的全部是零,则跳出
            for (int j = i; j < length; j++) {
                if (numbers[0].charAt(j) != '0') {
                    allZero = false;
                    break;
                }
            }

            if (allZero) {
                boolean hasValue = false;
                for (int z = i; z >= 0; z--) {
                    if (numbers[0].charAt(z) != '0' && length - z <= 7 && length - z >= 5) {
                        hasValue = true;
                        break;
                    }
                }
                // 加万单位
                if ((length - i > 4 && length <= 8) || (hasValue && length - i > 4)) {
                    result.append(BEFORE_SCALE[8]);
                }
                // 加亿单位
                if (length - i >= 9) {
                    result.append(BEFORE_SCALE[4]);
                }
                break;
            }

            if (length < 9 && length - i == 5) {
                if (!"0".equals(digit) && isZero > 0) {
                    result.append(NUMBER_MAPPING.get("0"));
                }
                if ("0".equals(digit)) {
                    result.append(BEFORE_SCALE[8]);
                    if (isZero > 0) {
                        result.append(NUMBER_MAPPING.get("0"));
                    }
                    continue;
                }
            }
            if ("0".equals(digit) && length > 9 && length - i == 9) {
                result.append(BEFORE_SCALE[4]);
                continue;
            }

            if (isZero < 1 || !"0".equals(digit)) {
                if ("0".equals(digit)) {
                    if (length - i != 6 && length - i != 7) {
                        result.append(NUMBER_MAPPING.get(digit));
                    }
                } else {
                    result.append(NUMBER_MAPPING.get(digit));
                }

                if (!"0".equals(digit)) {
                    result.append(BEFORE_SCALE[BEFORE_SCALE.length - length + i]);
                }
            }

            if ("0".equals(digit)) {
                isZero++;
            } else {
                isZero = 0;
            }
        }
        result.append(unit == null ? "圆" : result.append(unit));

        if (numbers.length == 1) {
            result.append(postfix == null ? "整" : result.append(postfix));
            return result.toString();
        }

        length = numbers[1].length();
        for (int j = 0; j < length; j++) {
            if (j > 2) {
                break;
            }
            if (numbers[1].charAt(j) == '0') {
                continue;
            }
            result.append(NUMBER_MAPPING.get(String.valueOf(numbers[1].charAt(j))));
            result.append(AFTER_SCALE[j]);
        }

//        result.append(postfix == null ? "整" : result.append(postfix));

        return result.toString();
    }

    static String getChineseNumber(int number) {
        return getChineseNumber(new Integer(number));
    }

    static String getChineseNumber(int number, String unit, String postfix) {
        return getChineseNumber(new Integer(number), unit, postfix);
    }

    static String getChineseNumber(Long number) {
        return getChineseNumber(number.toString(), null, null);
    }

    static String getChineseNumber(Integer number) {
        return getChineseNumber(number.toString(), null, null);
    }

    static String getChineseNumber(Integer number, String unit, String postfix) {
        return getChineseNumber(number.toString(), unit, postfix);
    }

    static String getChineseNumber(Long number, String unit, String postfix) {
        return getChineseNumber(number.toString(), unit, postfix);
    }

    static String getChineseNumber(long number) {
        return getChineseNumber(new Long(number));
    }

    static String getChineseNumber(long number, String unit, String postfix) {
        return getChineseNumber(new Long(number), unit, postfix);
    }

    static String getChineseNumber(double number, String unit, String postfix) {
        DecimalFormat f = (DecimalFormat) DecimalFormat.getInstance();
        f.applyLocalizedPattern("#.##");
        return getChineseNumber(f.format(number), unit, postfix);
    }

    static String getChineseNumber(double number) {
        return getChineseNumber(number, null, null);
    }

    static String getChineseNumber(Double number) {
        return getChineseNumber(number.doubleValue());
    }

    static String getChineseNumber(Double number, String unit, String postfix) {
        return getChineseNumber(number.doubleValue(), unit, postfix);
    }

    public static void main(String[] args) {
        System.out.println(ChineseNumber.getChineseNumber(2019));
        System.out.println(ChineseNumber.getChineseNumber(2345.632234));
        System.out.println(ChineseNumber.getChineseNumber(2345.68657));
        System.out.println(ChineseNumber.getChineseNumber(235667));
    }
}