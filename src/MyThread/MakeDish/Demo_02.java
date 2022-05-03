package MyThread.MakeDish;

import MyThread.SupplyAsyc.SmallTool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;

/**
 * Description
 *
 * @author tyw
 * @since 2022/4/3
 */
public class Demo_02 {
    public static void main(String[] args) {
        SmallTool.printTimeAndThread("小白和小伙伴们进入餐厅，开始点菜");
        long l = System.currentTimeMillis();
        List<Dish> dishes = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            Dish dish = new Dish("菜"+i,1);
            dishes.add(dish);

        }
        ArrayList<CompletableFuture> cflist = new ArrayList<>();
        for (Dish dish: dishes){
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> dish.make());
            cflist.add(future);
        }
        CompletableFuture.allOf(cflist.toArray(new CompletableFuture[cflist.size()])).join();
        SmallTool.printTimeAndThread("菜都做好了，上桌"+(System.currentTimeMillis()-l));

        System.out.println("当前时间最大线程数量"+ Runtime.getRuntime().availableProcessors());
        System.out.println("线程池当前线程数量方法"+ ForkJoinPool.commonPool().getPoolSize());
        System.out.println("线程池当前线程最大数量方法"+ ForkJoinPool.getCommonPoolParallelism());

        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","5");
        System.out.println("线程池当前线程数量方法"+ ForkJoinPool.commonPool().getPoolSize());
        System.out.println("线程池当前线程最大数量方法"+ ForkJoinPool.getCommonPoolParallelism());
    }


}
