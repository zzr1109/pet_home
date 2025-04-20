package com.xw.dao;

import com.xw.entity.PetCrousel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PetCrouselDao {
    //插入语句
    int insert(PetCrousel petCrousel);
    //通过主键id删除
    int deleteById(Integer id);
    //通过主键ID查询
    PetCrousel queryById(Integer id);
    //查询所有(无参）
    List<PetCrousel> queryAll();
    //查询所有(有参）
    List<PetCrousel> queryAll(PetCrousel petCrousel);
    //通过条件查询单条
    PetCrousel queryByALL(PetCrousel petCrousel);
    //通过id修改(有判断语句)
    int updateById(PetCrousel petCrousel);
    //通过id修改(无）
    int  updateEntity(PetCrousel petCrousel);
    //批量删除
    void deleteBatchByIds(@Param("ids") List<Integer> ids);
}
