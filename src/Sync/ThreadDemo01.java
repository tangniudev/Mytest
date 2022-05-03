package Sync;

/**
 * Description
 *
 * @author tyw
 * @since 2022/4/19
 */
//第一步创建资源类
    class Share{
        //初始值
    private int num = 0;
    //判断 干活 通知
    public synchronized void incr() throws InterruptedException {
        while (num != 0){
            this.wait();
        }
        num++;
        System.out.println(Thread.currentThread().getName()+"=="+num);
        //通知其他线程
        notify();
    }

    public synchronized void dect() throws InterruptedException {
        while (num != 1){//使用while防止虚假唤醒
            this.wait();
        }
        num--;
        System.out.println(Thread.currentThread().getName()+"=="+num);
        //通知其他线程
        notify();
    }
}
public class ThreadDemo01 {
    public static void main(String[] args) {
        Share share = new Share();
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
                    share.dect();
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
                    share.dect();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"DD").start();
    }
}
