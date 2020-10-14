package com.lyq3.evil.corp.dfa;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 卡卢比
 * @createTime 2020年10月13日
 * @description 处理规则
 */
public class HandleRules {
    /**前缀匹配*/
    private Boolean prefixMatch = false;
    /**后缀匹配*/
    private Boolean suffixMatch = false;
    /**替换规则*/
    private Map<String,String> replaceRules = new HashMap<>();

    public Boolean getPrefixMatch() {
        return prefixMatch;
    }

    public HandleRules setPrefixMatch(Boolean prefixMatch) {
        this.prefixMatch = prefixMatch;
        return this;
    }

    public Boolean getSuffixMatch() {
        return suffixMatch;
    }

    public HandleRules setSuffixMatch(Boolean suffixMatch) {
        this.suffixMatch = suffixMatch;
        return this;
    }

    public Map<String, String> getReplaceRules() {
        return replaceRules;
    }

    public HandleRules setReplaceRules(Map<String, String> replaceRules) {
        this.replaceRules = replaceRules;
        return this;
    }
}
