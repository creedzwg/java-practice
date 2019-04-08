package com.zwg.javabase.rabbitmq.helloworld.dlx;

import com.rabbitmq.client.*;
import com.zwg.javabase.rabbitmq.helloworld.BaseConnection;


import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @Author: 张文刚
 * @Date: 2019/03/04  22:22
 * @Version: V1.0
 * @Description:
 */
public class NormalConsumer extends BaseConnection {

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = BaseConnection.getConnection();
        Channel channel = connection.createChannel();

        channel.basicConsume(FANOUT_QUEUE_NAME_1,new DefaultConsumer(channel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("fanout receive2+"+new String(body,"UTF-8"));
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    //手动ack
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }

            }
        });
    }
}
