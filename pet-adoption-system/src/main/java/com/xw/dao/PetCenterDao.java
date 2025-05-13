package com.xw.dao;
import com.xw.entity.PetCenter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
@Mapper
public interface PetCenterDao {
    /**
     * @param petCenter 实例对象
     * @return 影响行数
     * @Description 添加PetCenter
     */
    int insert(PetCenter petCenter);
    /**
     * @param id 主键
     * @return 影响行数
     * @Description 删除PetCenter
     */
    int deleteById(Integer id);
    /**
     * @param id 主键
     * @return 实例对象
     * @Description 查询单条数据
     */
    PetCenter queryById(Integer id);
    /**
     * @return 对象列表
     * @Description 查询全部数据
     * 分页使用MyBatis的插件实现
     */
    List<PetCenter> queryAll();
    /**
     * @param petCenter 实例对象
     * @return 对象列表
     * @Description 实体作为筛选条件查询数据
     */
    List<PetCenter> queryAll(PetCenter petCenter);
    /**
     * @param 根据petCenter的主键修改数据
     * @return 影响行数
     * @Description 修改PetCenter
     */
    int updateById(PetCenter petCenter);
    int updateEntity(PetCenter petCenter);
    PetCenter queryByAll(PetCenter petCenter);
    void deleteBatchByIds(@Param("ids") List<Integer> ids);

    List<PetCenter> queryLimit12();
}