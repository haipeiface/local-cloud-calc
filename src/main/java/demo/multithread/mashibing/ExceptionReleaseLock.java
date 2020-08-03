package demo.multithread.mashibing;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

public class ExceptionReleaseLock {
    private int i = 0;

    public synchronized void m(){
        for (;i<20;){
            i++;
            System.out.println("i = " + i);
            if (i == 5) {
//                int j = i / 0;
            }
        }
    }

    public static void main(String args[]) throws InterruptedException {
        ExceptionReleaseLock exceptionReleaseLock = new ExceptionReleaseLock();

        Thread t1 = new Thread(
            () -> {
                System.out.println(Thread.currentThread().getName());
                exceptionReleaseLock.m();
            }
        );
        Thread t2 = new Thread(
            () -> {
                System.out.println(Thread.currentThread().getName());
                exceptionReleaseLock.m();
            }
        );

        t1.start();
        TimeUnit.MILLISECONDS.sleep(1000);
        t2.start();
    }
}
