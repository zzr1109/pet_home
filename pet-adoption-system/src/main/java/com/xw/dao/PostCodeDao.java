package com.xw.dao;

import com.xw.entity.PostCode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostCodeDao {
    // 插入语句
    int insert(PostCode postCode);
    // 通过主键ID删除
    int deleteById(Integer id);
    // 通过主键ID查询
    PostCode queryById(Integer id);
    // 查询所有 (无参)
    List<PostCode> queryAll();
    // 查询所有 (有参)
    List<PostCode> queryAll(PostCode postCode);
    // 通过条件查询单条数据
    PostCode queryByAll(PostCode postCode);
    // 通过ID修改 （有判断语句的）
    int updateById(PostCode postCode);
    // 通过ID修改 （没有判断语句的）
    int updateEntity(PostCode postCode);
    // 批量删除
    void deleteBatchByIds(@Param("ids") List<Integer> ids);
}
