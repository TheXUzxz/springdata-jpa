package cn.itcast.dao;

import cn.itcast.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CustomerDao extends JpaRepository<Customer,Long>,
        JpaSpecificationExecutor<Customer> {

        //根据名称查询
    @Query("from  Customer where custName= ?")
    public List<Customer> findJpqlByName(String name);
    @Query("from  Customer  where custName =? and custId =?")
    public Customer findCustmoerNameAndId(String name,Long id);

    @Query("update  Customer set custName =?2 where custId =?1 ")
    @Modifying//代表更新操作
    public void updateCustName(Long custId,String custName);


    //sql查询
   // @Query(value = "select  * from cst_customer",nativeQuery = true)
    @Query(value = "select  * from  cst_customer where  cust_name like ?",nativeQuery = true)
    public List<Object []> findAllBySql(String name);


    public Customer findByCustName(String custName);

    public List<Customer> findByCustNameLike(String custName);

    //多条件查询
    public Customer findByCustNameAndCustId(String name,Long id);


}
