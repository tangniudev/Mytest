package Sync.FockJoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Description
 *
 * @author tyw
 * @since 2022/4/21
 */
class MyTask extends RecursiveTask<Integer>{
    private final static Integer VALUE = 10;
    private int begin ;
    private int end;
    private int result = 0;

    public MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if ((end-begin)<=VALUE){
            for (int i = begin ; i <= end ; i++){
                result = result + i;
            }
        }else {
            int mid = (end+begin)/2;
            MyTask myTask = new MyTask(begin, mid);
            MyTask myTask1 = new MyTask(mid + 1, end);
            //拆分
            myTask1.fork();
            myTask.fork();
            //合并
            return myTask.join()+myTask1.join();

        }
        return result;
    }
}
public class FockJoinDemo {
    public static void main(String[] args) {
        MyTask myTask = new MyTask(0, 100);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> submit = forkJoinPool.submit(myTask);
        try {
            System.out.println(submit.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        forkJoinPool.shutdown();
    }
}
