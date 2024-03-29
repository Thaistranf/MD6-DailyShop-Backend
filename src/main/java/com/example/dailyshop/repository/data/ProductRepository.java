package com.example.dailyshop.repository.data;

import com.example.dailyshop.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


    List<Product> findProductByproductNameContaining(String name);

    List<Product> findProductByAccountIdAndIsDeleted(Long id, boolean deleted, Sort sort);

    List<Product> findAllByCategoryId(Long id);

    List<Product> findAllByIsDeleted(boolean deleted, Sort sort);

    Page<Product> findAllByIsDeleted(boolean deleted, Pageable pageable);
    @Query("SELECT p FROM Product p JOIN Category c ON p.category.id = c.id WHERE p.productName LIKE %:productName% AND p.isDeleted = false AND EXISTS (SELECT 1 FROM Product subp WHERE subp.productName = p.productName AND subp.category = p.category AND subp.price BETWEEN :minPrice AND :maxPrice)")
    List<Product> findProductsByConditions(
            @Param("productName") String productName,
            @Param("minPrice") int minPrice,
            @Param("maxPrice") int maxPrice
    );

    @Query("SELECT p FROM Product p WHERE p.productName LIKE %:name% ORDER BY p.price desc")
    List<Product> findByProductNameContaining(@Param("name") String name);

    @Query("SELECT p FROM Product p WHERE p.productName LIKE %:name% ORDER BY p.price")
    List<Product> findProductsByProductNameContaining(@Param("name") String name);

    List<Product> findAllByIsDeleted(boolean deleted);
  
    List<Product> findAllByCategoryId(Long id);

    @Query(value = "select ord.product, p.productName,sum(ord.quantity) as totalQuantity from OrderDetails ord join Product p on p.productID = ord.product.productID join Order o on o.orderId = ord.orderId where o.orderStatus = 'Paid' group by p.productName,ord.product order by totalQuantity desc limit 5")
    List<Product> findTop5Products();
}
