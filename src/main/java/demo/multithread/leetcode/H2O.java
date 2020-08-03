package demo.multithread.leetcode;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class H2O {

    CyclicBarrier cyclicBarrier;

    public H2O(CyclicBarrier cyclicBarrier){
        this.cyclicBarrier = cyclicBarrier;
    }

    Runnable H = new Runnable() {
        @Override
        public void run() {
            try {

                System.out.println("H");
                cyclicBarrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };


    public static void main(String args[]) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("O");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        H2O h2O = new H2O(cyclicBarrier);

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.execute(h2O.H);
        executorService.execute(h2O.H);
        executorService.execute(h2O.H);
        executorService.execute(h2O.H);
        executorService.execute(h2O.H);
        executorService.execute(h2O.H);
        executorService.execute(h2O.H);
        executorService.execute(h2O.H);
        executorService.execute(h2O.H);
        executorService.execute(h2O.H);
        executorService.execute(h2O.H);
        executorService.execute(h2O.H);

    }
}
