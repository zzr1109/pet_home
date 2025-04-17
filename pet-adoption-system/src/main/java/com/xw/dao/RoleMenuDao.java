package com.xw.dao;

import com.xw.entity.RoleMenu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

/**
 * @InterfaceName RoleMenuDao
 * @Description (RoleMenu)表数据库访问层
 * @Version 1.0
 **/
@Mapper
public interface RoleMenuDao {

    /**
     * @param roleMenu 实例对象
     * @return 影响行数
     * @Description 添加RoleMenu
     * @author makejava
     * @date 2024-07-12 11:29:03
     */
    int insert(RoleMenu roleMenu);

    /**
     * @param id 主键
     * @return 影响行数
     * @Description 删除RoleMenu
     * @author makejava
     * @date 2024-07-12 11:29:03
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
     * @date 2024-07-12 11:29:03
     */
    RoleMenu queryById(Integer id);

    /**
     * @return 对象列表
     * @Description 查询全部数据
     * @author makejava
     * @date 2024-07-12 11:29:03
     * 分页使用MyBatis的插件实现
     */
    List<RoleMenu> queryAll();

    /**
     * @param roleMenu 实例对象
     * @return 对象列表
     * @Description 实体作为筛选条件查询数据
     * @author makejava
     * @date 2024-07-12 11:29:03
     */
    List<RoleMenu> queryAll(RoleMenu roleMenu);

    /**
     * @param 根据roleMenu的主键修改数据
     * @return 影响行数
     * @Description 修改RoleMenu
     * @author makejava
     * @date 2024-07-12 11:29:03
     */
    int update(RoleMenu roleMenu);

    RoleMenu queryByAll(RoleMenu roleMenu);


    @Select("select menu_id from role_menu where role_id=#{roleId}")
    List<Integer> selectByRoleId(@Param("roleId") Integer roleId);

    @Delete("delete from role_menu where role_id=#{roleId}")
    void deleteByRoleId(@Param("roleId") Integer roleId);
}
