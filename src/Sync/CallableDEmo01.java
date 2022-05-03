package Sync;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Description
 *
 * @author tyw
 * @since 2022/4/20
 */
class MyThread01 implements Runnable{

    @Override
    public void run() {

    }
}
class MyThread02 implements Callable {

    @Override
    public Integer call() {
        System.out.println(Thread.currentThread().getName()+"  come in Callable");
        return 200;
    }
}
public class CallableDEmo01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new Thread(new MyThread01(),"AA").start();

        FutureTask<Integer> futureTask = new FutureTask<Integer>(new MyThread02());

        FutureTask<Integer> futureTask1 = new FutureTask<>(()->600);

        new Thread(futureTask,"lucy").start();
        new Thread(futureTask1,"tom").start();

        while (! futureTask.isDone()){
            System.out.println("--------wait");
        }
        System.out.println(futureTask.get());
        System.out.println(futureTask.get()+"不会再次计算");
        System.out.println(futureTask1.get());
        System.out.println(Thread.currentThread().getName()+" come over");
    }
}
