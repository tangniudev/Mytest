package MyThread.CreateThread;

/**
 * Description
 *
 * @author tyw
 * @since 2022/4/3
 */
public class Demo_01 {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> System.out.println("我是子线程"));
        thread.start();
        System.out.println("main 结束");
    }
}
