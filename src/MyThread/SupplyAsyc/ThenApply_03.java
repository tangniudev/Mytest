package MyThread.SupplyAsyc;

import java.util.concurrent.CompletableFuture;

/**
 * Description
 *
 * @author tyw
 * @since 2022/4/3
 * thenApplyAsync 后置处理
 */
public class ThenApply_03 {
    public static void main(String[] args) {
        SmallTool.printTimeAndThread("小白吃好了");
        SmallTool.printTimeAndThread("小白结账，要求开发票");
        CompletableFuture<String> invoice = CompletableFuture.supplyAsync(() ->
        {
            SmallTool.printTimeAndThread("服务员收款 500");
            SmallTool.sleepMillis(200);

            return "500";
        }).thenApplyAsync(money -> {
            SmallTool.printTimeAndThread("服务员开发票 面额 500");
            SmallTool.sleepMillis(200);
            return String.format("%s元发票",money);
        });
        SmallTool.printTimeAndThread("小白接到朋友电话，想一起打游戏");
        SmallTool.printTimeAndThread(String.format("小白拿到%s,准备回家",invoice.join()));
    }
}
