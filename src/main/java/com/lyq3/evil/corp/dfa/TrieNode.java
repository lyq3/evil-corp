package com.lyq3.evil.corp.dfa;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 卡卢比
 * @createTime 2020年10月13日 20:48
 * @description 前缀树,用于存放敏感词库
 */
public class TrieNode {
    /**
     * 标识当前结点是否是一个“关键词”的最后一个结点
     * true 关键词的终结 false 继续
     */
    private boolean isEnd = false;

    /**
     * key 下一个字符 value 对应的结点
     */
    private Map<Character , TrieNode> subNodes = new HashMap<>();

    /**
     * 向指定位置添加结点树
     * @param key
     * @param node
     */
    public void addSubNode(Character key , TrieNode node){
        subNodes.put(key , node);
    }

    /**
     * 根据key获得相应的子节点
     * @param key
     * @return
     */
    public TrieNode getSubNode(Character key){
        return subNodes.get(key);
    }

    /**
     * 判断是否是关键字的结尾
     */
    public boolean isEnd(){
        return isEnd;
    }

    /**
     *设置为关键字的结尾
     */
    public void setEnd(boolean isEnd){
        this.isEnd = isEnd;
    }
}
