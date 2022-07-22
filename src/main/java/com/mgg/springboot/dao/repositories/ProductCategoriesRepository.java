package com.mgg.springboot.dao.repositories;

import java.util.Optional;

// import java.util.List;

// import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.mgg.springboot.dao.models.ProductCategories;



@Repository
public interface ProductCategoriesRepository extends BaseRepository<ProductCategories, Integer>{
    Optional<ProductCategories> findById(int id);

}
