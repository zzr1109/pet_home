package com.xw.dao;
import com.xw.entity.PetAdoptionCenter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
@Mapper
public interface PetAdoptionCenterDao {
    /**
     * @param petAdoptionCenter 实例对象
     * @return 影响行数
     * @Description 添加PetAdoptionCenter
     */
    int insert(PetAdoptionCenter petAdoptionCenter);
    /**
     * @param id 主键
     * @return 影响行数
     * @Description 删除PetAdoptionCenter
     */
    int deleteById(Integer id);
    /**
     * @param id 主键
     * @return 实例对象
     * @Description 查询单条数据
     */
    PetAdoptionCenter queryById(Integer id);
    /**
     * @return 对象列表
     * @Description 查询全部数据
     * 分页使用MyBatis的插件实现
     */
    List<PetAdoptionCenter> queryAll();
    /**
     * @param petAdoptionCenter 实例对象
     * @return 对象列表
     * @Description 实体作为筛选条件查询数据
     */
    List<PetAdoptionCenter> queryAll(PetAdoptionCenter petAdoptionCenter);
    /**
     * @param 根据petAdoptionCenter的主键修改数据
     * @return 影响行数
     * @Description 修改PetAdoptionCenter
     */
    int updateById(PetAdoptionCenter petAdoptionCenter);
    int updateEntity(PetAdoptionCenter petAdoptionCenter);
    PetAdoptionCenter queryByAll(PetAdoptionCenter petAdoptionCenter);
    void deleteBatchByIds(@Param("ids") List<Integer> ids);
}