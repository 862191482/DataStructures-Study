package com.cat.algorithm;

import java.util.Arrays;

public class PrimAlgorithm {
    public static void main(String[] args) {
        //测试图的创建
        char[] data = new char[]{'A','B','C','D','E','F','G'};
        int vertex = data.length;;
        //邻接矩阵的关系使用二维数组表示 用足够大的数表示两个点不联通
        int max = Integer.MAX_VALUE;
        int[][] weight = new int[][]{
                {max,5,7,max,max,max,2},
                {5,max,max,9,max,max,3},
                {7,max,max,max,8,max,max},
                {max,9,max,max,max,4,max},
                {max,max,8,max,max,5,4},
                {max,max,max,4,5,max,6},
                {2,3,max,max,4,6,max}
        };
        //创建MGraph对象
        MGraph mGraph = new MGraph(vertex);
        //创建MinTree对象
        MinTree minTree = new MinTree();
        minTree.createGraph(mGraph,vertex,data,weight);
        minTree.showGraph(mGraph);
        //测试普利姆算法
        minTree.prim(mGraph,0);
    }
}
//创建最小生成树
class MinTree{
    //创建图的邻接矩阵
    /**
     * @param graph 图对象
     * @param vertex 图对应的顶点个数
     * @param data 图的各个顶点的值
     * @param weight 图的邻接矩阵
     */
    public void createGraph(MGraph graph,int vertex,char[] data,int[][] weight){
        int i,j;
        for (i = 0; i < vertex; i++) {//顶点
            graph.data[i] = data[i];
            for (j = 0; j < vertex; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }
    public void showGraph(MGraph graph){
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }
    //编写prim算法，得到最小生成树
    /**
     * @param graph 图
     * @param v 表示从图的第几个顶点开始生成'A'->0 'B'->1
     */
    public void prim(MGraph graph,int v){
        //标记结点是否被访问
        int[] visited = new int[graph.vertex];
        //java不需要初始化，默认为0，其它的语言可能需要初始化
        for (int i = 0; i < graph.vertex; i++) {
            visited[i] = 0;
        }
        //把当前结点标记为已访问
        visited[v] = 1;
        //h1和h2记录两个顶点的下标
        int h1 = -1;
        int h2 = -1;
        //将minWeight初始化成一个大数，后面的遍历过程中，会被替换
        int minWeight = Integer.MAX_VALUE;
        //因为有graph.vertex个顶点，普利姆算法结束后，有graph.vertex - 1条边
        for (int k = 1; k < graph.vertex; k++) {

            //确定每一次生成的子图和哪个结点的距离最近
            for (int i = 0; i < graph.vertex; i++) {//i结点表示被访问过的结点
                for (int j = 0; j < graph.vertex; j++) {//j表示还没有访问过的结点
                    //找出已经访问过的结点和没有访问过的结点的权的最小值
                    if(visited[i] == 1 && visited[j] == 0
                            && graph.weight[i][j] < minWeight){
                        //替换minWeight
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            //找到最小的一条边
            System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + ">权值：" + minWeight);
            //将当前找到的结点标记为已访问
            visited[h2] = 1;
            //minWeight 重新设置为最大值
            minWeight = Integer.MAX_VALUE;
        }
    }
}
class MGraph{
    int vertex;//表示图的结点个数
    char[] data;//存放结点数据
    int[][] weight;//存放边，就是我们的邻接矩阵

    public MGraph(int vertex) {
        this.vertex = vertex;
        data = new char[vertex];
        weight = new int[vertex][vertex];
    }
}