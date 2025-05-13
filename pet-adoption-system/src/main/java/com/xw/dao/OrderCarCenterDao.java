package com.xw.dao;
import com.xw.entity.OrderCarCenter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
@Mapper
public interface OrderCarCenterDao {
    /**
     * @param orderCarCenter 实例对象
     * @return 影响行数
     * @Description 添加OrderCarCenter
     */
    int insert(OrderCarCenter orderCarCenter);
    /**
     * @param id 主键
     * @return 影响行数
     * @Description 删除OrderCarCenter
     */
    int deleteById(Integer id);
    /**
     * @param id 主键
     * @return 实例对象
     * @Description 查询单条数据
     */
    OrderCarCenter queryById(Integer id);
    /**
     * @return 对象列表
     * @Description 查询全部数据
     * 分页使用MyBatis的插件实现
     */
    List<OrderCarCenter> queryAll();
    /**
     * @param orderCarCenter 实例对象
     * @return 对象列表
     * @Description 实体作为筛选条件查询数据
     */
    List<OrderCarCenter> queryAll(OrderCarCenter orderCarCenter);
    /**
     * @param 根据orderCarCenter的主键修改数据
     * @return 影响行数
     * @Description 修改OrderCarCenter
     */
    int updateById(OrderCarCenter orderCarCenter);
    int updateEntity(OrderCarCenter orderCarCenter);
    OrderCarCenter queryByAll(OrderCarCenter orderCarCenter);
    void deleteBatchByIds(@Param("ids") List<Integer> ids);
}