package com.cat.algorithm;

public class Hanoitower {
    public static void main(String[] args) {
        hanoitower(5, 'A', 'B', 'C');
    }
    //分治算法移动汉诺塔
    public static void hanoitower(int num,char a,char b,char c){
        //一个盘
        if(num == 1){
            System.out.println(a +"->"+ c);
        }else{
            //数量大于二时，总是可以看作是两个盘 1.最下面的盘 2.上面的所有盘
            //1.先把最上面的所有盘A->B，移动过程会使用到c
            hanoitower(num - 1, a, c, b);
            //2.把最下面的盘A->C
            System.out.println(a +"->"+ c);
            //3.把B塔所有盘B->C，移动过程会使用到a
            hanoitower(num - 1, b, a, c);
        }
    }
}
