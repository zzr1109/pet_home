package com.xw.dao;
import com.xw.entity.PetCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
@Mapper
public interface PetCategoryDao {
    /**
     * @param petCategory 实例对象
     * @return 影响行数
     * @Description 添加PetCategory
     */
    int insert(PetCategory petCategory);
    /**
     * @param id 主键
     * @return 影响行数
     * @Description 删除PetCategory
     */
    int deleteById(Integer id);
    /**
     * @param id 主键
     * @return 实例对象
     * @Description 查询单条数据
     */
    PetCategory queryById(Integer id);
    /**
     * @return 对象列表
     * @Description 查询全部数据
     * 分页使用MyBatis的插件实现
     */
    List<PetCategory> queryAll();
    /**
     * @param petCategory 实例对象
     * @return 对象列表
     * @Description 实体作为筛选条件查询数据
     */
    List<PetCategory> queryAll(PetCategory petCategory);
    /**
     * @param 根据petCategory的主键修改数据
     * @return 影响行数
     * @Description 修改PetCategory
     */
    int updateById(PetCategory petCategory);
    int updateEntity(PetCategory petCategory);
    PetCategory queryByAll(PetCategory petCategory);
    void deleteBatchByIds(@Param("ids") List<Integer> ids);
}