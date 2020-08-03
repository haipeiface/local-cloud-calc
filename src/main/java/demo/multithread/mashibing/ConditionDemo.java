package demo.multithread.mashibing;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo {
    @Test
    public void test(){
        final ReentrantLock reentrantLock = new ReentrantLock();
        final Condition condition = reentrantLock.newCondition();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    reentrantLock.lock();
                    System.out.println(Thread.currentThread().getName() + "在等待被唤醒");
                    condition.await();
                    System.out.println(Thread.currentThread().getName() + "恢复了");
                } catch (Exception e){
                    e.printStackTrace();
                } finally {
                    reentrantLock.unlock();
                }
            }
        },"thread1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    reentrantLock.lock();
                    System.out.println(Thread.currentThread().getName() + "抢到了锁");
                    condition.signal();
                    System.out.println(Thread.currentThread().getName() + "唤醒了其他等待的线程");
                } catch (Exception e){
                    e.printStackTrace();
                } finally {
                    reentrantLock.unlock();
                }
            }
        },"thread2").start();
    }

}
