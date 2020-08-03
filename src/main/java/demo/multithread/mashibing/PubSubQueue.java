package demo.multithread.mashibing;

import java.util.concurrent.*;

public class PubSubQueue {
    //FIFO
    private static ConcurrentLinkedQueue<Integer> q = new ConcurrentLinkedQueue<>();

    public static void main(String args[]){

        ExecutorService executorService = Executors.newFixedThreadPool(6);

        executorService.submit(new Producer("producer1"));
        executorService.submit(new Producer("producer2"));
        executorService.submit(new Producer("producer3"));
        executorService.submit(new Consumer("consumer1"));
        executorService.submit(new Consumer("consumer2"));
        executorService.submit(new Consumer("consumer3"));

        executorService.shutdown();
    }

    static class Producer implements Runnable{
        String name;

        public Producer(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            for (int i=1; i<10; i++){
                try {
                    System.out.println(this.name + " 生产+ " + i);
                    q.add(i);
//                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer implements Runnable{
        String name;

        public Consumer(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            for (int i=1; i<10; i++){
                try {
                    System.out.println(this.name + " 消费- " + q.poll());
//                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
