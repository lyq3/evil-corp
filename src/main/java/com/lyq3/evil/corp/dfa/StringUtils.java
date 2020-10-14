package com.lyq3.evil.corp.dfa;

/**
 * @author 卡卢比
 * @createTime 2020年10月13日
 * @description
 */
public class StringUtils {
    /**
     * 判断是否是一个符号
     * @param c
     * @return
     */
    public static boolean isSymbol(char c) {
        int ic = (int) c;
        // 0x2E80-0x9FFF 东亚文字范围
        return !((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z')|| (c >= 'A' && c <= 'Z')) && (ic < 0x2E80 || ic > 0x9FFF);
    }

    /**
     * 获取星号
     * @return
     */
    public static String getAsterisk(int num){
        String asterisks = "";
        if (num <= 0 || num >5){
            asterisks =  "*****";
        }else {
            for (int i = 0; i < num; i++) {
                asterisks += "*";
            }
        }
        return asterisks;
    }
}
