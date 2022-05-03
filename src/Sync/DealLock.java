package Sync;

import java.util.concurrent.TimeUnit;

/**
 * Description
 *
 * @author tyw
 * @since 2022/4/20
 */
public class DealLock {
    static Object a= new Object();
    static Object b= new Object();
    public static void main(String[] args) {
        new Thread(()->{
            synchronized (a){
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"持有锁A,试图获取锁B");
                synchronized (b){
                    System.out.println(Thread.currentThread().getName()+"持有锁B");
                }

            }
        },"AA").start();
        new Thread(()->{
            synchronized (b){
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"持有锁B,试图获取锁A");
                synchronized (a){
                    System.out.println(Thread.currentThread().getName()+"持有锁A");
                }

            }
        },"BB").start();
    }
}
