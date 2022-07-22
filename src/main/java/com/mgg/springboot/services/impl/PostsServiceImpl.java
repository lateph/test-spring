package com.mgg.springboot.services.impl;

import com.mgg.springboot.dao.models.Posts;
import com.mgg.springboot.dao.repositories.PostsRepository;
import com.mgg.springboot.exception.ResourceNotFoundException;
import com.mgg.springboot.services.PostsService;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.mgg.springboot.services.UploadFilesService;
import com.mgg.springboot.beans.UploadFileInfo;
import org.springframework.beans.factory.annotation.Autowired;

// @Slf4j
@Service
public class PostsServiceImpl extends BaseImplement<PostsRepository, Posts> implements PostsService {

    @Autowired
    UploadFilesService fileservice;



    @Override
    public Posts update(int id, Posts model) {
        Posts currentModel = getById(id);
        currentModel.setTitle(model.getTitle());
        currentModel.setUserId(model.getUserId());
        return repository.save(currentModel);
    }

    @Override
    public Posts patch(int id, Posts model)  {
        Posts newModel = getById(id);
            newModel.setTitle(model.getTitle());
            newModel.setUserId(model.getUserId());
        return repository.save(newModel);
    }
}
