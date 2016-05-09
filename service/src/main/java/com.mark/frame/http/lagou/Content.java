package com.mark.frame.http.lagou;

import java.util.List;

/**
 * Created by lulei on 2016/4/29.
 */
public class Content {
    private int totalCount;

    private int pageNo;

    private int pageSize;

    private boolean hasNextPage;

    private int totalPageCount;

    private int currentPageNo;

    private boolean hasPreviousPage;

    private List<Result> result ;

    private int start;

    public void setTotalCount(int totalCount){
        this.totalCount = totalCount;
    }
    public int getTotalCount(){
        return this.totalCount;
    }
    public void setPageNo(int pageNo){
        this.pageNo = pageNo;
    }
    public int getPageNo(){
        return this.pageNo;
    }
    public void setPageSize(int pageSize){
        this.pageSize = pageSize;
    }
    public int getPageSize(){
        return this.pageSize;
    }
    public void setHasNextPage(boolean hasNextPage){
        this.hasNextPage = hasNextPage;
    }
    public boolean getHasNextPage(){
        return this.hasNextPage;
    }
    public void setTotalPageCount(int totalPageCount){
        this.totalPageCount = totalPageCount;
    }
    public int getTotalPageCount(){
        return this.totalPageCount;
    }
    public void setCurrentPageNo(int currentPageNo){
        this.currentPageNo = currentPageNo;
    }
    public int getCurrentPageNo(){
        return this.currentPageNo;
    }
    public void setHasPreviousPage(boolean hasPreviousPage){
        this.hasPreviousPage = hasPreviousPage;
    }
    public boolean getHasPreviousPage(){
        return this.hasPreviousPage;
    }
    public void setResult(List<Result> result){
        this.result = result;
    }
    public List<Result> getResult(){
        return this.result;
    }
    public void setStart(int start){
        this.start = start;
    }
    public int getStart(){
        return this.start;
    }
}
