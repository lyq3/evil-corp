package com.lyq3.evil.corp.dfa;

import java.util.Arrays;
import java.util.List;

/**
 * @author 卡卢比
 * @createTime 2020年10月13日
 * @description 构建TrieNode
 */
public class TrieNodeBuilder {
    /**分隔符*/
    public static String DELIMITER_REG = ",|，";
    /**根节点*/
    private static TrieNode rootNode = new TrieNode();

    public static TrieNode build(){
        return rootNode;
    }

    /**
     * 添加敏感词
     */
    public static void addWord(String text){
        if (text == null || "".equals(text)){
            return;
        }
        String[] split = text.split(DELIMITER_REG);
        addWord(Arrays.asList(split));
    }

    /**
     * 添加敏感词
     */
    public static void addWord(List<String> text){
        if (text == null || text.size() <= 0){
            return;
        }
        for (String word : text) {
            addNode(word);
        }
    }

    /**
     * 添加节点
     */
    private static void addNode(String word){
        TrieNode tempNode = rootNode;
        for (int i = 0; i < word.length(); i++) {
            Character character = word.charAt(i);
            // 过滤符号
            if (StringUtils.isSymbol(character)) {
                continue;
            }
            TrieNode subNode = tempNode.getSubNode(character);
            if (subNode == null){
                subNode = new TrieNode();
                tempNode.addSubNode(character, subNode);
            }
            tempNode = subNode;

            if (i == word.length() - 1) {
                tempNode.setEnd(true);
            }
        }
    }

    public static void main(String[] args) {
        String text = "撒大声,地撒多,撒大,所大所发,不发共和,国方,面蛊惑江湖给,客户估计";
        TrieNodeBuilder.addWord(text);
        System.out.println(TrieNodeBuilder.build());
    }

}
