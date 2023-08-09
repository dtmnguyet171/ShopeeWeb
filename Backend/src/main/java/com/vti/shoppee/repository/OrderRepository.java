package com.vti.shoppee.repository;

import com.vti.shoppee.modal.entity.Account;
import com.vti.shoppee.modal.entity.Order;
import com.vti.shoppee.modal.entity.StatusOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>, JpaSpecificationExecutor<Order> {
    List<Order> findAllByStatusAndAccount_Id(StatusOrder status, int accountId);
    List<Order> findAllByAccount_Id(int accountId);
//    @Query(value = "SELECT o FROM Order o WHERE o.status = :statusOrder")
//    List<Order> findAllByStatusV2(@Param("statusOrder") StatusOrder statusOrder, @Param("accountId") Account accountId);

    @Query(value = "SELECT * FROM order o WHERE o.status = :statusOrder", nativeQuery = true)
    List<Order> findAllByStatusV3(StatusOrder statusOrder);
}
