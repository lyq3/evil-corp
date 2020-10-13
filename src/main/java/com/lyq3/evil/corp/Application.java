package com.lyq3.evil.corp;

import com.lyq3.evil.corp.dfa.*;
import sun.text.normalizer.Trie;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author 卡卢比
 * @createTime 2020年10月13日 20:32
 * @description
 */
public class Application {
    public static void main(String[] args) {
//        Scanner scan = new Scanner(System.in);
//        System.out.println("输入敏感词（多个逗号隔开）, 回车键确认：");
//        // 判断是否还有输入
//        while (scan.hasNextLine()) {
//            String sensitiveWords = scan.nextLine();
//            TrieNodeBuilder.addWord(sensitiveWords);
//            TrieNode trieNode = TrieNodeBuilder.build();
//
//            System.out.println("输入配置：");
//        }
//        scan.close();

        Map<String,String> rules = new HashMap<>();
//        rules.put("abc","妈妈咪");
        HandleRules handleRules = new HandleRules();
        handleRules.setReplaceRules(rules);
        handleRules.setPrefixMatch(true);
        handleRules.setSuffixMatch(true);
        SensitiveWordHandler sensitiveWordHandler = new SensitiveWordHandler("abc",handleRules);
        String res = sensitiveWordHandler.handle("ds sdgffgfg sdds abc  dfdfabc abcfff sdsabc abc abdc");
        System.out.println(res);
    }
}
