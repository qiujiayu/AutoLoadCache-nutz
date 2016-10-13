package com.test;

import org.nutz.ioc.loader.annotation.IocBean;

import com.jarvis.cache.annotation.Cache;
import com.jarvis.cache.annotation.ExCache;

@IocBean
public class AutoLoadCacheService {

    @Cache(autoload=true, expire=125, key="'test_'+@@hash(#args[0])")
    public AutoLoadCache getData(AutoLoadCache data) {
        System.out.println("get data from DAO >" + data.getName());
        AutoLoadCache tmp=new AutoLoadCache();
        tmp.setId(data.getId());
        tmp.setAge(data.getAge());
        tmp.setName(data.getName());
        return tmp;
    }

    @Cache(autoload=false, expire=125, key="'byid'", hfield="#args[0]", exCache={@ExCache(expire=125, key="'byname'", hfield="#retVal.name")})
    public AutoLoadCache getById(long id) {
        System.out.println("get data from DAO byId>" + id);
        AutoLoadCache tmp=new AutoLoadCache();
        tmp.setId(id);
        tmp.setAge(10);
        tmp.setName("name" + id);
        return tmp;
    }
    
    @Cache(autoload=false, expire=125, key="'byname'", hfield="#args[0]", exCache={@ExCache(expire=125, key="'byid'", hfield="#retVal.name")})
    public AutoLoadCache getByName(String name) {
        System.out.println("get data from DAO byName>" + name);
        AutoLoadCache tmp=new AutoLoadCache();
        String str=name.substring(4);
        tmp.setId(Long.parseLong(str));
        tmp.setAge(10);
        tmp.setName(name);
        return tmp;
    }
}
