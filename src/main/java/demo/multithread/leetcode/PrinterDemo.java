package demo.multithread.leetcode;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class PrinterDemo {



    static class Printer implements Runnable{

        String Content;

        public Printer(String content) {
            Content = content;
        }

        @Override
        public void run() {
            try {
                System.out.println(this.Content);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String args[]) throws InterruptedException {

        Semaphore semaphore = new Semaphore(0);
        System.out.println(semaphore.availablePermits());

    }
}
