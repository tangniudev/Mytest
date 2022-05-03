package Sync;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Description
 *
 * @author tyw
 * @since 2022/4/20
 */
public class CyclicBarrierDemo01 {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println(Thread.currentThread().getName()+"7龙珠被收集齐全，你可以许愿了");
        });
        for (int i = 0; i < 7; i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"颗龙珠被收集了");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();

        }
    }
}
