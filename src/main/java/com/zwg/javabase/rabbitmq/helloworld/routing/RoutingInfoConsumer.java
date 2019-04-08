package com.zwg.javabase.rabbitmq.helloworld.routing;

import com.rabbitmq.client.*;
import com.zwg.javabase.rabbitmq.helloworld.BaseConnection;


import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @Author: 张文刚
 * @Date: 2019/03/04  18:05
 * @Version: V1.0
 * @Description:
 */
public class RoutingInfoConsumer extends BaseConnection {

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("routing info queue",true,false,false,null);
        channel.queueBind("routing info queue",ROUTING_EXCHANGE_NAME,"info");
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
        channel.basicConsume("routing info queue",defaultConsumer);

    }
}
