package com.xw.dao;
import com.xw.entity.PostCode;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
@Mapper
public interface PostCodeDao {
    /**
     * @param postCode 实例对象
     * @return 影响行数
     * @Description 添加PostCode
     */
    int insert(PostCode postCode);
    /**
     * @param id 主键
     * @return 影响行数
     * @Description 删除PostCode
     */
    int deleteById(Integer id);
    /**
     * @param id 主键
     * @return 实例对象
     * @Description 查询单条数据
     */
    PostCode queryById(Integer id);
    /**
     * @return 对象列表
     * @Description 查询全部数据
     * 分页使用MyBatis的插件实现
     */
    List<PostCode> queryAll();
    /**
     * @param postCode 实例对象
     * @return 对象列表
     * @Description 实体作为筛选条件查询数据
     */
    List<PostCode> queryAll(PostCode postCode);
    /**
     * @param 根据postCode的主键修改数据
     * @return 影响行数
     * @Description 修改PostCode
     */
    int updateById(PostCode postCode);
    int updateEntity(PostCode postCode);
    PostCode queryByAll(PostCode postCode);
    void deleteBatchByIds(@Param("ids") List<Integer> ids);
}