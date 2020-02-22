package org.apache.dubbo.demo.consumer;

public class ThreadLocalUtil {

    private  static final  ThreadLocal THREAD_LOCAL = new ThreadLocal();

    public static void setThreadLocalVariable(Object obj){
        THREAD_LOCAL.set(obj);
    }

    public static Object getThreadLocalVariable(){
        return THREAD_LOCAL.get();
    }
}
