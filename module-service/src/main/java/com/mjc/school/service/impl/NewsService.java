package com.mjc.school.service.impl;


import com.mjc.school.repository.BaseRepository;
import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.repository.model.impl.NewsModel;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.annotation.ValidateAuthorParam;
import com.mjc.school.service.annotation.ValidateNewsId;
import com.mjc.school.service.annotation.ValidateNewsParam;
import com.mjc.school.service.constants.Constants;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import com.mjc.school.service.exception.ErrorCode;
import com.mjc.school.service.exception.NotFoundException;
import com.mjc.school.service.mapper.NewsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class NewsService implements BaseService<NewsDtoRequest, NewsDtoResponse, Long> {
    private final BaseRepository<NewsModel,Long> newsRepository;
    private final BaseRepository<AuthorModel, Long> authorRepository;

    @Autowired
    public NewsService(BaseRepository<NewsModel, Long> newsRepository, BaseRepository<AuthorModel, Long> authorRepository) {
        this.newsRepository = newsRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<NewsDtoResponse> readAll() {
        return newsRepository.readAll().stream().map(NewsMapper.INSTANCE::newsToDtoResponse).toList();
    }

    @Override
    @ValidateNewsId
    public NewsDtoResponse readById(Long id) {
        Optional<NewsModel> newsModel = newsRepository.readById(id);
        if (newsModel.isPresent()) {
            return NewsMapper.INSTANCE.newsToDtoResponse(newsModel.get());
        }
        throw new NotFoundException(
                String.format(ErrorCode.NOT_FOUND_DATA.getMessage(), Constants.AUTHOR, id));
    }

    @Override
    @ValidateNewsParam
    public NewsDtoResponse create(NewsDtoRequest createRequest) {
      if(authorRepository.existById(createRequest.getAuthorId())){
          NewsModel newsModel = newsRepository.create(NewsMapper.INSTANCE.newsFromDtoRequest(createRequest));
          return NewsMapper.INSTANCE.newsToDtoResponse(newsModel);
      }
        throw new NotFoundException(
                String.format(ErrorCode.NOT_FOUND_DATA.getMessage(), Constants.AUTHOR, createRequest.getAuthorId()));
    }

    @Override
    @ValidateNewsParam
    public NewsDtoResponse update(NewsDtoRequest updateRequest) {
        if(newsRepository.existById(updateRequest.getId())){
            NewsModel newsModel = newsRepository.update(NewsMapper.INSTANCE.newsFromDtoRequest(updateRequest));
            return NewsMapper.INSTANCE.newsToDtoResponse(newsModel);
        }
        throw new NotFoundException(
                String.format(ErrorCode.NOT_FOUND_DATA.getMessage(), Constants.NEWS_ID, updateRequest.getId()));
    }

    @Override
    @ValidateNewsId
    public boolean deleteById(Long id) {
        if(newsRepository.existById(id)){

            return newsRepository.deleteById(id);
        }
        throw new NotFoundException(
                String.format(ErrorCode.NOT_FOUND_DATA.getMessage(), Constants.NEWS_ID, id));
    }
}
