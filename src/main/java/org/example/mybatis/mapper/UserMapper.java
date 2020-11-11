package org.example.mybatis.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.mybatis.entity.User;

import java.util.List;

/**
 * @description:
 * @author: czm, PC of Chenzhimei
 * @time: 2020/11/2 18:03
 */
@Mapper
public interface UserMapper {
    User findById(@Param("uid")Integer userId);

    List<User> findAll();

    int update(@Param("user")User user);

//    int add(@Param("user")User user);

    int add(User user);

    int addBatch(@Param("list")List<User> users);
//int addBatch(List<User> users);

}
