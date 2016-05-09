package com.mark.gen;

/**
 * Created by lulei on 2016/3/21.
 */
public class PageTest {
    public static void main(String[] args) {
        new PageTest().page2();
    }
    public void page1(){
        int total = 10;
        int pagesize = 3;
        int index = 0;
        int to = 0;
        while (index < total) {
            to = index + pagesize;
            if(to >= total){
                to = total;
            }
            System.out.println(index + ":" + to);
            index = index + pagesize;
        }
    }

    public void page2(){
        int total = 5;
        int pagesize = 3;
        int totalPage = total % pagesize == 0 ? total / pagesize : total / pagesize + 1;
        for(int i=0; i<totalPage; i++){
            int start = i * pagesize;
            int end = start + pagesize > total ? total : start + pagesize;
            System.out.println(start + ":" + end);
        }

    }
}
