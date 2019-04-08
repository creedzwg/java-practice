package com.zwg.javabase.binarySearch;

/**
 * @Author: 张文刚
 * @Date: 2019/03/16  19:55
 * @Version: V1.0
 * @Description:
 */
public class BinarySearchDemo {

    public static void main(String[] args) {
        BinarySearchDemo binarySearchDemo = new BinarySearchDemo();
        int[] arrs=new int[]{1,2,3,4,5,6,7,8};
        System.out.println(binarySearchDemo.binarySearch(arrs,7));
    }



    public  Integer binarySearch(int[] arrs,int parm){
       int min=0;
       int max=arrs.length-1;

       while (min<=max){
           int mid = (max + min) >>> 1;
           if( arrs[mid] <parm){
              min=mid+1;
           }
           else if (arrs[mid]>parm){
              max=mid-1;
           }else{
               return mid;
           }

       }
       return -1;//未找到



    }
}
