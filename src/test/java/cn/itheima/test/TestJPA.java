package cn.itheima.test;


import com.itheima.pojo.Customer;
import com.itheima.utils.JAPUtil;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class TestJPA {
    /**
     * 测试jpa的保存
     *      案例：保存一个客户到数据库中
     *  Jpa的操作步骤
     *     1.加载配置文件创建工厂（实体管理器工厂）对象
     *     2.通过实体管理器工厂获取实体管理器
     *     3.获取事务对象，开启事务
     *     4.完成增删改查操作
     *     5.提交事务（回滚事务）
     *     6.释放资源
     */
    @Test
    public  void testSave(){
//        EntityManagerFactory factory = Persistence.createEntityManagerFactory( "myjpa" );
//        EntityManager em = factory.createEntityManager();
        EntityManager em = JAPUtil.getEntityManage();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Customer customer = new Customer();
        customer.setCustName( "TheShy" );
        customer.setCustIndustry( "no1" );
        em.persist( customer );
        tx.commit();

        em.close();
        //factory.close();



    }

    @Test
    public  void testFind(){
        EntityManager em = JAPUtil.getEntityManage();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Customer customer = em.find( Customer.class, 1L );
        System.out.println(customer);


        tx.commit();
        em.close();
    }
    @Test
    public  void testRefance(){
        EntityManager em = JAPUtil.getEntityManage();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Customer customer = em.getReference( Customer.class, 1L );
        System.out.println(customer);


        tx.commit();
        em.close();
    }
    @Test
    public  void testRemove(){
        EntityManager em = JAPUtil.getEntityManage();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Customer customer = em.find( Customer.class, 2L );
        em.remove( customer );


        tx.commit();
        em.close();
    }
    @Test
    public  void testRemove1(){
        EntityManager em = JAPUtil.getEntityManage();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Customer customer = em.find( Customer.class, 2L );
        em.remove( customer );


        tx.commit();
        em.close();
    }
    @Test
    public  void testUpdate(){
        EntityManager em = JAPUtil.getEntityManage();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Customer customer = em.find( Customer.class, 1L );
        customer.setCustIndustry( "上单" );
        em.merge( customer );


        tx.commit();
        em.close();
    }
}
