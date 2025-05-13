package com.xw.dao;
import com.xw.entity.PetCrousel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
@Mapper
public interface PetCrouselDao {
    /**
     * @param petCrousel 实例对象
     * @return 影响行数
     * @Description 添加PetCrousel
     */
    int insert(PetCrousel petCrousel);
    /**
     * @param id 主键
     * @return 影响行数
     * @Description 删除PetCrousel
     */
    int deleteById(Integer id);
    /**
     * @param id 主键
     * @return 实例对象
     * @Description 查询单条数据
     */
    PetCrousel queryById(Integer id);
    /**
     * @return 对象列表
     * @Description 查询全部数据
     * 分页使用MyBatis的插件实现
     */
    List<PetCrousel> queryAll();

    List<PetCrousel> queryLimit10();
    /**
     * @param petCrousel 实例对象
     * @return 对象列表
     * @Description 实体作为筛选条件查询数据
     */
    List<PetCrousel> queryAll(PetCrousel petCrousel);
    /**
     * @param 根据petCrousel的主键修改数据
     * @return 影响行数
     * @Description 修改PetCrousel
     */
    int updateById(PetCrousel petCrousel);
    int updateEntity(PetCrousel petCrousel);
    PetCrousel queryByAll(PetCrousel petCrousel);
    void deleteBatchByIds(@Param("ids") List<Integer> ids);
}