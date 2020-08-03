package demo.multithread.leetcode;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class ZeroEvenOdd {
    int n = 10;

    Semaphore s0 = new Semaphore(1);
    Semaphore s00 = new Semaphore(0);
    Semaphore s1 = new Semaphore(0);
    Semaphore s2 = new Semaphore(0);


    Runnable zero = new Runnable() {
        @Override
        public void run() {
            try {
                for (int i=0; i<n/2; i++){
                    s0.acquire();
                    System.out.println(0);
                    s1.release();
                    s00.acquire();
                    System.out.println(0);
                    s2.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    Runnable odd = new Runnable() {
        @Override
        public void run() {
            try {
                for (int i=1; i<=n; i=i+2){
                    s1.acquire();
                    System.out.println(i);
                    s00.release();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    Runnable even = new Runnable() {
        @Override
        public void run() {
            try {
                for (int i=2; i<=n; i=i+2){
                    s2.acquire();
                    System.out.println(i);
                    s0.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    public static void main(String args[]){
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd();
//        new Thread(zeroEvenOdd.zero).start();
//        new Thread(zeroEvenOdd.odd).start();
//        new Thread(zeroEvenOdd.even).start();

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(zeroEvenOdd.zero);
        executorService.execute(zeroEvenOdd.even);
        executorService.execute(zeroEvenOdd.odd);

        executorService.shutdown();
    }
}
