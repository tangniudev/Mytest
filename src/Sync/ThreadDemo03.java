package Sync;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description
 *
 * @author tyw
 * @since 2022/4/19
 */
class Share03{
    private int flag = 1; //1 AA  2 BB  3 CC
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private  Condition condition3= lock.newCondition();
    public void print5(int loop) throws InterruptedException {
        lock.lock();
        try {
            //判断
            while (flag != 1) {
                condition1.await();
            }
            //干活
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "   " + i + "   " + loop);
            }
            //通知 先修改标志位，再通知B线程
            flag = 2;
            condition2.signal();
        }finally {
            lock.unlock();
        }
    }

    public void print10(int loop) throws InterruptedException {
        lock.lock();
        try {
            //判断
            while (flag != 2) {
                condition2.await();
            }
            //干活
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "   " + i + "   " + loop);
            }
            //通知 先修改标志位，再通知C线程
            flag = 3;
            condition3.signal();
        }finally {
            lock.unlock();
        }
    }
    public void print15(int loop) throws InterruptedException {
        lock.lock();
        try {
            //判断
            while (flag != 3) {
                condition3.await();
            }
            //干活
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + "   " + i + "   " + loop);
            }
            //通知 先修改标志位，再通知A线程
            flag = 1;
            condition1.signal();
        }finally {
            lock.unlock();
        }
    }
}
public class ThreadDemo03 {
    public static void main(String[] args) {
        Share03 share03 = new Share03();
        new Thread(()->{
            for (int i = 0; i < 10 ; i++){
                try {
                    share03.print5(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"AA").start();
        new Thread(()->{
            for (int i = 0; i < 10 ; i++){
                try {
                    share03.print10(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"BB").start();
        new Thread(()->{
            for (int i = 0; i < 10 ; i++){
                try {
                    share03.print15(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"CC").start();
    }
}
