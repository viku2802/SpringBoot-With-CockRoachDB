package com.vikash.cockroachdb.controller;


import com.sipios.springsearch.anotation.SearchSpec;
import com.vikash.cockroachdb.model.Product;
import com.vikash.cockroachdb.repository.ProductRepository;
import com.vikash.cockroachdb.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/create")
    public void createProduct(@RequestBody Product product){
        this.productService.createProduct(product);
    }

    @GetMapping("/all")
    public Mono<List<Product>> getAllProducts(){
        return Mono.fromSupplier(() -> this.productService.getAllProducts());
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable final String id){
        this.productService.deleteProduct(UUID.fromString(id));
    }

    @PutMapping("/update")
    public Product updateProduct(@RequestBody final Product product){
       return this.productService.updateProduct(product);
    }
    
    @GetMapping("/paginationSorting")
    public ResponseEntity<List<Product>> getAllEmployees(
                        @RequestParam(defaultValue = "0") Integer pageNo,
                        @RequestParam(defaultValue = "") Integer pageSize,
                        @RequestParam(defaultValue = "id") String sortBy)
    {
        List<Product> list = productService.getAllEmployees(pageNo, pageSize, sortBy);
 
        return new ResponseEntity<List<Product>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    @GetMapping("/search")
    public List<Product> viewHomePage(Model model, @Param("keyword") String keyword) {
        List<Product> listProducts = productService.listAll(keyword);
        model.addAttribute("listProducts", listProducts);
        model.addAttribute("keyword", keyword);
         
        return listProducts;
    }
    
//    @GetMapping("/search1")
//    public ResponseEntity<List<Product>> searchProducts(@RequestParam("query") String query){
//        return ResponseEntity.ok(productService.searchProducts(query));
//    }
//    
//    @GetMapping("/cars")
//    public ResponseEntity<List<Product>> searchForCars(@SearchSpec Specification<Product> specs) {
//        return new ResponseEntity<>(productRepository.findAll(Specification.where(specs)), HttpStatus.OK);
//    }
 

}