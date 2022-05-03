package MyThread.SupplyAsyc;

import java.util.concurrent.CompletableFuture;

/**
 * Description
 *
 * @author tyw
 * @since 2022/4/3
 * thenCombine用来合并两个任务结构由合并函数BiFuction返回
 */
public class Demo_04 {
    public static void main(String[] args) {
        SmallTool.printTimeAndThread("小白进入餐厅");
        SmallTool.printTimeAndThread("小白点了 番茄炒蛋 一份米饭");
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(() ->
                {

                    SmallTool.printTimeAndThread("厨师炒菜");
                    SmallTool.sleepMillis(200);
                    return "番茄炒蛋";
                }
        ).thenCombine( CompletableFuture.supplyAsync(
                () -> {
                    SmallTool.printTimeAndThread("服务员煮饭");
                    SmallTool.sleepMillis(200);
                    return  "米饭";
                }
        ),(dish ,race) ->
        {
            SmallTool.printTimeAndThread("服务员打饭");
            SmallTool.sleepMillis(200);
            return String.format("%s + %s 好了",dish,race);
        });

        SmallTool.printTimeAndThread("小白在打王者");
        SmallTool.printTimeAndThread(String.format("%s ,小白开吃",stringCompletableFuture.join()));
    }
}
