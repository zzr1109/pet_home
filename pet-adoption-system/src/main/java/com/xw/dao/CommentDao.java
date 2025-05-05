package com.xw.dao;

import com.xw.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentDao {
    // 插入语句
    int insert(Comment comment);
    // 通过主键ID删除
    int deleteById(Long id);
    // 通过主键ID查询
    Comment queryById(Long id);
    // 查询所有 (无参)
    List<Comment> queryAll();
    // 查询所有 (有参)
    List<Comment> queryAll(Comment comment);
    // 通过条件查询单条数据
    Comment queryByAll(Comment comment);
    // 通过ID修改 （有判断语句的）
    int updateById(Comment comment);
    // 通过ID修改 （没有判断语句的）
    int updateEntity(Comment comment);
    // 批量删除
    void deleteBatchByIds(@Param("ids") List<Long> ids);
}
