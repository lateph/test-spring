package com.mgg.springboot.dao.repositories;

import java.util.Optional;

// import java.util.List;

// import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.mgg.springboot.dao.models.Users;



@Repository
public interface UsersRepository extends BaseRepository<Users, Integer>{
    Optional<Users> findById(int id);

    Users findByEmail(String email);
}
