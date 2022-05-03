package Sync;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Description
 *
 * @author tyw
 * @since 2022/4/20
 */
public class LockDemo02 {
    public static void main(String[] args) {

//        Object o = new Object();
//        new Thread(()->{
//            synchronized (o){
//                System.out.println(Thread.currentThread().getName()+"外层");
//
//                synchronized (o){
//                    System.out.println(Thread.currentThread().getName()+"中层");
//
//                    synchronized (o){
//                        System.out.println(Thread.currentThread().getName()+"内层");
//                    }
//                }
//            }
//        },"TT").start();

        ReentrantLock lock = new ReentrantLock();
        new Thread(()->{
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName()+"外层");
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName()+"内层");
                }finally {
//                    lock.unlock();//少释放一次
                }
            }finally {
                lock.unlock();
            }
        },"TT").start();
        new Thread(()->{
            lock.lock();
            try {
                System.out.println("上面没有把锁释放完全,我就输出不了");
            }finally {
                    lock.unlock();
            }
        }).start();
    }
}
