package com.xw.dao;

import com.xw.entity.User;
import com.xw.entity.UserToken;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface UserTokenDao {

    /**
     * 插入语句
     * @param
     */
    void insert(UserToken userToken);

    /**
     * 通过主键ID修改
     * @param userToken
     */
    void updateById(UserToken userToken);

    /**
     * 通过主键ID查询
     * @param id
     * @return
     */
    User selectById(Integer id);

    /**
     * 通过条件查询
     * @param user
     * @return
     */
    List<UserToken> selectAll(UserToken userToken);


    /**
     * 通过token删除
     * @param token
     */
    void deleteByToken(String token);

    UserToken selectByUserId(Integer userId);

    void deleteById(Integer id);

    UserToken selectByToken(String token);
}
