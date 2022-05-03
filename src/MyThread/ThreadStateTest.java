package MyThread;

import java.util.concurrent.TimeUnit;

/**
 * Description
 *
 * @author tyw
 * @since 2022/4/3
 */
public class ThreadStateTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread();
        System.out.println(thread.getState());
        thread.start();
        System.out.println(thread.getState());
        TimeUnit.SECONDS.sleep(2);
        System.out.println(thread.getState());
    }
}
