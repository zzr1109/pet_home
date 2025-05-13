package com.xw.controller;


import com.xw.common.Constants;
import com.xw.common.Result;
import com.xw.dao.*;
import com.xw.entity.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("data")
@Tag(name = "统计模块")
public class DataController {
    @Resource
    private PetCenterDao petCenterDao;

    @Resource
    private PetAdoptionCenterDao petAdoptionCenterDao;

    @Resource
    private PetCategoryDao petCategoryDao;

    @Resource
    private OrderCenterDao orderCenterDao;

    @Resource
    private GoodsCenterDao goodsCenterDao;


    @GetMapping("petClassificationPieChart")
    @Operation(summary = "宠物分类饼图")
    public Result<?> petClassificationPieChart() {
        List<PetCenter> petCenters = petCenterDao.queryAll();
        Map<String, List<PetCenter>> maps = petCenters.stream().collect(Collectors.groupingBy(PetCenter::getPetCategoryName));
        List<Map<String,Object>> listData = new ArrayList<>();
        for (Map.Entry<String, List<PetCenter>> map : maps.entrySet()) {
            Map<String,Object> data = new HashMap<>();
            data.put("name",map.getKey());
            data.put("value",map.getValue().stream().count());
            listData.add(data);
        }
        return new Result<>(listData, Constants.SUCCESS);
    }

    @GetMapping("barChartOfSuccessfulAdoption")
    @Operation(summary = "领养成功柱状图")
    public Result<?> barChartOfSuccessfulAdoption() {
        List<PetAdoptionCenter> petAdoptionCenters = petAdoptionCenterDao.queryAll();
        List<PetAdoptionCenter> petAdoptionCenterList = petAdoptionCenters.stream().filter(u -> u.getAdoptionStatus().compareTo(0) == 0).collect(Collectors.toList());
        List<Integer> petIds = petAdoptionCenterList.stream().map(PetAdoptionCenter::getPetCenterId).collect(Collectors.toList());

        List<PetCenter> petCenterList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(petIds)) {
            PetCenter petCenter = new PetCenter();
            petCenter.setIds(petIds);
            petCenterList = petCenterDao.queryAll(petCenter);
        }


        List<PetCategory> petCategories = petCategoryDao.queryAll();
        // 条目
        List<String> entryList = petCategories.stream().map(PetCategory::getCategory).collect(Collectors.toList());
        // 条目对应数量
        List<Long> dataList = new ArrayList<>();
        for (String entry : entryList) {
            List<PetCenter> collect = petCenterList.stream().filter(u -> u.getPetCategoryName().equals(entry)).collect(Collectors.toList());
            if (CollectionUtils.isEmpty(collect)) {
                dataList.add(0L);
            } else {
                dataList.add(collect.stream().count());
            }
        }

        Map<String,Object> map = new HashMap<>();
        map.put("entryList",entryList);
        map.put("dataList",dataList);
        return new Result<>(map, Constants.SUCCESS);
    }

    @GetMapping("dataStatistics")
    @Operation(summary = "数据统计")
    public Result<?> dataStatistics() {
        List<OrderCenter> orderCenters = orderCenterDao.queryAll();
        Map<String,Object> map = new HashMap<>();
        map.put("orderPrice",orderCenters.stream().map(OrderCenter::getOrderPrice).reduce((m,n) -> m.add(n)).orElse(BigDecimal.ZERO));
        map.put("orderNum",orderCenters.stream().count());
        List<GoodsCenter> goodsCenters = goodsCenterDao.queryAll();
        map.put("goodsNum",goodsCenters.stream().count());
        List<PetCenter> petCenters = petCenterDao.queryAll();
        map.put("petNum",petCenters.stream().count());
        return new Result<>(map,Constants.SUCCESS);
    }
}
