package Redis;

import redis.clients.jedis.Jedis;

import java.util.concurrent.TimeUnit;

/**
 * Description
 *
 * @author tyw
 * @since 2022/4/22
 */
public class StrRedis {
    public static void main(String[] args) throws InterruptedException {
        Jedis jedis = JedisTools.getJedis();
        jedis.set("company","sensors_data");
        System.out.println(jedis.get("company"));
        //如果有就不覆盖
        jedis.setnx("company","sensors_data2");
        System.out.println(jedis.get("company"));
        //添加数据并设置有效时间
        jedis.setex("people1",2,"zhouyugao");
        System.out.println(jedis.get("people1"));
        Thread.sleep(2000);
        System.out.println(jedis.get("people1"));
        System.out.println(jedis.getrange("company", 0, 6));
        System.out.println(jedis.getSet("company", "sensors_data3"));//重新复制并且返回旧值
        System.out.println(jedis.get("company"));

    }
}
