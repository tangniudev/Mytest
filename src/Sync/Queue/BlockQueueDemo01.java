package Sync.Queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Description
 *
 * @author tyw
 * @since 2022/4/21
 */
public class BlockQueueDemo01 {
    public static void main(String[] args) throws InterruptedException {
//        test01();
//        test02();
//        try {
//            test03();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        test04();


    }

    private static void test04() throws InterruptedException {
        BlockingQueue<String> blockQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockQueue.offer("a"));
        System.out.println(blockQueue.offer("b"));
        System.out.println(blockQueue.offer("c"));
        //设置时间
        System.out.println(blockQueue.offer("d",3, TimeUnit.SECONDS));

        System.out.println(blockQueue.poll());
        System.out.println(blockQueue.poll());
        System.out.println(blockQueue.poll());
        System.out.println(blockQueue.poll(3,TimeUnit.SECONDS));
    }

    private static void test03() throws InterruptedException {
        //阻塞 put take
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        blockingQueue.put("a");
        blockingQueue.put("a");
        blockingQueue.put("a");
//        blockingQueue.put("a");
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
//        System.out.println(blockingQueue.take());
    }

    private static void test02() {
        // 特殊值 offer poll peek
        BlockingQueue<String> blockQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockQueue.offer("a"));
        System.out.println(blockQueue.offer("b"));
        System.out.println(blockQueue.offer("c"));
        System.out.println(blockQueue.offer("d"));

        System.out.println(blockQueue.peek());
        System.out.println(blockQueue.peek());
        System.out.println(blockQueue.peek());

        System.out.println(blockQueue.poll());
        System.out.println(blockQueue.poll());
        System.out.println(blockQueue.poll());
        System.out.println(blockQueue.poll());
    }

    private static void test01() {
        //抛出异常 element add remove
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.element());
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
//        System.out.println(blockingQueue.add("d"));
        System.out.println(blockingQueue.element());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
//        System.out.println(blockingQueue.remove());
    }
}
