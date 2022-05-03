package MyThread.MakeDish;

import MyThread.SupplyAsyc.SmallTool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Description
 *
 * @author tyw
 * @since 2022/4/3
 */
public class Demo_01 {
    public static void main(String[] args) {
        SmallTool.printTimeAndThread("小白和小伙伴们进入餐厅，开始点菜");
        long l = System.currentTimeMillis();
        List<Dish> dishes = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            Dish dish = new Dish("菜"+i,1);
            dishes.add(dish);

        }
        for (Dish dish: dishes){
            CompletableFuture.runAsync(() ->
                dish.make()
            ).join();
        }
        SmallTool.printTimeAndThread("菜都做好了，上桌"+(System.currentTimeMillis()-l));
    }
}
