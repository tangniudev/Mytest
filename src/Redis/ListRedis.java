package Redis;

import redis.clients.jedis.Jedis;

/**
 * Description
 *
 * @author tyw
 * @since 2022/4/22
 */
public class ListRedis {
    public static void main(String[] args) {
        Jedis jedis = JedisTools.getJedis();
        System.out.println(jedis.lpush("list", "小明", "小红"));

        System.out.println(jedis.rpush("list", "老王","老李"));
        System.out.println(jedis.lrange("list", 1, 2));
        System.out.println(jedis.lpush("list", "小明"));
        System.out.println(jedis.lrem("list", 1, "小明"));
        System.out.println(jedis.lpop("list"));
        System.out.println(jedis.llen("list"));
        System.out.println(jedis.lindex("list", 1));
//        System.out.println(jedis.sort("list"));

    }
}
