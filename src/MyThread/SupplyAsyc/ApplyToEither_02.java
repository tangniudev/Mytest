package MyThread.SupplyAsyc;

import java.util.concurrent.CompletableFuture;

/**
 * Description
 *
 * @author tyw
 * @since 2022/4/3
 * exceptionally 异常出现时的处理情况
 */
public class ApplyToEither_02 {
    public static void main(String[] args) {
        SmallTool.printTimeAndThread("张三走出餐厅，来到公交站");
        SmallTool.printTimeAndThread("张三 等待 700路 或者800路 公交车的到来");
        CompletableFuture<String> bus = CompletableFuture.supplyAsync(() -> {
            SmallTool.printTimeAndThread("700路公交来了");
            SmallTool.sleepMillis(600);
            return "700 路公交车到了";
        }).applyToEither(CompletableFuture.supplyAsync(() ->
        {
            SmallTool.printTimeAndThread("800路公交来了");
            SmallTool.sleepMillis(100);
            return "800 路公交车到了";
        }),fiestBus-> {
            SmallTool.printTimeAndThread(fiestBus);
            if (fiestBus.startsWith("800")){
                throw new RuntimeException("撞树上了。。。");
            }
            return fiestBus;
        }).exceptionally(e -> {
            SmallTool.printTimeAndThread(e.getMessage());
            SmallTool.printTimeAndThread("小白叫出租车");
            return "出租车 叫到了";
        });
        SmallTool.printTimeAndThread(String.format("%s,小白坐车回家",bus.join()));
    }
}
