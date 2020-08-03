package demo.designPattern.singleton;

public class SingletonDemo {

    private static SingletonDemo instance;

    static SingletonDemo getInstance(){
        if (instance == null) {
            synchronized (SingletonDemo.class){
                if (instance == null)
                instance = new SingletonDemo();
            }
        }

        return instance;

    }

    private SingletonDemo(){}
}
