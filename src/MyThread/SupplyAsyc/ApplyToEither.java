package MyThread.SupplyAsyc;

import java.util.concurrent.CompletableFuture;

/**
 * Description
 *
 * @author tyw
 * @since 2022/4/3
 * applyToEither两个线程谁先结束就返回谁的结果
 */
public class ApplyToEither {
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
        }),fiestBus-> fiestBus);
        SmallTool.printTimeAndThread(String.format("%s,小白坐车回家",bus.join()));
    }
}
