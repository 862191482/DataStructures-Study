package com.cat.sort;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MergetSort {

    public static void main(String[] args) {
        int arr[] = {8,4,7,5,1,3,2,6};
        int temp[] = new int[arr.length];
        mergeSort(arr,0, arr.length - 1,temp);
        System.out.println(Arrays.toString(arr));
    }
    //分+合的方法
    public static void mergeSort(int[] arr,int left,int right,int[] temp){
        if(left < right){
            int mid = (left + right) / 2;
            //向左递归进行分解
            mergeSort(arr,left,mid,temp);
            //向右递归进行分解
            mergeSort(arr,mid + 1,right,temp);
            //合并
            merge(arr,left,mid,right,temp);
        }
    }
    //合并的方法
    /**
     * @param arr 排序的原始数组
     * @param left 左边有序序列的初始索引
     * @param mid 中间索引
     * @param right 最右边索引
     * @param temp //中转数组
     */
    public static void merge(int[] arr,int left,int mid,int right,int[] temp){
        int i = left;//左边有序序列的初始索引
        int j = mid + 1;//右边有序序列的初始索引
        int t = 0;//temp数组的初始索引

        //先把左右两边(有序)的数据按照规则填充到temp数组
        // 直到左右两边的有序序列，有一边处理完毕为止
        while(i <= mid && j <= right){
            if(arr[i] <= arr[j]){
                temp[t] = arr[i];
                t += 1;
                i += 1;
            }else{
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }

        //把有剩余数据的一边的数据依次全部填充到temp
        while(i <= mid){
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }
        while(j <= right){
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }
        //将temp数组的元素拷贝到arr
        //注意，并不是每次都拷贝
        t = 0;
        int tempLeft = left;
        while(tempLeft <= right){
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }
    }

}
