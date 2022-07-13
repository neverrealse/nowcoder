package com.nowcoder.community.entity;

//封装分页相关的信息   提高复用性

public class Page {
    //当前页码  客户端传入
    private int current = 1;
    //显示的上限  客户端传入
    private int limit = 10;
    //数据的总数(用于计算总页数 显示页码)
    private int rows;
    //查询路径（用于复用分页的链接）
    private String path;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        if(current>=1){
            this.current = current;
        }
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        //为提高服务器与浏览器的效率
        if(limit >= 1 && limit <= 100){
            this.limit = limit;
        }
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if(rows >= 0){
            this.rows = rows;
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    //计算当前页的起始行
    public int getOffset(){
        return (current-1)*limit;
    }

    //获取总页数
    public int getTotal(){
        if(rows % limit == 0){
            return rows / limit;
        }else{
            return rows / limit + 1;
        }
    }

    //获取起始页码  -2 -1 0 1 2
    public int getFrom(){
        int from = current - 2;
        return from > 1  ? from : 1; //规定左侧边界
    }

    //获取终止页码
    public int getTo(){
        int to = current + 2;
        int total = getTotal();
        return to > total ? total : to;
    }
}
