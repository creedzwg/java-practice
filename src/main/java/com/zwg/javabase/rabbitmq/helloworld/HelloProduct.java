package com.zwg.javabase.rabbitmq.helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author: 张文刚
 * @Date: 2019/02/28  22:01
 * @Version: V1.0
 * @Description:
 */
public class HelloProduct {

    private static final String  EXCHANGE_NAME = "exchange demo";

    private static  final String ROUTING_KEY = "routingkey demo";

    private static final String QUEUE_NAME = "queue demo ";

    private static final String IP_ADDRESS = "192.168.10.143";

    private static final int PORT = 5672;//RabbitMQ 服务端默认端口号为5672

    public static final  String USER="zwg";
    public static final  String PASSWORD="123456";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = getConnection();
        //创建channel
        Channel channel = connection.createChannel();
         //创建一个持久的,"direct"类型的,非自动删除的交换器
        channel.exchangeDeclare(EXCHANGE_NAME,"direct",true,false,null);
        //创建一个持久的,非排他的,非自动删除的队列
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        //将交换机与队列通过路由键绑定
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,ROUTING_KEY);

        String message="hello world";

        channel.addReturnListener((replyCode, replyText, exchange, routingKey, properties, body) -> {
            System.out.println(body.toString());
            System.out.println(routingKey);
            System.out.println(exchange);
            System.out.println(replyCode);
            System.out.println(replyText);
        });
        channel.basicPublish(EXCHANGE_NAME,"", true,MessageProperties.PERSISTENT_BASIC,message.getBytes());
        channel.close();
        connection.close();

    }

    @Test
    public void testTopic() throws IOException, TimeoutException {

        Connection connection = getConnection();
        Channel channel = connection.createChannel();
        Channel channel2 = connection.createChannel();
        System.out.println(channel==channel2);
        channel.exchangeDeclare("topic exchange","topic",true,false,null);
       // channel.queueDeclare("topic queue 1",true,false,false,null);
        channel.queueDeclare("topic queue 3",true,false,false,null);

       // channel.queueBind("topic queue 1","topic exchange","*.zwg.rabbit");
        channel.queueBind("topic queue 3","topic exchange","*.zwg.rabbit");
        String message="hello world topic 343 ";

        channel.basicPublish("topic exchange","",true, MessageProperties.PERSISTENT_BASIC,message.getBytes());
        channel.addReturnListener((replyCode, replyText, exchange, routingKey, properties, body) -> {
            System.out.println("111");
            System.out.println(body.toString());
            System.out.println(routingKey);
            System.out.println(exchange);
            System.out.println(replyCode);
            System.out.println(replyText);
        });
        channel.close();
        connection.close();

    }

    private static Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(IP_ADDRESS);
        connectionFactory.setPort(PORT);
        connectionFactory.setUsername(USER);
        connectionFactory.setPassword(PASSWORD);
        //通过连接工厂建立连接
        return connectionFactory.newConnection();
    }


}
