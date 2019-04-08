package com.zwg.javabase.sotr;

import java.util.Arrays;
import java.util.Collections;

/**
 * @Author: 张文刚
 * @Date: 2019/04/01  14:27
 * @Version: V1.0
 * @Description:
 */
public class MySort {

    public static void main(String[] args) {
        MySort mySort = new MySort();
        int[] ints = mySort.sortArray(new int[]{2332, 1, 5, 3, 63, 434, 21, 21});
        System.out.println(Arrays.toString(ints));

    }

    public  int[] sortArray(int[] arr){

           if(arr==null|| arr.length<2){
               return arr;
           }
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                  if(arr[i]>arr[j]){
                      int temp = arr[i];
                      arr[i]=arr[j];
                      arr[j]=temp;
                  }
            }

        }
        return arr;
    }
}
