package com.zwg.javabase.rabbitmq.helloworld.dlx;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.zwg.javabase.rabbitmq.helloworld.BaseConnection;


import java.io.IOException;
import java.sql.Time;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @Author: 张文刚
 * @Date: 2019/03/04  21:27
 * @Version: V1.0
 * @Description:
 *
 * dlx dead letter exchange 死信交换器,指当一个队列中的消息变成死信以后,他能被重新发送到另外一个交换机中,这个交换机就被称为dlx,
 *
 * 消息变成死信的情况
 * 1:被拒绝
 * 2.过期
 * 3.队列已经达到最大长度
 *
 *
 *
 */
public class DlxExchange extends BaseConnection {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        Connection connection = getConnection();
        Channel channel = connection.createChannel();
        Map<String, Object> map = new HashMap<>();
        channel.exchangeDeclare("exchange.normal","direct",true,false,null);
        map.put("x-message-ttl",6000);//过期时间6000
        map.put("x-dead-letter-exchange","exchange.dlx");
        map.put("x-dead-letter-routing-key","routingkey.dlx");
        channel.queueDeclare("normal queue",true,false,false,map);

        channel.queueBind("normal queue","exchange.normal","normal queue");
        String message="hello dlx 60000 ";
        for (int i = 0; i <10 ; i++) {
            TimeUnit.SECONDS.sleep(3);
            channel.basicPublish("exchange.normal","normal queue",true,null,message.getBytes("UTF-8"));
        }
        channel.close();
        connection.close();
    }
}
