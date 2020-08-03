package demo.multithread.ProCon;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/** 生产者消费者问题（英语：Producer-consumer problem），也称有限缓冲问题（Bounded-buffer problem），
*   是一个多线程同步问题的经典案例。
*   该问题描述了两个共享固定大小缓冲区的线程——即所谓的“生产者”和“消费者”——在实际运行时会发生的问题。
*   生产者的主要作用是生成一定量的数据放到缓冲区中，然后重复此过程。与此同时，消费者也在缓冲区消耗这些数据。
*   该问题的关键就是要保证生产者不会在缓冲区满时加入数据，消费者也不会在缓冲区中空时消耗数 -- 维基百科
*/
public class ProCon {

    private static final Object lock = new Object();
    static List<Integer> storage = new ArrayList<>();

    static class Producer implements Runnable{
        @Override
        public void run() {
            while (true){
                synchronized (lock){
                    if (storage.size() != 0){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    storage.add(13);
                    System.out.println("生产了一个元素");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.notify();
                }
            }
        }
    }

    static class Consumer implements Runnable{
        @Override
        public void run() {
            while(true){
                synchronized (lock){
                    if (storage.size() == 0){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    storage.remove(0);
                    System.out.println("消费者消费了一个元素");
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.notify();
                }
            }
        }
    }

    public static void main(String args[]){
        new Thread(new Producer()).start();
        new Thread(new Consumer()).start();
    }
}
