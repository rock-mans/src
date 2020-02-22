////ClassLoader:
////        +-sun.misc.Launcher$AppClassLoader@18b4aac2
////        +-sun.misc.Launcher$ExtClassLoader@156643d4
////
////        Location:
////        /D:/workspace-idea/dubbo2.7.4/dubbo-master-dubbo2.7.4/dubbo-common/target/classes/
////
////        /*
////         * Decompiled with CFR.
////         *
////         * Could not load the following classes:
////         *  org.apache.dubbo.common.bytecode.ClassGenerator
////         *  org.apache.dubbo.common.bytecode.ClassGenerator$DC
////         *  org.apache.dubbo.common.bytecode.NoSuchMethodException
////         *  org.apache.dubbo.common.bytecode.NoSuchPropertyException
////         *  org.apache.dubbo.common.bytecode.Wrapper
////         *  org.apache.dubbo.demo.DemoService
////         */
//package org.apache.dubbo.common.bytecode;
//
//import java.lang.reflect.InvocationTargetException;
//import java.util.Map;
//import org.apache.dubbo.common.bytecode.ClassGenerator;
//import org.apache.dubbo.common.bytecode.NoSuchMethodException;
//import org.apache.dubbo.common.bytecode.NoSuchPropertyException;
//import org.apache.dubbo.common.bytecode.Wrapper;
//import org.apache.dubbo.demo.DemoService;
//
//public class Wrapper0
//        extends Wrapper
//        implements ClassGenerator.DC {
//    public static String[] pns;
//    public static Map pts;
//    public static String[] mns;
//    public static String[] dmns;
//    public static Class[] mts0;
//
//    public boolean hasProperty(String string) {
//        return pts.containsKey(string);
//    }
//
//    public Class getPropertyType(String string) {
//        return (Class)pts.get(string);
//    }
//
//    public Object getPropertyValue(Object object, String string) {
//        try {
//            DemoService demoService = (DemoService)object;
//        }
//        catch (Throwable throwable) {
//            throw new IllegalArgumentException(throwable);
//        }
//        throw new NoSuchPropertyException(new StringBuffer().append("Not found property \"").append(string).append("\" field or setter method in class org.apache.dubbo.demo.DemoService.").toString())
//
//    }
//
//    public void setPropertyValue(Object object, String string, Object object2) {
//        try {
//            DemoService demoService = (DemoService)object;
//        }
//        catch (Throwable throwable) {
//            throw new IllegalArgumentException(throwable);
//        }
//        throw new NoSuchPropertyException(new StringBuffer().append("Not found property \"").append(string).append("\" field or setter method in class org.apache.dubbo.demo.DemoService.").toString())
//
//    }
//
//    public Object invokeMethod(Object object, String string, Class[] arrclass, Object[] arrobject) throws InvocationTargetException {
//        DemoService demoService;
//        try {
//            demoService = (DemoService)object;
//        }
//        catch (Throwable throwable) {
//            throw new IllegalArgumentException(throwable);
//        }
//        try {
//            if ("sayHello".equals(string) && arrclass.length == 1) {
//                return demoService.sayHello((String)arrobject[0]);
//            }
//        }
//        catch (Throwable throwable) {
//            throw new InvocationTargetException(throwable);
//        }
//        throw new NoSuchMethodException(new StringBuffer().append("Not found method \"").append(string).append("\" in class org.apache.dubbo.demo.DemoService.").toString());
//    }
//
//    public String[] getPropertyNames() {
//        return pns;
//    }
//
//    public String[] getMethodNames() {
//        return mns;
//    }
//
//    public String[] getDeclaredMethodNames() {
//        return dmns;
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
