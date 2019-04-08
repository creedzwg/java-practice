package com.zwg.javabase.nio;

import org.junit.Test;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * @Author: 张文刚
 * @Date: 2019/04/04  22:45
 * @Version: V1.0
 * @Description:
 */
public class NioTest {


    /**  channel 提供了输入输出,传输数据的一种通道
     * 缓存区是正在存取数据的地方,他的底层是数组,数组可以存取不同类型的元素,所以Buffer也分为很多种
     *
     * 2.缓冲区存取数据的两个核心方法
     * buffer.put() 向缓冲区写入数据
     * buffer.get() 从缓存区读取数据
     *
     * 3. 缓冲区的4个核心属性
     * capacity :容量,表示缓冲区中最大存储数据的容量,一旦声明不能改变
     * limit: 界限,表示缓冲区可以操作数据的大小,(limit后数据不能读写)
     * position:位置,表示缓冲区正在操作数据的位置
     *  mark:记录position的位置,mark(){mark=position}可以调用resert()恢复到position上一次的位置
     *
     *
     */


    @Test
    public void test1(){


        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        print(byteBuffer);

        String data="hello world";
        String data2="2hello world2";
        String data3="wewew";
        byteBuffer.put(data.getBytes(Charset.defaultCharset()));
        byteBuffer.put(data2.getBytes(Charset.defaultCharset()));
        byteBuffer.put(data3.getBytes(Charset.defaultCharset()));


        print(byteBuffer);
        //将写模式变成读模式
        byteBuffer.flip();
        byte[] bytes = {byteBuffer.get(0)};
        System.out.println(new String(bytes,0,bytes.length,Charset.defaultCharset()));
        print(byteBuffer);
        //清空缓冲区,但是数据还在,只是position和limit回到最初
        byteBuffer.clear();
        print(byteBuffer);
        byteBuffer.mark();
        byteBuffer.reset();
        print(byteBuffer);
    }

    private void print(ByteBuffer byteBuffer) {
        System.out.println("------------------------------------");
        System.out.println("capacity::"+byteBuffer.capacity());
        System.out.println("limit::"+byteBuffer.limit());
        System.out.println("position::"+byteBuffer.position());
    }
}
