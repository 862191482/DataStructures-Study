package com.cat.tree;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        int arr[] = {4,6,8,5,9};
        heapSort(arr);
    }
    public static void heapSort(int arr[]){
        int temp = 0;
        System.out.println("堆排序");
        //将无序序列构建成一个堆，根据升降序需求选择大小顶堆
        for(int i = arr.length / 2 - 1;i >= 0;i--){
            adjustHeap(arr, i, arr.length);
        }
        //将堆顶元素与末尾元素交换，将最大元素“沉”到数组末端
        //重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序
        for(int j = arr.length - 1;j > 0;j--){
            //交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr,0, j);
        }
        System.out.println("数组="+ Arrays.toString(arr));
    }

    //将数组（二叉树）调整成大顶堆
    /**
     * @param arr 待调整数组
     * @param i 非叶子结点在数组中的索引
     * @param length 表示对多少个元素调整 length逐渐减少
     */
    public static void adjustHeap(int arr[],int i,int length){
        int temp = arr[i];
        //开始
        for(int k = i * 2 + 1;k < length;k = i * 2 + 1){
            //k = i * 2 + 1
            //左子结点小于右子结点
            if(k+1 < length && arr[k] < arr[k+1]){
                //k 指向右子结点
                k++;
            }
            //子结点大于父结点
            if(arr[k] > temp){
                arr[i] = arr[k];//把较大值赋给当前结点
                i = k;
            }else{
                break;
            }
            //当for循环结束后，我们已经将以i为父结点的树的最大值，放在了最顶端（局部）
            arr[i] = temp;//将temp的值放到调整后的位置
        }
    }
}
