package com.mgg.springboot.services.impl;

import com.mgg.springboot.dao.models.ProductCategories;
import com.mgg.springboot.dao.repositories.ProductCategoriesRepository;
import com.mgg.springboot.exception.ResourceNotFoundException;
import com.mgg.springboot.services.ProductCategoriesService;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.mgg.springboot.services.UploadFilesService;
import com.mgg.springboot.beans.UploadFileInfo;
import org.springframework.beans.factory.annotation.Autowired;

// @Slf4j
@Service
public class ProductCategoriesServiceImpl extends BaseImplement<ProductCategoriesRepository, ProductCategories> implements ProductCategoriesService {

    @Autowired
    UploadFilesService fileservice;



    @Override
    public ProductCategories update(int id, ProductCategories model) {
        ProductCategories currentModel = getById(id);
        currentModel.setName(model.getName());
        return repository.save(currentModel);
    }

    @Override
    public ProductCategories patch(int id, ProductCategories model)  {
        ProductCategories newModel = getById(id);
            newModel.setName(model.getName());
        return repository.save(newModel);
    }
}
