package com.mgg.springboot.dao.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.mgg.springboot.dao.models.Users;
import com.mgg.springboot.dao.repositories.UsersRepository;
import com.mgg.springboot.services.UsersService;

@Service
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    private UsersRepository usersRepositoryxx;

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
        System.out.println("jancokxx");
        // SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        System.out.println("jancok"+email);
        try {
            Users user = usersRepositoryxx.findByEmail(email);
        System.out.println("jancok3");
            return true;
        } catch (Exception e) {
            System.out.println("jancok"+e.getMessage());
            return false;
        }
        
    }
}