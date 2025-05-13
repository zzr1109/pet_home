package com.xw.dao;
import com.xw.entity.PetReservation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;
@Mapper
public interface PetReservationDao {
    /**
     * @param petReservation 实例对象
     * @return 影响行数
     * @Description 添加PetReservation
     */
    int insert(PetReservation petReservation);
    /**
     * @param id 主键
     * @return 影响行数
     * @Description 删除PetReservation
     */
    int deleteById(Integer id);
    /**
     * @param id 主键
     * @return 实例对象
     * @Description 查询单条数据
     */
    PetReservation queryById(Integer id);
    /**
     * @return 对象列表
     * @Description 查询全部数据
     * 分页使用MyBatis的插件实现
     */
    List<PetReservation> queryAll();
    /**
     * @param petReservation 实例对象
     * @return 对象列表
     * @Description 实体作为筛选条件查询数据
     */
    List<PetReservation> queryAll(PetReservation petReservation);
    /**
     * @param 根据petReservation的主键修改数据
     * @return 影响行数
     * @Description 修改PetReservation
     */
    int updateById(PetReservation petReservation);
    int updateEntity(PetReservation petReservation);
    PetReservation queryByAll(PetReservation petReservation);
    void deleteBatchByIds(@Param("ids") List<Integer> ids);

    @Update("update pet_reservation set status = 4 where user_id=#{userId} and pet_center_id = #{petCenterId} and status = 2")
    void updateByUserIdAndPetId(Integer userId, Integer petCenterId);

    List<PetReservation> queryAllLimit10();
}