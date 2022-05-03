package Redis;

import redis.clients.jedis.Jedis;

/**
 * Description
 *
 * @author tyw
 * @since 2022/4/22
 */
public class NumRedis {
    public static void main(String[] args) {
        Jedis jedis = JedisTools.getJedis();
        jedis.set("num","1");
        System.out.println(jedis.incr("num"));
        System.out.println(jedis.incrBy("num", 5));
        System.out.println(jedis.decr("num"));
        System.out.println(jedis.decrBy("num", 5));
    }
}
