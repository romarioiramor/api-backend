package com.br.boacompra.boacompra.products;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {
    Products findById(long id);

    @Query(value = "SELECT * FROM flow_db.tbl_products WHERE productquantity <= negativebalance", nativeQuery = true)
    List<Products> findByNegativebalance();

    @Query(value = "select * FROM tbl_products tp " +
            " WHERE expirationdate <= DATE_ADD(current_timestamp(), INTERVAL 30 DAY) order by expirationdate ASC", nativeQuery = true)
    List<Products> findByExpirationDate();
}
