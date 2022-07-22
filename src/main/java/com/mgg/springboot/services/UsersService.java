package com.mgg.springboot.services;

import com.mgg.springboot.dao.models.Users;

public interface UsersService extends BaseService<Users>{
    Users findByEmail(String email);
}

