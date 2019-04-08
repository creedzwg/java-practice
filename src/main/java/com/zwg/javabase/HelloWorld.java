package com.zwg.javabase;


import com.zwg.javabase.dynamicproxy.AA;
import org.springframework.beans.BeanUtils;

/**
 * @Author: 张文刚
 * @Date: 2019/02/02  19:29
 * @Version: V1.0
 * @Description:
 */
public class HelloWorld {


    public static void main(String[] args) {
        AA aa = new AA(212121L, 1, 2, 3);
        AA.CustomerVO customerVO = new AA.CustomerVO();
        customerVO.setName("张文刚");
        aa.setCustomer(customerVO);
        BeanUtils.copyProperties(aa, customerVO);
        System.out.println(aa);


    }
}
