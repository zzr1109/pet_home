package com.xw.dao;

import com.xw.entity.Roles;
import com.xw.rep.RoleByMenuRep;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @InterfaceName RolesDao
 * @Description (Roles)表数据库访问层
 * @Version 1.0
 **/
@Mapper
public interface RolesDao {

    /**
     * @param roles 实例对象
     * @return 影响行数
     * @Description 添加Roles
     * @author makejava
     * @date 2024-07-12 11:52:18
     */
    int insert(Roles roles);

    /**
     * @param id 主键
     * @return 影响行数
     * @Description 删除Roles
     * @author makejava
     * @date 2024-07-12 11:52:18
     */
    int deleteById(Integer id);

    /**
     * 批量删除
     *
     * @param ids
     */
    void deleteBatchByIds(@Param("ids") List<Integer> ids);

    /**
     * @param id 主键
     * @return 实例对象
     * @Description 查询单条数据
     * @author makejava
     * @date 2024-07-12 11:52:18
     */
    Roles queryById(Integer id);

    /**
     * @return 对象列表
     * @Description 查询全部数据
     * @author makejava
     * @date 2024-07-12 11:52:18
     * 分页使用MyBatis的插件实现
     */
    List<Roles> queryAll();

    /**
     * @param roles 实例对象
     * @return 对象列表
     * @Description 实体作为筛选条件查询数据
     * @author makejava
     * @date 2024-07-12 11:52:18
     */
    List<Roles> queryAll(Roles roles);

    /**
     * @param 根据roles的主键修改数据
     * @return 影响行数
     * @Description 修改Roles
     * @author makejava
     * @date 2024-07-12 11:52:18
     */
    int update(Roles roles);

    Roles queryByAll(Roles roles);

    @Select("select remark,name,id from roles where name=#{name}")
    Roles selectByRoleName(@Param("name") String name);

}
