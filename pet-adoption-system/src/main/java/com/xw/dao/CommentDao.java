package com.xw.dao;
import com.xw.entity.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
@Mapper
public interface CommentDao {
    /**
     * @param comment 实例对象
     * @return 影响行数
     * @Description 添加Comment
     */
    int insert(Comment comment);
    /**
     * @param id 主键
     * @return 影响行数
     * @Description 删除Comment
     */
    int deleteById(Long id);
    /**
     * @param id 主键
     * @return 实例对象
     * @Description 查询单条数据
     */
    Comment queryById(Long id);
    /**
     * @return 对象列表
     * @Description 查询全部数据
     * 分页使用MyBatis的插件实现
     */
    List<Comment> queryAll();
    /**
     * @param comment 实例对象
     * @return 对象列表
     * @Description 实体作为筛选条件查询数据
     */
    List<Comment> queryAll(Comment comment);
    /**
     * @param 根据comment的主键修改数据
     * @return 影响行数
     * @Description 修改Comment
     */
    int updateById(Comment comment);
    int updateEntity(Comment comment);
    Comment queryByAll(Comment comment);
    void deleteBatchByIds(@Param("ids") List<Long> ids);

    @Delete("delete from comment where type_id = #{typeId} and type = #{type}")
    void deleteByTypeAndTypeId(int type, Integer typeId);
}