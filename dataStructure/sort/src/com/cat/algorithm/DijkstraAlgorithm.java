package com.cat.algorithm;

import java.util.Arrays;

public class DijkstraAlgorithm {
    public static void main(String[] args) {
        char[] vertex = {'A','B','C','D','E','F','G'};
        //邻接矩阵
        int[][] maxtrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        maxtrix[0] = new int[]{N,5,7,N,N,N,2};
        maxtrix[1] = new int[]{5,N,N,9,N,N,3};
        maxtrix[2] = new int[]{7,N,N,N,8,N,N};
        maxtrix[3] = new int[]{N,9,N,N,N,4,N};
        maxtrix[4] = new int[]{N,N,8,N,N,5,4};
        maxtrix[5] = new int[]{N,N,N,4,5,N,6};
        maxtrix[6] = new int[]{2,3,N,N,4,6,N};
        //创建Graph
        Graph graph = new Graph(vertex, maxtrix);
        graph.showGraph();
        graph.dsj(6);
        graph.show();
    }
}
class Graph{
    private char[] vertex;//顶点数组
    private int[][] maxtrix;//邻接矩阵
    private VisitedVertex vv;//已经访问的顶点的集合
    //构造器
    public Graph(char[] vertex,int[][] maxtrix){
        this.vertex = vertex;
        this.maxtrix = maxtrix;
    }
    //显示迪杰斯特拉结果
    public void show(){
        vv.show();
    }
    public void showGraph(){
        for (int[] link : maxtrix) {
            System.out.println(Arrays.toString(link));
        }
    }
    //迪杰斯特拉算法实现
    public void dsj(int index){
        vv = new VisitedVertex(vertex.length, index);
        update(index);//更新index顶点到周围顶点的距离和前驱顶点
        for (int i = 1; i < vertex.length; i++) {
            index = vv.updateArr();//选择并返回新的访问顶点
            update(index);//更新index顶点到周围顶点的距离和前驱顶点
        }
    }
    //更新index下标顶点到周围顶点的距离和周围顶点的前驱顶点
    private void update(int index){
        int len = 0;
        //遍历邻接矩阵maxtrix[index]行
        for (int i = 0; i < maxtrix[index].length; i++) {
            //出发顶点到index顶点的距离加上index顶点到i顶点的距离的和
            len = vv.getDis(index) + maxtrix[index][i];
            //如果i顶点没被访问过，并且len小于出发顶点到i顶点的距离，就需要更新
            if(!vv.in(i) && len < vv.getDis(i)){
                //更新i顶点的前驱为index顶点
                vv.updatePre(i,index);
                //更新出发顶点到i顶点的距离
                vv.updateDis(i,len);
            }
        }
    }
}
//已访问顶点集合
class VisitedVertex{
    public int[] already_arr;
    public int[] pre_visited;
    public int[] dis;
    //构造器
    /**
     * @param lenght 顶点的个数
     * @param index 出发顶点对应的下标
     */
    public VisitedVertex(int lenght,int index){
        this.already_arr = new int[lenght];
        this.pre_visited = new int[lenght];
        this.dis = new int[lenght];
        //初始化dis数组
        Arrays.fill(dis,65535);
        this.already_arr[index] = 1;//设置出发顶点已访问
        this.dis[index] = 0;//设置出发顶点的距离为0

    }
    /**
     * 判断index顶点是否被访问过
     * @param index
     * @return 访问过返回true 反之返回false
     */
    public boolean in(int index){
        return already_arr[index] == 1;
    }

    /**
     *  更c
     * @param index
     * @param len
     */
    public void updateDis(int index,int len){
        dis[index] = len;
    }
    /**
     * 更新pre顶点的前驱为index顶点
     * @param pre
     * @param index
     */
    public void updatePre(int pre, int index){
        pre_visited[pre] = index;
    }
    //返回出发顶点到index顶点的距离
    public int getDis(int index){
        return dis[index];
    }
    //继续选择并返回新的访问顶点
    public int updateArr(){
        int min = 65535,index = 0;
        for (int i = 0; i < already_arr.length; i++) {
            if(already_arr[i] == 0 && dis[i] < min){
                min = dis[i];
                index = i;
            }
        }
        //更新index被访问过
        already_arr[index] = 1;
        return index;
    }
    //显示最后的结果
    public void show(){
        System.out.println("===================================");
        //输出already_arr
        for (int i : already_arr) {
            System.out.print(i + " ");
        }
        System.out.println();
        //输出pre_visited
        for (int i : pre_visited) {
            System.out.print(i + " ");
        }
        System.out.println();
        //输出dis
        for (int i : dis) {
            System.out.print(i + " ");
        }
        System.out.println();
        char[] vertex = {'A','B','C','D','E','F','G'};
        int count = 0;
        for (int i : dis) {
            if(i != 65535){
                System.out.print(vertex[count] + "(" + i + ")");
            }else{
                System.out.println("N");
            }
            count++;
        }
        System.out.println();
    }
}