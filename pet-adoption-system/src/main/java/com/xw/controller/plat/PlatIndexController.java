package com.xw.controller.plat;
import com.xw.common.Constants;
import com.xw.common.Result;
import com.xw.common.TokenHandler;
import com.xw.dao.*;
import com.xw.entity.*;
import com.xw.exp.ServiceException;
import com.xw.req.PageRequest;
import com.xw.service.PetCenterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/plat/index")
@Tag(name = "（用户端）首页模块")
public class PlatIndexController {

    @Resource
    private PetCrouselDao petCrouselDao;

    @Resource
    private PetStarDao petStarDao;

    @Resource
    private PetCenterDao petCenterDao;

    @Resource
    private PetCenterService petCenterService;

    @Resource
    private PetCategoryDao petCategoryDao;

    @Resource
    private UserDao userDao;

    @Resource
    private PetAdoptionCenterDao petAdoptionCenterDao;

    @Resource
    private PetReservationDao petReservationDao;

    @GetMapping("crousel")
    @Operation(summary = "轮播图")
    public Result<?> crousel() {
        List<PetCrousel> petCrousels = petCrouselDao.queryLimit10();
        for (PetCrousel petCrousel : petCrousels) {
            if (StringUtils.hasText(petCrousel.getImageUrlList())) {
                petCrousel.setMenuImages(Arrays.stream(petCrousel.getImageUrlList().split(",")).collect(Collectors.toList()));
            }
        }
        return new Result<>(petCrousels, Constants.SUCCESS);
    }

    @GetMapping("recommendedPets")
    @Operation(summary = "推荐宠物(明星值宠物)")
    public Result<?> recommendedPets() {
        List<PetStar> petStars = petStarDao.queryLimit10();
        List<Integer> petIds = petStars.stream().map(PetStar::getPetCenterId).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(petIds)) {
            return new Result<>(Constants.SUCCESS);
        }
        PetCenter petCenter = new PetCenter();
        petCenter.setIds(petIds);
        List<PetCenter> petCenters = petCenterDao.queryAll(petCenter);
        for (PetCenter center : petCenters) {
            Optional<Integer> petStarOptional = petStars.stream().filter(u -> u.getPetCenterId().compareTo(center.getId()) == 0).map(PetStar::getStarNum).findAny();
            center.setStarNum(petStarOptional.orElse(0));

            if (StringUtils.hasText(center.getImageUrlList())) {
                center.setMenuImages(Arrays.stream(center.getImageUrlList().split(",")).collect(Collectors.toList()));
            }
        }
        return new Result<>(petCenters.stream().sorted(Comparator.comparing(PetCenter::getStarNum).reversed()).collect(Collectors.toList()), Constants.SUCCESS);
    }

    @PostMapping("petDisplay")
    @Operation(summary = "宠物展示列表")
    public Result<?> petDisplay(@RequestBody PageRequest<PetCenter> pageRequest) {
        return new Result<>(petCenterService.queryByPage(pageRequest),Constants.SUCCESS);
    }

    @GetMapping("petCategory")
    @Operation(summary = "宠物类别列表")
    public Result<?> petCategory() {
        return new Result<>(petCategoryDao.queryAll(),Constants.SUCCESS);
    }

    @GetMapping("addStar")
    @Operation(summary = "添加明星值")
    public Result<?> addStar(@RequestParam("petId") Integer petId) {
        PetCenter petCenter = petCenterDao.queryById(petId);
        if (Objects.isNull(petCenter)) {
            throw new ServiceException(Constants.ERROR.getCode(), "宠物不存在！");
        }
        PetStar petStarQuery = new PetStar();
        petStarQuery.setPetCenterId(petId);
        PetStar petStar = petStarDao.queryByAll(petStarQuery);
        if (Objects.isNull(petStar)) {
            PetStar petStarAdd = new PetStar();
            petStarAdd.setPetCenterId(petId);
            petStarAdd.setPetName(petCenter.getPetName());
            petStarAdd.setStarNum(1);
            petStarDao.insert(petStarAdd);
        } else {
            PetStar petUpdate = new PetStar();
            petUpdate.setPetCenterId(petId);
            petUpdate.setPetName(petCenter.getPetName());
            petUpdate.setStarNum(petStar.getStarNum() + 1);
            petUpdate.setId(petStar.getId());
            petStarDao.updateById(petUpdate);
        }
        return new Result<>(Constants.SUCCESS);
    }

    @PostMapping("adopt")
    @Operation(summary = "我要领养")
    public Result<?> adopt(@RequestBody @Validated PetReservation petReservation) {
        petReservation.setUserInfo(TokenHandler.getUserInfo());
        PetReservation query = new PetReservation();
        query.setPetCenterId(petReservation.getPetCenterId());
        query.setUserId(petReservation.getUserId());
        PetReservation petReservationQuery = petReservationDao.queryByAll(query);
        if (Objects.nonNull(petReservationQuery)) {
            throw new ServiceException(Constants.ERROR.getCode(), "您已有该宠物的领养预约记录,请前往个人中心清除该记录后再次领养！！！");
        }
        petReservationDao.insert(petReservation);
        return new Result<>(Constants.SUCCESS);
    }

    @GetMapping("adoptList")
    @Operation(summary = "最新领养")
    public Result<?> adoptList() {
        List<PetReservation> petReservationList = petReservationDao.queryAllLimit10();
        return new Result<>(petReservationList,Constants.SUCCESS);
    }

    @GetMapping("getPersonInfo")
    @Operation(summary = "个人信息")
    public Result<?> getPersonInfo(@RequestParam(required = false) Integer userId) {
        if (userId == null) {
            return new Result<>(Constants.SUCCESS);
        }
        return new Result<>(userDao.selectById(userId),Constants.SUCCESS);
    }
}
