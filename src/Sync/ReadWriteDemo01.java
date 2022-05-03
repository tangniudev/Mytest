package Sync;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Description
 *
 * @author tyw
 * @since 2022/4/20
 */
class MyCache{
     private  Map<String,Object> map = new HashMap<>();
    private ReadWriteLock readWriteLock =  new ReentrantReadWriteLock();
    public void put(String key,Object value){
        readWriteLock.writeLock().lock();
        try {
            System.out.println("开始写数据"+key);
            TimeUnit.MICROSECONDS.sleep(300);
            map.put(key,value);
            System.out.println("写数据结束"+key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            readWriteLock.writeLock().unlock();
        }

    }
    public Object get(String key){
        Object result = null;

       readWriteLock.readLock().lock();
        try {
            System.out.println("开始读取数据"+key);
            TimeUnit.MICROSECONDS.sleep(300);
            result = map.get(key);
            System.out.println("读取数据结束"+key);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            readWriteLock.readLock().unlock();
        }
        return result;

    }
}
public class ReadWriteDemo01 {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 0; i < 5; i++){
            final int num = i;
            new Thread(()->{
                myCache.put(""+num,num);
            },String.valueOf(i)).start();
        }
        for (int i = 0; i < 5; i++){
            final int num = i;
            new Thread(()->{
                myCache.get(num+"");
            },String.valueOf(i)).start();
        }
    }
}
