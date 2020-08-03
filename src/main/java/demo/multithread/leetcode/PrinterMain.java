package demo.multithread.leetcode;

import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class PrinterMain {

    CountDownLatch countDownLatch1;
    CountDownLatch countDownLatch2;
    CountDownLatch countDownLatch3;

    public PrinterMain(int count1,int count2, int count3){
        countDownLatch1 = new CountDownLatch(count1);
        countDownLatch2 = new CountDownLatch(count2);
        countDownLatch3 = new CountDownLatch(count3);
    }

    Runnable first = new Runnable() {
        @Override
        public void run() {
            try {
                countDownLatch1.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("first");
            countDownLatch2.countDown();
            countDownLatch3.countDown();
        }
    };

    Runnable second = new Runnable() {
        @Override
        public void run() {
            try {
                countDownLatch2.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("second");
            countDownLatch3.countDown();
            countDownLatch1.countDown();
        }
    };

    Runnable third = new Runnable() {
        @Override
        public void run() {
            try {
                countDownLatch3.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("third");
            countDownLatch1.countDown();
            countDownLatch2.countDown();
        }
    };

    public static void main(String args[]){

        PrinterMain printerMain = new PrinterMain(2,0,1);

        new Thread(printerMain.second).start();
        new Thread(printerMain.third).start();
        new Thread(printerMain.first).start();
    }
}
