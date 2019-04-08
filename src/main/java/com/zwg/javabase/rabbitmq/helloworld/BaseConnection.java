package com.zwg.javabase.rabbitmq.helloworld;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author: 张文刚
 * @Date: 2019/03/04  16:04
 * @Version: V1.0
 * @Description:
 */
public class BaseConnection {


    public static final String  EXCHANGE_NAME = "exchange demo";
    public static final String  FANOUT_EXCHANGE_NAME = "fanout exchange demo";
    public static final String  ROUTING_EXCHANGE_NAME = "routing exchange demo";
    public static final String  TOPIC_EXCHANGE_NAME = "topic exchange demo";

    public static  final String ROUTING_KEY = "routingkey demo";

    public final static String QUEUE_NAME = "work queue";
    public final static String EXCLUSIVE_QUEUE_NAME = "exclusive work queue";
    public final static String AUTODELETE_QUEUE_NAME = "autodelete work queue";
    public final static String FANOUT_QUEUE_NAME_1 = "fanout  queue 1";
    public final static String FANOUT_QUEUE_NAME_2 = "fanout  queue 2";
    public static final String IP_ADDRESS = "192.168.10.143";

    public static final int PORT = 5672;//RabbitMQ 服务端默认端口号为5672

    public static final  String USER="zwg";
    public static final  String PASSWORD="123456";


    public static Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(IP_ADDRESS);
        connectionFactory.setPort(PORT);
        connectionFactory.setUsername(USER);
        connectionFactory.setPassword(PASSWORD);
        connectionFactory.setVirtualHost("/");
        //通过连接工厂建立连接
        return connectionFactory.newConnection();
    }
}
