package com.cat.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {
    private ArrayList<String> vertexList;//存储顶点集合
    private int[][] edges;//存储图对应的邻接矩阵
    private int numOfEdges;//表示边的数目
    //定义数组boolean[],记录某个顶点是否被访问
    private boolean[] isVisited;
    public static void main(String[] args) {
        //测试
        int n = 5;//结点的个数
        String[] VertexValue = {"A","B","C","D","E"};
        //创建图对象
        Graph graph = new Graph(n);
        //循环添加顶点
        for(String vertex : VertexValue){
            graph.insertVertex(vertex);
        }
        //添加边
        //A-B A-C B-C B-D B-E
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);
        //显示邻接矩阵
        graph.showGraph();

        //测试深度优先
        System.out.println("深度优先");
//        graph.dfs();

        //测试广度优先
        System.out.println("广度优先");
        graph.bfs();
    }
    //构造器
    public Graph(int n) {
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOfEdges = 0;

    }
    //得到第一个邻接节点的下标w

    /**
     * @param index
     * @return 如果存在就返回对应的下标，否则返回-1
     */
    public int getFirstNeighbor(int index){
        for(int j = 0;j < vertexList.size();j++){
            if(edges[index][j] > 0){
                return j;
            }
        }
        return -1;
    }
    //根据前一个邻接节点的下标来获取下一个邻接节点
    public int getNextNerghbor(int v1,int v2){
        for(int j = v2 + 1;j < vertexList.size();j++){
            if(edges[v1][j] > 0){
                return j;
            }
        }
        return -1;
    }
    //广度优先搜索算法
    private void bfs(boolean[] isVisited,int i){
        int u;//表示队列的头结点对应的下标
        int w;//邻接结点w
        //队列，记录结点的访问顺序
        LinkedList queue = new LinkedList();
        //首先访问该结点，输出
        System.out.print(getValueByIndex(i) + "->");
        //将结点设置为已经访问过
        isVisited[i] = true;
        //将结点加入队列
        queue.addLast(i);
        //当队列不为空
        while(!queue.isEmpty()){
            //取出队列的头结点的下标
            u = (Integer)queue.removeFirst();
            w = getFirstNeighbor(u);
            while(w != -1){//说明有邻接节点
                if(!isVisited[w]){
                    System.out.print(getValueByIndex(w) + "->");
                    //标记已经访问，并加入队列
                    isVisited[w] = true;
                    queue.addLast(w);
                }
                //以u为前驱点，找w后的下一个邻接结点
                w = getNextNerghbor(u,w);//体现广度优先
            }
        }
    }
    //遍历所有的结点，都进行广度优先搜索
    public void bfs(){
        isVisited = new boolean[vertexList.size()];
        for (int i = 0; i < getNumOfVeretx(); i++) {
            if(!isVisited[i]){
                bfs(isVisited,i);
            }
        }
    }
    //深度优先搜索算法
    //i第一次为 0
    private void dfs(boolean[] isVisited,int i){
        //首先访问该结点，输出
        System.out.print(getValueByIndex(i) + "->");
        //将结点设置为已经访问过
        isVisited[i] = true;
        //查找结点i的第一个邻接节点w
        int w = getFirstNeighbor(i);
        while(w != -1){//说明有邻接节点
            if(!isVisited[w]){
                dfs(isVisited,w);
            }
            //如果w结点已经被访问过
            w = getNextNerghbor(i,w);//i为正在访问的结点，w为找到的下一个被访问过的
        }
    }
    //对dfs进行重载，遍历所有结点，并进行dfs
    public void dfs(){
        isVisited = new boolean[vertexList.size()];
        //遍历所有结点进行dfs
        for(int i = 0;i < getNumOfVeretx();i++){
            if(!isVisited[i]){
                dfs(isVisited,i);
            }
        }
    }
    //图中常用的方法
    //返回结点的个数
    public int getNumOfVeretx(){
        return vertexList.size();
    }
    //显示图对应的矩阵
    public void showGraph(){
        for(int[] link : edges){
            System.out.println(Arrays.toString(link));
        }
    }
    //得到边的个数
    public int getNumOfEdges(){
        return numOfEdges;
    }
    //返回结点i（下标）对应的数据 0->"A" 1->"B" 2->"C"
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }
    //返回v1 和 v2 的权值
    public int getWeight(int v1,int v2){
        return edges[v1][v2];
    }
    //插入结点
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }

    /**
     * 添加边
     * @param v1 表示点的下标 即第几个顶点 矩阵里的行列
     * @param v2 第二个顶点对应
     * @param weight
     */
    public void insertEdge(int v1,int v2,int weight){
         edges[v1][v2] = weight;
         edges[v2][v1] = weight;
         numOfEdges++;
    }
}
