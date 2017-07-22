package com.mytest;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by caijing on 2017/6/30.
 */
public class LambdaTest {
    public static void main(String[] args){
        List<Node> nodes = new ArrayList<>();
        nodes.add(new Node("1","1",2));
        nodes.add(new Node("1","1",3));
        nodes.add(new Node("2","2",2));
        nodes.add(new Node("3","3",3));
        nodes.add(new Node("4","4",4));
        Map<String,String> map = new HashMap<>();
        nodes.stream().collect(Collectors.groupingBy(e -> key(e),Collectors.toList())).forEach((k, v) -> {
            System.out.println(k + "," + v.stream().collect(Collectors.summingInt(Node::getValue)));
        });
//        nodes.stream().collect(Collectors.groupingBy(e -> key(e),Collectors.summarizingInt(Node::getValue));

//        nodes.stream().collect(Collectors.groupingBy(Node::getKey,Collectors.summarizingInt(Node::getValue)));

    }

    static  String key(Node node){
        return node.key+"-"+node.key1;
    }
}
class Node{
    String key;
    String key1;
    int value;

    public Node(String key, String key1, int value) {
        this.key = key;
        this.key1 = key1;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey1() {
        return key1;
    }

    public void setKey1(String key1) {
        this.key1 = key1;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
