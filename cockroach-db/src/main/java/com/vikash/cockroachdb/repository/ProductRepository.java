package com.vikash.cockroachdb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vikash.cockroachdb.model.Product;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
	

    Page<Product> findAll(Pageable pageable);

    Page<Product> findByTid(String tid, Pageable pageable);
    
    @Query(value="SELECT p FROM Product p WHERE p.tid LIKE %?1%"

           + " OR p.aid LIKE %?1%"
           +" OR p.aidType LIKE %?1%"
           + " OR p.faultType LIKE %?1%"
           +" OR p.severity LIKE %?1%"
           + " OR p.neModel LIKE %?1%"
           +" OR p.conditionDesc LIKE %?1%"
           + " OR p.condition LIKE %?1%"
           +" OR p.location LIKE %?1%"
           
           +" OR p.direction LIKE %?1%"
           + " OR p.serviceEffected LIKE %?1%"
           +" OR p.conditionEffect LIKE %?1%"
           + " OR p.aidDetail LIKE %?1%"
           +" OR p.rawMsg LIKE %?1%"
    		
    		)
           // + " OR CONCAT(p.price, '') LIKE %?1%")
    List<Product> search(String query);
}