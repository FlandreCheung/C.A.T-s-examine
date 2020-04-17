package com.lpl.event.util;

public class StringUtil {
    /** 判断输入是否为空的方法
     * @param str
     * @return true/false
     */

    public static boolean StringIsEmpty(String str) {
        if ("".equals(str)|| str == null) {
            return true;
        }
        return false;
    }


}
