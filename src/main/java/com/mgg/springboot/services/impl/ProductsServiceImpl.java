package com.mgg.springboot.services.impl;

import com.mgg.springboot.dao.models.Products;
import com.mgg.springboot.dao.repositories.ProductsRepository;
import com.mgg.springboot.exception.ResourceNotFoundException;
import com.mgg.springboot.services.ProductsService;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.mgg.springboot.services.UploadFilesService;
import com.mgg.springboot.beans.UploadFileInfo;
import org.springframework.beans.factory.annotation.Autowired;

// @Slf4j
@Service
public class ProductsServiceImpl extends BaseImplement<ProductsRepository, Products> implements ProductsService {

    @Autowired
    UploadFilesService fileservice;

    @Override
    public Products save(Products model) {

        Products newModel = new Products();
    
        newModel.setName(model.getName());
        newModel.setPrice(model.getPrice());
        newModel.setDescription(model.getDescription());
        newModel.setTags(model.getTags());
        newModel.setCategoriesId(model.getCategoriesId());
        UploadFileInfo urlImage = fileservice.saveBase64(model.getImage());
                        if (urlImage != null) {
                            newModel.setImage(urlImage.getUrl());
                        }

        return repository.save(newModel);
    }


    @Override
    public Products update(int id, Products model) {
        Products currentModel = getById(id);
        currentModel.setName(model.getName());
        currentModel.setPrice(model.getPrice());
        currentModel.setDescription(model.getDescription());
        currentModel.setTags(model.getTags());
        currentModel.setCategoriesId(model.getCategoriesId());
        currentModel.setImage(model.getImage());
        return repository.save(currentModel);
    }

    @Override
    public Products patch(int id, Products model)  {
        Products newModel = getById(id);
            newModel.setName(model.getName());
            newModel.setPrice(model.getPrice());
            newModel.setDescription(model.getDescription());
            newModel.setTags(model.getTags());
            newModel.setCategoriesId(model.getCategoriesId());
            UploadFileInfo urlImage = fileservice.saveBase64(model.getImage());
                        if (urlImage != null) {
                            newModel.setImage(urlImage.getUrl());
                        }
        return repository.save(newModel);
    }
}
