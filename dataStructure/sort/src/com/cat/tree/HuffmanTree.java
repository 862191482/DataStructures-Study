package com.cat.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {
    public static void main(String[] args) {
        int arr[] = {13,7,8,3,29,6,1};
        Node root = createHuffmanTree(arr);
        preOrder(root);
    }
    public static void preOrder(Node root){
        if(root != null){
            root.preOrder();
        }else{
            System.out.println("空树，不能遍历");
        }
    }
    //创建赫夫曼树的方法
    public static Node createHuffmanTree(int[] arr){
        //1.遍历arr数组
        //2.将arr的每个元素构成一个Node
        //3.将Node放入到ArraysList中
        List<Node> nodes = new ArrayList<Node>();
        for(int value : arr){
            nodes.add(new Node(value));
        }
        //排序从小到大
        Collections.sort(nodes);
        System.out.println("nodes =" + nodes);
        //取出根结点权值最小的两颗二叉树
        Node leftNode = nodes.get(0);
        Node rightNode = nodes.get(1);
        //构建一颗新的二叉树
        Node parent = new Node(leftNode.value + rightNode.value);
        parent.left = leftNode;
        parent.right = rightNode;
        //从ArraysList删除处理过的二叉树
        nodes.remove(leftNode);
        nodes.remove(rightNode);
        //将parent加入nodes
        nodes.add(parent);
        //返回赫夫曼树的root结点
        return nodes.get(0);
    }
}
//创建结点类
class Node implements Comparable<Node>{
    int value;//结点权值
    Node left;//指向左子结点
    Node right;//指向右子结点
    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if(this.left != null){
            this.left.preOrder();
        }
        if(this.right != null){
            this.right.preOrder();
        }
    }
    public Node(int value) {
        this.value = value;
    }

    @Override
    public String  toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        //表示从小到大
        return this.value - o.value;
    }
}