package com.xw.service.impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xw.dao.NoteDao;
import com.xw.entity.*;
import com.xw.req.PageRequest;
import com.xw.service.NoteService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class NoteServiceImpl implements NoteService {
    @Resource
    private NoteDao noteDao;

    @Override
    public PageInfo<Note> page(PageRequest<Note> pageRequest) {
        PageHelper.startPage(pageRequest.getPageNum(),pageRequest.getPageSize());
        List<Note> notes = noteDao.selectAll(pageRequest.getQ());
        PageInfo<Note> pageInfo = new PageInfo<>(notes);
        return pageInfo;
    }
}
