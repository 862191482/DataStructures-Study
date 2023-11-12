package com.cat.tree;

public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder(0);
    }
}
//顺序存储二叉树
/*
    特点：
    1.顺序存储二叉树通常只考虑完全二叉树
    2.第n个元素的左子结点为2n+1
    3.第n个元素的左子结点为2n+2
    4.第n个元素的父结点为（n-1）/2
    5.n从0开始
*/
class ArrBinaryTree{
    private int[] arr;

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }
    public void preOrder(){
        this.preOrder(0);
    }
    //顺序存储二叉树的前序遍历
    /**
     * @param index 数组的下标
     */
    public void preOrder(int index){
        if(arr == null || arr.length == 0){
            System.out.println("数组为空，不能遍历");
        }
        //输出当前元素
        System.out.println(arr[index]);
        //向左递归遍历
        if((index * 2 + 1) < arr.length){
            preOrder(2 * index + 1);
        }
        //向右递归遍历
        if((index * 2 + 2) < arr.length){
            preOrder(2 * index + 2);
        }
    }
}