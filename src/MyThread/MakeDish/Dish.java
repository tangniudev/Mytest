package MyThread.MakeDish;

import MyThread.SupplyAsyc.SmallTool;

import java.util.concurrent.TimeUnit;

/**
 * Description
 *
 * @author tyw
 * @since 2022/4/3
 */
public class Dish {
    private String name;

    private Integer productionTime;

    public Dish(String name, Integer productionTime) {
        this.name = name;
        this.productionTime = productionTime;
    }

    public void make(){
        SmallTool.sleepMillis(TimeUnit.SECONDS.toMillis(this.productionTime));
        SmallTool.printTimeAndThread(this.name + "制作完成");
    }
}
