package com.mjc.school.service.aspect;

import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.annotation.OnDeleteCascade;
import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.validator.Validator;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;

@Aspect
@Component
public class ServiceAspect {


    @Before("@annotation(com.mjc.school.service.annotation.ValidateAuthorId) && args(id)")
    public void validateAuthorId(Long id) {
        Validator.validateAuthorId(id);
    }

    @Before("@annotation(com.mjc.school.service.annotation.ValidateAuthorParam) && args(request)")
    public void validateAuthorDto(AuthorDtoRequest request) {
        Validator.validateAuthorDto(request);

    }

    @Before("@annotation(com.mjc.school.service.annotation.ValidateNewsId) && args(id)")
    public void validateNewsId(Long id) {
        Validator.validateAuthorId(id);
    }

    @Before("@annotation(com.mjc.school.service.annotation.ValidateNewsParam) && args(request)")
    public void validateNewsDto(NewsDtoRequest request) {
        Validator.validateNewsDto(request);

    }
}
