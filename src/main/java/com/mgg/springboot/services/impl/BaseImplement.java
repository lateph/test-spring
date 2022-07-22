package com.mgg.springboot.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.mgg.springboot.beans.FindAllResponse;
import com.mgg.springboot.dao.models.BaseEntity;
import com.mgg.springboot.dao.repositories.BaseRepository;
import com.mgg.springboot.exception.ResourceNotFoundException;
import com.mgg.springboot.utils.CommonUtil;
import com.querydsl.core.types.Predicate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class BaseImplement<T extends BaseRepository<M,Integer>, M extends BaseEntity> {
    @Autowired
    protected T repository;

    public M save(M model) {
        // overide if need password or some shit
        return repository.save(model);
    }

    public FindAllResponse getAll(Predicate predicate, int $skip, int $limit, Map<String, String> requestParams) {
        // STEP 1: Process Sort
        List<Order> orders = CommonUtil.processSort($skip, $limit, requestParams);

        // STEP 2: Get Data
        Page<M> result;
        if(requestParams.get("ids") == null) {
            result = repository.findAll(predicate,
                PageRequest.of($skip / $limit, $limit, Sort.by(orders)));
        } else {
            List<Integer> ids = Arrays.asList(requestParams.get("ids").split(",")).stream().map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());
            result = repository.findByIdIn(ids,
                PageRequest.of($skip / $limit, $limit, Sort.by(orders)));
        }
        

        // STEP 3: Format response
        FindAllResponse object = new FindAllResponse(result.getTotalElements(), $limit, $skip, result.getContent());

        return object;
    }

    public M getById(int id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(String.format("Employee with ID %s not found", id)));
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}
