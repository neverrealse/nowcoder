package com.nowcoder.community.dao.Impl;

import com.nowcoder.community.dao.AlphaDao;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository  //创建bean ；添加到ioc容器，实现bean的管理
@Primary    //更高优先级
public class AlphaDaoImpl2 implements AlphaDao {

    @Override
    public String select() {
        return "hb替换为mybatis";
    }
}
