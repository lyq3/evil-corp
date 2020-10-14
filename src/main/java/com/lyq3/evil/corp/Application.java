package com.lyq3.evil.corp;

import com.lyq3.evil.corp.dfa.*;
import sun.text.normalizer.Trie;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author 卡卢比
 * @createTime 2020年10月13日
 * @description 核心算法，前缀树(字典树)
 */
public class Application {
    /**敏感字*/
    private static String SENSITIVE_WORDS ="abc,ds";
    /**文本*/
    private static String TEXT = "ds sdgffgfg sdds abc  dfdfabc abcfff sdsabc abc abdc ";

    public static void main(String[] args) {
        // 创建替换规则
        HandleRules handleRules = new HandleRules();
        Map<String,String> rules = new HashMap<>();
        rules.put("abc","妈妈咪");
        handleRules.setReplaceRules(rules);
        handleRules.setPrefixMatch(false); //开启前缀匹配
        handleRules.setSuffixMatch(false); //开启后缀匹配
        //初始化处理器
        SensitiveWordHandler sensitiveWordHandler = new SensitiveWordHandler(SENSITIVE_WORDS,handleRules);
        //获取替换结果
        String res = sensitiveWordHandler.handle(TEXT);

        System.out.println(TEXT);
        System.out.println(res);
    }
}
