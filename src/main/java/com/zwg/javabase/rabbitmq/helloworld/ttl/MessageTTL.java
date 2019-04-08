package com.zwg.javabase.rabbitmq.helloworld.ttl;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.zwg.javabase.rabbitmq.helloworld.BaseConnection;


import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author: 张文刚
 * @Date: 2019/03/04  20:25
 * @Version: V1.0
 * @Description: 为每一条消息设置过期时间,当消息过期以后,消息就会变成dead message (死信),无法再次被消费(除非设置了死信)
 */
public class MessageTTL extends BaseConnection {


    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = getConnection();
        Channel channel = connection.createChannel();

        String message="hello ttl message ";
        AMQP.BasicProperties basicProperties=new AMQP.BasicProperties();
        AMQP.BasicProperties build = basicProperties.builder().expiration("0").build();
        channel.basicPublish(TOPIC_EXCHANGE_NAME,"com.zwg.topic",true,build,message.getBytes("UTF-8"));
        channel.addReturnListener((replyCode, replyText, exchange, routingKey, properties, body) -> {
            System.out.println("回来了"+replyText);
        });
        channel.close();
        connection.close();
    }



}
