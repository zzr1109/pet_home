package com.xw.dao;
import com.xw.entity.OrderDetailCenter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
@Mapper
public interface OrderDetailCenterDao {
    /**
     * @param orderDetailCenter 实例对象
     * @return 影响行数
     * @Description 添加OrderDetailCenter
     */
    int insert(OrderDetailCenter orderDetailCenter);
    /**
     * @param id 主键
     * @return 影响行数
     * @Description 删除OrderDetailCenter
     */
    int deleteById(Integer id);
    /**
     * @param id 主键
     * @return 实例对象
     * @Description 查询单条数据
     */
    OrderDetailCenter queryById(Integer id);
    /**
     * @return 对象列表
     * @Description 查询全部数据
     * 分页使用MyBatis的插件实现
     */
    List<OrderDetailCenter> queryAll();
    /**
     * @param orderDetailCenter 实例对象
     * @return 对象列表
     * @Description 实体作为筛选条件查询数据
     */
    List<OrderDetailCenter> queryAll(OrderDetailCenter orderDetailCenter);
    /**
     * @param 根据orderDetailCenter的主键修改数据
     * @return 影响行数
     * @Description 修改OrderDetailCenter
     */
    int updateById(OrderDetailCenter orderDetailCenter);
    int updateEntity(OrderDetailCenter orderDetailCenter);
    OrderDetailCenter queryByAll(OrderDetailCenter orderDetailCenter);
    void deleteBatchByIds(@Param("ids") List<Integer> ids);
}