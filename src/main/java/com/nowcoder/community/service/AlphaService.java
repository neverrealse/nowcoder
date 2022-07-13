package com.nowcoder.community.service;

import com.nowcoder.community.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service   //容器管理其创建、初始化和销毁的方法
@Scope("prototype")//多例注解；一般为单例，默认可以不写
public class AlphaService {
    @Autowired
    private AlphaDao alphaDao;
    public String find(){
        return alphaDao.select();
    }
    public AlphaService(){
        System.out.println("实例化AlphaService");
    }
    @PostConstruct   //在构造器之后
    public void init(){
        System.out.println("初始化AlphaService");
    }

    @PreDestroy  //在销毁之前
    public void destroy(){
        System.out.println("销毁");
    }
}
