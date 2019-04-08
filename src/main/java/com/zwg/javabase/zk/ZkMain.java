package com.zwg.javabase.zk;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: 张文刚
 * @Date: 2019/03/14  20:13
 * @Version: V1.0
 * @Description:
 */
public class ZkMain  {
    public static final String ZKURL="47.101.51.192:2181,47.101.51.192:2182,47.101.51.192:2183";




    public static void main(String[] args) throws Exception {
        ZooKeeperWatch zooKeeperWatch=new ZooKeeperWatch();
        //同步计数
        CountDownLatch countDownLatch = new CountDownLatch(1);
        //因为创建zk客户端的过程是异步的,必须要等到连接创建完毕以后才能进行操作
        ZooKeeper zooKeeper=new ZooKeeper(ZKURL, 5000,zooKeeperWatch );
        countDownLatch.await();
        //同步创建持久节点
       
        String s = zooKeeper.create("/zwg/cj", "Hello word".getBytes("UTF-8"), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(s);
    }
     static class ZooKeeperWatch implements Watcher{

        @Override
        public void process(WatchedEvent event) {
            Event.KeeperState state = event.getState();
            Event.EventType type = event.getType();
            if(Event.KeeperState.SyncConnected==state){
                if(Event.EventType.None==type){

                }

            }
        }
    }

}
