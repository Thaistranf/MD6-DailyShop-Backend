package com.example.dailyshop.repository.data;

import com.example.dailyshop.model.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails,Long> {
}