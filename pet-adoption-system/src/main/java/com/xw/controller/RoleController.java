package com.xw.controller;
import com.github.pagehelper.PageInfo;
import com.xw.common.Constants;
import com.xw.common.Result;
import com.xw.dao.RolesDao;
import com.xw.entity.Roles;
import com.xw.rep.RoleByMenuRep;
import com.xw.req.AddRoleMenuReq;
import com.xw.req.PageRequest;
import com.xw.service.RolesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("role")
@Tag(name = "角色模块")
public class RoleController {

    @Resource
    private RolesDao rolesDao;

    @Resource
    private RolesService rolesService;

    @PostMapping("page")
    @Operation(summary = "角色列表")
    public Result<?> page(@RequestBody PageRequest<Roles> pageRequest) {
        PageInfo<Roles> rolesPageInfo = rolesService.queryByPage(pageRequest);
        return new Result<>(rolesPageInfo, Constants.SUCCESS);
    }

    @PostMapping("saveOrUpdate")
    @Operation(summary = "角色的新增或修改")
    public Result<?> saveOrUpdate(@RequestBody @Validated Roles roles) {
        if (roles.getId() == null) {
            rolesDao.insert(roles);
        } else {
            rolesDao.update(roles);
        }
        return new Result<>(Constants.SUCCESS);
    }

    /**
     * 删除数据
     *
     * @return 删除是否成功
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "删除数据(ID)")
    public Result<?> delete(@PathVariable("id") Integer id) {
        rolesDao.deleteById(id);
        return new Result<>(Constants.SUCCESS);
    }

    @PostMapping("deleteByIds")
    @Operation(summary = "批量删除角色")
    public Result<?> deleteByIds(@RequestBody List<Integer> ids) {
        rolesDao.deleteBatchByIds(ids);
        return new Result<>(Constants.SUCCESS);
    }

    @GetMapping("selectAll")
    @Operation(summary = "查询所有角色信息")
    public Result<?> selectAll() {
        return new Result<>(rolesDao.queryAll(),Constants.SUCCESS);
    }

    @GetMapping("selectRoleByMenu")
    @Operation(summary = "查询角色下的菜单")
    public Result<?> selectRoleByMenu(@RequestParam Integer roleId) {
        RoleByMenuRep roleByMenuRep = rolesService.selectRoleByMenu(roleId);
        return new Result<>(roleByMenuRep,Constants.SUCCESS);
    }

    @PostMapping("addRoleMenu")
    @Operation(summary = "新增角色菜单")
    public Result<?> addRoleMenu(@RequestBody @Validated AddRoleMenuReq addRoleMenuReq) {
        rolesService.addRoleMenu(addRoleMenuReq);
        return new Result<>(Constants.SUCCESS);
    }
}
