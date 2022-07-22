package com.mgg.springboot.dao.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import com.mgg.springboot.dao.models.BaseEntity;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, ID> extends JpaRepository<T, ID> , QuerydslPredicateExecutor<T>{
    // Optional<T> findById(int id);
    Page<T> findByIdIn(List<ID> ids, Pageable pageable);
}
