package com.zwg.javabase.rabbitmq.helloworld.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.zwg.javabase.rabbitmq.helloworld.BaseConnection;


import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeoutException;

/**
 * @Author: 张文刚
 * @Date: 2019/03/04  18:01
 * @Version: V1.0
 * @Description: direct routingkey 与bindingKey完全匹配
 */
public class RoutingProduct extends BaseConnection {

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(ROUTING_EXCHANGE_NAME,"direct",true,false,null);
        String message="hello direct  路由模式";
        channel.basicPublish(ROUTING_EXCHANGE_NAME,"info",true,null,message.getBytes("UTF-8"));
        channel.addReturnListener((replyCode, replyText, exchange, routingKey, properties, body) -> {
         // System.out.println(new String(exchange.getBytes(),"UTF-8"));
          System.out.println(properties.getContentType());
            System.out.println("return message");
        });
        channel.close();
        connection.close();
    }



}
