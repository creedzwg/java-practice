package com.zwg.javabase.connectionpool;

import javax.security.auth.login.Configuration;
import java.util.LinkedList;
import java.util.stream.IntStream;

/**
 * @Author: 张文刚
 * @Date: 2019/02/21  21:43
 * @Version: V1.0
 * @Description:连接池:通过构造函数初始化连接的最大上限,同时通过一个双向链表来维护连接,调用方通过getConnection(long)方法在多少毫秒内超时获取连接, 当连接完成以后, 需要调用closeConnection(Connection)方法将连接返回连接池
 */
public class ConnectionPool {


    private LinkedList<Connection> connections = new LinkedList<>();

    private final static int DEFAULT_SIZE = 2;

    private int defaultSize;

    public ConnectionPool(int defaultSize) {
        if (defaultSize < 0) {
            throw new RuntimeException("asas");
        }
        this.defaultSize = defaultSize;
        init(defaultSize);
    }

    private void init(int defaultSize) {
        if (defaultSize > 0) {
            IntStream.range(0, defaultSize).forEach(value -> connections.addLast(new Connection()));
        }
    }

    private void closeConnection(Connection connection) {
        synchronized (connections) {
            if (connection != null) {
                connections.addLast(connection);
                connections.notifyAll();
            }
        }
    }

    //获取连接超时直接返回
    private Connection getConnection(long mills) {
        synchronized (connections) {
            //直接等待
            if (mills <= 0) {
                while (connections.isEmpty()) {
                    try {
                        connections.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return connections.pollFirst();
            } else {
                long future = System.currentTimeMillis() + mills;
                long remain = mills;
                while (remain > 0 && connections.isEmpty()) {
                    try {
                        connections.wait(remain);
                        remain = future - System.currentTimeMillis();
                        System.out.println("剩余remain----" + remain);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return connections.pollFirst();


            }

        }


    }

    public static void main(String[] args) {
        ConnectionPool connectionPool = new ConnectionPool(2);

        Connection connection = connectionPool.getConnection(-1);

        Connection connection2 = connectionPool.getConnection(-1);
        Connection connection3 = connectionPool.getConnection(5000);
        System.out.println(connection);
        System.out.println(connection2);
        System.out.println(connection3);


    }
}
