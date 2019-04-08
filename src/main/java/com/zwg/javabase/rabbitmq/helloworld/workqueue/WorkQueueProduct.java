package com.zwg.javabase.rabbitmq.helloworld.workqueue;

import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeoutException;

/**
 * @Author: 张文刚
 * @Date: 2019/03/04  10:24
 * @Version: V1.0
 * @Description:
 */
public class WorkQueueProduct {
    private static final String  EXCHANGE_NAME = "exchange demo";

    private static  final String ROUTING_KEY = "routingkey demo";

    private final static String EXCLUSIVE_QUEUE_NAME = "exclusive work queue";

    private final static String AUTODELETE_QUEUE_NAME = "autodelete work queue";

    private final static String QUEUE_NAME = "work queue";

    private static final String IP_ADDRESS = "192.168.10.143";

    private static final int PORT = 5672;//RabbitMQ 服务端默认端口号为5672

    public static final  String USER="zwg";
    public static final  String PASSWORD="123456";


    private static Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(IP_ADDRESS);
        connectionFactory.setPort(PORT);
        connectionFactory.setUsername(USER);
        connectionFactory.setPassword(PASSWORD);
        connectionFactory.setVirtualHost("/");
        //通过连接工厂建立连接
        return connectionFactory.newConnection();
    }

    public static void main(String[] args) throws IOException, TimeoutException {
//        Connection connection = getConnection();
//        Channel channel = connection.createChannel();
//        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
//        Channel channel2 = connection.createChannel();
////        channel2.queueDeclare(QUEUE_NAME+"3",false,false,false,null);
////        channel2.queueDeclare(QUEUE_NAME+"3",false,true,false,null);
////        channel.basicPublish("","hello",false,);
////        String message="hello word";
//        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
//        channel.close();
//        channel2.basicPublish("", QUEUE_NAME, null, message.getBytes());
//        channel2.close();
//        connection.close();

        Connection connection = getConnection();
        Channel channel = connection.createChannel();
       channel.queueDeclare(QUEUE_NAME,true,false,true,null);
        String message="hello word 竞争 ";
        for (int i = 0; i <20 ; i++) {
            channel.basicPublish("", QUEUE_NAME, null, (message+i).getBytes());
        }

        channel.close();
        connection.close();



    }

    //queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments) throws IOException;

    /**
     *   queue:队列名称
     *   durable:队列是否持久化,true则会持久化,当rabbitmq服务自动重启后会重新创建队列(rabbit内部对持久化队列或者exchange会有保存),false则不会
     *
     *   exclusive:独占;是否排他;true则表示只有同一个channel下的生产者,消费者才能将消息生产到队列和从队列中消费消息,
     *
      * autoDelete:是否自动删除;true时,表示当与队列绑定的消费者全部断开关闭连接时,队列会自动删除
     */
    @Test
    public void exclusiceQueueConsumer() throws IOException, TimeoutException {
        Connection connection = getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(EXCLUSIVE_QUEUE_NAME,true,true,false,null);
        String message="hello word exclusive ";
        channel.basicPublish("", EXCLUSIVE_QUEUE_NAME, null, message.getBytes());
        channel.close();
        connection.close();

    }
    @Test
    public void autoDeleteQueueConsumer() throws IOException, TimeoutException {
        Connection connection = getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(EXCLUSIVE_QUEUE_NAME,true,false,true,null);
        String message="hello word exclusive ";
        channel.basicPublish("", EXCLUSIVE_QUEUE_NAME, null, message.getBytes());
        channel.close();
        connection.close();

    }

}
