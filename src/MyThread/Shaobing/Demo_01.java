package MyThread.Shaobing;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * Description
 *
 * @author tyw
 * @since 2022/4/3
 */
public class Demo_01 {
    public static void main(String[] args) {
        Queue<String> shaobingQueue = new LinkedList<>();
        List<String> xiaobaiMsg = new LinkedList<>();
        List<String> roadPeopleMsg = new LinkedList<>();
        Thread xiaoBai = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                String temp = String.format("第%d个烧饼", i + 1);
                shaobingQueue.add(temp);
                xiaobaiMsg.add(String.format("%d 小白制作了%s", System.currentTimeMillis(), temp));
            }
        });
        Thread APeople = new Thread(() -> {
            for (int i = 0 ; i < 10; i++){
                roadPeopleMsg.add(String.format("%d 路人甲买到了%s", System.currentTimeMillis(), shaobingQueue.poll()));
            }
        });
        xiaoBai.start();
        APeople.start();
        try {
            xiaoBai.join();
            APeople.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(xiaobaiMsg.stream().collect(Collectors.joining("\n")));
        System.out.println("==========");
        System.out.println(roadPeopleMsg.stream().collect(Collectors.joining("\n")));

    }
}
