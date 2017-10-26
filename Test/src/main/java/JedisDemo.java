import redis.clients.jedis.Jedis;

public class JedisDemo {

    public static void main(String[] args) {
        // 构造jedis对象
       Jedis jedis = new Jedis("45.32.228.188", 6379);
       System.out.println(jedis.ping());
        //Jedis jedis = new Jedis("192.168.0.200", 6379);
       //jedis.auth("lin3291159..lin3291159..");
        // 向redis中添加数据
        //jedis.set("mytest", "123");
        // 从redis中读取数据
        String value = jedis.get("liuheqishu");

        System.out.println(value);
        // 关闭连接
        jedis.close();

    }

}
