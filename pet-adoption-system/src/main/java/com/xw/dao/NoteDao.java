package com.xw.dao;
import com.xw.entity.Note;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface NoteDao {
    /**
     * 插入语句
     */
    void insert(Note note);

    /**
     * 通过主键ID修改
     */
    void updateById(Note note);

    void updateEntity(Note note);

    /**
     * 通过主键ID查询用户单条信息
     */
    Note selectById(Integer id);

    /**
     * 通过条件查询数据
     */
    List<Note> selectAll(Note note);


    /**
     * 通过主键ID删除用户
     * @param id
     */
    void deleteById(Integer id);


    void deleteBatchByIds(@Param("ids") List<Integer> ids);
}
