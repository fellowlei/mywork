package com.mark.service;

/**
 * Created by lulei on 2016/2/19.
 */
public class GenSqlService {
    public static void main(String[] args) {
        genDelete();
    }

    public static void genDelete(){
        for (int i = 1; i <= 768; i++) {
            System.out.println("delete from skuadwordoperation_" + i + " where created < date_add(now(),interval -4 month);");
        }
    }

    public static void genInsert(){
        for (int i = 513; i <= 768; i++) {
            System.out.println("insert into skuadwordoperation_"+i+"(skuid,adwordContent,action,created) values(100010,'100010batchtest',1,date_add(now(),interval -4 month));");
        }
    }

    public static void genIndex(){
        for(int i=1; i<= 768; i++){
            System.out.println("ALTER TABLE skuadwordoperation_"+i+" ADD INDEX idx_created(created);");
        }
    }

    public static void genDeleteIndex(){
        for(int i=1; i<= 256; i++){
            System.out.println("ALTER TABLE skuadwordoperation_"+i+" DROP INDEX idx_created;");
        }
    }
}
