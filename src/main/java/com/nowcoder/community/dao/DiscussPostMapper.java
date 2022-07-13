package com.nowcoder.community.dao;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {
    //动态的sql  查询帖子 || 我发布的帖子   offset每页起始行号   limit每页最多显示多少条数据
    List<DiscussPost> selectDiscussPosts(int userId,int offset,int limit);

    //查询帖子条数
    // @Param注解用于给参数取别名
    //如果需要动态sql（在<if>里使用），且该方法只有一个参数时，必须要加@Param
    int selectDiscussPostRows(@Param("userId") int userId);


}
