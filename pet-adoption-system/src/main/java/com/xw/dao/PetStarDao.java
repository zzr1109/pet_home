package com.xw.dao;
import com.xw.entity.PetStar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
@Mapper
public interface PetStarDao {
    /**
     * @param petStar 实例对象
     * @return 影响行数
     * @Description 添加PetStar
     */
    int insert(PetStar petStar);
    /**
     * @param id 主键
     * @return 影响行数
     * @Description 删除PetStar
     */
    int deleteById(Integer id);
    /**
     * @param id 主键
     * @return 实例对象
     * @Description 查询单条数据
     */
    PetStar queryById(Integer id);
    /**
     * @return 对象列表
     * @Description 查询全部数据
     * 分页使用MyBatis的插件实现
     */
    List<PetStar> queryAll();
    List<PetStar> queryLimit10();
    /**
     * @param petStar 实例对象
     * @return 对象列表
     * @Description 实体作为筛选条件查询数据
     */
    List<PetStar> queryAll(PetStar petStar);
    /**
     * @param 根据petStar的主键修改数据
     * @return 影响行数
     * @Description 修改PetStar
     */
    int updateById(PetStar petStar);
    int updateEntity(PetStar petStar);
    PetStar queryByAll(PetStar petStar);
    void deleteBatchByIds(@Param("ids") List<Integer> ids);

}