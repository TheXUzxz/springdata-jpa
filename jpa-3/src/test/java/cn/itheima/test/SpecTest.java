package cn.itheima.test;

import cn.itcast.dao.CustomerDao;
import cn.itcast.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpecTest {
    @Autowired
    private CustomerDao customerDao;

    //匿名内部类
    /**
     * 自定义查询条件
     *      1.实现Specification接口（提供泛型：查询的对象类型）
     *      2.实现toPredicate方法（构造查询条件）
     *      3.需要借助方法参数中的两个参数（
     *          root：获取需要查询的对象属性
     *          CriteriaBuilder：构造查询条件的，内部封装了很多的查询条件（模糊匹配，精准匹配）
     *       ）
     *  案例：根据客户名称查询，查询客户名为传智播客的客户
     *          查询条件
     *              1.查询方式
     *                  cb对象
     *              2.比较的属性名称
     *                  root对象
     *
     */

    @Test
    public void testSpec(){
        Specification<Customer> spec = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?>
                    criteriaQuery, CriteriaBuilder cb) {
                Path<Object> custName = root.get( "custName" );
                //构造查询条件
                Predicate theShy = cb.equal( custName, "TheShy" );


                return theShy;
            }
        };
        Customer cus = customerDao.findOne( spec );
        System.out.println(cus);

    }
    //多条件查询

    @Test
    public void testSpec1(){
        Specification<Customer> spec=new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root,
                  CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                Path<Object> custName = root.get( "custName" );
                Path<Object> custIndustry = root.get( "custIndustry" );
                Predicate p1 = cb.equal( custName, "Faker" );
                Predicate p2 =   cb.equal( custIndustry,"Skt t1" );

                 Predicate and = cb.and( p1, p2 );//与
//cb.or(  );//或
                return and;
            }
        };
        Customer cus = customerDao.findOne( spec );
        System.out.println( cus );

    }
    //模糊查询
    @Test
    public void testSpec3(){
        Specification<Customer> spec = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root,
               CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                Path<Object> custName = root.get( "custName" );
                Predicate like = cb.like( custName.as( String.class ), "The%" );
                return like;
            }
        };
//        List<Customer> list = customerDao.findAll( spec );
        Sort sort = new Sort( Sort.Direction.DESC,"custId" );
        List<Customer> list = customerDao.findAll( spec, sort );
        System.out.println( list );
    }
    //分页查询

    @Test
    public void testFindPage(){
        Specification spec =null;
        Pageable pageable = new PageRequest(0,2);

        Page page = customerDao.findAll( spec, pageable );
        System.out.println( page.getContent() );
        System.out.println(page.getTotalElements());//总条数
        System.out.println(page.getTotalPages());//总页数
    }


}
