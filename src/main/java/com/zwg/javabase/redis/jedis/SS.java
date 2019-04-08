package com.zwg.javabase.redis.jedis;

/**
 * @Author: 张文刚
 * @Date: 2019/03/11  11:48
 * @Version: V1.0
 * @Description:
 */
public class SS {

    public static void main(String[] args) {
//nums = [2, 7, 11, 15], target = 9
        int[] ints = twoSum(new int[]{2, 7, 11, 15}, 9);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }

    public static int[] twoSum(int[] nums, int target) {

        int[] nums2 = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (target < num) {
                continue;
            }
            nums2[i] = i;
            target -= num;
            if (target == 0) {
                break;
            }

        }
        return nums2;


    }
}
