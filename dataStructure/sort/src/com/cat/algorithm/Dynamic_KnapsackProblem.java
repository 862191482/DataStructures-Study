package com.cat.algorithm;

public class Dynamic_KnapsackProblem {
    public static void main(String[] args) {
        int[] w = {1, 4, 3};//物品重量
        int[] val = {1500, 3000, 2000};//物品价值
        int m = 4;//背包的容量
        int n = val.length;//物品的个数

        int[][] v = new int[n+1][m+1];
        //为了记录放入商品的情况
        int[][] path = new int[n+1][m+1];
        //初始化第一行第一列，在本程序中，可以不去处理，因为默认为0
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;
        }
        //动态规划处理
        for (int i = 1; i < v.length; i++) {//为了不处理第一行
            for (int j = 1; j < v[0].length; j++) {//为了不处理第一列
                //公式
                //因为从1开始遍历的，因此原来公式中的w[i]此处应为w[i-1]
                //当准备加入的商品的容量大于当前背包的容量时，使用上一个单元格的装入策略
                if(w[i-1] > j){
                    v[i][j] = v[i-1][j];
                }else{//当准备加入的商品的容量小于当前背包的容量时
//                    v[i][j] = Math.max(v[i-1][j],val[i-1] + v[i-1][j-w[i-1]]);
                    //为了记录商品存放到背包的情况，不能直接使用上面的公式
                    if(v[i-1][j] < val[i-1] + v[i-1][j-w[i-1]]){
                        v[i][j] = val[i-1] + v[i-1][j-w[i-1]];
                        //把当前的情况记录到path
                        path[i][j] = 1;
                    }else{
                        v[i][j] = v[i-1][j];
                    }
                }
            }
        }
        //遍历二维数组(v)
        for (int i = 0; i < v.length; i++) {
            for(int j = 0;j < v[i].length;j++){
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }
        //输出最后我们放入的是哪些商品
        //这样输出会把所有放入的情况全部得到，其实我们只需要最后的放入
//        for (int i = 0; i < path.length; i++) {
//            for (int j = 0; j < path[i].length; j++) {
//                if(path[i][j] == 1){
//                    System.out.printf("第%d个商品放入到背包\n",i);
//                }
//            }
//        }
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
        //输出最后我们放入的是哪些商品（改进）
        int i = path.length - 1;
        int j = path[0].length - 1;
        while (i > 0 && j > 0){//从path的最后开始找
            if(path[i][j] == 1){
                System.out.printf("第%d个商品放入到背包\n",i);
                j -= w[i-1];
            }
            i--;
        }
    }
}
