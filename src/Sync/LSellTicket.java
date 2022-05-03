package Sync;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Description
 *
 * @author tyw
 * @since 2022/4/19
 */
public class LSellTicket {
    public static void main(String[] args) {
        LTicket ticket = new LTicket();
        new Thread(()->{
            for (int i = 0;i<40;i++){
                ticket.sell();;
            }
        },"AA").start();
        new Thread(()->{
            for (int i = 0;i<40;i++){
                ticket.sell();;
            }
        },"BB").start();
        new Thread(()->{
            for (int i = 0;i<40;i++){
                ticket.sell();;
            }
        },"CC").start();
    }
}
class LTicket{
    private int num = 30;
    /**
     * 非公平锁：效率高，但是会出现线程饿死
     * 公平锁：雨露均沾，效率相对低
     */
    private ReentrantLock lock = new  ReentrantLock(true);
    public void sell(){
        lock.lock();
        try {
            if (num > 0){
                System.out.println(Thread.currentThread().getName() +"卖出了第"+ num--+"张票");
            }
        }finally {
            lock.unlock();
        }

    }
}