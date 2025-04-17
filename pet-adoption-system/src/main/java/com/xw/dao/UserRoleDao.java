package com.xw.dao;

import com.xw.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @InterfaceName UserRoleDao
 * @Description (UserRole)表数据库访问层
 * @Version 1.0
 **/
@Mapper
public interface UserRoleDao {

    /**
     * @param userRole 实例对象
     * @return 影响行数
     * @Description 添加UserRole
     * @author makejava
     * @date 2024-07-12 11:57:24
     */
    int insert(UserRole userRole);

    /**
     * @param id 主键
     * @return 影响行数
     * @Description 删除UserRole
     * @author makejava
     * @date 2024-07-12 11:57:24
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
     * @date 2024-07-12 11:57:24
     */
    UserRole queryById(Integer id);

    /**
     * @return 对象列表
     * @Description 查询全部数据
     * @author makejava
     * @date 2024-07-12 11:57:24
     * 分页使用MyBatis的插件实现
     */
    List<UserRole> queryAll();

    /**
     * @param userRole 实例对象
     * @return 对象列表
     * @Description 实体作为筛选条件查询数据
     * @author makejava
     * @date 2024-07-12 11:57:24
     */
    List<UserRole> queryAll(UserRole userRole);

    /**
     * @param 根据userRole的主键修改数据
     * @return 影响行数
     * @Description 修改UserRole
     * @author makejava
     * @date 2024-07-12 11:57:24
     */
    int update(UserRole userRole);

    UserRole queryByAll(UserRole userRole);


}
