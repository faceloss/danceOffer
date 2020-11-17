package java_base;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyDynamicProxy {
    // 1.2 2.4
    private static <T extends Number & Comparable<? super T>> T min(T[] values) {
        if (values == null || values.length == 0) return null;
        T min = values[0];
        for (int i = 1; i < values.length; i++) {
            if (min.compareTo(values[i]) > 0) min = values[i];
        }
        return min;
    }

    public static void main (String[] args) {
        HelloImpl hello = new HelloImpl();
        MyInvocationHandler handler = new MyInvocationHandler(hello);
        // 构造代码实例
        Hello proxyHello = (Hello) Proxy.newProxyInstance(HelloImpl.class.getClassLoader(), HelloImpl.class.getInterfaces(), handler); // 调用代理方法
        proxyHello.sayHello();
    }
}

interface Hello {
    void sayHello();
}

/**
 * 4年～4w
 * 40w～可交易
 * 50个
 *
 * 440/0/7 11
 *
 * */
class HelloImpl implements Hello {
    @Override
    public void sayHello() {
        System.out.println("被代理对象打印"); }
}

class MyInvocationHandler implements InvocationHandler {
    private Object target;
    public MyInvocationHandler(Object target) {
        this.target = target;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("动态代理打印前面");
        Object result = method.invoke(target, args);
        System.out.println("动态代理打印后面");
        return result;
    }
}