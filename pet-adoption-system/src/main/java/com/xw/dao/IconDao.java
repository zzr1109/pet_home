package com.xw.dao;

import com.xw.entity.Icon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @InterfaceName IconDao
 * @Description (Icon)表数据库访问层
 * @Version 1.0
 **/
@Mapper
public interface IconDao {

    /**
     * @param icon 实例对象
     * @return 影响行数
     * @Description 添加Icon
     * @author makejava
     * @date 2024-07-12 11:03:21
     */
    int insert(Icon icon);

    /**
     * @param id 主键
     * @return 影响行数
     * @Description 删除Icon
     * @author makejava
     * @date 2024-07-12 11:03:21
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
     * @date 2024-07-12 11:03:21
     */
    Icon queryById(Integer id);

    /**
     * @return 对象列表
     * @Description 查询全部数据
     * @author makejava
     * @date 2024-07-12 11:03:21
     * 分页使用MyBatis的插件实现
     */
    List<Icon> queryAll();

    /**
     * @param icon 实例对象
     * @return 对象列表
     * @Description 实体作为筛选条件查询数据
     * @author makejava
     * @date 2024-07-12 11:03:21
     */
    List<Icon> queryAll(Icon icon);

    /**
     * @param 根据icon的主键修改数据
     * @return 影响行数
     * @Description 修改Icon
     * @author makejava
     * @date 2024-07-12 11:03:21
     */
    int update(Icon icon);

    Icon queryByAll(Icon icon);


}
