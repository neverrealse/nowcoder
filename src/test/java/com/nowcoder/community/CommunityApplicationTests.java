package com.nowcoder.community;

import com.nowcoder.community.dao.AlphaDao;
import com.nowcoder.community.service.AlphaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)//设置test也采用CommunityApplication为配置类
class CommunityApplicationTests implements ApplicationContextAware{
    //记录spring容器
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        //ApplicationContext 就是spring容器
        this.applicationContext = applicationContext;
    }

    //测试spring容器
    @Test
    public void testApplicationContext(){
        System.out.println(applicationContext);
        //获取bean的方法
        AlphaDao a =  applicationContext.getBean(AlphaDao.class);//多个实现类，primary注解   面向接口编程的思想，管理bean，降低bean间的耦合度
        String res = a.select();
        System.out.println(res);

        AlphaDao b =  applicationContext.getBean("hb",AlphaDao.class);//以名字获取bean,并给定类型
        String res2 = b.select();
        System.out.println(res2);
    }

    @Test
    public void testBeanManagement(){
        AlphaService alphaService = applicationContext.getBean(AlphaService.class);
        System.out.println(alphaService);  //bean是单例的，bean只实例化一次

        alphaService = applicationContext.getBean(AlphaService.class);
        System.out.println(alphaService);
    }

    @Test
    public void testBeanConfig(){
        SimpleDateFormat simpleDateFormat = applicationContext.getBean(SimpleDateFormat.class);
        System.out.println(simpleDateFormat.format(new Date()));
    }

    @Autowired  //根据属性类型注入
    @Qualifier("hb")  //根据属性名称进行注入，要和@Autowired一起，否则报错
    private AlphaDao alphaDao;
    @Autowired
    private SimpleDateFormat simpleDateFormat;
    @Test
    public void testDI(){
        String res = alphaDao.select();
        System.out.println(res);
        System.out.println(simpleDateFormat.format(new Date()));
    }
}
