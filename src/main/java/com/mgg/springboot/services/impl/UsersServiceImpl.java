package com.mgg.springboot.services.impl;

import com.mgg.springboot.dao.models.Users;
import com.mgg.springboot.dao.repositories.UsersRepository;
import com.mgg.springboot.exception.ResourceNotFoundException;
import com.mgg.springboot.services.UsersService;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.mgg.springboot.services.UploadFilesService;
import com.mgg.springboot.beans.UploadFileInfo;
import org.springframework.beans.factory.annotation.Autowired;

// @Slf4j
@Service
public class UsersServiceImpl extends BaseImplement<UsersRepository, Users> implements UsersService {

    @Autowired
    UploadFilesService fileservice;

    @Override
    public Users save(Users model) {

        Users newModel = new Users();
    
        newModel.setEmail(model.getEmail());
        
                        if (model.getPassword() != null && model.getPassword().trim().equals("")) {
                        BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder();
newModel.setPassword(bcryptEncoder.encode(model.getPassword()));
                        }
        newModel.setName(model.getName());

        return repository.save(newModel);
    }


    @Override
    public Users update(int id, Users model) {
        Users currentModel = getById(id);
        currentModel.setEmail(model.getEmail());
        currentModel.setPassword(model.getPassword());
        currentModel.setName(model.getName());
        return repository.save(currentModel);
    }

    @Override
    public Users patch(int id, Users model)  {
        Users newModel = getById(id);
            newModel.setEmail(model.getEmail());
            
                        if (model.getPassword() != null && model.getPassword().trim().equals("")) {
                        BCryptPasswordEncoder bcryptEncoder = new BCryptPasswordEncoder();
newModel.setPassword(bcryptEncoder.encode(model.getPassword()));
                        }
            newModel.setName(model.getName());
        return repository.save(newModel);
    }

    @Override
    public Users findByEmail(String email) {
        Users model = repository.findByEmail(email);
        if (model == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return model;
    }
}
