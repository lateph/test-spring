package com.mgg.springboot.controllers;

import java.util.Map;

import com.mgg.springboot.beans.FindAllResponse;
import com.mgg.springboot.dao.models.BaseEntity;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.mgg.springboot.services.BaseService;

import com.querydsl.core.types.Predicate;

public abstract class BaseController <TService extends BaseService<TModel>, TModel extends BaseEntity>{
    @Autowired
    private TService service;

    // @GetMapping("")
    public ResponseEntity<Object> baseAll(
            Predicate predicate,
            int $skip,
            int $limit,
            @RequestParam Map<String, String> requestParams) {
        FindAllResponse result = service.getAll(predicate, $skip, $limit, requestParams);
        return ResponseEntity.ok(result);
    }

    @PostMapping("")
    public TModel add(@RequestBody TModel model) {
        return service.save(model);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable int id, @RequestBody TModel model) {
        TModel result = service.update(id, model);
        return ResponseEntity.ok(result);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Object> getId(@PathVariable int id) {
        // Get data from service layer into entityList.
        Object result = service.getById(id);

        return ResponseEntity.ok(result);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> patch(@PathVariable int id, @RequestBody TModel model) {
        TModel result = service.patch(id, model);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id) {
        Object result = service.getById(id);
        service.delete(id);
        return ResponseEntity.ok(result);
    }
}
