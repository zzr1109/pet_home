package com.xw.dao;

import com.xw.entity.PetReservation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PetReservationDao {
    // 插入语句
    int insert(PetReservation petReservation);
    // 通过主键ID删除
    int deleteById(Long id);
    // 通过主键ID查询
    PetReservation queryById(Long id);
    // 查询所有 (无参)
    List<PetReservation> queryAll();
    // 查询所有 (有参)
    List<PetReservation> queryAll(PetReservation petReservation);
    // 通过条件查询单条数据
    PetReservation queryByAll(PetReservation petReservation);
    // 通过ID修改 （有判断语句的）
    int updateById(PetReservation petReservation);
    // 通过ID修改 （没有判断语句的）
    int updateEntity(PetReservation petReservation);
    // 批量删除
    void deleteBatchByIds(@Param("ids") List<Long> ids);
}
