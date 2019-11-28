package cn.itcast.test;

import cn.itcast.dao.CustomerDao;
import cn.itcast.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CustomerDaoTest {
        @Autowired
        private CustomerDao customerDao;
        @Test
        public void testFindOne(){
            Customer customer = customerDao.findOne( 1L );
            System.out.println(customer  );

        }
        //如果传递的id没有主键则保存,有则更新
        @Test
        public void testSave(){
            Customer customer = new Customer();
            customer.setCustName( "faker" );
            customer.setCustLevel( "VIP" );
            customer.setCustIndustry( "skt" );
           customerDao.save( customer );

        }
    @Test
    public void testUpdate(){
        Customer customer = new Customer();
        customer.setCustId( 5L );
        customer.setCustName( "Faker" );
        customer.setCustLevel( "VIP" );
        customer.setCustIndustry( "Skt t1" );
        customer.setCustSource( "666" );
        customerDao.save( customer );

    }
    @Test
    public void testDelete(){
            customerDao.delete( 3L );
    }
    @Test
    public void testFindAll(){
        List<Customer> list = customerDao.findAll();
        list.forEach( System.out::println );
    }
    @Test
    public void testCount(){
        long count = customerDao.count();
        System.out.println(count);
    }
    //是否存在
    @Test
    public void testExit(){
        boolean exists = customerDao.exists( 6L );
        System.out.println( exists );
    }
    @Test
    @Transactional
    public void testgetOne(){
        Customer customer = customerDao.getOne( 4L );
        System.out.println( customer );
    }



}
