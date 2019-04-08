package com.zwg.javabase.rabbitmq.helloworld.subpub;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.zwg.javabase.rabbitmq.helloworld.BaseConnection;


import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author: 张文刚
 * @Date: 2019/03/04  16:01
 * @Version: V1.0
 * @Description: 订阅/发布模式 需要使用到exchange
 */
public class FanoutProduct extends BaseConnection {

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = BaseConnection.getConnection();
        Channel channel = connection.createChannel();
        //声明为exchange为fanout模式
        channel.exchangeDeclare(FANOUT_EXCHANGE_NAME,"fanout",true,false,null);
//        channel.queueDeclare(FANOUT_QUEUE_NAME_1, true, false, false,null);
        //多个队列绑定到一个交换机(相当于订阅,如果不在生产者端订阅,也可以在消费者端订阅,也可以同时订阅)
//        channel.queueBind(FANOUT_QUEUE_NAME_1,FANOUT_EXCHANGE_NAME,"fanout");
//        channel.queueBind(FANOUT_QUEUE_NAME_2,FANOUT_EXCHANGE_NAME,"fanout");
        String msg="hello fanout 发布订阅";
        channel.basicPublish(FANOUT_EXCHANGE_NAME,"",true,null,msg.getBytes("UTF-8"));

        channel.close();
        connection.close();

    }
}
