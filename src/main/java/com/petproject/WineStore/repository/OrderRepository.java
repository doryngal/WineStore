package com.petproject.WineStore.repository;

import com.petproject.WineStore.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT b FROM Order b WHERE b.id = :orderId AND b.user.id = :userId")
    Order findByIdAndUserId(@Param("orderId") Long orderId, @Param("userId") Long userId);

    @Query("SELECT b FROM Order b WHERE b.user.id = :userId")
    Order findOrderByUserId(@Param("userId") Long userId);
}
