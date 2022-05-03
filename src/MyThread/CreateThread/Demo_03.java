package MyThread.CreateThread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Description
 *
 * @author tyw
 * @since 2022/4/3
 */
public class Demo_03 {
    public static void main(String[] args) {
        Callable<String> callable = () -> {
            System.out.println("我是子线程");
            return "sub task done";
        };
        FutureTask<String> stringFutureTask = new FutureTask<>(callable);
        Thread thread = new Thread(stringFutureTask);
        thread.start();
        System.out.println("子线程启动");
        try {
            String s = stringFutureTask.get();//在主线程中不停询问子线程的执行情况
            System.out.println(s);
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            Throwable cause = e.getCause();
            System.out.println(cause);
            e.printStackTrace();
        }

        System.out.println("mian 结束");
    }
}
