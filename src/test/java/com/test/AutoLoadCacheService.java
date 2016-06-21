package com.test;

import org.nutz.ioc.loader.annotation.IocBean;

import com.jarvis.cache.annotation.Cache;

@IocBean
public class AutoLoadCacheService {

    @Cache(autoload=true, expire=125, key="'test_'+hash(args[0])")
    public AutoLoadCache getData(AutoLoadCache data) {
        System.out.println("get data from DAO >" + data.getName());
        AutoLoadCache tmp=new AutoLoadCache();
        tmp.setId(data.getId());
        tmp.setAge(data.getAge());
        tmp.setName(data.getName());
        return tmp;
    }
}
