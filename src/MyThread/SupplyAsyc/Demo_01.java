package MyThread.SupplyAsyc;

import java.util.concurrent.CompletableFuture;

/**
 * Description
 *
 * @author tyw
 * @since 2022/4/3
 * supplyAsync开启一个异步任务
 */
public class Demo_01 {
    public static void main(String[] args) {
        SmallTool.printTimeAndThread("小白进入餐厅");
        SmallTool.printTimeAndThread("小白点了 番茄炒蛋 一份米饭");
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() ->
                {
                    SmallTool.printTimeAndThread("厨师炒菜");
                    SmallTool.sleepMillis(200);
                    SmallTool.printTimeAndThread("厨师打饭");
                    SmallTool.sleepMillis(100);
                    return "番茄炒蛋 + 米饭 做好了";
                }
        );
        SmallTool.printTimeAndThread("小白在打王者");
        SmallTool.printTimeAndThread(String.format("%s ,小白开吃",stringCompletableFuture.join()));
    }
}
