package com.mytest.impl;

import com.mytest.ZkDistriLockService;
import org.apache.zookeeper.*;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by caijing on 2017/6/16.
 */
public class ZkDistriLockServiceImpl implements ZkDistriLockService {

    private final String root = "/zk-distributedLock";
    private final String node = "Lock";
    private final String lock = root + "/" + node;
    @Override
    public boolean tryLock(Callback callback, long timeout) throws  Exception{
        ZooKeeper zk = getZookeeper();
        List<String> nodes = zk.getChildren(root, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                if(Event.KeeperState.SyncConnected == event.getState()
                        && event.getType() == Event.EventType.NodeChildrenChanged){
                    doLock(zk, callback, timeout);
                }
            }
        });
        // 没有人持有锁则尝试获取锁
        if (!nodes.contains(lock)) {
            doLock(zk, callback, timeout);
        }
        return false;
    }

    /**
     * 具体执行分布式锁，如果拥有分布式锁则执行callback回调，然后释放锁
     *
     * @param zk
     * @param callback
     * @param expireTime 过期时间
     */
    private void doLock(ZooKeeper zk, Callback callback, long expireTime) {
        try {
            if (expireTime > 0 && System.currentTimeMillis() > expireTime) {
                callback.expired();
                return;
            }
            String path = zk.create(lock, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            System.out.println(path);
            if (path != null) {//成功获取锁，执行业务逻辑
                callback.execute();
                zk.delete(lock, -1); // 完成之后释放锁
            }
        } catch (Exception e) {

        } finally {
            try {
                zk.close();
            } catch (InterruptedException e) {

            }
        }
    }


    public ZooKeeper getZookeeper() throws Exception{
        final CountDownLatch latch = new CountDownLatch(1);//如果未成功建立连接，不返回zk对象，有线程阻塞风险
        ZooKeeper zk = new ZooKeeper("", 2000, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                if (Event.KeeperState.SyncConnected == event.getState()) {
                    //如果客户端已经建立连接闭锁减一
                    latch.countDown();
                }
            }
        });

        // 等待连接建立
        latch.await();
        return zk;
    }
}
