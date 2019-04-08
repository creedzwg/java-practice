package com.zwg.javabase.rabbitmq.helloworld.workqueue;

import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @Author: 张文刚
 * @Date: 2019/03/04  10:40
 * @Version: V1.0
 * @Description:
 */
public class WorkQueueConsumer2 {
    private static final String  EXCHANGE_NAME = "exchange demo";

    private static  final String ROUTING_KEY = "routingkey demo";

    private final static String QUEUE_NAME = "work queue";
    private final static String EXCLUSIVE_QUEUE_NAME = "exclusive work queue";
    private final static String AUTODELETE_QUEUE_NAME = "autodelete work queue";
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
//
//        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
//        DefaultConsumer defaultConsumer = new DefaultConsumer(channel){
//            @Override
//            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                System.out.println("receive+"+ new String(body, Charset.forName("UTF-8")));
//                channel.basicAck(envelope.getDeliveryTag(),false);
//
//            }
//
//        };
//        channel.basicConsume(QUEUE_NAME,defaultConsumer);

        Connection connection = getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,true,false,true,null);
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("receive+"+ new String(body, Charset.forName("UTF-8")));
                channel.basicAck(envelope.getDeliveryTag(),false);
            }

        };
        channel.basicConsume(QUEUE_NAME,defaultConsumer);
    }


    @Test
    public void exclusiceQueueConsumer() throws IOException, TimeoutException, InterruptedException {

        Thread thread = new Thread(() -> {
            Connection connection = null;
            try {
                connection = getConnection();
                Channel channel = connection.createChannel();
                channel.queueDeclare(EXCLUSIVE_QUEUE_NAME, true, true, false, null);
                DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
                    @Override
                    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                        System.out.println("receive+" + new String(body, Charset.forName("UTF-8")));
                        channel.basicAck(envelope.getDeliveryTag(), false);

                    }

                };
                channel.basicConsume(QUEUE_NAME, defaultConsumer);
            } catch (Exception e) {
                e.printStackTrace();
            }


        });
        thread.start();
        thread.join();
        TimeUnit.MINUTES.sleep(10);


    }


}
