package com.lyq3.evil.corp.dfa;

import java.util.Map;

/**
 * @author 卡卢比
 * @createTime 2020年10月13日 22:45
 * @description
 */
public class SensitiveWordHandler {
    /**规则*/
    private HandleRules handleRules;
    /**根节点*/
    private TrieNode rootNode = TrieNodeBuilder.build();
    /**敏感词*/
    private String sensitiveWords;

    public SensitiveWordHandler(String sensitiveWords) {
        init(sensitiveWords);
    }

    public SensitiveWordHandler(String sensitiveWords, HandleRules handleRules) {
        init(sensitiveWords,handleRules);
    }

    /**
     * 初始化  敏感词库  和 操作规则
     * @param sensitiveWords
     */
    public void init(String sensitiveWords){
        this.init(sensitiveWords,new HandleRules());
    }
    public void init(String sensitiveWords,HandleRules rules){
        TrieNodeBuilder.addWord(sensitiveWords);
        this.sensitiveWords = sensitiveWords;
        this.handleRules = rules;
        //下面将敏感词对应的替换结果换成***
        String[] words = sensitiveWords.split(TrieNodeBuilder.DELIMITER_REG);
        Map<String, String> replaceRules = rules.getReplaceRules();
        if (words.length > 0){
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                String rule = replaceRules.get(word);
                if (rule == null || "".equals(rule)){
                    replaceRules.put(word,StringUtils.getAsterisk(word.length()));
                }
            }
        }

    }

    /**
     * 敏感词替换操作
     * @param text
     * @return
     */
    public String handle(String text){
        if (text.trim().length() == 0) {
            return text;
        }
        StringBuilder result = new StringBuilder();
        TrieNode tempNode = rootNode;
        int begin = 0; //起点
        int position = 0; //当前比较位置
        StringBuilder sensitiveWord = new StringBuilder();//敏感词语
        while (position < text.length()){
            char c = text.charAt(position);
            // 字符/空格直接跳过
            if (StringUtils.isSymbol(c)) {
                if (tempNode == rootNode) {
                    result.append(c);
                    ++begin;
                }
                ++position;
                continue;
            }

            tempNode = tempNode.getSubNode(c);
            if (tempNode == null) {
                // 以begin开始的字符串不存在敏感词
                result.append(text.charAt(begin));
                // 跳到下一个字符开始测试
                position = begin + 1;
                begin = position;
                // 回到树初始节点
                tempNode = rootNode;
            } else if (tempNode.isEnd()) {
                // 发现敏感词， 从begin到position的位置用replacement替换掉
                sensitiveWord.append(c);
                //后缀匹配
                if (handleRules.getSuffixMatch()){
                    while (result.length() > 0){
                        char lastChar = result.charAt(result.length() - 1);
                        if (StringUtils.isSymbol(lastChar)){
                            break;
                        }else {
                            result.deleteCharAt(result.length()-1);
                        }
                    }
                }
                result.append(handleRules.getReplaceRules().get(sensitiveWord.toString()));
                //前缀匹配
                if (handleRules.getPrefixMatch()){
                    while (position < text.length()) {
                        char nextChar = text.charAt(position);
                        if (StringUtils.isSymbol(nextChar)) {
                            break;
                        } else {
                            begin += 1;
                            position = begin;
                        }
                    }
                }

                position = position + 1;
                begin = position;
                tempNode = rootNode;
                sensitiveWord = new StringBuilder();
            } else {
                sensitiveWord.append(c);
                ++position;
            }
        }
        result.append(text.substring(begin));
        return result.toString();
    }
}
