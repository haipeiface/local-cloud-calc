package demo.multithread.mashibing;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierDemo {

    static class Traveler implements Runnable{
        String name;
        CyclicBarrier cyclicBarrie;
        int arriveTime;

        public Traveler(String name, CyclicBarrier cyclicBarrie, int arriveTime) {
            this.name = name;
            this.cyclicBarrie = cyclicBarrie;
            this.arriveTime = arriveTime;
        }

        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(arriveTime*1000);
                System.out.println(name + " arrived");
                cyclicBarrie.await();
                System.out.println(name + "出发啦。。。。");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class TourGuide implements Runnable{
        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(4*1000);
                System.out.println("导游发签证啦。。。");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, new TourGuide());
        ExecutorService executor = Executors.newFixedThreadPool(3);

        executor.execute(new Traveler("hx",cyclicBarrier,3));
        executor.execute(new Traveler("zt",cyclicBarrier,2));
        executor.execute(new Traveler("fs",cyclicBarrier,1));

        executor.shutdown();
    }
}
