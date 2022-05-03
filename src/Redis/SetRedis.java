package Redis;

import redis.clients.jedis.Jedis;

/**
 * Description
 *
 * @author tyw
 * @since 2022/4/22
 */
public class SetRedis {
    public static void main(String[] args) {
        Jedis jedis = JedisTools.getJedis();
        int m = 2;
        jedis.sadd("myset","qwe","asd","zxc");
        System.out.println(jedis.smembers("myset"));
        jedis.srem("myset","qwe");
        System.out.println(jedis.smembers("myset"));
        System.out.println(jedis.spop("myset"));
        System.out.println(jedis.smembers("myset"));
    }
}
