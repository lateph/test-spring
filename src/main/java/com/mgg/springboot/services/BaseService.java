package com.mgg.springboot.services;

import com.mgg.springboot.dao.models.BaseEntity;
import com.github.fge.jsonpatch.JsonPatch;
import com.mgg.springboot.beans.FindAllResponse;
import com.querydsl.core.types.Predicate;
import java.util.Map;
import java.util.Optional;

public interface BaseService <T extends BaseEntity> {
    T save(T model);

    FindAllResponse getAll(Predicate predicate, int $skip, int $limit, Map<String, String> requestParams);

    T getById(int id);

    void delete(int id);

    T update(int id, T model);

    T patch(int id, T model);
}
