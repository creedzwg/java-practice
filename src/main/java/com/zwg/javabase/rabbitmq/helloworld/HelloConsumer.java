package com.zwg.javabase.rabbitmq.helloworld;

import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @Author: 张文刚
 * @Date: 2019/02/28  22:02
 * @Version: V1.0
 * @Description:
 */
public class HelloConsumer {


    private static final String QUEUE_NAME = "queue demo ";
    private static final String IP_ADDRESS = "192.168.10.143";
    private static final int PORT = 5672 ;
    public static final  String USER="zwg";
    public static final  String PASSWORD="123456";
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = getConnection();

        final Channel channel = connection.createChannel() ; //创建信道
        channel.basicQos(64) ; // 设置客户端最多接收未被ack 的消息的个数
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel){

            /**
             * No-op implementation of {@link Consumer#handleDelivery}.
             *
             * @param consumerTag
             * @param envelope
             * @param properties
             * @param body
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("receiv+"+new String(body));

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                channel.basicAck(envelope.getDeliveryTag(),false);

            }

        };
        channel.basicConsume(QUEUE_NAME,defaultConsumer);
        TimeUnit.SECONDS.sleep(5);
        channel.close();
        connection.close();

    }

    @Test
    public void consumer1() throws IOException, TimeoutException, InterruptedException {
        Connection connection = getConnection();
        Channel channel = connection.createChannel();
        channel.basicQos(64);
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel){

            /**
             * No-op implementation of {@link Consumer#handleDelivery}.
             *
             * @param consumerTag
             * @param envelope
             * @param properties
             * @param body
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("receiv+"+new String(body));
                System.out.println(consumerTag);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //channel.basicAck(envelope.getDeliveryTag(),false);
                System.out.println("ok");
            }

        };
        channel.basicConsume("topic queue 2",defaultConsumer);
       TimeUnit.SECONDS.sleep(50);
        channel.close();
        connection.close();
    }



    private static Connection getConnection() throws IOException, TimeoutException {
        Address[] addresses = new Address[] {
                new Address(IP_ADDRESS , PORT)};
        ConnectionFactory connectionFactory  = new ConnectionFactory();
        connectionFactory.setUsername(USER);
        connectionFactory.setPassword(PASSWORD);
//这里的连接方式与生产者的demo 略有不同， 注意辨别区别
        return connectionFactory.newConnection(addresses);
    }




}
