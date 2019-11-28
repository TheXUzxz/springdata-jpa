package cn.itheima.test;

import com.itheima.utils.JAPUtil;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class JpqlTest {
    @Test
    public void testFindAll(){
        EntityManager em = JAPUtil.getEntityManage();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Query query = em.createQuery( "from  com.itheima.pojo.Customer" );
        List list = query.getResultList();
        list.forEach( System.out::println );



        tx.commit();

        em.close();

    }
    @Test
    public void testOrder(){
        EntityManager em = JAPUtil.getEntityManage();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Query query = em.createQuery( "from  Customer order by custId desc " );
        List list = query.getResultList();
        list.forEach( System.out::println );



        tx.commit();

        em.close();

    }

    @Test
    public void testPage(){
        EntityManager em = JAPUtil.getEntityManage();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Query query = em.createQuery( " from  Customer " );
        query.setFirstResult( 0 );
        query.setMaxResults( 2 );
        List list = query.getResultList();
        list.forEach( System.out::println );

        tx.commit();
        em.close();
    }
    @Test
    public void testCondition(){
        EntityManager em = JAPUtil.getEntityManage();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Query query = em.createQuery( " from  Customer where  custName like ?" );
        query.setParameter( 1, "The%" );
        List list = query.getResultList();
        list.forEach( System.out::println );


        tx.commit();
        em.close();
    }

}
