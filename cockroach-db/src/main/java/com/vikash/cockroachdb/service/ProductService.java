package com.vikash.cockroachdb.service;


import com.vikash.cockroachdb.model.Product;
import com.vikash.cockroachdb.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // insert
    public void createProduct(Product product){
        UUID uuid = UUID.randomUUID();
        product.setId(uuid);
        this.productRepository.save(product);
    }

    // select all
    public List<Product> getAllProducts(){
        return this.productRepository.findAll();
    }

    // delete
    @Transactional
    public void deleteProduct(UUID id){
        this.productRepository
                .findById(id)
                .ifPresent(this.productRepository::delete);
    }

    // update
    @Transactional
    public Product updateProduct(Product product){
        return this.productRepository
               .save(product);
    }
    
    public List<Product> getAllEmployees(Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
 
        Page<Product> pagedResult = productRepository.findAll(paging);
         
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Product>();
        }
    }
    
    public List<Product> listAll(String keyword) {
        if (keyword != null) {
            return productRepository.search(keyword);
        }
        return productRepository.findAll();
    }
    
    
//    public List<Product> searchProducts(String query) {
//        List<Product> products = productRepository.searchProducts(query);
//        return products;
//    }

}