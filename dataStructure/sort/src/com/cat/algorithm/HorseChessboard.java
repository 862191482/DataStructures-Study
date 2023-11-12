package com.cat.algorithm;

import java.awt.*;
import java.util.ArrayList;

public class HorseChessboard {
    private static int X;//棋盘的列
    private static int Y;//棋盘的行
    //标记棋盘的各个位置是否被访问过
    private static boolean[] visited;
    //标记是否棋盘的所有位置都被访问
    private static boolean finished;//如果为true,表示成功
    public static void main(String[] args) {
        X = 8;
        Y = 8;
        int row = 1;//马儿初始位置的行，从1开始编号
        int column = 1;//马儿初始位置的列，从1开始编号
        //创建棋盘
        int[][] chessboard = new int[X][Y];
        visited = new boolean[X * Y];//初始值为false
        long start = System.currentTimeMillis();
        traveraslChessboard(chessboard, row - 1, column - 1, 1);
        long end = System.currentTimeMillis();
        System.out.println("共耗时："+(end - start)+"毫秒");
        //输出棋盘最后的情况
        for (int[] rows : chessboard) {
            for (int step : rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }
    }
    /**
     * 其实周游问题的算法
     * @param chessboard 棋盘
     * @param row 马儿当前位置的行 0开始
     * @param column 马儿当前位置的列 0开始
     * @param step 第几步，1开始
     */
    public static void traveraslChessboard(int[][] chessboard, int row, int column, int step){
        chessboard[row][column] = step;
        visited[row * X + column] = true;//标记改位置已访问
        //获取当前位置可以走的下一个位置的集合
        ArrayList<Point> ps = next(new Point(column, row));
        //对ps进行排序，排序规则就是对ps的所有Point对象的下一步位置的数目，进行排序
        sort(ps);
        //遍历ps
        while(!ps.isEmpty()){
            //remove()如果传入元素，删除成功，则返回 true。
            //remove()如果传入索引值，则返回删除的元素。
            Point p = ps.remove(0);//取出下一个可以走的位置
            //判断该点是否已经访问过
            if (!visited[p.y * X + p.x]){//没有访问过
                traveraslChessboard(chessboard, p.y, p.x, step+1);
            }
        }
        //判断马儿是否完成了任务，使用step和应该走的步数比较，
        // 如果没有达到数量，则表示没有完成任务，将整个棋盘置0
        //step < X * Y成立的情况
        //1.棋盘到目前位置，仍然没有走完
        //2.棋盘处于回溯
        if(step < X * Y && !finished){
            chessboard[row][column] = 0;
            visited[row * X + column] = false;
        }else{
            finished = true;
        }
    }
    /**
     * 根据当前位置（Point对象），计算马儿还能走哪些位置（Point）,并放到一个集合中（ArrayList），最多8个位置
     * @param curPoint
     * @return
     */
    public static ArrayList<Point> next(Point curPoint){
        ArrayList<Point> ps = new ArrayList<>();
        Point p1 = new Point();
        //马儿可以走5
        if((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0){
            ps.add(new Point(p1));
        }
        //马儿可以走6
        if((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0){
            ps.add(new Point(p1));
        }
        //马儿可以走7
        if((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0){
            ps.add(new Point(p1));
        }
        //马儿可以走0
        if((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0){
            ps.add(new Point(p1));
        }
        //马儿可以走1
        if((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y){
            ps.add(new Point(p1));
        }
        //马儿可以走2
        if((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y){
            ps.add(new Point(p1));
        }
        //马儿可以走3
        if((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y){
            ps.add(new Point(p1));
        }
        //马儿可以走4
        if((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y){
            ps.add(new Point(p1));
        }
        return ps;
    }
    //根据当前这一步的所有的下一步的选择位置，进行非递减排序,减少回溯的次数
    public static void sort(ArrayList<Point> ps){
        ps.sort((o1,o2)-> next(o1).size()-next(o2).size());
    }
}
