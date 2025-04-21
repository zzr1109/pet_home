package com.xw.dao;

import com.xw.entity.PetCenter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PetCenterDao {
    // 插入语句
    int insert(PetCenter petCenter);
    // 通过主键ID删除
    int deleteById(Integer id);
    // 通过主键ID查询
    PetCenter queryById(Integer id);
    // 查询所有 (无参)
    List<PetCenter> queryAll();
    // 查询所有 (有参)
    List<PetCenter> queryAll(PetCenter petCenter);
    // 通过条件查询单条数据
    PetCenter queryByAll(PetCenter petCenter);
    // 通过ID修改 （有判断语句的）
    int updateById(PetCenter petCenter);
    // 通过ID修改 （没有判断语句的）
    int updateEntity(PetCenter petCenter);
    // 批量删除
    void deleteBatchByIds(@Param("ids") List<Integer> ids);
}
