package demo.lettuce;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class DistributedTransaction {

    static enum Result {
        SUCCESS, FAIL, CANCELED
    }

    static List<MyTask> tasks = new ArrayList<>();

    static class MyTask{
        private String name;
        private int timeInSeconds;
        private Result ret;

        volatile boolean cancelling = false;
        volatile boolean cancelled = false;

        public MyTask(String name, int timeInSeconds, Result result) {
            this.name = name;
            this.timeInSeconds = timeInSeconds * 1000;
            this.ret = ret;
        }

        //fei qi
        public Boolean call(){
            //模拟业务执行时间
            try {
                Thread.sleep(timeInSeconds);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println(name + "task callback");
            return true;
        }

        public Result runTask(){
            int interval = 100;
            int total = 0;

            try {
                for (;;){
                    Thread.sleep(interval); //cpu密集型 每个多长时间检查一下canceled flag
                    total += interval;
                    if (total >= timeInSeconds) break;
                    if (cancelled) return Result.CANCELED;
                }
            } catch (Exception e){
                e.printStackTrace();
            }
            System.out.println(name + " end!");
            return ret;
        }

        public void cancel() {
            cancelling = true; //正在cancel
            synchronized (this){
                System.out.println(name + " cancelling");
                try{
                    TimeUnit.MILLISECONDS.sleep(50);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(name + " cancelled");
            }
            cancelled = true;
        }
    }

    static void callback(Result result, MyTask task){
        if (Result.FAIL == result){
            for (MyTask _task:tasks){
                if (_task != task){
                    _task.cancel();
                }
            }
        }
        
    }

    public static void main(String args[]) throws ExecutionException, InterruptedException {
        MyTask task1 = new MyTask("task1",3, Result.SUCCESS);
        MyTask task2 = new MyTask("task2",4, Result.SUCCESS);
        MyTask task3 = new MyTask("task3",1, Result.FAIL);

        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);


        for (MyTask task: tasks) {
            CompletableFuture f = CompletableFuture.supplyAsync(() -> task.runTask()).thenAccept(result -> callback(result, task));
        }
    }
}
