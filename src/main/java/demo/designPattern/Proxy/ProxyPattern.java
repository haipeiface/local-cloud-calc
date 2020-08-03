package demo.designPattern.Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyPattern {

    Subject realSubject = new RealSubject();

    ClassLoader classLoader = realSubject.getClass().getClassLoader();

    DynamicProxyHandler dynamicProxyHandler = new DynamicProxyHandler(realSubject);

    Subject proxySubject = (Subject) Proxy.newProxyInstance(classLoader, new Class[]{Subject.class}, dynamicProxyHandler);

}

interface Subject {
    void doSomething();
}

class RealSubject implements Subject{
    @Override
    public void doSomething() {
        System.out.println("this is real doSomething...");
    }
}

class DynamicProxyHandler implements InvocationHandler {
    private Object iObject;

    public DynamicProxyHandler(Object object){
        iObject = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(iObject, args);
    }
}