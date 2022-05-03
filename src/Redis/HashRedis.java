package Redis;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

/**
 * Description
 *
 * @author tyw
 * @since 2022/4/22
 */
public class HashRedis {
    public static void main(String[] args) {
        Jedis jedis = JedisTools.getJedis();
        Map<String,String> map = new HashMap<>();
        for (int i = 0; i < 10; i++){
            map.put(String.valueOf(i),String.valueOf(i));
        }
        jedis.hmset("myhash",map);
        System.out.println(jedis.hgetAll("myhash"));
        jedis.hset("myhash","name","redis");
        System.out.println(jedis.hgetAll("myhash"));
        System.out.println(jedis.hgetAll("myhash"));
        System.out.println(jedis.hkeys("myhash"));
        System.out.println(jedis.hvals("myhash"));
        System.out.println(jedis.hlen("myhash"));
        System.out.println(jedis.hexists("myhash", "name"));
    }
}
