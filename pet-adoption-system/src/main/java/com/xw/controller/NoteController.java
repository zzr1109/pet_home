package com.xw.controller;
import com.xw.common.Constants;
import com.xw.common.Result;
import com.xw.common.TokenHandler;
import com.xw.dao.NoteDao;
import com.xw.entity.Note;
import com.xw.entity.User;
import com.xw.req.PageRequest;
import com.xw.service.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("note")
@Tag(name = "系统公告模块")
public class NoteController {
    @Resource
    private NoteService noteService;

    @Resource
    private NoteDao noteDao;

    @PostMapping("page")
    @Operation(summary = "公告列表")
    public Result<?> page(@RequestBody PageRequest<Note> pageRequest) {
        return new Result<>(noteService.page(pageRequest), Constants.SUCCESS);
    }

    @PostMapping("saveOrUpdate")
    @Operation(summary = "新增或修改公告")
    public Result<?> saveOrUpdate(@RequestBody @Validated Note note) {
        note.setCreateTime(new Date());
        if (note.getId() == null) {
            User userInfo = TokenHandler.getUserInfo();
            note.setUserId(userInfo.getId());
            note.setUserName(userInfo.getUserName());
            note.setUserImage(userInfo.getImageUrl());
            noteDao.insert(note);
        } else {
            noteDao.updateEntity(note);
        }
        return new Result<>(Constants.SUCCESS);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "通过ID删除公告信息")
    public Result<?> deleteById(@PathVariable("id") Integer id) {
        noteDao.deleteById(id);
        return new Result<>(Constants.SUCCESS);
    }

    @PostMapping("deleteBatchByIds")
    @Operation(summary = "通过ID批量删除公告信息")
    public Result<?> deleteBatchByIds(@RequestBody List<Integer> ids) {
        noteDao.deleteBatchByIds(ids);
        return new Result<>(Constants.SUCCESS);
    }
}
