package com.zwg.javabase.rabbitmq.helloworld.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.zwg.javabase.rabbitmq.helloworld.BaseConnection;


import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author: 张文刚
 * @Date: 2019/03/04  18:01
 * @Version: V1.0
 * @Description: direct routingkey 与bindingKey完全匹配
 */
public class TopicProduct extends BaseConnection {

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(TOPIC_EXCHANGE_NAME,"topic",true,false,null);
        String message="hello topic  topic模式";
        channel.basicPublish(TOPIC_EXCHANGE_NAME,"com.zwg.topic",true,null,message.getBytes("UTF-8"));
        channel.addReturnListener((replyCode, replyText, exchange, routingKey, properties, body) -> {
        System.out.println(new String(exchange.getBytes(),"UTF-8"));
          System.out.println(properties.getContentType());
            System.out.println("return message");
        });
        channel.close();
        connection.close();
    }



}
