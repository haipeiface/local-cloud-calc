package demo.multithread.mashibing;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {

    static class Employee implements Runnable{
        String id;
        Semaphore semaphore;

        public Employee(String id, Semaphore semaphore) {
            this.id = id;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println(id + " +using the toilet");
                TimeUnit.MILLISECONDS.sleep(1000);
                semaphore.release();
                System.out.println(id + " -leaving the toilet");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]){
        final int THREAD_COUNT = 10;
        Semaphore semaphore = new Semaphore(4);

        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        for (int i = 0; i<= THREAD_COUNT; i++){
            executorService.execute(new Employee(String.valueOf(i), semaphore));

        }

        executorService.shutdown();
    }
}
