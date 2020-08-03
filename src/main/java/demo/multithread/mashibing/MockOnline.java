package demo.multithread.mashibing;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class MockOnline {

    static class Miner implements Runnable{
        CyclicBarrier cyclicBarrier;
        String name;
        int onlineTime;

        public Miner(CyclicBarrier cyclicBarrier, String name, int onlineTime) {
            this.cyclicBarrier = cyclicBarrier;
            this.name = name;
            this.onlineTime = onlineTime;
        }

        @Override
        public void run() {
            try {
                System.out.println(name + " 准备上线。。。");
                TimeUnit.SECONDS.sleep(onlineTime);
                System.out.println(name + " 上线完成用了" + onlineTime + "秒");
                cyclicBarrier.await();
                System.out.println(name + " 走了。");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class Leader implements Runnable{
        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println("可以走了。。。");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new Leader());
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Random random = new Random();
        String[] nameArray = new String[]{"fs","mx","zl","sp","yq"};
        for (String name: nameArray){
            executorService.execute(new Miner(cyclicBarrier,name,random.nextInt(8)));
        }
        executorService.shutdown();

    }
}
