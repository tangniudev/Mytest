package MyThread.CrossBridge;

import MyThread.SupplyAsyc.SmallTool;

import java.util.Random;

/**
 * Description
 *
 * @author tyw
 * @since 2022/4/3
 */
public class Demo_01 {
    public static void main(String[] args) throws InterruptedException {
        Thread car2 = new Thread(() ->
        {
            SmallTool.printTimeAndThread("卡丁2号准备过桥");
            SmallTool.printTimeAndThread("发现卡丁1号正在过桥，开始等待");
            SmallTool.sleepMillis(3000);
            SmallTool.printTimeAndThread("卡丁2号过桥完毕");

        });
        Thread car1 = new Thread(() ->
        {
            SmallTool.printTimeAndThread("卡丁1号准备过桥");
            int i = new Random().nextInt(500) + 1000;
            SmallTool.sleepMillis(i);
            SmallTool.printTimeAndThread("卡丁1号过桥完毕");
            car2.interrupt();
        });

        car2.start();
        car1.start();

    }
}
