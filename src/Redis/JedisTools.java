package Redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


/**
 * Description
 *
 * @author tyw
 * @since 2022/4/22
 */
public class JedisTools {
    public static Jedis getJedis(){
        JedisPoolConfig jpconfig= new JedisPoolConfig();//初始化连接池
        jpconfig.setMaxTotal(100);//设置最大连接数
        jpconfig.setMaxIdle(10);//设置最大空闲连接数

        JedisPool jedisPool = new JedisPool(jpconfig,"10.120.240.1",6379);

        Jedis jedis=null;
        try{
            jedis=jedisPool.getResource();
            jedis.auth("123456");
           if (jedis!=null)
               return jedis;
        }catch(Exception e){
            e.printStackTrace();
        }
        return jedis;
    }
}
