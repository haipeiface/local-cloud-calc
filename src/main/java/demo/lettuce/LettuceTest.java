package demo.lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.sync.RedisCommands;

public class LettuceTest {
    public static void main(String[] args) {
        RedisClient client = RedisClient.create("redis://localhost:6379/0");

//        StatefulRedisConnection<String, String> connection = client.connect(); // 获取一个连接
//        RedisAsyncCommands<String,String> commands = connection.async();
//        RedisFuture<String> future1 = commands.get("dream-codehole");
//        future1.whenComplete((value,e) -> {System.out.println("xxxxxxxxxxxxxx" + value);});
//        RedisFuture<Long> future2 = commands.zcard("dreams-codehole");
//        future2.whenComplete((value, e) -> {
//            System.out.println("xxxxxxxxxxxxx" + value);
//        });
//        try {
//            Thread.sleep(1000); // 等一会，保证结果在连接关闭之前都拿到了
//        } catch (InterruptedException e1) {
//        }
//        connection.close();
//        client.shutdown();
//        RedisCommands<String,String> commands = connection.sync();
//        commands.set("dream-codehole", "Yui Aragaki");
//        System.out.println(commands.get("dream-codehole"));
//        commands.zadd("dreams-codehole", 10, "Yui Aragaki");
//        commands.zadd("dreams-codehole", 8, "Elane Zhong");
//        System.out.println(commands.zcard("dreams-codehole"));
//        connection.close(); // 关闭连接
//        client.shutdown(); // 关闭所有连接
    }
}