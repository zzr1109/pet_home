package com.xw.dao;

import com.xw.entity.PetCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PetCategoryDao {
    // 插入语句
    int insert(PetCategory petCategory);
    // 通过主键ID删除
    int deleteById(Integer id);
    // 通过主键ID查询
    PetCategory queryById(Integer id);
    // 查询所有 (无参)
    List<PetCategory> queryAll();
    // 查询所有 (有参)
    List<PetCategory> queryAll(PetCategory petCategory);
    // 通过条件查询单条数据
    PetCategory queryByAll(PetCategory petCategory);
    // 通过ID修改 （有判断语句的）
    int updateById(PetCategory petCategory);
    // 通过ID修改 （没有判断语句的）
    int updateEntity(PetCategory petCategory);
    // 批量删除
    void deleteBatchByIds(@Param("ids") List<Integer> ids);
}
