package com.xw.service;
import com.github.pagehelper.PageInfo;
import com.xw.entity.Note;
import com.xw.req.PageRequest;

public interface NoteService {
    PageInfo<Note> page(PageRequest<Note> pageRequest);
}
