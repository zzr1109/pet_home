package com.xw.dao;
import com.xw.entity.OrderCenter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
@Mapper
public interface OrderCenterDao {
    /**
     * @param orderCenter 实例对象
     * @return 影响行数
     * @Description 添加OrderCenter
     */
    int insert(OrderCenter orderCenter);
    /**
     * @param id 主键
     * @return 影响行数
     * @Description 删除OrderCenter
     */
    int deleteById(Integer id);
    /**
     * @param id 主键
     * @return 实例对象
     * @Description 查询单条数据
     */
    OrderCenter queryById(Integer id);
    /**
     * @return 对象列表
     * @Description 查询全部数据
     * 分页使用MyBatis的插件实现
     */
    List<OrderCenter> queryAll();
    /**
     * @param orderCenter 实例对象
     * @return 对象列表
     * @Description 实体作为筛选条件查询数据
     */
    List<OrderCenter> queryAll(OrderCenter orderCenter);
    /**
     * @param 根据orderCenter的主键修改数据
     * @return 影响行数
     * @Description 修改OrderCenter
     */
    int updateById(OrderCenter orderCenter);
    int updateEntity(OrderCenter orderCenter);
    OrderCenter queryByAll(OrderCenter orderCenter);
    void deleteBatchByIds(@Param("ids") List<Integer> ids);
}