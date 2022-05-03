package Redis;

import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Description
 *
 * @author tyw
 * @since 2022/4/20
 */
public class RedisJava {
    public static void main(String[] args) {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("10.120.137.31",6378);
        // 如果 Redis 服务设置了密码，需要下面这行，没有就不需要
         jedis.auth("123456");
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());
        List<String> lrange = jedis.lrange("user:role:24", 0, -1);
        Long llen = jedis.llen("user:role:24");
//        Long lpush = jedis.lpush("user:role:1", "98151");
        System.out.println(lrange);
        System.out.println(llen);
//        System.out.println(lpush);
    }
}
