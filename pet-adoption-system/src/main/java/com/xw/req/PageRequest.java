package com.xw.req;

import lombok.Data;

@Data
public class PageRequest<T> {
    private Integer pageNum;
    private Integer pageSize;
    private T q;
}
