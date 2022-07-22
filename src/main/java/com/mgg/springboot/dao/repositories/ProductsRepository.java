package com.mgg.springboot.dao.repositories;

import java.util.Optional;

// import java.util.List;

// import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.mgg.springboot.dao.models.Products;



@Repository
public interface ProductsRepository extends BaseRepository<Products, Integer>{
    Optional<Products> findById(int id);

}
