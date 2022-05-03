package Sync;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Description
 *
 * @author tyw
 * @since 2022/4/20
 * 锁降级
 * 写锁可以降级为读锁，写的时候可以读
 * 读锁不能升级为写锁，读的时候不能写
 */

public class ReadWriteDemo02 {
    public static void main(String[] args) {
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        Lock readLock = readWriteLock.readLock();
        Lock writeLock = readWriteLock.writeLock();

        //锁降级过程 先上写锁 再上读锁，释放写锁，此时整体降级为读锁，最后释放读锁，完成
        writeLock.lock();
        System.out.println("111111");
        readLock.lock();
        System.out.println("22222222222");
        writeLock.unlock();
        readLock.unlock();

//        readLock.lock();
//        System.out.println("111111");
//        writeLock.lock();
//        System.out.println("先上读锁再上写锁是不行的，这句话不能输出");
//        readLock.unlock();
//        writeLock.unlock();


    }
}
