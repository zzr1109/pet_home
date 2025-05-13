package com.xw.service.impl;
import com.xw.entity.PetReservation;
import com.xw.dao.PetReservationDao;
import com.xw.service.PetReservationService;
import org.springframework.stereotype.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xw.req.PageRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.util.StringUtils;
import jakarta.annotation.Resource;
/**
 * 宠物预约管理
 */
@Service("petReservationService")
public class PetReservationServiceImpl implements PetReservationService {
    @Resource
    private PetReservationDao petReservationDao;
    /**
     * 分页查询
     *
     * @return 查询结果
     */
    @Override
    public PageInfo<PetReservation> queryByPage(PageRequest<PetReservation> pageRequest) {
        //开始分页
        PageHelper.startPage(pageRequest.getPageNum(), pageRequest.getPageSize());
        List<PetReservation> list = this.petReservationDao.queryAll(pageRequest.getQ());
        Map<Integer, List<PetReservation>> maps = list.stream().collect(Collectors.groupingBy(PetReservation::getPetCenterId));
        for (Map.Entry<Integer, List<PetReservation>> map : maps.entrySet()) {
            Optional<PetReservation> petReservationOptional = map.getValue().stream().filter(u -> u.getStatus().compareTo(2) == 0).findAny();
            if (!petReservationOptional.isPresent()) {
                continue;
            }
            for (PetReservation petReservation : list) {
                petReservation.setWhetherToAdoptOrNot(false);
                if (petReservation.getPetCenterId().compareTo(map.getKey()) == 0) {
                    petReservation.setWhetherToAdoptOrNot(true);
                }
            }
        }
        PageInfo<PetReservation> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}