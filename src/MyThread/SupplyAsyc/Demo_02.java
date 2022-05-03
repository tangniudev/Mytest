package MyThread.SupplyAsyc;

import java.util.concurrent.CompletableFuture;

/**
 * Description
 *
 * @author tyw
 * @since 2022/4/3
 */
public class Demo_02 {
    public static void main(String[] args) {
        SmallTool.printTimeAndThread("小白进入餐厅");
        SmallTool.printTimeAndThread("小白点了 番茄炒蛋 一份米饭");
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() ->
                {
                    SmallTool.printTimeAndThread("厨师炒菜");
                    SmallTool.sleepMillis(200);
                    CompletableFuture<String> race = CompletableFuture.supplyAsync(
                            () -> {
                                SmallTool.printTimeAndThread("服务员打饭");
                                SmallTool.sleepMillis(200);
                                return "米饭";
                            }
                    );
                    return "番茄炒蛋" + race.join() +" 做好了";
                }
        );
        SmallTool.printTimeAndThread("小白在打王者");
        SmallTool.printTimeAndThread(String.format("%s ,小白开吃",stringCompletableFuture.join()));
    }
}
