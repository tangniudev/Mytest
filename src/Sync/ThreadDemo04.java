package Sync;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.stream.Collectors;

/**
 * Description
 *
 * @author tyw
 * @since 2022/4/19
 * List线程不安全
 * 解决方案
 * 1.Vector
 * 2.Collections
 * 3.CopyOnWriteArrayList
 */
public class ThreadDemo04 {
    public static void main(String[] args) {
        test06();
    }
    public void test01(){
                List<String> list = new ArrayList<>();
        for (int i = 1 ; i <= 30 ; i++){
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
    public void test02(){
        List<String> list = new Vector<>();
        for (int i = 1 ; i <= 30 ; i++){
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
    public void test03(){
        List<String> list = Collections.synchronizedList(new ArrayList<>());
        for (int i = 1 ; i <= 30 ; i++){
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
    public void test04(){
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 1 ; i <= 30 ; i++){
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
    public static void test05(){
//        HashMap<String,Object> list = new HashMap<>();
        Map<String,String> list = new ConcurrentHashMap<>();
        for (int i = 1 ; i <= 30 ; i++){
            int finalI = i;
            new Thread(() -> {
                list.put(String.valueOf(finalI),UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }

    public static void test06(){
//        HashSet<String> list = new HashSet<>();
        Set<String> list = new CopyOnWriteArraySet<>();
        for (int i = 1 ; i <= 30 ; i++){
            int finalI = i;
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
