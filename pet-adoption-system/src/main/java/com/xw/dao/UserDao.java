package com.xw.dao;

import com.xw.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface UserDao {

    /**
     * 插入语句
     * @param user 用户的实例化对象
     */
    void insert(User user);

    /**
     * 通过主键ID修改
     * @param user
     */
    void updateById(User user);



    /**
     * 通过主键ID查询用户单条信息
     * @param id
     * @return
     */
    User selectById(Integer id);

    /**
     * 通过条件查询用户数据
     * @param user
     * @return
     */
    List<User> selectAll(User user);


    /**
     * 通过主键ID删除用户
     * @param id
     */
    void deleteById(Integer id);

    User selectByUserNameAndPassword(User userQuery);

    /**
     * 查询救援中心
     * @return
     */
    @Select("select * from user where role='ROLE_ADMIN'")
    User selectRescue();

    @Select("select * from user where phone=#{phone}")
    User selectByPhone(@Param("phone") String phone);

    void deleteBatchByIds(@Param("ids") List<Integer> ids);
}
