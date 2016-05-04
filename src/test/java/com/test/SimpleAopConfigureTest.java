package com.test;

import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.combo.ComboIocLoader;
import org.nutz.json.Json;

public class SimpleAopConfigureTest {

    private static Ioc ioc;

    public static void main(String[] args) throws ClassNotFoundException {
        ioc=
            new NutIoc(
                new ComboIocLoader("*json", "/ioc", "*anno", "com.test", "*com.jarvis.cache.aop.nutz.AutoLoadCacheIocLoader"));
        test();
        try {
            Thread.sleep(120 * 1000);
        } catch(InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        test();
        ioc.depose();
    }

    private static void test() {
        AutoLoadCacheService autoLoadCacheService=ioc.get(AutoLoadCacheService.class);
        AutoLoadCache data=new AutoLoadCache();
        data.setId(1);
        data.setAge(20);
        data.setName("test1");
        AutoLoadCache res1=autoLoadCacheService.getData(data);
        System.out.println("res1==" + Json.toJson(res1));
        data.setAge(30);
        data.setName("test2");
        AutoLoadCache res2=autoLoadCacheService.getData(data);
        System.out.println("res2==" + Json.toJson(res2));
    }
}
