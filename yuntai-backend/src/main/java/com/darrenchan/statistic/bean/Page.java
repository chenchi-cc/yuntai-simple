package com.darrenchan.statistic.bean;

import java.util.List;

public class Page<T> {
    public Integer total;  //总页数
    public Integer size;  //每页数据量
    public Integer current;  //当前页的页码
    public List<T> records;  //分页中的数据列表

    public Page() {
    }
}
