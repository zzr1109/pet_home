package com.xw.dao;

import com.xw.entity.PetAdoptionCenter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PetAdoptionCenterDao {
    // 插入语句
    int insert(PetAdoptionCenter petAdoptionCenter);
    // 通过主键ID删除
    int deleteById(Long id);
    // 通过主键ID查询
    PetAdoptionCenter queryById(Long id);
    // 查询所有 (无参)
    List<PetAdoptionCenter> queryAll();
    // 查询所有 (有参)
    List<PetAdoptionCenter> queryAll(PetAdoptionCenter petAdoptionCenter);
    // 通过条件查询单条数据
    PetAdoptionCenter queryByAll(PetAdoptionCenter petAdoptionCenter);
    // 通过ID修改 （有判断语句的）
    int updateById(PetAdoptionCenter petAdoptionCenter);
    // 通过ID修改 （没有判断语句的）
    int updateEntity(PetAdoptionCenter petAdoptionCenter);
    // 批量删除
    void deleteBatchByIds(@Param("ids") List<Long> ids);
}
