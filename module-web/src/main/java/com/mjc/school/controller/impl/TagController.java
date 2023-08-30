package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.annotation.CommandHandler;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TagController implements BaseController<TagDtoRequest, TagDtoResponse, Long> {
    private final BaseService<TagDtoRequest, TagDtoResponse, Long> tagService;

    @Autowired
    public TagController(BaseService<TagDtoRequest, TagDtoResponse, Long> tagService) {
        this.tagService = tagService;
    }

    @Override
    @CommandHandler(operation = 3)
    public List<TagDtoResponse> readAll() {
        return tagService.readAll();
    }

    @Override
    @CommandHandler(operation = 6)
    public TagDtoResponse readById(Long id) {
        return tagService.readById(id);
    }

    @Override
    @CommandHandler(operation = 9)
    public TagDtoResponse create(TagDtoRequest createRequest) {
        return tagService.create(createRequest);
    }

    @Override
    @CommandHandler(operation = 12)
    public TagDtoResponse update(TagDtoRequest updateRequest) {
        return tagService.update(updateRequest);
    }

    @Override
    @CommandHandler(operation = 15)
    public boolean deleteById(Long id) {
        return tagService.deleteById(id);
    }
}
