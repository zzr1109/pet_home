package com.xw.dao;

import com.xw.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @InterfaceName MenuDao
 * @Description (Menu)表数据库访问层
 * @Version 1.0
 **/
@Mapper
public interface MenuDao {

    /**
     * @param menu 实例对象
     * @return 影响行数
     * @Description 添加Menu
     * @author makejava
     * @date 2024-07-12 11:10:10
     */
    int insert(Menu menu);

    /**
     * @param id 主键
     * @return 影响行数
     * @Description 删除Menu
     * @author makejava
     * @date 2024-07-12 11:10:10
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
     * @date 2024-07-12 11:10:10
     */
    Menu queryById(Integer id);

    /**
     * @return 对象列表
     * @Description 查询全部数据
     * @author makejava
     * @date 2024-07-12 11:10:10
     * 分页使用MyBatis的插件实现
     */
    List<Menu> queryAll();

    /**
     * @param menu 实例对象
     * @return 对象列表
     * @Description 实体作为筛选条件查询数据
     * @author makejava
     * @date 2024-07-12 11:10:10
     */
    List<Menu> queryAll(Menu menu);

    /**
     * @param 根据menu的主键修改数据
     * @return 影响行数
     * @Description 修改Menu
     * @author makejava
     * @date 2024-07-12 11:10:10
     */
    int update(Menu menu);

    Menu queryByAll(Menu menu);

    List<Menu> queryParentMenu();

    List<Menu> menuChildrenList(@Param("menuName") String menuName,@Param("menuIds") List<Integer> menuIds);
}
