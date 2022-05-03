package Sync;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description
 *
 * @author tyw
 * @since 2022/4/19
 */
class Share2 {
    private int num = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    public void incr() throws InterruptedException {
        lock.lock();
        try {
            //判断
            while (num != 0)
                condition.await();
            //干活
            num++;
            System.out.println(Thread.currentThread().getName()+"=="+num);
            //通知
            condition.signalAll();
        }finally {
            lock.unlock();
        }

    }

    public void decr() throws InterruptedException {
        lock.lock();
        try {
            //判断
            while (num != 1)
                condition.await();
            //干活
            num--;
            System.out.println(Thread.currentThread().getName()+"=="+num);
            //通知
            condition.signalAll();
        }finally {
            lock.unlock();
        }

    }

}
public class ThreadDemo02 {
    public static void main(String[] args) {
        Share2 share = new Share2();
        new Thread(()->{
            for (int i = 1 ; i< 10; i++){
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();
        new Thread(()->{
            for (int i = 1 ; i< 10; i++){
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();

        new Thread(()->{
            for (int i = 1 ; i< 10; i++){
                try {
                    share.incr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"CC").start();
        new Thread(()->{
            for (int i = 1 ; i< 10; i++){
                try {
                    share.decr();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"DD").start();
    }
}
