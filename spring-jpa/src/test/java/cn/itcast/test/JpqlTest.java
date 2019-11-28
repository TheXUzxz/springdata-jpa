package cn.itcast.test;

import cn.itcast.dao.CustomerDao;
import cn.itcast.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JpqlTest {
        @Autowired
        private CustomerDao customerDao;
        @Test
       public void testFindJpqlByName(){
           List<Customer> list = customerDao.findJpqlByName( "Theshy" );
           System.out.println(list);
       }
    @Test
    public void testFindCustmoerNameAndId(){
        Customer customer = customerDao.findCustmoerNameAndId( "TheShy", 1L );
        System.out.println(customer);

    }
    @Test
    @Transactional
    @Rollback(false)
    public void testUpdateCustname(){
       customerDao.updateCustName( 4L,"TheXu" );

    }
    @Test
    public void testFindAll(){
        List<Object[]> object = customerDao.findAllBySql("TheShy");
        for (Object[] obj: object) {
            System.out.println( Arrays.toString(obj));
        }
    }

    @Test //方法命名规则查询
    public void testFindCustName(){
        Customer theXu = customerDao.findByCustName( "TheXu" );
        System.out.println(theXu);
    }
    @Test //方法命名规则查询
    public void testFindCustNameLike(){
        List<Customer> list = customerDao.findByCustNameLike( "The%" );
        list.forEach( System.out::println );
    }
    @Test
    public void findByCustNameAndCustId(){
        Customer theXu = customerDao.findByCustNameAndCustId( "TheXu", 4L );
        System.out.println( theXu );
    }



}
