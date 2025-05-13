package com.xw.dao;
import com.xw.entity.GoodsCenter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;
@Mapper
public interface GoodsCenterDao {
    /**
     * @param goodsCenter 实例对象
     * @return 影响行数
     * @Description 添加GoodsCenter
     */
    int insert(GoodsCenter goodsCenter);
    /**
     * @param id 主键
     * @return 影响行数
     * @Description 删除GoodsCenter
     */
    int deleteById(Integer id);
    /**
     * @param id 主键
     * @return 实例对象
     * @Description 查询单条数据
     */
    GoodsCenter queryById(Integer id);
    /**
     * @return 对象列表
     * @Description 查询全部数据
     * 分页使用MyBatis的插件实现
     */
    List<GoodsCenter> queryAll();
    /**
     * @param goodsCenter 实例对象
     * @return 对象列表
     * @Description 实体作为筛选条件查询数据
     */
    List<GoodsCenter> queryAll(GoodsCenter goodsCenter);
    /**
     * @param 根据goodsCenter的主键修改数据
     * @return 影响行数
     * @Description 修改GoodsCenter
     */
    int updateById(GoodsCenter goodsCenter);
    int updateEntity(GoodsCenter goodsCenter);
    GoodsCenter queryByAll(GoodsCenter goodsCenter);
    void deleteBatchByIds(@Param("ids") List<Integer> ids);
}