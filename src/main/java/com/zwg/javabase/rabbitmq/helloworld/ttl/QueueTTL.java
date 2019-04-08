package com.zwg.javabase.rabbitmq.helloworld.ttl;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.zwg.javabase.rabbitmq.helloworld.BaseConnection;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @Author: 张文刚
 * @Date: 2019/03/04  20:25
 * @Version: V1.0
 * @Description: 设置队列的ttl,如果不设置则表示消息永不会过期,如果设置消息的时间为0,则如果消息发送的所有队列中没有一个消费者正在消费,消息会直接丢弃,通过ttl可以勉强实现immediate的半个功能,因为immediate 为true,表示当前没有消费者时会将消息返回,通过ttl+dlx 可以实现
 * 如果队列和消息同时设置ttl,则取短的
 * 对队列设置ttl和对每一条消息设置过期日期结果是不一样的
 * 队列ttl当消息过期后,则会从队列中删除;而对每一条消息设置ttl,消息过期后不会立即从队列中删除,会在消费者进行消费的时候,才会删除
 *
 * 因为对队列设置ttl,则队列中的所有消息都会有同一个ttl,按照消息插入到队列中的顺序排序,那么只要定期扫描队列头部就可以删除过期队列;而每一条消息的ttl都可能不一样,如果要删除过期消息,那么必然要扫描整个队列,会有很大的消耗,这样不如在消费的时候判断.
 *
 *
 *
 */
public class QueueTTL extends BaseConnection {


    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = getConnection();
        Channel channel = connection.createChannel();
        Map<String, Object> map = new HashMap<>();
        map.put("x-message-ttl",0);
        channel.queueDeclare("ttl queue",true,false,false,map);
        //channel.queueBind("ttl queue",TOPIC_EXCHANGE_NAME,)
        String message="hello ttl queue ";
        channel.basicPublish("","ttl queue",true,null,message.getBytes("UTF-8"));
        channel.addReturnListener((replyCode, replyText, exchange, routingKey, properties, body) -> {
            System.out.println("回来了"+replyText);
        });
        channel.close();
        connection.close();
    }
}
