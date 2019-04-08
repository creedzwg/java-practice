package com.zwg.javabase.rabbitmq.helloworld.subpub;

import com.rabbitmq.client.*;
import com.zwg.javabase.rabbitmq.helloworld.BaseConnection;


import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @Author: 张文刚
 * @Date: 2019/03/04  16:01
 * @Version: V1.0
 * @Description:  订阅/发布模式
 */
public class FanoutConsumer2 extends BaseConnection {

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = BaseConnection.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(FANOUT_EXCHANGE_NAME,"fanout",true,false,null);
        channel.queueDeclare(FANOUT_QUEUE_NAME_2,true,false,false,null);
        channel.queueBind(FANOUT_QUEUE_NAME_2,FANOUT_EXCHANGE_NAME,"wqwq");
        channel.basicConsume(FANOUT_QUEUE_NAME_2,new DefaultConsumer(channel){

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
