package com.itheima.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JAPUtil {
    private static  EntityManagerFactory factory;
    static {

        factory = Persistence.createEntityManagerFactory( "myjpa" );

    }
    public static EntityManager getEntityManage(){
        return  factory.createEntityManager();
    }
}
