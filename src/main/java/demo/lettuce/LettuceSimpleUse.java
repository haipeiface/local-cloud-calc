package demo.lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.api.sync.RedisStringCommands;
import io.netty.channel.ChannelFuture;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class LettuceSimpleUse {
    private void testLettuce() throws ExecutionException, InterruptedException {
        //构建RedisClient对象，RedisClient包含了Redis的基本配置信息，可以基于RedisClient创建RedisConnection
        RedisClient client = RedisClient.create("redis://localhost");

        //创建一个线程安全的StatefulRedisConnection，可以多线程并发对该connection操作,底层只有一个物理连接.
        StatefulRedisConnection<String, String> connection = client.connect();

        //获取SyncCommand。Lettuce支持SyncCommand、AsyncCommands、ReactiveCommand三种command
        RedisStringCommands<String, String> sync = connection.sync();
        String value = sync.get("key");
        System.out.println("get redis value with lettuce sync command, value is :" + value);

        //获取SyncCommand。Lettuce支持SyncCommand、AsyncCommands、ReactiveCommand三种command
        RedisAsyncCommands<String, String> async = connection.async();
        RedisFuture<String> getFuture = async.get("key");
        value = getFuture.get();
        System.out.println("get redis value with lettuce async command, value is :" + value);

//        ChannelFuture channelFuture =
        ExecutorService executorService = Executors.newCachedThreadPool();

    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        new LettuceSimpleUse().testLettuce();
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Rajeev";
        });

        // Attach a callback to the Future using thenApply()
        CompletableFuture<String> greetingFuture = future.thenApply(name -> {
            return "Hello " + name;
        });

        // Block and get the result of the future.
        System.out.println(greetingFuture.get());

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

            }
        };
        CompletableFuture<String> future2 = CompletableFuture.runAsync(runnable
        ).thenApply(name -> {return "Hello" + name;});

        System.out.println(future2.get());

    }

}